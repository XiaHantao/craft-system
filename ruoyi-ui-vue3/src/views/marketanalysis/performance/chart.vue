<template>
  <div class="app-container">
    <!-- 参数选择区域 -->
    <div class="param-selector compact">
      <div class="selector-title">对比参数选择：</div>
      <div class="selector-content">
        <el-checkbox-group 
          v-model="tempSelectedFields" 
          class="compact-checkbox-group"
        >
          <el-checkbox
            v-for="(label, field) in filteredAvailableFields"
            :key="field"
            :label="field"
          >
            {{ label }}
          </el-checkbox>
        </el-checkbox-group>
        <el-button 
          type="primary" 
          @click="generateChart"
          class="confirm-btn"
        >
          生成图表
        </el-button>
      </div>
    </div>

    <!-- 对比选择框 -->
    <div class="compare-selectors">
      <div class="selector-item" v-for="n in 2" :key="n">
        <div class="selector-container">
          <div class="mode-switcher">
            <el-button 
              size="mini" 
              @click="switchMode(n)"
              :type="inputMode[n-1] ? 'success' : ''"
            >
              {{ inputMode[n-1] ? '切换选择' : '手动输入' }}
            </el-button>
          </div>

          <!-- 下拉模式 -->
          <div v-if="!inputMode[n-1]" class="input-wrapper">
            <el-select
              v-model="selectedProducts[n-1]"
              filterable
              remote
              :placeholder="`请选择车型-制造商作为车型${n}`"
              :remote-method="searchProducts"
              @change="val => handleSelectChange(n, val)"
            >
              <el-option
                v-for="item in productOptions"
                :key="item.id"
                :label="`${item.vehicleType} - ${item.manufacturer}`"
                :value="item.id"
              />
            </el-select>
          </div>

          <!-- 输入模式 -->
          <div v-else class="input-wrapper">
            <el-input
              v-model="manualInputs[n-1].vehicleType"
              placeholder="输入车型"
              style="margin-bottom: 5px"
            />
            <el-input
              v-model="manualInputs[n-1].manufacturer"
              placeholder="输入制造商"
              style="margin-bottom: 5px"
            />
            <div class="btn-group">
              <el-button
                type="primary"
                size="mini"
                @click="handleManualConfirm(n)"
              >确认</el-button>
              <el-button
                size="mini"
                @click="inputMode[n-1] = false"
              >取消</el-button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 图表容器 -->
    <div 
      ref="chartContainer" 
      style="width: 90%; height: 400px; margin: 20px auto;"
    ></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import { listPerformance, getPerformance } from '@/api/marketanalysis/performance/performance';

const excludedFields = [
  'vehicleType', 'manufacturer', 
  'travelBrake', 'parkingBrake'
];

let chartInstance = null; // 使用普通变量而非响应式对象

export default {
  name: 'PerformanceBarChart',
  data() {
    return {
      selectedProducts: [null, null],
      inputMode: [false, false],
      manualInputs: [
        { vehicleType: '', manufacturer: '' },
        { vehicleType: '', manufacturer: '' }
      ],
      productOptions: [],
      tempSelectedFields: [
        'maximumDrivingSpeedFull', 'maxClimbCapabilityFull'
      ],
      availableFields: {
        vehicleType: '车型',
        manufacturer: '制造商',
        maximumDrivingSpeedFull: '最高行驶速度（满载）',
        maximumDrivingSpeedEmpty: '最高行驶速度（空载）',
        maxClimbCapabilityFull: '最大爬坡能力（满载）',
        maxClimbCapabilityEmpty: '最大爬坡能力（空载）',
        maximumTractionForceFull: '最大牵引力（满载）',
        maximumTractionForceEmpty: '最大牵引力（空载）',
        liftingSpeedFull: '起升速度（满载）',
        liftingSpeedEmpty: '起升速度（空载）',
        descentSpeedFull: '下降速度（满载）',
        descentSpeedEmpty: '下降速度（空载）',
        runningSpeedFull: '运行速度（满载）',
        runningSpeedEmpty: '运行速度（空载）',
        increaseSpeedFull: '提升速度（满载）',
        increaseSpeedEmpty: '提升速度（空载）',
        lateralMovementSpeedFull: '侧移速度（满载）',
        lateralMovementSpeedBehind: '侧移速度（空载）',
        bridgeLoadFullyLoadedFront: '桥负荷（满载）前',
        bridgeLoadFullyLoadedBehind: '桥负荷（满载）后',
        bridgeLoadUnloadedFront: '桥负荷（空载）前',
        bridgeLoadUnloadedBehind: '桥负荷（空载）后'
      },
      product1: null,
      product2: null
    };
  },
  computed: {
    filteredAvailableFields() {
      return Object.fromEntries(
        Object.entries(this.availableFields)
          .filter(([key]) => !excludedFields.includes(key))
      );
    }
  },
  methods: {
    async generateChart() {
      if (!this.product1 || !this.product2) {
        this.$message.warning('请先选择两个对比车型');
        return;
      }

      const option = this.buildChartOption();
      if (!chartInstance) {
        chartInstance = echarts.init(this.$refs.chartContainer);
      }
      chartInstance.setOption(option);
    },

    buildChartOption() {
      const categories = this.tempSelectedFields.map(
        field => this.availableFields[field]
      );

      // 修改点1：直接使用原始数值
      const baseValues = this.tempSelectedFields.map(field =>
        this.extractNumber(this.product1[field])
      );

      const compareValues = this.tempSelectedFields.map(field =>
        this.extractNumber(this.product2[field]) // 直接取车型2的数值
      );

      return {
        tooltip: {
          trigger: 'axis',
          formatter: params => {
            const index = params[0].dataIndex;
            const baseVal = baseValues[index];
            const compareVal = compareValues[index]; // 修改点2：使用新变量
            return `${params[0].marker} ${params[0].name}<br/>
                车型1: ${baseVal}<br/>
                车型2: ${compareVal}`;
          }
        },
        xAxis: {
          type: 'category',
          data: categories,
          axisLabel: { rotate: 45 }
        },
        yAxis: {
          type: 'value', // 修改点3：显示数值坐标轴
          axisLabel: { formatter: '{value}' }
        },
        series: [
          {
            name: '车型1',
            type: 'bar',
            barWidth: '15%',
            data: baseValues,
            itemStyle: { color: '#5470c6' }
          },
          {
            name: '车型2',
            type: 'bar',
            barWidth: '15%',
            data: compareValues, // 修改点4：使用原始数值
            itemStyle: { color: '#91cc75' }
          }
        ],
        grid: {
          top: 30,
          bottom: 120,
          left: 80,
          right: 30
        }
      };
    },

    extractNumber(value) {
      const num = parseFloat(String(value).replace(/[^0-9.]/g, ''));
      return isNaN(num) ? 0 : num;
    },

    async searchProducts(query) {
      try {
        const res = await listPerformance({ query });
        this.productOptions = res.rows;
      } catch (error) {
        console.error('搜索失败:', error);
      }
    },

    async handleSelectChange(n, val) {
      try {
        const res = await getPerformance(val);
        if (n === 1) {
          this.product1 = res.data;
        } else {
          this.product2 = res.data;
        }
      } catch (error) {
        this.$message.error('获取数据失败');
      }
    },

    switchMode(n) {
      this.inputMode[n-1] = !this.inputMode[n-1];
      this.manualInputs[n-1] = { vehicleType: '', manufacturer: '' };
      this.selectedProducts[n-1] = null;
      if(n === 1) this.product1 = null;
      else this.product2 = null;
    },

    async handleManualConfirm(n) {
      const input = this.manualInputs[n-1];
      if (!input.vehicleType || !input.manufacturer) {
        this.$message.error('请完整输入车型和制造商');
        return;
      }

      try {
        const res = await listPerformance({
          vehicleType: input.vehicleType,
          manufacturer: input.manufacturer
        });

        if (res.rows.length === 0) {
          this.$message.error('未找到匹配记录');
        } else if (res.rows.length > 1) {
          this.$message.warning('找到多条记录，请使用选择模式');
          this.inputMode[n-1] = false;
        } else {
          const productId = res.rows[0].id;
          this.selectedProducts[n-1] = productId;
          const productRes = await getPerformance(productId);
          if(n === 1) {
            this.product1 = productRes.data;
          } else {
            this.product2 = productRes.data;
          }
          this.inputMode[n-1] = false;
        }
      } catch (error) {
        this.$message.error('查询失败');
      }
    }
  },
  beforeUnmount() {
    if (chartInstance) {
      chartInstance.dispose();
      chartInstance = null;
    }
  }
};
</script>

<style scoped>
.app-container {
  max-width: 1200px;
  margin: 20px auto;
  padding: 0 20px;
}

.param-selector {
  margin-bottom: 20px;
  padding: 15px;
  background: #f8f9fa;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.param-selector.compact .selector-content {
  display: flex;
  align-items: center;
  gap: 15px;
}

.compact-checkbox-group {
  flex: 1;
  display: flex;
  gap: 15px;
  row-gap: 12px;
  flex-wrap: wrap;
}

.selector-title {
  font-weight: 600;
  color: #606266;
  margin-bottom: 0;
  white-space: nowrap;
}

.confirm-btn {
  margin-left: auto;
  min-width: 80px;
}

.compare-selectors {
  display: flex;
  gap: 20px;
  margin-bottom: 20px;
  flex-wrap: wrap;
}

.selector-item {
  flex: 1;
  min-width: 500px;
}

.selector-container {
  display: flex;
  align-items: center;
  gap: 10px;
  width: 100%;
}

.mode-switcher {
  flex-shrink: 0;
}

.input-wrapper {
  flex: 1;
  min-width: 0;
}

.el-select {
  width: 100% !important;
}

.btn-group {
  display: flex;
  gap: 5px;
  margin-top: 5px;
}

@media (max-width: 768px) {
  .app-container {
    padding: 0 10px;
  }
  
  .param-selector {
    padding: 12px;
  }
  
  .compact-checkbox-group {
    gap: 10px !important;
  }
  
  .selector-item {
    min-width: 100%;
  }
  
  .selector-container {
    flex-direction: column;
    align-items: flex-start;
  }
  
  .mode-switcher {
    margin-bottom: 10px;
  }
  
  .input-wrapper {
    width: 100%;
  }

  [ref="chartContainer"] {
    width: 95% !important;
    height: 300px !important;
  }
}
</style>
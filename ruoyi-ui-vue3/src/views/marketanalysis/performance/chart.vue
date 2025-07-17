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

    <!-- 对比选择框 - 同时显示5个选择框 -->
    <div class="compare-selectors">
      <div class="selector-title" style="margin-bottom: 10px;">
        车型选择（最多选择5个车型进行对比）：
      </div>
      <div class="selector-items-container">
        <div 
          class="selector-item" 
          v-for="n in 5" 
          :key="n"
        >
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
                :placeholder="`请选择车型${n}的车型-制造商`"
                :remote-method="searchProducts"
                @change="val => handleSelectChange(n, val)"
                clearable
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
                clearable
              />
              <el-input
                v-model="manualInputs[n-1].manufacturer"
                placeholder="输入制造商"
                style="margin-bottom: 5px"
                clearable
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
    </div>

    <!-- 图表容器 -->
    <div 
      ref="chartContainer" 
      style="width: 90%; height: 500px; margin: 20px auto;"
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

// 预设颜色方案
const COLORS = [
  '#5470c6', '#91cc75', '#fac858', '#ee6666', '#73c0de',
  '#3ba272', '#fc8452', '#9a60b4', '#ea7ccc'
];

let chartInstance = null;

export default {
  name: 'PerformanceBarChart',
  data() {
    return {
      // 选择器状态管理
      selectedProducts: [null, null, null, null, null],
      inputMode: [false, false, false, false, false],
      manualInputs: Array(5).fill().map(() => ({ 
        vehicleType: '', 
        manufacturer: '' 
      })),
      productOptions: [],
      
      // 参数选择
      tempSelectedFields: [
        // 'maximumDrivingSpeedFull', 'maxClimbCapabilityFull'
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
      
      // 存储所有产品数据
      productsData: [null, null, null, null, null]
    };
  },
  computed: {
    filteredAvailableFields() {
      return Object.fromEntries(
        Object.entries(this.availableFields)
          .filter(([key]) => !excludedFields.includes(key))
      );
    },
    
    // 当前激活的选择器数量
    activeSelectorsCount() {
      return this.productsData.filter(p => p !== null).length;
    },
    
    // 获取所有非空的产品数据
    activeProducts() {
      return this.productsData.filter(p => p !== null);
    }
  },
  methods: {
    async generateChart() {
      // 检查至少有两个产品
      if (this.activeSelectorsCount < 2) {
        this.$message.warning('请至少选择两个对比车型');
        return;
      }
      
      // 检查至少选择了一个参数
      if (this.tempSelectedFields.length === 0) {
        this.$message.warning('请至少选择一个对比参数');
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

      // 为每个激活的产品生成数据系列
      const series = [];
      const legendData = [];
      
      // 存储每个选择器的产品名称（带序号）
      const selectorNames = [];
      
      this.productsData.forEach((product, index) => {
        if (product !== null) {
          // 创建唯一标识符：产品名称 + 选择器位置
          const selectorId = `${product.vehicleType} - ${product.manufacturer} [${index + 1}]`;
          selectorNames.push(selectorId);
          
          const values = this.tempSelectedFields.map(field => 
            this.extractNumber(product[field])
          );
          
          series.push({
            name: selectorId,
            type: 'bar',
            barWidth: this.calculateBarWidth(),
            data: values,
            itemStyle: { 
              color: COLORS[index % COLORS.length] 
            },
            // 添加数据标签显示
            label: {
              show: true,
              position: 'top',
              formatter: '{c}',
              fontSize: 12,
              fontWeight: 'bold',
              color: COLORS[index % COLORS.length]  // 使用和柱子相同的颜色
            }
          });
          
          legendData.push({
            name: selectorId,
            icon: 'circle',
            textStyle: {
              color: COLORS[index % COLORS.length],
              fontWeight: 'bold'
            }
          });
        }
      });

      return {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          },
          formatter: params => {
            let result = params[0].name + '<br/>';
            params.forEach(item => {
              // 移除选择器序号显示
              const displayName = item.seriesName.replace(/ \[\d+\]$/, '');
              result += `${item.marker} ${displayName}: ${item.value}<br/>`;
            });
            return result;
          }
        },
        // 在右上角添加水平图例
        legend: {
          data: legendData,
          orient: 'horizontal',
          right: 20,
          top: 20,
          itemGap: 15,
          itemWidth: 10,
          itemHeight: 10,
          textStyle: {
            fontSize: 12
          },
          formatter: name => {
            // 移除选择器序号显示
            const displayName = name.replace(/ \[\d+\]$/, '');
            // 截断过长的名称
            return displayName.length > 20 ? displayName.substring(0, 20) + '...' : displayName;
          }
        },
        grid: {
          top: 70,     // 增加顶部间距给图例
          bottom: 50,
          left: 80,
          right: 30,
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: categories,
          axisLabel: { 
            rotate: 45,
            interval: 0,
            formatter: value => {
              // 如果参数名称太长，换行显示
              if (value.length > 8) {
                return value.split('').reduce((prev, next, i) => {
                  return (i % 8 === 0) ? prev + '\n' + next : prev + next;
                });
              }
              return value;
            }
          }
        },
        yAxis: {
          type: 'value',
          name: '数值',
          axisLabel: { formatter: '{value}' }
        },
        series: series
      };
    },
    
    // 根据选择的产品数量计算柱宽
    calculateBarWidth() {
      const count = this.activeSelectorsCount;
      if (count <= 2) return '30%';
      if (count === 3) return '25%';
      if (count === 4) return '20%';
      return '15%';
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
        if (!val) {
          this.productsData[n-1] = null;
          return;
        }
        
        const res = await getPerformance(val);
        this.productsData[n-1] = res.data;
      } catch (error) {
        this.$message.error('获取数据失败');
      }
    },

    switchMode(n) {
      this.inputMode[n-1] = !this.inputMode[n-1];
      this.manualInputs[n-1] = { vehicleType: '', manufacturer: '' };
      this.selectedProducts[n-1] = null;
      this.productsData[n-1] = null;
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
          this.productsData[n-1] = productRes.data;
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
  max-width: 1400px;
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
  margin-bottom: 10px;
  white-space: nowrap;
}

.confirm-btn {
  margin-left: auto;
  min-width: 80px;
}

.compare-selectors {
  margin-bottom: 20px;
}

.selector-items-container {
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
  margin-bottom: 15px;
}

.selector-item {
  position: relative;
  flex: 1;
  min-width: 300px;
  max-width: 350px;
  padding: 15px;
  background: #f8f9fa;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  transition: all 0.3s;
}

.selector-container {
  display: flex;
  align-items: flex-start;
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

@media (max-width: 1200px) {
  .selector-item {
    min-width: calc(50% - 15px);
  }
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
    max-width: 100%;
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
    height: 350px !important;
  }
}
</style>
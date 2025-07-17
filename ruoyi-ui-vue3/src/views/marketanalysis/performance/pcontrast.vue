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
            v-for="(label, field) in availableFields"
            :key="field"
            :label="field"
            :disabled="mandatoryFields.includes(field)"
          >
            {{ label }}
          </el-checkbox>
        </el-checkbox-group>
        <el-button 
          type="primary" 
          size="medium"
          @click="applyParameters"
          class="confirm-btn"
        >
          生成对比表格
        </el-button>
      </div>
    </div>

    <!-- 对比选择框 - 增加到5个 -->
    <div class="compare-selectors">
      <div class="selector-item" v-for="n in 5" :key="n">
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

    <!-- 对比表格 - 修改为支持5列 -->
    <div class="comparison-table-container" v-if="showComparison">
      <el-table
        :data="comparisonData"
        style="width: 100%; margin-top: 20px"
        :key="tableKey" 
        border
      >
        <el-table-column prop="field" label="参数\车型" width="180" fixed />
        
        <!-- 动态生成5个产品列 -->
        <el-table-column 
          v-for="(product, index) in products" 
          :key="index"
          :label="product ? `${product.vehicleType} - ${product.manufacturer}` : `车型${index+1}`"
        >
          <template #default="{ row }">
            <span :class="row.highlightClasses[index]">{{ row.values[index] }}</span>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { listPerformance, getPerformance } from '@/api/marketanalysis/performance/performance';

export default {
  name: 'PerformanceCompare',
  data() {
    const mandatoryFields = ['vehicleType', 'manufacturer'];
    return {
      // 修改为支持5个产品
      selectedProducts: Array(5).fill(null),
      inputMode: Array(5).fill(false),
      manualInputs: Array(5).fill().map(() => ({ vehicleType: '', manufacturer: '' })),
      products: Array(5).fill(null), // 存储5个产品数据
      productOptions: [],
      tableKey: 0,
      tempSelectedFields: [...mandatoryFields,
        'maximumDrivingSpeedFull', 'maximumDrivingSpeedEmpty',
        'maxClimbCapabilityFull', 'maxClimbCapabilityEmpty',
        'maximumTractionForceFull', 'maximumTractionForceEmpty',
        'liftingSpeedFull', 'liftingSpeedEmpty',
        'descentSpeedFull', 'descentSpeedEmpty',
        'runningSpeedFull', 'runningSpeedEmpty',
        'increaseSpeedFull', 'increaseSpeedEmpty',
        'lateralMovementSpeedFull', 'lateralMovementSpeedEmpty',
        'travelBrake', 'parkingBrake',
        'bridgeLoadFullyLoadedFront', 'bridgeLoadFullyLoadedBehind',
        'bridgeLoadUnloadedFront', 'bridgeLoadUnloadedBehind'
      ],
      selectedFields: [],
      mandatoryFields,
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
        lateralMovementSpeedEmpty: '侧移速度（空载）',
        travelBrake: '行程制动',
        parkingBrake: '停车制动',
        bridgeLoadFullyLoadedFront: '桥负荷（满载）前',
        bridgeLoadFullyLoadedBehind: '桥负荷（满载）后',
        bridgeLoadUnloadedFront: '桥负荷（空载）前',
        bridgeLoadUnloadedBehind: '桥负荷（空载）后'
      },
      comparisonData: [],
      showComparison: false
    };
  },
  methods: {
    applyParameters() {
      // 检查至少有两个产品被选择
      const selectedCount = this.products.filter(p => p !== null).length;
      if (selectedCount < 2) {
        this.$message.warning('请至少选择两个对比车型');
        return;
      }

      // 强制保留必选字段
      this.selectedFields = [...new Set([
        ...this.mandatoryFields,
        ...this.tempSelectedFields
      ])];
      this.generateComparison();
      this.showComparison = true;
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
          // 清空选择
          this.products[n-1] = null;
          return;
        }
        
        const res = await getPerformance(val);
        // 修复：直接赋值而不是使用 $set
        this.products = [
          ...this.products.slice(0, n-1),
          res.data,
          ...this.products.slice(n)
        ];
      } catch (error) {
        console.error('获取数据失败:', error);
        this.$message.error('获取数据失败: ' + error.message);
      }
    },

    generateComparison() {
      const dynamicFields = this.selectedFields
        .filter(field => this.availableFields[field] && !this.mandatoryFields.includes(field));

      this.comparisonData = [
        ...this.mandatoryFields.map(field => this.createRow(field)),
        ...dynamicFields.map(field => this.createRow(field))
      ];

      this.tableKey = Date.now();
    },

    createRow(fieldKey) {
      const numericFields = [
        'maximumDrivingSpeedFull', 'maximumDrivingSpeedEmpty',
        'maxClimbCapabilityFull', 'maxClimbCapabilityEmpty',
        'maximumTractionForceFull', 'maximumTractionForceEmpty',
        'liftingSpeedFull', 'liftingSpeedEmpty',
        'descentSpeedFull', 'descentSpeedEmpty',
        'runningSpeedFull', 'runningSpeedEmpty',
        'increaseSpeedFull', 'increaseSpeedEmpty',
        'lateralMovementSpeedFull', 'lateralMovementSpeedEmpty',
        'bridgeLoadFullyLoadedFront', 'bridgeLoadFullyLoadedBehind',
        'bridgeLoadUnloadedFront', 'bridgeLoadUnloadedBehind'
      ];

      const isNumeric = numericFields.includes(fieldKey);
      
      // 获取所有产品的当前字段值
      const values = this.products.map(product => {
        return product ? (product[fieldKey] || '-') : '-';
      });
      
      // 计算高亮类
      const highlightClasses = Array(5).fill('');
      
      if (isNumeric) {
        // 提取数值并找出最大值
        const numericValues = values.map(value => {
          if (value === '-') return -Infinity;
          const num = parseFloat(value);
          return isNaN(num) ? -Infinity : num;
        });
        
        const maxValue = Math.max(...numericValues);
        
        // 标记所有等于最大值的单元格
        numericValues.forEach((val, index) => {
          if (val === maxValue && val !== -Infinity) {
            highlightClasses[index] = 'highlight-red';
          }
        });
      }

      return {
        field: this.availableFields[fieldKey],
        values,
        highlightClasses
      };
    },

    switchMode(n) {
      this.inputMode[n-1] = !this.inputMode[n-1];
      this.manualInputs[n-1] = { vehicleType: '', manufacturer: '' };
      this.selectedProducts[n-1] = null;
      this.products = [
        ...this.products.slice(0, n-1),
        null,
        ...this.products.slice(n)
      ];
      this.showComparison = false;
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
          // 修复：直接赋值而不是使用 $set
          this.products = [
            ...this.products.slice(0, n-1),
            productRes.data,
            ...this.products.slice(n)
          ];
          this.inputMode[n-1] = false;
        }
      } catch (error) {
        this.$message.error('查询失败: ' + error.message);
      }
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
  min-width: 300px; /* 减小最小宽度以适应更多列 */
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

.highlight-red {
  color: #f56c6c;
  font-weight: 600;
}

.comparison-table-container {
  width: 100%;
  overflow-x: auto; /* 添加横向滚动 */
}

@media (max-width: 1200px) {
  .selector-item {
    min-width: 280px;
  }
}

@media (max-width: 992px) {
  .selector-item {
    min-width: 250px;
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
}
</style>
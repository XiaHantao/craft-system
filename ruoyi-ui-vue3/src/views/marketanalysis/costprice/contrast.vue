<template>
  <div class="app-container">
    <!-- 参数选择区域 -->
    <div class="param-selector">
      <div class="selector-title">对比参数选择：</div>
      <div class="selector-content">
        <el-checkbox-group v-model="tempSelectedFields">
          <el-checkbox
            v-for="(label, field) in availableFields"
            :key="field"
            :label="field"
          >
            {{ label }}
          </el-checkbox>
        </el-checkbox-group>
        <el-button 
          type="primary" 
          @click="applyParameters"
          class="confirm-btn"
        >
          确定
        </el-button>
      </div>
    </div>

    <!-- 对比选择框 -->
    <div class="compare-selectors">
      <div class="selector-item" v-for="n in 2" :key="n">
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
        <div v-if="!inputMode[n-1]" class="select-wrapper">
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
        <div v-else class="manual-input">
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

    <!-- 对比表格 -->
    <el-table
      v-if="showComparison"
      :data="comparisonData"
      style="width: 100%; margin-top: 20px"
      :key="tableKey" 
    >
      <el-table-column prop="field" label="参数\车型" width="180" />
      <el-table-column :label="product1Label">
        <template #default="{ row }">
          <span :class="row.product1Class">{{ row.product1Value }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="product2Label">
        <template #default="{ row }">
          <span :class="row.product2Class">{{ row.product2Value }}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { listCostprice, getCostprice } from '@/api/marketanalysis/costprice/costprice';

export default {
  name: 'CostPriceCompare',
  data() {
    return {
      selectedProducts: [null, null],
      inputMode: [false, false],
      manualInputs: [
        { vehicleType: '', manufacturer: '' },
        { vehicleType: '', manufacturer: '' }
      ],
      product1: null,
      product2: null,
      productOptions: [],
      tableKey: 0,
      tempSelectedFields: ['manufacturer', 'cost', 'carBody','lifting','panjinPricing', 'stockPricing','outletSellingPrice','grossMargin','grossMarginRate','grossProfitIncludingTax'],
      selectedFields: ['manufacturer', 'cost', 'carBody','lifting','panjinPricing', 'stockPricing','outletSellingPrice','grossMargin','grossMarginRate','grossProfitIncludingTax'],
      availableFields: {
        manufacturer: '制造商',
        cost: '成本（不含税）',
        carBody: '车体成本',
        lifting: '起重成本',
        panjinPricing: '盘锦定价（含税）',
        stockPricing: '股份定价（含税）',
        outletSellingPrice: '网点售价（含税）',
        grossMargin: '毛利（不含税）',
        grossMarginRate: '毛利率',
        grossProfitIncludingTax: '毛利（含税）'
      },
      comparisonData: []
    };
  },
  computed: {
    showComparison() {
      return !!this.product1 && !!this.product2;
    },
    product1Label() {
      return this.product1 ? `${this.product1.vehicleType} (${this.product1.manufacturer})` : '';
    },
    product2Label() {
      return this.product2 ? `${this.product2.vehicleType} (${this.product2.manufacturer})` : '';
    },
  },
  methods: {
    async searchProducts(query) {
      const res = await listCostprice({
        vehicleType: query,
        manufacturer: query,
      });
      this.productOptions = res.rows;
    },

    handleSelectChange(n, id) {
      if (n === 1) {
        this.loadProduct1(id);
      } else {
        this.loadProduct2(id);
      }
    },

    async loadProduct1(id) {
      const res = await getCostprice(id);
      this.product1 = res.data;
      this.generateComparison();
    },

    async loadProduct2(id) {
      const res = await getCostprice(id);
      this.product2 = res.data;
      this.generateComparison();
    },

    applyParameters() {
      this.selectedFields = [...this.tempSelectedFields];
      this.tableKey++;
      if (this.product1 && this.product2) {
        this.generateComparison();
      }
    },

    generateComparison() {
      if (!this.product1 || !this.product2) return;
      this.comparisonData = this.selectedFields
        .filter(field => this.availableFields[field])
        .map((field) => this.createRow(this.availableFields[field], field));
    },

    createRow(fieldName, fieldKey) {
      const numericFields = ['cost', 'carBody', 'lifting', 'panjinPricing', 
        'stockPricing', 'outletSellingPrice', 'grossMargin', 'grossMarginRate', 'grossProfitIncludingTax'];
      const isNumeric = numericFields.includes(fieldKey);
      
      let p1 = 0;
      let p2 = 0;

      if (isNumeric) {
        const getValue = (value) => {
          if (value === null || value === undefined || value === '-') return 0;
          if (fieldKey === 'grossMarginRate') {
            const numStr = String(value).replace('%', '');
            return parseFloat(numStr) || 0;
          }
          return parseFloat(value) || 0;
        };

        p1 = getValue(this.product1[fieldKey]);
        p2 = getValue(this.product2[fieldKey]);
      }

      return {
        field: fieldName,
        product1Value: this.product1[fieldKey] ?? '-',
        product2Value: this.product2[fieldKey] ?? '-',
        product1Class: isNumeric && p1 > p2 ? 'highlight-red' : '',
        product2Class: isNumeric && p2 > p1 ? 'highlight-red' : '',
      };
    },

    switchMode(n) {
      this.inputMode[n-1] = !this.inputMode[n-1];
      this.manualInputs[n-1] = { vehicleType: '', manufacturer: '' };
      this.selectedProducts[n-1] = null;
      if(n === 1) this.product1 = null;
      else this.product2 = null;
      this.generateComparison();
    },

    async handleManualConfirm(n) {
      const input = this.manualInputs[n-1];
      if (!input.vehicleType || !input.manufacturer) {
        this.$message.error('请完整输入车型和制造商');
        return;
      }

      try {
        const res = await listCostprice({
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
          if(n === 1) {
            await this.loadProduct1(productId);
          } else {
            await this.loadProduct2(productId);
          }
          this.inputMode[n-1] = false;
        }
      } catch (error) {
        this.$message.error('查询失败');
      }
    },
  },
};
</script>

<style scoped>
/* 原有样式保持不变 */
.app-container {
  max-width: 1200px;
  margin: 30px auto;
  padding: 0 20px;
}

.param-selector {
  margin-bottom: 20px;
  padding: 20px;
  background: #f8f9fa;
  border-radius: 15px; 
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  display: flex;
  align-items: center;
  gap: 20px;
}

.selector-content {
  flex: 1;
  display: flex;
  align-items: center;
  gap: 30px;
}

.el-checkbox-group {
  flex: 1;
  display: flex;
  gap: 30px;
  flex-wrap: wrap;
}

.confirm-btn {
  margin-left: auto;
  min-width: 80px;
}

.selector-title {
  margin-bottom: 0;
  font-weight: 600;
  color: #606266;
  white-space: nowrap;
}

.compare-selectors {
  display: flex;
  gap: 30px;
  margin-bottom: 30px;
  flex-wrap: wrap;
}

.selector-item {
  flex: 1;
  min-width: 500px;
  display: flex;
  align-items: center;
}

.el-select {
  width: 300px !important;
  flex: 1;
  width: 100%;
}

.el-table {
  margin-top: 30px !important;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.el-table::before {
  height: 0;
}

.highlight-red {
  color: #f56c6c;
  font-weight: 600;
}

@media (max-width: 768px) {
  .app-container {
    padding: 0 15px;
  }

  .param-selector {
    flex-direction: column;
    align-items: flex-start;
  }

  .selector-content {
    width: 100%;
    flex-direction: column;
    align-items: flex-start;
  }

  .confirm-btn {
    margin-left: 0;
    width: 100%;
  }

  .compare-selectors {
    gap: 15px;
  }

  .selector-item {
    min-width: 100%;
  }

  .el-checkbox-group {
    gap: 15px;
  }
}
</style>
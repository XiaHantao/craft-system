<template>
  <div style="padding: 20px;">
    <div class="chart-controls">
      <!-- 车类选择 -->
      <div class="control-group">
        <div class="mode-switcher">
          <el-button 
            size="mini" 
            @click="switchMode('category')"
            :type="inputMode.category ? 'success' : ''"
          >
            {{ inputMode.category ? '切换选择' : '手动输入' }}
          </el-button>
        </div>
        
        <el-select
          v-if="!inputMode.category"
          v-model="selectedCategory"
          filterable
          remote
          clearable
          placeholder="请选择车类"
          :remote-method="searchCategories"
          @change="val => handleCategorySelect(val)"
          class="select-input"
        >
          <el-option
            v-for="item in categoryOptions"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>

        <el-input
          v-else
          v-model="manualCategory"
          placeholder="输入车类"
          class="manual-input"
          clearable
          @keyup.enter="handleManualConfirm('category')"
        />
      </div>

      <!-- 地区选择 -->
      <div class="control-group">
        <div class="mode-switcher">
          <el-button 
            size="mini" 
            @click="switchMode('branch')"
            :type="inputMode.branch ? 'success' : ''"
          >
            {{ inputMode.branch ? '切换选择' : '手动输入' }}
          </el-button>
        </div>

        <el-select
          v-if="!inputMode.branch"
          v-model="selectedBranch"
          filterable
          remote
          clearable
          placeholder="请选择地区"
          :remote-method="searchBranches"
          @change="val => handleBranchSelect(val)"
          class="select-input"
        >
          <el-option
            v-for="item in branchOptions"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>

        <el-input
          v-else
          v-model="manualBranch"
          placeholder="输入地区"
          class="manual-input"
          clearable
          @keyup.enter="handleManualConfirm('branch')"
        />
      </div>

      <el-button 
        type="primary" 
        @click="loadData"
        class="confirm-btn"
      >
        生成图表
      </el-button>
    </div>

    <div ref="chartContainer" style="width: 90%; height: 500px; margin: 20px auto;"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import {
  countSalesByMonth1,
  countSalesByBranchForVehicle1,
  countSalesByVehicleForBranch1,
  getCategories,
  getBranches
} from '@/api/marketanalysis/saleanalysis/chart_a'

export default {
  data() {
    return {
      inputMode: { category: false, branch: false },
      selectedCategory: '',
      manualCategory: '',
      categoryOptions: [],
      selectedBranch: '',
      manualBranch: '',
      branchOptions: [],
    }
  },
  created() {
    this.myChart = null
    this.initOptions()
  },
  methods: {
    async initOptions() {
      const [categories, branches] = await Promise.all([
        getCategories(),
        getBranches()
      ])
      this.categoryOptions = categories.data
      this.branchOptions = branches.data
    },

    switchMode(type) {
      this.inputMode[type] = !this.inputMode[type]
      if(type === 'category') {
        this.selectedCategory = ''
        this.manualCategory = ''
      } else {
        this.selectedBranch = ''
        this.manualBranch = ''
      }
    },

    async searchCategories(query) {
      const res = await getCategories()
      this.categoryOptions = res.data.filter(item => 
        item.toLowerCase().includes(query.toLowerCase())
      )
    },

    async searchBranches(query) {
      const res = await getBranches()
      this.branchOptions = res.data.filter(item => 
        item.toLowerCase().includes(query.toLowerCase())
      )
    },

    handleCategorySelect(val) {
      this.manualCategory = ''
    },

    handleBranchSelect(val) {
      this.manualBranch = ''
    },

    handleManualConfirm(type) {
      if(type === 'category' && !this.manualCategory) {
        this.$message.error('请输入车类')
        return
      }
      if(type === 'branch' && !this.manualBranch) {
        this.$message.error('请输入地区')
        return
      }
      this.loadData()
    },

    async loadData() {
  const params = {
    vehicleCategory: this.inputMode.category ? this.manualCategory : this.selectedCategory,
    branch: this.inputMode.branch ? this.manualBranch : this.selectedBranch
  };

  let chartType = 'default';
  if (params.vehicleCategory && params.branch) {
    chartType = 'month';
  } else if (params.vehicleCategory && !params.branch) {
    chartType = 'branch';
  } else if (!params.vehicleCategory && params.branch) {
    chartType = 'category'; // 车类销量图
  } else {
    this.$message.warning('请至少输入一个查询条件');
    return;
  }

  let response;
  switch(chartType) {
    case 'month':
      response = await countSalesByMonth1(params);
      break;
    case 'branch':
      response = await countSalesByBranchForVehicle1(params);
      break;
    case 'category': // 车类销量图
      response = await countSalesByVehicleForBranch1(params);
      break;
  }

  // 关键修复：统一数据处理逻辑
  const data = response.data || [];
  const xData = data.map(item => item.month || item.name);
  const yData = data.map(item => item.value);

  const option = {
    title: {
      text: this.getChartTitle(chartType),
      top: 40,
      left: 'center',
      textStyle: { fontSize: 18, fontWeight: 'bold', color: '#333' }
    },
    xAxis: { type: 'category', data: xData },
    yAxis: { type: 'value' },
    series: [{ 
      data: yData, 
      type: 'line',
      smooth: false,
      lineStyle: { color: '#409eff', width: 3 },
      itemStyle: { color: '#409eff' },
      label: { show: true, position: 'top', formatter: '{c}' },
      symbol: 'circle',
      symbolSize: 8
    }],
    tooltip: {
      trigger: 'axis',
      formatter: (params) => this.getTooltipContent(chartType, params)
    },
    grid: { top: '25%', containLabel: true }
  };

  if (!this.myChart) {
    this.myChart = echarts.init(this.$refs.chartContainer);
  }
  this.myChart.setOption(option);
},

    filterLastTwoYears(data) {
      if (!data.length) return []
      
      // 检查是否为月份数据
      const hasMonth = data.some(item => item.month)
      if (!hasMonth) return data

      const parseDate = (item) => {
        const dateStr = item.month
        const [year, month] = String(dateStr).split('-')
        return new Date(`${year}-${month.padStart(2, '0')}-01`)
      }

      // 排序逻辑仅适用于月份数据
      const sortedData = [...data].sort((a, b) => 
        parseDate(b) - parseDate(a)
      )

      const latestDate = parseDate(sortedData[0])
      const twoYearsAgo = new Date(latestDate)
      twoYearsAgo.setFullYear(latestDate.getFullYear() - 2)
      
      return sortedData
        .filter(item => parseDate(item) >= twoYearsAgo)
        .reverse()
        .sort((a, b) => parseDate(a) - parseDate(b))
    },

    getChartTitle(type) {
      const titles = {
        month: '月份销量趋势图',
        branch: '地区销量趋势图',
        category: '车类销量趋势图'
      }
      return titles[type]
    },

    getTooltipContent(type, params) {
      let tip = ''
      switch(type) {
        case 'month':
          tip += `月份: ${params[0].name}<br/>`
          break
        case 'branch':
          tip += `地区: ${params[0].name}<br/>`
          break
        case 'category':
          tip += `车类: ${params[0].name}<br/>`
          break
      }
      return tip + `销量: ${params[0].value}辆`
    }
  },
  beforeUnmount() {
    if (this.myChart) {
      this.myChart.dispose()
      this.myChart = null
    }
  }
}
</script>

<style scoped>
.chart-controls {
  max-width: 800px;
  margin: 0 auto 20px;
  display: flex;
  gap: 15px;
  align-items: center;
  flex-wrap: wrap;
}

.control-group {
  flex: 1;
  min-width: 300px;
  display: flex;
  gap: 10px;
  align-items: center;
}

.mode-switcher {
  flex-shrink: 0;
}

.select-input {
  flex: 1;
  min-width: 200px;
}

.manual-input {
  flex: 1;
  min-width: 200px;
}

.confirm-btn {
  height: 40px;
  padding: 0 25px;
}

@media (max-width: 768px) {
  .chart-controls {
    flex-direction: column;
  }
  
  .control-group {
    width: 100%;
  }
}
</style>
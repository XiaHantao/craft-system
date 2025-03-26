<template>
  <div style="padding: 20px;">
    <div class="chart-controls">
      <!-- 车型选择 -->
      <div class="control-group">
        <div class="mode-switcher">
          <el-button 
            size="mini" 
            @click="switchMode('vehicle')"
            :type="inputMode.vehicle ? 'success' : ''"
          >
            {{ inputMode.vehicle ? '切换选择' : '手动输入' }}
          </el-button>
        </div>
        
        <el-select
          v-if="!inputMode.vehicle"
          v-model="selectedVehicle"
          filterable
          remote
          clearable
          placeholder="请选择车型"
          :remote-method="searchVehicles"
          @change="val => handleVehicleSelect(val)"
          class="select-input"
        >
          <el-option
            v-for="item in vehicleOptions"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>

        <el-input
          v-else
          v-model="manualVehicle"
          placeholder="输入车型"
          class="manual-input"
          clearable
          @keyup.enter="handleManualConfirm('vehicle')"
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
  countSalesByMonth,
  countSalesByBranchForVehicle,
  countSalesByVehicleForBranch,
  getVehicleTypes,
  getBranches
} from '@/api/marketanalysis/saleanalysis/chart'

export default {
  data() {
    return {
      // 车型相关
      inputMode: { vehicle: false, branch: false },
      selectedVehicle: '',
      manualVehicle: '',
      vehicleOptions: [],
      
      // 地区相关
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
      // 初始化选项
      const [vehicles, branches] = await Promise.all([
        getVehicleTypes(),
        getBranches()
      ])
      this.vehicleOptions = vehicles.data
      this.branchOptions = branches.data
    },

    switchMode(type) {
      this.inputMode[type] = !this.inputMode[type]
      if(type === 'vehicle') {
        this.selectedVehicle = ''
        this.manualVehicle = ''
      } else {
        this.selectedBranch = ''
        this.manualBranch = ''
      }
    },

    async searchVehicles(query) {
      const res = await getVehicleTypes(query)
      this.vehicleOptions = res.data.filter(item => 
        item.toLowerCase().includes(query.toLowerCase())
      )
    },

    async searchBranches(query) {
      const res = await getBranches(query)
      this.branchOptions = res.data.filter(item => 
        item.toLowerCase().includes(query.toLowerCase())
      )
    },

    handleVehicleSelect(val) {
      this.manualVehicle = ''
    },

    handleBranchSelect(val) {
      this.manualBranch = ''
    },

    handleManualConfirm(type) {
      if(type === 'vehicle' && !this.manualVehicle) {
        this.$message.error('请输入车型')
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
        vehicleType: this.inputMode.vehicle ? this.manualVehicle : this.selectedVehicle,
        branch: this.inputMode.branch ? this.manualBranch : this.selectedBranch
      }

      let chartType = 'default'
      if (params.vehicleType && params.branch) {
        chartType = 'month'
      } else if (params.vehicleType && !params.branch) {
        chartType = 'branch'
      } else if (!params.vehicleType && params.branch) {
        chartType = 'vehicle'
      } else {
        this.$message.warning('请至少输入一个查询条件')
        return
      }

      // 保持原有图表逻辑不变
      let response
      switch(chartType) {
        case 'month':
          response = await countSalesByMonth(params)
          break
        case 'branch':
          response = await countSalesByBranchForVehicle(params)
          break
        case 'vehicle':
          response = await countSalesByVehicleForBranch(params)
          break
      }

      const xData = response.data.map(item => item.month || item.name)
      const yData = response.data.map(item => item.value)

      // 保持原有图表配置
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
          itemStyle: { color: '#409eff' }
        }],
        tooltip: {
          trigger: 'axis',
          formatter: (params) => this.getTooltipContent(chartType, params)
        },
        grid: { top: '25%', containLabel: true }
      }

      if (!this.myChart) {
        this.myChart = echarts.init(this.$refs.chartContainer)
      }
      this.myChart.setOption(option)
    },

    getChartTitle(type) {
      const titles = {
        month: '月份销量趋势图',
        branch: '地区销量趋势图',
        vehicle: '车型销量趋势图'
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
        case 'vehicle':
          tip += `车型: ${params[0].name}<br/>`
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
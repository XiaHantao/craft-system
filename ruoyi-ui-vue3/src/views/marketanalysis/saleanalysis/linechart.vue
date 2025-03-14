<template>
  <div style="padding: 20px;">
    <div style="display: flex; gap: 10px; margin-bottom: 20px; max-width: 800px; margin: 0 auto;">
      <input 
        v-model="vehicleType" 
        placeholder="请输入车型"
        style="flex: 1; font-size: 14px; padding: 8px; border: 1px solid #ddd; border-radius: 4px;"
      >
      <input
        v-model="branch"
        placeholder="请输入地区"
        style="flex: 1; font-size: 14px; padding: 8px; border: 1px solid #ddd; border-radius: 4px;"
      >
      <button 
        @click="loadData"
        style="font-size: 14px; padding: 8px 25px; background: #409eff; color: white; border: none; border-radius: 4px; cursor: pointer;"
      >
        确定
      </button>
    </div>

    <div ref="chartContainer" style="width: 90%; height: 500px; margin: 0 auto;"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import { 
  countSalesByMonth, 
  countSalesByBranchForVehicle, 
  countSalesByVehicleForBranch 
} from '@/api/marketanalysis/saleanalysis/chart'

export default {
  data() {
    return {
      vehicleType: '',
      branch: '',
    }
  },
  created() {
    this.myChart = null // 定义为普通变量，避免响应式
  },
  methods: {
    async loadData() {
      let apiParams = {}
      let chartType = 'default'

      if (this.vehicleType && this.branch) {
        apiParams = { vehicleType: this.vehicleType, branch: this.branch }
        chartType = 'month'
      } else if (this.vehicleType && !this.branch) {
        apiParams = { vehicleType: this.vehicleType }
        chartType = 'branch'
      } else if (!this.vehicleType && this.branch) {
        apiParams = { branch: this.branch }
        chartType = 'vehicle'
      } else {
        return
      }

      let response
      switch(chartType) {
        case 'month':
          response = await countSalesByMonth(apiParams)
          break
        case 'branch':
          response = await countSalesByBranchForVehicle(apiParams)
          break
        case 'vehicle':
          response = await countSalesByVehicleForBranch(apiParams)
          break
      }

      const xData = response.data.map(item => item.month || item.name)
      const yData = response.data.map(item => item.value)

      const titles = {
        month: '月份销量趋势图',
        branch: '地区销量趋势图',
        vehicle: '车型销量趋势图'
      }

      const option = {
        title: {
          text: titles[chartType],
          top: 40,
          left: 'center',
          textStyle: {
            fontSize: 18,
            fontWeight: 'bold',
            color: '#333'
          }
        },
        xAxis: { type: 'category', data: xData },
        yAxis: { type: 'value' },
        series: [{ 
          data: yData, 
          type: 'line',
          smooth: false,
          lineStyle: {
            color: '#409eff',
            width: 3
          },
          itemStyle: {
            color: '#409eff'
          }
        }],
        tooltip: {
          trigger: 'axis',
          formatter: (params) => {
            let tip = ''
            switch(chartType) {
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
            tip += `销量: ${params[0].value}辆`
            return tip
          }
        },
        grid: {
          top: '25%',
          containLabel: true
        }
      }

      // 初始化图表
      if (!this.myChart) {
        this.myChart = echarts.init(this.$refs.chartContainer)
      }
      this.myChart.setOption(option)
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
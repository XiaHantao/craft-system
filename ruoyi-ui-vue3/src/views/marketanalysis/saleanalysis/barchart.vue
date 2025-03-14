<template>
  <div>
    <div style="display: flex; justify-content: center; align-items: center; margin-bottom: 20px; gap: 30px; flex-wrap: wrap;">
      <div style="display: flex; align-items: center;">
        <span style="margin-right: 10px; color: #606266;">年份</span>
        <el-select v-model="selectedYear" @change="initChart" style="width: 120px;">
          <el-option v-for="year in years" :key="year" :label="year" :value="year"/>
        </el-select>
      </div>
      
      <div style="display: flex; align-items: center;">
        <span style="margin-right: 10px; color: #606266;">车型</span>
        <el-select
          v-model="selectedVehicle"
          @change="initChart"
          clearable
          placeholder="全部车型"
          style="width: 180px;"
        >
          <el-option v-for="v in vehicleTypes" :key="v" :label="v" :value="v"/>
        </el-select>
      </div>

      <div style="display: flex; align-items: center;">
        <span style="margin-right: 10px; color: #606266;">网点</span>
        <el-select
          v-model="selectedBranch"
          @change="initChart"
          clearable
          placeholder="全部网点"
          style="width: 180px;"
        >
          <el-option v-for="b in branches" :key="b" :label="b" :value="b"/>
        </el-select>
      </div>
    </div>
    <div ref="chart" style="width: 100%; height: 500px;"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import { countMonthlyComparison, getVehicleTypes, getBranches } from '@/api/marketanalysis/saleanalysis/chart';
let myChart = null;

export default {
  data() {
    const currentYear = new Date().getFullYear();
    return {
      selectedYear: currentYear,
      years: Array.from({ length: 10 }, (_, i) => currentYear - i),
      selectedVehicle: null,
      selectedBranch: null,
      vehicleTypes: [],
      branches: [],
    };
  },
  mounted() {
    this.fetchFilters();
    this.initChart();
  },
  methods: {
    async fetchFilters() {
      try {
        const [vtRes, brRes] = await Promise.all([getVehicleTypes(), getBranches()]);
        this.vehicleTypes = vtRes.data || [];
        this.branches = brRes.data || [];
      } catch (error) {
        console.error('加载筛选条件失败:', error);
      }
    },

    async initChart() {
      try {
        // 销毁旧实例
        if (myChart) {
          myChart.dispose();
          myChart = null;
        }

        const params = { 
          year: this.selectedYear,
          vehicleType: this.selectedVehicle || undefined,
          branch: this.selectedBranch || undefined
        };
        
        const res = await countMonthlyComparison(params);
        const chartData = res.data || [];

        // 初始化12个月的数据容器
        const months = Array.from({ length: 12 }, (_, i) => `${i + 1}月`);
        const currentYearData = new Array(12).fill(0);
        const lastYearData = new Array(12).fill(0);

        // 填充数据
        chartData.forEach(item => {
          const monthIndex = parseInt(item.month) - 1;
          if (monthIndex >= 0 && monthIndex < 12) {
            currentYearData[monthIndex] = item.currentYear || 0;
            lastYearData[monthIndex] = item.lastYear || 0;
          }
        });

        // 初始化图表
        myChart = echarts.init(this.$refs.chart);
        myChart.setOption({
          title: {
            text: '销售数据同比环比柱状图',
            left: 'center',
            top: 20,
            textStyle: { fontSize: 16 }
          },
          legend: {
            data: ['本年销量', '去年同期销量'],
            top: 50,
            right: '10%'
          },
          grid: {
            top: 120,
            bottom: 80,
            containLabel: true
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: { type: 'shadow' },
          },
          xAxis: { 
            type: 'category',
            data: months,
            axisLabel: { rotate: 45 }
          },
          yAxis: { type: 'value' },
          series: [
            {
              name: '本年销量',
              type: 'bar',
              data: currentYearData,
              itemStyle: { color: '#5470C6' },
              emphasis: {
                itemStyle: { shadowBlur: 10, shadowColor: 'rgba(0, 0, 0, 0.5)' }
              }
            },
            {
              name: '去年同期销量',
              type: 'bar',
              data: lastYearData,
              itemStyle: { color: '#91CC75' },
              emphasis: {
                itemStyle: { shadowBlur: 10, shadowColor: 'rgba(0, 0, 0, 0.5)' }
              }
            }
          ]
        });

        // 窗口变化时自适应
        window.addEventListener('resize', this.handleResize);
      } catch (error) {
        console.error('加载图表数据失败:', error);
      }
    },

    handleResize() {
      if (myChart) {
        myChart.resize();
      }
    }
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
    if (myChart) {
      myChart.dispose();
    }
  }
};
</script>

<style scoped>
.el-select {
  margin-right: 15px;
}
/* 新增居中布局样式 */
@media (max-width: 768px) {
  .filter-container {
    flex-direction: column;
    align-items: stretch;
  }
}
</style>
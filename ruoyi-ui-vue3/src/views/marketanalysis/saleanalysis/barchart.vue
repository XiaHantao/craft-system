<template>
  <div>
    <div class="filter-container">
      <!-- 年份选择 -->
      <div class="filter-group">
        <div class="filter-label">年份</div>
        <el-select v-model="selectedYear" @change="initChart" class="filter-select">
          <el-option v-for="year in years" :key="year" :label="year" :value="year"/>
        </el-select>
      </div>

      <!-- 车型选择 -->
      <div class="filter-group">
        <div class="filter-label">车型</div>
        <div class="input-container">
          <el-button 
            size="mini" 
            @click="switchMode('vehicle')"
            :type="inputMode.vehicle ? 'success' : ''"
            class="mode-switch"
          >
            {{ inputMode.vehicle ? '切换选择' : '手动输入' }}
          </el-button>
          <el-select
            v-if="!inputMode.vehicle"
            v-model="selectedVehicle"
            @change="initChart"
            clearable
            placeholder="全部车型"
            filterable
            class="filter-select"
          >
            <el-option v-for="v in vehicleTypes" :key="v" :label="v" :value="v"/>
          </el-select>
          <el-input
            v-else
            v-model="manualVehicle"
            placeholder="输入车型"
            @keyup.enter="handleManualInput('vehicle')"
            class="manual-input"
          />
        </div>
      </div>

      <!-- 网点选择 -->
      <div class="filter-group">
        <div class="filter-label">网点</div>
        <div class="input-container">
          <el-button 
            size="mini" 
            @click="switchMode('branch')"
            :type="inputMode.branch ? 'success' : ''"
            class="mode-switch"
          >
            {{ inputMode.branch ? '切换选择' : '手动输入' }}
          </el-button>
          <el-select
            v-if="!inputMode.branch"
            v-model="selectedBranch"
            @change="initChart"
            clearable
            placeholder="全部网点"
            filterable
            class="filter-select"
          >
            <el-option v-for="b in branches" :key="b" :label="b" :value="b"/>
          </el-select>
          <el-input
            v-else
            v-model="manualBranch"
            placeholder="输入网点"
            @keyup.enter="handleManualInput('branch')"
            class="manual-input"
          />
        </div>
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
      inputMode: { vehicle: false, branch: false },
      manualVehicle: '',
      manualBranch: '',
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

    switchMode(type) {
      this.inputMode[type] = !this.inputMode[type];
      if (type === 'vehicle') {
        this.selectedVehicle = null;
        this.manualVehicle = '';
      } else {
        this.selectedBranch = null;
        this.manualBranch = '';
      }
    },

    handleManualInput(type) {
      if (type === 'vehicle' && !this.manualVehicle.trim()) {
        this.$message.error('请输入有效车型');
        return;
      }
      if (type === 'branch' && !this.manualBranch.trim()) {
        this.$message.error('请输入有效网点');
        return;
      }
      this.initChart();
    },

    async initChart() {
      try {
        if (myChart) {
          myChart.dispose();
          myChart = null;
        }

        const params = { 
          year: this.selectedYear,
          vehicleType: this.inputMode.vehicle ? this.manualVehicle : this.selectedVehicle || undefined,
          branch: this.inputMode.branch ? this.manualBranch : this.selectedBranch || undefined
        };

        const res = await countMonthlyComparison(params);
        const chartData = res.data || [];

        const months = Array.from({ length: 12 }, (_, i) => `${i + 1}月`);
        const currentYearData = new Array(12).fill(0);
        const lastYearData = new Array(12).fill(0);

        chartData.forEach(item => {
          const monthIndex = parseInt(item.month) - 1;
          if (monthIndex >= 0 && monthIndex < 12) {
            currentYearData[monthIndex] = item.currentYear || 0;
            lastYearData[monthIndex] = item.lastYear || 0;
          }
        });

        myChart = echarts.init(this.$refs.chart);
        myChart.setOption({
          // 保持原有图表配置不变
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
              itemStyle: { color: '#5470C6' }
            },
            {
              name: '去年同期销量',
              type: 'bar',
              data: lastYearData,
              itemStyle: { color: '#91CC75' }
            }
          ]
        });

        window.addEventListener('resize', this.handleResize);
      } catch (error) {
        console.error('加载图表数据失败:', error);
      }
    },

    handleResize() {
      if (myChart) myChart.resize();
    }
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize);
    if (myChart) myChart.dispose();
  }
};
</script>

<style scoped>
.filter-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
  margin-bottom: 20px;
  padding: 15px;
  background: #f5f7fa;
  border-radius: 8px;
}

.filter-group {
  display: flex;
  align-items: center;
  gap: 10px;
}

.filter-label {
  color: #606266;
  font-size: 14px;
  white-space: nowrap;
}

.input-container {
  display: flex;
  gap: 8px;
  align-items: center;
}

.mode-switch {
  flex-shrink: 0;
  padding: 7px 10px;
}

.filter-select {
  width: 180px;
}

.manual-input {
  width: 180px;
}

@media (max-width: 768px) {
  .filter-container {
    flex-direction: column;
    align-items: stretch;
  }
  
  .input-container {
    width: 100%;
  }
  
  .filter-select, .manual-input {
    flex: 1;
  }
}
</style>
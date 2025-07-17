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

      <!-- 车类选择 -->
      <div class="filter-group">
        <div class="filter-label">车类</div>
        <div class="input-container">
          <el-button 
            size="mini" 
            @click="switchMode('category')"
            :type="inputMode.category ? 'success' : ''"
            class="mode-switch"
          >
            {{ inputMode.category ? '切换选择' : '手动输入' }}
          </el-button>
          <template v-if="!inputMode.category">
            <el-select
              v-model="selectedCategory"
              @change="initChart"
              clearable
              placeholder="全部车类"
              filterable
              class="filter-select"
            >
              <el-option v-for="c in categories" :key="c" :label="c" :value="c"/>
            </el-select>
            <el-button
              v-if="selectedCategory"
              type="danger"
              size="mini"
              @click="clearSelection('category')"
              class="clear-btn"
            >清除</el-button>
          </template>
          <div v-else class="input-with-buttons">
            <el-input
              v-model="manualCategory"
              placeholder="输入车类"
              class="manual-input"
            />
            <div class="action-buttons">
              <el-button 
                type="primary" 
                size="mini" 
                @click="handleManualInput('category')"
                class="confirm-btn"
              >确定</el-button>
              <el-button 
                type="danger" 
                size="mini" 
                @click="clearInput('category')"
              >清除</el-button>
            </div>
          </div>
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
          <template v-if="!inputMode.branch">
            <el-select
              v-model="selectedBranch"
              @change="initChart"
              clearable
              placeholder="全部网点"
              filterable
              class="filter-select"
            >
              <el-option v-for="b in branches" :key="b" :label="b" :value="b"/>
            </el-select>
            <el-button
              v-if="selectedBranch"
              type="danger"
              size="mini"
              @click="clearSelection('branch')"
              class="clear-btn"
            >清除</el-button>
          </template>
          <div v-else class="input-with-buttons">
            <el-input
              v-model="manualBranch"
              placeholder="输入网点"
              class="manual-input"
            />
            <div class="action-buttons">
              <el-button 
                type="primary" 
                size="mini" 
                @click="handleManualInput('branch')"
                class="confirm-btn"
              >确定</el-button>
              <el-button 
                type="danger" 
                size="mini" 
                @click="clearInput('branch')"
              >清除</el-button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div ref="chart" style="width: 100%; height: 500px;"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import { countMonthlyComparison1, getCategories, getBranches } from '@/api/marketanalysis/saleanalysis/chart_a';
let myChart = null;

export default {
  data() {
    const currentYear = new Date().getFullYear();
    return {
      selectedYear: currentYear,
      years: Array.from({ length: 10 }, (_, i) => currentYear - i),
      selectedCategory: null,
      selectedBranch: null,
      inputMode: { category: false, branch: false },
      manualCategory: '',
      manualBranch: '',
      categories: [],
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
        const [catRes, brRes] = await Promise.all([getCategories(), getBranches()]);
        this.categories = catRes.data || [];
        this.branches = brRes.data || [];
      } catch (error) {
        console.error('加载筛选条件失败:', error);
        this.$message.error('加载筛选条件失败，请重试');
      }
    },

    switchMode(type) {
      this.inputMode[type] = !this.inputMode[type];
      if (type === 'category') {
        this.selectedCategory = null;
        this.manualCategory = '';
      } else {
        this.selectedBranch = null;
        this.manualBranch = '';
      }
    },

    handleManualInput(type) {
      if (type === 'category' && !this.manualCategory.trim()) {
        this.$message.error('请输入有效车类');
        return;
      }
      if (type === 'branch' && !this.manualBranch.trim()) {
        this.$message.error('请输入有效网点');
        return;
      }
      this.initChart();
    },

    clearInput(type) {
      if (type === 'category') {
        this.manualCategory = '';
      } else {
        this.manualBranch = '';
      }
      this.initChart();
    },

    clearSelection(type) {
      if (type === 'category') {
        this.selectedCategory = null;
      } else {
        this.selectedBranch = null;
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
          vehicleCategory: this.inputMode.category ? this.manualCategory : this.selectedCategory || undefined,
          branch: this.inputMode.branch ? this.manualBranch : this.selectedBranch || undefined
        };

        const res = await countMonthlyComparison1(params);
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

        // 计算增长率数据
        const growthRateData = [];   // 同比增长率
        const chainGrowthRateData = []; // 环比增长率
        for (let i = 0; i < 12; i++) {
          const current = currentYearData[i] || 0;
          const last = lastYearData[i] || 0;
          
          // 计算同比
          const yoyRate = last !== 0 ? 
            Number(((current - last) / last * 100).toFixed(2)) : 0;
          growthRateData.push(yoyRate);

          // 计算环比（第一个月无环比）
          if (i === 0) {
            chainGrowthRateData.push(0);
          } else {
            const prev = currentYearData[i - 1] || 0;
            const momRate = prev !== 0 ? 
              Number(((current - prev) / prev * 100).toFixed(2)) : 0;
            chainGrowthRateData.push(momRate);
          }
        }

        myChart = echarts.init(this.$refs.chart);
        myChart.setOption({
          title: {
            text: '销售数据同比环比分析',
            left: 'center',
            top: 20,
            textStyle: { fontSize: 16 }
          },
          legend: {
            data: ['本年销量', '去年同期销量', '同比增长率', '环比增长率'],
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
            formatter: function (params) {
              const current = params[0].value;
              const lastYear = params[1].value;
              const yoy = params[2].value;
              const mom = params[3].value;
              
              return `
                ${params[0].name}<br>
                ${params[0].marker} ${params[0].seriesName}: ${current} 台<br>
                ${params[1].marker} ${params[1].seriesName}: ${lastYear} 台<br>
                ${params[2].marker} 同比增长率: ${yoy}%<br>
                ${params[3].marker} 环比增长率: ${mom}%
              `;
            }
          },
          xAxis: { 
            type: 'category',
            data: months,
            axisLabel: { rotate: 45 }
          },
          yAxis: [
            { 
              type: 'value',
              name: '销量',
              axisLabel: { formatter: '{value} 台' }
            },
            {
              type: 'value',
              name: '增长率',
              axisLabel: { formatter: '{value}%' }
            }
          ],
          series: [
            {
              name: '本年销量',
              type: 'bar',
              data: currentYearData,
              itemStyle: { color: '#5470C6' },
              label: {
                show: true,
                position: 'top',
                formatter: '{c}',
                fontSize: 12,
                fontWeight: 'bold',
                color: '#333'
              }
            },
            {
              name: '去年同期销量',
              type: 'bar',
              data: lastYearData,
              itemStyle: { color: '#91CC75' },
              label: {
                show: true,
                position: 'top',
                formatter: '{c}',
                fontSize: 12,
                fontWeight: 'bold',
                color: '#333'
              }
            },
            {
              name: '同比增长率',
              type: 'line',
              data: growthRateData,
              yAxisIndex: 1,
              symbol: 'circle',
              symbolSize: 8,
              itemStyle: { color: '#EE6666' },
              lineStyle: { width: 3 },
              label: {
                show: true,
                position: 'top',
                formatter: '{c}%',
                fontSize: 11,
                fontWeight: 'bold',
                color: '#EE6666'
              }
            },
            {
              name: '环比增长率',
              type: 'line',
              data: chainGrowthRateData,
              yAxisIndex: 1,
              symbol: 'circle',
              symbolSize: 8,
              itemStyle: { color: '#FAC858' },
              lineStyle: { width: 3, type: 'dashed' },
              label: {
                show: true,
                position: 'bottom',
                formatter: '{c}%',
                fontSize: 11,
                fontWeight: 'bold',
                color: '#FAC858'
              }
            }
          ]
        });

        window.addEventListener('resize', this.handleResize);
      } catch (error) {
        console.error('加载图表数据失败:', error);
        this.$message.error('图表数据加载失败，请重试');
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

.input-with-buttons {
  display: flex;
  gap: 8px;
  align-items: center;
}

.action-buttons {
  display: flex;
  gap: 4px;
}

.filter-select {
  width: 180px;
}

.manual-input {
  width: 180px;
}

.clear-btn {
  margin-left: 8px;
  flex-shrink: 0;
}

@media (max-width: 768px) {
  .filter-container {
    flex-direction: column;
    align-items: stretch;
  }
  
  .input-container {
    width: 100%;
    flex-wrap: nowrap;
  }
  
  .filter-select, .manual-input {
    flex: 1;
  }

  .input-with-buttons {
    flex-direction: column;
    width: 100%;
  }

  .action-buttons {
    width: 100%;
    justify-content: flex-end;
  }

  .clear-btn {
    margin-left: 4px;
  }
}
</style>
<template>
  <el-row>
    <!-- 添加月份选择器 -->
    <el-col :span="24" style="margin-bottom: 20px; display: flex; align-items: center;">
      <span style="margin-right: 10px; font-weight: bold;">选择月份：</span>
      <el-date-picker
        v-model="selectedMonth"
        type="month"
        placeholder="请选择月份"
        value-format="YYYY-MM"
        style="width: 200px;"
        @change="handleMonthChange"
        :clearable="true"
      />
      <!-- 添加全部数据选项 -->
      <el-button 
        style="margin-left: 10px;" 
        :type="!selectedMonth ? 'primary' : ''"
        @click="selectAllData"
      >
        全部数据
      </el-button>
    </el-col>

    <el-col :span="24">
      <div ref="chart" style="height: 500px; margin-top: 20px;"></div>
    </el-col>
    
    <!-- 横向数据表格 -->
    <el-col :span="24" style="margin-top: 30px;">
      <div class="horizontal-table">
        <!-- 第一部分：地区名称 -->
        <div class="table-row header-row">
          <div class="table-cell" v-for="(item, index) in tableFirstPart" :key="'header1'+index">
            {{ item.name }}
          </div>
        </div>
        
        <!-- 第一部分：销量 -->
        <div class="table-row data-row">
          <div class="table-cell" v-for="(item, index) in tableFirstPart" :key="'data1'+index">
            {{ item.value }} 
          </div>
        </div>
        
        <!-- 第二部分：地区名称 -->
        <div class="table-row header-row" v-if="tableSecondPart.length > 0">
          <div class="table-cell" v-for="(item, index) in tableSecondPart" :key="'header2'+index">
            {{ item.name }}
          </div>
        </div>
        
        <!-- 第二部分：销量 -->
        <div class="table-row data-row" v-if="tableSecondPart.length > 0">
          <div class="table-cell" v-for="(item, index) in tableSecondPart" :key="'data2'+index">
            {{ item.value }} 
          </div>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { countSalesByBranch, countSalesByBranchForMonth } from "@/api/marketanalysis/saleanalysis/chart"; // 导入两个API
import * as echarts from 'echarts';
import { ElMessage } from 'element-plus'; // 引入消息提示

const chart = ref(null);
let myChart = null;
const tableData = ref([]); // 存储表格数据
const tableFirstPart = ref([]); // 存储表格第一部分数据
const tableSecondPart = ref([]); // 存储表格第二部分数据
const selectedMonth = ref(''); // 默认不选择任何月份

async function initChart() {
  try {
    let response;
    
    // 根据是否选择月份调用不同的API
    if (selectedMonth.value) {
      // 调用按月统计的API
      response = await countSalesByBranchForMonth(selectedMonth.value);
    } else {
      // 调用获取全部数据的API
      response = await countSalesByBranch();
    }
    
    if (!response || !response.data) {
      throw new Error('未能获取销售数据');
    }
    
    // 确保数据字段与后端返回一致
    const chartData = response.data;
    const xAxisData = chartData.map(item => item.name);
    const seriesData = chartData.map(item => item.value);
    
    // 存储表格数据
    tableData.value = chartData;
    
    // 将表格数据分成两部分
    const mid = Math.ceil(chartData.length / 2);
    tableFirstPart.value = chartData.slice(0, mid);
    tableSecondPart.value = chartData.slice(mid);
    
    // 初始化或更新ECharts实例
    if (!myChart) {
      myChart = echarts.init(chart.value);
      // 窗口调整时自适应
      window.addEventListener('resize', handleResize);
    }
    
    // 动态设置图表标题
    const chartTitle = selectedMonth.value 
      ? `各网点销售数据统计 (${selectedMonth.value})`
      : '各网点销售数据统计 (全部)';
    
    const option = {
      title: {
        text: chartTitle,
        left: 'center',
        textStyle: {
          fontSize: 16
        }
      },
      tooltip: {
        trigger: 'axis',
        formatter: '{b}：{c} 辆' // 显示网点名称和销量
      },
      xAxis: {
        type: 'category',
        data: xAxisData,
        axisLabel: { 
          rotate: 45,
          interval: 0,
          fontSize: 12
        },
        name: '网点名称',
        nameLocation: 'end'
      },
      yAxis: { 
        type: 'value',
        name: '销量（辆）',
        nameTextStyle: {
          fontSize: 12
        }
      },
      series: [{
        name: '销售数量',
        type: 'bar',
        data: seriesData,
        itemStyle: {
          color: '#409EFF'
        },
        barMaxWidth: 30,
        label: {
          show: true,
          position: 'top',
          formatter: '{c}',
          fontSize: 12,
          fontWeight: 'bold',
          color: '#333'
        }
      }],
      grid: {
        bottom: 80
      }
    };
    
    myChart.setOption(option);
    
  } catch (error) {
    console.error('图表初始化失败:', error);
    ElMessage.error('加载销售数据失败：' + error.message);
  }
}

function handleResize() {
  if (myChart) {
    myChart.resize();
  }
}

// 月份变更处理
function handleMonthChange() {
  initChart();
}

// 选择全部数据
function selectAllData() {
  selectedMonth.value = '';
  initChart();
}

onMounted(() => {
  initChart();
});

onBeforeUnmount(() => {
  // 组件销毁时清理实例和事件监听
  if (myChart) {
    window.removeEventListener('resize', handleResize);
    myChart.dispose();
  }
});
</script>

<style scoped>
/* 横向表格样式 - 整体缩小 */
.horizontal-table {
  display: flex;
  flex-direction: column;
  border: 1px solid #EBEEF5;
  border-radius: 3px;
  overflow: hidden;
  box-shadow: 0 1px 8px 0 rgba(0,0,0,0.08);
  font-size: 13px; /* 整体字体减小 */
}

.table-row {
  display: flex;
  flex-wrap: nowrap;
}

.header-row {
  background-color: #409EFF;
  color: white;
  font-weight: bold;
}

.data-row {
  background-color: white;
}

.table-cell {
  flex: 1;
  padding: 8px 10px; /* 内边距减小 */
  text-align: center;
  min-width: 50px; /* 最小宽度减小 */
  border-right: 1px solid #EBEEF5;
  box-sizing: border-box;
}

.table-cell:last-child {
  border-right: none;
}

.data-row .table-cell {
  font-size: 13px; /* 数据字体减小 */
  font-weight: 500;
  color: '#606266';
}

/* 在行之间添加分隔线 */
.header-row + .data-row,
.data-row + .header-row {
  border-top: 1px solid #EBEEF5;
}

/* 响应式适配 */
@media (max-width: 768px) {
  .table-cell {
    padding: 6px 8px; /* 移动端内边距更小 */
    min-width: 50px; /* 移动端最小宽度更小 */
    font-size: 12px; /* 移动端字体更小 */
  }
  
  /* 图表容器高度减小 */
  .el-col > div[ref="chart"] {
    height: 400px;
  }
  
  /* 月份选择器在小屏幕下全宽 */
  .el-date-picker {
    width: 100% !important;
  }
}
</style>
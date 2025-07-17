<template>
  <el-row>
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
import { countSalesByBranch } from "@/api/marketanalysis/saleanalysis/chart";
import * as echarts from 'echarts';

const chart = ref(null);
let myChart = null;
const tableData = ref([]); // 存储表格数据
const tableFirstPart = ref([]); // 存储表格第一部分数据
const tableSecondPart = ref([]); // 存储表格第二部分数据

async function initChart() {
  try {
    const response = await countSalesByBranch();
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
    
    // 初始化ECharts实例
    myChart = echarts.init(chart.value);
    const option = {
      title: {
        text: '各网点销售数据统计',
        left: 'center',
        textStyle: {
          fontSize: 16 // 图表标题稍小
        }
      },
      tooltip: {
        trigger: 'axis'
      },
      xAxis: {
        type: 'category',
        data: xAxisData,
        axisLabel: { 
          rotate: 45,
          interval: 0,
          fontSize: 12 // X轴标签字体稍小
        }
      },
      yAxis: { 
        type: 'value',
        name: '数量',
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
        barMaxWidth: 30, // 柱状图宽度减小
        // 添加数据标签配置
        label: {
          show: true,          // 显示标签
          position: 'top',     // 标签位置在柱子上方
          formatter: '{c}',    // 显示数值
          fontSize: 12,        // 标签字体大小
          fontWeight: 'bold',  // 加粗显示
          color: '#333'        // 字体颜色
        }
      }],
      grid: {
        bottom: 80 // 底部间距减小
      }
    };
    
    myChart.setOption(option);
    
    // 窗口调整时自适应
    window.addEventListener('resize', handleResize);
  } catch (error) {
    console.error('图表初始化失败:', error);
  }
}

function handleResize() {
  if (myChart) {
    myChart.resize();
  }
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
}
</style>
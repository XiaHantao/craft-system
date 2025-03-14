<template>
    <el-row>
      <el-col :span="24">
        <div ref="chart" style="height: 600px; margin-top: 20px;"></div>
      </el-col>
    </el-row>
  </template>
  
  <script setup>
  import { ref, onMounted, onBeforeUnmount } from 'vue';
  import { countSalesByBranch } from "@/api/marketanalysis/saleanalysis/chart";
  import * as echarts from 'echarts';
  
  const chart = ref(null);
  let myChart = null;
  
  async function initChart() {
    try {
      const response = await countSalesByBranch();
      if (!response || !response.data) {
        throw new Error('未能获取销售数据');
      }
      
      // 确保数据字段与后端返回一致（假设后端返回branch和count）
      const chartData = response.data;
      const xAxisData = chartData.map(item => item.name); // 使用name字段
      const seriesData = chartData.map(item => item.value); // 使用value字段
      
      // 初始化ECharts实例
      myChart = echarts.init(chart.value);
      const option = {
        title: {
          text: '各网点销售数据统计',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: xAxisData,
          axisLabel: { 
            rotate: 45,
            interval: 0 // 强制显示所有标签
          }
        },
        yAxis: { 
          type: 'value',
          name: '数量'
        },
        series: [{
          name: '销售数量',
          type: 'bar',
          data: seriesData,
          itemStyle: {
            color: '#409EFF'
          },
          barMaxWidth: 40 // 控制柱条宽度
        }],
        grid: {
          bottom: 100 // 防止标签被截断
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
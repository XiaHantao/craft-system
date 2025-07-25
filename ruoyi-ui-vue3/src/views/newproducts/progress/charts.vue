<template>
  <div class="app-container">
    <el-card>
      <div class="header">
        <h2>项目进度统计 - 项目: {{ projectCode }}</h2>
        <el-button type="primary" icon="Refresh" @click="refreshChart">刷新数据</el-button>
      </div>
      
      <!-- 物料统计部分 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <div class="chart-card">
            <h3>物料领用饼状图</h3>
            <div ref="receiveChart" style="width: 100%; height: 400px;"></div>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="chart-card">
            <h3>物料自制采购饼状图</h3>
            <div ref="completionChart" style="width: 100%; height: 400px;"></div>
          </div>
        </el-col>
      </el-row>
      
      <!-- 工序统计部分 -->
      <el-row :gutter="20" style="margin-top: 20px;">
        <el-col :span="12">
          <div class="chart-card">
            <h3>生产进度条状图</h3>
            <div ref="processBarChart" style="width: 100%; height: 500px;"></div>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="chart-card">
            <h3>生产进度饼状图</h3>
            <div ref="processRingChart" style="width: 100%; height: 500px;"></div>
          </div>
        </el-col>
      </el-row>
      
      <!-- 统计详情 -->
      <div class="stats-container">
        <el-descriptions title="物料统计详情" :column="2" border>
          <el-descriptions-item label="总物料数">{{ totalMaterials }}</el-descriptions-item>
          <el-descriptions-item label="已领用">{{ receivedMaterials }}</el-descriptions-item>
          <el-descriptions-item label="未领用">{{ pendingMaterials }}</el-descriptions-item>
          <el-descriptions-item label="领用率">{{ receiveRate }}%</el-descriptions-item>
          <el-descriptions-item label="自制已完成">{{ selfMadeCompleted }}</el-descriptions-item>
          <el-descriptions-item label="自制未完成">{{ selfMadePending }}</el-descriptions-item>
          <el-descriptions-item label="采购已到货">{{ purchasedArrived }}</el-descriptions-item>
          <el-descriptions-item label="采购未到货">{{ purchasedPending }}</el-descriptions-item>
        </el-descriptions>
        
        <el-descriptions title="生产进度详情" :column="8" border style="margin-top: 20px;">
          <el-descriptions-item 
            v-for="process in processData" 
            :key="process.name"
            :label="process.name"
          >
            {{ (process.value * 100).toFixed(1) }}%
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { useRoute } from 'vue-router';
import * as echarts from 'echarts';
import { listBom } from '@/api/newproducts/bom';
import { getProgressByProjectCode } from '@/api/newproducts/progress';

const route = useRoute();
const receiveChart = ref(null);
const completionChart = ref(null);
const processBarChart = ref(null);
const processRingChart = ref(null);
let receiveChartInstance = null;
let completionChartInstance = null;
let processBarChartInstance = null;
let processRingChartInstance = null;

// 数据状态
const projectCode = ref('');
const totalMaterials = ref(0);
const receivedMaterials = ref(0);
const pendingMaterials = ref(0);
const receiveRate = ref(0);

// 物料完成情况数据
const selfMadeCompleted = ref(0);
const selfMadePending = ref(0);
const purchasedArrived = ref(0);
const purchasedPending = ref(0);

// 工序数据
const processData = ref([
  { name: '备料', value: 0 },
  { name: '金工', value: 0 },
  { name: '门架结构', value: 0 },
  { name: '车架结构', value: 0 },
  { name: '涂装', value: 0 },
  { name: '门架装配', value: 0 },
  { name: '车体装配', value: 0 },
  { name: '试车', value: 0 }
]);

// 初始化领用情况图表（添加百分比标签）
const initReceiveChart = () => {
  if (!receiveChart.value) return;
  
  receiveChartInstance = echarts.init(receiveChart.value);
  
  const option = {
    title: {
      text: '物料领用情况',
      subtext: `项目: ${projectCode.value}`,
      left: 'center'
    },
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      left: 'left',
      data: ['已领用', '未领用']
    },
    series: [
      {
        name: '物料领用',
        type: 'pie',
        radius: '50%',
        data: [
          { value: receivedMaterials.value, name: '已领用' },
          { value: pendingMaterials.value, name: '未领用' }
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        },
        // 添加百分比标签
        label: {
          show: true,
          formatter: '{b}: {d}%'
        }
      }
    ]
  };
  
  receiveChartInstance.setOption(option);
};

// 初始化完成情况图表（添加百分比标签）
const initCompletionChart = () => {
  if (!completionChart.value) return;
  
  completionChartInstance = echarts.init(completionChart.value);
  
  const option = {
    title: {
      text: '物料完成情况',
      subtext: `项目: ${projectCode.value}`,
      left: 'center'
    },
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      right: 'right',
      data: ['自制已完成', '自制未完成', '采购已到货', '采购未到货']
    },
    series: [
      {
        name: '物料完成情况',
        type: 'pie',
        radius: '50%',
        data: [
          { value: selfMadeCompleted.value, name: '自制已完成' },
          { value: selfMadePending.value, name: '自制未完成' },
          { value: purchasedArrived.value, name: '采购已到货' },
          { value: purchasedPending.value, name: '采购未到货' }
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        },
        // 添加百分比标签
        label: {
          show: true,
          formatter: '{b}: {d}%'
        }
      }
    ]
  };
  
  completionChartInstance.setOption(option);
};

// 初始化工序条形图
const initProcessBarChart = () => {
  if (!processBarChart.value) return;
  
  processBarChartInstance = echarts.init(processBarChart.value);
  
  const completedData = processData.value.map(item => item.value);
  const pendingData = processData.value.map(item => 1 - item.value);
  
  const option = {
    title: {
      text: '生产进度',
      subtext: `项目: ${projectCode.value}`,
      left: 'center'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      },
      formatter: function(params) {
        const completed = params[0];
        const pending = params[1];
        return `
          ${completed.marker} ${completed.seriesName}: ${(completed.value * 100).toFixed(1)}%<br/>
          ${pending.marker} ${pending.seriesName}: ${(pending.value * 100).toFixed(1)}%<br/>
          总进度: ${((completed.value) * 100).toFixed(1)}%
        `;
      }
    },
    legend: {
      data: ['已完成', '未完成'],
      bottom: 10
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '15%',
      top: '15%',
      containLabel: true
    },
    xAxis: {
      type: 'value',
      max: 1,
      axisLabel: {
        formatter: function(value) {
          return (value * 100) + '%';
        }
      }
    },
    yAxis: {
      type: 'category',
      data: processData.value.map(item => item.name)
    },
    series: [
      {
        name: '已完成',
        type: 'bar',
        stack: 'total',
        barWidth: '60%',
        label: {
          show: true,
          formatter: function(params) {
            return (params.value * 100).toFixed(1) + '%';
          },
          position: 'insideRight'
        },
        itemStyle: {
          color: '#36a3f7'
        },
        data: completedData
      },
      {
        name: '未完成',
        type: 'bar',
        stack: 'total',
        barWidth: '60%',
        label: {
          show: true,
          formatter: function(params) {
            return (params.value * 100).toFixed(1) + '%';
          },
          position: 'insideRight'
        },
        itemStyle: {
          color: '#e6e6e6'
        },
        data: pendingData
      }
    ]
  };
  
  processBarChartInstance.setOption(option);
};

// 初始化工序饼状图（修改为物料饼状图样式）
const initProcessRingChart = () => {
  if (!processRingChart.value) return;
  
  processRingChartInstance = echarts.init(processRingChart.value);
  
  // 准备饼图数据 - 每个工序分成已完成和未完成两部分
  const pieData = [];
  const completedColors = [
    '#8ECFC9', '#FFBE7A', '#FA7F6F', '#82B0D2',
    '#A2D2CE', '#FFD8A7', '#FFA7A7', '#A7C7E7'
  ];
  
  const pendingColors = completedColors.map(color => color + '80'); // 添加透明度
  
  processData.value.forEach((process, index) => {
    // 已完成部分
    pieData.push({
      name: process.name + '-已完成',
      value: process.value,
      itemStyle: {
        color: completedColors[index]
      }
    });
    
    // 未完成部分
    pieData.push({
      name: process.name + '-未完成',
      value: 1 - process.value,
      itemStyle: {
        color: pendingColors[index]
      }
    });
  });
  
  const option = {
    title: {
      text: '生产进度',
      subtext: `项目: ${projectCode.value}`,
      left: 'center'
    },
    tooltip: {
      trigger: 'item',
      formatter: function(params) {
        const percentage = (params.value * 100).toFixed(1);
        return `${params.name.replace('-已完成', '').replace('-未完成', '')}<br/>
                ${params.name.includes('已完成') ? '已完成' : '未完成'}: ${percentage}%`;
      }
    },
    legend: {
      show: false // 不显示图例，避免过多项
    },
    series: [
      {
        name: '工序完成率',
        type: 'pie',
        radius: '70%', // 实心饼图
        center: ['50%', '50%'],
        data: pieData,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        },
        // 修改标签显示格式，与tooltip保持一致
        label: {
          show: true,
          formatter: function(params) {
           // 直接显示完整的名称和百分比
            return `${params.name}: ${(params.value * 100).toFixed(1)}%`;
          }
        },
        labelLine: {
          length: 15,
          length2: 20
        }
      }
    ]
  };
  
  processRingChartInstance.setOption(option);
};

// 获取数据
const fetchData = async () => {
  try {
    // 获取BOM数据
    const bomRes = await listBom({ projectCode: projectCode.value });
    const bomList = bomRes.data || [];
    
    // 获取Progress数据
    const progressRes = await getProgressByProjectCode(projectCode.value);
    const progressData = progressRes.data || {};
    
    // 重置物料计数器
    totalMaterials.value = bomList.length;
    receivedMaterials.value = 0;
    pendingMaterials.value = 0;
    
    // 重置物料完成情况计数器
    selfMadeCompleted.value = 0;
    selfMadePending.value = 0;
    purchasedArrived.value = 0;
    purchasedPending.value = 0;
    
    // 处理BOM数据
    const traverse = (nodes) => {
      nodes.forEach(node => {
        // 领用情况统计
        if (node.extField2 === '已领用') {
          receivedMaterials.value++;
        } else {
          pendingMaterials.value++;
        }
        
        // 物料完成情况统计
        if (node.purchaseType === 'E') { // 自制
          if (node.arrivalStatus === '已完成') {
            selfMadeCompleted.value++;
          } else {
            selfMadePending.value++;
          }
        } else if (node.purchaseType === 'F') { // 采购
          if (node.arrivalStatus === '已到货') {
            purchasedArrived.value++;
          } else {
            purchasedPending.value++;
          }
        }
        
        if (node.children && node.children.length > 0) {
          traverse(node.children);
        }
      });
    };
    
    traverse(bomList);
    
    // 计算领用率
    receiveRate.value = totalMaterials.value > 0 
      ? Math.round((receivedMaterials.value / totalMaterials.value) * 100) 
      : 0;
    
    // 处理工序数据
    processData.value = [
      { name: '备料', value: parseProgressValue(progressData.materialPreparation) },
      { name: '金工', value: parseProgressValue(progressData.metalworking) },
      { name: '门架结构', value: parseProgressValue(progressData.mastStructure) },
      { name: '车架结构', value: parseProgressValue(progressData.frameStructure) },
      { name: '涂装', value: parseProgressValue(progressData.painting) },
      { name: '门架装配', value: parseProgressValue(progressData.mastAssembly) },
      { name: '车体装配', value: parseProgressValue(progressData.bodyAssembly) },
      { name: '试车', value: parseProgressValue(progressData.testDrive) }
    ];
    
    // 更新所有图表
    updateCharts();
  } catch (error) {
    console.error('获取数据失败:', error);
  }
};

// 解析进度值 - 增强版，支持小数和百分数，并限制在0-1之间
const parseProgressValue = (value) => {
  if (value === null || value === undefined) return 0;
  
  // 尝试转换为数字
  const num = parseFloat(value);
  if (!isNaN(num)) {
    let normalizedValue;
    // 如果数字大于1，则认为是百分比格式（如40）
    if (num > 1) {
      normalizedValue = num / 100;
    } else {
      normalizedValue = num;
    }
    
    // 确保值在0-1之间
    if (normalizedValue > 1) {
      return 1; // 超过100%的按100%处理
    } else if (normalizedValue < 0) {
      return 0; // 负数的按0%处理
    } else {
      return normalizedValue;
    }
  }
  
  // 处理百分比字符串（如"40%"）
  if (typeof value === 'string') {
    // 移除所有空格和百分号
    const cleanValue = value.replace(/\s+/g, '').replace('%', '');
    const percentValue = parseFloat(cleanValue);
    if (!isNaN(percentValue)) {
      // 确保百分比值在0-100之间
      if (percentValue > 100) {
        return 1; // 超过100%的按100%处理
      } else if (percentValue < 0) {
        return 0; // 负数的按0%处理
      } else {
        return percentValue / 100;
      }
    }
  }
  
  return 0;
};

// 更新所有图表
const updateCharts = () => {
  if (receiveChartInstance) {
    receiveChartInstance.setOption({
      series: [{ 
        data: [
          { value: receivedMaterials.value, name: '已领用' },
          { value: pendingMaterials.value, name: '未领用' }
        ]
      }]
    });
  }
  
  if (completionChartInstance) {
    completionChartInstance.setOption({
      series: [{
        data: [
          { value: selfMadeCompleted.value, name: '自制已完成' },
          { value: selfMadePending.value, name: '自制未完成' },
          { value: purchasedArrived.value, name: '采购已到货' },
          { value: purchasedPending.value, name: '采购未到货' }
        ]
      }]
    });
  }
  
  if (processBarChartInstance) {
    const completedData = processData.value.map(item => item.value);
    const pendingData = processData.value.map(item => 1 - item.value);
    
    processBarChartInstance.setOption({
      series: [
        { data: completedData },
        { data: pendingData }
      ]
    });
  }
  
  if (processRingChartInstance) {
    // 重新构建饼图数据
    const pieData = [];
    const completedColors = [
      '#8ECFC9', '#FFBE7A', '#FA7F6F', '#82B0D2',
      '#A2D2CE', '#FFD8A7', '#FFA7A7', '#A7C7E7'
    ];
    const pendingColors = completedColors.map(color => color + '80');
    
    processData.value.forEach((process, index) => {
      pieData.push({
        name: process.name + '-已完成',
        value: process.value,
        itemStyle: { color: completedColors[index] }
      });
      pieData.push({
        name: process.name + '-未完成',
        value: 1 - process.value,
        itemStyle: { color: pendingColors[index] }
      });
    });
    
    processRingChartInstance.setOption({
      series: [{
        data: pieData
      }]
    });
  }
};

// 刷新图表
const refreshChart = () => {
  fetchData();
};

// 组件挂载时初始化
onMounted(() => {
  projectCode.value = route.query.projectCode || '';
  
  if (projectCode.value) {
    fetchData().then(() => {
      initReceiveChart();
      initCompletionChart();
      initProcessBarChart();
      initProcessRingChart();
    });
  }
  
  // 响应窗口大小变化
  window.addEventListener('resize', handleResize);
});

// 组件卸载前清理
onBeforeUnmount(() => {
  if (receiveChartInstance) {
    receiveChartInstance.dispose();
    receiveChartInstance = null;
  }
  if (completionChartInstance) {
    completionChartInstance.dispose();
    completionChartInstance = null;
  }
  if (processBarChartInstance) {
    processBarChartInstance.dispose();
    processBarChartInstance = null;
  }
  if (processRingChartInstance) {
    processRingChartInstance.dispose();
    processRingChartInstance = null;
  }
  window.removeEventListener('resize', handleResize);
});

// 处理窗口大小变化
const handleResize = () => {
  if (receiveChartInstance) receiveChartInstance.resize();
  if (completionChartInstance) completionChartInstance.resize();
  if (processBarChartInstance) processBarChartInstance.resize();
  if (processRingChartInstance) processRingChartInstance.resize();
};
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.chart-card {
  border: 1px solid #ebeef5;
  border-radius: 4px;
  padding: 20px;
  margin-bottom: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.chart-card h3 {
  text-align: center;
  margin-bottom: 15px;
}

.stats-container {
  margin-top: 30px;
}
</style>
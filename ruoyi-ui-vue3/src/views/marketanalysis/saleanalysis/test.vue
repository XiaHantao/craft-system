<template>
    <div class="config-compare">
      <!-- 车型选择区 -->
      <div class="car-selectors">
        <el-select
          v-for="(_, index) in selectedCars"
          :key="index"
          v-model="selectedCars[index]"
          filterable
          remote
          :remote-method="querySearch"
          placeholder="输入车型名称"
          class="car-selector"
        >
          <el-option
            v-for="item in filteredCars"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </div>
  
      <!-- 配置项管理 -->
      <div class="config-control">
        <el-select
          v-model="selectedConfig"
          placeholder="添加对比配置项"
          filterable
          class="config-select"
        >
          <el-option
            v-for="config in configList"
            :key="config"
            :label="config"
            :value="config"
          />
        </el-select>
        <el-button @click="addConfig">添加配置项</el-button>
      </div>
  
      <!-- 对比表格 -->
      <el-table :data="comparedConfigs" border class="compare-table">
        <el-table-column prop="name" label="配置项" fixed width="200" />
        <el-table-column v-for="(car, index) in selectedCarDetails" :key="index">
          <template #header>
            {{ car?.name || `车型${index + 1}` }}
          </template>
          <template #default="{ row }">
            <span :class="getCellClass(row.name, car)">
              {{ getConfigValue(row.name, car) }}
            </span>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </template>
  
  <script setup>
  import { ref, computed } from 'vue'
  
  // 模拟数据
  const cars = ref([
    {
      id: 1,
      name: 'Model A 2023',
      configs: {
        '发动机': { isStandard: true, value: '2.0T' },
        '座椅加热': { isStandard: true },
        '天窗': { isStandard: false },
        '自动驾驶': { isStandard: true, value: 'L2' }
      }
    },
    {
      id: 2,
      name: 'Model B Pro',
      configs: {
        '发动机': { isStandard: true, value: '1.5T' },
        '座椅加热': { isStandard: false },
        '氛围灯': { isStandard: true },
        '自动驾驶': { isStandard: true, value: 'L3' }
      }
    }
  ])
  
  // 所有可能的配置列表
  const fullConfigList = [
    '发动机', '座椅加热', '天窗', 
    '自动驾驶', '氛围灯', '电动尾门'
  ]
  
  // 响应式数据
  const selectedCars = ref([null, null])
  const searchQuery = ref('')
  const addedConfigs = ref(new Set())
  const selectedConfig = ref('')
  
  // 计算属性
  const filteredCars = computed(() => {
    return cars.value.filter(item => 
      item.name.toLowerCase().includes(searchQuery.value.toLowerCase())
    )
  })
  
  const selectedCarDetails = computed(() => {
    return selectedCars.value.map(id => 
      cars.value.find(car => car.id === id))
  })
  
  const comparedConfigs = computed(() => {
    const configs = new Set([...addedConfigs.value])
    
    // 自动包含两个车型的所有配置项
    selectedCarDetails.value.forEach(car => {
      if (car) {
        Object.keys(car.configs).forEach(key => configs.add(key))
      }
    })
    
    return Array.from(configs).map(name => ({ name }))
  })
  
  // 方法
  const querySearch = (query) => {
    searchQuery.value = query
  }
  
  const addConfig = () => {
    if (selectedConfig.value && !addedConfigs.value.has(selectedConfig.value)) {
      addedConfigs.value.add(selectedConfig.value)
    }
    selectedConfig.value = ''
  }
  
  const getConfigValue = (configName, car) => {
    if (!car) return '-'
    const config = car.configs[configName]
    if (!config) return '未配备'
    return config.value || (config.isStandard ? '●' : '○')
  }
  
  const getCellClass = (configName, car) => {
    const current = car?.configs[configName]
    const otherCar = selectedCarDetails.value.find(c => c !== car)
    const other = otherCar?.configs[configName]
    
    if (!current || !other) return 'diff-cell'
    if (current.isStandard !== other.isStandard) return 'diff-cell'
    if (current.value !== other.value) return 'diff-cell'
    return ''
  }
  </script>
  
  <style scoped>
  .config-compare {
    padding: 20px;
  }
  
  .car-selectors {
    display: flex;
    gap: 20px;
    margin-bottom: 20px;
  }
  
  .car-selector {
    width: 300px;
  }
  
  .config-control {
    display: flex;
    gap: 10px;
    margin-bottom: 20px;
  }
  
  .config-select {
    width: 200px;
  }
  
  .compare-table {
    width: 100%;
  }
  
  .diff-cell {
    background-color: #fff3f3;
    color: #f56c6c;
    font-weight: bold;
  }
  </style>
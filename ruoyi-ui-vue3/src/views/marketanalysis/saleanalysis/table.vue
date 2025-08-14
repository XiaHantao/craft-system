<template>
  <div class="app-container">
    <!-- 搜索框 -->
    <div class="filter-container">
      <el-row :gutter="10" align="middle">
        <!-- 添加年月选择器 -->
        <el-col :span="6">
          <el-date-picker
            v-model="selectedMonth"
            type="month"
            placeholder="选择年月"
            value-format="YYYY-MM"
            style="width: 100%"
          />
        </el-col>
        
        <el-col :span="6">
          <el-input
            v-model="searchVehicle"
            placeholder="输入车型查询"
            clearable
          />
        </el-col>
        <el-col :span="4">
          <el-button
            type="primary"
            @click="handleFilter"
            style="margin-left: 10px"
          >
            搜索
          </el-button>
        </el-col>
      </el-row>
    </div>
  
    <!-- 数据表格 -->
    <el-table
      :data="tableData" 
      border
      fit
      highlight-current-row
      v-loading="loading"
      style="width: 100%"
    >
      <el-table-column
        prop="name"
        label="车型"
        align="center"
      />
      <el-table-column
        prop="value"
        label="接单数"
        align="center"
      />
    </el-table>

    <!-- 分页组件 -->
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="fetchData"
    />
  </div>
</template>

<script>
import { countByVehicle } from '@/api/marketanalysis/saleanalysis/saleanalysis'

export default {
  data() {
    return {
      tableData: [],
      searchVehicle: '',
      selectedMonth: '', // 新增：选中的年月
      loading: true,
      total: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleType: null,
        month: null // 新增：月份参数
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    async fetchData() {
      this.loading = true
      try {
        const params = {
          pageNum: this.queryParams.pageNum,
          pageSize: this.queryParams.pageSize,
          vehicleType: this.queryParams.vehicleType,
          month: this.queryParams.month // 新增：传递月份参数
        }
        const response = await countByVehicle(params)
        this.tableData = response.rows
        this.total = Number(response.total)
      } catch (error) {
        console.error('数据加载失败:', error)
      } finally {
        this.loading = false
      }
    },
    handleFilter() {
      this.queryParams.pageNum = 1
      this.queryParams.vehicleType = this.searchVehicle
      this.queryParams.month = this.selectedMonth // 新增：设置月份参数
      this.fetchData()
    }
  }
}
</script>

<!-- 样式部分保持不变 -->
<style scoped>
.filter-container {
  margin-bottom: 20px;
}
.app-container {
  padding: 20px;
}
.el-row {
  margin-bottom: 10px;
}
</style>
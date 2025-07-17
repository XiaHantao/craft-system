<template>
  <div class="app-container">
    <!-- 搜索框 -->
    <div class="filter-container">
      <el-row :gutter="10" align="middle">
        <el-col :span="6">
          <el-input
            v-model="searchCategory"
            placeholder="输入车类查询"
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
        label="车类"
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
import { countByCategory } from '@/api/marketanalysis/saleanalysis/chart_a'

export default {
  data() {
    return {
      tableData: [],
      searchCategory: '', // 改为车类
      loading: true,
      total: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleCategory: null // 参数改为车类
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
          vehicleCategory: this.queryParams.vehicleCategory // 使用车类参数
        }
        const response = await countByCategory(params)
        this.tableData = response.rows
        this.total = Number(response.total)
      } catch (error) {
        console.error('数据加载失败:', error)
        this.$message.error('数据加载失败，请重试')
      } finally {
        this.loading = false
      }
    },
    handleFilter() {
      this.queryParams.pageNum = 1
      this.queryParams.vehicleCategory = this.searchCategory
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
import request from '@/utils/request'

// 按月份统计
export function countSalesByMonth1(params) {
  return request({
    url: '/marketanalysis/saleanalysis/countByMonth1',
    method: 'get',
    params
  })
}

// 按网点统计（车类）
export function countSalesByBranchForVehicle1(params) {
  return request({
    url: '/marketanalysis/saleanalysis/countByBranchForVehicle1',
    method: 'get',
    params
  })
}

// 按车类统计（网点）
export function countSalesByVehicleForBranch1(params) {
  return request({
    url: '/marketanalysis/saleanalysis/countByVehicleForBranch1',
    method: 'get',
    params
  })
}

// 获取所有车类
export function getCategories() {
  return request({
    url: '/marketanalysis/saleanalysis/categories',
    method: 'get'
  })
}

// 获取所有网点
export function getBranches() {
  return request({
    url: '/marketanalysis/saleanalysis/branches',
    method: 'get'
  })
}
// 按车类统计
export function countMonthlyComparison1(params) {
  return request({
    url: '/marketanalysis/saleanalysis/countMonthlyComparison1',
    method: 'get',
    params  // 包含year, vehicleCategory, branch
  })
}
// 查询所有车型销量统计
export function countByCategory(query) {
  return request({
    url: '/marketanalysis/saleanalysis/countByCategory',
    method: 'get',
    params: query
  })
}
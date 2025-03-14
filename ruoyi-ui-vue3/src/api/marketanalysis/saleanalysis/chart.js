import request from '@/utils/request'
// 新增统计接口调用
export function countSalesByBranch() {
    return request({
      url: '/marketanalysis/saleanalysis/countByBranch',
      method: 'get'
    })
  }
  // 按月份统计
export function countSalesByMonth(params) {
  return request({
    url: '/marketanalysis/saleanalysis/countByMonth',
    method: 'get',
    params
  })
}

// 按网点统计（车型）
export function countSalesByBranchForVehicle(params) {
  return request({
    url: '/marketanalysis/saleanalysis/countByBranchForVehicle',
    method: 'get',
    params
  })
}
// 修改countMonthlyComparison函数，添加年份参数
export function countMonthlyComparison(params) {
  return request({
    url: '/marketanalysis/saleanalysis/countMonthlyComparison',
    method: 'get',
    params  // 包含year, vehicleType, branch
  })
}
// chart.js 新增接口
// 按车型统计（网点）
export function countSalesByVehicleForBranch(params) {
  return request({
    url: '/marketanalysis/saleanalysis/countByVehicleForBranch',
    method: 'get',
    params
  })
}
// 获取所有车型
export function getVehicleTypes() {
  return request({
    url: '/marketanalysis/saleanalysis/vehicleTypes',
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
import request from '@/utils/request'

// 查询工艺优化与改进列表
export function listProcessOptimizationAndImprovement(query) {
  return request({
    url: '/process/ProcessOptimizationAndImprovement/list',
    method: 'get',
    params: query
  })
}

// 查询工艺优化与改进详细
export function getProcessOptimizationAndImprovement(id) {
  return request({
    url: '/process/ProcessOptimizationAndImprovement/' + id,
    method: 'get'
  })
}

// 新增工艺优化与改进
export function addProcessOptimizationAndImprovement(data) {
  return request({
    url: '/process/ProcessOptimizationAndImprovement',
    method: 'post',
    data: data
  })
}

// 修改工艺优化与改进
export function updateProcessOptimizationAndImprovement(data) {
  return request({
    url: '/process/ProcessOptimizationAndImprovement',
    method: 'put',
    data: data
  })
}

// 删除工艺优化与改进
export function delProcessOptimizationAndImprovement(id) {
  return request({
    url: '/process/ProcessOptimizationAndImprovement/' + id,
    method: 'delete'
  })
}

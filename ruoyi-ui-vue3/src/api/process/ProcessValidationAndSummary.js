import request from '@/utils/request'

// 查询工艺验证与总结列表
export function listProcessValidationAndSummary(query) {
  return request({
    url: '/process/ProcessValidationAndSummary/list',
    method: 'get',
    params: query
  })
}

// 查询工艺验证与总结详细
export function getProcessValidationAndSummary(id) {
  return request({
    url: '/process/ProcessValidationAndSummary/' + id,
    method: 'get'
  })
}

// 新增工艺验证与总结
export function addProcessValidationAndSummary(data) {
  return request({
    url: '/process/ProcessValidationAndSummary',
    method: 'post',
    data: data
  })
}

// 修改工艺验证与总结
export function updateProcessValidationAndSummary(data) {
  return request({
    url: '/process/ProcessValidationAndSummary',
    method: 'put',
    data: data
  })
}

// 删除工艺验证与总结
export function delProcessValidationAndSummary(id) {
  return request({
    url: '/process/ProcessValidationAndSummary/' + id,
    method: 'delete'
  })
}

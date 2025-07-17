import request from '@/utils/request'

// 查询性能表列表
export function listPerformance(query) {
  return request({
    url: '/marketanalysis/performance/list',
    method: 'get',
    params: query
  })
}

// 查询性能表详细
export function getPerformance(id) {
  return request({
    url: '/marketanalysis/performance/' + id,
    method: 'get'
  })
}

// 新增性能表
export function addPerformance(data) {
  return request({
    url: '/marketanalysis/performance',
    method: 'post',
    data: data
  })
}

// 修改性能表
export function updatePerformance(data) {
  return request({
    url: '/marketanalysis/performance',
    method: 'put',
    data: data
  })
}

// 删除性能表
export function delPerformance(id) {
  return request({
    url: '/marketanalysis/performance/' + id,
    method: 'delete'
  })
}
export function importP(data) {
  return request({
    url: '/marketanalysis/performance/importData',
    method: 'post',
    data: data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}

export function checkDataExists() {
  return request({
    url: '/marketanalysis/performance/checkDataExists',
    method: 'get'
  })
}
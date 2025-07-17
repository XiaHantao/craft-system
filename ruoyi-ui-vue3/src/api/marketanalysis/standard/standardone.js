import request from '@/utils/request'

// 查询一类车标准配置列表
export function listStandardone(query) {
  return request({
    url: '/marketanalysis/standardone/list',
    method: 'get',
    params: query
  })
}

// 查询一类车标准配置详细
export function getStandardone(id) {
  return request({
    url: '/marketanalysis/standardone/' + id,
    method: 'get'
  })
}

// 新增一类车标准配置
export function addStandardone(data) {
  return request({
    url: '/marketanalysis/standardone',
    method: 'post',
    data: data
  })
}

// 修改一类车标准配置
export function updateStandardone(data) {
  return request({
    url: '/marketanalysis/standardone',
    method: 'put',
    data: data
  })
}

// 删除一类车标准配置
export function delStandardone(id) {
  return request({
    url: '/marketanalysis/standardone/' + id,
    method: 'delete'
  })
}
export function importstandard_one(data) {
  return request({
    url: '/marketanalysis/standardone/importData',
    method: 'post',
    data: data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}

export function checkDataExists() {
  return request({
    url: '/marketanalysis/standardone/checkDataExists',
    method: 'get'
  })
}
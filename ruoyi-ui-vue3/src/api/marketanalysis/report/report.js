import request from '@/utils/request'

// 查询试验报告列表
export function listReport(query) {
  return request({
    url: '/marketanalysis/report/list',
    method: 'get',
    params: query
  })
}

// 查询试验报告详细
export function getReport(id) {
  return request({
    url: '/marketanalysis/report/' + id,
    method: 'get'
  })
}

// 新增试验报告
export function addReport(data) {
  return request({
    url: '/marketanalysis/report',
    method: 'post',
    data: data
  })
}

// 修改试验报告
export function updateReport(data) {
  return request({
    url: '/marketanalysis/report',
    method: 'put',
    data: data
  })
}

// 删除试验报告
export function delReport(id) {
  return request({
    url: '/marketanalysis/report/' + id,
    method: 'delete'
  })
}

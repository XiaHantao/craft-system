import request from '@/utils/request'

// 查询七类车参数列表
export function listParameterseven(query) {
  return request({
    url: '/marketanalysis/parameterseven/list',
    method: 'get',
    params: query
  })
}

// 查询七类车参数详细
export function getParameterseven(id) {
  return request({
    url: '/marketanalysis/parameterseven/' + id,
    method: 'get'
  })
}

// 新增七类车参数
export function addParameterseven(data) {
  return request({
    url: '/marketanalysis/parameterseven',
    method: 'post',
    data: data
  })
}

// 修改七类车参数
export function updateParameterseven(data) {
  return request({
    url: '/marketanalysis/parameterseven',
    method: 'put',
    data: data
  })
}

// 删除七类车参数
export function delParameterseven(id) {
  return request({
    url: '/marketanalysis/parameterseven/' + id,
    method: 'delete'
  })
}

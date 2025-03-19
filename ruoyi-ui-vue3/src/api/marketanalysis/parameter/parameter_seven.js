import request from '@/utils/request'

// 查询七类车参数列表
export function listParameter_seven(query) {
  return request({
    url: '/marketanalysis/parameter_seven/list',
    method: 'get',
    params: query
  })
}

// 查询七类车参数详细
export function getParameter_seven(id) {
  return request({
    url: '/marketanalysis/parameter_seven/' + id,
    method: 'get'
  })
}

// 新增七类车参数
export function addParameter_seven(data) {
  return request({
    url: '/marketanalysis/parameter_seven',
    method: 'post',
    data: data
  })
}

// 修改七类车参数
export function updateParameter_seven(data) {
  return request({
    url: '/marketanalysis/parameter_seven',
    method: 'put',
    data: data
  })
}

// 删除七类车参数
export function delParameter_seven(id) {
  return request({
    url: '/marketanalysis/parameter_seven/' + id,
    method: 'delete'
  })
}

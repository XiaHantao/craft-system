import request from '@/utils/request'

// 查询一类车参数列表
export function listParameter_one(query) {
  return request({
    url: '/marketanalysis/parameter_one/list',
    method: 'get',
    params: query
  })
}

// 查询一类车参数详细
export function getParameter_one(id) {
  return request({
    url: '/marketanalysis/parameter_one/' + id,
    method: 'get'
  })
}

// 新增一类车参数
export function addParameter_one(data) {
  return request({
    url: '/marketanalysis/parameter_one',
    method: 'post',
    data: data
  })
}

// 修改一类车参数
export function updateParameter_one(data) {
  return request({
    url: '/marketanalysis/parameter_one',
    method: 'put',
    data: data
  })
}

// 删除一类车参数
export function delParameter_one(id) {
  return request({
    url: '/marketanalysis/parameter_one/' + id,
    method: 'delete'
  })
}

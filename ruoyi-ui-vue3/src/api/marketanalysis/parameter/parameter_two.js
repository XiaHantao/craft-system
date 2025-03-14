import request from '@/utils/request'

// 查询二类车参数列表
export function listParameter_two(query) {
  return request({
    url: '/marketanalysis/parameter_two/list',
    method: 'get',
    params: query
  })
}

// 查询二类车参数详细
export function getParameter_two(id) {
  return request({
    url: '/marketanalysis/parameter_two/' + id,
    method: 'get'
  })
}

// 新增二类车参数
export function addParameter_two(data) {
  return request({
    url: '/marketanalysis/parameter_two',
    method: 'post',
    data: data
  })
}

// 修改二类车参数
export function updateParameter_two(data) {
  return request({
    url: '/marketanalysis/parameter_two',
    method: 'put',
    data: data
  })
}

// 删除二类车参数
export function delParameter_two(id) {
  return request({
    url: '/marketanalysis/parameter_two/' + id,
    method: 'delete'
  })
}

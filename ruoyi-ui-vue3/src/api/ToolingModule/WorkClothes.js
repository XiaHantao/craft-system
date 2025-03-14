import request from '@/utils/request'

// 查询工装台账列表
export function listWorkClothes(query) {
  return request({
    url: '/ToolingModule/WorkClothes/list',
    method: 'get',
    params: query
  })
}

// 查询工装台账详细
export function getWorkClothes(id) {
  return request({
    url: '/ToolingModule/WorkClothes/' + id,
    method: 'get'
  })
}

// 新增工装台账
export function addWorkClothes(data) {
  return request({
    url: '/ToolingModule/WorkClothes',
    method: 'post',
    data: data
  })
}

// 修改工装台账
export function updateWorkClothes(data) {
  return request({
    url: '/ToolingModule/WorkClothes',
    method: 'put',
    data: data
  })
}

// 删除工装台账
export function delWorkClothes(id) {
  return request({
    url: '/ToolingModule/WorkClothes/' + id,
    method: 'delete'
  })
}

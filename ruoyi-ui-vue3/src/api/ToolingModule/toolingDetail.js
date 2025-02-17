import request from '@/utils/request'

// 查询工装详细列表
export function listToolingDetail(query) {
  return request({
    url: '/ToolingModule/toolingDetail/list',
    method: 'get',
    params: query
  })
}

// 查询工装详细详细
export function getToolingDetail(id) {
  return request({
    url: '/ToolingModule/toolingDetail/' + id,
    method: 'get'
  })
}

// 新增工装详细
export function addToolingDetail(data) {
  return request({
    url: '/ToolingModule/toolingDetail',
    method: 'post',
    data: data
  })
}

// 修改工装详细
export function updateToolingDetail(data) {
  return request({
    url: '/ToolingModule/toolingDetail',
    method: 'put',
    data: data
  })
}

// 删除工装详细
export function delToolingDetail(id) {
  return request({
    url: '/ToolingModule/toolingDetail/' + id,
    method: 'delete'
  })
}

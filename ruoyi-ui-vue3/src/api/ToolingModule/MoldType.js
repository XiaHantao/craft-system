import request from '@/utils/request'

// 查询工装类别字典列表
export function listMoldType(query) {
  return request({
    url: '/ToolingModule/MoldType/list',
    method: 'get',
    params: query
  })
}

// 查询工装类别字典详细
export function getMoldType(id) {
  return request({
    url: '/ToolingModule/MoldType/' + id,
    method: 'get'
  })
}

// 新增工装类别字典
export function addMoldType(data) {
  return request({
    url: '/ToolingModule/MoldType',
    method: 'post',
    data: data
  })
}

// 修改工装类别字典
export function updateMoldType(data) {
  return request({
    url: '/ToolingModule/MoldType',
    method: 'put',
    data: data
  })
}

// 删除工装类别字典
export function delMoldType(id) {
  return request({
    url: '/ToolingModule/MoldType/' + id,
    method: 'delete'
  })
}

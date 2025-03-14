import request from '@/utils/request'

// 查询车型列表
export function listModelTable(query) {
  return request({
    url: '/process/modelTable/list',
    method: 'get',
    params: query
  })
}

// 查询车型详细
export function getModelTable(id) {
  return request({
    url: '/process/modelTable/' + id,
    method: 'get'
  })
}

// 新增车型
export function addModelTable(data) {
  return request({
    url: '/process/modelTable',
    method: 'post',
    data: data
  })
}

// 修改车型
export function updateModelTable(data) {
  return request({
    url: '/process/modelTable',
    method: 'put',
    data: data
  })
}

// 删除车型
export function delModelTable(id) {
  return request({
    url: '/process/modelTable/' + id,
    method: 'delete'
  })
}

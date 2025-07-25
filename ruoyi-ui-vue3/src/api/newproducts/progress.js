import request from '@/utils/request'

// 查询新产品生产进度跟踪列表
export function listProgress(query) {
  return request({
    url: '/newproducts/progress/list',
    method: 'get',
    params: query
  })
}

// 查询新产品生产进度跟踪详细
export function getProgress(id) {
  return request({
    url: '/newproducts/progress/' + id,
    method: 'get'
  })
}

// 新增新产品生产进度跟踪
export function addProgress(data) {
  return request({
    url: '/newproducts/progress',
    method: 'post',
    data: data
  })
}

// 修改新产品生产进度跟踪
export function updateProgress(data) {
  return request({
    url: '/newproducts/progress',
    method: 'put',
    data: data
  })
}

// 删除新产品生产进度跟踪
export function delProgress(id) {
  return request({
    url: '/newproducts/progress/' + id,
    method: 'delete'
  })
}
export function getProgressByProjectCode(projectCode) {
  return request({
    url: '/newproducts/progress/getByProjectCode',
    method: 'get',
    params: { projectCode }
  })
}
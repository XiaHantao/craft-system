import request from '@/utils/request'

// 查询新产品生产完成列表
export function listCompletion(query) {
  return request({
    url: '/newproducts/completion/list',
    method: 'get',
    params: query
  })
}

// 查询新产品生产完成详细
export function getCompletion(id) {
  return request({
    url: '/newproducts/completion/' + id,
    method: 'get'
  })
}

// 新增新产品生产完成
export function addCompletion(data) {
  return request({
    url: '/newproducts/completion',
    method: 'post',
    data: data
  })
}

// 修改新产品生产完成
export function updateCompletion(data) {
  return request({
    url: '/newproducts/completion',
    method: 'put',
    data: data
  })
}

// 删除新产品生产完成
export function delCompletion(id) {
  return request({
    url: '/newproducts/completion/' + id,
    method: 'delete'
  })
}

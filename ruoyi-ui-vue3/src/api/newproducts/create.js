import request from '@/utils/request'

// 查询新产品信息列表
export function listCreate(query) {
  return request({
    url: '/newproducts/create/list',
    method: 'get',
    params: query
  })
}

// 查询新产品信息详细
export function getCreate(id) {
  return request({
    url: '/newproducts/create/' + id,
    method: 'get'
  })
}

// 新增新产品信息
export function addCreate(data) {
  return request({
    url: '/newproducts/create',
    method: 'post',
    data: data
  })
}

// 修改新产品信息
export function updateCreate(data) {
  return request({
    url: '/newproducts/create',
    method: 'put',
    data: data
  })
}

// 删除新产品信息
export function delCreate(id) {
  return request({
    url: '/newproducts/create/' + id,
    method: 'delete'
  })
}

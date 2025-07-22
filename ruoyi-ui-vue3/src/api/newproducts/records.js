import request from '@/utils/request'

// 查询新产品生产问题记录列表
export function listRecords(query) {
  return request({
    url: '/newproducts/records/list',
    method: 'get',
    params: query
  })
}

// 查询新产品生产问题记录详细
export function getRecords(id) {
  return request({
    url: '/newproducts/records/' + id,
    method: 'get'
  })
}

// 新增新产品生产问题记录
export function addRecords(data) {
  return request({
    url: '/newproducts/records',
    method: 'post',
    data: data
  })
}

// 修改新产品生产问题记录
export function updateRecords(data) {
  return request({
    url: '/newproducts/records',
    method: 'put',
    data: data
  })
}

// 删除新产品生产问题记录
export function delRecords(id) {
  return request({
    url: '/newproducts/records/' + id,
    method: 'delete'
  })
}

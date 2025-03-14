import request from '@/utils/request'

// 查询新产品生产计划列表
export function listPlan(query) {
  return request({
    url: '/newproducts/plan/list',
    method: 'get',
    params: query
  })
}

// 查询新产品生产计划详细
export function getPlan(id) {
  return request({
    url: '/newproducts/plan/' + id,
    method: 'get'
  })
}

// 新增新产品生产计划
export function addPlan(data) {
  return request({
    url: '/newproducts/plan',
    method: 'post',
    data: data
  })
}

// 修改新产品生产计划
export function updatePlan(data) {
  return request({
    url: '/newproducts/plan',
    method: 'put',
    data: data
  })
}

// 删除新产品生产计划
export function delPlan(id) {
  return request({
    url: '/newproducts/plan/' + id,
    method: 'delete'
  })
}

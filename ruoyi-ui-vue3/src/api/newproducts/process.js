import request from '@/utils/request'

// 查询新产品试制过程列表
export function listProcess(query) {
  return request({
    url: '/newproducts/process/list',
    method: 'get',
    params: query
  })
}

// 查询新产品试制过程详细
export function getProcess(id) {
  return request({
    url: '/newproducts/process/' + id,
    method: 'get'
  })
}

// 新增新产品试制过程
export function addProcess(data) {
  return request({
    url: '/newproducts/process',
    method: 'post',
    data: data
  })
}

// 修改新产品试制过程
export function updateProcess(data) {
  return request({
    url: '/newproducts/process',
    method: 'put',
    data: data
  })
}

// 删除新产品试制过程
export function delProcess(id) {
  return request({
    url: '/newproducts/process/' + id,
    method: 'delete'
  })
}

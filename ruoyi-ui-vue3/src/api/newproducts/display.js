import request from '@/utils/request'

// 查询新产品项目文件展示列表
export function listDisplay(query) {
  return request({
    url: '/newproducts/display/list',
    method: 'get',
    params: query
  })
}

// 查询新产品项目文件展示详细
export function getDisplay(id) {
  return request({
    url: '/newproducts/display/' + id,
    method: 'get'
  })
}

// 新增新产品项目文件展示
export function addDisplay(data) {
  return request({
    url: '/newproducts/display',
    method: 'post',
    data: data
  })
}

// 修改新产品项目文件展示
export function updateDisplay(data) {
  return request({
    url: '/newproducts/display',
    method: 'put',
    data: data
  })
}

// 删除新产品项目文件展示
export function delDisplay(id) {
  return request({
    url: '/newproducts/display/' + id,
    method: 'delete'
  })
}

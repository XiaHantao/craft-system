import request from '@/utils/request'

// 查询新产品BOM列表
export function listBom(query) {
  return request({
    url: '/newproducts/bom/list',
    method: 'get',
    params: query
  })
}

// 查询新产品BOM详细
export function getBom(id) {
  return request({
    url: '/newproducts/bom/' + id,
    method: 'get'
  })
}

// 新增新产品BOM
export function addBom(data) {
  return request({
    url: '/newproducts/bom',
    method: 'post',
    data: data
  })
}

// 修改新产品BOM
export function updateBom(data) {
  return request({
    url: '/newproducts/bom',
    method: 'put',
    data: data
  })
}

// 删除新产品BOM
export function delBom(id) {
  return request({
    url: '/newproducts/bom/' + id,
    method: 'delete'
  })
}
// 导入新产品BOM
export function importBom(data) {
  return request({
    url: '/newproducts/bom/importData',
    method: 'post',
    data: data,
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    timeout: 60000 // 60秒超时
  })
}

// 检查项目数据是否存在
export function checkProjectDataExists(params) {
  return request({
    url: '/newproducts/bom/checkProjectDataExists',
    method: 'get',
    params: params
  })
}
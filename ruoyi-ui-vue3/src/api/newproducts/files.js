import request from '@/utils/request'

// 查询新产品文件提交列表
export function listFiles(query) {
  return request({
    url: '/newproducts/files/list',
    method: 'get',
    params: query
  })
}

// 查询新产品文件提交详细
export function getFiles(id) {
  return request({
    url: '/newproducts/files/' + id,
    method: 'get'
  })
}

// 新增新产品文件提交
export function addFiles(data) {
  return request({
    url: '/newproducts/files',
    method: 'post',
    data: data
  })
}

// 修改新产品文件提交
export function updateFiles(data) {
  return request({
    url: '/newproducts/files',
    method: 'put',
    data: data
  })
}

// 删除新产品文件提交
export function delFiles(id) {
  return request({
    url: '/newproducts/files/' + id,
    method: 'delete'
  })
}

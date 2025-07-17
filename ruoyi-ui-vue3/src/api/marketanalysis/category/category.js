import request from '@/utils/request'

// 查询车类列表
export function listCategory(query) {
  return request({
    url: '/marketanalysis/category/list',
    method: 'get',
    params: query
  })
}

// 查询车类详细
export function getCategory(id) {
  return request({
    url: '/marketanalysis/category/' + id,
    method: 'get'
  })
}

// 新增车类
export function addCategory(data) {
  return request({
    url: '/marketanalysis/category',
    method: 'post',
    data: data
  })
}

// 修改车类
export function updateCategory(data) {
  return request({
    url: '/marketanalysis/category',
    method: 'put',
    data: data
  })
}

// 删除车类
export function delCategory(id) {
  return request({
    url: '/marketanalysis/category/' + id,
    method: 'delete'
  })
}
// 新增导入方法
export function importCategory(data) {
  return request({
    url: '/marketanalysis/category/importData',
    method: 'post',
    data: data,
    timeout: 60000,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

// 新增数据检查方法
export function checkDataExists() {
  return request({
    url: '/marketanalysis/category/checkDataExists',
    method: 'get'
  })
}
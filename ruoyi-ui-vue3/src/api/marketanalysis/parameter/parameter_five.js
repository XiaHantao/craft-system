import request from '@/utils/request'

// 查询五类车参数列表
export function listParameter_five(query) {
  return request({
    url: '/marketanalysis/parameter_five/list',
    method: 'get',
    params: query
  })
}

// 查询五类车参数详细
export function getParameter_five(id) {
  return request({
    url: '/marketanalysis/parameter_five/' + id,
    method: 'get'
  })
}

// 新增五类车参数
export function addParameter_five(data) {
  return request({
    url: '/marketanalysis/parameter_five',
    method: 'post',
    data: data
  })
}

// 修改五类车参数
export function updateParameter_five(data) {
  return request({
    url: '/marketanalysis/parameter_five',
    method: 'put',
    data: data
  })
}

// 删除五类车参数
export function delParameter_five(id) {
  return request({
    url: '/marketanalysis/parameter_five/' + id,
    method: 'delete'
  })
}
// 添加导入方法
export function importParameter_five(data) {
  return request({
    url: '/marketanalysis/parameter_five/importData',
    method: 'post',
    data: data,
    timeout: 60000,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

// 添加数据存在检查
export function checkDataExistsForFive() {
  return request({
    url: '/marketanalysis/parameter_five/checkDataExists',
    method: 'get'
  })
}
import request from '@/utils/request'

// 查询配置图片表列表
export function listImage(query) {
  return request({
    url: '/marketanalysis/image/list',
    method: 'get',
    params: query
  })
}

// 查询配置图片表详细
export function getImage(id) {
  return request({
    url: '/marketanalysis/image/' + id,
    method: 'get'
  })
}

// 新增配置图片表
export function addImage(data) {
  return request({
    url: '/marketanalysis/image',
    method: 'post',
    data: data
  })
}

// 修改配置图片表
export function updateImage(data) {
  return request({
    url: '/marketanalysis/image',
    method: 'put',
    data: data
  })
}

// 删除配置图片表
export function delImage(id) {
  return request({
    url: '/marketanalysis/image/' + id,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询多媒体文件列表
export function listMedia(query) {
  return request({
    url: '/marketanalysis/media/list',
    method: 'get',
    params: query
  })
}

// 查询多媒体文件详细
export function getMedia(id) {
  return request({
    url: '/marketanalysis/media/' + id,
    method: 'get'
  })
}

// 新增多媒体文件
export function addMedia(data) {
  return request({
    url: '/marketanalysis/media',
    method: 'post',
    data: data
  })
}

// 修改多媒体文件
export function updateMedia(data) {
  return request({
    url: '/marketanalysis/media',
    method: 'put',
    data: data
  })
}

// 删除多媒体文件
export function delMedia(id) {
  return request({
    url: '/marketanalysis/media/' + id,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询工艺地图列表
export function listProcessMap(query) {
  return request({
    url: '/process/processMap/list',
    method: 'get',
    params: query
  })
}

// 查询工艺地图详细
export function getProcessMap(id) {
  return request({
    url: '/process/processMap/' + id,
    method: 'get'
  })
}

// 新增工艺地图
export function addProcessMap(data) {
  return request({
    url: '/process/processMap',
    method: 'post',
    data: data
  })
}

// 修改工艺地图
export function updateProcessMap(data) {
  return request({
    url: '/process/processMap',
    method: 'put',
    data: data
  })
}

// 删除工艺地图
export function delProcessMap(id) {
  return request({
    url: '/process/processMap/' + id,
    method: 'delete'
  })
}

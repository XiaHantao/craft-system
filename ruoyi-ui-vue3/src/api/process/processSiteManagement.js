import request from '@/utils/request'

// 查询工艺现场管理列表
export function listProcessSiteManagement(query) {
  return request({
    url: '/process/processSiteManagement/list',
    method: 'get',
    params: query
  })
}

// 查询工艺现场管理详细
export function getProcessSiteManagement(id) {
  return request({
    url: '/process/processSiteManagement/' + id,
    method: 'get'
  })
}

// 新增工艺现场管理
export function addProcessSiteManagement(data) {
  return request({
    url: '/process/processSiteManagement',
    method: 'post',
    data: data
  })
}

// 修改工艺现场管理
export function updateProcessSiteManagement(data) {
  return request({
    url: '/process/processSiteManagement',
    method: 'put',
    data: data
  })
}

// 删除工艺现场管理
export function delProcessSiteManagement(id) {
  return request({
    url: '/process/processSiteManagement/' + id,
    method: 'delete'
  })
}

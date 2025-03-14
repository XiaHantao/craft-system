import request from '@/utils/request'

// 查询维修记录列表
export function listMaintenanceRecord(query) {
  return request({
    url: '/ToolingModule/maintenanceRecord/list',
    method: 'get',
    params: query
  })
}

// 新查询维修记录列表
export function listMaintenancenewRecord(query , toolNumber) {
  return request({
    url: '/ToolingModule/maintenanceRecord/listnew',
    method: 'get',
    params: {query , toolNumber}
  })
}

// 查询维修记录详细
export function getMaintenanceRecord(id) {
  return request({
    url: '/ToolingModule/maintenanceRecord/' + id,
    method: 'get'
  })
}

// 新增维修记录
export function addMaintenanceRecord(data) {
  return request({
    url: '/ToolingModule/maintenanceRecord',
    method: 'post',
    data: data
  })
}

// 修改维修记录
export function updateMaintenanceRecord(data) {
  return request({
    url: '/ToolingModule/maintenanceRecord',
    method: 'put',
    data: data
  })
}

// 删除维修记录
export function delMaintenanceRecord(id) {
  return request({
    url: '/ToolingModule/maintenanceRecord/' + id,
    method: 'delete'
  })
}

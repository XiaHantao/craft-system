import request from '@/utils/request'

// 查询工艺定额值列表
export function listProcessQuotaValue(query) {
  return request({
    url: '/process/processQuotaValue/list',
    method: 'get',
    params: query
  })
}

// 查询工艺定额值详细
export function getProcessQuotaValue(id) {
  return request({
    url: '/process/processQuotaValue/' + id,
    method: 'get'
  })
}

// 新增工艺定额值
export function addProcessQuotaValue(data) {
  return request({
    url: '/process/processQuotaValue',
    method: 'post',
    data: data
  })
}

// 修改工艺定额值
export function updateProcessQuotaValue(data) {
  return request({
    url: '/process/processQuotaValue',
    method: 'put',
    data: data
  })
}

// 删除工艺定额值
export function delProcessQuotaValue(vehicleModels) {
  return request({
    url: '/process/processQuotaValue/' + vehicleModels,
    method: 'delete'
  })
}

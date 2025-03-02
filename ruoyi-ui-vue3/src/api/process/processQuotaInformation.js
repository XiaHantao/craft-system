import request from '@/utils/request'

// 查询工艺定额字典列表
export function listProcessQuotaInformation(query) {
  return request({
    url: '/process/processQuotaInformation/list',
    method: 'get',
    params: query
  })
}

// 查询工艺定额字典详细
export function getProcessQuotaInformation(id) {
  return request({
    url: '/process/processQuotaInformation/' + id,
    method: 'get'
  })
}

// 新增工艺定额字典
export function addProcessQuotaInformation(data) {
  return request({
    url: '/process/processQuotaInformation',
    method: 'post',
    data: data
  })
}

// 修改工艺定额字典
export function updateProcessQuotaInformation(data) {
  return request({
    url: '/process/processQuotaInformation',
    method: 'put',
    data: data
  })
}

// 删除工艺定额字典
export function delProcessQuotaInformation(id) {
  return request({
    url: '/process/processQuotaInformation/' + id,
    method: 'delete'
  })
}

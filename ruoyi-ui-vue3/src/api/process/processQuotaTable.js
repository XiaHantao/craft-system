import request from '@/utils/request'

// 查询工艺定额列表
export function listProcessQuotaTable(query) {
  return request({
    url: '/process/processQuotaTable/list',
    method: 'get',
    params: query
  })
}

// 查询当前车型关联的工艺定额列表
export function listRelatedProcessQuotaTable(vehicleModel) {
  return request({
    url: '/process/processQuotaTable/related/',
    method: 'get',
    params: { vehicleModel }
  })
}

// 查询工艺定额详细
export function getProcessQuotaTable(id) {
  return request({
    url: '/process/processQuotaTable/' + id,
    method: 'get'
  })
}

// 新增工艺定额
export function addProcessQuotaTable(data) {
  return request({
    url: '/process/processQuotaTable',
    method: 'post',
    data: data
  })
}

// 修改工艺定额
export function updateProcessQuotaTable(data) {
  return request({
    url: '/process/processQuotaTable',
    method: 'put',
    data: data
  })
}

// 删除工艺定额
export function delProcessQuotaTable(vehicleModels) {
  return request({
    url: '/process/processQuotaTable/delete',
    method: 'delete',
    params: {
      vehicleModels: vehicleModels.join(',')
    }
  })
}

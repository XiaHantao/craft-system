import request from '@/utils/request'

// 查询车型分类列表
export function listVehicletype(query) {
  return request({
    url: '/marketanalysis/vehicletype/list',
    method: 'get',
    params: query
  })
}

// 查询车型分类详细
export function getVehicletype(id) {
  return request({
    url: '/marketanalysis/vehicletype/' + id,
    method: 'get'
  })
}

// 新增车型分类
export function addVehicletype(data) {
  return request({
    url: '/marketanalysis/vehicletype',
    method: 'post',
    data: data
  })
}

// 修改车型分类
export function updateVehicletype(data) {
  return request({
    url: '/marketanalysis/vehicletype',
    method: 'put',
    data: data
  })
}

// 删除车型分类
export function delVehicletype(id) {
  return request({
    url: '/marketanalysis/vehicletype/' + id,
    method: 'delete'
  })
}
// 新增导入方法
export function importCostprice(data) {
  return request({
    url: '/marketanalysis/costprice/importData',
    method: 'post',
    data: data,
    timeout: 60000,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

// 新增检查数据存在方法
export function checkCostpriceDataExists() {
  return request({
    url: '/marketanalysis/costprice/checkDataExists',
    method: 'get'
  })
}
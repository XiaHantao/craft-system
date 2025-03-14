import request from '@/utils/request'

// 查询成本售价表列表
export function listCostprice(query) {
  return request({
    url: '/marketanalysis/costprice/list',
    method: 'get',
    params: query
  })
}

// 查询成本售价表详细
export function getCostprice(id) {
  return request({
    url: '/marketanalysis/costprice/' + id,
    method: 'get'
  })
}

// 新增成本售价表
export function addCostprice(data) {
  return request({
    url: '/marketanalysis/costprice',
    method: 'post',
    data: data
  })
}

// 修改成本售价表
export function updateCostprice(data) {
  return request({
    url: '/marketanalysis/costprice',
    method: 'put',
    data: data
  })
}

// 删除成本售价表
export function delCostprice(id) {
  return request({
    url: '/marketanalysis/costprice/' + id,
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
  

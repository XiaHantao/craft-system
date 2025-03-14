import request from '@/utils/request'

// 查询销售分析列表
export function listSaleanalysis(query) {
  return request({
    url: '/marketanalysis/saleanalysis/list',
    method: 'get',
    params: query
  })
}

// 查询销售分析详细
export function getSaleanalysis(branch) {
  return request({
    url: '/marketanalysis/saleanalysis/' + branch,
    method: 'get'
  })
}

// 新增销售分析
export function addSaleanalysis(data) {
  return request({
    url: '/marketanalysis/saleanalysis',
    method: 'post',
    data: data
  })
}

// 修改销售分析
export function updateSaleanalysis(data) {
  return request({
    url: '/marketanalysis/saleanalysis',
    method: 'put',
    data: data
  })
}

// 删除销售分析
export function delSaleanalysis(id) {
  return request({
    url: '/marketanalysis/saleanalysis/' + id,
    method: 'delete'
  })
}
export function importSaleanalysis(data) {
  return request({
    url: '/marketanalysis/saleanalysis/importData',
    method: 'post',
    data: data,
    timeout: 60000, // 超时时间设置为60秒
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}
// 检查数据是否存在
export function checkDataExists() {
  return request({
    url: '/marketanalysis/saleanalysis/checkDataExists',
    method: 'get'
  })
}
// 查询所有车型销量统计
export function countByVehicle(query) {
  return request({
    url: '/marketanalysis/saleanalysis/countByVehicle',
    method: 'get',
    params: query
  })
}
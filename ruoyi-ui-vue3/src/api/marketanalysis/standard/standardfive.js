import request from '@/utils/request'

// 查询五类/七类车标准配置列表
export function listStandardfive(query) {
  return request({
    url: '/marketanalysis/standardfive/list',
    method: 'get',
    params: query
  })
}

// 查询五类/七类车标准配置详细
export function getStandardfive(pneumaticTire) {
  return request({
    url: '/marketanalysis/standardfive/' + pneumaticTire,
    method: 'get'
  })
}

// 新增五类/七类车标准配置
export function addStandardfive(data) {
  return request({
    url: '/marketanalysis/standardfive',
    method: 'post',
    data: data
  })
}

// 修改五类/七类车标准配置
export function updateStandardfive(data) {
  return request({
    url: '/marketanalysis/standardfive',
    method: 'put',
    data: data
  })
}

// 删除五类/七类车标准配置
export function delStandardfive(pneumaticTire) {
  return request({
    url: '/marketanalysis/standardfive/' + pneumaticTire,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询二类车标准配置列表
export function listStandardtwo(query) {
  return request({
    url: '/marketanalysis/standardtwo/list',
    method: 'get',
    params: query
  })
}

// 查询二类车标准配置详细
export function getStandardtwo(polyurethaneTires) {
  return request({
    url: '/marketanalysis/standardtwo/' + polyurethaneTires,
    method: 'get'
  })
}

// 新增二类车标准配置
export function addStandardtwo(data) {
  return request({
    url: '/marketanalysis/standardtwo',
    method: 'post',
    data: data
  })
}

// 修改二类车标准配置
export function updateStandardtwo(data) {
  return request({
    url: '/marketanalysis/standardtwo',
    method: 'put',
    data: data
  })
}

// 删除二类车标准配置
export function delStandardtwo(polyurethaneTires) {
  return request({
    url: '/marketanalysis/standardtwo/' + polyurethaneTires,
    method: 'delete'
  })
}

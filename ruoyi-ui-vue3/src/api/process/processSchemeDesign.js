import request from '@/utils/request'

// 查询工艺方案设计列表
export function listProcessSchemeDesign(query) {
  return request({
    url: '/process/processSchemeDesign/list',
    method: 'get',
    params: query
  })
}

// 查询工艺方案设计详细
export function getProcessSchemeDesign(id) {
  return request({
    url: '/process/processSchemeDesign/' + id,
    method: 'get'
  })
}

// 新增工艺方案设计
export function addProcessSchemeDesign(data) {
  return request({
    url: '/process/processSchemeDesign',
    method: 'post',
    data: data
  })
}

// 修改工艺方案设计
export function updateProcessSchemeDesign(data) {
  return request({
    url: '/process/processSchemeDesign',
    method: 'put',
    data: data
  })
}

// 删除工艺方案设计
export function delProcessSchemeDesign(id) {
  return request({
    url: '/process/processSchemeDesign/' + id,
    method: 'delete'
  })
}

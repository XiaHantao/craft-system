import request from '@/utils/request'

// 查询专用规程列表
export function listProcessSpecificationDesign(query) {
  return request({
    url: '/process/processSpecificationDesign/list',
    method: 'get',
    params: query
  })
}

// 查询通用规程列表
export function listGeneral(query) {
  return request({
    url: '/process/processSpecificationDesign/generallist',
    method: 'get',
    params: query
  })
}

// 查询工艺规程设计详细
export function getProcessSpecificationDesign(id) {
  return request({
    url: '/process/processSpecificationDesign/' + id,
    method: 'get'
  })
}

// 新增工艺规程设计
export function addProcessSpecificationDesign(data) {
  return request({
    url: '/process/processSpecificationDesign',
    method: 'post',
    data: data
  })
}

// 修改工艺规程设计
export function updateProcessSpecificationDesign(data) {
  return request({
    url: '/process/processSpecificationDesign',
    method: 'put',
    data: data
  })
}

// 删除工艺规程设计
export function delProcessSpecificationDesign(id) {
  return request({
    url: '/process/processSpecificationDesign/' + id,
    method: 'delete'
  })
}

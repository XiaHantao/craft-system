import request from '@/utils/request'

// 查询工艺调研列表
export function listProcessResearchTable(query) {
  return request({
    url: '/process/processResearchTable/list',
    method: 'get',
    params: query
  })
}

// 查询工艺调研详细
export function getProcessResearchTable(id) {
  return request({
    url: '/process/processResearchTable/' + id,
    method: 'get'
  })
}

// 新增工艺调研
export function addProcessResearchTable(data) {
  return request({
    url: '/process/processResearchTable',
    method: 'post',
    data: data
  })
}

// 修改工艺调研
export function updateProcessResearchTable(data) {
  return request({
    url: '/process/processResearchTable',
    method: 'put',
    data: data
  })
}

// 删除工艺调研
export function delProcessResearchTable(id) {
  return request({
    url: '/process/processResearchTable/' + id,
    method: 'delete'
  })
}

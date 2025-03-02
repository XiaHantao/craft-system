import request from '@/utils/request'

// 查询工艺性审查列表
export function listProcessReviewTable(query) {
  return request({
    url: '/process/processReviewTable/list',
    method: 'get',
    params: query
  })
}

// 查询工艺性审查详细
export function getProcessReviewTable(id) {
  return request({
    url: '/process/processReviewTable/' + id,
    method: 'get'
  })
}

// 新增工艺性审查
export function addProcessReviewTable(data) {
  return request({
    url: '/process/processReviewTable',
    method: 'post',
    data: data
  })
}

// 修改工艺性审查
export function updateProcessReviewTable(data) {
  return request({
    url: '/process/processReviewTable',
    method: 'put',
    data: data
  })
}

// 删除工艺性审查
export function delProcessReviewTable(id) {
  return request({
    url: '/process/processReviewTable/' + id,
    method: 'delete'
  })
}

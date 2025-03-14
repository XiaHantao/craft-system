import request from '@/utils/request'

// 查询自制件台账列表
export function listSelfMadePartsLedger(query) {
  return request({
    url: '/ToolingModule/SelfMadePartsLedger/list',
    method: 'get',
    params: query
  })
}

// 查询自制件台账详细
export function getSelfMadePartsLedger(id) {
  return request({
    url: '/ToolingModule/SelfMadePartsLedger/' + id,
    method: 'get'
  })
}

// 新增自制件台账
export function addSelfMadePartsLedger(data) {
  return request({
    url: '/ToolingModule/SelfMadePartsLedger',
    method: 'post',
    data: data
  })
}

// 修改自制件台账
export function updateSelfMadePartsLedger(data) {
  return request({
    url: '/ToolingModule/SelfMadePartsLedger',
    method: 'put',
    data: data
  })
}

// 删除自制件台账
export function delSelfMadePartsLedger(id) {
  return request({
    url: '/ToolingModule/SelfMadePartsLedger/' + id,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询消息通知列表
export function listSysMessageNotification(query) {
  return request({
    url: '/system/sysMessageNotification/list',
    method: 'get',
    params: query
  })
}

// 查询消息通知详细
export function getSysMessageNotification(noticeId) {
  return request({
    url: '/system/sysMessageNotification/' + noticeId,
    method: 'get'
  })
}

// 新增消息通知
export function addSysMessageNotification(data) {
  return request({
    url: '/system/sysMessageNotification',
    method: 'post',
    data: data
  })
}

// 修改消息通知
export function updateSysMessageNotification(data) {
  return request({
    url: '/system/sysMessageNotification',
    method: 'put',
    data: data
  })
}

// 删除消息通知
export function delSysMessageNotification(noticeId) {
  return request({
    url: '/system/sysMessageNotification/' + noticeId,
    method: 'delete'
  })
}

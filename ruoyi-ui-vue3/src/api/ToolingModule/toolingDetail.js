import request from '@/utils/request'

// 查询工装详细列表
export function listToolingDetail(query) {
  return request({
    url: '/ToolingModule/toolingDetail/list',
    method: 'get',
    params: query
  })
}

// 查询公用工装详细列表
export function listshareToolingDetail(query) {
  return request({
    url: '/ToolingModule/toolingDetail/sharelist',
    method: 'get',
    params: query
  })
}

// 新查询工装详细列表
export function listToolingnewDetail(query , toolNumber) {
  return request({
    url: '/ToolingModule/toolingDetail/listNew',
    method: 'get',
    params: {query , toolNumber}
  })
}

// 到期工装预警列表
export function upcomingChangeTime(query) {
  return request({
    url: '/ToolingModule/toolingDetail/upcomingChangeTime',
    method: 'get',
    params: query
  })
}

// 查询工装详细详细
export function getToolingDetail(id) {
  return request({
    url: '/ToolingModule/toolingDetail/' + id,
    method: 'get'
  })
}

// 新增工装详细
export function addToolingDetail(data) {
  return request({
    url: '/ToolingModule/toolingDetail',
    method: 'post',
    data: data
  })
}

// 修改工装详细
export function updateToolingDetail(data) {
  return request({
    url: '/ToolingModule/toolingDetail',
    method: 'put',
    data: data
  })
}

// 删除工装详细
export function delToolingDetail(id) {
  return request({
    url: '/ToolingModule/toolingDetail/' + id,
    method: 'delete'
  })
}

// 上传文件的函数
export function uploadFile(file) {
  const formData = new FormData();
  formData.append("file", file);

  return request({
    url: '/ToolingModule/toolingDetail/import',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
}

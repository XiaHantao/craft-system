import request from '@/utils/request'

// 查询外部工装台账列表
export function listNonStructuralTooling(query) {
  return request({
    url: '/ToolingModule/NonStructuralTooling/list',
    method: 'get',
    params: query
  })
}

// 查询金工班工装台账列表
export function listNonStructuralTooling1(query) {
  return request({
    url: '/ToolingModule/NonStructuralTooling/list1',
    method: 'get',
    params: query
  })
}

// 查询金工班工装台账列表
export function listNonStructuralTooling2(query) {
  return request({
    url: '/ToolingModule/NonStructuralTooling/list2',
    method: 'get',
    params: query
  })
}

// 补充非结构工装工艺文件及物料清单
export function updateNonStructuralfile(data) {
  return request({
    url: '/ToolingModule/NonStructuralTooling/fileupdate',
    method: 'put',
    data: data
  })
}
// 查询非结构工装台账详细
export function getNonStructuralTooling(id) {
  return request({
    url: '/ToolingModule/NonStructuralTooling/' + id,
    method: 'get'
  })
}

// 新增非结构工装台账
export function addNonStructuralTooling(data) {
  return request({
    url: '/ToolingModule/NonStructuralTooling',
    method: 'post',
    data: data
  })
}

// 修改非结构工装台账
export function updateNonStructuralTooling(data) {
  return request({
    url: '/ToolingModule/NonStructuralTooling',
    method: 'put',
    data: data
  })
}

// 删除非结构工装台账
export function delNonStructuralTooling(id) {
  return request({
    url: '/ToolingModule/NonStructuralTooling/' + id,
    method: 'delete'
  })
}

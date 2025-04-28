import request from '@/utils/request'

// 查询工装台账列表
export function listWorkClothes(query) {
  return request({
    url: '/ToolingModule/WorkClothes/list',
    method: 'get',
    params: query
  })
}

// 查询工装台账详细
export function getWorkClothes(id) {
  return request({
    url: '/ToolingModule/WorkClothes/' + id,
    method: 'get'
  })
}

// 补充工艺文件及物料清单
export function updateWorkClothesfile(data) {
  return request({
    url: '/ToolingModule/WorkClothes/fileupdate',
    method: 'put',
    data: data
  })
}

// 新增工装台账
export function addWorkClothes(data) {
  return request({
    url: '/ToolingModule/WorkClothes',
    method: 'post',
    data: data
  })
}

// 修改工装台账
export function updateWorkClothes(data) {
  return request({
    url: '/ToolingModule/WorkClothes',
    method: 'put',
    data: data
  })
}

// 删除工装台账
export function delWorkClothes(id) {
  return request({
    url: '/ToolingModule/WorkClothes/' + id,
    method: 'delete'
  })
}

// 上传文件的函数
export function uploadFile(file) {
  const formData = new FormData();
  formData.append("file", file);

  return request({
    url: '/ToolingModule/WorkClothes/import',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
}


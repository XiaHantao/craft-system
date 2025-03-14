import request from '@/utils/request'

// 查询新产品提交列表
export function listSubmit(query) {
  return request({
    url: '/newproducts/submit/list',
    method: 'get',
    params: query
  })
}

// 查询新产品提交详细
export function getSubmit(id) {
  return request({
    url: '/newproducts/submit/' + id,
    method: 'get'
  })
}

// 新增新产品提交
export function addSubmit(data) {
  return request({
    url: '/newproducts/submit',
    method: 'post',
    data: data
  })
}

// 修改新产品提交
export function updateSubmit(data) {
  return request({
    url: '/newproducts/submit',
    method: 'put',
    data: data
  })
}

// 删除新产品提交
export function delSubmit(id) {
  return request({
    url: '/newproducts/submit/' + id,
    method: 'delete'
  })
}

// 删除服务器上传文件
export function deleteFile(path) {
  return request({
    url: '/newproducts/submit/file',
    method: 'delete',
    params: {
      path: path
    }
  });
}
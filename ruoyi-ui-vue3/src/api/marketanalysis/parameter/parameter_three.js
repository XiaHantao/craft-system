import request from '@/utils/request'

// 查询三类车参数列表
export function listParameter_three(query) {
  return request({
    url: '/marketanalysis/parameter_three/list',
    method: 'get',
    params: query
  })
}

// 查询三类车参数详细
export function getParameter_three(id) {
  return request({
    url: '/marketanalysis/parameter_three/' + id,
    method: 'get'
  })
}

// 新增三类车参数
export function addParameter_three(data) {
  return request({
    url: '/marketanalysis/parameter_three',
    method: 'post',
    data: data
  })
}

// 修改三类车参数
export function updateParameter_three(data) {
  return request({
    url: '/marketanalysis/parameter_three',
    method: 'put',
    data: data
  })
}

// 删除三类车参数
export function delParameter_three(id) {
  return request({
    url: '/marketanalysis/parameter_three/' + id,
    method: 'delete'
  })
}
// 导入二类车参数
export function importParameter_three(data) {
  return request({
    url: '/marketanalysis/parameter_three/importData',
    method: 'post',
    data: data,
    timeout: 60000, // 超时时间设置为60秒
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

// 检查数据是否存在
export function checkDataExists() {
  return request({
    url: '/marketanalysis/parameter_three/checkDataExists',
    method: 'get'
  })
}
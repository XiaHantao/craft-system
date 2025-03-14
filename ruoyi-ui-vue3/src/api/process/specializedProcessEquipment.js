import request from '@/utils/request'

// 查询专用工艺装备清单列表
export function listSpecializedProcessEquipment(query) {
  return request({
    url: '/process/specializedProcessEquipment/list',
    method: 'get',
    params: query
  })
}

// 查询专用工艺装备清单详细
export function getSpecializedProcessEquipment(id) {
  return request({
    url: '/process/specializedProcessEquipment/' + id,
    method: 'get'
  })
}

// 新增专用工艺装备清单
export function addSpecializedProcessEquipment(data) {
  return request({
    url: '/process/specializedProcessEquipment',
    method: 'post',
    data: data
  })
}

// 修改专用工艺装备清单
export function updateSpecializedProcessEquipment(data) {
  return request({
    url: '/process/specializedProcessEquipment',
    method: 'put',
    data: data
  })
}

// 删除专用工艺装备清单
export function delSpecializedProcessEquipment(id) {
  return request({
    url: '/process/specializedProcessEquipment/' + id,
    method: 'delete'
  })
}

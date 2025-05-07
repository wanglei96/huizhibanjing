import request from '@/request/index'

// 获取所有菜单列表
export const getChinaMap = () => {
  return request.get('/data/china.json')
}

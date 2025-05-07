import request from '@/request/index'
import common from '@/js/common'

//获取所有菜单列表
/* export const getAllMenu = () => {
return request.get('/data/menu.json')
} */

export const getAllMenu = () => {
  return new Promise((resolve, reject) => {
    let data = {
      sysUserId: common.getLocalStorage('sysUserId'),
      menuTypeCode: 'Platform',
    };
    common.call('GetPlatformMenuList', data, (res) => {
      resolve(res.data)
    });
  });
}
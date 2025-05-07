import request from '@/request/index'
import common from '@/js/common'

//获取所有菜单列表
/* export const getAllMenu = () => {
return request.get('/data/menu.json')
} */

export const getAllMenu = () => {
  return new Promise((resolve, reject) => {
    let data = {
      companyUserId: common.getLocalStorage('companyUserId') || 1,
      menuTypeCode: 'Company',
    };
    common.call('GetCompanyMenuList', data, (res) => {
      resolve(res.data)
    });
  });
} 
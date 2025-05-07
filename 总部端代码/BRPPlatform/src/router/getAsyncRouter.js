// 匹配view里面所有的.vue文件
const modules = import.meta.glob('./../views/**/*.vue')

// 遍历后台传来的路由字符串，转换为组件对象
export function filterAsyncRouter(asyncRouterMap) {
  // filter() 创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素
  return asyncRouterMap.filter((route) => {
    if (route.component) {
      route.component = loadView(route.component)
    }
    if (route.children != null && route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children)
    } else {
      delete route['children']
      delete route['redirect']
    }
    return true
  })
}

export const loadView = (view) => {
  let res
  for (const path in modules) {
    const dir = path.split('views')[1].split('layout')[1]
    if (dir === view) {
      res = () => modules[path]()
    }
  }
  return res
}

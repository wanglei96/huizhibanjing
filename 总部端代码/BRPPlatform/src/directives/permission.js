//permission.js
import { useUserStore } from '@/stores/user'
const store = useUserStore()

function hasPermission(value, el = false) {
  //如果自定义指令设置的不是数组，则抛出异常
  if (!Array.isArray(value)) {
    throw new Error(`需要配置权限，例如 v-permission="['getStatistics3,GET']" `)
  }
  //根据传过来的value，在state中的ruleNames中是否包含有这个value
  const hasAuth = value.findIndex((e) => store.getAuthorityList.includes(e)) != -1

  //如果找不到对应的数据，则移除这个el节点
  if (el && !hasAuth) {
    el.parentNode && el.parentNode.removeChild(el)
  }
  return hasAuth
}

export default {
  //如果app.use()中的插件是一个对象，必须提供 install 方法。如果插件是一个函数，它会被作为 install 方法。
  //install 方法调用时，会将 app 作为参数传入,此时就可以在别的文件中使用app.directive()了
  install(app) {
    app.directive('permission', {
      //指令的钩子会传递2个参数
      //el:指令绑定到的元素,这可以用于直接操作 DOM
      //binding.value：传递给指令的值,例如在 v-permission="['getStatistics3,GET']" 中，值是 ['getStatistics3,GET']
      mounted(el, binding) {
        hasPermission(binding.value, el)
      }
    })
  }
}

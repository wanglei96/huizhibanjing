export default {
  //如果app.use()中的插件是一个对象，必须提供 install 方法。如果插件是一个函数，它会被作为 install 方法。
  //install 方法调用时，会将 app 作为参数传入,此时就可以在别的文件中使用app.directive()了
  install(app) {
    app.directive('ellipsis', (el, binding) => {
      el.style.width = binding.value.width + 'px'
      el.style.display = '-webkit-box'
      el.style.webkitBoxOrient = 'vertical'
      el.style.webkitLineClamp = binding.value.line
      el.style.overflow = 'hidden'
    })
  }
}

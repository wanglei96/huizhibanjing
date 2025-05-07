export default {
  //如果app.use()中的插件是一个对象，必须提供 install 方法。如果插件是一个函数，它会被作为 install 方法。
  //install 方法调用时，会将 app 作为参数传入,此时就可以在别的文件中使用app.directive()了
  install(app) {
    app.directive('watermark', (el, binding) => {
      let text = binding.value.text
      let font = binding.value.font || '16px Microsoft JhengHei'
      let textColor = binding.value.textColor || 'rgba(215, 215, 215, 0.2)'
      let width = binding.value.width || 400
      let height = binding.value.height || 200
      let textRotate = binding.value.textRotate || -20

      function addWaterMarker(parentNode) {
        var can = document.createElement('canvas')
        parentNode.appendChild(can)
        can.width = width
        can.height = height
        can.style.display = 'none'
        var cans = can.getContext('2d')
        cans.rotate((textRotate * Math.PI) / 180)
        cans.font = font
        cans.fillStyle = textColor
        cans.textAlign = 'left'
        cans.textBaseline = 'Middle'
        cans.fillText(text, 0, can.height)
        parentNode.style.backgroundImage = 'url(' + can.toDataURL('image/png') + ')'
      }
      addWaterMarker(el)
    })
  }
}

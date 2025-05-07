import { createApp } from 'vue'
import pinia from '@/stores'

import App from './App.vue'
import router from './router'

import '@/assets/css/base.scss' // 全局样式
import * as ElementPlusIconsVue from '@element-plus/icons-vue' // 导入 element-plus 图标库

// 国际化
import i18n from './lang/index'

// 权限指令,用于权限控制
import permission from '@/directives/permission' //引入permission.js
// 水印指令
import watermark from '@/directives/watermark' //引入watermark.js
// 截取文本显示省略号
import ellipsis from '@/directives/ellipsis' //引入ellipsis.js
//引入百度编辑器
import VueUeditorWrap from 'vue-ueditor-wrap'
// 颜色选择器插件
import vcolorpicker from 'vcolorpicker'
// 引入百度地图组件
import BaiduMap from 'vue-baidu-map-3x'
import dayjs from "dayjs"
// 中文包
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

 
 



const app = createApp(App)

//全局组件挂载
app.component('VueUeditorWrap', VueUeditorWrap)

// 注册 element-plus 图标库
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.use(pinia)
app.use(router)
app.use(i18n)
app.use(permission)
app.use(watermark)
app.use(ellipsis)
app.use(VueUeditorWrap)
app.use(vcolorpicker)
app.use(BaiduMap, {
  ak: '7p9amiDTD3BLrHHE4nGTyqL27RLuAVog'
})
app.use(dayjs);
app.use(ElementPlus, { locale: zhCn })

// 定义一个全局复制指令
app.directive('copy', {
  mounted(el, binding) {
    el.addEventListener('click', () => {
      const input = document.createElement('input');
      input.value = binding.value; // 获取绑定的值
      document.body.appendChild(input);
      input.select();
      document.execCommand('copy');
      document.body.removeChild(input);
      // 这里可以添加复制成功的提示
	  ElMessage({ message: "复制成功", type: 'success' }) 
    });
  }
});

app.mount('#app')
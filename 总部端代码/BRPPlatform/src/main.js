import { createApp } from 'vue'
import pinia from '@/stores'

import App from './App.vue'
import router from './router'

import '@/assets/css/base.scss' // 全局样式

import '@/css/common.css' // 全局样式

import * as ElementPlusIconsVue from '@element-plus/icons-vue' // 导入 element-plus 图标库

// 国际化
import i18n from './lang/index'

// 权限指令,用于权限控制
import permission from '@/directives/permission' //引入permission.js
// 水印指令
import watermark from '@/directives/watermark' //引入watermark.js
// 截取文本显示省略号
import ellipsis from '@/directives/ellipsis' //引入ellipsis.js
// ueditor-plus
import VueUeditorWrap from 'vue-ueditor-wrap';


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

app.mount('#app')

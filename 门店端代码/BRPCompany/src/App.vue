<script setup>
import { useLangStore } from '@/stores/lang'
import { provide, ref, nextTick } from 'vue'

const langStore = useLangStore()
// Element-plus 国际化设置
import { ElConfigProvider } from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs' // 简体中文
import en from 'element-plus/dist/locale/en.mjs' // 英文
import zhTw from 'element-plus/dist/locale/zh-tw.mjs' // 繁体中文

let locale = zhCn
if (langStore.getLang === 'zh-CN') {
  locale = zhCn
}
if (langStore.getLang === 'zh-TW') {
  locale = zhTw
}
if (langStore.getLang === 'en-US') {
  locale = en
}

const isRouterAlive = ref(true)
const reload = () => {
  isRouterAlive.value = false
  nextTick(() => {
    isRouterAlive.value = true
  })
}
provide('reload', reload)
</script>

<template>
  <router-view v-if="isRouterAlive" />
</template>

<style lang="scss">
/* elementui 主题颜色 */
/* :root {
  color-scheme: light;
  --el-color-white: #ffffff;
  --el-color-black: #000000;
  --el-color-primary: #409eff;
  --el-color-primary-light-3: #79bbff;
  --el-color-primary-light-5: #a0cfff;
  --el-color-primary-light-7: #c6e2ff;
  --el-color-primary-light-8: #d9ecff;
  --el-color-primary-light-9: #ecf5ff;
  --el-color-primary-dark-2: #337ecc;
  --el-color-success: #67c23a;
  --el-color-success-light-3: #95d475;
  --el-color-success-light-5: #b3e19d;
  --el-color-success-light-7: #d1edc4;
  --el-color-success-light-8: #e1f3d8;
  --el-color-success-light-9: #f0f9eb;
  --el-color-success-dark-2: #529b2e;
  --el-color-warning: #e6a23c;
  --el-color-warning-light-3: #eebe77;
  --el-color-warning-light-5: #f3d19e;
  --el-color-warning-light-7: #f8e3c5;
  --el-color-warning-light-8: #faecd8;
  --el-color-warning-light-9: #fdf6ec;
  --el-color-warning-dark-2: #b88230;
  --el-color-danger: #f56c6c;
  --el-color-danger-light-3: #f89898;
  --el-color-danger-light-5: #fab6b6;
  --el-color-danger-light-7: #fcd3d3;
  --el-color-danger-light-8: #fde2e2;
  --el-color-danger-light-9: #fef0f0;
  --el-color-danger-dark-2: #c45656;
  --el-color-error: #f56c6c;
  --el-color-error-light-3: #f89898;
  --el-color-error-light-5: #fab6b6;
  --el-color-error-light-7: #fcd3d3;
  --el-color-error-light-8: #fde2e2;
  --el-color-error-light-9: #fef0f0;
  --el-color-error-dark-2: #c45656;
  --el-color-info: #909399;
  --el-color-info-light-3: #b1b3b8;
  --el-color-info-light-5: #c8c9cc;
  --el-color-info-light-7: #dedfe0;
  --el-color-info-light-8: #e9e9eb;
  --el-color-info-light-9: #f4f4f5;
  --el-color-info-dark-2: #73767a;
  --el-bg-color: #ffffff;
  --el-bg-color-page: #f2f3f5;
  --el-bg-color-overlay: #ffffff;
  --el-text-color-primary: #303133;
  --el-text-color-regular: #606266;
  --el-text-color-secondary: #909399;
  --el-text-color-placeholder: #a8abb2;
  --el-text-color-disabled: #c0c4cc;
  --el-border-color: #dcdfe6;
  --el-border-color-light: #e4e7ed;
  --el-border-color-lighter: #ebeef5;
  --el-border-color-extra-light: #f2f6fc;
  --el-border-color-dark: #d4d7de;
  --el-border-color-darker: #cdd0d6;
  --el-fill-color: #f0f2f5;
  --el-fill-color-light: #f5f7fa;
  --el-fill-color-lighter: #fafafa;
  --el-fill-color-extra-light: #fafcff;
  --el-fill-color-dark: #ebedf0;
  --el-fill-color-darker: #e6e8eb;
  --el-fill-color-blank: #ffffff;
  --el-box-shadow: 0px 12px 32px 4px rgba(0, 0, 0, 0.04), 0px 8px 20px rgba(0, 0, 0, 0.08);
  --el-box-shadow-light: 0px 0px 12px rgba(0, 0, 0, 0.12);
  --el-box-shadow-lighter: 0px 0px 6px rgba(0, 0, 0, 0.12);
  --el-box-shadow-dark: 0px 16px 48px 16px rgba(0, 0, 0, 0.08), 0px 12px 32px rgba(0, 0, 0, 0.12),
    0px 8px 16px -8px rgba(0, 0, 0, 0.16);
} */

/* 解决el-dropdown获取到焦点或点击出现黑边情况 */
.el-dropdown-link:focus {
  outline: none;
}

/* ************* 路由切换动画 ********* */
/* *********************************** */

/* **************  滑动消退  ********* */
/* **************  滑动消退  ********* */
/* **************  滑动消退  ********* */

.fade-enter-from {
  /* 进入时的透明度为0 和 刚开始进入时的原始位置通过active透明度渐渐变为1 */
  opacity: 0;
  transform: translateX(-3%);
}

.fade-enter-to {
  /*定义进入完成后的位置 和 透明度 */
  transform: translateX(0%);
  opacity: 1;
}

.fade-leave-active,
.fade-enter-active {
  transition: all 0.3s ease-out;
}

.fade-leave-from {
  /* 页面离开时一开始的css样式,离开后为leave-to，经过active渐渐透明 */
  transform: translateX(0%);
  opacity: 1;
}

.fade-leave-to {
  /* 这个是离开后的透明度通过下面的active阶段渐渐变为0 */
  transform: translateX(3%);
  opacity: 0;
}

/* **************  底部消退  ************ */
/* **************  底部消退  ************ */
/* **************  底部消退  ************ */

.fadebottom-enter-from {
  /* 进入时的透明度为0 和 刚开始进入时的原始位置通过active透明度渐渐变为1 */
  opacity: 0;
  transform: translateY(-5%);
}

.fadebottom-enter-to {
  /*定义进入完成后的位置 和 透明度 */
  transform: translateY(0%);
  opacity: 1;
}

.fadebottom-leave-active,
.fadebottom-enter-active {
  transition: all 0.3s ease-out;
}

.fadebottom-leave-from {
  /* 页面离开时一开始的css样式,离开后为leave-to，经过active渐渐透明 */
  transform: translateY(0%);
  opacity: 1;
}

.fadebottom-leave-to {
  /* 这个是离开后的透明度通过下面的active阶段渐渐变为0 */
  transform: translateY(5%);
  opacity: 0;
}

/* **************  放大渐变  ************ */
/* **************  放大渐变  ************ */
/* **************  放大渐变  ************ */

.fadezoomin-enter-from {
  /* 进入时的透明度为0 和 刚开始进入时的原始位置通过active透明度渐渐变为1 */
  opacity: 0;
  transform: scale(1.2);
}

.fadezoomin-enter-to {
  /*定义进入完成后的位置 和 透明度 */
  transform: translateY(0%);
  opacity: 1;
}

.fadezoomin-leave-active,
.fadezoomin-enter-active {
  transition: all 0.3s ease-out;
}

.fadezoomin-leave-from {
  /* 页面离开时一开始的css样式,离开后为leave-to，经过active渐渐透明 */
  transform: translateY(0%);
  opacity: 1;
}

.fadezoomin-leave-to {
  /* 这个是离开后的透明度通过下面的active阶段渐渐变为0 */
  transform: scale(1.2);
  opacity: 0;
}

/* **************  缩小渐变  ************ */
/* **************  缩小渐变  ************ */
/* **************  缩小渐变  ************ */
.fadezoomout-enter-from {
  /* 进入时的透明度为0 和 刚开始进入时的原始位置通过active透明度渐渐变为1 */
  opacity: 0;
  transform: scale(0.8);
}

.fadezoomout-enter-to {
  /*定义进入完成后的位置 和 透明度 */
  transform: translateY(0%);
  opacity: 1;
}

.fadezoomout-leave-active,
.fadezoomout-enter-active {
  transition: all 0.3s ease-out;
}

.fadezoomout-leave-from {
  /* 页面离开时一开始的css样式,离开后为leave-to，经过active渐渐透明 */
  transform: translateY(0%);
  opacity: 1;
}

.fadezoomout-leave-to {
  /* 这个是离开后的透明度通过下面的active阶段渐渐变为0 */
  transform: scale(0.8);
  opacity: 0;
}

/* **************  淡入淡出  ************ */
/* **************  淡入淡出  ************ */
/* **************  淡入淡出  ************ */

.fadeopacity-enter-from {
  /* 进入时的透明度为0 和 刚开始进入时的原始位置通过active透明度渐渐变为1 */
  opacity: 0;
  /* transform: scale(0.8); */
}

.fadeopacity-enter-to {
  /*定义进入完成后的位置 和 透明度 */
  /* transform: translateY(0%); */
  opacity: 1;
}

.fadeopacity-leave-active,
.fadeopacity-enter-active {
  transition: all 0.3s ease-out;
}

.fadeopacity-leave-from {
  /* 页面离开时一开始的css样式,离开后为leave-to，经过active渐渐透明 */
  /* transform: translateY(0%); */
  opacity: 1;
}

.fadeopacity-leave-to {
  /* 这个是离开后的透明度通过下面的active阶段渐渐变为0 */
  /* transform: scale(0.8); */
  opacity: 0;
}

/* 交换element-ui弹出框的确定和取消按钮位置。确定在前，取消在后 */
.el-message-box__btns {
  padding: 5px 15px 0;
  display: flex;
  flex-direction: row-reverse !important;
  justify-content: normal !important;
  gap: 10px;
}

/*设置富文本编辑器层级*/
.edui-editor.edui-default {
  z-index: 1 !important;
}

/*设置富文本编辑器宽度100%*/
.edui-default {
  .edui-editor {
    width: 100% !important;
    box-sizing: border-box;
  }

  .edui-editor > div {
    width: 100% !important;
    box-sizing: border-box;
  }
}
</style>
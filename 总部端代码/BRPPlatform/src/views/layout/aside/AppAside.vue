<script setup>
import { isCollapse } from '../configCenter'
import LeftMenuItem from './LeftMenuItem.vue'
import { getAllMenu } from '@/api/menu'

import { useGlobalConfigStore } from '@/stores/globalConfig'
import config from "@/js/config";
const store = useGlobalConfigStore()

import { useRoute } from 'vue-router'
const route = useRoute()

// console.log(store.aside)

// 菜单背景颜色
const menuStyle = reactive({
  txtColor: '#ffffff',
  hoverTxtColor: '#ffffff',
  bgColor: '#001529',
  hoverBgColor: '#1a252f',
  activeColor: '#ffd04b',
  activeBgColor: '#247bb4'
})

const menuStyle0 = store.getConfig.aside || menuStyle

const txtColor = menuStyle0.txtColor
const hoverTxtColor = menuStyle0.hoverTxtColor
const bgColor = menuStyle0.bgColor
const hoverBgColor = menuStyle0.hoverBgColor
const activeColor = menuStyle0.activeColor
const activeBgColor = menuStyle0.activeBgColor

// 菜单文字颜色
// const txtColor = ref('#ffffff')

// 左侧菜单数组
let items = ref([])

// 获取菜单列表
async function getMenu() {
  const res = await getAllMenu()
  items.value = res
  // console.log(res)
}
getMenu()

const onRoutes = computed(() => {
  if (route.meta.activeMenu) {
    return route.meta.activeMenu
  }
  var path = route.path;
  return route.path
})
</script>
<template>
  <!-- :style="{ 'background-color': bgColor }" -->
  <el-aside>
    <el-scrollbar>
      <!-- :text-color="txtColor" active-text-color="#ffd04b" :background-color="bgColor" -->
      <el-menu router unique-opened :collapse="isCollapse" :default-active="onRoutes">
        <a href="/" class="logo">
          <img src="@/assets/images/logo.png" style="height: 25px" alt="" />
          <div style="text-align: center;">
            <h1 :style="{ color: menuStyle0.txtColor }" style="font-size: 15px;">（总部）慧至半径童书馆</h1>
            <h1 :style="{ color: menuStyle0.txtColor }" style="font-size: 15px;">管理系统</h1>
          </div>

        </a>
        <LeftMenuItem v-for="item in items" :key="item.path" :item="item"></LeftMenuItem>
      </el-menu>
    </el-scrollbar>
  </el-aside>
</template>

<style lang="scss">
// 菜单背景颜色
// $menuBgColor: #001529;

.el-aside {
  background-color: v-bind(bgColor);
  height: 100vh;
  width: auto;
}

.el-menu {
  width: 200px;
  // background-color: $menuBgColor;
  border-right: none;

  // el-menu 颜色动态控制
  --el-menu-text-color: v-bind(txtColor) !important;
  --el-menu-hover-text-color: v-bind(hoverTxtColor) !important;
  --el-menu-bg-color: v-bind(bgColor) !important;
  --el-menu-hover-bg-color: v-bind(hoverBgColor) !important;
  --el-menu-active-color: v-bind(activeColor) !important;

  .el-menu-item.is-active {
    background-color: v-bind(activeBgColor) !important;
  }

  &.el-menu--collapse {
    width: 60px;

    & h1 {
      display: none;
    }
  }
}

.logo {
  display: flex;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: black;
  height: 60px;

  img {
    width: 32px;
    height: 32px;
  }
}

//******************************* */</style>

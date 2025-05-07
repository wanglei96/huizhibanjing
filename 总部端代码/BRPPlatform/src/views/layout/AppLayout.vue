<script setup>
import { useRoute } from 'vue-router'
import AppAside from './aside/AppAside.vue'
import AppHeader from './header/AppHeader.vue'

import { useGlobalConfigStore } from '@/stores/globalConfig'
import { useTabsStore } from '@/stores/tabs'

const store = useGlobalConfigStore()
const tabsStore = useTabsStore()

const router = useRouter()
const route = useRoute()

let isRouterAlive = true;
// 关闭当前tabs标签
function handleCloseTab(name) {
  const index = tabsStore.getTabs.findIndex((obj) => obj.name === name)
  const newArr = tabsStore.getTabs.filter((obj) => obj.name !== name)
  tabsStore.copyTabs(newArr)
  if (index - 1 >= 0) {
    router.push(newArr[index - 1].path)
  } else {
    router.push('/')
  }
}
// 关闭当前tabs标签左侧的标签
function handleCloseTabLeft(name) {
  const index = tabsStore.getTabs.findIndex((obj) => obj.name === name)
  const newArr = tabsStore.getTabs.splice(0, index)
  tabsStore.copyTabs(tabsStore.getTabs)
}
// 关闭当前tabs标签右侧的标签
function handleCloseTabRight(name) {
  const arr = tabsStore.getTabs
  const index = arr.findIndex((obj) => obj.name === name)
  let newArr = []
  if (arr.length - index > 0) {
    newArr = arr.splice(index + 1, arr.length - index)
  }
  tabsStore.copyTabs(arr)
}

// 关闭当前tabs标签以外的标签
function handleCloseTabOther(name) {
  const arr = tabsStore.getTabs
  const index = arr.findIndex((obj) => obj.name === name)
  const newArr = arr.filter((obj) => obj.name === name)

  tabsStore.copyTabs(newArr)
}

// 关闭当前tabs标签右侧的标签
function handleCloseTabCloseAll(name) {
  tabsStore.copyTabs([])
  router.push('/')
}

// tab栏最右侧下拉菜单
function handleTab(data) {
  // 刷新当前
  if (data === '01') {
    router.go(0)
  }
  // 关闭当前
  if (data === '02') {
    handleCloseTab(route.meta.title)
  }
  // 关闭左侧
  if (data === '03') {
    handleCloseTabLeft(route.meta.title)
  }
  // 关闭右侧
  if (data === '04') {
    handleCloseTabRight(route.meta.title)
  }
  // 关闭其他
  if (data === '05') {
    handleCloseTabOther(route.meta.title)
  }
  // 关闭全部
  if (data === '06') {
    handleCloseTabCloseAll(route.meta.title)
  }
}

const isComponent = ref(true);
const reloadComponent = () => {
  isComponent.value = false;
  nextTick(() => {
    isComponent.value = true;
  });
};
provide("reloadComponent", reloadComponent);

/* window.addEventListener('beforeunload', function (event) {
  localStorage.clear();
}); */
const scrollbar = ref(null);

function mainPanelScrolled(e) {
  sessionStorage.setItem('scrollTop', e.scrollTop)
  sessionStorage.setItem('scrollLeft', e.scrollLeft)
}

provide('setScrollbarPosition', setScrollbarPosition);
function setScrollbarPosition() {
  const scrollTop = sessionStorage.getItem('scrollTop');
  const scrollLeft = sessionStorage.getItem('scrollLeft');
  console.log(scrollTop);
  if (scrollTop && scrollLeft && scrollbar.value) {
    scrollbar.value.scrollTo({
      top: parseInt(scrollTop),
      left: parseInt(scrollLeft),
      behavior: 'auto'
    });
  }
};
</script>

<template>
  <div class="common-layout">
    <el-container>
      <AppAside />
      <el-container class="header-and-main">
        <AppHeader />
        <el-main>
          <!-- Tab选项卡 -->
          <div class="tabs">
            <div class="tabs-home">
              <router-link to="/" class="linkTabs">
                <el-icon>
                  <House />
                </el-icon>
              </router-link>
            </div>
            <div class="tabs content">
              <router-link v-for="item in tabsStore.getTabs" :key="item.tabs" class="linkTabs"
                :to="{ path: item.path, query: item.query }" active-class="active">
                <el-text>{{ $t(item.name) }}</el-text>
                <div class="close_bg">
                  <el-icon @click.prevent="handleCloseTab(item.name)">
                    <Close />
                  </el-icon>
                </div>
              </router-link>
              <!-- <el-tabs class="demo-tabs" closable @tab-click="show">
                <el-tab-pane
                  v-for="item in tabsStore.getTabs"
                  :key="item.url"
                  :label="item.name"
                  :name="item.url"
                >
                </el-tab-pane>
              </el-tabs> -->
            </div>
            <div class="tabs-tools">
              <el-dropdown>
                <span class="el-dropdown-link">
                  <el-icon class="el-icon--right">
                    <arrow-down />
                  </el-icon>
                </span>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item @click="handleTab('01')">
                      <el-icon>
                        <RefreshLeft />
                      </el-icon>
                      <el-text>{{ $t('刷新当前') }}</el-text>
                    </el-dropdown-item>
                    <el-dropdown-item @click="handleTab('02')">
                      <el-icon>
                        <Close />
                      </el-icon><el-text>{{ $t('关闭当前') }}</el-text>
                    </el-dropdown-item>
                    <el-dropdown-item @click="handleTab('03')">
                      <el-icon>
                        <Back />
                      </el-icon><el-text>{{ $t('关闭左侧') }}</el-text>
                    </el-dropdown-item>
                    <el-dropdown-item @click="handleTab('04')">
                      <el-icon>
                        <Right />
                      </el-icon><el-text>{{ $t('关闭右侧') }}</el-text>
                    </el-dropdown-item>
                    <el-dropdown-item @click="handleTab('05')">
                      <el-icon>
                        <Remove />
                      </el-icon><el-text>{{ $t('关闭其它') }}</el-text>
                    </el-dropdown-item>
                    <el-dropdown-item @click="handleTab('06')">
                      <el-icon>
                        <CircleClose />
                      </el-icon><el-text>{{ $t('关闭全部') }}</el-text>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
            </div>
          </div>
          <!-- 路由容器 -->
          <el-scrollbar @scroll="mainPanelScrolled" ref="scrollbar">
            <div class="container">
              <!-- <RouterView /> -->
              <!-- <RouterView v-slot="{ Component }" v-if="isRouterAlive">
                <transition :name="store.getConfig.animation || 'fade'" mode="out-in">
                  <component :is="Component" :key="key" />
                </transition>
              </RouterView> -->
              <RouterView v-slot="{ Component }" v-if="isComponent">
                <transition :name="store.getConfig.animation || 'fade'" mode="out-in">
                  <KeepAlive>
                    <component :is="Component" />
                  </KeepAlive>
                </transition>
              </RouterView>
            </div>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style lang="scss" scoped>
.el-main {
  background-color: #f0f2f5;
  overflow: hidden;
}

.header-and-main {
  flex-direction: column;
  height: 100vh;
}

.container {
  padding: 15px;
  overflow-x: hidden;
  padding-bottom: 55px;
}

.el-main {
  padding: 0;
  overflow-x: hidden;
}

.el-scrollbar__wrap {
  overflow-x: hidden;
}

.tabs {
  width: 100%;
  height: 40px;
  background-color: #ffffff;
  box-shadow: 0 1px 4px -2px rgba(0, 21, 41, 0.08);
  display: flex;
  flex-direction: row;
  align-items: center;

  .tabs-home {
    width: 12px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-left: 20px;
    margin-right: 20px;
  }

  .tabs content {
    flex: 1;
  }

  .tabs-tools {
    width: 70px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .active {
    padding-left: 20px;
    padding-right: 20px;
    text-decoration: none;
    color: #333;
    height: 38px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-bottom: 2px solid #1677ff;
    font-size: 14px;
    background-color: #e8f1ff;
  }

  .linkTabs {
    padding-left: 10px;
    padding-right: 10px;
    text-decoration: none;
    color: #333;
    height: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 14px;
    line-height: 40px;

    .el-text {
      margin-right: 3px;
    }
  }

  .close_bg {
    width: 14px;
    height: 14px;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .close_bg:hover {
    background-color: #ee6666;
    color: #ffffff;
  }
}
</style>

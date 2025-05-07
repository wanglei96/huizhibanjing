<script setup>
// 引入 Pinia
import { useTabsStore } from '@/stores/tabs'
const store = useTabsStore()
const router = useRouter()
// 定义 Props
const props = defineProps(['item'])
let menuIndex = ref(null);
function saveTab(item) {
  if (store.getTabs.length > 0) {
    if (store.getTabs.findIndex((obj) => obj.name === item.name) === -1) {
      store.saveTabs({
        name: item.name,
        path: item.path,
        query: item.query
      })
    }
  } else {
    store.saveTabs({
      name: item.name,
      path: item.path,
      query: item.query
    })
  }
  router.push({ path: item.path, query: item.query });
}
</script>

<template>
  <el-menu-item v-if="!item.children && item.menuType == 0" :index="item.path" :router="false" @click="saveTab(item)">
    <el-icon>
      <component :is="item.icon"></component>
    </el-icon> <span>{{ $t(item.name) }}</span>
  </el-menu-item>

  <el-sub-menu :index="item.path" v-if="item.children && item.menuType == 0">
    <template #title>
      <el-icon>
        <component :is="item.icon"></component>
      </el-icon> <span>{{ $t(item.name) }}</span>
    </template>
    <LeftMenuItem v-for="sub in item.children" :key="sub.path" :item="sub"></LeftMenuItem>
  </el-sub-menu>
</template>

<style lang="scss" scoped></style>

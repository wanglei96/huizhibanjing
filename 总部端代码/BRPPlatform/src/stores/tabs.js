import { defineStore } from 'pinia'
import { computed } from 'vue'

export const useTabsStore = defineStore('tabsStore', () => {
  let tabs = ref([])

  const getTabs = computed(() => {
    tabs.value = JSON.parse(localStorage.getItem('tabs')) || []
    return tabs.value
  })

  function saveTabs(data) {
    // 系统默认显示10个Tab页
    // TODO 等找到合适的左右滚动条，在替换

    if (tabs.value.length >= 10) {
      tabs.value.shift()
    }

    tabs.value.push(data)
    localStorage.setItem('tabs', JSON.stringify(tabs.value))
  }

  function copyTabs(data) {
    tabs.value = data
    localStorage.setItem('tabs', JSON.stringify(data))
  }

  return { tabs, getTabs, saveTabs, copyTabs }
})

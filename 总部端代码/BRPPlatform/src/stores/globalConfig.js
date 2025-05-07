import { defineStore } from 'pinia'
import { computed } from 'vue'

export const useGlobalConfigStore = defineStore('globalConfig', () => {
  const config = ref({})

  const getConfig = computed(() => {
    if (Object.keys(config.value).length === 0) {
      return JSON.parse(localStorage.getItem('globalConfig')) || {}
    } else {
      return config.value
    }
  })

  function saveConfig(data) {
    config.value = data
    localStorage.setItem('globalConfig', JSON.stringify(data))
  }

  return { config, getConfig, saveConfig }
})

import { defineStore } from 'pinia'
import { computed } from 'vue'
export const useLangStore = defineStore('lang', () => {
  const lang = ref('')
  const getLang = computed(() => {
    return lang.value || localStorage.getItem('lang') || 'zh-CN'
  })

  function setLang(value) {
    lang.value = value
    localStorage.setItem('lang', value)
  }

  return {
    lang,
    getLang,
    setLang
  }
})

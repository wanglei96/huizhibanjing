import pinia from '@/stores'
import { useLangStore } from '../stores/lang'
const store = useLangStore(pinia)

// language文件夹下的index.js
// zh, en, id 分别为三种语言文件
import { createI18n } from 'vue-i18n'
import cn from './cn'
import en from './en'
import tw from './tw'

const i18n = createI18n({
  legacy: false,
  globalInjection: true,
  // locale: localStorage.getItem('language') || 'zh-CN',
  locale: store.getLang,
  messages: {
    'zh-CN': cn,
    'en-US': en,
    'zh-TW': tw
  }
})

export default i18n

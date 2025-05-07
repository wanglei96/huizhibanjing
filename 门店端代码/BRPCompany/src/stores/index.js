// 此文件用于在外部JS文件中调用 pinia 时使用
// 在外部js文件中的调用方式如下：
// import pinia from '@/stores/pinia'
// import { useLangStore } from '../stores/lang'
// const store = useLangStore(pinia)

import { createPinia } from 'pinia'
const pinia = createPinia()
export default pinia

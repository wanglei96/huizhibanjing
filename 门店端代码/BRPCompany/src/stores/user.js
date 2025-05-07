import { defineStore } from 'pinia'
import { computed } from 'vue'

export const useUserStore = defineStore('user', () => {
  const userInfo = ref({})
  // 用户保存用户的按钮相关权限信息
  const authorityList = ref([])

  // 获取权限数组
  const getAuthorityList = computed(() => {
    return authorityList.value
  })

  // 获取用户基本信息
  const getUserInfo = computed(() => {
    return userInfo.value
  })

  // 通过调用接口获取登录用户信息后，把用户的权限列表 authorityList 更新
  function setAuthorityList(data) {
    authorityList.value = data
  }

  function setUserInfo(data) {
    userInfo.value = data
  }

  return {
    authorityList,
    getAuthorityList,
    setAuthorityList,
    userInfo,
    getUserInfo,
    setUserInfo
  }
})

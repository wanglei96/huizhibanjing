import { ElMessage } from 'element-plus'
import { defineStore } from 'pinia'
import { computed } from 'vue'

/* export const useTokenStore = defineStore('token', () => {
  // ref 相当于 states
  const tokenJson = ref('')

  // computed 相当于 getters
  const token = computed(() => {
    try {
      return JSON.parse(tokenJson.value || localStorage.getItem('token') || '{}')
    } catch (error) {
      ElMessage.error('Token转换JSON失败！')
      // 如果转换localStorage中的Token出错，清空localStorage中的Token的值
      localStorage.setItem('token', '')
      throw error
    }
  })

  // function 相当于 actions
  function saveToken(data) {
    tokenJson.value = data
    localStorage.setItem('token', data)
  }
  // 向外暴露
  return { token, saveToken }
})
 */

export const useTokenStore = defineStore('token', () => {
	// ref 相当于 states
	const tokenJson = ref('')

	// computed 相当于 getters
	const token = computed(() => {
		return localStorage.getItem('token') || "";
	})

	// function 相当于 actions
	function saveToken(data) {
		tokenJson.value = data
		localStorage.setItem('token', data)
	}
	// 向外暴露
	return {
		token,
		saveToken
	}
})
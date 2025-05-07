import {
	ElMessage
} from 'element-plus'
import {
	defineStore
} from 'pinia'
import {
	computed
} from 'vue'

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
import request from '@/request/index'
import common from '@/js/common'

// 测试封装接口
export const getJokeList = (num) => request.get('/joke/list', { num })
export const getUser = () => request.get('message.ashx')
export const getAdList = () => request.get('/ad/getAdList')

// 用户请求登录
export const login = (params) => {
  // 本地JSON数据
  return request.get('/data/login.json', params)
  // 接口数据
  // return request.post('/front/user/login', params)
}

// 获取用户信息
export const getUserInfo = () => {
  // 本地JSON数据
  return request.get('/data/userInfo.json')
  // 接口数据
  // return request.get('/front/user/getInfo')
}

// 用户退出
export const getUserLogout = () => {
  return request.post('/front/user/logout')
}

// 刷新Token
// 防止多次刷新token
let promiseRT
let isRefreshing = false // refresh开关
export const refreshToken = (params) => {
  if (isRefreshing) {
    return promiseRT
  } else {
    isRefreshing = true
    promiseRT = request.post('/front/user/refresh_token', params).finally(() => {
      // 重置 refresh开关
      isRefreshing = false
    })
    return promiseRT
  }
}

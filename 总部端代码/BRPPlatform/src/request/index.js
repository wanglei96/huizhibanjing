import axios from 'axios'
import QS from 'qs'
import { Base64 } from 'js-base64'
import router from '@/router/index'

import { useTokenStore } from '@/stores/token'
import { refreshToken } from '../api/userApi'
import { ElMessage } from 'element-plus'

// 设置请求超时时间
axios.defaults.timeout = 10000

// const server = 'https://autumnfish.cn/'
// const server = 'http://192.168.3.77:8086/'
// const server = 'http://localhost:5173/'

const server = ''

let url = ''
// 环境的切换
switch (process.env.NODE_ENV) {
  case 'development':
    // url = server + 'api/'
    url = server
    break
  case 'debug':
    url = 'https://www.baidu.com'
    break
  case 'production':
    url = server
    break
}

const myAxios = axios.create({
  baseURL: url
})

/*
  请求拦截器
*/
myAxios.interceptors.request.use(
  (config) => {
    // 请求白名单
    // const url = config.url
    // const whiteList = global.whiteList /
    // if (whiteList.indexOf(url) === -1 && token) {
    //   config.headers.token = token
    // }

    // access_token
    config.headers.Authorization = useTokenStore().token?.access_token
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

/*
  响应拦截器
*/
myAxios.interceptors.response.use(
  (response) => {
    if (response.status === 200) {
      return Promise.resolve(response)
    } else {
      return Promise.reject(response)
    }
  },
  async (error) => {
    if (error.response.status) {
      switch (error.response.status) {
        case 401: {
          console.log('401')
          // 根据refresh_token重新请求刷新token
          const res = await refreshToken({ refresh_token: useTokenStore().token?.refresh_token })
          if (res.success) {
            // 保存最新的token信息
            useTokenStore().saveToken(res.content)
            // 请求回最新的token后，继续没完成的请求
            return myAxios(error.config)
          } else {
            // 刷新token失败
            ElMessage.error('刷新Token失败，需要重新登录！')
            // 跳转到登录页面
            router.replace({
              path: '/login',
              query: {
                redirect: router.currentRoute.fullPath
              }
            })
          }
          break
        }
        case 403:
          localStorage.removeItem('token')
          setTimeout(() => {
            router.replace({
              path: '/login',
              query: {
                redirect: router.currentRoute.fullPath
              }
            })
          }, 1000)
          break
        case 404:
          console.log('网络请求不存在')
          break
        default:
          console.log(error.response.data.message)
      }
      return Promise.reject(error.response)
    }
  }
)

/* 
  封装 axios 方法
*/
export default {
  // get method
  get: function (url, params) {
    return new Promise((resolve, reject) => {
      // 打开Vant加载提示
      // loading...
      // 发送请求
      myAxios
        .get(url, {
          params
        })
        .then((res) => {
          resolve(res.data)
        })
        .catch((err) => {
          reject(err.data)
        })
    })
  },
  // post method
  post: function (url, params, successCallback, errorCallback) {
    return new Promise((resolve, reject) => {
      myAxios
        .post(url, QS.stringify(params))
        .then((res) => {
          resolve(res.data)
        })
        .catch((err) => {
          reject(err.data)
        })
    })
  }
}

import { createRouter, createWebHistory } from 'vue-router'
import AppLayout from '../views/layout/AppLayout.vue'
import { useTokenStore } from '@/stores/token.js'
import { getAllMenu } from '@/api/menu'
import { filterAsyncRouter } from './getAsyncRouter'

const routes = [
  {
    path: '/',
    name: 'home',
    meta: {
      title: '首页',
      requiresAuth: true // 需要token验证
    },
    component: AppLayout,
    redirect: '/dashboard/total/IndexView',
    children: []
  },
  {
    // 系统登录页
    path: '/login',
    name: 'login',
    component: () => import('../views/login/LoginView.vue')
  },
  {
    // 书箱门店信息页
    path: '/companyView',
    name: 'companyView',
    component: () => import('../views/public/CompanyView.vue')
  },
  {
    // 宣传二维码页
    path: '/companyPromotion',
    name: 'companyPromotion',
    component: () => import('../views/public/CompanyPromotion.vue')
  },
  {
    // 宣传二维码页
    path: '/MemberCardQueryView',
    name: 'MemberCardQueryView',
    component: () => import('../views/layout/member/memberCardQuery/MemberCardQueryView.vue'),
    meta: {
      keepAlive: true
    }
  }
  // {
  //   // 路由错误页面 404 页面
  //   // 路由匹配失败显示的页面
  //   path: '/:XXX(.*)*',
  //   name: 'ErrorPage',
  //   component: () => import('@/views/errorPage/ErrorPage_404.vue')
  // }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

// 加载菜单
async function loadMenu(to, from, next) {
  const res = await getAllMenu()
  window.menu = res

  // 处理异步路由
  const asyncRoutes = filterAsyncRouter(res)
  const newT = router.options.routes[0]
  // 加入到路由表
  asyncRoutes.forEach((r) => {
    newT.children.push(r)
  })

  router.addRoute(newT)

  // 404页面放到最后添加
  router.addRoute({
    path: '/:XXX(.*)*',
    name: 'ErrorPage',
    component: () => import('@/views/errorPage/ErrorPage_404.vue')
  })

  // console.log(router.options.routes[0])

  next({ ...to, replace: true })
}

// 全局路由守卫
router.beforeEach((to, from, next) => {
  if (to.matched.some((item) => item.meta?.requiresAuth)) {
    /* const store = useTokenStore()
    if (!store.token?.access_token) {
      next({ name: 'login', query: { redirect: to.fullPath } })
      return
    } */
    var companyUserId = localStorage.getItem("companyUserId");
    if (!companyUserId) {
      next({ name: 'login', query: { redirect: to.fullPath } })
    }
  }

  if (window.menu && window.menu.length > 0) return next() // 一般情况下  menu 会保存在 vuex 或 pinia 中，

  loadMenu(to, from, next)

  // next(to)
})


export default router

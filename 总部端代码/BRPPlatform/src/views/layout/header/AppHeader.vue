<script setup>
// 左侧菜单折叠和头部折叠按钮切换开关
import { isCollapse } from '../configCenter'
// 导入封装的接口
import { getUserInfo, getUserLogout } from '@/api/userApi'

// 导入Pinia 状态库
import { useTokenStore } from '@/stores/token'
import { useGlobalConfigStore } from '@/stores/globalConfig'
import { useLangStore } from '@/stores/lang'
import { useUserStore } from '@/stores/user'
import common from '@/js/common'

// 导入修改密码对话框组件
import ChangePass from '@/views/layout/system/ChangePassword.vue'
const dialogChangePass = ref()

import { useTabsStore } from '@/stores/tabs'
const store = useTabsStore()

// 路由组件
const router = useRouter()
const route = useRoute()

// 获取用户信息
let userInfo = ref({})
getUserInfo().then((res) => {
  userInfo.value = res.content
  const userStore = useUserStore()
  userStore.setUserInfo(res.content)
  userStore.setAuthorityList(res.content.authorityList)
})

const trueName = ref(
  `${common.getLocalStorage('trueName')}`
);

// 全屏开关
let isFullScreen = ref(false)

// 面包屑数据
const breadList = computed(() => {
  return route.matched
})

// 退出系统
function handleLogout() {
  // 询问
  ElMessageBox.confirm('您确定要退出吗？', '温馨提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(() => {
      // 执行退出
      // 清空token
      const store = useTokenStore()
      localStorage.clear();
      store.saveToken('')
      // 提示
      ElMessage.success('用户已登出！')
      // 跳转
      router.push('/login')

      // getUserLogout().then(() => {
      //   // 清空token
      //   const store = useTokenStore()
      //   store.saveToken('')
      //   // 提示
      //   ElMessage.success('用户已登出！')
      //   // 跳转
      //   router.push('/login')
      // })
    })
    .catch((err) => {
      // ElMessage.info('用户取消退出')
      // return Promise.reject(err)
      ElMessage({
        type: 'info',
        message: err === 'cancel' ? '用户取消退出' : 'Stay in the current route'
      })
    })
}

// 全屏
function fullScreen() {
  document.documentElement.webkitRequestFullscreen()
  isFullScreen.value = true
}
// 取消全屏
function cancelFullScreen() {
  document.webkitExitFullscreen()
  isFullScreen.value = false
}

// 右侧抽屉弹框控制开关
const drawerVisible = ref(false)

// 当前侧边栏样式
// 侧栏导航样式1
const menuStyle = {
  id: '01',
  txtColor: '#ffffff',
  hoverTxtColor: '#ffffff',
  bgColor: '#001529',
  hoverBgColor: '#1a252f',
  activeColor: '#ffd04b',
  activeBgColor: '#247bb4'
}
// 侧栏导航样式2
const menuStyle2 = {
  id: '02',
  txtColor: '#ffffff',
  hoverTxtColor: '#ffffff',
  bgColor: '#2a84c2',
  hoverBgColor: '#1a252f',
  activeColor: '#ffd04b',
  activeBgColor: '#247bb4'
}
// 侧栏导航样式3
const menuStyle3 = {
  id: '03',
  txtColor: '#333333',
  hoverTxtColor: '#333333',
  bgColor: '#ffffff',
  hoverBgColor: '#efefef',
  activeColor: '#ffffff',
  activeBgColor: '#247bb4'
}

// 顶栏样式1
const headerStyle = {
  id: '01',
  txtColor: '#333333',
  bgColor: '#ffffff'
}

// 顶栏样式1
const headerStyle2 = {
  id: '02',
  txtColor: '#ffffff',
  bgColor: '#001529'
}

// 顶栏样式1
const headerStyle3 = {
  id: '03',
  txtColor: '#ffffff',
  bgColor: '#5f80c7'
}

// 读取保存的样式
const configStore = useGlobalConfigStore()
let styleForm = reactive({
  animation: configStore.getConfig.animation,
  aside: configStore.getConfig.aside || menuStyle,
  header: configStore.getConfig.header || headerStyle
})

let obj = {}
let currentCelan = ''
// 保存侧边栏菜单样式
function handleCelan(data) {
  currentCelan = data
  if (data === '01') {
    obj = menuStyle
  }
  if (data === '02') {
    obj = menuStyle2
  }
  if (data === '03') {
    obj = menuStyle3
  }
  styleForm.aside = obj
  configStore.saveConfig(styleForm)
  ElMessage.success('侧栏样式配置成功！')
  router.go(0)
}

// 保存顶栏样式
let obj2 = {}
let currentHeader = ''
function handleHeader(data) {
  currentHeader = data
  if (data === '01') {
    obj2 = headerStyle
  }
  if (data === '02') {
    obj2 = headerStyle2
  }
  if (data === '03') {
    obj2 = headerStyle3
  }
  styleForm.header = obj2
  configStore.saveConfig(styleForm)
  // ElMessage.success('顶栏样式配置成功！')
  // router.go(0)
}

// 路由动画保存样式
function handleChange(value) {
  console.log(value)
  configStore.saveConfig(styleForm)
  ElMessage.success('切换动画配置成功！')
}

function handleReset() {
  const obj = {
    animation: 'fade',
    aside: menuStyle,
    header: headerStyle
  }
  configStore.saveConfig(obj)
  styleForm.animation = 'fade'
  ElMessage.success('所有设置已恢复默认！')
  router.go(0)
}

// 顶部栏消息pop
const activeName = ref('first')

function saveTab(item) {
  if (store.getTabs.length > 0) {
    if (store.getTabs.findIndex((obj) => obj.name === item.name) === -1) {
      store.saveTabs({
        name: item.name,
        path: item.path
      })
    }
  } else {
    store.saveTabs({
      name: item.name,
      path: item.path
    })
  }
  router.push(item.path)
}

// 国际化，设置语言
const langStore = useLangStore()
function setLang(value) {
  langStore.setLang(value)
  ElMessage.success('语言设置成功！')
  router.go(0)
}
</script>

<template>
  <!-- 修改密码组件 -->
  <ChangePass ref="dialogChangePass" />
  <el-header :style="{ backgroundColor: styleForm.header.bgColor, color: styleForm.header.txtColor }">
    <!-- 图标 -->
    <el-icon @click="isCollapse = !isCollapse" style="cursor: pointer">
      <Expand v-show="isCollapse" />
      <Fold v-show="!isCollapse" />
    </el-icon>
    <el-icon style="margin-left: 10px; cursor: pointer" @click="router.go(0)">
      <RefreshRight />
    </el-icon>
    <!-- 面包屑 -->
    <el-breadcrumb separator="/">
      <!-- 最末尾的级别span 不给跳转,其他的router-link可以跳转-->
      <el-breadcrumb-item v-for="(item, index) in breadList" :key="item.path">
        <span v-if="index == breadList.length - 1" :style="{ color: styleForm.header.txtColor }">{{
    $t(item.meta.title)
  }}</span>
        <router-link v-else :to="{ path: item.path, query: item.query }"
          :style="{ color: styleForm.header.txtColor }">{{
    $t(item.meta.title)
  }}</router-link>
      </el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 工具栏 -->
    <div class="tools" :style="{ color: styleForm.header.txtColor }">
      <span v-show="!isFullScreen" @click="fullScreen" style="padding-top: 3px">
        <el-tooltip effect="dark" content="进入全屏" placement="bottom">
          <el-icon>
            <FullScreen />
          </el-icon>
        </el-tooltip>
      </span>
      <span v-show="isFullScreen" @click="cancelFullScreen" style="padding-top: 3px">
        <el-tooltip effect="dark" content="取消全屏" placement="bottom">
          <el-icon size="20">
            <Monitor />
          </el-icon>
        </el-tooltip>
      </span>
      <!-- 国际化 -->
      <!--      <el-dropdown style="margin-right: 8px">
        <span class="el-dropdown-link" :style="{ color: styleForm.header.txtColor }">
          <el-icon size="20">
            <Star />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="setLang('zh-CN')">
              <el-icon v-if="langStore.getLang === 'zh-CN'">
                <Check />
              </el-icon>
              <el-icon v-else></el-icon>
              简体中文
            </el-dropdown-item>
            <el-dropdown-item @click="setLang('zh-TW')">
              <el-icon v-if="langStore.getLang === 'zh-TW'">
                <Check />
              </el-icon>
              <el-icon v-else></el-icon>
              繁体中文
            </el-dropdown-item>
            <el-dropdown-item @click="setLang('en-US')">
              <el-icon v-if="langStore.getLang === 'en-US'">
                <Check />
              </el-icon>
              <el-icon v-else></el-icon>
              English
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
</el-dropdown>
&lt;!&ndash; 站内消息 &ndash;&gt;
<el-popover placement="bottom" :width="320" trigger="click">
  <template #reference>
          <el-badge :value="15">
            <el-icon size="20">
              <Bell />
            </el-icon>
          </el-badge>
        </template>
  &lt;!&ndash; 站内消息内容 &ndash;&gt;
  <el-tabs v-model="activeName" stretch>
    <el-tab-pane label="通知（5）" name="first">
      <div class="msg-body">
        <div class="msg-item">
          <div class="msg-icon">
            <el-icon size="18">
              <Bell />
            </el-icon>
          </div>
          <div class="msg-content">
            <div class="msg-title">总部管理后台 2023年升级公告</div>
            <div class="msg-subtitle">2023-08-05 08:28:18</div>
          </div>
        </div>
        <div class="msg-item">
          <div class="msg-icon">
            <el-icon size="18">
              <Bell />
            </el-icon>
          </div>
          <div class="msg-content">
            <div class="msg-title">升级公告</div>
            <div class="msg-subtitle">2023-08-05 08:28:18</div>
          </div>
        </div>
        <div class="msg-item">
          <div class="msg-icon">
            <el-icon size="18">
              <Bell />
            </el-icon>
          </div>
          <div class="msg-content">
            <div class="msg-title">系统维护通知</div>
            <div class="msg-subtitle">2023-08-05 08:28:18</div>
          </div>
        </div>
        <div class="msg-item">
          <div class="msg-icon">
            <el-icon size="18">
              <Bell />
            </el-icon>
          </div>
          <div class="msg-content">
            <div class="msg-title">关于系统的升级通知</div>
            <div class="msg-subtitle">2023-08-05 08:28:18</div>
          </div>
        </div>
        <div class="msg-item">
          <div class="msg-icon">
            <el-icon size="18">
              <Bell />
            </el-icon>
          </div>
          <div class="msg-content">
            <div class="msg-title">请各部分提交下月工作计划</div>
            <div class="msg-subtitle">2023-08-05 08:28:18</div>
          </div>
        </div>
      </div>
    </el-tab-pane>
    <el-tab-pane label="私信（3）" name="second">
      <div class="msg-body">
        <div class="msg-item">
          <div class="msg-icon">
            <el-icon size="18">
              <Bell />
            </el-icon>
          </div>
          <div class="msg-content">
            <div class="msg-title">Tom 申请加您为好友</div>
            <div class="msg-subtitle">2023-08-05 16:08:28</div>
          </div>
        </div>
        <div class="msg-item">
          <div class="msg-icon">
            <el-icon size="18">
              <Bell />
            </el-icon>
          </div>
          <div class="msg-content">
            <div class="msg-title">今天下班后去吃饭吧</div>
            <div class="msg-subtitle">2023-08-05 08:28:18</div>
          </div>
        </div>
        <div class="msg-item">
          <div class="msg-icon">
            <el-icon size="18">
              <Bell />
            </el-icon>
          </div>
          <div class="msg-content">
            <div class="msg-title">这周末一块加班吧？</div>
            <div class="msg-subtitle">2023-08-05 08:28:18</div>
          </div>
        </div>
      </div>
    </el-tab-pane>
    <el-tab-pane label="待办（2）" name="third">
      <div class="msg-body">
        <div class="msg-item">
          <div class="msg-icon">
            <el-icon size="18">
              <Bell />
            </el-icon>
          </div>
          <div class="msg-content">
            <div class="msg-title">下周阿里云服务器续费</div>
            <div class="msg-subtitle">2023-08-05 08:28:18</div>
          </div>
        </div>
        <div class="msg-item">
          <div class="msg-icon">
            <el-icon size="18">
              <Bell />
            </el-icon>
          </div>
          <div class="msg-content">
            <div class="msg-title">下周三系统开标</div>
            <div class="msg-subtitle">2023-08-05 08:28:18</div>
          </div>
        </div>
      </div>
    </el-tab-pane>
  </el-tabs>
  <div class="bottom-tool">
    <span>
      <el-button text>清除通知</el-button>
    </span>
    <span>
      <el-button text @click="saveTab({ name: '站内消息', path: '/personal/message/notice' })">
        查看更多
      </el-button>
    </span>
  </div>
</el-popover>-->
    </div>

    <!-- 用户信息 -->
    <el-dropdown>
      <span class="el-dropdown-link">
        <el-avatar :size="25" :src="userInfo.portrait" />
        <span style="padding-left: 5px" :style="{ color: styleForm.header.txtColor }">{{
    trueName
  }}</span>
        <el-icon class="el-icon--right">
          <ArrowDown :style="{ color: styleForm.header.txtColor }" />
        </el-icon>
      </span>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item>
            <el-icon>
              <User />
            </el-icon>
            <!-- eleadmin.cn -->
            {{ trueName }}
          </el-dropdown-item>
          <el-dropdown-item @click="dialogChangePass.init(1)">
            <el-icon>
              <Edit />
            </el-icon>{{ $t('修改密码') }}
          </el-dropdown-item>
          <el-dropdown-item @click="handleLogout" divided>
            <el-icon>
              <Close />
            </el-icon>{{ $t('退出系统') }}
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
    <div style="margin-left: 10px; width: 10px; cursor: pointer" @click="drawerVisible = true">
      <el-icon style="transform: rotateZ(90deg)">
        <MoreFilled />
      </el-icon>
    </div>
  </el-header>
  <!-- *************** 右侧抽屉弹框 ******************* -->
  <el-drawer v-model="drawerVisible" size="300px">
    <template #header>
      <h4>{{ $t('整体风格设置') }}</h4>
    </template>
    <div class="right-drawer">
      <el-form :model="styleForm" :inline="true">
        <el-text>{{ $t('侧边栏风格') }}</el-text>
        <el-row>
          <el-col :span="24">
            <div class="celan">
              <div class="celan-item">
                <div class="celan_y" v-if="styleForm.aside.id === '01'">
                  <el-icon style="color: green">
                    <SuccessFilled />
                  </el-icon>
                </div>
                <el-tooltip content="深色侧边栏" placement="top">
                  <img src="@/assets/images/icons/celan_01.png" alt="" @click="handleCelan('01')" />
                </el-tooltip>
              </div>
              <div class="celan-item">
                <div class="celan_y" v-if="styleForm.aside.id === '02'">
                  <el-icon style="color: green">
                    <SuccessFilled />
                  </el-icon>
                </div>
                <el-tooltip content="蓝色侧边栏" placement="top">
                  <img src="@/assets/images/icons/celan_02.png" alt="" @click="handleCelan('02')" />
                </el-tooltip>
              </div>
              <div class="celan-item">
                <div class="celan_y" v-if="styleForm.aside.id === '03'">
                  <el-icon style="color: green">
                    <SuccessFilled />
                  </el-icon>
                </div>
                <el-tooltip content="浅色侧边栏" placement="top">
                  <img src="@/assets/images/icons/celan_03.png" alt="" @click="handleCelan('03')" />
                </el-tooltip>
              </div>
            </div>
          </el-col>
        </el-row>
        <el-text>{{ $t('顶栏风格') }}</el-text>
        <el-row>
          <el-col :span="24">
            <div class="celan">
              <div class="celan-item">
                <div class="celan_y" v-if="styleForm.header.id === '01'">
                  <el-icon style="color: green">
                    <SuccessFilled />
                  </el-icon>
                </div>
                <el-tooltip content="浅色顶栏" placement="top">
                  <img src="@/assets/images/icons/header_01.png" alt="" @click="handleHeader('01')" />
                </el-tooltip>
              </div>
              <div class="celan-item">
                <div class="celan_y" v-if="styleForm.header.id === '02'">
                  <el-icon style="color: green">
                    <SuccessFilled />
                  </el-icon>
                </div>
                <el-tooltip content="深色顶栏" placement="top">
                  <img src="@/assets/images/icons/header_02.png" alt="" @click="handleHeader('02')" />
                </el-tooltip>
              </div>
              <div class="celan-item">
                <div class="celan_y" v-if="styleForm.header.id === '03'">
                  <el-icon style="color: green">
                    <SuccessFilled />
                  </el-icon>
                </div>
                <el-tooltip content="蓝色顶栏" placement="top">
                  <img src="@/assets/images/icons/header_03.png" alt="" @click="handleHeader('03')" />
                </el-tooltip>
              </div>
            </div>
          </el-col>
        </el-row>
        <el-divider />
        <!-- <el-text>导航模式</el-text>
        <el-row>
          <el-col :span="24">
            <div style="display: flex; justify-content: space-around">
              <img src="@/assets/images/icons/dh01.png" alt="" />
              <img src="@/assets/images/icons/dh02.png" alt="" />
              <img src="@/assets/images/icons/dh03.png" alt="" />
            </div>
          </el-col>
        </el-row> -->
        <!-- <el-divider /> -->
        <el-text>{{ $t('切换动画') }}</el-text>
        <el-row>
          <el-col :span="24"><el-form-item :label="$t('页面切换动画')">
              <el-select v-model="styleForm.animation" placeholder="请选择" @change="handleChange">
                <el-option :label="$t('滑动消退')" value="fade" />
                <el-option :label="$t('底部消退')" value="fadebottom" />
                <el-option :label="$t('放大渐变')" value="fadezoomin" />
                <el-option :label="$t('缩小渐变')" value="fadezoomout" />
                <el-option :label="$t('淡入淡出')" value="fadeopacity" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- <el-text>其他配置</el-text>
        <el-row>
          <el-col :span="24">
            <el-form-item label="固定主体区域">
              <el-switch />
            </el-form-item>
          </el-col>
        </el-row> -->
        <el-row>
          <el-col :span="24">
            <el-button type="default" style="width: 100%" @click="handleReset">{{
    $t('恢复默认')
  }}</el-button>
          </el-col>
        </el-row>
      </el-form>
    </div>
  </el-drawer>
</template>

<style lang="scss" scoped>
.el-header {
  height: 56px;
  display: flex;
  align-items: center;
  border-bottom: 1px solid #efefef;
}

.el-dropdown {
  float: right;

  .el-dropdown-link {
    display: flex;
    align-items: center;
  }
}

.tools {
  margin-left: auto;
  margin-right: 50px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}

.tools span {
  margin-left: 8px;
  margin-right: 8px;
}

.el-breadcrumb {
  margin-left: 20px;
}

.el-dialog {
  height: 100px;
}

.right-drawer {
  .el-row {
    padding-top: 15px;
    padding-bottom: 15px;
  }
}

.celan {
  display: flex;
  justify-content: space-around;
}

.celan-item {
  width: 57px;
  height: 42px;
  position: relative;
  cursor: pointer;
}

.celan_y {
  width: 18px;
  height: 18px;
  position: absolute;
  top: 0px;
  right: 0px;
  background-color: #ffffff;
  border-radius: 50%;
}

.bottom-tool {
  height: 50px;
  line-height: 50px;
  display: flex;
  justify-content: space-around;
  text-align: center;
}

.bottom-tool span {
  display: inline-block;
}

.msg-body {
  .msg-item {
    width: 100%;
    height: 50px;
    padding: 15px;
    display: flex;
    align-items: center;
    border-bottom: 1px solid #ddd;

    .msg-icon {
      width: 35px;
      height: 35px;
      border-radius: 50%;
      color: #ffffff;
      display: flex;
      justify-content: center;
      align-items: center;
      background-color: #60b2fc;
      margin-right: 8px;
    }

    .msg-subtitle {
      font-size: 12px;
      color: #999;
      margin-top: 3px;
    }
  }

  .msg-item:hover {
    background-color: #f7f7f7;
  }
}
</style>

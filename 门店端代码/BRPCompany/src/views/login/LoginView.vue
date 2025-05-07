<script setup>
// 导入图标组件
import { User, Lock, Warning, Iphone, Postcard } from '@element-plus/icons-vue'
// 导入API
import { getJokeList, getUser, getAdList, login } from '@/api/userApi'
import SIdentify from '@/components/extension/Sidentify.vue'
// 导入Pinia Token状态库
import { useTokenStore } from '@/stores/token.js'
import { ElMessage } from 'element-plus'
import common from '@/js/common'
import { useTabsStore } from '@/stores/tabs'
//import { useRoute } from 'vue-router';

const tabsStore = useTabsStore()

const tokenStore = useTokenStore()

// 导入路由组件
const router = useRouter()
const route = useRoute();


function getParameterByName(name, url) {
  if (!url) url = window.location.href;
  name = name.replace(/[\[\]]/g, '\\$&');
  var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
    results = regex.exec(url);
  if (!results) return null;
  if (!results[2]) return '';
  return decodeURIComponent(results[2].replace(/\+/g, ' '));
}




// 登录表单验证规则
// 需要在 el-form 标签中使用 :rules="rules"
// 需要在 el-form-item 标签中使用 prop="username"
const rules = reactive({
  username: [
    { required: true, message: '请输入登录账号', trigger: 'blur' },
    { min: 3, max: 12, message: '账号长度为3~12位英文字母', trigger: 'blur' }
  ],
  mobilePhone: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
    { pattern: /^1[3456789]\d{9}$/, message: '手机号格式有误', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入登录密码', trigger: 'blur' },
    { min: 5, max: 18, message: '密码长度为5~18位', trigger: 'blur' }
  ],
  yzm: [
    { required: true, message: '请输入验证码', trigger: 'blur' },
    { min: 4, max: 4, message: '请输入正确的验证码', trigger: 'blur' }
  ]
})

// 定义登录表单响应式数据
const loginForm = reactive({
  // username: 'admin',
  mobilePhone: '',
  password: '',
  yzm: '',
  remeber: true
})

// 登录按钮控制变量，防止重复提交
const isLoading = ref(false)

// 登录按钮处理函数
const loginFormRef = ref()

// 关闭当前tabs标签右侧的标签
function handleCloseTabCloseAll(name) {
  tabsStore.copyTabs([])
  router.push('/')
}


const autoLogin = () => {
  //companyId = getParameterByName('companyId', window.location.href);
  const companyId = route.query['companyId'];
  const guid = route.query['guid'];
  console.log(companyId);
  console.log(guid);
  if (companyId && guid) {
    isLoading.value = true
    userLogin(companyId, guid);
  }

};

autoLogin();

function loginSubmit() {
  loginFormRef.value.validate((valid) => {
    if (valid) {
      //验证验证码是否正确
      if (loginForm.yzm != identifyCode.value) {
        ElMessage({ type: 'error', message: '验证码错误' })
        refreshCode()
        return
      }
      // 登录成功，跳转到管理首页
      // console.log('Submit')
      userLogin()
    } else {
      console.log('Error submit')
      return false
    }
  })
}


// 用户登录处理函数
async function userLogin(companyId, guid) {
  isLoading.value = true
  const data = {
    // LoginName: loginForm.username,
    MobilePhone: loginForm.mobilePhone,
    Password: loginForm.password
  }
  if (companyId && guid) {
    data.companyId = companyId;
    data.guid = guid
  }


  common.call(
    'CompanyUserLogin',
    data,
    function (result) {
      // 登录成功
      ElMessage.success('登录成功！')
      isLoading.value = false
      console.log(result.data.token)
      var accessToken = result.data.token;
      /* var accessToken =
        result.data.token ||
        '{"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiMTAwMDMwMDE5IiwidXNlcl9uYW1lIjoiMTgyMDEyODg3NzEiLCJzY29wZSI6WyJyZWFkIl0sIm9yZ2FuaXphdGlvbiI6IjE4MjAxMjg4NzcxIiwiZXhwIjoxNjc4MTg0MjIxLCJhdXRob3JpdGllcyI6WyJBRE1JTiJdLCJqdGkiOiJOdGF5cFJjYXZhbFVwMG9HdTR6WWhFekZJbTgiLCJjbGllbnRfaWQiOiJ0ZXN0X2NsaWVudCJ9.P8amxkKABG-Ao7pgZcWQQqAF7QN2oUrVHKCW9vXLeUE","token_type":"bearer","refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiMTAwMDMwMDE5IiwidXNlcl9uYW1lIjoiMTgyMDEyODg3NzEiLCJzY29wZSI6WyJyZWFkIl0sIm9yZ2FuaXphdGlvbiI6IjE4MjAxMjg4NzcxIiwiYXRpIjoiTnRheXBSY2F2YWxVcDBvR3U0elloRXpGSW04IiwiZXhwIjoxNjc4Mjg1MDIxLCJhdXRob3JpdGllcyI6WyJBRE1JTiJdLCJqdGkiOiJobXRHb1p5aTdEME5rR0Jrd2xRV3JURGM5NDgiLCJjbGllbnRfaWQiOiJ0ZXN0X2NsaWVudCJ9.QGCFshAFaAp99Q80yawpyfIeMXgPXip_TndLxN9Y_rs","expires_in":4878,"scope":"read","user_id":"100030019","organization":"18201288771","jti":"NtaypRcavalUp0oGu4zYhEzFIm8"}' */
      // 保存Token
      tokenStore.saveToken(accessToken)
      let companyUser = result.data.companyUser
      common.setLocalStorage('loginType', 'Company')
      common.setLocalStorage("token", accessToken);
      common.setLocalStorage('companyId', companyUser.companyId)
      common.setLocalStorage('companyUserId', companyUser.id)
      common.setLocalStorage('companyUserName', companyUser.name)
      common.setLocalStorage('companyUserLoginName', companyUser.loginName)
      common.setLocalStorage('companyNo', companyUser.companyNo)
      common.setLocalStorage('companyName', companyUser.companyName)
      // 路由跳转，跳转到系统首页
      router.push(route.query.redirect || '/')
      handleCloseTabCloseAll()
    },
    function (result) {
      let errorMessage = result.errorMessage || result.error_message;
      ElMessage.error(errorMessage);
      isLoading.value = false
      return false
    }
  )
}

const activeName = ref('01')

function handleChange(data) {
  activeName.value = data
}

function showMsg() {
  ElMessage.error('演示版本不支持此登录方式！')
}

let identifyCode = ref('') //图形验证码
let identifyCodes = ref('123456789abcdefjhjknpqrsduvwxyz') //验证码出现的数字和字母


//组件挂载
onMounted(() => {
  refreshCode();
})

// 生成随机数
const randomNum = (min, max) => {
  max = max + 1
  return Math.floor(Math.random() * (max - min) + min)
}
// 随机生成验证码字符串
const makeCode = (o, l) => {
  identifyCode.value = '';
  for (let i = 0; i < l; i++) {
    identifyCode.value += o.charAt(randomNum(0, o.length - 1));
  }

}
// 更新验证码
const refreshCode = () => {
  identifyCode.value = ''
  makeCode(identifyCodes.value, 4)
}

const sms = ref()
</script>

<template>
  <div class="login-wrapper">
    <el-card class="login-card">
      <div class="el-card-body">
        <div class="login-cover">
          <!-- <h1 class="login-title">ElementAdmin</h1> -->
          <h4 class="login-subtitle">慧至半径童书馆管理系统</h4>
        </div>
        <div class="login-body">
          <div class="login-type" style="margin-bottom: 36px">
            <!-- <el-text size="large" style="font-size: 24px">{{ $t('用户登录') }}</el-text> -->
            <div class="login-type-item">
              <div :class="{ activeclass: activeName === '01' }" @click="handleChange('01')">
                {{ $t('登录') }}
              </div>
              <span :class="{ activebg: activeName === '01' }"></span>
            </div>
            <!-- <div class="login-type-item">
              <div :class="{ activeclass: activeName === '02' }" @click="handleChange('02')">
                {{ $t('短信登录') }}
              </div>
              <span :class="{ activebg: activeName === '02' }"></span>
            </div>
            <div class="login-type-item">
              <div :class="{ activeclass: activeName === '03' }" @click="handleChange('03')">
                {{ $t('扫码登录') }}
              </div>
              <span :class="{ activebg: activeName === '03' }"></span>
            </div> -->
          </div>
          <!--************** 密码登录框 ************-->
          <el-form v-if="activeName === '01'" :model="loginForm" ref="loginFormRef" :rules="rules"
            @submit.prevent="loginSubmit">
            <!--            <el-form-item prop="username">
                          <el-input
                            placeholder="请输入用户名"
                            v-model="loginForm.username"
                            :prefix-icon="User"
                          />
                        </el-form-item>-->
            <el-form-item prop="mobilePhone">
              <el-input placeholder="请输入手机号" v-model="loginForm.mobilePhone" :prefix-icon="Iphone" clearable
                @keydown.enter.prevent=loginSubmit />
            </el-form-item>
            <el-form-item prop="password">
              <el-input type="password" v-model="loginForm.password" placeholder="请输入登录密码" :prefix-icon="Lock"
                show-password @keydown.enter.prevent=loginSubmit />
            </el-form-item>
            <el-form-item prop="yzm">
              <div class="yzm-group">
                <el-input v-model="loginForm.yzm" placeholder="请输入验证码" :prefix-icon="Postcard"
                  @keydown.enter.prevent="loginSubmit" />
                <!-- <img src="@/assets/login/yzm.png" alt="" /> -->
                <!-- 使用验证码组件 -->
                <div class="code" @click="refreshCode">
                  <SIdentify :identifyCode="identifyCode"></SIdentify>
                </div>
              </div>
            </el-form-item>

            <el-form-item v-if="false">
              <el-switch v-model="loginForm.remeber" />
              <el-text style="margin-left: 10px"> {{ $t('记住密码') }}</el-text>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" :loading="isLoading" style="width: 90%; height: 38px; margin: 0 auto"
                @click="loginSubmit">
                {{ $t('登录系统') }}
              </el-button>
            </el-form-item>
            <!-- <el-link type="primary">{{ $t('忘记密码') }}?</el-link> -->
          </el-form>
          <!--************** 手机验证码登录框 ************-->
          <el-form v-if="activeName === '02'">
            <el-form-item prop="username">
              <el-input placeholder="请输入11位手机号码" :prefix-icon="Iphone" />
            </el-form-item>
            <el-form-item prop="username">
              <el-input v-model="sms" placeholder="请输入验证码" :prefix-icon="Postcard">
                <template #append>
                  <el-button text @click="showMsg">获取验证码</el-button>
                </template>
              </el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" :loading="isLoading" @click="showMsg" style="width: 100%; height: 42px">
                {{ $t('登录系统') }}
              </el-button>
            </el-form-item>
          </el-form>
          <!--************** 扫码登录框 ************-->
          <div v-if="activeName === '03'" style="text-align: center">
            <img src="@/assets/login/ewm.png" alt="" style="width: 80%" />
            <div style="color: #999; font-size: 14px">请使用微信扫一扫进行登录</div>
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.login-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  box-sizing: border-box;
  background-image: url(@/assets/login/login-bg.jpg);
  background-repeat: no-repeat;
  background-size: 100% 100%;
}

.login-card {
  width: 770px;
  max-width: 100%;
  overflow: hidden;
}

.el-card-body {
  display: flex;
  padding: 0px;
  height: 350px;
}

.login-cover {
  flex: 1;
  padding: 36px 8px;
  box-sizing: border-box;
  background-color: #1681fd;
  background-image: url(@/assets/login/login-img-2.png);
  background-repeat: no-repeat;
  background-position: bottom;
  background-size: contain;
  text-align: center;
  transform: scale(0.8);
}

.login-title {
  color: #fffffffa;
  font-size: 26px;
  margin: 0 0 6px;
  font-weight: 400;
  font-family: AliPuHui;
  letter-spacing: 1.2px;
}

.login-subtitle {
  color: #fffc;
  font-size: 16px;
  margin: 0;
  font-weight: 400;
  font-family: AliPuHui;
  letter-spacing: 2px;
}

.login-body {
  width: 400px;
  padding: 22px 30px 0;
  box-sizing: border-box;
}

.el-input {
  height: 42px;
  line-height: 42px;
}

.yzm-group {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.el-form-item {
  margin-top: 25px;
  margin-bottom: 25px;
}

.login-type {
  display: flex;
  justify-content: center;
  font-size: 18px;
}

.login-type-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  color: #999;
  cursor: pointer;
}

.login-type-item span {
  display: inline-block;
  width: 20px;
  height: 3px;
  // background-color: #1681fd;
  background-color: #ffffff;
  margin-top: 10px;
  border-radius: 10px;
}

.activeclass {
  color: #1681fd !important;
}

.activebg {
  background-color: #1681fd !important;
}
</style>
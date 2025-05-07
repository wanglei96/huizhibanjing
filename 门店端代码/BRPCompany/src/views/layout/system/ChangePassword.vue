<script setup>
import { ElMessage } from 'element-plus'
import common from '@/js/common'
import { useTokenStore } from '@/stores/token'

// 路由组件
const router = useRouter()

const dialogFormVisible = ref(false)
const formLabelWidth = '80px'

const ruleFormRef = ref()

const dataEntity = reactive({
  oldPassword: '',
  password: '',
  rePassword: ''
})

function checkRePassword(rule, value, callback) {
  if (!(dataEntity.password === dataEntity.rePassword)) {
    return callback(new Error('两次输入的密码不一致！'))
  } else {
    callback()
  }
}

const rules = reactive({
  oldPassword: [
    { required: true, message: '请输入旧密码', trigger: 'blur' },
    { min: 5, max: 18, message: '密码长度为5~18位英文字母', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    { min: 5, max: 18, message: '密码长度为5~18位英文字母', trigger: 'blur' }
  ],
  rePassword: [
    { required: true, message: '请再次输入新密码', trigger: 'blur' },
    { min: 5, max: 18, message: '密码长度为5~18位英文字母', trigger: 'blur' },
    { validator: checkRePassword, trigger: 'blur' }
  ]
})

function init(userId) {
  // 呼出对话框
  dialogFormVisible.value = true
  // userId
  // TODO 根据用户userId,判断用户是否存在
}

function onSubmit() {
  ruleFormRef.value.validate((valid) => {
    if (valid) {
      // TODO
      // 调用修改密码接口
      var data = toRaw(dataEntity)
      data.companyUserId = common.getLocalStorage('companyUserId')
      common.call('ChangeCompanyUserPassword', dataEntity, function (result) {
        ElMessage.success('密码修改成功')
        dialogFormVisible.value = false
        resetDataEntity()
        logout()
      })
    } else {
      console.log('Error submit')
      return false
    }
  })
}

/**
 * 重置数据实体
 */
function resetDataEntity() {
  dataEntity.oldPassword = ''
  dataEntity.password = ''
  dataEntity.rePassword = ''
}

//退出登录
function logout() {
  // 执行退出
  // 清空token
  const store = useTokenStore()
  store.saveToken('')
  // 提示
  ElMessage.success('用户已登出！')
  localStorage.clear()
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
}

defineExpose({
  init
})
watchEffect(() => {
  if (!dialogFormVisible?.value) {
    resetDataEntity()
    ruleFormRef?.value?.clearValidate();
  }
})
</script>
<template>
  <div>
    <el-dialog destroy-on-close
      :close-on-click-modal="false"
      v-model="dialogFormVisible"
      title="修改密码"
      style="width: 450px"
    >
      <el-form ref="ruleFormRef" :model="dataEntity" :rules="rules">
        <el-form-item label="旧密码" prop="oldPassword" :label-width="formLabelWidth">
          <el-input
            autocomplete="off"
            type="password"
            v-model="dataEntity.oldPassword"
            placeholder="请输入旧密码"
          />
        </el-form-item>
        <el-form-item label="新密码" prop="password" :label-width="formLabelWidth">
          <el-input
            autocomplete="off"
            type="password"
            v-model="dataEntity.password"
            placeholder="请输入新密码"
          />
        </el-form-item>
        <el-form-item label="确认密码" prop="rePassword" :label-width="formLabelWidth">
          <el-input
            autocomplete="off"
            type="password"
            v-model="dataEntity.rePassword"
            placeholder="请再输入一次新密码"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="onSubmit"> 确认 </el-button>
          <el-button @click="dialogFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.el-input {
  width: 300px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
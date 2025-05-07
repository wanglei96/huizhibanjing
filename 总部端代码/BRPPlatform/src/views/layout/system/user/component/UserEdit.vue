<script setup>
import { avatarEmits } from 'element-plus'

const dialogFormVisible = ref(false)
const msg = ref('新建用户')

const selectedRoleList = ref([])
const options = [
  {
    value: '管理员',
    label: '管理员'
  },
  {
    value: '普通用户',
    label: '普通用户'
  },
  {
    value: '游客',
    label: '游客'
  }
]

const userForm = reactive({
  username: '',
  nickname: '',
  password: '',
  sex: '',
  phone: ''
})

function init(data) {
  Object.assign(userForm, data)
  dialogFormVisible.value = true
  if (!data) {
    msg.value = '新建用户'
  } else {
    msg.value = '编辑用户'
  }
}

defineExpose({
  init
})
</script>
<template>
  <div>
    <el-dialog :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="800" draggable>
      <el-form :model="userForm" :label-position="'right'" label-width="80px">
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="userForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="nickname">
              <el-input v-model="userForm.nickname" placeholder="请输入昵称"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input v-model="userForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
              <el-radio-group v-model="userForm.sex" class="ml-4">
                <el-radio label="1" size="large">男</el-radio>
                <el-radio label="2" size="large">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="Email" prop="email">
              <el-input v-model="userForm.email" placeholder="请输入email"></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="idcard">
              <el-input v-model="userForm.idcard" placeholder="请输入手机号码"></el-input>
            </el-form-item>
            <el-form-item label="出生日期" prop="birthday">
              <el-date-picker v-model="userForm.birthday" type="date" placeholder="请选择出生日期" />
            </el-form-item>
            <el-form-item label="所属部门" prop="dept">
              <el-input v-model="userForm.dept" placeholder="请输入部门名称"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="个人简介" prop="intro">
              <el-input v-model="userForm.intro" type="textarea" rows="10" placeholder="请输入个人简介"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="用户角色" prop="role">
              <el-select v-model="selectedRoleList" multiple placeholder="请选择角色" style="width: 100%">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false"> 确定 </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.el-dialog {
  width: 500px;
}

.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 300px;
}

.el-input {
  width: 200px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>

<script setup>
import { reactive, ref } from 'vue';
import detail from '@/js/detail';
import common from "@/js/common";

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

//组织机构树
const sysOrganizationTree = ref([]);
//获取组织机构树
const getSysOrganizationTree = () => {
  let params = {
    condition: "is_deleted is not true"
  }
  common.call('GetSysOrganizationTree', params, (res) => {
    sysOrganizationTree.value = res.data;
  })
}

let page = reactive({});
const init = (options) => {
  if (options.mode === 'edit' || options.mode === 'view') {
    if (!options.primaryKeyValue) {
      ElMessage({ message: '请先选择一个节点', type: 'warning' })
      return;
    }
  }

  //获取组织机构树
  getSysOrganizationTree();
  page = detail({
    baseTitle: '用户',
    tableName: 'sys_user',
    getServiceName: 'GetSysUser',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit,
    processDataEntity(dataEntity) {
      delete dataEntity.password;
      return dataEntity;
    }
  });
  detailFormVisible.value = true
  //添加模式下生成员工编号
  if (page.mode === 'add') {
    let data = {
      tableName: 'sys_user',
      filedName: 'employee_no',
      length: 6,
    };
    common.call('CreateRandomNo', data, (res) => {
      page.dataEntity.value.employeeNo = res.data;
    })
  }
}

//暴露
defineExpose({
  init
})

//表单校验规则
const rules = reactive({
  loginName: [
    { required: true, message: '请输入登录账号', trigger: 'blur' },
    { min: 3, max: 12, message: '长度为3~12位数字或英文字母', trigger: 'blur' }
  ],
  /* password: [
    { min: 5, max: 18, message: '登录密码长度为5~18位', trigger: 'blur' }
  ], */
  trueName: [
    { required: true, message: '请输入真实姓名', trigger: 'blur' }
  ],
  mobilePhone: [
    { required: true, message: '请输入手机号码', trigger: 'blur' },
    { pattern: /^1[3456789]\d{9}$/, message: '手机号格式有误', trigger: 'blur' }
  ]
})

function submitForm() {
  if (page.isAddMode()) {
    if (!page.dataEntity.value.password) {
      ElMessage({ message: "请输入密码", type: 'warning' })
      return;
    } else if (page.dataEntity.value.password.length < 6) {
      ElMessage({ message: "密码长度不能少于6位", type: 'warning' })
      return;
    } else if (page.dataEntity.value.password.length > 18) {
      ElMessage({ message: "密码长度不能超过18位", type: 'warning' })
      return;
    }
  }
  page.submitForm();
}
</script>
<template>
  <div>
    <el-dialog :close-on-click-modal="false" v-model="detailFormVisible" :title="page.title ? page.title.value : ''"
      width="800" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode === 'view'" label-width="80px">
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="员工编号" prop="employeeNo">
              <el-input v-model="page.dataEntity.value.employeeNo" placeholder="请输入员工编号" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="组织机构" prop="sysOrganizationId">
              <el-tree-select v-model="page.dataEntity.value.sysOrganizationId" :data="sysOrganizationTree"
                check-strictly placeholder="请选择组织机构" :render-after-expand="false" class="form-item-width" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="登录账号" prop="loginName">
              <el-input v-model="page.dataEntity.value.loginName" placeholder="请输入登录账号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="登录密码" prop="password">
              <el-input v-model="page.dataEntity.value.password" placeholder="请输入登录密码" type="password"
                show-password></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="真实姓名" prop="trueName">
              <el-input v-model="page.dataEntity.value.trueName" placeholder="请输入真实姓名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="手机号码" prop="mobilePhone">
              <el-input v-model="page.dataEntity.value.mobilePhone" placeholder="请输入手机号码"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="描述" prop="description">
              <el-input v-model="page.dataEntity.value.description" type="textarea" rows="10"
                placeholder="请输入描述"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" v-if="page.mode === 'add' || page.mode === 'edit'" :loading="page.isLoading.value"
            @click="submitForm()"> 确定 </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.form-item-width {
  width: 200px !important;
}

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

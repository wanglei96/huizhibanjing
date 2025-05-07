<script setup>
import { inject, reactive, ref } from 'vue'
import common from '@/js/common'

const dialogFormVisible = ref(false);
const formRef = ref();
const dataItem = ref({});

// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

function init() {
  // 显示弹窗
  dialogFormVisible.value = true
}

const submitForm = async (formRef) => {
  if (!formRef) return
  await formRef.validate((valid, fields) => {
    if (valid) {
      let data = dataItem.value;
      data.companyId = companyId.value;
      common.call("SwopMemberBorrowBook", data, function (result) {
        ElMessage({ message: "操作成功", type: 'success' });
        dialogFormVisible.value = false
        formRef.resetFields()
      })
    }
  })
}
const rules = reactive({
  memberACardNo: [
    { required: true, message: '会员A卡号不允许为空', trigger: 'blur' },
  ],
  memberABookSn: [
    { required: true, message: '会员A所借书号不允许为空', trigger: 'blur' },
  ],
  memberBCardNo: [
    { required: true, message: '会员B卡号不允许为空', trigger: 'blur' },
  ],
  memberBBookSn: [
    { required: true, message: '会员B所借书号不允许为空', trigger: 'blur' },
  ]
})
//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(dialogFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    formRef.value.clearValidate()
    dataItem.value = {}
  }
})
defineExpose({
  init
})
</script>

<template>
  <div>
    <el-dialog destroy-on-close v-model="dialogFormVisible" title="交换两个会员所借书籍" width="600" draggable :close-on-click-modal="false">
      <div style="color: red;margin-bottom: 20px;">该功能可以更改找错的书籍</div>
      <el-form :model="dataItem" ref="formRef" :rules="rules" :label-position="'right'">
        <el-row >
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="会员A卡号" prop="memberACardNo" label-width="100px">
              <el-input v-model="dataItem.memberACardNo" placeholder="请输入会员卡号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="会员A所借书号" prop="memberABookSn" label-width="120px">
              <el-input v-model="dataItem.memberABookSn" placeholder="请输入书号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row >
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="会员B卡号" prop="memberBCardNo" label-width="100px">
              <el-input v-model="dataItem.memberBCardNo" placeholder="请输入书号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="会员B所借书号" prop="memberBBookSn" label-width="120px">
              <el-input v-model="dataItem.memberBBookSn" placeholder="请输入书号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="submitForm(formRef)"> 确定 </el-button>
          <el-button @click="dialogFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script setup>
import { inject, reactive, ref } from 'vue'
import common from '@/js/common'

const dialogFormVisible = ref(false)
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
      common.call("OneClickMemberReturnBook", data, function (result) {
        ElMessage({ message: "操作成功", type: 'success' });
        dialogFormVisible.value = false
        formRef.resetFields()
      })
    }
  })
}
const rules = reactive({
  memberCardNo: [
    { required: true, message: '会员卡号不能为空', trigger: 'blur' },
  ],
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
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" title="一键帮助会员点还书" width="600" draggable >
      <div style="color: red;margin-bottom: 20px;">
        一键帮助会员点还书：通过会员卡号可以将会员名下“借阅中”、“到店借阅”的图书状态一键更改为“等待入库”状态，方便取书时派单。操作完成后，手动派单时请通过【配送单管理】->【只还未借订单】里查询（打开页面后按ctrl+f，查会员名即可）
      </div>
      <el-form :model="dataItem" ref="formRef" :rules="rules" :label-position="'right'" label-width="100px">
        <el-form-item label="会员卡号：" prop="memberCardNo">
          <el-input v-model="dataItem.memberCardNo" placeholder="请输入"></el-input>
        </el-form-item>
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
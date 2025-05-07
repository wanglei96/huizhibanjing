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
      common.call("AddMemberBorrowTimes", data, function (result) {
        ElMessage({ message: "操作成功", type: 'success' });
        dialogFormVisible.value = false
        formRef.resetFields()
      })
    }
  })
}
const rules = reactive({
  newMemberCardNo: [
    { required: true, message: '新会员不允许为空', trigger: 'blur' },
  ],
  oldMemberCardNo: [
    { required: true, message: '推荐人不允许为空', trigger: 'blur' },
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
    <el-dialog destroy-on-close v-model="dialogFormVisible" title="推荐人、被推荐人各延长一个月" width="600" draggable
    :close-on-click-modal="false">
      <el-form :model="dataItem" ref="formRef" :rules="rules" :label-position="'right'" label-width="80px">
        <el-form-item label="新会员：" prop="newMemberCardNo">
          <el-input v-model="dataItem.newMemberCardNo" placeholder="请输入卡号"></el-input>
        </el-form-item>
        <el-form-item label="推荐人：" prop="oldMemberCardNo">
          <el-input v-model="dataItem.bookSn" placeholder="请输入卡号"></el-input>
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
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
      common.call("ScanBookEnabled", data, function (result) {
        ElMessage({ message: "操作成功", type: 'success' });
        //dialogFormVisible.value = false
        formRef.resetFields()
      })
    }
  })
}
const rules = reactive({
  bookSn: [
    { required: true, message: '图书条码不能为空', trigger: 'blur' },
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
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" title="扫码快速下架、上架" width="600" draggable >
      <div style="color: red;margin-bottom: 20px;">该功能可以通过扫码。上下架图书</div>
      <el-form :model="dataItem" ref="formRef" :rules="rules" :label-position="'right'" label-width="100px">
        <el-form-item label="图书条码：" prop="bookSn">
          <el-input v-model="dataItem.bookSn" placeholder="请输入图书条码" @keydown.enter.prevent=submitForm(formRef)></el-input>
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
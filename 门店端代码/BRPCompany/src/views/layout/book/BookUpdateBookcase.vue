<script setup>
import { inject, reactive, ref } from 'vue'
import common from '@/js/common'
import DatabaseSelect from "@/components/extension/DatabaseSelect.vue";

const dialogFormVisible = ref(false)
const formRef = ref();
const reLoadData = inject('reLoadData');

// 编辑页初始化
const bookData = ref({});
function init(data) {
  // 显示弹窗
  dialogFormVisible.value = true
  // 表单渲染
  if (data) {
    bookData.value = data;
  }
}
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

const submitForm = async (formRef) => {
  if (!formRef) return
  await formRef.validate((valid, fields) => {
    if (valid) {
      let data = {
        id: bookData.value.id,
        bookcaseId: bookData.value.bookcaseId
      };
      common.call("UpdateBook", data, function (result) {
        dialogFormVisible.value = false
        formRef.resetFields();
        reLoadData();
      })
    }
  })
}
const rules = reactive({
  bookcaseId: [
    { required: true, message: '请选择书柜', trigger: 'change' },
  ]
})
//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(dialogFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    formRef.value.clearValidate()
    bookData.value = {}
  }
})
defineExpose({
  init
})
</script>

<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" width="600" draggable>
      <template #title>
        <span>修改《</span>
        <span style="color: #008000;">{{ bookData.bookSeriesName ? "[" + bookData.bookSeriesName + "]" : ""
          }}</span>
        <span>{{ bookData.bookName }}</span>
        <span style="color: #0000ff;">{{ bookData.bindingTypeName ? "(" + bookData.bindingTypeName + ")" : ""
          }}</span>
        <span>》</span>
      </template>
      <!-- <div style="margin: 0 0 10px 14px;font-weight: bold;">请输入书柜号，未创建的书柜号会自动创建</div> -->
      <el-form :model="bookData" ref="formRef" :rules="rules" :label-position="'right'" label-width="80px">
        <!-- <el-row :gutter="80">
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="书柜号：" prop="bookcaseName">
              <el-input v-model="bookData.bookcaseName" placeholder="请输入书柜名称"></el-input>
            </el-form-item>
          </el-col>
        </el-row> -->
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="书柜" prop="bookcaseId">
              <database-select placeholder="请选择书柜" table-name="bookcase" :condition="'company_id=' + companyId"
                v-model="bookData.bookcaseId" valueFieldName="id" class="form-item-width"></database-select>
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
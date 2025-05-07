<script setup>
import { inject, reactive, ref } from 'vue'
import common from '@/js/common'

const dialogFormVisible = ref(false)
const formRef = ref();
const emit = defineEmits(['reload-data']);

// 编辑页初始化
const bookSkuId = ref();
const approveComment = ref("");
// 当前登陆人
const sysUserId = ref(
  `${common.getLocalStorage('sysUserId')}`
);
function init(options) {
  // 显示弹窗
  dialogFormVisible.value = true
  bookSkuId.value = options.id;
}

function submitForm() {
  let data = {
    bookSkuId: bookSkuId.value,
    approveComment: approveComment.value,
    approverId: sysUserId.value,
  }
  common.call("ApprovedBookSku", data, function (result) {
    dialogFormVisible.value = false
    emit('reload-data');
  })
}

const rules = reactive({
})
//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(dialogFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    formRef.value.clearValidate()
    approveComment.value = ""
  }
})
defineExpose({
  init
})
</script>

<template>
  <div>
    <el-dialog v-model="dialogFormVisible" title="审核入库" width="600" draggable :close-on-click-modal="false">
      <el-form ref="formRef" :rules="rules" :label-position="'right'" label-width="80px">
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="处理内容" prop="description">
              <el-input v-model="approveComment" type="textarea" placeholder="请输入处理内容"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="submitForm()"> 通过 </el-button>
          <el-button @click="dialogFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
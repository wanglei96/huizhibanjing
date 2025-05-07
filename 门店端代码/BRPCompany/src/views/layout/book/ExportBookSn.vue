<script setup>
import { inject, reactive, ref } from 'vue'
import common from '@/js/common'
import config from "@/js/config";
import axios from 'axios'
import fileDownload from 'js-file-download'


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
      let startBookSn = dataItem.value.startBookSn;
      let endBookSn = dataItem.value.endBookSn;
      let url = config.adminRootUrl + "/api/BarCode";
      let params = {
        startBookSn: startBookSn,
        companyId: companyId.value
      }
      if (endBookSn) {
        params.endBookSn = endBookSn
      }
      axios.get(url, {
        params,
        responseType: 'blob'
      })
        .then(response => {
          ElMessage({ message: "书号条形码导出成功", type: 'success' })
          dialogFormVisible.value = false;
          const blob = new Blob([response.data], { type: 'application/zip' });
          fileDownload(blob, 'bookSnBarCode.zip'); // 使用 js-file-download 库下载文件
        })
        .catch(error => {
          ElMessage({ message: "书号条形码导出失败", type: 'error' })
        });
    }
  })
}

const exportForm = async (formRef) => {
  if (!formRef) return
  formRef.validate((valid, fields) => {
    if (valid) {
      let params = {}
      params.exportName = 'BookSn'
      params.pageSize = '100000'
      params.orderBy = 'sn'
      params.companyId = companyId.value
      params.condition = dataItem.value.endBookSn ? "sn >= '" + dataItem.value.startBookSn + "' and sn <= '" + dataItem.value.endBookSn + "'" : "sn >= '" + dataItem.value.startBookSn + "'";
      common.call('Export', params, (res) => {
        let url = res.adminRootUrl + res.data.filePath
        window.open(url)
      })
    }
  })
}

const rules = reactive({
  startBookSn: [
    { required: true, message: '开始书号不允许为空', trigger: 'blur' },
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
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" title="导出打码" width="600"
      draggable>
      <el-form :model="dataItem" ref="formRef" :rules="rules" :label-position="'right'" label-width="80px">
        <el-form-item label="开始书号" prop="startBookSn">
          <el-input v-model="dataItem.startBookSn" placeholder="请输入开始书号"></el-input>
        </el-form-item>
        <el-form-item label="结束书号" prop="endBookSn">
          <el-input v-model="dataItem.endBookSn" placeholder="请输入结束书号"></el-input>
        </el-form-item>
      </el-form>
      <div style="line-height: 25px;">
        <h3>备注：</h3>
        <div style="color: red;">① 开始书号（从这个书号开始导出），填数字小的，必填</div>
        <div style="color: red;">② 结束书号（到这个书号结束），填数字最大的，必填（不填默认导出从开始书号后录入的所有图书、填写当次录入的最后一本图书）</div>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <!-- <el-button type="primary" @click="submitForm(formRef)"> 导出打码 </el-button> -->
          <el-button type="primary" @click="exportForm(formRef)"> 导出Excel </el-button>
          <el-button @click="dialogFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
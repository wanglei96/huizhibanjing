<script setup>
import { reactive, ref, watch } from 'vue';
import common from '@/js/common';
import config from '@/js/config'
import { ElLoading, ElMessage } from 'element-plus';
//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
function init() {
  // 显示弹窗
  detailFormVisible.value = true
}

const upload = ref()

const handleExceed = (files) => {
  upload.value?.clearFiles()
  const file = files[0]
  //file.uid = genFileId()
  upload.value?.handleStart(file)
};


const submitUpload = () => {
  upload.value?.submit();
};

const beforeFileUpload = (rawFile) => {
  if (rawFile.type !== 'application/vnd.ms-excel' &&
    rawFile.type !== 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') {
    ElMessage.error('请上传Excel格式的文件!')
    return false
  }
  return true
}

const uploadExcel = async (file) => {
  console.log(file);
  common.uploadFile(file, function (result) {
    let fileId = result.data.id;
    uploadBookExcel(fileId);
  });
};

//ElLoading.props.lock = false; // 关闭蒙版
//ElLoading.props.background = 'rgba(255, 255, 255, 0.7)'; // 设置背景为白色

const loading = ref(false)
// 使用加载动画的函数
function uploadBookExcel(fileId) {
  //let loadingInstance = ElLoading.service({ fullscreen: true, text: '导入中...' }); // 将加载动画显示在整个页面中央
  loading.value = true;
  let params = {
    uploadId: fileId
  };
  common.call('UploadPlatformBookSkuExcel', params, (result) => {
    loading.value = false;
    //loadingInstance.close(); // 结束加载动画
    ElMessage({ message: "导入成功", type: 'success' });
  });
}

// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    upload.value.clearFiles();
    emit('reload-data');
  }
})
function downloadExcel() {
  window.open('https://admin.brp.rome8.com/template/newbooks.xlsx');
}
//暴露
defineExpose({
  init
})
</script>
<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" title="批量录入图书" width="1200"
      draggable>
      <el-card shadow="never" class="excel-card" v-loading="loading" element-loading-text="Loading..."
        :element-loading-spinner="svg" element-loading-svg-view-box="-10, -10, 50, 50"
        element-loading-background="rgba(122, 122, 122, 0.8)">
        <template #header>
          <div class="card-header">
            <span>Excel批量导入图书信息</span>
          </div>
        </template>
        <div class="card-body">
          <div>新书录入步骤：</div>
          <div class="infolist">
            <span>1、请先下载SKU新书录入模板 “newbooks.xlsx” ，</span>
            <span><el-link type="danger" @click="downloadExcel()" target="_blank">点击下载</el-link></span>
            <span>，然后将信息按照要求填入模板</span>
          </div>
          <div>
            <el-upload ref="upload" class="upload-demo" action="uploadExcel" :limit="1" :on-exceed="handleExceed"
              :auto-upload="false" :http-request="uploadExcel" :before-upload="beforeFileUpload">
              <template #trigger>
                <el-button type="primary">选择文件</el-button>
              </template>
              <el-button type="success" @click="submitUpload"  class="upload-button">
                提交excel
              </el-button>
            </el-upload>
          </div>
        </div>
      </el-card>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.el-card {
  margin-bottom: 20px;
}

.singleline {
  width: 600px;
  font-size: 14px;
  color: #666;
  display: flex;
}

.el-popper.is-customized {
  /* Set padding to ensure the height is 32px */
  padding: 6px 12px;
  background: linear-gradient(90deg, rgb(159, 229, 151), rgb(204, 229, 129));
}

.el-popper.is-customized .el-popper__arrow::before {
  background: linear-gradient(45deg, #b2e68d, #bce689);
  right: 0;
}

.el-link {
  margin-right: 8px;
}

.el-link .el-icon--right.el-icon {
  vertical-align: text-bottom;
}

.infolist {
  display: flex;
  height: 28px;
  line-height: 28px;
  justify-content: flex-start;
  align-items: center;
  font-size: 14px;

  span {
    display: inline-block;
    margin-right: 6px;
  }
}
</style>
<style>
.excel-card {
  margin-bottom: 15px;
  box-shadow: 0 0 0 1px #dff0d8;

  .el-card__header {
    background-color: #dff0d8;
  }
}

.upload-list-card {
  margin-bottom: 15px;
  box-shadow: 0 0 0 1px #dff0d8;

  .el-card__header {
    background-color: #dff0d8;
  }
}

.avatar-uploader .avatar {
  width: 150px;
  height: 150px;
  display: block;
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}

.upload-button {
  margin-left: 10px;
}
</style>

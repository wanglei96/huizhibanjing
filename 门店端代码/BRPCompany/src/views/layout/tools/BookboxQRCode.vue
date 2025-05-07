<script setup>
import { reactive, ref } from 'vue';
import common from "@/js/common";
import config from "@/js/config";


//详情页开关
const detailFormVisible = ref(false);

// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

const bookboxQRCodeUrl = ref("");

const companyInfo = ref({});
function init() {
  detailFormVisible.value = true;
  //获取门店信息
  getCompanyInfo();
  let content = config.companyRootUrl + "/companyView?companyId=" + companyId.value;
  let qrCodeUrl = config.adminRootUrl + "/api/QRCode?content=" + content + "&width=600&height=600";
  bookboxQRCodeUrl.value = qrCodeUrl;
  console.log("qrCodeUrl", qrCodeUrl);
}

function getCompanyInfo() {
  let params = {
    id: companyId.value
  }
  common.call("GetViewCompany", params, (res) => {
    companyInfo.value = res.data;
  })
}


defineExpose({
  init
})

</script>
<template>
  <div>
    <el-dialog destroy-on-close v-model="detailFormVisible" title="书箱不干胶二维码" width="800" draggable
      :close-on-click-modal="false">
      <el-card shadow="never" class="customer-card">
        <div class="card-body">
          <el-image :src="bookboxQRCodeUrl" class="avatar">
            <template #error>
              <el-icon class="avatar-uploader-icon">
                <Plus />
              </el-icon>
            </template>
          </el-image>
        </div>
      </el-card>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped></style>

<style>
.customer-card {
  margin-bottom: 20px;
  box-shadow: 0 0 0 1px #d9edf7;

  .el-card__header {
    background-color: #d9edf7;
  }
}

.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
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
</style>

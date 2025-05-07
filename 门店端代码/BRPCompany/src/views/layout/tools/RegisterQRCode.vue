<script setup>
import { reactive, ref } from 'vue';
import common from "@/js/common";


//详情页开关
const detailFormVisible = ref(false);

// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

const qrCodeUrl = ref("");
function init() {
  detailFormVisible.value = true;
  getQrCode();
}
function getQrCode() {
  var params = {};
  params.QRCodeWidth = "500";
  params.scene = "companyId=" + companyId.value;
  common.call("GetWXQRCode", params, function (result) {
    qrCodeUrl.value = result.data;
  })
}


defineExpose({
  init
})

</script>
<template>
  <div>
    <el-dialog destroy-on-close v-model="detailFormVisible" title="办卡登记二维码" width="800" draggable :close-on-click-modal="false">
      <el-card shadow="never" class="customer-card">
        <div class="card-body">
          <el-image :src="qrCodeUrl" class="avatar">
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

.avatar {
  width: 300px;
  height: 300px;
  display: flex;
  align-items: center;
  justify-content: center;

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

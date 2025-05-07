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

const companyInfo = ref({});
const companyPromotionQRCodeUrl = ref('');
function init() {
  detailFormVisible.value = true;
  //获取门店信息
  getCompanyInfo()
  let content = config.companyRootUrl + "/companyPromotion?companyId=" + companyId.value;
  let qrCodeUrl = config.adminRootUrl + "/api/QRCode?content=" + content;
  companyPromotionQRCodeUrl.value = qrCodeUrl;
  console.log("qrCodeUrl", qrCodeUrl);
}

function getCompanyInfo() {
  let params = {
    id: companyId.value
  }
  common.call('GetViewCompany', params, (res) => {
    companyInfo.value = res.data;
  })
}
defineExpose({
  init
})

const uploadImage = async (file) => {
  common.uploadFile(file, function (result) {
    companyInfo.value.customerServiceImageFilePath = result.data.path;
    companyInfo.value.customerServiceImageFileId = result.data.id;
    updateCompanyCustomerServiceImage(result.data.id);
  });
};

function updateCompanyCustomerServiceImage(id) {
  let params = {
    id: companyId.value,
    customerServiceImageFileId: id
  }
  common.call('UpdateCompany', params, (res) => {
    ElMessage.success('上传成功');
  });
}

const customerImageUrl = computed(() => {
  return common.getAdminUrl(companyInfo.value, 'customerServiceImageFilePath');
})






const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {
    ElMessage.error('请上传图片')
    return false
  } else if (rawFile.size / 1024 / 1024 > 5) {
    ElMessage.error('图片不能大于 5MB!')
    return false
  }
  return true
}

</script>
<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" title="微信文案二维码" width="800" draggable>
      <el-card shadow="never" class="customer-card">
        <template #header>
          <div>
            <span>上传微信客服二维码</span>
          </div>
        </template>
        <div class="card-body">
          <div>
            <el-upload class="avatar-uploader" action="uploadPath" :show-file-list="false"
              :before-upload="beforeAvatarUpload" :http-request="uploadImage">
              <el-image :src="customerImageUrl" class="avatar">
                <template #error>
                  <el-icon class="avatar-uploader-icon">
                    <Plus />
                  </el-icon>
                </template>
              </el-image>
            </el-upload>
          </div>
        </div>
      </el-card>
      <el-card shadow="never" class="customer-card">
        <template #header>
          <div>
            <span>宣传文案二维码</span>
          </div>
        </template>
        <div class="card-body avatar-uploader">
          <el-image :src="companyPromotionQRCodeUrl" class="avatar">
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

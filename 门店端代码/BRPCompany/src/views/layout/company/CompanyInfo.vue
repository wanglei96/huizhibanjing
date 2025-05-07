<script setup>
import { onMounted, reactive, ref } from 'vue'
import detail from '@/js/detail'
import common from '@/js/common'
import config from '@/js/config'
import ImageUpload from '@/components/extension/ImageUpload.vue'
import { ElMessage } from 'element-plus'

//定义事件发射器
const emit = defineEmits(['reload-data'])
//表单引用
const refDataEntityForm = ref()
const wxQRCode = new ref()
let page = reactive({})
const init = () => {
  page = detail({
    baseTitle: '门店',
    tableName: 'company',
    getServiceName: 'GetViewCompany',
    mode: 'edit',
    primaryKeyValue: localStorage.getItem('companyId'),
    refDataEntityForm,
    emit,
    processDataEntity(dataEntity) {
      delete dataEntity.password
      return dataEntity
    }
  })
}
init()
//表单校验规则
const rules = reactive({
  companyNo: [
    {
      required: true,
      message: '请输入门店编号',
      trigger: 'blur'
    },
    {
      min: 3,
      max: 14,
      message: '门店编号长度为3~12位数字',
      trigger: 'blur'
    },
    {
      pattern: /^[0-9]+$/,
      message: '门店编号长度为3~12位数字',
      trigger: 'blur'
    }
  ]
})

/**
 * 图片上传完成事件
 * @param val
 */
function imageUpload(val) {
  page.dataEntity.value.licenseImageFileId = val.id
}

/**
 * 图片上传完成事件
 * @param val
 */
function imageFileUpload(val) {
  page.dataEntity.value.imageFileId = val.id
}
function customerServiceImageFileUpload(val){
  page.dataEntity.value.customerServiceImageFileId = val.id
}
/*
绑定门店推荐二维码
*/
function bindQRCodeImage() {
  var requestData = {}
  requestData.scene = 'companyId-' + common.getCompanyId()
  requestData.QRCodeWidth = 500
  common.call('GetWXQRCode', requestData, function (result) {
    wxQRCode.value = result.data
  })
}

onMounted(function () {
  //绑定门店推荐二维码
  bindQRCodeImage()
})
function submitForm(){
  page.submitForm();
  ElMessage.success('提交成功');
}
</script>
<template>
  <el-card>
    <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
      :disabled="page.mode === 'view'" label-width="120px">
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="24">
          <el-form-item label="编号" prop="companyNo">
            <el-input v-model="page.dataEntity.value.companyNo" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="24">
          <el-form-item label="门店名称" prop="companyName">
            <el-input v-model="page.dataEntity.value.companyName" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- <el-row >
        <el-col :xs="24" :sm="24" :md="24" :lg="24">
          <el-form-item label="管理员账号" prop="loginName">
            <el-input v-model="page.dataEntity.value.loginName" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row> -->
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="24">
          <el-form-item label="联系人" prop="contact">
            <el-input v-model="page.dataEntity.value.contact" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="24">
          <el-form-item label="联系电话" prop="mobilePhone">
            <el-input v-model="page.dataEntity.value.mobilePhone" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="24">
          <el-form-item label="客服电话" prop="servicePhone">
            <el-input v-model="page.dataEntity.value.servicePhone"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="24">
          <el-form-item label="地址" prop="address">
            <el-input v-model="page.dataEntity.value.address"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- <el-row >
        <el-col :xs="24" :sm="24" :md="24" :lg="24">
          <el-form-item label="简介" prop="description">
            <vue-ueditor-wrap
              v-model="page.dataEntity.value.description"
              editor-id="editor"
              :config="config.editorConfig"
              :editorDependencies="['ueditor.config.js', 'ueditor.all.js']"
            />
          </el-form-item>
        </el-col>
      </el-row> -->
      <el-row>
        <el-col :xs="12" :sm="12" :md="12" :lg="12">
          <el-form-item label="证照图片" prop="cover">
            <ImageUpload v-model="page.dataEntity.value.licenseImageFileId"
              :base-img-url="page.dataEntity.value.licenseImageFilePath" @image-upload="imageUpload" />
          </el-form-item>
        </el-col>
        <el-col :xs="12" :sm="12" :md="12" :lg="12">
          <el-form-item label="门店图片" prop="cover">
            <ImageUpload v-model="page.dataEntity.value.imageFileId" :base-img-url="page.dataEntity.value.imageFileId"
              @image-upload="imageFileUpload" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-form label-width="120px">
      <el-row>
        <el-col :xs="12" :sm="12" :md="12" :lg="12">
          <el-form-item label="门店二维码">
            <img :src="wxQRCode" class="wx-qrcode" />
          </el-form-item>
        </el-col>
        <el-col :xs="12" :sm="12" :md="12" :lg="12">
          <el-form-item label="客服二维码" prop="cover">
            <ImageUpload v-model="page.dataEntity.value.customerServiceImageFileId" :base-img-url="page.dataEntity.value.customerServiceImageFileId"
              @image-upload="customerServiceImageFileUpload" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div class="footer-box">
      <el-button type="primary" @click="submitForm"> 保存</el-button>
    </div>
  </el-card>
</template>

<style lang="scss" scoped>
.footer-box {
  text-align: right;
}

.box {
  width: 1000px;
  box-sizing: border-box;
  background-color: #fff;
  padding: 20px;
  position: absolute;
  left: 50%;
  top: 45%;
  transform: translate(-50%, -50%);
}

.form-item-width {
  width: 200px;
}

.el-dialog {
  width: 500px;
}

.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 300px;
}

.el-input {
  width: 100%;
}

.dialog-footer button:first-child {
  margin-right: 10px;
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

.wx-qrcode {
  width: 200px;
  height: 200px;
}
</style>
<script setup>
import { reactive, ref } from 'vue';
import detail from '@/js/detail';
import common from "@/js/common";
import ImageUpload from "@/components/extension/ImageUpload.vue";

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

let page = reactive({});
const init = (options) => {
  detailFormVisible.value = true

  page = detail({
    baseTitle: '门店',
    tableName: 'company',
    getServiceName: 'GetViewCompany',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit,
    processDataEntity(dataEntity) {
      delete dataEntity.password;
      return dataEntity;
    }
  });
  //添加模式下生成门店编号
  if (page.mode === 'add') {
    let data = {
      tableName: 'company',
      filedName: 'company_no',
      length: 3,
    };
    common.call('CreateRandomNo', data, (res) => {
      page.dataEntity.value.companyNo = res.data;
    })
  }
}

//暴露
defineExpose({
  init
})



//表单校验规则
const rules = reactive({
  companyNo: [
    { required: true, message: '请输入门店编号', trigger: 'blur' },
    { min: 3, max: 14, message: '门店编号长度为3~12位数字', trigger: 'blur' },
    { pattern: /^[0-9]+$/, message: '门店编号长度为3~12位数字', trigger: 'blur' }
  ],
  companyName: [
    { required: true, message: '请输入门店名称', trigger: 'blur' },
    /* { min: 3, max: 10, message: '门店名称长度为3~10个字符', trigger: 'blur' } */
  ],
  contact: [
    { required: true, message: '请输入联系人', trigger: 'blur' }
  ],
  mobilePhone: [
    { required: true, message: '请输入联系电话', trigger: 'blur' },
    { pattern: /^1[3456789]\d{9}$/, message: '手机号格式有误', trigger: 'blur' }
  ],
  address: [
    { required: true, message: '请输入门店地址', trigger: 'blur' }
  ]
})

/* password: [
    { required: false, message: '请输入登录密码', trigger: 'blur' },
    { required: false, min: 5, max: 18, message: '登录密码长度为5~18位', trigger: 'blur' }
  ], */


/**
 * 图片上传完成事件
 * @param val
 */
function imageUpload(val) {
  page.dataEntity.value.imageFileId = val.id;
}

function licenseImageUpload(val) {
  page.dataEntity.value.licenseImageFileId = val.id;
}

function submitForm() {
  if (page.isAddMode()) {
    if (!page.dataEntity.value.password) {
      ElMessage({ message: "请输入密码", type: 'warning' })
      return;
    } else if (page.dataEntity.value.password.length < 6) {
      ElMessage({ message: "密码长度不能少于6位", type: 'warning' })
      return;
    } else if (page.dataEntity.value.password.length > 18) {
      ElMessage({ message: "密码长度不能超过18位", type: 'warning' })
      return;
    }
  }
  page.submitForm();
}
</script>
<template>
  <div>
    <el-dialog :close-on-click-modal="false" v-model="detailFormVisible" :title="page.title ? page.title.value : ''"
      width="800" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode === 'view'" label-width="120px">
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="门店编号" prop="companyNo">
              <el-input v-model="page.dataEntity.value.companyNo" :disabled="!(page.mode === 'add')"
                placeholder="请输入门店编号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="门店名称" prop="companyName">
              <el-input v-model="page.dataEntity.value.companyName" placeholder="请输入门店名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="联系人" prop="contact">
              <el-input v-model="page.dataEntity.value.contact" placeholder="请输入联系人"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="联系电话" prop="mobilePhone">
              <el-input v-model="page.dataEntity.value.mobilePhone" placeholder="请输入联系电话"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="登录密码" prop="password">
              <el-input v-model="page.dataEntity.value.password" placeholder="请输入登录密码" :show-password="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="开户银行" prop="bankName">
              <el-input v-model="page.dataEntity.value.bankName" placeholder="请输入开户银行"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="户名" prop="accountName">
              <el-input v-model="page.dataEntity.value.accountName" placeholder="请输入户名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="银行卡号" prop="bankCardNo">
              <el-input v-model="page.dataEntity.value.bankCardNo" placeholder="请输入银行卡号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="门店地址" prop="address">
              <el-input v-model="page.dataEntity.value.address" type="textarea" rows="10"
                placeholder="请输入门店地址"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="证照图片" prop="licenseImageFileId">
              <ImageUpload v-model="page.dataEntity.value.licenseImageFileId"
                :baseImgUrl="page.dataEntity.value.licenseImageFilePath" @image-upload="licenseImageUpload"
                :mode="page.mode" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="门店图片" prop="imageFileId">
              <ImageUpload v-model="page.dataEntity.value.imageFileId" :baseImgUrl="page.dataEntity.value.imageFilePath"
                @image-upload="imageUpload" :mode="page.mode" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" v-if="page.mode === 'add' || page.mode === 'edit'" :loading="page.isLoading.value"
            @click="submitForm()"> 确定 </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
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
  width: 200px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>

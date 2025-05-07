<script setup>
import ImageUpload from '@/components/extension/ImageUpload.vue'

const uploadModel = ref({
  name: '',
  sfzh: '',
  sfzImg1: '',
  sfzImg2: ''
})
</script>
<template>
  <div>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>图片上传</span>
        </div>
      </template>
      <div class="card-body">
        <el-row >
          <el-col :xs="24" :sm="24" :md="12" :lg="12">
            <el-form :model="uploadModel" label-width="120px">
              <el-form-item label="真实姓名">
                <el-input v-model="uploadModel.name" placeholder="请输入您的真实姓名"></el-input>
              </el-form-item>
              <el-form-item label="身份证号">
                <el-input v-model="uploadModel.sfzh" placeholder="请输入您的身份证号"></el-input>
              </el-form-item>
              <el-form-item label="身份证人像面">
                <div style="display: flex; flex-direction: column">
                  <ImageUpload v-model="uploadModel.sfzImg1" />
                </div>
              </el-form-item>
              <el-form-item label="身份证国徽面">
                <ImageUpload v-model="uploadModel.sfzImg2" />
              </el-form-item>
            </el-form>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12">
            <h3 style="margin-bottom: 20px">表单数据：</h3>
            {{ uploadModel }}
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.el-input {
  width: 300px;
}
</style>

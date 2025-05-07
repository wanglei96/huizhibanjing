<script setup>
import Qrcode from '@/components/extension/Qrcode.vue'
import { watch } from 'vue'
const qrcodeForm = ref({
  url: 'http://www.eleadmin.cn',
  level: 'L',
  size: 200,
  isShowLogo: false,
  logoUrl: '',
  logoSize: 25
})
const qrcodeRef = ref()
watch(
  qrcodeForm,
  (newValue, oldValue) => {
    qrcodeRef.value.getQRCode()
  },
  { deep: true }
)

function handleChange(value) {
  if (value) {
    qrcodeForm.value.logoUrl =
      'https://img2.baidu.com/it/u=4113082695,1479965454&fm=253&fmt=auto&app=138&f=PNG?w=500&h=500'
  } else {
    qrcodeForm.value.logoUrl = ''
  }
  console.log(qrcodeForm.value)
}
</script>
<template>
  <div>
    <el-card>
      <template #header>
        <div class="card-header">
          <span>在线生成二维码</span>
          <span>...</span>
        </div>
      </template>
      <div class="card-body">
        <div>
          <Qrcode
            ref="qrcodeRef"
            :url="qrcodeForm.url"
            :errorCorrectionLevel="qrcodeForm.level"
            :canvasWidth="qrcodeForm.size * 1"
            :canvasHeight="qrcodeForm.size * 1"
            :logoUrl="qrcodeForm.logoUrl"
            :logoSize="qrcodeForm.logoSize * 1"
          />
        </div>
        <el-form class="qrcode-form" label-width="120px">
          <el-form-item label="二维码内容">
            <el-input v-model="qrcodeForm.url"></el-input>
          </el-form-item>
          <el-form-item label="纠错级别">
            <el-select v-model="qrcodeForm.level" placeholder="请选择纠错级别" filterable>
              <el-option label="L" value="L" />
              <el-option label="M" value="M" />
              <el-option label="Q" value="Q" />
              <el-option label="H" value="H" />
            </el-select>
          </el-form-item>
          <el-form-item label="二维码大小">
            <el-slider v-model="qrcodeForm.size" :min="150" :max="400" />
          </el-form-item>
          <el-form-item label="logo大小">
            <el-slider v-model="qrcodeForm.logoSize" :min="15" :max="80" />
          </el-form-item>
          <el-form-item label="自定义图片">
            <el-switch v-model="qrcodeForm.isShowLogo" @change="handleChange" />
          </el-form-item>
          <el-form-item label="图片地址">
            <el-input v-model="qrcodeForm.logoUrl"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
}
.card-body {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.qrcode-form {
  margin-top: 20px;
}

.el-input {
  width: 360px;
}
</style>

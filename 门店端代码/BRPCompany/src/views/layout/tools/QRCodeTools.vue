<script setup>
import common from '@/js/common'
import { reactive, ref, inject } from 'vue';
import RegisterQRCode from './RegisterQRCode.vue'
import BookboxQRCode from './BookboxQRCode.vue'
import CustomerQRCode from './CustomerQRCode.vue'

// 声明登记二维码
const registerQRCodeRef = ref();
// 书箱二维码
const bookboxQRCodeRef = ref();
// 上传微信客服二维码
const customerQRCodeRef = ref();


</script>
<template>
  <div class="user">
    <RegisterQRCode ref="registerQRCodeRef" />
    <BookboxQRCode ref="bookboxQRCodeRef" />
    <CustomerQRCode ref="customerQRCodeRef" />
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>二维码工具</span>
        </div>
      </template>
      <el-row :gutter="20" style="margin-bottom: 20px">
        <el-col :xs="24" :sm="12" :md="8" :lg="6">
          <el-card :body-style="{ padding: '0px', background: '#87cefa', height: '120px' }" shadow="hover"
            @click="registerQRCodeRef.init()">
            <div style="padding: 14px;text-align: center;">
              <h4>获取办卡登记二维码</h4>
              <div style="font-size: 12px;margin-top: 10px;">
                <span>会员扫码登记信息，办卡时出示登记信息时的编号，快速登记会员信息</span>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :xs="24" :sm="12" :md="8" :lg="6">
          <el-card :body-style="{ padding: '0px', background: '#87cefa', height: '120px' }" shadow="hover"
            @click="bookboxQRCodeRef.init()">
            <div style="padding: 14px;text-align: center;">
              <h4>书箱不干胶二维码</h4>
              <div style="font-size: 12px;margin-top: 10px;">
                <span>书箱上贴的二维码，需淘宝联系商家定做。扫码可出现门店信息、联系方式</span>
              </div>
            </div>
          </el-card>
        </el-col>
        <!-- <el-col :xs="24" :sm="12" :md="8" :lg="6">
          <el-card :body-style="{ padding: '0px', background: '#87cefa', height: '120px' }" shadow="hover"
            @click="customerQRCodeRef.init()">
            <div style="padding: 14px;text-align: center;">
              <h4>微信文案二维码</h4>
              <div style="font-size: 12px;margin-top: 10px;">
                <span>微信文案中的动态二维码，使用人员识别二维码打开页面，页面中的客服微信可以随时更换</span>
              </div>
            </div>
          </el-card>
        </el-col> -->
      </el-row>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.user {
  .user-search-card {
    margin-bottom: 15px;

    .user-form {
      .el-form-item {
        margin-bottom: 3px;
      }
    }
  }

  .table-footer {
    display: flex;
    justify-content: center;
    width: 100%;
    text-align: center;
    margin-top: 20px;
  }

}

.el-col {
  margin-bottom: 20px;
}
</style>

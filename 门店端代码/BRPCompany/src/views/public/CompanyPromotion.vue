<script setup>
import { onMounted, reactive, ref } from 'vue'
import detail from '@/js/detail'
import common from '@/js/common'
import config from '@/js/config'
import { useRoute } from 'vue-router';

const wxQRCode = new ref()

const route = useRoute();
const companyId = route.query['companyId'];
const companyInfo = ref({});
function bindCompanyInfo() {
  let params = {
    id: companyId
  }
  common.call("GetViewCompany", params, (res) => {
    companyInfo.value = res.data;
    companyInfo.value.customerServiceImageFilePath = common.getAdminUrl(res.data, 'customerServiceImageFilePath');
  })
}


/*
绑定门店推荐二维码
*/
function bindQRCodeImage() {
  var requestData = {}
  requestData.scene = 'companyId-' + companyId
  requestData.QRCodeWidth = 500
  common.call('GetWXQRCode', requestData, function (result) {
    wxQRCode.value = result.data
  })
}

onMounted(function () {
  //绑定门店信息
  bindCompanyInfo()
  //绑定门店推荐二维码
  bindQRCodeImage()

})
</script>
<template>
  <div class="card-body" style="margin-left: 5px;">
    <div class="order-info-item">
      <div class="order-info-title">
        <span></span>
        <div>门店编号</div>
      </div>
      <el-row>
        <el-col :xs="24" :md="24" :lg="24">{{ companyInfo.companyNo }}</el-col>
      </el-row>
    </div>

    <div class="order-info-item">
      <div class="order-info-title">
        <span></span>
        <div>门店名称</div>
      </div>
      <el-row>
        <el-col :xs="24" :md="24" :lg="24">{{ companyInfo.companyName }} </el-col>
      </el-row>
    </div>

    <div class="order-info-item">
      <div class="order-info-title">
        <span></span>
        <div>客服电话</div>
      </div>
      <el-row>
        <!-- <el-col :xs="12" :md="8" :lg="8">名称：{{ companyInfo.contact }} </el-col>
        <el-col :xs="12" :md="8" :lg="8">电话：{{ companyInfo.mobilePhone }}</el-col> -->
        <el-col :xs="24" :md="24" :lg="24"> {{ companyInfo.servicePhone }} </el-col>
      </el-row>
    </div>

    <div class="order-info-item">
      <div class="order-info-title">
        <span></span>
        <div>门店地址</div>
      </div>
      <el-row>
        <el-col :xs="24" :md="24" :lg="24"> {{ companyInfo.address }} </el-col>
      </el-row>
    </div>
    <div class="order-info-item">
      <div class="order-info-title">
        <span></span>
        <div>客服二维码</div>
      </div>
      <el-row>
        <el-col :xs="24" :md="24" :lg="24"> <img :src="companyInfo.customerServiceImageFilePath" class="wx-qrcode" /></el-col>
      </el-row>
    </div>
    <div class="order-info-item">
      <div class="order-info-title">
        <span></span>
        <div>门店二维码</div>
      </div>
      <el-row>
        <el-col :xs="24" :md="24" :lg="24"> <img :src="wxQRCode" class="wx-qrcode" /></el-col>
      </el-row>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.wx-qrcode {
  width: 200px;
  height: 200px;
}
</style>



<style>
.order-title {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-bottom: 30px;
}

.order-title-info {
  margin-left: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;

  .order-type {
    font-size: 16px;
  }

  .order-no {
    font-size: 13px;
    color: #999;
    margin-top: 8px;
  }
}

.order-info-item {
  border-bottom: 1px dashed #eee;
}

.order-info-title {
  width: 100%;
  height: 40px;
  line-height: 40px;
  display: flex;
  align-items: center;
}

.order-info-title span {
  display: inline-block;
  width: 3px;
  height: 15px;
  background-color: #0256ff;
  margin-right: 10px;
}

.order-info-item {
  padding: 15px;
  font-size: 14px;

  .el-row {
    margin-top: 10px;
  }

  .el-col {
    margin-bottom: 18px;
  }
}
</style>
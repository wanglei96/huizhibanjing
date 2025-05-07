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
    companyInfo.value.customerServiceImageFilePath = common.getAdminUrl(companyInfo.value, 'customerServiceImageFilePath');
  })
}

/**
 * 拨打电话
 */
function makeCall() {
  const phoneNumber = companyInfo.value.servicePhone; // 替换为实际的电话号码
  window.location.href = `tel:${phoneNumber}`;
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
  <div class="card-body">
    <div style="text-align: center;">
      <div><img src="@/assets/images/logo.png" style="height: 80px" alt="" /></div>
      <div style="font-size: 28px;font-weight: 600;font-family: SimSun;">慧至半径童书馆线下门店</div>
    </div>
    <div class="company-info">
      <div style="text-align: center;font-size: 24px;font-weight: 600;font-family: SimSun;">{{ companyInfo.companyName
        }}</div>
      <div style="line-height: 30px;">
        门店地址： {{ companyInfo.address }}
      </div>
      <div>
        <span style="font-family: SimSun;font-weight: 600;">
          客服电话：
        </span>
        <span class="phone-link" @click="makeCall()">
          {{ companyInfo.servicePhone }}
        </span>
      </div>
      <div class="wx-qrcode-container">
        <div>
          <div style="text-align: center;font-family: SimSun;font-weight: 600;">扫码咨询</div>
          <img :src="companyInfo.customerServiceImageFilePath" class="wx-qrcode" />
        </div>
        <div>
          <div style="text-align: center;font-family: SimSun;font-weight: 600;">扫码体验</div>
          <img :src="wxQRCode" class="wx-qrcode" />
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.company-info {
  background-color: #5EAC59;
  width: 90%;
  margin: 0 auto;
  padding: 10px;
  border-radius: 20px;
  margin-top: 40px;
  line-height: 80px;

  .phone-link {
    cursor: pointer;
    color: #0b57d0;
  }

  .wx-qrcode-container {
    display: flex;
    align-items: center;
    justify-content: space-around;
    flex-wrap: wrap;
  }

  .wx-qrcode {
    width: 160px;
    height: 160px;
  }
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
<script setup>
import imgUrl from '@/assets/images/icons/order.png'

const activeName = ref('first')
</script>
<template>
  <div>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>订单详情</span>
        </div>
      </template>
      <div class="card-body">
        <div class="order-title">
          <img :src="imgUrl" />
          <div class="order-title-info">
            <div class="order-type">普通订单</div>
            <div class="order-no">订单号：cp229706324697415680</div>
          </div>
        </div>
        <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
          <el-tab-pane label="订单信息" name="first">
            <div class="order-info-item">
              <div class="order-info-title">
                <span></span>
                <div>用户信息</div>
              </div>
              <el-row>
                <el-col :xs="12" :md="8" :lg="8"> 用户名称：张三 </el-col>
                <el-col :xs="12" :md="8" :lg="8">绑定电话：17812345678</el-col>
                <el-col :xs="12" :md="8" :lg="8"></el-col>
              </el-row>
            </div>

            <div class="order-info-item">
              <div class="order-info-title">
                <span></span>
                <div>收货信息</div>
              </div>
              <el-row>
                <el-col :xs="12" :md="8" :lg="8"> 收货信息：河北省 秦皇岛市 山海关区 1111 </el-col>
              </el-row>
            </div>

            <div class="order-info-item">
              <div class="order-info-title">
                <span></span>
                <div>订单信息</div>
              </div>
              <el-row>
                <el-col :xs="12" :md="8" :lg="8">创建时间：2023-09-26 20:50:55</el-col>
                <el-col :xs="12" :md="8" :lg="8">商品数量：1</el-col>
                <el-col :xs="12" :md="8" :lg="8">商品总价：158.00</el-col>
                <el-col :xs="12" :md="8" :lg="8">优惠券金额：0.00</el-col>
                <el-col :xs="12" :md="8" :lg="8">积分抵扣：0.00</el-col>
                <el-col :xs="12" :md="8" :lg="8">交付邮费.00</el-col>
                <el-col :xs="12" :md="8" :lg="8">用户等级优惠：0.00</el-col>
                <el-col :xs="12" :md="8" :lg="8">付费会员优惠：0.00</el-col>
                <el-col :xs="12" :md="8" :lg="8">实际支付：0.00</el-col>
              </el-row>
            </div>
          </el-tab-pane>
          <el-tab-pane label="商品信息" name="second">商品信息</el-tab-pane>
          <el-tab-pane label="订单记录" name="third">订单记录</el-tab-pane>
        </el-tabs>
      </div>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
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

<script setup>
import common from '@/js/common'
import { reactive, ref, inject } from 'vue';
import ChangeMemberBorrowBook from './ChangeMemberBorrowBook.vue'
import LateReturnBookRecovery from './LateReturnBookRecovery.vue'
import CancelledMemberBorrowBook from './CancelledMemberBorrowBook.vue'
import SwopMemberBorrowBook from './SwopMemberBorrowBook.vue'

// 声明改变用户所借书籍
const changeMemberBorrowBookRef = ref();
// 声明用户误点还书恢复
const lateReturnBookRecoveryRef = ref();
// 取消用户所借书籍
const cancelledMemberBorrowBookRef = ref();
// 交换用户所借书籍
const swopMemberBorrowBookRef = ref();


</script>
<template>
  <div class="user">
    <ChangeMemberBorrowBook ref="changeMemberBorrowBookRef" />
    <LateReturnBookRecovery ref="lateReturnBookRecoveryRef" />
    <CancelledMemberBorrowBook ref="cancelledMemberBorrowBookRef" />
    <SwopMemberBorrowBook ref="swopMemberBorrowBookRef" />
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>借阅图书调整工具</span>
        </div>
      </template>
      <el-row  :gutter="20" style="margin-bottom: 20px">
        <el-col :xs="24" :sm="12" :md="8" :lg="6">
          <el-card :body-style="{ padding: '0px', background: '#87cefa', height: '120px' }" shadow="hover"
            @click="changeMemberBorrowBookRef.init()">
            <div style="padding: 14px;text-align: center;">
              <h4>更换用户线上所借书籍</h4>
              <div style="font-size: 12px;margin-top: 10px;">
                <span>该功能可以更改使用人员所借阅的书籍（等待出库、等待配送、已送达）</span>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :xs="24" :sm="12" :md="8" :lg="6">
          <el-card :body-style="{ padding: '0px', background: '#87cefa', height: '120px' }" shadow="hover"
            @click="lateReturnBookRecoveryRef.init()">
            <div style="padding: 14px;text-align: center;">
              <h4>用户误点还书恢复</h4>
              <div style="font-size: 12px;margin-top: 10px;">
                <span>该功能可以把会员自己点完还书但是又不想还的书的状态恢复成借阅中</span>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :xs="24" :sm="12" :md="8" :lg="6">
          <el-card :body-style="{ padding: '0px', background: '#87cefa', height: '120px' }" shadow="hover"
          @click="cancelledMemberBorrowBookRef.init()">
            <div style="padding: 14px;text-align: center;">
              <h4>取消用户所借书籍</h4>
              <div style="font-size: 12px;margin-top: 10px;">
                <span>取消使用人员所借书籍：该功能可以取消会员已借图书（等待配送、借阅中等），取消之后图书直接恢复成可借阅状态，请注意将书归位</span>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :xs="24" :sm="12" :md="8" :lg="6">
          <el-card :body-style="{ padding: '0px', background: '#87cefa', height: '120px' }" shadow="hover"
          @click="swopMemberBorrowBookRef.init()">
            <div style="padding: 14px;text-align: center;">
              <h4>两个会员的书互换</h4>
              <div style="font-size: 12px;margin-top: 10px;">
                <span>强制更换两个会员所借阅的书籍，用于处理书号错误。等待入库、等待配送、借阅中、已送达、等状态的书都可以更换</span>
              </div>
            </div>
          </el-card>
        </el-col>
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

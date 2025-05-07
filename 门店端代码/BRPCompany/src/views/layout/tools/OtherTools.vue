<script setup>
import common from '@/js/common'
import { reactive, ref, inject } from 'vue';
//import AddMemberBorrowTime from './AddMemberBorrowTime.vue'
import ScanBookEnabled from './ScanBookEnabled.vue'
//import OneClickMemberReturnBook from './OneClickMemberReturnBook.vue'

// 声明添加会员借阅时长
//const addMemberBorrowTimeRef = ref();
// 扫码快速上架、下架
const scanBookEnabledRef = ref();
// 一件帮助会员还书
//const oneClickMemberReturnBookRef = ref();


</script>
<template>
  <div class="user">
   <!--  <AddMemberBorrowTime ref="addMemberBorrowTimeRef" /> -->
    <ScanBookEnabled ref="scanBookEnabledRef" />
   <!--  <OneClickMemberReturnBook ref="oneClickMemberReturnBookRef" /> -->
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>其他工具</span>
        </div>
      </template>
      <el-row :gutter="20" style="margin-bottom: 20px">
        <!-- <el-col :xs="24" :sm="12" :md="8" :lg="6">
          <el-card :body-style="{ padding: '0px', background: '#87cefa', height: '120px' }" shadow="hover"
            @click="addMemberBorrowTimeRef.init()">
            <div style="padding: 14px;text-align: center;">
              <h4>推荐人增加一个月</h4>
              <div style="font-size: 12px;margin-top: 10px;">
                <span>推荐人和被推荐人各增加30天借阅时长，请填写双方的会员卡号。如果增加的时间不是30天，请在会员卡列表单独增加。</span>
              </div>
            </div>
          </el-card>
        </el-col> -->
        <el-col :xs="24" :sm="12" :md="8" :lg="6">
          <el-card :body-style="{ padding: '0px', background: '#87cefa', height: '120px' }" shadow="hover"
            @click="scanBookEnabledRef.init()">
            <div style="padding: 14px;text-align: center;">
              <h4>扫码快速下架、上架</h4>
              <div style="font-size: 12px;margin-top: 10px;">
                <span>扫书后面自打条码，可以快速上架或者下架图书，该功能可以用于图书的批量处理工作，例如 : 批量更换位置、批量处理等等</span>
              </div>
            </div>
          </el-card>
        </el-col>
        <!-- <el-col :xs="24" :sm="12" :md="8" :lg="6">
          <el-card :body-style="{ padding: '0px', background: '#87cefa', height: '120px' }" shadow="hover"
            @click="oneClickMemberReturnBookRef.init()">
            <div style="padding: 14px;text-align: center;">
              <h4>一键帮助会员点还书</h4>
              <div style="font-size: 12px;margin-top: 10px;">
                <span>通过会员卡号可以将会员名下“借阅中”、“到店借阅”的图书状态一键更改为“等待入库”，方便取书时派单。</span>
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

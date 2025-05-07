<script setup>
import { ref } from 'vue'
import CompanyDetail from './SalesOrderDetail.vue'
import management from '@/js/management'
import config from '@/js/config'
import common from '@/js/common'

//详情页组件引用
const refDetail = ref()
const refSearchForm = ref()

const list = management({
  tableName: 'sales_order',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewSalesOrderList',
  refDetail
})

provide('getPayQrCode', getPayQrCode)

const dialogVisible = ref(false)
const payCodeUrl = ref('')
const payAmount = ref(0.01)

function getPayQrCode(data) {
  payAmount.value = data.amount
  let params = {
    outTradeNo: data.salesOrderNo,
    amount: data.amount,
    //amount: 0.01,
    notifyUrl: config.salesOrderNotifyUrl
  }
  common.call('PCWxPay', params, (res) => {
    let content = res.data.codeUrl
    let qrCodeUrl = config.adminRootUrl + '/api/QRCode?content=' + content
    payCodeUrl.value = qrCodeUrl
    dialogVisible.value = true
  })
}
//修改订单状态
function updateSalesOrderStatus(row){
  let params = {
    id: row.id,
    salesOrderStatusCode: "Finished",
  }
  common.call('SalesOrderFinished', params, (res) => {
    list.loadData()
  })
}
//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(dialogVisible, (newValue, oldValue) => {
  if (!newValue) {
    list.loadData()
  }
})
</script>
<template>
  <div class="basic-list">
    <CompanyDetail ref="refDetail" @reload-data="list.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row :gutter="20" type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="订单编号" prop="salesOrderNo">
              <el-input placeholder="请输入..." v-model="list.parameters.salesOrderNo" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="创建日期" prop="dpSearchCreateTime">
              <el-date-picker v-model="list.parameters.dpSearchCreateTime" type="daterange" start-placeholder="开始日期"
                end-placeholder="结束日期" value-format="YYYY-MM-DD" :validate-event="true" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click="list.query">搜索</el-button>
              <el-button @click="list.resetForm(refSearchForm)">重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card shadow="never">
      <el-alert :title="`已选择 ${list.selectedColumnCount.value} 项`" v-show="list.selectedColumnCount.value > 0"
        type="warning" show-icon />
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="list.openDetailDialog({ mode: 'add' })">
              <el-icon style="margin-right: 3px">
                <Plus />
              </el-icon>
              新建
            </el-button>
            <!-- <el-button type="danger" @click="list.deleteRows">
              <el-icon tyle="margin-right: 3px">
                <Delete />
              </el-icon>
              删除
            </el-button> -->
          </el-col>
          <el-col :span="6"></el-col>
        </el-row>
      </el-form>

      <el-dialog destroy-on-close class="pay-dialog" v-model="dialogVisible" title="请使用微信扫描二维码支付" width="400"
        :close-on-click-modal="false" draggable :show-close="false">
        <div class="pay-code">
          <el-image :src="payCodeUrl" class="avatar">
            <template #error>
              <el-icon class="avatar-uploader-icon">
                <Plus />
              </el-icon>
            </template>
          </el-image>
        </div>
        <div class="pay-amount">金额：{{ payAmount }}</div>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">确认</el-button>
            <el-button @click="dialogVisible = false">取消</el-button>
          </span>
        </template>
      </el-dialog>

      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%" border
        @selection-change="list.selectRow" @sort-change="list.sortChange">
        <el-table-column type="selection" width="55" />
        <el-table-column property="salesOrderNo"  label="订单编号" />
        <el-table-column property="amount" label="支付金额" align="center" />
        <el-table-column property="paymentMethodName" label="付款方式" />
        <el-table-column property="salesOrderTypeName" label="订单类型" />
        <el-table-column property="salesOrderStatusName" label="订单状态" />
        <el-table-column property="companyUserName" label="操作人" />
        <el-table-column property="createTimeText" sortable="custom" label="订货时间" />
        <el-table-column label="操作" align="center" width="200">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="danger" v-if="scope.row.salesOrderStatusCode == 'Paying'"
                @click="getPayQrCode(scope.row)">支付
              </el-link>
              <el-link type="danger" v-if="scope.row.salesOrderStatusCode == 'Delivered'"
                @click="updateSalesOrderStatus(scope.row)">完成
              </el-link>
              <el-link type="warning" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看
              </el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">编辑
              </el-link>
              <el-link type="danger" v-if="scope.row.salesOrderStatusCode == 'Paying'"
                @click="list.deleteRow(scope.row.id)">删除</el-link>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:current-page="list.currentPage.value" v-model:page-size="list.pageSizeComputed.value"
        :page-sizes="list.pageSizes" :page-count="list.pageCount.value" :layout="list.layout"
        :total="list.recordCount.value" style="margin-top: 20px" />
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.search-card {
  margin-bottom: 15px;

  .el-form-item {
    margin-bottom: 0px;
  }
}

.image-slot {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 100px;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 30px;
}
</style>
<style lang="scss" scoped>
.avatar-uploader .avatar {
  width: 300px;
  height: 300px;
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

:deep(.pay-dialog .el-dialog__body) {
  .pay-code {
    display: flex;
    align-items: center;
    justify-content: center;

    .el-image {
      width: 200px;
    }
  }

  .pay-amount {
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 20px;
    color: red;
    text-align: center;
  }
}

:deep(.pay-dialog .el-dialog__header) {
  text-align: center;
}
</style>
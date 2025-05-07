<script setup>
import { ref } from 'vue';
import CompanyDetail from './SalesOrderDetail.vue';
import management from '@/js/management';
import common from "@/js/common";
import dayjs from "dayjs";
//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();

const list = management({
  tableName: 'sales_order',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewSalesOrderList',
  refDetail
})

/**
 * 发货
 */
const deliveringOrder = (row) => {
  ElMessageBox.prompt('填写物流信息', '发货', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    inputType: 'textarea',
  })
    .then(({ value }) => {
      updateSalesOrderDelivered(row, value);
    })
    .catch(() => {
    })
}

function updateSalesOrderDelivered(row, value) {
  let params = {
    id: row.id,
    logisticsInfo: value,
    salesOrderStatusCode: "Delivered",
    deliveredTime: dayjs().format('YYYY-MM-DD HH:mm:ss')
  }
  common.call('UpdateSalesOrder', params, (result) => {
    ElMessage.success('发货成功');
    list.loadData();
  })
}

</script>
<template>
  <div class="basic-list">
    <CompanyDetail ref="refDetail" @reload-data="list.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="门店编号" prop="companyNo">
              <el-input placeholder="请输入..." v-model="list.parameters.companyNo"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="门店名称" prop="companyName">
              <el-input placeholder="请输入..." v-model="list.parameters.companyName"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="订单号" prop="salesOrderNo">
              <el-input placeholder="请输入..." v-model="list.parameters.salesOrderNo"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click='list.query'>搜索</el-button>
              <el-button @click='list.resetForm(refSearchForm)'>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card shadow="never">
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%" border
        @selection-change="list.selectRow" @sort-change="list.sortChange">
        <el-table-column property="companyNo" sortable="custom" label="门店编号" width="180" />
        <el-table-column property="companyName" label="门店名称" />
        <el-table-column property="salesOrderNo" sortable="custom" label="订单编号" />
        <el-table-column property="amount" sortable="custom" label="支付金额" />
        <el-table-column property="paymentMethodName" label="付款方式" />
        <el-table-column property="isPaidName" label="是否支付成功" />
        <el-table-column property="salesOrderTypeName" label="订单类型" />
        <el-table-column property="salesOrderStatusName" label="订单状态" />
        <el-table-column property="companyUserName" label="操作人" />
        <el-table-column property="createTimeText" sortable="custom" label="订货时间" />
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="danger" v-if="scope.row.salesOrderStatusCode == 'Delivering'"
                @click="deliveringOrder(scope.row)">发货</el-link>
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
.el-row {
  row-gap: 10px;
}

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
<style>
/* 自定义输入文本框大小 */
.el-message-box__input textarea {
  width: 400px;
  /* 设置宽度 */
  height: 100px;
  /* 设置高度 */
  resize: both;
  /* 允许用户调整大小 */
}
</style>

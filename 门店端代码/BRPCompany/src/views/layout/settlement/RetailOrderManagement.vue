<script setup>
import { ref } from 'vue';
import RetailOrderDetail from './RetailOrderDetail.vue';
import management from '@/js/management';
import DatabaseSelect from "@/components/extension/DatabaseSelect.vue";
import common from '@/js/common'
//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();

const list = management({
  tableName: 'retail_order',
  orderBy: 'business_date desc',
  getListServiceName: 'GetViewRetailOrderList',
  refDetail
})

getSummaries();
const totalAmount = ref(0);
//获取合计金额
function getSummaries() {
  let params = { ...list.parameters };
  for (let key in params) {
    if (key.indexOf('dpSearch') !== -1) {
      let parameterName = getParameterName(key, 'dpSearch'.length);
      if (params[key]) {
        params[parameterName + 'Begin'] = params[key][0];
        params[parameterName + 'End'] = params[key][1];
      }
    }
  }
  params.fields = "ifnull(sum(amount),0) as total_amount"
  params.retailOrderStatusCode = 'Paid'
  params.companyId = common.getCompanyId()
  common.call('GetViewRetailOrderList', params, (res) => {
    let totalAmountInfo = res.data.dataList[0].totalAmount
    totalAmount.value = totalAmountInfo.toFixed(2)
  })
}
function getParameterName(searchControlId, prefixLength) {
  let parameterName = searchControlId.substring(prefixLength);
  parameterName = parameterName.substring(0, 1).toLowerCase()
    + parameterName.substring(1);
  return parameterName;
}
function search() {
  list.query();
  getSummaries();
}

</script>
<template>
  <div class="basic-list">
    <RetailOrderDetail ref="refDetail" @reload-data="list.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="会员名称" prop="memberName">
              <el-input placeholder="请输入..." v-model="list.parameters.memberName" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="零售单类型" prop="retailOrderTypeCode">
              <DatabaseSelect v-model="list.parameters.retailOrderTypeCode" table-name="retail_order_type" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="零售单状态" prop="retailOrderStatusCode">
              <DatabaseSelect v-model="list.parameters.retailOrderStatusCode" table-name="retail_order_status" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="交易日期" prop="dpSearchBusinessDate">
              <el-date-picker v-model="list.parameters.dpSearchBusinessDate" type="daterange" start-placeholder="开始日期"
                end-placeholder="结束日期" value-format="YYYY-MM-DD" :validate-event="true" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click='search()'>搜索</el-button>
              <el-button @click='list.resetForm(refSearchForm)'>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card shadow="never">
      <template #header>
        <div slot="header" style="color: red;">已支付金额合计：{{ totalAmount }}元</div>
      </template>
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%" 
      @selection-change="list.selectRow" @sort-change="list.sortChange">
        <el-table-column property="memberName" label="会员名称" />
        <el-table-column property="payTimeText" label="支付时间" sortable="custom"/>
        <el-table-column property="amount" label="支付金额" sortable="custom"/>
        <el-table-column property="retailOrderTypeName" label="交易类型" />
        <el-table-column property="retailOrderStatusName" label="交易状态" />
        <el-table-column property="isAvailable" label="是否结算">
          <template #default="scope">
            <span v-if="scope.row.settled === true">是</span>
            <span v-else>否</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看
              </el-link>
              <el-link type="danger" v-if="scope.row.retailOrderStatusCode != 'Paid'"
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

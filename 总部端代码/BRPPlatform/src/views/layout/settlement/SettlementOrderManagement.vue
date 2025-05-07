<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import common from '@/js/common'

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();

const list = management({
  tableName: 'settlement_order',
  orderBy: 'business_date desc',
  getListServiceName: 'GetViewSettlementOrderList',
  refDetail
})

function settling(row) {
  let params = {
    id: row.id,
    retailOrderIds: row.retailOrderIds
  }
  common.call("SettlementOrderSettling", params, (res) => {
    ElMessage({ message: "结算成功", type: 'success' });
    list.loadData();
  })
}
function batchSettling() {
  if (list.primaryKeyValueArray.value.length == 0) {
    ElMessage({ message: "请先选择结算单！", type: 'warning' })
    return;
  }
  let params = {
    ids: list.primaryKeyValueArray.value,
  };
  common.call("BatchSettlementOrderSettling", params, (res) => {
    ElMessage({ message: "结算成功", type: 'success' });
    list.loadData();
  })
}

function checkSelectable(row) {
  return row.settlementOrderStatusCode === 'Settling'
}

</script>
<template>
  <div class="basic-list">
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
            <el-form-item label="结算日期" prop="dpSearchBusinessDate">
              <el-date-picker v-model="list.parameters.dpSearchBusinessDate" type="daterange" start-placeholder="开始日期"
                end-placeholder="结束日期" value-format="YYYY-MM-DD" :validate-event="true" />
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
      <el-alert :title="`已选择 ${list.selectedColumnCount.value} 项`" v-show="list.selectedColumnCount.value > 0"
        type="warning" show-icon />
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="batchSettling"> 批量结算</el-button>
          </el-col>
          <el-col :span="6">
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%"
        @selection-change="list.selectRow">
        <el-table-column type="selection" width="55" :selectable="checkSelectable" />
        <el-table-column property="companyNo" label="门店编号" />
        <el-table-column property="companyName" label="门店名称" />
        <el-table-column property="businessDateText" label="结算日期" />
        <el-table-column property="beginDateText" label="开始日期" />
        <el-table-column property="endDateText" label="结束日期" />
        <!-- <el-table-column property="ratio" label="扣点比率" /> -->
        <el-table-column property="amount" label="总金额" />
        <el-table-column property="platformAmount" label="平台金额" />
        <el-table-column property="companyAmount" label="门店金额" />
        <el-table-column property="settlementOrderStatusName" label="结算状态" />
        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id" v-if="scope.row.settlementOrderStatusCode == 'Settling'"
                @click="settling(scope.row)">结算
              </el-link>
              <!-- <el-link type="danger" @click="list.deleteRow(scope.row.id)">删除</el-link> -->
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

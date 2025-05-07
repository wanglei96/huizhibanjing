<script setup>
import { ref } from 'vue';
import management from '@/js/management';

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();

const list = management({
  tableName: 'settlement_order',
  orderBy: 'business_date desc',
  getListServiceName: 'GetViewSettlementOrderList',
  refDetail
})

</script>
<template>
  <div class="basic-list">
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row  type="flex" :gutter="20">
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
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%" @selection-change="list.selectRow">
        <el-table-column property="businessDateText" label="结算日期" />
        <el-table-column property="beginDateText" label="开始日期" />
        <el-table-column property="endDateText" label="结束日期" />
        <!-- <el-table-column property="ratio" label="扣点比率" /> -->
        <el-table-column property="amount" label="总金额" />
        <el-table-column property="companyAmount" label="结算金额" />
        <el-table-column property="settlementOrderStatusName" label="结算状态" />
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

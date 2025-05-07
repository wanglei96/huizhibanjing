<script setup>
import { ref } from 'vue'
import management from '@/js/management'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import dayjs from "dayjs";
import common from '@/js/common'
import util from '@/js/util'

//详情页组件引用
const refDetail = ref()
const refSearchForm = ref()
const refRefundOrderDetail = ref()

const list = management({
  orderBy: 'total_borrow_count desc',
  getListServiceName: 'GetMemberBorrowBookTotal',
  refDetail,
  pageSizes: [10, 20, 30, 50, 100]
})

function exportDataList() {

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
  params.exportName = 'MemberBorrowBookTotal'
  params.pageIndex = 0
  params.pageSize = 1000000
  params.orderBy = 'total_borrow_count desc'
  params.companyId = localStorage.getItem('companyId')
  common.call('Export', params, (res) => {
    let url = res.adminRootUrl + res.data.filePath
    window.open(url)
  })
}
function getParameterName(searchControlId, prefixLength) {
  let parameterName = searchControlId.substring(prefixLength);
  parameterName = parameterName.substring(0, 1).toLowerCase()
    + parameterName.substring(1);
  return parameterName;
}

function formatBusinessDate(row, column, cellValue, index) {
  if (cellValue) {
    //return dayjs(cellValue).format('YYYY-MM-DD')
    return util.jsonToDateTimeString(cellValue);
  }
  return "";
}

</script>
<template>
  <div class="basic-list">
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row :gutter="20" type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="借阅时间范围" prop="dpSearchCreateTime">
              <el-date-picker v-model="list.parameters.dpSearchCreateTime" type="daterange" start-placeholder="开始时间"
                end-placeholder="结束时间" value-format="YYYY-MM-DD" :validate-event="true" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="会员卡号" prop="cardNo">
              <el-input placeholder="请输入..." v-model="list.parameters.cardNo" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="会员名称" prop="memberName">
              <el-input placeholder="请输入..." v-model="list.parameters.memberName" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="联系方式" prop="mobilePhone">
              <el-input placeholder="请输入..." v-model="list.parameters.mobilePhone" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item>
              <el-button type="primary" @click="list.query">搜索</el-button>
              <el-button @click="list.resetForm(refSearchForm)">重置</el-button>
              <el-button type="success" @click="exportDataList()">导出Excel</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card shadow="never">
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%" border
        @selection-change="list.selectRow" @sort-change="list.sortChange">
        <el-table-column property="memberName" label="会员名称" />
        <el-table-column property="cardNo" label="会员卡号" />
        <el-table-column property="batchName" label="会员卡批次" />
        <el-table-column property="effectiveDate" label="开卡时间" :formatter="formatBusinessDate" sortable="custom"/>
        <el-table-column property="expireDate" label="到期时间" :formatter="formatBusinessDate" sortable="custom"/>
        <el-table-column property="mobilePhone" label="联系方式" />
        <el-table-column property="totalBorrowCount" label="借阅本数" sortable="custom" />
        <el-table-column property="orderBorrowCount" label="借阅次数" sortable="custom" />
        <el-table-column property="totalPrice" label="节省借阅费（元）" sortable="custom" />
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

.el-input {
  width: 200px;
}
</style>
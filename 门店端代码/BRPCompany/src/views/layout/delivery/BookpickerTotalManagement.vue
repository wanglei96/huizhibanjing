<script setup>
import { ref } from 'vue'
import management from '@/js/management'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import common from "@/js/common";
import dayjs from "dayjs";

//详情页组件引用
const refDetail = ref()
const refSearchForm = ref()

const parameters = reactive({
  deliverTime: [dayjs().startOf('month').format('YYYY-MM-DD'), dayjs().endOf('month').format('YYYY-MM-DD')]
});

const list = management({
  orderBy: 'total_order_count desc,name',
  getListServiceName: 'GetBookpickerTotal',
  refDetail,
  pageSizes: [10, 20, 30, 50, 100],
  getParameters
})

//获取参数
function getParameters() {
  let params = {}
  Object.assign(params, parameters)
  if (parameters.deliverTime) {
    params.deliverTimeBegin = parameters.deliverTime[0];
    params.deliverTimeEnd = parameters.deliverTime[1];
  }
  return params
}

function exportDataList() {
  let params = getParameters()
  params.exportName = 'BookpickerTotal'
  params.pageIndex = 0
  params.pageSize = 1000000
  params.orderBy = 'total_order_count desc,name'
  params.companyId = localStorage.getItem('companyId')
  common.call('Export', params, (res) => {
    let url = res.adminRootUrl + res.data.filePath
    window.open(url)
  })
}

getSummaries();
const orderTotal = ref(0);
function getSummaries() {
  let params = getParameters()
  params.companyId = localStorage.getItem('companyId')
  params.isOffline = 0
  params.bookOutOrderStatusCode = 'Found'
  params.fields = "ifnull(count(DISTINCT book_order_id),0) as order_total"
  common.call('GetViewBookOutOrderList', params, (res) => {
    orderTotal.value = res.data.dataList[0].orderTotal
  })
}
function query() {
  list.query()
  getSummaries();
}

//重置搜索表单数
const resetForm = (form) => {
  form.resetFields()
}
</script>
<template>
  <div class="basic-list">
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="parameters">
        <el-row :gutter="20" type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="时间范围" prop="deliverTime">
              <el-date-picker v-model="parameters.deliverTime" type="daterange" start-placeholder="开始时间"
                end-placeholder="结束时间" value-format="YYYY-MM-DD" :validate-event="true" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="拣书员名称" prop="bookpickerName">
              <el-input placeholder="请输入..." v-model="parameters.bookpickerName" @keydown.enter.prevent=query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="配送路线" prop="deliverLineName">
              <el-input placeholder="请输入..." v-model="parameters.deliverLineName" @keydown.enter.prevent=query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item>
              <el-button type="primary" @click="query">搜索</el-button>
              <el-button @click="resetForm(refSearchForm)">重置</el-button>
              <el-button type="success" @click="exportDataList()">导出Excel</el-button>

            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card shadow="never">
      <template #header>
        <div slot="header" style="color: red;">拣书单总数：{{ orderTotal }}</div>
      </template>
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%" border
        @selection-change="list.selectRow" @sort-change="list.sortChange">
        <el-table-column property="name" label="拣书员" />
        <el-table-column property="deliverLineNamesAndCounts" label="配送路线" />
        <el-table-column property="totalOrderCount" label="数量" sortable="custom" />
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
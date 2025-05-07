<script setup>
import { ref } from 'vue'
import management from '@/js/management'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import common from '@/js/common'

//详情页组件引用
const refDetail = ref()
const refSearchForm = ref()
const refRefundOrderDetail = ref()

const list = management({
  orderBy: 'understock_count desc',
  getListServiceName: 'GetBookStocksLogTotal',
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
  params.exportName = 'BookStocksLogTotal'
  params.pageIndex = 0
  params.pageSize = 1000000
  params.orderBy = 'understock_count desc'
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
</script>
<template>
  <div class="basic-list">
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row :gutter="20" type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="时间范围" prop="dpSearchCreateTime">
              <el-date-picker v-model="list.parameters.dpSearchCreateTime" type="daterange" start-placeholder="开始时间"
                end-placeholder="结束时间" value-format="YYYY-MM-DD" :validate-event="true" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="书名" prop="bookName">
              <el-input placeholder="请输入..." v-model="list.parameters.bookName" @keydown.enter.prevent=list.query></el-input>
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
        <el-table-column property="bookName" label="书名" />
        <el-table-column property="bookSeriesName" label="系列" />
        <el-table-column property="author" label="作者" />
        <el-table-column property="press" label="出版社" />
        <el-table-column property="bindingTypeName" label="备注" />
        <el-table-column property="bookCount" label="总册数" sortable="custom" />
        <el-table-column property="understockCount" label="库存不足次数" sortable="custom" />
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
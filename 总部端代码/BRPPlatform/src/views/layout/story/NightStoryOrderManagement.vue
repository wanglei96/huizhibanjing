<script setup>
import { ref } from 'vue';
import CompanyDetail from './NightStoryOrderDetail.vue';
import management from '@/js/management';
import common from "@/js/common";

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();
//会员列表
const companyList = ref([]);

const list = management({
  tableName: 'retail_order',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewRetailOrderList',
  condition: "retail_order_type_code in ('NightStory','StoryVideo')",
  refDetail
})

/* function getCompanyList() {
  common.call('GetCompanyList', {}, (res) => {
    companyList.value = res.data.dataList;
  });
} */

//getCompanyList();
</script>
<template>
  <div class="basic-list">
    <CompanyDetail ref="refDetail" @reload-data="list.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row :gutter="20" type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="门店名称" prop="companyName">
              <el-input placeholder="请输入..." v-model="list.parameters.companyName" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="会员姓名" prop="memberName">
              <el-input placeholder="请输入..." v-model="list.parameters.memberName" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="订阅时间" prop="dpSearchBusinessDate">
              <el-date-picker v-model="list.parameters.dpSearchBusinessDate" type="daterange" start-placeholder="开始时间"
                end-placeholder="结束时间" value-format="YYYY-MM-DD" :validate-event="true" />
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
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%" border
        @selection-change="list.selectRow">
        <el-table-column property="companyName" label="门店名称" />
        <el-table-column property="memberName" label="会员名称" />
        <el-table-column property="nightStoryTypeName" label="订阅类型" />
        <el-table-column property="retailOrderTypeName" label="单据类型" />
        <el-table-column property="businessDateText" label="订阅时间" />
        <el-table-column property="retailOrderStatusName" label="状态" />
        <el-table-column property="payTimeText" label="支付时间" />
        <!-- <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看
              </el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">编辑
              </el-link>
              <el-link type="danger" @click="list.deleteRow(scope.row.id)">删除</el-link>
            </el-space>
          </template>
</el-table-column> -->
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

<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import DatabaseSelect from "@/components/extension/DatabaseSelect.vue";

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();
const refRefundOrderDetail = ref();


const list = management({
  tableName: 'retail_order',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewRetailOrderList',
  condition: "retail_order_type_code in ('NightStory','StoryVideo')",
  refDetail
})


</script>
<template>
  <div class="basic-list">
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row :gutter="20" type="flex">
          <!-- <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="订阅类型" prop="subscribeTypeCode">
              <DatabaseSelect v-model="list.parameters.subscribeTypeCode" table-name="subscribe_type"></DatabaseSelect>
            </el-form-item>
          </el-col> -->
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="订阅时间" prop="dpSearchBusinessDate">
              <el-date-picker v-model="list.parameters.dpSearchBusinessDate" type="daterange" start-placeholder="开始时间"
                end-placeholder="结束时间" value-format="YYYY-MM-DD" :validate-event="true" />
            </el-form-item>
          </el-col>
          <!-- <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="到期时间" prop="dpSearchExpireTime">
              <el-date-picker
                v-model="list.parameters.dpSearchExpireTime"
                type="daterange"
                start-placeholder="开始时间"
                end-placeholder="结束时间"
                value-format="YYYY-MM-DD"
                :validate-event="true"
              />
            </el-form-item>
          </el-col> -->
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
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
        <el-table-column type="selection" width="55" />
        <el-table-column property="memberName" label="会员名称" />
        <el-table-column property="nightStoryTypeName" label="订阅类型" />
        <el-table-column property="retailOrderTypeName" label="单据类型" />
        <el-table-column property="businessDateText" label="订阅时间" />
        <el-table-column property="retailOrderStatusName" label="状态" />
        <el-table-column property="payTimeText" label="支付时间" />
        <!-- <el-table-column label="操作" align="center" width="80">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
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

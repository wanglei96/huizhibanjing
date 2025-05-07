<script setup>
import { ref } from 'vue'
import RefundOrderDetail from './RefundOrderDetail.vue'
import management from '@/js/management'

//详情页组件引用
const refDetail = ref()
const refSearchForm = ref()

const list = management({
  tableName: 'refund_order',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewRefundOrderList',
  refDetail
})
</script>
<template>
  <div class="basic-list">
    <RefundOrderDetail ref="refDetail" @reload-data="list.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row :gutter="20"  type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="会员卡号" prop="memberCardNo">
              <el-input placeholder="请输入..." v-model="list.parameters.memberCardNo" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="会员姓名" prop="memberName">
              <el-input placeholder="请输入..." v-model="list.parameters.memberName" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="订单号" prop="payOrderNo">
              <el-input placeholder="请输入..." v-model="list.parameters.payOrderNo" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="创建日期" prop="dpSearchCreateTime">
              <el-date-picker
                v-model="list.parameters.dpSearchCreateTime"
                type="daterange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="YYYY-MM-DD"
                :validate-event="true"
              />
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
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="list.openDetailDialog({ mode: 'add' })">
              <el-icon style="margin-right: 3px">
                <Plus />
              </el-icon>
              新建
            </el-button>
          </el-col>
          <el-col :span="6"> </el-col>
        </el-row>
      </el-form>
      <el-table
        ref="multipleTableRef"
        :data="list.dataList.value"
        style="width: 100%"
        border
        @selection-change="list.selectRow"
      >
        <el-table-column label="会员信息">
          <template #default="scope">
            {{ scope.row.memberName + '(' + scope.row.memberCardNo + ')' }}
          </template>
        </el-table-column>
        <el-table-column property="payOrderNo" label="订单号" />
        <el-table-column property="refundAmount" label="退款金额" />
        <el-table-column property="refundTypeName" label="退款项目" />
        <el-table-column property="refunderName" label="退款人" />
        <el-table-column property="operatorName" label="操作人" />
        <el-table-column property="createTimeText" label="创建时间" />
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link
                type="warning"
                :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })"
                >查看
              </el-link>
              <el-link
                type="primary"
                :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })"
                >编辑
              </el-link>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        v-model:current-page="list.currentPage.value"
        v-model:page-size="list.pageSizeComputed.value"
        :page-sizes="list.pageSizes"
        :page-count="list.pageCount.value"
        :layout="list.layout"
        :total="list.recordCount.value"
        style="margin-top: 20px"
      />
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
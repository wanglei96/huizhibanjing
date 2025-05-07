<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import PayOrderDetail from './PayOrderDetail.vue'
import management from '@/js/management'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import RefundOrderDetail from '@/views/layout/sales/RefundOrderDetail.vue'

//详情页组件引用
const refDetail = ref()
const refSearchForm = ref()
const refRefundOrderDetail = ref()

const router = useRouter()

const list = management({
  tableName: 'pay_order',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewPayOrderList',
  refDetail
})
const paymentItemList = ref([])
;(() => {
  common.call('GetTable', { tableName: 'payment_item', pageSize: 99999 }, (res) => {
    paymentItemList.value = res.data
  })
})()
const isRefundList = [
  {
    code: 1,
    name: '是'
  },
  {
    code: 0,
    name: '否'
  }
]

function toRefundOrder() {
  router.push('RefundOrderManagement')
}

function exportDataList() {
  let data = list.parameters
  data.companyId = localStorage.getItem('companyId')
  data.exportName = 'PayOrder'
  common.call('Export', data, (res) => {
    let url = res.adminRootUrl + res.data.filePath
    window.open(url)
  })
}
</script>
<template>
  <div class="basic-list">
    <PayOrderDetail ref="refDetail" @reload-data="list.loadData" />
    <RefundOrderDetail ref="refRefundOrderDetail" @reload-data="toRefundOrder" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row :gutter="20" type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="会员卡号" prop="memberCardNo">
              <el-input placeholder="请输入..." v-model="list.parameters.memberCardNo" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="会员姓名" prop="memberName">
              <el-input placeholder="请输入..." v-model="list.parameters.memberName" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="账单号" prop="payOrderNo">
              <el-input placeholder="请输入..." v-model="list.parameters.payOrderNo" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="支付时间" prop="dpSearchPayTime">
              <el-date-picker
                v-model="list.parameters.dpSearchPayTime"
                type="daterange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="YYYY-MM-DD"
                :validate-event="true"
              />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="付款项目" prop="paymentItemCode">
              <DatabaseSelect v-model="list.parameters.paymentItemCode" table-name="payment_item" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="是否退款" prop="isRefund">
              <el-select
                v-model="list.parameters.isRefund"
                class="m-2"
                placeholder="请选择..."
                clearable  filterable
              >
                <el-option
                  v-for="item in isRefundList"
                  :key="item.code"
                  :label="item.name"
                  :value="item.code"
                />
              </el-select>
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
        <el-table-column property="payOrderNo" label="账单编号" />
        <el-table-column property="amount" label="支付金额" />
        <el-table-column property="paymentMethodName" label="付款方式" />
        <el-table-column property="paymentItemName" label="付款项目" />
        <el-table-column property="payTimeText" label="支付时间" />
        <!-- <el-table-column property="payOrderTypeName" label="账单类型" /> -->
        <el-table-column property="operatorName" label="操作人" />
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

.el-input {
  width: 200px;
}
</style>
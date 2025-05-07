<script setup>
import { ref } from 'vue';
import BookOrderDetail from './BookOrderDetail.vue';
import management from '@/js/management';
import common from '@/js/common';
import BatchUpdateBookOrder from './BatchUpdateBookOrder.vue'
import dayjs from "dayjs";
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();
const refreshList = inject('handleStockOut');
const error = ref(false);;

const batchUpdateBookOrderRef = ref();

const list = management({
  tableName: 'book_order',
  orderBy: 'create_time desc',
  getListServiceName: 'GetBookOrderInfoList',
  refDetail
})
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
)
function addStockOuting(id) {

  refreshList();
}
/* function customerProcessing(row) {
  let params = {
    id: row.id,
    borrowCount: row.borrowCount,
    returnCount: row.returnCount
  }
  common.call('CustomerProcessingBookOrder', params, (res) => {
    list.loadData();
  })
} */
//是否显示编辑按钮
function isEdit(row) {
  return !row.isOffline && (row.bookOrderBorrowStatusCode != 'Received' &&
    (row.bookOrderReturnStatusCode != 'Sterilizing' && row.bookOrderReturnStatusCode != 'Finished'));
}

function lockMember(row) {
  let params = {
    id: row.memberCardId,
    memberCardStatusCode: row.memberCardStatusCode === 'Locked' ? 'Normal' : 'Locked',
    lockDate: dayjs().format('YYYY-MM-DD HH:mm:ss')
  }
  common.call("UpdateMemberCard", params, (res) => {
    list.loadData();
    ElMessage({ message: "操作成功", type: 'success' });
  })
}

const errorChange = (val) => {
  if (val) {
    list.parameters.condition = "error_remark is not null"
  }else{
    list.parameters.condition = ""
  }
  list.loadData();
}

</script>

<template>
  <div class="basic-list">
    <BookOrderDetail ref="refDetail" @reload-data="list.loadData" />
    <BatchUpdateBookOrder ref="batchUpdateBookOrderRef" @reload-data="list.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters" label-width="80px">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="订单编号" prop="bookOrderNo">
              <el-input placeholder="请输入..." v-model="list.parameters.bookOrderNo"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="卡号" prop="memberCardNo">
              <el-input placeholder="请输入..." v-model="list.parameters.memberCardNo"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="姓名" prop="memberName">
              <el-input placeholder="请输入..." v-model="list.parameters.memberName"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="电话" prop="memberMobilePhone">
              <el-input placeholder="请输入..." v-model="list.parameters.memberMobilePhone"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="单据时间" prop="dpSearchCreateTime">
              <el-date-picker v-model="list.parameters.dpSearchCreateTime" type="daterange" start-placeholder="开始时间"
                end-placeholder="结束时间" value-format="YYYY-MM-DD" :validate-event="true" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="配送时间" prop="dpSearchDeliverTime">
              <el-date-picker v-model="list.parameters.dpSearchDeliverTime" type="daterange" start-placeholder="开始时间"
                end-placeholder="结束时间" value-format="YYYY-MM-DD" :validate-event="true" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="借阅状态" prop="bookOrderBorrowStatusCode">
              <DatabaseSelect table-name="book_order_borrow_status"
                condition="code in ('Submitting', 'StockOuting', 'Delivering', 'Received')"
                v-model="list.parameters.bookOrderBorrowStatusCode" placeholder="请选择" class="form-item-width" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="还书状态" prop="bookOrderReturnStatusCode">
              <DatabaseSelect table-name="book_order_return_status"
                condition="code in ('Submitting', 'Retrieving', 'Sterilizing', 'Finished','OfflineReturned')"
                v-model="list.parameters.bookOrderReturnStatusCode" placeholder="请选择" class="form-item-width" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="类型" prop="isOffline">
              <el-select placeholder="请选择" v-model="list.parameters.isOffline" filterable clearable>
                <el-option label="线下" value="1"></el-option>
                <el-option label="线上" value="0"></el-option>
              </el-select>
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
          <el-col :span="2">
            <el-button type="primary" @click="batchUpdateBookOrderRef.init(list.primaryKeyValueArray.value)">
              批量修改</el-button>
          </el-col>
          <el-col :span="2">
            <el-checkbox v-model="error" label="异常订单" @change="errorChange" border />
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%"
        @selection-change="list.selectRow">
        <el-table-column type="selection" width="55" :selectable="isEdit" />
        <el-table-column property="memberInfo" label="会员信息" align="center">
          <template #default="scope">
            <div>
              <span style="color: #337ab7;">{{ scope.row.memberName }}</span>
            </div>
            <div>
              <span style="color: #337ab7;">{{ scope.row.memberCardNo }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="bookOrderNo" label="订单编号" align="center" />
        <el-table-column property="" label="时间" align="center">

          <template #default="scope">
            <div>
              <span style="color: red;">下单：</span>
              <span>{{ scope.row.createTimeText }}</span>
            </div>
            <div v-if="!scope.row.isOffline">
              <span style="color: green;">配送：</span>
              <span>{{ scope.row.deliverTimeText }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="deliverTimeText" label="类型" align="center">

          <template #default="scope">
            <div>
              <span :style="{ color: scope.row.isOffline ? 'red' : 'blue' }">{{ scope.row.isOffline ? '线下' : '线上'
                }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="quantity" label="数量" align="center">

          <template #default="scope">
            <div>
              <span style="color: green;">借：{{ scope.row.borrowCount }}</span>
            </div>
            <div>
              <span style="color: red;">还：{{ scope.row.returnCount }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="" label="状态">

          <template #default="scope">
            <div style="color: green;">借：{{ scope.row.bookOrderBorrowStatusName }}</div>
            <div style="color: #ff0000;">还：{{ scope.row.bookOrderReturnStatusName }}</div>
          </template>
        </el-table-column>
        <el-table-column property="errorRemark" label="异常原因" align="center" />
        <el-table-column label="操作" align="center">

          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <!-- <el-link
                v-if="scope.row.bookOrderBorrowStatusCode == 'Approving' || scope.row.bookOrderReturnStatusCode == 'Approving'"
                type="primary" :id="scope.row.id" @click="customerProcessing(scope.row)">客服处理</el-link> -->
              <el-link type="danger" @click="lockMember(scope.row)"
                :style="{ color: scope.row.memberCardStatusCode === 'Locked' ? 'blue' : 'red' }">
                {{ scope.row.memberCardStatusCode === 'Locked' ? '解锁会员' : '锁定会员' }}</el-link>
              <el-link v-if="scope.row.bookOrderBorrowStatusCode == 'StockOuting'" type="primary" :id="scope.row.id"
                @click="addStockOuting(scope.row.id)">找书出库</el-link>
              <el-link type="warning" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })"
                v-if="isEdit(scope.row)">编辑
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

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.el-form-item {
  margin-bottom: 0px;
}

.user-search-card {
  margin-bottom: 15px;

  .user-form {
    .el-form-item {
      margin-bottom: 10px;
    }
  }
}

.task {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .task_desc {
    color: #999;
    font-size: 14px;
    margin-bottom: 5px;
  }

  .task_title {
    font-size: 22px;
  }
}

.list-item-title_3 {
  color: #337ab7;
}

.el-row {
  row-gap: 10px;
}
</style>
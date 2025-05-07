<script setup>
import common from '@/js/common'
import util from '@/js/util'
import { reactive, ref, inject } from 'vue';
import dayjs from "dayjs";
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

//当前页
const pageIndex = ref(0)
//总记录数
const recordCount = ref(0)
//总页数
const pageCount = ref(0)
//分页大小列表
const pageSizes = reactive([10, 50, 100, 200])
//每页大小
const pageSize = ref(pageSizes[0] || 10)

const deliveryOrderList = ref([]);

const refSearchForm = ref();
const parameters = reactive({});

const loading = ref(false)
const tableContainer = ref(null);

getDeliveryOrderInfoList();
//共计单数
const orderCount = ref(0);
function getDeliveryOrderInfoList(options) {
  let params = {
    "companyId": companyId.value,
    //"isOffline": 0,
    "pageSize": pageSize.value,
    "pageIndex": pageIndex.value,
    "orderBy": "create_time desc",
  }
  Object.assign(params, parameters)
  /* params.condition = "deliver_status_code='Storageing' or " +
    " (book_order_return_status_code='Submitting'" +
    "AND NOT EXISTS (SELECT 1 FROM view_book_order_item i WHERE i.book_order_id = view_book_order_item.book_order_id AND book_order_type_code = 'Borrow'))" */
  params.condition = "deliver_status_code='Storageing'";
  if (parameters.createTime) {
    params.createTimeBegin = parameters.createTime[0];
    params.createTimeEnd = parameters.createTime[1];
  }
  //loading.value = true;
  common.call("GetOldDeliveryOrderInfoList", params, (res) => {
    //deliveryOrderList.value.push(...res.data.dataList)
    deliveryOrderList.value = res.data.dataList
    orderCount.value = res.data.recordCount;
    recordCount.value = res.data.recordCount;
    pageCount.value = res.data.pageCount;
    //loading.value = false;
  })
}

function handleScroll() {
  const container = tableContainer.value;
  const bottom = Math.abs(container.scrollHeight - (container.scrollTop + container.clientHeight)) < 1;
  if (bottom) {
    pageIndex.value++
    getDeliveryOrderInfoList();
  }
}


//当前页
const currentPage = computed({
  get() {
    return pageIndex.value + 1
  },
  set(newValue) {
    pageIndex.value = newValue - 1
    getDeliveryOrderInfoList();
  }
})
//页面大小
const pageSizeComputed = computed({
  get() {
    return pageSize.value
  },
  set(newValue) {
    pageIndex.value = 0
    pageSize.value = newValue
    getDeliveryOrderInfoList();
  }
})

function formatBusinessDate(row, column, cellValue, index) {
  if (cellValue) {
    //return dayjs(cellValue).format('YYYY-MM-DD')
    return util.jsonToDateTimeString(cellValue);
  }
  return "";
}





function lockMember(item) {
  let params = {
    id: item.memberCardId,
    memberCardStatusCode: item.memberCardStatusCode === 'Locked' ? 'Normal' : 'Locked',
    lockDate: dayjs().format('YYYY-MM-DD HH:mm:ss')
  }
  common.call("UpdateMemberCard", params, (res) => {
    getDeliveryOrderInfoList();
    ElMessage({ message: "操作成功", type: 'success' });
  })
}

function confirmInStocks(row) {
  let params = {
    bookOrderItemId: row.id,
  }
  common.call("ConfirmInStocks", params, (res) => {
    getDeliveryOrderInfoList();
    ElMessage({ message: "操作成功", type: 'success' });
  })
}


//查询
function query() {
  pageIndex.value = 0
  deliveryOrderList.value = []
  getDeliveryOrderInfoList();
}
//重置搜索表单数
const resetForm = (form) => {
  form.resetFields()
}

</script>
<template>
  <div class="user">
    <el-card shadow="never" class="user-search-card">
      <template #header>
        <div class="card-header">
          <span>全部等待入库单</span>
        </div>
      </template>
      <el-form ref="refSearchForm" :model="parameters" label-width="80px">
        <el-row type="flex" :gutter="20">
          <el-form-item label="配送大区" prop="deliverAreaId">
            <DatabaseSelect table-name="deliver_area" :condition="'company_id=' + companyId"
              v-model="parameters.deliverAreaId" valueFieldName="id" placeholder="请选择" />
          </el-form-item>
          <el-form-item label="配送路线" prop="deliverLineId">
            <DatabaseSelect table-name="view_deliver_line" :condition="'company_id=' + companyId"
              v-model="parameters.deliverLineId" valueFieldName="id" placeholder="请选择" />
          </el-form-item>
          <el-form-item label="配送点" prop="deliverPlaceId">
            <DatabaseSelect table-name="view_deliver_place" :condition="'company_id=' + companyId"
              v-model="parameters.deliverPlaceId" valueFieldName="id" placeholder="请选择" />
          </el-form-item>
          <el-form-item label="会员名称" prop="memberName">
            <el-input placeholder="请输入..." v-model="parameters.memberName" @keydown.enter.prevent=query></el-input>
          </el-form-item>
          <el-form-item label="时间范围" prop="createTime">
            <el-date-picker v-model="parameters.createTime" type="daterange" value-format="YYYY-MM-DD"
              start-placeholder="开始日期" end-placeholder="结束日期" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click='query'>搜索</el-button>
            <el-button @click='resetForm(refSearchForm)'>重置</el-button>
          </el-form-item>
        </el-row>
      </el-form>
      <div style="margin-top: 10px;">
        <span>共计：</span>
        <span style="color: red;margin-right: 5px;">{{ orderCount }}</span>
        <span>单</span>
      </div>
    </el-card>
    <el-card v-for="item in deliveryOrderList" style="margin-top: 20px;">
      <div style="margin-top: 5px;">
        <span style="font-size: 18px;color: #337ab7;">{{ item.deliverAreaName }}</span>
        <span style="font-size: 18px;color: #337ab7;">，{{ item.memberName }}</span>
        <span style="font-size: 18px;color: #337ab7;">({{ item.memberCardNo }})</span>
        <span style="font-size: 18px;color: #337ab7;">，{{ item.memberMobilePhone }}</span>
        <span style="font-size: 18px;">，送{{ item.sendCount }}本</span>
        <span style="font-size: 18px;">，取{{ item.fetchCount }}本</span>
        <span style="font-size: 18px;color: #0c8415;">{{ item.delivererName }}</span>
      </div>
      <div style="font-size: 14px;margin-top: 5px;">
        <span style="color: #0000ff;margin-right: 10px;">{{ item.memberEntranceGuard ? "[" +
          item.memberEntranceGuard
          +
          "]" : "" }}</span>
        <span>{{ item.memberAddress }}</span>
      </div>
      <div style="font-size: 14px;margin-top: 5px;">
        <el-space spacer="|" style="color: #999">
          <el-link type="danger" @click="lockMember(item)"
            :style="{ color: item.memberCardStatusCode === 'Locked' ? 'blue' : 'red' }">
            {{ item.memberCardStatusCode === 'Locked' ? '解锁会员' : '锁定会员' }}</el-link>
        </el-space>
      </div>
      <el-table ref="multipleTableRef" :data="item.children" style="width: 100%">
        <el-table-column property="bookName" label="书名" width="400">
          <template #default="scope">
            <div>
              <span style="color: #008000;">{{ scope.row.bookSeriesName ? "[" + scope.row.bookSeriesName + "]" : ""
              }}</span>
              <span style="color: #337ab7;">{{ scope.row.bookName }}</span>
              <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
              }}</span>
            </div>
            <div class="list-item-title_3" style="color: red;">{{ scope.row.bookcaseName }} </div>
            <div class="list-item-title_3">书号:{{ scope.row.bookSn }} </div>
          </template>
        </el-table-column>
        <el-table-column property="bookOrderTypeName" label="类型" />
        <el-table-column property="createTime" label="时间" :formatter="formatBusinessDate" />
        <el-table-column property="deliverStatusName" label="状态" />
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="danger" v-if="scope.row.deliverStatusCode == 'Storageing'"
                @click="confirmInStocks(scope.row)">确认入库</el-link>
              <el-link type="primary" v-else disabled="true">确认入库</el-link>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSizeComputed" :page-sizes="pageSizes"
      :page-count="pageCount" :layout="'total, sizes, prev, pager, next, jumper'" :total="recordCount"
      style="margin-top: 20px" />
  </div>
</template>

<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.el-select {
  width: 200px;
}

.user {
  .user-search-card {
    margin-bottom: 15px;

    .user-form {
      .el-form-item {
        margin-bottom: 3px;
      }
    }
  }

  .table-footer {
    display: flex;
    justify-content: center;
    width: 100%;
    text-align: center;
    margin-top: 20px;
  }
}

.infolist {
  display: flex;
  height: 28px;
  line-height: 28px;
  justify-content: flex-start;
  align-items: center;
  font-size: 14px;

  span {
    display: inline-block;
    margin-right: 6px;
  }
}
</style>
<style>
.el-table .borrow-row {
  background-color: #d9edf7 !important;
}

/* .el-table .return-row {
  background-color: lightcoral !important;
} */

.table-container {
  height: 1000px;
  /* 设置合适的高度以允许滚动 */
  overflow-y: auto;
}

.loading {
  text-align: center;
  padding: 10px;
}
</style>
<script setup>
import { reactive, ref, inject } from 'vue';
import dayjs from "dayjs";
import util from '@/js/util'
import common from '@/js/common'
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


const bookOutOrderList = ref([]);
const refSearchForm = ref();
const parameters = reactive({
  deliverTime: [dayjs().format('YYYY-MM-DD'), dayjs().format('YYYY-MM-DD')]
});
const statusList = reactive([{ code: 'AllOrder', name: '全部订单' }, { code: 'NotFound', name: '待处理订单' }]);
getBookOutOrderInfoList();
function getBookOutOrderInfoList() {
  let params = {
    "companyId": companyId.value,
    "isOffline": 0,
    "pageSize": pageSize.value,
    "pageIndex": pageIndex.value,
    "orderBy": "deliver_area_id,deliver_area_number"
  }
  let condition = "";
  Object.assign(params, parameters)
  if (parameters.deliverTime) {
    params.deliverTimeBegin = parameters.deliverTime[0];
    params.deliverTimeEnd = parameters.deliverTime[1];
  } else {
    condition = "DATE(deliver_time) = CURDATE()";
  }
  if (parameters.statusCode === 'NotFound') {
    params.bookOutOrderStatusCode = "NotFound"
  }
  if (parameters.statusCode === 'AllOrder') {
    condition = ""
  }
  params.condition = condition
  common.call("GetBookOutOrderInfoList", params, (res) => {
    bookOutOrderList.value = res.data.dataList;
    /* orderCount.value = res.data.dataList.length;
    notOrderCount.value = 0;
    res.data.dataList.forEach(item => {
      const childrenNotFound = item.children.some(child => child.bookOutOrderStatusCode === 'NotFound');
      if (childrenNotFound) {
        notOrderCount.value++;
      }
    }); */
    recordCount.value = res.data.recordCount
    pageCount.value = res.data.pageCount
  })
}

//当前页
const currentPage = computed({
  get() {
    return pageIndex.value + 1
  },
  set(newValue) {
    pageIndex.value = newValue - 1
    getBookOutOrderInfoList();
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
    getBookOutOrderInfoList();
  }
})


//查询
function query() {
  getBookOutOrderInfoList();
  getBookOutOrderAreaTotal();
}
//重置搜索表单数
const resetForm = (form) => {
  form.resetFields()
}
function formatBusinessDate(row, column, cellValue, index) {
  if (cellValue) {
    //return dayjs(cellValue).format('YYYY-MM-DD')
    return util.jsonToDateTimeString(cellValue);
  }
  return "";
}
function foundBook(row) {
  ElMessageBox.confirm(
    '是否找到图书，确认出库!',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      bookOutOrderOutStock(row);
    })
    .catch(() => {
    })
}
function bookOutOrderOutStock(row) {
  let params = {
    bookOrderId: row.bookOrderId,
    bookOutOrderId: row.id,
    bookOrderItemId: row.bookOrderItemId,
    bookId: row.bookId
  }
  common.call("BookOutOrderOutStock", params, (res) => {
    getBookOutOrderInfoList();
    ElMessage({ message: "出库成功", type: 'success' });
  })
}
const rowClassName = ({ row }) => {
  if (row.bookOutOrderStatusCode === 'NotFound') {
    return 'notFound-row'; // 定义名为 active-row 的类名
  } else {
    return ''; // 默认情况下不设置额外类名
  }
};

//获取大区单数
const bookOutOrderAreaTotal = ref({});
getBookOutOrderAreaTotal();

function getBookOutOrderAreaTotal() {
  let params = {
    "companyId": companyId.value,
    "isOffline": 0,
    "pageSize": pageSize.value,
    "pageIndex": pageIndex.value,
    "orderBy": "deliver_area_id,deliver_area_number,display_order"
  }
  let condition = "";
  Object.assign(params, parameters)
  if (parameters.deliverTime) {
    params.deliverTimeBegin = parameters.deliverTime[0];
    params.deliverTimeEnd = parameters.deliverTime[1];
  } else {
    condition = "DATE(deliver_time) = CURDATE()";
  }
  if (parameters.statusCode === 'NotFound') {
    params.bookOutOrderStatusCode = "NotFound"
  }
  if (parameters.statusCode === 'AllOrder') {
    condition = ""
  }
  params.condition = condition
  common.call("GetBookOutOrderAreaTotal", params, (res) => {
    bookOutOrderAreaTotal.value = res.data;
  })
}


function move(moveDirection, row) {
  var data = new Object()
  data.primaryKey = row.bookOrderId
  data.primaryKeyName = "id"
  data.tableName = "book_order"
  data.moveDirection = moveDirection
  data.condition = "DATE(deliver_time) = '" + row.deliverTimeText + "'"
  common.call('Move', data, function (result) {
    getBookOutOrderInfoList();
  })
}
</script>
<template>
  <div class="user">
    <el-card shadow="never" class="user-search-card">
      <template #header>
        <div class="card-header">
          <span>今日全部配送单（找单）</span>
        </div>
      </template>
      <el-form label-width="80px" ref="refSearchForm" :model="parameters">
        <el-row type="flex" :gutter="20">
          <el-form-item label="配送大区" prop="deliverAreaId">
            <DatabaseSelect table-name="deliver_area" :condition="'company_id=' + companyId"
              v-model="parameters.deliverAreaId" valueFieldName="id" placeholder="请选择配送大区" />
          </el-form-item>
          <el-form-item label="配送路线" prop="deliverLineId">
            <DatabaseSelect table-name="view_deliver_line" :condition="'company_id=' + companyId"
              v-model="parameters.deliverLineId" valueFieldName="id" placeholder="请选择配送大区" />
          </el-form-item>
          <el-form-item label="配送点" prop="deliverPlaceId">
            <DatabaseSelect table-name="view_deliver_place" :condition="'company_id=' + companyId"
              v-model="parameters.deliverPlaceId" valueFieldName="id" placeholder="请选择配送大区" />
          </el-form-item>
          <el-form-item label="配送员" prop="delivererId">
            <DatabaseSelect table-name="view_company_user"
              :condition="'company_id=' + companyId + ' and is_deliverer =1 '" v-model="parameters.delivererId"
              valueFieldName="id" placeholder="请选择" class="form-item-width" />
          </el-form-item>
          <el-form-item label="拣书员" prop="bookpickerId">
            <DatabaseSelect table-name="view_company_user"
              :condition="'company_id=' + companyId + ' and is_bookpicker =1 '" v-model="parameters.bookpickerId"
              valueFieldName="id" placeholder="请选择" class="form-item-width" />
          </el-form-item>
          <el-form-item label="订单状态" prop="statusCode">
            <el-select class="form-item-width" v-model="parameters.statusCode" placeholder="请选择" filterable>
              <el-option v-for="item in statusList" :key="item.code" :label="item.name" :value="item.code" />
            </el-select>
          </el-form-item>
          <el-form-item label="时间范围" prop="deliverTime">
            <el-date-picker v-model="parameters.deliverTime" type="daterange" value-format="YYYY-MM-DD"
              start-placeholder="开始日期" end-placeholder="结束日期" />
          </el-form-item>
          <el-form-item label="会员名称" prop="memberName">
            <el-input placeholder="请输入..." v-model="parameters.memberName" @keydown.enter.prevent=query></el-input>
          </el-form-item>
          <!--  <el-checkbox label="仅显示待处理订单" border style="margin-left: 20px;"
            @change="value => getBookOutOrderInfoList({ type: 'statusCode', value: value })" />
          <el-checkbox label="全部订单" border
            @change="value => getBookOutOrderInfoList({ type: 'allOrder', value: value })" /> -->
          <el-form-item>
            <el-button type="primary" @click='query'>搜索</el-button>
            <el-button @click='resetForm(refSearchForm)'>重置</el-button>
          </el-form-item>
        </el-row>
      </el-form>
      <div>
        <el-space spacer="|" style="color: #999" wrap>
          <div style="color: black;">
            <span>共计：</span><span>{{ bookOutOrderAreaTotal.totalCount }}</span><span>单</span>
          </div>
          <div style="color: red;">
            <span>待处理：</span><span>{{ bookOutOrderAreaTotal.notFoundCount }}</span><span>单</span>
          </div>
          <div style="color: green;">
            <span>找书时一定要核对书号！</span>
          </div>
        </el-space>
      </div>
      <div style="margin-top: 20px;">
        <el-space spacer="|" style="color: #999">
          <div v-for="item in bookOutOrderAreaTotal.areaList">
            <span style="color: red;">{{ item.deliverAreaName }}</span>
            <span style="color: red;">：{{ item.count }}单</span>
          </div>
        </el-space>
      </div>
    </el-card>
    <el-card v-for="item in bookOutOrderList" style="margin-top: 20px;">
      <div>
        <span style="font-size: 24px;color: red;">{{ item.deliverAreaName }}</span>
        <!--  <span style="font-size: 24px;color: red;">，{{ item.deliverLineName }}</span> -->
        <span style="font-size: 24px;">，送{{ item.children.length }}本</span>
      </div>
      <!-- <div>
        <el-space spacer="|" style="color: #999" wrap>
          <el-link type="danger" @click="move('MoveUp', item)">上移</el-link>
          <el-link type="primary" @click="move('MoveDown', item)">下移</el-link>
        </el-space>
      </div> -->
      <div style="display: flex;justify-content:space-between;align-items: center;">
        <div style="margin-top: 5px;">
          <span style="font-size: 14px;color: #337ab7;">{{ item.memberName }}（{{ item.memberCardNo }}）</span>
          <span style="font-size: 14px;color: #337ab7;">，{{ item.memberMobilePhone }}</span>
          <span style="font-size: 14px;margin-left: 5px;">{{ item.address }}</span>
        </div>
        <div style="color: red">
          <span>配送时间：{{ item.deliverTimeText }}</span>
        </div>
      </div>
      <el-table ref="multipleTableRef" :data="item.children" style="width: 100%" :row-class-name="rowClassName">
        <el-table-column property="bookName" label="书名" width="500">
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
            <!-- <div>
              <el-link class="list-item-title_3">借阅记录</el-link>
            </div> -->
          </template>
        </el-table-column>
        <el-table-column property="createTime" label="下单时间" :formatter="formatBusinessDate" />
        <el-table-column property="bookOrderBorrowStatusName" label="状态" />
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="danger" v-if="scope.row.bookOutOrderStatusCode == 'NotFound'"
                @click="foundBook(scope.row)">找到点我</el-link>
              <el-link type="primary" v-if="scope.row.bookOutOrderStatusCode == 'Found'" disabled="true">{{
        scope.row.bookOutOrderStatusName }}</el-link>
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

/* .el-table {
  --el-table-tr-bg-color: #f2dede;
} */
</style>
<style>
.el-table .notFound-row {
  background-color: #f2dede !important;
}

/* .el-table .return-row {
  background-color: lightcoral !important;
} */
</style>

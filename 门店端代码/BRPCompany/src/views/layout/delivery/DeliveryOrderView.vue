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
const parameters = reactive({
  deliverTime: [dayjs().format('YYYY-MM-DD'), dayjs().format('YYYY-MM-DD')]
});
const statusList = reactive([
  { code: 'AllOrder', name: '全部订单' },
  { code: 'StockOuting', name: '待拣书' },
  { code: 'NotDisposeOrder', name: '待处理订单' },
  { code: 'ErrorProcessed', name: '异常订单' }
]);

getDeliveryOrderInfoList();
function getDeliveryOrderInfoList(options) {
  let params = {
    "companyId": companyId.value,
    "isOffline": 0,
    "pageSize": pageSize.value,
    "pageIndex": pageIndex.value,
    //"fields": "",
    //"orderBy": "deliver_area_id,deliver_line_id,deliver_place_display_order,deliver_place_id,deliver_area_number,create_time desc"
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
  //condition = "book_order_borrow_status_code IN ('Delivering','Received','ErrorProcessing')";
  if (parameters.statusCode === 'NotDisposeOrder') {
    condition = "book_order_borrow_status_code='Delivering'"
  }
  if (parameters.statusCode === 'AllOrder') {
    condition = "";
  }
  if (parameters.statusCode === 'ErrorProcessed') {
    condition = "error_remark is not null";
  }
  if (parameters.statusCode === 'StockOuting') {
    condition = "book_order_borrow_status_code='Submitting' OR book_order_borrow_status_code='StockOuting'";
  }
  params.condition = condition
  common.call("GetDeliveryOrderInfoList", params, (res) => {
    deliveryOrderList.value = res.data.dataList;
    recordCount.value = res.data.recordCount;
    pageCount.value = res.data.pageCount;
  })
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


/**
 * 一键配送
 * @param {*} bookOrderId 
 */
function oneClickDelivery(bookOrderId) {
  ElMessageBox.confirm(
    '确认全部配送！',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      allDelivery(bookOrderId);
    })
    .catch(() => {
    })
}


function allDelivery(bookOrderId) {
  let params = {
    bookOrderId: bookOrderId,
  }
  common.call("DeliverBookOrderReceived", params, (res) => {
    getDeliveryOrderInfoList();
    ElMessage({ message: "一键配送成功", type: 'success' });
  })
}

/**
 * 今日不送
 * @param {*} bookOrderId 
 */
function todayNotDelivery(bookOrderId) {
  ElMessageBox.confirm(
    '确认今日不配送，订单将延后至明天配送！',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      updateDeliveryOrderDeliveryTime(bookOrderId);
    })
    .catch(() => {
    })
}


function updateDeliveryOrderDeliveryTime(bookOrderId) {
  let params = {
    id: bookOrderId,
    deliverTime: dayjs().add(1, 'day').format('YYYY-MM-DD')
  }
  common.call("UpdateBookOrder", params, (res) => {
    getDeliveryOrderInfoList();
    ElMessage({ message: "操作成功", type: 'success' });
  })
}

const rowClassName = ({ row }) => {
  if (row.bookOrderTypeCode === 'Borrow') {
    return 'borrow-row'; 
  } else if (row.bookOrderTypeCode === 'Return') {
    return 'return-row'; 
  } else {
    return ''; 
  }
};

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
/**
 * 今日配送
 * @param {*} item 
 */
function todayDelivery(item) {
  ElMessageBox.confirm(
    '确认今日配送，订单将改为今日配送！',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      let params = {
        id: item.bookOrderId,
        deliverTime: dayjs().format('YYYY-MM-DD')
      }
      common.call("UpdateBookOrder", params, (res) => {
        getDeliveryOrderInfoList();
        ElMessage({ message: "操作成功", type: 'success' });
      })
    })
    .catch(() => {
    })


}

function isTodayDelivery(deliverTime) {
  const currentTime = dayjs().format('YYYY-MM-DD');
  const deliveryTime = util.jsonToDateTimeString(deliverTime);
  // 判断 deliverTime 是否大于当前时间
  return dayjs(deliveryTime).format('YYYY-MM-DD') > currentTime;
}
//是否显示一键配送
function isOneClickDelivery(item) {
  return item.bookOrderBorrowStatusCode === 'Delivering' 
  //||item.bookOrderBorrowStatusCode === 'ErrorProcessing'
}
//是否显示修改配送时间
/* function isUpdateDeliverTime(item){
  const currentTime = dayjs().format('YYYY-MM-DD');
  const deliveryTime = util.jsonToDateTimeString(item.deliverTime);
  item.children
} */

function confirmDelivery(row) {
  let params = {
    bookOrderItemId: row.id,
    bookOrderTypeCode: row.bookOrderTypeCode
  }
  common.call("ConfirmDelivery", params, (res) => {
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


//修改配送时间
const newDeliverTime = ref();

function updateDeliverTime(bookOrderId) {
  if (!newDeliverTime) {
    ElMessage({ message: "请先选择配送时间", type: 'warning' })
    return;
  }
  ElMessageBox.confirm(
    '确认将配送时间改为：' + newDeliverTime.value,
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      let params = {
        id: bookOrderId,
        deliverTime: newDeliverTime.value
      }
      common.call("UpdateBookOrder", params, (res) => {
        getDeliveryOrderInfoList();
        ElMessage({ message: "操作成功", type: 'success' });
      })
    })
    .catch(() => {
    })
}

//查询
function query() {
  getDeliveryOrderInfoList();
  getDeliverOrderAreaTotal();

}
//重置搜索表单数
const resetForm = (form) => {
  form.resetFields()
}

function getCardClass(item) {
  if (item.bookOrderBorrowStatusCode === 'Delivering') {
    return 'card-delivering';
  } else if (item.errorRemark) {
    return 'card-errorProcessing';
  } else if (item.bookOrderBorrowStatusCode === 'Submitting' || item.bookOrderBorrowStatusCode === 'StockOuting') {
    return 'card-stockOuting';
  } else {
    return '';
  }
}

function move(moveDirection, row) {
  var data = new Object()
  data.primaryKey = row.bookOrderId
  data.primaryKeyName = "id"
  data.tableName = "book_order"
  data.moveDirection = moveDirection
  data.condition = "company_id = " + companyId.value + " AND DATE(deliver_time) = '" + row.deliverTimeText + "'"
  common.call('Move', data, function (result) {
    getDeliveryOrderInfoList();
  })
}


//获取大区单数
const deliverOrderAreaTotal = ref({});
getDeliverOrderAreaTotal();

function getDeliverOrderAreaTotal() {
  let params = {
    "companyId": companyId.value,
  }
  let condition = "";
  Object.assign(params, parameters)
  if (parameters.deliverTime) {
    params.deliverTimeBegin = parameters.deliverTime[0];
    params.deliverTimeEnd = parameters.deliverTime[1];
  } else {
    condition = "DATE(deliver_time) = CURDATE()";
  }
  if (parameters.statusCode === 'NotDisposeOrder') {
    condition = "book_order_borrow_status_code='Delivering'"
  }
  if (parameters.statusCode === 'AllOrder') {
    condition = "";
  }
  if (parameters.statusCode === 'ErrorProcessed') {
    condition = "error_remark is not null";
  }
  if (parameters.statusCode === 'StockOuting') {
    condition = "book_order_borrow_status_code='Submitting' OR book_order_borrow_status_code='StockOuting'";
  }
  params.condition = condition
  common.call("GetDeliverOrderAreaTotal", params, (res) => {
    deliverOrderAreaTotal.value = res.data;
  })
}
</script>

<template>
  <div class="user">
    <el-card shadow="never" class="user-search-card">
      <template #header>
        <div class="card-header">
          <span>今日全部配送单（操作模式）</span>
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
          <el-form-item label="配送员" prop="delivererId">
            <DatabaseSelect table-name="view_company_user"
              :condition="'company_id=' + companyId + ' and is_deliverer =1 '" v-model="parameters.delivererId"
              valueFieldName="id" placeholder="请选择" class="form-item-width" />
          </el-form-item>
          <el-form-item label="订单状态" prop="statusCode">
            <el-select class="form-item-width" v-model="parameters.statusCode" placeholder="请选择" filterable>
              <el-option v-for="item in statusList" :key="item.code" :label="item.name" :value="item.code" />
            </el-select>
          </el-form-item>
          <el-form-item label="时间范围" prop="deliverTime" style="margin-right: 10px;">
            <el-date-picker v-model="parameters.deliverTime" type="daterange" value-format="YYYY-MM-DD"
              start-placeholder="开始日期" end-placeholder="结束日期" />
          </el-form-item>
          <el-form-item label="会员名称" prop="memberName">
            <el-input placeholder="请输入..." v-model="parameters.memberName" @keydown.enter.prevent=query></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click='query'>搜索</el-button>
            <el-button @click='resetForm(refSearchForm)'>重置</el-button>
          </el-form-item>
        </el-row>
      </el-form>
      <div style="margin-top: 10px;">
        <el-space spacer="|" style="color: #999" wrap>
          <div style="color: black;">
            <span>共计：</span><span>{{ deliverOrderAreaTotal.totalCount }}</span><span>单</span>
          </div>
          <div style="color: red;">
            <span>待拣书：</span><span>{{ deliverOrderAreaTotal.stockOutingCount }}</span><span>单</span>
          </div>
          <div style="color: red;">
            <span>待处理：</span><span>{{ deliverOrderAreaTotal.deliveringCount }}</span><span>单</span>
          </div>
          <div style="color: green;">
            <span>异常单：</span><span>{{ deliverOrderAreaTotal.errorProcessingCount }}</span><span>单</span>
          </div>
        </el-space>
      </div>
      <div style="margin-top: 20px;">
        <el-space spacer="|" style="color: #999">
          <div v-for="item in deliverOrderAreaTotal.areaList">
            <span style="color: red;">{{ item.deliverAreaName }}</span>
            <span style="color: red;">：{{ item.count }}单</span>
          </div>
        </el-space>
      </div>
    </el-card>
    <el-card v-for="item in deliveryOrderList" style="margin-top: 20px;" :class="getCardClass(item)">
      <!-- <template #header> -->
      <div style="margin-top: 5px;display: flex;justify-content:space-between;align-items: center;">
        <div>
          <span style="font-size: 18px;color: #0c8415;">{{ item.deliverAreaName }}</span>
          <span style="font-size: 18px;color: #337ab7;">，{{ item.memberName }}</span>
          <span style="font-size: 18px;color: #337ab7;">({{ item.memberCardNo }})</span>
          <span style="font-size: 18px;color: #337ab7;">，{{ item.memberMobilePhone }}</span>
          <span style="font-size: 18px;">，送{{ item.sendCount }}本</span>
          <span style="font-size: 18px;">，取{{ item.fetchCount }}本</span>
          <span style="font-size: 18px;color: #0c8415;">{{ item.delivererName }}</span>
          <span style="font-size: 18px;color: red;">{{ item.bookOrderBorrowStatusCode === 'ErrorProcessing' ? "(" +
        item.bookOrderBorrowStatusName + ")" : "" }}</span>
          <span style="font-size: 18px;color: red;">{{ item.errorRemark ? "异常信息：(" +
        item.errorRemark + ")" : "" }}</span>
        </div>
        <div style="color: red"
          v-if="item.bookOrderBorrowStatusCode === 'Submitting' || item.bookOrderBorrowStatusCode === 'StockOuting'">
          <span>待拣书</span>
        </div>
      </div>
      <div style="display: flex;justify-content:space-between;align-items: center;">
        <div style="font-size: 14px;margin-top: 5px;">
          <span style="color: #0000ff;margin-right: 10px;">{{ item.memberEntranceGuard ? "[" +
        item.memberEntranceGuard
        +
        "]" : "" }}</span>
          <span>{{ item.memberAddress }}</span>
        </div>
        <div style="color: red">
          <span>配送时间：{{ item.deliverTimeText }}</span>
        </div>
      </div>
      <div style="font-size: 14px;margin: 5px 0 ;">
        <el-space spacer="|" style="color: #999">
          <el-link type="danger" @click="oneClickDelivery(item.bookOrderId)"
            v-if="isOneClickDelivery(item)">一键配送</el-link>
          <!-- <el-link type="danger" @click="todayNotDelivery(item.bookOrderId)"
            v-if="isOneClickDelivery(item)">今日不送</el-link>
          <el-link type="danger" @click="todayDelivery(item)" v-if="isTodayDelivery(item.deliverTime)">今日配送</el-link> -->
          <el-link type="danger" @click="lockMember(item)"
            :style="{ color: item.memberCardStatusCode === 'Locked' ? 'blue' : 'red' }">
            {{ item.memberCardStatusCode === 'Locked' ? '解锁会员' : '锁定会员' }}</el-link>
          <!-- <el-link type="danger" @click="updateDeliverTime(item.bookOrderId)">修改配送时间</el-link>
          <el-date-picker v-model="newDeliverTime" type="datetime" value-format="YYYY-MM-DD hh:mm:ss"
            placeholder="请选择配送时间" style="width: 200px" :disabled-date="disabledDate" /> -->
          <!-- <div>
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="danger" @click="move('MoveUp', item)">上移</el-link>
              <el-link type="primary" @click="move('MoveDown', item)">下移</el-link>
            </el-space>
          </div> -->
        </el-space>
      </div>
      <!-- </template> -->

      <el-table ref="multipleTableRef" :data="item.children" style="width: 100%" :row-class-name="rowClassName">
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
            <div class="list-item-title_3">书号：{{ scope.row.bookSn }} </div>
          </template>
        </el-table-column>
        <el-table-column property="bookOrderTypeName" label="类型" />
        <el-table-column property="createTime" label="下单时间" :formatter="formatBusinessDate" />
        <el-table-column property="deliverStatusName" label="状态">
          <template #default="scope">
            <span>{{ scope.row.deliverStatusCode === 'Delivering' && scope.row.bookOrderTypeCode == 'Return' ? '等待取书'
        : scope.row.deliverStatusName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="180">

          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="danger"
                v-if="scope.row.deliverStatusCode == 'Delivering' && scope.row.bookOrderTypeCode == 'Borrow'"
                @click="confirmDelivery(scope.row)">确认配送</el-link>
              <el-link type="danger"
                v-if="scope.row.deliverStatusCode == 'Delivering' && scope.row.bookOrderTypeCode == 'Return'"
                @click="confirmDelivery(scope.row)">确认取书</el-link>
              <el-link type="danger"
                v-if="scope.row.deliverStatusCode == 'Storageing' && scope.row.bookOrderTypeCode == 'Return'"
                @click="confirmInStocks(scope.row)">确认入库</el-link>
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
.card-delivering {
  background-color: #f2dede;
}

.card-errorProcessing {
  background-color: rgb(210, 224, 210);
}

.card-stockOuting {
  background-color: rgba(223, 227, 223, 0.918);
}
</style>
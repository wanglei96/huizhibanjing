<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import common from '@/js/common'
import { useRoute } from 'vue-router';
import util from '@/js/util'

const route = useRoute();
const bookId = ref(route.query['bookId']);

// 获取当前地址
const url = new URL(window.location.href);

// 删除指定的参数
url.searchParams.delete('bookId');

// 更新浏览器地址栏
window.history.replaceState({}, '', url.toString());

const dayList = reactive([
  { code: 7, name: '7天' },
  { code: 15, name: '15天' },
  { code: 30, name: '30天以上' }
]);
const typeParameters = reactive({});
const refTypeSearchForm = ref();

const typeList = reactive([
  { code: 'Book', name: '归还的书籍' },
  { code: 'Member', name: '借书的会员' }
]);

const refSearchForm = ref();
const isShowDatalist = ref(true)
const isShowBooklist = ref(false)
const isShowMemberlist = ref(false)

const list = management({
  tableName: 'book_order_item',
  orderBy: 'id desc',
  //condition: bookId ? 'book_id=' + bookId : '',
  parameters: {
    bookOrderTypeCode: 'Borrow',
    bookId: bookId.value,
    //fields:"id,is_returned,create_time,book_in_stocks_time,book_return_time,delivered_time,deliver_time,book_sn"
  },
  getListServiceName: 'GetBookBorrowLogList'
})
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
)
/* let booklist = reactive({})
let memberlist = reactive({}) */
const booklist = ref([]);
const memberlist = ref([]);
function search() {
  if (!typeParameters.dayCode) {
    ElMessage({ message: "请先选择天数", type: 'warning' })
    return;
  }
  if (!typeParameters.typeCode) {
    ElMessage({ message: "请先选择操作类型", type: 'warning' })
    return;
  }
  if (typeParameters.typeCode === 'Book') {
    booklist.value = management({
      tableName: 'book_order_item',
      condition: "is_returned is not true AND DATE(create_time) <= CURDATE() - INTERVAL " + typeParameters.dayCode + " DAY",
      orderBy: 'id desc',
      getListServiceName: 'GetViewBookOrderItemList',
      parameters: {
        bookOrderTypeCode: 'Borrow'
      }
    })
    isShowBooklist.value = true
    isShowDatalist.value = false
    isShowMemberlist.value = false
  } else {
    memberlist.value = management({
      tableName: 'member_card',
      condition: "member_id is not null AND disabled IS NOT TRUE AND member_id  not in (SELECT DISTINCT member_id " +
        " FROM book_order WHERE create_time >= CURRENT_DATE() - INTERVAL " + typeParameters.dayCode + " DAY)",
      orderBy: 'id desc',
      getListServiceName: 'GetViewMemberCardList',
      parameters: {
        fields: "*,(SELECT MAX(create_time) FROM book_order WHERE view_member_card.member_id = book_order.member_id ) AS last_time",
      }
    })
    isShowMemberlist.value = true
    isShowBooklist.value = false
    isShowDatalist.value = false
  }
}

/* function search() {
  let params = {}
  params.companyId = companyId.value;
  params.condition = "member_id is not null AND disabled IS NOT TRUE AND member_id  not in (SELECT DISTINCT member_id " +
    " FROM book_order WHERE create_time >= CURRENT_DATE() - INTERVAL " + typeParameters.dayCode + " DAY)"
  let getMethod = "GetViewMemberList"
  if (typeParameters.typeCode === 'Book') {
    params.condition = "is_returned is not true AND DATE(create_time) <= CURDATE() - INTERVAL " + typeParameters.dayCode + " DAY"
    getMethod = "GetViewBookOrderItemList"
  }
  getDataList(params, getMethod)
}
function getDataList(params, getMethod) {
  common.call(getMethod, params, function (res) {
    if (typeParameters.typeCode === 'Book') {
      booklist = res.data.dataList
      isShowBooklist.value = true
    } else {
      memberlist = res.data.dataList
      isShowMemberlist.value = true
    }
  })
} */
function logResetForm(refSearchForm) {
  list.resetForm(refSearchForm)
  list.parameters.bookId = ''
  bookId.value = ''
}
//重置搜索表单数
const resetForm = (form) => {
  form.resetFields()
}
function query() {
  list.query()
  isShowDatalist.value = true
  isShowMemberlist.value = false
  isShowBooklist.value = false
}

</script>
<template>
  <div class="basic-list">
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="会员卡号" prop="memberCardNo">
              <el-input placeholder="请输入..." v-model="list.parameters.memberCardNo"
                @keydown.enter.prevent=query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="会员姓名" prop="memberName">
              <el-input placeholder="请输入..." v-model="list.parameters.memberName"
                @keydown.enter.prevent=query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="0" :sm="0" :md="0" :lg="0">
            <el-form-item prop="bookId" style="display: none;">
              <el-input v-model="list.parameters.bookId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书名" prop="bookName">
              <el-input placeholder="请输入..." v-model="list.parameters.bookName" @keydown.enter.prevent=query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书号" prop="bookSn">
              <el-input placeholder="请输入..." v-model="list.parameters.bookSn" @keydown.enter.prevent=query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click='query'>搜索</el-button>
              <el-button @click='logResetForm(refSearchForm)'>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-form ref="refTypeSearchForm" :model="typeParameters" style="margin-top: 10px;">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="超过" prop="dayCode">
              <el-select class="form-item-width" v-model="typeParameters.dayCode" placeholder="请选择" filterable>
                <el-option v-for="item in dayList" :key="item.code" :label="item.name" :value="item.code" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="未" prop="typeCode">
              <el-select class="form-item-width" v-model="typeParameters.typeCode" placeholder="请选择" filterable>
                <el-option v-for="item in typeList" :key="item.code" :label="item.name" :value="item.code" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click='search'>搜索</el-button>
              <el-button @click='resetForm(refTypeSearchForm)'>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card shadow="never" v-if="isShowDatalist">
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%"
        @selection-change="list.selectRow">
        <el-table-column property="memberInfo" label="姓名">
          <template #default="scope">
            <div>
              <span style="color: #337ab7;">{{ scope.row.memberName }}</span>
            </div>
            <div>
              <span style="color: #337ab7;">{{ scope.row.memberCardNo }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="bookSN" label="书号">
          <template #default="scope">
            <div>
              <span style="color: #337ab7;">{{ scope.row.bookSn }}</span>
            </div>
            <div>
              <!-- <span style="color: red;">云库</span> -->
            </div>
          </template>
        </el-table-column>
        <el-table-column property="" label="书名">
          <template #default="scope">
            <div>
              <span>{{ scope.row.bookName }}</span>
              <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
                }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="" label="状态" align="center">
          <template #default="scope">
            <div :style="{ color: scope.row.isReturned ? 'block' : 'green' }">{{ scope.row.isReturned ? "已还" : "未还" }}
            </div>
            <div :style="{ color: scope.row.isOffline ? 'red' : 'blue' }">
              {{ scope.row.isOffline ? '线下馆' : '线上' }}
            </div>
          </template>
        </el-table-column>
        <el-table-column property="" label="时间">
          <template #default="scope">
            <div>
              <span style="color: red;">借阅：</span>
              <span>{{ scope.row.createTimeText }}</span>
            </div>
            <div>
              <span style="color: green;">配送：</span>
              <span>{{ scope.row.deliverTimeText }}</span>
            </div>
            <div>
              <span style="color: green;">送达：</span>
              <span>{{ scope.row.deliveredTimeText }}</span>
            </div>
            <div>
              <span style="color: green;">还书：</span>
              <span>{{ scope.row.bookReturnTimeText }}</span>
            </div>
            <div>
              <span style="color: green;">入库：</span>
              <span>{{ scope.row.bookInStocksTimeText }}</span>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:current-page="list.currentPage.value" v-model:page-size="list.pageSizeComputed.value"
        :page-sizes="list.pageSizes" :page-count="list.pageCount.value" :layout="list.layout"
        :total="list.recordCount.value" style="margin-top: 20px" />
    </el-card>


    <el-card shadow="never" v-if="isShowBooklist">
      <el-table ref="multipleTableRef" :data="booklist.dataList" style="width: 100%">
        <el-table-column property="" label="书名">
          <template #default="scope">
            <div>
              <span style="color: #008000;">{{ scope.row.bookSeriesName ? "[" + scope.row.bookSeriesName + "]" : ""
                }}</span>
              <span>{{ scope.row.bookName }}</span>
              <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
                }}</span>
            </div>
            <div>
              <span>书号：{{ scope.row.bookSn }}</span>
              <span style="margin-left: 5px;">柜号：{{ scope.row.bookcaseName }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="" label="会员">
          <template #default="scope">
            <div>{{ scope.row.memberName }}：{{ scope.row.memberCardNo }}</div>
            <div>联系方式：{{ scope.row.memberMobilePhone }}</div>
            <div>到期时间：{{ scope.row.expireDateText }}</div>
          </template>
        </el-table-column>
        <el-table-column property="" label="状态" align="center">
          <template #default="scope">
            <div>{{ scope.row.borrowStatusName }}
            </div>
            <div :style="{ color: scope.row.isOffline ? 'red' : 'blue' }">
              {{ scope.row.isOffline ? '线下馆' : '线上' }}
            </div>
          </template>
        </el-table-column>
        <el-table-column property="" label="时间">
          <template #default="scope">
            <div>
              <span style="color: red;">借阅：</span>
              <span>{{ scope.row.createTimeText }}</span>
            </div>
            <div>
              <span style="color: green;">还书：</span>
              <span>{{ scope.row.bookReturnTimeText }}</span>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:current-page="booklist.currentPage" v-model:page-size="booklist.pageSizeComputed"
        :page-sizes="booklist.pageSizes" :page-count="booklist.pageCount" :layout="booklist.layout"
        :total="booklist.recordCount" style="margin-top: 20px" />
    </el-card>


    <el-card shadow="never" v-if="isShowMemberlist">
      <el-table ref="multipleTableRef" :data="memberlist.dataList" style="width: 100%">
        <el-table-column property="" label="会员">
          <template #default="scope">
            <div>{{ scope.row.memberName }}</div>
            <div>{{ scope.row.memberCardNo }}</div>
          </template>
        </el-table-column>
        <el-table-column property="batchName" label="会员卡批次" />
        <el-table-column property="memberCardTypeName" label="会员卡类型" />
        <el-table-column property="" label="时间">
          <template #default="scope">
            <div>
              <span style="color: red;">开卡时间：</span>
              <span>{{ scope.row.effectiveDateText }}</span>
            </div>
            <div>
              <span style="color: green;">到期时间：</span>
              <span>{{ scope.row.expireDateText }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="lastTimeText" label="最后一次借阅时间" />
      </el-table>
      <el-pagination v-model:current-page="memberlist.currentPage" v-model:page-size="memberlist.pageSizeComputed"
        :page-sizes="memberlist.pageSizes" :page-count="memberlist.pageCount" :layout="memberlist.layout"
        :total="memberlist.recordCount" style="margin-top: 20px" />
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
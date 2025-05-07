<script setup>
import { ref } from 'vue';
import BookDetail from './BookDetail.vue';
import BookUpdateBookcase from './BookUpdateBookcase.vue';
import BookDamageDetail from './BookDamageDetail.vue';
import ExportBookSn from './ExportBookSn.vue';
import BookBorrowStatusView from './BookBorrowStatusView.vue';
import BookCompensationDetail from '../bookOrder/BookCompensationDetail.vue';
import management from '@/js/management';
import common from '@/js/common'
import { useRoute } from 'vue-router';
import dayjs from "dayjs";

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();
const refUpdateBookcase = ref();
const refExportBookSn = ref();
const refBookBorrowStatusView = ref();
//标记损坏
const refBookDamageDetail = ref();
//赔付
const refBookCompensationDetail = ref();

const route = useRoute();

const skuId = ref(route.query.skuId);

// 获取当前地址
const url = new URL(window.location.href);

// 删除指定的参数
url.searchParams.delete('skuId');

// 更新浏览器地址栏
window.history.replaceState({}, '', url.toString());

const list = management({
  tableName: 'book',
  orderBy: 'sn desc',
  getListServiceName: 'GetNewBookInfoList',
  refDetail,
  //condition: skuId.value ? 'book_sku_id=' + skuId.value : '',
  processDataItem(dataItem) {
    if (dataItem.imageFilePath) {
      dataItem.imageFilePath = common.getAdminUrl(dataItem, 'imageFilePath');
    }
    return dataItem;
  },
  parameters: {
    bookSkuId: skuId.value,
  },
})
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
const bookSeriesList = ref([]);
const bookStatusList = ref([]);
const bookcaseList = ref([]);
// 获取系列
getSelected({
  tableName: 'book_series',
  selectedList: bookSeriesList,
  condition: "company_id=" + companyId.value
});
getSelected({
  tableName: 'bookcase',
  selectedList: bookcaseList,
  condition: "company_id=" + companyId.value
});
//获取图书状态
getSelected({
  tableName: 'book_status',
  selectedList: bookStatusList,
});
//获取下拉框
function getSelected(options) {
  let param = {
    "tableName": options.tableName,
    pageSize: 99999
  };
  if (options.condition) {
    param.condition = options.condition;
  }
  common.call("GetTable", param, function (result) {
    options.selectedList.value = result.data;
  })
}
provide('reLoadData', reLoadData);

function reLoadData() {
  list.loadData();
}
/**
 * 批量上下架
 * @param {*} bookStatusCode 
 */
function batchOnOff(bookStatusCode) {
  if (list.primaryKeyValueArray.value.length == 0) {
    ElMessage({ message: "请先选择图书！", type: 'warning' })
    return;
  }
  let param = {
    bookIds: list.primaryKeyValueArray.value,
    bookStatusCode: bookStatusCode
  };
  common.call("BatchOnOffBook", param, function (result) {
    ElMessage({ message: "操作成功", type: 'success' })
    list.loadData();
  })
}

const refBorrowLog = inject('openBorrowLog');
/**
 * 查看借阅记录
 * @param {*} bookId 
 */
function viewBorrowLog(bookId) {
  refBorrowLog(bookId);
}
/**
 * 标记记录
 */
const refBookDamageLog = inject('openBookDamageLog');
function bookDamageLog(bookId) {
  refBookDamageLog(bookId);
}

/**
 * 设置背景色
 */
const rowClassName = ({ row }) => {
  if (row.bookStatusCode === 'On') {
    return 'on-row'; // 定义名为 active-row 的类名
  } else if (row.bookStatusCode === 'Off') {
    return 'off-row'; // 定义名为 inactive-row 的类名
  } else {
    return ''; // 默认情况下不设置额外类名
  }
};
function bookStocksClick(row) {
  list.parameters.bookSkuId = row.bookSkuId
  list.loadPage({
  });
}
function bookSeriesClick(row) {
  list.parameters.bookSeriesId = row.bookSeriesId
  list.loadPage();
}

const companyUserId = ref(
  `${common.getLocalStorage('companyUserId')}`
);
function updateBookStatusCode(row) {
  let params = {
    id: row.id,
    bookStatusCode: row.bookStatusCode === 'On' ? 'Off' : 'On',
  }
  /* if (row.bookStatusCode === 'On') {
    params.offlineTime = dayjs().format('YYYY-MM-DD HH:mm:ss');
    params.offlineApproverId = companyUserId.value;
  } */
  common.call("UpdateBook", params, function (result) {
    list.loadData();
  })
}

function updateBookIsAvailable(row) {
  if (row.borrowStatusCode !== 'In') {
    ElMessage({ message: "图书已借出，暂不能修改可借状态！", type: 'warning' })
    return;
  }
  let params = {
    id: row.id,
    isAvailable: row.isAvailable ? 0 : 1,
  }
  common.call("UpdateBook", params, function (result) {
    list.loadData();
  })
}

function search() {
  list.query();
  list.parameters.sn = ''
}

function resetForm(refSearchForm) {
  list.resetForm(refSearchForm)
  list.parameters.bookSkuId = null
  skuId.value = null;
}


</script>
<template>
  <div class="basic-list">
    <BookDetail ref="refDetail" @reload-data="reLoadData" />
    <BookUpdateBookcase ref="refUpdateBookcase" @reload-data="reLoadData" />
    <ExportBookSn ref="refExportBookSn" @reload-data="reLoadData" />
    <BookDamageDetail ref="refBookDamageDetail" @reload-data="reLoadData" />
    <BookCompensationDetail ref="refBookCompensationDetail" @reload-data="reLoadData" />
    <BookBorrowStatusView ref="refBookBorrowStatusView" @reload-data="reLoadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters" label-width="80px">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书名" prop="bookName">
              <el-input placeholder="请输入..." v-model="list.parameters.bookName"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书号" prop="sn">
              <el-input placeholder="请输入..." v-model="list.parameters.sn" @keydown.enter.prevent=search></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="作者" prop="author">
              <el-input placeholder="请输入..." v-model="list.parameters.author"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="系列" prop="bookSeriesId">
              <el-select placeholder="请选择" v-model="list.parameters.bookSeriesId" filterable clearable>
                <el-option v-for="item in bookSeriesList" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="0" :sm="0" :md="0" :lg="0">
            <el-form-item prop="bookSkuId" style="display: none;">
              <el-input v-model="list.parameters.bookSkuId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书柜" prop="bookcaseId">
              <el-select placeholder="请选择" v-model="list.parameters.bookcaseId" filterable clearable>
                <el-option v-for="item in bookcaseList" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="图书状态" prop="bookStatusCode">
              <el-select placeholder="请选择" v-model="list.parameters.bookStatusCode" filterable clearable>
                <el-option v-for="item in bookStatusList" :key="item.code" :label="item.name" :value="item.code" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="是否有损" prop="condition">
              <el-select placeholder="请选择" v-model="list.parameters.condition" filterable clearable>
                <el-option label="是" value="is_damaged is true"></el-option>
                <el-option label="否" value="is_damaged is not true"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click='search'>搜索</el-button>
              <el-button @click='resetForm(refSearchForm)'>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card shadow="never">
      <el-alert :title="`已选择 ${list.selectedColumnCount.value} 项`" v-show="list.selectedColumnCount.value > 0"
        type="warning" show-icon />
      <div>
        <text>总计</text>
        <text style="color: red;">{{ list.recordCount.value }}</text>
        <text>册图书，当页共计</text>
        <text>{{ list.dataList.value.length }}</text>
        <text>册</text>
      </div>
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="batchOnOff('On')">批量上架</el-button>
            <el-button type="primary" @click="batchOnOff('Off')">批量下架</el-button>
            <el-button type="primary" @click="refExportBookSn.init()">点击展开导出打码页面</el-button>
          </el-col>
          <el-col :span="6">
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%"
        @selection-change="list.selectRow" :row-class-name="rowClassName">
        <el-table-column type="selection" width="55" />
        <el-table-column property="avator" label="封面" width="100" align="center">
          <template #default="scope">
            <el-image style="width: 100px; height: 100px" :src="scope.row.imageFilePath" fit="contain">
              <template #error>
                <div class="image-slot">
                  <el-icon>
                    <Picture />
                  </el-icon>
                </div>
              </template>
            </el-image>
          </template>
        </el-table-column>
        <el-table-column property="bookName" label="书名">
          <template #default="scope">
            <div>
              <span style="color: #008000;">{{ scope.row.bookSeriesName ? "[" + scope.row.bookSeriesName + "]" : ""
                }}</span>
              <span>{{ scope.row.bookName }}</span>
              <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
                }}</span>
            </div>
            <el-space spacer="|" style="color: #999">
              <el-link class="list-item-title_3" @click="bookStocksClick(scope.row)">书库</el-link>
              <el-link class="list-item-title_3" @click="bookSeriesClick(scope.row)"
                :disabled="!scope.row.bookSeriesId">书库（系列）</el-link>
            </el-space>
            <div>
              <el-link class="list-item-title_3"
                @click="refBookBorrowStatusView.init(scope.row.bookSkuId)">云库借阅详情</el-link>
            </div>
            <div>
              <el-link class="list-item-title_3" @click="viewBorrowLog(scope.row.id)">借阅记录</el-link>
            </div>
            <div class="list-item-title_3">书号:{{ scope.row.sn }}
            </div>
          </template>
        </el-table-column>
        <el-table-column property="" label="分类&年龄段&标签">
          <template #default="scope">
            <div>
              <span>{{ scope.row.languageName }}类</span>
              <span style="margin-left: 10px;">{{ scope.row.ageGroupNames }}</span>
            </div>
            <div>
              <span>{{ scope.row.bookTagNames }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="" label="书柜&书号">
          <template #default="scope">
            <div>{{ scope.row.bookcaseName }}</div>
            <div>
              <el-link class="list-item-title_3" @click="refUpdateBookcase.init(scope.row)">修改书柜</el-link>
            </div>
            <div>{{ scope.row.sn }}</div>
            <!--  <div style="color: #ff0000;">云库</div> -->
          </template>
        </el-table-column>
        <el-table-column property="price" label="价格" width="80" />
        <el-table-column property="price" label="借阅状态" width="80">
          <template #default="scope">
            <div>
              <span>{{ scope.row.borrowStatusName }}</span>
              <!-- <span>{{ scope.row.memberName ? '：' : '' }}</span> -->

            </div>
            <div> <span style="color: #0000ff;">{{ scope.row.memberName ? scope.row.memberName : '' }}</span></div>
          </template>
        </el-table-column>
        <el-table-column property="price" label="图书状态" width="80">
          <template #default="scope">
            <el-link type="danger" @click="updateBookStatusCode(scope.row)"
              :style="{ color: scope.row.bookStatusCode == 'On' ? '#008000' : '#ff0000' }">
              {{ scope.row.bookStatusName }}</el-link>
          </template>
        </el-table-column>
        <el-table-column property="price" label="是否可借" width="80">
          <template #default="scope">
            <el-link type="danger" @click="updateBookIsAvailable(scope.row)"
              :style="{ color: scope.row.isAvailable ? '#008000' : '#ff0000' }">
              {{ scope.row.isAvailable ? '是' : '否' }}</el-link>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="150">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="primary" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">编辑</el-link>
              <el-link type="danger" @click="list.deleteRow(scope.row.id)"
                :disabled="scope.row.borrowStatusCode !== 'In'">删除</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="refBookDamageDetail.init({ mode: 'add', bookInfo: scope.row })">标记</el-link>
            </el-space>
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="danger" :id="scope.row.id" @click="bookDamageLog(scope.row.id)">标记记录</el-link>
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
<style>
.el-table .on-row {
  background-color: #def0d8 !important;
}

.el-table .off-row {
  background-color: #fdf8e4 !important;
}

.el-table tr:hover {
  background-color: #d0eac7;
  /* 修改鼠标停留时的背景色 */
}
</style>
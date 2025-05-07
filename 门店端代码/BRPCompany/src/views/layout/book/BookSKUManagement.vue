<script setup>
import { nextTick, ref, onMounted, onBeforeUnmount } from 'vue';
import BookSKUDetail from './BookSKUDetail.vue'
import management from '@/js/management';
import common from '@/js/common'
import BatchUpdateBookSkuAgeTag from './BatchUpdateBookSkuAgeTag.vue'
import CombineBookSku from './CombineBookSku.vue'
import BookSkuReviseManagement from './BookSkuReviseManagement.vue'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();

const batchUpdateBookSkuAgeTagRef = ref();

const combineBookSkuRef = ref();
const bookSkuReviseManagementRef = ref();

const list = management({
  tableName: 'book_sku',
  orderBy: 'create_time desc,book_series_id,book_name',
  getListServiceName: 'GetBookSkuInfoList',
  refDetail,
  processDataItem(dataItem) {
    if (dataItem.smallImageFilePath) {
      dataItem.smallImageFilePath = common.getAdminUrl(dataItem, 'smallImageFilePath');
    }
    return dataItem;
  }
})
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

// 获取系列
getBookSeries();

//获取图书系列
const bookSeriesList = ref([]);
function getBookSeries() {
  let param = {
    tableName: "book_series",
    condition: "company_id=" + companyId.value,
    pageSize: 99999
  };
  common.call("GetTable", param, function (result) {
    bookSeriesList.value = result.data;
  })
}


//是否线上可见
function switchChange(value, skuId) {
  let params = {
    "id": skuId,
    "isOnline": value
  };
  common.call("UpdateBookSku", params, function (result) {
    list.loadData()
  })
}
/**
 * 增加图书
 */
const addBook = (id) => {
  ElMessageBox.prompt('填写要添加的图书数量', '数量', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    inputPattern:
      /^[1-9]\d*$/,
    inputErrorMessage: '请填写正确数字',
  })
    .then(({ value }) => {
      addBookBySku(id, value);
    })
    .catch(() => {
    })
}
function addBookBySku(skuId, count) {
  let params = {
    "bookSkuId": skuId,
    "count": count,
    "companyId": companyId.value
  }
  common.call("AddBookByBookSkuId", params, function (result) {
    ElMessage({
      type: 'success',
      message: '添加成功',
    })
    list.loadData()
  })
}
function synchronousName(skuId) {
  let params = {
    "id": skuId,
  }
  common.call("BookSkuSynchronousName", params, function (result) {
    ElMessage({
      type: 'success',
      message: '同步成功',
    })
    list.loadData()
  })
}
/**
 * 批量是否可见
 * @param {*} isOnline 
 */
function batchIsOnline(isOnline) {
  if (list.primaryKeyValueArray.value.length == 0) {
    ElMessage({ message: "请先选择图书SKU！", type: 'warning' })
    return;
  }
  let param = {
    bookSkuIds: list.primaryKeyValueArray.value,
    isOnline: isOnline
  };
  common.call("BatchUpdateBookSkuIsOnline", param, function (result) {
    ElMessage({ message: "操作成功", type: 'success' })
    list.loadData();
  })
}

const refBookStocks = inject('openBook');
/**
 * 查看图书库存
 */
function viewBookStocks(sdkId) {
  refBookStocks(sdkId);
}

/**
  * 删除
  */
function deleteRow(row) {
  let params = {
    condition: "book_sku_id=" + row.id,
    fields: "count(*) as count"
  };
  common.call("GetBookList", params, function (result) {
    if (result.data.dataList[0].count > 0) {
      ElMessage({ message: "该图书SKU有图书库存，无法删除！", type: 'warning' })
      return;
    }
    // 询问
    ElMessageBox.confirm('确定要删除这条数据吗？', '温馨提示', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    })
      .then(() => {
        const data = {}
        data.id = row.id
        common.call("DeleteBookSku", data, (result) => {
          // 刷新数据
          list.loadData()
        })
      })
      .catch((error) => {
        throw new Error(error.message)
      })
  })
}

function openDetailDialog(options) {
  list.openDetailDialog(options);
}

const multipleTableRef = ref()

function reloadDataList() {
  multipleTableRef.value.clearSelection();
  list.loadData();
}

</script>
<template>
  <div class="basic-list">
    <BookSKUDetail ref="refDetail" @reload-data="list.loadData" />
    <BatchUpdateBookSkuAgeTag ref="batchUpdateBookSkuAgeTagRef" @reload-data="list.loadData" />
    <CombineBookSku ref="combineBookSkuRef" @reload-data="reloadDataList" />
    <BookSkuReviseManagement ref="bookSkuReviseManagementRef" @reload-data="list.loadData" />
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
            <el-form-item label="ISBN" prop="ISBN">
              <el-input placeholder="请输入..." v-model="list.parameters.ISBN"
                @keydown.enter.prevent=list.query></el-input>
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
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="线上可见" prop="isOnlineQuery">
              <el-select placeholder="请选择" v-model="list.parameters.isOnlineQuery" filterable clearable>
                <el-option label="是" value="is_online is true"></el-option>
                <el-option label="否" value='is_online is not true'></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="年龄段" prop="ageGroupSelectValue">
              <DatabaseSelect table-name="age_group" v-model="list.parameters.ageGroupSelectValue" valueFieldName="id"
                placeholder="请选择年龄段" class="form-item-width" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="标签" prop="bookTagSelectValue">
              <DatabaseSelect table-name="book_tag" v-model="list.parameters.bookTagSelectValue" valueFieldName="id"
                placeholder="请选择标签" :condition="'company_id=' + companyId" class="form-item-width" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="图书信息" prop="condition">
              <el-select placeholder="请选择" v-model="list.parameters.condition" filterable clearable>
                <el-option label="未上传封面" value="image_file_id is null"></el-option>
                <el-option label="未录入详情" value="introduction is null or introduction = ''"></el-option>
                <el-option label="未设置年龄段"
                  value="NOT EXISTS (SELECT 1 FROM book_sku_age_group  WHERE book_sku.id = book_sku_age_group.book_sku_id)"></el-option>
                <el-option label="未设置标签"
                  value="NOT EXISTS (SELECT 1 FROM book_sku_book_tag  WHERE book_sku.id = book_sku_book_tag.book_sku_id)"></el-option>
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
      <div>
        <text>总计</text>
        <text style="color: red;">{{ list.recordCount.value }}</text>
        <text>个SKU，当页共计</text>
        <text>{{ list.dataList.value.length }}</text>
        <text>个</text>
      </div>
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="list.openDetailDialog({ mode: 'add' })">
              <el-icon style="margin-right: 3px">
                <Plus />
              </el-icon> 新建</el-button>
            <el-button type="danger" @click="list.deleteRows">
              <el-icon tyle="margin-right: 3px">
                <Delete />
              </el-icon>
              删除
            </el-button>
            <el-button type="primary" @click="combineBookSkuRef.init(list.primaryKeyValueArray.value)">
              合并SKU</el-button>
            <el-button type="primary" @click="batchIsOnline(1)"> 批量可见</el-button>
            <el-button type="primary" @click="batchIsOnline(0)"> 批量不可见</el-button>
            <el-button type="primary" @click="batchUpdateBookSkuAgeTagRef.init(list.primaryKeyValueArray.value)">
              批量修改年龄段标签</el-button>
          </el-col>
          <el-col :span="6">
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%"
        @selection-change="list.selectRow" :row-key="row => row.id">
        <el-table-column type="selection" width="55" :reserve-selection="true" />
        <el-table-column property="avator" label="封面" width="100" align="center">
          <template #default="scope">
            <el-image style="width: 100px; height: 100px" :src="scope.row.smallImageFilePath" fit="contain">
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
        <el-table-column property="bookName" label="书名" width="300">
          <template #default="scope">
            <div>
              <span style="color: #008000;">{{ scope.row.bookSeriesName ? "[" + scope.row.bookSeriesName + "]" : ""
                }}</span>
              <span>{{ scope.row.bookName }}</span>
              <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
                }}</span>
            </div>
            <div class="list-item-title_3">ISBN：{{ scope.row.ISBN }}
            </div>
            <div class="list-item-title_3">基础信息ID：{{ scope.row.platformBookSkuId }}
            </div>
            <!-- <div>
              <el-link class="list-item-title_3">修改封面图</el-link>
              <el-link class="list-item-title_3" style="margin-left: 20px;">修改书名</el-link>
            </div> -->
          </template>
        </el-table-column>
        <el-table-column property="languageName" label="分类" />
        <el-table-column property="" label="年龄段&标签">
          <template #default="scope">
            <div>
              <span>{{ scope.row.ageGroupNames }}</span>
            </div>
            <div>
              <span>{{ scope.row.bookTagNames }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="isOnline" label="线上可见">
          <template #default="scope">
            <el-switch v-model="scope.row.isOnline" @change="value => switchChange(value, scope.row.id)" />
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id"
                @click="openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看详情</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">修改详情</el-link>
            </el-space>
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="primary" :id="scope.row.id" @click="addBook(scope.row.id)">增加图书</el-link>
              <el-link type="primary" :id="scope.row.id" @click="viewBookStocks(scope.row.id)">图书库存</el-link>
            </el-space>
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="primary" :id="scope.row.id" @click="synchronousName(scope.row.id)">同步名称</el-link>
              <el-link type="danger" @click="deleteRow(scope.row)">删除图书</el-link>
            </el-space>
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="primary" :id="scope.row.id"
                @click="bookSkuReviseManagementRef.init(scope.row)">详情报错</el-link>
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
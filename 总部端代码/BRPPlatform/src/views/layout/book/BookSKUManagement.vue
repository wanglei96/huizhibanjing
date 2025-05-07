<script setup>
import { ref } from 'vue';
import BookSKUDetail from './BookSKUDetail.vue'
import management from '@/js/management';
import common from '@/js/common'
import BatchUpdateBookSkuAgeTag from './BatchUpdateBookSkuAgeTag.vue'
import CombineBookSku from './CombineBookSku.vue'
import BookAddExcel from './BookAddExcel.vue'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'


//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();
const BookAddExcelRef = ref()

const batchUpdateBookSkuAgeTagRef = ref();

const combineBookSkuRef = ref();

const list = management({
  tableName: 'book_sku',
  orderBy: 'create_time desc,book_series_id,book_name',
  getListServiceName: 'GetBookSkuInfoList',
  condition: "company_id is null",
  refDetail,
  processDataItem(dataItem) {
    if (dataItem.imageFilePath) {
      dataItem.imageFilePath = common.getAdminUrl(dataItem, 'imageFilePath');
    }
    return dataItem;
  }
})

// 获取系列
getBookSeries();

//获取图书系列
const bookSeriesList = ref([]);
function getBookSeries() {
  let param = {
    "tableName": "book_series",
    "condition": "company_id is null"
  };
  common.call("GetTable", param, function (result) {
    bookSeriesList.value = result.data;
  })
}

//改变是否可见
function switchChange(value, skuId) {
  let params = {
    "id": skuId,
    "isOnline": value
  };
  common.call("UpdateBookSku", params, function (result) {
    list.loadData
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

function synchronization(id) {
  ElMessageBox.confirm(
    '确定同步信息吗，将会修改所有门店的SKU信息!',
    '提醒',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      common.call("SynchronizationPlatformBookSku", { id: id }, (result) => {
        ElMessage({ message: "同步成功", type: 'success' });
      })
    })
    .catch(() => {
    })
}


function exportDataList() {
  let params = { ...list.parameters };
  for (let key in params) {
    if (key.indexOf('dpSearch') !== -1) {
      let parameterName = getParameterName(key, 'dpSearch'.length);
      if (params[key]) {
        params[parameterName + 'Begin'] = params[key][0];
        params[parameterName + 'End'] = params[key][1];
      }
    }
  }
  params.exportName = 'BookSku'
  params.pageIndex = 0
  params.pageSize = 1000000
  params.orderBy = 'create_time desc,book_series_id,book_name'
  params.condition = "company_id is null"
  common.call('Export', params, (res) => {
    let url = res.adminRootUrl + res.data.filePath
    window.open(url)
  })
}
function getParameterName(searchControlId, prefixLength) {
  let parameterName = searchControlId.substring(prefixLength);
  parameterName = parameterName.substring(0, 1).toLowerCase()
    + parameterName.substring(1);
  return parameterName;
}

</script>
<template>
  <div class="basic-list">
    <BookSKUDetail ref="refDetail" @reload-data="list.loadData" />
    <BatchUpdateBookSkuAgeTag ref="batchUpdateBookSkuAgeTagRef" @reload-data="list.loadData" />
    <CombineBookSku ref="combineBookSkuRef" @reload-data="list.loadData" />
    <BookAddExcel ref="BookAddExcelRef" @reload-data="list.loadData" />
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
              <el-select placeholder="请选择" v-model="list.parameters.bookSeriesId" filterable>
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
                placeholder="请选择标签" condition="company_id is null" class="form-item-width" />
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
            <el-button type="primary" @click="combineBookSkuRef.init(list.primaryKeyValueArray.value)">
              合并SKU</el-button>
            <el-button type="primary" @click="batchIsOnline(1)"> 批量可见</el-button>
            <el-button type="primary" @click="batchIsOnline(0)"> 批量不可见</el-button>
            <el-button type="primary" @click="batchUpdateBookSkuAgeTagRef.init(list.primaryKeyValueArray.value)">
              批量修改年龄段标签</el-button>
            <el-button type="warning" @click="BookAddExcelRef.init()">批量录入新书</el-button>
            <el-button type="success" @click="exportDataList()">导出Excel</el-button>
          </el-col>
          <el-col :span="6">
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%"
        @selection-change="list.selectRow">
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
        <el-table-column property="bookName" label="书名" width="400">
          <template #default="scope">
            <div>
              <span style="color: #008000;">{{ scope.row.bookSeriesName ? "[" + scope.row.bookSeriesName + "]" : ""
                }}</span>
              <span>{{ scope.row.bookName }}</span>
              <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
                }}</span>
            </div>
            <div class="list-item-title_3">ISBN:{{ scope.row.ISBN }}
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
        <el-table-column label="操作" align="center" width="240">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">编辑</el-link>
              <el-link type="warning" :id="scope.row.id" @click="synchronization(scope.row.id)">同步信息</el-link>
              <el-link type="danger" @click="deleteRow(scope.row)">删除</el-link>
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
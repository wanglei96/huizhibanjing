<script setup>
import BookcaseDetail from './BookcaseDetail.vue'
import BookcaseOperateBook from './BookcaseOperateBook.vue'
import CombineBookcase from './CombineBookcase.vue'
import common from '@/js/common'
import { reactive, ref, inject } from 'vue';
import management from '@/js/management';
//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();
// 声明书柜操作书籍页
const BookcaseOperateBookRef = ref();
const combineBookcaseRef = ref();


// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

const list = management({
  tableName: 'bookcase',
  orderBy: 'create_time desc',
  getListServiceName: 'GetBookcaseList',
  refDetail,
  parameters: {
    fields: "*,(select count(1) from book where bookcase.id = book.bookcase_id) as book_count"
  },
})

const multipleTableRef = ref()

function reloadDataList() {
  selectRowValueArray.value = []
  primaryKeyValueArray.value = []
  multipleTableRef.value.clearSelection();
  list.loadData();
}

const selectRowValueArray = ref([])
//多选
const selectRow = (val) => {
  selectRowValueArray.value = val
  primaryKeyValueArray.value = []
  val.forEach((item) => {
    primaryKeyValueArray.value.push(item.id)
  })
}
const primaryKeyValueArray = ref([])
//多选选中数量
const selectedColumnCount = computed(() => {
  return primaryKeyValueArray.value.length
})
</script>
<template>
  <div class="user">
    <BookcaseDetail ref="refDetail" @reload-data="list.loadData" />
    <BookcaseOperateBook ref="BookcaseOperateBookRef" @reload-data="list.loadData" />
    <CombineBookcase ref="combineBookcaseRef" @reload-data="reloadDataList" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="名称" prop="name">
              <el-input placeholder="请输入..." v-model="list.parameters.name"
                @keydown.enter.prevent=list.query></el-input>
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

    <el-card shadow="never" class="user-list-card">
      <el-alert :title="`已选择 ${selectedColumnCount} 项`" v-show="selectedColumnCount > 0"
        type="warning" show-icon />
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="list.openDetailDialog({ mode: 'add' })">
              <el-icon style="margin-right: 3px">
                <Plus />
              </el-icon> 新建</el-button>
            <el-button type="primary" @click="combineBookcaseRef.init(primaryKeyValueArray)">
              合并书柜</el-button>
          </el-col>
          <el-col :span="6">
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%"
        @selection-change="selectRow" :row-key="row => row.id">
        <el-table-column type="selection" width="55" :reserve-selection="true" />
        <el-table-column property="name" label="书柜名称" align="center" />
        <el-table-column property="bookCount" label="图书数量" align="center" />
        <el-table-column property="createTimeText" label="添加时间" align="center" />
        <el-table-column label="操作" align="center" width="380">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">编辑</el-link>
              <el-link type="danger" @click="list.deleteRow(scope.row.id)">删除</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="BookcaseOperateBookRef.init({ mode: 'add', data: scope.row })">批量添加图书</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="BookcaseOperateBookRef.init({ mode: 'view', data: scope.row })">查询现有图书</el-link>
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

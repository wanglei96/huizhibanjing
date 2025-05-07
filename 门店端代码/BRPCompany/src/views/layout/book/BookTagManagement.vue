<script setup>
import BookTagDetail from './BookTagDetail.vue'
import common from '@/js/common'
import { reactive, ref, inject } from 'vue';
import BatchTagDisposeBook from './BatchTagDisposeBook.vue';
import management from '@/js/management';
import CombineBookTag from './CombineBookTag.vue';
const combineBookTagRef = ref()

const batchTagDisposeBookRef = ref()
const refDetail = ref();
const refSearchForm = ref();

const list = management({
  tableName: 'book_tag',
  orderBy: 'CASE WHEN display_order IS NULL THEN 1 ELSE 0 END, display_order ASC, create_time DESC',
  getListServiceName: 'GetViewBookTagList',
  refDetail
})

const multipleTableRef = ref()

function reloadDataList() {
  multipleTableRef.value.clearSelection();
  list.loadData();
}


</script>
<template>
  <div class="user">
    <BookTagDetail ref="refDetail" @reload-data="list.loadData" />
    <BatchTagDisposeBook ref="batchTagDisposeBookRef" @reload-data="list.loadData" />
    <CombineBookTag ref="combineBookTagRef" @reload-data="reloadDataList" />
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
      <el-alert :title="`已选择 ${list.selectedColumnCount.value} 项`" v-show="list.selectedColumnCount.value > 0"
        type="warning" show-icon />
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="list.openDetailDialog({ mode: 'add' })">
              <el-icon style="margin-right: 3px">
                <Plus />
              </el-icon> 新建</el-button>
            <el-button type="primary" @click="combineBookTagRef.init(list.primaryKeyValueArray.value)">
              合并标签</el-button>
          </el-col>
          <el-col :span="6">
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%"
        @selection-change="list.selectRow" :row-key="row => row.id">
        <el-table-column type="selection" width="55" :reserve-selection="true" />
        <el-table-column property="name" label="标签名" align="center" />
        <el-table-column property="frontName" label="前台显示名称" align="center" />
        <el-table-column property="languageName" label="分类" align="center" />
        <el-table-column property="displayOrder" label="排序" align="center" />
        <el-table-column property="skuCount" label="sku数量" align="center" />
        <el-table-column property="bookCount" label="图书数量" align="center" />
        <el-table-column property="isVisible" label="会员可见" align="center">
          <template #default="scope">
            <span v-if="scope.row.isVisible === true">可见</span>
            <span v-else>不可见</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="320">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">编辑</el-link>
              <el-link type="danger" @click="list.deleteRow(scope.row.id)">删除</el-link>
              <el-link type="primary" @click="list.moveUp(scope.row)">上移</el-link>
              <el-link type="primary" @click="list.moveDown(scope.row)">下移</el-link>
            </el-space>
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="primary" :id="scope.row.id"
                @click="batchTagDisposeBookRef.init({ type: 'delete', item: scope.row })">批量删除图书</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="batchTagDisposeBookRef.init({ type: 'add', item: scope.row })">批量添加图书</el-link>
              <el-link type="warning" :id="scope.row.id"
                @click="batchTagDisposeBookRef.init({ type: 'view', item: scope.row })">查询现有图书</el-link>
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
<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import common from '@/js/common'
import BookSkuReviseDetail from './BookSkuReviseDetail.vue'


//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();
const list = ref([]);

const dialogFormVisible = ref(false)
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
const bookSkuInfo = ref({});
function init(row) {
  bookSkuInfo.value = row;
  // 显示弹窗
  dialogFormVisible.value = true;
  list.value = management({
    tableName: 'book_sku_revise',
    orderBy: 'create_time desc',
    getListServiceName: 'GetViewBookSkuReviseList',
    parameters: {
      bookSkuId: row.id
    },
    refDetail
  })
}

defineExpose({
  init
})
</script>
<template>
  <div class="basic-list">
    <BookSkuReviseDetail ref="refDetail" @reload-data="list.loadData" />
    <el-dialog destroy-on-close :close-on-click-modal="false" v-if="dialogFormVisible" v-model="dialogFormVisible"
      title="详情报错" width="1200" draggable>
      <el-card shadow="never" class="search-card">
        <el-form ref="refSearchForm" :model="list.parameters">
          <el-row type="flex" :gutter="20">
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item label="时间范围" prop="dpSearchCreateTime">
                <el-date-picker v-model="list.parameters.dpSearchCreateTime" type="daterange" start-placeholder="开始时间"
                  end-placeholder="结束时间" value-format="YYYY-MM-DD" :validate-event="true" />
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
            <el-col :span="18">
              <el-button type="primary" @click="list.openDetailDialog({ mode: 'add', data: bookSkuInfo })">
                <el-icon style="margin-right: 3px">
                  <Plus />
                </el-icon> 新建</el-button>
            </el-col>
            <el-col :span="6">
            </el-col>
          </el-row>
        </el-form>
        <el-table ref="multipleTableRef" :data="list.dataList" style="width: 100%" @selection-change="list.selectRow">
          <el-table-column property="bookName" label="书名"  />
          <el-table-column property="companyUserName" label="提交人"  />
          <el-table-column property="createTimeText" label="提交时间"  />
          <el-table-column label="操作" align="center" width="180">
            <template #default="scope">
              <el-space spacer="|" style="color: #999" wrap>
                <el-link type="primary" :id="scope.row.id"
                  @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看</el-link>
                <el-link type="danger" @click="list.deleteRow(scope.row.id)">删除</el-link>
              </el-space>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination v-model:current-page="list.currentPage" v-model:page-size="list.pageSizeComputed"
          :page-sizes="list.pageSizes" :page-count="list.pageCount" :layout="list.layout" :total="list.recordCount"
          style="margin-top: 20px" />
      </el-card>
    </el-dialog>
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
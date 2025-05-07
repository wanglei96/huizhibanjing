<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import common from '@/js/common'

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();

const list = management({
  tableName: 'book_in_order',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewBookInOrderList',
  refDetail
})
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
</script>
<template>
  <div class="basic-list">
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书名" prop="bookName">
              <el-input placeholder="请输入..." v-model="list.parameters.bookName"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书号" prop="bookSn">
              <el-input placeholder="请输入..." v-model="list.parameters.bookSn"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="会员名称" prop="memberName">
              <el-input placeholder="请输入..." v-model="list.parameters.memberName"
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
    <el-card shadow="never">
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%"
        @selection-change="list.selectRow">
        <el-table-column property="memberInfo" label="会员"  width="200px">
          <template #default="scope">
            <div>
              <sapn>{{ scope.row.memberName }}</sapn>
            </div>
            <div>
              <sapn>{{ scope.row.memberCardNo }}</sapn>
            </div>
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
          </template>
        </el-table-column>
        <el-table-column property="bookSn" label="书号" width="200px" />
        <el-table-column property="bookcaseName" label="柜号"  width="200px"/>
        <el-table-column property="createTimeText" label="线下还书时间"  width="200px"/>
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
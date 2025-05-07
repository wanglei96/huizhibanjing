<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import common from '@/js/common'
import BookDamageDetail from './BookDamageDetail.vue'
import { useRoute } from 'vue-router';

const route = useRoute();
const bookId = route.query['bookId'];

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

const list = management({
  tableName: 'book_damage',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewBookDamageList',
  condition: bookId ? 'book_id=' + bookId : '',
  refDetail
})

</script>
<template>
  <div class="basic-list">
    <BookDamageDetail ref="refDetail" @reload-data="list.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="会员名称" prop="memberName">
              <el-input placeholder="请输入..." v-model="list.parameters.memberName" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书名" prop="bookName">
              <el-input placeholder="请输入..." v-model="list.parameters.bookName" @keydown.enter.prevent=list.query></el-input>
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
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%"
        @selection-change="list.selectRow">
        <el-table-column property="bookName" label="书名" width="400">
          <template #default="scope">
            <div>
              <span style="color: #008000;">{{ scope.row.bookSeriesName ? "[" + scope.row.bookSeriesName + "]" : ""
                }}</span>
              <span>{{ scope.row.bookName }}</span>
              <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
                }}</span>
            </div>
            <div>书号：{{ scope.row.bookSn }}</div>
          </template>
        </el-table-column>
        <!-- <el-table-column property="bookSn" label="书号" width="200" /> -->
        <el-table-column property="memberInfo" label="会员" width="200">
          <template #default="scope">
            <div>
              <sapn>{{ scope.row.memberName }}</sapn>
            </div>
            <div>
              <sapn>{{ scope.row.memberCardNo }}</sapn>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="isCompensation" label="是否已赔">
          <template #default="scope">
            <div>
              <sapn>{{ scope.row.isCompensation ? "已赔" : "未赔" }}</sapn>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="bookDamageTypeName" label="类型" />
        <el-table-column property="amount" label="赔付金额" />
        <el-table-column property="remark" label="备注" />
        <el-table-column property="damageTimeText" label="损坏时间" />
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="primary" :id="scope.row.id" v-if="!scope.row.isCompensation"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">赔付</el-link>
              <el-link type="danger" @click="list.deleteRow(scope.row.id)">删除</el-link>
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
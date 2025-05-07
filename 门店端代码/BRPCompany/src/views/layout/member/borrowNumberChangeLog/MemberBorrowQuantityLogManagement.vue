<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import common from '@/js/common'

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();

const list = management({
  tableName: 'member_borrow_quantity_log',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewMemberBorrowQuantityLogList',
  refDetail
})
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

function formatDate(row, column, cellValue, index) {
  if (cellValue) {
    return cellValue.substring(0, 10);
  }
  return "";
}
const deleteRow = (id) => {
  ElMessageBox.confirm('确定要删除这条数据吗？', '温馨提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(() => {
      let params = {
        id: id,
        tableName: 'member_borrow_quantity_log'
      }
      common.call('HideIdTable', params, (result) => {
        list.loadData()
      })
    })
    .catch((error) => {
      throw new Error(error.message)
    })
}
</script>
<template>
  <div class="basic-list">
    <BookSeriesDetail ref="refDetail" @reload-data="list.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="会员名称" prop="memberName">
              <el-input placeholder="请输入..." v-model="list.parameters.memberName"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="会员卡号" prop="memberCardNo">
              <el-input placeholder="请输入..." v-model="list.parameters.memberCardNo"
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

        <el-table-column property="memberCardNo" label="会员卡号" align="center" />
        <el-table-column property="memberName" label="会员名称" align="center" />
        <el-table-column property="quantity" label="增减本数" align="center" />
        <el-table-column property="memberBorrowQuantityLogTypeName" label="类型" align="center" />
        <el-table-column property="startDateText" label="开始日期" align="center" :formatter="formatDate" />
        <el-table-column property="endDateText" label="结束日期" align="center" :formatter="formatDate" />
        <el-table-column property="isDeleted" label="是否删除" align="center">
          <template #default="scope">
            <span>{{ scope.row.isDeleted ? '是' : '否' }}</span>
          </template>
        </el-table-column>
        <el-table-column property="remark" label="备注" align="center" />
        <el-table-column property="createTimeText" label="添加时间" align="center" />

        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="danger" @click="deleteRow(scope.row.id)" :disabled="scope.row.isDeleted">删除</el-link>
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
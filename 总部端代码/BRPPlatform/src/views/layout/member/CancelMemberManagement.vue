<script setup>
import common from '@/js/common'
import { reactive, ref, inject } from 'vue';
import management from '@/js/management';
import dayjs from "dayjs";
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'


const refDetail = ref();
const refSearchForm = ref();

const list = management({
  tableName: 'member',
  orderBy: 'create_time DESC',
  getListServiceName: 'GetViewMemberList',
  condition: "is_cancel is true",
  refDetail
})

const formatBusinessDate = (row, column, cellValue, index) => {
  if (cellValue) {
    return cellValue.substring(0, 10);
  }
  return "";
}




</script>
<template>
  <div class="user">
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="会员名称" prop="name">
              <el-input placeholder="请输入..." v-model="list.parameters.name"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="会员手机号" prop="cancelMobilePhone">
              <el-input placeholder="请输入..." v-model="list.parameters.cancelMobilePhone"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="门店" prop="companyId">
              <DatabaseSelect table-name="company" v-model="list.parameters.companyId" textFieldName="company_name"
                valueFieldName="id" placeholder="请选择" class="form-item-width" />
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
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%"
        @selection-change="list.selectRow" :row-key="row => row.id">
        <el-table-column property="name" label="会员名称" align="center" />
        <el-table-column property="cancelMobilePhone" label="手机号" align="center" />
        <el-table-column property="gender" label="性别" align="center">
          <template #default="scope">
            <span v-if="scope.row.gender">男</span>
            <span v-else>女</span>
          </template>
        </el-table-column>
        <el-table-column property="birthdayText" label="出生日期" align="center" :formatter="formatBusinessDate" />
        <el-table-column property="school" label="学校" align="center" />
        <el-table-column property="gradeName" label="年级" align="center" />
        <el-table-column property="className" label="班级" align="center" />
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
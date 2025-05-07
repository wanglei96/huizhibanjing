<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import common from '@/js/common';

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();

const list = management({
  tableName: 'sys_login_log',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewSysLoginLogList',
  refDetail
})

</script>
<template>
  <div class="basic-list">
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row :gutter="20" type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="登录账号" prop="loginName">
              <el-input placeholder="请输入..." v-model="list.parameters.loginName"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="IP地址" prop="ip">
              <el-input placeholder="请输入..." v-model="list.parameters.ip" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="登录用户名称" prop="name">
              <el-input placeholder="请输入..." v-model="list.parameters.name"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="登录时间" prop="dpSearchCreateTime">
              <el-date-picker v-model="list.parameters.dpSearchCreateTime" type="daterange" start-placeholder="开始日期"
                end-placeholder="结束日期" value-format="YYYY-MM-DD" :validate-event="true" />
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
            <el-button type="danger" @click="list.deleteRows">
              <el-icon tyle="margin-right: 3px">
                <Delete />
              </el-icon>
              删除
            </el-button>
          </el-col>
          <el-col :span="6">
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%" border
        @selection-change="list.selectRow">
        <el-table-column type="selection" width="55" />
        <el-table-column property="loginName" label="登录账号" />
        <el-table-column property="ip" label="IP地址" />
        <el-table-column property="name" label="登录用户名称" />
        <el-table-column property="errorMessage" label="登录失败原因" />
        <el-table-column property="createTimeText" label="登录时间" />
        <el-table-column label="操作" align="center" width="100">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
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
.el-row {
  row-gap: 10px;
}

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
</style>

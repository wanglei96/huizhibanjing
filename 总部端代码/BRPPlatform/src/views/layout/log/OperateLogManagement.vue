<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import common from '@/js/common';
import dayjs from "dayjs";
import DatabaseSelect from "@/components/extension/DatabaseSelect.vue";
import util from '@/js/util'

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();

const list = management({
  tableName: 'sys_operation_log',
  orderBy: 'operate_time desc',
  getListServiceName: 'GetSysOperationLogInfoList',
  refDetail
})

function formatBusinessDate(row, column, cellValue, index) {
  if (cellValue) {
    return dayjs(cellValue).format('YYYY-MM-DD HH:mm:ss')
    //return util.jsonToDateTimeString(cellValue);
  }
  return "";
}

</script>
<template>
  <div class="basic-list">
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row :gutter="20" type="flex">
          <!-- <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="操作人" prop="sysUserTrueName">
              <el-input placeholder="请输入..." v-model="list.parameters.sysUserTrueName"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="登录账号" prop="sysUserLoginName">
              <el-input placeholder="请输入..." v-model="list.parameters.sysUserLoginName"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col> -->
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="操作内容" prop="operationTypeCode">
              <DatabaseSelect table-name="sys_operation_type" v-model="list.parameters.operationTypeCode">
              </DatabaseSelect>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="操作时间" prop="dpSearchOperateTime">
              <el-date-picker v-model="list.parameters.dpSearchOperateTime" type="daterange" start-placeholder="开始日期"
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
        <el-table-column property="sysUserTrueName" label="操作人" />
        <el-table-column property="sysUserLoginName" label="登录账号" />
        <el-table-column property="operationTargetName" label="操作模块" />
        <el-table-column property="operationTypeName" label="操作类型" />
        <el-table-column property="operateTime" :formatter="formatBusinessDate" label="操作时间" />
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

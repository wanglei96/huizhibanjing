<script setup>
import { ref } from 'vue';
import CmsSiteDetail from './CmsSiteDetail.vue';
import management from '@/js/management';
import common from '@/js/common';

//详情页组件引用
const refDetail = ref();
//搜索表单组件引用
const refSearchForm = ref();

const page = management({
  tableName: 'cms_site',
  orderBy: 'display_order',
  refDetail
})

function setDefault(cmsSiteId) {
  common.setLocalStorage("cmsSiteId", cmsSiteId);
  page.loadData();
}
</script>
<template>
  <div class="basic-page">
    <CmsSiteDetail ref="refDetail" @reload-data="page.loadData" />
    <SetRolePermission ref="refSetRolePermission" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="page.parameters">
        <el-row :gutter="20" type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="名称" prop="name">
              <el-input placeholder="请输入..." v-model="page.parameters.name"
                @keydown.enter.prevent=page.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="描述" prop="description">
              <el-input placeholder="请输入..." v-model="page.parameters.description"
                @keydown.enter.prevent=page.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click='page.query'>搜索</el-button>
              <el-button @click='page.resetForm(refSearchForm)'>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card shadow="never">
      <el-alert :title="`已选择 ${page.selectedColumnCount.value} 项`" v-show="page.selectedColumnCount.value > 0"
        type="warning" show-icon />
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="page.openDetailDialog({ mode: 'add' })">
              <el-icon style="margin-right: 3px">
                <Plus />
              </el-icon>
              新建
            </el-button>
            <el-button type="danger" @click="page.deleteRows">
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
      <el-table ref="multipleTableRef" :data="page.dataList.value" style="width: 100%"
        @selection-change="page.selectRow">
        <el-table-column property="name" label="名称" width="100" />
        <el-table-column property="path" label="路径" width="150" />
        <el-table-column property="description" label="描述" />
        <el-table-column label="操作" align="center" width="340">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id"
                @click="page.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看
              </el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="page.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">编辑
              </el-link>
              <el-link type="danger" @click="page.deleteRow(scope.row.id)">删除</el-link>
              <el-link type="primary" @click="page.moveUp(scope.row)">上移</el-link>
              <el-link type="primary" @click="page.moveDown(scope.row)">下移</el-link>
              <el-link type="success" @click="setDefault(scope.row.id)"
                v-if="scope.row.id != common.getLocalStorage('cmsSiteId')">切换站点
              </el-link>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:current-page="page.currentPage.value" v-model:page-size="page.pageSizeComputed.value"
        :page-sizes="page.pageSizes" :page-count="page.pageCount.value" :layout="page.layout"
        :total="page.recordCount.value" style="margin-top: 20px" />
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

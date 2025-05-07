<script setup>
import { ref } from 'vue';
import CmsChannelDetail from './CmsChannelDetail.vue';
import common from "@/js/common";
import management from '@/js/management';

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();

const page = management({
  gridType: 'Tree',
  tableName: 'cms_channel',
  fields: 'id,code,name,parent_id,parent_name,description,create_time',
  orderBy: "display_order",
  condition: "cms_site_id=" + common.getLocalStorage("cmsSiteId"),
  getListServiceName: 'GetViewCmsChannelList',
  parentFieldName: 'parentId',
  isCms: true,
  refDetail,
  parameters: {
    cmsSiteId: common.getCmsSiteId()
  }
})
</script>
<template>
  <div class="basic-page">
    <CmsChannelDetail ref="refDetail" @reload-data="page.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="page.parameters.value">
        <el-row :gutter="20" type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="编码" prop="code">
              <el-input placeholder="请输入..." v-model="page.parameters.code"
                @keydown.enter.prevent=page.query></el-input>
            </el-form-item>
          </el-col>
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
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="page.openDetailDialog({ mode: 'add' })">
              <el-icon style="margin-right: 3px">
                <Plus />
              </el-icon>
              新建
            </el-button>
          </el-col>
          <el-col :span="6">
          </el-col>
        </el-row>
      </el-form>
      <el-table :data="page.dataList.value" style="width: 100%;margin-top: 10px;" row-key="id" border default-expand-all
        :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">
        <el-table-column label="编码" width="200" prop="code" />
        <el-table-column label="名称" width="150" prop="name" />
        <el-table-column prop="description" label="描述" />
        <el-table-column label="操作" width="160">
          <template #default="scope">
            <el-space spacer="|" style="color: #999">
              <el-link type="warning" @click="page.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">
                查看
              </el-link>
              <el-link type="primary" @click="page.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">
                修改
              </el-link>
              <el-link type="danger" @click="page.deleteRow(scope.row.id)">删除</el-link>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
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
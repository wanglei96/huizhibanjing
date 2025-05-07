<script setup>
import { ref } from 'vue'
import CompanyOrganizationDetail from './CompanyOrganizationDetail.vue'
import management from '@/js/management'
import common from '@/js/common'
//详情页组件引用
const refDetail = ref()
const refSearchForm = ref()

const list = management({
  gridType: 'Tree',
  tableName: 'company_organization',
  orderBy: 'display_order',
  getListServiceName: 'GetViewCompanyOrganizationList',
  parentFieldName: 'parentId',
  refDetail,
  condition: "is_deleted is not true"
})

const deleteRow = (id) => {
  ElMessageBox.confirm('确定要删除这条数据吗？', '温馨提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(() => {
      let params = {
        id: id,
        tableName: "company_organization"
      }
      common.call('HideIdTreeTable', params, (result) => {
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
    <CompanyOrganizationDetail ref="refDetail" @reload-data="list.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row :gutter="20" type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="名称" prop="name">
              <el-input placeholder="请输入..." v-model="list.parameters.name"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="描述" prop="description">
              <el-input placeholder="请输入..." v-model="list.parameters.description"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click="list.query">搜索</el-button>
              <el-button @click="list.resetForm(refSearchForm)">重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card shadow="never">
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="list.openDetailDialog({ mode: 'add' })">
              <el-icon style="margin-right: 3px">
                <Plus />
              </el-icon>
              新建
            </el-button>
          </el-col>
          <el-col :span="6"> </el-col>
        </el-row>
      </el-form>
      <el-table :data="list.dataList.value" style="width: 100%; margin-top: 10px" row-key="id" border default-expand-all
        :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">
        <el-table-column prop="name" label="名称" width="240" />
        <el-table-column prop="description" label="描述" />
        <el-table-column label="操作" width="160">
          <template #default="scope">
            <el-space spacer="|" style="color: #999">
              <el-link type="warning" @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">
                查看
              </el-link>
              <el-link type="primary" @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">
                修改
              </el-link>
              <el-link type="danger" @click="deleteRow(scope.row.id)">删除</el-link>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
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
</style>
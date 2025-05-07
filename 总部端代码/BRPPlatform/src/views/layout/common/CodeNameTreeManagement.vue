<script setup>
import {ref} from 'vue';
import management from '@/js/management';
import {useRoute} from 'vue-router';
import CodeNameTreeDetail from './CodeNameTreeDetail.vue'

var route = useRoute();

//详情页组件引用
const refDetail = ref();
//搜索表单组件引用
const refSearchForm = ref();

const tableName = route.query['tableName'];
const title = route.query['title'];

/*const list = management({
  tableName: tableName,
  dataTitle: title,
  orderBy: 'display_order',
  dataName: "CodeNameTree",
  primaryKeyName: "code",
  isBasicTable: true,
  refDetail
})*/
const list = management({
  dataTitle: title,
  gridType: 'Tree',
  tableName: route.query['tableName'],
  orderBy: "display_order",
  dataName: "CodeNameTree",
  parentValueFieldName: 'parentCode',
  valueFieldName: 'code',
  refDetail,
  isBasicTable: true,
})
</script>
<template>
  <div class="basic-list">
    <CodeNameTreeDetail ref="refDetail" @reload-data="list.loadData"/>
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="名称" prop="name">
              <el-input placeholder="请输入..." v-model="list.parameters.name" @keydown.enter.prevent=page.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="描述" prop="description">
              <el-input placeholder="请输入..." v-model="list.parameters.description" @keydown.enter.prevent=page.query></el-input>
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
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="list.openDetailDialog({mode:'add'})">
              <el-icon style="margin-right: 3px">
                <Plus/>
              </el-icon>
              新建
            </el-button>
          </el-col>
          <el-col :span="6">
          </el-col>
        </el-row>
      </el-form>
      <el-table
          :data="list.dataList.value"
          style="width: 100%;margin-top: 10px;"
          row-key="id"
          border
          default-expand-all
          :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      >
        <el-table-column prop="name" label="名称" width="240"/>
        <el-table-column prop="description" label="描述"/>
        <el-table-column label="操作" width="280">
          <template #default="scope">
            <el-space spacer="|" style="color: #999">
              <el-link type="warning" @click="list.openDetailDialog({mode: 'view', primaryKeyValue: scope.row.code})">
                查看
              </el-link>
              <el-link type="primary" @click="list.openDetailDialog({mode: 'edit', primaryKeyValue: scope.row.code})">
                编辑
              </el-link>
              <el-link type="danger" @click="list.deleteRow(scope.row.id)">删除</el-link>
              <el-link type="primary" @click="page.moveUp(scope.row)">上移</el-link>
              <el-link type="primary" @click="page.moveDown(scope.row)">下移</el-link>
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
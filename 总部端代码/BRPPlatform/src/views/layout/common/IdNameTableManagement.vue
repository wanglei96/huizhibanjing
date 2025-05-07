<script setup>
import { ref } from 'vue';
import IdNameTableDetail from './IdNameTableDetail.vue';
import management from '@/js/management';
import { useRoute } from 'vue-router';
import common from '@/js/common';

var route = useRoute();

//详情页组件引用
const refDetail = ref();
//搜索表单组件引用
const refSearchForm = ref();
const tableName = route.query['tableName'];
const title = route.query['title'];


const page = management({
  tableName: tableName,
  dataTitle: title,
  orderBy: 'display_order',
  dataName: "IdNameTable",
  isBasicTable: true,
  pageSize: 50,
  refDetail,
  condition: "is_deleted is not true"
})
/*watch(()=>route.query,()=>{
  tableName = route.query['tableName'];
  title = route.query['title'];
  page = management({
    tableName: tableName,
    dataTitle: title,
    orderBy: 'display_order',
    dataName: "CodeNameTable",
    primaryKeyName: "code",
    isBasicTable: true,
    refDetail
  })
})*/

const deleteRow = (id) => {
  ElMessageBox.confirm('确定要删除这条数据吗？', '温馨提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(() => {
      let params = {
        id: id,
        tableName: tableName
      }
      common.call('HideIdTable', params, (result) => {
        page.loadData()
      })
    })
    .catch((error) => {
      throw new Error(error.message)
    })
}

</script>
<template>
  <div class="basic-list">
    <IdNameTableDetail ref="refDetail" @reload-data="page.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="page.parameters">
        <el-row type="flex">
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
              </el-icon> 新建</el-button>
            <!-- <el-button type="danger" @click="page.deleteRows">
              <el-icon tyle="margin-right: 3px">
                <Delete />
              </el-icon> 删除
            </el-button> -->
          </el-col>
          <el-col :span="6">
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="multipleTableRef" :data="page.dataList.value" border style="width: 100%"
        @selection-change="page.selectRow">
        <el-table-column type="selection" width="55" />
        <el-table-column property="name" label="名称" width="180" />
        <el-table-column property="displayOrder" label="显示顺序" width="180" />
        <el-table-column property="description" label="描述">
          <template #default="scope">
            <span>{{ scope.row.description !== 'null' ? scope.row.description : "" }}</span>
          </template>
        </el-table-column>
        <el-table-column property="enabled" label="是否启用" width="100">
          <template #default="scope">
            <el-switch v-model="scope.row.enabled" @click="page.toggleEnabled(scope.row)" />
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="280">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id"
                @click="page.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="page.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">编辑</el-link>
              <el-link type="danger" @click="deleteRow(scope.row.id)"
              :disabled="tableName === 'age_group'">删除</el-link>
              <el-link type="primary" @click="page.moveUp(scope.row)">上移</el-link>
              <el-link type="primary" @click="page.moveDown(scope.row)">下移</el-link>
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

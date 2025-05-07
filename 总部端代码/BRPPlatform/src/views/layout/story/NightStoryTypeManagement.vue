<script setup>
import {ref} from 'vue';
import CompanyDetail from './NightStoryTypeDetail.vue';
import management from '@/js/management';
import common from '@/js/common';

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();

const list = management({
  tableName: 'night_story_type',
  orderBy: 'display_order',
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
        tableName: 'night_story_type'
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
    <CompanyDetail ref="refDetail" @reload-data="list.loadData"/>
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row  :gutter="20" type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="名称" prop="name">
              <el-input placeholder="请输入..." v-model="list.parameters.name" @keydown.enter.prevent=list.query></el-input>
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
                type="warning" show-icon/>
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="list.openDetailDialog({ mode:'add'})">
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
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%" border
                @selection-change="list.selectRow">
        <el-table-column type="selection" width="55"/>
        <el-table-column property="name" label="名称"/>
        <el-table-column property="dayCount" sortable label="天数"/>
        <el-table-column property="price" sortable label="价格"/>
        <el-table-column property="maxSubscribeCount" sortable label="最大订阅次数"/>
        <el-table-column property="displayOrder" sortable label="排序"/>
        <el-table-column property="createTimeText" label="创建时间" sortable/>
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id"
                       @click="list.openDetailDialog({ mode:'view',primaryKeyValue:scope.row.id})">查看
              </el-link>
              <el-link type="primary" :id="scope.row.id"
                       @click="list.openDetailDialog({mode: 'edit', primaryKeyValue:scope.row.id})">编辑
              </el-link>
              <el-link type="danger" @click="deleteRow(scope.row.id)">删除</el-link>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:current-page="list.currentPage.value" v-model:page-size="list.pageSizeComputed.value"
                     :page-sizes="list.pageSizes" :page-count="list.pageCount.value" :layout="list.layout"
                     :total="list.recordCount.value" style="margin-top: 20px"/>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.el-row{
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

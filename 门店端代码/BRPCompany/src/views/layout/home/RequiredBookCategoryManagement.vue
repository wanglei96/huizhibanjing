<script setup>
import { ref } from 'vue'
import management from '@/js/management'
import RequiredBookCategoryDetail from './RequiredBookCategoryDetail.vue'
import RequiredBookList from './RequiredBookList.vue'
import common from '@/js/common'

//详情页组件引用
const refDetail = ref()
const refSearchForm = ref()
const refRequiredBookList = ref()


const list = management({
  tableName: 'required_book_category',
  getListServiceName: 'GetViewRequiredBookCategoryList',
  orderBy: 'display_order',
  refDetail,
  processDataItem(dataItem) {
    if (dataItem.imageFilePath) {
      dataItem.imageFilePath = common.getAdminUrl(dataItem, 'imageFilePath')
    } else {
      delete dataItem.imageFilePath
    }
    return dataItem
  },
  processDataList(resDataList) {
    return common.getTreeData({
      dataList: resDataList
    })
  }
})
</script>
<template>
  <div class="basic-list">
    <RequiredBookCategoryDetail ref="refDetail" @reload-data="list.loadData" />
    <RequiredBookList ref="refRequiredBookList" @reload-data="list.loadData" />
    <el-card shadow="never">
      <el-alert :title="`已选择 ${list.selectedColumnCount.value} 项`" v-show="list.selectedColumnCount.value > 0"
        type="warning" show-icon />
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
          <el-col :span="6"></el-col>
        </el-row>
      </el-form>
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%" border
        @selection-change="list.selectRow" default-expand-all
        :tree-props="{ children: 'children', hasChildren: 'hasChildren' }" row-key="id">
        <el-table-column property="name" label="名称" />
        <el-table-column property="imageFilePath" label="图标" width="150" align="center">
          <template #default="scope">
            <div>
              <el-image :src="scope.row.imageFilePath" fit="contain" :preview-src-list="[scope.row.imageFilePath]"
                preview-teleported style="width: 50px; height: 50px">
                <template #error>
                  <div class="image-slot">
                    <el-icon>
                      <Picture />
                    </el-icon>
                  </div>
                </template>
              </el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="displayOrder" label="排序" width="100" sortable />
        <el-table-column label="是否启用" width="100">
          <template #default="scope">
            {{ scope.row.enabled ? '是' : '否' }}
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="230">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id" @click="refRequiredBookList.init({ data: scope.row })">图书管理
              </el-link>
              <el-link type="warning" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看
              </el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">编辑
              </el-link>
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

.el-input {
  width: 200px;
}
</style>
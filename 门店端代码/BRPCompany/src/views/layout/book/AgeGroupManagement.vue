<script setup>
import { ref } from 'vue';
import BatchDisposeBookTag from './BatchDisposeBookTag.vue';
import BatchDisposeBook from './BatchDisposeBook.vue';
import management from '@/js/management';

// 声明批量处理标签页
const batchDisposeBookTagRef = ref()
// 声明批量处理书籍页
const batchDisposeBookRef = ref()

const list = management({
  getListServiceName: 'GetAgeGroupInfoListNew',
  processDataItem(dataItem) {
    if (dataItem.bookTagNames) {
      dataItem.bookTagNamesList = dataItem.bookTagNames.split(',');
    }
    return dataItem;
  }
})
function reLoadData() {
  list.loadData({
    processDataItem(dataItem) {
      if (dataItem.bookTagNames) {
        dataItem.bookTagNamesList = dataItem.bookTagNames.split(',');
      }
      return dataItem;
    }
  });
}

</script>
<template>
  <div class="basic-list">
    <BatchDisposeBookTag ref="batchDisposeBookTagRef" @reload-data="reLoadData" />
    <BatchDisposeBook ref="batchDisposeBookRef" @reload-data="reLoadData" />
    <el-card shadow="never">
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%">
        <el-table-column property="ageGroupName" sortable label="年龄段" width="180" />
        <el-table-column property="skuCount" label="SKU数" />
        <el-table-column property="bookCount" label="图书数量" />
        <el-table-column property="bookTagNames" label="下属标签" width="300">
          <template #default="scope">
            <div>
              <el-tag v-for="item in scope.row.bookTagNamesList" :key="item" type="info" size="small"
                style="margin-left: 10px;">{{ item }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="languageName" label="分类" />
        <el-table-column label="操作" align="center" width="240">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.ageGroupCode"
                @click="batchDisposeBookTagRef.init(scope.row)">批量修改标签</el-link>
              <el-link type="primary" :id="scope.row.ageGroupCode"
                @click="batchDisposeBookRef.init({ type: 'delete', item: scope.row })">批量删除图书</el-link>
            </el-space>
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="primary" :id="scope.row.ageGroupCode"
                @click="batchDisposeBookRef.init({ type: 'add', item: scope.row })">批量添加图书</el-link>
              <el-link type="warning" :id="scope.row.ageGroupCode"
                @click="batchDisposeBookRef.init({ type: 'view', item: scope.row })">查询现有图书</el-link>
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
</style>

<script setup>
import { ref } from 'vue';
import BookSKUDetail from './BookSKUDetail.vue'
import ApproveSKU from './ApproveSKU.vue'
import management from '@/js/management';
import common from '@/js/common';
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'


//详情页组件引用
const refDetail = ref();
const refApproveSKU = ref();
const refSearchForm = ref();

//parameters:{approveStatusCode:"Approving"},
const list = management({
  tableName: 'book_sku',
  orderBy: 'create_time desc',
  getListServiceName: 'GetBookSkuInfoList',
  condition: "company_id IS NOT NULL AND  ISBN NOT IN (SELECT ISBN FROM book_sku  WHERE company_id IS NULL) " +
    " AND approve_status_code is null ",
  refDetail,
  processDataItem(dataItem) {
    if (dataItem.imageFilePath) {
      dataItem.imageFilePath = common.getAdminUrl(dataItem, 'imageFilePath');
    }
    return dataItem;
  }
})

function rejectRow(id) {
  ElMessageBox.confirm('确定要删除所选数据吗？', '温馨提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(() => {
      let params = {
        "id": id,
        "approveStatusCode": "Rejected"
      };
      common.call("UpdateBookSku", params, function (result) {
        list.loadData();
      })
    })
    .catch((error) => {
      throw new Error(error.message)
    })
}
</script>
<template>
  <div class="basic-list">
    <BookSKUDetail ref="refDetail" @reload-data="list.loadData" />
    <ApproveSKU ref="refApproveSKU" @reload-data="list.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书名" prop="bookName">
              <el-input placeholder="请输入..." v-model="list.parameters.bookName"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="作者" prop="author">
              <el-input placeholder="请输入..." v-model="list.parameters.author"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <!-- <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="门店名称" prop="companyName">
              <el-input placeholder="请输入..." v-model="list.parameters.companyName"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col> -->
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="门店" prop="companyId">
              <DatabaseSelect table-name="company" v-model="list.parameters.companyId" valueFieldName="id"
                textFieldName="company_name" placeholder="请选择门店" class="form-item-width" />
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
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%"
        @selection-change="list.selectRow">
        <el-table-column type="selection" width="55" />
        <el-table-column property="avator" label="封面" width="100" align="center">
          <template #default="scope">
            <el-image style="width: 100px; height: 100px" :src="scope.row.imageFilePath" fit="contain">
              <template #error>
                <div class="image-slot">
                  <el-icon>
                    <Picture />
                  </el-icon>
                </div>
              </template>
            </el-image>
          </template>
        </el-table-column>
        <el-table-column property="bookName" label="书名" width="300">
          <template #default="scope">
            <div>
              <span style="color: #008000;">{{ scope.row.bookSeriesName ? "[" + scope.row.bookSeriesName + "]" : ""
                }}</span>
              <span>{{ scope.row.bookName }}</span>
              <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
                }}</span>
            </div>
            <div class="list-item-title_3">ISBN:{{ scope.row.ISBN }}
            </div>
          </template>
        </el-table-column>
        <el-table-column property="languageName" label="分类" />
        <el-table-column property="" label="年龄段&标签">
          <template #default="scope">
            <div>
              <span>{{ scope.row.ageGroupNames }}</span>
            </div>
            <div>
              <span>{{ scope.row.bookTagNames }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="companyName" label="提交门店" />
        <el-table-column label="操作" align="center" width="200">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="refApproveSKU.init({ id: scope.row.id })">审核入库</el-link>
              <el-link type="danger" @click="rejectRow(scope.row.id)">删除</el-link>
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

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.el-form-item {
  margin-bottom: 0px;
}

.user-search-card {
  margin-bottom: 15px;

  .user-form {
    .el-form-item {
      margin-bottom: 10px;
    }
  }
}

.task {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .task_desc {
    color: #999;
    font-size: 14px;
    margin-bottom: 5px;
  }

  .task_title {
    font-size: 22px;
  }
}

.list-item-title_3 {
  color: #337ab7;
}
</style>
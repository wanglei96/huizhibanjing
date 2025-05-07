<script setup>
import { reactive, ref } from 'vue'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import ImageUpload from '@/components/extension/ImageUpload.vue'
import management from '@/js/management';

//定义事件发射器
const emit = defineEmits(['reload-data'])
//详情页开关
const detailFormVisible = ref(false)

const isLoading = ref()
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
const list = ref([]);
const title = ref('');
const init = (options) => {
  detailFormVisible.value = true
  list.value = management({
    tableName: 'book_sku',
    orderBy: 'create_time desc',
    getListServiceName: 'GetBookSkuInfoList',
    pageSize: 5,
    parameters: {
      companyId: companyId.value
    },
    condition: `id not in (SELECT book_sku_id FROM required_book WHERE required_book_category_id = '${options.requiredBookCategoryId}')`,
    processDataItem(dataItem) {
      if (dataItem.imageFilePath) {
        dataItem.imageFilePath = common.getAdminUrl(dataItem, 'imageFilePath');
      }
      return dataItem;
    }
  })
}

//暴露
defineExpose({
  init
})

const refSearchForm = ref();

const setBooks = inject('setBooks');

//提交表单
function confirm() {
  setBooks(list.value.selectRowValueArray)
  console.log("selectRowValueArray:", list.value.selectRowValueArray)
  detailFormVisible.value = false
}

</script>
<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" title="图书列表" width="1200"
      draggable>
      <el-card shadow="never" v-model="detailFormVisible">
        <el-form ref="refSearchForm" :model="list.parameters" label-width="80px" v-if="detailFormVisible">
          <el-row type="flex" :gutter="20">
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item label="ISBN" prop="ISBN">
                <el-input placeholder="请输入..." v-model="list.parameters.ISBN"
                  @keydown.enter.prevent=list.query></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item label="书名" prop="bookName">
                <el-input placeholder="请输入..." v-model="list.parameters.bookName"
                  @keydown.enter.prevent=list.query></el-input>
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
        <el-alert :title="`已选择 ${list.selectedColumnCount} 项`" v-show="list.selectedColumnCount > 0" type="warning"
          show-icon />
        <el-table ref="multipleTableRef" :data="list.dataList" style="width: 100%" @selection-change="list.selectRow"
          :row-key="row => row.id">
          <el-table-column type="selection" width="55" :reserve-selection="true" />
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
          <el-table-column property="bookName" label="书名" />
          <el-table-column property="bookSeriesName" label="系列" />
          <el-table-column property="ISBN" label="ISBN" />
        </el-table>
        <el-pagination v-model:current-page="list.currentPage" v-model:page-size="list.pageSizeComputed"
          :page-sizes="list.pageSizes" :page-count="list.pageCount" :layout="list.layout" :total="list.recordCount"
          style="margin-top: 20px" />
      </el-card>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" :loading="isLoading" @click="confirm()">
            确定
          </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.form-item-width {
  width: 100% !important;
}

.user-search-card {
  margin-bottom: 15px;

  .user-form {
    .el-form-item {
      margin-bottom: 10px;
    }
  }
}

.el-dialog {
  width: 500px;
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

.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 300px;
}

.el-input {
  width: 300px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
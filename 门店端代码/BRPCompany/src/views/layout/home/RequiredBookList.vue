<script setup>
import { reactive, ref } from 'vue'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import ImageUpload from '@/components/extension/ImageUpload.vue'
import management from '@/js/management';
import SelectedBookSku from './SelectedBookSku.vue'

//定义事件发射器
const emit = defineEmits(['reload-data'])
//详情页开关
const detailFormVisible = ref(false)
const refSelectedBookSku = ref()
const isLoading = ref()
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
const list = ref([]);
const title = ref('');
const requiredBookCategoryId = ref();
const init = (options) => {
  detailFormVisible.value = true
  title.value = options.data.name+"书单"
  requiredBookCategoryId.value = options.data.id
  list.value = management({
    tableName: 'required_book',
    orderBy: 'create_time desc',
    getListServiceName: 'GetViewRequiredBookList',
    pageSize: 5,
    parameters: {
      requiredBookCategoryId: options.data.id
    },
    processDataItem(dataItem) {
      if (dataItem.bookSkuImageFilePath) {
        dataItem.bookSkuImageFilePath = common.getAdminUrl(dataItem, 'bookSkuImageFilePath')
      } else {
        delete dataItem.bookSkuImageFilePath
      }
      return dataItem
    }
  })
}

//暴露
defineExpose({
  init
})

const refSearchForm = ref();
provide('setBooks', setBooks);

const newBookList = ref([])
function setBooks(dataList) {
  dataList.forEach(item => {
    // 判断item是否存在于newBookList中
    if (!newBookList.value.some(book => book.id === item.id)) {
      newBookList.value.push(item);
    }
  });
}
const newPrimaryKeyValueArray = ref([]);
const newSelectRow = (val) => {
  newPrimaryKeyValueArray.value = []
  val.forEach((item) => {
    newPrimaryKeyValueArray.value.push(item.id)
  })
}
const newSelectedColumnCount = computed(() => {
  return newPrimaryKeyValueArray.value.length
})

/* const deleteRows = () => {
  debugger
  list.value.dataList = list.value.dataList.filter(book => !list.value.primaryKeyValueArray.includes(book.id));
  //newPrimaryKeyValueArray.value = []
} */

const newDeleteRows = () => {
  newBookList.value = newBookList.value.filter(book => !newPrimaryKeyValueArray.value.includes(book.id));
  newPrimaryKeyValueArray.value = []
}
//提交表单
function confirm() {
  //获取newBookSKUIDS
  let bookSkuIds = newBookList.value.map(book => book.id);
  let params = {
    bookSkuIds: bookSkuIds,
    requiredBookCategoryId: requiredBookCategoryId.value
  }
  common.call("BatchAddRequiredBook", params, function (res) {
    list.value.loadData()
    newBookList.value = []
  })
}

watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    newBookList.value = []
    newPrimaryKeyValueArray.value = []
  }
})

</script>
<template>
  <div>
    <SelectedBookSku ref="refSelectedBookSku" />
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" :title="title" width="1200"
      draggable>
      <el-card shadow="never" v-model="detailFormVisible" class="book-card">
        <template #header>
          <div>
            <span>图书列表</span>
          </div>
        </template>
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
        <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
          <el-row>
            <el-col :span="18">
              <el-button type="danger" @click="list.deleteRows">
                <el-icon tyle="margin-right: 3px">
                  <Delete />
                </el-icon>
                删除
              </el-button>
            </el-col>
            <el-col :span="6"></el-col>
          </el-row>
        </el-form>
        <el-table ref="multipleTableRef" :data="list.dataList" style="width: 100%" @selection-change="list.selectRow"
          :row-key="row => row.id">
          <el-table-column type="selection" width="55" :reserve-selection="true" />
          <el-table-column property="avator" label="封面" width="100" align="center">
            <template #default="scope">
              <el-image style="width: 100px; height: 100px" :src="scope.row.bookSkuImageFilePath" fit="contain">
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
      <el-card shadow="never" v-model="detailFormVisible" class="new-book-card">
        <template #header>
          <div>
            <span>新增列表</span>
          </div>
        </template>
        <el-alert :title="`已选择 ${newSelectedColumnCount} 项`" v-show="newSelectedColumnCount > 0" type="warning"
          show-icon />
        <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
          <el-row>
            <el-col :span="18">
              <el-button type="primary"
                @click="refSelectedBookSku.init({ requiredBookCategoryId: requiredBookCategoryId })">
                <el-icon style="margin-right: 3px">
                  <Plus />
                </el-icon>
                添加
              </el-button>
              <el-button type="danger" @click="newDeleteRows">
                <el-icon tyle="margin-right: 3px">
                  <Delete />
                </el-icon>
                删除
              </el-button>
            </el-col>
          </el-row>
        </el-form>
        <el-table ref="multipleTableRef" :data="newBookList" style="width: 100%" @selection-change="newSelectRow"
          :row-key="row => row.id">
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
          <el-table-column property="bookName" label="书名" />
          <el-table-column property="bookSeriesName" label="系列" />
          <el-table-column property="ISBN" label="ISBN" />
        </el-table>
        <!--  <el-pagination v-model:current-page="list.currentPage" v-model:page-size="list.pageSizeComputed"
          :page-sizes="list.pageSizes" :page-count="list.pageCount" :layout="list.layout" :total="list.recordCount"
          style="margin-top: 20px" /> -->
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
<style>
.book-card {
  box-shadow: 0 0 0 1px #dff0d8;

  .el-card__header {
    background-color: #dff0d8;
  }
}

.new-book-card {
  box-shadow: 0 0 0 1px #f5e3e3;

  margin-top: 20px;

  .el-card__header {
    background-color: #f5e3e3;
  }
}
</style>
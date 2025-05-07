<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import common from '@/js/common'

let list = ref({});

// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

const emit = defineEmits(['reload-data']);

const refSearchForm = ref();
const languageList = ref([]);
const ageGroupList = ref([]);
const bookTagList = ref([]);
const bookSeriesList = ref([]);

const msg = ref()

const detailFormVisible = ref(false);

const condition = ref();

const isShowAddBtn = ref(true);

const isShowDeleteBtn = ref(true);

const bookcaseData = ref({});

const parameters = reactive({});


const init = (options) => {
  if (options.mode == "view") {
    condition.value = "bookcase_id=" + options.data.id;
    isShowAddBtn.value = false;
    isShowDeleteBtn.value = true;
    msg.value = "当前书柜中所有图书列表";
  } else if (options.mode == "add") {
    condition.value = "bookcase_id is null";
    isShowAddBtn.value = true;
    isShowDeleteBtn.value = false;
    msg.value = "批量添加无书柜号图书";
  }
  bookcaseData.value = options.data;
  list.value = management({
    tableName: 'book',
    orderBy: 'create_time desc',
    getListServiceName: 'GetViewBookList',
    condition: condition.value,
    getParameters() {
      let params = {}
      Object.assign(params, parameters)
      params.companyId = companyId.value;
      params.fields = "id,sn,book_name,book_series_name"
      if (bookTagSelectValue.value.length > 0) {
        params.bookTagSelectValue = bookTagSelectValue.value.join(',');
      }
      if (ageGroupSelectValue.value.length > 0) {
        params.ageGroupSelectValue = ageGroupSelectValue.value.join(',');
      }
      return params;
    },
  })
  detailFormVisible.value = true;
  //获取语言分类下拉框
  getSelected({
    tableName: 'language',
    selectedList: languageList,
  });
  //获取年龄段下拉框
  getSelected({
    tableName: 'age_group',
    selectedList: ageGroupList,
  });
  //获取系列下拉框
  getSelected({
    tableName: 'book_series',
    selectedList: bookSeriesList,
    condition: "company_id=" + companyId.value
  });
  //获取标签下拉框
  getSelected({
    tableName: 'book_tag',
    selectedList: bookTagList,
    condition: "company_id=" + companyId.value
  });
}
//获取下拉框
function getSelected(options) {
  let param = {
    "tableName": options.tableName
  };
  if (options.condition) {
    param.condition = options.condition;
  }
  common.call("GetTable", param, function (result) {
    options.selectedList.value = result.data;
  })
}

const ageGroupSelectValue = ref([])

const bookTagSelectValue = ref([])

/**
 * 添加图书
 */
function batchAddBook() {
  if (list.value.primaryKeyValueArray.length === 0) {
    ElMessage({ message: "请先选择要添加的数据", type: "warning" });
  } else {
    let param = {
      bookIds: list.value.primaryKeyValueArray,
      bookcaseId: bookcaseData.value.id,
    }
    common.call("BatchAddBookBookcaseId", param, function (result) {
      ElMessage({ message: "添加成功", type: "success" });
      list.value.loadData();
      //detailFormVisible.value = false;
    })
  }
}
/**
 * 删除书柜图书
 */
function batchDeleteBooks() {
  if (list.value.primaryKeyValueArray.length === 0) {
    ElMessage({ message: "请先选择要删除的数据", type: "warning" });
  } else {
    let param = {
      bookIds: list.value.primaryKeyValueArray,
      bookcaseId: bookcaseData.value.id,
    }
    common.call("BatchDeleteBookInBookcase", param, function (result) {
      ElMessage({ message: "删除成功", type: "success" });
      list.value.loadData();
      //detailFormVisible.value = false;
    })
  }
}

//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    emit('reload-data')
    ageGroupSelectValue.value = []
    bookTagSelectValue.value = []
  }
})
const resetForm = (form) => {
  form.resetFields()
  ageGroupSelectValue.value = []
  bookTagSelectValue.value = []
}
defineExpose({
  init
})
</script>
<template>
  <div class="basic-list">
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" :title="msg" width="1200" draggable>
      <div style="font-size: 20px;margin-bottom: 5px;">当前书柜号：{{ bookcaseData.name }}</div>
      <el-card shadow="never" class="search-card">
        <el-form ref="refSearchForm" :model="parameters">
          <el-row  type="flex" :gutter="20">
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item label="书名" prop="bookName">
                <el-input placeholder="请输入..." v-model="parameters.bookName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item label="系列" prop="bookSeriesId">
                <el-select placeholder="请选择" v-model="parameters.bookSeriesId" filterable>
                  <el-option v-for="item in bookSeriesList" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item label="标签" prop="bookTagSelectValue">
                <el-select v-model="bookTagSelectValue" multiple clearable placeholder="请选择标签" filterable>
                  <el-option v-for="item in bookTagList" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item label="年龄段" prop="ageGroupSelectValue">
                <el-select v-model="ageGroupSelectValue" multiple clearable placeholder="请选择年龄段" filterable>
                  <el-option v-for="item in ageGroupList" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-top: 10px;">
              <el-form-item>
                <el-button type="primary" @click='list.query'>搜索</el-button>
                <el-button @click='resetForm(refSearchForm)'>重置</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
      <el-card shadow="never">
        <el-alert :title="`已选择 ${list.selectedColumnCount} 项`" v-show="list.selectedColumnCount > 0" type="warning"
          show-icon />
        <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
          <el-row>
            <el-col :span="18">
              <el-button type="primary" @click="batchAddBook" v-if="isShowAddBtn">
                <el-icon style="margin-right: 3px">
                  <Plus />
                </el-icon> 添加</el-button>
              <el-button type="danger" @click="batchDeleteBooks" v-if="isShowDeleteBtn">
                <el-icon tyle="margin-right: 3px">
                  <Delete />
                </el-icon> 删除
              </el-button>
            </el-col>
            <el-col :span="6">
            </el-col>
          </el-row>
        </el-form>
        <el-table ref="multipleTableRef" :data="list.dataList" style="width: 100%" @selection-change="list.selectRow">
          <el-table-column type="selection" width="55" />
          <el-table-column property="bookName" label="书名" width="300" />
          <el-table-column property="bookSeriesName" label="系列" width="300" />
          <!-- <el-table-column property="bindingTypeName" label="装订方式" /> -->
          <el-table-column property="sn" label="图书编号" />
        </el-table>
        <el-pagination v-model:current-page="list.currentPage" v-model:page-size="list.pageSizeComputed"
          :page-sizes="list.pageSizes" :page-count="list.pageCount" :layout="list.layout" :total="list.recordCount"
          style="margin-top: 20px" />
      </el-card>
    </el-dialog>
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
<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import common from '@/js/common'
import { ElMessage } from 'element-plus';

let list = ref({});

// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
const refreshList = inject('getDataList');

const refSearchForm = ref();
const bookSeriesList = ref([]);

const msg = ref()

const detailFormVisible = ref(false);

const condition = ref();

const addBookOrderItemList = inject('addBookOrderItemList');
//定义事件发射器
const emit = defineEmits(['reload-data'])

const mode = ref('');
const currentRadio = ref();

const init = (options) => {
  mode.value = options.mode;
  if (options.mode == "borrow") {
    condition.value = "is_available is true";
    msg.value = "借书列表";
  } else if (options.mode == "return") {
    condition.value = "borrow_status_code = 'Out' and member_id =" + options.memberId;
    msg.value = "还书列表";
  }
  list.value = management({
    tableName: 'book',
    orderBy: 'create_time desc',
    getListServiceName: 'GetViewBookList',
    condition: condition.value,
    pageSize: 5,
    pageSizes: [5, 10, 30, 50]
  })
  detailFormVisible.value = true;
  //获取系列下拉框
  getSelected({
    tableName: 'book_series',
    selectedList: bookSeriesList,
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

//多选选中数量
const selectedColumnCount = computed(() => {
  return selectValueArray.value.length
})
const selectValueArray = ref([])
//多选
const selectRow = (val) => {
  selectValueArray.value = []
  val.forEach((item) => {
    selectValueArray.value.push(item)
  })
}

const currentRow = ref();
const handleCurrentChange = (row) => {
  currentRow.value = row;
  currentRadio.value = row?.id;
  console.log(currentRadio.value)
};

function batchAddBook() {
  if (mode.value === 'Radio') {
    emit('reload-data', { type: 'book', data: currentRow.value });
  } else {
    if (!selectValueArray.value.length) {
      ElMessage({ message: "请至少选择一条数据", type: 'warning' })
      return;
    }
    addBookOrderItemList({ dataList: selectValueArray.value, type: mode.value });
  }

  detailFormVisible.value = false;
}


//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    //refreshList();
    selectValueArray.value = []
  }
})
defineExpose({
  init
})
</script>

<template>
  <div class="basic-list">
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" :title="msg" width="800"
      draggable>
      <el-card shadow="never" class="search-card">
        <el-form ref="refSearchForm" :model="list.parameters">
          <el-row type="flex" :gutter="20">
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item label="书名" prop="bookName">
                <el-input placeholder="请输入..." v-model="list.parameters.bookName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item label="书号" prop="sn">
                <el-input placeholder="请输入..." v-model="list.parameters.sn"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item label="系列" prop="bookSeriesId">
                <el-select placeholder="请选择" v-model="list.parameters.bookSeriesId" filterable>
                  <el-option v-for="item in bookSeriesList" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
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
        <el-alert v-if="mode !== 'Radio'" :title="`已选择 ${selectedColumnCount} 项`" v-show="selectedColumnCount > 0"
          type="warning" show-icon />
        <el-form :inline="true">
          <el-row>
            <el-col :span="18">
              <el-button type="primary" @click="batchAddBook">
                <el-icon style="margin-right: 3px">
                  <Plus />
                </el-icon> 添加</el-button>
            </el-col>
          </el-row>
        </el-form>
        <el-table ref="multipleTableRef" :data="list.dataList" style="width: 100%" @selection-change="selectRow"
          @current-change="handleCurrentChange">
          <el-table-column width="55" v-if="mode === 'Radio'">
            <template #default="scope">
              <el-radio v-model="currentRadio" :label="scope.row.id">
                <span></span>
              </el-radio>
            </template>
          </el-table-column>
          <el-table-column type="selection" v-else width="55" />
          <el-table-column property="bookName" label="书名" />
          <el-table-column property="sn" label="图书编号" />
          <el-table-column property="bookSeriesName" label="系列" />
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
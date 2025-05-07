<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import common from '@/js/common'

let list = ref({});
const allBookcaseIds = ref([]);


const detailFormVisible = ref(false);

//定义事件发射器
const emit = defineEmits(['reload-data']);

//确定按钮是否加载中
const isLoading = ref(false);


const init = (bookcaseIds) => {
  if (bookcaseIds.length < 2) {
    ElMessage({ message: "请至少选择两个书柜进行合并！", type: 'warning' })
    return;
  }
  allBookcaseIds.value = bookcaseIds;
  const strBookcaseIds = bookcaseIds.join(",");
  list.value = management({
    tableName: 'bookcase',
    orderBy: 'create_time desc',
    getListServiceName: 'GetBookcaseList',
    condition: 'id in (' + strBookcaseIds + ')',
    parameters: {
      fields: "*,(select count(1) from book where bookcase.id = book.bookcase_id) as book_count"
    },
  })
  detailFormVisible.value = true;
}

const multipleTableRef = ref(null)

const currentRow = ref();
const currentRadio = ref();

const handleCurrentChange = (row) => {
  currentRow.value = row;
  currentRadio.value = row?.id;
  console.log(currentRadio.value)
};

function combine() {
  if (!currentRadio.value) {
    ElMessage({ message: "请选择一个要保留的标签！", type: 'warning' })
    return;
  }
  isLoading.value = true;

  let deleteBookcaseIds = allBookcaseIds.value.filter((item) => item !== currentRadio.value);
  let params = {
    deleteBookcaseIds: deleteBookcaseIds,
    keepBookcaseId: currentRadio.value
  }
  common.call('CombineBookcase', params, (res) => {
    setTimeout(() => {
      isLoading.value = false;
    }, 310);
    ElMessage({ message: "操作成功", type: 'success' })
    detailFormVisible.value = false;
    emit('reload-data');
  })
}

defineExpose({
  init
})
</script>
<template>
  <div class="basic-list">
    <el-dialog :close-on-click-modal="false" v-model="detailFormVisible" title="合并书柜" width="1200" draggable>
      <div style="margin-bottom: 20px;font-size: 15px;">
        <span>选择一个要保留的书柜，其他的书柜将会被删除，被删除书柜下的图书都将转移到保留的书柜下。</span>
      </div>
      <el-card shadow="never">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" :loading="isLoading" @click="combine">合并</el-button>
          </el-col>
        </el-row>
        <el-table :ref="multipleTableRef" :data="list.dataList" style="width: 100%"
          @current-change="handleCurrentChange" :row-key="row => row.id">
          <el-table-column width="55" :reserve-selection="true">
            <template #default="scope">
              <el-radio v-model="currentRadio" :label="scope.row.id">
                <span></span>
              </el-radio>
            </template>
          </el-table-column>
          <el-table-column property="name" label="书柜名称" align="center" />
          <el-table-column property="bookCount" label="图书数量" align="center" />
          <el-table-column property="createTimeText" label="添加时间" align="center" />
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
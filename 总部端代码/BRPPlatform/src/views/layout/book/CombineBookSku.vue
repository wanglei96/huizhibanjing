<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import common from '@/js/common'

let list = ref({});
const allSkuIds = ref([]);

// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

const detailFormVisible = ref(false);

//定义事件发射器
const emit = defineEmits(['reload-data']);

//确定按钮是否加载中
const isLoading = ref(false);


const init = (skuIds) => {
  if (skuIds.length < 2) {
    ElMessage({ message: "请至少选择两条SKU进行合并！", type: 'warning' })
    return;
  }
  allSkuIds.value = skuIds;
  const strSkuIds = skuIds.join(",");
  list.value = management({
    tableName: 'book_sku',
    orderBy: 'create_time desc',
    getListServiceName: 'GetViewBookSkuList',
    condition: 'id in (' + strSkuIds + ')',
    processDataItem(dataItem) {
      if (dataItem.imageFilePath) {
        dataItem.imageFilePath = common.getAdminUrl(dataItem, 'imageFilePath');
      }
      return dataItem;
    }
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
    ElMessage({ message: "请选择一个要保留的SKU！", type: 'warning' })
    return;
  }
  isLoading.value = true;
  setTimeout(() => {
    isLoading.value = false;
  }, 310);
  let deleteSkuIds = allSkuIds.value.filter((item) => item !== currentRadio.value);
  let params = {
    deleteSkuIds: deleteSkuIds,
    keepSkuId: currentRadio.value
  }
  common.call('CombineBookSku', params, (res) => {
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
    <el-dialog :close-on-click-modal="false" v-model="detailFormVisible" title="合并SKU" width="1200" draggable >
      <div style="margin-bottom: 20px;font-size: 15px;"><span>选择一个要保留的SKU，其他的SKU将会被删除，被删除SKU的库存都将转移到保留的SKU下。</span>
        <span style="color: red;">注意!!!请务必保证是SKU重复才进行合并操作!此操作不可恢复，请谨慎操作!!!</span>
      </div>
      <el-card shadow="never">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" :loading="isLoading" @click="combine">合并</el-button>
          </el-col>
        </el-row>
        <el-table :ref="multipleTableRef" :data="list.dataList" style="width: 100%" @current-change="handleCurrentChange">
          <el-table-column width="55">
            <template #default="scope">
              <el-radio v-model="currentRadio" :label="scope.row.id">
                <span></span>
              </el-radio>
            </template>
          </el-table-column>
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
          <el-table-column property="bookName" label="书名" width="300" />
          <el-table-column property="press" label="出版社" />
          <el-table-column property="author" label="作者" />
          <el-table-column property="ISBN" label="ISBN" />
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
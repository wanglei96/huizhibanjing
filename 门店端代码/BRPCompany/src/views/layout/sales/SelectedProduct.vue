<script setup>
import { ref } from 'vue'
import management from '@/js/management'
import config from '@/js/config'
import common from '@/js/common'

//详情页组件引用
const refSearchForm = ref()
let list = ref({});

const detailFormVisible = ref(false);
const selectedRowKeys = ref([]);
const selectRowList = ref([]);
//定义事件发射器
const emit = defineEmits(['reload-data'])

const init = (salesOrderItemList) => {
  selectedRowKeys.value = salesOrderItemList.map(item => item.productId)
  list.value = management({
    tableName: 'product',
    orderBy: 'create_time desc',
    getListServiceName: 'GetProductList',
    pageSize: 5
  })
  detailFormVisible.value = true;
  // 设置 selectRowList，根据已选中的 salesOrderItemList

}

const selectRow = (val) => {
  selectRowList.value = val;
}
//多选选中数量
const selectedColumnCount = computed(() => {
  return selectRowList.value.length
})

function submit() {
  //获取所有选中的行
  emit('reload-data', selectRowList.value);
  detailFormVisible.value = false;
}

defineExpose({
  init
})

</script>
<template>
  <div class="basic-list">
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" title="添加商品" width="800"
      draggable>
      <el-card shadow="never" class="search-card">
        <el-form ref="refSearchForm" :model="list.parameters">
          <el-row :gutter="20" type="flex">
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item label="商品名称" prop="name">
                <el-input placeholder="请输入..." v-model="list.parameters.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item>
                <el-button type="primary" @click="list.query">搜索</el-button>
                <el-button @click="list.resetForm(refSearchForm)">重置</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
      <el-card shadow="never">
        <el-alert :title="`已选择 ${selectedColumnCount} 项`" v-show="selectedColumnCount > 0" type="warning" show-icon />
        <el-table ref="multipleTableRef" :data="list.dataList" style="width: 100%" border @selection-change="selectRow"
          :default-selection="selectedRowKeys" :row-key="row => row.id">
          <el-table-column type="selection" width="55" />
          <el-table-column property="name" sortable label="商品名称" />
          <el-table-column property="price" sortable label="价格" align="center" />
          <el-table-column property="description" label="描述" />
        </el-table>
        <el-pagination v-model:current-page="list.currentPage" v-model:page-size="list.pageSizeComputed"
          :page-sizes="list.pageSizes" :page-count="list.pageCount" :layout="list.layout" :total="list.recordCount"
          style="margin-top: 20px" />
      </el-card>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click.prevent="submit()"> 确定 </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
      </template>
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
</style>
<style lang="scss" scoped>
.avatar-uploader .avatar {
  width: 300px;
  height: 300px;
  display: block;
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

:deep(.pay-dialog .el-dialog__body) {
  .pay-code {
    display: flex;
    align-items: center;
    justify-content: center;

    .el-image {
      width: 200px;
    }
  }

  .pay-amount {
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 20px;
    color: red;
    text-align: center;
  }
}

:deep(.pay-dialog .el-dialog__header) {
  text-align: center;
}
</style>
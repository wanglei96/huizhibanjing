<script setup>
import { ref } from 'vue';
import management from '@/js/management';
import common from '@/js/common'

let list = ref({});
const allTagIds = ref([]);


const detailFormVisible = ref(false);

//定义事件发射器
const emit = defineEmits(['reload-data']);

//确定按钮是否加载中
const isLoading = ref(false);


const init = (tagIds) => {
  if (tagIds.length < 2) {
    ElMessage({ message: "请至少选择两个标签进行合并！", type: 'warning' })
    return;
  }
  allTagIds.value = tagIds;
  const strTagIds = tagIds.join(",");
  list.value = management({
    tableName: 'book_tag',
    orderBy: 'create_time desc',
    getListServiceName: 'GetBookTagList',
    condition: 'id in (' + strTagIds + ')',
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
  setTimeout(() => {
    isLoading.value = false;
  }, 310);
  let deleteTagIds = allTagIds.value.filter((item) => item !== currentRadio.value);
  let params = {
    deleteTagIds: deleteTagIds,
    keepTagId: currentRadio.value
  }
  common.call('CombineBookTag', params, (res) => {
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
    <el-dialog :close-on-click-modal="false" v-model="detailFormVisible" title="合并标签" width="1200" draggable>
      <div style="margin-bottom: 20px;font-size: 15px;">
        <span>选择一个要保留的标签，其他的标签将会被删除，被删除标签下的SKU都将转移到保留的标签下。</span>
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
          <el-table-column property="name" label="标签名" align="center" />
          <el-table-column property="frontName" label="前台显示名称" align="center" />
          <el-table-column property="languageCode" label="分类">
            <template #default="scope">
              <span v-if="scope.row.languageCode === 'English'">英文</span>
              <span v-if="scope.row.languageCode === 'Chinese'">中文</span>
              <span v-else></span>
            </template>
          </el-table-column>
          <el-table-column property="isVisible" label="会员可见" align="center">
            <template #default="scope">
              <span v-if="scope.row.isVisible === true">可见</span>
              <span v-else>不可见</span>
            </template>
          </el-table-column>
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
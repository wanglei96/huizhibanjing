<script setup>
import { inject, reactive, ref } from 'vue'
import common from '@/js/common'


//定义事件发射器
const emit = defineEmits(['reload-data']);
//组件开关
const detailFormVisible = ref(false);
//获取SKUIDS
const bookSkuIds = ref({});
//书签列表
const bookTagList = ref([]);
//年龄段列表
const ageGroupList = ref([]);
//选中标签ID列表
const bookTagIds = ref([]);
//选中年龄段ID列表
const ageGroupIds = ref([]);
//确定按钮是否加载中
const isLoading = ref(false);
function init(skuIds) {
  if (skuIds.length == 0) {
    ElMessage({ message: "请先选择图书SKU！", type: 'warning' })
    return;
  }
  // 显示弹窗
  detailFormVisible.value = true
  bookSkuIds.value = skuIds;
  //获取标签列表
  getBookTagList();
  //获取年龄段列表
  getAgeGroupList();
}
function getBookTagList() {
  let params = {
    condition: "company_id is null",
    pageSize: 1000,
  }
  common.call("GetBookTagList", params, (res) => {
    bookTagList.value = res.data.dataList;
  });
}
function getAgeGroupList() {
  let params = {
    tableName: 'age_group'
  }
  common.call("GetTable", params, (res) => {
    ageGroupList.value = res.data;
  });
}

function save() {
  isLoading.value = true;
  setTimeout(() => {
    isLoading.value = false;
  }, 310);
  let data = {
    bookSkuIds: bookSkuIds.value,
    ageGroupIds: ageGroupIds.value,
    bookTagIds: bookTagIds.value
  };
  common.call('BatchUpdateBookSkuAgeTag', data, function (res) {
    detailFormVisible.value = false;
    ElMessage({ message: "操作成功", type: 'success' })
    emit('reload-data');
  })
}

watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    bookTagIds.value = [];
    ageGroupIds.value = [];
  }
})
defineExpose({
  init
})
</script>

<template>
  <el-dialog :close-on-click-modal="false" v-model="detailFormVisible" title="批量修改年龄段标签" width="800" draggable>
    <div style="color: red;margin-bottom: 20px;font-size: 15px;">注意!如果不勾选任何选项，点提交，则表示为清空操作!</div>
    <el-card shadow="never" class="customer-card">
      <template #header>
        <div>
          <span>选择年龄段</span>
        </div>
      </template>
      <div class="card-body">
        <el-checkbox-group v-model="ageGroupIds" size="default">
          <el-checkbox :label="item.id" border v-for="item in ageGroupList" :key="item.name" style="margin-top:10px;">{{
            item.name }}</el-checkbox>
        </el-checkbox-group>
      </div>
    </el-card>
    <el-card shadow="never" class="customer-card">
      <template #header>
        <div>
          <span>选择标签</span>
        </div>
      </template>
      <div class="card-body">
        <el-checkbox-group v-model="bookTagIds" size="default">
          <el-checkbox :label="item.id" border v-for="item in bookTagList" :key="item.name" style="margin-top:10px;">{{
            item.name }}</el-checkbox>
        </el-checkbox-group>
      </div>
    </el-card>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" :loading="isLoading" @click="save"> 确定 </el-button>
        <el-button @click="detailFormVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>


<style>
.customer-card {
  margin-bottom: 20px;
  box-shadow: 0 0 0 1px #d9edf7;

  .el-card__header {
    background-color: #d9edf7;
  }
}

.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
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

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
<script setup>
import { inject, reactive, ref } from 'vue'
import common from '@/js/common'

const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

//定义事件发射器
const emit = defineEmits(['reload-data']);
//组件开关
const detailFormVisible = ref(false);
//获取信息
const dataItem = ref({});
//书签列表
const bookTagList = ref([]);
//选中标签ID列表
const bookTagIds = ref([]);
//确定按钮是否加载中
const isLoading = ref(false);
function init(data) {
  // 显示弹窗
  detailFormVisible.value = true
  dataItem.value = data;
  //获取标签列表
  getBookTagList();
}
function getBookTagList() {
  let params = {
    ageGroupId: dataItem.value.ageGroupId,
    languageCode: dataItem.value.languageCode,
    companyId: companyId.value
  }
  common.call("GetBookTagByAgeGroupIdNew", params, (res) => {
    bookTagList.value = res.data;
    res.data.forEach(item => {
      if (item.hasBookTag == 1) {
        bookTagIds.value.push(item.id);
      }
    })
  });
}

function save() {
  isLoading.value = true;
  detailFormVisible.value = false;
  setTimeout(() => {
    isLoading.value = false;
  }, 310);
  let data = {
    ageGroupId: dataItem.value.ageGroupId,
    languageCode: dataItem.value.languageCode,
    bookTagIds: bookTagIds.value,
    companyId: companyId.value
  };
  common.call('AddAgeGroupBookTagByAgeGroupIdNew', data, function (res) {
    emit('reload-data');
  })
}
/**
 * 全选
 */
function checkAll() {
  if (bookTagIds.value.length === bookTagList.value.length) {
    bookTagIds.value.splice(0, bookTagIds.value.length);
  } else {
    bookTagIds.value.splice(0, bookTagIds.value.length);
    bookTagList.value.forEach(item => {
      bookTagIds.value.push(item.id);
    })
  }
}
watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    bookTagIds.value = [];
  }
})
defineExpose({
  init
})
</script>

<template>
  <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" title="批量修改标签" width="600" draggable>
    <div>当前年龄段：{{ dataItem.ageGroupName }}</div>
    <span class="dialog-footer">
      <el-button @click="checkAll">全选</el-button>
      <span style="margin-left: 10px;">已选择：{{ bookTagIds.length }}个</span>
    </span>
    <el-checkbox-group v-model="bookTagIds" size="large">
      <el-checkbox :label="item.id" border v-for="item in bookTagList" :key="item.name" style="margin-top:10px;">{{
        item.name }}</el-checkbox>
    </el-checkbox-group>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" :loading="isLoading" @click="save"> 确定 </el-button>
        <el-button @click="detailFormVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>
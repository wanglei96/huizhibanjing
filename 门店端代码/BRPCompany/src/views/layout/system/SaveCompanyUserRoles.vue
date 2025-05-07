<script setup>
import {ref} from 'vue';
import common from "@/js/common";
//组件开关
const detailFormVisible = ref(false);
//角色ID
const id = ref();
//角色列表
const companyRoleList = ref([]);
//选中角色ID列表
const companyRoleIds = ref([]);
//确定按钮是否加载中
const isLoading = ref(false);

function getCompanyRoleListByCompanyUserId(companyUserId) {
  id.value = companyUserId;
  detailFormVisible.value = true;
  let params = {
    userId:companyUserId,
    companyId: common.getLocalStorage('companyId')
  };
  common.call('GetCompanyRoleListByCompanyUserId', params, (res) => {
    companyRoleList.value = res.data.dataList;
    res.data.dataList.forEach(item => {
      if (item.checked) {
        companyRoleIds.value.push(item.id);
      }
    })
  })
}


function save() {
  isLoading.value = true;
  detailFormVisible.value = false;
  setTimeout(() => {
    isLoading.value = false;
  }, 310);
  let data = {
    userId: id.value,
    companyRoleIds: companyRoleIds.value
  };
  common.call('SaveCompanyUserRoles', data);
}

watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    companyRoleIds.value = [];
  }
})
//暴露
defineExpose({
  getCompanyRoleListByCompanyUserId
})
</script>

<template>
  <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" title="设置权限" width="800" draggable>
    <el-checkbox-group v-model="companyRoleIds" size="large">
      <el-checkbox :label="item.id" border v-for="item in companyRoleList" :key="item.name" style="margin-top:10px;">
        {{ item.name }}
      </el-checkbox>
    </el-checkbox-group>
    <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" :loading="isLoading" @click="save"> 确定 </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
    </template>
  </el-dialog>
</template>

<style scoped lang="scss">

</style>
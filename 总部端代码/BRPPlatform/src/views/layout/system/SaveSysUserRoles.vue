<script setup>
import { ref } from 'vue';
import common from "@/js/common";
//组件开关
const detailFormVisible = ref(false);
//角色ID
const id = ref();
//角色列表
const sysRoleList = ref([]);
//选中角色ID列表
const sysRoleIds = ref([]);
//确定按钮是否加载中
const isLoading = ref(false);

function getSysRoleListBySysUserId(sysUserId) {
  id.value = sysUserId;
  detailFormVisible.value = true;
  common.call('GetSysRoleListBySysUserId', { id: sysUserId }, (res) => {
    sysRoleList.value = res.data.dataList;
    res.data.dataList.forEach(item => {
      if (item.checked) {
        sysRoleIds.value.push(item.id);
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
    id: id.value,
    sysRoleIds: sysRoleIds.value
  };
  common.call('SaveSysUserRoles', data)
}

watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    sysRoleIds.value = [];
  }
})
//暴露
defineExpose({
  getSysRoleListBySysUserId
})
</script>

<template>
  <el-dialog :close-on-click-modal="false" v-model="detailFormVisible" title="设置权限" width="800" draggable>
    <el-checkbox-group v-model="sysRoleIds" size="large">
      <el-checkbox :label="item.id" border v-for="item in sysRoleList" :key="item.name" style="margin-top:10px;">{{
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

<style scoped lang="scss"></style>
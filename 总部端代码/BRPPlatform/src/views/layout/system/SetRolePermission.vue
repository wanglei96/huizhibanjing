<script setup>
import {ref} from 'vue';
import common from "@/js/common";
//组件开关
const detailFormVisible = ref(false);
//角色ID
const sysRoleId = ref();
//角色权限树
const rolePermissionTree = ref([]);
//当前角色权限列表(展示时使用)
const rolePermissionList = ref([]);
//当前角色权限列表(保存时使用)
const permissions = ref([]);
//角色权限树组件引用
const refRolePermissionTree = ref();
//确定按钮是否加载中
const isLoading = ref(false);

function getRolePermissionTree(id) {
  sysRoleId.value = id;
  detailFormVisible.value = true;
  common.call('GetRolePermissionTree', {sysRoleId: id}, (res) => {
    rolePermissionTree.value = res.data.rolePermissionTree;
    rolePermissionList.value = res.data.rolePermissionList;
    permissions.value = res.data.rolePermissionList;
  })
}

watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    rolePermissionList.value = [];
    permissions.value = [];
  }
})

//当复选框被点击的时候触发
function selectPermission(val, checkInfo) {
  rolePermissionList.value=[];
  rolePermissionList.value.push(...checkInfo.checkedKeys);
  permissions.value=[];
  permissions.value.push(...checkInfo.halfCheckedKeys)
  permissions.value.push(...checkInfo.checkedKeys);
}

function save() {
  isLoading.value = true;
  detailFormVisible.value = false;
  setTimeout(() => {
    isLoading.value = false;
  }, 310);
  common.call('SaveSysRolePermissions', {sysRoleId: sysRoleId.value, permissions: permissions.value})
}

//暴露
defineExpose({
  getRolePermissionTree
})
</script>

<template>
  <el-dialog :close-on-click-modal="false" v-model="detailFormVisible" title="设置权限" width="800" draggable>
    <el-tree
        ref="refRolePermissionTree"
        :data="rolePermissionTree"
        show-checkbox
        node-key="permission"
        :default-expanded-keys="rolePermissionList"
        :default-checked-keys="rolePermissionList"
        @check="selectPermission"/>
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
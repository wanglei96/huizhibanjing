<script setup>
const dialogFormVisible = ref(false)

const roleForm = reactive({
  roleName: '',
  roleFlag: '',
  memo: ''
})

const title = ref('新增角色')
function init(data) {
  Object.assign(roleForm, data)
  dialogFormVisible.value = true
  if (!data) {
    title.value = '新增角色'
  } else {
    title.value = '编辑角色'
  }
}

defineExpose({
  init
})
</script>
<template>
  <div>
    <el-dialog :close-on-click-modal="false" v-model="dialogFormVisible" :title="title" width="460" draggable>
      <el-form :model="roleForm">
        <el-form-item label="角色名称" :label-width="80">
          <el-input v-model="roleForm.roleName" autocomplete="off" placeholder="请输入角色名称" />
        </el-form-item>
        <el-form-item label="角色标识" :label-width="80">
          <el-input v-model="roleForm.roleFlag" autocomplete="off" placeholder="请输入角色标识" />
        </el-form-item>
        <el-form-item label="备注" :label-width="80">
          <el-input
            v-model="roleForm.memo"
            type="textarea"
            autocomplete="off"
            placeholder="请输入备注"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false"> 确定 </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped></style>

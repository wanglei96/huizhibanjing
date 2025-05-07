<script setup>
const dialogFormVisible = ref(false)
const formLabelWidth = '80px'
const dictionaryItemForm = reactive({
  name: '',
  value: '',
  sort: '',
  memo: ''
})

const title = ref()
function init(data) {
  dialogFormVisible.value = true
  if (data) {
    title.value = '编辑字典项'
    Object.assign(dictionaryItemForm, data)
    // TODO
    // 编辑操作
  } else {
    title.value = '新增字典项'
    // TODO
    // 新增操作
  }
}

defineExpose({
  init
})
</script>
<template>
  <div>
    <el-dialog destroy-on-close v-model="dialogFormVisible" :title="title" width="450" draggable>
      <el-form :model="form">
        <el-form-item label="字典项名" :label-width="formLabelWidth" prop="name">
          <el-input
            v-model="dictionaryItemForm.name"
            autocomplete="off"
            placeholder="请输入字典项名称"
          />
        </el-form-item>
        <el-form-item label="字典项值" :label-width="formLabelWidth" prop="value">
          <el-input
            v-model="dictionaryItemForm.value"
            autocomplete="off"
            placeholder="请输入字典项值"
          />
        </el-form-item>
        <el-form-item label="序号" :label-width="formLabelWidth" prop="sort">
          <el-input
            v-model="dictionaryItemForm.sort"
            autocomplete="off"
            type="number"
            placeholder="请输入字典项排序号"
          />
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth" prop="memo">
          <el-input
            v-model="dictionaryItemForm.memo"
            autocomplete="off"
            type="textarea"
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

<style lang="scss" scoped>
.el-input {
  width: 300px;
}
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>

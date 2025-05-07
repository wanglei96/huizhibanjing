<script setup>
const dialogFormVisible = ref(false)
const formLabelWidth = '80px'

const deptForm = reactive({
  parentId: '',
  name: '',
  leader: '',
  type: '',
  sort: '',
  code: '',
  memo: ''
})

const title = ref()
function init(deptId) {
  dialogFormVisible.value = true
  if (deptId) {
    title.value = '编辑部门'
    // TODO
    // 根据 deptId 获取部门信息
  } else {
    title.value = '新增部门'
  }
}

defineExpose({
  init
})
</script>
<template>
  <div>
    <el-dialog :close-on-click-modal="false" v-model="dialogFormVisible" :title="title" width="800" draggable>
      <el-form :model="deptForm">
        <el-row>
          <el-col :xs="24" :sm="24" :md="12" :lg="12">
            <el-form-item label="所属部门" :label-width="formLabelWidth">
              <el-input
                autocomplete="off"
                v-model="deptForm.parentId"
                placeholder="请输入上级部门ID"
              />
            </el-form-item>
            <el-form-item label="部门名称" :label-width="formLabelWidth">
              <el-input autocomplete="off" v-model="deptForm.name" placeholder="请输入部门名称" />
            </el-form-item>
            <el-form-item label="负责人" :label-width="formLabelWidth">
              <el-input
                autocomplete="off"
                v-model="deptForm.leader"
                placeholder="请输入部门负责人"
              />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12">
            <el-form-item label="部门类型" :label-width="formLabelWidth">
              <el-input autocomplete="off" v-model="deptForm.type" placeholder="请输入部门类型" />
            </el-form-item>
            <el-form-item label="部门序号" :label-width="formLabelWidth">
              <el-input autocomplete="off" v-model="deptForm.sort" placeholder="请输入部门序号" />
            </el-form-item>
            <el-form-item label="部门代码" :label-width="formLabelWidth">
              <el-input autocomplete="off" v-model="deptForm.code" placeholder="请输入部门代码" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="备注信息" :label-width="formLabelWidth">
              <el-input
                autocomplete="off"
                type="textarea"
                v-model="deptForm.memo"
                placeholder="请输入备注信息"
              />
            </el-form-item>
          </el-col>
        </el-row>
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
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>

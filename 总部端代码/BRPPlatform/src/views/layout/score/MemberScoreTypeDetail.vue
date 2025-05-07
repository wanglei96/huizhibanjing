<script setup>
import { reactive, ref, watch } from 'vue';
import detail from '@/js/detail';
import common from '@/js/common';
import config from '@/js/config'

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

let page = reactive({});


const init = (options) => {
  page = detail({
    baseTitle: '积分类型',
    tableName: 'member_score_type',
    getServiceName: 'GetMemberScoreType',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    primaryKeyName: "code",
    detailFormVisible,
    refDataEntityForm,
    emit
  });
  detailFormVisible.value = true;
  if (page.mode === "add") {
    nextTick(function () {
      page.dataEntity.value = { enabled: true, description: "" };
    })
  }
}
//暴露
defineExpose({
  init
})
function positiveInteger(rule, value, callback) {
  if (!/^[0-9]\d*$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正整数'))
  } else {
    // 校验通过
    callback()
  }
}
function integer(rule, value, callback) {
  if (!/^[+-]?\d+$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入整数'))
  } else {
    // 校验通过
    callback()
  }
}
//表单校验规则
const rules = reactive({
  name: [
    { required: true, message: '请输入名称', trigger: 'blur' }
  ],
  displayOrder: [
    { required: true, message: '请输入排序', trigger: 'blur' },
    { validator: positiveInteger, trigger: 'blur' }
  ],
  score: [
    { required: true, message: '请输入积分', trigger: 'blur' },
    { validator: integer, trigger: 'blur' }
  ],
})

</script>
<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''" width="800" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode == 'view'" label-width="80px">
        <el-form-item label="" prop="备注">
          <div style="color: red;">积分正数代表加积分，负数代表减积分</div>
        </el-form-item>
        <el-row>
          <el-col :xs="24" :sm="12" :md="12" :lg="12">
            <el-form-item label="名称" prop="code">
              <el-input v-model="page.dataEntity.value.code" placeholder="编码"
                :disabled="page.mode !== 'add'"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="12" :lg="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="page.dataEntity.value.name" placeholder="请输入名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="积分" prop="score">
              <el-input v-model.number="page.dataEntity.value.score" placeholder="请输入积分"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="12" :lg="12">
            <el-form-item label="是否启用" prop="enabled">
              <el-switch v-model="page.dataEntity.value.enabled" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="排序" prop="displayOrder">
              <el-input v-model.number="page.dataEntity.value.displayOrder" placeholder="请输入排序"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="描述" prop="description">
              <el-input v-model="page.dataEntity.value.description" type="textarea" placeholder="请输入描述"
                rows="10"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" v-if="page.mode === 'add' || page.mode === 'edit'" :loading="page.isLoading.value"
            @click="page.submitForm()"> 确定 </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.el-dialog {
  width: 500px;
}

.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 300px;
}

.el-input {
  width: 200px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
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
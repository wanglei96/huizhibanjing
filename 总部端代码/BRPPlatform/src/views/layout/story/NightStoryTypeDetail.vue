<script setup>
import {reactive, ref} from 'vue';
import detail from '@/js/detail';
import common from "@/js/common";

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

let page = reactive({});
const init = (options) => {
  detailFormVisible.value = true
  page = detail({
    baseTitle: '订阅类型',
    tableName: 'night_story_type',
    getServiceName: 'GetNightStoryType',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  });
}

//暴露
defineExpose({
  init
})

function floatValidator(rule, value, callback) {
  if (!/^(?=.+)(?:[1-9]\d*|0)?(?:\.\d+)?$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正数'))
  } else {
    // 校验通过
    callback()
  }
}
function positiveInteger(rule, value, callback) {
  if (!/^[0-9]\d*$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正整数'))
  } else {
    // 校验通过
    callback()
  }
}
//表单校验规则
const rules = reactive({
  name: [
    {required: true, message: '请输入名称', trigger: 'blur'}
  ],
  dayCount: [
    {required: true, message: '请输入天数', trigger: 'blur'},
    {validator: positiveInteger, trigger: 'blur'}
  ],
  price: [
    {required: true, message: '请输入价格', trigger: 'blur'},
    {validator: floatValidator, trigger: 'blur'}
  ],
  displayOrder: [
    {required: true, message: '请输入排序', trigger: 'blur'},
    {validator: positiveInteger, trigger: 'blur'}
  ],
  maxSubscribeCount: [
    {required: true, message: '最大订阅次数', trigger: 'blur'},
    {validator: positiveInteger, trigger: 'blur'}
  ]
})

</script>
<template>
  <div>
    <el-dialog :close-on-click-modal="false" v-model="detailFormVisible" :title="page.title?page.title.value:''" width="800" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
               :disabled="page.mode === 'view'" label-width="110px">
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="page.dataEntity.value.name" placeholder="请输入名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="天数" prop="dayCount">
              <el-input v-model.number="page.dataEntity.value.dayCount" placeholder="请输入天数"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="价格" prop="price">
              <el-input v-model="page.dataEntity.value.price" placeholder="请输入价格"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="最大订阅次数" prop="maxSubscribeCount">
              <el-input v-model="page.dataEntity.value.maxSubscribeCount" placeholder="请输入最大订阅次数"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="排序" prop="displayOrder">
              <el-input v-model.number="page.dataEntity.value.displayOrder" placeholder="请输入排序"></el-input>
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
.form-item-width{
  width: 200px;
}

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

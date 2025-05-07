<script setup>
import { inject, reactive, ref } from 'vue'
import common from '@/js/common'
import detail from '@/js/detail';


// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

let page = reactive({});

const init = (options) => {
  page = detail({
    baseTitle: '书柜',
    tableName: 'bookcase',
    getServiceName: 'GetBookcase',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  });
  detailFormVisible.value = true;
}

const rules = reactive({
  name: [
    { required: true, message: '名称不允许为空', trigger: 'blur' },
  ]
})
defineExpose({
  init
})
</script>

<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''" width="800" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode == 'view'" label-width="80px">
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="书柜名称" prop="name">
              <el-input v-model="page.dataEntity.value.name" placeholder="请输入书柜名称"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="描述" prop="description">
              <el-input v-model="page.dataEntity.value.description" type="textarea" placeholder="请输入描述"></el-input>
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
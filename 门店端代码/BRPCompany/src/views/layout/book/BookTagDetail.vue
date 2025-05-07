<script setup>
import { inject, reactive, ref } from 'vue'
import common from '@/js/common'
import detail from '@/js/detail';
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

let page = reactive({});


const init = (options) => {
  page = detail({
    baseTitle: '图书标签',
    tableName: 'book_tag',
    getServiceName: 'GetBookTag',
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
    { required: true, message: '标签名称不允许为空', trigger: 'blur' },
  ],
  languageCode: [
    { required: true, message: '语言分类不允许为空', trigger: 'blur' },
  ],
  displayOrder: [
    { required: true, message: '排序不允许为空', trigger: 'blur' },
  ],
  frontName: [
    { required: true, message: '前台显示名称', trigger: 'blur' },
    { max: 6, message: '前台显示名称不能超过6位', trigger: 'blur' }
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
        :disabled="page.mode == 'view'" label-width="120px">
        <el-form-item label="标签名称" prop="name">
          <el-input v-model="page.dataEntity.value.name" placeholder="请输入标签名称"></el-input>
        </el-form-item>
        <el-form-item label="前台显示名称" prop="frontName">
          <el-input v-model="page.dataEntity.value.frontName" placeholder="请输入前台显示名称"></el-input>
        </el-form-item>
        <el-form-item label="排序" prop="displayOrder">
          <el-input v-model.number="page.dataEntity.value.displayOrder" placeholder="请输入排序"></el-input>
        </el-form-item>
        <el-form-item label="是否会员可见" prop="isVisible">
          <el-switch v-model="page.dataEntity.value.isVisible" />
        </el-form-item>
        <el-form-item label="语言分类" prop="languageCode">
          <DatabaseSelect table-name="language" v-model="page.dataEntity.value.languageCode" placeholder="请选择语言分类"
            class="form-item-width" />
        </el-form-item>
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
<script setup>
import { reactive, ref } from 'vue'
import detail from '@/js/detail'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'

//定义事件发射器
const emit = defineEmits(['reload-data'])
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref()

let page = reactive({})
const init = (options) => {
  detailFormVisible.value = true
  page = detail({
    baseTitle: '新书上架',
    tableName: 'home_new_book',
    getServiceName: 'GetViewHomeNewBook',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  })
  if (options.mode === 'add') {
    page.dataEntity.value.enabled = true
  }
}

//暴露
defineExpose({
  init
})

function floatValidator(rule, value, callback) {
  if (!/^-?\d+(\.\d+)?$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正数'))
  } else {
    // 校验通过
    callback()
  }
}

//表单校验规则
const rules = reactive({
  name: [{ required: true, message: '请输入前台显示图书名', trigger: 'blur' }],
  bookSkuId: [{ required: true, message: '请选择图书', trigger: 'change' }],
  displayOrder: [
    { required: true, message: '请输入排序', trigger: 'blur' },
    { validator: common.positiveInteger, trigger: 'blur' }
  ]
})

function bookSkuOnChange(item) {
  if (item) {
    page.dataEntity.value.name = item.book_name
  } else {
    page.dataEntity.value.name = ''
  }
}

function bookSkuOnClear() {
  page.dataEntity.value.name = ''
}

function getTableByCompanyId() {
  return `company_id=${localStorage.getItem('companyId')}`
}
</script>
<template>
  <div>
    <el-dialog
      destroy-on-close
      :close-on-click-modal="false"
      v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''"
      width="800"
      draggable
    >
      <el-form
        :model="page.dataEntity.value"
        :rules="rules"
        ref="refDataEntityForm"
        :label-position="'right'"
        :disabled="page.mode === 'view'"
        label-width="80px"
      >
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="图书" prop="bookSkuId">
              <database-select
                placeholder="请选择图书"
                table-name="book_sku"
                v-model="page.dataEntity.value.bookSkuId"
                valueFieldName="id"
                textFieldName="book_name"
                @onChange="bookSkuOnChange"
                @onClear="bookSkuOnClear"
                class="form-item-width"
                :condition="getTableByCompanyId()"
              ></database-select>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="前台显示图书名" prop="name" label-width="auto">
              <el-input
                v-model="page.dataEntity.value.name"
                class="form-item-width"
                placeholder="请输入图书名"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="排序" prop="displayOrder">
              <el-input
                v-model.number="page.dataEntity.value.displayOrder"
                class="form-item-width"
                placeholder="请输入排序"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="是否启用" prop="enabled">
              <el-switch v-model="page.dataEntity.value.enabled"></el-switch>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button
            type="primary"
            v-if="page.mode === 'add' || page.mode === 'edit'"
            :loading="page.isLoading.value"
            @click="page.submitForm()"
          >
            确定
          </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.form-item-width {
  width: 100% !important;
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
<script setup>
import { reactive, ref } from 'vue'
import detail from '@/js/detail'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import ImageUpload from '@/components/extension/ImageUpload.vue'

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
    baseTitle: '导航配置',
    tableName: 'home_navigation',
    getServiceName: 'GetViewHomeNavigation',
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
  name: [{ required: true, message: '请输入名称', trigger: 'blur' }],
  path: [{ required: true, message: '请输入路径', trigger: 'blur' }],
  imageFileId: [{ required: true, message: '请上传图标', trigger: 'change' }],
  displayOrder: [
    { required: true, message: '请输入排序', trigger: 'blur' },
    { validator: common.positiveInteger, trigger: 'blur' }
  ]
})

function clubActivityOnChange(item) {
  if (item) {
    page.dataEntity.value.name = item.name
  } else {
    page.dataEntity.value.name = ''
  }
}

function clubActivityOnClear() {
  page.dataEntity.value.name = ''
}
</script>
<template>
  <div>
    <el-dialog destroy-on-close
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
        <el-row >
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="名称" prop="name">
              <el-input
                v-model="page.dataEntity.value.name"
                class="form-item-width"
                placeholder="请输入名称"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="路径" prop="path" label-width="auto">
              <el-input
                v-model="page.dataEntity.value.path"
                class="form-item-width"
                placeholder="请输入路径"
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
          <el-col>
            <el-form-item label="图标" prop="imageFileId">
              <image-upload
                v-model="page.dataEntity.value.imageFileId"
                :base-img-url="page.dataEntity.value.imageFilePath"
              >
                >
              </image-upload>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="介绍" prop="introduction">
              <el-input
                type="textarea"
                rows="4"
                style="width: 100%"
                v-model.number="page.dataEntity.value.introduction"
                placeholder="请输入介绍"
              ></el-input>
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
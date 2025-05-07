<script setup>
import { reactive, ref } from 'vue'
import detail from '@/js/detail'
import config from '@/js/config'
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
    baseTitle: '阅读活动',
    tableName: 'read_activity',
    getServiceName: 'GetViewReadActivity',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  })
  setTimeout(() => {
    detailFormVisible.value = true
    page.loadPage({
      // bindControls,
      processDataEntity
    })
  }, 1)
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
  price: [
    { required: true, message: '请输入价格', trigger: 'blur' },
    { validator: floatValidator, trigger: 'blur' }
  ]
})
</script>
<template>
  <div class="ReadActivityDetail-box">
    <el-dialog destroy-on-close :close-on-click-modal="false"
      v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''"
      width="1000"
      draggable
      v-if="detailFormVisible"
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
                placeholder="请输入活动名称"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="价格" prop="price">
              <el-input
                v-model.number="page.dataEntity.value.price"
                class="form-item-width"
                placeholder="请输入价格"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="图片" prop="imageFileId">
              <image-upload
                v-model="page.dataEntity.value.imageFileId"
                :base-img-url="page.dataEntity.value.imageFilePath"
              ></image-upload>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="简介" prop="content">
              <vue-ueditor-wrap
                v-model="page.dataEntity.value.content"
                editor-id="editor"
                :config="config.editorConfig"
                :editorDependencies="['ueditor.config.js', 'ueditor.all.js']"
                style="width: 100%"
              />
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
  width: 200px !important;
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
<style lang="scss">
.ReadActivityDetail-box {
  .edui-editor-iframeholder {
    width: 100% !important;
  }

  .edui-editor {
    width: 100% !important;
  }
}
</style>
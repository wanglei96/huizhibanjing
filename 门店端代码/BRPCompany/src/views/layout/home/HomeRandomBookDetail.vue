<script setup>
import { reactive, ref } from 'vue'
import common from '@/js/common'
import ImageUpload from '@/components/extension/ImageUpload.vue'

//定义事件发射器
const emit = defineEmits(['reload-data'])
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref()
const isLoading = ref(false)
const dataEntity = ref({})

let page = reactive({})
const init = (options) => {
  detailFormVisible.value = true
}

//暴露
defineExpose({
  init
})

//表单校验规则
const rules = reactive({
  allCount: [{ validator: common.positiveInteger, trigger: 'blur' }],
  ageGroupCount: [{ validator: common.positiveInteger, trigger: 'blur' }]
})

function submitForm() {
  if (!dataEntity.value.allCount && !dataEntity.value.ageGroupCount) {
    ElMessage.warning('请填写至少一个参数')
    return
  }
  detailFormVisible.value = false
  let data = {}
  data.companyId = localStorage.getItem('companyId')
  if (dataEntity.value.allCount) {
    data.allCount = dataEntity.value.allCount
  }
  if (dataEntity.value.ageGroupCount) {
    data.ageGroupCount = dataEntity.value.ageGroupCount
  }
  dataEntity.value = {}
  common.call('AddHomeAgeGroupAndAllRandomBook', data, (res) => {
    emit('reload-data')
  })
}
</script>
<template>
  <div>
    <el-dialog destroy-on-close
      :close-on-click-modal="false"
      v-model="detailFormVisible"
      title="首页随便看看配置"
      width="800"
      draggable
    >
      <el-form
        :model="dataEntity"
        :rules="rules"
        ref="refDataEntityForm"
        :label-position="'right'"
        label-width="80px"
      >
        <el-row >
          <el-col :xs="12" :sm="12" :md="12" :lg="12" class="accountCommentCount-col">
            <el-form-item label="所有书本：" prop="allCount" label-width="auto">
              <div class="input-box">
                <el-input
                  v-model.number="dataEntity.allCount"
                  placeholder=""
                  style="width: 100px"
                ></el-input>
                <div class="unit">条</div>
              </div>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12" class="accountCommentCount-col">
            <el-form-item label="匹配年龄段内书本：" prop="ageGroupCount" label-width="auto">
              <div class="input-box">
                <el-input
                  v-model.number="dataEntity.ageGroupCount"
                  placeholder=""
                  style="width: 100px"
                ></el-input>
                <div class="unit">条</div>
              </div>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" :loading="isLoading" @click="submitForm()"> 确定 </el-button>
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

:deep(.accountCommentCount-col) {
  .el-input__wrapper {
    box-shadow: none;
  }

  .input-box {
    padding: 1px;
    border-radius: 4px;
    display: flex;
    box-shadow: 0 0 0 1px var(--el-input-border-color, var(--el-border-color)) inset;
  }

  .unit {
    width: 32px;
    flex-shrink: 0;
    background-color: #f6f8fa;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 12px;
    border-left: 1px solid #dcdfe6;
  }
}
</style>
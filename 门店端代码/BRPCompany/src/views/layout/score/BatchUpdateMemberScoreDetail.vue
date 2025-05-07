<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import detail from '@/js/detail'
import config from '@/js/config'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import dayjs from 'dayjs'
import { ElMessage } from 'element-plus'

//详情页组件引用
const refSearchForm = ref()
const refForm = ref()
const isLoading = ref(false)

const router = useRouter()

//批量曾加会员卡时长信息
const activityServiceTime = ref({})
//会员卡信息列表
const memberCardArr = ref([])
//会员卡信息异常列表
const exceptionMemberCardList = ref([])
//文件列表
const fileList = ref([])
//门店id
const companyId = ref()
companyId.value = localStorage.getItem('companyId')

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
    tableName: 'activity',
    addServiceName: 'BatchUpdateMemberScore',
    getServiceName: 'GetViewActivityServiceTime',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit,
    getDataEntity(dataEntity) {
      let memberIds = memberCardArr.value.map((item) => item.memberId)

      dataEntity.value.memberIds = memberIds
    },
    onDetailHide() {
      activityServiceTime.value = {}
      memberCardArr.value = []
      fileList.value = []
      exceptionMemberCardList.value = []
    }
  })
}
const scoreTypeList = reactive([{ code: "augment", name: "增加" }, { code: "decrease", name: "减少" }])


//暴露
defineExpose({
  init
})

//文件列表移除文件时的钩子
const handleRemove = (file, uploadFiles) => {
  console.log(file, uploadFiles)
}
//上传成功的钩子
const attachmentFileSuccess = (response, uploadFile, uploadFiles) => {
  common.call('GetMemberCardListByExcel', { uploadId: response.data.id, companyId: companyId.value }, (res) => {
    memberCardArr.value = res.data.memberCardListByExcel
    exceptionMemberCardList.value = res.data.exceptionMemberCardList
  })
}

//监听文件列表长度
watch(
  () => fileList.value.length,
  (newValue, oldValue) => {
    if (newValue === 0) {
      memberCardArr.value = []
    }
  }
)

//上传失败的钩子
const attachmentFileError = (error, uploadFile, uploadFiles) => {
  console.log(error)
  ElMessage.error('上传失败')
}

//文件数量超出限制时的钩子
const handleExceed = (files, uploadFiles) => {
  ElMessage.warning(`只能上传一份文件`)
}

/* function floatValidator(rule, value, callback) {
  if (!/^-?[1-9]\d*$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正整数或负整数'))
  } else {
    // 校验通过
    callback()
  }
} */

const rules = reactive({
  /* score: [
    { required: true, message: '请输入积分', trigger: 'blur' },
    { validator: floatValidator, trigger: 'blur' }
  ], */
  memberScoreTypeCode: [{ required: true, message: '请选择积分类型', trigger: 'change' }],
  type: [{ required: true, message: '请选择积分方式', trigger: 'change' }]
})

//是否显示校验错误信息
const showMessage = ref(true)

//提交表单
function submitForm() {
  if (memberCardArr.value.length === 0) {
    ElMessage({ type: 'error', message: '未查询到会员' })
    return
  }
  page.submitForm()
}
const onChange = (row) => {
  page.dataEntity.value.score = row.score
}

</script>
<template>
  <div class="ActivityServiceTimeDetail-box">
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''" width="800" draggable overflow>
      <div class="basic-list">
        <el-card shadow="never" class="operate-card">
          <template #header>
            <div class="card-header">
              <span>操作栏</span>
            </div>
          </template>
          <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
            :disabled="page.mode === 'view'" label-width="80px">
            <el-row>
              <el-col :xs="12" :sm="12" :md="12" :lg="12">
                <el-form-item label="积分类型" prop="memberScoreTypeCode">
                  <database-select table-name="member_score_type" style="flex-grow: 1"
                    :condition="'is_deleted is not true'" v-model="page.dataEntity.value.memberScoreTypeCode"
                    @onChange="onChange"></database-select>
                </el-form-item>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="12">
                <el-form-item label="积分" prop="score">
                  <el-input v-model.number="page.dataEntity.value.score" class="form-item-width" placeholder="积分"
                    style="flex-grow: 1"></el-input>
                  <!-- <div style="color: #888888">（输入正正数加积分，负整数减积分）</div> -->
                </el-form-item>
              </el-col>
            </el-row>
            <!-- <el-row>
              <el-col :xs="12" :sm="12" :md="12" :lg="12">
                <el-form-item label="积分方式" prop="type">
                  <el-select style="flex-grow: 1" v-model="page.dataEntity.value.type" placeholder="请选择积分方式">
                    <el-option v-for="item in scoreTypeList" :key="item.code" :label="item.name" :value="item.code" />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row> -->
          </el-form>
        </el-card>
        <el-card shadow="never" class="result-card">
          <template #header>
            <div class="card-header">
              <span>结果栏</span>
            </div>
          </template>
          <el-row type="flex">
            <el-col :xs="24" :sm="24" :md="24" :lg="24" style="margin-bottom: 10px">
              <span style="font-size: 16px; color: #f00000">任意创建一个excel表格，会员卡号放在第一列，格式文本</span>
              <el-form-item label="选择要导入的Excel" prop="name">
                <el-upload v-model:file-list="fileList" class="upload-demo"
                  :action="config.adminRootUrl + '/api/Service?serviceName=UploadFile'" :on-remove="handleRemove"
                  :on-success="attachmentFileSuccess" :on-error="attachmentFileError" :limit="1"
                  :on-exceed="handleExceed"
                  accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                  <el-button type="primary" size="small">选择文件</el-button>
                </el-upload>
              </el-form-item>
            </el-col>
          </el-row>
          <el-card shadow="never" class="result-card-list-card">
            <template #header>
              <div class="card-header">
                <span>会员卡信息</span>
              </div>
            </template>
            <el-alert :title="`共 ${memberCardArr.length} 项`" v-show="memberCardArr.length >= 0" type="warning" show-icon
              style="margin-bottom: 10px" />
            <el-table ref="multipleTableRef" :data="memberCardArr" style="width: 100%" border>
              <el-table-column property="memberName" label="会员姓名" />
              <el-table-column property="memberScore" label="会员积分">
                <template #default="scope">
                  {{ scope.row.memberScore ? scope.row.memberScore : 0 }}
                </template>
              </el-table-column>
            </el-table>
          </el-card>
          <el-card shadow="never" class="result-card-list-card" style="margin-top: 15px">
            <template #header>
              <div class="card-header">
                <span>会员卡信息异常列表</span>
              </div>
            </template>
            <el-alert :title="`共 ${exceptionMemberCardList.length} 项`" v-show="exceptionMemberCardList.length >= 0"
              type="warning" show-icon style="margin-bottom: 10px" />
            <el-table ref="multipleTableRef" :data="exceptionMemberCardList" style="width: 100%" border>
              <el-table-column property="cardNo" label="会员卡号" />
            </el-table>
          </el-card>
        </el-card>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" :loading="isLoading" @click="submitForm()">
            批量增加积分
          </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.head-card {
  margin-bottom: 15px;

  .card-header {
    height: 20px;
  }

  .el-form-item {
    margin-bottom: 0px;
  }
}

.image-slot {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 100px;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 30px;
}

.el-input {
  width: 200px;
}

.el-table {
  mix-height: 300px;
  overflow-y: auto;
}

.upload-demo {
  display: flex;
  align-items: center;
  flex-grow: 1;
}
</style>
<style>
.ActivityServiceTimeDetail-box {
  .result-card {
    .el-card__header {
      background-color: #f89898;
    }
  }

  .result-card-list-card {
    .el-card__header {
      background-color: #ffffff;
      color: #303133;
    }
  }

  .operate-card {
    margin-bottom: 15px;

    .el-card__header {
      background-color: #95d475;
    }
  }

  .el-card__header {
    padding: 12px;
    color: #ffffff;
    font-size: 1.05rem;
  }

  .operate-card-footer {
    display: flex;
    justify-content: flex-end;
  }

  .el-upload-list__item {
    margin: 0;
  }

  .result-card .el-form-item__label {
    font-size: 16px;
  }

  .el-form-item__content {
    flex-grow: 2;
  }

  .el-upload-list {
    margin: 0;
    width: 300px;
    height: 34px;
  }
}
</style>
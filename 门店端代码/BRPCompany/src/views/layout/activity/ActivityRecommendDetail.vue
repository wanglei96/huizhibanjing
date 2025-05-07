<script setup>
import { reactive, ref } from 'vue';
import { useRouter } from "vue-router";
import detail from "@/js/detail";
import config from '@/js/config';
import common from "@/js/common";
import DatabaseSelect from "@/components/extension/DatabaseSelect.vue";
import dayjs from "dayjs";
import { ElMessage } from "element-plus";
import util from '@/js/util'

//详情页组件引用
const refSearchForm = ref();
const refForm = ref();
const isLoading = ref(false);

const router = useRouter();

//批量曾加会员卡时长信息
const activityServiceTime = ref({});
//会员卡信息列表
const memberCardArr = ref([]);
//会员卡信息异常列表
const exceptionMemberCardList = ref([]);
//文件列表
const fileList = ref([])
//门店id
const companyId = ref();
companyId.value = localStorage.getItem('companyId');

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
    tableName: 'activity_recommend',
    addServiceName: 'BatchAddActivityRecommend',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit,
    getDataEntity(dataEntity) {
      let memberCardIdArr = memberCardArr.value.map(item => {
        return {
          newMemberCardId: item.newMemberCardId, referrerCardId: item.referrerCardId,
          newMemberId: item.newMemberId, referrerId: item.referrerId, displayOrder: item.displayOrder,
        };
      });
      let exceptionMemberCardNoList = exceptionMemberCardList.value.map(item => {
        return { errorContext: item.errorContext, memberCardNo: item.memberCardNo, referrerCardNo: item.referrerCardNo, displayOrder: item.displayOrder, };
      });

      dataEntity.value.memberCardIdArr = memberCardIdArr;
      dataEntity.value.exceptionMemberCardList = exceptionMemberCardNoList;
    }, onDetailHide() {
      activityServiceTime.value = {};
      memberCardArr.value = [];
      fileList.value = [];
      exceptionMemberCardList.value = [];
    }
  });
  if (options.mode === 'view' || options.mode === 'edit') {
    getActivityRecommendMemberList(options.primaryKeyValue)
    getActivityRecommendErrorList(options.primaryKeyValue)
  }
}
function getActivityRecommendMemberList(id) {
  let params = {
    activityRecommendId: id,
    pageSize: 10000,
    orderBy: 'display_order asc'
  }
  common.call('GetViewActivityRecommendMemberList', params, res => {
    memberCardArr.value = res.data.dataList;
  })
}
function getActivityRecommendErrorList(id) {
  let params = {
    activityRecommendId: id,
    pageSize: 10000,
    orderBy: 'display_order asc'
  }
  common.call('GetActivityRecommendErrorList', params, res => {
    exceptionMemberCardList.value = res.data.dataList;
  })
}

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
  common.call('GetRecommendMemberCardInfoListByExcel', { uploadId: response.data.id }, res => {
    memberCardArr.value = res.data.memberCardList;
    exceptionMemberCardList.value = res.data.exceptionMemberCardList;
  })
}

//监听文件列表长度
watch(() => fileList.value.length, (newValue, oldValue) => {
  if (newValue === 0) {
    memberCardArr.value = [];
  }
})

//上传失败的钩子
const attachmentFileError = (error, uploadFile, uploadFiles) => {
  console.log(error)
  ElMessage.error('上传失败')
}

//文件数量超出限制时的钩子
const handleExceed = (files, uploadFiles) => {
  ElMessage.warning(`只能上传一份文件`)
}

//上传前的钩子
const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== 'video/mp4') {
    ElMessage.error('请上传大小在200M以内的mp4格式视频文件');
    return false;
  } else if (rawFile.size / 1024 / 1024 > 200) {
    ElMessage.error('请上传大小在200M以内的mp4格式视频文件');
  }
  return true
}

const timeTypeArr = reactive([]);
const getTimeTypeArr = function () {
  timeTypeArr.push({ label: '时长', value: 'duration' });
}
getTimeTypeArr();

function floatValidator(rule, value, callback) {
  if (!/^[1-9]\d*$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正整数'))
  } else {
    // 校验通过
    callback()
  }
}

const rules = reactive({
  days: [
    { required: true, message: '请输入天数', trigger: 'blur' },
    { validator: floatValidator, trigger: 'blur' }
  ]
});

//是否显示校验错误信息
const showMessage = ref(true);

//提交表单
function submitForm() {
  if (memberCardArr.value.length === 0) {
    ElMessage({ type: 'error', message: '未查询到会员' });
    return;
  }
  page.submitForm();
}

function formatBusinessDate(row, column, cellValue, index) {
  if (cellValue) {
    //return dayjs(cellValue).format('YYYY-MM-DD')
    return util.jsonToDateTimeString(cellValue);
  }
  return "";
}
</script>
<template>
  <div class="ActivityRecommendDetail-box">
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''" width="1000" draggable>
      <div class="basic-list">
        <el-card shadow="never" class="operate-card">
          <template #header>
            <div class="card-header">
              <span>操作栏</span>
            </div>
          </template>
          <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
            :disabled="page.mode === 'view'" label-width="100px">
            <el-row>
              <el-col :xs="12" :sm="12" :md="12" :lg="12">
                <el-form-item label="天数" prop="days">
                  <el-input v-model.number="page.dataEntity.value.days" class="form-item-width" placeholder="请输入天数"
                    style="flex-grow: 1;"></el-input>
                </el-form-item>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="12">
                <el-form-item label="活动" prop="activityId">
                  <database-select table-name="activity" style="flex-grow: 1;"
                    v-model="page.dataEntity.value.activityId" valueFieldName="id"
                    :condition="'is_deleted is not true and company_id=' + companyId"></database-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
        <el-card shadow="never" class="result-card">
          <template #header>
            <div class="card-header">
              <span>结果栏</span>
            </div>
          </template>
          <el-row type="flex" v-if="page.mode === 'add'">
            <el-col :xs="24" :sm="24" :md="24" :lg="24" style="margin-bottom: 10px">
              <span style="font-size:16px;color:#f00000;">
                任意创建一个excel表格，把需要查询的的会员卡号放在第一列和第二列，（新会员在第一列，对应的老会员在同一行的第二列）</span>
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
              style="margin-bottom: 10px;" />
            <el-table ref="multipleTableRef" :data="memberCardArr" style="width: 100%" border>
              <el-table-column property="newMemberName" label="新会员姓名" width="100" />
              <el-table-column property="newMemberCardNo" label="新会员卡号" width="130" />
              <el-table-column property="referrerName" label="推荐人姓名" width="100" />
              <el-table-column property="referrerCardNo" label="推荐人卡号" width="130" />
              <!-- <el-table-column label="新会员会员卡失效时间">
                <template #default="scope">
                  {{ dayjs(scope.row.newMemberCardExpireDate.time).format('YYYY-MM-DD HH:mm:ss') }}
                </template>
              </el-table-column>
              <el-table-column label="推荐人会员卡失效时间">
                <template #default="scope">
                  {{ dayjs(scope.row.referrerCardExpireDate.time).format('YYYY-MM-DD HH:mm:ss') }}
                </template>
              </el-table-column> -->
              <el-table-column property="newExpireDateText" label="新会员会员卡失效时间">
                <template #default="scope">
                  <span>{{ scope.row.newExpireDateText || scope.row.newMemberCardExpireDateText }}</span>
                </template>
              </el-table-column>
              <el-table-column property="referrerExpireDateText" label="推荐人会员卡失效时间">
                <template #default="scope">
                  <span>{{ scope.row.referrerExpireDateText || scope.row.referrerCardExpireDateText }}</span>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
          <el-card shadow="never" class="result-card-list-card" style="margin-top: 15px;">
            <template #header>
              <div class="card-header">
                <span>会员卡信息异常列表</span>
              </div>
            </template>
            <el-alert :title="`共 ${exceptionMemberCardList.length} 项`" v-show="exceptionMemberCardList.length >= 0"
              type="warning" show-icon style="margin-bottom: 10px;" />
            <el-table ref="multipleTableRef" :data="exceptionMemberCardList" style="width: 100%" border>
              <el-table-column property="memberCardNo" label="新会员会员信息" />
              <!-- <el-table-column property="newMemberName" label="新会员会员名称" /> -->
              <el-table-column property="referrerCardNo" label="推荐人会员信息" />
              <!-- <el-table-column property="referrerName" label="推荐人会员名称" /> -->
              <el-table-column property="errorContext" label="异常信息" />
            </el-table>
          </el-card>
        </el-card>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" v-if="page.mode === 'add'" :loading="isLoading" @click="submitForm()"> 批量增加时长
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
<style lang="scss">
.ActivityRecommendDetail-box {

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


  .el-upload-list {
    margin: 0;
    width: 300px;
    height: 34px;

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

  .operate-card-footer {
    display: flex;
    justify-content: flex-end;
  }
}
</style>

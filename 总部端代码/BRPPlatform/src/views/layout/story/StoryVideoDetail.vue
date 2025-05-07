<script setup>
import { reactive, ref } from 'vue';
import detail from '@/js/detail';
import common from "@/js/common";
import { ElMessage } from "element-plus";
import config from "@/js/config";

//定义事件发射器
const emit = defineEmits(["reload-data"]);
//详情页开关
const detailFormVisible = ref(false);
//表单引用
const refDataEntityForm = ref();

let page = reactive({});
const init = (options) => {
  detailFormVisible.value = true;
  page = detail({
    baseTitle: "视频",
    tableName: "story_video",
    getServiceName: "GetViewStoryVideo",
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  });
  if (page.mode === "add") {
    let data = {
      tableName: "company",
      filedName: "company_no",
      length: 6
    };
    common.call("CreateRandomNo", data, (res) => {
      page.dataEntity.value.companyNo = res.data;
    });
    page.dataEntity.value.playbackCount = 0;
    page.dataEntity.value.isPay = true;
  }
  pushTimeText.value = 21;
};

//暴露
defineExpose({
  init
});
const pushDateText = computed({
  get() {
    return page.dataEntity.value.pushDateText;
  }, set(val) {
    page.dataEntity.value.pushDateText = val;
    page.dataEntity.value.pushDate = val;
  }
});
const pushTimeText = computed({
  get() {
    return page.dataEntity.value.pushTimeText ? parseInt(page.dataEntity.value.pushTimeText.substring(10, 13)) : "";
  }, set(val) {
    page.dataEntity.value.pushTimeText = `2023-01-01 ${val >= 10 ? val : "0" + val}:00:00`;
    page.dataEntity.value.pushTime = `2023-01-01 ${val >= 10 ? val : "0" + val}:00:00`;
  }
});

//表单校验规则
const rules = reactive({
  title: [
    { required: true, message: '请输入标题', trigger: 'blur' }
  ],
  pushDate: [
    { required: true, message: '请选择推送日期', trigger: 'blur' }
  ],
  pushTime: [
    { required: true, message: '请选择推送时间', trigger: 'blur' }
  ],
  storyVideoFilePath: [
    { required: true, message: '请上传晚安故事', trigger: 'blur' }
  ]
});

const timeArr = ref([]);
(() => {
  for (let i = 1; i <= 24; i++) {
    timeArr.value.push({ label: `当日${i >= 10 ? i : '0' + i}点`, value: i })
  }
})();

//上传前的钩子
const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== "video/mp4") {
    ElMessage.error("请上传大小在200M以内的mp4格式视频文件");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 200) {
    ElMessage.error("请上传大小在200M以内的mp4格式视频文件");
  }
  return true;
};

//上传前的钩子
const beforeCoverImageUpload = (rawFile) => {
  if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {
    ElMessage.error('请上传图片格式文件')
    return false
  } else if (rawFile.size / 1024 / 1024 > 5) {
    ElMessage.error('大小不能超过 5MB!')
    return false
  }
  return true
}

const uploadCoverImage = async (file) => {
  console.log(file);
  common.uploadFile(file, function (result) {
    console.log(result);
    page.dataEntity.value.coverImageFilePath = result.data.path;
    page.dataEntity.value.coverImageFileId = result.data.id;
  });
};
const coverImageUrl = computed(() => {
  if (page.dataEntity.value.coverImageFilePath) {
    return common.getAdminUrl(page.dataEntity.value, 'coverImageFilePath');
  }
  else {
    return common.getAdminUrl(page.dataEntity.value, 'videoImageFilePath');
  }
})

const storyVideoFilePath = computed(() => {
  if (page.dataEntity.value.storyVideoFilePath) {
    return common.getAdminUrl(page.dataEntity.value, "storyVideoFilePath");
  }
  return null
});


// 是否显示进度条
const isShowProgess = ref(false)

// 上传进度值
const percentage = ref(0)

// 回调：文件上传成功之后
const handleAvatarSuccess = (response, uploadFile) => {
  console.log("uploadFile:", uploadFile);
  console.log("response:", response);
  page.dataEntity.value.storyVideoFilePath = response.data.path;
  page.dataEntity.value.storyVideoFileId = response.data.id;
  page.dataEntity.value.coverImageFilePath = response.data.videoImageFilePath;
  isShowProgess.value = false;
  percentage.value = 0;
}
// 回调：图片上传之前
const beforeVideoUpload = (rawFile) => {
  isShowProgess.value = true // 显示上传进度条
  if (rawFile.type !== 'video/mp4') {
    ElMessage.error('上传的文件必须是 mp4 格式!')
    isShowProgess.value = false // 关闭上传进度条
    return false
  } /*else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('上传的文件大小不能超过 2MB!')
    isShowProgess.value = false // 关闭上传进度条
    return false
  }*/
  return true
}

// 上传进度回调
function handleProgress(evt) {
  percentage.value = Math.floor(evt.percent)
}
</script>
<template>
  <div>
    <el-dialog v-model="detailFormVisible" :title="page.title ? page.title.value : ''" width="800" draggable
      :close-on-click-modal="false" :destroy-on-close="true">
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode === 'view'" label-width="120px">
        <el-row>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="封面" prop="cover">
              <el-upload class="avatar-uploader" action="uploadPath" :show-file-list="false"
                :before-upload="beforeCoverImageUpload" :http-request="uploadCoverImage">
                <el-image :src="coverImageUrl" class="avatar">
                  <template #error>
                    <el-icon class="avatar-uploader-icon">
                      <Plus />
                    </el-icon>
                  </template>
                </el-image>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="标题" prop="title">
              <el-input v-model="page.dataEntity.value.title" placeholder="请输入标题"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="推送日期" prop="pushDate">
              <el-date-picker v-model="pushDateText" type="date" placeholder="请选择推送日期" value-format="YYYY-MM-DD"
                style="width: 200px" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="推送时间" prop="pushTime">
              <el-select v-model="pushTimeText" class="m-2" placeholder="请选择推送时间" style="width: 200px" filterable>
                <el-option v-for="item in timeArr" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="是否付费" prop="isPay">
              <el-switch v-model="page.dataEntity.value.isPay" />
            </el-form-item>
          </el-col>
          <!-- <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="是否置顶" prop="isTop">
              <el-switch v-model="page.dataEntity.value.isTop" />
            </el-form-item>
          </el-col> -->
          <el-col :xs="12" :sm="12" :md="12" :lg="12" v-show="page.mode === 'view' || page.mode === 'edit'">
            <el-form-item label="播放次数" prop="playbackCount">
              <el-input v-model="page.dataEntity.value.playbackCount" default-value="0" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="晚安故事" prop="storyVideoFilePath">
              <div style="display: flex;flex-direction: column;row-gap: 10px;">
                <video v-if="storyVideoFilePath" :src="storyVideoFilePath" controls="controls"
                  class="avatar my-video"></video>
                <el-progress v-if="isShowProgess" :percentage="percentage" :stroke-width="10" striped striped-flow
                  :duration="10" style="width: 255px" />
                <el-upload class="avatar-uploader" :action="config.adminRootUrl + '/api/Service?serviceName=UploadFile'"
                  :show-file-list="false" :on-success="handleAvatarSuccess" :on-progress="handleProgress"
                  :before-upload="beforeVideoUpload">
                  <el-button type="primary">上传视频</el-button>
                </el-upload>
              </div>
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
.form-item-width {
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

.my-video {
  height: 200px;
}
</style>
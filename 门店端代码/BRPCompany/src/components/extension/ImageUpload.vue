<script setup>
// 用于显示的临时图片地址
import common from "@/js/common";
import config from "@/js/config";

const imageUrl = ref('')

// 声明属性，用户绑定自定义组件的 v-model
const props = defineProps({
  modelValue: {
    default: ''
  },
  baseImgUrl: {
    type: String
  },
  disabled: {
    type: Boolean,
    default: false
  },
  mode: {
    type: String,
    default: 'add'
  }
})


watchEffect(() => {
  if (props?.modelValue) {
    common.call("GetTable", {tableName: 'sys_upload_file', condition: `id=${props.modelValue}`}, res => {
      imageUrl.value = props?.modelValue ? common.getImageUrl(res.data[0].path) : '';
    })
  } else {
    imageUrl.value = '';
  }
})

// 自定义事件，用户绑定自定义组件的 v-model
const emits = defineEmits(['update:modelValue', 'update:baseImgUrl', 'image-upload'])

// 是否显示进度条
const isShowProgess = ref(false)

// 上传进度值
const percentage = ref(0)

// 回调：图片上传成功之后
const handleAvatarSuccess = (response, uploadFile) => {
  stop();
  imageUrl.value = common.getImageUrl(response.data.path);
  console.log(response);
  isShowProgess.value = false;
  percentage.value = 0;
  emits('update:modelValue', response.data.id);
  emits('image-upload', response.data);
}

// 回调：图片上传之前
const beforeAvatarUpload = (rawFile) => {
  isShowProgess.value = true // 显示上传进度条
  if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {
    ElMessage.error('上传的文件必须是 jpg/png 格式!')
    isShowProgess.value = false // 关闭上传进度条
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('上传的文件大小不能超过 2MB!')
    isShowProgess.value = false // 关闭上传进度条
    return false
  }
  return true
}

// 上传进度回调
function handleProgress(evt) {
  percentage.value = Math.floor(evt.percent)
}
</script>
<template>
  <div>
    <!-- action="http://39.97.218.60/boss/course/upload" -->
    <el-progress v-show="isShowProgess" type="circle" :percentage="percentage" :width="178"/>
    <el-upload
      v-show="!isShowProgess"
      class="avatar-uploader"
      :action="config.adminRootUrl+'/api/Service?serviceName=UploadFile'"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
      :on-progress="handleProgress"
      :disabled="props.disabled"
    >
      <!--上传结束后，等图片加载完成以后，关闭上传进度条显示-->
      <img v-if="imageUrl" :src="imageUrl" class="avatar" @load="isShowProgess = false"/>
      <el-icon v-else class="avatar-uploader-icon">
        <Plus v-if="props.mode==='add' || props.mode==='edit'"/>
        <Picture v-if="props.mode==='view'"/>
      </el-icon>
    </el-upload>
  </div>
</template>

<style lang="scss" scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}

:deep(.avatar-uploader .el-upload) {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

:deep(.avatar-uploader .el-upload:hover) {
  border-color: var(--el-color-primary);
}

:deep(.el-icon.avatar-uploader-icon) {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>

<script setup>
import {reactive, ref} from 'vue';
import detail from '@/js/detail';
import common from "@/js/common";
import config from "@/js/config";
import DatabaseSelect from "@/components/extension/DatabaseSelect.vue";
import TreeSelect from "@/components/extension/TreeSelect.vue";
import ImageUpload from "@/components/extension/ImageUpload.vue";
import {ElMessage} from "element-plus";

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

//栏目树数据
const channelTreeData = ref();

let page = reactive({});
const init = (options) => {
  if (options.mode === 'edit' || options.mode === 'view') {
    if (!options.primaryKeyValue) {
      ElMessage({message: '请先选择一个节点', type: 'warning'})
      return;
    }
  }
  page = detail({
    baseTitle: '内容',
    tableName: 'cms_content',
    getServiceName: 'GetViewCmsContent',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    autoLoad: false,
    isCms: true,
    emit,
    onDetailHide() {
      fileList.value = [];
    }
  });
  detailFormVisible.value = false;
  setTimeout(() => {
    detailFormVisible.value = true;
    page.loadPage({
      // bindControls,
      processDataEntity
    })
  }, 1);
}

/**
 * 绑定控件
 * @param {*} options
 */
function bindControls(options) {
  page.bindControlsBase(options);
  //绑定上级栏目
  common.bindTreeSelect({
    tableName: "cms_channel",
    orderBy: "display_order",
    condition: "cms_site_id=" + common.getCmsSiteId(),
    treeData: channelTreeData
  })
}

/**
 * 处理数据实体
 */
function processDataEntity(dataEntity) {
  return dataEntity;
}

function positiveInteger(rule, value, callback) {
  if (!/^[1-9]\d*$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正整数'))
  } else {
    // 校验通过
    callback()
  }
}

//表单校验规则
const rules = reactive({
  title: [
    {required: true, message: '请输入标题', trigger: 'blur'},
  ],
  cmsChannelId: [
    {required: true, message: '请选择所属栏目', trigger: 'blur'},
  ],
  displayOrder: [
    {required: true, message: '请输入显示顺序', trigger: 'blur'},
    {validator: positiveInteger, trigger: 'blur'}
  ],
})
//暴露
defineExpose({
  init
})


const fileList = ref([])

const handleRemove = (file, uploadFiles) => {
  console.log(file, uploadFiles)
}
const attachmentFileSuccess = (response, uploadFile, uploadFiles) => {
  page.dataEntity.value.attachmentFileId = response.data.id;
}
const attachmentFileError = (error, uploadFile, uploadFiles) => {
  console.log(error)
  ElMessage.error('上传失败')
}
const handleExceed = (files, uploadFiles) => {
  ElMessage.warning(`只能上传一份附件`)
}

const beforeRemove = (uploadFile, uploadFiles) => {
  return ElMessageBox.confirm(
      `Cancel the transfer of ${uploadFile.name} ?`
  ).then(
      () => true,
      () => false
  )
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

//上传文件
const uploadImage = (file) => {
  common.uploadFile(file, function (result) {
    page.dataEntity.value.videoFilePath = result.data.path;
    page.dataEntity.value.videoFileId = result.data.id;
    // let path = common.getAdminUrl(page.dataEntity.value, 'videoFilePath')
    // let videoCoverImgBase64 = getVideoCoverImg(path);
    // page.dataEntity.value.storyVideoCoverImg = videoCoverImgBase64;
  });
};

const videoFilePath = computed(() => {
  return common.getAdminUrl(page.dataEntity.value, 'videoFilePath');
})
const getCmsChannelCondition = ref();
getCmsChannelCondition.value = `cms_site_id = ${localStorage.getItem('cmsSiteId')}`;
</script>
<template>
  <div>
    <el-dialog :close-on-click-modal="false" v-if="detailFormVisible" v-model="detailFormVisible"
               :title="page.title ? page.title.value : ''"
               width="1000" draggable>
      <el-scrollbar style="height:500px">
        <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" label-position="top"
                 :disabled="page.mode === 'view'" label-width="80px">
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="标题" prop="title">
                <el-input v-model="page.dataEntity.value.title" placeholder="请输入标题"
                          style="width: 100%;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="副标题" prop="subTitle">
                <el-input v-model="page.dataEntity.value.subTitle" placeholder="请输入副标题"
                          style="width: 100%;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="外部连接" prop="url">
                <el-input v-model="page.dataEntity.value.url" placeholder="请输入外部连接"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="所属栏目" prop="cmsChannelId">
                <TreeSelect table-name="cms_channel" v-model="page.dataEntity.value.cmsChannelId"
                            :condition="getCmsChannelCondition"></TreeSelect>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="显示顺序" prop="displayOrder">
                <el-input v-model.number="page.dataEntity.value.displayOrder" placeholder="请输入显示顺序"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="发布日期" prop="publishDate">
                <el-date-picker style="width: 200px;" v-model="page.dataEntity.value.publishDate" type="date"
                                placeholder="请选择发布日期"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="来源" prop="source">
                <el-input v-model="page.dataEntity.value.source" placeholder="请输入来源"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="关键字" prop="keywords">
                <el-input v-model="page.dataEntity.value.keywords" placeholder="请输入关键字"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="内容状态" prop="cmsContentStatusCode">
                <DatabaseSelect table-name="cms_content_status" v-model="page.dataEntity.value.cmsContentStatusCode">
                </DatabaseSelect>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="作者" prop="author">
                <el-input v-model="page.dataEntity.value.author" placeholder="请输入作者"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="附件" prop="attachmentFileId">
                <el-upload v-model:file-list="fileList" class="upload-demo"
                           :action="config.adminRootUrl + '/api/Service?serviceName=UploadFile'"
                           :on-remove="handleRemove"
                           :on-success="attachmentFileSuccess" :on-error="attachmentFileError" :limit="1"
                           :on-exceed="handleExceed">
                  <el-button type="primary">上传附件</el-button>
                  <!--                  <template #tip>
                                      <div class="el-upload__tip">
                                        jpg/png files with a size less than 500KB.
                                      </div>
                                    </template>-->
                </el-upload>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="图片" prop="imageFileId">
                <ImageUpload v-model="page.dataEntity.value.imageFileId"
                             :base-img-url="page.dataEntity.value.imageFilePath"></ImageUpload>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="是否推荐" prop="isRecommend">
                <el-switch v-model="page.dataEntity.value.isRecommend"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="是否热点" prop="isHot">
                <el-switch v-model="page.dataEntity.value.isHot"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="是否置顶" prop="isTop">
                <el-switch v-model="page.dataEntity.value.isTop"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="是否醒目" prop="isHighlight">
                <el-switch v-model="page.dataEntity.value.isHighlight"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="摘要" prop="summary">
                <el-input type="textarea" v-model="page.dataEntity.value.summary" rows="5"
                          placeholder="摘要"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="备注" prop="remark">
                <el-input type="textarea" v-model="page.dataEntity.value.remark" rows="5" placeholder="备注"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="描述" prop="description">
                <el-input v-model="page.dataEntity.value.description" type="textarea" rows="5"
                          placeholder="请输入描述"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="6" :sm="6" :md="6" :lg="6">
              <el-form-item label="视频" prop="videoFilePath">
                <video v-if="videoFilePath" :src="videoFilePath" controls="controls" class="avatar"></video>
                <el-upload class="avatar-uploader" action="uploadPath" :show-file-list="false"
                           :before-upload="beforeAvatarUpload" :http-request="uploadImage">
                  <el-button type="primary" style="margin-top:10px">上传视频</el-button>
                </el-upload>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="简介" prop="text">
                <vue-ueditor-wrap v-model="page.dataEntity.value.text" editor-id="editor" :config="config.editorConfig"
                                  :editorDependencies="['ueditor.config.js', 'ueditor.all.js']"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-scrollbar>
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
.el-dialog {
  width: 500px;
}

.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 200px;
}

.el-date-editor {
  width: 200px;
}

.el-input {
  width: 200px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

.el-upload-list {
  border: 1px dashed var(--el-border-color);
}

.upload-demo {
  width: 200px;
}
</style>

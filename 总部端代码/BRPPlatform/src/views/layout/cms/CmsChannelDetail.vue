<script setup>
import { nextTick, reactive, ref } from 'vue';
import detail from '@/js/detail';
import common from "@/js/common";
import config from "@/js/config";
import TreeSelect from "@/components/extension/TreeSelect.vue";

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
  //获取组织机构树
  // getCmsChannelTree(options.primaryKeyValue);
  page = detail({
    baseTitle: '栏目',
    tableName: 'cms_channel',
    getServiceName: 'GetViewCmsChannel',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    autoLoad: false,
    isCms: true,
    detailFormVisible,
    refDataEntityForm,
    emit
  });
  detailFormVisible.value = false;
  setTimeout(() => {
    detailFormVisible.value = true;
    page.loadPage({
      processDataEntity: processDataEntity,
      bindControls: bindControls,
    });
  }, 100);
}
function bindControls(options) {
  page.bindControlsBase(options);
  if (page.isAddMode()) {
    //绑定上级栏目
    common.bindTreeSelect({
      tableName: "cms_channel",
      orderBy: "display_order",
      condition: "cms_site_id=" + common.getCmsSiteId(),
      treeData: channelTreeData
    })
  }
}
/**
 * 处理数据实体
 */
function processDataEntity(dataEntity) {
  dataEntity.imageUrl = common.getAdminUrl(dataEntity.imageFilePath);
  //绑定上级栏目
  common.bindTreeSelect({
    tableName: "cms_channel",
    condition: "cms_site_id=" + common.getCmsSiteId(),
    orderBy: "display_order",
    treeData: channelTreeData
  })
  return dataEntity;
}
//暴露
defineExpose({
  init
})

//表单校验规则
const rules = reactive({
  name: [
    { required: true, message: '请输入名称', trigger: 'blur' }
  ],
  code: [
    { required: true, message: '请输入编码', trigger: 'blur' }
  ]
})

const uploadImage = async (file) => {
  console.log(file);
  common.uploadFile(file, function (result) {
    console.log(result);
    page.dataEntity.value.imageFilePath = result.data.path;
    page.dataEntity.value.imageFileId = result.data.id;
    page.dataEntity.value.imageUrl = common.getAdminUrl(page.dataEntity.value.imageFilePath);
  });
};
</script>
<template>
  <div>
    <el-dialog :close-on-click-modal="false" v-if="detailFormVisible" v-model="detailFormVisible" :title="page.title ? page.title.value : ''"
      width="1000" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" label-position="top"
        :disabled="page.mode === 'view'" label-width="120px">
        <el-scrollbar height="500px">
          <el-row>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="编码" prop="code">
                <el-input v-model="page.dataEntity.value.code" placeholder="请输入编码"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="名称" prop="name">
                <el-input v-model="page.dataEntity.value.name" placeholder="请输入名称"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="上级" prop="parentId" style="width:200px">
<!--                <el-tree-select v-model="page.dataEntity.value.parentId" :data="channelTreeData" check-strictly
                  :render-after-expand="false" />-->
                <TreeSelect table-name="cms_channel" v-model="page.dataEntity.value.parentId"></TreeSelect>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="显示顺序" prop="displayOrder">
                <el-input v-model="page.dataEntity.value.displayOrder" placeholder="请输入显示顺序"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="导航地址" prop="url">
                <el-input v-model="page.dataEntity.value.url" placeholder="请输入导航地址"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12">
              <el-form-item label="图片" prop="imageFilePath">
                <el-upload class="avatar-uploader" action="uploadPath" :show-file-list="false"
                  :http-request="uploadImage">
                  <img v-if="page.dataEntity.value.imageUrl" :src="page.dataEntity.value.imageUrl" class="upload-img" />
                  <el-icon v-else class="avatar-uploader-icon">
                    <Plus />
                  </el-icon>
                </el-upload>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="描述" prop="description">
                <el-input v-model="page.dataEntity.value.description" type="textarea" placeholder="请输入描述"
                  rows="6"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="栏目内容" prop="text">
                <vue-ueditor-wrap v-model="page.dataEntity.value.text" editor-id="editor" :config="config.editorConfig"
                  :editorDependencies="['ueditor.config.js', 'ueditor.all.js']" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-scrollbar>
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

.upload-img {
  height: 60px;
}
</style>

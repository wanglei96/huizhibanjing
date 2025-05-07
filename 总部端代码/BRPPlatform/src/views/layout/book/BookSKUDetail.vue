<script setup>
import { reactive, ref, watch } from 'vue';
import detail from '@/js/detail';
import common from '@/js/common';
import config from '@/js/config';
import ImageUpload from "@/components/extension/ImageUpload.vue";

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

let page = reactive({});

const languageList = ref([]);
const bindingTypeList = ref([]);
const ageGroupList = ref([]);
const bookTagList = ref([]);
const bookSeriesList = ref([]);

const init = (options) => {
  page = detail({
    baseTitle: '书籍SKU',
    tableName: 'book_sku',
    getServiceName: 'GetViewBookSku',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  });
  detailFormVisible.value = false;
  setTimeout(() => {
    detailFormVisible.value = true;
    page.loadPage({
      // bindControls,
      processDataEntity
    })
  }, 1);
  //获取语言分类下拉框
  getSelected({
    tableName: 'language',
    selectedList: languageList,
  });
  //获取系列下拉框
  getSelected({
    tableName: 'book_series',
    selectedList: bookSeriesList,
    condition: "company_id is null"
  });
  //获取装订方式下拉框
  getSelected({
    tableName: 'binding_type',
    selectedList: bindingTypeList,
  });
  //获取年龄段下拉框
  getSelected({
    tableName: 'age_group',
    selectedList: ageGroupList,
  });
  //获取标签下拉框
  getSelected({
    tableName: 'book_tag',
    selectedList: bookTagList,
    condition: "company_id is null"
  });
  if (options.mode === 'edit' || options.mode === 'view') {
    outPutEntity(options.primaryKeyValue);
  }

}
/**
 * 处理数据实体
 */
function processDataEntity(dataEntity) {
  return dataEntity;
}
//提交表单
function confirm() {
  let options = {
    getDataEntity
  }
  refDataEntityForm.value.validate().then(() => {
    page.isLoading.value = true;
    page.save(options);
  }).catch((res) => {
    console.log(res);
    ElMessage({ message: '表单格式有误', type: 'warning' });
  })
}
function getDataEntity(data) {
  data.value.ageGroupSelectValue = ageGroupSelectValue.value;
  data.value.bookTagSelectValue = bookTagSelectValue.value;
  return data;
}
//获取下拉框
function getSelected(options) {
  let param = {
    "tableName": options.tableName
  };
  if (options.condition) {
    param.condition = options.condition;
  }
  common.call("GetTable", param, function (result) {
    options.selectedList.value = result.data;
  })
}

const ageGroupSelectValue = ref([])

const bookTagSelectValue = ref([])

const setScrollbarPosition = inject('setScrollbarPosition');
//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    ageGroupSelectValue.value = []
    bookTagSelectValue.value = []
    setScrollbarPosition()
  }
})

function outPutEntity(primaryKeyValue) {
  //获取年龄段
  bindBookSkuAgeGroup(primaryKeyValue);
  //绑定标签
  bindBookSkuTag(primaryKeyValue);
}
/**
 * 获取年龄段
 * @param primaryKeyValue
 */
function bindBookSkuAgeGroup(primaryKeyValue) {
  let param = {
    "bookSkuId": primaryKeyValue
  };
  common.call("GetBookSkuAgeGroupList", param, function (result) {
    let dataList = result.data.dataList;
    let ageGroupIdArray = dataList.map(item => item.ageGroupId);
    ageGroupSelectValue.value = ageGroupIdArray;
  })
}
/**
 * 绑定标签
 * @param primaryKeyValue
 */
function bindBookSkuTag(primaryKeyValue) {
  let param = {
    "bookSkuId": primaryKeyValue
  };
  common.call("GetBookSkuBookTagList", param, function (result) {
    let dataList = result.data.dataList;
    let bookTagSelectValueIdArray = dataList.map(item => item.bookTagId);
    bookTagSelectValue.value = bookTagSelectValueIdArray;
  })
}


const uploadImage = async (file) => {
  console.log(file);
  common.uploadFile(file, function (result) {
    console.log(result);
    page.dataEntity.value.imageFilePath = result.data.path;
    page.dataEntity.value.imageFileId = result.data.id;
  });
};


const imageUrl = computed(() => {
  return common.getAdminUrl(page.dataEntity.value, 'imageFilePath');
})


const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {
    ElMessage.error('请上传图片格式!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 5) {
    ElMessage.error('图片不能大于 5MB!')
    return false
  }
  return true
}




//暴露
defineExpose({
  init
})

//表单校验规则
const rules = reactive({
  bookName: [
    { required: true, message: '请输入书名', trigger: 'blur' }
  ],
  author: [
    { required: true, message: '请输入作者', trigger: 'blur' }
  ],
  ISBN: [
    { required: true, message: 'ISBN', trigger: 'blur' }
  ],
  languageCode: [
    { required: true, message: '请选择语言', trigger: 'change' }
  ],
  bindingTypeCode: [
    { required: true, message: '请选择装订方式', trigger: 'change' }
  ],
  press: [
    { required: true, message: '请选择出版社', trigger: 'change' }
  ],
  price: [
    { required: true, message: '请输入价格', trigger: 'change' },
    { validator: floatValidator, trigger: 'blur' }
  ]
})

function floatValidator(rule, value, callback) {
  if (value && !/^(?=.+)(?:[1-9]\d*|0)?(?:\.\d+)?$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正确金额'))
  } else {
    // 校验通过
    callback();
  }
}

</script>
<template>
  <div>
    <el-dialog :close-on-click-modal="false" v-if="detailFormVisible" v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''" width="1200" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode == 'view'" label-width="80px">
        <el-scrollbar height="500px">
          <el-row>
            <el-col :xs="6" :sm="6" :md="6" :lg="6">
              <el-form-item label="封面" prop="cover">
                <el-upload class="avatar-uploader" action="uploadPath" :show-file-list="false"
                  :before-upload="beforeAvatarUpload" :http-request="uploadImage">
                  <el-image :src="imageUrl" class="avatar">
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
            <el-col :xs="6" :sm="6" :md="6" :lg="6">
              <el-form-item label="书名" prop="bookName">
                <el-input v-model="page.dataEntity.value.bookName" placeholder="请输入书名"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="6" :sm="6" :md="6" :lg="6">
              <el-form-item label="作者" prop="author">
                <el-input v-model="page.dataEntity.value.author" placeholder="请输入作者"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="6" :sm="6" :md="6" :lg="6">
              <el-form-item label="系列" prop="bookSeriesId">
                <el-select v-model="page.dataEntity.value.bookSeriesId" placeholder="请选择系列" filterable clearable>
                  <el-option v-for="item in bookSeriesList" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="6" :sm="6" :md="6" :lg="6">
              <el-form-item label="语言分类" prop="languageCode">
                <el-select v-model="page.dataEntity.value.languageCode" placeholder="请选择语言分类" filterable clearable>
                  <el-option v-for="item in languageList" :key="item.code" :label="item.name" :value="item.code" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="6" :sm="6" :md="6" :lg="6">
              <el-form-item label="出版社" prop="press">
                <el-input v-model="page.dataEntity.value.press" placeholder="请输入出版社"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="6" :sm="6" :md="6" :lg="6">
              <el-form-item label="定价" prop="price">
                <el-input v-model="page.dataEntity.value.price" placeholder="请输入价格"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="6" :sm="6" :md="6" :lg="6">
              <el-form-item label="ISBN" prop="ISBN">
                <el-input v-model="page.dataEntity.value.ISBN" placeholder="请输入ISBN"></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="6" :sm="6" :md="6" :lg="6">
              <el-form-item label="装订方式" prop="bindingTypeCode">
                <el-select v-model="page.dataEntity.value.bindingTypeCode" placeholder="请选择装订方式" filterable clearable>
                  <el-option v-for="item in bindingTypeList" :key="item.code" :label="item.name" :value="item.code" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="年龄段" prop="ageGroup">
                <el-select v-model="ageGroupSelectValue" multiple clearable placeholder="请选择年龄段" filterable
                  style="width: 99%;">
                  <el-option v-for="item in ageGroupList" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="标签" prop="bookTag">
                <el-select v-model="bookTagSelectValue" multiple clearable placeholder="请选择标签" filterable
                  style="width: 99%;">
                  <el-option v-for="item in bookTagList" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="备注" prop="remark">
                <el-input v-model="page.dataEntity.value.remark" type="textarea" placeholder="请输入备注" style="width: 99%;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="简介" prop="introduction">
                <vue-ueditor-wrap v-model="page.dataEntity.value.introduction" editor-id="editor" style="width: 99%;"
                  :config="config.editorConfig" :editorDependencies="['ueditor.config.js', 'ueditor.all.js']" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-scrollbar>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" v-if="page.mode == 'add' || page.mode == 'edit'" :loading="page.isLoading.value"
            @click="confirm()"> 确定 </el-button>
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

.el-input {
  width: 200px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
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

.edui-editor-iframeholder {
  width: 100% !important;
}

.edui-editor {
  width: 100% !important;
}
</style>
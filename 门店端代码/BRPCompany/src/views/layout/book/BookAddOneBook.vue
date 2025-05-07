<script setup>
import { reactive, ref, watch } from 'vue';
import detail from '@/js/detail';
import common from '@/js/common';
import config from '@/js/config';

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

// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

const init = (options) => {
  page = detail({
    baseTitle: '书籍SKU',
    tableName: 'book_sku',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm
  });
  detailFormVisible.value = true;
  //获取语言分类下拉框
  getSelected({
    tableName: 'language',
    selectedList: languageList,
  });
  //获取系列下拉框
  getSelected({
    tableName: 'book_series',
    selectedList: bookSeriesList,
    condition: "company_id = " + companyId.value,
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
  getSelected({
    tableName: 'book_tag',
    selectedList: bookTagList,
    condition: "company_id = " + companyId.value,
    orderBy: "display_order asc"
  });
}

// 提交表单
const openBookAddList = inject('openBookAddList');
function confirm() {

  refDataEntityForm.value.validate().then(() => {
    detailFormVisible.value = false;
    const dataEntity = page.dataEntity.value;
    // 获取表单数据
    dataEntity.ageGroupList = getAgeGroupSelectDataItem();
    dataEntity.bookTagNameList = getBookTagSelectDataItem();
    dataEntity.bookSeriesName = getIdValueName(page.dataEntity.value.bookSeriesId, bookSeriesList.value);
    dataEntity.languageName = getCodeValueName(page.dataEntity.value.languageCode, languageList.value);
    dataEntity.bindingTypeName = getCodeValueName(page.dataEntity.value.bindingTypeCode, bindingTypeList.value);
    openBookAddList({
      dataEntity: dataEntity,
      type: 'one'
    })
  }).catch((res) => {
    console.log(res);
    ElMessage({ message: '表单格式有误', type: 'warning' });
  })
}
function getCodeValueName(key, valueList) {
  const foundItem = valueList.find(item => item.code === key);
  const value = foundItem ? foundItem.name : null;
  return value;
}
function getIdValueName(key, valueList) {
  const foundItem = valueList.find(item => item.id === key);
  const value = foundItem ? foundItem.name : null;
  return value;
}
function getBookTagSelectDataItem() {
  const selectedIds = bookTagSelectValue.value;
  const selectedObjects = bookTagList.value.filter(item => selectedIds.includes(item.id));
  const selectedNames = selectedObjects.map(item => item.name);
  return selectedNames;
}
function getAgeGroupSelectDataItem() {
  const selectedIds = ageGroupSelectValue.value;
  const selectedObjects = ageGroupList.value.filter(item => selectedIds.includes(item.id));
  const selectedObjectsArray = selectedObjects.map(item => ({ ...item }));
  return selectedObjectsArray;
}

//获取下拉框
function getSelected(options) {
  let param = {
    "tableName": options.tableName,
    pageSize: 99999
  };
  if (options.condition) {
    param.condition = options.condition;
  }
  if (options.orderBy) {
    param.orderBy = options.orderBy;
  }
  common.call("GetTable", param, function (result) {
    options.selectedList.value = result.data;
  })
}

const ageGroupSelectValue = ref([])

const bookTagSelectValue = ref([])


//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    ageGroupSelectValue.value = []
    bookTagSelectValue.value = []
  }
})


//暴露
defineExpose({
  init
})

function floatValidator(rule, value, callback) {
  if (!/^-?\d+(\.\d+)?$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正确金额'))
  } else {
    // 校验通过
    callback()
  }
}
function intValidator(rule, value, callback) {
  if (!/^[1-9]\d*$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入整数'))
  } else {
    // 校验通过
    callback()
  }
}

//表单校验规则
const rules = reactive({
  bookName: [
    { required: true, message: '请输入书名', trigger: 'blur' }
  ],
  author: [
    { required: true, message: '请输入作者', trigger: 'blur' }
  ],
  ISBN: [
    { required: true, message: 'ISBN', trigger: 'blur' },
    { validator: intValidator, trigger: 'blur' }
  ],
  languageCode: [
    { required: true, message: '请选择语言', trigger: 'change' }
  ],
  bindingTypeCode: [
    { required: true, message: '请选择装订方式', trigger: 'change' }
  ],
  press: [
    { required: true, message: '请输入出版社', trigger: 'change' }
  ],
  bookCount: [
    { required: true, message: '请输入本数', trigger: 'change' },
    { validator: intValidator, trigger: 'blur' }
  ],
  price: [
    { required: true, message: '请输入价格', trigger: 'blur' },
    { validator: floatValidator, trigger: 'blur' }
  ]
})

</script>
<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''" width="1200" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode == 'view'" label-width="80px">
        <el-row>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="书名" prop="bookName">
              <el-input v-model="page.dataEntity.value.bookName" placeholder="请输入书名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="系列" prop="bookSeriesId">
              <el-select v-model="page.dataEntity.value.bookSeriesId" placeholder="请选择系列" filterable>
                <el-option v-for="item in bookSeriesList" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="作者" prop="author">
              <el-input v-model="page.dataEntity.value.author" placeholder="请输入作者"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="语言分类" prop="languageCode">
              <el-select v-model="page.dataEntity.value.languageCode" placeholder="请选择语言分类" filterable>
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
            <el-form-item label="价格" prop="price">
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
              <el-select v-model="page.dataEntity.value.bindingTypeCode" placeholder="请选择装订方式" filterable>
                <el-option v-for="item in bindingTypeList" :key="item.code" :label="item.name" :value="item.code" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="本数" prop="bookCount">
              <el-input v-model="page.dataEntity.value.bookCount" placeholder="本数"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="年龄段" prop="ageGroup">
              <el-select v-model="ageGroupSelectValue" multiple clearable placeholder="请选择年龄段" filterable style="width: 100%;">
                <el-option v-for="item in ageGroupList" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="标签" prop="bookTag">
                <el-select v-model="bookTagSelectValue" multiple clearable placeholder="请选择标签" filterable style="width: 100%;">
                  <el-option v-for="item in bookTagList" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="page.dataEntity.value.remark" type="textarea" placeholder="请输入备注"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
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
  width: 300px;
}

.el-input {
  width: 300px;
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
</style>
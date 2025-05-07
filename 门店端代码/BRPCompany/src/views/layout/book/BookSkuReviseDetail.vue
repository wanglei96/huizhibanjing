<script setup>
import { reactive, ref, watch } from 'vue';
import detail from '@/js/detail';
import common from '@/js/common';
import config from '@/js/config'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import FieldRow from './FieldRow.vue';

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

let page = reactive({});


// 当前登陆人ID
const companyUserId = ref(
  `${common.getLocalStorage('companyUserId')}`
);
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
)
const bookSkuInfo = ref({});
const bookSkuFieldList = ref([]);
const bookSkuReviseItemList = ref([]); // 存储子页面数据的数组
const init = (options) => {
  bookSkuInfo.value = options.data;
  page = detail({
    baseTitle: '详情报错',
    tableName: 'book_sku_revise',
    getServiceName: 'GetViewBookSkuRevise',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit,
    getDataEntity(dataEntityRef) {
      dataEntityRef.value.bookSkuId = bookSkuInfo.value.id;
      dataEntityRef.value.bookSkuReviseItemList = bookSkuReviseItemList.value
    }
  });
  detailFormVisible.value = true;
  //获取年龄段下拉框
  getSelected({
    tableName: 'book_sku_field',
    selectedList: bookSkuFieldList,
  });
  if (options.mode == 'view') {
    getBookSkuReviseItemList(options.primaryKeyValue);
  }
}
function getBookSkuReviseItemList(bookSkuReviseId) {
  let data = {
    bookSkuReviseId: bookSkuReviseId,
    orderBy: 'create_time desc'
  }
  common.call('GetViewBookSkuReviseItemList', data, (res) => {
    bookSkuReviseItemList.value = res.data.dataList
  })
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
  common.call("GetTable", param, function (result) {
    options.selectedList.value = result.data;
  })
}

const addField = () => {
  bookSkuReviseItemList.value.push({
    bookSkuFieldCode: '',
    oldValue: '',
    newValue: ''
  });
};

const updateItemField = (index, value) => {
  //获取oldValue
  bookSkuReviseItemList.value[index].oldValue = bookSkuInfo.value[value];
}


const removeField = (index) => {
  bookSkuReviseItemList.value.splice(index, 1);
}

//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空
watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    bookSkuReviseItemList.value = []
  }
})
//暴露
defineExpose({
  init
})

//表单校验规则
//表单校验规则
const rules = reactive({
  companyUserId: [
    { required: true, message: '请选择办理人', trigger: 'change' }
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
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="办理人" prop="companyUserId">
              <database-select placeholder="请选择办理人" table-name="company_user" :condition="'company_id=' + companyId"
                v-model="page.dataEntity.value.companyUserId" :defaultValue="companyUserId" valueFieldName="id"
                class="form-item-width"></database-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-button type="primary" @click='addField'>添加字段</el-button>
        </el-row>
        <div v-for="(item, index) in bookSkuReviseItemList" :key="index" style="margin-top: 10px;">
          <FieldRow :bookSkuFieldList="bookSkuFieldList" @remove="removeField(index)" :index="index" :item="item"
            @fieldChange="updateItemField(index, $event)">
          </FieldRow>
        </div>
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
<script setup>
import { reactive, ref, watch } from 'vue';
import detail from '@/js/detail';
import common from '@/js/common';
import config from '@/js/config'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

let page = reactive({});

const bookStatusList = ref([]);
const borrowStatusList = ref([]);
const companyUserList = ref([]);

// 当前登陆人ID
const companyUserId = ref(
  `${common.getLocalStorage('companyUserId')}`
);
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

const init = (options) => {
  page = detail({
    baseTitle: '图书信息',
    tableName: 'book',
    getServiceName: 'GetViewBook',
    updateServiceName: 'UpdateBookAndSkuPrice',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit,
    processDataEntity
  });
  detailFormVisible.value = true;
  //获取图书状态下拉框
  getSelected({
    tableName: 'book_status',
    selectedList: bookStatusList,
  });
  //获取借阅状态下拉框
  getSelected({
    tableName: 'borrow_status',
    selectedList: borrowStatusList,
  });
}
function processDataEntity(dataEntity) {
  console.log("----dataEntity", dataEntity);
  return dataEntity;
}
//获取下拉框
function getSelected(options) {
  let param = {
    "tableName": options.tableName,
    pageSize: 99999
  };
  common.call("GetTable", param, function (result) {
    options.selectedList.value = result.data;
  })
}

//暴露
defineExpose({
  init
})

const offlineTimeText = computed({
  get() {
    return page.dataEntity.value.offlineTimeText;
  }, set(val) {
    page.dataEntity.value.offlineTimeText = val;
    page.dataEntity.value.offlineTime = val;
  }
})
function bookStatusChange(val) {
  if (val == "Off") {
    getSelected({
      tableName: 'company_user',
      selectedList: companyUserList,
    });
  } else {
    page.dataEntity.value.offlineTimeText = "";
    page.dataEntity.value.offlineReason = "";
    page.dataEntity.value.offlineApproverId = "";
  }
}
//表单校验规则
const rules = reactive({
  price: [
    { required: true, message: '请输入价格', trigger: 'blur' },
    { validator: floatValidator, trigger: 'blur' }
  ]
})

function floatValidator(rule, value, callback) {
  if (value && !/^(?=.+)(?:[1-9]\d*|0)?(?:\.\d+)?$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正数'))
  } else {
    // 校验通过
    callback();
  }
}

</script>
<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''" width="600" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode == 'view'" label-width="100px">
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="书名" prop="bookName">
              <el-input type="textarea" v-model="page.dataEntity.value.bookName" disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="价格" prop="price">
              <el-input v-model="page.dataEntity.value.price" placeholder="请输入价格"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="图书状态" prop="bookStatusCode">
              <el-select v-model="page.dataEntity.value.bookStatusCode" placeholder="请选择" @change="bookStatusChange"
                filterable>
                <el-option v-for="item in bookStatusList" :key="item.code" :label="item.name" :value="item.code" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="借阅状态" prop="borrowStatusCode">
              <el-select v-model="page.dataEntity.value.borrowStatusCode" placeholder="请选择" filterable
                :disabled="page.dataEntity.value.borrowStatusCode !== 'In'">
                <el-option v-for="item in borrowStatusList" :key="item.code" :label="item.name" :value="item.code" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-if="page.dataEntity.value.bookStatusCode == 'Off'">
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="下架时间" prop="offlineTimeText">
              <el-date-picker v-model="offlineTimeText" type="datetime" placeholder="请选择下架时间"
                value-format="YYYY-MM-DD HH:mm:ss" style="width: 200px" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24" v-if="page.dataEntity.value.bookStatusCode == 'Off'">
            <el-form-item label="下架原因" prop="description">
              <el-input v-model="page.dataEntity.value.offlineReason" type="textarea" placeholder="请输入描述"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24" v-if="page.dataEntity.value.bookStatusCode == 'Off'">
            <el-form-item label="下架操作人" prop="offlineApproverId">
              <database-select placeholder="请选择" table-name="company_user" :condition="'company_id=' + companyId"
                v-model="page.dataEntity.value.offlineApproverId" :defaultValue="companyUserId" valueFieldName="id"
                class="form-item-width"></database-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="是否可借" prop="isAvailable">
              <el-switch v-model="page.dataEntity.value.isAvailable"
                :disabled="page.dataEntity.value.borrowStatusCode !== 'In'" />
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
.el-dialog {
  width: 500px;
}

.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 100%;
}

/* .el-date-picker{
  width: 100%;
} */

/* .el-input {
  width: 200px;
} */

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
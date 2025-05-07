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

const sysUserId = ref(
  `${common.getLocalStorage('sysUserId')}`
);

let page = reactive({});

const bookSkuReviseItemList = ref([]); // 存储子页面数据的数组
const init = (options) => {
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
      dataEntityRef.value.bookSkuReviseItemList = selectValueArray.value
    }
  });
  detailFormVisible.value = true;
  getBookSkuReviseItemList(options.primaryKeyValue);
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

//多选选中数量
const selectedColumnCount = computed(() => {
  return selectValueArray.value.length
})
const selectValueArray = ref([])
//多选
const selectRow = (val) => {
  selectValueArray.value = []
  val.forEach((item) => {
    selectValueArray.value.push(item)
  })
}

//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空
watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    selectValueArray.value = []
  }
})

//暴露
defineExpose({
  init
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
            <el-form-item label="提交人" prop="companyUserId">
              <database-select placeholder="提交人" table-name="company_user" v-model="page.dataEntity.value.companyUserId"
                valueFieldName="id" class="form-item-width"></database-select>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="审核人" prop="approverId">
              <database-select placeholder="审核人" table-name="sys_user" :defaultValue="sysUserId"
                v-model="page.dataEntity.value.approverId" valueFieldName="id" textFieldName="true_name"
                class="form-item-width"></database-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-card shadow="never">
        <el-alert :title="`已选择 ${selectedColumnCount} 项`" v-show="selectedColumnCount > 0" type="warning" show-icon />
        <el-table ref="multipleTableRef" :data="bookSkuReviseItemList" style="width: 100%"
          @selection-change="selectRow">
          <el-table-column type="selection" width="55"/>
          <el-table-column property="bookSkuFieldName" label="字段名称" />
          <el-table-column property="oldValue" label="原值" />
          <el-table-column property="newValue" label="新值" />
          <el-table-column property="approveStatusName" label="审核状态" />
        </el-table>
      </el-card>
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
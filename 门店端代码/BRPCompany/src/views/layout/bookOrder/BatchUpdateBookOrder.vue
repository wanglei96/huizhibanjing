<script setup>
import { inject, reactive, ref } from 'vue'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'

const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

const refDataEntityForm = ref();

//定义事件发射器
const emit = defineEmits(['reload-data']);
//组件开关
const detailFormVisible = ref(false);

const bookOrderIds = ref([]);
//确定按钮是否加载中
const isLoading = ref(false);

const dataItem = ref({});

function init(orderIds) {
  // 判断是否选择单据
  if (orderIds.length == 0) {
    ElMessage({ message: "请先选择单据！", type: 'warning' })
    return;
  }
  // 显示弹窗
  detailFormVisible.value = true
  bookOrderIds.value = orderIds;
}

function save() {
  isLoading.value = true;
  setTimeout(() => {
    isLoading.value = false;
  }, 310);
  let data = {
    bookOrderIds: bookOrderIds.value,
    companyId: companyId.value,
  };
  if (dataItem.value.deliverTime) {
    data.deliverTime = dataItem.value.deliverTime
  }
  if (dataItem.value.delivererId) {
    data.delivererId = dataItem.value.delivererId
  }
  if (dataItem.value.bookpickerId) {
    data.bookpickerId = dataItem.value.bookpickerId
  }
  if (!dataItem.value.bookpickerId && !dataItem.value.deliverTime & !dataItem.value.delivererId) {
    return
  }
  common.call('BatchUpdateBookOrder', data, function (res) {
    detailFormVisible.value = false;
    ElMessage({ message: "操作成功", type: 'success' })
    emit('reload-data');
  })
}

watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    dataItem.value = {}
  }
})
defineExpose({
  init
})
</script>

<template>
  <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" title="批量修改借还书单" width="1000"
    draggable>
    <div style="color: red;margin-bottom: 20px;font-size: 15px;">注意!如果没有选择任何选项，点提交，则表示不修改!</div>
    <el-form :model="dataItem" :rules="rules" ref="refDataEntityForm" :label-position="'right'" label-width="100px">
      <el-row>
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <el-form-item label="配送时间" prop="deliverTime">
            <el-date-picker v-model="dataItem.deliverTime" type="datetime" value-format="YYYY-MM-DD HH:mm:ss"
              placeholder="配送时间" />
          </el-form-item>
        </el-col>
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <el-form-item label="配送员" prop="delivererId">
            <DatabaseSelect table-name="view_company_user"
              :condition="'company_id=' + companyId + ' and is_deliverer =1 '" v-model="dataItem.delivererId"
              valueFieldName="id" placeholder="请选择" class="form-item-width" />
          </el-form-item>
        </el-col>
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <el-form-item label="拣书员" prop="bookpickerId">
            <DatabaseSelect table-name="view_company_user"
              :condition="'company_id=' + companyId + ' and is_bookpicker =1 '" v-model="dataItem.bookpickerId"
              valueFieldName="id" placeholder="请选择" class="form-item-width" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" :loading="isLoading" @click="save"> 确定 </el-button>
        <el-button @click="detailFormVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
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

.order-title {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-bottom: 30px;
}

.order-title-info {
  margin-left: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;

  .order-type {
    font-size: 16px;
  }

  .order-no {
    font-size: 13px;
    color: #999;
    margin-top: 8px;
  }
}

.order-info-item {
  border-bottom: 1px dashed #eee;
}

.order-info-title {
  width: 100%;
  height: 40px;
  line-height: 40px;
  display: flex;
  align-items: center;
}

.order-info-title span {
  display: inline-block;
  width: 3px;
  height: 15px;
  background-color: #0256ff;
  margin-right: 10px;
}

.order-info-item {
  padding: 15px;
  font-size: 14px;

  .el-row {
    margin-top: 10px;
  }

  .el-col {
    margin-bottom: 18px;
  }
}
</style>
<script setup>
import {reactive, ref} from 'vue';
import detail from '@/js/detail';
import common from "@/js/common";

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

let page = reactive({});
const init = (options) => {
  detailFormVisible.value = true
  page = detail({
    baseTitle: '订阅记录',
    tableName: 'night_story_order',
    getServiceName: 'GetViewNightStoryOrder',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  });
  getsubscribeTypeList();
}

//暴露
defineExpose({
  init
})

//表单校验规则
const rules = reactive({
  subscribeTypeCode: [
    {required: true, message: '请选择订阅类型', trigger: 'blur'}
  ],
  expireTimeText: [
    {required: true, message: '请选择到期时间', trigger: 'blur'}
  ]
})
const subscribeTimeText = computed({
  get() {
    return page.dataEntity.value.subscribeTimeText;
  }, set(val) {
    page.dataEntity.value.subscribeTimeText = val;
    page.dataEntity.value.subscribeTime = val;
  }
})
const expireTimeText = computed({
  get() {
    return page.dataEntity.value.expireTimeText;
  }, set(val) {
    page.dataEntity.value.expireTimeText = val;
    page.dataEntity.value.expireTime = val;
  }
})

//订单类型列表
const subscribeTypeList = ref([{code: '', name: '请选择'}]);

function getsubscribeTypeList() {
  let data = {
    tableName: 'subscribe_type',
    orderBy: 'display_order',
    pageSize: 99999
  };
  common.call('GetTable', data, (res) => {
    subscribeTypeList.value.splice(1, subscribeTypeList.value.length, ...res.data);
  })
}
</script>
<template>
  <div>
    <el-dialog :close-on-click-modal="false" v-model="detailFormVisible" :title="page.title?page.title.value:''" width="800" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
               :disabled="page.mode === 'view'" label-width="80px">
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="会员" prop="memberName">
              <el-input v-model="page.dataEntity.value.memberName" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="订阅时间" prop="subscribeTimeText">
              <el-date-picker
                  v-model="subscribeTimeText"
                  type="datetime"
                  placeholder="请选择订阅时间"
                  value-format="YYYY-MM-DD hh:mm:ss"
                  style="width: 200px" disabled
              />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="订阅类型" prop="subscribeTypeCode">
              <el-select v-model="page.dataEntity.value.subscribeTypeCode" placeholder="请选择订阅类型" filterable
                         class="form-item-width">
                <el-option
                    v-for="item in subscribeTypeList"
                    :key="item.code"
                    :label="item.name"
                    :value="item.code"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="到期时间" prop="expireTimeText">
              <el-date-picker
                  v-model="expireTimeText"
                  type="datetime"
                  placeholder="请选择到期时间"
                  value-format="YYYY-MM-DD hh:mm:ss"
                  style="width: 200px"
              />
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

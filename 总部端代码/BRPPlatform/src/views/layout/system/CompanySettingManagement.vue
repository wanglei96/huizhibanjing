<script setup>
import {reactive, ref} from 'vue';
import management from '@/js/management';
import common from '@/js/common';
import {ElMessage} from "element-plus";

//表单
const refDataEntityForm = ref();

const list = management({
  tableName: 'sys_setting',
  getListServiceName: 'GetSysSettingList',
  orderBy: 'code',
  condition: `sys_setting_category_code='Business'`,
  pageSize: 999999,
  processDataItem(dataItem) {
    if (dataItem.sysDataTypeCode === 'bool') {
      dataItem.value = dataItem.value === 'true';
    }
    return dataItem;
  }
})
//表单校验规则
const rules = reactive({
  /*  companyNo: [
      {required: true, message: '请输入门店编号', trigger: 'blur'},
      {min: 3, max: 14, message: '门店编号长度为3~12位数字', trigger: 'blur'},
      {pattern: /^[0-9]+$/, message: '门店编号长度为3~12位数字', trigger: 'blur'}
    ]*/
});

function batchUpdateSysSetting() {
  let sysSettingList = toRaw(list.dataList.value);
  sysSettingList.forEach(item => {
    item.value = item.value.toString();
  });
  common.call('BatchUpdateSysSetting', {sysSettingList: list.dataList.value}, (res) => {
    list.loadData();
    ElMessage({type: 'success', message: '操作成功'});
  });
}
</script>
<template>
  <div class="basic-list">
    <el-card>
      <el-form :model="list.dataList.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
               label-width="230px">
        <el-row  :gutter="20" v-for="item in list.dataList.value" :key="item.id">
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item :label="item.name" prop="value">
              <!--布尔型-->
              <template v-if="item.sysDataTypeCode==='bool'">
                <el-switch v-model="item.value"/>
              </template>
              <!--定点小数-->
              <template v-if="item.sysDataTypeCode==='decimal'">
                <el-input v-model.number="item.value"></el-input>
              </template>
              <!--整数-->
              <template v-if="item.sysDataTypeCode==='int'">
                <el-input v-model="item.value"></el-input>
              </template>
              <!--字符串-->
              <template v-if="item.sysDataTypeCode==='string'">
                <!--日期选择框-->
                <template v-if="item.sysUiControlCode==='DateBox'">
                  <el-date-picker
                      v-model="item.value"
                      type="datetime"
                      value-format="YYYY-MM-DD hh:mm:ss"
                      style="width: 200px"
                  />
                </template>
                <!--日期时间选择框-->
                <template v-else-if="item.sysUiControlCode==='DateTimeBox'">
                  <el-date-picker
                      v-model="item.value"
                      type="datetime"
                      value-format="YYYY-MM-DD hh:mm:ss"
                      style="width: 200px"
                  />
                </template>
                <!--文本框-->
                <template v-else>
                  <el-input v-model="item.value"></el-input>
                </template>
              </template>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <div style="text-align: right;">
          <el-button type="primary" @click="batchUpdateSysSetting">确定</el-button>
        </div>
      </template>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.el-input {
  width: 200px;
}

.el-form {
  justify-content: center;
}

.el-col {
  margin: 0 auto;
}

</style>

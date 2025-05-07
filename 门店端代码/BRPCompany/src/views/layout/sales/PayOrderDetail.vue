<script setup>
import { reactive, ref } from 'vue';
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
    baseTitle: '账单',
    tableName: 'pay_order',
    getServiceName: 'GetViewPayOrder',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  });

  //添加模式
  if (page.mode === 'add') {
    getPayOrderNo();
    //默认没退款
    page.dataEntity.value.isRefund = false;
    page.dataEntity.value.isFinished = false;
    page.dataEntity.value.isPaid = false;
    page.dataEntity.value.businessDateText = new Date().format("yyyy-MM-dd hh:mm:ss");
    page.dataEntity.value.payTimeText = new Date().format("yyyy-MM-dd hh:mm:ss");
  }
  page.dataEntity.value.operatorId = localStorage.getItem('companyUserId');
  bindSelect();
}

//暴露
defineExpose({
  init
})

function getPayOrderNo() {
  let data = {
    tableName: 'pay_order',
    filedName: 'pay_order_no',
    length: 8,
  };
  common.call('CreateRandomNo', data, (res) => {
    page.dataEntity.value.payOrderNo = res.data;
  });
}

// 单据时间
const businessDateText = computed({
  get() {
    return page.dataEntity.value.businessDateText;
  }, set(val) {
    page.dataEntity.value.businessDateText = val;
    page.dataEntity.value.businessDate = val;
  }
})
//支付时间
const payTimeText = computed({
  get() {
    return page.dataEntity.value.payTimeText;
  }, set(val) {
    page.dataEntity.value.payTimeText = val;
    page.dataEntity.value.payTime = val;
  }
})
//付款方式
const memberList = ref([]);
//付款方式
const paymentMethodList = ref([]);
//付款项目
const paymentItemList = ref([]);
//账单类型
const payOrderTypeList = ref([]);

function bindSelect() {

  common.call('GetMemberList', { companyId: localStorage.getItem('companyId'), pageSize: 99999 }, (res) => {
    memberList.value = res.data.dataList;
  });
  common.call('GetTable', { tableName: 'payment_method', pageSize: 99999 }, (res) => {
    paymentMethodList.value = res.data;
  });
  common.call('GetTable', { tableName: 'payment_item', pageSize: 99999 }, (res) => {
    paymentItemList.value = res.data;
  });
  common.call('GetTable', { tableName: 'pay_order_type', pageSize: 99999 }, (res) => {
    payOrderTypeList.value = res.data;
  });
}

function floatValidator(rule, value, callback) {
  if (!/^(?=.+)(?:[1-9]\d*|0)?(?:\.\d+)?$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正数'))
  } else {
    // 校验通过
    callback()
  }
}

//表单校验规则
const rules = reactive({
  memberId: [
    { required: true, message: '请选择会员', trigger: 'change' }
  ],
  amount: [
    { required: true, message: '请输入支付金额', trigger: 'blur' },
    { validator: floatValidator, trigger: 'blur' }
  ],
  paymentMethodCode: [
    { required: true, message: '请选择付款方式', trigger: 'change' }
  ],
  paymentItemCode: [
    { required: true, message: '请选择付款项目', trigger: 'change' }
  ],
  /*  businessDateText: [
      {required: true, message: '请选择单据时间', trigger: 'change'}
    ],
    payTimeText: [
      {required: true, message: '请选择支付时间', trigger: 'change'}
    ],*/
  payOrderTypeCode: [
    { required: true, message: '请选择账单类型', trigger: 'change' }
  ],
})
</script>
<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''" width="800" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode === 'view'" label-width="100px">
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="账单编号" prop="payOrderNo">
              <el-input v-model="page.dataEntity.value.payOrderNo" disabled class="form-item-width"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="会员" prop="memberId">
              <el-select v-model="page.dataEntity.value.memberId" class="m-2 form-item-width" placeholder="请选择会员"
                filterable clearable>
                <el-option v-for="item in memberList" :key="item.id" :label="item.name" :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="支付金额" prop="amount">
              <el-input v-model.number="page.dataEntity.value.amount" placeholder="请输入支付金额"
                class="form-item-width"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="付款方式" prop="paymentMethodCode">
              <el-select v-model="page.dataEntity.value.paymentMethodCode" class="m-2 form-item-width"
                placeholder="请选择付款方式" clearable filterable>
                <el-option v-for="item in paymentMethodList" :key="item.code" :label="item.name" :value="item.code" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="付款项目" prop="paymentItemCode">
              <el-select v-model="page.dataEntity.value.paymentItemCode" class="m-2 form-item-width"
                placeholder="请选择付款项目" clearable filterable>
                <el-option v-for="item in paymentItemList" :key="item.code" :label="item.name" :value="item.code" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="是否支付成功" label-width='auto' prop="isPaid">
              <el-switch v-model="page.dataEntity.value.isPaid" class="form-item-width" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="单据时间" prop="businessDateText">
              <el-date-picker v-model="businessDateText" type="datetime" value-format="YYYY-MM-DD HH:mm:ss"
                placeholder="请选择单据时间" style="width: 200px" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="支付时间" prop="payTimeText">
              <el-date-picker v-model="payTimeText" type="datetime" value-format="YYYY-MM-DD HH:mm:ss"
                placeholder="请选择支付时间" class="form-item-width" style="width: 200px" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="账单类型" prop="payOrderTypeCode">
              <el-select v-model="page.dataEntity.value.payOrderTypeCode" class="m-2 form-item-width"
                placeholder="请选择账单类型" clearable filterable>
                <el-option v-for="item in payOrderTypeList" :key="item.code" :label="item.name" :value="item.code" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="是否完成" prop="isFinished">
              <el-switch v-model="page.dataEntity.value.isFinished" class="form-item-width" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12" v-if="page.mode !== 'add'">
            <el-form-item label="操作人" prop="operatorName">
              <input type="hidden" v-model="page.dataEntity.operatorId">
              <el-input v-model="page.dataEntity.value.operatorName" disabled class="form-item-width"></el-input>
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
  width: 200px !important;
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

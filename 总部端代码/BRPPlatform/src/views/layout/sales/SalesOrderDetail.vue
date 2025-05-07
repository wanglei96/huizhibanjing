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

//付款方式列表
const paymentMethodList = ref([]);

function getPaymentMethodList() {
  let data = {
    tableName: 'payment_method',
    orderBy: 'display_order',
    pageSize: 99999
  };
  common.call('GetTable', data, (res) => {
    paymentMethodList.value = res.data;
  })
}

//订单类型列表
const salesOrderTypeList = ref([]);

function getSalesOrderTypeList() {
  let data = {
    tableName: 'sales_order_type',
    orderBy: 'display_order',
    pageSize: 99999
  };
  common.call('GetTable', data, (res) => {
    salesOrderTypeList.value = res.data;
  })
}

//物流状态列表
const salesOrderStatusList = ref([]);

function getSalesOrderStatusList() {
  let data = {
    tableName: 'sales_order_status',
    orderBy: 'display_order',
    pageSize: 99999
  };
  common.call('GetTable', data, (res) => {
    salesOrderStatusList.value = res.data;
  })
}

//子订单列表
const salesOrderItemList = ref([]);

function getSalesOrderItemList(primaryKeyValue) {
  common.call('GetViewSalesOrderItemList', {salesOrderId: primaryKeyValue, orderBy: 'update_time desc'}, (res) => {
    salesOrderItemList.value = res.data.dataList;
  })
}

let page = reactive({});
const init = (options) => {
  page = detail({
    baseTitle: '订单',
    tableName: 'sales_order',
    getServiceName: 'GetViewSalesOrder',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit,
    onDetailHide() {
      salesOrderItemList.value.splice(0);
    },
    getDataEntity(dataEntityRef) {
      dataEntityRef.value.salesOrderItemList = salesOrderItemList.value;
    }
  });
  getSalesOrderItemList(options.primaryKeyValue);
  getPaymentMethodList();
  getSalesOrderStatusList();
  getSalesOrderTypeList();
  detailFormVisible.value = true
}

//暴露
defineExpose({
  init
})

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
  amount: [
    {required: true, message: '请输入支付金额', trigger: 'blur'},
    {validator: floatValidator, trigger: 'blur'}
  ],
  paymentMethodCode: [
    {required: true, message: '请选择付款方式', trigger: 'blur'}
  ],
  salesOrderTypeCode: [
    {required: true, message: '请选择订单类型', trigger: 'blur'}
  ],
  salesOrderStatusCode: [
    {required: true, message: '请选择物流状态', trigger: 'blur'}
  ],
  payTimeText: [
    {required: true, message: '请选择付款时间', trigger: 'blur'}
  ],
  receiverName: [
    {required: true, message: '请输入收货人', trigger: 'blur'}
  ],
  receiverPhone: [
    {required: true, message: '请输入收货电话', trigger: 'blur'},
    {pattern: /^1[3456789]\d{9}$/, message: '手机号格式有误', trigger: 'blur'}
  ],
  receiverAddress: [
    {required: true, message: '请输入收货地址', trigger: 'blur'}
  ]
})

function productQuantityChange(productId, quantity) {
  if (productId === 1) {
    salesOrderItemList.value[0].amount = parseFloat((quantity * salesOrderItemList.value[0].price).toFixed(2));
  } else if (productId === 2) {
    salesOrderItemList.value[1].amount = parseFloat((quantity * salesOrderItemList.value[1].price).toFixed(2));
  }
}

const salesOrderTotalAmount = computed(() => {
  let totalAmount = salesOrderItemList.value.reduce((totalAmount, lastItem) => {
    return totalAmount + lastItem.amount || 0;
  }, 0);
  page.dataEntity.value.amount = totalAmount;
  return totalAmount;
});
</script>
<template>
  <div>
    <el-dialog :close-on-click-modal="false" v-model="detailFormVisible" :title="page.title?page.title.value:''"
               width="1200" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
               :disabled="page.mode === 'view'" label-width="80px">
        <el-row>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="门店" prop="companyName">
              <el-input v-model="page.dataEntity.value.companyName" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="订单编号" prop="salesOrderNo">
              <el-input v-model="page.dataEntity.value.salesOrderNo" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="支付金额" prop="amount">
              <el-input disabled v-model="salesOrderTotalAmount" placeholder="请输入支付金额"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="付款方式" prop="paymentMethodCode">
              <el-select v-model="page.dataEntity.value.paymentMethodCode" placeholder="请选择付款方式" filterable>
                <el-option
                    v-for="item in paymentMethodList"
                    :key="item.code"
                    :label="item.name"
                    :value="item.code"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="订单类型" prop="salesOrderTypeCode">
              <el-select v-model="page.dataEntity.value.salesOrderTypeCode" placeholder="请选择订单类型" filterable>
                <el-option
                    v-for="item in salesOrderTypeList"
                    :key="item.code"
                    :label="item.name"
                    :value="item.code"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="物流状态" prop="salesOrderStatusCode">
              <el-select v-model="page.dataEntity.value.salesOrderStatusCode" class="m-2" placeholder="请选择物流状态" filterable>
                <el-option
                    v-for="item in salesOrderStatusList"
                    :key="item.code"
                    :label="item.name"
                    :value="item.code"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="操作人" prop="companyUserName">
              <el-input v-model="page.dataEntity.value.companyUserName" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="付款时间" prop="payTimeText">
              <el-date-picker
                  v-model="page.dataEntity.value.payTimeText"
                  type="datetime"
                  placeholder="付款时间"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="收货人" prop="receiverName">
              <el-input v-model="page.dataEntity.value.receiverName" placeholder="请输入收货人"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="收货电话" prop="receiverPhone">
              <el-input v-model="page.dataEntity.value.receiverPhone" placeholder="请输入收货电话"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="收货地址" prop="receiverAddress">
              <el-input v-model="page.dataEntity.value.receiverAddress" rows="2" type="textarea"
                        placeholder="请输入收货地址"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="物流信息" prop="logisticsInfo">
              <el-input v-model="page.dataEntity.value.logisticsInfo" rows="2" type="textarea"
                        placeholder="请输入物流信息"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-card class="box-card" shadow="never" style="margin-bottom: 15px">
        <template #header>
          <div class="card-header">
            <span>子订单列表</span>
            <el-button class="button" text></el-button>
          </div>
        </template>
        <el-table ref="multipleTableRef" :data="salesOrderItemList" style="width: 100%">
          <el-table-column property="productName" sortable label="商品名称"/>
          <el-table-column property="price" sortable label="单价(元)"/>
          <el-table-column property="quantity" sortable label="数量">
            <template #default="scope">
              <el-input-number v-model.number="scope.row.quantity" :min="10" :max="1000"
                               :step="scope.row.productId===1?10:1"
                               :step-strictly="true"
                               :disabled="!(page.mode === 'add')"
                               @change="productQuantityChange(scope.row.productId,scope.row.quantity)"/>
            </template>
          </el-table-column>
          <el-table-column property="amount" sortable label="金额（元）"/>
          <el-table-column property="description" sortable label="描述"/>
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

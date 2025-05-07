<script setup>
import { reactive, ref } from 'vue'
import detail from '@/js/detail'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import config from '@/js/config'
import SelectedProduct from './SelectedProduct.vue'

const refSelectedProduct = ref()

//定义事件发射器
const emit = defineEmits(['reload-data'])
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref()

//子订单列表
const salesOrderItemList = ref([])

function getSalesOrderItemList(salesOrderId) {
  let data = {
    salesOrderId: salesOrderId,
    orderBy: 'update_time desc'
  }
  common.call('GetViewSalesOrderItemList', data, (res) => {
    salesOrderItemList.value = res.data.dataList
  })
}

let page = reactive({})

function floatValidator(rule, value, callback) {
  if (!/^-?\d+(\.\d+)?$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入数字'))
  } else {
    // 校验通过
    callback()
  }
}

//表单校验规则
const rules = reactive({
  amount: [
    { required: true, message: '请输入支付金额', trigger: 'blur' },
    { validator: floatValidator, message: '商户编号长度为3~12位数字', trigger: 'blur' }
  ],
  paymentMethodCode: [{ required: true, message: '请选择付款方式', trigger: 'change' }],
  salesOrderStatusCode: [{ required: true, message: '请选择物流状态', trigger: 'change' }],
  companyUserId: [{ required: true, message: '请选择操作人', trigger: 'change' }],
  payTimeText: [{ required: true, message: '请选择付款时间', trigger: 'change' }],
  receiverName: [{ required: true, message: '请输入收货人', trigger: 'blur' }],
  receiverPhone: [
    { required: true, message: '请输入收货电话', trigger: 'blur' },
    { pattern: /^1[3456789]\d{9}$/, message: '手机号格式有误', trigger: 'blur' }
  ],
  receiverAddress: [{ required: true, message: '请输入收货地址', trigger: 'blur' }]
})

//添加一个书箱子订单
function addBookcaseForOrder() {
  common.call('GetProduct', { id: 1 }, (res) => {
    let bookcaseOrderItem = {
      productId: res.data.id,
      productName: res.data.name,
      price: res.data.price,
      quantity: 10,
      description: res.data.description,
      amount: parseFloat((res.data.price * 10).toFixed(2))
    }
    salesOrderItemList.value.splice(0, 1, bookcaseOrderItem)
  })
}

//添加一个锁芯子订单
function addCylinderForOrder() {
  common.call('GetProduct', { id: 2 }, (res) => {
    let cylinderOrderItem = {
      productId: res.data.id,
      productName: res.data.name,
      price: res.data.price,
      quantity: 1,
      description: res.data.description,
      amount: parseFloat((res.data.price * 10).toFixed(2))
    }
    salesOrderItemList.value.splice(1, 1, cylinderOrderItem)
  })
}

function getSalesOrderNo() {
  let data = {
    tableName: 'sales_order',
    filedName: 'sales_order_no',
    length: 8
  }
  common.call('CreateRandomNo', data, (res) => {
    page.dataEntity.value.salesOrderNo = res.data
    page.dataEntity.value.companyUserId = parseInt(localStorage.getItem('companyUserId'))
    /* page.dataEntity.value.payTimeText = new Date().format('yyyy-MM-dd hh:mm:ss')
    page.dataEntity.value.payTime = new Date().format('yyyy-MM-dd hh:mm:ss') */
    //写死订单类型为“书箱锁芯”
    page.dataEntity.value.salesOrderTypeCode = 'Bookbox'
    //写死订单状态为“待发货”
    page.dataEntity.value.salesOrderStatusCode = 'Paying'
    page.dataEntity.value.paymentMethodCode = 'WXScan'
  })
}

const selectInfo = reactive({})
const bindSelectOptions = [
  { tableName: 'payment_method', orderBy: 'display_order' },
  { tableName: 'sales_order_status', orderBy: 'display_order' },
  {
    tableName: 'company_user',
    orderBy: 'create_time desc',
    condition: `company_id=${localStorage.getItem('companyId')}`
  }
]

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
      salesOrderItemList.value.splice(0)
    },
    getDataEntity(dataEntityRef) {
      dataEntityRef.value.salesOrderItemList = salesOrderItemList.value
    }
  })
  if (options.mode === 'add') {
    //addBookcaseForOrder()
    getSalesOrderNo()
  }

  if (options.mode === 'view' || options.mode === 'edit') {
    getSalesOrderItemList(options.primaryKeyValue)
  }
  detailFormVisible.value = true
  //绑定选择器
  common.bindSelectsInfo(bindSelectOptions, selectInfo)
}
// 单据时间
const payTimeText = computed({
  get() {
    return page.dataEntity.value.payTimeText
  },
  set(val) {
    page.dataEntity.value.payTimeText = val
    page.dataEntity.value.payTime = val
  }
})
//暴露
defineExpose({
  init
})

/* function productQuantityChange(productId, quantity) {
  if (productId === 1) {
    salesOrderItemList.value[0].amount = parseFloat(
      (quantity * salesOrderItemList.value[0].price).toFixed(2)
    )
  } else if (productId === 2) {
    salesOrderItemList.value[1].amount = parseFloat(
      (quantity * salesOrderItemList.value[1].price).toFixed(2)
    )
  }
} */
function productQuantityChange(productId, quantity) {
  const item = salesOrderItemList.value.find(orderItem => orderItem.productId === productId);
  if (item) {
    item.amount = parseFloat((quantity * item.price).toFixed(2));
  }
}

const salesOrderTotalAmount = computed(() => {
  let totalAmount = salesOrderItemList.value.reduce((totalAmount, lastItem) => {
    return totalAmount + lastItem.amount || 0
  }, 0)
  page.dataEntity.value.amount = totalAmount
  return totalAmount
})


/* const getPayQrCode = inject('getPayQrCode');
function submitSuccess() {
  detailFormVisible.value = false
  emit('reload-data')
  setTimeout(() => {
    page.isLoading.value = false
  }, 300)
  if (page.mode === 'add') {
    let data = {
      salesOrderNo: page.dataEntity.value.salesOrderNo,
      amount: page.dataEntity.value.amount
    }
    getPayQrCode(data)
  }
} */
function addProduct(productList) {
  productList.forEach((item) => {
    // 判断 item.id 是否在 salesOrderItemList 中已存在
    if (salesOrderItemList.value.some(orderItem => orderItem.productId === item.id)) {
      return; // 如果存在则跳出当前循环，执行下次循环
    }
    let cylinderOrderItem = {
      productId: item.id,
      productName: item.name,
      price: item.price,
      quantity: 1,
      description: item.description,
      amount: parseFloat((item.price * 10).toFixed(2))
    }
    salesOrderItemList.value.push(cylinderOrderItem);
  })
}
</script>
<template>
  <div>
    <SelectedProduct ref="refSelectedProduct" @reload-data="addProduct" />
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''" width="1200" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode === 'view'" label-width="100px">
        <el-row>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="订单编号" prop="salesOrderNo">
              <el-input v-model="page.dataEntity.value.salesOrderNo" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="支付金额" prop="amount">
              <el-input v-model="salesOrderTotalAmount" placeholder="请输入支付金额" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="付款方式" prop="paymentMethodCode">
              <el-select v-model="page.dataEntity.value.paymentMethodCode" placeholder="请选择付款方式" disabled filterable
                clearable>
                <el-option v-for="item in selectInfo.paymentMethodList" :key="item.code" :label="item.name"
                  :value="item.code" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="物流状态" prop="salesOrderStatusCode">
              <el-select v-model="page.dataEntity.value.salesOrderStatusCode" class="m-2" placeholder="请选择物流状态" disabled
                filterable clearable>
                <el-option v-for="item in selectInfo.salesOrderStatusList" :key="item.code" :label="item.name"
                  :value="item.code" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-form-item label="操作人" prop="companyUserId">
              <el-select v-model="page.dataEntity.value.companyUserId" class="m-2" placeholder="请选择操作人" filterable
                clearable>
                <el-option v-for="item in selectInfo.companyUserList" :key="item.id" :label="item.name"
                  :value="item.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6" v-if="page.mode === 'edit'">
            <el-form-item label="付款时间" prop="payTimeText">
              <el-date-picker v-model="payTimeText" type="datetime" placeholder="付款时间" disabled/>
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
              <el-input v-model="page.dataEntity.value.logisticsInfo" rows="2" type="textarea" disabled
                        placeholder="请输入物流信息"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-card class="box-card" shadow="never" style="margin-bottom: 15px">
        <template #header>
          <div class="card-header">
            <span>子订单列表</span>
            <el-button type="primary" @click="refSelectedProduct.init(salesOrderItemList)"
              v-if="page.mode === 'add'">添加商品
            </el-button>
            <!-- <el-button type="primary" @click="addCylinderForOrder"
              v-if="salesOrderItemList.length !== 2 && page.mode === 'add'">添加锁芯
            </el-button> -->
          </div>
        </template>
        <el-table ref="multipleTableRef" :data="salesOrderItemList" style="width: 100%">
          <el-table-column property="productName" sortable label="商品名称" />
          <el-table-column property="price" sortable label="单价(元)" />
          <el-table-column property="quantity" sortable label="数量">
            <template #default="scope">
              <el-input-number v-model.number="scope.row.quantity" :min="10" :max="1000"
                :step="scope.row.productId === 1 ? 10 : 1" :step-strictly="true" :disabled="!(page.mode === 'add')"
                @change="productQuantityChange(scope.row.productId, scope.row.quantity)" />
            </template>
          </el-table-column>
          <el-table-column property="amount" sortable label="金额(元)" />
          <el-table-column property="description" label="描述" />
          <el-table-column align="center" width="60">
            <template #default="scope">
              <el-icon size="14" color="#a8abb2" v-if="page.mode === 'add'"
                @click="salesOrderItemList.splice(scope.$index, 1)">
                <Delete />
              </el-icon>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" v-if="page.mode === 'add' || page.mode === 'edit'" :loading="page.isLoading.value"
            @click="page.submitForm()">
            确定
          </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.card-header {
  display: flex;
  align-content: center;
  justify-content: space-between;
}

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

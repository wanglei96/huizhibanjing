<script setup>
import { reactive, ref } from 'vue'
import detail from '@/js/detail'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import BookOrderAddBook from '../bookOrder/BookOrderAddBook.vue'
import SelectedMember from '../activity/clubActivity/SelectedMember.vue'
import SelectedPayOrder from './SelectedPayOrder.vue'

const refSelectedMember = ref()
const refBookOrderAddBook = ref()
const refSelectedPayOrder = ref()

//定义事件发射器
const emit = defineEmits(['reload-data'])
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref()
// 当前登陆人ID
const companyUserId = ref(
  `${common.getLocalStorage('companyUserId')}`
);
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
function floatValidator(rule, value, callback) {
  if (value) {
    if (
      page?.dataEntity?.value?.refundTypeCode === 'Member' &&
      (page?.dataEntity?.value?.memberFee ||
        page?.dataEntity?.value?.bookcaseFee ||
        page?.dataEntity?.value?.depositFee)
    ) {
      rules.memberFee.splice(1, 1)
      rules.bookcaseFee.splice(1, 1)
      rules.depositFee.splice(1, 1)
    }
    // rules.depositFee.splice(1, 1);
  }
  if (value && !/^(?=.+)(?:[1-9]\d*|0)?(?:\.\d+)?$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正数'))
  } else {
    // 校验通过
    callback()
  }
}

//表单校验规则
const rules = reactive({
  damageFee: [
    { required: true, message: '请输入折损费', trigger: 'blur' },
    { validator: floatValidator, trigger: 'blur' }
  ],
  memberFee: [{ validator: floatValidator, trigger: 'blur' }],
  bookcaseFee: [{ validator: floatValidator, trigger: 'blur' }],
  depositFee: [{ validator: floatValidator, trigger: 'blur' }],
  memberId: [{ required: true, message: '请选择会员', trigger: 'change' }],
  operatorId: [{ required: true, message: '请选择操作人', trigger: 'change' }],
  refunderId: [{ required: true, message: '请选择操作人', trigger: 'change' }],
  payOrderId: [{ required: true, message: '请选择订单', trigger: 'change' }]
})

function onRefundTypeClick(type) {
  page.dataEntity.value.refundTypeCode = type
}

const selectInfo = reactive({})
const bindSelectOptions = [
  { tableName: 'refund_type', orderBy: 'display_order' },
  { tableName: 'view_pay_order', orderBy: 'create_time desc', condition: 'is_refund is not true and company_id=' + companyId.value }
]

let page = reactive({})

const init = (options) => {
  detailFormVisible.value = true
  page = detail({
    baseTitle: '退款单',
    tableName: 'refund_order',
    getServiceName: 'GetViewRefundOrder',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit,
    submitFail() {
      /*if (!page.dataEntity.value.memberFee && !page.dataEntity.value.bookcaseFee && !page.dataEntity.value.depositFee) {
        rules.depositFee.push({required: true, message: '请至少填写一个退费项目', trigger: 'blur'});
      }*/
    },
    getDataEntity() {
      page.dataEntity.value.refundAmount = 0
      if (page.dataEntity.value.refundTypeCode === 'Member') {
        if (page.dataEntity.value.memberFee) {
          page.dataEntity.value.refundAmount += parseFloat(page.dataEntity.value.memberFee)
        }
        if (page.dataEntity.value.bookcaseFee) {
          page.dataEntity.value.refundAmount += parseFloat(page.dataEntity.value.bookcaseFee)
        }
        if (page.dataEntity.value.depositFee) {
          page.dataEntity.value.refundAmount += parseFloat(page.dataEntity.value.depositFee)
        }
      } else if (page.dataEntity.value.refundTypeCode === 'Damage') {
        if (page.dataEntity.value.damageFee) {
          page.dataEntity.value.refundAmount += parseFloat(page.dataEntity.value.damageFee)
        }
      }
      page.dataEntity.value.refundAmount = parseFloat(page.dataEntity.value.refundAmount.toFixed(2))
    }
  })
  //绑定选择器
  common.bindSelectsInfo(bindSelectOptions, selectInfo)
  if (options.mode === 'add') {
    page.dataEntity.value.refundTypeCode = 'Member'
    page.dataEntity.value.operatorId = localStorage.getItem('companyId') + ''
  }

}
/* getPayOrderList();
const payOrderList = ref([]);
function getPayOrderList(){
  let params = {
    condition:'is_refund is not true and company_id=' + companyId.value
  }
  common.call("GetViewPayOrderList",params,function(res){
    payOrderList.value = res.data.dataList
  })
} */

//暴露
defineExpose({
  init
})

function submitForm() {
  debugger
  if (!page.dataEntity.value.payOrderId) {
    ElMessage({ message: "请先选择账单！", type: 'warning' })
    return;
  }
  if (
    page.dataEntity.value.refundTypeCode === 'Member' &&
    !page.dataEntity.value?.memberFee &&
    !page.dataEntity.value?.bookcaseFee &&
    !page.dataEntity.value?.depositFee
  ) {
    rules.memberFee.push({ required: true, message: '请至少填写一个退费项目', trigger: 'blur' })
    rules.bookcaseFee.push({ required: true, message: '请至少填写一个退费项目', trigger: 'blur' })
    rules.depositFee.push({ required: true, message: '请至少填写一个退费项目', trigger: 'blur' })
  }

  page.submitForm()
}

/*watch(() => {
  debugger
  return page?.dataEntity?.value?.refundTypeCode;
}, () => {
  debugger
  if (page?.dataEntity?.value?.refundTypeCode === 'Member' && (page?.vdataEntity?.value?.memberFee || page?.dataEntity?.value?.bookcaseFee || page?.dataEntity?.value?.depositFee)) {
    rules.memberFee.splice(1, 1);
    rules.bookcaseFee.splice(1, 1);
    rules.depositFee.splice(1, 1);
  }
});*/


function changePayOrder(row) {
  const payOrderInfo = selectInfo.viewPayOrderList.find(item => item.id === row);
  if (payOrderInfo.payment_item_code === 'MembershipFee') {
    page.dataEntity.value.memberFee = payOrderInfo.amount
  } else if (payOrderInfo.payment_item_code === 'BookcaseFee') {
    page.dataEntity.value.bookcaseFee = payOrderInfo.amount
  } else if (payOrderInfo.payment_item_code === 'DepositFee') {
    page.dataEntity.value.depositFee = payOrderInfo.amount
  } else if (payOrderInfo.payment_item_code === 'BookDamageCompensate') {
    page.dataEntity.value.damageFee = payOrderInfo.amount
    page.dataEntity.value.bookId = payOrderInfo.book_id
    page.dataEntity.value.bookName = payOrderInfo.book_name
    page.dataEntity.value.bookSn = payOrderInfo.book_sn

  } else {
    page.dataEntity.value.memberFee = ''
    page.dataEntity.value.bookcaseFee = ''
    page.dataEntity.value.depositFee = ''
    page.dataEntity.value.damageFee = ''
    page.dataEntity.value.bookId = ''
  }
  page.dataEntity.value.memberId = payOrderInfo.member_id
  page.dataEntity.value.memberName = payOrderInfo.member_name
}
function addBook(item) {
  if (item.type === 'book') {
    page.dataEntity.value.bookId = item.data.id
    page.dataEntity.value.bookName = item.data.bookName
    page.dataEntity.value.bookSn = item.data.sn
  } else {
    page.dataEntity.value.memberId = item.data.id
    page.dataEntity.value.memberName = item.data.name
    page.dataEntity.value.mobilePhone = item.data.mobilePhone
  }

}

function payOrderData(item) {
  let payOrderInfo = item.data;
  page.dataEntity.value.bookId = payOrderInfo.bookId
  page.dataEntity.value.bookName = payOrderInfo.bookName
  page.dataEntity.value.bookSn = payOrderInfo.bookSn
  page.dataEntity.value.memberId = payOrderInfo.memberId
  page.dataEntity.value.memberName = payOrderInfo.memberName
  page.dataEntity.value.memberCardNo = payOrderInfo.memberCardNo
  page.dataEntity.value.payOrderId = payOrderInfo.id
  page.dataEntity.value.payOrderNo = payOrderInfo.payOrderNo
  if (payOrderInfo.paymentItemCode === 'MembershipFee') {
    page.dataEntity.value.memberFee = payOrderInfo.amount
  } else if (payOrderInfo.paymentItemCode === 'BookcaseFee') {
    page.dataEntity.value.bookcaseFee = payOrderInfo.amount
  } else if (payOrderInfo.paymentItemCode === 'DepositFee') {
    page.dataEntity.value.depositFee = payOrderInfo.amount
  } else if (payOrderInfo.paymentItemCode === 'BookDamageCompensate') {
    page.dataEntity.value.damageFee = payOrderInfo.amount
  }
}
const closePayOrder = () => {
  page.dataEntity.value.bookId = ''
  page.dataEntity.value.bookName = ''
  page.dataEntity.value.bookSn = ''
  page.dataEntity.value.memberId = ''
  page.dataEntity.value.memberName = ''
  page.dataEntity.value.memberCardNo = ''
  page.dataEntity.value.payOrderId = ''
  page.dataEntity.value.payOrderNo = ''
  page.dataEntity.value.memberFee = ''
  page.dataEntity.value.bookcaseFee = ''
  page.dataEntity.value.depositFee = ''
  page.dataEntity.value.damageFee = ''
}

</script>
<template>
  <div>
    <BookOrderAddBook ref="refBookOrderAddBook" @reload-data="addBook" />
    <SelectedMember ref="refSelectedMember" @reload-data="addBook" />
    <SelectedPayOrder ref="refSelectedPayOrder" @reload-data="payOrderData" />
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''" width="800" draggable overflow>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode === 'view'" label-width="100px">
        <el-row :gutter="0" style="margin-bottom: 10px">
          <el-col :xs="3" :sm="3" :md="3" :lg="3" v-for="refundType in selectInfo.refundTypeList" :key="refundType.code"
            v-show="((page.mode === 'view' || page.mode === 'edit') &&
      page.dataEntity.value.refundTypeCode === refundType.code) ||
      page.mode === 'add'
      ">
            <el-tag class="on-el-tag"
              :type="page.dataEntity.value.refundTypeCode === refundType.code ? 'success' : 'info'" size="large"
              @click="onRefundTypeClick(refundType.code)">{{ refundType.name }}
            </el-tag>
          </el-col>
        </el-row>
        <el-card class="box-card" shadow="never">
          <div>
            <el-button type="primary"
              @click="refSelectedPayOrder.init({ mode: 'Radio', data: page.dataEntity.value })">选择账单</el-button>
            <el-alert style="margin-top: 10px;" :title="`账单编号： ${page.dataEntity.value.payOrderNo} `"
              v-show="page.dataEntity.value.payOrderNo" type="success" show-icon @close="closePayOrder"
              :closable="page.mode !== 'view'" />
          </div>

          <!-- <div v-if="page.dataEntity.value.refundTypeCode === 'Member'">
            <el-button type="primary" @click="refSelectedMember.init({ mode: 'Radio' })">选择会员</el-button>
            <div>{{ page.dataEntity.value.memberName }}{{ page.dataEntity.value.mobilePhone }}</div>
          </div>

          <div v-if="page.dataEntity.value.refundTypeCode === 'Damage'">
            <el-button type="primary" @click="refBookOrderAddBook.init({ mode: 'Radio' })">选择图书</el-button>
            <div>{{ page.dataEntity.value.bookName }}{{ page.dataEntity.value.bookSn }}</div>
          </div> -->
        </el-card>

        <el-card class="box-card" shadow="never" v-if="page.dataEntity.value.refundTypeCode === 'Member'">
          <template #header>
            <div class="card-header">
              <span>会员信息</span>
            </div>
          </template>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="会员名称" prop="memberName">
                <el-input v-model="page.dataEntity.value.memberName" class="item-input" disabled></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="会员卡号" prop="memberCardNo">
                <el-input v-model="page.dataEntity.value.memberCardNo" class="item-input" disabled></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>

        <el-card class="box-card" shadow="never" v-if="page.dataEntity.value.refundTypeCode === 'Damage'">
          <template #header>
            <div class="card-header">
              <span>图书信息</span><!--折损退费-->
            </div>
          </template>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="书号" prop="bookSn">
                <el-input v-model.number="page.dataEntity.value.bookSn" disabled class="item-input"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="书名" prop="bookName">
                <el-input v-model.number="page.dataEntity.value.bookName" disabled class="item-input"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>

        <el-card class="box-card" shadow="never" v-if="page.dataEntity.value.refundTypeCode === 'Member'">
          <template #header>
            <div class="card-header">
              <span>退费项目</span><!--会员退费-->
              <span class="input-description" style="margin-left: 10px">退费项目如实填写，可以同时填写多项</span>
            </div>
          </template>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="会员费" prop="memberFee">
                <el-input v-model.number="page.dataEntity.value.memberFee" placeholder="请输入退还的会员费总计"
                  class="item-input"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="书箱费" prop="bookcaseFee">
                <el-input v-model.number="page.dataEntity.value.bookcaseFee" placeholder="请输入退还的书箱费总计"
                  class="item-input"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="押金" prop="depositFee">
                <el-input v-model.number="page.dataEntity.value.depositFee" placeholder="请输入退还的押金费用总计"
                  class="item-input"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="page.mode === 'add'">
            <el-col :xs="12" :sm="12" :md="12" :lg="12">
              <el-form-item label="是否注销会员" label-width="auto" prop="isCancelMember">
                <el-switch v-model="page.dataEntity.value.isCancelMember" />
              </el-form-item>
            </el-col>
            <el-col :xs="12" :sm="12" :md="12" :lg="12">
              <el-form-item label="是否注销书箱" label-width="auto" prop="isCancelBookbox">
                <el-switch v-model="page.dataEntity.value.isCancelBookbox" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
        <el-card class="box-card" shadow="never" v-if="page.dataEntity.value.refundTypeCode === 'Damage'">
          <template #header>
            <div class="card-header">
              <span>退费项目</span><!--折损退费-->
            </div>
          </template>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <span class="input-description">退费项目如实填写，可以同时填写多项</span>
              <el-form-item label="折损费" prop="damageFee">
                <el-input v-model.number="page.dataEntity.value.damageFee" placeholder="请输入退还的折损赔付费用总计"
                  class="item-input"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="page.mode === 'add'">
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <span class="input-description">恢复折损的书籍</span>
              <el-form-item label="恢复赔付书籍" label-width="auto" prop="isDamageBook">
                <el-switch v-model="page.dataEntity.value.isDamageBook" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
        <el-card class="box-card" shadow="never">
          <template #header>
            <div class="card-header">
              <span>退费信息</span>
            </div>
          </template>
          <!-- <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <span class="input-description">从账单列表获取</span>
              <el-form-item label="订单号" prop="payOrderId">
                <el-select v-model="page.dataEntity.value.payOrderId" class="m-2" placeholder="请选择订单号" clearable
                  filterable @change="changePayOrder">
                  <el-option v-for="item in selectInfo.viewPayOrderList" :key="item.id" :label="item.pay_order_no"
                    :value="item.id" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row> -->
          <!-- <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="退款人" prop="memberId">
                <DatabaseSelect table-name="member" :condition="'company_id=' + companyId"
                  v-model="page.dataEntity.value.memberId" valueFieldName="id" placeholder="请选择"
                  class="form-item-width" />
              </el-form-item>
            </el-col>
          </el-row> -->
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="退款人" prop="refunderId">
                <database-select placeholder="请选择" table-name="company_user" :condition="'company_id=' + companyId"
                  v-model="page.dataEntity.value.refunderId" :defaultValue="companyUserId" valueFieldName="id"
                  class="form-item-width"></database-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="操作人" prop="operatorId">
                <database-select placeholder="请选择" table-name="company_user" :condition="'company_id=' + companyId"
                  v-model="page.dataEntity.value.operatorId" :defaultValue="companyUserId" valueFieldName="id"
                  class="form-item-width"></database-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="24" :lg="24">
              <el-form-item label="描述" prop="remark">
                <el-input v-model="page.dataEntity.value.remark" type="textarea" rows="2" placeholder="请输入描述"
                  style="width: calc(100% - 20px)"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>

      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" v-if="page.mode === 'add' || page.mode === 'edit'" :loading="page.isLoading.value"
            @click="submitForm">
            确定
          </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.input-description {
  color: #f56c6c;
  margin-left: 100px;
  font-size: 12px;
}

.item-input {
  width: 100% !important;
}

.on-el-tag {
  cursor: pointer;
}

.box-card {
  margin-bottom: 10px;
}

.card-header {
  display: flex;
  align-items: center;
  height: 0px;
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
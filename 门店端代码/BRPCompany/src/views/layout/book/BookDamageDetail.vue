<script setup>
import { reactive, ref, watch } from 'vue';
import detail from '@/js/detail';
import common from '@/js/common';
import config from '@/js/config'
import DatabaseSelect from "@/components/extension/DatabaseSelect.vue";

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

const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
const bookInfo = ref({});

const inputMemberCardNo = ref('');


const init = (options) => {
  page = detail({
    baseTitle: '图书损坏记录',
    tableName: 'book_damage',
    getServiceName: 'GetViewBookDamage',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  });
  if (options.bookInfo) {
    options.bookInfo['bookSn'] = options.bookInfo['sn'];
    options.bookInfo['bookId'] = options.bookInfo['id'];
    bookInfo.value = options.bookInfo;
  }
  if (options.dataItem) {
    bookInfo.value = options.dataItem;
  }
  detailFormVisible.value = true;
}
//暴露
defineExpose({
  init
})

function checkMemberCardNo() {
  if (!bookInfo.value.memberCardNo) {
    ElMessage({ message: "请先输入会员卡号", type: 'warning' })
    return;
  }
  let params = {
    "tableName": "view_member_card",
    "condition": "card_no='" + bookInfo.value.memberCardNo + "' and company_id=" + companyId.value + " and disabled IS NOT TRUE",
    "pageSize": 1
  }
  common.call("GetTable", params, function (result) {
    if (result.data.length > 0) {
      bookInfo.value.memberName = result.data[0].member_name;
      ElMessage({ message: "校验成功", type: 'success' })
    } else {
      ElMessage({ message: "校验失败，会员卡号不存在！", type: 'warning' })
      return;
    }
  })
}

//表单校验规则
const rules = reactive({
  bookDamageTypeCode: [
    { required: true, message: '请选择类型', trigger: 'change' }
  ],
})

const isCompensationDisable = ref(true);

const changeIsCompensation = (value) => {
  if (value) {
    rules.amount = [
      { required: true, message: '赔付金额', trigger: 'blur' },
      { validator: floatValidator, trigger: 'blur' }
    ];
    rules.paymentMethodCode = [
      { required: true, message: '请选择赔付方式', trigger: 'change' },
    ]
    isCompensationDisable.value = false;
  } else {
    isCompensationDisable.value = true;
    rules.amount = []
    rules.paymentMethodCode = []
    refDataEntityForm.value.clearValidate('paymentMethodCode');
    refDataEntityForm.value.clearValidate('amount');
  }
}

function floatValidator(rule, value, callback) {
  if (value && !/^(?=.+)(?:[1-9]\d*|0)?(?:\.\d+)?$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正确金额'))
  } else {
    // 校验通过
    callback();
  }
}


//提交表单
function confirm() {
  if (bookInfo.value.memberCardNo) {
    if (!bookInfo.value.memberName) {
      ElMessage({ message: '会员校验失败', type: 'warning' });
      return;
    }
  }
  let options = {
    getDataEntity
  }
  refDataEntityForm.value.validate().then(() => {
    page.isLoading.value = true;
    page.save(options);
    //emit('reload-data');
  }).catch((res) => {
    console.log(res);
    ElMessage({ message: '表单格式有误', type: 'warning' });
  })
}
function getDataEntity(data) {
  data.value.bookId = bookInfo.value.bookId;
  data.value.memberCardNo = bookInfo.value.memberCardNo;
  return data;
}

//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    bookInfo.value = {}

  }
})

const damageTimeText = computed({
  get() {
    return page.dataEntity.value.damageTimeText
  },
  set(val) {
    page.dataEntity.value.damageTimeText = val
    page.dataEntity.value.damageTime = val
  }
})

const paymentTimeText = computed({
  get() {
    return page.dataEntity.value.paymentTimeText
  },
  set(val) {
    page.dataEntity.value.paymentTimeText = val
    page.dataEntity.value.paymentTime = val
  }
})



</script>
<template>
  <div>
    <el-dialog v-model="detailFormVisible" :title="page.title ? page.title.value : ''" width="800" draggable
      :close-on-click-modal="false">
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode == 'view'" label-width="80px">
        <div style="margin: 0 0 10px 10px;" v-if="page.mode === 'add'">
          <span style="color: #008000;">{{ bookInfo.bookSeriesName ?
      "[" + bookInfo.bookSeriesName + "]" : "" }}</span>
          <span>{{ bookInfo.bookName }}</span>
          <span style="color: #0000ff;">{{ bookInfo.bindingTypeName ?
      "(" + bookInfo.bindingTypeName + ")" : "" }}</span>
        </div>
        <el-row v-if="page.mode === 'add'">
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="图书编码" prop="bookSn">
              <el-input v-model="bookInfo.bookSn" disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-if="page.mode === 'edit'">
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="图书编码" prop="bookSn">
              <el-input v-model="page.dataEntity.value.bookSn" disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-if="page.mode === 'add'">
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="会员卡号" prop="memberCardNo">
              <el-input v-model="bookInfo.memberCardNo"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="会员名称" prop="memberName">
              <el-input v-model="bookInfo.memberName" disabled="true"></el-input>
              <el-link type="danger" @click="checkMemberCardNo">校验会员卡</el-link>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-if="page.mode === 'edit'">
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="会员卡号" prop="memberCardNo">
              <el-input v-model="page.dataEntity.value.memberCardNo" disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="类型" prop="bookDamageTypeCode">
              <DatabaseSelect v-model="page.dataEntity.value.bookDamageTypeCode" table-name="book_damage_type" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="办理人" prop="companyUserId">
              <database-select placeholder="请选择办理人" table-name="company_user" :condition="'company_id=' + companyId"
                v-model="page.dataEntity.value.companyUserId" :defaultValue="companyUserId" valueFieldName="id"
                class="form-item-width"></database-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="8" :sm="8" :md="8" :lg="8">
            <el-form-item label="是否提醒" prop="isRemind">
              <el-switch v-model="page.dataEntity.value.isRemind" />
            </el-form-item>
          </el-col>
          <el-col :xs="8" :sm="8" :md="8" :lg="8">
            <el-form-item label="是否下架" prop="isOff">
              <el-switch v-model="page.dataEntity.value.isOff" />
            </el-form-item>
          </el-col>
          <el-col :xs="8" :sm="8" :md="8" :lg="8">
            <el-form-item label="是否赔付" prop="isCompensation">
              <el-switch v-model="page.dataEntity.value.isCompensation" @change="changeIsCompensation" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="损坏时间" prop="damageTimeText">
              <el-date-picker v-model="damageTimeText" type="datetime" value-format="YYYY-MM-DD HH:mm:ss"
                placeholder="请选择损坏时间" style="width: 200px" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12" v-if="page.mode == 'view'">
            <el-form-item label="赔付时间" prop="paymentTimeText">
              <el-date-picker v-model="paymentTimeText" type="datetime" value-format="YYYY-MM-DD HH:mm:ss"
                placeholder="请选择赔付时间" style="width: 200px" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="支付方式" prop="paymentMethodCode">
              <DatabaseSelect v-model="page.dataEntity.value.paymentMethodCode" table-name="payment_method"
                :disabled="isCompensationDisable" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="赔付金额" prop="amount">
              <el-input v-model="page.dataEntity.value.amount" :disabled="isCompensationDisable"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="赔付原因" prop="reason">
              <el-input v-model="page.dataEntity.value.reason" type="textarea" placeholder="请输入原因"
                :disabled="isCompensationDisable"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="page.dataEntity.value.remark" type="textarea" placeholder="请输入备注"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" v-if="page.mode === 'add' || page.mode === 'edit'" :loading="page.isLoading.value"
            @click="confirm()"> 确定 </el-button>
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
  width: 200px;
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
<script setup>
import { inject, reactive, ref } from 'vue'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'

const dialogFormVisible = ref(false)
const msg = ref("图书赔付")
const formRef = ref();

const dataItem = ref({});

const paymentMethodList = ref([]);
const companyUserList = ref([]);
//会员图书标记记录
const inputMemberCardNo = ref('');

// 当前登陆人ID
const companyUserId = ref(
  `${common.getLocalStorage('companyUserId')}`
);
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

const reLoadData = inject('reLoadData');
function init(options) {
  // 显示弹窗
  dialogFormVisible.value = true
  //支付方式
  getSelected({
    tableName: 'payment_method',
    selectedList: paymentMethodList,
  });
  //处理人
  getSelected({
    tableName: 'company_user',
    selectedList: companyUserList,
    condition: 'company_id=' + companyId.value
  });
  // 表单渲染
  if (options.dataItem) {
    dataItem.value = options.dataItem;
  }
  if (options.bookInfo) {
    options.bookInfo['bookId'] = options.bookInfo.id;
    options.bookInfo['bookSn'] = options.bookInfo.sn;
    dataItem.value = options.bookInfo;
  }
}

//获取下拉框
function getSelected(options) {
  let param = {
    "tableName": options.tableName,
    pageSize: 99999
  };
  if (options.condition) {
    param.condition = options.condition;
  }
  common.call("GetTable", param, function (result) {
    options.selectedList.value = result.data;
  })
}

const submitForm = async (formRef) => {
  if (!formRef) return
  await formRef.validate((valid, fields) => {
    if (valid) {
      let data = {
        bookId: dataItem.value.bookId,
        amount: dataItem.value.amount,
        paymentMethodCode: dataItem.value.paymentMethodCode,
        memberId: dataItem.value.memberId,
        companyUserId: dataItem.value.companyUserId,
        remark: dataItem.value.remark,
        reason: dataItem.value.reason,
        memberCardNo: dataItem.value.memberCardNo,
        inputMemberCardNo: inputMemberCardNo.value,
        companyId: companyId.value
      }
      common.call('AddBookCompensation', data, function (result) {
        dialogFormVisible.value = false
        ElMessage({ message: "赔付成功", type: 'success' })
        formRef.resetFields()
        reLoadData();
      })
    }
  })
}
const rules = reactive({
  amount: [
    { required: true, message: '赔付金额', trigger: 'blur' },
    { validator: floatValidator, trigger: 'blur' }
  ],
  paymentMethodCode: [
    { required: true, message: '请选择支付方式', trigger: 'change' }
  ],
  companyUserId: [
    { required: true, message: '请选择办理人', trigger: 'change' }
  ],
  memberCardNo: [
    { required: true, message: '请输入会员卡号', trigger: 'change' }
  ],
  memberName: [
    { required: true, message: '会员卡校验失败', trigger: 'change' }
  ],
})

function floatValidator(rule, value, callback) {
  if (value && !/^(?=.+)(?:[1-9]\d*|0)?(?:\.\d+)?$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正确金额'))
  } else {
    // 校验通过
    callback();
  }
}

function checkMemberCardNo() {
  if (!dataItem.value.memberCardNo) {
    ElMessage({ message: "请先输入会员卡号", type: 'warning' })
    return;
  }
  let params = {
    "tableName": "view_member_card",
    "condition": "card_no='" + dataItem.value.memberCardNo + "' and company_id=" + companyId.value + " and disabled IS NOT TRUE",
    "pageSize": 1
  }
  common.call("GetTable", params, function (result) {
    if (result.data.length > 0) {
      dataItem.value.memberName = result.data[0].member_name;
      ElMessage({ message: "校验成功", type: 'success' })
    } else {
      ElMessage({ message: "校验失败，会员卡号不存在！", type: 'warning' })
      return;
    }
  })
}

//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(dialogFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    formRef.value.clearValidate()
    dataItem.value = {}
    reLoadData();
  }
})
defineExpose({
  init
})



</script>

<template>
  <div>
    <el-dialog destroy-on-close v-model="dialogFormVisible" :title="msg" width="800" draggable
      :close-on-click-modal="false">
      <div style="margin:0 0 20px 12px;">
        <span style="color: #008000;">{{ dataItem.bookSeriesName ? "[" + dataItem.bookSeriesName + "]" : ""
        }}</span>
        <span>{{ dataItem.bookName }}</span>
        <span style="color: #0000ff;">{{ dataItem.bindingTypeName ? "(" + dataItem.bindingTypeName + ")" : ""
        }}</span>
      </div>
      <el-form :model="dataItem" ref="formRef" :rules="rules" :label-position="'right'" label-width="80px">
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="图书编码" prop="bookSn">
              <el-input v-model="dataItem.bookSn" disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="会员卡号" prop="memberCardNo">
              <el-input v-model="dataItem.memberCardNo"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="会员名称" prop="memberName">
              <el-input v-model="dataItem.memberName" disabled="true"></el-input>
              <el-link type="danger" @click="checkMemberCardNo">校验会员卡</el-link>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="赔付金额" prop="amount">
              <el-input v-model="dataItem.amount"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="支付方式" prop="paymentMethodCode">
              <el-select class="form-item-width" v-model="dataItem.paymentMethodCode" clearable placeholder="请选择支付方式" filterable>
                <el-option v-for="item in paymentMethodList" :key="item.code" :label="item.name" :value="item.code" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="办理人" prop="companyUserId">
              <database-select placeholder="请选择办理人" table-name="company_user" :condition="'company_id=' + companyId"
                v-model="dataItem.companyUserId" :defaultValue="companyUserId" valueFieldName="id"
                class="form-item-width"></database-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="赔付原因" prop="reason">
              <el-input v-model="dataItem.reason" type="textarea" placeholder="请输入原因"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="dataItem.remark" type="textarea" placeholder="请输入备注"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="submitForm(formRef)"> 确定 </el-button>
          <el-button @click="dialogFormVisible = false">取消</el-button>
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
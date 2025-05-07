<script setup>
import { reactive, ref } from 'vue'
import detail from '@/js/detail'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'

//定义事件发射器
const emit = defineEmits(['reload-data'])
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref()

let page = reactive({})
const init = (options) => {
  detailFormVisible.value = true
  page = detail({
    baseTitle: '活动',
    tableName: 'activity',
    getServiceName: 'GetViewActivity',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  })
}

//暴露
defineExpose({
  init
})

function floatValidator(rule, value, callback) {
  if (!/^-?\d+(\.\d+)?$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正数'))
  } else {
    // 校验通过
    callback()
  }
}

//const isDisable = ref(false)

//表单校验规则
const rules = reactive({
  name: [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
  activityTypeCode: [{ required: true, message: '请选择活动类型', trigger: 'blur' }],
})
const onChange = (item) => {
  if (item.code == 'Renew') {
    //isDisable.value = false
    rules.amount = [
      { required: true, message: '请输入支付金额', trigger: 'blur' },
      { validator: floatValidator, trigger: 'blur' }
    ]
    rules.renewalPrice = [
      { required: true, message: '请输入活动收费', trigger: 'blur' },
      { validator: floatValidator, trigger: 'blur' }
    ]
    rules.buyDays = [
      { required: true, message: '请输入购买天数', trigger: 'blur' },
      { type: 'number', message: '请输入正整数', trigger: 'blur' }
    ]
    rules.giftDays = [
      { required: true, message: '请输入赠送天数', trigger: 'blur' },
      { type: 'number', message: '请输入正整数', trigger: 'blur' }
    ]
    rules.loanableNum = [
      { required: true, message: '请输入可借本数', trigger: 'blur' },
      { type: 'number', message: '请输入正整数', trigger: 'blur' }
    ]
    rules.giftScore = [
      { required: true, message: '请输入新增积分', trigger: 'blur' },
      { type: 'number', message: '请输入正整数', trigger: 'blur' }
    ]
    rules.giftStopCardQuantity = [
      { required: true, message: '请输入新增停卡次数', trigger: 'blur' },
      { type: 'number', message: '请输入正整数', trigger: 'blur' }
    ]
  } else {
    //isDisable.value = true
    rules.amount = []
    rules.renewalPrice = []
    rules.giftDays = []
    rules.loanableNum = []
    rules.buyDays = []
    rules.giftScore = []
    rules.giftStopCardQuantity = []
    refDataEntityForm.value.clearValidate();
  }
}
</script>
<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''" width="60%" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode === 'view'" label-width="120px">
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="活动名称" prop="name">
              <el-input v-model="page.dataEntity.value.name" class="form-item-width" placeholder="请输入活动名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="活动类型" prop="activityTypeCode">
              <DatabaseSelect table-name="activity_type" v-model="page.dataEntity.value.activityTypeCode"
                placeholder="请选择活动类型" :condition="'is_deleted is not true'" class="form-item-width"
                @onChange="onChange" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="收费金额" prop="renewalPrice">
              <el-input v-model="page.dataEntity.value.renewalPrice" class="form-item-width"
                :disabled="page.dataEntity.value.activityTypeCode !== 'Renew'" placeholder="请输入收费金额"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="购买天数" prop="buyDays">
              <el-input v-model.number="page.dataEntity.value.buyDays" class="form-item-width"
                :disabled="page.dataEntity.value.activityTypeCode !== 'Renew'" placeholder="请输入购买天数"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="赠送天数" prop="giftDays">
              <el-input v-model.number="page.dataEntity.value.giftDays" class="form-item-width"
                :disabled="page.dataEntity.value.activityTypeCode !== 'Renew'" placeholder="请输入赠送"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="可借本数" prop="loanableNum">
              <el-input v-model.number="page.dataEntity.value.loanableNum" class="form-item-width"
                :disabled="page.dataEntity.value.activityTypeCode !== 'Renew'" placeholder="请输入可借本书"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="新增积分" prop="giftScore">
              <el-input v-model.number="page.dataEntity.value.giftScore" class="form-item-width"
                :disabled="page.dataEntity.value.activityTypeCode !== 'Renew'" placeholder="请输入新增积分"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="新增停卡次数" prop="giftStopCardQuantity">
              <el-input v-model.number="page.dataEntity.value.giftStopCardQuantity" class="form-item-width"
              :disabled="page.dataEntity.value.activityTypeCode !== 'Renew'" placeholder="请输入新增停卡次数"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
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
<script setup>
import { reactive, ref } from 'vue';
import detail from '@/js/detail';
import DatabaseSelect from "@/components/extension/DatabaseSelect.vue";

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

let page = reactive({});
const init = (options) => {
  page = detail({
    baseTitle: '交易明细',
    tableName: 'retail_order',
    getServiceName: 'GetViewRetailOrder',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  });
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
  name: [
    { required: true, message: '请输入商品名称', trigger: 'blur' }
  ],
  price: [
    { required: true, message: '请输入商品价格', trigger: 'blur' },
    { validator: floatValidator, trigger: 'blur' }
  ]
})

</script>
<template>
  <div>
    <el-dialog v-model="detailFormVisible" :title="page.title ? page.title.value : ''" width="800" draggable
      :close-on-click-modal="false">
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode === 'view'" label-width="150px">
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="门店编号" prop="companyNo">
              <el-input v-model="page.dataEntity.value.companyNo" placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="门店名称" prop="companyName">
              <el-input v-model="page.dataEntity.value.companyName" placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="会员名称" prop="memberName">
              <el-input v-model.number="page.dataEntity.value.memberName" placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="订单日期" prop="businessDateText">
              <el-input v-model.number="page.dataEntity.value.businessDateText" placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="零售单类型" prop="retailOrderTypeCode">
              <DatabaseSelect v-model="page.dataEntity.value.retailOrderTypeCode" table-name="retail_order_type" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="零售单状态" prop="retailOrderStatusCode">
              <DatabaseSelect v-model="page.dataEntity.value.retailOrderStatusCode" table-name="retail_order_status" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12" v-if="page.dataEntity.value.retailOrderTypeCode == 'ClueActivity'">
            <el-form-item label="俱乐部活动名称" prop="clubActivityName">
              <el-input v-model.number="page.dataEntity.value.clubActivityName" placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12" v-if="page.dataEntity.value.retailOrderTypeCode == 'StoryVideo'">
            <el-form-item label="晚安故事购买视频" prop="storyVideoTitle">
              <el-input v-model.number="page.dataEntity.value.storyVideoTitle" placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12" v-if="page.dataEntity.value.retailOrderTypeCode == 'NightStory'">
            <el-form-item label="晚安故事购买类型" prop="nightStoryType">
              <el-input v-model.number="page.dataEntity.value.nightStoryType" placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12" v-if="page.dataEntity.value.retailOrderTypeCode == 'NightStory'">
            <el-form-item label="晚安故事订阅天数" prop="nightStoryDays">
              <el-input v-model.number="page.dataEntity.value.nightStoryDays" placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="支付时间" prop="payTimeText" v-if="page.dataEntity.value.retailOrderStatusCode == 'Paid'">
              <el-input v-model.number="page.dataEntity.value.payTimeText" placeholder="请输入"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="支付金额" prop="amount" v-if="page.dataEntity.value.retailOrderStatusCode == 'Paid'">
              <el-input v-model.number="page.dataEntity.value.amount" placeholder="请输入"></el-input>
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

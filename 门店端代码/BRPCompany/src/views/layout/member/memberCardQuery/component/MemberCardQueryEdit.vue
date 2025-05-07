<template>
	 <div>
	    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600" draggable overflow>
  <el-form
    ref="MemberCardBatchFormRef"
    :model="MemberCardBatchForm"
    :rules="rules"
    label-width="130px"
    class="demo-MemberCardBatchForm"
    :size="formSize"
    status-icon>
    <el-form-item label="批次名" prop="name">
      <el-input v-model="MemberCardBatchForm.name" placeholder="请输入批次名"/>
    </el-form-item>
   <el-form-item label="批次编号" prop="batchNo">
     <el-input v-model="MemberCardBatchForm.batchNo" placeholder="请输入批次编号"/>
   </el-form-item>
   <el-form-item label="批次显示颜色" prop="color">
     <el-color-picker v-model="MemberCardBatchForm.color" />
   </el-form-item>
   <el-form-item label="最大借阅次数" prop="borrowQuantity">
     <el-input v-model="MemberCardBatchForm.borrowQuantity" placeholder="请输入最大借阅次数"/>
   </el-form-item>
   <el-form-item label="是否能在线借阅" prop="isOnlineReading">
      <el-radio-group v-model="MemberCardBatchForm.isOnlineReading">
            <el-radio :label="true" size="large">是</el-radio>
            <el-radio :label="false" size="large">否</el-radio>
	  </el-radio-group>
   </el-form-item>
   <el-form-item label="会员卡类型" prop="memberCardTypeId">
    <el-select v-model="MemberCardBatchForm.memberCardTypeId" placeholder="请选择会员卡类型" filterable>
			 <el-option
			      v-for="item in MemberCardTypeData"
			      :key="item.id"
			      :label="item.name"
			      :value="item.id"
			    />
    </el-select>
   </el-form-item>
  </el-form>
     <template #footer>
          <span class="dialog-footer">
			<el-button v-show="isShowBtn" type="primary" @click="submitForm(MemberCardBatchFormRef)"> 确定 </el-button>
            <el-button @click="dialogFormVisible = false">取消</el-button>
          </span>
        </template>
  </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { inject, reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import common from '@/js/common'
import util from '@/js/util'

const dialogFormVisible = ref(false)
const msg = ref('编辑会员卡批次')
const refreshList = inject("getDataList");
const color = ref()


interface MemberCardBatchForm {
  id: string
  companyId: string
  name: string
  batchNo: string
  color: string
  borrowQuantity: string
  isOnlineReading: string
  memberCardTypeId: string
  createTime: string
  updateTime: string
}

const formSize = ref('default')
const MemberCardBatchFormRef = ref<FormInstance>()
const MemberCardBatchForm = reactive<MemberCardBatchForm>({
      id: '',
      companyId: '1',
      name: '',
      batchNo: '',
      color: '',
      borrowQuantity: '',
      isOnlineReading: '',
      memberCardTypeId: '',
      createTime: '',
      updateTime: ''
})
const MemberCardBatchFormInit = reactive<MemberCardBatchForm>({
      id: '',
      companyId: '1',
      name: '',
      batchNo: '',
      color: '',
      borrowQuantity: '',
      isOnlineReading: '',
      memberCardTypeId: '',
      createTime: '',
      updateTime: ''
})


const rules = reactive<FormRules<MemberCardBatchForm>>({
  name: [
    { required: true, message: '批次名不允许为空', trigger: 'change' },
  ],
  batchNo: [
    {
      required: true,
      message: '批次编号不允许为空',
      trigger: 'change',
    },
  ],
  color: [
    {
      required: true,
      message: '批次显示颜色不允许为空',
      trigger: 'change',
    },
  ],
  borrowQuantity: [
    {
      required: true,
      message: '最大借阅次数不允许为空',
      trigger: 'change',
    },
  ],
  isOnlineReading: [
   {
     required: true,
     message: '是否能在线借阅不允许为空',
     trigger: 'change',
   },
  ],
  memberCardTypeId: [
    {
      required: true,
      message: '会员卡类型不允许为空',
      trigger: 'change',
    },
  ],
})

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
        // id不为空修改，否则新增
		const serviceName = ref();
          if(MemberCardBatchForm.id == ''){
          	serviceName.value = "AddMemberCardBatch";
          }else{
          	serviceName.value = "UpdateMemberCardBatch";
          }
          common.call(serviceName.value, MemberCardBatchForm, function(result){
          	dialogFormVisible.value = false
          	refreshList();
          },function(result){
          	console.log("会员卡批次编辑失败");
          })
    }
  })
}

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}

// 编辑页初始化
const isShowBtn = ref(true);
const MemberCardTypeData = ref([]) 
function init(data) {  
	// 显示弹窗
	dialogFormVisible.value = true
	// 清除表单的校验规则
  this.$nextTick(()=>{
  	this.$refs.MemberCardBatchFormRef.clearValidate();  
  })
  // 表单渲染
  Object.assign(MemberCardBatchForm, data)
 // 隐藏确定按钮
  isShowBtn.value = true;
  // 会员卡类型下拉框列表回显
  common.call("GetMemberCardTypeList", null, function(result){
	  MemberCardTypeData.value = result.data.dataList;
  },function(result){
  	console.log("会员卡类型获取失败");
  })
  // 会员卡类型下拉框回显
  let param = {
  		 id: parseInt(MemberCardBatchForm.memberCardTypeId)
  }
  common.call("GetMemberCardType", param, function(result){
	  debugger
  	  MemberCardBatchForm.memberCardTypeId = result.data.name;
  },function(result){
  	console.log("会员卡类型获取失败");
  })
 
	 MemberCardBatchForm.updateTime = util.getFormatTime(new Date());
}

defineExpose({
  init
})
</script>
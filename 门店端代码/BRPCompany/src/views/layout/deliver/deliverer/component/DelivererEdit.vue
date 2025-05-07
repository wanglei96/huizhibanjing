<template>
	 <div>
	    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600" draggable overflow>
  <el-form
    ref="DelivererFormRef"
    :model="DelivererForm"
    :rules="rules"
    label-width="150px"
    class="demo-DelivererForm"
    status-icon>
    <el-form-item label="编号" prop="employeeNo">
      <el-input v-model="DelivererForm.employeeNo" disabled placeholder="编号自动生成"/>
    </el-form-item>
	
   <el-form-item label="联系方式" prop="mobilePhone">
     <el-input v-model="DelivererForm.mobilePhone" placeholder="联系方式"/>
   </el-form-item>
   <el-form-item label="密码" prop="password">
     <el-input v-model="DelivererForm.password" placeholder="请输入密码" type="password" show-password />
   </el-form-item>
   <el-form-item label="姓名" prop="name">
     <el-input v-model="DelivererForm.name" placeholder="请输入配送员姓名"/>
   </el-form-item>
   <el-form-item label="配送大区" prop="deliverAreaId">
     <el-select v-model="DelivererForm.deliverAreaId" placeholder="请选择配送大区" filterable>
   		  <el-option
   		         v-for="item in DeliverAreaData"
   		         :key="item.id"
   		         :label="item.name"
   		         :value="item.id"
   		       />
           </el-select>
   </el-form-item>
  </el-form>
     <template #footer>
          <span class="dialog-footer">
			<el-button v-show="isShowBtn" type="primary" @click="submitForm(DelivererFormRef)"> 确定 </el-button>
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
const msg = ref()
const refreshList = inject("getDataList");
const companyId = common.getLocalStorage("companyId")

const DelivererFormRef = ref<FormInstance>()
const DelivererForm = reactive({
      id: '',
      companyId: companyId,
      employeeNo: '',
      mobilePhone: '',
	  password: '',
	  name: '',
	  deliverAreaId: '',
      createTime: '',
      updateTime: ''
})
const DelivererFormInit = reactive({
    id: '',
    companyId: companyId,
    employeeNo: '',
    mobilePhone: '',
    password: '',
    name: '',
    deliverAreaId: '',
    createTime: '',
    updateTime: ''
})

const rules = reactive<FormRules>({
  mobilePhone: [{
  		required: true,
  		message: '联系方式不允许为空',
  		trigger: 'change'
  	},
  	{
  		pattern: /^1[3456789]\d{9}$/,
  		message: "请输入正确的联系方式",
  		trigger: "change",
  	}
  ],
  password: [
    { required: true, message: '密码不允许为空', trigger: 'change' },
  ],
  name: [
    { required: true, message: '配送员姓名不允许为空', trigger: 'change' },
  ],
  deliverAreaId: [
    { required: true, message: '配送大区不允许为空', trigger: 'change' },
  ],
})

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
		  common.call("EditDeliverer", DelivererForm, function(result){
		  			   ElMessage({
		  			      message: result.data,
		  			      type: 'success',
		  			    })
		  	dialogFormVisible.value = false
		  	refreshList();
		  },function(result){
		  			   ElMessage.error(result.errorMessage)
		  })
    }
  })
}

// 编辑页初始化
const isShowBtn = ref(true);
const DeliverAreaData = ref([]) 
function init(type,data) {  
	// 显示弹窗
	dialogFormVisible.value = true
	// 清除表单的校验规则
  this.$nextTick(()=>{
  	this.$refs.DelivererFormRef.clearValidate();  
  })
    // 表单渲染
    Object.assign(DelivererForm, data)
    // 隐藏确定按钮
    isShowBtn.value = true;
    if (type === 'add') {
		msg.value = '新建配送员'
		Object.assign(DelivererForm, DelivererFormInit)
	 } else if(type === 'edit'){
		 msg.value = '修改配送员'
	 }else {
		msg.value = '查看配送员'
		isShowBtn.value = false;
	 }
 
 // 配送大区下拉框列表渲染
 common.call("GetDeliverAreaList", {companyId: companyId}, function(result){
 	  DeliverAreaData.value = result.data.dataList;
 },function(result){
 	console.log("配送大区获取失败");
 })
}
defineExpose({
  init
})
</script>
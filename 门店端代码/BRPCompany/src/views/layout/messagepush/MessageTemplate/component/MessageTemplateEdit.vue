<template>
	 <div>
	    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600" draggable>
  <el-form
    ref="MessageTemplateFormRef"
    :model="MessageTemplateForm"
    :rules="rules"
    label-width="150px"
    class="demo-MessageTemplateForm"
    status-icon>
	<!-- <el-form-item label="推送人员" prop="pusherCode">
	  <el-select v-model="MessageTemplateForm.pusherCode" placeholder="请选择推送人员">
			  <el-option
			         v-for="item in DeliverAreaData"
			         :key="item.id"
			         :label="item.name"
			         :value="item.id"
			       />
	        </el-select>
	</el-form-item> -->
	<el-form-item label="推送人员" prop="pusherCode">
	  <el-input v-model="MessageTemplateForm.pusherCode" placeholder="请输入推送人员"/>
	</el-form-item>
	<el-form-item label="推送时间" prop="pushTime">
	   <el-date-picker
	          v-model="MessageTemplateForm.pushTime"
			  value-format="YYYY-MM-DD HH:mm:ss"
	          type="date"
	          placeholder="请选择推送时间"
	          :size="size"
	        />
	</el-form-item>
	<el-form-item label="时间间隔" prop="pushInterval">
	  <el-input v-model="MessageTemplateForm.pushInterval" placeholder="请输入时间间隔"/>
	</el-form-item>
	<el-form-item label="内容" prop="content">
	  <el-input type="textarea" v-model="MessageTemplateForm.content" placeholder="请输入内容"/>
	</el-form-item>
	<el-form-item label="推送方式" prop="pushTypeCode">
	  <el-select v-model="MessageTemplateForm.pushTypeCode" placeholder="请选择推送方式" filterable>
			  <el-option
			         v-for="item in PushTypeCodeData"
			         :key="item.code"
			         :label="item.name"
			         :value="item.code"
			       />
	        </el-select>
	</el-form-item>
   <el-form-item label="消息类型" prop="messageTypeCode">
     <el-select v-model="MessageTemplateForm.messageTypeCode" placeholder="请选择消息类型" filterable>
   		  <el-option
   		         v-for="item in MessageTypeCodeData"
   		         :key="item.code"
   		         :label="item.name"
   		         :value="item.code"
   		       />
           </el-select>
   </el-form-item>
  </el-form>
     <template #footer>
          <span class="dialog-footer">
			<el-button v-show="isShowBtn" type="primary" @click="submitForm(MessageTemplateFormRef)"> 确定 </el-button>
            <el-button @click="dialogFormVisible = false">取消</el-button>
          </span>
        </template>
  </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { inject, reactive, ref } from 'vue'
import type { FormInstance, FormRules, ElMessage } from 'element-plus'
import common from '@/js/common'
import util from '@/js/util'

const dialogFormVisible = ref(false)
const msg = ref()
const refreshList = inject("getDataList");
const companyId = common.getLocalStorage("companyId")
const MessageTemplateFormRef = ref<FormInstance>()
const MessageTemplateForm = reactive({
      id: '',
      companyId: companyId,
      pusherCode: '',
      pushTime: '',
	  pushTimeText: '',
      messageTypeCode: '',
      content: '',
      pushInterval: '',
      pushTypeCode: '',
      createTime: '',
      updateTime: ''

})
const MessageTemplateFormInit = reactive({
      id: '',
      companyId: companyId,
      pusherCode: '',
      pushTime: '',
	  pushTimeText: '',
      messageTypeCode: '',
      content: '',
      pushInterval: '',
      pushTypeCode: '',
      createTime: '',
      updateTime: ''
})

const rules = reactive<FormRules>({
  pusherCode: [
    { required: true, message: '推送人员不允许为空', trigger: 'change' },
  ],
  pushTime: [
    { required: true, message: '推送时间不允许为空', trigger: 'change' },
  ],
  pushInterval: [
    { required: true, message: '时间间隔不允许为空', trigger: 'change' },
  ],
  content: [
    { required: true, message: '内容不允许为空', trigger: 'change' },
  ],
  pushTypeCode: [
    { required: true, message: '推送方式不允许为空', trigger: 'change' },
  ],
  messageTypeCode: [
    { required: true, message: '消息类型不允许为空', trigger: 'change' },
  ],
})

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
		// id不为空修改，否则新增
		const serviceName = ref();
		  if(MessageTemplateForm.id == ''){
			serviceName.value = "AddMessageTemplate";
			MessageTemplateForm.createTime = util.getFormatTime(new Date());
		  }else{
			serviceName.value = "UpdateMessageTemplate";
			MessageTemplateForm.updateTime = util.getFormatTime(new Date());
		  }
		common.call(serviceName.value, MessageTemplateForm, function(result){
			dialogFormVisible.value = false
			refreshList();
		},function(result){
			console.log("消息模板编辑失败");
		})
    }
  })
}

// 编辑页初始化
const isShowBtn = ref(true);
const MessageTypeCodeData = ref();
const PushTypeCodeData = ref();
function init(type,data) {  
	// 显示弹窗
	dialogFormVisible.value = true
	// 隐藏确定按钮
	isShowBtn.value = true;
	 if (type === 'add') {
		msg.value = '新建消息模板'
		Object.assign(MessageTemplateForm, MessageTemplateFormInit)
	 } else{
		 // 表单渲染
		 Object.assign(MessageTemplateForm, data);
		 MessageTemplateForm.pushTime = data.pushTimeText;
		 MessageTemplateForm.createTime = data.createTimeText;
		 if(type === 'edit'){
		 		 msg.value = '修改消息模板'
		 }else if(type === 'view'){
		 		 msg.value = '查看消息模板'
		 		 isShowBtn.value = false;
		 }
	 } 
	 
	 // 消息类型下拉框列表渲染
	 common.call("GetMessageTypeList", null, function(result){
	 		  MessageTypeCodeData.value = result.data.dataList;
	 },function(result){
	 		console.log("消息类型获取失败");
	 })
	 
	 // 推送方式下拉框列表渲染
	 common.call("GetMessagePushTypeList", null, function(result){
	 		  PushTypeCodeData.value = result.data.dataList;
	 },function(result){
	 		console.log("推送方式获取失败");
	 })
}
defineExpose({
  init
})
</script>
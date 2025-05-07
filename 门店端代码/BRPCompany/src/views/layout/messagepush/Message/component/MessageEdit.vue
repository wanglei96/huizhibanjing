<template>
	 <div>
	    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600" draggable>
  <el-form
    ref="MessageFormRef"
    :model="MessageForm"
    label-width="150px"
    class="demo-MessageForm"
    status-icon>
    <el-form-item label="会员信息" prop="memberCardId">
      <el-input v-model="MessageForm.memberCardId" disabled placeholder="编号自动生成"/>
    </el-form-item>
	
   <el-form-item label="推送时间" prop="pushTimeText">
     <el-input v-model="MessageForm.pushTimeText" placeholder="请输入登录账号"/>
   </el-form-item>

   <el-form-item label="消息类型" prop="messageTypeCode">
     <el-select v-model="MessageForm.messageTypeCode" placeholder="请选择消息类型" filterable>
   		  <el-option
   		         v-for="item in MessageTypeCodeData"
   		         :key="item.code"
   		         :label="item.name"
   		         :value="item.code"
   		       />
           </el-select>
   </el-form-item>
   <el-form-item label="内容" prop="content">
     <el-input type="textarea" v-model="MessageForm.content" placeholder="请输入内容"/>
   </el-form-item>
  </el-form>
     <template #footer>
          <span class="dialog-footer">
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

const MessageFormRef = ref<FormInstance>()
const MessageForm = reactive({
      id: '',
      memberCardId: '',
      pushTimeText: '',
	  messageTypeCode: '',
	  content: '',
      createTime: '',
      updateTime: ''
})


// 编辑页初始化
const MessageTypeCodeData = ref([]) 
function init(type,data) {  
	// 显示弹窗
	dialogFormVisible.value = true
    // 表单渲染
    Object.assign(MessageForm, data)
	 // 消息类型下拉框列表渲染
	 common.call("GetMessageTypeList", null, function(result){
		  MessageTypeCodeData.value = result.data.dataList;
	 },function(result){
		console.log("消息类型获取失败");
	 })
}
defineExpose({
  init
})
</script>
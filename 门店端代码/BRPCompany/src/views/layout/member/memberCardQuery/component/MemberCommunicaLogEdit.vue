<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600"
			draggable overflow>
			<el-form ref="MemberCommunicaLogFormRef" :model="MemberCommunicaLogForm" :rules="rules" label-width="150px"
				class="demo-MemberCommunicaLogForm" status-icon>

				<el-form-item label="沟通人员" prop="companyUserId">
					<DatabaseSelect table-name="view_company_user" :condition="'company_id=' + companyId"
						v-model="MemberCommunicaLogForm.companyUserId" valueFieldName="id" placeholder="请选择沟通人员" />
				</el-form-item>
				
				<el-form-item label="沟通类别" prop="memberCommunicaLogTypeCode">
					<DatabaseSelect table-name="member_communica_log_type"
						v-model="MemberCommunicaLogForm.memberCommunicaLogTypeCode" placeholder="请选择沟通类别" />
				</el-form-item>
				
				<el-form-item label="沟通渠道" prop="memberCommunicaLogMethodCode">
					<DatabaseSelect table-name="member_communica_log_method"
						v-model="MemberCommunicaLogForm.memberCommunicaLogMethodCode" placeholder="请选择沟通渠道" />
				</el-form-item>

				<el-form-item label="沟通内容" prop="content">
					<el-input v-model="MemberCommunicaLogForm.content" type="textarea" placeholder="请输入沟通内容" />
				</el-form-item>

				<el-form-item label="沟通时间" prop="communicaTime">
					<el-date-picker v-model="MemberCommunicaLogForm.communicaTime" value-format="YYYY-MM-DD HH:mm:ss"
						type="date" placeholder="请选择沟通时间" :size="size" />
				</el-form-item>

			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button v-show="isShowBtn" type="primary" @click="submitForm(MemberCommunicaLogFormRef)"> 确定
					</el-button>
					<el-button @click="dialogFormVisible = false">取消</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>

<script lang="ts" setup>
import { inject, reactive, ref } from 'vue'
import type { FormInstance, FormRules, ElMessage } from 'element-plus'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import common from '@/js/common'
import util from '@/js/util'

const isShowBtn = ref(true);
const dialogFormVisible = ref(false)
const msg = ref()
const refreshList = inject("getDataList");
// 当前登陆人ID
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
const companyUserId = ref(
  `${common.getLocalStorage('companyUserId')}`
);
const MemberCommunicaLogFormRef = ref<FormInstance>()
const MemberCommunicaLogForm = reactive({
	id:'',
	memberId: '',
	companyUserId: companyUserId.value,
	memberCommunicaLogTypeCode: '',
	memberCommunicaLogMethodCode: '',
	content: '',
	communicaTime: '',
	createTime: '',
	updateTime: ''
})
const MemberCommunicaLogFormInit = reactive({
	id:'',
	memberId: '',
	companyUserId: companyUserId.value,
	memberCommunicaLogTypeCode: '',
	memberCommunicaLogMethodCode: '',
	content: '',
	communicaTime: '',
	createTime: '',
	updateTime: ''
})

const rules = reactive<FormRules>({
	content: [
		{ required: true, message: '沟通内容不允许为空', trigger: 'change' },
	],
})

const submitForm = async (formEl: FormInstance | undefined) => {
	if (!formEl) return
	await formEl.validate((valid, fields) => {
		if (valid) {
			let serviceName = "AddMemberCommunicaLog"
			if (MemberCommunicaLogForm.id != null && MemberCommunicaLogForm.id != '') {
				serviceName = "UpdateMemberCommunicaLog"
				MemberCommunicaLogForm.updateTime = util.getFormatTime(new Date());
			}else{
				MemberCommunicaLogForm.createTime = util.getFormatTime(new Date());
			}
			common.call(serviceName, MemberCommunicaLogForm, function (result) {
				ElMessage({
					message: "操作成功",
					type: 'success',
				})
				dialogFormVisible.value = false
				refreshList(MemberCommunicaLogForm.memberId);
			}, function (result) {
				ElMessage.error(result.errorMessage)
			})
		}
	})
}

const MemberCommunicaLogTypeData = ref([])
const MemberCommunicaLogChannelData = ref([])

// 编辑页初始化
function init(type, data) {
	// 默认显示确定按钮
	isShowBtn.value = true;
	// 显示弹窗
	dialogFormVisible.value = true
	// 清除表单的校验规则
	this.$nextTick(() => {
		this.$refs.MemberCommunicaLogFormRef.clearValidate();
	})
	// 表单渲染
	Object.assign(MemberCommunicaLogForm, data)
	if (type === 'add') {
		msg.value = '新建沟通记录'
		Object.assign(MemberCommunicaLogForm, MemberCommunicaLogFormInit)
		MemberCommunicaLogForm.memberId = data;
	} else if (type === 'edit') {
		msg.value = '修改沟通记录'
		MemberCommunicaLogForm.time = data.timeText;
	} else if (type === 'view') {
		msg.value = '查看沟通记录'
		MemberCommunicaLogForm.time = data.timeText;
		// 隐藏确定按钮
		isShowBtn.value = false;
	}

	common.call("GetMemberCommunicaLogTypeStatusList", null, function (result) {
		MemberCommunicaLogTypeData.value = result.data.dataList;
	}, function (result) {
		console.log("沟通类别获取失败");
	})

	common.call("GetMemberCommunicaLogChannelStatusList", null, function (result) {
		MemberCommunicaLogChannelData.value = result.data.dataList;
	}, function (result) {
		console.log("沟通渠道获取失败");
	})
}
defineExpose({
	init
})
</script>
<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600"
			draggable overflow>
			<el-form ref="MemberAddScoreFormRef" :model="MemberAddScoreForm" label-width="130px"
				class="demo-MemberAddScoreForm" :size="formSize" status-icon>

				<el-form-item label="积分类型" prop="memberScoreTypeCode">
					<DatabaseSelect table-name="member_score_type" v-model="MemberAddScoreForm.memberScoreTypeCode"
						placeholder="请选择积分类型" />
				</el-form-item>
				<el-form-item label="增减类型" prop="type">
					<DatabaseSelect table-name="member_card_disable_log_type" v-model="MemberAddScoreForm.type"
						placeholder="请选择增减类型" />
				</el-form-item>
				<el-form-item label="积分" prop="score">
					<el-input v-model="MemberAddScoreForm.score" placeholder="请输入积分" />
				</el-form-item>
				<el-form-item label="操作人" prop="operator">
					<el-input disabled v-model="MemberAddScoreForm.operator" placeholder="请输入操作人" />
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="submitForm(MemberAddScoreFormRef)"> 确定 </el-button>
					<el-button @click="dialogFormVisible = false">取消</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>

<script lang="ts" setup>
	import {
		inject,
		reactive,
		ref
	} from 'vue'
	import type {
		FormInstance,
		FormRules,
		ElMessage
	} from 'element-plus'
	import common from '@/js/common'
	import util from '@/js/util'
	// 当前门店登录用户
	const companyUserName = common.getLocalStorage("companyUserName")
	const dialogFormVisible = ref(false);
	const msg = ref('增加积分');
	const refreshList = inject("getDataList");
	const formSize = ref('default');

	const MemberAddScoreFormRef = ref < FormInstance > ()
	const MemberAddScoreForm = reactive({
		memberId: '',
		memberScoreTypeCode: '',
		score: '',
		operator: '',
		type: ''
	})

	const MemberAddScoreFormInit = reactive({
		memberId: '',
		memberScoreTypeCode: '',
		score: '',
		operator: '',
		type: ''
	})

	function submitForm() {
		common.call("EditMemberScore", MemberAddScoreForm, function(result) {
			dialogFormVisible.value = false
			refreshList();
		}, function(result) {
			ElMessage.error(result.errorMessage)
		})
	}

	// 编辑页初始化
	function init(data) {
		// 表单渲染
		Object.assign(MemberAddScoreForm, MemberAddScoreFormInit)
		// 显示弹窗
		dialogFormVisible.value = true;
		MemberAddScoreForm.operator = companyUserName;
		MemberAddScoreForm.memberId = data.memberId;
	}
	defineExpose({
		init
	})
</script>

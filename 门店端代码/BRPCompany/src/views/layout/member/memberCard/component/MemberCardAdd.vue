<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600"
			draggable overflow>
			<el-form ref="MemberCardBatchFormRef" :model="MemberCardBatchForm" :rules="rules" label-width="130px"
				class="demo-MemberCardBatchForm" :size="formSize" status-icon>
				<el-form-item label="会员卡批次" prop="memberCardBatchId">
					<DatabaseSelect table-name="member_card_batch" :condition="'company_id=' + companyId"
						v-model="MemberCardBatchForm.memberCardBatchId" valueFieldName="id" placeholder="请选择会员卡批次" />
				</el-form-item>
				<el-form-item label="数量" prop="num">
					<el-input v-model="MemberCardBatchForm.num" type="text" autocomplete="off"
						placeholder="请输入要生成的数量" />
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="submitForm(MemberCardBatchFormRef)"> 确定 </el-button>
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
		FormRules
	} from 'element-plus'
	import common from '@/js/common'
	import util from '@/js/util'

	const dialogFormVisible = ref(false)
	const msg = ref('会员卡生成')
	const refreshList = inject("getDataList");
	const companyId = common.getLocalStorage("companyId");

	const formSize = ref('default')
	const MemberCardBatchFormRef = ref < FormInstance > ()
	const MemberCardBatchForm = reactive({
		id: '',
		companyId: companyId,
		num: '',
		memberCardBatchId: '',
		createTime: '',
		updateTime: ''
	})


	const rules = reactive < FormRules > ({
		memberCardBatchId: [{
			required: true,
			message: '会员卡批次不允许为空',
			trigger: 'change',
		}],
		num: [{
				required: true,
				message: '数量不允许为空',
				trigger: 'change',
			},
			/* {
			  type: 'number',
			  message: '数量必须是一个数字',
			  trigger: 'change',
			}, */
		],
	})

	const submitForm = async (formEl: FormInstance | undefined) => {
		if (!formEl) return
		await formEl.validate((valid, fields) => {
			if (valid) {
				common.call('AddMemberCard', MemberCardBatchForm, function(result) {
					dialogFormVisible.value = false
					refreshList();
				}, function(result) {
					ElMessage.error(result.errorMessage)
				})
			}
		})
	}

	// 编辑页初始化
	function init(data) {
		// 显示弹窗
		dialogFormVisible.value = true
		// 清除表单的校验规则
		this.$nextTick(() => {
			this.$refs.MemberCardBatchFormRef.clearValidate();
		})
	}
	defineExpose({
		init
	})
</script>

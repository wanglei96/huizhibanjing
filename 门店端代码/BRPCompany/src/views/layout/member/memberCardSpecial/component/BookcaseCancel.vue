<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="700"
			draggable overflow>
			<el-form ref="BookcaseCancelFormRef" :model="BookcaseCancelForm" :rules="rules" label-width="130px"
				class="demo-BookcaseCancelForm" :size="formSize" status-icon>

				<el-form-item label="会员卡号" prop="cardNo">
					<el-input disabled v-model="BookcaseCancelForm.cardNo" placeholder="请输入会员卡号" />
				</el-form-item>
				<el-form-item label="会员姓名" prop="memberName">
					<el-input disabled v-model="BookcaseCancelForm.memberName" placeholder="请输入会员姓名" />
				</el-form-item>

				<el-form-item label="书箱收费" prop="bookboxAmount">
					<el-input disabled v-model="BookcaseCancelForm.bookboxAmount" placeholder="请输入已付金额" />
				</el-form-item>

				<el-form-item label="备注" prop="bookboxRemark">
					<el-input v-model="BookcaseCancelForm.bookboxRemark" type="textarea" placeholder="请输入备注" />
				</el-form-item>

			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="submitForm(BookcaseCancelFormRef)"> 确定 </el-button>
					<el-button @click="dialogFormVisible = false">取消</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>
<!-- <script src="//api.map.baidu.com/api?type=webgl&v=1.0&ak=您的密钥"/> -->
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

	const dialogFormVisible = ref(false)
	const msg = ref('注销书箱')
	const refreshList = inject("getDataList");
	const formSize = ref('default')

	const BookcaseCancelFormRef = ref < FormInstance > ()
	const BookcaseCancelForm = reactive({
		id: '',
		companyId: '',
		memberId: '',
		memberName: '',
		cardNo: '',
		bookboxAmount: '',
		bookboxRemark: ''
	})

	const BookcaseCancelFormInit = reactive({
		id: '',
		companyId: '',
		memberId: '',
		memberName: '',
		cardNo: '',
		bookboxAmount: '',
		bookboxRemark: ''
	})

	const rules = reactive < FormRules > ({
		bookboxRemark: [{
			required: true,
			message: '注销原因不允许为空',
			trigger: 'change',
		}, ],
	})

	const submitForm = async (formEl: FormInstance | undefined) => {
		if (!formEl) return
		await formEl.validate((valid, fields) => {
			if (valid) {
				// 传入需要修改的参数
				let params = {
					memberId: BookcaseCancelForm.memberId,
					bookboxRemark: BookcaseCancelForm.bookboxRemark
				}
				common.call("CancelBookBox", params, function(result) {
					dialogFormVisible.value = false
					refreshList();
				}, function(result) {
					console.log("注销书箱失败");
				})
			}
		})
	}

	const resetForm = (formEl: FormInstance | undefined) => {
		if (!formEl) return
		formEl.resetFields()
	}

	// 编辑页初始化
	const EmployeeData = ref([])
	const PaymentMethodData = ref([])

	function init(data) {
		// 显示弹窗
		dialogFormVisible.value = true

		// 清除表单的校验规则
		this.$nextTick(() => {
			this.$refs.BookcaseCancelFormRef.clearValidate();
		})
		// 表单渲染
		Object.assign(BookcaseCancelForm, BookcaseCancelFormInit)
		Object.assign(BookcaseCancelForm, data)
	}

	defineExpose({
		init
	})
</script>

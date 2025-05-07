<template>
	<div>
		<BookcaseCancel ref="BookcaseCancelRef" />
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="700"
			draggable overflow>
			<el-form ref="BookcaseInfoFormRef" :model="BookcaseInfoForm" :rules="rules" label-width="130px"
				class="demo-BookcaseInfoForm" :size="formSize" status-icon>
				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="会员姓名" prop="memberName">
							<el-input disabled v-model="BookcaseInfoForm.memberName" placeholder="请输入会员姓名" />
						</el-form-item>

						<el-form-item label="已付金额" prop="bookboxAmount">
							<el-input disabled v-model="BookcaseInfoForm.bookboxAmount" placeholder="请输入已付金额" />
						</el-form-item>

						<el-form-item label="支付方式" prop="paymentMethodCode">
							<DatabaseSelect table-name="payment_method" order
								v-model="BookcaseInfoForm.paymentMethodCode" placeholder="请选择支付方式" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="会员卡号" prop="cardNo">
							<el-input disabled v-model="BookcaseInfoForm.cardNo" placeholder="请输入会员卡号" />
						</el-form-item>

						<el-form-item label="增加收费" prop="addAmount">
							<el-input v-model="BookcaseInfoForm.addAmount" placeholder="请输入增加收费" />
						</el-form-item>

						<el-form-item label="办理人" prop="employeeId">
							<DatabaseSelect table-name="company_user" :condition="'company_id='+companyId"
								v-model="BookcaseInfoForm.employeeId" valueFieldName="id" placeholder="请选择办理人" />
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :xs="24" :sm="24" :md="24" :lg="24">
						<el-form-item label="备注" prop="bookboxRemark">
							<el-input v-model="BookcaseInfoForm.bookboxRemark" type="textarea" placeholder="请输入备注" />
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button style="margin-right:400px" type="danger"
						@click="BookcaseCancelRef.init(BookcaseInfoForm)"> 注销书箱 </el-button>
					<el-button type="primary" @click="submitForm(BookcaseInfoFormRef)"> 确定 </el-button>
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
	// 注销书箱
	import BookcaseCancel from './BookcaseCancel.vue'

	const dialogFormVisible = ref(false)
	const msg = ref('书箱信息登记')
	const refreshList = inject("getDataList");
	const formSize = ref('default')
	// 当前门店id
	const companyId = common.getLocalStorage("companyId")

	const BookcaseCancelRef = ref()

	const BookcaseInfoFormRef = ref < FormInstance > ()
	const BookcaseInfoForm = reactive({
		id: '',
		companyId: '',
		memberId: '',
		memberName: '',
		cardNo: '',
		bookboxAmount: '',
		addAmount: '',
		paymentMethodCode: '',
		employeeId: '',
		bookboxRemark: '',
		remark: ''
	})

	const BookcaseInfoFormInit = reactive({
		id: '',
		companyId: '',
		memberId: '',
		memberName: '',
		cardNo: '',
		bookboxAmount: '',
		addAmount: '',
		paymentMethodCode: '',
		employeeId: '',
		bookboxRemark: '',
		remark: ''
	})

	const rules = reactive < FormRules > ({
		addAmount: [{
			required: true,
			message: '增加收费不允许为空',
			trigger: 'change',
		}, ],
		paymentMethodCode: [{
			required: true,
			message: '支付方式不允许为空',
			trigger: 'change',
		}, ],
	})

	const submitForm = async (formEl: FormInstance | undefined) => {
		if (!formEl) return
		await formEl.validate((valid, fields) => {
			if (valid) {
				if (BookcaseInfoForm.paymentMethodCode === 'Other') {
					ElMessage.error('请输入备注')
				}
				// 传入需要修改的参数
				let params = {
					memberId: BookcaseInfoForm.memberId,
					bookboxAmount: parseFloat(BookcaseInfoForm.bookboxAmount),
					addAmount: parseFloat(BookcaseInfoForm.addAmount),
					paymentMethodCode: BookcaseInfoForm.paymentMethodCode,
					employeeId: BookcaseInfoForm.employeeId,
					bookboxRemark: BookcaseInfoForm.bookboxRemark
				}
				common.call("UpdateBookcaseInfo", params, function(result) {
					dialogFormVisible.value = false
					refreshList();
				}, function(result) {
					console.log("书箱信息编辑失败");
				})
			}
		})
	}

	const resetForm = (formEl: FormInstance | undefined) => {
		if (!formEl) return
		formEl.resetFields()
	}

	// 编辑页初始化
	function init(data) {
		// 显示弹窗
		dialogFormVisible.value = true
		// 清除表单的校验规则
		this.$nextTick(() => {
			this.$refs.BookcaseInfoFormRef.clearValidate();
		})
		// 表单渲染
		Object.assign(BookcaseInfoForm, BookcaseInfoFormInit)
		Object.assign(BookcaseInfoForm, data)
	}

	defineExpose({
		init
	})
</script>

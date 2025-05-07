<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600"
			draggable overflow>
			<el-form ref="MemberCardTypeFormRef" :model="MemberCardTypeForm" :rules="rules" label-width="150px"
				class="demo-MemberCardTypeForm" :size="formSize" status-icon>
				<el-form-item label="名称" prop="name">
					<el-input v-model="MemberCardTypeForm.name" placeholder="请输入名称" />
				</el-form-item>
				<el-form-item label="每次最多可借数量" prop="maxQuantity">
					<el-input v-model="MemberCardTypeForm.maxQuantity" placeholder="请输入每次最多可借数量" />
				</el-form-item>
				<el-form-item label="是否需要借书押金" prop="isSecurityDeposit">
					<el-radio-group v-model="MemberCardTypeForm.isSecurityDeposit" @change="isSecurityDepositChange">
						<el-radio :label="true" size="large">是</el-radio>
						<el-radio :label="false" size="large">否</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="押金金额" prop="securityDepositPrice">
					<el-input v-model="MemberCardTypeForm.securityDepositPrice" placeholder="请输入押金金额" />
				</el-form-item>
				<el-form-item label="会员缴费金额" prop="membershipPrice">
					<el-input v-model="MemberCardTypeForm.membershipPrice" placeholder="请输入会员缴费金额" />
				</el-form-item>
				<el-form-item label="书箱价格" prop="bookboxPrice">
					<el-input v-model="MemberCardTypeForm.bookboxPrice" placeholder="请输入书箱费用" />
				</el-form-item>
				<el-form-item label="借阅天数" prop="days">
					<el-input v-model="MemberCardTypeForm.days" placeholder="请输入借阅天数" />
				</el-form-item>
				<el-form-item label="会员卡分类" prop="memberCardCategoryCode">
					<DatabaseSelect table-name="member_card_category"
						v-model="MemberCardTypeForm.memberCardCategoryCode" placeholder="请选择会员卡分类" />
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button v-show="isShowBtn" type="primary" @click="submitForm(MemberCardTypeFormRef)"> 确定
					</el-button>
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
	const msg = ref()
	const companyId = localStorage.getItem("companyId")
	const refreshList = inject("getDataList");

	const formSize = ref('default')
	const MemberCardTypeFormRef = ref < FormInstance > ()
	const MemberCardTypeForm = reactive({
		id: '',
		companyId: companyId,
		name: '',
		maxQuantity: '3',
		isSecurityDeposit: '',
		securityDepositPrice: '',
		bookboxPrice: '',
		membershipPrice: '',
		days: '',
		memberCardCategoryCode: '',
		createTime: '',
		updateTime: ''
	})

	const MemberCardTypeFormInit = reactive({
		id: '',
		companyId: companyId,
		name: '',
		maxQuantity: '3',
		isSecurityDeposit: '',
		securityDepositPrice: '',
		bookboxPrice: '',
		membershipPrice: '',
		days: '',
		memberCardCategoryCode: '',
		createTime: '',
		updateTime: ''
	})

	const rules = reactive < FormRules > ({
		name: [{
			required: true,
			message: '名称不允许为空',
			trigger: 'change'
		}, ],
		maxQuantity: [{
			required: true,
			message: '每次最多可借数量不允许为空',
			trigger: 'change',
		}, ],
		isSecurityDeposit: [{
			required: true,
			message: '是否需要借书押金不允许为空',
			trigger: 'change',
		}, ],
		securityDepositPrice: [{
			required: false,
			message: '押金金额不允许为空',
			trigger: 'change',
		}, ],
		bookboxPrice: [{
			required: true,
			message: '书箱费用不允许为空',
			trigger: 'change',
		}, ],
		membershipPrice: [{
			required: true,
			message: '会员缴费金额不允许为空',
			trigger: 'change',
		}, ],
		days: [{
			required: true,
			message: '借阅天数不允许为空',
			trigger: 'change',
		}, ],
		memberCardCategoryCode: [{
			required: true,
			message: '会员卡分类编码不允许为空',
			trigger: 'change',
		}, ],
	})

	const submitForm = async (formEl: FormInstance | undefined) => {
		if (!formEl) return
		await formEl.validate((valid, fields) => {
			if (valid) {
				// id不为空修改，否则新增
				const serviceName = ref();
				if (MemberCardTypeForm.id == '') {
					serviceName.value = "AddMemberCardType";
					MemberCardTypeForm.createTime = util.getFormatTime(new Date());
				} else {
					serviceName.value = "UpdateMemberCardType";
					MemberCardTypeForm.updateTime = util.getFormatTime(new Date());
				}
				common.call(serviceName.value, MemberCardTypeForm, function(result) {
					dialogFormVisible.value = false
					refreshList();
				}, function(result) {
					console.log("会员卡批次编辑失败");
				})
			}
		})
	}

	// 编辑页初始化
	const isShowBtn = ref(true);

	function init(type, data) {
		// 显示弹窗
		dialogFormVisible.value = true
		// 清除表单的校验规则
		this.$nextTick(() => {
			this.$refs.MemberCardTypeFormRef.clearValidate();
		})
		// 隐藏确定按钮
		isShowBtn.value = true;

		if (type === 'add') {
			msg.value = '新建会员卡类型'
			Object.assign(MemberCardTypeForm, MemberCardTypeFormInit)
		} else {
			if (type === 'edit') {
				msg.value = '编辑会员卡类型'
			} else {
				msg.value = '查看会员卡类型'
				isShowBtn.value = false;
			}
			// 表单渲染
			Object.assign(MemberCardTypeForm, data)
			MemberCardTypeForm.createTime = data.createTimeText
		}
	}
	// 是否需要借书押金控制押金金额是否必填
	const isSecurityDepositChange = (value) => {
		if (value) {
			rules.securityDepositPrice[0].required = true;
		} else {
			rules.securityDepositPrice[0].required = false;
		}
	}

	defineExpose({
		init
	})
</script>

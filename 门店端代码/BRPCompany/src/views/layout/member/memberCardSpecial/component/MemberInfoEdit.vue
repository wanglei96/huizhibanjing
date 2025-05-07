<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="700"
			draggable overflow>
			<el-form ref="MemberInfoFormRef" :model="MemberInfoForm" :rules="rules" label-width="130px"
				class="demo-MemberInfoForm" :size="formSize" status-icon>
				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="开卡时间" prop="effectiveDateText">
							<el-date-picker v-model="MemberInfoForm.effectiveDateText"
								value-format="YYYY-MM-DD HH:mm:ss" type="date" placeholder="请选择开卡时间" :size="size" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="失效时间" prop="expireDateText">
							<el-date-picker disabled v-model="MemberInfoForm.expireDateText"
								value-format="YYYY-MM-DD HH:mm:ss" type="date" placeholder="请选择失效时间" :size="size" />
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="会员姓名" prop="memberName">
							<el-input v-model="MemberInfoForm.memberName" placeholder="请输入会员姓名" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="出生日期" prop="birthdayText">
							<el-date-picker v-model="MemberInfoForm.birthdayText" value-format="YYYY-MM-DD HH:mm:ss"
								type="date" placeholder="请选择出生日期" :size="size" />
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="联系方式" prop="mobilePhone">
							<el-input v-model="MemberInfoForm.mobilePhone" placeholder="请输入联系方式" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="性别" prop="gender">
							<el-radio-group v-model="MemberInfoForm.gender">
								<el-radio :label="true" size="large">男</el-radio>
								<el-radio :label="false" size="large">女</el-radio>
							</el-radio-group>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="学校" prop="school">
							<el-input v-model="MemberInfoForm.school" placeholder="请输入学校" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="年级" prop="grade">
							<el-input v-model="MemberInfoForm.grade" placeholder="请输入年级" />
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="班级" prop="classes">
							<el-input v-model="MemberInfoForm.classes" placeholder="请输入班级" />
						</el-form-item>
					</el-col>

					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="会员卡类型" prop="memberCardTypeId">
							<DatabaseSelect table-name="member_card_type" :condition="'company_id=' + companyId"
								v-model="MemberInfoForm.memberCardTypeId" valueFieldName="id" placeholder="请选择会员卡类型" />
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="推荐人" prop="referrerId">
							<DatabaseSelect table-name="member" :condition="'company_id=' + companyId"
								v-model="MemberInfoForm.referrerId" valueFieldName="id" placeholder="请选择推荐人" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="推荐人备注" prop="referrerRemark">
							<el-input v-model="MemberInfoForm.referrerRemark" type="textarea" placeholder="请输入推荐人备注" />
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="24" :sm="24" :md="24" :lg="24">
						<el-form-item label="备注" prop="remark">
							<el-input v-model="MemberInfoForm.remark" type="textarea" placeholder="请输入备注" />
						</el-form-item>
					</el-col>
				</el-row>

			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="submitForm(MemberInfoFormRef)"> 确定 </el-button>
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
	const msg = ref('信息修改')
	const refreshList = inject("getDataList");
	const formSize = ref('default')
	const companyId = common.getLocalStorage("companyId")

	const MemberInfoFormRef = ref < FormInstance > ()
	const MemberInfoForm = reactive({
		id: '',
		companyId: companyId,
		memberId: '',
		memberName: '',
		birthday: '',
		birthdayText: '',
		mobilePhone: '',
		referrerId: '',
		gender: '',
		classes: '',
		referrerRemark: '',
		memberCardTypeId: '',
		effectiveDateText: '',
		expireDateText: '',
		effectiveDate: '',
		expireDate: '',
		remark: '',
		createTime: '',
		updateTime: '',
		school: '',
		grade: '',
		days: ''
	})

	const MemberInfoFormInit = reactive({
		id: '',
		companyId: companyId,
		memberId: '',
		memberName: '',
		birthday: '',
		birthdayText: '',
		mobilePhone: '',
		referrerId: '',
		gender: '',
		classes: '',
		referrerRemark: '',
		memberCardTypeId: '',
		effectiveDateText: '',
		expireDateText: '',
		effectiveDate: '',
		expireDate: '',
		remark: '',
		createTime: '',
		updateTime: '',
		school: '',
		grade: '',
		days: ''
	})

	const rules = reactive < FormRules > ({
		/* memberName: [
		    {
		      required: true,
		      message: '会员姓名不允许为空',
		      trigger: 'change',
		    },
		  ], */
	})

	const submitForm = async (formEl: FormInstance | undefined) => {
		if (!formEl) return
		await formEl.validate((valid, fields) => {
			if (valid) {
				common.call("EditMemberInfo", MemberInfoForm, function(result) {
					dialogFormVisible.value = false
					refreshList();
				}, function(result) {
					console.log("会员信息修改失败");
				})
			}
		})
	}

	// 编辑页初始化
	function init(data) {
		// 显示弹窗
		dialogFormVisible.value = true
		// 清除表单的校验规则
		//this.$nextTick(()=>{
		//this.$refs.MemberInfoFormRef.clearValidate();  
		//})
		// 表单渲染
		if(data.memberCardTypeId == null){
			data.memberCardTypeId = "";
		}
		if(data.referrerId == null){
			data.referrerId = "";
		}
		Object.assign(MemberInfoForm, data)
		MemberInfoForm.createTime = data.createTimeText
	}

	defineExpose({
		init
	})
</script>

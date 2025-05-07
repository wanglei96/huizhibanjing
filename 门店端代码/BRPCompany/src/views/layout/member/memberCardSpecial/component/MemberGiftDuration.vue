<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600"
			draggable overflow>

			<el-form ref="MemberGiftDurationFormRef" :model="MemberGiftDurationForm" :rules="rules" :size="formSize"
				status-icon>
				<el-form-item style="background-color:  #FCF8E3;color: #95886D;">
					<div style="margin-left: 0.3125rem;">
						<p>增加时长规则</p>
						<p>1. 会员卡未到期时，到期时间向后顺延。</p>
						<p>2. 会员卡已到期时，以当天时间向后顺延。</p>
					</div>
				</el-form-item>
				<el-form-item label="会员姓名" prop="memberName">
					<el-input disabled v-model="MemberGiftDurationForm.memberName" />
				</el-form-item>

				<el-form-item label="会员编号" prop="cardNo">
					<el-input disabled v-model="MemberGiftDurationForm.cardNo" />
				</el-form-item>

				<el-form-item label="会员卡期限">
					<el-date-picker disabled v-model="MemberGiftDurationForm.memberCardDate" type="datetimerange"
						value-format="YYYY-MM-DD HH:mm:ss" start-placeholder="会员卡生效日期" end-placeholder="会员卡失效日期" />
				</el-form-item>

				<el-form-item label="增加时长(天)" prop="timeDays">
					<el-input type="number" v-model="MemberGiftDurationForm.timeDays" placeholder="请输入增加时长"
						@input="handleTimeDaysChange" />
				</el-form-item>

				<el-form-item label="增加后会员卡失效日期" prop="expiryTime">
					<el-date-picker disabled v-model="MemberGiftDurationForm.expiryTime"
						value-format="YYYY-MM-DD HH:mm:ss" type="date" :size="size" />
				</el-form-item>


				<el-form-item label="类型" prop="memberCardServiceTimeTypeCode">
					<DatabaseSelect table-name="member_card_service_time_type"
						:condition="'code not in (\'AddBorrowNumber\',\'MemberRenew\')'" order
						v-model="MemberGiftDurationForm.memberCardServiceTimeTypeCode" placeholder="请选择类型" />
				</el-form-item>

				<el-form-item label="赠送类型" prop="memberCardServiceTimeGiftTypeCode">
					<DatabaseSelect table-name="member_card_service_time_gift_type" order
						v-model="MemberGiftDurationForm.memberCardServiceTimeGiftTypeCode" placeholder="请选择赠送类型" />
				</el-form-item>
				<el-form-item label="参加的活动名称" prop="readActivityId">
					<DatabaseSelect table-name="view_read_activity_member"
						:condition="'member_id='+MemberGiftDurationForm.memberId"
						v-model="MemberGiftDurationForm.readActivityId" valueFieldName="id"
						placeholder="参加的活动名称" />
				</el-form-item>

				<el-form-item label="备注" prop="timeRemark">
					<el-input v-model="MemberGiftDurationForm.timeRemark" type="textarea" placeholder="请输入备注" />
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="submitForm(MemberGiftDurationFormRef)"> 确定 </el-button>
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
	const msg = ref('赠送时长')
	const refreshList = inject("getDataList");
	const formSize = ref('default')
	const MemberGiftDurationFormRef = ref < FormInstance > ()
	const MemberGiftDurationForm = reactive({
		id: '',
		memberId: '',
		memberName: '',
		cardNo: '',
		memberCardDate: '',
		timeDays: '',
		memberCardServiceTimeTypeCode: '',
		memberCardServiceTimeGiftTypeCode: '',
		readActivityId: '',
		cost: '', // 没用到
		paymentMethodCode: '', // 没用到
		timeRemark: '',
		addTime: '',
		expiryTime: '',
		createTime: '',
		updateTime: '',
		expireDateText: ''
	})

	const MemberGiftDurationFormInit = reactive({
		id: '',
		memberId: '',
		memberName: '',
		cardNo: '',
		memberCardDate: '',
		timeDays: '',
		memberCardServiceTimeTypeCode: '',
		memberCardServiceTimeGiftTypeCode: '',
		readActivityId: '',
		cost: '', // 没用到
		paymentMethodCode: '', // 没用到
		timeRemark: '',
		addTime: '',
		expiryTime: '',
		createTime: '',
		updateTime: '',
		expireDateText: ''
	})


	const rules = reactive < FormRules > ({
		timeDays: [{
			required: true,
			message: '增加时长不允许为空',
			trigger: 'change',
		}, ],
	})

	// 监听输入框变化的函数
	const handleTimeDaysChange = (day) => {
		if (day === '') {
			MemberGiftDurationForm.expiryTime = '';
			return
		}
		MemberGiftDurationForm.expiryTime = util.getAddDays(MemberGiftDurationForm.expireDateText, day);
	};

	const submitForm = async (formEl: FormInstance | undefined) => {
		if (!formEl) return
		await formEl.validate((valid, fields) => {
			if (valid) {
				let param = {
					memberId: MemberGiftDurationForm.memberId,
					days: MemberGiftDurationForm.timeDays,
					memberCardServiceTimeTypeCode: MemberGiftDurationForm
						.memberCardServiceTimeTypeCode,
					memberCardServiceTimeGiftTypeCode: MemberGiftDurationForm
						.memberCardServiceTimeGiftTypeCode,
					expiryTime: MemberGiftDurationForm.expiryTime,
					remark: MemberGiftDurationForm.timeRemark,
					createTime: util.getFormatTime(new Date())
				}
				common.call("AddMemberCardServiceTimeLog", param, function(result) {
					common.call("UpdateMemberCard", {
						id: MemberGiftDurationForm.id,
						expireDate: MemberGiftDurationForm.expiryTime
					}, function(result) {
						dialogFormVisible.value = false
						refreshList();
					}, function(result) {
						console.log("会员赠送时长失败");
					})
				}, function(result) {
					console.log("会员赠送时长失败");
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
			this.$refs.MemberGiftDurationFormRef.clearValidate();
		})
		// 会员生效/失效日期回显
		data.memberCardDate = [data.effectiveDateText, data.expireDateText];
		// 表单渲染
		Object.assign(MemberGiftDurationForm, MemberGiftDurationFormInit)
		Object.assign(MemberGiftDurationForm, data)
	}

	defineExpose({
		init
	})
</script>

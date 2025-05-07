<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600"
			draggable overflow>

			<el-form ref="MemberRenewFormRef" :model="MemberRenewForm" :rules="rules" :size="formSize" status-icon>
				<el-form-item style="background-color:  #FCF8E3;color: #95886D;">
					<div style="margin-left: 0.3125rem;">
						<p>增加时长规则</p>
						<p>1. 会员卡未到期时，到期时间向后顺延。</p>
						<p>2. 会员卡已到期时，以当天时间向后顺延。</p>
					</div>
				</el-form-item>
				<el-form-item label="会员姓名" prop="memberName">
					<el-input disabled v-model="MemberRenewForm.memberName" />
				</el-form-item>

				<el-form-item label="会员卡号" prop="cardNo">
					<el-input disabled v-model="MemberRenewForm.cardNo" />
				</el-form-item>

				<el-form-item label="会员卡期限">
					<el-date-picker disabled v-model="MemberRenewForm.memberCardDate" type="datetimerange"
						value-format="YYYY-MM-DD HH:mm:ss" start-placeholder="会员卡生效日期" end-placeholder="会员卡失效日期" />
				</el-form-item>

				<el-form-item label="活动类型" prop="activityTypeCode">
					<el-select v-model="MemberRenewForm.activityTypeCode" placeholder="请选择活动类型" filterable
						@change="activityChanged">
						<el-option v-for="item in ActivityData" :key="item.id" :label="item.name" :value="item.id" />
					</el-select>
				</el-form-item>

				<el-form-item label="续费价格" prop="renewalPrice">
					<el-input disabled v-model="MemberRenewForm.renewalPrice" placeholder="请输入续费价格" />
				</el-form-item>

				<el-form-item label="购买天数" prop="buyDays">
					<el-input disabled v-model="MemberRenewForm.buyDays" placeholder="请输入购买天数" />
				</el-form-item>

				<el-form-item label="赠送天数" prop="giftDays">
					<el-input disabled v-model="MemberRenewForm.giftDays" placeholder="请输入赠送天数" />
				</el-form-item>

				<el-form-item label="可借本数" prop="loanableNum">
					<el-input disabled v-model="MemberRenewForm.loanableNum" placeholder="请输入可借本数" />
				</el-form-item>

				<el-form-item label="新增积分" prop="giftScore">
					<el-input disabled v-model="MemberRenewForm.giftScore" placeholder="请输入新增积分" />
				</el-form-item>

				<el-form-item label="赠送停卡数量" prop="giftStopCardQuantity">
					<el-input disabled v-model="MemberRenewForm.giftStopCardQuantity" placeholder="赠送停卡数量" />
				</el-form-item>

				<el-form-item label="到期时间" prop="expireDate">
					<el-date-picker disabled v-model="MemberRenewForm.expireDate" value-format="YYYY-MM-DD HH:mm:ss"
						type="date" placeholder="请选择到期时间" :size="size" />
				</el-form-item>

				<el-form-item label="支付方式" prop="renewPaymentMethodCode">
					<DatabaseSelect table-name="payment_method" v-model="MemberRenewForm.renewPaymentMethodCode"
						placeholder="请选择支付方式" />
				</el-form-item>

				<el-form-item label="办理人" prop="renewEmployeeId">
					<DatabaseSelect table-name="company_user" :condition="'company_id=' + companyId"
						v-model="MemberRenewForm.renewEmployeeId" valueFieldName="id" placeholder="请选择办理人" />
				</el-form-item>

				<el-form-item label="备注" prop="renewRemark">
					<el-input v-model="MemberRenewForm.renewRemark" type="textarea" placeholder="请输入备注" />
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="submitForm(MemberRenewFormRef)" :loading="isLoading"> 确定 </el-button>
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
	FormRules
} from 'element-plus'
import common from '@/js/common'
import util from '@/js/util'

const dialogFormVisible = ref(false)
const msg = ref('会员续费')
const refreshList = inject("getDataList");
const formSize = ref('default')
const companyId = common.getLocalStorage("companyId")
const isLoading = ref(false);

const MemberRenewFormRef = ref<FormInstance>()
const MemberRenewForm = reactive({
	id: '',
	companyId: '',
	memberId: '',
	memberName: '',
	cardNo: '',
	memberCardDate: '',
	activityTypeCode: '',
	renewalPrice: '',
	buyDays: '',
	giftDays: '',
	loanableNum: '',
	expireDate: '',
	renewPaymentMethodCode: '',
	renewEmployeeId: '',
	renewRemark: '',
	activityId: '',
	membershipAmount: '',
	createTime: '',
	updateTime: '',
	giftScore: '',
	giftStopCardQuantity: ''
})

const MemberRenewFormInit = reactive({
	id: '',
	companyId: '',
	memberId: '',
	memberName: '',
	cardNo: '',
	memberCardDate: '',
	activityTypeCode: '',
	renewalPrice: '',
	buyDays: '',
	giftDays: '',
	loanableNum: '',
	expireDate: '',
	renewPaymentMethodCode: '',
	renewEmployeeId: '',
	renewRemark: '',
	activityId: '',
	membershipAmount: '',
	createTime: '',
	updateTime: '',
	giftScore: '',
	giftStopCardQuantity: ''
})


const rules = reactive<FormRules>({
	activityTypeCode: [{
		required: true,
		message: '活动类型不允许为空',
		trigger: 'change',
	},],
	renewPaymentMethodCode: [{
		required: true,
		message: '支付方式不允许为空',
		trigger: 'change',
	},],
	renewEmployeeId: [{
		required: true,
		message: '办理人不允许为空',
		trigger: 'change',
	},],
})

const submitForm = async (formEl: FormInstance | undefined) => {
	if (!formEl) return
	await formEl.validate((valid, fields) => {
		if (valid) {
			let memberRenewForm = {
				companyId: MemberRenewForm.companyId,
				memberId: MemberRenewForm.memberId,
				memberCardId: MemberRenewForm.id,
				activityId: MemberRenewForm.activityId,
				oldAmount: MemberRenewForm.membershipAmount,
				amount: MemberRenewForm.renewalPrice,
				newAmount: parseFloat(MemberRenewForm.membershipAmount) + parseFloat(
					MemberRenewForm.renewalPrice),
				paymentMethodCode: MemberRenewForm.renewPaymentMethodCode,
				employeeId: MemberRenewForm.renewEmployeeId,
				remark: MemberRenewForm.renewRemark,
				expireDate: MemberRenewForm.expireDate,
				buyDays: MemberRenewForm.buyDays,
				giftDays: MemberRenewForm.giftDays,
				loanableNum: MemberRenewForm.loanableNum,
				giftScore: MemberRenewForm.giftScore,
				giftStopCardQuantity: MemberRenewForm.giftStopCardQuantity
			}
			let data = filterNullProperties(memberRenewForm)
			isLoading.value = true
			common.call("AddMemberCardRenewLog", data, function (result) {
				setTimeout(() => {
					isLoading.value = false
				}, 200)
				dialogFormVisible.value = false
				refreshList();
			})
		}
	})
}

function filterNullProperties(data) {
        const filteredData = Object.entries(data).reduce((acc, [key, value]) => {
            // 如果值不为null，则保留这个属性
            if (value !== null) {
                acc[key] = value
            }
            return acc
        }, {})
        return filteredData
    }

/**
 * 续费活动选择监听事件
 * @param {Object} value 活动id
 */
function activityChanged(value) {
	common.call("GetActivity", {
		id: value
	}, function (result) {
		MemberRenewForm.activityId = result.data.id;
		MemberRenewForm.renewalPrice = result.data.renewalPrice;
		MemberRenewForm.buyDays = result.data.buyDays;
		MemberRenewForm.giftDays = result.data.giftDays;
		MemberRenewForm.loanableNum = result.data.loanableNum;
		MemberRenewForm.giftScore = result.data.giftScore || 0;
		MemberRenewForm.giftStopCardQuantity = result.data.giftStopCardQuantity || 0;
		// 计算到期时间（1、续费会员如未到期，则以到期日往后增加天数。2、续费会员已到期，则以当前日期往后加时长）
		let days = parseInt(MemberRenewForm.buyDays) + parseInt(MemberRenewForm.giftDays)
		if (new Date(MemberRenewForm.memberCardDate[1]) > new Date()) {
			MemberRenewForm.expireDate = util.getAddDays(MemberRenewForm.memberCardDate[1], days);
		} else {
			MemberRenewForm.expireDate = util.getAddDays(new Date(), days);
		}
	})
}

// 编辑页初始化
const ActivityData = ref([])

function init(data) {
	// 显示弹窗
	dialogFormVisible.value = true
	// 清除表单的校验规则
	this.$nextTick(() => {
		this.$refs.MemberRenewFormRef.clearValidate();
	})
	// 会员生效/失效日期回显
	data.memberCardDate = [data.effectiveDateText, data.expireDateText];
	// 表单渲染
	Object.assign(MemberRenewForm, MemberRenewFormInit)
	Object.assign(MemberRenewForm, data)

	// 活动类型下拉框列表渲染
	let param = {
		companyId: companyId,
		activityTypeCode: 'Renew'
	}
	common.call("GetActivityList", param, function (result) {
		ActivityData.value = result.data.dataList;
	})
}

defineExpose({
	init
})
</script>

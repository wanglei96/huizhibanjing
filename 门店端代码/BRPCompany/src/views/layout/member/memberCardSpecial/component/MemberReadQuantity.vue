<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="700"
			draggable overflow>
			<el-form ref="MemberReadQuantityFormRef" :model="MemberReadQuantityForm" :rules="rules" label-width="150px"
				class="demo-MemberReadQuantityForm" :size="formSize" status-icon>
				<el-form-item label="会员：" prop="memberName">
					<span>{{ MemberReadQuantityForm.memberName }}（{{ MemberReadQuantityForm.cardNo }}）</span>
				</el-form-item>
				<el-form-item label="目前可借">
					<span style="color: red;">{{ MemberReadQuantityForm.maxBorrowableQuantity }}</span>（基础）+ <span
						style="color: red;">{{ MemberReadQuantityForm.extendQuantity }}</span>（扩展）
				</el-form-item>

				<el-form-item label="增加借阅本数" prop="quantity">
					<el-input type="number" v-model="MemberReadQuantityForm.quantity" placeholder="请输入增加的借阅本数" />
				</el-form-item>

				<el-form-item label="积分类型" prop="memberBorrowQuantityTypeCode">
					<database-select table-name="member_borrow_quantity_log_type"
						v-model="MemberReadQuantityForm.memberBorrowQuantityTypeCode"></database-select>
				</el-form-item>
				<el-form-item label="有效期">
					<el-date-picker v-model="MemberReadQuantityForm.validDate" type="datetimerange"
						value-format="YYYY-MM-DD HH:mm:ss" start-placeholder="生效日期" end-placeholder="失效日期" />
				</el-form-item>
				<el-form-item label="参加的活动名称" prop="activityName">
					<el-input v-model="MemberReadQuantityForm.activityName" type="text" placeholder="请输入活动名称" />
				</el-form-item>
				<el-form-item label="增加借阅本数原因" prop="borrowRemark">
					<el-input v-model="MemberReadQuantityForm.borrowRemark" type="textarea" placeholder="请输入备注" />
				</el-form-item>

			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="submitForm(MemberReadQuantityFormRef)"> 确定 </el-button>
					<el-button @click="dialogFormVisible = false">取消</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>
<script lang="ts" setup>
import { inject, reactive, ref } from 'vue'
import type { FormInstance, FormRules, ElMessage } from 'element-plus'
import common from '@/js/common'
import util from '@/js/util'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'

const dialogFormVisible = ref(false)
const msg = ref('会员卡可借次数扩展')
const refreshList = inject("getDataList");
const formSize = ref('default')
const companyId = common.getLocalStorage("companyId")

const MemberReadQuantityFormRef = ref<FormInstance>()
const MemberReadQuantityForm = reactive({
	id: '',
	memberId: '',
	memberCardId: '',
	memberName: '',
	cardNo: '',
	quantity: '',
	borrowRemark: '',
	createTime: '',
	updateTime: '',
	activityName: '',
	memberBorrowQuantityTypeCode: '',
	validDate: [],
	maxBorrowableQuantity: '',
	extendQuantity: ''
})

const MemberReadQuantityFormInit = reactive({
	id: '',
	memberId: '',
	memberCardId: '',
	memberName: '',
	cardNo: '',
	quantity: '',
	borrowRemark: '',
	createTime: '',
	updateTime: '',
	maxBorrowableQuantity: '',
	extendQuantity: '',
	activityName: '',
	memberBorrowQuantityTypeCode: '',
	validDate: []
})


const rules = reactive<FormRules>({
	quantity: [
		{
			required: true,
			message: '增加借阅本数不允许为空',
			trigger: 'blur',
		},
	],
	memberBorrowQuantityTypeCode: [
		{
			required: true,
			message: '请选择类型',
			trigger: 'change',
		},
	],
	borrowRemark: [
		{
			required: true,
			message: '增加借阅本数原因不允许为空',
			trigger: 'blur',
		},
	],
})

const submitForm = async (formEl: FormInstance | undefined) => {
	if (!formEl) return
	await formEl.validate((valid, fields) => {
		if (valid) {
			if (MemberReadQuantityForm.validDate.length > 0) {
				MemberReadQuantityForm.startDate = MemberReadQuantityForm.validDate[0]
				MemberReadQuantityForm.endDate = MemberReadQuantityForm.validDate[1]
			}
			common.call("EditMemberBorrowQuantityLog", MemberReadQuantityForm, function (result) {
				dialogFormVisible.value = false
				refreshList();
				ElMessage({ message: "增加借阅本数成功", type: 'success', })
			}, function (result) {
				console.log("增加借阅本数失败");
				ElMessage.error("增加借阅本数失败")
			})
		}
	})
}

// 编辑页初始化
function init(data) {
	// 显示弹窗
	dialogFormVisible.value = true
	// 清除表单的校验规则
	/* this.$nextTick(() => {
	  this.$refs.MemberReadQuantityFormRef.clearValidate();
	}) */
	// 表单渲染
	Object.assign(MemberReadQuantityForm, MemberReadQuantityFormInit)
	Object.assign(MemberReadQuantityForm, data)
}

defineExpose({
	init
})
</script>
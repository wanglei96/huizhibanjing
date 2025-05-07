<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600"
			draggable overflow>
			<el-form ref="MemberCardBatchFormRef" :model="MemberCardBatchForm" :rules="rules" label-width="130px"
				class="demo-MemberCardBatchForm" :size="formSize" status-icon>
				<el-form-item label="批次名" prop="name">
					<el-input v-model="MemberCardBatchForm.name" placeholder="请输入批次名" />
				</el-form-item>
				<el-form-item label="批次编号" prop="batchNo">
					<el-input disabled v-model="MemberCardBatchForm.batchNo" />
				</el-form-item>
				<el-form-item label="批次显示颜色" prop="color">
					<el-color-picker v-model="MemberCardBatchForm.color" />
				</el-form-item>
				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="最大借阅次数" prop="borrowQuantity">
							<el-input v-model="MemberCardBatchForm.borrowQuantity" placeholder="请输入最大借阅次数"
								:disabled="MemberCardBatchForm.isUnlimited" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="无限次数" prop="isUnlimited">
							<el-radio-group v-model="MemberCardBatchForm.isUnlimited" @change="unlimitedChange">
								<el-radio :label="true" size="large">是</el-radio>
								<el-radio :label="false" size="large">否</el-radio>
							</el-radio-group>
						</el-form-item>
					</el-col>
				</el-row>
				<el-form-item label="是否能在线借阅" prop="isOnlineReading">
					<el-radio-group v-model="MemberCardBatchForm.isOnlineReading">
						<el-radio :label="true" size="large">是</el-radio>
						<el-radio :label="false" size="large">否</el-radio>
					</el-radio-group>
				</el-form-item>
				<!-- <el-form-item label="会员卡类型" prop="memberCardTypeId">
					<DatabaseSelect table-name="member_card_type" :condition="'company_id=' + companyId"
						v-model="MemberCardBatchForm.memberCardTypeId" valueFieldName="id" placeholder="请选择会员卡类型" />
				</el-form-item> -->
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button v-show="isShowBtn" type="primary" @click="submitForm(MemberCardBatchFormRef)"> 确定
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
	const refreshList = inject("getDataList");
	const companyId = common.getLocalStorage("companyId")

	const formSize = ref('default')
	const MemberCardBatchFormRef = ref < FormInstance > ()
	const MemberCardBatchForm = reactive({
		id: '',
		companyId: companyId,
		name: '',
		batchNo: '',
		color: '',
		borrowQuantity: '0',
		isOnlineReading: true,
		memberCardTypeId: '',
		createTime: '',
		updateTime: '',
		isUnlimited: true
	})
	const MemberCardBatchFormInit = reactive({
		id: '',
		companyId: companyId,
		name: '',
		batchNo: '',
		color: '',
		borrowQuantity: '0',
		isOnlineReading: true,
		memberCardTypeId: '',
		createTime: '',
		updateTime: '',
		isUnlimited: true
	})


	const rules = reactive < FormRules > ({
		name: [{
			required: true,
			message: '批次名不允许为空',
			trigger: 'change'
		}, ],
		color: [{
			required: true,
			message: '批次显示颜色不允许为空',
			trigger: 'change',
		}, ],
		borrowQuantity: [{
			required: true,
			message: '最大借阅次数不允许为空',
			trigger: 'change',
		}, ],
		isOnlineReading: [{
			required: true,
			message: '是否能在线借阅不允许为空',
			trigger: 'change',
		}, ],
		/* memberCardTypeId: [{
			required: true,
			message: '会员卡类型不允许为空',
			trigger: 'change',
		}, ], */
	})

	const submitForm = async (formEl: FormInstance | undefined) => {
		if (!formEl) return
		await formEl.validate((valid, fields) => {
			if (valid) {
				// id不为空修改，否则新增
				const serviceName = ref();
				if (MemberCardBatchForm.id == '') {
					serviceName.value = "AddMemberCardBatch";
					MemberCardBatchForm.createTime = util.getFormatTime(new Date());
				} else {
					serviceName.value = "UpdateMemberCardBatch";
					MemberCardBatchForm.updateTime = util.getFormatTime(new Date());
				}
				common.call(serviceName.value, MemberCardBatchForm, function(result) {
					dialogFormVisible.value = false
					refreshList();
				})
			}
		})
	}

	function unlimitedChange(val) {
		if (val) {
			MemberCardBatchForm.borrowQuantity = '0';
		}
	}

	// 编辑页初始化
	const isShowBtn = ref(true);

	function init(type, data) {
		// 显示弹窗
		dialogFormVisible.value = true
		// 清除表单的校验规则
		this.$nextTick(() => {
			this.$refs.MemberCardBatchFormRef.clearValidate();
		})
		// 隐藏确定按钮
		isShowBtn.value = true;

		if (type === 'add') {
			msg.value = '新建会员卡批次'
			Object.assign(MemberCardBatchForm, MemberCardBatchFormInit)

			// 生成批次编号
			common.call("GetMemberCardBatchNo", {
				companyId: companyId
			}, function(result) {
				MemberCardBatchForm.batchNo = result.data;
			}, function(result) {
				console.log("会员卡批次编号获取失败");
			})

		} else {
			if (type === 'edit') {
				msg.value = '编辑会员卡批次'
			} else {
				msg.value = '查看会员卡批次'
				isShowBtn.value = false;
			}
			// 表单渲染
			Object.assign(MemberCardBatchForm, data)
			MemberCardBatchForm.createTime = data.createTimeText
		}
	}
	defineExpose({
		init
	})
</script>

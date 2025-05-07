<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600"
			draggable overflow>
			<el-form ref="MemberStopCardServiceFormRef" :model="MemberStopCardServiceForm" :rules="rules"
				:size="formSize" status-icon>
				<el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
					<el-tab-pane label="后台办理停卡" name="first">
						<el-form-item style="background-color:  #FCF8E3;color: #95886D;">
							<div style="margin-left: 0.3125rem;">
								<p>头部温馨提示：</p>
								<p>1、尽量引导会员在小程序（注意是小程序！）自主办理暂停；</p>
								<p>2、后台办理的暂停没有通知，办理时请告知会员；</p>
								<p>3、办理时请填写消耗的次数，填0时不消耗次数</p>
							</div>
						</el-form-item>
					</el-tab-pane>
					<el-tab-pane label="补办停卡" name="second">
						<el-form-item style="background-color:  #FCF8E3;color: #95886D;">
							<div style="margin-left: 0.3125rem;">
								<p>头部温馨提示：</p>
								<p>1、用来处理类似会员有一段时间未借书，也未办理暂停，但是想要补时间的问题。</p>
								<p>2、补办停卡多少天，会员卡时间就延长多少天。</p>
								<p>3、补办停卡的开始时间必须是在会员卡开卡时间之后。</p>
							</div>
						</el-form-item>
					</el-tab-pane>
					<el-tab-pane label="赠送停卡次数" name="third">
					</el-tab-pane>
				</el-tabs>
				<el-form-item label="会员姓名" prop="memberName">
					<el-input disabled v-model="MemberStopCardServiceForm.memberName" />
				</el-form-item>

				<el-form-item label="会员编号" prop="cardNo">
					<el-input disabled v-model="MemberStopCardServiceForm.cardNo" />
				</el-form-item>
				<el-form-item label="剩余停卡次数" prop="cardNo">
					<el-input disabled v-model="MemberStopCardServiceForm.stoppableCardQuantity" />
				</el-form-item>
				<div v-show="stopCardTab">
					<el-form-item label="时间范围" prop="stopDate">
						<el-date-picker v-model="MemberStopCardServiceForm.stopDate" value-format="YYYY-MM-DD HH:mm:ss"
							type="date" placeholder="请选择开始日期" :size="size" :disabled-date="disabledDate"
							@change="handleApplyTimeChange" />
						<el-date-picker  v-model="MemberStopCardServiceForm.stopEndDate"
							value-format="YYYY-MM-DD HH:mm:ss" type="date" :size="size" />
					</el-form-item>
					<el-form-item label="停卡天数" prop="stopDays">
						<el-input type="number" v-model="MemberStopCardServiceForm.stopDays" placeholder="请输入停卡天数"
							@input="handleStopDaysChange" />
					</el-form-item>

					<!-- <el-form-item label="结束时间" prop="stopEndDate">
						
					</el-form-item> -->
				</div>
				<el-form-item label="停卡次数" prop="stopCardNum">
					<el-input v-model="MemberStopCardServiceForm.stopCardNum" placeholder="请输入停卡次数" />
				</el-form-item>


				<el-form-item label="备注" prop="stopRemark">
					<el-input v-model="MemberStopCardServiceForm.stopRemark" type="textarea" placeholder="请输入备注" />
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="submitForm(MemberStopCardServiceFormRef)"> 确定 </el-button>
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
		TabsPaneContext
	} from 'element-plus'
	import common from '@/js/common'
	import util from '@/js/util'

	const dialogFormVisible = ref(false)
	const stopCardTab = ref(true)
	const msg = ref('停卡服务')
	const refreshList = inject("getDataList");
	const formSize = ref('default')
	const activeName = ref('first')

	const MemberStopCardServiceFormRef = ref < FormInstance > ()
	const MemberStopCardServiceForm = reactive({
		id: '',
		memberId: '',
		memberCardId: '',
		memberName: '',
		cardNo: '',
		stopEndDate: '',
		stopDays: '',
		stopDate: new Date(),
		stopCardNum: 30,
		stopRemark: '',
		createTime: '',
		updateTime: '',
		source: '',
		status: '',
		type: ''
	})

	const MemberStopCardServiceFormInit = reactive({
		id: '',
		memberId: '',
		memberCardId: '',
		memberName: '',
		cardNo: '',
		stopEndDate: '',
		stopDays: '',
		stopDate: new Date(),
		stopCardNum: '',
		stopRemark: '',
		createTime: '',
		updateTime: '',
		source: '',
		status: '',
		type: ''
	})


	const rules = reactive < FormRules > ({
		stopDate: [{
			required: true,
			message: '开始时间不允许为空',
			trigger: 'change',
		}, ],
		stopDays: [{
			required: true,
			message: '停卡天数不允许为空',
			trigger: 'change',
		}, ],
		stopEndDate: [{
			required: true,
			message: '结束时间不允许为空',
			trigger: 'change',
		}, ],
		stopCardNum: [{
			required: true,
			message: '停卡次数不允许为空',
			trigger: 'change',
		}, ],
	})

	const submitForm = async (formEl: FormInstance | undefined) => {
		if (!formEl) return

		await formEl.validate((valid, fields) => {
			if (MemberStopCardServiceForm.type == 'augment' && MemberStopCardServiceForm.stopCardNum !=
				"") {
				valid = true;
			}
			if (valid) {
				let params = {
					memberId: MemberStopCardServiceForm.memberId,
					memberCardId: MemberStopCardServiceForm.id,
					stopDate: MemberStopCardServiceForm.stopDate,
					days: MemberStopCardServiceForm.stopDays,
					stopCardNum: MemberStopCardServiceForm.stopCardNum,
					remark: MemberStopCardServiceForm.stopRemark,
					stopEndDate: MemberStopCardServiceForm.stopEndDate,
					source: MemberStopCardServiceForm.source,
					status: MemberStopCardServiceForm.status,
					type: MemberStopCardServiceForm.type
				}
				common.call("UpdateMemberCardStop", params, function(result) {
					dialogFormVisible.value = false
					console.log("会员卡停卡成功");
					refreshList();
				}, function(result) {
					ElMessage.error(result.errorMessage)
					console.log("会员卡停卡失败");
				})
			}
		})
	}

	// index 0为后台办理停卡(减少)  1为补办停卡(减少) 2为赠送停卡次数(增加)
	const handleClick = (tab: TabsPaneContext, event: Event) => {
		if (tab.index === '0') {
			stopCardTab.value = true;
			MemberStopCardServiceForm.status = 'transact';
			MemberStopCardServiceForm.type = 'decrease';
		} else if (tab.index === '1') {
			stopCardTab.value = true;
			MemberStopCardServiceForm.status = 'reissue';
			MemberStopCardServiceForm.type = 'decrease';
		} else {
			stopCardTab.value = false;
			MemberStopCardServiceForm.status = 'givestopcard';
			MemberStopCardServiceForm.type = 'augment';
		}
	}

	//禁用当前日期之前的日期
	function disabledDate(time) {
		//Date.now()是javascript中的内置函数，它返回自1970年1月1日00:00:00 UTC以来经过的毫秒数。
		//return time.getTime() < Date.now() - 8.64e7;
	}

	// 监听停卡天数事件
	function handleApplyTimeChange() {
		let days = MemberStopCardServiceForm.stopDays;
		if (days != '') {
			MemberStopCardServiceForm.stopEndDate = util.getAddDays(MemberStopCardServiceForm.stopDate, days);
		}
	}

	const handleStopDaysChange = (days) => {
		if (days === '') {
			MemberStopCardServiceForm.stopEndDate = '';
			return
		}
		MemberStopCardServiceForm.stopEndDate = util.getAddDays(MemberStopCardServiceForm.stopDate, days);
	};
	
	// 编辑页初始化
	function init(data) {
		// 显示弹窗
		dialogFormVisible.value = true
		// 清除表单的校验规则
		this.$nextTick(() => {
			this.$refs.MemberStopCardServiceFormRef.clearValidate();
		})
		// 会员生效/失效日期回显
		data.memberCardDate = [data.effectiveDateText, data.expireDateText];
		// 表单渲染
		Object.assign(MemberStopCardServiceForm, MemberStopCardServiceFormInit)
		Object.assign(MemberStopCardServiceForm, data)
		MemberStopCardServiceForm.source = 'AdminStopCard';
		// 默认后台办理停卡的状态和类型
		MemberStopCardServiceForm.status = 'transact';
		MemberStopCardServiceForm.type = 'decrease';
		this.handleClick(0)
	}

	defineExpose({
		init
	})
</script>

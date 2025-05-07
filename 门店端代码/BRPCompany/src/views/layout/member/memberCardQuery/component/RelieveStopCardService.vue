<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600"
			draggable overflow>
			<el-form ref="MemberStopCardServiceFormRef" :model="MemberStopCardServiceForm" :rules="rules"
				:size="formSize" status-icon>
				<el-tabs v-model="activeName" class="demo-tabs">
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
				</el-tabs>

				<el-form-item label="停卡开始时间">
					<el-input disabled v-model="MemberStopCardServiceForm.stopDateText" />
				</el-form-item>

				<el-form-item label="停卡结束时间">
					<el-input disabled v-model="MemberStopCardServiceForm.stopEndDateText" />
				</el-form-item>

				<el-form-item label="停卡时长">
					<el-input disabled v-model="MemberStopCardServiceForm.days" />
				</el-form-item>

			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="danger" @click="cancelStopCardBtn()"> 提前解除停卡 </el-button>
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
import dayjs from "dayjs";
import { orderBy } from 'element-plus/es/components/table/src/util';

const dialogFormVisible = ref(false)
const stopCardTab = ref(true)
const msg = ref('停卡服务')
const refreshList = inject("getDataList");
const formSize = ref('default')
const activeName = ref('first')

const MemberStopCardServiceFormRef = ref<FormInstance>()
const MemberStopCardServiceForm = reactive({})



/* function cancelStopCardBtn(){
	let params = {
		id : MemberStopCardServiceForm.id,
		isRelieve : true
	}
	common.call("UpdateMemberCardDisableLog", params, function(result) {
		let params = {
			id : MemberStopCardServiceForm.memberCardId,
			isStopCard : 0
		}
		common.call("UpdateMemberCard", params, function(result) {
			dialogFormVisible.value = false
			ElMessage({ message: "解除停卡成功", type: 'success' })
			refreshList();
		}, function(result) {
			ElMessage.error("解除停卡失败")
		})
	}, function(result) {
		ElMessage.error("解除停卡失败")
	})
} */

function cancelStopCardBtn() {

	// 解析 stopDate
	const parsedStopDate = dayjs(MemberStopCardServiceForm.stopDateText);

	//获取停卡日期
	const stopEndDateText = MemberStopCardServiceForm.stopEndDateText;
	
	const stopEndDate = dayjs(stopEndDateText);

	// 获取当前日期
	const nowDate = dayjs();

	// 计算天数差异
	let daysDifference = nowDate.diff(parsedStopDate, 'day');

	if (stopEndDate.isBefore(nowDate)) {
		daysDifference = stopEndDate.diff(parsedStopDate, 'day');
	}

	let params = {
		memberCardDisableLogId: MemberStopCardServiceForm.id,
		offDays: daysDifference
	}
	common.call("RelieveStopMemberCard", params, function (result) {
		dialogFormVisible.value = false
		ElMessage({ message: "解除停卡成功", type: 'success' })
		refreshList();
	})
}

// 编辑页初始化
function init(data) {

	let params = {
		memberCardId: data.id,
		isRelieve: false,
		//condition: "status !='givestopcard' OR status is null"
		condition: "type='decrease'",
		orderBy: "id desc"
	}
	// 根据会员卡id获取未解除的停卡记录
	common.call("GetMemberCardDisableLogList", params, function (result) {
		if (result.data.dataList.length === 0) {
			ElMessage.error("该会员卡没有未解除的停卡记录")
			return;
		}
		let data = result.data.dataList[0];
		Object.assign(MemberStopCardServiceForm, data)
		// 显示弹窗
		dialogFormVisible.value = true
		console.log("获取停卡记录成功")
	})
}

defineExpose({
	init
})
</script>

<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600"
			draggable overflow>
			<el-form ref="MemberCardFormRef" :model="MemberCardForm" label-width="130px" class="demo-MemberCardForm"
				:size="formSize" status-icon>
				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="会员姓名" prop="memberName">
							<el-input v-model="MemberCardForm.memberName" disabled />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="会员卡号" prop="cardNo">
							<el-input v-model="MemberCardForm.cardNo" disabled />
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="地址信息" prop="address">
							<el-input v-model="MemberCardForm.address" placeholder="请输入地址信息" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="门禁" prop="entranceGuard">
							<el-input v-model="MemberCardForm.entranceGuard" />
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="配送大区" prop="deliverAreaId">
							<DatabaseSelect table-name="deliver_area" :condition="'company_id=' + companyId"
								@change="deliverAreaChange" v-model="MemberCardForm.deliverAreaId" valueFieldName="id"
								placeholder="请选择配送大区" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="配送线路" prop="deliverLineId">
							<DatabaseSelect table-name="view_deliver_line" :condition="'company_id=' + companyId"
								@change="deliverLineChange" v-model="MemberCardForm.deliverLineId" valueFieldName="id"
								placeholder="请选择配送线路" />
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="配送点" prop="deliverPlaceId">
							<DatabaseSelect table-name="view_deliver_place" :condition="'company_id=' + companyId"
								@change="deliverPlaceChange" v-model="MemberCardForm.deliverPlaceId" valueFieldName="id"
								placeholder="请选择配送点" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="配送员" prop="delivererId">
							<DatabaseSelect table-name="view_company_user"
								:condition="'company_id=' + companyId + ' and is_deliverer =1 '"
								v-model="MemberCardForm.delivererId" valueFieldName="id" placeholder="请选择配送员" />
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="距离" prop="distanceCode">
							<DatabaseSelect table-name="distance" v-model="MemberCardForm.distanceCode"
								placeholder="请选择距离" />
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="submitForm(MemberCardFormRef)"> 确定 </el-button>
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
		ElMessage
	} from 'element-plus'
	import common from '@/js/common'
	import util from '@/js/util'

	const dialogFormVisible = ref(false);
	const msg = ref('修改地址');
	const formSize = ref('default');
	const companyId = common.getLocalStorage("companyId")
	const refreshList = inject("getDataList");
	const MemberCardFormRef = ref < FormInstance > ()
	const MemberCardForm = reactive({
		id: '',
		memberId: '',
		memberName: '',
		cardNo: '',
		entranceGuard: '',
		address: '',
		deliverLineId: '',
		createTime: '',
		updateTime: '',
		deliverAreaId: '',
		delivererId: '',
		deliverPlaceId: '',
		distanceCode: '',
		referrerId: '',
		paymentMethodCode: ''
	})
	const MemberCardFormInit = reactive({
		id: '',
		memberId: '',
		memberName: '',
		cardNo: '',
		entranceGuard: '',
		address: '',
		deliverLineId: '',
		createTime: '',
		updateTime: '',
		deliverAreaId: '',
		delivererId: '',
		deliverPlaceId: '',
		distanceCode: '',
		referrerId: '',
		paymentMethodCode: ''
	})

	function submitForm() {
		let params = {
			id: MemberCardForm.memberId,
			address: MemberCardForm.address,
			entranceGuard: MemberCardForm.entranceGuard,
			deliverAreaId: MemberCardForm.deliverAreaId,
			deliverLineId: MemberCardForm.deliverLineId,
			deliverPlaceId: MemberCardForm.deliverPlaceId,
			delivererId: MemberCardForm.delivererId,
			distanceCode: MemberCardForm.distanceCode
		}
		common.call("UpdateMember", params, function(result) {
			dialogFormVisible.value = false
			refreshList();
			ElMessage({
				message: "修改成功",
				type: 'success'
			})
		}, function(result) {
			ElMessage.error('修改失败')
		})
	}


	// 配送大区监听动态查询配送线路
	function deliverAreaChange(deliverAreaId) {
		if (deliverAreaId == "") {
			return;
		}
		common.call(
			'GetDeliverLineList', {
				'deliverAreaId': deliverAreaId
			},
			function(result) {
				DeliverLineData.value = result.data.dataList;
				MemberCardForm.deliverLineId = ''
			},
			function(result) {
				console.log('配送线路获取失败')
			}
		)
	}

	// 配送线路监听动态查询配送点
	function deliverLineChange(deliverLineId) {
		if (deliverLineId == "") {
			return;
		}
		// 获取配送线路的所属大区
		common.call(
			'GetDeliverLineList', {
				'id': deliverLineId
			},
			function(result) {
				MemberCardForm.deliverAreaId = result.data.dataList[0].deliverAreaId;
			},
			function(result) {
				console.log('配送大区获取失败')
			}
		)
		// 获取配送线路下的配送点
		common.call(
			'GetDeliverPlaceList', {
				'deliverLineId': deliverLineId
			},
			function(result) {
				DeliverPlaceData.value = result.data.dataList;
				MemberCardForm.deliverPlaceId = '';
			},
			function(result) {
				console.log('配送点获取失败')
			}
		)
	}

	// 配送点监听动态查询配送大区，线路
	function deliverPlaceChange(deliverPlaceId) {
		if (deliverPlaceId == "") {
			return;
		}
		// 获取配送点的所属大区，线路
		common.call(
			'GetViewDeliverPlaceList', {
				'id': deliverPlaceId
			},
			function(result) {
				MemberCardForm.deliverAreaId = result.data.dataList[0].deliverAreaId;
				MemberCardForm.deliverLineId = result.data.dataList[0].deliverLineId;
			},
			function(result) {
				console.log('配送大区,线路获取失败')
			}
		)
	}

	// 编辑页初始化
	function init(data) {
		// 显示弹窗
		dialogFormVisible.value = true
		// 表单渲染
		Object.assign(MemberCardForm, MemberCardFormInit)
		Object.assign(MemberCardForm, data)
	}
	defineExpose({
		init
	})
</script>

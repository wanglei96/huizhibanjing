<script setup>
// 修改地址
import EditAddress from './component/EditAddress.vue'
// 书箱信息登记
import BookcaseInfoEdit from './component/BookcaseInfoEdit.vue'
// 信息修改
import MemberInfoEdit from './component/MemberInfoEdit.vue'
// 赠送时长
import MemberGiftDuration from './component/MemberGiftDuration.vue'
// 加可阅读数量
import MemberReadQuantity from './component/MemberReadQuantity.vue'
// 停卡服务
import MemberStopCardService from './component/MemberStopCardService.vue'
// 解除停卡
import RelieveStopCardService from './component/RelieveStopCardService.vue'
// 会员续费
import MemberRenew from './component/MemberRenew.vue'
// 会员增加积分
import MemberAddScore from './component/MemberAddScore.vue'
// 会员沟通记录
import MemberCommunicaLogView from './MemberCommunicaLogView.vue'
// 预登记会员信息列表
import SignInMemberInfoView from '../signInMemberInfo/SignInMemberInfoView.vue'
// 注销会员信息列表
import CancelMemberInfoView from '../signInMemberInfo/CancelMemberInfoView.vue'

import common from '@/js/common'
import util from '@/js/util'
import {
	reactive,
	ref,
	inject,
	onMounted
} from 'vue';
import {
	useRoute
} from 'vue-router'
const EditAddressRef = ref()
const BookcaseInfoEditRef = ref()
const MemberInfoEditRef = ref()
const MemberGiftDurationRef = ref()
const MemberReadQuantityRef = ref()
const MemberStopCardServiceRef = ref()
const RelieveStopCardServiceRef = ref()
const MemberRenewRef = ref()
const MemberAddScoreRef = ref()
const MemberCommunicaLogViewRef = ref()
const SignInMemberInfoViewRef = ref()
const CancelMemberInfoViewRef = ref()


// 分页相关
/* const currentPage = ref(1)
const pageSize = ref(10)
const recordCount = ref() */
const small = ref(false)
const background = ref(false)
const disabled = ref(false)


//当前页
const pageIndex = ref(0)
//总记录数
const recordCount = ref(0)
//总页数
const pageCount = ref(0)
//分页大小列表
const pageSizes = reactive([10, 20, 50, 100])
//每页大小
const pageSize = ref(pageSizes[0] || 10)

const companyId = common.getLocalStorage("companyId")
// 非特殊会员卡
const isSpecial = ref()

const isValid = ref()

const route = useRoute()
const memberCardIds = ref(route.query.memberCardIds);



// 获取当前地址
const url = new URL(window.location.href);

// 删除指定的参数
url.searchParams.delete('memberCardIds');

// 更新浏览器地址栏
window.history.replaceState({}, '', url.toString());
provide('getDataList', queryDataList);
// 首次页面加载获取列表
getDataList();

// 分页监听
/* const handleSizeChange = (val) => {
	getDataList();
}
const handleCurrentChange = (val) => {
	getDataList();
} */

//当前页
const currentPage = computed({
	get() {
		return pageIndex.value + 1
	},
	set(newValue) {
		pageIndex.value = newValue - 1
		getDataList(
			memberNameQuery.value,
			cardNoQuery.value,
			addressQuery.value,
			mobilePhoneQuery.value,
			memberCardBatchIdQuery.value,
			memberCardTypeIdQuery.value,
			isExpireQuery.value,
			isBookQuery.value,
			memberInfoCardStatusQuery.value,
			memberCardStatusCodeQuery.value,
			distanceCodeQuery.value,
			timeTypeQuery.value,
			timeQuery.value);
	}
})
//页面大小
const pageSizeComputed = computed({
	get() {
		return pageSize.value
	},
	set(newValue) {
		pageIndex.value = 0
		pageSize.value = newValue
		getDataList(
			memberNameQuery.value,
			cardNoQuery.value,
			addressQuery.value,
			mobilePhoneQuery.value,
			memberCardBatchIdQuery.value,
			memberCardTypeIdQuery.value,
			isExpireQuery.value,
			isBookQuery.value,
			memberInfoCardStatusQuery.value,
			memberCardStatusCodeQuery.value,
			distanceCodeQuery.value,
			timeTypeQuery.value,
			timeQuery.value);
	}
})

// 获取会员书箱列表
const tableData = ref([]);

function getDataList(memberNameQuery,
	cardNoQuery,
	addressQuery,
	mobilePhoneQuery,
	memberCardBatchIdQuery,
	memberCardTypeIdQuery,
	isExpireQuery,
	isBookQuery,
	memberInfoCardStatusQuery,
	memberCardStatusCodeQuery,
	distanceCodeQuery,
	timeTypeQuery,
	timeQuery) {
	let deliverPlaceId = route.query.deliverPlaceId;
	if (deliverPlaceId == undefined) {
		isSpecial.value = 0
	}
	let params = {
		memberName: memberNameQuery,
		cardNo: cardNoQuery,
		address: addressQuery,
		mobilePhone: mobilePhoneQuery,
		memberCardBatchId: memberCardBatchIdQuery,
		memberCardTypeId: memberCardTypeIdQuery,
		pageIndex: pageIndex.value,
		pageSize: pageSize.value,
		companyId: companyId,
		isSpecial: isSpecial.value,
		deliverPlaceId: route.query.deliverPlaceId,
		/* isExpire: isExpireQuery, */
		/* isBook: isBookQuery, */
		memberCardStatusCode: memberCardStatusCodeQuery,
		distanceCode: distanceCodeQuery,
		orderBy: 'employee_time desc',
		isCancel: 0
	}

	params.condition = memberCardIds.value ? "member_id is not null and id in (" + memberCardIds.value + " ) and disabled is not true " :
		'member_id is not null and disabled is not true '

	// 书箱状态查询判断
	if (isBookQuery === 'NoBookbox') {
		//params.isBook = 0;
		params.condition += " and bookbox_amount is null";
	} else if (isBookQuery === 'WaitReady') {
		params.memberBookboxStatusCode = 'WaitReady';
		params.condition += " and bookbox_amount is not null and member_bookbox_status_code = 'WaitReady'";
	} else if (isBookQuery === 'AlReady') {
		params.memberBookboxStatusCode = 'AlReady';
	} else if (isBookQuery === 'InstalledNoActivate') {
		params.memberBookboxStatusCode = 'Installed';
		params.isActivated = 0;
	} else if (isBookQuery === 'InstalledYesActivate') {
		params.memberBookboxStatusCode = 'Installed';
		params.isActivated = 1;
	}
	if (isExpireQuery === "0") {
		params.condition += " and expire_date >= CURDATE() ";
	} else if (isExpireQuery === "1") {
		params.condition += " and expire_date < CURDATE() ";
	} else if (isExpireQuery === "2") {
		params.condition += " AND DATEDIFF(expire_date, CURDATE()) > 0 and DATEDIFF(expire_date, CURDATE()) <= 30 ";
	} else if (isExpireQuery === "3") {
		params.condition += " AND DATEDIFF(expire_date, CURDATE()) > 0 and DATEDIFF(expire_date, CURDATE()) <= 60 ";
	} else if (isExpireQuery === "4") {
		params.condition += " AND DATEDIFF(expire_date, CURDATE()) > 0 and DATEDIFF(expire_date, CURDATE()) <= 90 ";
	}

	// 会员状态查询
	if (memberInfoCardStatusQuery === "StopCardIng") {
		params.condition += " and is_stop_card is true ";
	} else if (memberInfoCardStatusQuery === "Locked") {
		params.condition += " and member_card_status_code='Locked' ";
	} else if (memberInfoCardStatusQuery === "Normal") {
		params.condition += " and member_card_status_code='Normal' and is_stop_card is not true ";
	}

	if (timeTypeQuery == 'employeeTime') {
		if (timeQuery != undefined) {
			params.employeeTimeBegin = timeQuery[0];
			params.employeeTimeEnd = timeQuery[1];
		}
	} else if (timeTypeQuery == 'effectiveDate') {
		if (timeQuery != undefined) {
			params.effectiveDateBegin = timeQuery[0];
			params.effectiveDateEnd = timeQuery[1];
		}
	} else if (timeTypeQuery == 'expireDate') {
		if (timeQuery != undefined) {
			params.expireDateBegin = timeQuery[0];
			params.expireDateEnd = timeQuery[1];
		}
	} else if (timeTypeQuery == 'lockDate') {
		if (timeQuery != undefined) {
			params.lockDateBegin = timeQuery[0];
			params.lockDateEnd = timeQuery[1];
			params.memberCardStatusCode = 'Locked';
		}
	} else if (timeTypeQuery == 'lastCommunicaDate') {
		if (timeQuery != undefined) {
			params.lastCommunicaDateBegin = timeQuery[0];
			params.lastCommunicaDateEnd = timeQuery[1];
		}
	} else if (timeTypeQuery == 'birthday') {
		if (timeQuery != undefined) {
			params.birthdayBegin = timeQuery[0];
			params.birthdayEnd = timeQuery[1];
		}
	}

	common.call("GetViewMemberCardList", params, function (result) {
		tableData.value = result.data.dataList;
		/* pageSize.value = result.data.pageSize;
		recordCount.value = result.data.recordCount; */
		recordCount.value = result.data.recordCount;
		pageCount.value = result.data.pageCount;
	})

	// 获取预登记和注销会员数量
	common.call("GetSignInAndCancelMemberCount", { companyId: companyId }, function (result) {
		signCount.value = result.data.signCount;
		cancelCount.value = result.data.cancelCount;
	})
}

const signCount = ref(0);
const cancelCount = ref(0);
// 会员书箱列表条件查询
const memberNameQuery = ref();
const cardNoQuery = ref();
const addressQuery = ref();
const mobilePhoneQuery = ref();
const memberCardBatchIdQuery = ref();
const memberCardTypeIdQuery = ref();
const isExpireQuery = ref();
const isBookQuery = ref();
const memberInfoCardStatusQuery = ref();
const memberCardStatusCodeQuery = ref();
const distanceCodeQuery = ref();
const timeTypeQuery = ref();
const timeQuery = ref([]);

function queryDataList() {
	getDataList(
		memberNameQuery.value,
		cardNoQuery.value,
		addressQuery.value,
		mobilePhoneQuery.value,
		memberCardBatchIdQuery.value,
		memberCardTypeIdQuery.value,
		isExpireQuery.value,
		isBookQuery.value,
		memberInfoCardStatusQuery.value,
		memberCardStatusCodeQuery.value,
		distanceCodeQuery.value,
		timeTypeQuery.value,
		timeQuery.value);
}

// 查询条件重置
function resetQuery() {
	memberNameQuery.value = '';
	cardNoQuery.value = '';
	addressQuery.value = '';
	mobilePhoneQuery.value = '';
	memberCardBatchIdQuery.value = '';
	memberCardTypeIdQuery.value = '';
	isExpireQuery.value = '';
	isBookQuery.value = '';
	memberInfoCardStatusQuery.value = '';
	memberCardStatusCodeQuery.value = '';
	timeTypeQuery.value = '';
	timeQuery.value = '';
	distanceCodeQuery.value = '';
	memberCardIds.value = '';
}

// 锁定账号监听
function memberCardStatusChange(data) {
	let params = {
		id: data.id,
		memberCardStatusCode: data.memberCardStatusCode
	}
	if (data.memberCardStatusCode == 'Locked') {
		params.lockDate = util.getFormatTime(new Date());
	}
	common.call("UpdateMemberCard", params, function (result) {
		getDataList();
	})
}

// 会员书箱安装事件
function MemberBookboxStatusClick(data) {
	let memberId = data.memberId;
	if (data.memberBookboxStatusCode === 'Installed') {
		return;
	}
	ElMessageBox.confirm("确定修改书箱安装状态吗？", "温馨提示", {
		confirmButtonText: "确认",
		cancelButtonText: "取消",
		type: "warning"
	})
		.then(() => {
			if (data.memberBookboxStatusCode === 'WaitReady') { // 等待领取
				let params = {
					code: "Bookbox",
					memberId: memberId,
					companyId: companyId
				}
				common.call("MemberGetBookBox", params, function (result) {
					//修改书箱状态
					updateMemberBookBoxStatus(memberId, 'AlReady');
				})
			} else if (data.memberBookboxStatusCode === 'AlReady') { // 已领取
				// 已安装
				updateMemberBookBoxStatus(memberId, 'Installed');
			} else {
				updateMemberBookBoxStatus(memberId, 'WaitReady');

			}
		})
		.catch(() => {

		});
}

function updateMemberBookBoxStatus(memberId, memberBookboxStatusCode) {
	let params = {
		id: memberId,
		memberBookboxStatusCode: memberBookboxStatusCode
	}
	common.call("UpdateMember", params, function (result) {
		//getDataList();
		getDataList(
			memberNameQuery.value,
			cardNoQuery.value,
			addressQuery.value,
			mobilePhoneQuery.value,
			memberCardBatchIdQuery.value,
			memberCardTypeIdQuery.value,
			isExpireQuery.value,
			isBookQuery.value,
			memberInfoCardStatusQuery.value,
			memberCardStatusCodeQuery.value,
			distanceCodeQuery.value,
			timeTypeQuery.value,
			timeQuery.value);
	})
}

// 会员书箱激活事件
function MemberBookboxActivateClick(data) {
	// 如果书箱状态是已激活不可以修改
	if (data.isActivated) {
		return;
	}
	// 校验书箱是否是已安装状态并且配送信息是否填写完整
	if (data.memberBookboxStatusCode != 'Installed') {
		ElMessage.error('当前书箱未安装，暂无法激活');
		return;
	}
	if (data.deliverAreaId == null || data.deliverLineId == null ||
		data.deliverPlaceId == null || data.distanceCode == null) {
		ElMessage.error('请在修改地址中完善配送信息');
		return;
	}
	ElMessageBox.confirm("确定修改书箱激活状态吗？", "温馨提示", {
		confirmButtonText: "确认",
		cancelButtonText: "取消",
		type: "warning"
	})
		.then(() => {
			if (data.isActivated) {
				data.isActivated = false
			} else {
				data.isActivated = true
			}
			let params = {
				id: data.memberId,
				isActivated: data.isActivated
			}
			common.call("UpdateMember", params, function (result) {
				getDataList();
			}, function (result) {
				console.log("会员书箱激活状态修改失败");
			})
		})
		.catch((error) => {
			throw new Error(error.message);
		});
}
const isLoading = ref(false);
function exportDataList() {
	let data = {
		companyId: companyId,
		memberName: memberNameQuery.value,
		cardNo: cardNoQuery.value,
		address: addressQuery.value,
		mobilePhone: mobilePhoneQuery.value,
		memberCardBatchId: memberCardBatchIdQuery.value,
		memberCardTypeId: memberCardTypeIdQuery.value,
		isExpire: isExpireQuery.value,
		isBook: isBookQuery.value,
		memberCardStatusCode: memberCardStatusCodeQuery.value,
		distanceCode: distanceCodeQuery.value,
		isSpecial: isSpecial.value,
		memberCardIds: memberCardIds.value
	}

	if (timeTypeQuery.value == 'employeeTime') {
		if (timeQuery) {
			data.employeeTimeBegin = timeQuery.value[0];
			data.employeeTimeEnd = timeQuery.value[1];
		}
	} else if (timeTypeQuery.value == 'effectiveDate') {
		if (timeQuery) {
			data.effectiveDateBegin = timeQuery.value[0];
			data.effectiveDateEnd = timeQuery.value[1];
		}
	} else if (timeTypeQuery.value == 'expireDate') {
		if (timeQuery) {
			data.expireDateBegin = timeQuery.value[0];
			data.expireDateEnd = timeQuery.value[1];
		}
	} else if (timeTypeQuery.value == 'lockDate') {
		if (timeQuery) {
			data.lockDateBegin = timeQuery.value[0];
			data.lockDateEnd = timeQuery.value[1];
			data.memberCardStatusCode = 'Locked';
		}
	} else if (timeTypeQuery.value == 'lastCommunicaDate') {
		if (timeQuery) {
			data.lastCommunicaDateBegin = timeQuery.value[0];
			data.lastCommunicaDateEnd = timeQuery.value[1];
		}
	} else if (timeTypeQuery.value == 'birthday') {
		if (timeQuery) {
			data.birthdayBegin = timeQuery.value[0];
			data.birthdayEnd = timeQuery.value[1];
		}
	}
	data.exportName = 'MemberInfoList';
	data.pageSize = 10000000;
	isLoading.value = true
	common.call('Export', data, (res) => {
		ElMessage.success("导出成功");
		setTimeout(() => {
			isLoading.value = false
		}, 300)
		let url = res.adminRootUrl + res.data.filePath;
		window.open(url)
	})
}
</script>
<template>
	<div>
		<EditAddress ref="EditAddressRef" />
		<BookcaseInfoEdit ref="BookcaseInfoEditRef" />
		<MemberInfoEdit ref="MemberInfoEditRef" />
		<MemberGiftDuration ref="MemberGiftDurationRef" />
		<MemberReadQuantity ref="MemberReadQuantityRef" />
		<MemberStopCardService ref="MemberStopCardServiceRef" />
		<RelieveStopCardService ref="RelieveStopCardServiceRef" />
		<MemberRenew ref="MemberRenewRef" />
		<MemberAddScore ref="MemberAddScoreRef" />
		<MemberCommunicaLogView ref="MemberCommunicaLogViewRef" />
		<SignInMemberInfoView ref="SignInMemberInfoViewRef" />
		<CancelMemberInfoView ref="CancelMemberInfoViewRef" />


		<el-card shadow="never" class="user-search-card">
			<el-form :inline="true" class="user-form" label-width="100px">
				<el-row type="flex">
					<el-col :xs="24" :sm="12" :md="8" :lg="6">
						<el-form-item label="姓名">
							<el-input placeholder="请输入" v-model="memberNameQuery"
								@keydown.enter.prevent=queryDataList()></el-input>
						</el-form-item>
					</el-col>
					<el-col :xs="24" :sm="12" :md="8" :lg="6">
						<el-form-item label="卡号">
							<el-input placeholder="请输入" v-model="cardNoQuery"
								@keydown.enter.prevent=queryDataList()></el-input>
						</el-form-item>
					</el-col>
					<el-col :xs="24" :sm="12" :md="8" :lg="6">
						<el-form-item label="地址">
							<el-input placeholder="请输入" v-model="addressQuery"
								@keydown.enter.prevent=queryDataList()></el-input>
						</el-form-item>
					</el-col>
					<el-col :xs="24" :sm="12" :md="8" :lg="6">
						<el-form-item label="电话">
							<el-input placeholder="请输入" v-model="mobilePhoneQuery"
								@keydown.enter.prevent=queryDataList()></el-input>
						</el-form-item>
					</el-col>
					<el-col :xs="24" :sm="12" :md="8" :lg="6">
						<el-form-item label="会员卡批次">
							<DatabaseSelect table-name="member_card_batch" :condition="'company_id=' + companyId"
								v-model="memberCardBatchIdQuery" valueFieldName="id" placeholder="请选择会员卡批次" />
						</el-form-item>
					</el-col>
					<el-col :xs="24" :sm="12" :md="8" :lg="6">
						<el-form-item label="会员卡类型">
							<DatabaseSelect table-name="member_card_type" :condition="'company_id=' + companyId"
								v-model="memberCardTypeIdQuery" valueFieldName="id" placeholder="请选择会员卡类型" />
						</el-form-item>
					</el-col>
					<el-col :xs="24" :sm="12" :md="8" :lg="6">
						<el-form-item label="到期时间">
							<el-select placeholder="请选择" v-model="isExpireQuery" filterable>
								<el-option label="已到期" value="1"></el-option>
								<el-option label="30天内到期" value="2"></el-option>
								<el-option label="60天内到期" value="3"></el-option>
								<el-option label="90天内到期" value="4"></el-option>
								<el-option label="未到期" value="0"></el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :xs="24" :sm="12" :md="8" :lg="6">
						<!-- <el-form-item label="是否有书箱">
							<el-select placeholder="请选择" v-model="isBookQuery" filterable>
								<el-option label="是" value="1"></el-option>
								<el-option label="否" value="0"></el-option>
							</el-select>
						</el-form-item> -->
						<el-form-item label="书箱状态">
							<el-select placeholder="请选择" v-model="isBookQuery" filterable>
								<el-option label="无书箱" value="NoBookbox"></el-option>
								<el-option label="等待领取" value="WaitReady"></el-option>
								<el-option label="等待安装" value="AlReady"></el-option>
								<el-option label="已安装未激活" value="InstalledNoActivate"></el-option>
								<el-option label="已安装已激活" value="InstalledYesActivate"></el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :xs="24" :sm="12" :md="8" :lg="6">
						<el-form-item label="账号状态">
							<el-select placeholder="请选择" v-model="memberInfoCardStatusQuery" filterable>
								<el-option label="停卡中" value="StopCardIng"></el-option>
								<el-option label="锁定中" value="Locked"></el-option>
								<el-option label="正常" value="Normal"></el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<!-- <el-col :xs="24" :sm="12" :md="8" :lg="6">
						<el-form-item label="账号状态">
							<DatabaseSelect table-name="member_card_status" order v-model="memberCardStatusCodeQuery"
								placeholder="请选择账号状态" />
						</el-form-item>
					</el-col> -->
					<el-col :xs="24" :sm="12" :md="8" :lg="6">
						<el-form-item label="距离">
							<DatabaseSelect table-name="distance" v-model="distanceCodeQuery" placeholder="请选择距离" />
						</el-form-item>
					</el-col>
					<el-row>
						<el-col :xs="24" :sm="24" :md="8" :lg="10">
							<el-select placeholder="请选择" v-model="timeTypeQuery" clearable filterable>
								<el-option label="办理时间" value="employeeTime"></el-option>
								<el-option label="开卡时间" value="effectiveDate"></el-option>
								<el-option label="到期时间" value="expireDate"></el-option>
								<el-option label="锁定时间" value="lockDate"></el-option>
								<el-option label="沟通时间" value="lastCommunicaDate"></el-option>
								<el-option label="会员生日" value="birthday"></el-option>
							</el-select>
						</el-col>
						<el-col :xs="24" :sm="24" :md="12" :lg="12">
							<el-date-picker v-model="timeQuery" type="daterange" start-placeholder="开始日期"
								end-placeholder="结束日期" value-format="YYYY-MM-DD" :validate-event="true" />
						</el-col>
					</el-row>

				</el-row>
				<el-row>
					<el-col :xs="24" :sm="24" :md="14" :lg="14">
						<el-form-item>
							<el-button type="primary" @click="queryDataList()">查询</el-button>
							<el-button @click="resetQuery()">重置</el-button>
							<el-button type="success" @click="exportDataList()" :loading="isLoading">导出Excel</el-button>
						</el-form-item>
					</el-col>
					<el-col :xs="24" :sm="24" :md="10" :lg="10">
						<el-form-item>
							<el-button type="warning" @click="SignInMemberInfoViewRef.init()">预登记信息列表{{ '(' + signCount
				+ ')' }}</el-button>
							<el-button type="danger" @click="CancelMemberInfoViewRef.init()">注销会员列表{{ '(' + cancelCount
				+ ')' }}</el-button>
						</el-form-item>
					</el-col>
				</el-row>

			</el-form>
		</el-card>
		<el-pagination layout="total" :total="recordCount" />
		<el-card shadow="never">
			<div class="list-container">
				<div class="list-item" v-for="item in tableData">
					<div class="list-item-title">
						<div class="list-item-title_1">会员卡号</div>
						<!-- <div class="list-item-title_2" v-copy="item.cardNo"> 会员卡号: {{ item.cardNo }}</div> -->
						<div class="list-item-title_2"> 会员卡号: {{ item.cardNo }}</div>
						<div class="list-item-title_2">办理时间:{{ util.simpleDateFormat(item.employeeTimeText) }}</div>
						<div class="list-item-title_2">
							开卡时间:{{ util.simpleDateFormat(item.effectiveDateText) }}
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							到期时间:{{ item.expireDateText ? util.simpleDateFormat(item.expireDateText) : "" }}
						</div>
						<div class="list-item-title_2">会员卡剩余天数:{{ item.diffDay }}</div>
						<div class="list-item-title_2">
							<router-link
								:to="{ path: '/score/BatchUpdateMemberScoreManagement', query: { memberId: item.memberId } }">
								会员积分:{{ item.memberScore }}
							</router-link>
						</div>
						<div class="list-item-title_2">
							是否启用
							<el-switch size="small" v-model="item.memberCardStatusCode" active-value="Normal"
								inactive-value="Locked" @change="memberCardStatusChange(item)" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span v-if="item.lockDate != null && item.memberCardStatusCode == 'Locked'">锁定时间:{{
				util.simpleDateFormat(item.lockDateText) }}</span>
							<br>
							<span class="list-item-title_3" v-if="item.isStopCard != null && item.isStopCard == '1'"
								style="color: #F56C6C;">停卡中</span>
						</div>
					</div>
					<div class="list-item-title">
						<div class="list-item-title_1">会员信息</div>
						<div class="list-item-title_2">会员姓名:{{ item.memberName }}</div>
						<div class="list-item-title_2">性别:
							<span v-if="item.gender === true">男</span>
							<span v-else-if="item.gender === false">女</span>
						</div>
						<div class="list-item-title_2">会员生日:{{
				item.birthdayText ? util.simpleDateFormat(item.birthdayText) : "" }}</div>
						<div class="list-item-title_2">学校:{{ item.school }}</div>
						<div class="list-item-title_2">年级:{{ item.gradeName }}</div>
						<div class="list-item-title_2">班级:{{ item.className }}</div>
						<div class="list-item-title_2">会员卡类型:{{ item.memberCardTypeName }}</div>
					</div>
					<div class="list-item-title">
						<div class="list-item-title_1">会员收费</div>
						<div class="list-item-title_2">会员费:{{ item.membershipAmount }}</div>
						<div class="list-item-title_2">书箱费:{{ item.bookboxAmount }}</div>
						<div class="list-item-title_2">押金:{{ item.securityDepositPrice }}</div>
						<div class="list-item-title_2">办理人:{{ item.employeeName }}</div><!-- renewEmployeeName -->
						<div class="list-item-title_2">支付方式:{{ item.paymentMethodName }}</div>
						<div class="list-item-title_2">续费次数:{{ item.renewNum }}</div>
						<div class="list-item-title_2">备注:{{ item.remark }}</div><!-- renewRemark -->
					</div>
					<div class="list-item-title">
						<div class="list-item-title_1">地址&电话</div>
						<div class="list-item-title_2">地址:{{ item.address }}</div>
						<div class="list-item-title_2">电话:{{ item.mobilePhone }}</div>
						<div class="list-item-title_2">门禁:{{ item.entranceGuard }}</div>
						<div>
							<el-link class="list-item-title_3" @click="EditAddressRef.init(item)">修改地址</el-link>
						</div>
					</div>
					<div class="list-item-title">
						<div class="list-item-title_1">书箱</div>
						<!-- 书箱信息登记之后，才有书箱领取激活等操作 -->
						<div v-if="item.bookbox_amount != null">
							<div>
								<el-link @click="MemberBookboxStatusClick(item)">
									<span class="list-item-title_2" v-if="item.memberBookboxStatusCode === 'WaitReady'"
										style="color: #E6A23C;">{{ item.memberBookboxStatusName }}</span>
									<span class="list-item-title_2" v-if="item.memberBookboxStatusCode === 'AlReady'"
										style="color: #409EFF;">{{ item.memberBookboxStatusName }}</span>
									<span class="list-item-title_2" v-if="item.memberBookboxStatusCode === 'Installed'"
										style="color: #67C23A;">{{ item.memberBookboxStatusName }}</span>
									<span class="list-item-title_2" v-if="item.memberBookboxStatusCode === 'Cancel'"
										style="color: #F56C6C;">{{ item.memberBookboxStatusName }}</span>
								</el-link>
							</div>

							<div>
								<el-link @click="MemberBookboxActivateClick(item)">
									<span class="list-item-title_2" v-if="item.isActivated === false"
										style="color: #F56C6C;">未激活</span>
									<span class="list-item-title_2" v-else-if="item.isActivated === true"
										style="color: #67C23A;">已激活</span>
								</el-link>
							</div>
						</div>
						<div>
							<el-link class="list-item-title_3" @click="BookcaseInfoEditRef.init(item)">书箱信息登记</el-link>

						</div>
						<div>
							<el-link class="list-item-title_3" @click="MemberCommunicaLogViewRef.init(item)">沟通记录
							</el-link>
						</div>
						<div class="list-item-title_3" style="color: #E6A23C">
							最后一次沟通时间:{{ item.lastCommunicaDateText ? util.simpleDateFormat(item.lastCommunicaDateText) :
				""
							}}
						</div>
					</div>
					<div class="list-item-title">
						<div>
							<el-link class="list-item-title_3" @click="MemberInfoEditRef.init(item)">信息修改</el-link>
						</div>
						<div>
							<el-link class="list-item-title_3" @click="MemberGiftDurationRef.init(item)">赠送时长</el-link>
						</div>
						<div>
							<el-link class="list-item-title_3" @click="MemberReadQuantityRef.init(item)">加可借数量</el-link>
						</div>
						<div>
							<el-link v-if="item.isStopCard != true" class="list-item-title_3"
								@click="MemberStopCardServiceRef.init(item)">停卡服务
							</el-link>
							<el-link v-if="item.isStopCard == true" class="list-item-title_3"
								@click="RelieveStopCardServiceRef.init(item)">解除停卡
							</el-link>
						</div>
						<div>
							<el-link class="list-item-title_3" @click="MemberRenewRef.init(item)">会员续费</el-link>
						</div>
						<div>
							<el-link class="list-item-title_3" @click="MemberAddScoreRef.init(item)">积分管理</el-link>
						</div>
					</div>
				</div>
			</div>
			<!-- <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSizeComputed"
				:page-sizes="pageSizes" :small="small" :disabled="disabled" :background="background"
				:page-count="pageCount" layout="total, sizes, prev, pager, next, jumper" :total="recordCount"
				style="margin-top: 20px" /> -->

			<el-pagination v-model:current-page="currentPage" v-model:page-size="pageSizeComputed"
				:page-sizes="pageSizes" :page-count="pageCount" :layout="'total, sizes, prev, pager, next, jumper'"
				:total="recordCount" style="margin-top: 20px" />
		</el-card>
	</div>
</template>

<style lang="scss" scoped>
.card-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.el-input {
	width: 200px;
}

.el-select {
	width: 200px;
}

.el-form-item {
	margin-bottom: 0px;
}

.user-search-card {
	margin-bottom: 15px;

	.user-form {
		.el-form-item {
			margin-bottom: 10px;
		}
	}
}

.task {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;

	.task_desc {
		color: #999;
		font-size: 14px;
		margin-bottom: 5px;
	}

	.task_title {
		font-size: 22px;
	}
}

.list-container {
	width: 110%;
	font-size: 14px;

	.list-item {
		display: flex;
		flex-direction: row;
		align-items: center;
		border-bottom: 1px solid #ccc;
		padding: 0px 0px 100px 0px;
	}

	.list-item-title {
		width: 30%;
		height: 100px;
		margin-left: 10px;
		margin-right: 10px;

		.list-item-title_1 {
			font-size: 15px;
			margin-bottom: 5px;
			color: #999;
		}

		.list-item-title_2 {
			font-size: 12px;
			margin-bottom: 8px;
		}

		.list-item-title_3 {
			font-weight: 700;
			font-size: 12px;
			color: #409EFF;
		}

	}
}
</style>

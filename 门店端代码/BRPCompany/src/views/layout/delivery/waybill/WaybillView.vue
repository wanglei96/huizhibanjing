<script setup>
import common from '@/js/common'
import util from '@/js/util'
import { reactive, ref, inject } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'
// 声明配送单详情页
import WaybillDetail from './component/WaybillDetail.vue'
const WaybillDetailRef = ref();
// 分页相关
const currentPage = ref(1);
const pageSize = ref(10);
const recordCount = ref();
const small = ref(false);
const background = ref(false);
const disabled = ref(false);
// 当前门店id
const companyId = common.getLocalStorage("companyId");

// 首次页面加载获取列表
getDataList();
getDeliverAreaData();

// 分页监听
const handleSizeChange = (val) => {
	getDataList();
}
const handleCurrentChange = (val) => {
	getDataList();
}

// 获取会员卡类型列表
const tableData = ref([]);
const todayOrder = ref();
const tomorrowOrder = ref();
const deliverAreaIdQuery = ref();
const deliverTimeQuery = ref();
function getDataList(btnIndex, deliverAreaId) {
	let param = {
		pageIndex: currentPage.value - 1,
		pageSize: pageSize.value,
		companyId: companyId,
		deliverAreaId: deliverAreaId,
		condition:"is_offline is not true"
	}
	if (btnIndex === 1) {
		console.log('全部订单')
	} else if (btnIndex === 2) {
		console.log('今日订单')
		todayOrder.value = util.simpleDateFormat(new Date());
		param.deliverTimeBegin = todayOrder.value;
		param.deliverTimeEnd = todayOrder.value;
	} else if (btnIndex === 3) {
		console.log('明日订单')
		tomorrowOrder.value = util.simpleDateFormat(util.getAddDays(new Date(), 1));
		param.deliverTimeBegin = tomorrowOrder.value;
		param.deliverTimeEnd = tomorrowOrder.value;
	} else if (btnIndex === 4) {
		console.log('显示未处理订单');
		param.isHandle = 1;
	} else if (btnIndex === 5) {
		console.log('显示未到配送时间订单');
		param.isTime = 1;
	} else if (btnIndex === 6) {
		console.log('时间范围订单')
		let startTime = util.getFormatTime(deliverTimeQuery.value[0]);
		let endTime = util.getFormatTime(deliverTimeQuery.value[1]);
		param.deliverTimeBegin = startTime;
		param.deliverTimeEnd = endTime;
	}
	//param.condition = "book_order_borrow_status_code is not null";
	common.call("GetViewWaybillList", param, function (result) {
		tableData.value = result.data.dataList;
		pageSize.value = result.data.pageSize;
		recordCount.value = result.data.recordCount;
	}, function (result) {
		console.log("获取数据列表失败");
	})
}

// 锁定账号监听
function enabledChange(data) {
	if (data.enabled) {
		data.enabled = false;
	} else {
		data.enabled = true;
	}
	let params = {
		id: data.memberId,
		enabled: data.enabled
	}
	common.call("UpdateMember", params, function (result) {
		getDataList();
	}, function (result) {
		console.log("会员书箱锁定状态修改失败");
	})
}

/**
 * 今日不送单击事件
 * @param {Object} data
 */
function todayNoSend(data) {
	let params = {
		id: data.id,
		deliverTime: util.simpleDateFormat(util.getAddDays(new Date(), 1))
	}
	common.call("UpdateBookOrder", params, function (result) {
		ElMessage({
			message: '该订单已转到明日配送',
			type: 'success',
		})
		getDataList();
	}, function (result) {
		ElMessage.error('订单配送转移错误')
	})
}

/**
 * 获取大区街道数据
 */
let deliverAreaData = ref();
function getDeliverAreaData() {
	common.call("GetDeliverAreaList", { companyId: companyId }, function (result) {
		deliverAreaData.value = result.data.dataList;
	}, function (result) {
		console.log("获取大区街道失败");
	})
}

/**
 * 大区街道监听事件
 * @param {Object} value 大区街道id
 */
function deliverAreaChanged(value) {
	getDataList(null, value);
}

/**
 * 时间范围监听事件
 */
function deliverTimeQueryChanged() {
	getDataList(6);
}

/**
 * 一键配送事件
 * @param {Object} data
 */
function onClickDelivery(data) {
	common.call("UpdateOneClickDeliver", { id: data.id }, function (result) {
		ElMessage({
			message: '一键配送成功',
			type: 'success',
		})
		getDataList();
	}, function (result) {
		ElMessage.error('一键配送失败')
	})
}

</script>
<template>
	<div class="user">
		<WaybillDetail ref="WaybillDetailRef" />
		<el-card shadow="never" class="user-search-card">
			<el-row type="flex">
				<el-col :xs="2" :sm="2" :md="2" :lg="2">
					<el-button type="primary" @click="getDataList(1)">全部订单</el-button>
				</el-col>
				<el-col :xs="2" :sm="2" :md="2" :lg="2">
					<el-button type="success" @click="getDataList(2)">今日订单</el-button>
				</el-col>
				<el-col :xs="2" :sm="2" :md="2" :lg="2">
					<el-button type="warning" @click="getDataList(3)">明日订单</el-button>
				</el-col>
				<el-col :xs="3" :sm="3" :md="3" :lg="3">
					<el-button type="info" @click="getDataList(4)">显示未处理订单</el-button>
				</el-col>
				<el-col :xs="3" :sm="3" :md="3" :lg="3">
					<el-button type="info" @click="getDataList(5)">显示未到配送时间订单</el-button>
				</el-col>
				<el-col :xs="4" :sm="4" :md="4" :lg="4">
					<el-select v-model="deliverAreaIdQuery" placeholder="大区街道" style="margin-left: 10px;" filterable
						@change="deliverAreaChanged">
						<el-option key="" label="全部" value="" />
						<el-option v-for="item in deliverAreaData" :key="item.id" :label="item.name" :value="item.id" />
					</el-select>
				</el-col>
				<el-col :xs="6" :sm="6" :md="6" :lg="6">
					<el-date-picker @change="deliverTimeQueryChanged" v-model="deliverTimeQuery" type="datetimerange"
						value-format="YYYY-MM-DD HH:mm:ss" start-placeholder="开始日期" end-placeholder="结束日期" />
				</el-col>
			</el-row>
		</el-card>
		<el-card shadow="never" class="user-list-card">
			<el-table :data="tableData" style="width: 100%">
				<el-table-column type="index" label="号数" align="center" width="55px" />
				<el-table-column property="deliverAreaName" label="街道名称" align="center" />
				<el-table-column property="name" label="会员姓名" align="center" />
				<el-table-column property="memberNo" label="会员卡号" align="center" />
				<el-table-column property="mobilePhone" label="联系电话" align="center" />
				<el-table-column property="jieNum" label="送几本" align="center" />
				<el-table-column property="huanNum" label="取几本" align="center" />
				<el-table-column property="entranceGuard" label="门禁卡" align="center" />
				<el-table-column property="deliverAreaName" label="大区街道名称" align="center" />
				<el-table-column property="address" label="会员详细地址" align="center" />
				<el-table-column label="操作" align="center">
					<template #default="scope">
						<el-link type="primary" :id="scope.row.id" @click="onClickDelivery(scope.row)">一键配送</el-link><br />
						<el-link type="primary" :id="scope.row.id" @click="todayNoSend(scope.row)">今日不送</el-link><br />
						<el-link type="primary" :id="scope.row.id" @click="enabledChange(scope.row)">
							<span v-if="scope.row.enabled === false">锁定账号</span>
							<span v-else-if="scope.row.enabled === true">账号已锁定</span>
						</el-link><br />
						<el-link type="primary" :id="scope.row.id" @click="WaybillDetailRef.init(scope.row)">配送单列表</el-link>
					</template>
				</el-table-column>
			</el-table>
			<el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[10, 50, 100]"
				:small="small" :disabled="disabled" :background="background"
				layout="total, sizes, prev, pager, next, jumper" :total="recordCount" @size-change="handleSizeChange"
				@current-change="handleCurrentChange" style="margin-top: 20px" />
		</el-card>
	</div>
</template>

<style lang="scss" scoped>
.card-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.user {
	.user-search-card {

		margin-bottom: 15px;

		.user-form {
			.el-form-item {
				margin-bottom: 3px;
				margin-left: 3px;
			}
		}
	}

	.table-footer {
		display: flex;
		justify-content: center;
		width: 100%;
		text-align: center;
		margin-top: 20px;
	}
}
</style>

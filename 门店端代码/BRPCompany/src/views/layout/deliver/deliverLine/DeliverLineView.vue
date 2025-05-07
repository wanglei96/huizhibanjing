<script setup>
import DeliverLineEdit from './component/DeliverLineEdit.vue'
import DeliverPlaceView from './DeliverPlaceView.vue'
import common from '@/js/common'
import {
	reactive,
	ref,
	inject
} from 'vue';
import {
	ElMessage,
	ElMessageBox
} from 'element-plus'
// 声明会员卡类型编辑页
const DeliverLineEditRef = ref()
const DeliverPlaceViewRef = ref()
// 页面重载
provide('getDataList', getDataList);
// 分页相关
const currentPage = ref(1)
const pageSize = ref(10)
const recordCount = ref();
const small = ref(false)
const background = ref(false)
const disabled = ref(false)
// 当前门店id
const companyId = common.getLocalStorage("companyId")

// 首次页面加载获取列表
getDataList();

// 分页监听
const handleSizeChange = (val) => {
	getDataList();
}
const handleCurrentChange = (val) => {
	getDataList();
}

// 获取会员卡类型列表
const tableData = ref([])

function getDataList(nameQuery, delivererIdQuery, bookpickerIdQuery, descriptionQuery) {
	let param = {
		pageIndex: currentPage.value - 1,
		pageSize: pageSize.value,
		companyId: companyId,
		name: nameQuery,
		delivererId: delivererIdQuery,
		bookpickerId: bookpickerIdQuery,
		description: descriptionQuery
	}
	common.call("GetViewDeliverLineList", param, function (result) {
		tableData.value = result.data.dataList;
		pageSize.value = result.data.pageSize;
		recordCount.value = result.data.recordCount;
	}, function (result) {
		console.log("获取数据列表失败");
	})
}


// 配送路线删除
function DeliverLineDel(id) {
	ElMessageBox.confirm(
		'确认删除配送路线?', {
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		type: 'warning',
	}
	)
		.then(() => {
			//判断路线是否存在会员
			let params = {
				deliverLineId: id,
				fields: "count(1) as count"
			}
			common.call("GetMemberList", params, function (result) {
				if (result.data.dataList[0].count > 0) {
					ElMessage.error("该路线存在会员，不能删除");
					return;
				}
				common.call("DeleteDeliverLine", {
					id: id
				}, function (result) {
					getDataList()
					ElMessage.success("删除成功");
				})
			})
		})
}

// 会员卡类型列表条件查询
const nameQuery = ref();
const delivererIdQuery = ref();
const bookpickerIdQuery = ref();
const descriptionQuery = ref();

function queryDataList() {
	getDataList(nameQuery.value, delivererIdQuery.value, bookpickerIdQuery.value, descriptionQuery.value);
}

// 查询条件重置
function resetQuery() {
	nameQuery.value = ''
	delivererIdQuery.value = ''
	descriptionQuery.value = ''
}
</script>
<template>
	<div class="user">
		<DeliverLineEdit ref="DeliverLineEditRef" />
		<DeliverPlaceView ref="DeliverPlaceViewRef" />
		<el-card shadow="never" class="user-search-card">
			<el-form :inline="true" class="user-form">
				<el-form-item label="名称">
					<el-input placeholder="请输入配送路线名称" v-model="nameQuery"
						@keydown.enter.prevent=queryDataList()></el-input>
				</el-form-item>
				<el-form-item label="配送员">
					<DatabaseSelect table-name="view_company_user"
						:condition="'company_id=' + companyId + ' and is_deliverer = 1 '" v-model="delivererIdQuery"
						valueFieldName="id" placeholder="请选择配送员" />
				</el-form-item>
				<el-form-item label="拣书员">
					<DatabaseSelect table-name="view_company_user"
						:condition="'company_id=' + companyId + ' and is_bookpicker = 1 '" v-model="bookpickerIdQuery"
						valueFieldName="id" placeholder="请选择拣书员" />
				</el-form-item>
				<el-form-item label="描述">
					<el-input placeholder="请输入描述内容" v-model="descriptionQuery"
						@keydown.enter.prevent=queryDataList()></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="queryDataList()">查询</el-button>
					<el-button @click="resetQuery()">重置</el-button>
				</el-form-item>
			</el-form>
		</el-card>
		<el-card shadow="never" class="user-list-card">
			<template #header>
				<div class="card-header">
					<div>
						<el-button type="primary" @click="DeliverLineEditRef.init('add')">
							<el-icon style="margin-right: 3px">
								<Plus />
							</el-icon> 新建
						</el-button>
					</div>
					<div style="width: 80px; display: flex; justify-content: space-around; margin-right: 20px">
					</div>
				</div>
			</template>
			<el-table :data="tableData" style="width: 100%">
				<el-table-column property="name" label="名称" align="center" />
				<el-table-column property="delivererName" label="配送员" align="center" />
				<el-table-column property="bookpickerName" label="拣书员" align="center" />
				<el-table-column property="deliverAreaName" label="配送大区" align="center" />
				<el-table-column label="配送点总数" align="center">
					<template #default="scope">
						<el-link type="primary" :id="scope.row.id" @click="DeliverPlaceViewRef.init(scope.row)">
							{{ scope.row.deliverPlaceNum }}(点击查看详细)</el-link>
					</template>
				</el-table-column>
				<el-table-column property="description" label="描述" align="center" />
				<el-table-column label="操作" align="center">
					<template #default="scope">
						<el-space spacer="|" style="color: #999" wrap>
							<el-link type="warning" :id="scope.row.id"
								@click="DeliverLineEditRef.init('view', scope.row)">查看</el-link>
							<el-link type="primary" :id="scope.row.id"
								@click="DeliverLineEditRef.init('edit', scope.row)">修改</el-link>
							<el-link type="danger" @click="DeliverLineDel(scope.row.id)">删除</el-link>
						</el-space>
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

.el-input {
	width: 200px;
}

.el-select {
	width: 200px;
}

.user {
	.user-search-card {
		margin-bottom: 15px;

		.user-form {
			.el-form-item {
				margin-bottom: 3px;
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

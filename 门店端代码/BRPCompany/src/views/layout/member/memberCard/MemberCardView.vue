<script setup>
import MemberCardAdd from './component/MemberCardAdd.vue'
import MemberCardEdit from './component/MemberCardEdit.vue'
import common from '@/js/common'
import { reactive, ref, inject } from 'vue';
import { ElMessage } from 'element-plus'

const MemberCardAddRef = ref()
const MemberCardEditRef = ref()
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
getMemberCardBatchList();

// 分页监听
const handleSizeChange = (val) => {
	getDataList();
}
const handleCurrentChange = (val) => {
	getDataList();
}

// 获取会员卡仓库列表
const tableData = ref([])
function getDataList(isOnlineReadingQuery, memberCardNoQuery) {
	let param = {
		pageIndex: currentPage.value - 1,
		pageSize: pageSize.value,
		companyId: companyId,
		batchId: isOnlineReadingQuery,
		cardNo: memberCardNoQuery,
		condition: "member_id is null",
		orderBy: 'card_no',
	}
	common.call("GetViewMemberCardList", param, function (result) {
		tableData.value = result.data.dataList;
		pageSize.value = result.data.pageSize;
		recordCount.value = result.data.recordCount;
	})
}

// 会员卡仓库列表条件查询
const isOnlineReadingQuery = ref();
const memberCardNoQuery = ref();
function queryDataList() {
	getDataList(isOnlineReadingQuery.value, memberCardNoQuery.value);
}

// 获取会员卡批次
const MemberCardBatchData = ref([]);
function getMemberCardBatchList() {
	let param = {
		companyId: companyId
	}
	common.call("GetMemberCardBatchList", param, function (result) {
		MemberCardBatchData.value = result.data.dataList;
	})
}

// 查询条件重置
function resetQuery() {
	isOnlineReadingQuery.value = ''
	memberCardNoQuery.value = ''
}

// 是否特殊会员卡监听
function isSpecialChange(id, isSpecial) {
	let param = {
		id: id,
		isSpecial: isSpecial
	}
	common.call("UpdateMemberCard", param, function (result) {
		let message = ref();
		if (isSpecial == true) {
			message.value = "已修改为特殊会员卡";
		} else {
			message.value = "已修改为非特殊会员卡";
		}
		ElMessage({
			showClose: true,
			message: message.value,
			type: 'success',
		})
	}, function (result) {
		ElMessage({
			showClose: true,
			message: "修改是否特殊会员卡失败",
			type: 'error',
		})
	})
}

const cellStyle = (data) => {
  return {
    background: data.row.color,
  };
};

</script>
<template>
	<div class="user">
		<MemberCardAdd ref="MemberCardAddRef" />
		<MemberCardEdit ref="MemberCardEditRef" />
		<el-card shadow="never" class="user-search-card">
			<el-form :inline="true" class="user-form">
				<el-form-item label="会员卡批次">
					<el-select placeholder="请选择" v-model="isOnlineReadingQuery" filterable>
						<el-option v-for="item in MemberCardBatchData" :key="item.id" :label="item.name" :value="item.id" />
					</el-select>
				</el-form-item>
				<el-form-item label="会员卡号">
					<el-input placeholder="请输入" v-model="memberCardNoQuery" @keydown.enter.prevent=queryDataList()></el-input>
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
						<el-button type="primary" @click="MemberCardAddRef.init()"><el-icon style="margin-right: 3px">
								<Plus />
							</el-icon> 新建</el-button>
					</div>
					<div style="width: 80px; display: flex; justify-content: space-around; margin-right: 20px">
					</div>
				</div>
			</template>
			<el-table :data="tableData" style="width: 100%" :cell-style="cellStyle">
				<el-table-column property="cardNo" label="会员卡号" align="center" />
				<el-table-column property="batchName" label="会员卡批次" align="center" />
				<!-- <el-table-column label="是否特殊会员卡">
		          <template #default="scope">
		            <el-switch
		              v-model="scope.row.isSpecial"
					     @change="isSpecialChange(scope.row.id, $event)"
					  />
					
		          </template>
		</el-table-column> -->
				<el-table-column label="操作" align="center">
					<template #default="scope">
						<el-space spacer="|" style="color: #999" wrap>
							<el-link type="primary" :id="scope.row.id"
								@click="MemberCardEditRef.init(scope.row)">绑定会员信息</el-link>
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

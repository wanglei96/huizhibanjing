<script setup>
import MemberCardTypeEdit from './component/MemberCardTypeEdit.vue'
import common from '@/js/common'
import {
	reactive,
	ref,
	inject,
	nextTick
} from 'vue';
import {
	ElMessage,
	ElMessageBox
} from 'element-plus'
// 声明会员卡类型编辑页
const MemberCardTypeEditRef = ref()
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

function getDataList(nameQuery, memberCardCategoryCodeQuery) {
	let param = {
		pageIndex: currentPage.value - 1,
		pageSize: pageSize.value,
		companyId: companyId,
		name: nameQuery,
		memberCardCategoryCode: memberCardCategoryCodeQuery
	}
	common.call("GetMemberCardTypeList", param, function (result) {
		tableData.value = result.data.dataList;
		pageSize.value = result.data.pageSize;
		recordCount.value = result.data.recordCount;
	})
}

// 会员卡类型删除
function memberCardTypeDel(id) {
	ElMessageBox.confirm(
		"确定删除会员卡类型？", {
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		type: 'warning',
	})
		.then(() => {
			//判断是否存在会员
			let params = {
				memberCardTypeId: id,
				fields: "count(1) as count"
			}
			common.call("GetMemberCardList", params, function (result) {
				if (result.data.dataList[0].count > 0) {
					ElMessage.error("该类型存在会员信息，不能删除");
					return;
				}
				common.call("DeleteMemberCardType", {
					id: id
				}, function (result) {
					getDataList();
					ElMessage.success("删除成功");
				})
			})
		})
}

// 会员卡类型列表条件查询
const nameQuery = ref();
const memberCardCategoryCodeQuery = ref();

function queryDataList() {
	getDataList(nameQuery.value, memberCardCategoryCodeQuery.value);
}

// 查询条件重置
function resetQuery() {
	nameQuery.value = ''
	memberCardCategoryCodeQuery.value = ''
}
</script>
<template>
	<div class="user">
		<MemberCardTypeEdit ref="MemberCardTypeEditRef" />
		<el-card shadow="never" class="user-search-card">
			<el-form :inline="true" class="user-form">
				<el-form-item label="名称">
					<el-input placeholder="请输入" v-model="nameQuery" @keydown.enter.prevent=queryDataList()></el-input>
				</el-form-item>
				<el-form-item label="会员卡分类">
					<DatabaseSelect table-name="member_card_category" v-model="memberCardCategoryCodeQuery"
						placeholder="请选择会员卡分类" />
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
						<el-button type="primary" @click="MemberCardTypeEditRef.init('add')">
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
				<el-table-column property="maxQuantity" label="每次最多可借阅数量" align="center" />
				<el-table-column property="isSecurityDeposit" label="是否需要借书押金" align="center">
					<template #default="scope">
						<span v-if="scope.row.isSecurityDeposit === true">是</span>
						<span v-else-if="scope.row.isSecurityDeposit === false">否</span>
					</template>
				</el-table-column>
				<el-table-column property="securityDepositPrice" label="押金金额" align="center" />
				<el-table-column property="membershipPrice" label="会员费" align="center" />
				<el-table-column property="bookboxPrice" label="书箱费" align="center" />
				<el-table-column property="days" label="借阅天数" align="center" />

				<el-table-column label="操作" align="center">
					<template #default="scope">
						<el-space spacer="|" style="color: #999" wrap>
							<el-link type="warning" :id="scope.row.id"
								@click="MemberCardTypeEditRef.init('view', scope.row)">查看</el-link>
							<el-link type="primary" :id="scope.row.id"
								@click="MemberCardTypeEditRef.init('edit', scope.row)">修改</el-link>
							<el-link type="danger" @click="memberCardTypeDel(scope.row.id)">删除</el-link>
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

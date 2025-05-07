<script lang="ts" setup>
import type {
	FormInstance,
	FormRules,
	ElMessage,
	ElMessageBox
} from 'element-plus'

import common from '@/js/common'
import {
	reactive,
	ref,
	inject,
	nextTick,
	watch
} from 'vue';
import DeliverPlaceEdit from './component/DeliverPlaceEdit.vue'
import MemberTransferEdit from './component/MemberTransferEdit.vue'
import Sortable from "sortablejs"
const {
	proxy
} = getCurrentInstance();
// 页面重载
provide('getDataList', getDataList);
// 分页相关
const currentPage = ref(1)
const pageSize = ref(50000)
const recordCount = ref();
const small = ref(false)
const background = ref(false)
const disabled = ref(false)
const companyId = common.getLocalStorage("companyId")
const dialogFormVisible = ref(false)
const msg = ref('配送点管理列表')
const DeliverPlaceEditRef = ref()
const MemberTransferEditRef = ref()

// 分页监听
const handleSizeChange = (val) => {
	getDataList(deliverLineId.value);
}
const handleCurrentChange = (val) => {
	getDataList(deliverLineId.value);
}

// 获取会员列表
const tableData = ref([])

function getDataList(deliverLineId) {
	console.log(deliverLineId)
	let param = {
		pageIndex: currentPage.value - 1,
		pageSize: pageSize.value,
		deliverLineId: deliverLineId,
		orderBy: 'display_order',
		companyId: companyId.value
	}
	common.call("GetViewDeliverPlaceList", param, function (result) {
		tableData.value = result.data.dataList;
		pageSize.value = result.data.pageSize;
		recordCount.value = result.data.recordCount;
	})
}

// 会员卡时长变更记录列表条件查询
const cardNoQuery = ref();

function queryDataList() {
	getDataList(cardNoQuery.value);
}

// 查询条件重置
function resetQuery() {
	cardNoQuery.value = ''
}

const deliverLineId = ref();

function init(data) {
	// 显示弹窗
	dialogFormVisible.value = true;
	deliverLineId.value = data.id;
	getDataList(deliverLineId.value);
	nextTick(() => {
		initSortable('t1');
	})
}

// 配送点删除
function DeliverPlaceDel(id) {
	ElMessageBox.confirm(
		'确认删除配送点?', {
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		type: 'warning',
	})
		.then(() => {
			//判断路线是否存在会员
			let params = {
				deliverPlaceId: id,
				fields: "count(1) as count"
			}
			common.call("GetMemberList", params, function (result) {
				if (result.data.dataList[0].count > 0) {
					ElMessage.error("该路线存在会员，不能删除");
					return;
				}
				common.call("DeleteDeliverPlace", {
					id: id
				}, function (result) {
					getDataList(deliverLineId.value)
					ElMessage.success("删除成功");
				})
			})
		})
}
const startIndex = ref(-1);
const endIndex = ref(-1);
// 创建sortable实例
function initSortable(className) {
	// 获取表格row的父节点
	const table = document.querySelector('.' + className + ' .el-table__body-wrapper tbody');
	// 创建拖拽实例
	Sortable.create(table, {
		animation: 150, //动画
		disabled: false, // 拖拽不可用? false 启用（刚刚渲染表格的时候起作用，后面不起作用）
		//handle: ".move", //指定拖拽目标，点击此目标才可拖拽元素(此例中设置操作按钮拖拽)
		filter: ".disabled", //指定不可拖动的类名（el-table中可通过row-class-name设置行的class）
		// 开始拖动事件
		onStart: () => {
			console.log("开始拖动");
		},
		// 结束拖动事件
		onEnd: (evt) => {
			let newIndex = evt.newIndex  // 拖动后的索引位置
			let oldIndex = evt.oldIndex  // 拖动前的索引位置
			console.log("结束拖动", `拖动前索引${oldIndex}---拖动后索引${newIndex}`);

			startIndex.value = oldIndex;
			endIndex.value = newIndex;
			//let start = Math.min(newIndex, oldIndex);
			//let end = Math.max(newIndex, oldIndex);

			/* const rearrangedTableData = reorderArray(tableData.value, oldIndex, newIndex);

			console.log("--rearrangedTableData--", rearrangedTableData);
 */
			//let rangeDataList = tableData.value.slice(start, end + 1);

			const rearrangedRangeDataList = reorderArray(tableData.value, oldIndex, newIndex);

			// 输出拖动范围内的列表数据

			//console.log("--rangeDataList--", rangeDataList);  

			console.log("--rearrangedRangeDataList--", rearrangedRangeDataList);

			console.log("--tableData.value--", tableData.value);


			//console.log("开始拖动", tableData.value);
			/* const currRow = tableData.value.splice(oldIndex, 1)[0];
			tableData.value.splice(newIndex, 0, currRow); */
			//console.log("结束拖动", tableData.value);


			// 更新排序字段
			/* rearrangedTableData.forEach((item, index) => {
				item.displayOrder = index + 1; // 重新计算排序字段
			}) */

			/* let param = {
				tableValue: tableData.value
			}
			common.call("GetSortable", param, function (result) {
				getDataList(deliverLineId.value);
			}) */

		},
	});
};
// 重新排序数组的辅助函数
function reorderArray(list, startIndex, endIndex) {
	//const result = Array.from(list);
	const [removed] = list.splice(startIndex, 1);
	list.splice(endIndex, 0, removed);
	return list;
}
// 设置表格row的class
function tableRowClassName({
	row
}) {
	if (row.disabled) {
		return "disabled";
	}
	return "";
};
defineExpose({
	init
})
const newDialog = ref(false);
const DelivererData = ref([])
const oldDeliverPlaceId = ref()
// 配送员监听
const DeliverPlaceData = ref([])
const DeliverPlaceFormRef = ref<FormInstance>()
const DeliverPlaceForm = reactive({
	id: '',
	delivererId: '',
	deliverPlaceId: '',
	createTime: '',
	updateTime: ''
})
const rules = reactive<FormRules>({
	delivererId: [{
		required: true,
		message: '配送员不允许为空',
		trigger: 'blur'
	},],
	deliverPlaceId: [{
		required: true,
		message: '配送地点不允许为空',
		trigger: 'blur'
	},],
})
const submitForm = (formEl) => {
	if (!formEl) return
	formEl.validate((valid, fields) => {
		if (valid) {
			let param = {
				oldDeliverPlaceId: oldDeliverPlaceId.value,
				delivererId: DeliverPlaceForm.delivererId,
				newDeliverPlaceId: DeliverPlaceForm.deliverPlaceId
			}
			common.call("EditMemberTransfer", param, function (result) {
				let data = { id: deliverLineId.value }
				init(data)
				newDialog.value = false
				// this.$emit('getInit')
				// getDataList();
			})
		}
	})
}

const delivererChange = (value) => {
	// 配送地点下拉框列表渲染
	let param = {
		delivererId: value
	}
	common.call("GetViewDeliverPlaceList", param, function (result) {
		DeliverPlaceData.value = result.data.dataList;
	})
}

function editDialog(data) {
	if (data.memberNum == 0) {
		ElMessage.error('当前配送点下没有会员')
		return
	}
	// 显示弹窗
	newDialog.value = true
	// 清除表单的校验规则
	//this.$nextTick(()=>{
	//this.$refs.DeliverPlaceFormRef.clearValidate();  
	//})
	// 获取旧的配送点id
	oldDeliverPlaceId.value = data.id
	// 配送员下拉框列表渲染
	common.call("GetViewCompanyUserList", {
		companyId: companyId,
		isDeliverer: 1
	}, function (result) {
		DelivererData.value = result.data.dataList;
	})
}
const refreshList = inject("getDataList");
watch(dialogFormVisible, (newValue, oldValue) => {
	if (!newValue) {
		currentPage.value = 1
		if (startIndex.value >= 0 && endIndex.value >= 0) {
			var ids = tableData.value.map(item => item.id).join(',');
			console.log("ids", ids);
			let params = {
				deliverPlaceIds: ids
			}
			common.call("UpdateDeliverPlaceDisplayOrder", params, function (result) {
			})
		}
		tableData.value = []
		refreshList();
	}
})


</script>
<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="newDialog" :title="msg" width="500" draggable
			overflow>
			<el-form ref="DeliverPlaceFormRef" :model="DeliverPlaceForm" :rules="rules" label-width="150px"
				class="demo-DeliverPlaceForm" status-icon>
				<el-form-item label="配送员" prop="delivererId">
					<el-select v-model="DeliverPlaceForm.delivererId" placeholder="请选择配送员" @change="delivererChange"
						filterable>
						<el-option v-for="item in DelivererData" :key="item.id" :label="item.name" :value="item.id" />
					</el-select>
				</el-form-item>

				<el-form-item label="配送地点" prop="deliverPlaceId">
					<el-select v-model="DeliverPlaceForm.deliverPlaceId" placeholder="请选择配送地点" filterable>
						<el-option v-for="item in DeliverPlaceData" :key="item.id" :label="item.name"
							:value="item.id" />
					</el-select>
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="submitForm(DeliverPlaceFormRef)"> 确定 </el-button>
					<el-button @click="newDialog = false">取消</el-button>
				</span>
			</template>
		</el-dialog>



		<DeliverPlaceEdit ref="DeliverPlaceEditRef" />
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="70%"
			draggable overflow>
			<!-- <el-card shadow="never" class="user-search-card">
      <el-form :inline="true" class="user-form">
        <el-form-item label="会员卡号">
          <el-input placeholder="请输入" v-model="cardNoQuery"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="queryDataList()">查询</el-button>
          <el-button @click="resetQuery()">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card> -->
			<el-card shadow="never" class="user-list-card">
				<template #header>
					<div class="card-header">
						<div>
							<el-button type="primary" @click="DeliverPlaceEditRef.init('add', deliverLineId)">
								<el-icon style="margin-right: 3px">
									<Plus />
								</el-icon>新建配送地点
							</el-button>
						</div>
						<div style="width: 80px; display: flex; justify-content: space-around; margin-right: 20px">
						</div>
					</div>
				</template>
				<!--    style="width: 100%;height: 100%" -->
				<el-table class="t1" ref="dragTable" :data="tableData" row-key="displayOrder"
					:row-class-name="tableRowClassName">
					<el-table-column property="name" label="配送地点" align="center" />
					<el-table-column property="displayOrder" label="顺序" align="center" width="60"/>
					<el-table-column label="配送点会员数" align="center">
						<template #default="scope">
							<span v-if="scope.row.memberNum === 0">
								{{ scope.row.memberNum }}(点击查看详细)
							</span>
							<span v-else>
								<router-link
									:to="{ path: '/member/memberCardQuery/MemberCardQueryView', query: { deliverPlaceId: scope.row.id } }">
									{{ scope.row.memberNum }}(点击查看详细)
								</router-link>
							</span>
						</template>
					</el-table-column>
					<el-table-column property="delivererName" label="配送员" align="center" />
					<el-table-column property="deliverAreaName" label="配送大区" align="center" />
					<el-table-column label="操作" align="center" width="300">
						<template #default="scope">
							<el-space spacer="|" style="color: #999" wrap>
								<el-link type="primary" :id="scope.row.id"
									@click="DeliverPlaceEditRef.init('edit', scope.row)">修改</el-link>
								<el-link type="danger" @click="DeliverPlaceDel(scope.row.id)">删除</el-link>
								<!-- <el-link type="warning" @click="MemberTransferEditRef.init(scope.row)">将会员转移到其他配送点
 								</el-link> -->
								<el-link type="warning" @click="editDialog(scope.row)">将会员转移到其他配送点
								</el-link>
							</el-space>
						</template>
					</el-table-column>
				</el-table>
				<!-- <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize"
					:page-sizes="[10, 50, 100]" :small="small" :disabled="disabled" :background="background"
					layout="total, sizes, prev, pager, next, jumper" :total="recordCount"
					@size-change="handleSizeChange" @current-change="handleCurrentChange" style="margin-top: 20px" /> -->
			</el-card>
		</el-dialog>
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

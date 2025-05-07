<script setup>
import {
	ref
} from 'vue'
import util from '@/js/util'
import management from '@/js/management'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import common from '@/js/common';

const companyId = common.getLocalStorage("companyId");
const dialogFormVisible = ref(false);
const refSearchForm = ref();
const list = management({
	tableName: 'member',
	orderBy: 'create_time desc',
	condition: "company_id = " + companyId + " and is_cancel = true",
	getListServiceName: 'GetViewMemberList'
});
// 编辑页初始化
function init(data) {
	// 显示弹窗
	dialogFormVisible.value = true
	list.loadData();
}

defineExpose({
	init
})
</script>
<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="70%"
			draggable overflow>
			<div class="basic-list">
				<el-card shadow="never" class="search-card">
					<el-form ref="refSearchForm" :model="list.parameters">
						<el-row :gutter="20" type="flex">
							<el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
								<el-form-item label="会员姓名" prop="name">
									<el-input placeholder="请输入..." v-model="list.parameters.name"
										@keydown.enter.prevent=list.query></el-input>
								</el-form-item>
							</el-col>
							<el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
								<el-form-item>
									<el-button type="primary" @click="list.query">搜索</el-button>
									<el-button @click="list.resetForm(refSearchForm)">重置</el-button>
								</el-form-item>
							</el-col>
						</el-row>
					</el-form>
				</el-card>
				<el-card shadow="never">
					<el-alert :title="`已选择 ${list.selectedColumnCount.value} 项`"
						v-show="list.selectedColumnCount.value > 0" type="warning" show-icon />
					<el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
						<el-row>
							<el-col :span="18">
								<el-button type="danger" @click="list.deleteRows">
									<el-icon tyle="margin-right: 3px">
										<Delete />
									</el-icon>
									删除
								</el-button>
							</el-col>
							<el-col :span="6"> </el-col>
						</el-row>
					</el-form>
					<el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%"
						@selection-change="list.selectRow">
						<el-table-column type="selection" width="55" />
						<el-table-column type="index" label="序号" :index="1" width="60" />
						<el-table-column property="name" label="会员姓名" align="center" />
						<el-table-column property="birthdayText" label="会员生日" align="center">
							<template #default="scope">
								<span>
									{{ scope.row.birthdayText ? util.simpleDateFormat(scope.row.birthdayText) : "" }}
								</span>
							</template>
						</el-table-column>
						<el-table-column property="mobilePhone" label="联系电话" align="center" />
						<el-table-column property="gender" label="性别" align="center">
							<template #default="scope">
								<span v-if="scope.row.gender === true">男</span>
								<span v-else-if="scope.row.gender === false">女</span>
							</template>
						</el-table-column>
						<el-table-column property="school" label="学校" align="center" />
						<el-table-column property="gradeName" label="班级" align="center" />
						<el-table-column property="className" label="年级" align="center" />
						<el-table-column property="address" label="地址" align="center" />
						<el-table-column property="cancelOperator" label="操作人" align="center" />
						<el-table-column property="cancelTimeText" label="操作时间" align="center" />
						<el-table-column label="操作" align="center" width="230">
							<template #default="scope">
								<el-space spacer="|" style="color: #999" wrap>
									<el-link type="danger" @click="list.deleteRow(scope.row.id)">删除</el-link>
								</el-space>
							</template>
						</el-table-column>
					</el-table>
					<el-pagination v-model:current-page="list.currentPage.value"
						v-model:page-size="list.pageSizeComputed.value" :page-sizes="list.pageSizes"
						:page-count="list.pageCount.value" :layout="list.layout" :total="list.recordCount.value"
						style="margin-top: 20px" />
				</el-card>
			</div>
		</el-dialog>
	</div>
</template>

<style lang="scss" scoped>
.search-card {
	margin-bottom: 15px;

	.el-form-item {
		margin-bottom: 0px;
	}
}

.image-slot {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 100px;
	height: 100px;
	background: var(--el-fill-color-light);
	color: var(--el-text-color-secondary);
	font-size: 30px;
}

.el-input {
	width: 200px;
}
</style>

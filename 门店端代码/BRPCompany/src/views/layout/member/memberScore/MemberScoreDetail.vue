<script setup>
	import {
		reactive,
		ref
	} from 'vue'
	import detail from '@/js/detail'
	import common from '@/js/common'
	import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'

	//定义事件发射器
	const emit = defineEmits(['reload-data'])
	//详情页开关
	const detailFormVisible = ref(false)
	//表单引用
	const refDataEntityForm = ref()

	let page = reactive({})
	const init = (options) => {
		detailFormVisible.value = true
		page = detail({
			baseTitle: '会员积分',
			tableName: 'view_member_score',
			getServiceName: 'GetViewMemberScore',
			mode: options.mode,
			primaryKeyValue: options.primaryKeyValue,
			detailFormVisible,
			refDataEntityForm,
			emit
		})
	}
	defineExpose({
		init
	})
</script>
<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible"
			:title="page.title ? page.title.value : ''" width="800" draggable>
			<el-form :model="page.dataEntity.value" ref="refDataEntityForm" :label-position="'right'"
				:disabled="page.mode === 'view'" label-width="80px">
				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="会员姓名" prop="memberName">
							<el-input v-model="page.dataEntity.value.memberName" class="form-item-width"></el-input>
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="会员卡号" prop="memberCardNo">
							<el-input v-model="page.dataEntity.value.memberCardNo" class="form-item-width"></el-input>
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="积分时间" prop="scoreTimeText">
							<el-input v-model="page.dataEntity.value.scoreTimeText" class="form-item-width"></el-input>
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="积分类型" prop="memberScoreTypeName">
							<el-input v-model.number="page.dataEntity.value.memberScoreTypeName"
								class="form-item-width"></el-input>
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="积分" prop="score">
							<el-input v-model.number="page.dataEntity.value.score" class="form-item-width"></el-input>
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="操作人" prop="operator">
							<el-input v-model.number="page.dataEntity.value.operator" class="form-item-width">
							</el-input>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" v-if="page.mode === 'add' || page.mode === 'edit'"
						:loading="page.isLoading.value" @click="page.submitForm()">
						确定
					</el-button>
					<el-button @click="detailFormVisible = false">取消</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>

<style lang="scss" scoped>
	.form-item-width {
		width: 200px !important;
	}

	.el-dialog {
		width: 500px;
	}

	.el-button--text {
		margin-right: 15px;
	}

	.el-select {
		width: 300px;
	}

	.el-input {
		width: 200px;
	}

	.dialog-footer button:first-child {
		margin-right: 10px;
	}
</style>

<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600"
			draggable overflow>
			<el-form ref="DeliverLineFormRef" :model="DeliverLineForm" :rules="rules" label-width="150px"
				class="demo-DeliverLineForm" status-icon>
				<el-form-item label="名称" prop="name">
					<el-input v-model="DeliverLineForm.name" placeholder="请输入名称" />
				</el-form-item>

				<el-form-item label="描述" prop="description">
					<el-input v-model="DeliverLineForm.description" placeholder="请输入描述" />
				</el-form-item>
				<el-form-item label="配送大区" prop="deliverAreaId">
					<DatabaseSelect table-name="deliver_area" :condition="'company_id=' + companyId"
						v-model="DeliverLineForm.deliverAreaId" valueFieldName="id" placeholder="请选择配送大区" />
				</el-form-item>
				<el-form-item label="配送员" prop="delivererId">
					<DatabaseSelect table-name="view_company_user"
						:condition="'company_id=' + companyId + ' and is_deliverer = 1 and is_deleted is not true'"
						v-model="DeliverLineForm.delivererId" valueFieldName="id" placeholder="请选择配送员" />
				</el-form-item>
				<el-form-item label="拣书员" prop="bookpickerId">
					<DatabaseSelect table-name="view_company_user"
						:condition="'company_id=' + companyId + ' and is_bookpicker = 1 and is_deleted is not true'"
						v-model="DeliverLineForm.bookpickerId" valueFieldName="id" placeholder="请选择拣书员" />
				</el-form-item>

			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button v-show="isShowBtn" type="primary" @click="submitForm(DeliverLineFormRef)"> 确定
					</el-button>
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
		FormRules
	} from 'element-plus'
	import common from '@/js/common'
	import util from '@/js/util'

	const dialogFormVisible = ref(false);
	const msg = ref();
	const refreshList = inject("getDataList");
	const companyId = common.getLocalStorage("companyId");

	const DeliverLineFormRef = ref < FormInstance > ()
	const DeliverLineForm = reactive({
		id: '',
		name: '',
		description: '',
		delivererId: '',
		deliverAreaId: '',
		bookpickerId: '',
		createTime: '',
		updateTime: ''
	})
	const DeliverLineFormInit = reactive({
		id: '',
		name: '',
		description: '',
		delivererId: '',
		deliverAreaId: '',
		bookpickerId: '',
		createTime: '',
		updateTime: ''
	})


	const rules = reactive < FormRules > ({
		name: [{
			required: true,
			message: '登录账号不允许为空',
			trigger: 'change'
		}, ],
		description: [{
			required: true,
			message: '描述不允许为空',
			trigger: 'change'
		}, ],
		delivererId: [{
			required: true,
			message: '配送员不允许为空',
			trigger: 'change'
		}, ],
		bookpickerId: [{
			required: true,
			message: '拣书员不允许为空',
			trigger: 'change'
		}, ],
		deliverAreaId: [{
			required: true,
			message: '配送大区不允许为空',
			trigger: 'change'
		}, ],
	})

	const submitForm = async (formEl: FormInstance | undefined) => {
		if (!formEl) return
		await formEl.validate((valid, fields) => {
			if (valid) {
				// id不为空修改，否则新增
				const serviceName = ref();
				if (DeliverLineForm.id == '') {
					serviceName.value = "AddDeliverLine";
					DeliverLineForm.createTime = util.getFormatTime(new Date());
				} else {
					serviceName.value = "UpdateDeliverLine";
					DeliverLineForm.updateTime = util.getFormatTime(new Date());
				}
				common.call(serviceName.value, DeliverLineForm, function(result) {
					dialogFormVisible.value = false
					refreshList();
				}, function(result) {
					console.log("配送大区编辑失败");
				})
			}
		})
	}

	const resetForm = (formEl: FormInstance | undefined) => {
		if (!formEl) return
		formEl.resetFields()
	}

	// 编辑页初始化
	const isShowBtn = ref(true);

	function init(type, data) {
		// 显示弹窗
		dialogFormVisible.value = true
		// 清除表单的校验规则
		this.$nextTick(() => {
			this.$refs.DeliverLineFormRef.clearValidate();
		})
		// 表单渲染
		if (data != null) {
			Object.assign(DeliverLineForm, data)
			DeliverLineForm.createTime = data.createTimeText;
		}
		// 隐藏确定按钮
		isShowBtn.value = true;
		if (type === 'add') {
			msg.value = '新建配送路线'
			Object.assign(DeliverLineForm, DeliverLineFormInit)
		} else if (type === 'edit') {
			msg.value = '修改配送路线'
		} else {
			msg.value = '查看配送路线'
			isShowBtn.value = false;
		}
	}
	defineExpose({
		init
	})
</script>

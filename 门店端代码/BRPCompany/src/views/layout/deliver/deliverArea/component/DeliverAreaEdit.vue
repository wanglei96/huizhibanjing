<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="600"
			draggable overflow>
			<el-form ref="DeliverAreaFormRef" :model="DeliverAreaForm" :rules="rules" label-width="150px"
				class="demo-DeliverAreaForm" status-icon>
				<el-form-item label="配送大区" prop="name">
					<el-input v-model="DeliverAreaForm.name" placeholder="请输入配送大区" />
				</el-form-item>
				<el-form-item label="描述" prop="description">
					<el-input v-model="DeliverAreaForm.description" type="textarea" placeholder="请输入描述" />
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="submitForm(DeliverAreaFormRef)"> 确定 </el-button>
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

	const dialogFormVisible = ref(false)
	const msg = ref()
	const refreshList = inject("getDataList");
	const companyId = common.getLocalStorage("companyId")
	const DeliverAreaFormRef = ref < FormInstance > ()
	const DeliverAreaForm = reactive({
		id: '',
		companyId: companyId,
		name: '',
		description: '',
		createTime: '',
		updateTime: ''
	})
	const DeliverAreaFormInit = reactive({
		id: '',
		companyId: companyId,
		name: '',
		description: '',
		createTime: '',
		updateTime: ''
	})

	const rules = reactive < FormRules > ({
		name: [{
			required: true,
			message: '配送大区不允许为空',
			trigger: 'change'
		}, ],
	})

	const submitForm = async (formEl: FormInstance | undefined) => {
		if (!formEl) return
		await formEl.validate((valid, fields) => {
			if (valid) {
				common.call("EditDeliverArea", DeliverAreaForm, function(result) {
					ElMessage({
						message: result.data,
						type: 'success',
					})
					dialogFormVisible.value = false
					refreshList();
				}, function(result) {
					ElMessage.error(result.errorMessage)
				})
			}
		})
	}

	// 编辑页初始化
	function init(type, data) {
		// 显示弹窗
		dialogFormVisible.value = true
		// 清除表单的校验规则
		this.$nextTick(() => {
			this.$refs.DeliverAreaFormRef.clearValidate();
		})
		// 表单渲染
		Object.assign(DeliverAreaForm, data)
		if (type === 'add') {
			msg.value = '新建配送大区'
			Object.assign(DeliverAreaForm, DeliverAreaFormInit)
		} else if (type === 'edit') {
			msg.value = '修改配送大区'
		}
	}
	defineExpose({
		init
	})
</script>

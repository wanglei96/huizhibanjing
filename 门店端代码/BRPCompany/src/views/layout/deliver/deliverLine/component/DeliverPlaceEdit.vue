<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="700"
			draggable overflow>
			<el-form ref="DeliverPlaceFormRef" :model="DeliverPlaceForm" :rules="rules" label-width="150px"
				class="demo-DeliverPlaceForm" status-icon>
				<el-form-item label="配送地点" prop="name">
					<el-input v-model="DeliverPlaceForm.name" placeholder="请输入配送地点(建议填小区名字)" />
				</el-form-item>

				<el-form-item label="详细地址" prop="description">
					<el-input v-model="DeliverPlaceForm.description"
						placeholder="请输入详细地址(配送地点怎么走,例如:XX路和XX路交叉口,东行100米路北)" />
				</el-form-item>
				<el-form-item label="排序" prop="displayOrder">
					<el-input v-model="DeliverPlaceForm.displayOrder" placeholder="请输入排序(自动按顺序规划路线)" />
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button v-show="isShowBtn" type="primary" @click="submitForm(DeliverPlaceFormRef)"> 确定
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
	ref,
	watch
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

const DeliverPlaceFormRef = ref<FormInstance>()
const DeliverPlaceForm = reactive({
	id: '',
	name: '',
	description: '',
	displayOrder: '',
	deliverLineId: '',
	createTime: '',
	updateTime: ''
})
const DeliverPlaceFormInit = reactive({
	id: '',
	name: '',
	description: '',
	displayOrder: '',
	deliverLineId: '',
	createTime: '',
	updateTime: ''
})


const rules = reactive<FormRules>({
	name: [{
		required: true,
		message: '配送地点不允许为空',
		trigger: 'change'
	},],
	description: [{
		required: true,
		message: '详细地址不允许为空',
		trigger: 'change'
	},],
	displayOrder: [{
		required: true,
		message: '排序不允许为空',
		trigger: 'change'
	},],
})

const submitForm = async (formEl: FormInstance | undefined) => {
	if (!formEl) return
	await formEl.validate((valid, fields) => {
		if (valid) {
			// 查询是否存在同名配送点
			let params = {
				condition: `name='${DeliverPlaceForm.name}'`,
				deliverLineId: DeliverPlaceForm.deliverLineId
			}
			common.call('GetViewDeliverPlaceList', params, function (result) {
				// id不为空修改，否则新增
				let dataList = result.data.dataList;
				const serviceName = ref();
				if (DeliverPlaceForm.id == '') {
					if (dataList.length > 0) {
						ElMessage.error('在' + dataList[0].deliverLineName +
							'下存在同名配送点');
						return;
					}
					serviceName.value = "AddDeliverPlace";
					DeliverPlaceForm.createTime = util.getFormatTime(new Date());
				} else {
					if (dataList.length > 0) {
						let exists = dataList.some(item => item.id === DeliverPlaceForm.id);
						if (!exists) {
							ElMessage.error('在' + dataList[0].deliverLineName +
								'下存在同名配送点');
							return;
						}
					}
					serviceName.value = "UpdateDeliverPlace";
					DeliverPlaceForm.updateTime = util.getFormatTime(new Date());
				}
				common.call(serviceName.value, DeliverPlaceForm, function (result) {
					dialogFormVisible.value = false
					refreshList(DeliverPlaceForm.deliverLineId);
				}, function (result) {
					console.log("配送点编辑失败");
				})

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
		this.$refs.DeliverPlaceFormRef.clearValidate();
	})
	// 表单渲染
	Object.assign(DeliverPlaceForm, data)
	// 隐藏确定按钮
	isShowBtn.value = true;
	if (type === 'add') {
		msg.value = '新建配送点'
		Object.assign(DeliverPlaceForm, DeliverPlaceFormInit)
		DeliverPlaceForm.deliverLineId = data
	} else if (type === 'edit') {
		msg.value = '修改配送点'
	} else {
		msg.value = '查看配送员'
		isShowBtn.value = false;
	}
}





defineExpose({
	init
})
</script>

<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="500"
			draggable overflow>
			<el-form ref="DeliverPlaceFormRef" :model="DeliverPlaceForm" :rules="rules" label-width="150px"
				class="demo-DeliverPlaceForm" status-icon>
				<el-form-item label="配送员" prop="delivererId">
					<el-select v-model="DeliverPlaceForm.delivererId" placeholder="请选择配送员" @change="delivererChange" filterable>
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

	const dialogFormVisible = ref(false);
	const msg = ref('会员转移至以下所选配送点');
	const reload: any = inject("reload");
	const companyId = common.getLocalStorage("companyId");

	const DeliverPlaceFormRef = ref < FormInstance > ()
	const DeliverPlaceForm = reactive({
		id: '',
		delivererId: '',
		deliverPlaceId: '',
		createTime: '',
		updateTime: ''
	})
	/* const DeliverPlaceFormInit = reactive({
	    id: '',
	    delivererId: '',
		deliverPlaceId:'',
	    createTime: '',
	    updateTime: ''
	}) */


	const rules = reactive < FormRules > ({
		delivererId: [{
			required: true,
			message: '配送员不允许为空',
			trigger: 'change'
		}, ],
		deliverPlaceId: [{
			required: true,
			message: '配送地点不允许为空',
			trigger: 'change'
		}, ],
	})

	// const submitForm = async (formEl: FormInstance | undefined) => {
	//   if (!formEl) return
	//   await formEl.validate((valid, fields) => {
	//     if (valid) {
	// 		let param = {
	// 			oldDeliverPlaceId: oldDeliverPlaceId.value,
	// 			delivererId:DeliverPlaceForm.delivererId,
	// 			newDeliverPlaceId: DeliverPlaceForm.deliverPlaceId
	// 		}
	// 	  common.call("EditMemberTransfer", param, function(result){
	// 	  	dialogFormVisible.value = false
	// 		// this.$emit('getInit')
	// 		// reload();
	// 	  },function(result){
	// 	  	console.log("会员转移失败");
	// 	  })
	//     }
	//   })
	// }

	const resetForm = (formEl: FormInstance | undefined) => {
		if (!formEl) return
		formEl.clearValidate()
	}

	// 配送员监听
	const DeliverPlaceData = ref([])
	const delivererChange = (value) => {
		// 配送地点下拉框列表渲染
		let param = {
			delivererId: value
		}
		common.call("GetViewDeliverPlaceList", param, function(result) {
			DeliverPlaceData.value = result.data.dataList;
		}, function(result) {
			console.log("配送地点获取失败");
		})
	}

	// 编辑页初始化
	const DelivererData = ref([])
	const oldDeliverPlaceId = ref()

	function init(data) {
		// 如果当前配送点会员数为0，那么不展示会员转移弹窗
		if (data.memberNum == 0) {
			ElMessage.error('当前配送点下没有会员')
			return
		}
		// 显示弹窗
		dialogFormVisible.value = true
		// 清除表单的校验规则
		this.$nextTick(() => {
			this.$refs.DeliverPlaceFormRef.clearValidate();
		})
		// 获取旧的配送点id
		oldDeliverPlaceId.value = data.id
		// 配送员下拉框列表渲染
		common.call("GetViewCompanyUserList", {
			companyId: companyId,
			isDeliverer: 1
		}, function(result) {
			DelivererData.value = result.data.dataList;
		}, function(result) {
			console.log("配送员获取失败");
		})
	}
	defineExpose({
		init
	})
</script>

<template>
	<el-form-item :label="props.label" :prop="formItemProp">
		<el-input clearable v-model="selectedText" class=" form-refer" :placeholder="props.placeholder"
			:disabled="props.disabled"
			v-if="props.controlType =='textbox' || props.controlType =='' || props.controlType == null"
			@click="showDialog" :suffix-icon="Search" />
	</el-form-item>
	<el-button type="primary" v-if="props.controlType =='button'" @click="showDialog()">{{props.buttonText}}</el-button>
	<el-dialog :close-on-click-modal="false" v-model="detailFormVisible" :title="props.title" width="1200" draggable
		:destroy-on-close="true">
		<el-card shadow="never">
			<div class="container">
				<div class="aside" v-if="selection[props.selectionName].showCategory"> <!-- v-if="" -->
					<div class="aside-menu" :style="{ width: isShow ? '265px' : '0px' }">
						<!-- <div class="tree-title" :style="{ width: isShow ? '265px' : '0px' }">
							{{selection[props.selectionName].category.rootNodeText}}
						</div> -->
						<el-scrollbar>
							<el-tree :data="treeData" :highlight-current="true" default-expand-all
								:expand-on-click-node="false" :props="defaultProps" node-key="id"
								@node-click="treeNodeClicked" />
						</el-scrollbar>
					</div>
					<div class="aside-spliter">
						<span class="arrow-box" @click="handleIsShow">
							<el-icon v-show="isShow">
								<ArrowLeft />
							</el-icon>
							<el-icon v-show="!isShow">
								<ArrowRight />
							</el-icon>
						</span>
					</div>
				</div>
				<div class="content">
					<el-card shadow="never" class="search-card">
						<el-form ref="refSearchForm" :model="searchForm">
							<el-row type="flex" :gutter="20">
								<el-col :xs="24" :sm="12" :md="8" :lg="6">
									<el-form-item v-for="queryField in selection[props.selectionName].queryFields"
										:label="queryField.text" :prop="queryField.fieldName">
										<el-input placeholder="请输入..." clearable v-model="searchForm.name"
											class="form-input"></el-input>
									</el-form-item>
								</el-col>
								<el-col :xs="24" :sm="12" :md="8" :lg="6">
									<el-form-item>
										<el-button type="primary" @click='query()'>搜索</el-button>
										<el-button @click='resetForm()'>重置</el-button>
									</el-form-item>
								</el-col>
							</el-row>
						</el-form>
					</el-card>
					<el-card shadow="never" class="grid-card">
						<el-alert v-if="!props.single" :title="`已选择 ${selectedItemCount} 项`"
							v-show="selectedItemCount > 0" type="warning" show-icon />
						<el-table ref="multipleTableRef" :data="dataList" border :table-layout="'auto'"
							style="width: 100%" @selection-change="selectRow" :default-selection="selectedRowKeys"
							:row-key="row => row[selectionConfig.valueFieldName]" @current-change="handleCurrentChange"
							@row-dblclick="rowDblclicked">
							<el-table-column type="selection" width="55" v-if="!props.single" />
							<el-table-column width="55" v-else>
								<template #default="scope">
									<el-radio v-model="currentRadio" :label="scope.row.id">
										<span></span>
									</el-radio>
								</template>
							</el-table-column>
							<el-table-column v-for="column in selection[props.selectionName].columns"
								:key="column.dataField" :prop="column.dataField" :label="column.headerText" />
							<!-- <el-table-column label="操作" align="center" width="120">
								<template #default="scope">
									<el-space spacer="|" style="color: #999" wrap>
										<el-link type="warning" :id="scope.row.id"
											@click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看
										</el-link>
									</el-space>
								</template>
							</el-table-column> -->
						</el-table>
						<!-- <el-pagination v-model:current-page="list.currentPage.value"
							v-model:page-size="list.pageSizeComputed.value" :page-sizes="list.pageSizes"
							:page-count="list.pageCount.value" :layout="list.layout" :total="list.recordCount.value"
							style="margin-top: 20px" /> -->
					</el-card>
				</div>
			</div>
		</el-card>
		<template #footer>
			<span class="dialog-footer">
				<el-button type="primary" @click.prevent="submit()"> 确定 </el-button>
				<el-button @click="detailFormVisible = false">取消</el-button>
			</span>
		</template>
	</el-dialog>
</template>

<script setup>
	import {
		ref,
		toRaw
	} from 'vue'
	import common from "@/js/common"
	import selection from "@/js/selection"
	import management from '@/js/management'
	import {
		Search
	} from '@element-plus/icons-vue'
	const props = defineProps({
		modelValue: {
			type: String,
			default: {}
		},
		//文本
		text: {
			type: String,
			default: ''
		},
		//参照名称
		selectionName: {
			type: String,
			required: true
		},
		//下拉框内占位符
		placeholder: {
			type: String,
			default: '请选择...'
		},
		//是否禁用
		disabled: {
			type: Boolean,
			default: false
		},
		//默认值
		defaultValue: {
			type: String
		},
		//text 文本框  button 按钮
		controlType: {
			type: String,
			default: ''
		},
		//按钮文字
		buttonText: {
			type: String,
			default: ''
		},
		//标题
		title: {
			type: String,
			default: ''
		},
		//说明
		label: {
			type: String,
			default: ''
		},
		//默认是单选  false=多选 true=单选
		single: {
			type: Boolean,
			default: false
		},
		//查询条件
		condition: {
			type: String,
			default: ''
		},
		//表单项prop
		formItemProp: {
			type: String,
			default: ''
		}
	})
	//原值
	var oldValue;
	//新值
	var newValue;
	const emit = defineEmits(['update:modelValue', "change"]);

	/**
	 * 值
	 */
	const value = computed({
		get() {
			return props.modelValue
		},
		set(val) {
			try {
				props.modelValue = val;
				emit('update:modelValue', val);
			} catch (e) {
				console.log(e);
			}
		}
	});
	const dataList = ref([]);
	const treeData = ref([]);
	let treeDataList = [];
	//当前选中组织机构节点
	const currentNodeId = ref();
	const detailFormVisible = ref(false) //列表开关
	let selectionConfig = selection[props.selectionName];
	let searchForm = reactive({
		name: '',
	});
	let pageIndex = 0
	let pageSize = 10
	const isShow = ref(true)
	const selectedItemList = ref([]);
	const selectedText = ref("");
	const currentRow = ref();
	const currentRadio = ref();
	const refLabel = ref("");

	function init() {
		console.log("props.modelValue:", toRaw(props.modelValue))
		oldValue = toRaw(props.modelValue);
		selectedText.value = props.text;
		console.log("props.label", props.label)
		refLabel.value = props.label;
		selectionConfig = selection[props.selectionName];
	}
	init();
	/**
	 * 绑定数据列表
	 */
	function bindData() {
		let requestData = {
			tableName: selectionConfig.tableName,
			pageIndex: pageIndex,
			pageSize: pageSize,
		}
		if (selectionConfig.fields) {
			requestData.fields = selectionConfig.fields;
		}
		var condition = getCondition();
		if (condition) {
			requestData.condition = condition;
		}
		if (selectionConfig.orderBy) {
			requestData.orderBy = props.orderBy;
		}
		common.call('GetTable', requestData, (res) => {
			dataList.value = res.data;
		})
	}

	/**
	 * 查询条件
	 * 由三部分组成
	 * 1.配置信息中的condition
	 * 2.属性中的condition
	 * 3.选中的树节点
	 */
	function getCondition() {
		var arrCondition = [];
		if (selectionConfig.condition) {
			arrCondition.push(selectionConfig.condition)
		}
		if (props.condition) {
			arrCondition.push(props.condition)
		}
		if (currentNodeId.value) {
			var inValues = common.getInValues(treeDataList, selectionConfig.category.parentValueFieldName, selectionConfig
				.category.valueFieldName, currentNodeId.value);
			var condition = selectionConfig.categoryValueFieldName + " in (" + inValues + ")";
			arrCondition.push(condition);
		}
		var condition = "";
		for (var i = 0; i < arrCondition.length; i++) {
			if (i > 0) {
				condition += " and ";
			}
			condition += "(" + arrCondition[i] + ")";
		}
		return condition;
	}

	function bindTreeData() {
		const data = {
			tableName: selectionConfig.category.tableName
		};
		if (selectionConfig.category?.condition) {
			data.condition = selectionConfig.category.condition;
		}
		if (selectionConfig.category.orderBy) {
			data.orderBy = selectionConfig.category.orderBy;
		}
		data.pageSize = 99999;
		common.call("GetTable", data, function(result) {
			treeDataList = result.data;
			var data = common.getTreeData({
				dataList: treeDataList,
				valueFieldName: selectionConfig.category.valueFieldName,
				textFieldName: selectionConfig.category.textFieldName,
				parentValueFieldName: selectionConfig.category.parentValueFieldName,
				parentValue: 0,
				rootNodeText: selectionConfig.category.rootNodeText
			});
			treeData.value = data;
		})
	}

	function showDialog() {
		detailFormVisible.value = true;
		//绑定树列表数据
		bindTreeData();
		//绑定列表数据
		bindData();
	}

	function handleIsShow() {
		isShow.value = !isShow.value
	}

	function query() { //查询
		bindData();
	}

	function resetForm() { //重置

	}
	const defaultProps = ref({ //左侧树配置,还有其他属性可参考Element Plus官方文档增加配置
		//指定子树为节点对象的某个属性值
		children: 'children',
		//指定节点标签为节点对象的某个属性值
		label: 'name'
	});
	/**
	 * 树点击事件
	 * 修改当前页触发计算属性重新加载数据
	 */
	const treeNodeClicked = (val) => {
		console.log("treeNodeClicked:" + val[selectionConfig.valueFieldName])
		currentNodeId.value = val[selectionConfig.valueFieldName];
		bindData(); //绑定列表数据
	}


	const selectRow = (val) => {
		selectedItemList.value = val;
		newValue = val;
	}
	const selectedItemCount = computed(() => { //多选选中数量
		return selectedItemList.value.length
	})

	function submit() { //提交
		if (oldValue != newValue) {
			emit("change", newValue, oldValue);
		}
		emit('reload-data', selectedItemList.value); //获取所有选中的行
		detailFormVisible.value = false;
		oldValue = newValue;
		value = newValue;
	}
	const selectedRowKeys = ref([]);


	const handleCurrentChange = (row) => { //单选事件
		selectedText.value = toRaw(row)[selectionConfig.textFieldName];
		newValue = toRaw(row)[selectionConfig.valueFieldName];
		//下面代码不再使用
		selectedItemList.value = []
		selectedItemList.value.push(row)
	};

	/**
	 * 行双击事件处理
	 * @param {Object} row
	 */
	function rowDblclicked(row) {
		if (props.single) {
			newValue = toRaw(row)[selectionConfig.valueFieldName];
			submit();
		}
	}
</script>

<style scoped lang="scss">
	.el-card {
		//height: 87vh;
		--el-card-padding: 0px;
		//padding: 0px 16px;
	}

	.search-card {
		margin-top: 15px;
		margin-bottom: 15px;
		padding: 10px;
		width: calc(100% - 20px);
		box-sizing: border-box;

		.el-form-item {
			margin-bottom: 0px;
		}
	}

	.el-row {
		row-gap: 10px;
	}

	.search-card {
		margin-bottom: 15px;

		.el-form-item {
			margin-bottom: 0px;
		}
	}

	.grid-card {
		padding: 10px;
		width: calc(100% - 20px);
		box-sizing: border-box;
	}



	.container {
		width: 100%;
		display: flex !important;

		.aside {
			display: flex;
			justify-content: space-between;

			.aside-menu {
				padding: 0 10px;
				flex: 1;
				border-right: 1px solid #ccc;
				height: 500px;
				// 主要靠下面两行实现效果
				overflow: hidden; // 很重要!!!!
				transition: 0.3s ease;

				.tree-title {
					line-height: 35px;
					text-align: center;
					border-bottom: 1px solid #ccc;
					overflow: hidden;
					transition: 0.3s ease;
					color: #606266;
				}
			}

			.aside-spliter {
				width: 15px;
				height: 50vh;
				display: flex;
				justify-content: center;
				align-items: center;

				.arrow-box {
					display: flex;
					justify-content: center;
					align-items: center;
					background-color: #ffffff;
					width: 20px;
					height: 20px;
					border: 1px solid #ccc;
					border-radius: 50%;
					margin-left: -15px;
					// z-index: 99999;
				}
			}
		}

		.content {
			flex: 1;
		}
	}

	.el-tree {
		--el-tree-node-content-height: 36px;
	}
</style>
 <script setup>
import common from '@/js/common'
import { reactive, ref, inject  } from 'vue';
import { ElTable, ElMessage  } from 'element-plus'



// 分页相关
const currentPage = ref(1)
const pageSize = ref(10)
const recordCount = ref();
const small = ref(false)
const background = ref(false)
const disabled = ref(false)
// 当前门店id
const companyId = common.getLocalStorage("companyId")
const dialogFormVisible = ref(false)
const msg = ref('继承他人路线')


// 分页监听
const handleSizeChange = (val) => {
  getDataList(delivererId.value);
}
const handleCurrentChange = (val) => {
  getDataList(delivererId.value);
}

// 获取会员列表
 const tableData = ref([])
 function getDataList(delivererId) {
	let param = {
			pageIndex:currentPage.value - 1,
			pageSize:pageSize.value,
			delivererId:delivererId,
			companyId:companyId
	}
	common.call("GetFilterViewDeliverLineList", param, function(result){
		tableData.value = result.data.dataList;
		pageSize.value = result.data.pageSize;
		recordCount.value = result.data.recordCount;
	},function(result){
		console.log("获取数据列表失败");
	}) 
 }


// 会员卡时长变更记录列表条件查询
const cardNoQuery = ref();
function queryDataList(){
	getDataList(cardNoQuery.value);
}

// 查询条件重置
function resetQuery(){
	cardNoQuery.value = ''
}

const delivererId = ref();
function init(data) {  
	// 显示弹窗
	dialogFormVisible.value = true
	delivererId.value = data.id
	getDataList(delivererId.value);
}


const multipleSelection = ref([])
 const handleSelectionChange = (val) => {
  multipleSelection.value = val
}

const submitForm = (val) => {
	if(val.length === 0){
		ElMessage.error('请选择要继承的路线')
		return
	}
	val.map((item, index) =>{
		let param = {
			id: item.id,
			delivererId: delivererId.value
		}
		common.call("UpdateDeliverLine", param, function(result){
		 dialogFormVisible.value = false
		 getDataList();
		},function(result){
			console.log("继承他人路线失败");
		}) 
	})
}

defineExpose({
  init
})
</script>
<template>
  <div>
	   <el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="50%" draggable>
 
 <!--   <el-card shadow="never" class="user-search-card">
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
      <el-table
        :data="tableData"
        style="width: 100%"
		 @selection-change="handleSelectionChange"
		>
		  <el-table-column type="selection" width="55" />
        <el-table-column property="name" label="线路名称" align="center" />
		<el-table-column property="description" label="描述" align="center" />
		<el-table-column property="delivererName" label="配送员" align="center" />
		<el-table-column property="deliverAreaName" label="配送大区" align="center"/>
      </el-table>
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[10, 50, 100]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="recordCount"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        style="margin-top: 20px"
      />
    </el-card>
	<template #footer>
	     <span class="dialog-footer">
		   <el-button type="primary" @click="submitForm(multipleSelection)"> 确定 </el-button>
	       <el-button @click="dialogFormVisible = false">取消</el-button>
	     </span>
	   </template>
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
 <script setup>
import common from '@/js/common'
import { reactive, ref, inject, nextTick} from 'vue';
import Sortable from "sortablejs";
const { proxy } = getCurrentInstance();
// 分页相关
const currentPage = ref(1)
const pageSize = ref(10)
const recordCount = ref();
const small = ref(false)
const background = ref(false)
const disabled = ref(false)
const companyId = localStorage.getItem("companyId")
const dialogFormVisible = ref(false)
const msg = ref('配送单列表')

// 分页监听
const handleSizeChange = (val) => {
  getDataList(deliverLineId.value);
}
const handleCurrentChange = (val) => {
  getDataList(deliverLineId.value);
}

// 获取会员列表
 const tableData = ref([])
 function getDataList(bookOrderId) {
	let param = {
			pageIndex:currentPage.value - 1,
			pageSize:pageSize.value,
			bookOrderId:bookOrderId,
			bookOrderTypeCode:'Return'
	}
	common.call("GetViewWaybillItemList", param, function(result){
		tableData.value = result.data.dataList;
		pageSize.value = result.data.pageSize;
		recordCount.value = result.data.recordCount;
	},function(result){
		console.log("获取配送单列表失败");
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

const bookOrderId = ref();
function init(data) {  
	// 显示弹窗
	dialogFormVisible.value = true
	bookOrderId.value = data.id
	getDataList(bookOrderId.value);
}

/**
 * 操作状态事件
 * @param {Object} data 
 */
function confirmBtnClick(data){
	let params = {
		id: data.id,
		deliverStatusCode: 'Storageed'
	}
common.call("UpdateBookOrderItem", params, function(result){
		 ElMessage({
		    message: '状态变更成功',
		    type: 'success',
		  })
		  getDataList(data.bookOrderId);
	  	},function(result){
			ElMessage.error('状态变更失败')
	}) 
}

defineExpose({
  init
})
</script>
<template>
  <div class="user">
	   <el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="50%" draggable overflow>
    <el-card shadow="never" class="user-list-card">
      <el-table
        :data="tableData">
        <el-table-column property="bookName" label="书名" align="center" />
		<el-table-column property="bindingTypeName" label="装订方式" align="center" />
		<el-table-column property="bookcaseName" label="书柜" align="center" />
		<el-table-column property="bookNo" label="书号" align="center" />
		<el-table-column  label="会员下单时间" align="center" >
			 <template #default="scope">
				 {{scope.row.bookOrderTypeName}}:{{scope.row.createTimeText}}
			 </template>
		</el-table-column>
		<el-table-column property="deliverStatusName" label="状态" align="center" />
		<el-table-column label="操作" align="center" >
		  <template #default="scope">
			  <el-link type="primary" :id="scope.row.id" @click="confirmBtnClick(scope.row)">确认入库</el-link>
		  </template>
		</el-table-column>
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
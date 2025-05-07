 <script setup>
import common from '@/js/common'
import { reactive, ref, inject  } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'
// 声明配送单详情页
import ReturnBookOrderDetail from './component/ReturnBookOrderDetail.vue'
const ReturnBookOrderDetailRef = ref();
// 分页相关
const currentPage = ref(1);
const pageSize = ref(10);
const recordCount = ref();
const small = ref(false);
const background = ref(false);
const disabled = ref(false);
// 当前门店id
const companyId = common.getLocalStorage("companyId");

// 首次页面加载获取列表
getDataList();
getDeliverAreaData();

// 分页监听
const handleSizeChange = (val) => {
  getDataList();
}
const handleCurrentChange = (val) => {
  getDataList();
}

// 获取会员卡类型列表
 const tableData = ref([])
 function getDataList(deliverAreaId) {
	let param = {
			pageIndex:currentPage.value - 1,
			pageSize:pageSize.value,
			companyId:companyId,
			deliverAreaId: deliverAreaId,
			condition: 'book_order_return_status_code is not null'
	}
	common.call("GetViewWaybillList", param, function(result){
		tableData.value = result.data.dataList;
		pageSize.value = result.data.pageSize;
		recordCount.value = result.data.recordCount;
	},function(result){
		console.log("获取数据列表失败");
	}) 
 }

// 锁定账号监听
function enabledChange(data){
	if(data.enabled){
		data.enabled = false;
	}else{
		data.enabled = true;
	}
	let params = {
		id: data.memberId,
		enabled: data.enabled
	}
	common.call("UpdateMember", params, function(result){
		getDataList();
	  	},function(result){
	  		console.log("会员书箱锁定状态修改失败");
	}) 
}

/**
 * 获取大区街道数据
 */
let deliverAreaData = ref();
let deliverAreaIdQuery = ref();
function getDeliverAreaData(){
	common.call("GetDeliverAreaList", {companyId: companyId}, function(result){
		deliverAreaData.value = result.data.dataList;
	  	},function(result){
	  		console.log("获取大区街道失败");
	}) 
}

/**
 * 大区街道监听事件
 * @param {Object} value 大区街道id
 */
function deliverAreaChanged(value){
	getDataList(value);
}

</script>
<template>
  <div class="user">
	  <ReturnBookOrderDetail ref="ReturnBookOrderDetailRef" />
    <el-card shadow="never" class="user-search-card">
	      <el-button type="primary" @click="getDataList()">全部订单</el-button>
			<el-select v-model="deliverAreaIdQuery" placeholder="大区街道" style="margin-left: 12px;" @change="deliverAreaChanged" filterable>
			   <el-option key="" label="全部" value=""/>
			   <el-option
			     v-for="item in deliverAreaData"
			     :key="item.id"
			     :label="item.name"
			     :value="item.id"/>
			 </el-select>
    </el-card>
    <el-card shadow="never" class="user-list-card">
      <el-table
        :data="tableData"
        style="width: 100%">
		<el-table-column type="index" label="号数" align="center" width="55px" />
        <el-table-column property="deliverAreaName" label="街道名称" align="center" />
		<el-table-column property="name" label="会员姓名" align="center" />
		<el-table-column property="memberNo" label="会员卡号" align="center" />
		<el-table-column property="mobilePhone" label="联系电话" align="center" />
		<!-- <el-table-column property="jieNum" label="送几本" align="center" /> -->
		<el-table-column property="huanNum" label="取几本" align="center" />
		<el-table-column property="entranceGuard" label="门禁卡" align="center" />
		<el-table-column property="deliverAreaName" label="大区街道名称" align="center" />
		<el-table-column property="address" label="会员详细地址" align="center" />
        <el-table-column label="操作" align="center" >
          <template #default="scope">
			  <el-link type="primary" :id="scope.row.id" @click="ReturnBookOrderDetailRef.init(scope.row)">会员订单详细</el-link>
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
		margin-left: 3px;
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

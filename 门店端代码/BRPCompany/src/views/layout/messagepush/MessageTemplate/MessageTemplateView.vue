 <script setup>
import MessageTemplateEdit from './component/MessageTemplateEdit.vue'
import common from '@/js/common'
import { reactive, ref, inject  } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'
// 声明会员卡类型编辑页
const MessageTemplateEditRef = ref()
// 页面重载
provide('getDataList', getDataList);
// 分页相关
const currentPage = ref(1)
const pageSize = ref(10)
const recordCount = ref()
const small = ref(false)
const background = ref(false)
const disabled = ref(false)
// 当前门店id
const companyId = common.getLocalStorage("companyId")

// 首次页面加载获取列表
getDataList();

// 分页监听
const handleSizeChange = (val) => {
  getDataList();
}
const handleCurrentChange = (val) => {
  getDataList();
}

// 获取消息模板列表
 const tableData = ref([])
 function getDataList(pusherCodeQuery, contentQuery) {
	let param = {
			pageIndex:currentPage.value - 1,
			pageSize:pageSize.value,
			companyId:companyId,
			pusherCode:pusherCodeQuery,
			content: contentQuery
	}
	common.call("GetViewMessageTemplateList", param, function(result){
		tableData.value = result.data.dataList;
		pageSize.value = result.data.pageSize;
		recordCount.value = result.data.recordCount;
	},function(result){
		console.log("获取数据列表失败");
	}) 
 }

// 消息模板删除
function DeliverAreaDel(id){
	ElMessageBox.confirm(
	   '确认删除消息模板?',
	   {
		 confirmButtonText: '确定',
	     cancelButtonText: '取消',
	     type: 'warning',
	   })
	   .then(() => {
				  common.call("DeleteMessageTemplate", {id:id}, function(result){
				  	 ElMessage({
				  	     message: '消息模板删除成功',
				  	     type: 'success',
				  	   })
				  	 getDataList();
				  },function(result){
					  ElMessage.error('消息模板删除失败')
				  })
	   })
}

// 消息模板列表条件查询
const pusherCodeQuery = ref();
const contentQuery = ref();
function queryDataList(){
	getDataList(pusherCodeQuery.value,contentQuery.value);
}

// 查询条件重置
function resetQuery(){
	pusherCodeQuery.value = ''
	contentQuery.value = ''
}
</script>
<template>
  <div class="user">
    <MessageTemplateEdit ref="MessageTemplateEditRef" />
    <el-card shadow="never" class="user-search-card">
      <el-form :inline="true" class="user-form">
        <el-form-item label="推送人员">
          <el-input placeholder="请输入推送人员" v-model="pusherCodeQuery"></el-input>
        </el-form-item>
		<el-form-item label="内容">
		  <el-input placeholder="请输入内容" v-model="contentQuery"></el-input>
		</el-form-item>
        <el-form-item>
          <el-button type="primary" @click="queryDataList()">查询</el-button>
          <el-button @click="resetQuery()">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card shadow="never" class="user-list-card">
      <template #header>
        <div class="card-header">
          <div>
			<el-button type="primary" @click="MessageTemplateEditRef.init('add')"
			  ><el-icon style="margin-right: 3px"><Plus /></el-icon> 新建</el-button>
          </div>
          <div
            style="width: 80px; display: flex; justify-content: space-around; margin-right: 20px">
          </div>
        </div>
      </template>
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column property="pusherCode" label="推送人员" align="center" />
		<el-table-column property="pushTimeText" label="推送时间" align="center" />
		<el-table-column property="pushInterval" label="时间间隔" align="center" />
        <el-table-column property="pushTypeName" label="推送方式" align="center" />
		<el-table-column property="messageTypeName" label="消息类型" align="center" />
        <el-table-column label="操作" align="center" >
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
			 <el-link type="warning" :id="scope.row.id" @click="MessageTemplateEditRef.init('view',scope.row)">查看</el-link>
			  <el-link type="primary" :id="scope.row.id" @click="MessageTemplateEditRef.init('edit',scope.row)">修改</el-link>
              <el-link type="danger" @click="DeliverAreaDel(scope.row.id)">删除</el-link></el-space>
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

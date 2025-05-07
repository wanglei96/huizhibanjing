 <script setup>
import MessageEdit from './component/MessageEdit.vue'


import common from '@/js/common'
import { reactive, ref, inject  } from 'vue';
import { ElMessage, ElMessageBox  } from 'element-plus'
// 声明会员卡类型编辑页
const MessageEditRef = ref()
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

// 获取消息列表
 const tableData = ref([])
 function getDataList(nameQuery, contentQuery) {
	let param = {
			pageIndex: currentPage.value - 1,
			pageSize: pageSize.value,
			companyId: companyId,
			name: nameQuery,
			content: contentQuery
	}
	common.call("GetViewMessageList", param, function(result){
		tableData.value = result.data.dataList;
		pageSize.value = result.data.pageSize;
		recordCount.value = result.data.recordCount;
	},function(result){
		console.log("获取数据列表失败");
	}) 
 }

// 消息列表条件查询
const nameQuery = ref();
const contentQuery = ref();
function queryDataList(){
	getDataList(nameQuery.value,contentQuery.value);
}

// 查询条件重置
function resetQuery(){
	nameQuery.value = ''
	contentQuery.value = ''
}
</script>
<template>
  <div class="user">
    <MessageEdit ref="MessageEditRef" />
    <el-card shadow="never" class="user-search-card">
      <el-form :inline="true" class="user-form">
        <el-form-item label="会员名称">
          <el-input placeholder="请输入会员名称" v-model="nameQuery"></el-input>
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
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column property="name" label="会员信息" align="center" />
		<el-table-column property="pushTimeText" label="推送时间" align="center" />
		<el-table-column property="content" label="内容" align="center" />
        <el-table-column label="操作" align="center" >
          <template #default="scope">
			  <el-link type="warning" :id="scope.row.id" @click="MessageEditRef.init(scope.row)">查看</el-link>
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

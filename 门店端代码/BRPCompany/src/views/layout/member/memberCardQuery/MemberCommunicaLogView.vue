 <script setup>
import MemberCommunicaLogEdit from './component/MemberCommunicaLogEdit.vue'
import common from '@/js/common'
import { reactive, ref, inject  } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'
// 页面重载
provide('getDataList', getDataList);
// 声明会员卡类型编辑页
const MemberCommunicaLogEditRef = ref()
// 分页相关
const currentPage = ref(1)
const pageSize = ref(10)
const recordCount = ref()
const small = ref(false)
const background = ref(false)
const disabled = ref(false)
// 当前门店id
const companyId = common.getLocalStorage("companyId")

const dialogFormVisible = ref(false)
// 首次页面加载获取列表
getDataList();

// 分页监听
const handleSizeChange = (val) => {
  getDataList();
}
const handleCurrentChange = (val) => {
  getDataList();
}

// 获取会员卡类型列表
 const tableData = ref([])
 const memberId = ref();
 function getDataList(memberId) {
	let param = {
			pageIndex:currentPage.value - 1,
			pageSize:pageSize.value,
			memberId:memberId
	}
	common.call("GetViewMemberCommunicaLogList", param, function(result){
		tableData.value = result.data.dataList;
		pageSize.value = result.data.pageSize;
		recordCount.value = result.data.recordCount;
	}) 
 }

function init(data) {
		// 显示弹窗
		dialogFormVisible.value = true;
		getDataList(data.memberId);
		memberId.value = data.memberId;
	}
	
defineExpose({
		init
	})
</script>
<template>
  <div class="user">
    <MemberCommunicaLogEdit ref="MemberCommunicaLogEditRef" />
	<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="50%" draggable>
    <el-card shadow="never" class="user-list-card">
      <template #header>
        <div class="card-header">
          <div>
			<el-button type="primary" @click="MemberCommunicaLogEditRef.init('add', memberId)"
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
        <el-table-column property="memberCommunicaLogTypeName" label="沟通类别" align="center" />
        <el-table-column property="companyUserName" label="沟通人员" align="center" />
		<el-table-column property="memberCommunicaLogMethodName" label="沟通渠道" align="center" />
		<el-table-column property="communicaTimeText" label="沟通时间" align="center" />
        <el-table-column label="操作" align="center" >
          <template #default="scope">
		   <el-space spacer="|" style="color: #999" wrap>
		   	<el-link type="warning" :id="scope.row.id" @click="MemberCommunicaLogEditRef.init('view',scope.row)">查看</el-link>
		     <el-link type="primary" :id="scope.row.id" @click="MemberCommunicaLogEditRef.init('edit',scope.row)">修改</el-link>
			 </el-space>
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

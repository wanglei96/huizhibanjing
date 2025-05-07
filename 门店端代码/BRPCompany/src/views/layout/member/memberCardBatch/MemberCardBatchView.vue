<script setup>
import MemberCardBatchEdit from './component/MemberCardBatchEdit.vue'
import common from '@/js/common'
import { reactive, ref, inject } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'
// 声明会员卡批次编辑页
const MemberCardBatchEditRef = ref()
// 页面重载
provide('getDataList', getDataList);
// 分页相关
const currentPage = ref(1)
const pageSize = ref(10)
const recordCount = ref();
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

// 获取会员卡批次列表
const tableData = ref([])
function getDataList(nameQuery, batchNoQuery, isOnlineReadingQuery) {
  let param = {
    pageIndex: currentPage.value - 1,
    pageSize: pageSize.value,
    companyId: companyId,
    name: nameQuery,
    batchNo: batchNoQuery,
    isOnlineReading: isOnlineReadingQuery,
    orderBy: 'batch_no',
  }
  common.call("GetMemberCardBatchList", param, function (result) {
    tableData.value = result.data.dataList;
    pageSize.value = result.data.pageSize;
    recordCount.value = result.data.recordCount;
  })
}


// 会员卡批次删除
function memberCardBatchDel(id) {
  ElMessageBox.confirm(
    '确认删除会员卡批次?',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      common.call("GetMemberCardList", { memberCardBatchId: id }, function (result) {
        if (result.data.dataList.length > 0) {
          ElMessage.error("当前会员卡批次存在会员卡信息，不可删除");
        } else {
          common.call("DeleteMemberCardBatch", { id: id }, function (result) {
            ElMessage({
              message: '会员卡批次删除成功',
              type: 'success'
            })
            getDataList();
          })
        }
      })
    })
}

// 会员卡批次列表条件查询
const nameQuery = ref();
const batchNoQuery = ref();
const isOnlineReadingQuery = ref();
function queryDataList() {
  getDataList(nameQuery.value, batchNoQuery.value, isOnlineReadingQuery.value);
}

// 查询条件重置
function resetQuery() {
  nameQuery.value = ''
  batchNoQuery.value = ''
  isOnlineReadingQuery.value = ''
}
</script>
<template>
  <div class="user">
    <MemberCardBatchEdit ref="MemberCardBatchEditRef" />
    <el-card shadow="never" class="user-search-card">
      <el-form :inline="true" class="user-form">
        <el-form-item label="批次名">
          <el-input placeholder="请输入" v-model="nameQuery" @keydown.enter.prevent=queryDataList()></el-input>
        </el-form-item>
        <el-form-item label="批次编号">
          <el-input placeholder="请输入" v-model="batchNoQuery" @keydown.enter.prevent=queryDataList()></el-input>
        </el-form-item>
        <el-form-item label="是否能在线借阅">
          <el-select placeholder="请选择" v-model="isOnlineReadingQuery" filterable>
            <el-option label="是" value="1"></el-option>
            <el-option label="否" value="0"></el-option>
          </el-select>
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
            <el-button type="primary" @click="MemberCardBatchEditRef.init('add')"><el-icon style="margin-right: 3px">
                <Plus />
              </el-icon> 新建</el-button>
          </div>
          <div style="width: 80px; display: flex; justify-content: space-around; margin-right: 20px">
          </div>
        </div>
      </template>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column property="name" label="批次名" align="center" />
        <el-table-column property="batchNo" label="批次编号" align="center" />
        <el-table-column property="color" label="批次颜色" align="center" />
        <el-table-column property="borrowQuantity" label="最大借阅次数" align="center" />
        <el-table-column property="isOnlineReading" label="是否能在线借阅" align="center">
          <template #default="scope">
            <span v-if="scope.row.isOnlineReading === true">是</span>
            <span v-else-if="scope.row.isOnlineReading === false">否</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id"
                @click="MemberCardBatchEditRef.init('view', scope.row)">查看</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="MemberCardBatchEditRef.init('edit', scope.row)">修改</el-link>
              <el-link type="danger" @click="memberCardBatchDel(scope.row.id)">删除</el-link></el-space>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[10, 50, 100]"
        :small="small" :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper"
        :total="recordCount" @size-change="handleSizeChange" @current-change="handleCurrentChange"
        style="margin-top: 20px" />
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.el-input {
  width: 200px;
}

.el-select {
  width: 200px;
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

<script setup>
import common from '@/js/common'
import { reactive, ref, inject } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'
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

// 获取会员卡时长变更记录列表
const tableData = ref([])
function getDataList(cardNoQuery) {

  let param = {
    pageIndex: currentPage.value - 1,
    pageSize: pageSize.value,
    companyId: companyId,
    memberCardNo: cardNoQuery,
	memberCardServiceTimeTypeCode : 'AddBorrowNumber',
    orderBy:"create_time desc"
  }
  common.call("GetViewMemberCardServiceTimeLogList", param, function (result) {
    tableData.value = result.data.dataList;
    pageSize.value = result.data.pageSize;
    recordCount.value = result.data.recordCount;
  })
}


// 会员卡时长变更记录删除
function memberCardServiceTimeLogDel(id) {
  ElMessageBox.confirm(
    '确认删除会员卡时长变更记录?',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      const param = {
        id: id
      }
      common.call("DeleteMemberCardServiceTimeLog", param, function (result) {
        ElMessage({
          message: '会员卡时长变更记录删除成功',
          type: 'success',
        })
        getDataList();
      }, function (result) {
        ElMessage.error('会员卡时长变更记录删除失败')
      })
    })
}

// 会员卡时长变更记录列表条件查询
const cardNoQuery = ref();
function queryDataList() {
  getDataList(cardNoQuery.value);
}

// 查询条件重置
function resetQuery() {
  cardNoQuery.value = ''
}

</script>
<template>
  <div class="user">
    <el-card shadow="never" class="user-search-card">
      <el-form :inline="true" class="user-form">
        <el-form-item label="会员卡号">
          <el-input placeholder="请输入" v-model="cardNoQuery" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="queryDataList()">查询</el-button>
          <el-button @click="resetQuery()">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card shadow="never" class="user-list-card">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column property="memberCardNo" label="会员卡号" align="center" />
        <el-table-column property="memberName" label="会员名称" align="center" />
        <el-table-column property="days" label="增减本数" align="center" />
        <el-table-column property="memberCardServiceTimeTypeName" label="类型" align="center" />
        <el-table-column property="cost" label="收费" align="center" />
        <el-table-column property="paymentTypeName" label="支付方式" align="center" />
        <el-table-column property="remark" label="备注" align="center" />
        <el-table-column property="createTimeText" label="时间" align="center" />
        <!-- <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="danger" @click="memberCardServiceTimeLogDel(scope.row.id)">删除</el-link></el-space>
          </template>
        </el-table-column> -->
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

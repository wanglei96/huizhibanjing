<script setup>
import common from '@/js/common'
import { reactive, ref, inject } from 'vue';

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
  getDataList(cardNoQuery.value,nameQuery.value);
}
const handleCurrentChange = (val) => {
  getDataList(cardNoQuery.value,nameQuery.value);
}

// 获取会员卡停卡记录列表
const tableData = ref([])
function getDataList(cardNoQuery,nameQuery) {

  let param = {
    pageIndex: currentPage.value - 1,
    pageSize: pageSize.value,
    companyId: companyId,
    memberCardNo: cardNoQuery,
    memberName: nameQuery,
    orderBy: "create_time desc"
  }
  common.call("GetViewMemberCardDisableLogList", param, function (result) {
    tableData.value = result.data.dataList;
    pageSize.value = result.data.pageSize;
    recordCount.value = result.data.recordCount;
  })
}

// 会员卡停卡记录列表条件查询
const cardNoQuery = ref();
const nameQuery = ref();
function queryDataList() {
  getDataList(cardNoQuery.value,nameQuery.value);
}

// 查询条件重置
function resetQuery() {
  cardNoQuery.value = ''
  nameQuery.value = ''
}

</script>
<template>
  <div class="user">
    <el-card shadow="never" class="user-search-card">
      <el-form :inline="true" class="user-form">
        <el-form-item label="会员卡号">
          <el-input placeholder="请输入" v-model="cardNoQuery" @keydown.enter.prevent=queryDataList()></el-input>
        </el-form-item>
        <el-form-item label="会员名称">
          <el-input placeholder="请输入" v-model="nameQuery" @keydown.enter.prevent=queryDataList()></el-input>
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
        <el-table-column property="days" label="停卡时长" align="center" />
        <el-table-column property="stopDateText" label="停卡日期" align="center" />
        <el-table-column property="stopEndDateText" label="到期日期" align="center" />
        <el-table-column property="status" label="状态" align="center">
          <template #default="scope">
            <span v-if="scope.row.status === 'transact'">办理停卡</span>
            <span v-else-if="scope.row.status === 'reissue'">补办停卡</span>
            <span v-else-if="scope.row.status === 'givestopcard'">赠送停卡次数</span>
          </template>
        </el-table-column>

        <el-table-column property="remark" label="备注" align="center" />
        <el-table-column property="applyTimeText" label="办理时间" align="center" />
        <el-table-column property="source" label="来源" align="center">
          <template #default="scope">
            <span v-if="scope.row.source === 'AdminStopCard'">管理员停卡</span>
            <span v-if="scope.row.source === 'MemberStopCard'">会员自助办理停卡</span>
            <span v-if="scope.row.source === 'MemberRenew'">会员续费</span>
          </template>
        </el-table-column>
        <el-table-column property="type" label="类型" align="center">
          <template #default="scope">
            <span style="color: green;" v-if="scope.row.type === 'augment'">增加</span>
            <span style="color: red;" v-if="scope.row.type === 'decrease'">减少</span>
          </template>
        </el-table-column>
        <el-table-column property="stopCardNum" label="停卡次数" align="center" />
        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <router-link :to="{ path: '/bookOrder/BorrowReturnBook', query: { memberCardNo: scope.row.memberCardNo } }">
                查看是否有未还的书
              </router-link>
            </el-space>
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

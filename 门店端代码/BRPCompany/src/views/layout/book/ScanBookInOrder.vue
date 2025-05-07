<script setup>
import common from '@/js/common'
import { reactive, ref, inject } from 'vue';
import successSound from '@/assets/mp3/success.mp3';
import errorSound from '@/assets/mp3/error.mp3';
import router from '@/router/index.js'
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
// 图书编号条件查询
const bookSnQuery = ref();
const bookInOrderList = ref([]);
const isShowbookInOrder = ref(false);
const isEnabled = ref(true);

const isLoading = ref(false);
function queryDataList() {
  if (!bookSnQuery.value) {
    ElMessage({ message: "书号不能为空", type: 'warning' })
    return;
  }
  var param = {
    bookSn: bookSnQuery.value,
    isEnabled: isEnabled.value,
    companyId: companyId.value
  }
  isLoading.value = true;
  common.call("AddScanBookInOrder", param, function (result) {
    ElMessage({ message: "入库成功", type: 'success' })
    setTimeout(() => {
      isLoading.value = false
    }, 200)
    bookInOrderList.value.unshift(result.data);
    isShowbookInOrder.value = true;
    bookSnQuery.value = '';
    const audio = new Audio(successSound);
    audio.play();
  }, function (error) {
    var errorMessage = error.errorMessage || error.error_message;
    //输出错误信息
    if (error.messageType === "warning") {
      common.showWarning(errorMessage);
    } else {
      common.showError(errorMessage);
    }
    const audio = new Audio(errorSound);
    audio.play();
    bookSnQuery.value = '';
    setTimeout(() => {
      isLoading.value = false
    }, 100)
  })
}

const handleEnter = () => {
  queryDataList();
};



// 查询条件重置
function resetQuery() {
  bookSnQuery.value = '';
}

const viewMemberInfo = (memberCardNo) => {
  router.push({
    path: '/bookOrder/BorrowReturnBook',
    query: {
      memberCardNo: memberCardNo,
    }
  })
}

</script>
<template>
  <div class="user">
    <el-card shadow="never" class="user-search-card">
      <div style="display: flex; justify-content: space-between; align-items: center;">
        <div>
          <el-form :inline="true" class="user-form">
            <el-form-item label="">
              <el-input placeholder="书籍编号" v-model="bookSnQuery" @keydown.enter.prevent="queryDataList()"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="queryDataList()" :loading="isLoading">查询</el-button>
              <el-button @click="resetQuery()">重置</el-button>
            </el-form-item>
          </el-form>
          <el-switch v-model="isEnabled" inactive-text="是否上架：" />
        </div>
        <div v-if="bookInOrderList.length > 0">
          <span style="color: red;font-size: 40px;">总数：{{ bookInOrderList.length }}</span>
        </div>
      </div>
    </el-card>
    <el-card shadow="never" class="user-list-card" v-if="isShowbookInOrder">
      <!-- <template #header>
        <div style="display: flex; justify-content: flex-end; align-items: center;">
          <span style="color: red;font-size: 40px;">总数：{{ bookInOrderList.length }}</span>
        </div>
      </template> -->
      <el-table :data="bookInOrderList" style="width: 100%">
        <el-table-column property="memberInfo" label="会员信息" width="200">
          <template #default="scope">
            <div @click="viewMemberInfo(scope.row.memberCardNo)">
              <div>
                <span style="color: #5799d1;">{{ scope.row.memberName }}</span>
              </div>
              <div>
                <span style="color: #5799d1;">{{ scope.row.memberCardNo }}</span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="bookName" label="书名" width="500">
          <template #default="scope">
            <div>
              <span style="color: #008000;">{{ scope.row.bookSeriesName ? "[" + scope.row.bookSeriesName + "]" : ""
                }}</span>
              <span>{{ scope.row.bookName }}</span>
              <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
                }}</span>
            </div>
            <div>
              <span>{{ scope.row.bookSn }}</span>
            </div>
            <div>
              <span>{{ scope.row.bookcaseName }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="memberInfo" label="位置">
          <template #default="scope">
            <div>
              <span style="color: red;">{{ scope.row.bookcaseName }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="bookInOrderStatusName" label="状态">
          <template #default="scope">
            <div>
              <span style="color: green;">{{ scope.row.bookInOrderStatusName }}</span>
            </div>
            <div v-if="scope.row.showUnlock">
              <span style="color: red;">{{ scope.row.memberCardStatusCode === 'Normal' ? "已解锁" : "账号锁定，还需归还" +
            scope.row.borrowingQuantity +
            "本书"
                }}</span>
            </div>
          </template>
        </el-table-column>
      </el-table>
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

.infolist {
  display: flex;
  height: 28px;
  line-height: 28px;
  justify-content: flex-start;
  align-items: center;
  font-size: 14px;

  span {
    display: inline-block;
    margin-right: 6px;
  }
}
</style>

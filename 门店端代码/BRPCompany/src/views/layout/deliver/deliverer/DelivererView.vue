<script setup>
import ExtendLineView from './ExtendLineView.vue'
import DelivererEdit from './component/DelivererEdit.vue'


import common from '@/js/common'
import { reactive, ref, inject } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'
// 声明会员卡类型编辑页
const ExtendLineViewRef = ref()
const DelivererEditRef = ref()
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
function getDataList(nameQuery, deliverAreaIdQuery) {
  let param = {
    pageIndex: currentPage.value - 1,
    pageSize: pageSize.value,
    companyId: companyId,
    name: nameQuery,
    deliverAreaId: deliverAreaIdQuery,
    condition: "is_deleted is not true"
  }
  common.call("GetViewDelivererList", param, function (result) {
    tableData.value = result.data.dataList;
    pageSize.value = result.data.pageSize;
    recordCount.value = result.data.recordCount;
  })
}

const DelivererDel = (id) => {
  ElMessageBox.confirm('确定要删除这条数据吗？', '温馨提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(() => {
      let params = {
        id: id,
        tableName: 'company_user'
      }
      common.call('HideIdTable', params, (result) => {
        getDataList();
      })
    })
    .catch((error) => {
      throw new Error(error.message)
    })
}

// 配送员删除
/* function DelivererDel(id) {
  ElMessageBox.confirm(
    '确认删除配送员?',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      //判断是否存在订单
      const param = {
        id: id
      }
      common.call("DeleteCompanyUser", param, function (result) {
        ElMessage({
          message: '配送员删除成功',
          type: 'success',
        })
        getDataList();
      })
    })
} */

// 会员卡类型列表条件查询
const nameQuery = ref();
const deliverAreaIdQuery = ref();
function queryDataList() {
  getDataList(nameQuery.value, deliverAreaIdQuery.value);
}

// 查询条件重置
function resetQuery() {
  nameQuery.value = ''
  deliverAreaIdQuery.value = ''
}

// 获取配送大区列表
const DeliverAreaData = ref([])
function getDeliverAreaData() {
  // 配送大区下拉框列表渲染
  common.call("GetDeliverAreaList", { companyId: companyId }, function (result) {
    DeliverAreaData.value = result.data.dataList;
  }, function (result) {
    console.log("配送大区获取失败");
  })
}

</script>
<template>
  <div class="user">
    <DelivererEdit ref="DelivererEditRef" />
    <ExtendLineView ref="ExtendLineViewRef" />


    <el-card shadow="never" class="user-search-card">
      <el-form :inline="true" class="user-form">
        <el-form-item label="姓名">
          <el-input placeholder="请输入配送员姓名" v-model="nameQuery" @keydown.enter.prevent=queryDataList()></el-input>
        </el-form-item>
        <el-form-item label="配送大区">
          <el-select v-model="deliverAreaIdQuery" placeholder="请选择配送大区" filterable>
            <el-option v-for="item in DeliverAreaData" :key="item.id" :label="item.name" :value="item.id" />
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
            <el-button type="primary" @click="DelivererEditRef.init('add')"><el-icon style="margin-right: 3px">
                <Plus />
              </el-icon> 新建</el-button>
          </div>
          <div style="width: 80px; display: flex; justify-content: space-around; margin-right: 20px">
          </div>
        </div>
      </template>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column property="employeeNo" label="编号" align="center" />
        <el-table-column property="mobilePhone" label="联系方式" align="center" />
        <el-table-column property="name" label="配送员姓名" align="center" />
        <el-table-column property="deliverAreaName" label="配送大区" align="center" />
        <el-table-column property="deliverLineNum" label="配送线路总数" align="center" />
        <el-table-column property="deliverPlaceNum" label="配送点总数" align="center" />
        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id" @click="DelivererEditRef.init('view', scope.row)">查看</el-link>
              <el-link type="primary" :id="scope.row.id" @click="DelivererEditRef.init('edit', scope.row)">修改</el-link>
              <el-link type="danger" @click="DelivererDel(scope.row.id)">删除</el-link>
              <el-link :id="scope.row.id" @click="ExtendLineViewRef.init(scope.row)">继承他人路线</el-link></el-space>
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

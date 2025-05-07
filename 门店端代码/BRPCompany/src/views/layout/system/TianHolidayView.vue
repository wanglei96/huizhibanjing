<script setup>
import common from '@/js/common'
import { reactive, ref, inject } from 'vue';

const detailFormVisible = ref(false)
const tableData = ref([])

function init() {
  let param = {
  }
  common.call("GetTianHoliday", param, function (result) {
    tableData.value = result.data;
    tableData.value.forEach(item => {
      item.vacationList = item.vacation.split('|');
      item.remarkList = item.remark.split('|');
    })
    detailFormVisible.value = true
  })
}

//暴露
defineExpose({
  init
})


</script>
<template>
  <div class="user">
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" title="法定节假日详情" width="1200"
      draggable>
      <el-card shadow="never" class="user-list-card">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column property="name" label="节日名称" align="center" width="100" />
          <el-table-column property="vacation" label="放假时间" align="center">
            <template #default="scope">
              <el-tag v-for="item in scope.row.vacationList" :key="item" type="danger" size="small"
                style="margin-left: 10px;">{{ item }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column property="remark" label="调休" align="center">
            <template #default="scope">
              <div>
                <el-tag v-for="item in scope.row.remarkList" :key="item" type="info" size="small"
                  style="margin-left: 10px;">{{ item }}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column property="tip" label="备注" align="center" />
        </el-table>
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

<script setup>
import { inject, reactive, ref } from 'vue'
import common from '@/js/common'
import config from "@/js/config";


const dialogFormVisible = ref(false);

const borrowDataList = ref([]);

const inDataList = ref([]);

// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);

function init(skuId) {
  // 显示弹窗
  dialogFormVisible.value = true
  let params = {
    bookSkuId: skuId
  }
  common.call("GetViewBookList", params, (res) => {
    inDataList.value = res.data.dataList.filter(item => item.borrowStatusCode === 'In');
    borrowDataList.value = res.data.dataList.filter(item => item.borrowStatusCode != 'In');
  })
}
defineExpose({
  init
})
</script>

<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" title="云库图书借阅详情" width="1200"
      draggable>
      <el-card shadow="never">
        <template #header>
          <div class="card-header">
            <span>已借出{{ borrowDataList.length }}本</span>
          </div>
        </template>
        <el-table ref="multipleTableRef" :data="borrowDataList" style="width: 100%">
          <el-table-column property="bookName" label="书名">
            <template #default="scope">
              <div>
                <span style="color: #008000;">{{ scope.row.bookSeriesName ? "[" + scope.row.bookSeriesName + "]" : ""
                  }}</span>
                <span>{{ scope.row.bookName }}</span>
                <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
                  }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column property="sn" label="书柜&书号">
            <template #default="scope">
              <div>
                <span style="color: #008000;">{{ scope.row.bookCaseName ? "[" + scope.row.bookCaseName + "]" : ""
                  }}</span>
                <span>{{ scope.row.sn }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column property="sn" label="位置">
            <template #default="scope">
              <span style="color: red;">云库</span>
            </template>
          </el-table-column>
          <el-table-column property="borrowStatusName" label="借阅状态" />
        </el-table>
      </el-card>
      <el-card shadow="never" style="margin-top: 20px;">
        <template #header>
          <div class="card-header">
            <span>馆内{{ inDataList.length }}本</span>
          </div>
        </template>
        <el-table ref="multipleTableRef" :data="inDataList" style="width: 100%">
          <el-table-column property="bookName" label="书名">
            <template #default="scope">
              <div>
                <span style="color: #008000;">{{ scope.row.bookSeriesName ? "[" + scope.row.bookSeriesName + "]" : ""
                  }}</span>
                <span>{{ scope.row.bookName }}</span>
                <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
                  }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column property="sn" label="书柜&书号">
            <template #default="scope">
              <div>
                <span style="color: #008000;">{{ scope.row.bookCaseName ? "[" + scope.row.bookCaseName + "]" : ""
                  }}</span>
                <span>{{ scope.row.sn }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column property="sn" label="位置">
            <template #default="scope">
              <span style="color: red;">云库</span>
            </template>
          </el-table-column>
          <el-table-column property="borrowStatusName" label="借阅状态" />
        </el-table>
      </el-card>
    </el-dialog>
  </div>
</template>
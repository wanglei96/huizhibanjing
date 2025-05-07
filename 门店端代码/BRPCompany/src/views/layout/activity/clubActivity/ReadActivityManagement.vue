<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import ReadActivityDetail from './ReadActivityDetail.vue'
import management from '@/js/management'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'

//详情页组件引用
const refDetail = ref()
const refSearchForm = ref()
const refRefundOrderDetail = ref()

const router = useRouter()

const list = management({
  tableName: 'read_activity',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewReadActivityList',
  refDetail,
  processDataItem(dataItem) {
    if (dataItem.imageFilePath) {
      dataItem.imageFilePath = common.getAdminUrl(dataItem, 'imageFilePath')
    } else {
      delete dataItem.imageFilePath
    }
    return dataItem
  }
})
</script>
<template>
  <div class="basic-list">
    <ReadActivityDetail ref="refDetail" @reload-data="list.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row :gutter="20" type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="名称" prop="name">
              <el-input placeholder="请输入..." v-model="list.parameters.name" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="创建时间" prop="dpSearchCreateTime">
              <el-date-picker
                v-model="list.parameters.dpSearchCreateTime"
                type="daterange"
                start-placeholder="开始时间"
                end-placeholder="结束时间"
                value-format="YYYY-MM-DD"
                :validate-event="true"
              />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click="list.query">搜索</el-button>
              <el-button @click="list.resetForm(refSearchForm)">重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card shadow="never">
      <el-alert
        :title="`已选择 ${list.selectedColumnCount.value} 项`"
        v-show="list.selectedColumnCount.value > 0"
        type="warning"
        show-icon
      />
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="list.openDetailDialog({ mode: 'add' })">
              <el-icon style="margin-right: 3px">
                <Plus />
              </el-icon>
              新建
            </el-button>
            <el-button type="danger" @click="list.deleteRows">
              <el-icon tyle="margin-right: 3px">
                <Delete />
              </el-icon>
              删除
            </el-button>
          </el-col>
          <el-col :span="6"></el-col>
        </el-row>
      </el-form>
      <el-table
        ref="multipleTableRef"
        :data="list.dataList.value"
        style="width: 100%"
        border
        @selection-change="list.selectRow"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column label="图片" width="150" align="center">
          <template #default="scope">
            <div>
              <el-image
                :src="scope.row.imageFilePath"
                fit="contain"
                :preview-src-list="[scope.row.imagFilePath]"
                preview-teleported
              >
                <template #error>
                  <div class="image-slot">
                    <el-icon>
                      <Picture />
                    </el-icon>
                  </div>
                </template>
              </el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="name" label="名称" />
        <el-table-column property="price" label="价格" />
        <el-table-column property="createTimeText" label="创建时间" />
        <el-table-column label="操作" align="center" width="230">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link
                type="warning"
                :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })"
                >查看
              </el-link>
              <el-link
                type="primary"
                :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })"
                >编辑
              </el-link>
              <el-link type="danger" @click="list.deleteRow(scope.row.id)">删除</el-link>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        v-model:current-page="list.currentPage.value"
        v-model:page-size="list.pageSizeComputed.value"
        :page-sizes="list.pageSizes"
        :page-count="list.pageCount.value"
        :layout="list.layout"
        :total="list.recordCount.value"
        style="margin-top: 20px"
      />
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.search-card {
  margin-bottom: 15px;

  .el-form-item {
    margin-bottom: 0;
  }
}

.image-slot {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 100px;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 30px;
}

.el-input {
  width: 200px;
}
</style>
<script setup>
import { ref } from 'vue';
import CompanyDetail from './StoryVideoDetail.vue';
import management from '@/js/management';
import util from '@/js/util'
import common from "@/js/common";

//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();

const list = management({
  tableName: 'story_video',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewStoryVideoList',
  refDetail,
  condition: "is_deleted is not true",
  processDataItem(dataItem) {
    dataItem.coverImageUrl = common.getAdminUrl(dataItem, 'coverImageFilePath');
    dataItem.videoImageUrl = common.getAdminUrl(dataItem, 'videoImageFilePath');
    dataItem.imageUrl = common.getAdminUrl(dataItem.coverImageFilePath || dataItem.videoImageFilePath);
    return dataItem;
  }
})

function deleteRow(row) {
  ElMessageBox.confirm("确定要删除这条数据吗？", "温馨提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning"
  })
    .then(() => {
      let params = {
        id: row.id,
        isDeleted: 1,
      }
      common.call("UpdateStoryVideo", params, function (result) {
        list.loadData();
      })
    })
    .catch((error) => {
      throw new Error(error.message);
    });

}

</script>
<template>
  <div class="basic-list">
    <CompanyDetail ref="refDetail" @reload-data="list.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row :gutter="20" type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="标题" prop="title">
              <el-input placeholder="请输入..." v-model="list.parameters.title"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="推送日期" prop="dpSearchPushDate">
              <el-date-picker v-model="list.parameters.dpSearchPushDate" type="daterange" start-placeholder="开始日期"
                end-placeholder="结束日期" value-format="YYYY-MM-DD" :validate-event="true" />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click='list.query'>搜索</el-button>
              <el-button @click='list.resetForm(refSearchForm)'>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card shadow="never">
      <el-alert :title="`已选择 ${list.selectedColumnCount.value} 项`" v-show="list.selectedColumnCount.value > 0"
        type="warning" show-icon />
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="list.openDetailDialog({ mode: 'add' })">
              <el-icon style="margin-right: 3px">
                <Plus />
              </el-icon>
              新建
            </el-button>
            <!-- <el-button type="danger" @click="list.deleteRows">
              <el-icon tyle="margin-right: 3px">
                <Delete />
              </el-icon>
              删除
            </el-button> -->
          </el-col>
          <el-col :span="6">
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%" border
        @selection-change="list.selectRow" @sort-change="list.sortChange">
        <el-table-column type="selection" width="55" />
        <el-table-column property="avator" label="封面" width="150" align="center">
          <template #default="scope">
            <div>
              <el-image style="width: 100px; height: 100px" :src="scope.row.imageUrl" fit="contain"
                :preview-src-list="[scope.row.imageUrl]" preview-teleported>
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
        <el-table-column property="title" label="标题" />
        <el-table-column property="pushDateText" sortable="custom" label="推送日期">
          <template #default="scope">
            {{ scope.row.pushDateText.substring(0, 10) }}
          </template>
        </el-table-column>
        <el-table-column property="pushDateTime" label="推送时间">
          <template #default="scope">
            {{ '当日' + scope.row.pushTimeText.substring(10, 13) + '点' }}
          </template>
        </el-table-column>
        <el-table-column property="totalBrowsedCount" sortable="custom" label="播放次数" />
        <el-table-column property="createTimeText" sortable="custom" label="发布时间" />
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看
              </el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">编辑
              </el-link>
              <el-link type="danger" @click="deleteRow(scope.row)">删除</el-link>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:current-page="list.currentPage.value" v-model:page-size="list.pageSizeComputed.value"
        :page-sizes="list.pageSizes" :page-count="list.pageCount.value" :layout="list.layout"
        :total="list.recordCount.value" style="margin-top: 20px" />
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.el-row {
  row-gap: 10px;
}

.search-card {
  margin-bottom: 15px;

  .el-form-item {
    margin-bottom: 0px;
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
</style>

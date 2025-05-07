<script setup>
import { ref } from 'vue'
import BatchUpdateMemberScoreDetail from '@/views/layout/score/BatchUpdateMemberScoreDetail.vue'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import management from '@/js/management'
import { useRouter } from 'vue-router'
//详情页组件引用
const refDetail = ref();
const refSearchForm = ref();
const refRefundOrderDetail = ref();

const route = useRoute();
const memberId = route.query['memberId'];

const list = management({
  tableName: 'member_score',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewMemberScoreList',
  refDetail,
  condition: memberId ? 'member_id=' + memberId : ''
})
</script>
<template>
  <div class="basic-list">
    <BatchUpdateMemberScoreDetail ref="refDetail" @reload-data="list.loadData" />
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters">
        <el-row  type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="会员姓名" prop="memberName">
              <el-input placeholder="请输入..." v-model="list.parameters.memberName" @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
            <el-form-item label="积分类型" prop="memberScoreTypeCode">
              <database-select
                table-name="member_score_type"
                v-model="list.parameters.memberScoreTypeCode"
              ></database-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6" style="margin-bottom: 10px">
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
            <!-- <el-button type="danger" @click="list.deleteRows">
              <el-icon tyle="margin-right: 3px">
                <Delete />
              </el-icon>
              删除
            </el-button> -->
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
        <el-table-column property="memberName" label="会员姓名" />
        <el-table-column property="memberCardNo" label="会员卡号" />
				<el-table-column property="scoreTimeText" label="积分时间"  />
        <el-table-column property="memberScoreTypeName" label="积分类型" />
        <el-table-column property="memberScore" label="积分">
          <template #default="scope">
            {{ scope.row.score ? scope.row.score : 0 }}
          </template>
        </el-table-column>
        <!-- <el-table-column label="操作" align="center" width="100">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="danger" @click="list.deleteRow(scope.row.id)">删除</el-link>
            </el-space>
          </template>
        </el-table-column> -->
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

.el-input {
  width: 200px;
}
</style>
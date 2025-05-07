<script setup>
const tableData = [
  {
    id: '001',
    title: '中欧班列今年开行达万列，释放外贸积极信号',
    createTime: '2023-07-31 15:19:20',
    status: false
  },
  {
    id: '002',
    title: '强降雨天气持续 多地升级应急响应',
    createTime: '2023-07-31 07:25:20',
    status: false
  },
  {
    id: '003',
    title: '中共中央 国务院关于促进民营经济发展壮大的意见',
    createTime: '2023-07-31 07:25:20',
    status: false
  },
  {
    id: '004',
    title: '国务院办公厅转发国家发展改革委关于恢复和扩大消费措施的通知',
    createTime: '2023-07-31 07:25:20',
    status: false
  },
  {
    id: '005',
    title: '私募投资基金监督管理条例 ',
    createTime: '2023-07-31 07:25:20',
    status: false
  },
  {
    id: '006',
    title: '中共中央 国务院关于促进民营经济发展壮大的意见',
    createTime: '2023-07-28 07:19:20',
    status: true
  }
]
function handleSelectionChange() {}
</script>
<template>
  <div class="notice_container">
    <el-row >
      <el-col :span="22">
        <el-button type="primary">批量确认</el-button>
        <el-button type="danger">批量删除</el-button>
      </el-col>
      <el-col :span="2">...</el-col>
    </el-row>
    <el-row  style="margin-top: 20px">
      <el-col :span="24">
        <el-table
          ref="multipleTableRef"
          :data="tableData"
          style="width: 100%"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="55" />
          <el-table-column property="title" label="通知标题" />
          <el-table-column property="createTime" label="创建时间" width="180" />
          <el-table-column property="status" label="状态" width="120">
            <template #default="scope">
              <el-tag class="ml-2" type="success" v-if="scope.row.status">已读</el-tag>
              <el-tag class="ml-2" type="danger" v-else>未读</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="180">
            <template #default="scope">
              <el-space spacer="|" style="color: #999" wrap
                ><el-link type="primary" :id="scope.row.username">确认</el-link>
                <el-link type="danger">删除</el-link></el-space
              >
            </template>
          </el-table-column>
        </el-table>
        <div class="table-footer">
          <el-pagination background layout="prev, pager, next" :total="100" />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.notice_container {
  padding: 0px 0px 20px 20px;
}
.table-footer {
  margin-top: 20px;
}
</style>

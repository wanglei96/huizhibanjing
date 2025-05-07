<script setup>
const tableData = [
  {
    id: '001',
    title: '8月1日开标',
    createTime: '2023-07-31 15:19:20',
    status: false
  },
  {
    id: '002',
    title: '8月15日，回老家走亲戚',
    createTime: '2023-07-31 07:25:20',
    status: false
  },
  {
    id: '003',
    title: '2023年8月20日，公司域名续费，金额180元',
    createTime: '2023-07-31 07:25:20',
    status: false
  },
  {
    id: '004',
    title: '2023年9月1日，复查血脂',
    createTime: '2023-07-31 07:25:20',
    status: false
  },
  {
    id: '005',
    title: '2023年9月10日，教师节，去拜访中学班主任 ',
    createTime: '2023-07-31 07:25:20',
    status: false
  },
  {
    id: '006',
    title: '2023年9月10日，公司阿里云服务器续费，金额为2880元，记得要开专票！',
    createTime: '2023-07-28 07:19:20',
    status: true
  }
]
function handleSelectionChange() {}
</script>
<template>
  <div class="todo_container">
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
          <el-table-column property="title" label="待办事项清单" />
          <el-table-column property="createTime" label="结束时间" width="180" />
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
.todo_container {
  padding: 0px 0px 20px 20px;
}
.table-footer {
  margin-top: 20px;
}
</style>

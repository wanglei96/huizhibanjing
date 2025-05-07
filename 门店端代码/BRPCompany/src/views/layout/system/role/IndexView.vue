<script setup>
import RoleEdit from './component/RoleEdit.vue'
import RoleMenu from './component/RoleMenu.vue'

const roleEditRef = ref()
const roleMenuRef = ref()

const tableData = [
  {
    id: '001',
    roleName: '管理员',
    roleFlag: 'admin',
    memo: '管理员',
    createTime: '2023-07-28 07:19:20',
    status: true
  },
  {
    id: '002',
    roleName: '普通用户',
    roleFlag: 'user',
    memo: '普通用户',
    createTime: '2023-07-28 07:19:20',
    status: true
  },
  {
    id: '003',
    roleName: '游客',
    roleFlag: 'guest',
    memo: '游客',
    createTime: '2023-07-28 07:19:20',
    status: true
  }
]

// 分页相关
const currentPage4 = ref(4)
const pageSize4 = ref(100)
const small = ref(false)
const background = ref(false)
const disabled = ref(false)

const handleSizeChange = (val) => {
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val) => {
  console.log(`current page: ${val}`)
}
</script>
<template>
  <div>
    <RoleEdit ref="roleEditRef" />
    <RoleMenu ref="roleMenuRef" />
    <div class="role-container">
      <!-- 顶部搜索框 -->
      <el-card shadow="never">
        <el-form :inline="true">
          <el-form-item label="角色名称">
            <el-input placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item label="角色标识">
            <el-input placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item label="备注">
            <el-input placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary">搜索</el-button>
            <el-button type="default">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
      <!-- 角色列表 -->
      <el-card shadow="never">
        <template #header>
          <div class="card-header">
            <div>
              <el-button type="primary" @click="roleEditRef.init()">
                <el-icon style="margin-right: 3px"><Plus /></el-icon> 新建
              </el-button>
              <el-button type="danger">
                <el-icon style="margin-right: 3px"><Delete /></el-icon> 删除
              </el-button>
            </div>
            <div
              style="width: 80px; display: flex; justify-content: space-around; margin-right: 20px"
            >
              <el-icon><Refresh /></el-icon>
              <el-icon><Setting /></el-icon>
              <el-icon><FullScreen /></el-icon>
            </div>
          </div>
        </template>
        <el-table ref="multipleTableRef" :data="tableData" style="width: 100%">
          <el-table-column type="selection" width="55" />
          <el-table-column property="roleName" label="角色名称" />
          <el-table-column property="roleFlag" label="角色标识" />
          <el-table-column property="memo" label="备注" />
          <el-table-column property="createTime" label="创建时间" width="180" />
          <el-table-column label="操作" align="center" width="200">
            <template #default="scope">
              <el-space spacer="|" style="color: #999" wrap
                ><el-link
                  type="primary"
                  :id="scope.row.username"
                  @click="roleEditRef.init(scope.row)"
                  >修改</el-link
                >
                <el-link type="primary" @click="roleMenuRef.init()">分配权限</el-link>
                <el-link type="danger">删除</el-link></el-space
              >
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          v-model:current-page="currentPage4"
          v-model:page-size="pageSize4"
          :page-sizes="[100, 200, 300, 400]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="400"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          style="margin-top: 20px"
        />
      </el-card>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.role-container {
  .el-card {
    border: none;
    // el-card 间距
    --el-card-padding: 15px;
    margin-bottom: 20px;

    .card-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .el-form-item {
      margin-bottom: 3px;
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

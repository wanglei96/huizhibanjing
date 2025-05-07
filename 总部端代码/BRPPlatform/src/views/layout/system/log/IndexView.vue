<script setup>
const tableData = [
  {
    id: '001',
    username: 'admin',
    nickname: '管理员',
    module: '用户管理',
    function: '分页查询用户',
    url: '/api/system/user/page',
    method: 'GET',
    status: '1',
    time: '0.015S',
    oprationTime: '2023-07-28 09:25:30'
  },
  {
    id: '002',
    username: 'admin',
    nickname: '管理员',
    module: '操作日志',
    function: '查询全部日志',
    url: '/api/system/user/page',
    method: 'GET',
    status: '1',
    time: '0S',
    oprationTime: '2023-07-28 09:25:30'
  },
  {
    id: '003',
    username: 'admin',
    nickname: '管理员',
    module: '角色菜单管理',
    function: '查询角色菜单',
    url: '/api/system/user/page',
    method: 'GET',
    status: '1',
    time: '0.01S',
    oprationTime: '2023-07-28 09:25:30'
  },
  {
    id: '004',
    username: 'admin',
    nickname: '管理员',
    module: '用户管理',
    function: '分页查询用户',
    url: '/api/system/user/page',
    method: 'GET',
    status: '1',
    time: '0.015S',
    oprationTime: '2023-07-28 09:25:30'
  },
  {
    id: '005',
    username: 'admin',
    nickname: '管理员',
    module: '字典数据管理',
    function: '查询字典数据',
    url: '/api/system/user/page',
    method: 'GET',
    status: '2',
    time: '15S',
    oprationTime: '2023-07-28 09:25:30'
  },
  {
    id: '006',
    username: 'admin',
    nickname: '管理员',
    module: '角色管理',
    function: '分页查询角色',
    url: '/api/system/user/page',
    method: 'GET',
    status: '1',
    time: '0S',
    oprationTime: '2023-07-28 09:25:30'
  },
  {
    id: '007',
    username: 'admin',
    nickname: '管理员',
    module: '用户管理',
    function: '检查用户是否可用',
    url: '/api/system/user/page',
    method: 'GET',
    status: '1',
    time: '0.2S',
    oprationTime: '2023-07-28 09:25:30'
  },
  {
    id: '008',
    username: 'admin',
    nickname: '管理员',
    module: '文件管理',
    function: '查看文件列表',
    url: '/api/system/user/page',
    method: 'GET',
    status: '1',
    time: '0.018S',
    oprationTime: '2023-07-28 09:25:30'
  },
  {
    id: '009',
    username: 'admin',
    nickname: '管理员',
    module: '机构管理',
    function: '分页查询结构',
    url: '/api/system/user/page',
    method: 'GET',
    status: '1',
    time: '0S',
    oprationTime: '2023-07-28 09:25:30'
  },
  {
    id: '010',
    username: 'admin',
    nickname: '管理员',
    module: '菜单管理',
    function: '添加菜单',
    url: '/api/system/user/page',
    method: 'GET',
    status: '1',
    time: '0.05S',
    oprationTime: '2023-07-28 09:25:30'
  }
]
const value1 = ref('')

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
  <div class="log-container">
    <div class="log-search-container">
      <el-card shadow="never">
        <el-form :inline="true">
          <el-form-item label="用户账号">
            <el-input placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item label="操作模块">
            <el-input placeholder="请输入"></el-input>
          </el-form-item>
          <el-form-item label="操作时间">
            <el-date-picker
              v-model="value1"
              type="daterange"
              range-separator="To"
              start-placeholder="Start date"
              end-placeholder="End date"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary">查询</el-button>
            <el-button type="default">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
    <div class="log-list-container">
      <el-card shadow="never" style="margin-bottom: 0px">
        <template #header>
          <div class="card-header">
            <div>
              <el-button type="primary">
                <el-icon tyle="margin-right: 5px"><Download /></el-icon> 导出
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
        <el-table :data="tableData" style="width: 100%" :show-overflow-tooltip="true">
          <el-table-column prop="username" label="账号" />
          <el-table-column prop="nickname" label="用户名" />
          <el-table-column prop="module" label="操作模块" />
          <el-table-column prop="function" label="操作功能" />
          <el-table-column prop="url" label="请求地址" />
          <el-table-column prop="method" label="请求方式" />
          <el-table-column prop="status" label="状态">
            <template #default="scope">
              <el-tag v-if="scope.row.status === '1'" class="ml-2" type="success">正常</el-tag>
              <el-tag v-if="scope.row.status === '2'" class="ml-2" type="danger">失败</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="time" label="耗时" />
          <el-table-column prop="oprationTime" label="操作时间" />
          <el-table-column label="操作" align="center">
            <template #default="scope">
              <el-link :id="scope.row.id" type="primary">查看</el-link>
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
.el-card {
  border: none;
}
.log-container {
  .log-search-container {
    .el-card {
      margin-bottom: 20px;
      .el-form {
        .el-form-item {
          margin-bottom: 3px;
        }
      }
    }
  }
  .log-list-container {
    .card-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
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

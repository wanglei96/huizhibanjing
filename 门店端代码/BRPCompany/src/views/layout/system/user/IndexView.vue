<script setup>
import UserEdit from './component/UserEdit.vue'
const userEditRef = ref()

// 引入 Pinia
import { useTabsStore } from '@/stores/tabs'
const store = useTabsStore()

function saveTab(item) {
  console.log(item)
  if (store.getTabs.length > 0) {
    if (store.getTabs.findIndex((obj) => obj.name === item.name) === -1) {
      store.saveTabs({
        name: item.name,
        path: item.path
      })
    }
  } else {
    store.saveTabs({
      name: item.name,
      path: item.path
    })
  }
}

const multipleSelection = ref([])
const tableData = [
  {
    id: '001',
    username: 'wang33771',
    nickname: '爱吃猫的鱼',
    sex: '男',
    phone: '13812345628',
    role: '普通用户',
    createTime: '2023-07-28 07:19:20',
    status: true
  },
  {
    id: '002',
    username: '13785201425',
    nickname: '桃桃奶昔',
    sex: '女',
    phone: '13785201425',
    role: '普通用户',
    createTime: '2023-07-26 05:25:10',
    status: false
  },
  {
    id: '003',
    username: '1368752411',
    nickname: '小姚',
    sex: '女',
    phone: '13785201425',
    role: '普通用户',
    createTime: '2023-07-26 05:25:10',
    status: true
  },
  {
    id: '004',
    username: 'cloundy',
    nickname: '天边的云',
    sex: '男',
    phone: '13012396932',
    role: '管理用户',
    createTime: '2023-07-26 05:25:10',
    status: true
  },
  {
    id: '005',
    username: '18012345214',
    nickname: '小姚',
    sex: '女',
    phone: '13785201425',
    role: '普通用户',
    createTime: '2023-07-26 05:25:10',
    status: true
  },
  {
    id: '006',
    username: '1301251',
    nickname: '用户十一',
    sex: '女',
    phone: '13214257844',
    role: '普通用户',
    createTime: '2023-07-26 05:25:10',
    status: false
  },
  {
    id: '007',
    username: '1368752411',
    nickname: '爱打乒乓球的女孩',
    sex: '女',
    phone: '13500000000',
    role: '普通用户',
    createTime: '2023-07-26 05:25:10',
    status: true
  },
  {
    id: '008',
    username: '13800000000',
    nickname: '食物中毒',
    sex: '女',
    phone: '13785201425',
    role: '普通用户',
    createTime: '2023-07-26 05:25:10',
    status: true
  }
]
const handleSelectionChange = (val) => {
  multipleSelection.value = val
}

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
  <div class="user">
    <UserEdit ref="userEditRef" />
    <el-card shadow="never" class="user-search-card">
      <el-form :inline="true" class="user-form">
        <el-form-item label="用户账号">
          <el-input placeholder="请输入" clearable></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input placeholder="请输入" clearable></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select placeholder="请选择">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
          <el-button>重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card shadow="never" class="user-list-card">
      <template #header>
        <div class="card-header">
          <div>
            <!-- 此处有权限控制，当获取到用户的权限列表后， 可以用 v-permission 指令来控制是否显示相应的按钮 -->
            <el-button type="primary" v-permission="['sys_user_add']" @click="userEditRef.init()"
              ><el-icon style="margin-right: 3px"><Plus /></el-icon> 新建</el-button
            >
            <el-button type="danger">
              <el-icon tyle="margin-right: 3px"><Delete /></el-icon> 删除
            </el-button>
            <el-button type="default">
              <el-icon tyle="margin-right: 3px"><Upload /></el-icon> 导入
            </el-button>
          </div>
          <div
            style="width: 80px; display: flex; justify-content: space-around; margin-right: 20px"
          >
            <!-- <el-icon><Refresh /></el-icon>
            <el-icon><Setting /></el-icon>
            <el-icon><FullScreen /></el-icon> -->
          </div>
        </div>
      </template>
      <el-table
        ref="multipleTableRef"
        :data="tableData"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column property="username" label="用户名" />
        <el-table-column label="用户账号" width="150">
          <template #default="scope">
            <el-link style="color: #1677ff">
              <router-link
                @click="saveTab({ path: `/system/user/detail`, name: '用户详情' })"
                :to="{ path: '/system/user/detail', query: { id: scope.row.nickname } }"
                >{{ scope.row.nickname }}</router-link
              >
            </el-link>
          </template>
        </el-table-column>
        <el-table-column property="sex" label="性别" width="80" />
        <el-table-column property="phone" label="手机号" />
        <el-table-column property="role" label="角色" />
        <el-table-column property="createTime" label="创建时间" width="180" />
        <el-table-column property="status" label="状态">
          <template #default="scope">
            <el-switch v-model="scope.row.status" />
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="200">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap
              ><el-link type="primary" :id="scope.row.username" @click="userEditRef.init(scope.row)"
                >修改</el-link
              >
              <el-link type="primary">重置密码</el-link>
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
  .user-list-card {
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

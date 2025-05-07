<script setup>
import DeptEdit from './component/DeptEdit.vue'
import UserEdit from '../user/component/UserEdit.vue'

const deptEditRef = ref()
const userEditRef = ref()

const isShow = ref(true)
function handleIsShow() {
  isShow.value = !isShow.value
}
const defaultProps = {
  children: 'children',
  label: 'label'
}

// 部门树 JSON 数据
const data = [
  {
    id: 1,
    label: 'UFO科技有限公司',
    children: [
      {
        label: '研发部',
        children: [
          { id: 6, label: '研发一部' },
          { id: 7, label: '研发二部' },
          { id: 8, label: '研发三部' },
          { id: 9, label: '研发四部' },
          { id: 10, label: '研发五部' },
          { id: 11, label: '研发六部' }
        ]
      },
      {
        id: 2,
        label: '设计部',
        children: [
          { id: 12, label: '设计一部' },
          { id: 13, label: '设计二部' }
        ]
      },
      { id: 3, label: '市场部' },
      { id: 4, label: '测试部' },
      { id: 5, label: '运维部' }
    ]
  }
]

// 用户列表 JSON 数据
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

function handleSelectionChange() {}
function handleNodeClick() {}

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
    <DeptEdit ref="deptEditRef" />
    <UserEdit ref="userEditRef" />
    <el-card shadow="never">
      <div class="dept-container">
        <div class="dept-aside">
          <div class="dept-aside-menu" :style="{ width: isShow ? '265px' : '0px' }">
            <el-space :size="10" spacer="" style="margin-top: 15px; margin-bottom: 15px">
              <el-button type="primary" @click="deptEditRef.init()">
                <el-icon><Plus /></el-icon>新建
              </el-button>
              <el-button type="warning" @click="deptEditRef.init('6')">
                <el-icon><EditPen /></el-icon>修改
              </el-button>
              <el-button type="danger">
                <el-icon><Delete /></el-icon>删除
              </el-button>
            </el-space>
            <el-scrollbar>
              <el-tree
                :data="data"
                :highlight-current="true"
                :default-expanded-keys="[1]"
                :props="defaultProps"
                node-key="id"
                @node-click="handleNodeClick"
              />
            </el-scrollbar>
          </div>
          <div class="dept-aside-spliter">
            <span class="arrow-box" @click="handleIsShow">
              <el-icon v-show="isShow"><ArrowLeft /></el-icon>
              <el-icon v-show="!isShow"><ArrowRight /></el-icon>
            </span>
          </div>
        </div>
        <div class="dept-content">
          <el-form :inline="true" class="user-form" style="margin-top: 15px">
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
          <div class="detp-user-tool">
            <el-button type="primary" @click="userEditRef.init()">
              <el-icon><Plus /></el-icon>添加
            </el-button>
            <el-button type="danger">
              <el-icon><Delete /></el-icon>删除
            </el-button>
          </div>
          <div class="dept-user-table">
            <el-table
              ref="multipleTableRef"
              :data="tableData"
              :table-layout="'auto'"
              style="width: 100%"
              @selection-change="handleSelectionChange"
            >
              <el-table-column type="selection" width="55" />
              <el-table-column property="username" label="用户名" />
              <el-table-column label="用户账号">
                <template #default="scope">
                  <el-link style="color: #1677ff">{{ scope.row.nickname }}</el-link>
                </template>
              </el-table-column>
              <el-table-column property="sex" label="性别" width="80" />
              <el-table-column property="phone" label="手机号" />
              <el-table-column property="role" label="角色" />
              <el-table-column property="createTime" label="创建时间" width="180" />
              <el-table-column label="操作" align="center" width="200">
                <template #default="scope">
                  <el-space spacer="|" style="color: #999" wrap
                    ><el-link type="primary" @click="userEditRef.init(scope.row)">修改</el-link>
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
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.el-card {
  height: 87vh;
  --el-card-padding: 0px;
  padding: 0px 0px 0px 16px;
}

.dept-container {
  width: 100%;
  display: flex;
  .dept-aside {
    display: flex;
    justify-content: space-between;
    .dept-aside-menu {
      flex: 1;
      border-right: 1px solid #ccc;
      height: 100vh;
      // 主要靠下面两行实现效果
      overflow: hidden; // 很重要!!!!
      transition: 0.3s ease;
    }
    .dept-aside-spliter {
      width: 15px;
      height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      .arrow-box {
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #ffffff;
        width: 20px;
        height: 20px;
        border: 1px solid #ccc;
        border-radius: 50%;
        margin-left: -15px;
        // z-index: 99999;
      }
    }
  }
  .dept-content {
    flex: 1;
    height: 100%;
  }
}

.el-tree {
  --el-tree-node-content-height: 36px;
}

.table-footer {
  display: flex;
  text-align: center;
  margin-top: 20px;
}
</style>

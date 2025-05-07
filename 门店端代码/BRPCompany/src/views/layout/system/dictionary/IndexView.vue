<script setup>
import DictionaryEdit from './component/DictionaryEdit.vue'
import DictionaryItemEdit from './component/DictionaryItemEdit.vue'

const dictionaryEditRef = ref()
const dictionaryItemEditRef = ref()

const isShow = ref(true)
function handleIsShow() {
  isShow.value = !isShow.value
}
const defaultProps = {
  children: 'children',
  label: 'label'
}

// 字典类型树 JSON 数据
const data = [
  {
    id: 1,
    label: '用户性别'
  },
  {
    id: 2,
    label: '用户类型'
  }
]

// 字典列表 JSON 数据
const multipleSelection = ref([])
const tableData = [
  {
    id: '001',
    name: '男',
    value: '1',
    sort: 1,
    createTime: '2023-07-28 07:19:20',
    status: true
  },
  {
    id: '002',
    name: '女',
    value: '0',
    sort: 2,
    createTime: '2023-07-28 07:19:20',
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
    <DictionaryEdit ref="dictionaryEditRef" />
    <DictionaryItemEdit ref="dictionaryItemEditRef" />
    <el-card shadow="never">
      <div class="dept-container">
        <div class="dept-aside">
          <div class="dept-aside-menu" :style="{ width: isShow ? '265px' : '0px' }">
            <el-space :size="10" spacer="" style="margin-top: 15px; margin-bottom: 15px">
              <el-button type="primary" @click="dictionaryEditRef.init()">
                <el-icon><Plus /></el-icon>新建
              </el-button>
              <el-button type="warning" @click="dictionaryEditRef.init(10)">
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
                :default-expanded-keys="[0]"
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
            <el-form-item label="字典名称">
              <el-input placeholder="请输入" clearable></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary">查询</el-button>
              <el-button>重置</el-button>
            </el-form-item>
          </el-form>
          <div class="detp-user-tool">
            <el-button type="primary" @click="dictionaryItemEditRef.init()">
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
              style="width: 100%"
              @selection-change="handleSelectionChange"
            >
              <el-table-column type="selection" width="55" />
              <el-table-column property="name" label="字典项名称" />
              <el-table-column property="value" label="字典值" width="80" />
              <el-table-column property="sort" label="排序号" />
              <el-table-column property="createTime" label="创建时间" width="180" />
              <el-table-column property="status" label="状态">
                <template #default="scope">
                  <el-switch v-model="scope.row.status" />
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center" width="200">
                <template #default="scope">
                  <el-space spacer="|" style="color: #999" wrap
                    ><el-link type="primary" @click="dictionaryItemEditRef.init(scope.row)"
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

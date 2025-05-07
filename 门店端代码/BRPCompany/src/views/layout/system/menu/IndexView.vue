<script setup>
import { getAllMenu } from '@/api/menu'
import { ElMessage } from 'element-plus'
import { nextTick } from 'vue'
import MenuEdit from './component/MenuEdit.vue'

// 存储返回的数据
const allMenus = ref()

// 新增菜单弹框 ref 引用
const menuEditRef = ref()

// 获取菜单数据
async function getMenus() {
  const res = await getAllMenu()
  allMenus.value = res
}
getMenus()

// 搜索表单
const searchForm = reactive({
  user: '',
  region: '',
  date: ''
})

const onSubmit = () => {
  console.log('submit!')
}

// 菜单数组

// 默认折叠
let Expand = ref(true)
let refreshTable = ref(true)

function toggleExpandAll() {
  refreshTable.value = false
  Expand.value = !Expand.value
  nextTick(() => {
    refreshTable.value = true
  })
}
</script>
<template>
  <div class="menu-container">
    <MenuEdit ref="menuEditRef" />
    <el-card shadow="never" style="margin-bottom: 15px">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline">
        <el-form-item label="菜单名称">
          <el-input v-model="searchForm.user" placeholder="请输入" clearable />
        </el-form-item>
        <el-form-item label="菜单地址">
          <el-input v-model="searchForm.user" placeholder="请输入" clearable />
        </el-form-item>
        <el-form-item label="权限名称">
          <el-input v-model="searchForm.user" placeholder="请输入" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
          <el-button @click="onSubmit">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="box-card" shadow="never">
      <template #header>
        <div class="card-header">
          <div>
            <el-button type="primary" @click="menuEditRef.init()"
              ><el-icon style="margin-right: 3px"><Plus /></el-icon> 新建</el-button
            >
            <el-button type="default" @click="toggleExpandAll"
              ><el-icon style="margin-right: 3px"><Upload /></el-icon>折叠全部</el-button
            >
            <el-button type="default" @click="toggleExpandAll"
              ><el-icon style="margin-right: 3px"><Download /></el-icon>展开全部</el-button
            >
          </div>
          <div
            style="width: 80px; display: flex; justify-content: space-around; margin-right: 20px"
          ></div>
        </div>
      </template>
      <el-table
        :data="allMenus"
        style="width: 100%"
        row-key="menuId"
        border
        v-if="refreshTable"
        :default-expand-all="Expand"
        :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      >
        <el-table-column type="index" label="ID" width="80" />
        <el-table-column label="菜单名称" width="240">
          <template #default="scope">
            <span>
              <el-icon><component :is="scope.row.icon"></component></el-icon>
              <span style="margin-left: 10px">{{ scope.row.name }}</span>
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="path" label="路由地址" width="240" />
        <el-table-column prop="" label="组件路径" />
        <el-table-column prop="" label="权限标识" />
        <el-table-column prop="" label="排序" />
        <el-table-column prop="" label="可见" />
        <el-table-column prop="" label="类型" />
        <el-table-column label="操作" width="160">
          <template #default="scope">
            <el-space spacer="|" style="color: #999">
              <el-link type="primary" @click="menuEditRef.init()">添加</el-link>
              <el-link type="primary" @click="menuEditRef.init(scope.row)">修改</el-link>
              <el-link type="danger">删除</el-link>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.menu-container {
  .el-card {
    border: none;
    // el-card 间距
    --el-card-padding: 15px;
  }

  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .el-card__header {
    --el-card-padding: 15px !important;
  }

  .demo-form-inline .el-input {
    --el-input-width: 220px;
    .el-form-item {
      width: auto;
      .el-input {
        width: auto;
      }
    }
  }

  .demo-form-inline {
    .el-form-item {
      margin-top: 3px;
      margin-bottom: 3px;
    }
  }
}
</style>

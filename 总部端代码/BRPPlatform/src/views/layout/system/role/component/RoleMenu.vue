<script setup>
const dialogVisible = ref(false)
function init() {
  dialogVisible.value = true
}

const defaultProps = {
  children: 'children',
  label: 'name'
}
// 左侧菜单数组
const menuList = reactive([
  {
    name: 'Dashboard',
    icon: 'House',
    url: '0',
    children: [
      {
        name: '工作台',
        url: '/dashboard/workplace',
        icon: 'DataBoard'
      },
      {
        name: '分析页',
        url: '/dashboard/analysis',
        icon: 'Histogram'
      },
      {
        name: '监控页',
        url: '/dashboard/monitor',
        icon: 'VideoCamera'
      }
    ]
  },
  {
    name: '系统管理',
    icon: 'Setting',
    url: '1',
    children: [
      { name: '用户管理', url: '/system/user', icon: 'User' },
      {
        name: '角色管理',
        url: '/system/role',
        icon: 'MessageBox'
      },
      {
        name: '菜单管理',
        url: '/system/menu',
        icon: 'Operation'
      },
      {
        name: '部门管理',
        url: '/system/dept',
        icon: 'OfficeBuilding'
      },
      {
        name: '字典管理',
        url: '/system/dictionary',
        icon: 'Document'
      },
      {
        name: '系统日志',
        url: '/system/log',
        icon: 'Memo'
      }
    ]
  },
  {
    name: '表单页面',
    icon: 'Eleme',
    url: 'form',
    children: [
      { name: '基础表单', url: '/form/basic', icon: 'CollectionTag' },
      {
        name: '复杂表单',
        url: '/form/advanced',
        icon: 'CollectionTag'
      },
      {
        name: '分步表单',
        url: '/form/step',
        icon: 'CollectionTag'
      }
    ]
  },
  {
    name: '列表页面',
    icon: 'Tickets',
    url: 'list',
    children: [
      { name: '基础列表', url: '/list/basic', icon: 'Document' },
      {
        name: '复杂列表',
        url: '/list/advanced',
        icon: 'DocumentCopy'
      },
      {
        name: '卡片列表',
        url: 'card',
        icon: 'Postcard',
        children: [
          { name: '项目列表', url: '/list/card/project', icon: 'Memo' },
          {
            name: '应用列表',
            url: '/list/card/application',
            icon: 'MessageBox'
          },
          {
            name: '文章列表',
            url: '/list/card/article',
            icon: 'Tickets'
          }
        ]
      }
    ]
  },
  {
    name: '结果页面',
    url: 'result',
    icon: 'Reading',
    children: [
      {
        name: '成功页面',
        url: '/result/success',
        icon: 'Camera'
      },
      {
        name: '失败页面',
        url: '/result/fail',
        icon: 'Monitor'
      }
    ]
  },
  {
    name: '异常页面',
    url: 'exception',
    icon: 'Warning',
    children: [
      {
        name: 'HTTP 403',
        url: '/exception/403',
        icon: 'Camera'
      },
      {
        name: 'HTTP 404',
        url: '/exception/404',
        icon: 'Camera'
      },
      {
        name: 'HTTP 500',
        url: '/exception/500',
        icon: 'Monitor'
      }
    ]
  },
  {
    name: '个人中心',
    url: 'personal',
    icon: 'User',
    children: [
      {
        name: '个人资料',
        url: '/personal/profile',
        icon: 'Camera'
      },
      {
        name: '站内消息',
        url: '/personal/message',
        icon: 'Camera'
      }
    ]
  },
  { name: '获取授权', url: '/authorization', icon: 'Eleme' }
])

defineExpose({
  init
})
</script>
<template>
  <div>
    <el-dialog :close-on-click-modal="false" v-model="dialogVisible" title="分配菜单" width="500" draggable>
      <div style="width: 450px; height: 500px">
        <el-scrollbar>
          <el-tree
            :data="menuList"
            show-checkbox
            node-key="id"
            :default-expanded-keys="[2, 3]"
            :default-checked-keys="[5]"
            :props="defaultProps"
            default-expand-all
            style="width: 400px"
          />
        </el-scrollbar>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="dialogVisible = false"> 确定 </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped></style>

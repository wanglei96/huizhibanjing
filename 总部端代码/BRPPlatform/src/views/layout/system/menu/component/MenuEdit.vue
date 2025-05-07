<script setup>
// 新增菜单弹框控制开关
const dialogFormVisible = ref(false)
const formLabelWidth = '140px'

// 表单数据
const menuForm = reactive({
  parentId: '',
  name: '',
  type: '',
  openType: '',
  icon: '',
  code: '',
  url: '',
  sort: '',
  componentUrl: '',
  meta: ''
})

const defaultExpandedKeys = ref([])
const defaultCheckedKeys = ref([])

const title = ref('')
function init(data) {
  console.log(data)
  dialogFormVisible.value = true
  if (data) {
    title.value = '编辑菜单'
    defaultExpandedKeys.value.push(data.value)
    defaultCheckedKeys.value.push(data.value)
    Object.assign(menuForm, data)
  } else {
    title.value = '新增菜单'
  }
}

const defaultProps = {
  children: 'children',
  label: 'name'
}
// 菜单数组
const menuList = reactive([
  {
    value: '0',
    name: 'Dashboard',
    icon: 'House',
    url: '0',
    children: [
      {
        value: '1',
        name: '工作台',
        url: '/dashboard/workplace',
        icon: 'DataBoard'
      },
      {
        value: '2',
        name: '分析页',
        url: '/dashboard/analysis',
        icon: 'Histogram'
      },
      {
        value: '3',
        name: '监控页',
        url: '/dashboard/monitor',
        icon: 'VideoCamera'
      }
    ]
  },
  {
    value: '4',
    name: '系统管理',
    icon: 'Setting',
    url: '1',
    children: [
      { value: '5', name: '用户管理', url: '/system/user', icon: 'User' },
      {
        value: '6',
        name: '角色管理',
        url: '/system/role',
        icon: 'MessageBox'
      },
      {
        value: '7',
        name: '菜单管理',
        url: '/system/menu',
        icon: 'Operation'
      },
      {
        value: '8',
        name: '部门管理',
        url: '/system/dept',
        icon: 'OfficeBuilding'
      },
      {
        value: '9',
        name: '字典管理',
        url: '/system/dictionary',
        icon: 'Document'
      },
      {
        value: '10',
        name: '系统日志',
        url: '/system/log',
        icon: 'Memo'
      }
    ]
  },
  {
    value: '11',
    name: '表单页面',
    icon: 'Eleme',
    url: 'form',
    children: [
      { value: '12', name: '基础表单', url: '/form/basic', icon: 'CollectionTag' },
      {
        value: '13',
        name: '复杂表单',
        url: '/form/advanced',
        icon: 'CollectionTag'
      },
      {
        value: '14',
        name: '分步表单',
        url: '/form/step',
        icon: 'CollectionTag'
      }
    ]
  },
  {
    value: '15',
    name: '列表页面',
    icon: 'Tickets',
    url: 'list',
    children: [
      { value: '16', name: '基础列表', url: '/list/basic', icon: 'Document' },
      {
        value: '32',
        name: '复杂列表',
        url: '/list/advanced',
        icon: 'DocumentCopy'
      },
      {
        value: '17',
        name: '卡片列表',
        url: 'card',
        icon: 'Postcard',
        children: [
          { value: '18', name: '项目列表', url: '/list/card/project', icon: 'Memo' },
          {
            value: '19',
            name: '应用列表',
            url: '/list/card/application',
            icon: 'MessageBox'
          },
          {
            value: '20',
            name: '文章列表',
            url: '/list/card/article',
            icon: 'Tickets'
          }
        ]
      }
    ]
  },
  {
    value: '21',
    name: '结果页面',
    url: 'result',
    icon: 'Reading',
    children: [
      {
        value: '22',
        name: '成功页面',
        url: '/result/success',
        icon: 'Camera'
      },
      {
        value: '23',
        name: '失败页面',
        url: '/result/fail',
        icon: 'Monitor'
      }
    ]
  },
  {
    value: '24',
    name: '异常页面',
    url: 'exception',
    icon: 'Warning',
    children: [
      {
        value: '25',
        name: 'HTTP 403',
        url: '/exception/403',
        icon: 'Camera'
      },
      {
        value: '26',
        name: 'HTTP 404',
        url: '/exception/404',
        icon: 'Camera'
      },
      {
        value: '27',
        name: 'HTTP 500',
        url: '/exception/500',
        icon: 'Monitor'
      }
    ]
  },
  {
    value: '28',
    name: '个人中心',
    url: 'personal',
    icon: 'User',
    children: [
      {
        value: '29',
        name: '个人资料',
        url: '/personal/profile',
        icon: 'Camera'
      },
      {
        value: '30',
        name: '站内消息',
        url: '/personal/message',
        icon: 'Camera'
      }
    ]
  },
  { value: '31', name: '获取授权', url: '/authorization', icon: 'Eleme' }
])

defineExpose({
  init
})
</script>
<template>
  <div>
    <!-- 新增弹框 -->
    <el-dialog :close-on-click-modal="false" v-model="dialogFormVisible" style="width: 740px">
      <template #header="{ titleId, titleClass }">
        <div class="my-header">
          <h4 :id="titleId" :class="titleClass">{{ title }}</h4>
        </div>
      </template>
      <el-form :model="menuForm" label-width="100px" :label-position="'right'">
        <el-row>
          <el-col :span="12">
            <el-form-item label="上级菜单">
              <el-tree-select
                v-model="menuForm.parentId"
                :data="menuList"
                node-key="value"
                :default-expanded-keys="defaultExpandedKeys"
                :default-checked-keys="defaultCheckedKeys"
                :render-after-expand="false"
                :props="defaultProps"
              />
            </el-form-item>
            <el-form-item label="菜单名称">
              <el-input v-model="menuForm.name" placeholder="请输入菜单名称" clearable
            /></el-form-item>
          </el-col>
          <el-col :span="12"
            ><el-form-item label="菜单类型">
              <el-radio-group v-model="menuForm.type">
                <el-radio label="目录" />
                <el-radio label="菜单" />
                <el-radio label="按钮" />
              </el-radio-group>
            </el-form-item>
            <el-form-item label="打开方式">
              <el-radio-group v-model="menuForm.openType">
                <el-radio label="组件" />
                <el-radio label="内链" />
                <el-radio label="外链" />
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-divider />
        <el-row>
          <el-col :span="12">
            <el-form-item label="菜单图标">
              <el-input placeholder="请选择" v-model="menuForm.icon" clearable />
            </el-form-item>
            <el-form-item label="路由地址">
              <el-input placeholder="请输入路由地址" v-model="menuForm.url" clearable
            /></el-form-item>
            <el-form-item label="组件路径">
              <el-input placeholder="请输入组件路径" v-model="menuForm.componentUrl" clearable
            /></el-form-item>
          </el-col>
          <el-col :span="12"
            ><el-form-item label="权限标识">
              <el-input placeholder="请输入权限标识" v-model="menuForm.code" clearable />
            </el-form-item>
            <el-form-item label="排序号">
              <el-input
                placeholder="请输入排序号"
                type="number"
                v-model="menuForm.sort"
                clearable
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="路由元信息">
              <el-input
                v-model="menuForm.meta"
                type="textarea"
                rows="4"
                placeholder="请输入JSON格式的路由元信息"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false"> 确定 </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped></style>

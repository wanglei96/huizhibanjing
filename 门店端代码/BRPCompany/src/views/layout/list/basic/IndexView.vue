<script setup>
const tableData = [
  {
    id: '001',
    avator: 'http://www.eleadmin.cn/assets/images/avator/avator00.png',
    username: 'zhangsan',
    nickname: '张三',
    sex: '男',
    phone: '13712345678',
    company: '华为技术公司',
    createTime: '2023-07-31 21:53:30',
    status: true
  },
  {
    id: '002',
    avator: 'http://www.eleadmin.cn/assets/images/avator/avator01.png',
    username: 'lisi',
    nickname: '李四',
    sex: '男',
    phone: '13712345678',
    company: '北京科创',
    createTime: '2023-07-31 21:53:30',
    status: true
  },
  {
    id: '003',
    avator: 'http://www.eleadmin.cn/assets/images/avator/avator02.png',
    username: 'wangwu',
    nickname: '王五',
    sex: '男',
    phone: '13712345678',
    company: '上海重汽',
    createTime: '2023-07-31 21:53:30',
    status: true
  },
  {
    id: '004',
    avator: 'http://www.eleadmin.cn/assets/images/avator/avator03.png',
    username: 'shangsanfeng',
    nickname: '张三丰',
    sex: '男',
    phone: '13712345678',
    company: '石家庄公司',
    createTime: '2023-07-31 21:53:30',
    status: true
  },
  {
    id: '005',
    avator: 'http://www.eleadmin.cn/assets/images/avator/avator04.png',
    username: 'xiaoliu',
    nickname: '钱小六',
    sex: '男',
    phone: '13712345678',
    company: '北京联想',
    createTime: '2023-07-31 21:53:30',
    status: true
  }
]
const form = reactive({
  border: true,
  stripe: false,
  showHeader: false
})
function handleSelectionChange() { }

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
  <div class="basic-list">
    <el-card shadow="never" class="search-card">
      <el-form>
        <el-row  type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="用户账号">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="用户昵称">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="用户性别">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary">搜索</el-button>
              <el-button>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card shadow="never">
      <el-alert title="已选择 0 项" type="warning" show-icon />
      <el-form :model="form" :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary">新建</el-button>
            <el-button>删除</el-button>
          </el-col>
          <el-col :span="6">
            <el-form-item label="表格边框">
              <el-switch v-model="form.border" />
            </el-form-item>
            <el-form-item label="斑马纹">
              <el-switch v-model="form.stripe" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="multipleTableRef" :data="tableData" style="width: 100%" :border="form.border" :stripe="form.stripe"
        :show-header="form.showHeader" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" />
        <el-table-column type="index" label="序号" width="55" />
        <el-table-column property="avator" label="头像" width="80" align="center">
          <template #default="scope">
            <el-avatar :size="40" :src="scope.row.avator" />
          </template>
        </el-table-column>
        <el-table-column property="username" label="用户账号" />
        <el-table-column property="nickname" label="用户昵称" />
        <el-table-column property="sex" label="性别" />
        <el-table-column property="phone" label="手机号码" />
        <el-table-column property="company" label="公司名称" />
        <el-table-column property="createTime" label="创建时间" width="180" />
        <el-table-column property="status" label="状态" width="120">
          <template #default="scope">
            <el-tag class="ml-2" type="success" v-if="scope.row.status">已读</el-tag>
            <el-tag class="ml-2" type="danger" v-else>未读</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap><el-link type="primary" :id="scope.row.username">确认</el-link>
              <el-link type="danger">删除</el-link></el-space>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:current-page="currentPage4" v-model:page-size="pageSize4" :page-sizes="[100, 200, 300, 400]"
        :small="small" :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper"
        :total="400" @size-change="handleSizeChange" @current-change="handleCurrentChange" style="margin-top: 20px" />
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.search-card {
  margin-bottom: 15px;

  .el-form-item {
    margin-bottom: 0;
  }
}
</style>

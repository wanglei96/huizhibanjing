<script setup>
// 表格响应式数据
const tableData = reactive([
  {
    xingming: '张三丰',
    chigu: '51%',
    renjiao: '3060',
    riqi: '2020-12-31'
  },
  {
    xingming: '殷天正',
    chigu: '30%',
    renjiao: '1200',
    riqi: '2021-12-25'
  }
])

// 增加表格数据行
function handleAdd() {
  tableData.push({
    xingming: '',
    chigu: '',
    renjiao: '',
    riqi: ''
  })
}

// 删除表格数据行
function delRow(index) {
  tableData.splice(index, 1)
}
</script>
<template>
  <div>
    <el-card shadow="never" style="margin-bottom: 15px">
      <h4>复杂表单</h4>
      <div style="margin-top: 5px; font-size: 14px; color: #aaa">
        多种表单控件采集不同的用户信息，包含文本输入框, 单选框, 下拉选择,
        多选框、图片上传等用户输入的组件。 使用复杂表单，您可以收集、验证和提交用户数据。
      </div>
    </el-card>
    <!-- 企业基本信息 -->
    <el-card class="box-card" shadow="never" style="margin-bottom: 15px">
      <template #header>
        <div class="card-header">
          <span>基本信息</span>
          <el-button class="button" text></el-button>
        </div>
      </template>
      <el-form label-width="120px" input-width="200px">
        <el-row type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="企业名称">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="社会信用代码">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="法定代表人">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="经营状态">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>

          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="成立日期">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="行政区划">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="注册资本">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="实缴资本">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <!-- 企业工商信息 -->
    <el-card class="box-card" shadow="never" style="margin-bottom: 15px">
      <template #header>
        <div class="card-header">
          <span>工商信息</span>
          <el-button class="button" text></el-button>
        </div>
      </template>
      <el-form label-width="120px" input-width="200px">
        <el-row type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="工商注册号">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="组织机构代码">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="纳税人识别号">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="纳税人资质">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>

          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="营业期限">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="核准日期">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="登记机关">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="参保人数">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <!-- 股东信息 -->
    <el-card class="box-card" shadow="never" style="margin-bottom: 15px">
      <template #header>
        <div class="card-header">
          <span>股东信息</span>
          <el-button class="button" type="primary" @click="handleAdd">添加股东</el-button>
        </div>
        <el-table :data="tableData" style="width: 100%">
          <el-table-column type="index" width="80" />
          <el-table-column prop="xingming" label="股东名称">
            <template #default="scope">
              <el-input v-model="scope.row.xingming"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="chigu" label="持股比例">
            <template #default="scope">
              <el-input v-model="scope.row.chigu"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="renjiao" label="认缴出资额">
            <template #default="scope">
              <el-input v-model="scope.row.renjiao"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="riqi" label="认缴日期">
            <template #default="scope">
              <el-input v-model="scope.row.riqi"></el-input>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template #default="scope">
              <el-button type="danger" text @click="delRow(scope.$index)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.el-row {
  display: flex;
  flex-wrap: wrap;
}

.el-form-item {
  min-width: 200px;
}
.el-input__wrapper {
  flex: 1;
}
</style>

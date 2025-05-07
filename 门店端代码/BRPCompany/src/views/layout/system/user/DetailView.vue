<script setup>
import { useRoute } from 'vue-router'

const route = useRoute()
console.log(route)
</script>

<template>
  <el-card shadow="never">
    <template #header>
      <div class="card-header">
        <span>用户详情</span>
      </div>
    </template>
    <table class="mytable">
      <tr>
        <td style="width: 10%; text-align: right; background-color: #fdfdfd">用户名：</td>
        <td>{{ route.query.id }}</td>
        <td style="width: 10%; text-align: right; background-color: #fdfdfd">性别：</td>
        <td>男</td>
      </tr>
      <tr>
        <td style="width: 10%; text-align: right; background-color: #fdfdfd">用户账号：</td>
        <td>测试账号</td>
        <td style="width: 10%; text-align: right; background-color: #fdfdfd">角色：</td>
        <td>管理员</td>
      </tr>
      <tr>
        <td style="width: 10%; text-align: right; background-color: #fdfdfd">手机号码：</td>
        <td>13788888888</td>
        <td style="width: 10%; text-align: right; background-color: #fdfdfd">创建时间：</td>
        <td>2023-10-30 21:32</td>
      </tr>
    </table>
  </el-card>
</template>

<style lang="scss" scoped>
.mytable {
  width: 100%;
}
.mytable,
.mytable td {
  border: 1px solid #ebeef5;
  border-collapse: collapse;
  font-size: 14px;
}
.mytable td {
  padding: 10px;
}
</style>

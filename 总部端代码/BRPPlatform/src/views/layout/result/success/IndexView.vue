<template>
  <div>
    <el-card shadow="never">
      <el-result
        icon="success"
        title="操作成功"
        sub-title="Result组件，用于对用户的操作结果或者异常状态做反馈。"
      >
        <template #extra>
          <el-button type="primary">返回首页</el-button>
          <el-button type="default">返回列表</el-button>
        </template>
      </el-result>
    </el-card>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

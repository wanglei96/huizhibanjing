<template>
  <div>
    <el-card shadow="never">
      <el-result
        icon="error"
        title="操作失败"
        sub-title="请核对并修改以下信息后，再重新提交。用于对用户的操作结果或者异常状态做反馈。"
      >
        <template #extra>
          <el-button type="default">返回修改</el-button>
          <el-button type="primary">重新提交</el-button>
        </template>
      </el-result>
    </el-card>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

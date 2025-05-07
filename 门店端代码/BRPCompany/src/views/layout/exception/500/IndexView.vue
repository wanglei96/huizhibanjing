<script setup>
import imgUrl from '@/assets/images/error_500.png'
</script>
<template>
  <div>
    <el-result title="500" sub-title="服务器错误，请联系管理员！">
      <template #icon>
        <el-image :src="imgUrl" />
      </template>
      <template #extra>
        <el-button type="primary">返回</el-button>
      </template>
    </el-result>
  </div>
</template>

<style lang="scss" scoped></style>

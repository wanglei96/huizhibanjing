<script setup>
import imgUrl from '@/assets/images/error_403.png'
</script>
<template>
  <div>
    <el-result title="403" sub-title="对不起, 你无权访问该页面！">
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

<script setup>
import imgUrl from '@/assets/images/error_404.png'
</script>
<template>
  <div>
    <el-result title="404" sub-title="对不起, 你访问的页面不存在！">
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

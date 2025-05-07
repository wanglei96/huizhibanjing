<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()

// 返回首页按钮处理函数
function goIndex() {
  router.replace('/')
}
</script>
<template>
  <div class="container">
    <img src="@/assets/images/error_404.png" alt="" />
    <el-text style="font-size: 16px; margin-top: 20px">系统提示：</el-text>
    <el-text style="font-size: 16px; margin-top: 20px">抱歉, 你访问的页面不存在!</el-text>
    <el-button type="primary" style="margin-top: 30px" @click="goIndex">返回首页</el-button>
  </div>
</template>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>

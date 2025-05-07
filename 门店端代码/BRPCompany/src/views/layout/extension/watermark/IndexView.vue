<script setup>
const watermarkConfig = {
  //水印
  text: 'www.eleadmin.cn',
  font: '16px 微软雅黑',
  textColor: '#dddddd',
  width: 250, //水印文字的水平间距
  height: 120, //水印文字的高度间距（低于文字高度会被替代）
  extRotate: -30 //-90到0， 负数值，不包含-90
}
</script>
<template>
  <div>
    <el-card shadow="never" v-watermark="watermarkConfig">
      <template #header>
        <div class="card-header">
          <div>自定义水印组件</div>
          <div>...</div>
        </div>
      </template>
      <div class="card-body"></div>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
}
.card-body {
  width: 100%;
  height: 400px;
}
</style>

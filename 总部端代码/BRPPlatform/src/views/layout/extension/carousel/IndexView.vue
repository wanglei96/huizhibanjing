<template>
  <div>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>基础用法</span>
        </div>
      </template>
      <div class="card-body">
        <div class="lunbo">
          <el-carousel trigger="click" height="300px">
            <el-carousel-item>
              <img src="@/assets/images/lunbo/001.jpg" />
            </el-carousel-item>
            <el-carousel-item>
              <img src="@/assets/images/lunbo/002.jpg" />
            </el-carousel-item>
            <el-carousel-item>
              <img src="@/assets/images/lunbo/003.jpg" />
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
    </el-card>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>卡片模式</span>
        </div>
      </template>
      <div class="card-body">
        <div class="lunbo2">
          <el-carousel :interval="4000" type="card" height="360px">
            <el-carousel-item>
              <img
                src="@/assets/images/lunbo/b001.jpg"
                style="border: 1px solid #ccc; border-radius: 10px"
              />
            </el-carousel-item>
            <el-carousel-item>
              <img
                src="@/assets/images/lunbo/b002.jpg"
                style="border: 1px solid #ccc; border-radius: 10px"
              />
            </el-carousel-item>
            <el-carousel-item>
              <img
                src="@/assets/images/lunbo/b003.jpg"
                style="border: 1px solid #ccc; border-radius: 10px"
              />
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
    </el-card>
    <el-card>
      <template #header>
        <div class="card-header">
          <span>垂直方向</span>
        </div>
      </template>
      <div class="card-body">
        <div class="lunbo3">
          <el-carousel height="300px" direction="vertical" :autoplay="true">
            <el-carousel-item>
              <img src="@/assets/images/lunbo/c001.jpg" />
            </el-carousel-item>
            <el-carousel-item>
              <img src="@/assets/images/lunbo/c002.jpg" />
            </el-carousel-item>
            <el-carousel-item>
              <img src="@/assets/images/lunbo/c003.jpg" />
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped>
.el-card {
  margin-bottom: 20px;
}
.card-body {
  display: flex;
  justify-content: center;
  align-items: center;
}
.lunbo {
  width: 750px;
  height: 300px;
}
.lunbo2 {
  width: 1300px;
  height: 360px;
}
.lunbo3 {
  width: 600px;
  height: 300px;
}
.demonstration {
  color: var(--el-text-color-secondary);
}
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>

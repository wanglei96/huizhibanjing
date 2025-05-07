<script setup>
import Live from '@/components/extension/Live.vue'
import XgPlayer from '@/components/extension/XgPlayer.vue'
</script>
<template>
  <div>
    <el-row >
      <el-col :xs="24" :sm="24" :md="12" :lg="12">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>在线直播</span>
              <span style="font-size: 14px; color: red">m3u8视频流</span>
            </div>
          </template>
          <div class="card-body">
            <div style="width: 100%; height: 360px; display: flex; justify-content: center">
              <Live style="width: 100%" />
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>在线直播</span>
              <span style="font-size: 14px; color: red">flv视频流</span>
            </div>
          </template>
          <div class="card-body">
            <div style="width: 100%; height: 360px; display: flex; justify-content: center">
              <XgPlayer style="width: 100%; height: 520px" />
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
}
.card-body {
  width: 100%;
  height: 505px;
  display: flex;
  flex-direction: column;
}
</style>

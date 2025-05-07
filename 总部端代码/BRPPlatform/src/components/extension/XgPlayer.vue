<script setup>
import Player from 'xgplayer'
import 'xgplayer/dist/index.min.css'

let player
const url = 'https://s1.pstatp.com/cdn/expire-1-M/byted-player-videos/1.0.0/xgplayer-demo.mp4'
function initPlayer() {
  player = new Player({
    id: 'mse',
    url: url,
    width: '100%',
    height: '85%',
    autoplay: false,
    poster:
      'https://img1.baidu.com/it/u=3943156777,3438828826&fm=253&fmt=auto&app=138&f=JPEG?w=758&h=500'
  })
}

onMounted(() => {
  initPlayer()
})
</script>
<template>
  <div>
    <div class="control-btn" style="margin-bottom: 20px">
      <el-button type="primary" @click="player.play()">中央1套</el-button>
      <el-button type="primary" @click="player.pause()">中央2套</el-button>
    </div>
    <div id="mse"></div>
  </div>
</template>

<style lang="scss" scoped></style>

<!-- 视频播放 -->
<script setup>
import Player from 'xgplayer'
import Danmu from 'xgplayer/es/plugins/danmu'
import 'xgplayer/es/plugins/danmu/index.css'
import 'xgplayer/dist/index.min.css'

const comments = [
  {
    duration: 15000, //弹幕持续显示时间,毫秒(最低为5000毫秒)
    id: '001', //弹幕id，需唯一
    start: 1000, //弹幕出现时间, 单位：ms 毫秒
    prior: false, //该条弹幕优先显示，默认false
    color: false, //该条弹幕为彩色弹幕，默认false
    txt: '这电视剧不错啊，值得观看！！', //弹幕文字内容
    style: {
      //弹幕自定义样式
      color: '#ff9500', //例：'#ff9500',
      fontSize: '20px', // 例：'20px',
      padding: '2px 11px' //例： 2px 11px',
    },
    mode: 'scroll' // 例：'top', 显示模式，top顶部居中，bottom底部居中，scroll滚动，默认为scroll
  }
]

let player
const url = 'https://s1.pstatp.com/cdn/expire-1-M/byted-player-videos/1.0.0/xgplayer-demo.mp4'
// const url2 = 'https://n.v.netease.com/2017/1212_erce/xjy_1080p.mp4'
const url2 = 'https://www.fzgc.tv/storage/video/20200726/video.mp4'
function initPlayer() {
  player = new Player({
    id: 'mse',
    url: url,
    width: '100%',
    height: '85%',
    autoplay: false,
    poster:
      'https://img1.baidu.com/it/u=966117778,3748345607&fm=253&fmt=auto&app=138&f=JPEG?w=960&h=500'
  })
}
let player2
function initPlayer2() {
  player2 = new Player({
    id: 'mse2',
    url: url2,
    width: '100%',
    height: '85%',
    autoplay: false,
    plugins: [Danmu],
    poster:
      'https://img0.baidu.com/it/u=2552058363,765531025&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500',
    danmu: {
      comments: comments
    }
  })
}

onMounted(() => {
  initPlayer()
  initPlayer2()
})

const danmuTxt = ref('')
function sendDanmu() {
  player2.danmu.sendComment({
    duration: 15000, // 弹幕持续显示时间，毫秒(最低为5000毫秒)
    id: Math.random() * 100000, //弹幕id,需唯一
    txt: danmuTxt.value, // 弹幕文字内容
    style: {
      // 弹幕自定义样式
      color: '#ffffff',
      fontSize: '20px',
      borderRadius: '50px',
      padding: '5px 11px',
      backgroundColor: 'rgba(255, 255, 255, 0.1)'
    }
  })
  danmuTxt.value = ''
}
</script>
<template>
  <div>
    <el-row >
      <el-col :xs="24" :sm="24" :md="12" :lg="12">
        <el-card shadow="never">
          <template #header>
            <div class="card-header">
              <span>基础演示</span>
              <span>...</span>
            </div>
          </template>
          <div class="card-body">
            <div class="control-btn" style="margin-bottom: 20px">
              <el-button type="primary" @click="player.play()">播放</el-button>
              <el-button type="primary" @click="player.pause()">暂停</el-button>
              <el-button type="primary" @click="player.replay()">从头播放</el-button>
              <el-button type="primary" @click="player.getFullscreen()">全屏</el-button>
              <el-button type="primary" @click="player.getCssFullscreen()">网页全屏</el-button>
            </div>
            <div id="mse"></div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12">
        <el-card shadow="never">
          <template #header>
            <div class="card-header">
              <span>弹幕播放</span>
              <span>...</span>
            </div>
          </template>
          <div class="card-body">
            <div class="control-btn" style="margin-bottom: 20px">
              <el-form :inline="true">
                <el-form-item>
                  <el-input v-model="danmuTxt" placeholder="想说点什么" />
                </el-form-item>
                <el-form-item
                  ><el-button type="warning" @click="sendDanmu">开喷</el-button></el-form-item
                >
                <el-form-item>
                  <el-button type="primary" @click="player2.play()">播放</el-button>
                  <el-button type="primary" @click="player2.pause()">暂停</el-button>
                </el-form-item>
              </el-form>
            </div>
            <div id="mse2"></div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12"></el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12"></el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
}
.card-body {
  height: 450px;
}
.el-form-item {
  margin-bottom: 0;
}
</style>

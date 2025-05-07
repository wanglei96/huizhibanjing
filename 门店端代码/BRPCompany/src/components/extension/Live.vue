<script setup>
import videojs from 'video.js'
import lang_zhcn from 'video.js/dist/lang/zh-CN.json'
import 'video.js/dist/video-js.css'

// 使用中文
videojs.addLanguage('zh-CN', lang_zhcn)

let tai = ref('01')
const videoPlayer = ref(null)
let myPlayer = null

function initTv() {
  myPlayer = videojs(
    videoPlayer.value,
    {
      poster:
        'https://img0.baidu.com/it/u=3642039708,2201706084&fm=253&fmt=auto&app=120&f=JPEG?w=890&h=500',
      controls: true,
      fluid: true,
      language: 'zh-CN',
      width: '800px',
      height: '450px',
      sources: [
        {
          // src: 'http://220.161.87.62:8800/hls/0/index.m3u8'
          // src: 'https://live-play.cctvnews.cctv.com/cctv/merge_cctv13_mud.m3u8'
          // src: 'https://hw-m-l.cztv.com/channels/lantian/channel10/720p.m3u8'
          // src: url.value
          // type: 'video/mp4'
        }
      ],
      controlBar: {
        remainingTimeDisplay: {
          displayNegative: false
        },
        fullscreenToggle: true
      },
      playbackRates: [0.5, 1, 1.5, 2]
    },
    () => {
      myPlayer.log('play.....')
    }
  )
}
onMounted(() => {
  initTv()
  changeChannel('01')
})

onUnmounted(() => {
  if (myPlayer.value) {
    myPlayer.value.dispose()
  }
})

// 切换频道
function changeChannel(flag) {
  if (flag === '01') {
    tai.value = '01'
    myPlayer.src({
      src: 'https://live-play.cctvnews.cctv.com/cctv/merge_cctv13_mud.m3u8',
      load: 'https://live-play.cctvnews.cctv.com/cctv/merge_cctv13_mud.m3u8'
    })
    myPlayer.play()
  }
  if (flag === '02') {
    tai.value = '02'
    myPlayer.src({
      src: 'https://hw-m-l.cztv.com/channels/lantian/channel10/720p.m3u8',
      load: 'https://hw-m-l.cztv.com/channels/lantian/channel10/720p.m3u8'
    })
    myPlayer.play()
  }
  if (flag === '03') {
    tai.value = '03'
    myPlayer.src({
      src: 'http://220.161.87.62:8800/hls/0/index.m3u8',
      load: 'http://220.161.87.62:8800/hls/0/index.m3u8'
    })
    myPlayer.play()
  }
}
</script>
<template>
  <div>
    <div style="margin-bottom: 20px">
      <el-button :type="tai === '01' ? 'primary' : 'default'" @click="changeChannel('01')"
        >中央13套</el-button
      >
      <el-button :type="tai === '02' ? 'primary' : 'default'" @click="changeChannel('02')"
        >浙江国际</el-button
      >
      <el-button :type="tai === '03' ? 'primary' : 'default'" @click="changeChannel('03')"
        >漳浦综合</el-button
      >
    </div>
    <video ref="videoPlayer" class="video-js" style="auto auto"></video>
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

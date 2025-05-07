<script>
import QRCode from 'qrcode' //引入生成二维码插件
export default {
  name: 'CodeS',
  props: {
    canvasWidth: {
      default: 200,
      type: Number
    },
    canvasHeight: {
      default: 200,
      type: Number
    },
    url: {
      default: '',
      type: String,
      required: true
    },
    logoUrl: {
      default: '',
      type: String
    },
    errorCorrectionLevel: {
      default: 'L',
      type: String
    },
    logoSize: {
      default: 25,
      type: Number
    }
  },
  methods: {
    getQRCode() {
      let opts = {
        errorCorrectionLevel: this.errorCorrectionLevel, //容错级别,指二维码被遮挡可以扫出结果的区域比例
        type: 'image/png', //生成的二维码类型
        quality: 0.3, //二维码质量
        margin: 5, //二维码留白边距
        width: this.canvasWidth, //宽
        height: this.canvasHeight, //高
        // text: '11dfasfdaf11', //二维码内容
        color: {
          light: '#ffffff' //背景色
        }
      }
      // 将获取到的数据（val）画到msg（canvas）上,加上时间戳动态生成二维码
      QRCode.toCanvas(this.$refs.canvas, this.url, opts, function (error) {
        if (error) {
          console.log('加载失败！')
        }
      })
      this.setLogo()
    },
    saveCode() {
      //下载二维码
      let base64Img = this.$refs.canvas.toDataURL('image/jpg')
      //创建下载标签，然后设置链接和图片名称
      let a = document.createElement('a')
      a.href = base64Img
      a.download = '二维码' + Date.now()
      a.click()
      //销毁元素
      a.remove()
    },
    setLogo() {
      if (this.logoUrl != '') {
        let myCanvas = this.$refs.canvas
        let ctx = myCanvas.getContext('2d')
        // 在Canvas画布 添加图片
        let img = new Image()
        img.crossOrigin = 'Anonymous' //解决Canvas.toDataURL 图片跨域问题
        img.src = this.logoUrl
        img.onload = () => {
          //第一个设置的元素，第二三是位置，后面两个是宽和高
          //居中的位置计算为 （二维码宽度-img宽度）/2
          let codeWidth = (this.canvasWidth * 1 - this.logoSize) / 2
          let codeHeight = (this.canvasHeight * 1 - this.logoSize) / 2
          ctx.drawImage(
            img,
            codeWidth,
            codeHeight,
            this.logoSize,
            this.logoSize
            // this.canvasWidth * 0.25,
            // this.canvasHeight * 0.25
          )
        }
      }
    }
  },
  mounted() {
    this.getQRCode()

    //设置logo图标
    // this.setLogo()
  }
}
</script>
<template>
  <div class="qr-code">
    <canvas id="QRCode_header" ref="canvas" title="扫描二维码"></canvas>
    <div class="mask-code" @click="saveCode"><i></i><span>保存二维码</span></div>
  </div>
</template>

<style lang="scss" scoped>
.qr-code {
  display: flex;
  width: fit-content;
  width: -webkit-fit-content;
  width: -moz-fit-content;
  position: relative;
}
.qr-code:hover > div {
  z-index: 0;
}
.mask-code {
  position: absolute;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.4);
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  z-index: -1;
}
.mask-code i {
  display: inline-block;
  width: 25px;
  height: 25px;
  background-size: cover;
}
.mask-code span {
  color: white;
}
</style>

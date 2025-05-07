<script setup>
// 配置文档地址：https://driverjs.com/docs/static-tour

import { driver } from 'driver.js'
import 'driver.js/dist/driver.css'

const radio = ref('red')

function beginGuide() {
  const driverObj = new driver({
    showProgress: true,
    steps: [
      {
        element: '#first',
        popover: {
          title: '新生注册',
          description:
            '所有参加考试的考生都必须在考生平台上进行注册，否则无法考试！\r\n 可以使用键盘左右键',
          side: 'left',
          align: 'start',
          nextBtnText: '下一步',
          prevBtnText: '上一步',
          doneBtnText: '完成'
        }
      },
      {
        element: '#second',
        popover: {
          title: '报名缴费',
          description: '考生选择考试科目，支付考试费用！',
          side: 'top',
          align: 'start',
          nextBtnText: '下一步',
          prevBtnText: '上一步',
          doneBtnText: '完成'
        }
      },
      {
        element: '#third',
        popover: {
          title: '成绩查询',
          description: '在指定日期内，考生使用身份证号、准考证号及密码登录系统查询考试成绩！',
          side: 'bottom',
          align: 'start',
          nextBtnText: '下一步',
          prevBtnText: '上一步',
          doneBtnText: '完成'
        }
      },
      {
        element: '#fourth',
        popover: {
          title: '志愿填报',
          description: '根据根据自己的考生成绩， 在系统中进行志愿填报，每个考生可以填报20个志愿！',
          side: 'top',
          align: 'start',
          nextBtnText: '下一步',
          prevBtnText: '上一步',
          doneBtnText: '完成'
        }
      },
      {
        element: '#fifth',
        popover: {
          title: '录取查询',
          description: '考生登录系统查询，查询录取结果！',
          side: 'left',
          align: 'start',
          nextBtnText: '下一步',
          prevBtnText: '上一步',
          doneBtnText: '完成'
        }
      }
    ]
  })
  driverObj.drive()
}

function beginGuide2() {
  const driverObj2 = new driver({
    overlayColor: radio.value, // 遮罩颜色
    overlayOpacity: 0.3, // 遮罩透明度
    showProgress: true,
    showButtons: ['next', 'previous', 'close'],
    steps: [
      {
        element: '#step1',
        popover: {
          title: '新生注册',
          description:
            '所有参加考试的考生都必须在考生平台上进行注册，否则无法考试！\r\n 可以使用键盘左右键',
          side: 'left',
          align: 'start',
          nextBtnText: '下一步',
          prevBtnText: '上一步',
          closeBtnText: '完成'
        }
      },
      {
        element: '#step2',
        popover: {
          title: '报名缴费',
          description: '考生选择考试科目，支付考试费用！',
          side: 'top',
          align: 'start',
          nextBtnText: '下一步',
          prevBtnText: '上一步',
          closeBtnText: '完成'
        }
      },
      {
        element: '#step3',
        popover: {
          title: '成绩查询',
          description: '在指定日期内，考生使用身份证号、准考证号及密码登录系统查询考试成绩！',
          side: 'bottom',
          align: 'start',
          nextBtnText: '下一步',
          prevBtnText: '上一步',
          closeBtnText: '完成'
        }
      },
      {
        element: '#step4',
        popover: {
          title: '志愿填报',
          description: '根据根据自己的考生成绩， 在系统中进行志愿填报，每个考生可以填报20个志愿！',
          side: 'top',
          align: 'start',
          nextBtnText: '下一步',
          prevBtnText: '上一步',
          closeBtnText: '完成'
        }
      },
      {
        element: '#step5',
        popover: {
          title: '录取查询',
          description: '考生登录系统查询，查询录取结果！',
          side: 'left',
          align: 'start',
          nextBtnText: '下一步',
          prevBtnText: '上一步',
          doneBtnText: '完成'
        }
      }
    ]
  })
  driverObj2.drive()
}
</script>
<template>
  <div>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>新手引导</span>
        </div>
      </template>
      <div class="card-body">
        <div>
          <el-button type="primary" @click="beginGuide">开始新手引导</el-button>
        </div>
        <div class="btn_list">
          <el-button id="first">新生注册</el-button>
          <el-button id="second">报名缴费</el-button>
          <el-button id="third">成绩查询</el-button>
          <el-button id="fourth">志愿填报</el-button>
          <el-button id="fifth">录取查询</el-button>
        </div>
      </div>
    </el-card>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>不同背景颜色遮罩</span>
        </div>
      </template>
      <div class="card-body">
        <div style="margin-bottom: 20px">
          <el-radio-group v-model="radio" size="large">
            <el-radio label="red">红色遮罩</el-radio>
            <el-radio label="blue">蓝色遮罩</el-radio>
            <el-radio label="yellow">黄色遮罩</el-radio>
          </el-radio-group>
        </div>
        <div><el-button type="primary" @click="beginGuide2">开始新生报名引导</el-button></div>
        <div class="btn_list">
          <el-button id="step1">新生注册</el-button>
          <el-button id="step2">报名缴费</el-button>
          <el-button id="step3">成绩查询</el-button>
          <el-button id="step4">志愿填报</el-button>
          <el-button id="step5">录取查询</el-button>
        </div>
      </div>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.el-card {
  margin-bottom: 20px;
}
.card-header {
  display: flex;
  justify-content: space-between;
}
.card-body {
  height: 200px;
}
.btn_list {
  width: 90%;
  margin: 0 auto;
  margin-top: 50px;
  display: flex;
  justify-content: flex-start;
  .el-button {
    width: 150px;
    height: 36px;
    margin-right: 30px;
  }
}

.dot-ripple {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: blueviolet;
  animation: eleAnimDot 1.2s ease-in-out infinite;
  transform-origin: center;
  border-radius: 50%;
  display: none;
}
</style>

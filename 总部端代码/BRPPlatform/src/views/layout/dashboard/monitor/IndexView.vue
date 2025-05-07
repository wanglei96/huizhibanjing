<script setup>
import chinaJson from 'echarts/map/js/china'
import * as echarts from 'echarts'
console.log(chinaJson)

import CountUp from 'vue-countup-v3'
const endValueRef = ref(25486)

const chart = shallowRef()

function randomData() {
  return Math.round(Math.random() * 500)
}
const mapData = [
  { name: '北京', value: randomData() },
  { name: '天津', value: randomData() },
  { name: '上海', value: randomData() },
  { name: '重庆', value: randomData() },
  { name: '河北', value: randomData() },
  { name: '河南', value: randomData() },
  { name: '云南', value: randomData() },
  { name: '辽宁', value: randomData() },
  { name: '黑龙江', value: randomData() },
  { name: '湖南', value: randomData() },
  { name: '安徽', value: randomData() },
  { name: '山东', value: randomData() },
  { name: '新疆', value: randomData() },
  { name: '江苏', value: randomData() },
  { name: '浙江', value: randomData() },
  { name: '江西', value: randomData() },
  { name: '湖北', value: randomData() },
  { name: '广西', value: randomData() },
  { name: '甘肃', value: randomData() },
  { name: '山西', value: randomData() },
  { name: '内蒙古', value: randomData() },
  { name: '陕西', value: randomData() },
  { name: '吉林', value: randomData() },
  { name: '福建', value: randomData() },
  { name: '贵州', value: randomData() },
  { name: '广东', value: randomData() },
  { name: '青海', value: randomData() },
  { name: '西藏', value: randomData() },
  { name: '四川', value: randomData() },
  { name: '宁夏', value: randomData() },
  { name: '海南', value: randomData() },
  { name: '台湾', value: randomData() },
  { name: '香港', value: randomData() },
  { name: '澳门', value: randomData() }
]

// 中国地图
function init() {
  let myChart = echarts.init(chart.value)
  echarts.registerMap('chain', chinaJson)
  let option = {
    //数据分段
    visualMap: {
      type: 'piecewise',
      show: true,
      splitList: [
        { start: 500, end: 600 },
        { start: 400, end: 500 },
        { start: 300, end: 400 },
        { start: 200, end: 300 },
        { start: 100, end: 200 },
        { start: 0, end: 100 }
      ],
      color: ['#5475f5', '#9feaa5', '#85daef', '#74e2ca', '#e6ac53', '#9fb5ea'],
      itemWidth: 8,
      itemHeight: 8
    },

    //配置属性
    series: [
      {
        type: 'map',
        map: 'china',
        roam: true,

        label: {
          color: '#333333',
          fontSize: 11,
          show: true //省份名称
          // normal: {
          //   show: true //省份名称
          // },
          // emphasis: {
          //   show: false
          // }
        },
        itemStyle: {
          borderColor: '#666',
          borderWidth: 1
        },
        zoom: 1.2, // 控制地图的放大缩小
        emphasis: {
          //高亮状态下的多边形和标签样式
          // 控制地图滑过后的颜色
          label: {
            color: 'red',
            fontSize: 12
          },
          itemStyle: {
            areaColor: '#1bc1ad',
            borderColor: 'red'
          }
        },
        data: mapData //数据
      }
    ]
  }
  myChart.setOption(option)
  // 为窗口加上宽度变化事件，当宽高发生改变时，调用echarts的resize()方法，调整图表尺寸
  window.addEventListener('resize', function () {
    myChart.resize()
  })
}

// 城市数据列表
const cityList = [
  {
    id: '001',
    name: '北京',
    percent: 90,
    number: 9999
  },
  {
    id: '002',
    name: '上海',
    percent: 85,
    number: 8569
  },
  {
    id: '003',
    name: '广州',
    percent: 80,
    number: 8021
  },
  {
    id: '004',
    name: '深圳',
    percent: 75,
    number: 7321
  },
  {
    id: '005',
    name: '山东',
    percent: 70,
    number: 7509
  },
  {
    id: '006',
    name: '湖北',
    percent: 65,
    number: 7012
  },
  {
    id: '007',
    name: '浙江',
    percent: 60,
    number: 6854
  },
  {
    id: '008',
    name: '广东',
    percent: 55,
    number: 6632
  },
  {
    id: '009',
    name: '江苏',
    percent: 50,
    number: 6324
  },
  {
    id: '010',
    name: '四川',
    percent: 45,
    number: 6012
  },
  {
    id: '011',
    name: '江西',
    percent: 40,
    number: 5867
  },
  {
    id: '012',
    name: '重庆',
    percent: 35,
    number: 5524
  },
  {
    id: '013',
    name: '河南',
    percent: 30,
    number: 5416
  },
  {
    id: '014',
    name: '安徽',
    percent: 25,
    number: 5236
  },
  {
    id: '015',
    name: '陕西',
    percent: 20,
    number: 5000
  },
  {
    id: '016',
    name: '山西',
    percent: 15,
    number: 4869
  }
]

// 动态时间显示
const nowDateref = ref('')
const nowTimeref = ref('')
const nowWeekref = ref('')
function getDate1() {
  let yy = new Date().getFullYear()
  let mm = new Date().getMonth() + 1
  let dd = new Date().getDate()
  let week = new Date().getDay()
  let hh = new Date().getHours()
  let ms = new Date().getSeconds() < 10 ? '0' + new Date().getSeconds() : new Date().getSeconds()
  let mf = new Date().getMinutes() < 10 ? '0' + new Date().getMinutes() : new Date().getMinutes()
  if (week == 1) {
    nowWeekref.value = '周一'
  } else if (week == 2) {
    nowWeekref.value = '周二'
  } else if (week == 3) {
    nowWeekref.value = '周三'
  } else if (week == 4) {
    nowWeekref.value = '周四'
  } else if (week == 5) {
    nowWeekref.value = '周五'
  } else if (week == 6) {
    nowWeekref.value = '周六'
  } else {
    nowWeekref.value = '周日'
  }
  // nowTimeref.value = hh + ":" + mf + ":" + ms;
  //ms是秒，这里可以根据自己需要调整格式
  nowTimeref.value = hh + ':' + mf + ':' + ms
  // nowDateref.value = yy + "年" + mm + "月" + dd + "日";
  nowDateref.value = yy + '-' + mm + '-' + dd
}

// 滚动数字
let num = ref(100)
function counter() {
  num.value++
  if (num.value > 999) {
    num.value = 100
    counter()
  }
}

// 浏览器分布饼图
const chartBrowser = ref()
function initBrowser() {
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(chartBrowser.value)
  // 指定图表的配置项和数据
  var option = {
    tooltip: {
      trigger: 'item'
    },
    legend: {
      bottom: '0',
      left: 'center',
      padding: [30, 0, 0, 0]
    },
    series: [
      {
        name: '浏览器分布',
        type: 'pie',
        radius: ['40%', '70%'],
        avoidLabelOverlap: false,
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: 40,
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: false
        },
        data: [
          { value: 1048, name: 'Chorme' },
          { value: 735, name: 'Edge' },
          { value: 580, name: 'FireFox' },
          { value: 150, name: 'safari' }
        ]
      }
    ]
  }
  // 使用刚指定的配置项和数据显示图表。
  myChart.setOption(option)
  // 为窗口加上宽度变化事件，当宽高发生改变时，调用echarts的resize()方法，调整图表尺寸
  window.addEventListener('resize', function () {
    myChart.resize()
  })
}

onMounted(() => {
  init()
  setInterval(getDate1, 1000)
  // setInterval(counter, 10)
  initBrowser()
})
</script>
<template>
  <div>
    <el-row>
      <el-col :xs="24" :sm="12" :md="8" :lg="6">
        <el-card shadow="never">
          <div class="jk-card">
            <div class="jk-img">
              <el-icon size="20"><View /></el-icon>
            </div>
            <div class="jk-number">25,251</div>
            <div class="jk-title">总浏览量（PV）</div>
            <div class="jk-info">
              <span style="display: inline-block; margin-right: 20px;">
                同比 3% <el-icon style="color: green"><Top /></el-icon>
              </span>
              <span style="display: inline-block; margin-right: 20px;">
                环比 1.2% <el-icon style="color: red"><Bottom /></el-icon>
              </span>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="12" :md="8" :lg="6">
        <el-card shadow="never">
          <div class="jk-card">
            <div class="jk-img">
              <el-icon size="20"><Avatar /></el-icon>
            </div>
            <div class="jk-number">1568</div>
            <div class="jk-title">访客数（UV）</div>
            <div class="jk-info">
              <span style="display: inline-block; margin-right: 20px;">
                同比 3% <el-icon style="color: green"><Top /></el-icon>
              </span>
              <span style="display: inline-block; margin-right: 20px;">
                环比 1.2% <el-icon style="color: red"><Bottom /></el-icon>
              </span>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="12" :md="8" :lg="6">
        <el-card shadow="never">
          <div class="jk-card">
            <div class="jk-img">
              <el-icon size="20"><Star /></el-icon>
            </div>
            <div class="jk-number">46.9%</div>
            <div class="jk-title">跳出率</div>
            <div class="jk-info">昨日跳出率：<span style="color: red">53.23%</span></div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="12" :md="8" :lg="6">
        <el-card shadow="never">
          <div class="jk-card">
            <div class="jk-img">
              <el-icon size="20"><Timer /></el-icon>
            </div>
            <div class="jk-number">00:05:39</div>
            <div class="jk-title">平均访问时长</div>
            <div class="jk-info">昨日平均访问时长：<span style="color: green">00:07:05</span></div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!-- 中国地图 -->
    <el-row style="margin-top: 15px">
      <el-col :xs="24" :sm="24" :md="18">
        <el-card shadow="never">
          <el-row>
            <el-col :xs="24" :sm="24" :md="18">
              <template #header>
                <div class="card-header">用户地理分布</div>
              </template>
              <div ref="chart" style="height: 500px"></div>
            </el-col>
            <el-col :xs="24" :sm="24" :md="6">
              <div class="city-item" v-for="(item, index) in cityList" :key="index">
                <div class="city-name">{{ item.name }}</div>
                <div class="city-progress">
                  <el-progress :percentage="item.percent" :stroke-width="10">
                    <el-button text>{{ item.number }}</el-button>
                  </el-progress>
                </div>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="6">
        <el-card shadow="never" style="margin-bottom: 15px; height: 265px">
          <template #header>
            <div class="card-header">在线总人数</div>
          </template>
          <div class="online">
            <div class="online-date">当前时间：{{ nowDateref }} {{ nowTimeref }}</div>
            <div class="online-number">
              <count-up
                :end-val="endValueRef"
                :duration="2.5"
                :decimal-places="0"
                :loop="2"
                :delay="3"
              ></count-up>
            </div>
            <div class="online-title">当前在线人数</div>
          </div>
        </el-card>
        <el-card shadow="never" style="height: 270px">
          <template #header>
            <div class="card-header">浏览器使用比例</div>
          </template>
          <div
            ref="chartBrowser"
            style="width: 100%; height: 160px;  margin-top: -10px; "
          ></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.jk-card {
  height: 180px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
}
.jk-img {
  width: 42px;
  height: 42px;
  border-radius: 50%;
  background-color: #e8f1ff;
  display: flex;
  justify-content: center;
  align-items: center;
}
.jk-number {
  width: 100%;
  text-align: center;
  font-size: 36px;
}
.jk-title {
  text-align: center;
  font-size: 14px;
  color: #999;
}
.jk-info {
  width: 100%;
  text-align: center;
  padding-top: 15px;
  font-size: 14px;
  border-top: 1px solid #ddd;
  display: flex;
  align-items: center;
  justify-content: center;
}

.city-item {
  width: 100%;
  display: flex;
  align-items: center;
  .city-name {
    min-width: 50px;
    font-size: 14px;
  }
  .city-progress {
    flex: 1;
    // width: 200px;
  }
}

.online {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  text-align: center;

  .online-date {
    font-size: 14px;
    color: #999;
  }
  .online-number {
    font-size: 42px;
    margin-top: 20px;
    margin-bottom: 20px;
  }
  .online-title {
    font-size: 14px;
  }
  .online-refresh {
    font-size: 14px;
    margin-top: 10px;
    color: #999;
    font-size: 12px;
  }
}
</style>

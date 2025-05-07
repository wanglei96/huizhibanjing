<script setup>
import * as echarts from 'echarts'
import { nextTick } from 'vue'

const myContainer = ref()
const orderContainer = ref()
const saleContainer = ref()
const visitContainer = ref()

const radio2 = ref('今日')
const radio3 = ref('销售额')

// 上部访问量折线图
function init() {
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(myContainer.value)
  // 指定图表的配置项和数据
  var option = {
    xAxis: {
      type: 'category',
      boundaryGap: false,
      axisLine: {
        show: false //不显示坐标轴轴线
      },
      data: ['1月', '2月', '3月', '4月']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [256, 512, 1024, 2048],
        type: 'line',
        areaStyle: {}
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

// 订单数柱状图
function initBar() {
  // 基于准备好的dom，初始化echarts实例
  var myChartBar = echarts.init(orderContainer.value)
  // 指定图表的配置项和数据
  var optionBar = {
    xAxis: {
      type: 'category',
      // show: false,
      data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12']
    },
    yAxis: {
      type: 'value'
      // show: false
    },
    series: [
      {
        data: [120, 200, 150, 80, 70, 110, 130, 200, 150, 60, 90, 140],
        type: 'bar'
      }
    ]
  }
  // 使用刚指定的配置项和数据显示图表。
  myChartBar.setOption(optionBar)
  // 为窗口加上宽度变化事件，当宽高发生改变时，调用echarts的resize()方法，调整图表尺寸
  window.addEventListener('resize', function () {
    myChartBar.resize()
  })
}

// 销售量柱状图
function initSale() {
  // 基于准备好的dom，初始化echarts实例
  var myChartSale = echarts.init(saleContainer.value)
  // 指定图表的配置项和数据
  var optionSale = {
    xAxis: {
      type: 'category',
      data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [120, 200, 150, 80, 70, 110, 130, 350, 220, 400, 210, 100],
        type: 'bar',
        barWidth: '35'
      }
    ]
  }
  // 使用刚指定的配置项和数据显示图表。
  myChartSale.setOption(optionSale)
  // 为窗口加上宽度变化事件，当宽高发生改变时，调用echarts的resize()方法，调整图表尺寸
  window.addEventListener('resize', function () {
    myChartSale.resize()
  })
}

// 访问量柱状图
function initVisit() {
  // 基于准备好的dom，初始化echarts实例
  var myChartSale = echarts.init(visitContainer.value)
  // 指定图表的配置项和数据
  var optionVisit = {
    xAxis: {
      type: 'category',
      data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [520, 100, 350, 180, 170, 610, 230, 150, 20, 100, 410, 200],
        type: 'bar',
        barWidth: '35'
      }
    ]
  }
  // 使用刚指定的配置项和数据显示图表。
  myChartSale.setOption(optionVisit)
  window.addEventListener('resize', function () {
    myChartSale.resize()
  })
}

function showSale(value) {
  if (value === '销售额') {
    initSale()
  }
  if (value === '访问量') {
    initVisit()
  }
}

onMounted(() => {
  init()
  initBar()
  initSale()
})
</script>
<template>
  <div class="tongji-body">
    <!-- 头部统计图表 -->
    <el-row  class="row-card">
      <el-col :xs="24" :sm="24" :md="12" :lg="6">
        <el-card shadow="hover">
          <!-- /////////// card content //////////// -->
          <div class="card-header">
            <span>8月总访问量</span>
            <span>
              <el-icon><More /> </el-icon>
            </span>
          </div>
          <div class="card-body">
            <div class="card-xse">
              <span style="font-size: 18px; margin-top: 12px; margin-right: 10px; color: #999">
                ¥
              </span>
              126,560
            </div>
            <div class="card-percent">
              <span>
                月同比10% <el-icon style="color: green"><CaretTop /></el-icon>
              </span>
              <span>
                日同比8% <el-icon style="color: red"><CaretBottom /></el-icon>
              </span>
            </div>
          </div>

          <div class="card-bottom">日访问量：25,862</div>
          <!-- /////////// card content //////////// -->
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="6">
        <el-card shadow="hover">
          <!-- /////////// card content //////////// -->
          <div class="card-header">
            <span>访问量</span>
            <span>
              <el-icon><el-tag class="ml-2" type="danger">月</el-tag> </el-icon>
            </span>
          </div>
          <div class="card-body">
            <div ref="myContainer" style="width: 100%; height: 100%; padding-top: 10px"></div>
          </div>

          <div class="card-bottom">本月访问量：265487</div>
          <!-- /////////// card content //////////// -->
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="6">
        <el-card shadow="hover">
          <!-- /////////// card content //////////// -->
          <div class="card-header">
            <span>订单数</span>
            <span>
              <el-icon><el-tag class="ml-2" type="warning">月</el-tag> </el-icon>
            </span>
          </div>
          <div class="card-body">
            <div ref="orderContainer" style="width: 100%; height: 100%; padding-top: 10px"></div>
          </div>

          <div class="card-bottom">本月订单数：265487</div>
          <!-- /////////// card content //////////// -->
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="6">
        <el-card shadow="hover">
          <!-- /////////// card content //////////// -->
          <div class="card-header">
            <span>本月任务完成情况</span>
            <span>
              <el-icon><el-tag class="ml-2" type="warning">月</el-tag> </el-icon>
            </span>
          </div>
          <div class="card-body">
            <div class="card-xse">
              <span style="font-size: 14px; margin-top: 12px; margin-right: 10px; color: #999">
                已完成
              </span>
              88%
            </div>
            <div class="demo-progress">
              <el-progress :percentage="88" :stroke-width="10" striped striped-flow />
            </div>
          </div>

          <div class="card-bottom">本月订单数：265487</div>
          <!-- /////////// card content //////////// -->
        </el-card>
      </el-col>
    </el-row>
    <!-- 柱状图 -->
    <el-card shadow="never" style="margin-top: 15px">
      <template #header>
        <div class="card-header">
          <div class="card-tabs">数据统计</div>
          <div>
            <el-radio-group v-model="radio2">
              <el-radio-button label="今日" />
              <el-radio-button label="本周" />
              <el-radio-button label="本月" />
              <el-radio-button label="本年" />
            </el-radio-group>
          </div>
        </div>
      </template>
      <el-row >
        <el-col :span="18">
          <div>
            <el-radio-group v-model="radio3" size="small" @change="showSale">
              <el-radio-button label="销售额" />
              <el-radio-button label="访问量" />
            </el-radio-group>
          </div>
          <div
            ref="saleContainer"
            style="width: 100%; height: 400px; margin-top: 10px"
            v-if="radio3 === '销售额'"
          ></div>
          <div
            ref="visitContainer"
            style="width: 100%; height: 400px; margin-top: 10px"
            v-if="radio3 === '访问量'"
          ></div>
        </el-col>
        <el-col :span="6">
          <div class="sale-paihang">
            <div class="sale-title">门店销售排行榜</div>
            <div class="list-item">
              <span class="sale-item-index">1</span>
              <span class="sale-item-title">保龙仓金百林店</span>
              <span class="sale-item-amout">¥ 18,235</span>
            </div>
            <div class="list-item">
              <span class="sale-item-index">2</span>
              <span class="sale-item-title">保龙仓中华店店</span>
              <span class="sale-item-amout">¥ 36,142</span>
            </div>
            <div class="list-item">
              <span class="sale-item-index">3</span>
              <span class="sale-item-title">保龙仓西二环店</span>
              <span class="sale-item-amout">¥ 1245</span>
            </div>
            <div class="list-item">
              <span class="sale-item-index">4</span>
              <span class="sale-item-title">保龙仓西王店</span>
              <span class="sale-item-amout">¥ 10,412</span>
            </div>
            <div class="list-item">
              <span class="sale-item-index">5</span>
              <span class="sale-item-title">保龙仓槐底店</span>
              <span class="sale-item-amout">¥ 15,502</span>
            </div>
            <div class="list-item">
              <span class="sale-item-index">6</span>
              <span class="sale-item-title">保龙仓建华店</span>
              <span class="sale-item-amout">¥ 5,252</span>
            </div>
            <div class="list-item">
              <span class="sale-item-index">7</span>
              <span class="sale-item-title">保龙仓丰收店</span>
              <span class="sale-item-amout">¥ 4,128</span>
            </div>
            <div class="list-item">
              <span class="sale-item-index">8</span>
              <span class="sale-item-title">保龙仓柳营店</span>
              <span class="sale-item-amout">¥ 9,254</span>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.tongji-body {
  font-size: 14px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.card-tabs {
  width: 50%;
}
.row-card {
  .el-card {
    height: 190px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    .card-header {
      display: flex;
      padding-bottom: 0px;
      justify-content: space-between;
      align-items: center;
      font-size: 13px;
      color: #999999;
    }
    .card-body {
      display: flex;
      flex-direction: column;
      height: 100px;
      align-items: center;
      .card-xse {
        width: 100%;
        height: 70px;
        text-align: left;
        display: flex;
        align-items: center;
        justify-content: flex-start;
        font-size: 32px;
      }
      .card-percent {
        display: flex;
        margin-bottom: 10px;
        width: 100%;
        color: #666;
        display: flex;
        justify-content: space-between;
      }
    }
    .card-bottom {
      font-size: 14px;
      border-top: 1px solid #ccc;
      padding-top: 15px;
    }
  }
}

.demo-progress .el-progress--line {
  margin-bottom: 15px;
  width: 250px;
}

.sale-list {
  width: 100%;
  display: flex;
  height: 36px;
  align-items: center;
  padding: 0 20px;
}
.sale-paihang {
  padding: 15px;
}

.sale-title {
  line-height: 36px;
}

.list-item {
  width: 100%;
  height: 40px;
  display: flex;
  align-items: center;
  border-bottom: 1px solid #ddd;
}

.sale-item-index {
  width: 18px;
  height: 18px;
  background-color: #314659;
  font-size: 12px;
  border-radius: 50%;
  margin-right: 10px;
  color: #ffffff;
  display: flex;
  align-items: center;
  justify-content: center;
}
.sale-item-amout {
  margin-left: auto;
  color: #999;
}
</style>

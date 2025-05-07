<script setup>
import * as echarts from 'echarts'
import { nextTick, ref } from 'vue'
import common from '@/js/common'

// 当前门店id
const companyId = ref(`${common.getLocalStorage('companyId')}`)

const borrowContainer = ref()
const memberContainer = ref()
const memberAgeGroupContainer = ref()
const memberBookBoxContainer = ref()
const memberRiseContainer = ref()
const bookInfoContainer = ref()
const bookAgeGroupContainer = ref()

const memberInfoTotal = ref({})

/**
 * 获取会员统计信息
 */
function bindMemberInfoTotal() {
  let params = {
    companyId: companyId.value
  }
  common.call('GetMemberInfoTotal', params, function (res) {
    memberInfoTotal.value = res.data
  })
}

/**
 * 14天借阅人数统计
 */
function bindFourteenBookOrderTotal() {
  let params = {
    companyId: companyId.value,
    dateType: dateType.value
  }
  common.call('GetFourteenBookOrderTotal', params, (res) => {
    let dataList = res.data
    let orderDate = []
    let offlineCount = []
    let onlineCount = []
    dataList.forEach((element) => {
      orderDate.push(element.orderDate)
      offlineCount.push(element.offlineCount)
      onlineCount.push(element.onlineCount)
    })
    bindFourteenBookOrderTotalBarChart(orderDate, offlineCount, onlineCount)
  })
}

/**
 * 14天借阅人数统计图
 */
function bindFourteenBookOrderTotalBarChart(orderDate, offlineCount, onlineCount) {
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(borrowContainer.value)
  // 指定图表的配置项和数据
  var option = {
    title: {
      text: '借阅人数统计'
    },
    tooltip: {
      trigger: 'axis'
    },
    legend: {
      orient: 'horizontal',
      left: 'center',
      data: ['配送到家', '到店借还'],
      textStyle: {
        fontSize: 12
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '6%',
      containLabel: true
    },
    toolbox: {
      feature: {
        saveAsImage: {}
      }
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: orderDate,
      axisLabel: {
        rotate: 45, // 设置倾斜角度
        textStyle: {}
      }
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        name: '配送到家',
        type: 'line',
        data: onlineCount,
        label: {
          show: true,
          position: 'top'
        }
      },
      {
        name: '到店借还',
        type: 'line',
        data: offlineCount,
        label: {
          show: true,
          position: 'top'
        }
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

/**
 * 获取年卡会员数据
 */
function bindMemberInfoListTotal() {
  let params = {
    companyId: companyId.value
  }
  common.call('GetMemberInfoListTotal', params, (res) => {
    let dataList = res.data
    let xDataList = []
    let yDataList = []
    dataList.forEach((element) => {
      xDataList.push(element.name)
      yDataList.push(element.value)
    })
    bindMemberInfoListTotalBarChart(xDataList, yDataList)
  })
}

/**
 * 绑定年卡会员统计图
 */
function bindMemberInfoListTotalBarChart(xDataList, yDataList) {
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(memberContainer.value)
  // 指定图表的配置项和数据
  var option = {
    title: {
      text: '年卡会员数据',
      left: 'left'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: xDataList,
      axisTick: {
        alignWithLabel: true
      },
      axisLabel: {
        rotate: 45
      }
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: yDataList,
        name: '数量',
        type: 'bar',
        barWidth: '60%',
        label: {
          show: true,
          position: 'top' // 可根据需要设置位置
        }
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

/**
 * 获取年卡会员各个年龄段分布
 */
function bindMemberAgeGroupTotal() {
  let params = {
    companyId: companyId.value
  }
  common.call('GetMemberAgeGroupTotal', params, (res) => {
    let dataList = res.data
    bindMemberAgeGroupTotalBarChart(dataList)
  })
}

function bindMemberAgeGroupTotalBarChart(dataList) {
  var myChart = echarts.init(memberAgeGroupContainer.value)
  var option = {
    title: {
      text: '年卡会员各个年龄段分布',
      left: 'left'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      bottom: 10,
      orient: 'horizontal',
      left: 'center',
      textStyle: {
        fontSize: 12
      }
    },
    series: [
      {
        name: '年龄分布',
        type: 'pie',
        radius: '50%',
        data: dataList,
        label: {
          formatter: '{b}: ({c}) ({d}%)' // {b}表示数据项名称，{c}表示数值，{d}%表示百分比
        },
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
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

/**
 * 获取年卡会员书箱数据
 */
function bindMemberBookBoxTotal() {
  let params = {
    companyId: companyId.value
  }
  common.call('GetMemberBookboxTotal', params, (res) => {
    let dataList = res.data
    let xDataList = []
    let yDataList = []
    dataList.forEach((element) => {
      xDataList.push(element.name)
      yDataList.push(element.value)
    })
    bindMemberBookBoxTotalBarChart(xDataList, yDataList)
  })
}

/**
 * 绑定年卡会员书箱数据统计图
 */
function bindMemberBookBoxTotalBarChart(xDataList, yDataList) {
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(memberBookBoxContainer.value)
  // 指定图表的配置项和数据
  var option = {
    title: {
      text: '年卡会员书箱数据',
      left: 'left'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: xDataList,
      axisTick: {
        alignWithLabel: true
      },
      axisLabel: {
        rotate: 45
      }
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: yDataList,
        name: '数量',
        type: 'bar',
        barWidth: '60%',
        itemStyle: {
          color: '#fac858'
        },
        label: {
          show: true,
          position: 'top' // 可根据需要设置位置
        }
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

/**
 * 获取近一年年卡会员增长统计信息
 */
function bindYearMemberCardGrowthTotal() {
  let params = {
    companyId: companyId.value
  }
  common.call('GetYearMemberCardGrowthTotal', params, (res) => {
    let dataList = res.data
    let xDataList = []
    let yDataList = []
    dataList.forEach((element) => {
      xDataList.push(element.orderMonth + '(' + element.orderCount + ')')
      yDataList.push(element.orderCount)
    })
    bindYearMemberCardGrowthTotalBarChart(xDataList, yDataList)
  })
}

/**
 * 近一年年卡会员增长统计图
 * @param {*} xDataList
 * @param {*} yDataList
 */
function bindYearMemberCardGrowthTotalBarChart(xDataList, yDataList) {
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(memberRiseContainer.value)
  // 指定图表的配置项和数据
  var option = {
    title: {
      text: '近一年年卡会员增长统计',
      left: 'left'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: xDataList,
      axisTick: {
        alignWithLabel: true
      },
      axisLabel: {
        rotate: 45
      }
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: yDataList,
        name: '数量',
        type: 'bar',
        barWidth: '60%',
        label: {
          show: true,
          position: 'top' // 可根据需要设置位置
        }
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

/**
 * 获取图书数据
 */
function bindBookInfoListTotal() {
  let params = {
    companyId: companyId.value
  }
  common.call('GetBookInfoListTotal', params, (res) => {
    let dataList = res.data
    let xDataList = []
    let yDataList = []
    dataList.forEach((element) => {
      xDataList.push(element.name)
      yDataList.push(element.value)
    })
    bindBookInfoListTotalBarChart(xDataList, yDataList)
  })
}

/**
 * 获取图书数据统计图
 * @param {*} xDataList
 * @param {*} yDataList
 */
function bindBookInfoListTotalBarChart(xDataList, yDataList) {
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(bookInfoContainer.value)
  // 指定图表的配置项和数据
  var option = {
    title: {
      text: '图书数据',
      left: 'left'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: xDataList,
      axisTick: {
        alignWithLabel: true
      },
      axisLabel: {
        rotate: 45
      }
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: yDataList,
        name: '数量',
        type: 'bar',
        barWidth: '60%',
        itemStyle: {
          color: '#91cc75'
        },
        label: {
          show: true,
          position: 'top' // 可根据需要设置位置
        }
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

/**
 * 获取图书年龄段数据
 */
function bindBookAgeGroupTotal() {
  let params = {
    companyId: companyId.value
  }
  common.call('GetBookAgeGroupListTotal', params, (res) => {
    let dataList = res.data
    bindBookAgeGroupTotalBarChart(dataList)
  })
}

function bindBookAgeGroupTotalBarChart(dataList) {
  var myChart = echarts.init(bookAgeGroupContainer.value)
  var option = {
    title: {
      text: '图书各年龄段比例',
      left: 'left'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      bottom: 10,
      orient: 'horizontal',
      left: 'center',
      textStyle: {
        fontSize: 12
      }
    },
    series: [
      {
        name: '年龄分布',
        type: 'pie',
        radius: '50%',
        data: dataList,
        label: {
          formatter: '{b}: ({c}) ({d}%)' // {b}表示数据项名称，{c}表示数值，{d}%表示百分比
        },
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
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

function init() {
  //获取会员统计信息
  bindMemberInfoTotal()
  //获取14天借阅人数统计
  bindFourteenBookOrderTotal()
  //获取年卡会员数据
  bindMemberInfoListTotal()
  //获取年卡会员各个年龄段分布
  bindMemberAgeGroupTotal()
  //获取年卡会员书箱数据
  bindMemberBookBoxTotal()
  //获取近一年年卡会员增长数量
  bindYearMemberCardGrowthTotal()
  //获取图书数据
  bindBookInfoListTotal()
  //获取图书年龄段统计信息
  bindBookAgeGroupTotal()
}

const dateType = ref('FourteenDay')

function fourteenBookOrderChange() {
  //获取14天借阅人数统计
  bindFourteenBookOrderTotal()
}

onMounted(() => {
  init()
})
</script>
<template>
  <div class="tongji-body">
    <el-card shadow="hover">
      <div class="top-box">
        <div style="text-align: center">
          <router-link :to="{
            path: '/member/memberCardQuery/MemberCardQueryView',
            query: { memberCardIds: memberInfoTotal.notBorrowedForFourteenDaysIds }
          }" style="text-decoration: none;">
            <div style="font-size: 20px; margin-bottom: 10px">{{ memberInfoTotal.notBorrowedForFourteenDaysCount }}</div>
            <div>14天未借阅会员</div>
          </router-link>
        </div>
        <div style="text-align: center">
          <router-link :to="{
            path: '/member/memberCardQuery/MemberCardQueryView',
            query: { memberCardIds: memberInfoTotal.expiredNotCommunicaIds }
          }" style="text-decoration: none;">
            <div style="font-size: 20px; margin-bottom: 10px">{{ memberInfoTotal.expiredNotCommunicaCount }}</div>
            <div>已到期未沟通会员</div>
          </router-link>
        </div>
        <div style="text-align: center">
          <router-link :to="{
            path: '/member/memberCardQuery/MemberCardQueryView',
            query: { memberCardIds: memberInfoTotal.expiringThirtyDayIds }
          }" style="text-decoration: none;">
            <div style="font-size: 20px; margin-bottom: 10px">
              {{ memberInfoTotal.expiringThirtyDayCount }}
            </div>
            <div>30天内到期的会员</div>
          </router-link>
        </div>
        <div style="text-align: center">
          <router-link :to="{
            path: '/member/memberCardQuery/MemberCardQueryView',
            query: { memberCardIds: memberInfoTotal.suspendMemberIds }
          }" style="text-decoration: none;">
            <div style="font-size: 20px; margin-bottom: 10px">
              {{ memberInfoTotal.suspendMemberCount }}
            </div>
            <div>正在暂停的会员</div>
          </router-link>
        </div>
        <div style="text-align: center">
          <router-link :to="{
            path: '/member/memberCardQuery/MemberCardQueryView',
            query: { memberCardIds: memberInfoTotal.expiredBooksIds }
          }" style="text-decoration: none;">
            <div style="font-size: 20px; margin-bottom: 10px">
              {{ memberInfoTotal.expiredBooksCount }}
            </div>
            <div>到期未还书的会员</div>
          </router-link>
        </div>
        <div style="text-align: center">
          <router-link :to="{
            path: '/member/memberCardQuery/MemberCardQueryView',
            query: { memberCardIds: memberInfoTotal.expiredIds }
          }" style="text-decoration: none;">
            <div style="font-size: 20px; margin-bottom: 10px">{{ memberInfoTotal.expiredCount }}</div>
            <div>已经到期的会员</div>
          </router-link>
        </div>
      </div>
    </el-card>
    <el-row class="row-card" style="margin-top: 20px">
      <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <el-card shadow="hover">
          <div>
            <el-radio-group v-model="dateType" @change="fourteenBookOrderChange">
              <el-radio-button label="FourteenDay">14天</el-radio-button>
              <el-radio-button label="Year">年</el-radio-button>
              <el-radio-button label="Month">月</el-radio-button>
            </el-radio-group>
          </div>
          <div class="card-body">
            <div ref="borrowContainer" style="width: 100%; height: 100%; padding-top: 10px"></div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row class="row-card" style="margin-top: 20px">
      <el-col :xs="12" :sm="12" :md="12" :lg="12">
        <el-card shadow="hover">
          <div class="card-body">
            <div ref="memberContainer" style="width: 100%; height: 100%; padding-top: 10px"></div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="12" :md="12" :lg="12">
        <el-card shadow="hover">
          <div class="card-body">
            <div ref="memberAgeGroupContainer" style="width: 100%; height: 100%; padding-top: 10px"></div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row class="row-card" style="margin-top: 20px">
      <el-col :xs="12" :sm="12" :md="12" :lg="12">
        <el-card shadow="hover">
          <div class="card-body">
            <div ref="memberBookBoxContainer" style="width: 100%; height: 100%; padding-top: 10px"></div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="12" :md="12" :lg="12">
        <el-card shadow="hover">
          <div class="card-body">
            <div ref="memberRiseContainer" style="width: 100%; height: 100%; padding-top: 10px"></div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row class="row-card" style="margin-top: 20px">
      <el-col :xs="12" :sm="12" :md="12" :lg="12">
        <el-card shadow="hover">
          <div class="card-body">
            <div ref="bookInfoContainer" style="width: 100%; height: 100%; padding-top: 10px"></div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="12" :md="12" :lg="12">
        <el-card shadow="hover">
          <div class="card-body">
            <div ref="bookAgeGroupContainer" style="width: 100%; height: 100%; padding-top: 10px"></div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.top-box {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

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
    height: 550px;
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
      height: 500px;
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
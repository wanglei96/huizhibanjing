<script setup>
import CountUp from 'vue-countup-v3'

const endValueRef = ref(8888888)
function resetCount() {
  endValueRef.value = 88888888
}
function changeCount() {
  endValueRef.value = 12345678
}

const endValueRef2 = ref(3562.28)
function resetCount2() {
  endValueRef2.value = 658238.89
}
function changeCount2() {
  endValueRef2.value = 6854723.96
}
const options = {
  prefix: '$ ', // 数字前缀
  suffix: '' // 数字后缀
}
</script>
<template>
  <div>
    <el-row>
      <el-col :xs="23" :sm="24" :md="12" :lg="12">
        <el-card shadow="never">
          <template #header>
            <div class="card-header">
              <span>滚动数字</span>
              <span>...</span>
            </div>
          </template>
          <div class="card-body">
            <div class="counter-num">
              <count-up
                :end-val="endValueRef"
                :duration="2.5"
                :decimal-places="0"
                :loop="2"
                :delay="3"
              ></count-up>
            </div>
            <div class="counter-btn">
              <el-button @click="resetCount">重新开始</el-button>
              <el-button @click="changeCount">换个数字</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="23" :sm="24" :md="12" :lg="12">
        <el-card shadow="never">
          <template #header>
            <div class="card-header">
              <span>带前缀滚动数字</span>
              <span>...</span>
            </div>
          </template>
          <div class="card-body">
            <div class="counter-num" style="color: #4e6ef2">
              <count-up
                :end-val="endValueRef2"
                :duration="2.5"
                :decimal-places="2"
                :loop="2"
                :options="options"
                :delay="3"
              ></count-up>
            </div>
            <div class="counter-btn">
              <el-button @click="resetCount2">重新开始</el-button>
              <el-button @click="changeCount2">换个数字</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.card-header {
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.card-body {
  height: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.counter-num {
  font-size: 32px;
}
.counter-btn {
  margin-top: 50px;
}
</style>

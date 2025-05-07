<script setup>
import { provinceAndCityData, regionData } from 'element-china-area-data'

// 省市两级联动
const options = ref(provinceAndCityData)
const selectedOptions = ref([])
const handleChange = () => {
  console.log(selectedOptions.value)
  if (selectedOptions.value[0] != null && selectedOptions.value[1] != null) {
    // const str = CodeToText[selectedOptions.value[0]] + '/' + CodeToText[selectedOptions.value[1]]
    console.log(selectedOptions.value[0])
    console.log(selectedOptions.value[1])
  }
}

// 省市区三级联动
const options2 = ref(regionData)
const selectedOptions2 = ref([])
const handleChange2 = () => {
  console.log(selectedOptions2.value)
  if (
    selectedOptions2.value[0] != null &&
    selectedOptions2.value[1] != null &&
    selectedOptions2.value[2] != null
  ) {
    console.log(selectedOptions2.value[0])
    console.log(selectedOptions2.value[1])
    console.log(selectedOptions2.value[2])
  }
}
</script>
<template>
  <div>
    <el-row >
      <el-col :xs="24" :sm="24" :md="12" :lg="12">
        <el-card shadow="never">
          <template #header>
            <div class="card-header">
              <span>省市两级联动</span>
            </div>
          </template>
          <div class="card-body">
            <div>
              <el-cascader
                size="large"
                :options="options"
                v-model="selectedOptions"
                @change="handleChange"
              />
            </div>
            <div style="margin-top: 20px; font-size: 14px">已选择：{{ selectedOptions }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12">
        <el-card shadow="never">
          <template #header>
            <div class="card-header">
              <span>省市区三级联动</span>
            </div>
          </template>
          <div class="card-body">
            <div>
              <el-cascader
                size="large"
                :options="options2"
                v-model="selectedOptions2"
                @change="handleChange2"
              />
            </div>
            <div style="margin-top: 20px; font-size: 14px">已选择：{{ selectedOptions2 }}</div>
            <div></div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12"> </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12"></el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.el-card {
  margin-bottom: 20px;
}
.card-body {
  height: 250px;
  display: flex;
  flex-direction: column;
  align-items: center;
  // justify-content: center;
}
</style>

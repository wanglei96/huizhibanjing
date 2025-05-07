<script setup>
const form = reactive({
  name: '',
  region: '',
  date1: '',
  date2: '',
  delivery: false,
  type: [],
  resource: '',
  desc: ''
})

const onSubmit = () => {
  console.log('submit!')
}
</script>
<template>
  <div>
    <el-card shadow="never" style="margin-bottom: 15px">
      <h4>基础表单</h4>
      <div style="margin-top: 5px; font-size: 14px; color: #aaa">
        表单是UI设计中很常见的元素，不管是PC端还是移动端，表单几乎是无法规避的UI控件，用户可以通过表单完成线上购物、信息交换、文章订阅等任务，使用范畴极为广泛且牵扯到方方面面。
      </div>
    </el-card>
    <el-card shadow="never" class="basicForm">
      <el-form
        :model="form"
        label-width="120px"
        style="width: 800px; margin: 0 auto; margin-top: 30px; margin-bottom: 30px"
      >
        <el-form-item label="活动名称">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="活动区域">
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="上海" value="shanghai" />
            <el-option label="北京" value="beijing" />
          </el-select>
        </el-form-item>
        <el-form-item label="活动时间">
          <el-col :span="11">
            <el-date-picker
              v-model="form.date1"
              type="date"
              placeholder="请选择日期"
              style="width: 100%"
            />
          </el-col>
          <el-col :span="2" class="text-center">
            <span class="text-gray-500">-</span>
          </el-col>
          <el-col :span="11">
            <el-time-picker v-model="form.date2" placeholder="请选择时间" style="width: 100%" />
          </el-col>
        </el-form-item>
        <el-form-item label="即时送达">
          <el-switch v-model="form.delivery" />
        </el-form-item>
        <el-form-item label="活动类型">
          <el-checkbox-group v-model="form.type">
            <el-checkbox label="在线活动" name="type" />
            <el-checkbox label="促销活动" name="type" />
            <el-checkbox label="线下活动" name="type" />
            <el-checkbox label="简单活动" name="type" />
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="资源">
          <el-radio-group v-model="form.resource">
            <el-radio label="赞助商" />
            <el-radio label="地点" />
          </el-radio-group>
        </el-form-item>
        <el-form-item label="活动形式">
          <el-input v-model="form.desc" type="textarea" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">创建</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style lang="scss" scoped></style>

<script setup>
import StatusText from '@/components/extension/StatusText.vue'
const flag = ref(true)
let customSize = ref(20)
let customColor = ref('#1677ff')

function handleChange(data) {
  customSize.value = data
}

function handleColor(data) {
  customColor.value = data
}
</script>
<template>
  <div>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>基础样式</span>
        </div>
      </template>
      <div class="card-body">
        <el-form>
          <el-form-item label="效果展示：">
            <StatusText txt="正在运行" color="#48b612" />
            <StatusText txt="系统故障" color="#e88f21" />
            <StatusText txt="系统宕机" color="#ef0709" />
            <StatusText txt="系统关机" color="#898686" />
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>定制样式</span>
        </div>
      </template>
      <div class="card-body">
        <el-form>
          <el-form-item label="效果展示：">
            <StatusText txt="定制样式" :size="customSize" :color="customColor" :showRipple="flag" />
          </el-form-item>
          <el-divider />
          <el-form-item label="关闭动画：">
            <el-switch size="small" v-model="flag" active-text="打开" inactive-text="关闭" />
          </el-form-item>
          <el-form-item label="尺寸设置：">
            <el-slider
              v-model="customSize"
              :min="6"
              :max="20"
              style="width: 100px"
              @change="handleChange"
            />
          </el-form-item>
          <el-form-item label="选择颜色：">
            <el-color-picker v-model="customColor" @change="handleColor" />
          </el-form-item>
        </el-form>
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
</style>

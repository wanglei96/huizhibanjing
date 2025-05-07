<script setup>
const inputValue = ref('')
const dynamicTags = ref(['美食标签', '时尚标签', '旅游标签', '运动健身标签', '影视标签'])
const inputVisible = ref(false)
const InputRef = ref()

const size = ref('default')
const color = ref('success')
const effect = ref('dark')
const xingzhuang = ref('round')

const handleClose = (tag) => {
  dynamicTags.value.splice(dynamicTags.value.indexOf(tag), 1)
}

const showInput = () => {
  inputVisible.value = true
  nextTick(() => {
    InputRef.value.input.focus()
  })
}

const handleInputConfirm = () => {
  if (inputValue.value) {
    dynamicTags.value.push(inputValue.value)
  }
  inputVisible.value = false
  inputValue.value = ''
}
</script>
<template>
  <div>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>动态编辑标签</span>
        </div>
      </template>
      <div class="card-body">
        <el-form-item label="标签列表：">
          <div class="tag-list">
            <el-tag
              v-for="tag in dynamicTags"
              :key="tag"
              class="mx-1"
              :size="size"
              :effect="effect"
              :type="color"
              round
              closable
              :disable-transitions="false"
              @close="handleClose(tag)"
            >
              {{ tag }}
            </el-tag>
            <el-input
              v-if="inputVisible"
              ref="InputRef"
              v-model="inputValue"
              class="ml-1 w-20"
              size="small"
              @keyup.enter="handleInputConfirm"
              @blur="handleInputConfirm"
            />
            <el-button v-else class="button-new-tag ml-1" size="small" @click="showInput">
              + 新标签
            </el-button>
          </div>
        </el-form-item>
        <el-form-item label="">
          {{ dynamicTags }}
        </el-form-item>
        <el-divider />
        <el-form-item label="标签大小：">
          <el-radio-group v-model="size">
            <el-radio label="small">Small</el-radio>
            <el-radio label="default">Default</el-radio>
            <el-radio label="large">Large</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="标签颜色：">
          <el-radio-group v-model="color">
            <el-radio label="">Primary</el-radio>
            <el-radio label="success">Success</el-radio>
            <el-radio label="info">Info</el-radio>
            <el-radio label="warning">Warning</el-radio>
            <el-radio label="danger">Danger</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="标签主题：">
          <el-radio-group v-model="effect">
            <el-radio label="light">Light</el-radio>
            <el-radio label="dark">Dark</el-radio>
            <el-radio label="plain">Plain</el-radio>
          </el-radio-group>
        </el-form-item>
      </div>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.tag-list {
  display: flex;
  justify-content: flex-start;
  .el-tag {
    margin-right: 10px;
  }
  .el-input {
    width: 100px;
  }
}

.added-tags {
  font-size: 14px;
  margin-top: 20px;
  color: #999;
}
</style>

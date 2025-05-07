<script setup>
// 引入
import TEditor from '@/components/extension/TEditor.vue'
const formState = reactive({ contents: '' })
const disabled = ref(false)
const getContent = (v) => {
  formState.contents = v
}

function showContent() {
  ElMessageBox.alert(formState.contents, '文本框内容', {
    confirmButtonText: 'OK'
  })
}

let htmlContent = ref('')
function setContent() {
  const content = `<div style="font-size: 24px;text-align: center;text-decoration-line: none;
  background: linear-gradient(
    90deg,
    rgba(88, 104, 255, 1) 4.44%,
    rgba(152, 72, 250, 1) 51.46%,
    rgba(82, 105, 255, 1) 97.98%
  );
  -webkit-background-clip: text;
  color: transparent;"><h1>Element Admin</h1></div>
  <div style="text-align: center; font-size: 22px; margin-top: -15px;"><h1>基于Vue3的通用型后台管理模板</h1></div>
  <div style="font-size: 18px; text-align: center;">基于Vue3、Vite、Pinia、ElementPlus、Axios 全新技术栈</div>`
  htmlContent.value = content
}
function disabledEditor() {
  disabled.value = true
}
function enabledEditor() {
  disabled.value = false
}
</script>
<template>
  <div>
    <el-card>
      <div style="margin-bottom: 20px">
        <el-button type="primary" @click="showContent">获取编辑器内容</el-button>
        <el-button type="primary" @click="setContent">设置编辑器内容</el-button>
        <el-button type="danger" v-if="!disabled" @click="disabledEditor">禁用编辑器</el-button>
        <el-button type="default" v-if="disabled" @click="enabledEditor">启用编辑器</el-button>
      </div>
      <TEditor
        ref="editor"
        v-model="formState.content"
        :value="htmlContent"
        :disabled="disabled"
        @getContent="getContent"
      />
    </el-card>
  </div>
</template>

<style lang="scss" scoped></style>

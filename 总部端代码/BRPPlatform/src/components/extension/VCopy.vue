<script setup>
const props = defineProps({
  data: {
    type: String,
    default: ''
  }
})

const msg = ref('复制')
function copyContent() {
  copy(props.data)
  msg.value = '复制成功'
  setTimeout(() => {
    msg.value = '复制'
  }, 500)
}

function copy(data) {
  let textarea = document.createElement('textarea')
  textarea.value = data
  textarea.id = 'creatDom'
  document.body.appendChild(textarea)
  textarea.select()
  document.execCommand('copy')
  document.body.removeChild(textarea)
}
</script>
<template>
  <div>
    <span style="cursor: pointer">
      <el-tooltip :content="msg" placement="top">
        <el-icon @click="copyContent"> <DocumentCopy /></el-icon>
      </el-tooltip>
    </span>
  </div>
</template>

<style lang="scss" scoped></style>

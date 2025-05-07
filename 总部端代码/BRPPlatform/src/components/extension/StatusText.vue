<script setup>
import { watch } from 'vue'

const props = defineProps({
  txt: {
    type: String,
    default: '正在运行'
  },
  color: {
    type: String,
    default: '#1677ff'
  },
  size: {
    type: Number,
    default: 12
  },
  showRipple: {
    type: Boolean,
    default: true
  }
})

var size = computed(() => props.size + 'px')
</script>
<template>
  <div class="ripple">
    <span class="ripple-box">
      <span class="item-ripple" :style="{ display: showRipple ? 'inline-block' : '' }"></span>
    </span>
    <span style="display: inline-block; margin-left: 5px">{{ props.txt }}</span>
  </div>
</template>

<style lang="scss" scoped>
.ripple {
  display: inline-flex;
  justify-content: flex-start;
  align-items: center;
  align-items: center;
  margin-right: 20px;
}
.ripple-box {
  display: inline-block;
  width: v-bind(size);
  height: v-bind(size);
  position: relative;
  background: v-bind(color);
  border-radius: 50%;
}
.item-ripple {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border-radius: 50%;
  background: v-bind(color);
  animation: aniRipple 1.5s ease-in-out infinite;
  transform-origin: center;
  display: none;
}
@keyframes aniRipple {
  0% {
    transform: scale(0.9);
    opacity: 0.7;
  }
  to {
    transform: scale(2.2);
    opacity: 0;
  }
}
</style>

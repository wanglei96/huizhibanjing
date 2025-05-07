<script setup>
import { useGlobalConfigStore } from '@/stores/globalConfig'
const store = useGlobalConfigStore()
</script>
<template>
  <el-card shadow="never" class="msg_card">
    <el-row>
      <el-col :span="2">
        <div class="msg_aside">
          <div class="msg_aside_item">
            <router-link to="/personal/message/notice" active-class="link_active" class="linkStyle"
              ><span>系统消息</span><span class="badge">5</span></router-link
            >
          </div>
          <div class="msg_aside_item">
            <router-link to="/personal/message/msg" active-class="link_active" class="linkStyle"
              ><span>个人私信</span><span class="badge">5</span></router-link
            >
          </div>
          <div class="msg_aside_item">
            <router-link to="/personal/message/todo" active-class="link_active" class="linkStyle"
              ><span>代办事项</span><span class="badge">3</span></router-link
            >
          </div>
        </div>
      </el-col>
      <el-col :span="22"
        ><RouterView v-slot="{ Component }">
          <transition :name="store.getConfig.animation || 'fade'" mode="out-in">
            <component :is="Component" />
          </transition> </RouterView
      ></el-col>
    </el-row>
  </el-card>
</template>

<style lang="scss" scoped>
.msg_card {
  min-height: 500px;
  height: auto !important;
}
.msg_aside {
  min-height: 500px;
  height: auto !important;
  border-right: 1px solid #ccc;
}
.linkStyle {
  display: block;
  height: 20px;
  line-height: 20px;
  text-decoration: none;
  border-bottom: 1px solid #dedede;
  text-align: center;
  color: #333;
  // border: 1px solid red;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  padding-right: 20px;
  font-size: 14px;
  border-right: 4px solid #ffffff;
}

.link_active {
  border-right: 4px solid #1677ff;
  background-color: #e8f1ff;
}
.badge {
  display: flex;
  min-width: 16px;
  height: 16px;
  background-color: red;
  border-radius: 40%;
  font-size: 12px;
  color: #ffffff;
  justify-content: center;
  align-items: center;
  padding-left: 2px;
  padding-right: 2px;
}
</style>

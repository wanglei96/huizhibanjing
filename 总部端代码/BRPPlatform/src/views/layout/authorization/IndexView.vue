<script setup>
import config from "@/js/config";

const dialogVisible = ref(false)
function handleAuth() {
  dialogVisible.value = true
}
function handleClose() {}
</script>
<template>
  <div>
    <el-row>
      <el-col :xs="24" :sm="24" :md="12" :lg="16">
        <el-card shadow="never">
          <template #header> 产品优势 </template>
          <div class="sub_left_t1">技术优势</div>
          <div class="sub_lft_c1">
            使用 Vue3、ElementPlus、Pinia、vite 最新主流技术栈
            系统有完整的登录鉴权、token过期自动刷新token、动态路由、按钮级别的鉴权控制、使用模块化开发、界面简洁美观，可灵活设置主题颜色、响应式布局，自适应全屏幕尺寸、源码注释详细，帮助您快速掌握、同时可加入授权用户专属技术服务群
          </div>
          <div class="sub_left_t1">采用最新技术栈</div>
          <div class="sub_lft_c1">
            使用 Vue3、ElementPlus、Pinia、vite 最新主流技术栈
            使用当前主流的前端技术栈：Vite、Vue、CompositionApi、Typescript、VueRouter、Pinia、Axios、Scss
            等， 以及 CompositionApi、script setup 的开发方式。
          </div>
          <div class="sub_left_t1">支持多种终端使用</div>
          <div class="sub_lft_c1">本系统采用响应式设计、支持主流的浏览器、并支持APP内置浏览器</div>
          <div class="sub_left_t1">上手容易</div>
          <div class="sub_lft_c1">
            系统已经完成了大部分后台管理功能，大量演示案例，系统采用组件式开发
          </div>
          <div class="sub_left_t1">让您低成本投入开发</div>
          <div class="sub_lft_c1">
            系统界面美观，且完成后台管理的大部分功能。能免去大部分的设计工作、省去项目前期搭建时间！
          </div>
          <div class="sub_left_t1">源代码安全可控</div>
          <div class="sub_lft_c1">
            授权用户可获取全部源代码，所有源代码无后门，您可放心使用、系统所有源代码都有详细的注释，便于阅读。可以快速上手进行功能开发
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card shadow="never">
          <template #header> 获取授权 </template>

          <div class="r1_container">
            <div class="right_1_pic">
              <img src="@/assets/images/eleadmincn.png" style="width: 100%" />
            </div>
            <div class="right_1_txt1">{{ config.systemName }}</div>
            <div class="right_1_txt2">{{}}</div>
            <div class="price">
              <span>授权价格:</span>
              <span style="margin-left: 5px">¥</span>
              <span style="color: #ff0000; font-size: 28px; margin-right: 10px">300</span>
              <span style="text-decoration: line-through">¥999</span>
            </div>
            <div class="fuwu">一次永久授权，终身免费更新</div>
            <div class="sub_btn">
              <a href="javascript:;" style="color: #ffffff" @click="handleAuth">申请授权</a>
              <!-- <el-button type="primary">申请授权</el-button> -->
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog :close-on-click-modal="false" v-model="dialogVisible" title="获取授权" width="30%" :before-close="handleClose">
      <span>请联系客服QQ：35763281 获取授权！</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="dialogVisible = false"> 确认 </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.sub_left_t1 {
  font-size: 16px;
  line-height: 45px;
}
.sub_lft_c1 {
  font-size: 14px;
  line-height: 28px;
  color: #999;
}

.right_1_title {
  height: 50px;
  line-height: 50px;
  border-bottom: 1px solid #f6f6f6;
}
.right_1_title span {
  padding-left: 20px;
}
.right_1_pic {
  margin: 0 auto;
  margin-top: 20px;
  border: 1px solid #f6f6f6;
}
.right_1_txt1 {
  height: 50px;
  margin: 0 auto;
  line-height: 50px;
  font-size: 22px;
  margin-top: 10px;
}
.right_1_txt2 {
  color: #666666;
  font-size: 14px;
  line-height: 40px;
  padding-bottom: 10px;
  border-bottom: 1px dashed #999;
}
.price {
  width: 100%;
  height: 80px;
  line-height: 80px;
  font-size: 16px;
}
.fuwu {
  width: 100%;
  height: 40px;
  line-height: 40px;
  text-align: center;
  background-color: #f4f7f7;
  color: #ff6700;
}

.sub_btn {
  width: 100%;
  height: 42px;
  line-height: 42px;
  margin-top: 20px;
  text-align: center;
  /* background-color: #ff9639; */
  background-color: #4c6aff;
  border-radius: 4px;
  font-size: 16px;
  color: #ffffff;
}
.sub_btn:hover {
  background-color: #ff9639;
}
</style>

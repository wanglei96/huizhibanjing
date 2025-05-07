<script setup>
import Vcopy from '@/components/extension/VCopy.vue'
const txt = '127.0.0.1:8080'
const txt2 = 'www.eleadmin.cn'
</script>
<template>
  <div>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>单行截取文本并加省略号</span>
        </div>
      </template>
      <div class="card-body">
        <div class="singleline">
          <span style="width: 110px">单行文本省略：</span>
          <span v-ellipsis="{ width: 500, line: 1 }" style="color: #409eff">
            贯彻落实关于防汛救灾工作的重要指示精神听取当前防汛抗洪救灾情况汇报，研究下一步做好防汛抢险救灾、群众生活保障和灾后恢复重建工作举措，贯彻落实关于防汛救灾工作的重要指示精神听取当前防汛抗洪救灾情况汇报。
          </span>
        </div>
      </div>
    </el-card>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>多行截取文本并加省略号</span>
        </div>
      </template>
      <div class="singleline">
        <span style="width: 110px">多行文本省略：</span>
        <span v-ellipsis="{ width: 500, line: 2 }" style="color: #409eff">
          李强主持召开国务院常务会议,贯彻落实习近平总书记关于防汛救灾工作的重要指示精神等,贯彻落实习近平总书记关于防汛救灾工作的重要指示精神听取当前防汛抗洪救灾情况汇报，研究下一步做好防汛抢险救灾、群众生活保障和灾后恢复重建工作举措
        </span>
      </div>
    </el-card>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>复制文本</span>
        </div>
      </template>
      <div class="card-body">
        <div class="infolist">
          <span>服务器IP：</span>
          <span style="color: #eb7c54">{{ txt }}</span>
          <span><Vcopy :data="txt" /></span>
        </div>
        <div class="infolist"><span>服务器用户名：</span><span>administrator</span></div>
        <div class="infolist">
          <span>服务器初始管理员密码：</span>
          <span style="color: #0b84d8">{{ txt2 }}</span>
          <span>
            <Vcopy :data="txt2" />
          </span>
        </div>
      </div>
    </el-card>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>Tooltip 文字提示</span>
        </div>
      </template>
      <div class="card-body">
        <el-tooltip content="Top center" placement="top">
          <el-button>Dark</el-button>
        </el-tooltip>
        <el-tooltip content="Bottom center" placement="bottom" effect="light">
          <el-button>Light</el-button>
        </el-tooltip>

        <el-tooltip content="Bottom center" effect="customized">
          <el-button>Customized theme</el-button>
        </el-tooltip>
      </div>
    </el-card>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>Link 链接</span>
        </div>
      </template>
      <div class="card-body">
        <el-link href="https://element-plus.org" target="_blank">default</el-link>
        <el-link type="primary">primary</el-link>
        <el-link type="success">success</el-link>
        <el-link type="warning">warning</el-link>
        <el-link type="danger">danger</el-link>
        <el-link type="info">info</el-link>
      </div>
    </el-card>

    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>Text 文本</span>
        </div>
      </template>
      <div class="card-body">
        <el-space direction="vertical">
          <el-text>span</el-text>
          <el-text tag="p">This is a paragraph.</el-text>
          <el-text tag="b">Bold</el-text>
          <el-text tag="i">Italic</el-text>
          <el-text>
            This is
            <el-text tag="sub" size="small">subscript</el-text>
          </el-text>
          <el-text>
            This is
            <el-text tag="sup" size="small">superscript</el-text>
          </el-text>
          <el-text tag="ins">Inserted</el-text>
          <el-text tag="del">Deleted</el-text>
          <el-text tag="mark">Marked</el-text>
        </el-space>
      </div>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.el-card {
  margin-bottom: 20px;
}
.singleline {
  width: 600px;
  font-size: 14px;
  color: #666;
  display: flex;
}

.el-popper.is-customized {
  /* Set padding to ensure the height is 32px */
  padding: 6px 12px;
  background: linear-gradient(90deg, rgb(159, 229, 151), rgb(204, 229, 129));
}

.el-popper.is-customized .el-popper__arrow::before {
  background: linear-gradient(45deg, #b2e68d, #bce689);
  right: 0;
}

.el-link {
  margin-right: 8px;
}
.el-link .el-icon--right.el-icon {
  vertical-align: text-bottom;
}

.infolist {
  display: flex;
  height: 28px;
  line-height: 28px;
  justify-content: flex-start;
  align-items: center;
  font-size: 14px;
  span {
    display: inline-block;
    margin-right: 6px;
  }
}
</style>

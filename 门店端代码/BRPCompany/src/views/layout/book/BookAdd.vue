<script setup>
import { ref } from 'vue';
import BookAddExcel from './BookAddExcel.vue'
import BookAddISBN from './BookAddISBN.vue'

// 声明批量添加新书标签页
const BookAddExcelRef = ref()
const BookAddISBNRef = ref()

</script>
<template>
  <div>
    <BookAddExcel ref="BookAddExcelRef" />
    <BookAddISBN ref="BookAddISBNRef" />
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>扫描ISBN录入新书</span>
        </div>
      </template>
      <div class="card-body">
        <div class="infolist">1、通过扫书后ISBN条码的方式录入新书</div>
        <div class="infolist">
          <span>2、如果书后没有条码，可以打开书的内页或者购买链接找到ISBN，</span>
          <span style="color: red;">手动输入ISBN查询</span>
        </div>
        <div class="infolist">
          <span>3、套系书一般扫其中一本，同系列的都会查询出来，可以批量选择录入</span>
        </div>
        <div style="margin-top: 10px;">
          <el-button type="success" @click="BookAddISBNRef.init()">扫描ISBN批量添加新书</el-button>
        </div>
      </div>
    </el-card>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>表格方式录入新书</span>
        </div>
      </template>
      <div class="card-body">
        <div class="infolist">
          <span style="color: red;">1、当通过ISBN或者书名查询不到</span>
          <span>想要的结果时，可以通过该方式录入新书</span>
        </div>
        <div class="infolist">
          <span>2、通过这里录入的新书，需要</span>
          <span style="color: red;">填写图书相关信息和拍摄封面图</span>
        </div>
        <div style="margin-top: 10px;">
          <el-button type="warning" @click="BookAddExcelRef.init()">批量录入新书</el-button>
        </div>
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

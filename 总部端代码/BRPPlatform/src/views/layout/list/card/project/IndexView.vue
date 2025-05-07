<script setup>
import { dayjs } from 'element-plus'

const currentDate = ref(new Date())
const dt = dayjs(currentDate.value).format('YYYY-MM-DD HH:mm:ss')

const listData = [
  {
    id: '001',
    title: 'Yummy hamburger',
    imgUrl: 'https://www.eleadmin.cn/assets/images/list/001.jpg'
  },
  {
    id: '002',
    title: 'Yummy hamburger',
    imgUrl: 'https://www.eleadmin.cn/assets/images/list/002.jpg'
  },
  {
    id: '003',
    title: 'Yummy hamburger',
    imgUrl: 'https://www.eleadmin.cn/assets/images/list/003.jpg'
  },
  {
    id: '004',
    title: 'Yummy hamburger',
    imgUrl: 'https://www.eleadmin.cn/assets/images/list/004.jpg'
  },
  {
    id: '005',
    title: 'Yummy hamburger',
    imgUrl: 'https://www.eleadmin.cn/assets/images/list/005.jpg'
  },
  {
    id: '006',
    title: 'Yummy hamburger',
    imgUrl: 'https://www.eleadmin.cn/assets/images/list/006.jpg'
  },
  {
    id: '007',
    title: 'Yummy hamburger',
    imgUrl: 'https://www.eleadmin.cn/assets/images/list/007.jpg'
  },
  {
    id: '008',
    title: 'Yummy hamburger',
    imgUrl: 'https://www.eleadmin.cn/assets/images/list/008.jpg'
  }
]
</script>
<template>
  <div>
    <el-card shadow="never" class="search-card">
      <el-form>
        <el-row type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="项目名称">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="项目编号">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="项目类别">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary">搜索</el-button>
              <el-button>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-row style="margin-bottom: 20px">
      <el-col :xs="24" :sm="12" :md="8" :lg="6" v-for="item in listData" :key="item.id">
        <el-card :body-style="{ padding: '0px' }" shadow="hover">
          <img :src="item.imgUrl" class="image" />
          <div style="padding: 14px">
            <span>{{ item.title }}</span>
            <div class="bottom">
              <time class="time">{{ dt }}</time>
              <el-button text class="button">查看详情...</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.search-card {
  margin-bottom: 15px;
  .el-form-item {
    margin-bottom: 0px;
  }
}

.time {
  font-size: 12px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
}

.el-col {
  margin-bottom: 20px;
}
</style>

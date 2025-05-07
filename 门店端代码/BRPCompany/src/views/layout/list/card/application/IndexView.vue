<script setup>
const appList = [
  {
    id: '001',
    title: '欢乐斗地主(腾讯)',
    subtitle: '棋牌 | 腾讯',
    imgUrl: 'https://pp.myapp.com/ma_icon/0/icon_97949_1688717948/256'
  },
  {
    id: '002',
    title: '和平精英',
    subtitle: '第一人称射击',
    imgUrl: 'https://pp.myapp.com/ma_icon/0/icon_52575843_1687916139/256'
  },
  {
    id: '003',
    title: '英雄杀',
    subtitle: '卡牌 | 策略 | 棋牌',
    imgUrl: 'https://pp.myapp.com/ma_icon/0/icon_1105393_1690794043/256'
  },
  {
    id: '004',
    title: '王者荣耀',
    subtitle: 'MOBA | 策略',
    imgUrl: 'https://pp.myapp.com/ma_icon/0/icon_12127266_1687836445/256'
  },
  {
    id: '005',
    title: '妄想山海',
    subtitle: 'MMORPG | 角色扮演',
    imgUrl: 'https://pp.myapp.com/ma_icon/0/icon_12160554_1689033948/256'
  },
  {
    id: '006',
    title: '凡人修仙传',
    subtitle: '养成 | 角色扮演',
    imgUrl: 'https://pp.myapp.com/ma_icon/0/icon_54216361_1690164195/256'
  },
  {
    id: '007',
    title: '金铲铲之战',
    subtitle: '自走棋 | 策略 | 卡通',
    imgUrl: 'https://pp.myapp.com/ma_icon/0/icon_54152245_1686846897/256'
  },
  {
    id: '008',
    title: '三国杀',
    subtitle: '卡牌 | 策略 | 三国 | 中国风',
    imgUrl: 'https://pp.myapp.com/ma_icon/0/icon_12147939_1689130821/256'
  },
  {
    id: '009',
    title: '穿越火线-枪战王者',
    subtitle: '第一人称射击 | 第三人称',
    imgUrl: 'https://pp.myapp.com/ma_icon/0/icon_12165022_1688605678/256'
  },
  {
    id: '010',
    title: '新剑侠情缘',
    subtitle: '角色扮演 | 武侠',
    imgUrl: 'https://pp.myapp.com/ma_icon/0/icon_42241779_1690863404/256'
  },
  {
    id: '011',
    title: '英雄联盟手游',
    subtitle: 'MOBA | 奇幻',
    imgUrl: 'https://pp.myapp.com/ma_icon/0/icon_53934324_1689725549/256'
  },
  {
    id: '012',
    title: '失控猎人 - 新角色',
    subtitle: '格斗 | 动作冒险',
    imgUrl: 'https://pp.myapp.com/ma_icon/0/icon_1012592_1689819647/256'
  }
]
</script>
<template>
  <div>
    <el-card shadow="never" class="search-card">
      <el-form>
        <el-row  type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="应用名称">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="应用编号">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="应用类别">
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
    <el-row  style="margin-bottom: 20px">
      <el-col :xs="24" :sm="12" :md="8" :lg="4" v-for="item in appList" :key="item.id">
        <el-card shadow="hover" class="app-list">
          <el-avatar :size="60" shape="square" :src="item.imgUrl" />
          <div class="app-title">{{ item.title }}</div>
          <div class="app-subtitle">{{ item.subtitle }}</div>
          <div><el-button type="primary" round size="small">下载</el-button></div>
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

.app-list {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  margin-bottom: 20px;
}

.app-title {
  font-size: 16px;
  line-height: 25px;
  margin-top: 10px;
}

.app-subtitle {
  color: #999;
  font-size: 12px;
  margin-top: 5px;
  margin-bottom: 20px;
}
</style>

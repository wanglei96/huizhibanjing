<template>
  <div>
    <el-card shadow="never" class="search-card">
      <el-form>
        <el-row type="flex">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="文章标题">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="文章作者">
              <el-input placeholder="请输入..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="文章类别">
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
    <el-card shadow="never">
      <div class="article-list">
        <!-- /////////// listitem /////////////// -->
        <div class="article-list-item">
          <div class="list-item-info">
            <div class="list-item-title">绿水青山带笑颜丨一棵松繁衍出一片“海”</div>
            <div class="list-item-tags">
              <el-tag class="ml-2" type="info">绿水青山</el-tag>
              <el-tag class="ml-2" type="info">笑颜</el-tag>
            </div>
            <div class="list-item-desc">
              从“一棵松”到百万亩人工绿海，塞罕坝每棵树的年轮里都蕴含着中国生态文明的发展进程。
            </div>
            <div class="list-item-author">
              <el-avatar
                :size="'small'"
                :src="'https://pp.myapp.com/ma_icon/0/icon_12127266_1687836445/256'"
              />
              爱游泳的鱼，发表于3小时前
            </div>
            <div class="list-item-tools">
              <span
                ><el-icon><Star /></el-icon> 289</span
              >
              <el-divider direction="vertical" />
              <span
                ><el-icon><Discount /></el-icon>256</span
              >
              <el-divider direction="vertical" />
              <span
                ><el-icon><ChatLineRound /></el-icon>36</span
              >
            </div>
          </div>
          <div class="list-item-img">
            <img
              src="https://www.gov.cn/shouye/hygq/202307/W020230714590562544517_ORIGIN.jpg"
              alt=""
            />
          </div>
        </div>
        <!-- ///////////// list item  ///////// -->
        <!-- /////////// listitem /////////////// -->
        <div class="article-list-item">
          <div class="list-item-info">
            <div class="list-item-title">
              花椒专家下乡送良方 河北派出专家指导唐县林农果农恢复生产
            </div>
            <div class="list-item-tags">
              <el-tag class="ml-2" type="info">花椒</el-tag>
              <el-tag class="ml-2" type="info">专家</el-tag>
              <el-tag class="ml-2" type="info">河北</el-tag>
            </div>
            <div class="list-item-desc">
              唐县石南坡村辉磊花椒种植园里，原本被水浸泡过的种植区域已经看不到明显的积水。但走进地里，土壤依然湿软，部分地方仍不能通行。
            </div>
            <div class="list-item-author">
              <el-avatar
                :size="'small'"
                :src="'https://pp.myapp.com/ma_icon/0/icon_12127266_1687836445/256'"
              />
              新闻网，发表于6小时前
            </div>
            <div class="list-item-tools">
              <span
                ><el-icon><Star /></el-icon> 289</span
              >
              <el-divider direction="vertical" />
              <span
                ><el-icon><Discount /></el-icon>256</span
              >
              <el-divider direction="vertical" />
              <span
                ><el-icon><ChatLineRound /></el-icon>36</span
              >
            </div>
          </div>
          <div class="list-item-img">
            <img src="https://www.gov.cn/home/tt/202307/W020230731721586398247.jpg" alt="" />
          </div>
        </div>
        <!-- ///////////// list item  ///////// -->
        <!-- /////////// listitem /////////////// -->
        <div class="article-list-item">
          <div class="list-item-info">
            <div class="list-item-title">撸起袖子加油干 风雨无阻向前行｜发展特色产业</div>
            <div class="list-item-tags">
              <el-tag class="ml-2" type="info">风雨无阻</el-tag>
              <el-tag class="ml-2" type="info">特色产业</el-tag>
              <el-tag class="ml-2" type="info">加油干</el-tag>
            </div>
            <div class="list-item-desc">
              发展县域经济是推动乡村振兴的关键，也是石家庄市经济高质量发展的重要标志。
            </div>
            <div class="list-item-author">
              <el-avatar
                :size="'small'"
                :src="'https://pp.myapp.com/ma_icon/0/icon_12127266_1687836445/256'"
              />
              新闻网，发表于8小时前
            </div>
            <div class="list-item-tools">
              <span
                ><el-icon><Star /></el-icon> 289</span
              >
              <el-divider direction="vertical" />
              <span
                ><el-icon><Discount /></el-icon>256</span
              >
              <el-divider direction="vertical" />
              <span
                ><el-icon><ChatLineRound /></el-icon>36</span
              >
            </div>
          </div>
          <div class="list-item-img">
            <img
              src="https://www.gov.cn/yaowen/liebiao/202307/W020230730801770537518_ORIGIN.JPG"
              alt=""
            />
          </div>
        </div>
        <!-- ///////////// list item  ///////// -->
      </div>
    </el-card>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped>
.search-card {
  margin-bottom: 15px;
  .el-form-item {
    margin-bottom: 0px;
  }
}
.article-list {
  .article-list-item {
    display: flex;
    justify-content: space-between;
    padding: 25px 24px;
    border-bottom: 1px solid #ccc;
    .list-item-info {
      flex: 1;
      .list-item-title {
        font-size: 18px;
      }
      .list-item-tags {
        margin-top: 15px;
        margin-bottom: 15px;
        .el-tag {
          margin-right: 10px;
        }
      }
      .list-item-desc {
        font-size: 14px;
        color: #999;
      }
      .list-item-author {
        display: flex;
        align-items: center;
        margin-top: 15px;
        font-size: 14px;
        color: #666666;
        .el-avatar {
          margin-right: 10px;
        }
      }
      .list-item-tools {
        margin-top: 20px;
        font-size: 14px;
        color: #999999;
        display: flex;
        align-items: center;
        .el-icon {
          margin-right: 3px;
        }
      }
    }
    .list-item-img {
      width: 280px;
      height: 175px;
      img {
        width: 100%;
        height: 100%;
        border-radius: 8px;
      }
    }
  }
}
</style>

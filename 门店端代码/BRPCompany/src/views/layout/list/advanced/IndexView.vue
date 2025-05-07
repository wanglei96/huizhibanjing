<script setup>
import { Search } from '@element-plus/icons-vue'
import circleUrl from '@/assets/images/avator/avator01.png'
const radio2 = ref('全部')

// 分页相关
const currentPage4 = ref(4)
const pageSize4 = ref(100)
const small = ref(false)
const background = ref(false)
const disabled = ref(false)

const handleSizeChange = (val) => {
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val) => {
  console.log(`current page: ${val}`)
}
</script>
<template>
  <div>
    <el-card shadow="never" style="margin-bottom: 15px">
      <el-row>
        <el-col :span="8" align="center">
          <div class="task">
            <div class="task_desc">当前进行中的项目</div>
            <div class="task_title">15<span style="font-size: 12px"> 个</span></div>
          </div>
        </el-col>
        <el-col :span="8" align="center"
          ><div class="task">
            <div class="task_desc">已经完成的项目</div>
            <div class="task_title">5<span style="font-size: 12px"> 个</span></div>
          </div></el-col
        >
        <el-col :span="8" align="center"
          ><div class="task">
            <div class="task_desc">项目总耗时</div>
            <div class="task_title">258 <span style="font-size: 12px"> 人天</span></div>
          </div></el-col
        >
      </el-row>
    </el-card>
    <el-card shadow="never">
      <template #header>
        <div class="card-header">
          <span>
            <el-button type="primary"
              ><el-icon><Plus /></el-icon> 新增</el-button
            >
          </span>
          <div class="card-right">
            <el-form :inline="true">
              <el-form-item>
                <el-radio-group v-model="radio2">
                  <el-radio-button label="全部" />
                  <el-radio-button label="进行中" />
                  <el-radio-button label="已完成" />
                </el-radio-group>
              </el-form-item>
              <el-form-item>
                <el-input placeholder="请输入..." style="width: 200px">
                  <template #append>
                    <el-button :icon="Search" />
                  </template>
                </el-input>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </template>
      <div class="list-container">
        <div class="list-item">
          <el-avatar shape="square" :size="50" :src="circleUrl" />
          <div class="list-item-title">
            <div class="list-item-title_1">ElementAdmin开发项目</div>
            <div class="list-item-title_2">基于Vue3+ElementPlus的通用后台管理系统</div>
          </div>

          <div class="list-item-title" style="width: 150px">
            <div class="list-item-title_1">发布人</div>
            <div class="list-item-title_2">管理员</div>
          </div>

          <div class="list-item-title" style="width: 150px">
            <div class="list-item-title_1">创建时间</div>
            <div class="list-item-title_2">2023-08-01 09:40:00</div>
          </div>

          <div class="list-item-progress">
            <el-progress :percentage="80" />
          </div>
          <div class="list-item-caozuo">
            <el-link>编辑</el-link>
            <el-divider direction="vertical" />
            <el-link>删除</el-link>
          </div>
        </div>

        <div class="list-item">
          <el-avatar shape="square" :size="50" :src="circleUrl" />
          <div class="list-item-title">
            <div class="list-item-title_1">烟草专卖数据采集系统</div>
            <div class="list-item-title_2">
              烟草专卖数据采集系统包含硬件及软件两个部份。硬件包含了手持设备及打印输出设备
            </div>
          </div>

          <div class="list-item-title" style="width: 150px">
            <div class="list-item-title_1">发布人</div>
            <div class="list-item-title_2">管理员</div>
          </div>

          <div class="list-item-title" style="width: 150px">
            <div class="list-item-title_1">创建时间</div>
            <div class="list-item-title_2">2023-08-01 09:40:00</div>
          </div>

          <div class="list-item-progress">
            <el-progress :percentage="60" />
          </div>
          <div class="list-item-caozuo">
            <el-link>编辑</el-link>
            <el-divider direction="vertical" />
            <el-link>删除</el-link>
          </div>
        </div>
        <div class="list-item">
          <el-avatar shape="square" :size="50" :src="circleUrl" />
          <div class="list-item-title">
            <div class="list-item-title_1">用户行为数据采集系统</div>
            <div class="list-item-title_2">
              覆盖PC、APP、微信等终端进行数据收集，提供从采集、建模、存储、分析、智能应用的全流程数据驱动解决方案
            </div>
          </div>

          <div class="list-item-title" style="width: 150px">
            <div class="list-item-title_1">发布人</div>
            <div class="list-item-title_2">管理员</div>
          </div>

          <div class="list-item-title" style="width: 150px">
            <div class="list-item-title_1">创建时间</div>
            <div class="list-item-title_2">2023-08-01 09:40:00</div>
          </div>

          <div class="list-item-progress">
            <el-progress :percentage="40" />
          </div>
          <div class="list-item-caozuo">
            <el-link>编辑</el-link>
            <el-divider direction="vertical" />
            <el-link>删除</el-link>
          </div>
        </div>
        <div class="list-item">
          <el-avatar shape="square" :size="50" :src="circleUrl" />
          <div class="list-item-title">
            <div class="list-item-title_1">大数据标签计算引擎</div>
            <div class="list-item-title_2">
              一个高效开放的数据计算管理平台，对海量数据提供强大的多数据源数据整合和分析能力，提供实时用户画像和人群分析服务。
            </div>
          </div>

          <div class="list-item-title" style="width: 150px">
            <div class="list-item-title_1">发布人</div>
            <div class="list-item-title_2">管理员</div>
          </div>

          <div class="list-item-title" style="width: 150px">
            <div class="list-item-title_1">创建时间</div>
            <div class="list-item-title_2">2023-08-01 09:40:00</div>
          </div>

          <div class="list-item-progress">
            <el-progress :percentage="20" />
          </div>
          <div class="list-item-caozuo">
            <el-link>编辑</el-link>
            <el-divider direction="vertical" />
            <el-link>删除</el-link>
          </div>
        </div>
        <div class="list-item">
          <el-avatar shape="square" :size="50" :src="circleUrl" />
          <div class="list-item-title">
            <div class="list-item-title_1">多渠道信息智能路由系统</div>
            <div class="list-item-title_2">
              采用了大数据、流数据、物联网技术，取得了高可用、高并发、高吞吐量的技术成果。
            </div>
          </div>

          <div class="list-item-title" style="width: 150px">
            <div class="list-item-title_1">发布人</div>
            <div class="list-item-title_2">管理员</div>
          </div>

          <div class="list-item-title" style="width: 150px">
            <div class="list-item-title_1">创建时间</div>
            <div class="list-item-title_2">2023-08-01 09:40:00</div>
          </div>

          <div class="list-item-progress">
            <el-progress :percentage="10" />
          </div>
          <div class="list-item-caozuo">
            <el-link>编辑</el-link>
            <el-divider direction="vertical" />
            <el-link>删除</el-link>
          </div>
        </div>
      </div>
      <el-pagination
        v-model:current-page="currentPage4"
        v-model:page-size="pageSize4"
        :page-sizes="[100, 200, 300, 400]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="400"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        style="margin-top: 20px"
      />
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.el-form-item {
  margin-bottom: 0px;
}

.task {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .task_desc {
    color: #999;
    font-size: 14px;
    margin-bottom: 5px;
  }
  .task_title {
    font-size: 22px;
  }
}

.list-container {
  width: 100%;
  font-size: 14px;

  .list-item {
    display: flex;
    flex-direction: row;
    align-items: center;
    border-bottom: 1px solid #ccc;
    padding: 20px 0px 20px 0px;
  }

  .list-item-title {
    width: 50%;
    height: 48px;
    margin-left: 10px;
    margin-right: 10px;
    .list-item-title_1 {
      font-size: 16px;
      margin-bottom: 5px;
    }
    .list-item-title_2 {
      font-size: 12px;
      color: #999;
    }
  }
  .list-item-progress {
    width: 250px;
    margin: 0 10px;
  }
  .list-item-caozuo {
    width: 100px;
    margin-left: auto;
  }
}
</style>

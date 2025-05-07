<script setup>
import { ref } from 'vue';
import CmsContentDetail from './CmsContentDetail.vue';
import management from '@/js/management';
import common from '@/js/common';

//管理员搜索表单组件引用
const refSearchForm = ref();
//管理员详情页组件引用
const refCmsContentDetail = ref();

const page = management({
  tableName: 'cms_content',
  orderBy: 'create_time desc',
  hasTree: true,
  treeOrderBy: "display_order",
  treeTableName: "cms_channel",
  treeCondition: "cms_site_id=" + common.getCmsSiteId(),
  getListServiceName: "GetViewCmsContentList",
  isCms: true,
  treeRootNodeText: "栏目",
  treeParentFieldName: "cmsChannelId",
  refDetail: refCmsContentDetail
})

const isShow = ref(true)

function handleIsShow() {
  isShow.value = !isShow.value
}

const defaultProps = {
  children: 'children',
  label: 'name'
}

</script>
<template>
  <div>
    <CmsContentDetail ref="refCmsContentDetail" @reload-data="page.loadData" />
    <el-card shadow="never">
      <div class="dept-container">
        <div class="dept-aside">
          <div class="dept-aside-menu" :style="{ width: isShow ? '265px' : '0px' }">
            <div class="tree-title" :style="{ width: isShow ? '265px' : '0px' }">
              栏目
            </div>
            <el-scrollbar>
              <el-tree :data="page.treeData.value" :highlight-current="true" default-expand-all
                :expand-on-click-node="false" :props="page.defaultProps.value" node-key="id"
                @node-click="page.handleTreeNodeClick" />
            </el-scrollbar>
          </div>
          <div class="dept-aside-spliter">
            <span class="arrow-box" @click="handleIsShow">
              <el-icon v-show="isShow">
                <ArrowLeft />
              </el-icon>
              <el-icon v-show="!isShow">
                <ArrowRight />
              </el-icon>
            </span>
          </div>
        </div>
        <div class="dept-content">
          <el-card shadow="never" class="search-card">
            <el-form ref="refSearchForm" :model="page.parameters">
              <el-row :gutter="20" type="flex">
                <el-col :xs="24" :sm="12" :md="8" :lg="6">
                  <el-form-item label="标题" prop="title">
                    <el-input placeholder="请输入..." clearable v-model="page.parameters.title"
                      @keydown.enter.prevent=page.query></el-input>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="6">
                  <el-form-item label="作者" prop="author">
                    <el-input placeholder="请输入..." clearable v-model="page.parameters.author"
                      @keydown.enter.prevent=page.query></el-input>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="6">
                  <el-form-item label="来源" prop="source">
                    <el-input placeholder="请输入..." clearable v-model="page.parameters.source"
                      @keydown.enter.prevent=page.query></el-input>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="6">
                  <el-form-item label="时间范围" prop="dpSearchPublishDate">
                    <el-date-picker v-model="page.parameters.dpSearchPublishDate" type="daterange"
                      start-placeholder="开始日期" end-placeholder="结束日期" value-format="YYYY-MM-DD"
                      :validate-event="true" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="6">
                  <el-form-item>
                    <el-button type="primary" @click='page.query'>搜索</el-button>
                    <el-button @click='page.resetForm(refSearchForm)'>重置</el-button>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-card>
          <el-card shadow="never" class="grid-card">
            <el-alert :title="`已选择 ${page.selectedColumnCount.value} 项`" v-show="page.selectedColumnCount.value > 0"
              type="warning" show-icon />
            <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
              <el-row>
                <el-col :span="18">
                  <el-button type="primary" @click="page.openDetailDialog({ mode: 'add' })">
                    <el-icon style="margin-right: 3px">
                      <Plus />
                    </el-icon>
                    新建
                  </el-button>
                  <el-button type="danger" @click="page.deleteRows">
                    <el-icon tyle="margin-right: 3px">
                      <Delete />
                    </el-icon>
                    删除
                  </el-button>
                </el-col>
                <el-col :span="6">
                </el-col>
              </el-row>
            </el-form>
            <el-table ref="multipleTableRef" :data="page.dataList.value" border :table-layout="'auto'"
              style="width: 100%">
              <el-table-column property="title" label="标题" width="180" />
              <el-table-column label="发布时间">
                <template #default="scope">
                  {{ scope.row.publish_date_text }}
                </template>
              </el-table-column>
              <el-table-column property="cms_channel_name" label="栏目" />
              <el-table-column label="操作" align="center" width="240">
                <template #default="scope">
                  <el-space spacer="|" style="color: #999" wrap>
                    <el-link type="warning" :id="scope.row.id"
                      @click="page.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看
                    </el-link>
                    <el-link type="primary" :id="scope.row.id"
                      @click="page.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">编辑
                    </el-link>
                    <el-link type="danger" @click="page.deleteRow(scope.row.id)">删除</el-link>
                  </el-space>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination v-model:current-page="page.currentPage.value" v-model:page-size="page.pageSizeComputed.value"
              :page-sizes="page.pageSizes" :page-count="page.pageCount.value" :layout="page.layout"
              :total="page.recordCount.value" style="margin-top: 20px" />
          </el-card>
        </div>
      </div>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.el-row {
  row-gap: 10px;
}

.el-card {
  //height: 87vh;
  --el-card-padding: 0px;
  //padding: 0px 16px;
}

.search-card {
  margin-top: 15px;
  margin-bottom: 15px;
  padding: 10px;
  width: calc(100% - 20px);
  box-sizing: border-box;

  .el-form-item {
    margin-bottom: 0px;
  }
}

.grid-card {
  padding: 10px;
  width: calc(100% - 20px);
  box-sizing: border-box;
}

.dept-container {
  width: 100%;
  display: flex;

  .dept-aside {
    display: flex;
    justify-content: space-between;

    .dept-aside-menu {
      padding: 0 10px;
    }
  }

  .el-form-item {
    margin-bottom: 0px;
  }
}

.dept-container {
  width: 100%;
  display: flex;

  .dept-aside {
    display: flex;
    justify-content: space-between;

    .dept-aside-menu {
      padding: 0 10px;
      flex: 1;
      border-right: 1px solid #ccc;
      height: 100vh;
      // 主要靠下面两行实现效果
      overflow: hidden; // 很重要!!!!
      transition: 0.3s ease;

      .tree-title {
        line-height: 35px;
        text-align: center;
        border-bottom: 1px solid #ccc;
        overflow: hidden;
        transition: 0.3s ease;
        color: #606266;
      }
    }

    .dept-aside-spliter {
      width: 15px;
      height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;

      .arrow-box {
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #ffffff;
        width: 20px;
        height: 20px;
        border: 1px solid #ccc;
        border-radius: 50%;
        margin-left: -15px;
        // z-index: 99999;
      }
    }
  }

  .dept-content {
    flex: 1;
    //height: 100%;
  }
}

.el-tree {
  --el-tree-node-content-height: 36px;
}

.table-footer {
  display: flex;
  text-align: center;
  margin-top: 20px;
}
</style>

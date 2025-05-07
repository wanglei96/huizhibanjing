<script setup>
import { ref } from 'vue'
import CompanyUserDetail from './CompanyUserDetail.vue'
import SaveCompanyUserRoles from './SaveCompanyUserRoles.vue'
import management from '@/js/management'
import common from '@/js/common'

//管理员搜索表单组件引用
const refSearchForm = ref()
//管理员详情页组件引用
const refCompanyDetail = ref()
//设置角色组件引用
const refSaveCompanyUserRoles = ref()

const list = management({
  tableName: 'company_user',
  orderBy: 'create_time desc',
  getListServiceName: 'GetViewCompanyUserList',
  hasTree: true,
  treeOrderBy: 'display_order',
  treeTableName: 'company_organization',
  refDetail: refCompanyDetail,
  treeCondition: "is_deleted is not true",
  condition: "is_deleted is not true"
})
const updateEnabled = (id, enabled) => {
  common.call('SetCompanyUserEnabled', { id, enabled })
}

const isShow = ref(true)

function handleIsShow() {
  isShow.value = !isShow.value
}

//当前选中组织机构节点
const currentNodeId = ref()

//修改当前页触发计算属性重新加载数据
const handleNodeClick = (val) => {
  currentNodeId.value = val.id
  list.parameters.companyOrganizationId = val.id
  list.currentPage.value = 1
}

const deleteRow = (id) => {
  ElMessageBox.confirm('确定要删除这条数据吗？', '温馨提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(() => {
      let params = {
        id: id,
        tableName: 'company_user'
      }
      common.call('HideIdTable', params, (result) => {
        list.loadData()
      })
    })
    .catch((error) => {
      throw new Error(error.message)
    })
}
</script>
<template>
  <div>
    <CompanyUserDetail ref="refCompanyDetail" @reload-data="list.loadData" />
    <SaveCompanyUserRoles ref="refSaveCompanyUserRoles" />
    <el-card shadow="never">
      <div class="dept-container">
        <div class="dept-aside">
          <div class="dept-aside-menu" :style="{ width: isShow ? '265px' : '0px' }">
            <div class="tree-title" :style="{ width: isShow ? '265px' : '0px' }">组织机构</div>
            <el-scrollbar>
              <el-tree :data="list.treeData.value" :highlight-current="true" default-expand-all
                :props="list.defaultProps.value" node-key="id" @node-click="handleNodeClick" />
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
            <el-form ref="refSearchForm" :model="list.parameters">
              <el-row :gutter="20" type="flex">
                <el-col :xs="24" :sm="12" :md="8" :lg="6">
                  <el-form-item label="编号" prop="employeeNo">
                    <el-input placeholder="请输入..." clearable v-model="list.parameters.employeeNo"
                      @keydown.enter.prevent=list.query></el-input>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="6">
                  <el-form-item label="登录手机号" prop="mobilePhone">
                    <el-input placeholder="请输入..." clearable v-model="list.parameters.mobilePhone"
                      @keydown.enter.prevent=list.query></el-input>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="6">
                  <el-form-item label="员工姓名" prop="name">
                    <el-input placeholder="请输入..." clearable v-model="list.parameters.name"
                      @keydown.enter.prevent=list.query></el-input>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="6">
                  <el-form-item>
                    <el-button type="primary" @click="list.query">搜索</el-button>
                    <el-button @click="list.resetForm(refSearchForm)">重置</el-button>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-card>
          <el-card shadow="never" class="grid-card">
            <el-alert :title="`已选择 ${list.selectedColumnCount.value} 项`" v-show="list.selectedColumnCount.value > 0"
              type="warning" show-icon />
            <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
              <el-row>
                <el-col :span="18">
                  <el-button type="primary" @click="list.openDetailDialog({ mode: 'add' })">
                    <el-icon style="margin-right: 3px">
                      <Plus />
                    </el-icon>
                    新建
                  </el-button>
                  <el-button type="danger" @click="list.deleteRows">
                    <el-icon tyle="margin-right: 3px">
                      <Delete />
                    </el-icon>
                    删除
                  </el-button>
                </el-col>
                <el-col :span="6"> </el-col>
              </el-row>
            </el-form>
            <el-table ref="multipleTableRef" :data="list.dataList.value" :table-layout="'auto'" style="width: 100%">
              <el-table-column property="employeeNo" label="员工编号" width="180" />
              <el-table-column property="mobilePhone" label="登录手机号" />
              <el-table-column property="name" label="员工姓名" />
              <el-table-column property="companyOrganizationName" label="部门" />
              <el-table-column property="enabled" label="是否启用">
                <template #default="scope">
                  <el-switch v-model="scope.row.enabled" :disabled="scope.row.is_admin === 1"
                    @change="updateEnabled(scope.row.id, scope.row.enabled)" />
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center" width="240">
                <template #default="scope">
                  <el-space spacer="|" style="color: #999" wrap>
                    <el-link type="warning" :id="scope.row.id" @click="
      list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })
      ">查看
                    </el-link>
                    <el-link type="primary" :id="scope.row.id" @click="
      list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })
      ">编辑
                    </el-link>
                    <el-link type="danger" v-if="scope.row.is_admin !== 1" @click="deleteRow(scope.row.id)">删除</el-link>
                    <el-link type="success" @click="
      refSaveCompanyUserRoles.getCompanyRoleListByCompanyUserId(scope.row.id)
      ">
                      设置角色
                    </el-link>
                  </el-space>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination v-model:current-page="list.currentPage.value" v-model:page-size="list.pageSizeComputed.value"
              :page-sizes="list.pageSizes" :page-count="list.pageCount.value" :layout="list.layout"
              :total="list.recordCount.value" style="margin-top: 20px" />
          </el-card>
        </div>
      </div>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
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
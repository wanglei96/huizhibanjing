<script setup>
import { ref } from 'vue';
import CompanyDetail from './CompanyDetail.vue';
import CompanyReport from './CompanyReport.vue';
import management from '@/js/management';
import common from '@/js/common';
import { ElMessage, ElMessageBox } from "element-plus";
/* import { v4 as uuidv4 } from 'uuid';

const guid = uuidv4();
console.log(guid); */

//详情页组件引用
const refDetail = ref();
const refCompanyReport = ref();
const refSearchForm = ref();

const list = management({
  tableName: 'company',
  orderBy: 'create_time desc,id desc',
  getListServiceName: 'GetViewCompanyList',
  refDetail,
  processDataItem(dataItem) {
    if (dataItem.licenseImageFilePath) {
      dataItem.licenseImageFilePath = common.getAdminUrl(dataItem, 'licenseImageFilePath');
    } else {
      delete dataItem.licenseImageFilePath;
    }
    return dataItem;
  }
})

function setDefaultCompany(companyId) {
  common.call('SetDefaultCompany', { companyId }, res => {
    list.loadData();
  });
}

function completionCompanySetting() {
  common.call("CompletionCompanySetting", {}, res => {
    if (res.success) {
      ElMessage({ message: '操作成功', type: 'success' })
    } else {
      ElMessage({ message: res.errorMessage, type: 'success' })
    }
  });
}

/**
 * 删除
 */
function deleteRow(primaryKeyValue) {
  // 询问
  ElMessageBox.confirm("确定要删除这条数据吗？", "温馨提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning"
  })
    .then(() => {
      const data = {};
      data.id = primaryKeyValue;
      common.call('DeleteCompany', data, (result) => {
        if (!result.success) {
          ElMessage({ type: 'warning', message: '该门店中存在重要数据，不能删除' });
        } else {
          // 刷新数据
          list.loadData();
        }
      }, res => {
        if (!res.success) {
          ElMessage({ type: 'warning', message: '该门店中存在重要数据，不能删除' });
        }
      });
    })
    .catch((error) => {
      throw new Error(error.message);
    });
}
function openCompany(row) {
  var companyId = row.id;
  let params = {
    companyId: companyId
  }
  common.call('SetGUID', params, (res) => {
    let guid = res.data;
    //let url = 'http://company.brp.rome8.com/login?companyId=' + companyId;
    let url = 'http://company.brp.rome8.com/login?companyId=' + companyId + "&guid=" + guid;
    window.open(url);
  })
}
function unlockNumber(row) {
  let params = {
    companyId: row.id
  }
  common.call('UnlockCompanyNumber', params, (res) => {
    ElMessage.success("解锁成功");
  })
}
</script>
<template>
  <div class="basic-list">
    <CompanyDetail ref="refDetail" @reload-data="list.loadData" />
    <CompanyReport ref="refCompanyReport"></CompanyReport>
    <el-card shadow="never" class="search-card">
      <el-form ref="refSearchForm" :model="list.parameters" label-width="120">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="门店名称" prop="companyName">
              <el-input placeholder="请输入..." v-model="list.parameters.companyName"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="联系电话" prop="mobilePhone">
              <el-input placeholder="请输入..." v-model="list.parameters.mobilePhone"
                @keydown.enter.prevent=list.query></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click='list.query'>搜索</el-button>
              <el-button @click='list.resetForm(refSearchForm)'>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card shadow="never">
      <el-form :inline="true" style="margin-top: 20px; margin-bottom: 20px">
        <el-row>
          <el-col :span="18">
            <el-button type="primary" @click="list.openDetailDialog({ mode: 'add' })">
              <el-icon>
                <Plus />
              </el-icon>
              新建
            </el-button>
          </el-col>
          <el-col :span="6">
          </el-col>
        </el-row>
      </el-form>
      <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%" border
        @selection-change="list.selectRow" @sort-change="list.sortChange">
        <el-table-column property="companyName" label="门店名称" />
        <el-table-column property="companyNo" label="门店编号" />
        <el-table-column property="contact" label="联系人" />
        <el-table-column property="mobilePhone" label="联系电话" />
        <el-table-column property="address" label="门店地址" />
        <el-table-column property="memberCount" sortable="custom" label="会员数量" />
        <el-table-column property="bookSkuCount" sortable="custom" label="SKU数量" />
        <el-table-column property="bookCount" sortable="custom" label="图书数量" />
        <el-table-column property="boxStocksCount" sortable="custom" label="库存书箱数量">
          <!-- <template #default="scope">
            {{ scope.row.bookboxInventoryQuantity ? scope.row.bookboxInventoryQuantity : 0 }}
          </template> -->
        </el-table-column>
        <el-table-column label="操作" align="center" width="320">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="primary" :id="scope.row.id"
                @click="refCompanyReport.init({ companyId: scope.row.id })">图表分析
              </el-link>
              <el-link type="warning" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'view', primaryKeyValue: scope.row.id })">查看
              </el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="list.openDetailDialog({ mode: 'edit', primaryKeyValue: scope.row.id })">编辑
              </el-link>
              <el-link type="danger" @click="deleteRow(scope.row.id)">删除</el-link>
              <el-link type="success" @click="setDefaultCompany(scope.row.id)" v-if="!scope.row.isDefault">设为默认
              </el-link>
              <el-link type="success" @click="openCompany(scope.row)">进入门店
              </el-link>
              <el-link type="success" @click="unlockNumber(scope.row)">解锁账号
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
</template>

<style lang="scss" scoped>
.el-row {
  row-gap: 10px;
}

.search-card {
  margin-bottom: 15px;

  .el-form-item {
    margin-bottom: 0px;
  }
}

.image-slot {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 100px;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 30px;
}
</style>

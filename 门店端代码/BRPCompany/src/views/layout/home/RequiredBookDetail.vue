<script setup>
import { reactive, ref } from 'vue'
import detail from '@/js/detail'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import ImageUpload from '@/components/extension/ImageUpload.vue'
import management from '@/js/management';

//定义事件发射器
const emit = defineEmits(['reload-data'])
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref()
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
const list = ref([]);
let page = reactive({})
const init = (options) => {
  detailFormVisible.value = true
  page = detail({
    baseTitle: '必读书单',
    tableName: 'required_book',
    getServiceName: 'GetViewRequiredBook',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  })
  if (options.mode === 'add') {
    page.dataEntity.value.enabled = true

  }
  if (options.mode !== 'view') {
    list.value = management({
      tableName: 'book_sku',
      //orderBy: 'create_time desc,book_series_id,book_name',
      getListServiceName: 'GetBookSkuInfoList',
      pageSize: 5,
      parameters: {
        companyId: companyId.value
      },
      processDataItem(dataItem) {
        if (dataItem.imageFilePath) {
          dataItem.imageFilePath = common.getAdminUrl(dataItem, 'imageFilePath');
        }
        currentRadio.value = page.dataEntity.value.bookSkuId
        return dataItem;
      }
    })

  }

}

//暴露
defineExpose({
  init
})

function floatValidator(rule, value, callback) {
  if (!/^-?\d+(\.\d+)?$/.test(value)) {
    // 校验不通过
    return callback(new Error('请输入正数'))
  } else {
    // 校验通过
    callback()
  }
}

//表单校验规则
const rules = reactive({
  bookName: [
    { required: true, message: '请输入书名', trigger: 'blur' }
  ],
  requiredBookCategoryId: [{ required: true, message: '请选择必读书单分类', trigger: 'change' }]
})

function getTableByCompanyId() {
  return `company_id=${localStorage.getItem('companyId')}`
}
const refSearchForm = ref();
const currentRow = ref();
const currentRadio = ref();
const handleCurrentChange = (row) => {
  currentRow.value = row;
  page.dataEntity.value.bookName = row?.bookName;
  page.dataEntity.value.bookSkuId = row?.id;
  currentRadio.value = row?.id;
  console.log(currentRadio.value)
};

//提交表单
/* function confirm() {
  if (!currentRadio.value) {
    ElMessage({ message: '请选择图书', type: 'warning' });
    return;
  }
  let options = {
    getDataEntity
  }
  refDataEntityForm.value.validate().then(() => {
    page.isLoading.value = true;
    page.save(options);
  }).catch((res) => {
    console.log(res);
    ElMessage({ message: '表单格式有误', type: 'warning' });
  })

}
function getDataEntity(data) {
  data.value.bookSkuId = currentRadio.value
  return data;
} */

</script>
<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''" width="1000" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode === 'view'" label-width="80px">
        <el-row>
          <!-- <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="名称" prop="bookSkuId">
              <database-select table-name="book_sku" v-model="page.dataEntity.value.bookSkuId" placeholder="请选择图书"
                textFieldName="book_name" valueFieldName="id" :condition="getTableByCompanyId()"></database-select>
            </el-form-item>
          </el-col> -->
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="书名" prop="bookName">
              <el-input v-model="page.dataEntity.value.bookName" placeholder="请输入书名" disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="必读书单分类" prop="requiredBookCategoryId" label-width="auto">
              <TreeSelect table-name="required_book_category" v-model="page.dataEntity.value.requiredBookCategoryId"
                :condition="getTableByCompanyId()" placeholder="请选择必读书单分类" text-field-name="name" value-field-name="id">
              </TreeSelect>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-card shadow="never" v-if="page.mode === 'add' || page.mode === 'edit'">
        <template #header>
          <div>
            <span>图书列表</span>
          </div>
        </template>
        <el-form ref="refSearchForm" :model="list.parameters" label-width="80px">
          <el-row type="flex" :gutter="20">
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item label="ISBN" prop="ISBN">
                <el-input placeholder="请输入..." v-model="list.parameters.ISBN"
                  @keydown.enter.prevent=list.query></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="8" :lg="6">
              <el-form-item label="书名" prop="bookName">
                <el-input placeholder="请输入..." v-model="list.parameters.bookName"
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
        <el-table ref="multipleTableRef" :data="list.dataList" style="width: 100%" :default-selection="selectedRowKeys"
          @current-change="handleCurrentChange" :row-key="row => row.id">
          <el-table-column width="55">
            <template #default="scope">
              <el-radio v-model="currentRadio" :label="scope.row.id">
                <span></span>
              </el-radio>
            </template>
          </el-table-column>
          <el-table-column property="avator" label="封面" width="100" align="center">
            <template #default="scope">
              <el-image style="width: 100px; height: 100px" :src="scope.row.imageFilePath" fit="contain">
                <template #error>
                  <div class="image-slot">
                    <el-icon>
                      <Picture />
                    </el-icon>
                  </div>
                </template>
              </el-image>
            </template>
          </el-table-column>
          <el-table-column property="bookName" label="书名" width="300">
            <template #default="scope">
              <div>
                <span style="color: #008000;">{{ scope.row.bookSeriesName ? "[" + scope.row.bookSeriesName + "]" : ""
                  }}</span>
                <span>{{ scope.row.bookName }}</span>
                <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
                  }}</span>
              </div>
              <div class="list-item-title_3">ISBN：{{ scope.row.ISBN }}
              </div>
            </template>
          </el-table-column>
          <el-table-column property="languageName" label="分类" />
          <el-table-column property="" label="年龄段&标签">
            <template #default="scope">
              <div>
                <span>{{ scope.row.ageGroupNames }}</span>
              </div>
              <div>
                <span>{{ scope.row.bookTagNames }}</span>
              </div>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination v-model:current-page="list.currentPage" v-model:page-size="list.pageSizeComputed"
          :page-sizes="list.pageSizes" :page-count="list.pageCount" :layout="list.layout" :total="list.recordCount"
          style="margin-top: 20px" />
      </el-card>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" v-if="page.mode === 'add' || page.mode === 'edit'" :loading="page.isLoading.value"
            @click="page.submitForm()">
            确定
          </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.form-item-width {
  width: 100% !important;
}

.user-search-card {
  margin-bottom: 15px;

  .user-form {
    .el-form-item {
      margin-bottom: 10px;
    }
  }
}

.el-dialog {
  width: 500px;
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

.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 300px;
}

.el-input {
  width: 300px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
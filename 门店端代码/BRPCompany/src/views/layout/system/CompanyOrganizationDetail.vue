<script setup>
import {reactive, ref} from 'vue';
import detail from '@/js/detail';
import common from "@/js/common";
import TreeSelect from "@/components/extension/TreeSelect.vue";

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

//组织机构树
const companyOrganizationTree = ref([]);
//获取组织机构树
const getCompanyOrganizationTree = (primaryKeyValue) => {
  let data = {
    primaryKeyValue,
    companyId: common.getLocalStorage("companyId"),
    condition: "is_deleted is not true"
  }
  common.call('GetCompanyOrganizationTree', data, (res) => {
    companyOrganizationTree.value = res.data;
    let topParent = {label: '空', value: 0}
    companyOrganizationTree.value.unshift(topParent);
  })
}

let page = reactive({});
const init = (options) => {
  //获取组织机构树
  getCompanyOrganizationTree(options.primaryKeyValue);
  page = detail({
    baseTitle: '组织机构',
    tableName: 'company_organization',
    getServiceName: 'GetCompanyOrganization',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  });
  detailFormVisible.value = true
}

//暴露
defineExpose({
  init
})

//表单校验规则
const rules = reactive({
  name: [
    {required: true, message: '请输入名称', trigger: 'blur'}
  ],
  parentId: [
    {required: true, message: '请选择组织机构', trigger: 'blur'}
  ]
})

</script>
<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" :title="page.title?page.title.value:''" width="800" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
               :disabled="page.mode === 'view'" label-width="80px">
        <el-row >
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="page.dataEntity.value.name" placeholder="请输入名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="上级" prop="parentId">
              <el-tree-select
                  v-model="page.dataEntity.value.parentId"
                  :data="companyOrganizationTree"
                  check-strictly
                  placeholder="请选择上级"
                  :render-after-expand="false"
                  class="form-item-width"
                  filterable
                  clearable
              />
            </el-form-item>
          </el-col>
          <!--          <el-col :xs="12" :sm="12" :md="12" :lg="12" v-if="detailFormVisible">
                      <el-form-item label="上级" prop="parentId">
                        <TreeSelect v-model="page.dataEntity.value.parentId" table-name="company_organization" placeholder="请选择上级"/>
                      </el-form-item>
                    </el-col>-->
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="显示顺序" prop="displayOrder">
              <el-input v-model="page.dataEntity.value.displayOrder" placeholder="请输入显示顺序"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row >
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="描述" prop="description">
              <el-input v-model="page.dataEntity.value.description" type="textarea" rows="10"
                        placeholder="请输入描述"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" v-if="page.mode === 'add' || page.mode === 'edit'" :loading="page.isLoading.value"
                     @click="page.submitForm()"> 确定 </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.form-item-width {
  width: 200px !important;
}

.el-dialog {
  width: 500px;
}

.el-button--text {
  margin-right: 15px;
}

.el-select {
  width: 300px;
}

.el-input {
  width: 200px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>

<script setup>
import { reactive, ref } from 'vue';
import detail from '@/js/detail';
import { useRoute } from 'vue-router';

var route = useRoute();

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();

const tableName = route.query['tableName'];
const title = route.query['title'];
let page = reactive({});
const init = (option) => {
  page = detail({
    baseTitle: title,
    tableName: tableName,
    isBasicTable: true,
    dataName: "CodeNameTable",
    primaryKeyName: "code",
    mode: option.mode,
    primaryKeyValue: option.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  });
  detailFormVisible.value = true;
  if (page.mode === "add") {
    nextTick(function () {
      page.dataEntity.value = { enabled: true, description: "" };
    })
  }
}
//暴露
defineExpose({
  init
})

//表单校验规则
const rules = reactive({
  name: [
    { required: true, message: '请输入名称', trigger: 'blur' }
  ],
  code: [
    { required: true, message: '请输入编码', trigger: 'blur' }
  ]
})

</script>
<template>
  <div>
    <el-dialog :close-on-click-modal="false" v-model="detailFormVisible" :title="page.title?page.title.value:''" width="800" draggable>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode === 'view'" label-width="80px">
        <el-row>
          <el-col :xs="24" :sm="12" :md="12" :lg="12">
            <el-form-item label="编码" prop="code">
              <el-input v-model="page.dataEntity.value.code" :disabled="page.mode !== 'add'" placeholder="请输入编码"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="12" :lg="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="page.dataEntity.value.name" placeholder="请输入名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="12" :lg="12">
            <el-form-item label="是否启用" prop="enabled">
              <el-switch v-model="page.dataEntity.value.enabled" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="描述" prop="description">
              <el-input v-model="page.dataEntity.value.description" type="textarea" placeholder="请输入描述"
                rows="10"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
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
.form-item-width{
  width: 200px;
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

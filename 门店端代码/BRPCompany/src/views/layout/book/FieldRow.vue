<template>
  <div class="field-row">
    <el-select v-model="item.bookSkuFieldCode" placeholder="请选择字段" @change="handleSelectChange">
      <el-option v-for="field in bookSkuFieldList" :key="field.code" :label="field.name" :value="field.code">
      </el-option>
    </el-select>
    <el-input v-model="item.oldValue" disabled></el-input>
    <el-input v-model="item.newValue" placeholder="输入值"></el-input>
    <el-button type="danger" @click="removeField">删除</el-button>
  </div>
</template>

<script>
import { defineComponent, ref } from 'vue';

export default defineComponent({
  props: ['bookSkuFieldList', 'index', 'item'],
  setup(props, { emit }) {

    const removeField = () => {
      emit('remove');
    };

    const handleSelectChange = (value) => {
      emit('fieldChange', value); // 触发 fieldChange 事件
    };

    return {
      removeField,
      handleSelectChange
    };
  }
});
</script>

<style scoped>
.field-row {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.field-row>*:not(:last-child) {
  margin-right: 10px;
  /* 设置标签之间的右边距 */
}
</style>
<script setup>
import { ref } from 'vue'
import common from '@/js/common'

const props = defineProps({
  modelValue: {
    type: String,
    default: ''
  },
  currentValue: {
    type: String
  },
  //表名
  tableName: {
    type: String,
    required: true
  },
  //查询的字段
  fields: {
    type: String
  },
  //查询条件
  condition: {
    type: String
  },
  //起始页下标
  pageIndex: {
    type: Number,
    default: 0
  },
  //页面大小
  pageSize: {
    type: Number,
    default: 99999
  },
  //排序
  order: {
    type: String,
    default: 'display_order'
  },
  //显示的字段名
  textFieldName: {
    type: String,
    default: 'name'
  },
  //为值的字段名
  valueFieldName: {
    type: String,
    default: 'id'
  },
  //下拉框内占位符
  placeholder: {
    type: String,
    default: '请选择...'
  },
  //是否禁用
  disabled: {
    type: Boolean,
    default: false
  },
  //是否可清空
  clearable: {
    type: Boolean,
    default: true
  },
  //是否可筛选
  filterable: {
    type: Boolean,
    default: true
  },
  //默认值
  defaultValue: {
    type: String
  },
  defaultExpandAll: {
    type: Boolean,
    default: true
  }
})
const emit = defineEmits(['update:modelValue'])
const selectValue = computed({
  get() {
    return props.modelValue ? String(props.modelValue) : null
  },
  set(newValue) {
    emit('update:modelValue', newValue ? String(newValue) : null)
  }
})
//原始数据
const dataList = ref([])
//树形数据
const dataTree = ref([])
//默认展开的节点的 key 的数组
/*const defaultExpandedKeys = ref([]);
defaultExpandedKeys.value=[props.modelValue];*/

const getDataTree = () => {
  let data = {
    tableName: props.tableName,
    pageIndex: props.pageIndex,
    pageSize: props.pageSize,
    orderBy: 'display_order'
  }
  if (props.fields) {
    data.fields = props.fields
  }
  if (props.condition) {
    data.condition = props.condition
  }
  if (props.order) {
    data.order = props.order
  }
  common.call('GetTable', data, (res) => {
    dataTree.value = common.getTreeData({
      dataList: res.data,
      valueFieldName: props.valueFieldName,
      parentValueFieldName: 'parent_code'
    })

    if (props.defaultValue !== undefined) {
      selectValue.value = props.defaultValue
    }
    /*let topParent = {name: '空', value: 0}
    dataTree.value.unshift(topParent);*/
    dataTree.value = common.getTreeData({
      dataList: dataTree.value
    })
    let option = {
      treeData: dataTree.value,
      valueFieldName: props.valueFieldName,
      currentValue: String(props.modelValue)
    } /*
    dataTree.value = common.setCurrentBranchDisabled(option);
    emit('update:modelValue', String(props.modelValue))*/
  })
}
getDataTree()
/*{
  "children": [
  {
    "children": null,
    "description": null,
    "disabled": true,
    "id": 8,
    "label": "行政部",
    "parentId": 6,
    "value": 8
  },
  {
    "children": null,
    "description": null,
    "disabled": true,
    "id": 14,
    "label": "市场一组",
    "parentId": 6,
    "value": 14
  }
],
    "description": null,
    "disabled": true,
    "id": 6,
    "label": "市场部",
    "parentId": 0,
    "value": 6
}*/
</script>

<template>
  <el-tree-select
      v-model="selectValue"
      :data="dataTree"
      :node-key="props.valueFieldName"
      check-strictly
      placeholder="请选择上级"
      :default-expanded-keys="[props.modelValue]"
      :props="{ label: props.textFieldName }"
      :default-expand-all="props.defaultExpandAll"
      class="form-item-width"
      filterable
      clearable
  >
  </el-tree-select>
</template>

<style scoped lang="scss"></style>
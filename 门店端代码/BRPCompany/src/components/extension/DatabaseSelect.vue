<script setup>
import { ref } from 'vue'
import common from '@/js/common'

const props = defineProps({
  modelValue: {
    default: ''
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
    default: ''
  },
  //显示的字段名
  textFieldName: {
    type: String,
    default: 'name'
  },
  //为值的字段名
  valueFieldName: {
    type: String,
    default: 'code'
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
  //开启多选
  multiple: {
    type: Boolean,
    default: false
  }
})
const emit = defineEmits(['update:modelValue'])
const selectValue = computed({
  get() {
    return props.modelValue + ''
  },
  set(newValue) {
    emit('update:modelValue', newValue + '')
  }
})
const dataList = ref([])

function getData() {
  let data = {
    tableName: props.tableName,
    pageIndex: props.pageIndex,
    pageSize: props.pageSize
  }
  if (props.fields) {
    data.fields = props.fields
  }
  if (props.condition) {
    data.condition = props.condition
  }
  if (props.order) {
    data.orderBy = props.order
  }
  common.call('GetTable', data, (res) => {
    res.data.map((item) => {
      item[props.valueFieldName] = item[props.valueFieldName] + ''
    })
    dataList.value = res.data
    if (props.defaultValue !== undefined) {
      selectValue.value = props.defaultValue
    }
  })
}

getData()

function onChange(value) {
  if (!props.multiple) {
    for (let item of dataList.value) {
      if (item[props.valueFieldName] === value) {
        emit('onChange', item)
        return
      }
    }
  } else {
    let itemObjArr = []
    for (let item of dataList.value) {
      for (let i = 0; i < value.length; i++) {
        if (item[props.valueFieldName] === value[i]) {
          itemObjArr.push(item)
        }
      }
    }
    emit('onChange', itemObjArr)
  }
}

function onClear() {
  emit('onClear')
}

watch(
  () => props.condition,
  () => getData()
)
</script>

<template>
  <el-select
    :multiple="props.multiple"
    v-model="selectValue"
    class="m-2"
    :placeholder="props.placeholder"
    :disabled="props.disabled"
    :clearable="props.clearable"
    :filterable="props.filterable"
    @change="onChange"
    @clear="onClear"
  >
    <slot name="header"></slot>
    <el-option
      v-for="item in dataList"
      :key="item[props.valueFieldName]"
      :label="item[props.textFieldName]"
      :value="item[props.valueFieldName]"
    >
      <slot :item="item"></slot>
    </el-option>
  </el-select>
</template>

<style scoped lang="scss"></style>
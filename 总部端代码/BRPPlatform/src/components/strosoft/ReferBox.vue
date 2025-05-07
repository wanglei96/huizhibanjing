<script setup>
import  {ref} from 'vue'
import  common from "@/js/common"

const props = defineProps({
  modelValue :{
    default:{}
  },
  //参照名称
  selectionName:{
    type:String,
    required:true
  },
  //下拉框内占位符
  placeholder:{
    type:String,
    default:'请选择...'
  },
  //是否禁用
  disabled:{
    type:Boolean,
    default:false
  },
  //默认值
  defaultValue:{
    type:String
  }
})
const emit = defineEmits(['update:modelValue']);
const selectValue = computed({
  get() {
    return props.modelValue
  },
  set(newValue) {
    emit('update:modelValue', newValue)
  }
});
const  dataList = ref([]);
function getData(){
  let data = {
    tableName:props.tableName,
    pageIndex:props.pageIndex,
    pageSize:props.pageSize
  }
  if(props.fields){
    data.fields=props.fields;
  }
  if(props.condition){
    data.condition=props.condition;
  }
  if(props.order){
    data.order=props.order;
  }
  common.call('GetTable',data,(res)=>{
    dataList.value = res.data;
    if (props.defaultValue !== undefined){
      selectValue.value = props.defaultValue;
    }
  })
}
getData();

</script>

<template>
    <el-input v-model="selectedText" class="refer-box" :placeholder="props.placeholder" :disabled="props.disabled" />
</template>

<style scoped lang="scss">
.refer-box{

}
</style>
<script setup>
import { reactive, ref, watch } from 'vue';
import common from '@/js/common';
import BookSKUDetail from './BookSKUDetail.vue'
import DatabaseSelect from "@/components/extension/DatabaseSelect.vue";
import management from '@/js/management';

const refDetail = ref();
const refSearchForm = ref();
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)

//获取信息
const dataItem = ref({});
//sku列表
const skuDataList = ref([]);
//确定按钮是否加载中
const isLoading = ref(false);
//书名
const bookInfoQuery = ref('');
//标题
const title = ref('');
const isHasBookSku = ref(1);
const bookTagId = ref();
//类型
const type = ref('');
const list = ref([]);
function init(data) {
  // 显示弹窗
  detailFormVisible.value = true
  if (data.type == 'delete') {
    title.value = '批量删除【' + data.item.name + '】图书';
    isHasBookSku.value = 1;
    type.value = 'delete';
  }
  if (data.type == 'add') {
    title.value = '批量添加【' + data.item.name + '】图书';
    isHasBookSku.value = 0;
    type.value = 'add';
  }
  if (data.type == 'view') {
    title.value = '查看【' + data.item.name + '】图书';
    isHasBookSku.value = 1;
    type.value = 'view';
  }
  bookTagId.value = data.item.id
  dataItem.value = data.item;
  list.value = management({
    tableName: 'book_sku',
    orderBy: 'create_time desc,book_series_id,book_name',
    getListServiceName: 'GetViewBookSkuList',
    condition: data.type == 'add' ? `id NOT IN (SELECT book_sku_id FROM book_sku_book_tag WHERE book_tag_id = ${data.item.id})` : `id IN (SELECT book_sku_id FROM book_sku_book_tag WHERE book_tag_id = ${data.item.id})`,
    pageSize: 8,
    processDataItem(dataItem) {
      if (dataItem.imageFilePath) {
        dataItem.imageFilePath = common.getAdminUrl(dataItem, 'imageFilePath');
      }
      return dataItem;
    }
  })
}
/**
 * 获取SKU列表
 */
function getBookSkuList(bookInfoQuery) {
  let param = {
    isHasBookSku: isHasBookSku.value,
    companyId: companyId.value,
    bookTagId: bookTagId.value
  }
  if (bookInfoQuery) {
    param.bookInfoQuery = bookInfoQuery;
  }
  common.call("GetBookSkuListByBookTagId", param, (res) => {
    let dataList = res.data;
    dataList.forEach(element => {
      element.imageFilePath = common.getAdminUrl(element, 'imageFilePath');
      element.selected = false;
    });
    list.value.dataList = dataList;
  })
}

/**
 * 删除
 */

function deleteSku() {
  executeOperation('DeleteBookSkuBookTagByBookSkuIds');
}
/**
 * 添加
 */
function addSku() {
  executeOperation('AddBookSkuBookTagByBookSkuIds');
}
/**
 * 执行操作
 */
function executeOperation(operationName) {
  isLoading.value = true;
  //detailFormVisible.value = false;
  setTimeout(() => {
    isLoading.value = false;
  }, 310);
  //获取选中的skuId列表
  let bookSkuIds = list.value.dataList.filter(item => item.selected).map(item => item.id);
  let data = {
    bookTagId: dataItem.value.id,
    bookSkuIds: bookSkuIds
  };
  common.call(operationName, data, function (res) {
    ElMessage({ message: "操作成功", type: 'success' })
    list.value.loadData()
  })
}
//单选变化事件
function handleClick(value, item) {
  item.selected = value
  list.value.dataList = list.value.dataList.slice();
}
//全选 
function selectAll() {
  const shouldSelectAll = !list.value.dataList.every(item => item.selected)
  list.value.dataList.forEach(item => {
    item.selected = shouldSelectAll
  })
  list.value.dataList = list.value.dataList.slice();
}

const hasBookSkuCount = ref(0);

watch(() => list.value.dataList, (newValue, oldValue) => {
  if (newValue !== oldValue) {
    hasBookSkuCount.value = newValue.filter(item => item.selected).length;
  }
});


//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    emit('reload-data');
    bookInfoQuery.value = '';
    list.value.dataList = [];
    hasBookSkuCount.value = 0;
  }
})

//暴露
defineExpose({
  init
})
</script>
<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" :title="title" width="1200"
      draggable>
      <BookSKUDetail ref="refDetail" />
      <el-scrollbar height="600px">
        <span class="dialog-footer">
          <el-button @click="selectAll">全选</el-button>
          <el-button type="danger" @click="deleteSku" style="margin-left: 10px;" v-if="type == 'delete'">删除</el-button>
          <el-button type="primary" @click="addSku" style="margin-left: 10px;" v-if="type == 'add'">添加</el-button>
          <span style="margin-left: 10px;">已选择：{{ hasBookSkuCount }}个</span>
        </span>
        <el-card shadow="never" class="sku-search-card">
          <template #header>
            <div>
              <span>查询栏</span>
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
                <el-form-item label="系列" prop="bookSeriesName">
                  <el-input placeholder="请输入..." v-model="list.parameters.bookSeriesName"
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
          <div class="card-body">
            <el-row :gutter="20" style="margin-bottom: 20px">
              <el-col :xs="24" :sm="12" :md="8" :lg="6" v-for="item in list.dataList" :key="item.id">
                <el-card :body-style="{ padding: '0px', height: '300px' }" shadow="hover">
                  <div class="image-wrapper">
                    <el-checkbox v-model="item.selected" class="checkbox" @change="value => handleClick(value, item)"
                      :checked="item.selected"></el-checkbox>
                    <el-image :src="item.imageFilePath" class="image" fit="contain">
                      <template #error>
                        <div class="image-slot">
                          <el-icon>
                            <Picture />
                          </el-icon>
                        </div>
                      </template>
                    </el-image>
                  </div>
                  <div style="padding: 14px">

                    <div>
                      <span style="color: #008000;">{{ item.bookSeriesName ? "[" + item.bookSeriesName + "]" :""
                        }}</span>
                      <span style="font-weight:550;">{{ item.bookName }}</span>
                      <span style="color: #0000ff;">{{ item.bindingTypeName ? "(" + item.bindingTypeName + ")": ""
                        }}</span>
                    </div>

                    <!-- <div>
                      <span style="font-weight:550;">{{ item.bookName }}</span>
                      <span style="color: #2e00ff;">{{ item.bindingTypeName ? "(" + item.bindingTypeName + ")" : ""
                        }}</span>
                    </div> -->
                    <div class="infolist">
                      <span>价格：</span>
                      <span>{{ item.price }}</span>
                    </div>
                    <div class="infolist">
                      <span>ISBN：</span>
                      <span>{{ item.ISBN }}</span>
                    </div>
                    <div class="bottom">
                      <el-button text class="button"
                        @click="refDetail.init({ mode: 'view', primaryKeyValue: item.id })">查看详情...</el-button>
                    </div>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </div>
          <el-pagination v-model:current-page="list.currentPage" v-model:page-size="list.pageSizeComputed"
            :page-sizes="list.pageSizes" :page-count="list.pageCount" :layout="list.layout" :total="list.recordCount"
            style="margin-top: 20px" />
        </el-card>
      </el-scrollbar>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.el-card {
  margin-bottom: 20px;
}

.el-select {
  width: 200px;
}

.bottom {
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

/* .image {
  width: 100%;
  display: block;
} */
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

.el-col {
  margin-bottom: 20px;
}

.infolist {
  display: flex;
  height: 28px;
  line-height: 28px;
  justify-content: flex-start;
  align-items: center;
  font-size: 14px;

  span {
    display: inline-block;
    margin-right: 6px;
  }

}

.image-wrapper {
  position: relative;
  width: 100%;
  height: 150px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.image-wrapper .image {
  width: 100px;
}

.image-wrapper .checkbox {
  position: absolute;
  top: 0;
  left: 10px;
  z-index: 1;
}
</style>
<style>
.sku-search-card {
  margin: 15px 0;
  box-shadow: 0 0 0 1px #dff0d8;

  .el-card__header {
    background-color: #dff0d8;
  }
}
</style>

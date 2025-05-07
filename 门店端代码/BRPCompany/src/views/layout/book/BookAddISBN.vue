<script setup>
import { reactive, ref, watch } from 'vue';
import common from '@/js/common';
import BookSKUDetail from './BookSKUDetail.vue'

const isLoading = ref(false);

//当前页
const pageIndex = ref(0)
//总记录数
const recordCount = ref(0)
//总页数
const pageCount = ref(0)
//分页大小列表
const pageSizes = reactive([10, 20, 30, 50])
//每页大小
const pageSize = ref(8)

const refDetail = ref();


//当前页
const pageIndexSeries = ref(0)
//总记录数
const recordCountSeries = ref(0)
//总页数
const pageCountSeries = ref(0)
//分页大小列表
const pageSizesSeries = reactive([10, 20, 30, 50])
//每页大小
const pageSizeSeries = ref(8)


//分页布局
const layout = 'total, sizes, prev, pager, next, jumper'

// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
//详情页开关
const detailFormVisible = ref(false)
//sku列表
const skuDataList = ref([])
//同系列sku列表
const bookSeriesDataList = ref([])
// 声明ISBN
const ISBN = ref('')
const newISBN = ref('')
/* const inputRef = ref(null);

onMounted(() => {
  // 将焦点设置在输入框上
  if (inputRef.value) {
    inputRef.value.focus();
  }
}); */
function queryDataList() {
  if (ISBN.value) {
    newISBN.value = ISBN.value
    pageIndex.value = 0
  }

  let param = {
    condition: "(company_id = " + companyId.value + " OR company_id IS NULL ) AND ISBN='" + newISBN.value + "'",
    fields: "id,book_name,image_file_id,author,press,price,ISBN,language_code" +
      ",binding_type_code,dangdang_link,introduction,remark,company_id,is_online,book_series_id" +
      ",image_file_path,book_series_name,language_name,binding_type_name" +
      ",EXISTS (SELECT 1 FROM book_sku WHERE book_sku.ISBN = view_book_sku.ISBN AND book_sku.book_name = view_book_sku.book_name " +
      " AND book_sku.company_id = " + companyId.value + ") AS is_exist," +
      "CASE WHEN company_id IS NULL THEN 'Platfrom' ELSE 'Company' END AS location_type",
    pageSize: pageSize.value,
    pageIndex: pageIndex.value
  }
  common.call("GetViewBookSkuList", param, (res) => {
    let dataList = res.data.dataList;
    if (dataList.length === 0) {
      ElMessage({ message: "未查询到，请打开【批量录入新书】选择【表格方式】或【单本录入方式】录入新书", type: 'warning' })
      ISBN.value = '';
      return;
    }
    dataList.forEach(element => {
      element.imageFilePath = common.getAdminUrl(element, 'imageFilePath');
      element.selected = false;
      element.bookCount = 0;
    });
    skuDataList.value = dataList;
    recordCount.value = res.data.recordCount
    pageCount.value = res.data.pageCount
    if (dataList.length > 0) {
      //获取用系类图书列表
      getBookSkuSeriesList();
    }
  })
}

function getBookSkuSeriesList() {
  // 使用逗号分隔所有书籍系列ID
  const allBookSeriesIds = skuDataList.value
    .filter(sku => sku.bookSeriesId) // 过滤掉为空的 bookSeriesId
    .map(sku => sku.bookSeriesId)
    .join(',');
  if (allBookSeriesIds) {
    let data = {
      condition: "(company_id = " + companyId.value + " OR company_id IS NULL) AND book_series_id in (" + allBookSeriesIds + ") AND ISBN !='" + newISBN.value + "'",
      fields: "id,book_name,image_file_id,author,press,price,ISBN,language_code" +
        ",binding_type_code,dangdang_link,introduction,remark,company_id,is_online,book_series_id" +
        ",image_file_path,book_series_name,language_name,binding_type_name" +
        ",EXISTS (SELECT 1 FROM book_sku WHERE book_sku.ISBN = view_book_sku.ISBN AND book_sku.book_name = view_book_sku.book_name " +
        " AND book_sku.company_id = " + companyId.value + ") AS is_exist," +
        "CASE WHEN company_id IS NULL THEN 'Platfrom' ELSE 'Company' END AS location_type",
      pageSize: pageSizeSeries.value,
      pageIndex: pageIndexSeries.value
    }
    common.call("GetViewBookSkuList", data, (res) => {
      let homologyList = res.data.dataList;
      homologyList.forEach(element => {
        element.imageFilePath = common.getAdminUrl(element, 'imageFilePath');
        element.selected = false;
        element.bookCount = 0;
      });
      bookSeriesDataList.value = homologyList;
      recordCountSeries.value = res.data.recordCount
      pageCountSeries.value = res.data.pageCount
    })
  } else {
    bookSeriesDataList.value = []
    recordCountSeries.value = 0
    pageCountSeries.value = 0
  }
  ISBN.value = '';

}

function init() {
  // 显示弹窗
  detailFormVisible.value = true
}

/**
 * 录入图书 
 */
const isOn = ref(false);

function addBookSku() {
  let selectedSku = skuDataList.value.filter(item => item.selected);
  let selectedSeriesSku = bookSeriesDataList.value.filter(item => item.selected);
  const mergedRecords = selectedSku.concat(selectedSeriesSku);
  if (mergedRecords.length == 0) {
    ElMessage({ message: "请选择图书", type: 'warning' })
    return;
  }
  let param = {
    selectedSkuList: mergedRecords,
    companyId: companyId.value,
    isOn: isOn.value,
  }
  isLoading.value = true
  common.call('BatchAddBookSkuAndBookByISBN', param, (res) => {
    isOn.value = false;
    setTimeout(() => {
      isLoading.value = false
    }, 500)
    ElMessage({ message: "添加成功", type: 'success' })
    queryDataList();
  })
}

function handleClick(value, item) {
  item.selected = value
}

function selectAll() {
  const shouldSelectAll = !skuDataList.value.every(item => item.selected)
  skuDataList.value.forEach(item => {
    item.selected = shouldSelectAll
  })
  skuDataList.value = skuDataList.value.slice();
  const seriesSelectAll = !bookSeriesDataList.value.every(item => item.selected)
  bookSeriesDataList.value.forEach(item => {
    item.selected = seriesSelectAll
  })
  bookSeriesDataList.value = bookSeriesDataList.value.slice();
}

//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    ISBN.value = '';
    skuDataList.value = [];
    bookSeriesDataList.value = [];
  }
})

//当前页
const currentPage = computed({
  get() {
    return pageIndex.value + 1
  },
  set(newValue) {
    pageIndex.value = newValue - 1
    queryDataList()
  }
})
//页面大小
const pageSizeComputed = computed({
  get() {
    return pageSize.value
  },
  set(newValue) {
    pageIndex.value = 0
    pageSize.value = newValue
    queryDataList()
  }
})

//当前页
const currentPageSeries = computed({
  get() {
    return pageIndexSeries.value + 1
  },
  set(newValue) {
    pageIndexSeries.value = newValue - 1
    getBookSkuSeriesList()
  }
})
//页面大小
const pageSizeComputedSeries = computed({
  get() {
    return pageSizeSeries.value
  },
  set(newValue) {
    pageIndexSeries.value = 0
    pageSizeSeries.value = newValue
    getBookSkuSeriesList()
  }
})

//暴露
defineExpose({
  init
})
</script>
<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" title="扫描ISBN批量添加图书"
      width="85%" draggable>
      <BookSKUDetail ref="refDetail" />
      <el-scrollbar height="600px">
        <el-card shadow="never" class="user-search-card">
          <el-form :inline="true" class="user-form">
            <el-form-item ref="formItem">
              <el-input ref="inputRef" placeholder="ISBN" v-model="ISBN" autofocus
                @keydown.enter.prevent=queryDataList()></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="queryDataList()">查询</el-button>
            </el-form-item>
          </el-form>
          <div>
            <el-button @click="selectAll()">全选</el-button>
            <el-button type="success" @click="addBookSku()" :loading="isLoading">录入图书</el-button>
            <el-switch v-model="isOn" inactive-text="是否上架" style="margin-left: 10px;" />
          </div>
          <div style="line-height: 30px;">
            <div>
              <span>选择图书，然后点击录入。</span><span style="color: red;">选择的时候注意书名和ISBN</span>
            </div>
            <div>
              <span>注意！</span>
              <span style="color: blue">蓝框，</span>
              <span style="color: red;">标注的为本门店SKU</span>
            </div>
          </div>
        </el-card>
        <el-card shadow="never">
          <template #header>
            <div class="card-header">
              <span>查询结果</span>
            </div>
          </template>
          <div class="card-body">
            <div v-if="skuDataList.length == 0" style="text-align: center;">暂无数据</div>
            <el-row :gutter="20" style="margin-bottom: 20px">
              <el-col :xs="24" :sm="12" :md="8" :lg="6" v-for="item in skuDataList" :key="item.id">

                <el-card
                  :body-style="{ padding: '0px', border: item.locationType === 'Company' ? '2px solid blue' : 'none' }"
                  shadow="hover">
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
                  <div style="padding: 14px;line-height: 24px;">
                    <!-- <div style="display: flex;">
                      <div>系列：</div>
                      <div style="width: 80%;">
                        <span style="color: green">{{ item.bookSeriesName }}</span>
                      </div>
                    </div> -->
                    <div style="display: flex;">
                      <div>书名：</div>
                      <div style="width: 80%;">
                        <span style="color: #008000;">{{ item.bookSeriesName ? "[" + item.bookSeriesName + "]" : ""
                          }}</span>
                        <span style="font-weight:550;">{{ item.bookName }}</span>
                        <span style="color: #2e00ff;">{{ item.bindingTypeName ? "(" + item.bindingTypeName + ")" : ""
                          }}</span>
                      </div>
                    </div>
                    <div class="infolist">
                      <span>价格：</span>
                      <span>{{ item.price }}</span>
                    </div>
                    <div class="infolist">
                      <span>出版社：</span>
                      <span>{{ item.press }}</span>
                    </div>
                    <div class="infolist">
                      <span>ISBN：</span>
                      <span>{{ item.ISBN }}</span>
                    </div>
                    <div class="infolist">
                      <span>图书入库数量：</span>
                      <el-input-number v-model.number="item.bookCount" :min="0" :max="100" size="small"
                        @change="handleChange" />
                    </div>
                    <div class="bottom">
                      <el-button text class="button" type="primary"
                        @click="refDetail.init({ mode: 'view', primaryKeyValue: item.id })">查看详情...</el-button>
                    </div>
                    <div v-if="item.isExist == 1" style="color: red;">
                      <span>提示：</span>
                      <span>SKU已存在，可选择增加几本。此操作只增加书本，不新增SKU</span>
                    </div>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </div>
          <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSizeComputed" :page-sizes="pageSizes"
            :page-count="pageCount" :layout="layout" :total="recordCount" style="margin-top: 20px" />
        </el-card>
        <el-card shadow="never">
          <template #header>
            <div class="card-header">
              <span>同系列图书</span>
            </div>
          </template>
          <div class="card-body">
            <div v-if="bookSeriesDataList.length == 0" style="text-align: center;">暂无数据</div>
            <el-row :gutter="20" style="margin-bottom: 20px">
              <el-col :xs="24" :sm="12" :md="8" :lg="6" v-for="item in bookSeriesDataList" :key="item.id">
                <el-card class="card-item"
                  :body-style="{ padding: '0px', border: item.locationType === 'Company' ? '2px solid blue' : 'none' }"
                  shadow="hover">
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
                  <div style="padding: 14px;line-height: 24px;">
                    <!-- <div style="display: flex;">
                      <div>系列：</div>
                      <div style="width: 80%;">
                        <span style="color: green">{{ item.bookSeriesName }}</span>
                      </div>
                    </div> -->
                    <div style="display: flex;">
                      <div>书名：</div>
                      <div style="width: 80%;">
                        <span style="color: #008000;">{{ item.bookSeriesName ? "[" + item.bookSeriesName + "]" : ""
                          }}</span>
                        <span style="font-weight:550;">{{ item.bookName }}</span>
                        <span style="color: #2e00ff;">{{ item.bindingTypeName ? "(" + item.bindingTypeName + ")" : ""
                          }}</span>
                      </div>
                    </div>
                    <div class="infolist">
                      <span>价格：</span>
                      <span>{{ item.price }}</span>
                    </div>
                    <div class="infolist">
                      <span>出版社：</span>
                      <span>{{ item.press }}</span>
                    </div>
                    <div class="infolist">
                      <span>ISBN：</span>
                      <span>{{ item.ISBN }}</span>
                    </div>
                    <div class="infolist">
                      <span>图书入库数量：</span>
                      <el-input-number v-model.number="item.bookCount" :min="0" :max="100" size="small"
                        @change="handleChange" />
                    </div>
                    <div class="bottom">
                      <el-button text class="button" type="primary"
                        @click="refDetail.init({ mode: 'view', primaryKeyValue: item.id })">查看详情...</el-button>
                    </div>
                    <div v-if="item.isExist == 1" style="color: red;">
                      <span>提示：</span>
                      <span>SKU已存在，可选择增加几本。此操作只增加书本，不新增SKU</span>
                    </div>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </div>
          <el-pagination v-model:current-page="currentPageSeries" v-model:page-size="pageSizeComputedSeries"
            :page-sizes="pageSizesSeries" :page-count="pageCountSeries" :layout="layout" :total="recordCountSeries"
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
<!-- <style>
.card-item {
  display: flex;
  flex-direction: column;
  align-items: stretch;

  .el-card__body {
    flex-grow: 1;
  }
}
</style> -->

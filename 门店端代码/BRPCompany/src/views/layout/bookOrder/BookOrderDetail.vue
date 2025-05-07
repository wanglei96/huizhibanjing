<script setup>
import { reactive, ref, watch } from 'vue';
import detail from '@/js/detail';
import common from '@/js/common';
import config from '@/js/config';
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import BookOrderAddBook from './BookOrderAddBook.vue';

//定义事件发射器
const emit = defineEmits(['reload-data']);
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref();
//图书列表引用
const refBookOrderAddBook = ref();

let page = reactive({});


// 当前登陆人ID
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
const bookOrderItemList = ref([]);
const init = (options) => {
  page = detail({
    baseTitle: '借书订单',
    tableName: 'book_order',
    getServiceName: 'GetViewBookOrder',
    updateServiceName: 'UpdateBookOrderInfo',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit
  });
  getBookOrderItemList(options.primaryKeyValue);
  detailFormVisible.value = true;
}
function getBookOrderItemList(bookOrderId) {
  var params = {
    bookOrderId: bookOrderId,
    orderBy: "book_order_type_code,create_time",
    pageSize: 1000
  }
  common.call('GetViewBookOrderItemList', params, (res) => {
    bookOrderItemList.value = res.data.dataList;
  });
}

//暴露
defineExpose({
  init
})

const businessDateText = computed({
  get() {
    return page.dataEntity.value.businessDateText
  },
  set(val) {
    page.dataEntity.value.businessDateText = val
    page.dataEntity.value.businessDate = val
  }
})

const deliverTimeText = computed({
  get() {
    return page.dataEntity.value.deliverTimeText
  },
  set(val) {
    page.dataEntity.value.deliverTimeText = val
    page.dataEntity.value.deliverTime = val
  }
})


//表单校验规则
const rules = reactive({
  deliverTimeText: [
    { required: true, message: '请输入配送时间', trigger: 'change' }
  ],
  businessDateText: [
    { required: true, message: '请输入单据时间', trigger: 'change' }
  ],
  delivererId: [
    { required: true, message: '请选择配送员', trigger: 'change' }
  ]
})
function deleteBookRow(row, index) {
  if (!row.id) {
    bookOrderItemList.value.splice(index, 1);
  } else {
    ElMessageBox.confirm(
      '此操作不可逆，是否确认删除',
      '提醒',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
    )
      .then(() => {
        let params = {
          bookOrderItemId: row.id,
          memberId: row.memberId,
          bookOrderTypeCode: row.bookOrderTypeCode,
          bookId: row.bookId
        }
        common.call('DeleteBookOrderItemInfo', params, (res) => {
          bookOrderItemList.value.splice(index, 1);
        });
      })
      .catch(() => {
      })
  }
  console.log(bookOrderItemList.value)
}

provide('addBookOrderItemList', addBookOrderItemList);

function addBookOrderItemList(options) {
  if (options.dataList) {
    options.dataList.forEach(element => {
      //判断图书ID是否存在
      if (bookOrderItemList.value.some(item => item.bookId === element.id)) {
        return;
      }
      let bookOrderItem = {
        bookId: element.id,
        bookName: element.bookName,
        bookSn: element.sn,
      }
      if (options.type === 'borrow') {
        bookOrderItem.bookOrderTypeCode = 'Borrow'
        bookOrderItem.bookOrderTypeName = '借书'
      } else if (options.type === 'return') {
        bookOrderItem.bookOrderTypeCode = 'Return'
        bookOrderItem.bookOrderTypeName = '还书'
      }
      bookOrderItemList.value.push(bookOrderItem);
    });
  }
  console.log(bookOrderItemList.value);
}


//提交表单
function confirm() {
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
  const borrowItems = bookOrderItemList.value.filter(item => item.bookOrderTypeCode === 'Borrow');
  const returnItems = bookOrderItemList.value.filter(item => item.bookOrderTypeCode === 'Return');
  data.value.borrowCount = borrowItems.length;
  data.value.returnCount = returnItems.length;
  const newBorrowItems = borrowItems.filter(item => !item.id);
  const newReturnItems = returnItems.filter(item => !item.id);
  data.value.newBorrowCount = newBorrowItems.length;
  data.value.newReturnCount = newReturnItems.length;
  data.value.bookOrderItemList = bookOrderItemList.value;
  return data;
}

const rowClassName = ({ row }) => {
  if (row.bookOrderTypeCode === 'Borrow') {
    return 'borrow-row'; // 定义名为 active-row 的类名
  } else if (row.bookOrderTypeCode === 'Return') {
    return 'return-row'; // 定义名为 inactive-row 的类名
  } else {
    return ''; // 默认情况下不设置额外类名
  }
};

</script>

<template>
  <div>
    <BookOrderAddBook ref="refBookOrderAddBook" />
    <el-dialog destroy-on-close v-model="detailFormVisible" :title="page.title ? page.title.value : ''" width="1200"
      draggable :close-on-click-modal="false">
      <div>
        <div class="card-body">
          <div class="order-info-item">
            <div class="order-info-title">
              <span></span>
              <div>会员信息</div>
            </div>
            <el-form label-width="80px">
              <el-row>
                <el-col :xs="12" :md="8" :lg="8">
                  <el-form-item label="会员名称">
                    {{ page.dataEntity.value.memberName }}
                  </el-form-item>
                </el-col>
                <el-col :xs="12" :md="8" :lg="8">
                  <el-form-item label="会员卡号">
                    {{ page.dataEntity.value.memberCardNo }}
                  </el-form-item>
                </el-col>
                <el-col :xs="12" :md="8" :lg="8">
                  <el-form-item label="会员电话">
                    {{ page.dataEntity.value.memberMobilePhone }}
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </div>
          <div class="order-info-item">
            <div class="order-info-title">
              <span></span>
              <div>订单信息</div>
            </div>
            <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
              :disabled="page.mode === 'view'" label-width="80px">
              <el-row>
                <el-col :xs="6" :sm="6" :md="6" :lg="6">
                  <el-form-item label="订单编号" prop="bookOrderNo">
                    <el-input v-model="page.dataEntity.value.bookOrderNo" disabled></el-input>
                  </el-form-item>
                </el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6">
                  <el-form-item label="单据日期" prop="businessDateText">
                    <el-date-picker v-model="businessDateText" type="datetime" value-format="YYYY-MM-DD HH:mm:ss"
                      placeholder="单据日期" />
                  </el-form-item>
                </el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6">
                  <el-form-item label="配送时间" prop="deliverTimeText">
                    <el-date-picker v-model="deliverTimeText" type="datetime" value-format="YYYY-MM-DD HH:mm:ss"
                      placeholder="配送时间" />
                  </el-form-item>
                </el-col>
                <!-- <el-col :xs="6" :sm="6" :md="6" :lg="6">
                  <el-form-item label="借阅状态" prop="bookOrderBorrowStatusCode">
                    <DatabaseSelect table-name="book_order_borrow_status"
                      v-model="page.dataEntity.value.bookOrderBorrowStatusCode" placeholder="请选择"
                      class="form-item-width" />
                  </el-form-item>
                </el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6">
                  <el-form-item label="还书状态" prop="bookOrderReturnStatusCode">
                    <DatabaseSelect table-name="book_order_return_status"
                      v-model="page.dataEntity.value.bookOrderReturnStatusCode" placeholder="请选择"
                      class="form-item-width" />
                  </el-form-item>
                </el-col> -->
                <el-col :xs="6" :sm="6" :md="6" :lg="6">
                  <el-form-item label="配送员" prop="delivererId">
                    <DatabaseSelect table-name="view_company_user"
                      :condition="'company_id=' + companyId + ' and is_deliverer =1 '"
                      v-model="page.dataEntity.value.delivererId" valueFieldName="id" placeholder="请选择"
                      class="form-item-width" />
                  </el-form-item>
                </el-col>
                <el-col :xs="6" :sm="6" :md="6" :lg="6">
                  <el-form-item label="拣书员" prop="bookpickerId">
                    <DatabaseSelect table-name="view_company_user"
                      :condition="'company_id=' + companyId + ' and is_bookpicker =1 '"
                      v-model="page.dataEntity.value.bookpickerId" valueFieldName="id" placeholder="请选择"
                      class="form-item-width" />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </div>
          <div class="order-info-item">
            <div class="order-info-title">
              <span></span>
              <div>图书信息</div>
            </div>
            <el-form :inline="true" v-if="page.mode === 'add' || page.mode === 'edit'">
              <el-row>
                <el-col :span="18">
                  <el-button type="primary"
                    @click="refBookOrderAddBook.init({ mode: 'borrow', memberId: page.dataEntity.value.memberId })">借书</el-button>
                  <el-button type="danger"
                    @click="refBookOrderAddBook.init({ mode: 'return', memberId: page.dataEntity.value.memberId })">还书</el-button>
                </el-col>
              </el-row>
            </el-form>
            <el-table ref="multipleTableRef" :data="bookOrderItemList" style="width: 100%"
              :row-class-name="rowClassName">
              <el-table-column property="bookName" label="图书名称" />
              <el-table-column property="bookSn" label="图书编号" />
              <el-table-column property="bookOrderTypeName" label="类型" />
              <el-table-column property="deliverStatusName" label="状态" />
              <el-table-column property="createTimeText" label="时间" />
              <el-table-column label="操作" align="center">

                <template #default="scope">
                  <el-space spacer="|" style="color: #999" wrap>
                    <el-link type="danger" @click="deleteBookRow(scope.row, scope.$index)"
                      v-if="page.mode === 'add' || page.mode === 'edit'">删除</el-link>
                  </el-space>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </div>
      </div>

      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" v-if="page.mode === 'add' || page.mode === 'edit'" :loading="page.isLoading.value"
            @click="confirm()"> 确定 </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
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

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}

.order-title {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-bottom: 30px;
}

.order-title-info {
  margin-left: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;

  .order-type {
    font-size: 16px;
  }

  .order-no {
    font-size: 13px;
    color: #999;
    margin-top: 8px;
  }
}

.order-info-item {
  border-bottom: 1px dashed #eee;
}

.order-info-title {
  width: 100%;
  height: 40px;
  line-height: 40px;
  display: flex;
  align-items: center;
}

.order-info-title span {
  display: inline-block;
  width: 3px;
  height: 15px;
  background-color: #0256ff;
  margin-right: 10px;
}

.order-info-item {
  padding: 15px;
  font-size: 14px;

  .el-row {
    margin-top: 10px;
  }

  .el-col {
    margin-bottom: 18px;
  }
}
</style>

<style>
.el-table .borrow-row {
  background-color: #d9edf7 !important;
}

/* .el-table .return-row {
  background-color: lightcoral !important;
} */
</style>
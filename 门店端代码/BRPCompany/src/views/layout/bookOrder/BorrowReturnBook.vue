<script setup>
import common from '@/js/common'
import { reactive, ref, inject } from 'vue';
import { useRoute } from 'vue-router'
import BookCompensationDetail from './BookCompensationDetail.vue';
import BookDamageDetail from '../book/BookDamageDetail.vue';
import dayjs from "dayjs";
import management from '@/js/management';
import successSound from '@/assets/mp3/success.mp3';
import errorSound from '@/assets/mp3/error.mp3';

// 声明赔付页页
const bookCompensationDetailRef = ref();

// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
// 还书单编号
const returnBookSnQuery = ref();
// 会员信息查询条件
const memberInfoQuery = ref('');
// 会员信息
const memberCarInfo = ref({});
//是否显示会员信息
const isShowMemberInfo = ref(false);
//借书编号
const borrowBookSn = ref('');
//还书列表
const returnBookOrderItemList = ref([]);
//是否显示还书列表
const isShowReturnTable = ref(false);
//馆内图书信息
const viewBookDtata = ref({});
//馆内图书显示
const isShowViewBook = ref(false);
//会员列表
const memberCartInfoList = ref([]);
//是否显示会员列表
const isShowMemberCartInfoList = ref(false);
// 会员卡停卡记录路由参数查询
const route = useRoute();

watch(() => route.query.memberCardNo, (newValue, oldValue) => {
  if (newValue !== oldValue) {
    if (newValue) {
      memberInfoQuery.value = newValue;
      queryDataList();
    }
  }
});



onMounted(() => {
  const memberCardNo = route.query.memberCardNo;
  if (memberCardNo) {
    memberInfoQuery.value = memberCardNo;
    queryDataList();
  }
  // 获取当前地址
  const url = new URL(window.location.href);

  // 删除指定的参数
  url.searchParams.delete('memberCardNo');

  // 更新浏览器地址栏
  window.history.replaceState({}, '', url.toString());
});


function queryDataList() {
  if (memberInfoQuery.value) {
    returnBookSnQuery.value = '';
    memberCarInfo.value = {}
    isShowMemberInfo.value = false
    borrowBookSn.value = ''
    returnBookOrderItemList.value = []
    isShowReturnTable.value = false
    viewBookDtata.value = {}
    isShowViewBook.value = false
    memberCartInfoList.value = []
    isShowMemberCartInfoList.value = false
    selectedItemId.value = 0
    //查询会员列表
    let params = {
      /*  condition: "member_id is not null and disabled IS NOT TRUE and " +
         "(member_name like '%" + memberInfoQuery.value + "%'  or card_no  like '%" + memberInfoQuery.value + "%' or mobile_phone like '%" + memberInfoQuery.value + "%')", */
      companyId: companyId.value,
      memberInfoQuery: memberInfoQuery.value
    }
    common.call("GetMemberCardListByInfo", params, function (result) {
      memberCartInfoList.value = result.data;
      if (result.data.length == 1) {
        selectedItemId.value = result.data[0].id
        borrowBookSn.value = ""
        //获取会员信息
        getMemberCardInfo();
      } else if (result.data.length == 0) {
        ElMessage({ message: "未查询到会员信息", type: 'warning' })
        isShowMemberCartInfoList.value = false;
        isShowMemberInfo.value = false;
        return;
      } else {
        isShowMemberCartInfoList.value = true;
      }
    })
  } else {
    memberCarInfo.value = {};
    isShowMemberInfo.value = false;
    memberCartInfoList.value = [];
    isShowMemberCartInfoList.value = false;
  }
}
/**
 * 还书
 */
const isLoadingReturn = ref(false);
function returnBook() {
  if (!returnBookSnQuery.value) {
    ElMessage({ message: "请先输入借书号", type: 'warning' })
    return;
  }
  let param = {
    returnBookSn: returnBookSnQuery.value,
    bookOrderNo: new Date().format("yyyyMMddhhmmss"),
    companyId: companyId.value
  }
  isLoadingReturn.value = true
  common.call("AddReturnBookOrder", param, function (result) {
    var dataItem = result.data;
    if (dataItem.viewBook) {
      viewBookDtata.value = dataItem.viewBook;
      isShowViewBook.value = true;
    }
    if (dataItem.returnBookOrderItemInfo) {
      ElMessage({ message: "还书成功", type: 'success' });
      returnBookOrderItemList.value.unshift(dataItem.returnBookOrderItemInfo);
      isShowReturnTable.value = true;
      if (selectedItemId.value) {
        getMemberCardInfo();
      }
    }
    setTimeout(() => {
      isLoadingReturn.value = false
    }, 200)
    returnBookSnQuery.value = '';
    const audio = new Audio(successSound);
    audio.play();
  }, function (error) {
    var errorMessage = error.errorMessage || error.error_message;
    //输出错误信息
    if (error.messageType === "warning") {
      common.showWarning(errorMessage);
    } else {
      common.showError(errorMessage);
    }
    setTimeout(() => {
      isLoadingReturn.value = false
    }, 100)
    const audio = new Audio(errorSound);
    audio.play();
    returnBookSnQuery.value = '';
  })
}
/**
 * 还书行
 * @param {*} bookSn 
 */
function returnBookRow(bookSn) {
  let param = {
    returnBookSn: bookSn,
    bookOrderNo: new Date().format("yyyyMMddhhmmss"),
    companyId: companyId.value
  }
  common.call("AddReturnBookOrder", param, function (result) {
    var dataItem = result.data;
    if (dataItem.viewBook) {
      viewBookDtata.value = dataItem.viewBook;
      isShowViewBook.value = true;
    }
    if (dataItem.returnBookOrderItemInfo) {
      ElMessage({ message: "还书成功", type: 'success' });
      returnBookOrderItemList.value.unshift(dataItem.returnBookOrderItemInfo);
      isShowReturnTable.value = true;
      getMemberCardInfo();
    }
  })
}
/**
 * 借书
 */
const isLoadingBorrow = ref(false);
function borrowBook() {
  if (!borrowBookSn.value) {
    ElMessage({ message: "书号不能为空", type: 'warning' })
    return;
  }
  //判断是否借阅过
  let param = {
    borrowBookSn: borrowBookSn.value,
    memberId: memberCarInfo.value.memberId,
    companyId: companyId.value
  }
  common.call("CheckMemberIsBorrowed", param, function (result) {
    isLoadingBorrow.value = true
    if (result.data) {
      ElMessageBox.confirm(
        '这本书，会员已经借过了，请询问会员是否继续借阅!',
        '提醒',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      )
        .then(() => {
          addBorrowBookOrder();
        })
        .catch(() => {
          setTimeout(() => {
            isLoadingBorrow.value = false
          }, 200)
        })
    } else {
      addBorrowBookOrder();
    }
  })
}

function addBorrowBookOrder() {
  returnBookSnQuery.value = '';
  let param = {
    borrowBookSn: borrowBookSn.value,
    memberId: memberCarInfo.value.memberId,
    bookOrderNo: new Date().format("yyyyMMddhhmmss"),
    companyId: companyId.value
  }
  common.call("AddOfflineBookOrder", param, function (result) {
    ElMessage({ message: "借阅成功", type: 'success' })
    setTimeout(() => {
      isLoadingBorrow.value = false
    }, 200)
    borrowBookSn.value = '';
    const audio = new Audio(successSound);
    audio.play();
    getMemberCardInfo();
  }, function (error) {
    var errorMessage = error.errorMessage || error.error_message;
    //输出错误信息
    if (error.messageType === "warning") {
      common.showWarning(errorMessage);
    } else {
      common.showError(errorMessage);
    }
    borrowBookSn.value = '';
    const audio = new Audio(errorSound);
    audio.play();
    setTimeout(() => {
      isLoadingBorrow.value = false
    }, 100)
  })
}

// 查询条件重置
function resetQuery() {
  returnBookSnQuery.value = '';
  memberInfoQuery.value = '';
  memberCarInfo.value = {}
  isShowMemberInfo.value = false
  borrowBookSn.value = ''
  returnBookOrderItemList.value = []
  isShowReturnTable.value = false
  viewBookDtata.value = {}
  isShowViewBook.value = false
  memberCartInfoList.value = []
  isShowMemberCartInfoList.value = false
  selectedItemId.value = 0
}

function formatCreateTime(row, column, cellValue, index) {
  if (cellValue) {
    return dayjs(cellValue).format('YYYY-MM-DD HH:mm:ss');
  }
  return cellValue;
}

//领取书袋
function addBookPocket(memberId) {
  let params = {
    "memberId": memberId,
  };
  common.call("AddMemberBookPocket", params, function (result) {
    getMemberCardInfo();
  })
}
const selectedItemId = ref(0);
const tagChange = (status, item) => {

  returnBookSnQuery.value = '';
  returnBookOrderItemList.value = []
  isShowReturnTable.value = false
  viewBookDtata.value = {}
  isShowViewBook.value = false

  selectedItemId.value = item.id
  borrowBookSn.value = ""
  //获取会员信息
  getMemberCardInfo();

}
const isReturnedList = [
  {
    value: 'is_returned is true',
    label: '是',
  },
  {
    value: 'is_returned is not true',
    label: '否',
  }]

const borrowBookList = ref({});
const bookDamageList = ref([]);
const refBorrowBookSearchForm = ref();
const refBookDamageSearchForm = ref();
const refBorrowBookDetail = ref();
const refBookDamageDetail = ref();
//等待配送列表
const deliveringBookList = ref({})
const refDeliveringBookSearchForm = ref();
//等待入库列表
const storageingBookList = ref({})
const refStorageingBookSearchForm = ref();

const bookOrderStatusCodeList = ref([]);

function getMemberCardInfo(memberCartId) {
  let param = {
    companyId: companyId.value
  }
  param.memberCartId = memberCartId || selectedItemId.value;
  common.call("GetBorrowReturnBookInfo", param, function (result) {
    memberCarInfo.value = result.data;
    isShowMemberInfo.value = true;
    let memberId = result.data.memberId;
    bookOrderStatusCodeList.value = [
      {
        value: "deliver_status_code = 'Delivering' and book_order_type_code='Borrow'",
        label: '等待配送',
      },
      {
        value: "is_returned is not true and book_order_type_code='Borrow' and " +
          "book_id in (select id from book where member_id =" + memberId + "  and company_id = " + companyId.value + " and borrow_status_code = 'Out')",
        label: '借阅中',
      }, {
        value: "deliver_status_code = 'Storageing' and book_order_type_code='Return'",
        label: '等待入库',
      }, {
        value: "book_order_borrow_status_code = 'Received' and book_order_type_code='Borrow'",
        label: '借阅完成',
      }]
    borrowBookList.value = management({
      tableName: 'book_order_item',
      orderBy: 'create_time desc',
      getListServiceName: 'GetViewBookOrderItemList',
      pageSize: 10,
      parameters: {
        memberId: result.data.memberId,
        condition: "is_returned is not true and book_order_type_code='Borrow' and " +
          "book_id in (select id from book where member_id =" + memberId + "  and company_id = " + companyId.value + " and borrow_status_code = 'Out')",
      },
    })
    deliveringBookList.value = management({
      tableName: 'book_order_item',
      orderBy: 'create_time desc',
      getListServiceName: 'GetViewBookOrderItemList',
      pageSize: 10,
      parameters: {
        condition: "deliver_status_code in ('Delivering','Outing')",
        bookOrderTypeCode: 'Borrow',
        memberId: result.data.memberId,
      },
    })
    storageingBookList.value = management({
      tableName: 'book_order_item',
      orderBy: 'create_time desc',
      getListServiceName: 'GetViewBookOrderItemList',
      pageSize: 10,
      parameters: {
        condition: "(deliver_status_code = 'Storageing')",
        bookOrderTypeCode: 'Return',
        memberId: result.data.memberId,
      },
    })
    bookDamageList.value = management({
      tableName: 'book_damage',
      orderBy: 'create_time desc',
      pageSize: 10,
      getListServiceName: 'GetViewBookDamageList',
      parameters: {
        memberId: result.data.memberId,
      },
    })
  })
}

function viewMemberInfo(memberCardId) {
  selectedItemId.value = memberCardId
  //获取会员信息
  getMemberCardInfo();
}

function cancelBorrowed(id) {
  ElMessageBox.confirm(
    '确定要取消借书吗？',
    '提醒',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      let params = {
        bookOrderItemId: id,
      }
      common.call('CancelledOnlineBorrowBookOrder', params, (res) => {
        ElMessage({ message: "取消借书成功", type: 'success' })
        getMemberCardInfo()
      })
    })
    .catch(() => {
    })
}
function cancelReturned(id) {
  ElMessageBox.confirm(
    '确定要取消还书吗？',
    '提醒',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      let params = {
        bookOrderItemId: id,
      }
      common.call('CancelledOnlineReturnBookOrder', params, (res) => {
        ElMessage({ message: "取消还书成功", type: 'success' })
        getMemberCardInfo()
      })
    })
    .catch(() => {
    })
}

function saveReturn(id) {
  let params = {
    isReturned: 1,
    id: id,
    bookReturnTime: '2024-07-02 21:28:35',
    /* bookInStocksTime:'2024-07-09 14:22:22', */
  }
  common.call('UpdateBookOrderItem', params, (res) => {
    ElMessage({ message: "还书成功", type: 'success' })
    //getMemberCardInfo()
  })
}
</script>

<template>
  <div class="user">
    <BookCompensationDetail ref="bookCompensationDetailRef" />
    <BookDamageDetail ref="refBookDamageDetail" @reload-data="getMemberCardInfo" />
    <BookDamageDetail ref="refBorrowBookDetail" @reload-data="getMemberCardInfo" />
    <el-card shadow="never" class="user-search-card">
      <el-form :inline="true" class="user-form">
        <el-form-item label="">
          <el-input placeholder="书籍编号" v-model="returnBookSnQuery" @keydown.enter.prevent="returnBook()"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="returnBook()" :loading="isLoadingReturn">还书</el-button>
        </el-form-item>
        <el-form-item label="">
          <el-input placeholder="卡号/姓名/电话" v-model="memberInfoQuery"
            @keydown.enter.prevent="queryDataList()"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="queryDataList()">查询</el-button>
          <el-button @click="resetQuery()">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <div v-show="isShowMemberCartInfoList">
      <el-check-tag v-for="item in memberCartInfoList" :key="item" :checked="selectedItemId != item.id" type="primary"
        style="margin: 0 10px 5px 0;" @change="status => tagChange(status, item)">
        {{ item.memberName ? item.memberName : '' }}，
        {{ item.gender ? '男' : '女' }}，
        {{ item.mobilePhone ? item.mobilePhone : '无' }}，
        {{ item.memberCardTypeName ? item.memberCardTypeName : '' }}</el-check-tag>
    </div>
    <!-- 在馆内图书 -->
    <el-card v-show="isShowViewBook">
      <div class="in-book">
        <span style="color: #b86769;">书名：</span>
        <span style="color: #008000;">{{ viewBookDtata.bookSeriesName ? "[" + viewBookDtata.bookSeriesName + "]" : ""
          }}</span>
        <span style="color: #b86769;">{{ viewBookDtata.bookName }}</span>
        <span style="color: #0000ff;">{{ viewBookDtata.bindingTypeName ? "(" + viewBookDtata.bindingTypeName + ")" : ""
          }}</span>
      </div>
      <div class="in-book"><span style="color: #b86769;">图书状态：{{ viewBookDtata.borrowStatusName }}</span></div>
      <div class="in-book"><span style="color: #b86769;">借书，请先输入会员卡号！</span></div>
    </el-card>
    <!-- 还书 -->
    <el-card shadow="never" class="return-book-card" v-show="isShowReturnTable">
      <template #header>
        <div>
          <span>还书列表</span>
        </div>
      </template>
      <el-table :data="returnBookOrderItemList" style="width: 100%">
        <el-table-column property="bookSn" label="会员信息" width="200" align="center">
          <template #default="scope">
            <div @click="viewMemberInfo(scope.row.memberCardId)">
              <div>
                <span style="color: #337ab7;">{{ scope.row.memberName }}</span>
              </div>
              <div>
                <span style="color: #337ab7;">{{ scope.row.memberCardNo }}</span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="bookName" label="书名" width="450" align="center">
          <template #default="scope">
            <div>
              <span style="color: #008000;">{{ scope.row.bookSeriesName ? "[" + scope.row.bookSeriesName + "]" : ""
                }}</span>
              <span>{{ scope.row.bookName }}</span>
              <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
                }}</span>
            </div>
            <div>
              <span>{{ scope.row.bookSn }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="bookOrderReturnStatusName" label="状态" width="260" align="center">
          <template #default="scope">
            <div>
              <span style="color: red;">{{ scope.row.bookOrderReturnStatusName }}</span>
            </div>
            <div v-if="scope.row.memberBookboxIsActivated == false">
              <span style="color: red;">{{ scope.row.memberCardStatusCode === 'Normal' ? "已解锁" : "账号锁定，还需归还" +
                scope.row.borrowingQuantity +
                "本书"
                }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="weizhi" label="位置" align="center">
          <template #default="scope">
            <div>
              <span style="color: red;">云库</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="tixing" label="损坏提醒" width="150" align="center">
          <template #default="scope">
            <!-- <div>
              <el-switch v-model="scope.row.isDamage" inactive-text="是否有损："
                @change="value => isDamageChange(value, scope.row.id)" />
            </div> -->
            <div></div>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 借书 -->
    <el-card shadow="never" v-show="isShowMemberInfo" class="member-card">
      <template #header>
        <div>
          <span>会员卡信息</span>
        </div>
      </template>
      <div class="card-body">
        <div class="infolist">
          <span>会员卡号：</span>
          <span style="color: #eb7c54">{{ memberCarInfo.cardNo }}</span>
        </div>
        <div class="infolist"><span>宝宝姓名：</span><span>{{ memberCarInfo.memberName }}</span></div>
        <div class="infolist">
          <span>联系方式：</span>
          <span style="color: #0b84d8">{{ memberCarInfo.mobilePhone }}</span>
        </div>
        <div class="infolist">
          <span>宝宝生日：</span>
          <span style="color: #0b84d8">{{ memberCarInfo.birthdayText }}</span>
        </div>
        <div class="infolist">
          <span>会员卡失效日期：</span>
          <span style="color: #0b84d8">{{ memberCarInfo.expireDateText }}</span>
        </div>
        <div class="infolist">
          <span>会员卡最大借阅次数：</span>
          <span style="color: #0b84d8">{{ memberCarInfo.isUnlimited ? '无限制' : memberCarInfo.maxBorrowQuantity }}</span>
        </div>
        <div class="infolist">
          <span>会员卡每次可借数量：</span>
          <span style="color: red;">{{ memberCarInfo.maxBorrowableQuantity }}</span>（基础） <span style="color: red;">{{
            memberCarInfo.extendQuantity }}</span>（扩展）
        </div>
        <!-- <div class="infolist">
          <span>会员卡当前可借数量：</span>
          <span style="color: #0b84d8">{{ memberCarInfo.borrowableQuantity }}</span>
        </div> -->
        <div class="infolist">
          <span>会员卡类型：</span>
          <span style="color: #0b84d8">{{ memberCarInfo.memberCardTypeName }}</span>
        </div>
        <div class="infolist">
          <span>停卡状态：</span>
          <span style="color: red" v-if="memberCarInfo.isStopCard">停卡中</span>
          <span style="color: #0b84d8" v-else>正常</span>
        </div>
        <div class="infolist">
          <span>已领书袋数量：</span>
          <span style="color: #0b84d8">{{ memberCarInfo.bookPocketQuantity ? memberCarInfo.bookPocketQuantity : 0
            }}</span>
          <span><el-link type="danger" @click="addBookPocket(memberCarInfo.memberId)">领取（点击+1）</el-link></span>
        </div>
        <div class="infolist">
          <span>借阅中的册数：</span>
          <span style="color: #0b84d8">{{ memberCarInfo.borrowingBookCount }}</span>
        </div>
        <div class="infolist">
          <span>共计借阅册数：</span>
          <span style="color: #0b84d8">{{ memberCarInfo.borrowedBookCount }}</span>
        </div>
        <div class="infolist">
          <span>总计借阅次数：</span>
          <span style="color: #0b84d8">{{ memberCarInfo.borrowCount }}</span>
        </div>
        <div class="infolist">
          <span>会员地址：</span>
          <span style="color: #0000ff">{{ memberCarInfo.entranceGuard ? "[" + memberCarInfo.entranceGuard + "]" : ""
            }}</span>
          <span style="color: #0b84d8">{{ memberCarInfo.address }}</span>
          <span style="color: #0000ff">{{ memberCarInfo.bookboxPlace ? "[" + memberCarInfo.bookboxPlace + "]" : ""
            }}</span>
        </div>
        <div class="infolist" v-if="memberCarInfo.canBorrowed" style="margin-top: 10px;">
          <el-input placeholder="输入书号借书" v-model="borrowBookSn" @keydown.enter.prevent=borrowBook()
            style="width: 200px;margin-right: 20px;" />
          <el-button type="primary" @click="borrowBook()" :loading="isLoadingBorrow">借书</el-button>
        </div>
        <div v-else>
          <div class="not-borrow-book">
            <span style="color: red;">无法借书原因：</span>
            <span>{{ memberCarInfo.notBorrowedCause }}</span>
          </div>
        </div>
      </div>
    </el-card>

    <!-- 已借绘本列表 -->
    <el-card shadow="never" class="order-item-card" v-if="isShowMemberInfo">
      <template #header>
        <div>
          <span>已借绘本列表</span>
        </div>
      </template>
      <el-form ref="refBorrowBookSearchForm" :model="borrowBookList.parameters">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书名" prop="bookName">
              <el-input placeholder="请输入..." v-model="borrowBookList.parameters.bookName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书号" prop="bookSn">
              <el-input placeholder="请输入..." v-model="borrowBookList.parameters.bookSn"></el-input>
            </el-form-item>
          </el-col>
          <!-- <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="是否已还" prop="condition">
              <el-select placeholder="请选择" v-model="borrowBookList.parameters.condition" filterable clearable>
                <el-option label="是" value="is_returned is true"></el-option>
                <el-option label="否" value="is_returned is not true"></el-option>
                <el-option v-for="item in isReturnedList" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
          </el-col> -->
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="借阅状态" prop="condition">
              <el-select placeholder="请选择" v-model="borrowBookList.parameters.condition" filterable clearable>
                <el-option v-for="item in bookOrderStatusCodeList" :key="item.value" :label="item.label"
                  :value="item.value" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click='borrowBookList.query'>搜索</el-button>
              <el-button @click='borrowBookList.resetForm(refBorrowBookSearchForm)'>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-table :data="borrowBookList.dataList" style="width: 100%">
        <el-table-column property="bookSn" label="书号" width="200">
          <template #default="scope">
            <div>
              <span>{{ scope.row.bookSn }}</span>
            </div>
            <div>
              <span>{{ scope.row.bookcaseName }}</span>
            </div>
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
          </template>
        </el-table-column>
        <el-table-column property="bookOrderBorrowStatusName" label="借阅状态">
          <template #default="scope">
            <div>
              <span>{{ scope.row.bookOrderBorrowStatusName }}</span>
            </div>
            <div>
              <span :style="{ color: scope.row.isOffline ? 'red' : 'blue' }">{{ scope.row.isOffline ? '线下' : '线上'
                }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="isReturned" label="是否已还">
          <template #default="scope">
            <div>
              <span :style="{ color: scope.row.isReturned ? 'red' : 'blue' }">{{ scope.row.isReturned ? '已还' : '未还'
                }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="createTimeText" label="借阅时间" />
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id"
                v-if="scope.row.isReturned != true && scope.row.bookOrderBorrowStatusCode == 'Received'"
                @click="returnBookRow(scope.row.bookSn)">还书</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="refBorrowBookDetail.init({ mode: 'add', dataItem: scope.row })">标记</el-link>
              <!-- <el-link type="primary" :id="scope.row.id" @click="saveReturn(scope.row.id)">确定</el-link> -->
            </el-space>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:current-page="borrowBookList.currentPage"
        v-model:page-size="borrowBookList.pageSizeComputed" :page-sizes="borrowBookList.pageSizes"
        :page-count="borrowBookList.pageCount" :layout="borrowBookList.layout" :total="borrowBookList.recordCount"
        style="margin-top: 20px" />
    </el-card>


    <!-- 等待配送列表 -->
    <el-card shadow="never" class="order-item-card" v-if="isShowMemberInfo">
      <template #header>
        <div>
          <span>等待配送列表</span>
        </div>
      </template>
      <el-form ref="refDeliveringBookSearchForm" :model="deliveringBookList.parameters">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书名" prop="bookName">
              <el-input placeholder="请输入..." v-model="deliveringBookList.parameters.bookName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书号" prop="bookSn">
              <el-input placeholder="请输入..." v-model="deliveringBookList.parameters.bookSn"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click='deliveringBookList.query'>搜索</el-button>
              <el-button @click='deliveringBookList.resetForm(refDeliveringBookSearchForm)'>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-table :data="deliveringBookList.dataList" style="width: 100%">
        <el-table-column property="bookSn" label="书号" width="200">
          <template #default="scope">
            <div>
              <span>{{ scope.row.bookSn }}</span>
            </div>
            <div>
              <span>{{ scope.row.bookcaseName }}</span>
            </div>
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
          </template>
        </el-table-column>
        <el-table-column property="deliverStatusName" label="状态">
          <template #default="scope">
            <div>
              <span>{{ scope.row.deliverStatusName }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="createTimeText" label="借阅时间" />
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id" @click="cancelBorrowed(scope.row.id)">取消借阅</el-link>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:current-page="deliveringBookList.currentPage"
        v-model:page-size="deliveringBookList.pageSizeComputed" :page-sizes="deliveringBookList.pageSizes"
        :page-count="deliveringBookList.pageCount" :layout="deliveringBookList.layout"
        :total="deliveringBookList.recordCount" style="margin-top: 20px" />
    </el-card>

    <!-- 等待入库列表 -->
    <el-card shadow="never" class="order-item-card" v-if="isShowMemberInfo">
      <template #header>
        <div>
          <span>等待入库列表</span>
        </div>
      </template>
      <el-form ref="refStorageingBookSearchForm" :model="storageingBookList.parameters">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书名" prop="bookName">
              <el-input placeholder="请输入..." v-model="storageingBookList.parameters.bookName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书号" prop="bookSn">
              <el-input placeholder="请输入..." v-model="storageingBookList.parameters.bookSn"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click='storageingBookList.query'>搜索</el-button>
              <el-button @click='storageingBookList.resetForm(refStorageingBookSearchForm)'>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-table :data="storageingBookList.dataList" style="width: 100%">
        <el-table-column property="bookSn" label="书号" width="200">
          <template #default="scope">
            <div>
              <span>{{ scope.row.bookSn }}</span>
            </div>
            <div>
              <span>{{ scope.row.bookcaseName }}</span>
            </div>
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
          </template>
        </el-table-column>
        <el-table-column property="deliverStatusName" label="状态">
          <template #default="scope">
            <div>
              <span>{{ scope.row.deliverStatusName }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="createTimeText" label="还书时间" />
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="warning" :id="scope.row.id" @click="cancelReturned(scope.row.id)">取消还书</el-link>

              <el-link type="primary" :id="scope.row.id"
                @click="refBorrowBookDetail.init({ mode: 'add', dataItem: scope.row })">标记</el-link>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:current-page="storageingBookList.currentPage"
        v-model:page-size="storageingBookList.pageSizeComputed" :page-sizes="storageingBookList.pageSizes"
        :page-count="storageingBookList.pageCount" :layout="storageingBookList.layout"
        :total="storageingBookList.recordCount" style="margin-top: 20px" />
    </el-card>

    <!-- 会员损坏记录 -->
    <el-card shadow="never" class="book-damage-card" v-if="isShowMemberInfo">
      <template #header>
        <div>
          <span>用户损坏记录</span>
        </div>
      </template>
      <el-form ref="refBookDamageSearchForm" :model="bookDamageList.parameters">
        <el-row type="flex" :gutter="20">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书名" prop="bookName">
              <el-input placeholder="请输入..." v-model="bookDamageList.parameters.bookName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item label="书号" prop="bookSn">
              <el-input placeholder="请输入..." v-model="bookDamageList.parameters.bookSn"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <el-form-item>
              <el-button type="primary" @click='bookDamageList.query'>搜索</el-button>
              <el-button @click='bookDamageList.resetForm(refBookDamageSearchForm)'>重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-table :data="bookDamageList.dataList" style="width: 100%">
        <el-table-column property="bookSn" label="书名" width="350">
          <template #default="scope">
            <div>
              <span style="color: #008000;">{{ scope.row.bookSeriesName ? "[" + scope.row.bookSeriesName + "]" : ""
                }}</span>
              <span>{{ scope.row.bookName }}</span>
              <span style="color: #0000ff;">{{ scope.row.bindingTypeName ? "(" + scope.row.bindingTypeName + ")" : ""
                }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="bookSn" label="书号" width="200" />
        <el-table-column property="isCompensation" label="是否已赔">
          <template #default="scope">
            <div>
              <sapn>{{ scope.row.isCompensation ? "已赔" : "未赔" }}</sapn>
            </div>
          </template>
        </el-table-column>
        <el-table-column property="damageTimeText" label="损坏时间" width="200" />
        <el-table-column property="amount" label="赔付金额" />
        <el-table-column property="remark" label="备注" />
        <el-table-column label="操作" align="center" width="180">
          <template #default="scope">
            <el-space spacer="|" style="color: #999" wrap>
              <el-link type="primary" :id="scope.row.id" v-if="!scope.row.isCompensation"
                @click="refBookDamageDetail.init({ mode: 'edit', primaryKeyValue: scope.row.id })">赔付</el-link>
              <el-link type="primary" :id="scope.row.id"
                @click="refBookDamageDetail.init({ mode: 'view', primaryKeyValue: scope.row.id })">查看</el-link>
            </el-space>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:current-page="bookDamageList.currentPage"
        v-model:page-size="bookDamageList.pageSizeComputed" :page-sizes="bookDamageList.pageSizes"
        :page-count="bookDamageList.pageCount" :layout="bookDamageList.layout" :total="bookDamageList.recordCount"
        style="margin-top: 20px" />
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.user {
  .user-search-card {
    margin-bottom: 15px;

    .user-form {
      .el-form-item {
        margin-bottom: 3px;
      }
    }
  }

  .table-footer {
    display: flex;
    justify-content: center;
    width: 100%;
    text-align: center;
    margin-top: 20px;
  }


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



.in-book:last-child {
  margin-bottom: 0;
}
</style>

<style>
.member-card {
  margin-bottom: 15px;
  box-shadow: 0 0 0 1px #dff0d8;

  .el-card__header {
    background-color: #dff0d8;
  }
}

.return-book-card {
  margin-bottom: 15px;
  box-shadow: 0 0 0 1px #dff0d8;

  .el-card__header {
    background-color: #dff0d8;
  }
}

.order-item-card {
  margin-bottom: 15px;
  box-shadow: 0 0 0 1px #d9edf7;

  .el-card__header {
    background-color: #d9edf7;
  }
}


.book-damage-card {
  box-shadow: 0 0 0 1px #f5f5f5;

  .el-card__header {
    background-color: #f5f5f5;
  }
}

.in-book {
  background-color: #f2dede;
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 10px;
}

.not-borrow-book {
  background-color: #fcf8e3;
  padding: 15px;
  border-radius: 5px;
  margin-bottom: 10px;
  font-size: 14px;
}
</style>
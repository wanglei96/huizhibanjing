<script setup>
import { reactive, ref, watch } from 'vue';
import common from '@/js/common';
import BookAddOneBook from './BookAddOneBook.vue'
import BookAddList from './BookAddList.vue'
import config from '@/js/config'
//详情页开关
const detailFormVisible = ref(false)
// 声明单本添加新书标签页
const BookAddOneBookRef = ref()
const BookAddListRef = ref()
const isLoading = ref(false);
function init() {
  // 显示弹窗
  detailFormVisible.value = true
}
provide('openBookAddList', openBookAddList);

const upload = ref()

const handleExceed = (files) => {
  upload.value?.clearFiles()
  const file = files[0]
  //file.uid = genFileId()
  upload.value?.handleStart(file)
};


const submitUpload = () => {
  upload.value?.submit();
};

const beforeFileUpload = (rawFile) => {
  if (rawFile.type !== 'application/vnd.ms-excel' &&
    rawFile.type !== 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') {
    ElMessage.error('请上传Excel格式的文件!')
    return false
  }
  return true
}

const uploadExcel = async (file) => {
  console.log(file);
  common.uploadFile(file, function (result) {
    let fileId = result.data.id;
    uploadBookExcel(fileId);
  });
};
function uploadBookExcel(fileId) {
  let params = {
    uploadId: fileId
  };
  common.call('UploadBookExcel', params, (result) => {
    let dataList = result.data;
    openBookAddList({
      dataList: dataList,
      type: "excel"
    });
  })
}


const tableDataList = ref([]);
const isOn = ref(false);
function switchChange(value) {
  isOn.value = value;
}

function openBookAddList(options) {
  //BookAddListRef.value.init(options);
  if (options.dataEntity) {
    options.dataEntity.bookCount = parseInt(options.dataEntity.bookCount);
    let ISBN = options.dataEntity.ISBN
    options.dataEntity.ISBN = ISBN.toString();
    tableDataList.value.push(options.dataEntity);
  }
  if (options.dataList) {
    options.dataList.forEach(element => {
      tableDataList.value.push(element);
    });
  }
  console.log(tableDataList.value);
}

function deleteRow(row, index) {
  console.log(index);
  tableDataList.value.splice(index, 1);
}
/**
 * 上传图片
 */
const uploadImage = async (file, index) => {
  console.log(file);
  common.uploadFile(file, function (result) {
    console.log(result);
    tableDataList.value[index].imageFilePath = config.adminRootUrl + "/" + result.data.path;
    tableDataList.value[index].imageFileId = result.data.id;
  });
};

/**
 * 上传图片检查
 */
const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('请上传图片格式!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('图片大小不能超过2MB!')
    return false
  }
  return true
}
// 当前门店id
const companyId = ref(
  `${common.getLocalStorage('companyId')}`
);
/**
 * 批量上传
 */
function uploudListData() {
  // 创建一个空数组来存储存在相同 bookName 和 ISBN 的数据
  const duplicateData = [];

  // 遍历数组并检查是否存在相同的 bookName 和 ISBN 数据
  tableDataList.value.forEach((item, index) => {
    if (tableDataList.value.slice(index + 1).some(otherItem => otherItem.bookName === item.bookName && otherItem.ISBN === item.ISBN)) {
      duplicateData.push(item);
    }
  });
  if (duplicateData.length > 0) {
    const bookNames = duplicateData.map(item => `《${item.bookName}》`).join(", ");
    ElMessageBox.confirm(
      '列表中有重复的书籍名称和ISBN：' + bookNames + '，是否继续？继续将过滤重复书籍',
      '提醒',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
    )
      .then(() => {
        addListData();
      })
      .catch(() => {
      })
  } else {
    addListData();
  }

}

function addListData() {
  // 辅助对象来跟踪已经出现过的书名和ISBN组合
  const uniqueData = {};

  // 过滤重复数据，只保留其中一个
  const filteredData = tableDataList.value.filter(item => {
    const key = item.bookName + '-' + item.ISBN;
    if (!uniqueData[key]) {
      uniqueData[key] = true;
      return true;
    }
    return false;
  });

  const hasEmptyImageFileId = filteredData.every(item => item.imageFileId && item.imageFileId !== "");
  if (!hasEmptyImageFileId) {
    ElMessage({ message: "请先上传图书封面！", type: 'warning' });
    return;
  }
  isLoading.value = true
  let params = {
    dataList: filteredData,
    isOn: isOn.value,
    companyId: companyId.value
  }
  common.call("BatchAddBookSkuAndBook", params, (result) => {
    ElMessage.success("导入成功");
    setTimeout(() => {
      isLoading.value = false
    }, 500)
    detailFormVisible.value = false;
  })
}

//监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
watch(detailFormVisible, (newValue, oldValue) => {
  if (!newValue) {
    tableDataList.value = []
    upload.value.clearFiles();
  }
})
function downloadExcel() {
  window.open('https://admin.brp.rome8.com/template/newbooks.xlsx');
}
//暴露
defineExpose({
  init
})
</script>
<template>
  <div>
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible" title="批量录入图书" width="1200"
      draggable>
      <BookAddOneBook ref="BookAddOneBookRef" />
      <BookAddList ref="BookAddListRef" />
      <el-card shadow="never" class="excel-card">
        <template #header>
          <div class="card-header">
            <span>Excel批量导入图书信息</span>
          </div>
        </template>
        <div class="card-body">
          <div>新书录入步骤：</div>
          <div class="infolist">
            <span>1、请先下载SKU新书录入模板 “newbooks.xlsx” ，</span>
            <span><el-link type="danger" @click="downloadExcel()" target="_blank">点击下载</el-link></span>
            <span>，然后将信息按照要求填入模板</span>
          </div>
          <div class="infolist">
            <span>2、选择填好信息的excel表格，然后点“提交Excel”。</span>
          </div>
          <div class="infolist">
            <span>3、提交完成，确认信息无误后，挨个上传封面图，点击一键导入即可。</span>
          </div>
          <div>注意事项：</div>
          <div class="infolist">
            <span>1、除系列、年龄段外，</span>
            <span style="color: red;">其他都为必填项</span>
          </div>
          <div class="infolist">
            <span>2、书上如果没有ISBN，请翻开书的内页，或者从购买链接上查找。</span>
          </div>
          <div class="infolist">
            <span>3、封面图，请打开手机应用商店，搜索“扫描全能王 ”，使用该APP进行拍摄，拍完用微信传到电脑上，选择对应的封面图即可。</span>
          </div>
          <div>
            <el-upload ref="upload" class="upload-demo" action="uploadExcel" :limit="1" :on-exceed="handleExceed"
              :auto-upload="false" :http-request="uploadExcel" :before-upload="beforeFileUpload">
              <template #trigger>
                <el-button type="primary">选择文件</el-button>
              </template>
              <el-button type="success" @click="submitUpload" style="margin-left: 10px;">
                提交excel
              </el-button>
            </el-upload>
            <el-button type="warning" @click="BookAddOneBookRef.init({ mode: 'add' })">单本录入新书</el-button>
          </div>
        </div>
      </el-card>
      <el-card shadow="never" class="upload-list-card" style="margin-top: 10px;" v-if="tableDataList.length > 0">
        <template #header>
          <div>
            <span>待导入列表</span>
          </div>
        </template>
        <div>
          <el-button type="primary" @click="uploudListData" style="margin-right: 10px;"
            :loading="isLoading">一键导入</el-button>
          <el-switch @change="switchChange" v-model="isOn" inactive-text="是否上架" />
        </div>
        <el-table :data="tableDataList" style="width: 100%">
          <el-table-column property="avator" label="上传图片" width="200" align="center">
            <template #default="scope">
              <el-upload class="avatar-uploader" action="uploadPath" :show-file-list="false"
                :before-upload="beforeAvatarUpload" :http-request="file => uploadImage(file, scope.$index)">
                <img v-if="scope.row.imageFilePath" :src="scope.row.imageFilePath" class="avatar" />
                <el-icon v-else class="avatar-uploader-icon">
                  <Plus />
                </el-icon>
              </el-upload>
            </template>
          </el-table-column>
          <el-table-column property="bookName" label="书名&分类" align="center" width="150">
            <template #default="scope">
              <div>{{ scope.row.bookName }}</div>
              <div>{{ scope.row.languageName }}</div>
            </template>
          </el-table-column>
          <el-table-column property="ageGroupNames" label="年龄段" align="center" width="120">
            <template #default="scope">
              <div>
                <el-tag v-for="item in scope.row.ageGroupList" :key="item" type="info" size="small"
                  style="margin-left: 10px;">{{ item.name }}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column property="bookTagNames" label="标签" align="center" width="120">
            <template #default="scope">
              <div>
                <el-tag v-for="item in scope.row.bookTagNameList" :key="item" type="info" size="small"
                  style="margin-left: 10px;">{{ item }}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column property="bookSeriesName" label="系列" align="center" width="120" />
          <el-table-column property="bookCount" label="数量" align="center" width="80" />
          <el-table-column property="price" label="价格" align="center" width="80" />
          <el-table-column property="author" label="作者&出版社&备注" align="center" width="150">
            <template #default="scope">
              <div>{{ scope.row.author }}</div>
              <div>{{ scope.row.press }}</div>
              <div>{{ scope.row.bindingTypeName }}</div>
            </template>
          </el-table-column>
          <el-table-column property="ISBN" label="ISBN" width="150" align="center" />
          <el-table-column label="操作" align="center">
            <template #default="scope">
              <el-space spacer="|" style="color: #999" wrap>
                <el-link type="danger" @click="deleteRow(scope.row, scope.$index)">删除</el-link></el-space>
            </template>
          </el-table-column>
        </el-table>
        <!-- <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[10, 50, 100]"
          :small="small" :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper"
          :total="recordCount" @size-change="handleSizeChange" @current-change="handleCurrentChange"
          style="margin-top: 20px" /> -->
      </el-card>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.el-card {
  margin-bottom: 20px;
}

.singleline {
  width: 600px;
  font-size: 14px;
  color: #666;
  display: flex;
}

.el-popper.is-customized {
  /* Set padding to ensure the height is 32px */
  padding: 6px 12px;
  background: linear-gradient(90deg, rgb(159, 229, 151), rgb(204, 229, 129));
}

.el-popper.is-customized .el-popper__arrow::before {
  background: linear-gradient(45deg, #b2e68d, #bce689);
  right: 0;
}

.el-link {
  margin-right: 8px;
}

.el-link .el-icon--right.el-icon {
  vertical-align: text-bottom;
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
</style>
<style>
.excel-card {
  margin-bottom: 15px;
  box-shadow: 0 0 0 1px #dff0d8;

  .el-card__header {
    background-color: #dff0d8;
  }
}

.upload-list-card {
  margin-bottom: 15px;
  box-shadow: 0 0 0 1px #dff0d8;

  .el-card__header {
    background-color: #dff0d8;
  }
}

.avatar-uploader .avatar {
  width: 150px;
  height: 150px;
  display: block;
}

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
</style>

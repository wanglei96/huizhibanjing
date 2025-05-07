<script setup>
import common from '@/js/common'
import config from '@/js/config'
import { reactive, ref, inject } from 'vue';

const dialogFormVisible = ref(false)
//暴露
defineExpose({
  init
})
const tableDataList = ref([]);
const isOn = ref(false);
function switchChange(value) {
  isOn.value = value;
}
function init(options) {
  dialogFormVisible.value = true
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
    ElMessage.error('Avatar picture must be JPG format!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!')
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
  let params = {
    dataList: tableDataList.value,
    isOn: isOn.value,
    companyId: companyId.value
  }
  common.call("BatchAddBookSkuAndBook", params, (result) => {
    console.log(result);
    ElMessage.success("导入成功");
    dialogFormVisible.value = false;
  })
}
</script>
<template>
  <div class="user">
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" title="待导入列表" width="1200" draggable >
      <div>
        <el-button type="primary" @click="uploudListData">一键导入</el-button>
        <el-switch @change="switchChange" v-model="isOn" inactive-text="是否上架" />
      </div>
      <el-card shadow="never" class="upload-list-card" style="margin-top: 10px;">
        <el-table :data="tableDataList" style="width: 100%">
          <el-table-column property="avator" label="上传图片" width="200" align="center">
            <template #default="scope">
              <el-upload class="avatar-uploader" action="uploadPath" :show-file-list="false"
                :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload"
                :http-request="file => uploadImage(file, scope.$index)">
                <img v-if="scope.row.imageFilePath" :src="scope.row.imageFilePath" class="avatar" />
                <el-icon v-else class="avatar-uploader-icon">
                  <Plus />
                </el-icon>
              </el-upload>
            </template>
          </el-table-column>
          <el-table-column property="bookName" label="书名" align="center" />
          <el-table-column property="bookSeriesName" label="系列" align="center" />
          <el-table-column property="languageName" label="分类" align="center" />
          <el-table-column property="bookTagNames" label="年龄段" width="200">
            <template #default="scope">
              <div>
                <el-tag v-for="item in scope.row.ageGroupList" :key="item" type="info" size="small"
                  style="margin-left: 10px;">{{ item.name }}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column property="bookCount" label="数量" align="center" />
          <el-table-column property="price" label="价格" align="center" />
          <el-table-column property="author" label="作者" align="center" />
          <el-table-column property="press" label="出版社" align="center" />
          <el-table-column property="ISBN" label="ISBN" width="200" align="center" />
          <el-table-column property="bindingTypeName" label="备注" align="center" />
          <el-table-column label="操作" align="center">
            <template #default="scope">
              <el-space spacer="|" style="color: #999" wrap>
                <el-link type="danger" @click="deleteRow(scope.row, scope.$index)">删除</el-link></el-space>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[10, 50, 100]"
          :small="small" :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper"
          :total="recordCount" @size-change="handleSizeChange" @current-change="handleCurrentChange"
          style="margin-top: 20px" />
      </el-card>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

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

.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
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

<script setup>
import { reactive, ref } from 'vue'
import detail from '@/js/detail'
import management from '@/js/management'
import common from '@/js/common'
import DatabaseSelect from '@/components/extension/DatabaseSelect.vue'
import ImageUpload from '@/components/extension/ImageUpload.vue'
import ActivityDetail from '@/views/layout/activity/ActivityDetail.vue'
import config from '@/js/config'
import SelectedMember from './SelectedMember.vue'

const refSelectedMember = ref()

//定义事件发射器
const emit = defineEmits(['reload-data'])
//详情页开关
const detailFormVisible = ref(false)
//表单引用
const refDataEntityForm = ref()

//详情页组件引用
const refDetail = ref()
const refSearchForm = ref()
const refRefundOrderDetail = ref()

let page = reactive({})
let list = reactive({})
const init = (options) => {
  detailFormVisible.value = true
  page = detail({
    baseTitle: '俱乐部活动',
    tableName: 'club_activity',
    getServiceName: 'GetViewClubActivity',
    mode: options.mode,
    primaryKeyValue: options.primaryKeyValue,
    detailFormVisible,
    refDataEntityForm,
    emit,
    getDataEntity(dataEntity) {
      /* dataEntity.value.longitude = parseFloat(dataEntity.value.longitude)
      dataEntity.value.latitude = parseFloat(dataEntity.value.latitude) */
      dataEntity.value.clubActivityMemberList = clubActivityMemberList.value
    }
  })

  if (options.mode === 'edit' || options.mode === 'view') {
    list = management({
      tableName: 'club_activity_member',
      condition: `club_activity_id=${options.primaryKeyValue}`,
      orderBy: 'create_time desc',
      getListServiceName: 'GetViewClubActivityMemberList',
      refDetail
    })
  }
}

//暴露
defineExpose({
  init
})

//表单校验规则
const rules = reactive({
  name: [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
  imageFileId: [{ required: true, message: '请上传活动图片', trigger: 'change' }],
  quantity: [
    { required: true, message: '请输入活动次数', trigger: 'blur' },
    { validator: common.positiveInteger, trigger: 'blur' }
  ],
  expiryDateText: [{ required: true, message: '请选择有效期', trigger: 'change' }],
  clubActivityCategoryCode: [
    { required: true, message: '请选择俱乐部活动分类', trigger: 'change' }
  ],
  cost: [
    { required: true, message: '请输入费用', trigger: 'blur' },
    { validator: common.floatValidator, trigger: 'blur' }
  ]
})

const expiryDateText = computed({
  get() {
    return page.dataEntity.value.expiryDateText
  },
  set(val) {
    page.dataEntity.value.expiryDateText = val
    page.dataEntity.value.expiryDate = val
  }
})

/**
 * 消次
 * @param memberId
 */
const minusMemberSurplusQuantity = function (id) {
  common.call('MinusMemberSurplusQuantity', { id }, () => {
    list.loadData()
    ElMessage({ type: 'success', message: '操作成功' })
  })
}

function disabledDate(date) {
  return new Date().getTime() >= date.getTime()
}
const clubActivityMemberList = ref([]);
function addMember(memberList) {
  memberList.forEach((item) => {
    // 判断 item.id 是否在 salesOrderItemList 中已存在
    if (clubActivityMemberList.value.some(orderItem => orderItem.memberId === item.id)) {
      return; // 如果存在则跳出当前循环，执行下次循环
    }
    let cylinderOrderItem = {
      memberId: item.id,
      memberName: item.name,
      mobilePhone: item.mobilePhone,
    }
    clubActivityMemberList.value.push(cylinderOrderItem);
  })
}

</script>
<template>
  <div class="ClubActivityDetail-box">
    <SelectedMember ref="refSelectedMember" @reload-data="addMember" />
    <el-dialog destroy-on-close :close-on-click-modal="false" v-model="detailFormVisible"
      :title="page.title ? page.title.value : ''" width="1000" v-if="detailFormVisible" draggable overflow>
      <el-form :model="page.dataEntity.value" :rules="rules" ref="refDataEntityForm" :label-position="'right'"
        :disabled="page.mode === 'view'" label-width="130px">
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="活动名称" prop="name">
              <el-input v-model="page.dataEntity.value.name" class="form-item-width" placeholder="请输入活动名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="活动次数" prop="quantity">
              <el-input v-model.number="page.dataEntity.value.quantity" class="form-item-width"
                placeholder="请输入活动次数"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="报名人数限制" prop="maxApplyQuantity">
              <el-input v-model.number="page.dataEntity.value.maxApplyQuantity" class="form-item-width"
                placeholder="请输入报名人数限制"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="有效期" prop="expiryDateText">
              <el-date-picker v-model="expiryDateText" type="datetime" value-format="YYYY-MM-DD HH:mm:ss"
                placeholder="请选择有效期" style="width: 200px" :disabled-date="disabledDate" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="俱乐部活动分类" prop="clubActivityCategoryCode">
              <DatabaseSelect table-name="club_activity_category"
                v-model="page.dataEntity.value.clubActivityCategoryCode" placeholder="请选择俱乐部活动分类"
                class="form-item-width" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="报名费" prop="cost">
              <el-input v-model="page.dataEntity.value.cost" placeholder="请输入报名费" class="form-item-width"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="是否首页显示" prop="isHome">
              <el-switch v-model="page.dataEntity.value.isHome" />
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="是否首页置顶" prop="isTop">
              <el-switch v-model="page.dataEntity.value.isTop" />
            </el-form-item>
          </el-col>
          <!-- <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="经度" prop="latitude">
              <el-input v-model="page.dataEntity.value.latitude" placeholder="请输入经度" class="form-item-width"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <el-form-item label="纬度" prop="longitude">
              <el-input v-model="page.dataEntity.value.longitude" placeholder="请输入维度"
                class="form-item-width"></el-input>
            </el-form-item>
          </el-col> -->
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="地址" prop="address">
              <el-input v-model="page.dataEntity.value.address" placeholder="请输入地址" style="width: 100%"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="12" :sm="12" :md="12" :lg="12" class="upload-image-col">
            <el-form-item label="活动图片" prop="imageFileId">
              <ImageUpload v-model="page.dataEntity.value.imageFileId"
                :base-img-url="page.dataEntity.value.imageFilePath"></ImageUpload>
              <div>jpg/png文件，大小小于2MB。</div>
              <div>推荐竖版图片宽高比（10:13）</div>
            </el-form-item>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12" class="upload-image-col">
            <el-form-item label="活动详情图片" prop="detailImageFileId" label-width="auto">
              <ImageUpload v-model="page.dataEntity.value.detailImageFileId"
                :base-img-url="page.dataEntity.value.detailImageFilePath"></ImageUpload>
              <div>jpg/png文件，大小小于2MB。</div>
              <div>推荐横版图片宽高比（13:5）</div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item label="内容" prop="content">
              <vue-ueditor-wrap v-model="page.dataEntity.value.content" editor-id="editor" :config="config.editorConfig"
                :editorDependencies="['ueditor.config.js', 'ueditor.all.js']" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <!-- <el-card shadow="never" class="member-list-card">
        <template #header>
          <el-button type="primary" @click="refSelectedMember.init()">添加会员</el-button>
        </template>
<el-table ref="multipleTableRef" :data="clubActivityMemberList" style="width: 100%">
  <el-table-column property="memberName" sortable label="会员名称" />
  <el-table-column property="mobilePhone" sortable label="手机号" />
  <el-table-column align="center" width="60">
    <template #default="scope">
              <el-icon size="14" color="#a8abb2" v-if="page.mode === 'add'"
                @click="clubActivityMemberList.splice(scope.$index, 1)">
                <Delete />
              </el-icon>
            </template>
  </el-table-column>
</el-table>
</el-card> -->

      <el-card shadow="never" v-if="page.mode === 'edit' || page.mode === 'view'" class="member-list-card">
        <template #header>
          <div class="card-header">学员列表</div>
        </template>
        <div class="basic-list">
          <ActivityDetail ref="refDetail" @reload-data="list.loadData" />
          <el-card shadow="never" class="search-card" style="margin-bottom: 15px">
            <el-form ref="refSearchForm" :model="list.parameters">
              <el-row type="flex">
                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                  <el-form-item label="会员名称" prop="retailOrderName">
                    <el-input placeholder="请输入..." v-model="list.parameters.retailOrderName" clearable></el-input>
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                  <el-form-item label="时间范围" prop="dpSearchCreateTime">
                    <el-date-picker v-model="list.parameters.dpSearchCreateTime" type="daterange"
                      start-placeholder="开始时间" end-placeholder="结束时间" value-format="YYYY-MM-DD"
                      :validate-event="true" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="8" :lg="8">
                  <el-form-item>
                    <el-button type="primary" @click="list.query">搜索</el-button>
                    <el-button @click="list.resetForm(refSearchForm)">重置</el-button>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-card>
          <el-card shadow="never">
            <el-alert :title="`已选择 ${list.selectedColumnCount.value} 项`" v-show="list.selectedColumnCount.value > 0"
              type="warning" show-icon />
            <el-table ref="multipleTableRef" :data="list.dataList.value" style="width: 100%" border
              @selection-change="list.selectRow">
              <el-table-column type="selection" width="55" />
              <el-table-column property="memberName" label="姓名">
                <template #default="scope">
                  <div>{{ scope.row.retailOrderName ? scope.row.retailOrderName : scope.row.memberName }}</div>
                </template>
              </el-table-column>
              <el-table-column property="memberMobilePhone" label="会员手机号" width="130">
                <template #default="scope">
                  <div>{{ scope.row.retailOrderMobilePhone ? scope.row.retailOrderMobilePhone :
      scope.row.memberMobilePhone }}</div>
                </template>
              </el-table-column>
              <el-table-column property="memberCardCardNo" label="会员卡号" width="120" />
              <el-table-column property="joinDateText" label="加入时间" width="170" />
              <el-table-column property="surplusQuantity" label="剩余次数" width="100" />
              <el-table-column label="操作" align="center" width="160">
                <template #default="scope">
                  <el-space spacer="|" style="color: #999" wrap>
                    <el-link type="success" :id="scope.row.id" @click="minusMemberSurplusQuantity(scope.row.id)">消次
                    </el-link>
                    <el-link type="danger" @click="list.deleteRow(scope.row.id)">删除</el-link>
                  </el-space>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination v-model:current-page="list.currentPage.value" v-model:page-size="list.pageSizeComputed.value"
              :page-sizes="list.pageSizes" :page-count="list.pageCount.value" :layout="list.layout"
              :total="list.recordCount.value" style="margin-top: 20px" />
          </el-card>
        </div>
      </el-card>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" v-if="page.mode === 'add' || page.mode === 'edit'" :loading="page.isLoading.value"
            @click="page.submitForm()">
            确定
          </el-button>
          <el-button @click="detailFormVisible = false">取消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.form-item-width {
  width: 200px !important;
}

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

.member-list-card {
  .el-form-item {
    margin-bottom: 0;
  }
}

:deep(.upload-image-col) {
  .el-form-item__content {
    display: flex;
    flex-wrap: wrap;
    flex-direction: column;
    align-items: flex-start;
    color: #aaaaaa;
  }
}
</style>
<style lang="scss">
.ClubActivityDetail-box {
  .edui-editor-iframeholder {
    width: 100% !important;
  }

  .edui-editor {
    width: 100% !important;
  }
}
</style>
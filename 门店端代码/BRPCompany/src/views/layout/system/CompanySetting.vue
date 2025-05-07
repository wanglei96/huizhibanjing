<script setup>
import { reactive, ref } from 'vue'
import common from '@/js/common'
import config from '@/js/config'
import { ElMessage } from 'element-plus'
import TianHolidayView from './TianHolidayView.vue'

//表单
const refDataEntityForm = ref()

const refTianHolidayView = ref()

const dataEntity = ref({})

const getCompanySetting = function () {
  common.call(
    'GetViewCompanySettingList',
    { condition: `company_id=${localStorage.getItem('companyId')}`, pageSize: 9999 },
    (res) => {
      res.data.dataList.forEach((item) => {
        if ('CheckBoxList' === item.sysUiControlCode) {
          //复选列表
          if (item.value) {
            dataEntity.value[item.sysSettingCode] = item.value.split(',').map(Number)
          }
        } else if ('CheckBox' === item.sysUiControlCode) {
          //复选框
          if (item.value) {
            dataEntity.value[item.sysSettingCode] = item.value.split(',')
          }
        } else if ('DateTimeBox' === item.sysUiControlCode) {
          //日期时间框
          if (item.value) {
            dataEntity.value[item.sysSettingCode] = item.value.split(',')
          }
        } else if ('NumericBox' === item.sysUiControlCode) {
          //数字框
          dataEntity.value[item.sysSettingCode] = parseInt(item.value)
        } else {
          dataEntity.value[item.sysSettingCode] = item.value
        }
      })
    }
  )
}
getCompanySetting()
//获取门店营业状态
getCompanyOpenStatus()

function getCompanyOpenStatus() {
  let params = {
    companyId: localStorage.getItem('companyId')
  }
  common.call('GetCompanyOpenStatus', params, (res) => {
    dataEntity.value['companyOpenStatus'] = res.data
  })
}

//保存配送规则配置
function saveSetting(codeArr) {
  let data = { companySettingInfoList: [] }
  codeArr.forEach((item) => {
    let settingObj = {
      companyId: parseInt(localStorage.getItem('companyId')),
      sysSettingCode: item
    }
    if (typeof dataEntity.value[item] === 'object' || typeof dataEntity.value[item] === 'boolean') {
      if (Array.isArray(dataEntity.value[item])) {
        settingObj.value = dataEntity.value[item].join()
      } else {
        settingObj.value = JSON.stringify(dataEntity.value[item])
      }
    } else if (typeof dataEntity.value[item] === 'number') {
      settingObj.value = dataEntity.value[item] + ''
    } else {
      settingObj.value = dataEntity.value[item]
    }
    data.companySettingInfoList.push(settingObj)
  })
  common.call('UpdateCompanySettingInfo', data, (res) => {
    ElMessage({ message: '已保存', type: 'success' })
    getCompanyOpenStatus();
  })
}

//表单校验规则
const rules = reactive({})

</script>
<template>
  <div class="basic-list">
    <TianHolidayView ref="refTianHolidayView" />
    <el-form :model="dataEntity" :rules="rules" ref="refDataEntityForm" :label-position="'right'" label-width="180"
      style="display: flex; flex-direction: column; row-gap: 15px">
      <el-card class="setting-box">
        <template #header> 线上借书暂停配置</template>
        <div class="card-body">
          <div style="color: #f00000; font-size: 14px">
            该设置影响会员借书和配送单处理，修改前一定要慎重！建议固定下来不要经常修改。
          </div>
          <div class="rule-box">
            <div class="title">模式介绍：</div>
            <div>每周：每周的星期几线上借书暂停，例如选择周一，那就是每周一线上借书暂停。</div>
            <div>
              临时：用于假期或者其他特殊情况要暂停线上借书的场景，例如：国庆节线上借书暂停或者店里装修需要暂停几天。
            </div>
            <div>
              法定节假日：用于法定节假日自动闭馆。
            </div>
          </div>
          <div class="rule-box">
            <div class="title">设置介绍：</div>
            <div>每周：可以选择一天或者多天，或者不暂停。当天无法设置暂停或者取消暂停。</div>
            <div>
              临时：选择一个时间段，该时段内会员无法在线上借书。例如：要设置10月1日到10月8日线上借书暂停，10月9日开始正常配送，则开始时间设置为：2020-10-01，结束时间为：2020-10-08，10月9号0:00恢复借阅配送。
            </div>
            <div>法定节假日：勾选之后，法定节假日将自动闭馆。点击“查看节假日信息”可查看当前年度的节假日安排。</div>
            <div class="prompt">模式切换时，请确认当日的订单已经处理完了！</div>
          </div>
          <div class="rule-box">
            <div class="title">静默开启借书功能介绍：</div>
            <div>该项勾选后，会员可在暂停时间段内线上借书。</div>
            <div>
              该功能可以有效缓解暂停后第一天爆单的情况，例如：周一不能借书，周二借书的会员就会暴增，以至于周三出现爆单。
            </div>
            <div>
              开启该功能后，请留意配送单的单量，等到单量饱和时，取消勾选保存配置，就会恢复不可借阅的状态。
            </div>
            <div class="prompt">
              该功能勾选后，请勿随意修改线上借书暂停时间，否则会出现订单查询不到的问题。
            </div>
          </div>
          <el-row :gutter="15">
            <el-col>
              <el-form-item label="书馆当前状态:" prop="companyOpenStatus">
                <span :style="{ color: dataEntity.companyOpenStatus ? '#67C23A' : '#F56C6C' }">{{
      dataEntity.companyOpenStatus ?
        '开放中' : '闭馆中' }}</span>
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item label="选择模式：" prop="PauseMode">
                <el-checkbox-group v-model="dataEntity.PauseMode">
                  <el-checkbox label="Weekly">每周</el-checkbox>
                  <el-checkbox label="Temp">临时</el-checkbox>
                  <el-checkbox label="Holiday">法定节假日</el-checkbox>
                </el-checkbox-group>
                <el-link style="margin-left: 10px;" type="danger" @click="refTianHolidayView.init()">查看节假日信息</el-link>
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item label="每周闭馆时间设置：" prop="PauseDayArr">
                <el-checkbox-group v-model="dataEntity.PauseDayArr">
                  <el-checkbox :label="2">周一</el-checkbox>
                  <el-checkbox :label="3">周二</el-checkbox>
                  <el-checkbox :label="4">周三</el-checkbox>
                  <el-checkbox :label="5">周四</el-checkbox>
                  <el-checkbox :label="6">周五</el-checkbox>
                  <el-checkbox :label="7">周六</el-checkbox>
                  <el-checkbox :label="1">周日</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item label="临时闭馆日期设置：" prop="PauseDayArr" style="width: 800px;">
                <el-date-picker v-model="dataEntity.PauseDateRangeArr" type="daterange" start-placeholder="开始日期"
                  end-placeholder="结束日期" value-format="YYYY-MM-DD" :validate-event="true" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item label="每周闭馆借书时提示语：" prop="BorrowingPrompt">
                <el-input v-model="dataEntity.BorrowingPrompt" style="width: 100%"></el-input>
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item label="临时闭馆借书时提示语:" prop="	TempBorrowingPrompt">
                <el-input v-model="dataEntity.TempBorrowingPrompt" style="width: 100%"></el-input>
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item label="法定节假日借书时提示语：" prop="HolidayBorrowingPrompt">
                <el-input v-model="dataEntity.HolidayBorrowingPrompt" style="width: 100%"></el-input>
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item label="暂停期间，静默开启借书：" prop="SilentBorrowing">
                <el-switch v-model="dataEntity.SilentBorrowing" active-value="true" inactive-value="false" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-button @click="
      saveSetting([
        'PauseMode',
        'PauseDayArr',
        'PauseDateRangeArr',
        'BorrowingPrompt',
        'SilentBorrowing',
        'TempBorrowingPrompt',
        'HolidayBorrowingPrompt'
      ])
      ">
                保存线上借书配置
              </el-button>
            </el-col>
          </el-row>
        </div>
      </el-card>
      <!--      <el-card class="setting-box">
              <div class="card-body">
                <div class="rule-box">
                  <div class="title">会员名下等待入库图书上限设置</div>
                  <div>默认为1倍</div>
                  <div>例如：会员可解数量为3本。</div>
                  <div>
                    等待入库图书上限设置1倍：会员可以还3本书，如果再借3本书，如果这3本书不还则无法再点还书，无法借书。
                  </div>
                  <div>等待入库图书上限设置为2倍：会员有3本书未还时，还可以再还3本书，借3本书。</div>
                  <div>此设置用于用户未还书被锁定账户后，联系馆里解锁后，可以再次借书。</div>
                </div>
                <el-row :gutter="15">
                  <el-col>
                    <el-form-item prop="BorrowingPrompt" label-width="0">
                      <el-select
                        v-model="dataEntity.BorrowableQuantityMultiple"
                        class="m-2"
                        style="width: 100%"
                      >
                        <el-option label="可借数量的1倍" value="1" />
                        <el-option label="可借数量的2倍" value="2" />
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col>
                    <el-button @click="saveSetting(['BorrowableQuantityMultiple'])"
                      >保存待入库图书上限设置
                    </el-button>
                  </el-col>
                </el-row>
              </div>
            </el-card>-->
      <el-card class="setting-box">
        <div class="card-body">
          <el-row :gutter="15">
            <el-col>
              <el-form-item prop="BorrowingPrompt" label-width="0">
                <div>阅读积分</div>
                <vue-ueditor-wrap editor-id="ueditor1" v-model="dataEntity.DeliveryRules" :config="config.editorConfig"
                  :editorDependencies="['ueditor.config.js', 'ueditor.all.js']" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-button @click="saveSetting(['DeliveryRules'])">保存阅读积分</el-button>
            </el-col>
          </el-row>
        </div>
      </el-card>
      <el-card class="setting-box">
        <div class="card-body">
          <el-row :gutter="15">
            <el-col>
              <el-form-item prop="BorrowingPrompt" label-width="0">
                <div>书馆介绍</div>
                <vue-ueditor-wrap editor-id="ueditor2" v-model="dataEntity.BusinessHoursConfiguration"
                  :config="config.editorConfig" :editorDependencies="['ueditor.config.js', 'ueditor.all.js']" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-button @click="saveSetting(['BusinessHoursConfiguration'])">保存书馆介绍
              </el-button>
            </el-col>
          </el-row>
        </div>
      </el-card>
      <el-card class="setting-box">
        <div class="card-body">
          <el-row :gutter="15">
            <el-col>
              <el-form-item prop="BorrowingPrompt" label-width="0">
                <div>配送说明</div>
                <vue-ueditor-wrap editor-id="ueditor3" v-model="dataEntity.DeliverExplain"
                  :config="config.editorConfig" :editorDependencies="['ueditor.config.js', 'ueditor.all.js']" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-button @click="saveSetting(['DeliverExplain'])">保存配送说明
              </el-button>
            </el-col>
          </el-row>
        </div>
      </el-card>
      <el-card class="setting-box">
        <div class="card-body">
          <el-row :gutter="15">
            <el-col>
              <el-form-item prop="ReturnButNotBorrowed" label="只还未借是否配送：" label-width="auto">
                <el-switch v-model="dataEntity.ReturnButNotBorrowed" active-value="true" inactive-value="false" />
              </el-form-item>
            </el-col>
            <!-- <el-col>
              <el-form-item prop="CloseRange" label="近距离范围（公里）：" label-width="auto">
                <el-input-number v-model="dataEntity.CloseRange" :min="0" :max="999" />
              </el-form-item>
            </el-col> -->
            <el-col>
              <el-form-item prop="LongDistanceRrderDeliveryTime" label="远距离订单配送时间：" label-width="auto">
                <el-select v-model="dataEntity.LongDistanceRrderDeliveryTime" filterable>
                  <el-option label="周一" value="1" />
                  <el-option label="周二" value="2" />
                  <el-option label="周三" value="3" />
                  <el-option label="周四" value="4" />
                  <el-option label="周五" value="5" />
                  <el-option label="周六" value="6" />
                  <el-option label="周日" value="7" />
                </el-select>
              </el-form-item>
            </el-col>
            <!-- <el-col>
              <el-form-item prop="NearbyOrderBookCount" label="近距离订单可配送图书数量：" label-width="auto">
                <el-input-number v-model="dataEntity.NearbyOrderBookCount" :min="0" :max="999" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item prop="FarawayOrderBookCount" label="远距离订单可配送图书数量：" label-width="auto">
                <el-input-number v-model="dataEntity.FarawayOrderBookCount" :min="0" :max="999" />
              </el-form-item>
            </el-col> -->
            <el-col>
              <el-button @click="
      saveSetting([
        'ReturnButNotBorrowed',
        'CloseRange',
        'LongDistanceRrderDeliveryTime',
        'NearbyOrderBookCount',
        'FarawayOrderBookCount'
      ])
      ">保存配置
              </el-button>
            </el-col>
          </el-row>
        </div>
      </el-card>
      <el-card class="setting-box">
        <template #header> 新书首页展示配置</template>
        <div class="card-body">
          <el-row :gutter="15">
            <el-col>
              <el-form-item label="选择模式：" prop="HomeBookShowMode">
                <el-radio-group v-model="dataEntity.HomeBookShowMode">
                  <el-radio label="Automatic">自动</el-radio>
                  <el-radio label="Manual">手动</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item prop="HomeBookShowQuantity" label="首页新书上架显示数量：" label-width="auto">
                <el-input-number v-model="dataEntity.HomeBookShowQuantity" :min="0" :max="999" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item prop="HomeBookShowIsAvailable" label="首页新书上架是否显示不可借：" label-width="auto">
                <el-switch v-model="dataEntity.HomeBookShowIsAvailable" active-value="true" inactive-value="false" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-button @click="
      saveSetting([
        'HomeBookShowMode',
        'HomeBookShowQuantity',
        'HomeBookShowIsAvailable'
      ])
      ">保存配置
              </el-button>
            </el-col>
          </el-row>
        </div>
      </el-card>
      <!-- <el-card class="setting-box">
        <template #header> 条码打印设置</template>
        <div class="card-body">
          <el-row :gutter="15">
            <el-col>
              <el-form-item prop="ImageCodeHeight" label="图片高度：" label-width="auto">
                <el-input-number v-model="dataEntity.ImageCodeHeight" :min="0" :max="999" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item prop="ImageCodeWidth" label="图片宽度：" label-width="auto">
                <el-input-number v-model="dataEntity.ImageCodeWidth" :min="0" :max="999" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item prop="BarCodeHeight" label="条码高度：" label-width="auto">
                <el-input-number v-model="dataEntity.BarCodeHeight" :min="0" :max="999" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item prop="BarCodeWidth" label="条码宽度：" label-width="auto">
                <el-input-number v-model="dataEntity.BarCodeWidth" :min="0" :max="999" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item prop="BarCodeSize" label="文字大小：" label-width="auto">
                <el-input-number v-model="dataEntity.BarCodeSize" :min="0" :max="999" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item prop="HorizontalSpacing" label="水平间距：" label-width="auto">
                <el-input-number v-model="dataEntity.HorizontalSpacing" :min="0" :max="999" />
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item prop="VerticalSpacing" label="垂直间距：" label-width="auto">
                <el-input-number v-model="dataEntity.VerticalSpacing" :min="0" :max="999" />
                </el-form-item>
            </el-col>
            <el-col>
              <el-button @click="
      saveSetting([
        'ImageCodeHeight',
        'ImageCodeWidth',
        'BarCodeHeight',
        'BarCodeWidth',
        'HorizontalSpacing',
        'VerticalSpacing',
        'BarCodeSize'
      ])
      ">保存配置
              </el-button>
            </el-col>
          </el-row>
        </div>
      </el-card> -->
    </el-form>
  </div>
</template>

<style lang="scss" scoped>
.el-input {
  width: 200px;
}

.el-form {
  justify-content: center;
}

.el-col {
  margin: 0 auto;
}

:deep(.el-form-item__label) {
  font-size: 14px;
}

:deep(.rule-box) {
  font-size: 14px;

  .title {
    font-size: 16px;
    font-weight: 600;
    margin-bottom: 2px;
  }

  .prompt {
    color: #f00000;
  }
}

:deep(.setting-box .el-card__header) {
  background-color: #d9edf6;
  color: #124a69;
}

:deep(.edui-default) {
  .edui-editor {
    width: 100% !important;
  }

  .edui-editor>div {
    width: 100% !important;
  }
}

:deep(.card-body) {
  display: flex;
  flex-direction: column;
  row-gap: 15px;
}
</style>
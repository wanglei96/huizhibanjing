<template>
	<div>
		<el-dialog destroy-on-close :close-on-click-modal="false" v-model="dialogFormVisible" :title="msg" width="700"
			draggable overflow>
			<el-form ref="MemberCardFormRef" :model="MemberCardForm" :rules="rules" label-width="130px"
				class="demo-MemberCardForm" :size="formSize" status-icon>
				<el-row>
					<el-col :xs="24" :sm="24" :md="24" :lg="24">
						<el-form-item style="background-color: #fcf8e3; color: #95886d">
							<p style="margin-left: -50px">
								温馨提示：联系方式可以选择未绑卡的会员信息，否则将会创建新的会员信息；
							</p>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="联系方式" prop="mobilePhone">
							<el-autocomplete v-model="MemberCardForm.mobilePhone" :fetch-suggestions="querySearch"
								clearable placeholder="请输入联系方式" @select="handleSelect" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="出生日期" prop="birthdayText">
							<el-date-picker v-model="MemberCardForm.birthdayText" value-format="YYYY-MM-DD HH:mm:ss"
								type="date" placeholder="请选择出生日期" :size="size" />
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="会员姓名" prop="memberName">
							<el-input v-model="MemberCardForm.memberName" placeholder="请输入会员姓名" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="性别" prop="gender">
							<el-radio-group v-model="MemberCardForm.gender">
								<el-radio :label="true" size="1">男</el-radio>
								<el-radio :label="false" size="0">女</el-radio>
							</el-radio-group>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="学校" prop="school">
							<el-input v-model="MemberCardForm.school" placeholder="请输入学校" />
						</el-form-item>
					</el-col>

					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="年级" prop="gradeId">
							<DatabaseSelect table-name="grade" :condition="'is_deleted is not true'"
								v-model="MemberCardForm.gradeId" :order="'display_order'" valueFieldName="id"
								placeholder="请选择年级" />
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="门禁" prop="entranceGuard">
							<el-input v-model="MemberCardForm.entranceGuard" placeholder="请输入门禁" />
						</el-form-item>
					</el-col>

					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="班级" prop="classId">
							<DatabaseSelect table-name="classes" v-model="MemberCardForm.classId"
								:order="'display_order'" valueFieldName="id" :condition="'is_deleted is not true'"
								placeholder="请选择班级" />
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="配送大区" prop="deliverAreaId">
							<DatabaseSelect table-name="deliver_area" :condition="'company_id=' + companyId"
								@change="deliverAreaChange" v-model="MemberCardForm.deliverAreaId" valueFieldName="id"
								placeholder="请选择配送大区" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="配送线路" prop="deliverLineId">
							<DatabaseSelect table-name="view_deliver_line" :condition="'company_id=' + companyId"
								@change="deliverLineChange" v-model="MemberCardForm.deliverLineId" valueFieldName="id"
								placeholder="请选择配送线路" />
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="配送点" prop="deliverPlaceId">
							<DatabaseSelect table-name="view_deliver_place" :condition="'company_id=' + companyId"
								@change="deliverPlaceChange" v-model="MemberCardForm.deliverPlaceId" valueFieldName="id"
								placeholder="请选择配送点" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="地址" prop="address">
							<el-input v-model="MemberCardForm.address" placeholder="请输入地址" />
						</el-form-item>
					</el-col>
					<!-- <el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="配送员" prop="delivererId">
							<DatabaseSelect table-name="view_company_user"
								:condition="'company_id=' + companyId + ' and is_deliverer = 1 '"
								disabled v-model="MemberCardForm.delivererId" valueFieldName="id" placeholder="请选择配送员" />
						</el-form-item>
					</el-col> -->
				</el-row>

				<!-- <el-row>
					<el-col :xs="24" :sm="24" :md="24" :lg="24">
						<el-form-item label="定位" prop="location">
							<el-input readonly v-model="MemberCardForm.location" placeholder="请点击选择定位"
								@click="locationChange" />
						</el-form-item>
					</el-col>
				</el-row> -->

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="距离" prop="distanceCode">
							<DatabaseSelect table-name="distance" v-model="MemberCardForm.distanceCode"
								placeholder="请选择距离" :condition="'is_deleted is not true'" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="支付方式" prop="paymentMethodCode">
							<DatabaseSelect table-name="payment_method" v-model="MemberCardForm.paymentMethodCode"
								placeholder="请选择支付方式" :condition="'is_deleted is not true'" />
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="推荐人" prop="referrerId">
							<DatabaseSelect table-name="member"
								:condition="'is_sign is not true and company_id=' + companyId"
								v-model="MemberCardForm.referrerId" valueFieldName="id" placeholder="请选择推荐人" />
						</el-form-item>
					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="办理人" prop="employeeId">
							<DatabaseSelect table-name="company_user"
								:condition="'is_deleted is not true and  company_id=' + companyId"
								v-model="MemberCardForm.employeeId" valueFieldName="id" placeholder="请选择办理人" />
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="会员卡类型" prop="memberCardTypeId">
							<DatabaseSelect table-name="member_card_type" :condition="'company_id=' + companyId"
								@change="memberCardTypeChange" v-model="MemberCardForm.memberCardTypeId"
								valueFieldName="id" placeholder="请选择会员卡类型" />
						</el-form-item>
					</el-col>

					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<span style="margin-left: 20px">会员费:{{ MemberCardForm.membershipAmount }}</span>
						&nbsp;&nbsp;
						<el-checkbox v-model="MemberCardForm.isBookboxAmount" @change="isBookboxAmountChange">
							<span>书箱费:{{ MemberCardForm.bookboxAmount }}</span>
						</el-checkbox>
						<el-checkbox v-model="MemberCardForm.isSecurityDepositPrice">
							<span>押金:{{ MemberCardForm.securityDepositPrice }}</span>
						</el-checkbox>
					</el-col>
				</el-row>
				<el-row>
					<el-col :xs="24" :sm="24" :md="24" :lg="24">
						<el-form-item label="书箱位置" prop="bookboxPlace">
							<el-input v-model="MemberCardForm.bookboxPlace" />
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<!-- <el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="是否特殊会员" prop="isSpecial">
							<el-radio-group v-model="MemberCardForm.isSpecial">
								<el-radio :label="true" size="1">是</el-radio>
								<el-radio :label="false" size="0">否</el-radio>
							</el-radio-group>
						</el-form-item>
					</el-col> -->
					<el-col :xs="24" :sm="24" :md="24" :lg="24">
						<el-form-item label="会员卡总天数" prop="days">
							<el-input v-model="MemberCardForm.days" disabled />
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<!-- datetimerange -->
						<!-- <el-form-item label="会员卡日期">
							<el-date-picker v-model="MemberCardForm.memberCardDate" type="daterange"
								value-format="YYYY-MM-DD HH:mm:ss" start-placeholder="会员卡生效日期"
								end-placeholder="会员卡失效日期" :disabled-date="disabledDate"/>
						</el-form-item> -->
						<el-form-item label="会员卡生效日期">
							<el-date-picker v-model="MemberCardForm.effectiveDateText"
								value-format="YYYY-MM-DD HH:mm:ss" type="date" placeholder="请选择开始日期" :size="size"
								:disabled-date="disabledDate" @change="handleApplyTimeChange" />
						</el-form-item>

					</el-col>
					<el-col :xs="12" :sm="12" :md="12" :lg="12">
						<el-form-item label="会员卡失效日期">
							<el-date-picker v-model="MemberCardForm.expireDateText" value-format="YYYY-MM-DD HH:mm:ss"
								type="date" placeholder="请选择失效日期" :size="size" />
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :xs="24" :sm="24" :md="24" :lg="24">
						<el-form-item label="推荐人备注" prop="referrerRemark">
							<el-input v-model="MemberCardForm.referrerRemark" type="textarea" placeholder="请输入推荐人备注" />
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :xs="24" :sm="24" :md="24" :lg="24">
						<el-form-item label="备注" prop="remark">
							<el-input v-model="MemberCardForm.remark" type="textarea" placeholder="请输入备注" />
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="submitForm(MemberCardFormRef)"> 确定 </el-button>
					<el-button @click="dialogFormVisible = false">取消</el-button>
				</span>
			</template>
		</el-dialog>
	</div>

	<el-dialog destroy-on-close v-model="dialogBaiduMap" :title="locationMsg" width="50%" draggable>
		<el-row>
			<el-col :xs="6" :sm="6" :md="6" :lg="6">
				<el-input v-model.number="center.lng" />
			</el-col>
			<el-col :xs="6" :sm="6" :md="6" :lg="6">
				<el-input v-model.number="center.lat" />
			</el-col>
			<el-col :xs="6" :sm="6" :md="6" :lg="6">
				<el-input v-model.number="zoom" />
			</el-col>
			<el-col :xs="6" :sm="6" :md="6" :lg="6">
				<el-button @click="locationChangeHidden">确认</el-button>
			</el-col>
		</el-row>
		<baidu-map class="map" :scroll-wheel-zoom="true" :center="center" :zoom="zoom" @moving="syncCenterAndZoom"
			@moveend="syncCenterAndZoom" @zoomend="syncCenterAndZoom">
			<!-- 缩放控件-->
			<bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
			<!-- 比例尺 -->
			<bm-scale anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-scale>
			<!-- 地图类型 分别是二维地图和卫星地图-->
			<!-- <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']" ></bm-map-type>   -->
			<!-- 缩略图-->
			<bm-overview-map anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :isOpen="true"></bm-overview-map>
			<!-- 版权-->
			<!--   <bm-copyright anchor="BMAP_ANCHOR_TOP_RIGHT" :copyright="[{ id: 1, content: '<a>图书管理系统</a>' },]"></bm-copyright> -->
			<!--  城市列表-选择城市搜索城市(支持市级以上搜索)-->
			<bm-city-list anchor="BMAP_ANCHOR_TOP_LEFT"></bm-city-list>
			<!--全景控件 -->
			<!-- <bm-panorama></bm-panorama> -->
			<!-- 覆盖物-点-->
			<bm-marker :position="{ lng: center.lng, lat: center.lat }" :dragging="true"></bm-marker>
		</baidu-map>
	</el-dialog>
</template>

<script lang="ts" setup>
import {
	inject,
	reactive,
	ref,
	nextTick,
	onMounted
} from 'vue'
import type {
	FormInstance,
	FormRules,
	ElMessage
} from 'element-plus'
import common from '@/js/common'
import util from '@/js/util'

const dialogFormVisible = ref(false);
const msg = ref('绑定会员信息');
const dialogBaiduMap = ref(false);
const locationMsg = ref('定位信息');
const refreshList = inject("getDataList");
const formSize = ref('default');
const companyId = common.getLocalStorage('companyId');
const companyUserId = common.getLocalStorage('companyUserId');

const MemberCardFormRef = ref<FormInstance>()
const MemberCardForm = reactive({
	id: '',
	companyId: companyId,
	companyUserId: companyUserId,
	memberId: '',
	memberName: '',
	birthday: '',
	birthdayText: '',
	mobilePhone: '',
	gender: '',
	classId: '',
	school: '',
	gradeId: '',
	entranceGuard: '',
	deliverAreaId: '',
	deliverLineId: '',
	address: '',
	delivererId: '',
	longitude: '',
	latitude: '',
	deliverPlaceId: '',
	distanceCode: '',
	referrerId: '',
	referrerRemark: '',
	memberCardTypeId: '',
	paymentMethodCode: '',
	paymentMethodName: '',
	membershipAmount: '',
	bookboxAmount: '',
	isBookboxAmount: '',
	employeeId: '',
	days: '',
	effectiveDate: '',
	expireDate: '',
	effectiveDateText: '',
	expireDateText: '',
	memberCardDate: [],
	isSpecial: '',
	remark: '',
	createTime: '',
	updateTime: '',
	location: '',
	bookboxNo: '',
	securityDepositPrice: '',
	isSecurityDepositPrice: '',
	bookboxPlace: ''
})

const MemberCardFormInit = reactive({
	id: '',
	companyId: companyId,
	companyUserId: companyUserId,
	memberId: '',
	memberName: '',
	birthday: '',
	birthdayText: '',
	mobilePhone: '',
	gender: '',
	classId: '',
	school: '',
	gradeId: '',
	entranceGuard: '',
	deliverAreaId: '',
	deliverLineId: '',
	address: '',
	delivererId: '',
	longitude: '',
	latitude: '',
	deliverPlaceId: '',
	distanceCode: '',
	referrerId: '',
	referrerRemark: '',
	memberCardTypeId: '',
	paymentMethodCode: '',
	paymentMethodName: '',
	membershipAmount: '',
	bookboxAmount: '',
	isBookboxAmount: '',
	employeeId: '',
	days: '',
	effectiveDate: '',
	expireDate: '',
	effectiveDateText: '',
	expireDateText: '',
	memberCardDate: [],
	isSpecial: '',
	remark: '',
	createTime: '',
	updateTime: '',
	location: '',
	bookboxNo: '',
	securityDepositPrice: '',
	bookboxPlace: ''
})
const rules = reactive<FormRules>({
	memberName: [{
		required: true,
		message: '会员姓名不允许为空',
		trigger: 'change'
	}],
	/* birthdayText: [{
		required: true,
		message: '出生日期不允许为空',
		trigger: 'change'
	}], */
	gender: [{
		required: true,
		message: '性别不允许为空',
		trigger: 'change'
	}],
	employeeId: [{
		required: true,
		message: '办理人不允许为空',
		trigger: 'change'
	}],
	memberCardTypeId: [{
		required: true,
		message: '会员卡类型不允许为空',
		trigger: 'change'
	}],
	mobilePhone: [{
		required: true,
		message: '联系方式不允许为空',
		trigger: 'change'
	},
	{
		pattern: /^1[3456789]\d{9}$/,
		message: "请输入正确的联系方式",
		trigger: "change",
	}
	],
	/* school: [{
		required: true,
		message: '学校不允许为空',
		trigger: 'change'
	}], */
	/* gradeId: [{
		required: true,
		message: '年级不允许为空',
		trigger: 'change'
	}],
	classId: [{
		required: true,
		message: '班级不允许为空',
		trigger: 'change'
	}], */
	address: [{
		required: false,
		message: '地址不允许为空',
		trigger: 'change'
	}],
	deliverAreaId: [{
		required: false,
		message: '配送大区不允许为空',
		trigger: 'change'
	}],
	deliverLineId: [{
		required: false,
		message: '配送线路不允许为空',
		trigger: 'change'
	}],
	deliverPlaceId: [{
		required: false,
		message: '配送点不允许为空',
		trigger: 'change'
	}],
	delivererId: [{
		required: false,
		message: '配送员不允许为空',
		trigger: 'change'
	}],
	distanceCode: [{
		required: false,
		message: '距离不允许为空',
		trigger: 'change'
	}],
	paymentMethodCode: [{
		required: true,
		message: '支付方式不允许为空',
		trigger: 'change'
	}]
})

const submitForm = async (formEl: FormInstance | undefined) => {
	if (!formEl) return
	await formEl.validate((valid, fields) => {
		if (valid) {
			/* if (MemberCardForm.memberCardDate.length != 0) {
				MemberCardForm.effectiveDateText = MemberCardForm.memberCardDate[0]
				MemberCardForm.expireDateText = MemberCardForm.memberCardDate[1]
			} */
			let data = filterNullProperties(MemberCardForm)
			common.call(
				'BindMemberInfo',
				data,
				function (result) {
					dialogFormVisible.value = false
					refreshList()
				}
			)
		}
	})
}

/**
	* 过滤掉对象中值为null的属性并返回一个新的对象
	* @param data
	* @returns {{}}
	*/
function filterNullProperties(data) {
	const filteredData = Object.entries(data).reduce((acc, [key, value]) => {
		// 如果值不为null，则保留这个属性
		if (value !== null) {
			acc[key] = value
		}
		return acc
	}, {})
	return filteredData
}

interface RestaurantItem {
	value: String
	name: string
	memberId: String
	birthdayText: string
	mobilePhone: string
	gender: string
	school: string
	gradeId: string
	classId: string
	referrer: string
	address: string
}

let timeout: ReturnType<typeof setTimeout>
const querySearch = (queryString: string, cb: any) => {
	// call callback function to return suggestions
	if (queryString == 'null') {
		queryString = ''
	}
	common.call(
		'GetViewMemberList', {
		companyId: companyId,
		mobilePhone: queryString,
		//isSign: 1,
		pageSize: 1000,
		condition: 'member_card_card_no is null'
	},
		function (result) {
			const results = []
			var data = result.data.dataList
			for (var i = 0; i < data.length; i++) {
				var obj = {
					value: data[i].mobilePhone,
					name: data[i].name,
					memberId: data[i].id,
					birthdayText: data[i].birthdayText,
					mobilePhone: data[i].mobilePhone,
					gender: data[i].gender,
					school: data[i].school,
					gradeId: data[i].gradeId,
					classId: data[i].classId,
					referrer: data[i].referrer,
					address: data[i].address,
				}
				results.push(obj)
			}
			clearTimeout(timeout)
			timeout = setTimeout(() => {
				cb(results)
			}, 1000 * Math.random())
		}
	)
}

const handleSelect = (item: RestaurantItem) => {
	MemberCardForm.memberId = item.memberId
	MemberCardForm.memberName = item.name
	MemberCardForm.birthdayText = item.birthdayText
	MemberCardForm.mobilePhone = item.mobilePhone
	MemberCardForm.gender = item.gender
	MemberCardForm.school = item.school
	MemberCardForm.gradeId = item.gradeId
	MemberCardForm.classId = item.classId
	MemberCardForm.referrerRemark = item.referrer
	MemberCardForm.address = item.address
}

// 显示百度地图弹窗
function locationChange() {
	dialogBaiduMap.value = true
}

// 隐藏百度地图弹窗
function locationChangeHidden() {
	dialogBaiduMap.value = false
}

const center = ref({
	lng: 116.404,
	lat: 39.928
})
const zoom = ref(15)
const syncCenterAndZoom = (e) => {
	const {
		lng,
		lat
	} = e.target.getCenter()
	zoom.value = e.target.getZoom()
	nextTick(() => {
		center.value.lng = lng
		center.value.lat = lat
		MemberCardForm.longitude = lng
		MemberCardForm.latitude = lat
		MemberCardForm.location = lng + ',' + lat
	})
}

// 获取所选会员卡类型的会员费 书箱费 总天数
function memberCardTypeChange(memberCardTypeId) {
	common.call(
		'GetMemberCardType', {
		id: memberCardTypeId
	},
		function (result) {
			MemberCardForm.membershipAmount = result.data.membershipPrice
			MemberCardForm.bookboxAmount = result.data.bookboxPrice
			let securityDepositPrice = result.data.securityDepositPrice;
			MemberCardForm.securityDepositPrice = securityDepositPrice == null ? 0 : securityDepositPrice

			MemberCardForm.days = result.data.days
			// 获取当前时间
			var current = new Date();
			// 计算总毫秒数
			var totalMilliseconds = current.getTime() + (result.data.days * 24 * 60 * 60 * 1000);
			// 创建新的Date对象
			var addCurrent = new Date(totalMilliseconds);
			MemberCardForm.memberCardDate = [current.format('yyyy-MM-dd HH:mm:ss'), addCurrent.format(
				'yyyy-MM-dd HH:mm:ss')]
		}
	)
}

// 配送大区监听动态查询配送线路
function deliverAreaChange(deliverAreaId) {
	if (deliverAreaId == "") {
		return;
	}
	common.call(
		'GetDeliverLineList', {
		'deliverAreaId': deliverAreaId
	},
		function (result) {
			MemberCardForm.deliverLineId = ''
		}
	)
}

// 配送线路监听动态查询配送点
function deliverLineChange(deliverLineId) {
	if (deliverLineId == "") {
		return;
	}
	// 获取配送线路的所属大区
	common.call(
		'GetDeliverLineList', {
		'id': deliverLineId
	},
		function (result) {
			MemberCardForm.deliverAreaId = result.data.dataList[0].deliverAreaId;
			MemberCardForm.delivererId = result.data.dataList[0].delivererId;
		}
	)
}

// 配送点监听动态查询配送大区，线路
function deliverPlaceChange(deliverPlaceId) {
	if (deliverPlaceId == "") {
		return;
	}
	// 获取配送点的所属大区，线路
	common.call(
		'GetViewDeliverPlaceList', {
		'id': deliverPlaceId
	},
		function (result) {
			MemberCardForm.deliverAreaId = result.data.dataList[0].deliverAreaId;
			MemberCardForm.deliverLineId = result.data.dataList[0].deliverLineId;
		}
	)
}

// 选择书箱费必填控制
function isBookboxAmountChange(val) {
	if (val) {
		rules.deliverAreaId[0].required = true;
		rules.deliverLineId[0].required = true;
		rules.deliverPlaceId[0].required = true;
		rules.delivererId[0].required = true;
		rules.distanceCode[0].required = true;
		rules.address[0].required = true;
	} else {
		rules.deliverAreaId[0].required = false;
		rules.deliverLineId[0].required = false;
		rules.deliverPlaceId[0].required = false;
		rules.delivererId[0].required = false;
		rules.distanceCode[0].required = false;
		rules.address[0].required = false;
	}
}

// 编辑页初始化
function init(data) {
	// 显示弹窗
	dialogFormVisible.value = true
	// 清除表单的校验规则
	this.$nextTick(() => {
		this.$refs.MemberCardFormRef.clearValidate()
	})
	// 会员生效/失效日期回显
	if (data.effectiveDateText != null && data.expireDateText != null) {
		data.memberCardDate = [data.effectiveDateText, data.expireDateText]
	}
	// 表单渲染
	Object.assign(MemberCardForm, MemberCardFormInit);
	if (data.deliverAreaId == null) {
		data.deliverAreaId = "";
	}
	if (data.deliverLineId == null) {
		data.deliverLineId = "";
	}
	if (data.delivererId == null) {
		data.delivererId = "";
	}
	if (data.deliverPlaceId == null) {
		data.deliverPlaceId = "";
	}
	if (data.distanceCode == null) {
		data.distanceCode = "";
	}
	if (data.memberCardTypeId == null) {
		data.memberCardTypeId = "";
	}
	if (data.paymentMethodCode == null) {
		data.paymentMethodCode = "";
	}
	if (data.employeeId == null) {
		data.employeeId = "";
	}
	if (data.referrerId == null) {
		data.referrerId = "";
	}
	if (data.gradeId == null) {
		data.gradeId = "";
	}
	if (data.classId == null) {
		data.classId = "";
	}
	Object.assign(MemberCardForm, data)
	if (MemberCardForm.longitude != null && MemberCardForm.latitude != null) {
		MemberCardForm.location = MemberCardForm.longitude + ',' + MemberCardForm.latitude
		center.value.lng = Number(MemberCardForm.longitude)
		center.value.lat = Number(MemberCardForm.latitude)
	}
}

//禁用当前日期之前的日期
function disabledDate(time) {
	//Date.now()是javascript中的内置函数，它返回自1970年1月1日00:00:00 UTC以来经过的毫秒数。
	//return time.getTime() < Date.now() - 8.64e7;
}

// 监听停卡天数事件
function handleApplyTimeChange(value) {
	if (MemberCardForm.memberCardTypeId == '') {
		ElMessage.error('请先选择会员卡类型');
		MemberCardForm.effectiveDateText = '';
		return
	}
	if (value == null) {
		MemberCardForm.expireDateText = '';
		return
	}
	let days = MemberCardForm.days;
	if (days != '') {
		MemberCardForm.expireDateText = util.getAddDays(MemberCardForm.effectiveDateText, days);
	}
}

defineExpose({
	init
})
</script>
<style>
.map {
	width: 100%;
	height: 800px;
}
</style>

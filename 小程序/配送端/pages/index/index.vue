<template>
	<view>
		<!-- <uni-nav-bar :fixed="false" color="#FFFFFF" background-color="#5EAC59"  @clickLeft="showCity" :statusBar="true"  fixed="true">
			<block slot="left">
				<view class="city">
					<view style="display: flex;justify-content: center;align-items: center;flex-wrap: wrap;">
						<text class="uni-nav-bar-text f24 b" >日期选择:{{selectDay | toselectDay}}</text>
						<image src="../../static/triangle.png" style="width: 30rpx; height: 30rpx; margin-left: 2rpx;"></image>
					</view>
				</view>
			</block>
				首页
			<block slot="right">
				<view class="">
				</view>
			</block>
		</uni-nav-bar> -->
		<view class="content-view">
			<view class="labelView" style="position: fixed;  background-color: #5EAC59; z-index: 1; color: #ffffff; height: 50rpx;">
				<view class="itemSty"  @click="showCity()" style="align-items: center;" >
					日期选择：{{selectDay | toselectDay}}<image src="../../static/triangle.png" style="width: 30rpx; height: 30rpx; margin-left: 2rpx;"></image>
				</view>
			</view>
			<view class="h50"></view>
			<!-- 单角色拣书员 -->
			<view v-if="isBookPicker == true && isDistribution==false">
				<!-- Table选项卡 -->
				<view class="labelView" style="position: fixed; background-color: #5EAC59; z-index: 1;">
					<view class="itemSty" :class="{'SeItem':bookPickerActive == 0}" @click="changeBookPickerActive(0)">
						待拣书单(待拣&nbsp;<span class="span" :class="{'span1':returnNumber == 0}">{{returnNumber}}</span>&nbsp;)
					</view>
					<!-- <view class="itemSty" :class="{'SeItem':bookPickerActive == 1}" @click="changeBookPickerActive(1)">
			        明日日待拣书单(待拣&nbsp;<span  class="span" :class="{'span1':tomorrowReturnNumber == 0}">{{tomorrowReturnNumber}}</span>&nbsp;)
			    </view> -->
					<p :style="{left:`calc(${bookPickerActive == 0 ? 50 : 0}% - 25px)`}"></p>
				</view>
				<view style="height: 80rpx;"></view>
				<!-- 按书架汇总明细显示  -- 按钮 -->
				<!-- <view class="wayType" style="height: 50rpx; line-height: 50rpx;">
			    <view @click="wayShow = !wayShow" style="height: 50rpx; line-height: 50rpx;">
			        {{wayShow ? '按列表显示' : '按书架汇总明细显示'}}
			    </view>
			</view> -->
				<!-- 按书架汇总明细显示 -- 列表 -->
				<view class="wayTable" v-show="wayShow">
					<view v-for="(item,bookCaseIndex) in bookCaseDetailList" :key="bookCaseIndex">
						<view class="bookcase">
							书柜：{{item.bookcaseName}}号
						</view>
						<table>
							<thead>
								<tr>
									<th>序号</th>
									<th>书名</th>
									<th>图书编号</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="(ite,bookSkuIndex) in item.children" :key="bookSkuIndex">
									<td>{{ite.no}}</td>
									<td>{{ite.bookName}}</td>
									<td>{{ite.bookSn}}</td>
								</tr>

							</tbody>
						</table>
					</view>
				</view>
				<view style="height: 20rpx;"></view>
				<view class="listView" id="NumInfo" v-show="!wayShow">
					<view style="display: flex;flex-wrap:wrap; background-color: #fff;padding: 5rpx;border-radius: 20rpx;margin-bottom: 10rpx;">
						<view  class="line80" style="margin-left: 10rpx; font-weight: 600; display: flex;flex-wrap:wrap;" v-for="(item,index) in bookOutOrderAreaList" :key="index" :class="{'c_red':item.deliverAreaId == bookOutOrderAreaId}" @click="bindAreaBookOutOrderInfoList(item.deliverAreaId)">
							<view class="f29">{{item.deliverAreaName}}：{{item.count}}</view>
							<view class="f29" style="margin-left: 10rpx;" v-if="index != bookOutOrderAreaList.length - 1">|</view>
						</view>
						<view class="f26 line80" style="margin-left: 25rpx;"><checkbox :checked="isShowUntreated" @click="onCheckboxChange()">只显示未处理</checkbox></view>
					</view> 
					<view class="liView" v-for="(item,index) in PickBookList" :key="index">
						<view class="info">
							<view class=" item" style="color: rgb(0, 128, 0);font-weight: normal;">{{item.deliverAreaName}}</view>
							<view class="top">
								<!-- <view class="item"><span>会员号：</span>{{item.memberNo}}</view> -->
								<view class="item"><span>姓名：</span>{{item.memberName}}</view>
								<img src="../../static/open.png"
									:style="{transform: `rotate(${item.open ? -180 : 0}deg)`}" @click="openClick(index)"
									alt="" />
							</view>
							<!-- <view class="item"><span>路线：</span>{{item.deliverLineName}}</view> -->
							<view class="item"><span>地址：</span>{{item.address}}</view>
							<view class="item"><img src="../../static/phone.png" alt=""
									style="width: 40rpx; height: 40rpx;" />{{item.memberMobilePhone}}</view>
							<view class="tips">共{{item.pickTotal}}本，（已拣<span>{{item.yPicKNum}}</span>本，未拣
								<span>{{item.nPicKNum}}</span> 本)</view>
							<view class="openListView" :style="{maxHeight:item.open ? '2000px' : '0'}">
								<view class="openView" v-for="(li,childrenindex) in item.children" :key="childrenindex">
									<image :src="li | toBookImageFilePath" alt="" mode="aspectFit"
										style=" height: 210rpx;display: block;width:25% ;  border:  2rpx solid rgb(211 211 211); border-radius: 10rpx;">
									</image>
									<view class="news" style="margin-left: 10rpx;">
										<view class="title">
											<text class="f26" style="color: rgb(0, 128, 0);font-weight: normal;"
												v-if="li.bookSeriesName !='' && li.bookSeriesName !=''">[{{li.bookSeriesName}}]</text>
											{{li.bookName }}
											<text class="f26"
												style="color: rgb(0, 0, 255); font-weight: normal;">({{li.bindingTypeName}})</text>
										</view>
										<view class="f30 line70" style="color: #5EAC59;">
											<span class="f30">书号:</span>{{li.bookSn}}
										</view>
										<view class="f30" style="color: #5EAC59;display: flex;align-items: center;flex-wrap: wrap;">
											<view style="width: calc(100% - 160rpx);">
												<span class="f26">位置:</span>{{li.bookcaseName}}
											</view>
											<view class="butt" style="width: 160rpx;">
												<view v-if="li.bookOutOrderStatusCode == 'NotFound'" class="returnbook"
													@click="clickFindMe(li,index,childrenindex)">找到点我</view>
												<view v-if="li.bookOutOrderStatusCode == 'Found'" class="cancelbooks">已拣</view>
											</view>
										</view>
									</view>
								</view>
							</view>
						</view>
					</view>
				</view>
				<view style="height: 50px;"></view>
			</view>
			<view v-else>
				<!-- Table选项卡 -->
				<view class="labelView" style="position: fixed;  background-color: #5EAC59; z-index: 1;">
					<view class="itemSty" :class="{'SeItem':active == 0}" @click="changeActive(0)" v-if="isDistribution == true">
						配送单（待配送）<span class="span" :class="{'span1':borrowNumber == 0}">{{borrowNumber}}</span>
					</view>
					<view class="itemSty" :class="{'SeItem':active == 1}" @click="changeActive(1)" v-if="isDistribution == true && isBookPicker == true">
						拣书单（待拣书）<span class="span" :class="{'span1':returnNumber == 0}">{{returnNumber}}</span>
					</view>
					<p :style="{left:`calc(${active == 0 ? 50 : 50}% - 25px)`}" v-if="isDistribution == true && isBookPicker == false"></p>
					<p :style="{left:`calc(${active == 0 ? 25 : 75}% - 25px)`}" v-if="isDistribution == true && isBookPicker == true"></p>
				</view>
				<view style="height: 90rpx;"></view>
				<!-- 配送单列表 -->
				<view class="listView" id="NumInfo" v-if="active == 0">
					<view style="display: flex;flex-wrap:wrap; background-color: #fff;padding: 5rpx;border-radius: 20rpx;margin-bottom: 10rpx;" v-if="deliverOrderAreaTotalList.length > 0">
						<view  class="line80" style="margin-left: 10rpx;font-weight: 600; display: flex;flex-wrap:wrap;" v-for="(item,index) in deliverOrderAreaTotalList" :key="index"  :class="{'c_red':item.deliverAreaId == deliverAreaId}" @click="bindAreaDeliveryOrderInfoList(item.deliverAreaId)">
							<view class="f29 line80">{{item.deliverAreaName}}：{{item.count}}</view>
							<view class="f29 line80" style="margin-left: 10rpx;" v-if="index != deliverOrderAreaTotalList.length - 1">|</view>
						</view>
						<view class="f26 line80" style="margin-left: 25rpx;"><checkbox :checked="isShowUntreated" @click="onCheckboxChange()">只显示未处理</checkbox></view>
					</view>
					<view class="liView" v-for="(item,index) in deliveryBookOrderList" :key="index" style="display: block;">
						<view class="info" style="float: left; width:calc(100% - 120px)">
							<view class=" item f30" style="color: rgb(0, 128, 0);font-weight: normal; font-size: 40rpx;">{{item.deliverAreaName}}</view>
							<!-- <view class="item">{{item.deliverLineName}}，{{item.deliverPlaceName}}</view> -->
							<!-- <view class="item">{{item.deliverLineName}}，{{item.deliverPlaceName}}</view> -->
							<view class="f35" style="font-weight: 600;">
								<text class="b c_blue f30" v-if="item.memberEntranceGuard!=''">[{{item.memberEntranceGuard}}]</text>{{item.memberAddress}}</view>
							<!-- <view class="item"><span></span>书箱号：{{item.bookboxNo}}</view> -->
							<view class="item b f35" @click="clickCallPhone(item)"> {{item.memberName}} {{item.memberMobilePhone}}</view>
							<view class="tips" @tap.stop="deliveryBookOpenClick(index)">送
								<text style="color:#FACC3F; font-weight: bold;">{{item.sendCount}}</text>本，取
								<text class=" c_red b">{{item.fetchCount}}</text>本 点击核对</view>
						</view>
						<view class="butt" style="float: right; "> <!-- display: flex; flex-direction: column; gap: 20px; -->
							<view  @tap.stop="clickMoveUp(item,index,'MoveUp')" :style="{'background-color':index != 0?'#FACC3F':'gray','color':'#ffffff'}">上移</view>
							<view  @tap.stop="clickMoveDown(item,index,'MoveDown')" :style="{'background-color':index + 1 != deliveryBookOrderList.length?'#FACC3F':'gray','color':'#ffffff'}">下移</view>
							<view v-if="item.bookOrderBorrowStatusCode === 'Delivering' || item.bookOrderBorrowStatusCode === 'ErrorProcessing' || item.bookOrderBorrowStatusCode === 'Retrieving' " style="" @tap.stop="clickDeliverBookOrderReceived(item,index)">送达点我</view>
							<view v-else-if="item.bookOrderBorrowStatusCode === 'Submitting' || item.bookOrderBorrowStatusCode === 'StockOuting'" style="background-color: gray; color: white; ">待拣书</view>
							<view v-else style="background-color: gray; color: white; ">已送达</view>
							<!-- <view v-if="item.bookOrderBorrowStatusCode != 'Delivering'" style="background-color: gray; color: white;" @tap.stop="clickpopupContent(item,index)"> 异常点我</view>
							<view v-else="item.bookOrderBorrowStatusCode == 'Delivering'" style="" @tap.stop="clickDeliveryException(item,index)">异常点我</view> -->
						</view> 
						<view style="clear: both;"></view>
						<view class="openListView" :style="{maxHeight:item.open ? '2000px' : '0'}"
							style="border:0;margin: 0;">
							<!-- <view style="border-bottom: 1px solid rgba(236, 237, 239, 1);">
							<view class="left80 line80 b"> 书名</view>
							<view class="left20 line80 b">状态</view>
						</view>
						<view class="clear"></view>
						<view v-for="(li,bookindex) in item.bookList" :key="bookindex" style="border-bottom: 1px solid rgba(236, 237, 239, 1);">
							<view class="f28 left80" style="height: 75rpx; line-height: 35rpx; display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 2;overflow: hidden;"> {{li.bookName}}</view>
							<view class="f28 left20" style="height: 75rpx; line-height: 75rpx;" > {{li.deliverStatusName | toNull}}</view>
							<view class="clear"></view>
						</view> -->
							<view class="openView line80"
								style="margin-top: 15rpx;border-bottom: 1px solid rgba(236, 237, 239, 1);padding: 10rpx;">
								<view class="news b f28">
									书名
								</view>
								<view class="butt b f28 center" style="width: 75rpx;">
									状态
								</view>
							</view>
							<!-- background-color: #d9edf7!important; -->
							<view class="openView" v-for="(li,bookindex) in item.children" :key="bookindex"
								style="margin-top: 15rpx;border-bottom: 1px solid rgba(236, 237, 239, 1); padding: 10rpx;"
								:style="{'background-color':li.bookOrderTypeName == '借书'?'#d9edf7':''}">
								<view class="news">
									<view class="title f27 line50"
										style="-webkit-line-clamp: 1; height: 50rpx; line-height: 50rpx;">
										{{li.bookName}}
									</view>
									<view class="f27 line50">
										{{li.bookSn | toNull}}
									</view>
								</view>
								<view class="butt f27 center line100" style="">
									<block v-if="li.bookOrderTypeName == '还书' && li.deliverStatusName == '等待配送'">
										等待取书
									</block>
									<block v-else>
										{{li.deliverStatusName | toNull}}
									</block>
								</view>
							</view>
						</view>
						<view style="clear: both;"></view>
					</view>
				</view>
				<!-- 拣书单列表 -->
				<view class="listView" id="NumInfo" v-if="active == 1">
					<view style="display: flex;flex-wrap:wrap; background-color: #fff;padding: 5rpx;border-radius: 20rpx;margin-bottom: 10rpx;" v-if="bookOutOrderAreaList.length > 0">
						<view  class="line80" style="margin-left: 10rpx;font-weight: 600; display: flex;flex-wrap:wrap;" v-for="(item,index) in bookOutOrderAreaList" :key="index" :class="{'c_red':item.deliverAreaId == bookOutOrderAreaId}" @click="bindAreaBookOutOrderInfoList(item.deliverAreaId)">
							<view class="f29">{{item.deliverAreaName}}：{{item.count}}</view>
							<view class="f29" style="margin-left: 10rpx;" v-if="index != bookOutOrderAreaList.length - 1">|</view>
						</view>
						<view class="f26 line80" style="margin-left: 25rpx;"><checkbox :checked="isShowUntreated" @click="onCheckboxChange()">只显示未处理</checkbox></view>
					</view>
					<view class="liView" v-for="(item,index) in PickBookList" :key="index">
						<view class="info">
							<view class=" item" style="color: rgb(0, 128, 0);font-weight: normal;">{{item.deliverAreaName}}</view>
							<view class="top">
								<!-- <view class="item"><span>会员号：</span>{{item.memberNo}}</view> -->
								<view class="item"><span>姓名：</span>{{item.memberName}}</view>
								<img src="../../static/open.png"
									:style="{transform: `rotate(${item.open ? -180 : 0}deg)`}" @click="openClick(index)"
									alt="" />
							</view>
							<!-- <view class=" item"><span>路线：</span>{{item.deliverLineName}}</view> -->
							<view class="item"><span>地址：</span>{{item.address}}</view>
							<view class="item"><img src="../../static/phone.png" alt=""
									style="width: 40rpx; height: 40rpx;" />{{item.memberMobilePhone}}</view>
							<view class="tips" @click="openClick(index)">
								共{{item.pickTotal}}本，（已拣<span>{{item.yPicKNum}}</span>本，未拣
								<span>{{item.nPicKNum}}</span> 本)</view>
							<view class="openListView" :style="{maxHeight:item.open ? '2000px' : '0'}">
								<view class="openView" v-for="(li,childrenindex) in item.children" :key="childrenindex">
									<image :src="li | toBookImageFilePath" alt="" mode="aspectFit"
										style=" height: 210rpx;display: block;width:25% ;  border:  2rpx solid rgb(211 211 211); border-radius: 10rpx;">
									</image>
									<view class="news">
										<view class="title">
											<text class="f26" style="color: rgb(0, 128, 0);font-weight: normal;"
												v-if="li.bookSeriesName !='' && li.bookSeriesName !=''">[{{li.bookSeriesName}}]</text>
											{{li.bookName }}
											<text class="f26"
												style="color: rgb(0, 0, 255); font-weight: normal;">({{li.bindingTypeName}})</text>
										</view>
										<view class="f30 line70" style="color: #5EAC59;">
											<span class="f30">书号:</span>{{li.bookSn}}
										</view>
										<view class="f30" style="color: #5EAC59;display: flex;align-items: center;flex-wrap: wrap;">
											<view style="width: calc(100% - 160rpx);">
												<span class="f26">位置:</span>{{li.bookcaseName}}
											</view>
											<view class="butt" style="width: 160rpx;">
												<view v-if="li.bookOutOrderStatusCode == 'NotFound'" class="returnbook"
													@click="clickFindMe(li,index,childrenindex)">找到点我</view>
												<view v-if="li.bookOutOrderStatusCode == 'Found'" class="cancelbooks">已拣</view>
											</view>
										</view>
										<!-- <view class="item"><span>位置：</span>{{li.bookcaseName}}</view> -->
									</view>
								</view>
							</view>
						</view>
					</view>
				</view>
				<view style="height: 50px;"></view>
			</view>
			<!-- 配送异常弹框 输入框示例 -->
			<uni-popup ref="popup" type="center" background-color="#fff">
				<uni-popup-dialog ref="inputClose" title="送达点我"  @confirm="dialogInputConfirm" @close ="dialogInputclose">
					<view class="popupSty">
						<!-- <view v-for="(item,index) in abnormityList" :key="index" @click="clickSelected(item,index)">
							<view :style="{color: item.isSelected===true ? '#fe1303':'black'}">
								{{item.name}}
							</view>
						</view> -->
						<view style="padding-top: 5px;">异常备注</br>(无异常不用填写) </view>
						<view><uni-easyinput style="margin-left: 10px;" v-model="other"
								placeholder="请填写异常情况" /></view>
					</view>
				</uni-popup-dialog>
			</uni-popup>
			<!-- 显示异常内容 -->
			<!-- <uni-popup ref="popupContent" type="center" background-color="#fff">
				<uni-popup-dialog ref="inputClose" title="异常内容">
					<view class="popupSty">
						<view style="padding-top: 5px;">内容 <uni-easyinput disabled="true" style="margin-left: 10px;"
								v-model="abnormityContent" placeholder="" /></view>
					</view>
				</uni-popup-dialog>
			</uni-popup> -->
			<!-- 日历弹出层 -->
			<view class="calendarPopup" v-show="IsCalendarPopup">
				<view class="mask"></view>
				<view class="contents">
					<ren-calendar ref='ren' :markDays='markDays' :open="true" :disabledAfter='true'
						@onDayClick='onDayClick'></ren-calendar>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	let that = null;
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	import popup from "@/components/uni-popup/uni-popup.vue";
	import popupdialog from "@/components/uni-popup-dialog/uni-popup-dialog.vue";
	import renCalendar from '@/components/ren-calendar/ren-calendar.vue'
	import uniNavBar from '@/components/uni-nav-bar/uni-nav-bar.vue'
	export default {
		data() {
			return {
				isShowUntreated:false,
				adminRootUrl: config.adminRootUrl + "/", //后端图片地址
				isDistribution: false, //是否配送员
				isBookPicker: false, //是否拣书员
				bookPickerActive: 0, //单角色拣书员
				todayReturnNumber: 0, //今日待拣数量
				tomorrowReturnNumber: 0, //明日待拣数量
				borrowNumber: 0, //待配送的数量
				returnNumber: 0, //待拣数量
				other: '',
				active: 0,
				wayShow: false,
				title: 'Hello',
				deliveryBookOrderList: [], //配送单
				PickBookList: [], //拣书单
				bookCaseDetailList: [], //书架汇总明细列表
				selectedBookOrderID: "",
				selectedBookOrderIndex: "",
				abnormityList: [{
					name: "无书",
					isSelected: false
				}, {
					name: "少书",
					isSelected: false
				}, {
					name: "书箱异常",
					isSelected: false
				}, ],
				abnormityContent: '', //异常内容
				IsCalendarPopup: false, //日历 弹出层	
				selectDay: '',
				markDays: [], //'2024-04-02'
				deliverOrderAreaTotalList:[],//配送单大区数据
				deliverAreaId:0,//配送单大区ID
				bookOutOrderAreaList:[],//拣书单大区数据
				bookOutOrderAreaId:0,//拣书单大区ID
			}
		},
		components: {
			popup,
			popupdialog,
			renCalendar,
			uniNavBar
		},
		onShow() {
			if(uni.getStorageSync("islogin") == "true"){
				console.log("界面onShow")
				let roleList = uni.getStorageSync("RoleList");
				that.isDistribution = false;
				that.isBookPicker = false;
				for (let i = 0; i < roleList.length; i++) {
					let stringdata = JSON.stringify(roleList[i])
					if (stringdata.indexOf("配送员") != -1) {
						that.isDistribution = true
					}
					if (stringdata.indexOf("拣书员") != -1) {
						that.isBookPicker = true
					}
				}
				console.log(that.isDistribution)
				console.log(that.isBookPicker)
				that.bindSelectDay();
				uni.removeStorageSync("islogin");
			}
		},
		onLoad() {
			that = this;
			if(uni.getStorageSync("islogin") != "true"){
				console.log("界面onLoad")
				let roleList = uni.getStorageSync("RoleList");
				that.isDistribution = false;
				that.isBookPicker = false;
				for (let i = 0; i < roleList.length; i++) {
					let stringdata = JSON.stringify(roleList[i])
					if (stringdata.indexOf("配送员") != -1) {
						that.isDistribution = true
					}
					if (stringdata.indexOf("拣书员") != -1) {
						that.isBookPicker = true
					}
				}
				console.log(that.isDistribution)
				console.log(that.isBookPicker)
				that.bindSelectDay();
				uni.removeStorageSync("islogin");
			}
		},
		methods: {
			showCity() {
				console.log("点击左侧区域")
				that.IsCalendarPopup = true
			},
			bindSelectDay() { //界面首次进入获取当前时间和今日是否为周六日
				let current = new Date();
				let y = current.getFullYear();
				let m = current.getMonth();
				let d = current.getDate();
				that.selectDay = y + '-' + that.formatNum(m + 1) + '-' + that.formatNum(d)
				that.bindData();
			},
			formatNum(num) {
				let res = Number(num);
				return res < 10 ? '0' + res : res;
			},
			onNavigationBarButtonTap(obj) {
				console.log("更多")
				that.IsCalendarPopup = true
				// uni.navigateTo({
				// 	url: 'MemberFriendMore?FriendMemberID='+that.MemberFriendID
				// });
			},
			onDayClick(data) { //日历的时间点击事件
				that.selectDay = data.date; //后去点击的时间
				that.IsCalendarPopup = false
				console.log(that.selectDay)
				that.bindData();
			},
			bindData() { //绑定数据
				that.bindGetDeliverOrderAreaTotal();//绑定配送单大区统计信息
				that.bindGetBookOutOrderAreaTotal();//绑定拣书单大区统计信息
				var numberData = {
					delivererId: uni.getStorageSync("DelivererID")
				}
				numberData.condition = "DATE(deliver_time) = '" + that.selectDay + "'"
				common.call('GetDeliverOrPickNumber', numberData, function(result) {
					console.log(result)
					that.todayReturnNumber = result.data.todayReturnNumber  //今日待拣数量
					that.tomorrowReturnNumber = result.data.tomorrowReturnNumber //明日待拣数量
					that.borrowNumber = result.data.borrowNumber //待配送的数量
					that.returnNumber = result.data.returnNumber  //待拣数量
				});
				if (that.isBookPicker == true && that.isDistribution == false) { //单角色为拣书员
					that.PickBookList = []
					var data = {};
					data.companyId= uni.getStorageSync("CompanyID")
					data.bookpickerId = uni.getStorageSync("DelivererID");
					if(that.deliverAreaId != 0){
						data.deliverAreaId = that.deliverAreaId //配送单大区ID
					}
					data.isOffline = false //线上
					data.orderBy = "deliver_area_id,deliver_area_number" 
					data.condition = "DATE(deliver_time) = '" + that.selectDay + "'"
					data.pageSize = 1000
					if(that.isShowUntreated == true){
						data.bookOutOrderStatusCode = 'NotFound'
					}
					common.call('GetBookOutOrderInfoList', data, function(result) { //拣书单
						that.PickBookList = result.data.dataList;
						for (let i = 0; i < that.PickBookList.length; i++) {
							var flg = false
							for (let j = 0; j < that.PickBookList[i].children.length; j++) {
								if (that.PickBookList[i].children[j].bookOutOrderStatusCode == "NotFound") {
									flg = true
									break;
								}
							}
							if (flg == true) {
								that.PickBookList[i].open = true
							} else {
								that.PickBookList[i].open = false
							}
						}
						console.log(that.PickBookList)
						//
					});

					var bookCaseData = {
						delivererId: uni.getStorageSync("DelivererID")
					}
					if (that.bookPickerActive == 0) {
						let current = new Date();
						let y = current.getFullYear();
						let m = current.getMonth();
						let d = current.getDate();
						bookCaseData.createTimeBegin = y + '-' + that.formatNum(m + 1) + '-' + that.formatNum(d) +
							" 00:00:00";
						bookCaseData.createTimeEnd = y + '-' + that.formatNum(m + 1) + '-' + that.formatNum(d) +
							" 23:59:59";
					} else {
						let current = new Date();
						current.setDate(current.getDate() + 1);
						let y = current.getFullYear();
						let m = current.getMonth();
						let d = current.getDate();
						bookCaseData.createTimeBegin = y + '-' + that.formatNum(m + 1) + '-' + that.formatNum(d) +
							" 00:00:00";
						bookCaseData.createTimeEnd = y + '-' + that.formatNum(m + 1) + '-' + that.formatNum(d) +
							" 23:59:59";
					}
					bookCaseData.pageSize = 1000
					common.call('GetBookCaseDetailList', bookCaseData, function(result) { //书架汇总明细列表
						console.log(result.data)
						that.bookCaseDetailList = result.data.dataList;
					});
				}
				if (that.isBookPicker == false && that.isDistribution == true) { // 单角色为配送员
					that.deliveryBookOrderList = []
					var deliveryBookOrderData = {
						delivererId: uni.getStorageSync("DelivererID"),
						companyId: uni.getStorageSync("CompanyID")
					}
					if(that.deliverAreaId != 0){
						deliveryBookOrderData.deliverAreaId = that.deliverAreaId //配送单大区ID
					}
					deliveryBookOrderData.pageIndex = 0
					deliveryBookOrderData.pageSize = 10000
					deliveryBookOrderData.orderBy = "display_order"
					deliveryBookOrderData.condition = "DATE(deliver_time) = '" + that.selectDay + "'";
					if(that.isShowUntreated == true){
						deliveryBookOrderData.condition	+= " and book_order_borrow_status_code ='Delivering'"
					}
					common.call('GetDeliveryOrderInfoList', deliveryBookOrderData, function(result) { //配送单
						console.log(result.data)
						that.deliveryBookOrderList = result.data.dataList;
						for (let i = 0; i < that.deliveryBookOrderList.length; i++) {
							that.deliveryBookOrderList[i].open = false
						}
					});
				}
				if (that.isBookPicker == true && that.isDistribution == true) { // 角色为配送员和拣书员
					that.deliveryBookOrderList = []
					var deliveryBookOrderData = {
						delivererId: uni.getStorageSync("DelivererID"),
						companyId: uni.getStorageSync("CompanyID")
					}
					if(that.deliverAreaId != 0){
						deliveryBookOrderData.deliverAreaId = that.deliverAreaId //配送单大区ID
					}
					deliveryBookOrderData.pageIndex = 0
					deliveryBookOrderData.pageSize = 10000
					deliveryBookOrderData.orderBy = "display_order"
					deliveryBookOrderData.condition = "DATE(deliver_time) = '" + that.selectDay + "'";
					if(that.isShowUntreated == true){
						deliveryBookOrderData.condition += " and book_order_borrow_status_code ='Delivering'"
					}
					common.call('GetDeliveryOrderInfoList', deliveryBookOrderData, function(result) { //配送单
						console.log(result.data)
						that.deliveryBookOrderList = result.data.dataList;
						for (let i = 0; i < that.deliveryBookOrderList.length; i++) {
							that.deliveryBookOrderList[i].open = false
						}
					});
					that.PickBookList = []
					var data = {};
					data.companyId= uni.getStorageSync("CompanyID")
					data.bookpickerId = uni.getStorageSync("DelivererID");
					if(that.deliverAreaId != 0){
						data.deliverAreaId = that.deliverAreaId //配送单大区ID
					}
					data.isOffline = false //线上
					data.orderBy = "deliver_area_id,deliver_area_number"
					data.condition = "DATE(deliver_time) = '" + that.selectDay + "'"
					data.pageSize = 1000
					if(that.isShowUntreated == true){
						data.bookOutOrderStatusCode = 'NotFound'
					}
					common.call('GetBookOutOrderInfoList', data, function(result) { ////拣书单
						that.PickBookList = result.data.dataList;
						for (let i = 0; i < that.PickBookList.length; i++) {
							var flg = false
							for (let j = 0; j < that.PickBookList[i].children.length; j++) {
								if (that.PickBookList[i].children[j].bookOutOrderStatusCode == "NotFound") {
									flg = true
									break;
								}
							}
							if (flg == true) {
								that.PickBookList[i].open = true
							} else {
								that.PickBookList[i].open = false
							}
						}
						console.log(that.PickBookList)
						//
					});
				}
			},
			clickFindMe(item, index, childrenindex) { //找到点我
					var numberData = {
					// id: item.id
					bookOrderId: item.bookOrderId,
					bookOutOrderId: item.id,
					bookOrderItemId: item.bookOrderItemId,
					bookId: item.bookId
				}
				common.call('BookOutOrderOutStock', numberData, function(result) {
					console.log(result)
					//已拣：yPicKNum 未拣：nPicKNum
					that.PickBookList[index].children[childrenindex].bookOutOrderStatusCode = "Found"
					var flg = false
					for (let j = 0; j < that.PickBookList[index].children.length; j++) {
						if (that.PickBookList[index].children[j].bookOutOrderStatusCode == "NotFound") {
							flg = true
							break;
						}
					}
					if (flg == true) {
						that.PickBookList[index].open = true //只要有一条未拣就展开
						let yPicKNum = 0 //已拣
						let nPicKNum = 0 //未拣
						for (let j = 0; j < that.PickBookList[index].children.length; j++) {
							if (that.PickBookList[index].children[j].bookOutOrderStatusCode == "NotFound") {
								nPicKNum += 1
							}
							if (that.PickBookList[index].children[j].bookOutOrderStatusCode == "Found") {
								yPicKNum += 1
							}
						}
						that.PickBookList[index].yPicKNum = yPicKNum //已拣
						that.PickBookList[index].nPicKNum = nPicKNum //未拣

					} else {
						that.PickBookList[index].open = false //全部拣完就收缩
						that.PickBookList[index].yPicKNum = that.PickBookList[index].children.length //已拣
						that.PickBookList[index].nPicKNum = 0 //未拣
					}
					var numberData = {
						delivererId: uni.getStorageSync("DelivererID")
					}
					numberData.condition = "DATE(deliver_time) = '" + that.selectDay + "'"
					common.call('GetDeliverOrPickNumber', numberData, function(result) {
						console.log(result)
						that.todayReturnNumber = result.data.todayReturnNumber //今日待拣数量
						that.tomorrowReturnNumber = result.data.tomorrowReturnNumber //明日待拣数量
						that.borrowNumber = result.data.borrowNumber //待配送的数量
						that.returnNumber = result.data.returnNumber //待拣数量
					});
				});
			},
			openClick(index) { //拣书单展开详情
				this.PickBookList[index].open = !this.PickBookList[index].open
				that.$forceUpdate();
			},
			deliveryBookOpenClick(index) { //配送单展开详情
				console.log(1111)
				this.deliveryBookOrderList[index].open = !this.deliveryBookOrderList[index].open
				console.log(this.deliveryBookOrderList)
				that.$forceUpdate();
			},
			changeActive(type) { //选项卡
				this.active = type
				that.bindData();
			},
			changeBookPickerActive(type) { //选项卡
				that.bookPickerActive = type
				that.bindData();
			},
			clickMoveUp(item, index,moveDirection) { //上移
				if(index == 0 ){
					console.log("第一个不能上移")
					return
				}
				console.log(item)
				console.log(index)
				console.log(moveDirection +":上移")
				
				var data = {};
				data.primaryKey = item.bookOrderId
				data.primaryKeyName = "id"
				data.tableName = "book_order"
				data.moveDirection = moveDirection
				//data.condition = "DATE(deliver_time) = '" + item.deliverTimeText + "'"
				data.condition = "deliverer_id = " + uni.getStorageSync("DelivererID") + " and company_id = " + uni.getStorageSync("CompanyID") + " AND DATE(deliver_time) = '" + item.deliverTimeText + "'"
				common.call('Move', data, function(result) {
					console.log(result)
					//that.deliveryBookOrderList = result.data
					//const arr = that.deliveryBookOrderList;
					//that.swapArrayElements(arr, index, index - 1);
					that.bindDeliveryOrderInfoList();
				});
			},
			clickMoveDown(item, index,moveDirection) { //下移
				if(index + 1 == that.deliveryBookOrderList.length){
					console.log("最后一个不能下移")
					return
				}
				console.log(item)
				console.log(index)
				console.log(moveDirection +":下移")
				var data = {};
				data.primaryKey = item.bookOrderId
				data.primaryKeyName = "id"
				data.tableName = "book_order"
				data.moveDirection = moveDirection
				//data.condition = "DATE(deliver_time) = '" + item.deliverTimeText + "'"
				data.condition = "deliverer_id = " + uni.getStorageSync("DelivererID") + " and company_id = " + uni.getStorageSync("CompanyID") + " AND DATE(deliver_time) = '" + item.deliverTimeText + "'"
				common.call('Move', data, function(result) {
					//console.log(result)
					//const arr = that.deliveryBookOrderList;
					//that.swapArrayElements(arr, index, index + 1); 
					that.bindDeliveryOrderInfoList();
				});
			},
			
			swapArrayElements(arr, index1, index2){
				  arr.splice(index2, 1, arr.splice(index1, 1, arr[index2])[0]);
			},
			clickDeliverBookOrderReceived(item, index) { //送达点我
				// var bookCaseData = {
				// 	bookOrderId: item.bookOrderId
				// }
				// common.call('DeliverBookOrderReceived', bookCaseData, function(result) {
				// 	console.log(result.data)
				// 	// that.bindData();
				// 	that.deliveryBookOrderList[index].bookOrderBorrowStatusCode = 'Received'
				// 	for (let i = 0; i < that.deliveryBookOrderList[index].bookList.length; i++) {
				// 		if (that.deliveryBookOrderList[index].bookList[i].bookOrderTypeName == "借书") {
				// 			that.deliveryBookOrderList[index].bookList[i].deliverStatusName = "已送达"
				// 		}
				// 		if (that.deliveryBookOrderList[index].bookList[i].bookOrderTypeName == "还书") {
				// 			that.deliveryBookOrderList[index].bookList[i].deliverStatusName = "等待入库"
				// 		}
				// 	}
				// 	var numberData = {
				// 		delivererId: uni.getStorageSync("DelivererID")
				// 	}
				// 	numberData.condition = "DATE(deliver_time) = '" + that.selectDay + "'"
				// 	common.call('GetDeliverOrPickNumber', numberData, function(result) {
				// 		console.log(result)
				// 		that.todayReturnNumber = result.data.todayReturnNumber
				// 		that.tomorrowReturnNumber = result.data.tomorrowReturnNumber
				// 		that.borrowNumber = result.data.borrowNumber
				// 		that.returnNumber = result.data.returnNumber
				// 	});
				// });
				that.selectedBookOrderID = item.bookOrderId
				that.selectedBookOrderIndex =index
				this.$refs.popup.open();
			},
			clickDeliveryException(item, index) { //配送异常
				that.selectedBookOrderID = item.bookOrderId
				this.$refs.popup.open();
			},
			clickSelected(item, index) { //"异常选中"
				that.other = item.name
				for (let i = 0; i < that.abnormityList.length; i++) {
					that.abnormityList[i].isSelected = false
				}
				that.abnormityList[index].isSelected = true
				console.log(that.abnormityList)
			},
			dialogInputConfirm() { //异常提交
				let bookOrderID= that.selectedBookOrderID
				let index = that.selectedBookOrderIndex
				let errorRemark = that.other
				console.log(bookOrderID)
				console.log(index)
				console.log(errorRemark)
				var bookCaseData = {
					bookOrderId: bookOrderID
				}
				if(errorRemark != ""){
					bookCaseData.errorRemark = errorRemark
				}
				console.log(bookCaseData)
				common.call('DeliverBookOrderReceived', bookCaseData, function(result) {
					console.log(result.data)
					// that.bindData();
					that.other =""
					that.deliveryBookOrderList[index].bookOrderBorrowStatusCode = 'Received'
					for (let i = 0; i < that.deliveryBookOrderList[index].children.length; i++) {
						if (that.deliveryBookOrderList[index].children[i].bookOrderTypeName == "借书") {
							that.deliveryBookOrderList[index].children[i].deliverStatusName = "已送达"
						}
						if (that.deliveryBookOrderList[index].children[i].bookOrderTypeName == "还书") {
							that.deliveryBookOrderList[index].children[i].deliverStatusName = "等待入库"
						}
					}
					var numberData = {
						delivererId: uni.getStorageSync("DelivererID")
					}
					numberData.condition = "DATE(deliver_time) = '" + that.selectDay + "'"
					common.call('GetDeliverOrPickNumber', numberData, function(result) { // 绑定数量
						console.log(result)
						that.todayReturnNumber = result.data.todayReturnNumber
						that.tomorrowReturnNumber = result.data.tomorrowReturnNumber
						that.borrowNumber = result.data.borrowNumber
						that.returnNumber = result.data.returnNumber
					});
				});
				// var data = {
				// 	bookOrderId: that.selectedBookOrderID,
				// 	errorRemark: that.other
				// }
				// console.log(data)
				// common.call('DeliverErrorRemark', data, function(result) {
				// 	that.bindData();
				// });
			},
			dialogInputclose(){//异常取消
				that.other =""
				this.$refs.popup.close();
			},
			clickpopupContent(item, index) { //显示异常内容
				this.$refs.popupContent.open();
				// if (item.errorRemark != null) {
				// 	that.abnormityContent = item.errorRemark
				// } else {
				// 	that.abnormityContent = "暂无异常"
				// }
			},
			clickCallPhone(item) {
				//item.mobilePhone
				wx.makePhoneCall({
					phoneNumber: item.memberMobilePhone //仅为示例，并非真实的电话号码
				})
			},
			bindGetDeliverOrderAreaTotal(){//绑定配送单大区统计信息 GetDeliverOrderAreaTotal
				that.deliverOrderAreaTotalList =[]
				var data = {
					delivererId: uni.getStorageSync("DelivererID"),
					companyId: uni.getStorageSync("CompanyID")
				}
				data.pageIndex = 0
				data.pageSize = 10000
				data.orderBy = "display_order"
				data.condition = "DATE(deliver_time) = '" + that.selectDay + "'";
				common.call('GetDeliverOrderAreaTotal', data, function(result) {
					let dataAdd = {
						deliverAreaName:'全部',
						deliverAreaId:0,
						count:result.data.totalCount
					}
					that.deliverOrderAreaTotalList.push(dataAdd)
					let areaList = result.data.areaList
					for (let i = 0; i < areaList.length; i++) {
						let datas ={
							deliverAreaName:areaList[i].deliverAreaName,
							deliverAreaId:areaList[i].deliverAreaId,
							count:areaList[i].count,
						}
						that.deliverOrderAreaTotalList.push(datas)
					}
				});
			},
			bindGetBookOutOrderAreaTotal(){ //绑定拣书单大区统计信息 GetBookOutOrderAreaTotal
				that.bookOutOrderAreaList=[];
				var data = {};
				data.bookpickerId = uni.getStorageSync("DelivererID");
				data.isOffline = false //线上
				data.condition = "DATE(deliver_time) = '" + that.selectDay + "'"
				common.call('GetBookOutOrderAreaTotal', data, function(result) {
					let dataAdd = {
						deliverAreaName:'全部',
						deliverAreaId:0,
						count:result.data.totalCount
					}
					that.bookOutOrderAreaList.push(dataAdd)
					let areaList = result.data.areaList
					for (let i = 0; i < areaList.length; i++) {
						let datas ={
							deliverAreaName:areaList[i].deliverAreaName,
							deliverAreaId:areaList[i].deliverAreaId,
							count:areaList[i].count,
						}
						that.bookOutOrderAreaList.push(datas)
					}
				});
			},
			bindDeliveryOrderInfoList(){//绑定配送单 点击上移下移绑定配送单
				// that.deliveryBookOrderList = []
				var data = {}
				data.delivererId = uni.getStorageSync("DelivererID")
				data.companyId = uni.getStorageSync("CompanyID")
				if(that.deliverAreaId != 0){
					data.deliverAreaId = that.deliverAreaId //配送单大区ID
				}
				data.pageIndex = 0
				data.pageSize = 10000
				data.orderBy = "display_order"
				data.condition = "DATE(deliver_time) = '" + that.selectDay + "'";
				if(that.isShowUntreated == true){
					data.condition  += " and book_order_borrow_status_code ='Delivering'"
				}
				common.call('GetDeliveryOrderInfoList', data, function(result) { //配送单
					console.log(result.data)
					that.deliveryBookOrderList = result.data.dataList;
					for (let i = 0; i < that.deliveryBookOrderList.length; i++) {
						that.deliveryBookOrderList[i].open = false
					}
				});
			},
			bindAreaDeliveryOrderInfoList(deliverAreaId){//绑定配送单 点击大区绑定配送单
				that.deliverAreaId = deliverAreaId
				// that.deliveryBookOrderList = []
				var data = {}
				data.delivererId = uni.getStorageSync("DelivererID")
				data.companyId = uni.getStorageSync("CompanyID")
				if(that.deliverAreaId != 0){
					data.deliverAreaId = that.deliverAreaId //配送单大区ID
				}
				data.pageIndex = 0
				data.pageSize = 10000
				data.orderBy = "display_order"
				data.condition = "DATE(deliver_time) = '" + that.selectDay + "'";
				if(that.isShowUntreated == true){
					data.condition += " and book_order_borrow_status_code ='Delivering'"
				}
				common.call('GetDeliveryOrderInfoList', data, function(result) { //配送单
					console.log(result.data)
					that.deliveryBookOrderList = result.data.dataList;
					for (let i = 0; i < that.deliveryBookOrderList.length; i++) {
						that.deliveryBookOrderList[i].open = false
					}
				});
			},
			bindAreaBookOutOrderInfoList(bookOutOrderAreaId){//绑定拣书单 点击大区绑定拣书单
				that.bookOutOrderAreaId = bookOutOrderAreaId
				that.PickBookList=[];
				var data = {};
				data.bookpickerId = uni.getStorageSync("DelivererID");
				data.companyId= uni.getStorageSync("CompanyID")
				if(that.bookOutOrderAreaId != 0){
					data.deliverAreaId = that.bookOutOrderAreaId //拣书单大区ID
				}
				data.isOffline = false //线上
				data.orderBy = "deliver_area_id,deliver_area_number"
				data.condition = "DATE(deliver_time) = '" + that.selectDay + "'"
				data.pageSize = 1000
				if(that.isShowUntreated == true){
					data.bookOutOrderStatusCode = 'NotFound'
				}
				common.call('GetBookOutOrderInfoList', data, function(result) { ////拣书单
					that.PickBookList = result.data.dataList;
					for (let i = 0; i < that.PickBookList.length; i++) {
						var flg = false
						for (let j = 0; j < that.PickBookList[i].children.length; j++) {
							if (that.PickBookList[i].children[j].bookOutOrderStatusCode == "NotFound") {
								flg = true
								break;
							}
						}
						if (flg == true) {
							that.PickBookList[i].open = true
						} else {
							that.PickBookList[i].open = false
						}
					}
				});
			},
			onCheckboxChange(e){ //复选框变化事件
				that.isShowUntreated=!that.isShowUntreated
				console.log(that.isShowUntreated)
				that.bindDeliveryOrderInfoListAndBookOutOrderInfoList();//绑定配送单 绑定拣书单
			},
			bindDeliveryOrderInfoListAndBookOutOrderInfoList(){
				//绑定配送单
				var data = {}
				data.delivererId = uni.getStorageSync("DelivererID")
				data.companyId = uni.getStorageSync("CompanyID")
				if(that.deliverAreaId != 0){
					data.deliverAreaId = that.deliverAreaId //配送单大区ID
				}
				data.pageIndex = 0
				data.pageSize = 10000
				data.orderBy = "display_order"
				data.condition = "DATE(deliver_time) = '" + that.selectDay + "'";
				if(that.isShowUntreated == true){
					data.condition += " and book_order_borrow_status_code ='Delivering'"
				}
				common.call('GetDeliveryOrderInfoList', data, function(result) { //配送单
					console.log(result.data)
					that.deliveryBookOrderList = result.data.dataList;
					for (let i = 0; i < that.deliveryBookOrderList.length; i++) {
						that.deliveryBookOrderList[i].open = false
					}
				});
				//绑定拣书单
				that.PickBookList=[];
				var bookOutOrderData = {};
				bookOutOrderData.bookpickerId = uni.getStorageSync("DelivererID");
				bookOutOrderData.companyId= uni.getStorageSync("CompanyID")
				if(that.bookOutOrderAreaId != 0){
					bookOutOrderData.deliverAreaId = that.bookOutOrderAreaId //拣书单大区ID
				}
				bookOutOrderData.isOffline = false //线上
				bookOutOrderData.orderBy = "deliver_area_id,deliver_area_number"
				bookOutOrderData.condition = "DATE(deliver_time) = '" + that.selectDay + "'"
				bookOutOrderData.pageSize = 1000
				if(that.isShowUntreated == true){
					bookOutOrderData.bookOutOrderStatusCode = 'NotFound'
				}
				common.call('GetBookOutOrderInfoList', bookOutOrderData, function(result) { ////拣书单
					that.PickBookList = result.data.dataList;
					for (let i = 0; i < that.PickBookList.length; i++) {
						var flg = false
						for (let j = 0; j < that.PickBookList[i].children.length; j++) {
							if (that.PickBookList[i].children[j].bookOutOrderStatusCode == "NotFound") {
								flg = true
								break;
							}
						}
						if (flg == true) {
							that.PickBookList[i].open = true
						} else {
							that.PickBookList[i].open = false
						}
					}
				});
			}
		},
		filters: {
			toNull(val) {
				if (val == null) {
					return ""
				} else {
					return val
				}
			},
			toBookImageFilePath(item) {
				if (item == "undefined" || item == undefined) {
					return ''
				}
				if (item.imageFilePath != "" && item.imageFilePath != null) {
					return that.adminRootUrl + "/" + item.imageFilePath
				} else {
					return "../../static/default.jpg"
				}
			},
			toselectDay(val){
				let current = new Date();
				let y = current.getFullYear();
				let m = current.getMonth();
				let d = current.getDate();
				let day = y + '-' + that.formatNum(m + 1) + '-' + that.formatNum(d)
				if(val == day){
					return "今日"
				}else{
					return val
				}
			}
		}
	}
</script>

<style lang="scss">
	@import url("/css/common.css");

	page {
		background: #5EAC59;
	}
	
	.popupSty {
		width: 100%;
		overflow: hidden;

		>view {
			font-size: 36rpx;
			height: 50px;
			// border-top: 1px solid rgba(0, 0, 0, 0.1);
			display: flex;
			;
			align-items: center;
			;
			justify-content: center;
			// padding: 0 10rpx;
		}

	}

	.wayTable {
		flex-shrink: 1;
		overflow: auto;

		>view {
			margin: 10px 20px;
			background-color: rgb(198, 255, 205);
			border-radius: 8px;
			padding: 15px;
		}

		.bookcase {
			font-size: 15px;
			margin-bottom: 10px;
			margin-left: 5px;
		}

		table {
			width: 100%;
			background-color: #fff;
			border-collapse: collapse;

			td,
			th {
				text-align: center;
				border: 1px solid rgb(219, 219, 219);
				padding: 8px 0;
				font-size: 14px;
			}
		}
	}

	.wayType {
		margin: 10px 0;

		view {
			width: max-content;
			border-radius: 4px;
			font-size: 14px;
			padding: 5px 10px;
			background: #fff;
			float: right;
			margin-right: 20px;
		}
	}

	#NumInfo {
		.tips {
			color: rgb(58, 181, 74);
			margin-top: 5px;
			font-size: 18px;

			span {
				color: red;
			}
		}

		.openListView {
			border-top: 1px solid #eee;
			margin-top: 15px;
			overflow: hidden;
			;
			transition: all 0.3s;
		}

		.openView {
			display: flex;
			margin-top: 15px;

			.news {
				flex: 1;

				.title {
					font-size: 28rpx;
					display: -webkit-box;
					-webkit-box-orient: vertical;
					// -webkit-line-clamp: 2;
					overflow: hidden;
					font-weight: bold;
					line-height: 20px;
					// height: 40px;
				}
			}

			img {
				width: calc(30vw - 37px);
				height: calc(27vw);
				border-radius: 10px;
				overflow: hidden;
				flex-shrink: 0;
				margin-right: 10px;
			}

			.returnbook {
				width: 80px;
				background-color: #53d776;
				color: white;
			}

			.butt {
				width: 80px;
			}

			.cancelbooks {
				width: 80px;
				background-color: #facc3f;
				color: white;
			}
		}

		.top {
			display: flex;
			align-items: center;

			.item {
				flex: 1;
			}

			img {
				width: 20px;
				height: 20px;
				transition: transform 0.3s;
			}
		}

		.info {
			.item {
				display: flex;
				align-items: center;

				img {
					width: 18px;
					margin-top: 2px;
					margin-right: 6px;
				}
			}
		}
	}

	.listView {
		flex-shrink: 1;
		overflow: auto;
		padding: 20rpx;
		padding-top: 0;

		.liView {
			padding: 10px;
			background: #fff;
			border-radius: 20rpx;
			display: flex;
			margin-bottom: 10px;

			.butt {
				width: 100px;

				view {
					width: 90px;
					height: 30px;
					background: #FACC3F;
					border-radius: 6px;
					font-size: 13px;
					font-weight: 600; 
					margin-bottom: 30rpx;
					display: flex;
					align-items: center;
					justify-content: center;
				}
			}

			.item {
				color: #333333;
				font-size: 14px;
				padding: 4px 0;

				span {
					color: #999999;
				}
			}

			.tips {
				color: red;
				font-size: 14px;
				margin-top: 4px;
			}

			.info {
				flex: 1;

				>.item:nth-child(1) {
					font-size: 16px;

					span {
						// font-size: 14px;
					}
				}
			}
		}
	}

	.labelView {
		width: 100%;
		height: 80rpx;
		// border-bottom: 1px solid rgba(0, 0, 0, 0.1);
		// border-top: 1px solid rgba(0, 0, 0, 0.1);
		display: flex;
		;
		align-items: center;
		justify-content: center;
		font-size: 26rpx;
		position: relative;
		flex-shrink: 0;

		p {
			width: 50px;
			height: 2px;
			background: #ffffff;
			position: absolute;
			bottom: -1px;
			transition: left 0.3s;
		}

		.itemSty {
			width: 50%;
			text-align: center;
			display: flex;
			justify-content: center;

			.span {
				background: red;
				font-size: 12px;
				color: #fff;
				width: 20px;
				height: 20px;
				border-radius: 50%;
				display: flex;
				align-items: center;
				;
				justify-content: center;
			}

			.span1 {
				background: gray;
				font-size: 12px;
				color: #fff;
				width: 20px;
				height: 20px;
				border-radius: 50%;
				display: flex;
				align-items: center;
				;
				justify-content: center;
			}
		}

		.SeItem {
			color: #ffffff;
		}
	}

	.content-view {
		height: 100%;
		display: flex;
		flex-direction: column;
		// /* #ifdef MP-WEIXIN */
		// margin-top: 40px;
		// /* #endif */
		// /* #ifdef H5 */
		// margin-top: 0px;
		// /* #endif */
		// top: 55px;
		// z-index: 8;
		// position: absolute;
	}
</style>
<!-- 输入支付密码弹出层样式 -->
<style lang="scss">
	.calendarPopup {
		.mask {
			position: fixed;
			z-index: 999;
			top: 0;
			right: 0;
			left: 0;
			bottom: 0;
			background: rgba(0, 0, 0, .5);
		}

		.contents {
			position: fixed;
			z-index: 999;
			width: 80%;
			max-width: 300px;
			top: 50%;
			left: 50%;
			-webkit-transform: translate(-50%, -50%);
			transform: translate(-50%, -50%);
			background-color: #fff;
			text-align: center;
			border-radius: 3px;
			overflow: hidden;

			.content_hd {
				padding: 1em;

				.content_title {
					font-weight: 400;
					font-size: 18px;
					overflow: hidden;
					text-overflow: ellipsis;
					display: -webkit-box;
					-webkit-line-clamp: 2;
					-webkit-box-orient: vertical;
				}
			}

			input {
				width: 90%;
				margin: 0 5%;
				height: 40px;
				line-height: 40px;
				border: 1px solid #bfbfbf;
				margin-bottom: 10px;
			}

			.content_ft {
				position: relative;
				line-height: 48px;
				font-size: 18px;
				display: -webkit-box;
				display: -webkit-flex;
				display: flex;

				.content_btn {
					display: block;
					-webkit-box-flex: 1;
					-webkit-flex: 1;
					flex: 1;
					color: #3cc51f;
					text-decoration: none;
					-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
					position: relative;
					cursor: pointer;
				}

				.content_btn:after {
					content: " ";
					position: absolute;
					left: 0;
					top: 0;
					width: 1px;
					bottom: 0;
					border-left: 1px solid #d5d5d6;
					color: #d5d5d6;
					-webkit-transform-origin: 0 0;
					transform-origin: 0 0;
					-webkit-transform: scaleX(.5);
					transform: scaleX(.5);
				}

				.cancel {
					color: rgb(0, 0, 0);
				}

				.ok {
					color: rgb(0, 122, 255);
				}
			}

			.content_ft:after {
				content: " ";
				position: absolute;
				left: 0;
				top: 0;
				right: 0;
				height: 1px;
				border-top: 1px solid #d5d5d6;
				color: #d5d5d6;
				-webkit-transform-origin: 0 0;
				transform-origin: 0 0;
				-webkit-transform: scaleY(.5);
				transform: scaleY(.5);
			}

			.payMent-input {
				width: 90%;
				margin: 0 5%;
				height: 80upx;
				margin: 0 auto;
				border-right: 2upx solid #000000;
				border: 2upx solid #000000;
				position: relative;
				/* border-right: none; */
				margin-bottom: 10px;
			}

			.payMent-input-passInput {
				position: absolute !important;
				opacity: 0;
			}

			.payMent-input-item {
				width: 100%;
				height: 100%;

				image {
					width: 40px;
					height: 40px;
				}

				.password {
					border-right: 1upx solid #000000;
					width: 16.3%;
					text-align: center;
					height: 100%;
					float: left;

					&:last-child {
						border-right: 0px;
					}
				}

			}


		}
	}
</style>
<template>
	<view>

		<view class="container">
			<view class="suspendCardPanel">
				<view class="h30"></view>
				<view class="line50 f26 center suspendCardNum">
					可用停卡次数：{{stoppableCardQuantity}}次
				</view>
				<image :src="imageRootUrl + 'image/member/member.png'" class="memberimg"></image>
				<view class="h20"></view>
				<view class="line40 center shopMemberCar">
					慧至半径童书馆会员卡
				</view>
			</view>
			<view class="h20"></view>
			<!-- <view class="btnsuspendCard" @click="aaaaaaa();">
				解除停卡111111
			</view> -->
			<view v-if="memberCardDisableLogInfo ==''">
				<!-- <picker @change="bindPickerChange" :value="index" :range="suspendCardTypeList" range-key="name" ref="suspendCardType"> -->
					<view class="btnsuspendCard" @click="clickCancelTheCard()">
						办理停卡
					</view>
				<!-- </picker> -->
			</view>
			<view v-else>
				<view class="btnsuspendCard" @click="RelieveStopMemberCard();">
					解除停卡
				</view>
			</view>
			<view class="h20"></view>
			<view class="contentPanel" v-if="memberCardDisableLogInfo !=''">
				<view class="line90  f30 b bb">停卡信息</view>
				<view class="h10"></view>
				<view class="line50 f25 b">停卡开始时间：{{memberCardDisableLogInfo.stopDateText}}</view>
				<view class="line50 f25 b">停卡结束时间：{{memberCardDisableLogInfo.stopEndDateText}}</view>
				<view class="line50 f25 b">已暂停{{memberCardDisableLogInfo.days}}天</view>
			</view>
			<view class="h20"></view>
			<view class="contentPanel ">
				<view class="h10"></view>
				<view class="line60">
					<view style="float: left;">
						<image :src="imageRootUrl + 'image/member/suspendCardRecord.png'" class="size50"
							style="margin-top: 5rpx;"></image>
					</view>
					<view class="f30" style="float: left; margin-left: 5rpx;">
						停卡变更记录
					</view>
				</view>
				
				<view class="h10"></view>
				<view class="line110 recordPanel" v-for="(item,index) in list" :key="index">
					<view class="line100 center recordgreenleft">
						<view class="line50 f28 number">次数</view>
						<block v-if="item.type == 'augment'"> <!-- 赠送 -->
							<view class="line40 f36 numbergreen">+{{item.stop_card_num | toNull}}</view>
						</block>
						<block v-if="item.type == 'decrease'"> <!-- 使用 -->
							<view class="line40 f36 numberRed">-{{item.stop_card_num | toNull}}</view>
						</block>
					</view>
					<view class="recordright">
						<view class="h10"></view>
						<view class="line40 f28 recordRightTitle">{{item.type_name}}</view>
						<view class="line30 f24 recordRightTime">{{item.createTimeText | toTime}}</view>
						<view class="h20"></view>
					</view>
					<view class="h10"></view>
				</view>
			</view>
		</view>
		<uni-popup ref="popup" type="center" >
		   <uni-popup-dialog ref="inputClose" title="办理停卡" @confirm="dialogCancelTheCardConfirm" @close="dialogdialogCancelTheCardClose">
		        <view class="popupSty">
					<!-- <radio-group @change="radioChange"> -->
						<view style="" class="line80 suspendCardTypeList" v-for="(item,index) in suspendCardTypeList " :key="index" @click="radioChange(item.code)">
							<radio :value="item.code" :checked="index === current" /> {{item.name}}
						</view>
				   <!-- </radio-group> -->
		        </view>
		    </uni-popup-dialog>
		</uni-popup>
	</view>
</template>

<script>
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	let statusBarHeight = uni.getSystemInfoSync().statusBarHeight; // 状态栏 高度
	import popup from "@/components/uni-popup/uni-popup.vue";
	import popupdialog from "@/components/uni-popup-dialog/uni-popup-dialog.vue";
	import transition from "@/components/uni-transition/uni-transition.vue"; 
	let that = null;
	export default {
		data() {
			return {
				url: "http://admin.8630.net/WYB/UploadFile/headerTopbg.png",
				imageRootUrl: config.imageRootUrl, //图片地址
				statusBarHeight, // 状态栏 高度
				head_is_fixed: 0,
				suspendCardTypeList: [{
					code: '1',
					name: '停卡一次（30天)',
					days: 30
				}, {
					code: '2',
					name: '停卡二次（60天)',
					days: 60
				}, {
					code: '3',
					name: '停卡三次（90天)',
					days: 90
				}, ],
				index: 0,
				list: [],
				stoppableCardQuantity: 0, //停卡次数
				memberCardDisableLogInfo:"",
				current:0,
				isAntiShake:false,//防抖
			};
		},
		onReady() {

		},
		onShow() {

		},
		onPageScroll(e) {
			that.head_is_fixed = e.scrollTop > 0 ? 1 : 0
		},
		onLoad() {
			that = this;
			that.GetMemberCard(); //绑定停卡次数
			that.bindData(); //
			
		},
		methods: {
			GetMemberCard() {
				var data = {};
				data.id = common.getMemberId()
				common.call("GetViewMember", data, function(result) {
					//console.log(result.data.stoppableCardQuantity)
					if (result.data.stoppableCardQuantity != null) {
						that.stoppableCardQuantity = result.data.stoppableCardQuantity
					} else {
						that.stoppableCardQuantity = 0
					}
				});
			},
			bindData() {
				var data = {};
				data.memberId = common.getMemberId()
				data.pageSize = 10000
				data.orderBy = "create_time desc"
				common.call("GetViewMemberCardDisableLogList", data, function(result) {//获取停卡记录列表
					//console.log(result)
					that.list = result.data.dataList
					if(result.data.dataList.length > 0) {
						if(result.data.dataList[0].type =="decrease"){
							var current = Date.parse(new Date())//当前时间
							let startTime = Date.parse(new Date(result.data.dataList[0].stopDateText));//开始停卡时间
							let endTime =   Date.parse(new Date(result.data.dataList[0].stopEndDateText));//结束停卡时间
							// that.memberCardDisableLogInfo = result.data.dataList[0]
							console.log(startTime)
							console.log(endTime)
							console.log(current)
							if(result.data.dataList[0].isRelieve ==false){ //停卡状态未解除
								if(current >= startTime && current <= endTime){
									console.log('在范围内')
									that.memberCardDisableLogInfo = result.data.dataList[0]
									that.memberCardDisableLogInfo.days  = that.getDaysBetween(startTime, current)
									console.log(that.memberCardDisableLogInfo)
								}
							}
						}
					}
				});
			},
			getDaysBetween(sDate, eDate){
				// const sDate = Date.parse(startDate)
				// const eDate = Date.parse(enDate)
				// console.log(sDate)
				// console.log(eDate)
				if (sDate > eDate) {
				  return 0
				}
				// 这个判断可以根据需求来确定是否需要加上
				if (sDate === eDate) {
				  return 1
				}
				const days = (eDate - sDate) / (1 * 24 * 60 * 60 * 1000)
				return days.toFixed(0)
			},
			bindPickerChange: function(e) { //
				console.log('picker发送选择改变，携带值为', e.detail.value)
				console.log(that.suspendCardTypeList[e.detail.value].days)
				var data = {};
				data.tableName = "member_card";
				data.condition = "member_id = " + common.getMemberId() + " and disabled is not true"
				common.call("GetMemberCardList", data, function(result) {
					console.log(result.data.dataList)
					if (result.data.dataList.length > 0) {
						that.UpdateMemberCardStop(e.detail.value,result.data.dataList[0].id);
						
					}else{
						uni.showModal({
							title: '提示',
							content: '请先开通会员',
							showCancel: false,//没有取消按钮的弹框
							success: function (res) {
								if (res.confirm) {
									console.log('用户点击确定');
								} 
						}});
					}
				});
			},
			RelieveStopMemberCard(){//解除停卡
				console.log(that.memberCardDisableLogInfo)
				var data = {};
				data.memberCardDisableLogId   = that.memberCardDisableLogInfo.id;
				data.offDays = that.memberCardDisableLogInfo.days;
				common.call("RelieveStopMemberCard", data, function(result) {
					uni.showModal({
						title: '提示',
						content: '解除成功！',
						showCancel: false,//没有取消按钮的弹框
						success: function (res) {
							if (res.confirm) {
								uni.navigateBack();
							} 
						},
					});
				});
				console.log(data)
			},
			UpdateMemberCardStop(value,memberCardId){ //办理停卡
				// 创建一个新的Date对象
				var currentTime = new Date()
				// 获取年份、月份（注意需要加1）、日期、小时、分钟和秒数
				var stopYear = currentTime.getFullYear(); // 2023
				var stopMonth = currentTime.getMonth() + 1; // 9 (表示九月)
				var stopDay = currentTime.getDate(); // 5
				var stopHour = currentTime.getHours(); // 8
				var stopMinute = currentTime.getMinutes(); // 47
				var stopSecond = currentTime.getSeconds(); // 36
				var stopStopTime = stopYear + "-" + stopMonth + "-" + stopDay + " " + stopHour + ":" + stopMinute + ":" + stopSecond
				var endTime = currentTime.setDate(currentTime.getDate() + 30);
				var endYear = currentTime.getFullYear(); // 2023
				var endMonth = currentTime.getMonth() + 1; // 9 (表示九月)
				var endDay = currentTime.getDate(); // 5
				var endHour = currentTime.getHours(); // 8
				var endMinute = currentTime.getMinutes(); // 47
				var endSecond = currentTime.getSeconds(); // 36
				var stopEndTime = endYear + "-" + endMonth + "-" + endDay + " " + endHour + ":" + endMinute + ":" + endSecond
					
				var data = {};
				data.memberId = common.getMemberId() //会员id
				data.memberCardId = memberCardId //会员卡id
				data.stopDate = stopStopTime //停卡开始日期
				data.days = that.suspendCardTypeList[value].days //停卡时长
				data.remark = "" //备注
				data.stopEndDate = stopEndTime //停卡结束日期
				data.source = 'MemberStopCard' //管理员停卡AdminStopCard，会员自助办理停卡MemberStopCard
				data.type = "decrease"
				data.status="transact"
				if(that.suspendCardTypeList[value].days == 30){
					data.stopCardNum = 1;
				}else if(that.suspendCardTypeList[value].days ==60){
					data.stopCardNum = 2;
				}else if(that.suspendCardTypeList[value].days ==90){
					data.stopCardNum = 3;
				}
				console.log(data)
				common.call("UpdateMemberCardStop", data, function(result) {
					uni.showModal({
						title: '提示',
						content: '办理成功！',
						showCancel: false,//没有取消按钮的弹框
						success: function (res) {
							if (res.confirm) {
								wx.requestSubscribeMessage({
									tmplIds: ['43yYs8iYed4EE4nHKuG7RRvrmOBP-Y0VcnqNYmrmioo'],
									success (ress) { 
										
									}
								})
							} 
						},
					});
					setTimeout(() => {
						that.isAntiShake = false
						that.GetMemberCard(); //绑定停卡次数
						that.bindData(); //
						that.current = 0
						that.$refs.popup.close()
					}, 1000);
				}, function(e) {
					console.log(e)
					if (e.success == false) {
						that.isAntiShake = false
						uni.showModal({
							title: "提示",
							content: e.errorMessage,
							showCancel: false,
							confirmText: "确定",
						});
					}
				});
			},
			clickCancelTheCard(){//办理停卡弹框
				this.$refs.popup.open();
			},
			clickToBack() {
				uni.navigateBack();
			},
			radioChange(code) {
				for (let i = 0; i < this.suspendCardTypeList.length; i++) {
					if (this.suspendCardTypeList[i].code === code) {
						this.current = i;
						break;
					}
				}
			},
			dialogCancelTheCardConfirm(){ //办理停卡提交
				if(that.isAntiShake == true){
					return
				}
				that.isAntiShake = true
				console.log(that.current)
				var data = {};
				data.tableName = "member_card";
				data.condition = "member_id = " + common.getMemberId() + " and disabled is not true"
				common.call("GetMemberCardList", data, function(result) {
					console.log(result.data.dataList)
					if (result.data.dataList.length > 0) {
						that.UpdateMemberCardStop(that.current,result.data.dataList[0].id);
						
					}else{
						uni.showModal({
							title: '提示',
							content: '请先开通会员',
							showCancel: false,//没有取消按钮的弹框
							success: function (res) {
								if (res.confirm) {
									this.current = 0
									that.isAntiShake = false
									this.$refs.popup.close()
									console.log('用户点击确定');
								} 
						}});
					}
				});
			},
			dialogdialogCancelTheCardClose(){//办理停卡取消
				// 关闭窗口后，恢复默认内容
				this.$refs.popup.close()
				this.current = 0
				console.log(that.current)
			},
		},
		filters: {
			toTime: function(value) {
				var date = new Date(value);
				var year = date.getFullYear()
				var month = date.getMonth() + 1
				var day = date.getDate();
				return year + "年" + month + "月" + day + "日"
			},
			toNull(val) {
				if (val == null) {
					return ""
				} else {
					return val
				}
			}
		}
	};
</script>

<style lang="scss">
	@import url("/css/common.css");

	page {
		background: #5EAC59;
	}

	.container {}

	.suspendCardPanel {
		background: -webkit-linear-gradient(left, #FFD25E, #FFF4CD, #FFD540);
		width: 672rpx;
		height: 392rpx;
		border-radius: 40rpx;
		text-align: center;
	}

	.suspendCardNum {
		background-color: #EEBA00;
		color: #FFFFFF;
		border-radius: 60rpx;
		width: 270rpx;
		margin-left: 40rpx;
	}

	.memberimg {
		width: 200rpx;
		height: 190rpx;
		border-radius: 40rpx;
	}

	.shopMemberCar {
		color: #AA5200;
		font-weight: bold;
	}

	.btnsuspendCard {
		background-color: #FE613D;
		text-align: center;
		height: 80rpx;
		font-size: 32rpx;
		font-weight: 600;
		color: #FFFFFF;
		line-height: 80rpx;
		border-radius: 50rpx;
	}

	.recordPanel {
		border-bottom: 1rpx solid #F3F3F3;
		margin-top: 20rpx;
	}

	.recordgreenleft {
		float: left;
		width: 100rpx;
		background: rgba(83, 215, 118, 0.1);
		border-radius: 16rpx;
	}

	.recordRedLeft {
		float: left;
		width: 100rpx;
		background: rgba(254, 97, 61, 0.1);
		border-radius: 16rpx;
	}

	.number {
		font-weight: 600;
	}

	.numbergreen {
		color: #53D776;
		font-weight: bold;
	}

	.numberRed {
		color: #FE613D;
		font-weight: bold;
	}

	.recordright {
		float: left;
		margin-left: 20rpx;
	}

	.recordRightTitle {
		font-weight: 600;
	}

	.recordRightTime {
		color: #999999;
		font-weight: bold;
	}
	.bb{
		border-bottom: 1rpx solid #F3F3F3;
	}
	.popupSty {
	    width: 100%; 
	    overflow: hidden;
	    >view {
	        font-size: 14px;
	        height: 50px;
	        border-top: 1px solid rgba(0, 0, 0, 0.1);
	        display: flex;;
	        align-items: center;;
	        justify-content: center;
	        padding: 0 10px;
	    }
		.suspendCardTypeList{
			border-top: 1px solid #e5e5e5;
			&:last-child {
				border-bottom:1px solid #e5e5e5;
			}
		}
		
	}
</style>
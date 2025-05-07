<template>
	<view>
		<view class="container">
			<image :src="adminRootUrl + clubActivityInfo.detailImageFilePath" style="width: 100%;height: 30.7vw; border-radius: 20rpx;"></image>
				
			<view class="h20"></view>
			<view class="contentPanel">
				<view class="f30 ellipsis" style="font-weight: bold;">{{clubActivityInfo.name}}</view>
				<view class="f26 ellipsis line50"><text class="text f26">活动截止时间：</text><text class="f26"
						style="font-weight: 600;color: #333333;">{{clubActivityInfo.expiryDateText | toTime}}</text>
				</view>
				<view class="f26 ellipsis" style="line-height: 40rpx;"><text class="text f26">活动地点：</text><text
						class="f26" style="font-weight: 600;color: #333333;">{{clubActivityInfo.address | toNull}}</text></view>
				<view class="f26 ellipsis line50"><text class="text f26">报名费：</text><text class="f26"
						style="font-weight: 600;color: #333333;">￥{{clubActivityInfo.cost | toAmount}}</text></view>
			</view>
			<view class="h20"></view>
			<view class="contentPanel" style="margin-top: 60rpx;">
				<view class="line70 highlight160Panel">
					<view class="line60 center highlight160">
						活动介绍
					</view>
				</view>
				<view>
					<!-- <rich-text :nodes="clubActivityInfo.content"></rich-text> -->
					<!-- <mp-html container-style="padding:20px" :content="clubActivityInfo.content" domain="https://mp-html.oss-cn-hangzhou.aliyuncs.com" lazy-load scroll-table selectable use-anchor ::tag-style="tagStyle" /> -->
					<mp-html :content="clubActivityInfo.content" />
				</view>
			</view>
			<view class="h180"></view>
		</view>

		<view class="btn" v-if="type == 'home'">
			<view class="back" @tap="clickToBack()">返回</view>
			<view class="signUp" @tap="clickPublish()">报名</view>
		</view>
		
		<uni-popup ref="popup" type="center" >
		   <uni-popup-dialog ref="inputClose" title="报名信息" @confirm="dialogInputConfirm">
		        <view class="popupSty">
		           <input class="nickname" type="text"  v-model="memberName"  placeholder="请输入孩子姓名" style="border: 1px solid #ddd; height: 80rpx; border-radius: 20rpx;"/>
				   <input class="nickname" type="number"  v-model="memberPhone"  placeholder="请输入手机号" style="border: 1px solid #ddd; height: 80rpx; border-radius: 20rpx; margin-top: 20rpx;"/>
		        </view>
		    </uni-popup-dialog>
		</uni-popup>
	</view>
</template>

<script>
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	import mpHtml from '@/components/mp-html/mp-html'
	import popup from "@/components/uni-popup/uni-popup.vue";
	import popupdialog from "@/components/uni-popup-dialog/uni-popup-dialog.vue";
	import transition from "@/components/uni-transition/uni-transition.vue"; 
	let statusBarHeight = uni.getSystemInfoSync().statusBarHeight; // 状态栏 高度
	let that = null;
	export default {
		data() {
			return {
				html: '<div>Hello World!</div>',
				url: "http://admin.8630.net/WYB/UploadFile/headerTopbg.png",
				imageRootUrl: config.imageRootUrl, //图片地址
				adminRootUrl: config.adminRootUrl + "/", //后端图片地址
				statusBarHeight, // 状态栏 高度
				head_is_fixed: 0,
				id: "", //活动ID
				clubActivityInfo: {}, //活动详情
				type:'',
				tagStyle: {
				  table: 'box-sizing: border-box; border-top: 1px solid #dfe2e5; border-left: 1px solid #dfe2e5;',
				  th: 'border-right: 1px solid #dfe2e5; border-bottom: 1px solid #dfe2e5;',
				  td: 'border-right: 1px solid #dfe2e5; border-bottom: 1px solid #dfe2e5;',
				  li: 'margin: 5px 0;'
				},
				isAntiShake:false,//防抖
				memberName:'',//孩子姓名
				memberPhone:'',//手机号
			};
		},
		components:{
			mpHtml,
			popup,
			popupdialog,
			transition
		},
		onReady() {

		},
		onShow() {
			wx.showShareMenu({
				withShareTicket:true,
				//设置下方的Menus菜单，才能够让发送给朋友与分享到朋友圈两个按钮可以点击
				menus:["shareAppMessage","shareTimeline"]
			})
		},
		onPageScroll(e) {
			that.head_is_fixed = e.scrollTop > 0 ? 1 : 0
		},
		onLoad(option) {
			that = this;
			that.id = option.id
			that.type = option.type
			that.bindData(); //
		},
		methods: {
			bindData() {
				var data = {};
				data.id = that.id
				common.call("GetViewClubActivity", data, function(result) {
					console.log(result)
					that.clubActivityInfo = result.data
					if (that.clubActivityInfo.content != "" && that.clubActivityInfo.content != null) {
						var str = that.clubActivityInfo.content
						let reg = new RegExp('<img', 'g') //g代表全部
						console.log(reg)
						let newMsgs = str.replace(reg, '<img width="100%" ');
						console.log(newMsgs)
						that.clubActivityInfo.content = newMsgs
					} else {
						that.clubActivityInfo.content = ""
					}
					console.log(that.clubActivityInfo.content)
				});
				var datas = {};
				datas.id = common.getMemberId();
				common.call("GetViewMember", datas, function(result) {
					that.memberName = result.data.name
					that.memberPhone = result.data.mobilePhone
				});
			},
			clickToBack() {
				uni.navigateBack();
			},
			clickPublish() { //报名
				var isValid = common.checkLogin();
				if (!isValid) {
					return;
				}
				this.$refs.popup.open();
			},
			dialogInputConfirm(){ //姓名提交提交
				if(that.memberName ==null || that.memberName ==""){
					uni.showToast({
						title: '请输入姓名',
						icon: "none",
						duration:1500
					});
					return
				}
				if(that.memberName == "微信用户"){
					uni.showToast({
						title: '请输入姓名',
						icon: "none",
						duration:1500
					});
					return
				}
				if(that.memberPhone ==null || that.memberPhone ==""){
					uni.showToast({
						title: '请输入手机号',
						icon: "none",
						duration:1500
					});
					return
				}
				if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(this.memberPhone))) {
					uni.showToast({
						title: '请填写正确手机号码',
						icon: "none"
					});
					return false;
				}
				if(that.isAntiShake == true){
					return
				}
				that.isAntiShake = true
				console.log(that.clubActivityInfo.expiryDateText)
				var time1 = new Date(); // 当前时间
				var time2 = new Date(that.clubActivityInfo.expiryDateText); // 截止时间
				if (time1 > time2) {
				    uni.showToast({
				    	title: '已过截止时间',
				    	icon: "none",
						duration:2500
				    });
					return
				} 
				console.log(util.getFormatTime(new Date()).replace("-","").replace(":","").replace("-","").replace(":","").replace(" ",""))
				// return
				var data = {};
				data.retailOrderNo = util.getFormatTime(new Date()).replace("-","").replace(":","").replace("-","").replace(":","").replace(" ",""); //订单编号
				data.companyId = common.getCommunityId(); //门店ID
				data.memberId = common.getMemberId(); //会员ID
				data.clubActivityId = that.id; //活动ID
				//data.storyVideoId     // 单个视频购买ID                   不用传
				//data.nightStoryTypeId // 晚安故事购买类型ID//单个视频不传 不用传
				//data.nightStoryDays   // 晚安故事订阅天数//单个不传       不用传
				data.businessDate = util.getFormatTime(new Date()) //订单日期
				data.amount = that.clubActivityInfo.cost;
				data.retailOrderTypeCode = "ClueActivity"   //零售单状态编码   //ClueActivity俱乐部活动）  （NightStory订阅晚安故事）  （StoryVideo购买一个视频）
				data.retailOrderStatusCode = "Paying"//零售单类型编码（Paying）
				data.name = that.memberName;
				data.mobilePhone = that.memberPhone;
				common.call('AddRetailOrder', data, function(res) {
					common.payByWxa({
						openId: uni.getStorageSync("wxOpenId"),//openID
						description: '俱乐部活动支付', //内容
						outTradeNo: data.retailOrderNo, //订单编号
						amount: that.clubActivityInfo.cost, //金额
						// amount: 0.1, //金额
						notifyUrl: 'https://admin.brp.rome8.com/api/WxPayNotify',//回调地址
						success: function(e) {//支付成功
							console.log(e)
							uni.showToast({
								title: '报名成功',
								icon: "none",
								duration:5000
							});
							that.isAntiShake = false
						},
						fail: function() {//支付失败
							that.isAntiShake = false
							uni.redirectTo({
								url: "/pages/member/index",
							});
						}
					})
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
		},
		filters: {
			toTime: function(value) {
				var date = new Date(value);
				var year = date.getFullYear()
				var month = date.getMonth() + 1
				var day = date.getDate();
				return year + "年" + month + "月" + day + "日"
			},
			toAmount: function(value) {
				if (value == null || value == "") {
					return 0;
				} else {
					return value.toFixed(2);
				}
			},
			toNull(val){
				if(val ==null){
					return ""
				}else{
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

	.text {
		color: #999999;
	}

	.ellipsis {
		text-overflow: ellipsis;
		overflow: hidden;
		-webkit-line-clamp: 2;
		display: -webkit-box;
		-webkit-box-orient: vertical;
	}

	.highlight160Panel {
		background: white;
		border-radius: 15px;
		padding: 2px;
		position: relative;
		top: -45rpx;
		width: 160rpx;
		margin-left: -20rpx;
	}

	.highlight160 {
		background: #FFD965;
		border-radius: 15px;
		border-bottom-left-radius: 7px;
		border-bottom-right-radius: 7px;
		font-size: 28rpx;
		font-weight: bold;
		color: #000000;
	}

	.btn {
		margin-top: 20px;
		bottom: 0;
		width: 100%;
		height: 120rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		position: fixed;
		width: 100%;
		height: 150rpx;
		background-color: #ffffff;
		bottom: 0;
		z-index: 8;
	}

	.back {
		color: #FACC3F;
		width: 120px;
		height: 80rpx;
		border-radius: 10rpx;
		justify-content: center;
		align-items: center;
		font-size: 27rpx;
		line-height: 80rpx;
		text-align: center;
		margin-left: 20rpx;
		margin-right: 20rpx;
		border: 1px solid #FACC3F;
		border-radius: 448rpx;
		font-weight: bold;
		font-size: 30rpx;

	}

	.signUp {
		width: 120px;
		height: 80rpx;
		border-radius: 10rpx;
		justify-content: center;
		align-items: center;
		font-size: 27rpx;
		line-height: 80rpx;
		text-align: center;
		margin-left: 20rpx;
		margin-right: 20rpx;
		background: #FACC3F;
		border-radius: 448rpx;
		font-weight: bold;
		font-size: 30rpx;
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
	}
</style>
<template>
	<view>
		<view class="container center">
			<view class="h50"></view>
			<image show-menu-by-longpress="true" :src="customerServiceImageFilePath"  mode="widthFix" style="width: 100%;"></image>
			<!-- <view class="center line50 c_white">会员信息填写完毕</view> -->
			<view class="center line100 c_white" style="font-weight: bold;">长按识别二维码，加客服微信完成注册。</view>
			<view class="h40"></view>
			<view class="finish"  @click="clickToUserIndex()">完成</view>
			<!-- <view class="next" @click="clickToNext()">下一步</view> -->
		</view>
	</view>
</template>

<script>
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	let statusBarHeight = uni.getSystemInfoSync().statusBarHeight; // 状态栏 高度
	let that = null;
	export default {
		data() {
			return {
				url:"http://admin.8630.net/WYB/UploadFile/headerTopbg.png",
				imageRootUrl:config.imageRootUrl,//图片地址
				adminRootUrl:config.adminRootUrl +"/",//后端图片地址
				statusBarHeight,// 状态栏 高度
				head_is_fixed:0,
				customerServiceImageFilePath:'',
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
			that.bindData(); //
		},
		methods: {
			bindData(){
				var data = {};
				data.id = common.getCommunityId()
				common.call("GetViewCompany", data, function(result) {
					console.log(result.data)
					that.customerServiceImageFilePath = that.adminRootUrl + result.data.customerServiceImageFilePath
					console.log(that.customerServiceImageFilePath)
				});
			},
			clickToBack(){
				uni.navigateBack();
			},
			clickToUserIndex(){
				setTimeout(() => {
					uni.switchTab({
						url: "/pages/user/index" 
					})
				}, 200);
			}
		},
	};
</script>

<style lang="scss">
	@import url("/css/common.css");
	page {
		background: #5EAC59;
	}
	.container{
		/* #ifdef MP-WEIXIN */
			margin-top: 40px;
		/* #endif */
		/* #ifdef H5 */     
			margin-top: 0px;
		/* #endif */
		z-index: 8;
	}
	.finish {
		width: 120px;
		height: 80rpx;
		border-radius: 10rpx;
		justify-content: center;
		align-items: center;
		font-size: 26rpx;
		line-height: 80rpx;
		text-align: center;
		margin-left: 20rpx;
		margin-right: 20rpx;
		background: #FACC3F;
		border-radius: 448rpx;
		font-weight: bold;
		font-size: 28rpx;
		margin: 0 auto;
		color: #ffffff;
	}
	
</style>
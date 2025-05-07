<template>
	<view>
		<view class="" style="padding-top:0"> <!-- 顶部内容 -->
			<image class="topHeadbakimg" :src= "imageRootUrl + 'image/common/headerTopbg.png'"  mode="widthFix"></image> <!-- mode="widthFix" --> 	<!-- 顶部背景图片 -->
			<view class="head-content-fixed " :style="{'background':head_is_fixed == 1?'#FACC3F':''}" style="border-bottom: 1rpx solid rgba(0,0,0,0.1);"> 
				<view class="head-content-fixed-content " >
				   <view  class="head-top-nav-title center">
						<view class="headBack"  >
							<image :src= "imageRootUrl + 'image/common/back.png'" style="width: 18rpx; height:32rpx" @click="clickToBack"></image>
						</view>
						会员卡变更记录 
				   </view>
				</view>
			</view>
		</view>
		<view class="container">
			
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
				imageRootUrl:config.imageRootUrl,//图片地址
				statusBarHeight,// 状态栏 高度
				head_is_fixed:0,
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
				
			},
			clickToBack(){
				uni.navigateBack();
			},
		},
	};
</script>

<style lang="scss">
	@import url("/css/common.css");
	page {
		background: #FACC3F;
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
</style>
<template>
	<view>
		 <!-- 顶部内容 -->
		<view class="home-top-nav-content" style="padding-top:0">
			<!-- 顶部背景图片 -->
			<image class="topHeadbakimg" mode="widthFix" :src= "imageRootUrl + 'image/common/headerTopbg.png'" ></image>
			<view class="search-content-fixed " :style="{'background':head_is_fixed == 1?'#FACC3F':''}">
				<view class="search-content-fixed-content padding-left-main" style="padding-top: 50px;">
				   <view  class="home-top-nav-logo-title cr-white single-text">标题</view>
				</view>
			</view>
		</view>
		<view class="container">
			<view class="line80">
				<view class="line80" style="float:left; "><image src="../../static/image/member/head.png" style="width: 80rpx; height: 80rpx;"></image></view>
				<view class="line80 f28" style="float:left; margin-left: 10rpx; font-weight: bold;">昵称：雨晴</view>
			</view>
			<view class="h40"></view>
			<view class="contentPanel">
				<view class="h10"></view>
				<view class="line190 userinfobb">
					<view class="left40 ">
						<view class="line45 f28"><text class="f28 userinfotext">会员：</text><text class="f28 userinfovaluetext">张雨晴</text></view>
						<view class="line45 f28"><text class="f28 userinfotext">编号：</text><text class="f28 userinfovaluetext">1684168</text></view>
						<view class="line45 f28"><text class="f28 userinfotext">会员积分：</text><text class="f28 userinfovaluetext">100</text></view>
						<view class="line45 f28"><text class="f28 userinfotext">服务商：</text><text class="f28 userinfovaluetext">钟书阁</text></view>
					</view>
					<view class="left60 ">
						<view class="line50 f28" style="font-weight: 600;">会员权益</view>
						<view class="line65">
							<view class="line50 userinfobtn">图书借阅</view>
							<view class="line50 userinfobtn">配送到家</view>
						</view>
						<view class="line65">
							<view class="line50 userinfobtn">俱乐部会员</view>
							<view class="line50 userinfobtn">晚安故事</view>
						</view>
					</view>
					<view class="h10"></view>
				</view>
				<view class="line60">
					<view class="left50 starTime f24">开卡时间：2023年11月12日</view>
					<view class="left50 endTime  f24">到期时间：2023年11月12日</view>
				</view>
				<view class="line40">
					<view class="left50 f24"><text class="f24 userinfotext">还能继续借阅：</text><text class="f24 userinfovaluetext">365天</text></view>
					<view class="left50 f24"><text class="f24 userinfotext">一次最多可借阅：</text><text class="f24 userinfovaluetext">3本</text></view>
				</view>
			</view>
			<view class="h40"></view>
			<view class="contentPanel">
				<view class="line140">
					<view class="left45 line80 onloanOrsavedPanel">
						<view style="float: left;">
							<image src="../../static/image/member/onloan.png" style=" width: 80rpx; height: 80rpx;"></image>
						</view>
						<view class="onloanTextPanel">
							<view class="line40"><text class="f36 onloantext">29</text><text class="f24">本</text></view>
							<view class="line40 f24">已借阅</view>
						</view>
					</view>
					<view class="left4 line80 onloanOrsavedPanel bleft"></view>
					<view class="left50 line80 onloanOrsavedPanel" >
						<view style="float: left;">
							<image src="../../static/image/member/saved.png" style=" width: 80rpx; height: 80rpx;"></image>
						</view>
						<view class="savedTextPanel">
							<view class="line40"><text class="f36 savedtext">298.54</text><text class="f24">元</text></view>
							<view class="line40 f24">已节省买书费用</view>
						</view>
					</view>
				</view>
			</view>
			<view class="h40"></view>
			<view class="contentPanel h600">
				<view class="left33 h200">
					<view class="line_150 center">
						<image src="../../static/image/member/jfmx.png" class="size120" style=" margin-top: 35rpx;">
						</image>
					</view>
					<view class="line_50 center f28">积分明细</view>
				</view>
				<view class="left33 h200">
					<view class="line_150 center">
						<image src="../../static/image/member/jlb.png" class="size120" style=" margin-top: 35rpx;">
						</image>
					</view>
					<view class="line_50 center f28">俱乐部活动</view>
				</view>
				<view class="left33 h200">
					<view class="line_150 center">
						<image src="../../static/image/member/wags.png" class="size120" style=" margin-top: 35rpx;">
						</image>
					</view>
					<view class="line_50 center f28">晚安故事</view>
				</view>
				<view class="left33 h200">
					<view class="line_150 center">
						<image src="../../static/image/member/sc.png" class="size120" style=" margin-top: 35rpx;">
						</image>
					</view>
					<view class="line_50 center f28">我的收藏</view>
				</view>
				<view class="left33 h200">
					<view class="line_150 center">
						<image src="../../static/image/member/notice.png" class="size120" style=" margin-top: 35rpx;">
						</image>
					</view>
					<view class="line_50 center f28">可借通知</view>
				</view>
				<view class="left33 h200">
					<view class="line_150 center">
						<image src="../../static/image/member/jy.png" class="size120" style=" margin-top: 35rpx;">
						</image>
					</view>
					<view class="line_50 center f28">借阅记录</view>
				</view>
				<view class="left33 h200">
					<view class="line_150 center">
						<image src="../../static/image/member/tk.png" class="size120" style=" margin-top: 35rpx;">
						</image>
					</view>
					<view class="line_50 center f28">办理停卡</view>
				</view>
				<view class="left33 h200">
					<view class="line_150 center">
						<image src="../../static/image/member/bg.png" class="size120" style=" margin-top: 35rpx;">
						</image>
					</view>
					<view class="line_50 center f28">会员卡变更记录</view>
				</view>
			</view>
			<view class="h150"></view>
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
		},
	};
</script>

<style lang="scss">
	@import url("/css/common.css");
	@import './index.css';
	page {
		background: #FACC3F;
	}
	.container{
		 margin-top: 40px;z-index: 8;
	}
	.userinfobb{
		border-bottom: 1rpx solid #F3F3F3;
	}
	.userinfotext{
		color: #999999;
	}
	.userinfovaluetext{
		color: #333333;
	}
	.userinfobtn{
		width: 170rpx;
		height: 50rpx;
		background: #FACC3F; 
		border-radius: 12rpx;
		opacity: 1;
		font-size: 26rpx;
		text-align: center;
		line-height: 50rpx;
		margin-top: 7rpx;
		float: left;
		margin-right: 10rpx;
		font-weight: 600;
	}
	.starTime{
		color: #53D776;
	}
	.endTime{
		color: #FE613D;
	}
	.onloanOrsavedPanel{
		margin-top: 30rpx;
	}
	.bleft{
		border-left: 1px solid #F3F3F3;
	}
	.onloanTextPanel{
		float: left; margin-left: 10rpx;
	}
	.savedTextPanel{
		float: left; margin-left: 10rpx;
	}
	.onloantext{
		color: #FE613D;font-weight: bold;
	}
	.savedtext{
		color: #53D776;font-weight: bold;
	}
</style>
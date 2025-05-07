<template>
	<view>
		<view class="topTabBar">
			<view class="grid" v-for="(item,tbIndex) in typeList" :key="tbIndex" @tap="showType(tbIndex)">
				<view class="text" :class="[tbIndex==tabbarIndex?'on':'']">{{item}}</view>
			</view>
		</view>
		<view class="container">
			<!-- #ifdef MP-WEIXIN -->
				<view class="h70"></view>
			<!-- #endif -->
			<!-- #ifdef H5 -->    
				<view class="h70"></view>
			<!-- #endif -->

			
			<view class="tow-list">
				<view class="item" v-for="(item,index) in storyVideoList" :key="index" @tap="toGoodNightStoryDetail(item,index)">   <!-- aspectFill -->
					<image mode="aspectFill" :src="item | toVideoImage" style="width: 345rpx; height: 345rpx;"></image>
					<view class="name">{{ item.storyVideoTitle }}</view>
					<view class="time">{{ item.createTimeText }}</view>
				</view>
			</view>
			<view class="clear h40"></view>
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
				adminRootUrl: config.adminRootUrl + "/", //后端图片地址
				statusBarHeight,// 状态栏 高度
				head_is_fixed:0,
				tabbarIndex: 0,
				typeList: ['看过', '喜欢', '收藏', '付费故事'], //选项卡
				list: [
					{id: 0,img: config.imageRootUrl + 'image/common/a7.jpg',name: '晚安故事1晚安故事1晚安故事1',time: '2023年12月12日'},
					{id: 0,img: config.imageRootUrl + 'image/common/a8.jpg',name: '晚安故事2',time: '2023年12月12日'},
					{id: 0,img: config.imageRootUrl + 'image/common/a11.jpg',name: '晚安故事3',time: '2023年12月12日'},
					{id: 0,img: config.imageRootUrl + 'image/common/a12.jpg',name: '晚安故事4',time: '2023年12月12日'},
					{id: 0,img: config.imageRootUrl + 'image/common/a17.jpg',name: '晚安故事5',time: '2023年12月12日'},
					{id: 0,img: config.imageRootUrl + 'image/common/a18.jpg',name: '晚安故事6',time: '2023年12月12日'},
				],
				storyVideoList:[],//晚安故事列表
				loadingText: "正在加载...",
				showLoadMore: false,
				pageIndex: 0, //页数
				pageSize: 10, //条数
				pageCount: 0, //最大页数
			};
		},
		onReady() {
			
		},
		onShow() {
			
		},
		onPageScroll(e) {
		    that.head_is_fixed = e.scrollTop > 0 ? 1 : 0
		},
		onPullDownRefresh() { //刷新   //下拉刷新，需要自己在page.json文件中配置开启页面下拉刷新 "enablePullDownRefresh": true
			uni.stopPullDownRefresh();
		},
		onReachBottom() {  //上拉加载，需要自己在page.json文件中配置"onReachBottomDistance"
			if ((that.pageIndex + 1) == that.pageCount) {
				that.loadingText = "没有更多数据了";
			} else if ((that.pageIndex + 1) < that.pageCount) {
				that.pageIndex = that.pageIndex + 1;
				this.showLoadMore = true;
				that.loadingText = "加载更多";
				that.bindStoryVideoList();
			}
		},
		onLoad() {
			that = this;
			that.bindStoryVideoList(); //
		},
		methods: {
			bindStoryVideoList(){
				var serviceName = ""
				if(that.tabbarIndex == 0){ // 看过
					serviceName = "GetViewMemberBrowsedStoryVideoList"
				}else if(that.tabbarIndex == 1){//喜欢
					serviceName = "GetViewMemberLikeStoryVideoList"
				}else if(that.tabbarIndex == 2){//收藏
					serviceName = "GetViewMemberCollectStoryVideoList"
				}else if(that.tabbarIndex == 3){//付费故事
					serviceName = "GetViewRetailOrderList"
				}
				var data = {};
				data.pageIndex = that.pageIndex
				data.pageSize = that.pageSize
				data.memberId = common.getMemberId()
				if(that.tabbarIndex == 3){
					data.condition = "( retail_order_type_code='StoryVideo') and retail_order_status_code='Paid'"  //只查处支付一元的列表    retail_order_type_code='NightStory' or
				}
				data.orderBy = "create_time desc"
				common.call(serviceName, data, function(result) {
					console.log(result)
					that.storyVideoList.push(...result.data.dataList);
					that.pageCount = result.data.pageCount;
					that.pageIndex = result.data.pageIndex;
					that.pageSize = result.data.pageSize;
					if(that.storyVideoList.length== 0 || that.storyVideoList.length<=10){
						that.showLoadMore =true
						that.loadingText="没有更多数据了" 
					}
				});
			},
			showType(tbIndex) { //状态导航栏切换
				if(tbIndex != that.tabbarIndex){
					that.tabbarIndex = tbIndex;
					that.storyVideoList=[];
					that.bindStoryVideoList();
				}
			},
			clickToBack(){
				uni.navigateBack();
			},
			toGoodNightStoryDetail(item,index){//跳转晚安故事详情
				setTimeout(() => {
					if(that.tabbarIndex == 3){//付费故事
						uni.navigateTo({
							url: 'goodNightStoryDetailRecord?id='+item.storyVideoId +"&index="+index +"&tabbarIndex=" +that.tabbarIndex
						});
					}else{
						uni.navigateTo({
							url: 'goodNightStoryDetailRecord?id='+item.id +"&index="+index +"&tabbarIndex=" +that.tabbarIndex
						});
					}
					
				}, 200);
			},
		},
		filters: {
			toVideoImage(item){
				if(item.videoImageFilePath == null || item.videoImageFilePath ==""){
					return that.adminRootUrl + item.coverImageFilePath
				}else{
					return  that.adminRootUrl + item.videoImageFilePath
				}
			},
		}
	};
</script>

<style lang="scss">
	@import url("/css/common.css");
	page {
		background: #5EAC59;
	}
	.container{
		width:100%; 
		margin-left:0%;
	}
	.topTabBar {
		border-radius: 10rpx;
		width: 100%;
		position: fixed;
		
		
		z-index: 10;
		background-color: #5EAC59;
		height: 80rpx;
		display: flex;
		justify-content: space-around;
		// padding: 20rpx;
	
		.grid {
			width: 20%;
			height: 80upx;
			display: flex;
			justify-content: center;
			align-items: center;
			color: #444;
			font-size: 28upx;
	
			.text {
				height: 76upx;
				display: flex;
				align-items: center;
	
				&.on {
					color: #ffffff;
					border-bottom: solid 4upx #ffffff;
				}
			}
	
		}
	}
	.tow-list {
		.item {
			width: 345rpx;
			margin-left: 20rpx;
			margin-top: 20rpx;
			display: block;
			float: left;
			border-radius: 20upx;
			background-color: #fff;
			box-shadow: 0upx 5upx 25upx rgba(0, 0, 0, 0.1);
			image {
				width: 100%;
				border-radius: 20upx 20upx 0 0;
			}
			.name {
				width: 92%;
				padding: 10upx 4%;
				display: -webkit-box;
				-webkit-box-orient: vertical;
				-webkit-line-clamp: 2;
				text-align: justify;
				overflow: hidden;
				font-size: 28upx;
				white-space: nowrap;
				text-overflow: ellipsis;
			}
			.time {
				width: 92%;
				padding: 10upx 4%;
				display: -webkit-box;
				-webkit-box-orient: vertical;
				-webkit-line-clamp: 2;
				text-align: justify;
				overflow: hidden;
				font-size: 30upx;
				white-space: nowrap;
				text-overflow: ellipsis;
				color:#999999;
				font-size: 24rpx;
			}
		}
	}
		
</style>
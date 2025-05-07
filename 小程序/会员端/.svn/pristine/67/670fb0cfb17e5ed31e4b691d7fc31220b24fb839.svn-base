<template>
	<view>
		<!-- <view class="" style="padding-top:0"> 
			<image class="topHeadbakimg"  :src= "imageRootUrl + 'image/common/headerTopbg.png'" mode="widthFix"></image>
			<view class="head-content-fixed " :style="{'background':head_is_fixed == 1?'#FACC3F':''}" style="border-bottom: 1rpx solid rgba(0,0,0,0.1);"> 
				<view class="head-content-fixed-content " >
				   <view  class="head-top-nav-title center">
						<view class="headBack"  >
							<image :src= "imageRootUrl + 'image/common/back.png'" style="width: 18rpx; height:32rpx" @click="clickToBack"></image>
						</view>
						慧至半径童书馆 
				   </view>
				</view>
			</view>
		</view> -->
		<view class="container">
			<image :src= "imageRootUrl + 'image/swiper/1.png'" style="width: 100%;height: 210rpx; border-radius: 20rpx;"></image>
			<view class="h20"></view>
			<view class="contentPanel" style="padding: 10rpx; border-radius: 24rpx;"><!-- 搜索 -->
				<view class="line70">
					<view class="top_search_btn_inputPanel" style="width: calc(100% - 110rpx);">   
						<!-- <input class="top_search_btn_input" type="text" style="" placeholder="请输入书籍名称、系列、ISBN"/> -->
						<uni-search-bar radius="5"  class="top_search_btn_input" v-model="searchContent"  cancelButton="none"  placeholder="请输入故事名称" >
						</uni-search-bar>
					</view>
					<view class="top_search_btn f26" @click="clickSearch()">搜索</view>
				</view>
			</view>
			<view class="h20"></view>
			<view class="contentPanel">
				<view>
					<uni-datetime-picker v-model="range" type="daterange" rangeSeparator="至"  @maskClick="maskClick" />
				</view>
				<view class="h20"></view>
				<view class="tow-list">
					<view class="item" v-for="(item,index) in storyVideoList" :key="index" @tap="toGoodNightStoryDetail(item,index)">   <!-- aspectFill adminRootUrl + item.coverImageFilePath-->
						<image mode="aspectFill" :src="item | toVideoImage" style="width: 295rpx; height: 295rpx;"></image>
						<view class="name">{{ item.title }}</view>
						<view class="time">上传时间{{ item.createTimeText | toCreateTime}}</view>
					</view>
				</view>
				<view class="loading-text" style="text-align: center;" v-if="showLoadMore">{{ loadingText }}</view>
				<view class="clear h40"></view>
			</view>
		</view>
	</view>
</template>

<script>
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	let statusBarHeight = uni.getSystemInfoSync().statusBarHeight; // 状态栏 高度
	import searchbar from '@/components/uni-search-bar/uni-search-bar.vue' //搜索
	import datetimepicker from '@/components/uni-datetime-picker/uni-datetime-picker.vue' //时间范围选择
	let that = null;
	export default {
		data() {
			return {
				url:"http://admin.8630.net/WYB/UploadFile/headerTopbg.png",
				imageRootUrl:config.imageRootUrl,//图片地址
				adminRootUrl: config.adminRootUrl + "/", //后端图片地址
				statusBarHeight,// 状态栏 高度
				head_is_fixed:0,
				range: [],
				list: [
					{id: 0,img: config.imageRootUrl + 'image/common/a7.jpg',name: '晚安故事1晚安故事1晚安故事1',time: '2023年12月12日'},
					{id: 1,img: config.imageRootUrl + 'image/common/a8.jpg',name: '晚安故事2',time: '2023年12月12日'},
					{id: 2,img: config.imageRootUrl + 'image/common/a11.jpg',name: '晚安故事3',time: '2023年12月12日'},
					{id: 3,img: config.imageRootUrl + 'image/common/a12.jpg',name: '晚安故事4',time: '2023年12月12日'},
					{id: 4,img: config.imageRootUrl + 'image/common/a17.jpg',name: '晚安故事5',time: '2023年12月12日'},
					{id: 5,img: config.imageRootUrl + 'image/common/a18.jpg',name: '晚安故事6',time: '2023年12月12日'},
				],
				searchContent:'',//搜索内容
				storyVideoList:[],//晚安故事列表
				loadingText: "正在加载...",
				showLoadMore: false,
				pageIndex: 0, //页数
				pageSize: 10, //条数
				pageCount: 0, //最大页数
			};
		},
		components: {
			searchbar,
			datetimepicker
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
			clickSearch(){//搜索
				that.storyVideoList=[];
				that.pageIndex = 0;
				that.pageSize =  10;
				that.bindStoryVideoList();//绑定图书列表
			},
			bindStoryVideoList(){  
				console.log(this.range.length)
				var data = {};
				data.pageIndex = that.pageIndex
				data.pageSize = that.pageSize
				if(that.range.length !=0){
					data.createTimeBegin =that.range[0];
					data.createTimeEnd = that.range[1];
				}
				data.orderBy = "is_pay ,create_time desc"
				if(that.searchContent!=""){
					data.title = that.searchContent ;
				}
				common.call("GetViewStoryVideoList", data, function(result) {
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
			toGoodNightStoryDetail(item,index){//晚安故事详情
				setTimeout(() => {
					uni.navigateTo({
						url: '/pages/user/goodNightStoryDetail?id='+item.id +"&index="+index
					});
				}, 200);
			},
			clickToBack(){
				uni.navigateBack();
			},
		},
		filters: {
			toCreateTime: function(value) {
				var date = new Date(value);
				var year = date.getFullYear()
				var month = date.getMonth() + 1
				var day = date.getDate();
				return year+"年"+month+"月" +day+"日"
			},
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
		// /* #ifdef MP-WEIXIN */
		// 	margin-top: 40px;
		// /* #endif */
		// /* #ifdef H5 */     
		// 	margin-top: 0px;
		// /* #endif */
		// z-index: 8;
	}
	.tow-list {
		.item {
			width: 305rpx;
			margin-left: 10rpx;
			margin-top: 10rpx;
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
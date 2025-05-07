<template>
	<view>
		<view class="container">
			<view class="h60"></view>
			<view class="contentPanel">
				<view class="tow-list">
					<view class="item" v-for="(item,index) in requiredBookCategoryList" :key="index" @tap="toRequiredBookDetail(item,index)">   <!-- aspectFill -->
						<image mode="aspectFill" :src="adminRootUrl + item.imageFilePath" style="width: 295rpx; height: 295rpx;"></image>
						<view class="name">{{ item.name }}</view>
						<view class="time"></view>
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
				
				requiredBookCategoryList:[],//晚安故事列表
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
				that.bindRequiredBookCategoryList();
			}
		},
		onLoad() {
			that = this;
			that.bindRequiredBookCategoryList(); //
		},
		methods: {
			 
			bindRequiredBookCategoryList(){  
				var data = {};
				data.pageIndex = that.pageIndex
				data.pageSize = that.pageSize
				if(common.getCommunityId() == "" || common.getCommunityId() == null ){
					 data.condition = 'company_id=(SELECT id FROM company where is_default is true)'
				}else{
					data.companyId = common.getCommunityId()
				}
				data.orderBy = "create_time"
				common.call("GetViewRequiredBookCategoryList", data, function(result) {
					console.log(result)
					that.requiredBookCategoryList.push(...result.data.dataList);
					that.pageCount = result.data.pageCount;
					that.pageIndex = result.data.pageIndex;
					that.pageSize = result.data.pageSize;
					if(that.requiredBookCategoryList.length== 0 || that.requiredBookCategoryList.length<=10){
						that.showLoadMore =true
						that.loadingText="没有更多数据了" 
					}
				});
			},
			toRequiredBookDetail(item,index){//必读书单详情
				setTimeout(() => {
					uni.navigateTo({
						url: 'requiredBookDetail?id='+item.id +"&name=" + item.name
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
<template>
	<view>
		<view class="container">
			<view class="contentPanel" style="border-radius: 18rpx; margin-top: 20rpx;" v-for="(item,index) in clubActivityList" :key="index" @click="clickToActivityDetail(item)">
				<view class="booksList">
					<view class="books-info">
						<view class="img">
							<image :src="adminRootUrl + item.imageFilePath" mode="aspectFit"></image><!-- aspectFit -->
						</view>
						<view class="info">
							<view class="f26 ellipsis" style="font-weight: bold;">{{item.name}}</view>
							<view class="f26 line40">时间：{{item.createTimeText | toTime}}</view>
							<view class="f26 ellipsis">地点：{{item.address | toNull}}</view>
							<view class="f26 line40">有效期：{{item.expiryDateText | toTime}}</view>
						</view>
					</view>
				</view>
			</view>
			<view class="loading-text" style="text-align: center;" v-if="showLoadMore">{{ loadingText }}</view>
			<view class="h40"></view>
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
				activitylist:[
					{id:1,url:config.imageRootUrl + '/image/common/books1.png',name:'诗歌现场诗歌现场诗歌现场诗歌现场诗歌现场诗歌现场诗歌现场诗歌现场诗歌现场诗歌现场诗歌现场诗歌现场',time:'2021年1月20日',address:'北京市昌平区威尼斯花园1-2384北京市昌平区威尼斯花园1-2384北京市昌平区威尼斯花园1-2384北京市昌平区威尼斯花园1-2384',num:1,endtime:'2023年1月20日'},
					{id:2,url:config.imageRootUrl + 'image/common/books2.png',name:'阅读幸运抽奖',time:'2021年1月20日',address:'湖北省武汉市黄陂区天纵城三五酒店',num:2,endtime:'2023年1月20日'},
					{id:3,url:config.imageRootUrl +'image/common/books3.png',name:'读书修身励志.永葆为民本色',time:'2021年1月20日',address:'湖北省武汉市黄陂区天纵城三五酒店',num:3,endtime:'2023年1月20日'},
					{id:4,url:config.imageRootUrl +'image/common/books1.png',name:'创造发明式阅读',time:'2021年1月20日',address:'湖北省武汉市黄陂区天纵城三五酒店',num:4,endtime:'2023年1月20日'},
					{id:5,url:config.imageRootUrl +'image/common/books2.png',name:'倡导全民阅读，建设书香邯郸',time:'2021年1月20日',address:'湖北省武汉市黄陂区天纵城三五酒店',num:5,endtime:'2023年1月20日'},
					{id:6,url:config.imageRootUrl +'image/common/books3.png',name:'思无疆，学无崖，勤为径',time:'2021年1月20日',address:'湖北省武汉市黄陂区天纵城三五酒店',num:6,endtime:'2023年1月20日'},
				],
				clubActivityList:[],//活动列表
				loadingText: "正在加载...",
				showLoadMore: false,
				pageIndex: 0, //页数
				pageSize: 15, //条数
				pageCount: 0, //最大页数
			};
		},
		onReady() {},
		onShow() {
			that.clubActivityList = [];
			that.bindData(); //
		},
		onPageScroll(e) {
		    that.head_is_fixed = e.scrollTop > 0 ? 1 : 0
		},
		onLoad() {
			that = this;
			// console.log(config.imageRootUrl + 'books1.png')
		},
		methods: {
			bindData(){
				var data = {};
				if(common.getCommunityId() == "" || common.getCommunityId() == null ){
					 data.condition = 'company_id=(SELECT id FROM company where is_default is true) and is_home is true'
				}else{
					data.companyId = common.getCommunityId()
					data.condition = "is_home is true"  
					// and DATE(expiry_date) >= CURDATE()
				}
				data.orderBy = "is_top desc"
				data.pageIndex = that.pageIndex
				data.pageSize = that.pageSize 
				common.call("GetViewClubActivityList", data, function(result) {
					console.log(result)
					that.clubActivityList.push(...result.data.dataList);
					that.pageCount = result.data.pageCount;
					that.pageIndex = result.data.pageIndex;
					that.pageSize = result.data.pageSize;
					if(that.clubActivityList.length== 0 || that.clubActivityList.length<=15){
						that.showLoadMore =true
						that.loadingText="没有更多数据了" 
					}
				});
			},
			clickToActivityDetail(item){
				setTimeout(() => {
					uni.navigateTo({
						url: '/pages/user/activityDetail?id=' +item.id +"&type=home"
					});
				}, 200);
			},
			clickToBack(){
				uni.navigateBack();
			},
		},
		filters: {
			toTime: function(value) {
				var date = new Date(value);
				var year = date.getFullYear()
				var month = date.getMonth() + 1
				var day = date.getDate();
				return year+"年"+month+"月" +day+"日"
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
	.container{
		
	}
	.ellipsis{
		text-overflow: ellipsis;
		    overflow: hidden;
		    -webkit-line-clamp: 2;
		    display: -webkit-box;
		    -webkit-box-orient: vertical;
	}
	.booksList {
		width: 100%;
		display: block;
		position: relative;
		.books-info {
			display: flex;
			width: calc(100%);
			padding: 20rpx 10rpx 10rpx 10rpx;
			flex-wrap: nowrap;
			border-bottom: 1px solid #dedede;
	
			&:last-child {
				border-bottom: none;
			}
	
			.img {
				width: calc(30vw - 70rpx);
				height: calc(28vw);
				display: inline-block;
				overflow: hidden;
				flex-shrink: 0;
				margin-right: 20upx;
				image {
					width: calc(30vw - 70rpx);
					height: calc(27vw);
					border-radius: 20rpx;
				}
			}
	
			.info {
				width: 100%;
				// height: calc(27vw);
				overflow: hidden;
				flex-wrap: wrap;
				align-content: space-between;
				position: relative;
	
				.name {
					width: 100%;
					font-size: 30rpx;
					display: -webkit-box;
					-webkit-box-orient: vertical;
					-webkit-line-clamp: 2;
					overflow: hidden;
					font-weight: bold;
					line-height: 30rpx;
				}
	
				.series {
					.series1 {
						color: rgb(153, 153, 153);
						font-weight: 500;
						font-size: 25rpx;
					}
				}
				.typename{
					background: #F7F8FA; line-height: 30rpx; border-radius: 10rpx; text-align: center;color: #66667B; float: left;
					 padding: 10rpx 15rpx 10rpx 15rpx ; margin-left: 10rpx; margin-top: 10rpx;
					 font-size: 24rpx;
				}
				.borrowTime {
					.borrowTime1 {
						color: rgb(153, 153, 153);
						font-weight: 500;
						font-size: 25rpx;
					}
				}
			}
	
			.countDownPanel {
				width: 130rpx;
				margin-top: 80rpx;
	
				.countDownimg {
					float: left;
	
					image {
						width: 40rpx;
						height: 40rpx;
						margin-top: 5rpx;
					}
				}
	
				.countDownTime {
					float: left;
					color: rgb(153, 153, 153);
				}
			}
			
			.btnCancelNotice {
				width: 130rpx;
				background-color: rgb(254, 97, 61);
				height: 50rpx;
				line-height: 50rpx;
				font-size: 26rpx;
				text-align: center;
				color: white;
				border-radius: 10rpx;
			}
		}
	
	}
	
	 
	
</style>
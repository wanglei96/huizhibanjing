<template>
	<view>
		<view class="container">
			<view  class="searchPanel" style="">
				<view class="contentPanel" style=""> <!-- 搜索 -->
						<view class="top_search_btn_iamgePanel" style="width: 50rpx;">
							<image :src= "imageRootUrl + 'image/common/scan.png'"  style="" @tap.stop="clickToRichScan()"></image>
						</view>
						<view class="top_search_btn_inputPanel" style=" width: calc(100% - 150rpx);">  
							<!-- <input class="top_search_btn_input" type="text" style="" placeholder="请输入书籍名称、系列、ISBN"/> -->
							<uni-search-bar radius="5"  class="top_search_btn_input" v-model="searchVal"  cancelButton="none" @confirm="search" placeholder="请输入书籍名称、系列、ISBN" >
							</uni-search-bar>
						</view>
						<view class="top_search_btn f26" style="background-color: #c8dcd2;width: 100rpx;" @click="clickSearch()">搜索</view>
				</view>
			</view>
			<view style="">
				<view class="h110"></view>
				<!-- <scroll-view :scroll-y="true"  class="msg-box-scroller"   
					:scroll-anchoring="true" @scrolltoupper="loadMorePullDown" @scrolltolower="lower" lower-threshold="20"
					upper-threshold="20" @scroll="scrollViewScrolling" >
					<view class="" v-for="(item,index) in list" :key="index">
						<view class="h800">{{item.categoryName}}</view>
					</view>
				</scroll-view> -->
				<!-- <scroll-view  scroll-y="true" class="scroll-Y" @scrolltoupper="upper" :style="'height:'+scrollViewHeight+'px'"
					@scrolltolower="lower" > -->
					<view class="" v-for="(item,index) in bookslist" :key="index">
						<view class="line80 center">{{item.bookTagName}}</view>
						<view v-for="(ite, bookIndex) in item.booksList" :key="bookIndex" class="booksListPanel">
							<view style="position: relative;height: 100% ;width: 100%;"> <!-- overflow: hidden; -->
								<view style="cursor: pointer;" @tap.stop="clickToBooksDetail(ite)">
									<image :src="adminRootUrl + ite.imageFilePath" mode="aspectFit" style=" height: 210rpx;display: block;width: 95% ;  border:  2rpx solid rgb(211 211 211); border-radius: 10rpx;"></image>
									<view class="line50 center f24">{{ite.name | toName}}</view>
								</view>
							</view>
						</view>
					</view>
					<view class="loading-text" style="text-align: center;" v-if="showLoadMore">{{ loadingText }}</view>
					<view class="h40"></view>
				<!-- </scroll-view> -->
			</view>
		</view>
	</view>
</template>

<script>
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	let that = null;
	export default {
		data() {
			return {
				imageRootUrl:config.imageRootUrl,//图片地址
				adminRootUrl:config.adminRootUrl +"/",//后端图片地址
				searchVal:'',
				bookslist:[
					// {categoryID:1,categoryName:'绘本认知',booksList:[{id: 1, name: '密码花园',url: config.imageRootUrl + 'image/common/bookb1.jpg',},{id: 2,name: '安徒生童话全集',url: config.imageRootUrl + 'image/common/bookb2.jpg',},
					// 	{id: 3,name: '窗边的小豆豆',url: config.imageRootUrl + 'image/common/bookb3.jpg',}, {id: 4,name: '夏洛的网',url: config.imageRootUrl + 'image/common/bookb4.jpg',},
					// 	{id: 5,name: '柳林风声',url: config.imageRootUrl + 'image/common/bookb5.jpg',}, {id: 6,name: '格林童话',url: config.imageRootUrl + 'image/common/bookb6.jpg',},
					// 	{id: 7,name: '100层的火箭',url: config.imageRootUrl + 'image/common/bookb7.jpg',},],
					// },
					// {categoryID:2,categoryName:'绘本故事',booksList:[{id: 1, name: '密码花园',url: config.imageRootUrl + 'image/common/bookb1.jpg',}],
					// },
					// {categoryID:3,categoryName:'科普百科',booksList:[{id: 2,name: '安徒生童话全集',url: config.imageRootUrl + 'image/common/bookb2.jpg',},
					// 	{id: 3,name: '窗边的小豆豆',url: config.imageRootUrl + 'image/common/bookb3.jpg',}, ],
					// },
					// {categoryID:4,categoryName:'科普百科',booksList:[{id: 2,name: '安徒生童话全集',url: config.imageRootUrl + 'image/common/bookb2.jpg',},
					// 	{id: 3,name: '窗边的小豆豆',url: config.imageRootUrl + 'image/common/bookb3.jpg',}, {id: 4,name: '夏洛的网',url: config.imageRootUrl + 'image/common/bookb4.jpg',},
					// 	],
					// },
					// {categoryID:5,categoryName:'动漫卡通',booksList:[{id: 2,name: '安徒生童话全集',url: config.imageRootUrl + 'image/common/bookb2.jpg',},
					// 	{id: 3,name: '窗边的小豆豆',url: config.imageRootUrl + 'image/common/bookb3.jpg',}, {id: 4,name: '夏洛的网',url: config.imageRootUrl + 'image/common/bookb4.jpg',},
					// 	{id: 5,name: '柳林风声',url: config.imageRootUrl + 'image/common/bookb5.jpg',}],
					// },
					// {categoryID:6,categoryName:'名人传记',booksList:[{id: 2,name: '安徒生童话全集',url: config.imageRootUrl + 'image/common/bookb2.jpg',},
					// 	{id: 3,name: '窗边的小豆豆',url: config.imageRootUrl + 'image/common/bookb3.jpg',}, {id: 4,name: '夏洛的网',url: config.imageRootUrl + 'image/common/bookb4.jpg',},
					// 	{id: 5,name: '柳林风声',url: config.imageRootUrl + 'image/common/bookb5.jpg',},{id: 6,name: '格林童话',url: config.imageRootUrl + 'image/common/bookb6.jpg',}],
					// },
					// {categoryID:7,categoryName:'国学',booksList:[{id: 2,name: '安徒生童话全集',url: config.imageRootUrl + 'image/common/bookb2.jpg',},
					// 	{id: 3,name: '窗边的小豆豆',url: config.imageRootUrl + 'image/common/bookb3.jpg',}, {id: 4,name: '夏洛的网',url: config.imageRootUrl + 'image/common/bookb4.jpg',},
					// 	{id: 5,name: '柳林风声',url: config.imageRootUrl + 'image/common/bookb5.jpg'},{id: 6,name: '格林童话',url: config.imageRootUrl + 'image/common/bookb6.jpg',},
					// 	{id: 7,name: '100层的火箭',url: config.imageRootUrl + 'image/common/bookb7.jpg',}],
					// },
					// {categoryID:8,categoryName:'通话故事',booksList:[{id: 1, name: '密码花园',url: config.imageRootUrl + 'image/common/bookb1.jpg',},{id: 2,name: '安徒生童话全集',url: config.imageRootUrl + 'image/common/bookb2.jpg',},
					// 	{id: 3,name: '窗边的小豆豆',url: config.imageRootUrl + 'image/common/bookb3.jpg',}, {id: 4,name: '夏洛的网',url: config.imageRootUrl + 'image/common/bookb4.jpg',},
					// 	{id: 5,name: '柳林风声',url: config.imageRootUrl + 'image/common/bookb5.jpg'},{id: 6,name: '格林童话',url: config.imageRootUrl + 'image/common/bookb6.jpg',},
					// 	{id: 7,name: '100层的火箭',url: config.imageRootUrl + 'image/common/bookb7.jpg',}],
					// },
				],
				loadingText: "正在加载...",
				showLoadMore: false,
				pageIndex: 0, //页数
				pageSize: 10, //条数
				pageCount: 0, //最大页数
				scrollViewHeight:600,
			};
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
				that.bindData();
			}
		},
		onReady() { },
		onShow() { },
		onLoad(option) {
			that = this;
			this.scrollViewHeight = uni.getSystemInfoSync().windowHeight - uni.getSystemInfoSync().statusBarHeight - 45 - 55;
			that.bookslist=[]
			that.bindData(); //
		},
		methods: {
			clickSearch(){
				that.bookslist=[]
				that.bindData(); //
			},
			bindData(){ 
				var data = {};
				data.companyId =common.getCommunityId()
				data.searchParameter = that.searchVal
				data.pageIndex = that.pageIndex 
				data.pageSize = that.pageSize 
				common.call("GetHomeNewBookInfoList", data, function(result) {
					that.bookslist.push(...result.data.dataList);
					that.pageCount = result.data.pageCount;
					that.pageIndex = result.data.pageIndex;
					that.pageSize = result.data.pageSize;
					if(that.bookslist.length== 0 || that.bookslist.length<=10){
						that.showLoadMore =true
						that.loadingText="没有更多数据了" 
					}
					console.log(that.bookslist)
				});
			},
			clickToBooksDetail(item){//跳转图书详情
				setTimeout(() => {
					uni.navigateTo({
						url: 'booksDetail?bookSkuID=' +item.bookSkuId
					});
				}, 200);
			},
			scroll: function(e) {
				//console.log(e)
				//this.old.scrollTop = e.detail.scrollTop
			},
			upper(e){//向上滑动加载更多
				console.log("滚动到顶部")
			},
			lower(e){
				console.log("滚动到底部")
			},
			async clickToRichScan() {
				uni.scanCode({
					autoZoom:false,//是否启用自动放大，默认启用
					onlyFromCamera:true,//是否只能从相机扫码，不允许从相册选择图片
					hideAlbum:true,//是否隐藏相册（不允许从相册选择图片），只能从相机扫码。默认值为 false。
					scanType: ['barCode'],//扫码类型  barCode:一维码  qrCode:二维码  datamatrix:Data Matrix 码  pdf417:PDF417 条码
					success: (res) => { 
						console.log(res)
						console.log("res.result="+ res.result)
						setTimeout(() => {
							that.scanToBooksDetail(res.result);
						}, 200); ;
			
					},
					fail: (err) => {
						// 需要注意的是小程序扫码不需要申请相机权限
						console.log(err)
					}
				});
			},
			scanToBooksDetail(sn) { //扫码跳转图书详情
				if(sn =="" || sn ==null){
					uni.showToast({
						title: '请重新扫码',
						icon: "none",
					duration:2500
					});
					return;
				}
				var data = {};
				data.sn = sn;
				data.companyId = common.getCommunityId()
				common.call('GetViewBookInfoList', data, function(res) {
					console.log(res.data)
					if(res.data.dataList.length <= 0 ){
						uni.showToast({
							title: '请重新扫码',
							icon: "none",
						duration:2500
						});
						return;
					}else{
						setTimeout(() => {
							uni.navigateTo({
								url: "booksDetail?bookSkuID=" + res.data.dataList[0].bookSkuId
							});
						}, 200);
					}
				});
			},
		},
		filters: {
			toName(name){
				//console.log(title)
				if(name == "undefined"){
					return ""
				}else{
					if(name.length >6){
						return name.substring(0,6) + "..."
					}else{
						return name
					}
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
	.container {
		
		top: 0px;
		margin-left: 0%;
		width: 100%;
	}
	.searchPanel{
		width: 100%; background-color: #5EAC59; z-index: 9999; height: 110rpx;position: fixed;
	}
	.contentPanel{
		padding: 10rpx; border-radius: 24rpx; position: fixed;justify-content: center;align-items: center; display: flex; width: calc(100% - 20rpx - 10% ); margin-left: 5%;
	}
	.booksListPanel{
		display: inline-block;
		width: 30%;
		// height: 250rpx;
		// line-height: 250rpx;
		height: 190rpx;
		line-height: 190rpx;
		text-align: center;
		font-size: 36rpx;
		margin-left: 2%;
		margin-top: 20rpx;
	}
</style>
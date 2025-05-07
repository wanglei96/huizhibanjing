<template>
	<view>
		 
		<view class="topTabBar">
			<view class="grid" v-for="(item,tbIndex) in typeList" :key="tbIndex" @tap="showType(tbIndex)">
				<view class="text" :class="[tbIndex==tabbarIndex?'on':'']">{{item}}</view>
			</view>
		</view>
		<view class="container">
			<!-- <view class="tab" style="">
				<uni-segmented-control :current="current" :values="items" :style-type="styleType" :active-color="activeColor" @clickItem="onClickItem" />
			</view> -->
			<!-- #ifdef H5 -->       
				<view class="h80"></view>
			<!-- #endif -->
			<view class="contentPanel" style="border-radius: 18rpx; margin-top: 20rpx;" v-for="(item,index) in bookSkuInfoList" :key="index">
				<view class="booksList">
					<view class="books-info">
						<view class="img" @tap.stop="clickToBooksDetail(item)">
							<view style="position: relative;overflow: hidden;height: 100% ;width: 100%;">
								<view style="cursor: pointer;">
									<image :src="item | toBookImageFilePath" mode="aspectFit" style=" height: 210rpx;display: block;width: 97% ;  border:  2rpx solid rgb(211 211 211); border-radius: 10rpx;"></image>
								</view>
							</view>
						</view>
						<view class="info">
							<view class="name">{{item.bookName}}</view>
							<view class="series" v-for="(ageTtem,ageindex) in item.ageGroupNameList" :key="ageindex">
								<view class="typename" style="">{{ageTtem}}</view>
							</view>
							<view class="series" v-for="(bookTagItem,bookTagindex) in item.bookTagList" :key="bookTagindex+100">
								<view class="typename" style="">{{bookTagItem}}</view>
							</view>
						</view>
						<view>
							<view class="f26 line40 countDownPanel">
								
							</view>
							<block v-if="tabbarIndex == 0">
								<view class="btnBorrow clear" @click="clickBorrow(item,index)">
									借阅
								</view>
								<view class="btnCancelNotice clear" @click="clickCancelNotice(item,index)">
									取消通知
								</view>
							</block>
							<block v-else>
								<view class="btnCancelNotice clear" @click="clickCancelNotice(item,index)">
									取消通知
								</view>
							</block>
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
				tabbarIndex: 0,
				typeList: ['可借', '不可借'], //选项卡
				current: 0, //选项卡
				items: ['可借', '不可借'], //选项卡
				styleType: 'button', //选项卡  text  button
				activeColor: '#53D776', //选项卡
				list:[
					{id:1,url:config.imageRootUrl + 'image/common/bookb1.jpg',name:'密码花园',typeList:[
						{code:'',name:'0-2岁'},{code:'',name:'3-6岁'},{code:'',name:'11-14岁'},{code:'',name:'洞洞翻翻纸板书'},
						{code:'',name:'精装'},{code:'',name:'获奖'},{code:'',name:'国家大师名作套系'},
					]},
					{id:2,url:config.imageRootUrl + 'image/common/bookb2.jpg',name:'安徒生童话全集',typeList:[
						{code:'',name:'0-2岁'},{code:'',name:'3-6岁'},{code:'',name:'11-14岁'},{code:'',name:'绘本认知'},{code:'',name:'洞洞翻翻纸板书'},
						{code:'',name:'精装'},{code:'',name:'获奖'},{code:'',name:'国家大师名作套系'},
					]},
					{id:3,url:config.imageRootUrl + 'image/common/bookb3.jpg',name:'窗边的小豆豆',typeList:[
						{code:'',name:'0-2岁'},{code:'',name:'3-6岁'},{code:'',name:'11-14岁'},{code:'',name:'绘本认知'},{code:'',name:'洞洞翻翻纸板书'},
						{code:'',name:'精装'},{code:'',name:'获奖'},{code:'',name:'国家大师名作套系'},
					]},
					{id:4,url:config.imageRootUrl + 'image/common/bookb4.jpg',name:'夏洛的网',typeList:[
						{code:'',name:'0-2岁'},{code:'',name:'3-6岁'},{code:'',name:'11-14岁'},{code:'',name:'绘本认知'},{code:'',name:'洞洞翻翻纸板书'},
						{code:'',name:'精装'},{code:'',name:'获奖'},{code:'',name:'国家大师名作套系'},
					]},
					{id:5,url:config.imageRootUrl + 'image/common/bookb5.jpg',name:'柳林风声',typeList:[
						{code:'',name:'0-2岁'},{code:'',name:'3-6岁'},{code:'',name:'11-14岁'},{code:'',name:'绘本认知'},{code:'',name:'洞洞翻翻纸板书'},
						{code:'',name:'精装'},{code:'',name:'获奖'},{code:'',name:'国家大师名作套系'},
					]},
					{id:6,url:config.imageRootUrl + 'image/common/bookb6.jpg',name:'格林童话',typeList:[
						{code:'',name:'0-2岁'},{code:'',name:'3-6岁'},{code:'',name:'11-14岁'},{code:'',name:'绘本认知'},{code:'',name:'洞洞翻翻纸板书'},
						{code:'',name:'精装'},{code:'',name:'获奖'},{code:'',name:'国家大师名作套系'},
					]},
				],
				available:">",//（值为“>”,查询可借），（值为“=”，查询不可借）
				bookSkuInfoList:[],//可借通知列表
				loadingText: "正在加载...",
				showLoadMore: false,
				pageIndex: 0, //页数
				pageSize: 10, //条数
				pageCount: 0, //最大页数
				isAntiShake:false,//防抖
			};
		},
		components: {
			
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
				that.bindData();
			}
		},
		onLoad() {
			that = this;
			that.bookSkuInfoList=[]
			that.bindData(); //
		},
		methods: {
			bindData(){
				var data = {};
				data.borrowMemberId = common.getMemberId()
				data.orderBy = "create_time"
				data.companyId = common.getCommunityId()
				data.available =that.available
				data.pageIndex = that.pageIndex  
				data.pageSize = that.pageSize 
				common.call("GetViewBookSkuInfoList", data, function(result) {
					for (let i = 0; i < result.data.dataList.length; i++) {
						var strAge = result.data.dataList[i].ageGroupNames
						
						var strBookTag = result.data.dataList[i].bookTagNames
						if(strAge!="" && strAge!=null && strAge!="null"){
							var arrAge = strAge.split(",");
							//console.log(arrAge)
							result.data.dataList[i].ageGroupNameList = arrAge
						}else{
							result.data.dataList[i].ageGroupNameList = []
						}
						if(strBookTag!="" && strBookTag!=null && strBookTag!="null"){
							var arrbookTag = strBookTag.split(",");
							result.data.dataList[i].bookTagList = arrbookTag
						}else{
							result.data.dataList[i].bookTagList = []
						}
					}
					that.bookSkuInfoList.push(...result.data.dataList);
					that.pageCount = result.data.pageCount;
					that.pageIndex = result.data.pageIndex;
					that.pageSize = result.data.pageSize;
					if(that.bookSkuInfoList.length== 0 || that.bookSkuInfoList.length<=10){
						that.showLoadMore =true
						that.loadingText="没有更多数据了" 
					}
					console.log(that.bookSkuInfoList)
				});
			},
			clickBorrow(item,index){//借阅
				if(that.isAntiShake == true){
					return
				}
				that.isAntiShake = true
				that.Borrow(item.id,index);
				// var data = {};
				// data.memberId = common.getMemberId()
				// data.book_sku_id =item.id 
				// data.fields = "count(*) as count"
				// condition:"is_returned is not true"
				// common.call("GetViewBookOrderItemList", data, function(result) {
				// 	if(result.data.dataList[0].count > 0){
				// 		uni.showModal({
				// 			title: '提示',
				// 			content: '您已经借过了是否还要借',
				// 			showCancel: true, //没有取消按钮的弹框
				// 			success: function(res) {
				// 				if (res.confirm) {
				// 					that.Borrow(item.id,index);
				// 				}else if (res.cancel) {
				// 					that.isAntiShake = false
				// 				}
				// 			}
				// 		});
				// 	}else{
						
				// 	}
				// })
			},
			Borrow(bookSkuID,index){
				var data = {};
				data.memberId = common.getMemberId()
				data.companyId = common.getCommunityId()
				data.bookSkuId =bookSkuID
				common.call("AddOnLineBorrowBookOrder", data, function(result) {
					console.log(result)
					uni.showModal({
						title: '提示', 
						content: "借阅成功！您借阅的图书，将在" + result.data + "为您配送，请注意查收。",
						showCancel: false,//没有取消按钮的弹框
						success: function (res) {
							if (res.confirm) {
								that.isAntiShake = false
								//T6yoA-2ZrVAtXNQ89le-rkW3QrNcDDRqr98R8l4qLdY 借阅超时提醒
								//i2jBP4bt8e7vkWbJJNIlbcl7hxUecYVAQovmDpNYcGo 图书送达通知
								//rUfZ2uKPijZ1uDSk67lzhVsmWJCWaPviAQFgEPm7LkM 会员到期提醒
								wx.requestSubscribeMessage({
									tmplIds: ['T6yoA-2ZrVAtXNQ89le-rkW3QrNcDDRqr98R8l4qLdY','i2jBP4bt8e7vkWbJJNIlbcl7hxUecYVAQovmDpNYcGo','rUfZ2uKPijZ1uDSk67lzhVsmWJCWaPviAQFgEPm7LkM'],
									success (res) { 
										console.log(res)
									}
								}) 
								//借阅完成后删除
								let item = {
									id:bookSkuID
								}
								that.deleteNotice(item,index)
							}
						},
					});
					
				}, function(e) {
					console.log(e)
					if (e.success == false) {
						uni.showModal({
							title: '提示', 
							content: e.errorMessage,
							showCancel: false,//没有取消按钮的弹框
							success: function (res) {
								if (res.confirm) {
									that.isAntiShake = false
								}
							},
						});
					}
				})
			},
			deleteNotice(item,index){
				var data = {};
				data.tableName = "member_book_subscription";
				data.condition = "member_id = " + common.getMemberId() +" and book_sku_id=" + item.id
				common.call("DeleteMemberBookSubscription", data, function(result) {
					that.bookSkuInfoList.splice(index, 1);
				})
			},
			clickCancelNotice(item,index){//取消借阅通知
				var data = {};
				data.tableName = "member_book_subscription";
				data.condition = "member_id = " + common.getMemberId() +" and book_sku_id=" + item.id
				common.call("DeleteMemberBookSubscription", data, function(result) {
					uni.showModal({
						title: '提示',
						content: "取消成功！",
						showCancel: false,//没有取消按钮的弹框
						success: function (res) {
							if (res.confirm) {
								that.bookSkuInfoList.splice(index, 1);
							} 
					}});
				})
			},
			onClickItem(e) { //Tab选项卡切换
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex
				}
				that.bookSkuInfoList=[]
				that.bindData();
			},
			showType(tbIndex) { //状态导航栏切换
				this.tabbarIndex = tbIndex;
				if(tbIndex == 0 ){
					that.available = ">"
				}else{
					that.available = "="
				}
				that.bookSkuInfoList=[]
				that.bindData();
			},
			clickToBack(){
				uni.navigateBack();
			},
			clickToBooksDetail(item){// 跳转到图书详情
				uni.navigateTo({
					url: "/pages/home/booksDetail?bookSkuID=" + item.id
				})
			}, 
		},
		filters:{
			toBookImageFilePath(item){
				if(item.imageFilePath !="" && item.imageFilePath !=null){
					return that.adminRootUrl + "/" + item.imageFilePath
				}else{
					return "../../static/image/common/default.jpg"
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
		/* #ifdef MP-WEIXIN */
			margin-top: 40px;
		/* #endif */
		/* #ifdef H5 */     
			margin-top: 0px;
		/* #endif */
		z-index: 8;
		 
	}
	.tab{
		position: fixed;
		background-color: #fff;
		width: 90%;
		z-index: 99;
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
				height: calc(29vw);
				border-radius: 20rpx;
				// overflow: hidden;
				flex-shrink: 0;
				margin-right: 20upx;
	
				image {
					width: calc(30vw - 70rpx);
					height: calc(29vw);
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
			.btnBorrow {
				width: 130rpx;
				background-color:#53D776;
				height: 50rpx;
				line-height: 50rpx;
				font-size: 26rpx;
				text-align: center;
				color: white;
				border-radius: 10rpx;
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
				margin-top: 10rpx;
			}
		}
	
	}
	
	.topTabBar {
		border-radius: 10rpx;
		width: 100%;
		position: fixed;
		// top: 94px;
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
			font-size: 26upx;
	
			.text {
				font-size: 26upx;
				height: 76upx;
				display: flex;
				align-items: center;
	
				&.on {
					color: #53D776;
					border-bottom: solid 4upx #53D776;
				}
			}
	
		}
	}
	
	
</style>
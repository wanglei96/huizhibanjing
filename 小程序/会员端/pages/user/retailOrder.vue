<template>
	<view class="container">
		<view class="contentPanel" v-for="(item,index) in list" :key="index" style="margin-top: 20rpx;" @click="clickTo(item)">
			<view class="line60">
				<view class="left50"><text class="f26" style="font-weight: 600;">{{item.retailOrderTypeName}}</text></view>
				<view class="right50 f26 b right" style="color: red;">￥{{item.amount}}</view>
			</view>
			<view class="line60 clear">
				<view class="left50 f26">{{item.clubActivityName | toNull}}</view>
				<view class="right50 f26 right" style="">{{item.businessDateText}}</view>
			</view>
		</view>
		<view class="loading-text" style="text-align: center;" v-if="showLoadMore">{{ loadingText }}</view>
		<view class="h40"></view>
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
				list:[
					// {id:1,name:'购买会员卡',price:'实付：889元',time:'2023年6月23日支付成功',time1:'会员卡有效期：2023.06.03-2024.06.22'},
					// {id:2,name:'赠送借阅本书',price:'+10本',time:'2023年6月23日赠送',time1:'赠送有效期：2023.06.03-2024.06.22'},
				],
				loadingText: "正在加载...",
				showLoadMore: false,
				pageIndex: 0, //页数
				pageSize: 10, //条数
				pageCount: 0, //最大页数
				IsShowGoodNightStory:false
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
				that.bindData();
			}
		},
		onLoad() {
			that = this;
			that.list=[]
			that.bindData(); //
			that.bindIsShowGoodNightStory(); //绑定是否显示晚安故事
		},
		methods: {
			bindIsShowGoodNightStory(){
				var data = {};
				data.code ="IsShowGoodNightStory"
				common.call("GetSysSetting", data, function(result) {
					console.log(result.data)
					if(result.data.value == "true"){
						that.IsShowGoodNightStory = true
					}else{
						that.IsShowGoodNightStory = false
					}
					console.log("IsShowGoodNightStory："+that.IsShowGoodNightStory )
				});
			},
			bindData(){
				var data = {};
				data.memberId = common.getMemberId()
				data.retailOrderStatusCode='Paid'
				data.orderBy ="pay_time desc"
				data.pageSize = that.pageSize
				data.pageIndex = that.pageIndex
				common.call("GetViewRetailOrderList", data, function(result) {
					console.log(result)
					that.list.push(...result.data.dataList);
					that.pageCount = result.data.pageCount;
					that.pageIndex = result.data.pageIndex;
					that.pageSize = result.data.pageSize;
					if(that.list.length== 0 || that.list.length<=10){
						that.showLoadMore =true
						that.loadingText="没有更多数据了" 
					}
					console.log(that.list)
				});
			},
			clickTo(item){
				console.log(item)
				if(item.retailOrderTypeCode =='ClueActivity'){
					console.log(item.clubActivityId)
					setTimeout(() => {
						uni.navigateTo({
							url: 'activityDetail?id='+item.clubActivityId
						});
					}, 200);
				}else if(item.retailOrderTypeCode =='NightStory'){
					if(!that.IsShowGoodNightStory){
						return
					}
					console.log("订阅晚安故事")
					var data = {};
					data.orderBy = "is_pay ,create_time desc"
					common.call("GetViewStoryVideoList", data, function(result) {
						//result.data.dataList
						setTimeout(() => {
							uni.navigateTo({
								url: 'goodNightStoryDetail?id='+result.data.dataList[0].id +"&index="+0
							});
						}, 200);
					});
				}
				else if(item.retailOrderTypeCode =='StoryVideo'){
					if(!that.IsShowGoodNightStory){
						return
					}
					var data = {};
					data.pageSize=10000
					data.orderBy = "is_pay ,create_time desc"
					common.call("GetViewStoryVideoList", data, function(result) {
						//result.data.dataList
						let list = result.data.dataList
						let index = 0
						for (let i = 0; i < list.length; i++) {
							if(list[i].id == item.storyVideoId){
								index = i
							}
						}
						setTimeout(() => {
							uni.navigateTo({
								url: 'goodNightStoryDetail?id='+item.storyVideoId +"&index="+ index
							});
						}, 200);
					});
				}
			},
		},
		filters: {
			toNull(val){
				if(val ==null){
					return ""
				}else{
					return val
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
</style>
<template>
	<view>
		 
		<view class="container">
			<view class="contentPanel" v-for="(item,index) in list" :key="index" style="margin-top: 20rpx;">
				<view class="line60">
					<view class="left50"><text class="f30" style="font-weight: 600;">{{item.memberCardServiceTimeTypeName}}</text></view>
					<!-- 购买 、疫情补偿、赠送 、其它-->
					<view v-if="item.memberCardServiceTimeTypeCode == 'Buy' || item.memberCardServiceTimeTypeCode == 'EpidemicSituation' || item.memberCardServiceTimeTypeCode == 'Give' || item.memberCardServiceTimeTypeCode == 'Other' || item.memberCardServiceTimeTypeCode == 'ActivityRecommend' || item.memberCardServiceTimeTypeCode == 'ActivityBatcGift'"> 
						<view class="right50 f26 right" style="">{{item.days}}天</view>
					</view>
					<view v-if="item.memberCardServiceTimeTypeCode == 'AddBorrowNumber' ">
						<view class="right50 f26 right" style="">{{item.days}}本</view>
					</view>
					<view v-if="item.memberCardServiceTimeTypeCode == 'MemberRenew' ">
						<view class="right50 f26 right" style="">购买{{item.days}}天  赠送天数{{item.cost}}天</view>
					</view>
				</view>
				<view class="line30 clear">
					<!-- 购买 -->
					<view v-if="item.memberCardServiceTimeTypeCode == 'Buy' "> 
						<text class="f26" style="color: #6C6B6B;">{{item.createTimeText | toTime}}购买成功</text>
					</view>
					<view v-else-if="item.memberCardServiceTimeTypeCode == 'EpidemicSituation'"> <!-- 疫情补偿 -->
						<text class="f26" style="color: #6C6B6B;">{{item.createTimeText | toTime}}疫情补偿成功</text>
					</view>
					<view v-else-if="item.memberCardServiceTimeTypeCode == 'Give'"> <!-- 赠送 -->
						<text class="f26" style="color: #6C6B6B;">{{item.createTimeText | toTime}}赠送成功</text>
					</view>
					<view v-else-if="item.memberCardServiceTimeTypeCode == 'Other'"> <!-- 其它 -->
						<text class="f26" style="color: #6C6B6B;">{{item.createTimeText | toTime}}其它</text>
					</view>
					<view v-else-if="item.memberCardServiceTimeTypeCode == 'AddBorrowNumber'"> <!-- 加可借数量 -->
						<text class="f26" style="color: #6C6B6B;">{{item.createTimeText | toTime}}加可借数量</text>
					</view>
					<view v-else-if="item.memberCardServiceTimeTypeCode == 'MemberRenew'"> <!-- 续费 -->
						<text class="f26" style="color: #6C6B6B;">{{item.createTimeText | toTime}}续费</text>
					</view>
					
					<view v-else-if="item.memberCardServiceTimeTypeCode == 'ActivityRecommend'"> <!-- 老带新赠送时长 -->
						<text class="f26" style="color: #6C6B6B;">{{item.createTimeText | toTime}}老带新赠送</text>
					</view>
					<view v-else-if="item.memberCardServiceTimeTypeCode == 'ActivityBatcGift'"> <!-- 活动批量赠送时长 -->
						<text class="f26" style="color: #6C6B6B;">{{item.createTimeText | toTime}}活动批量赠送</text>
					</view>
				</view>
				<view class="line70 f30" style="color: #101010;">
					<!-- 购买 、疫情补偿、赠送 、其它-->
					<view class="f28" v-if="item.memberCardServiceTimeTypeCode == 'Buy' || item.memberCardServiceTimeTypeCode == 'EpidemicSituation' || item.memberCardServiceTimeTypeCode == 'Give' || item.memberCardServiceTimeTypeCode == 'Other' || item.memberCardServiceTimeTypeCode == 'MemberRenew'" > 
						{{item.effectiveTimeText | toTime}}-{{item.expiryTimeText | toTime}}
					</view>
				</view>
			</view>
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
		},
		methods: {
			bindData(){
				var data = {};
				data.memberId = common.getMemberId()
				data.pageSize = that.pageSize
				data.pageIndex = that.pageIndex
				common.call("GetViewMemberCardServiceTimeLogList", data, function(result) {
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
		},
		filters: {
			toTime: function(value) {
				var date = new Date(value);
				var year = date.getFullYear()
				var month = date.getMonth() + 1
				var day = date.getDate();
				return year + "年" + month + "月" + day + "日"
			},
			toNull(val) {
				if (val == null) {
					return ""
				} else {
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
</style>
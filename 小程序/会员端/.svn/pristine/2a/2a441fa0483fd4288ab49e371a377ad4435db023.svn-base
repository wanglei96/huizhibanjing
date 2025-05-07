<template>
	<view>
		 
		<view class="container">
			<view class="line60 f32">当前积分：{{score | toScore}}</view>
			<view class="contentPanel" style="border-radius: 18rpx; margin-top: 20rpx;" v-for="(item,index) in memberScoreList" :key="index">
				<view class="line40">
					{{item.memberScoreTypeName}}
				</view>
				<view class="line40">
					<view class="line40 left50 f26">
						{{item.createTimeText}}
					</view>
					<view class="line40 right50 right">
						<!-- <block v-if="item.type == 'jia'">  增加augment  减少 decrease-->
							<!-- <view class="line40 f36 scoreGreen" v-if="item.type == 'augment'">+{{item.score}}</view>
							<view class="line40 f36 scoreRed" v-if="item.type == 'decrease'">-{{item.score}}</view> -->
						<view class="line40 f36 b" :style="{'color':item.score > 0?'#53D776':'#FE613D'}">{{item.score | toTypeScore}}</view>	
						
						<!-- </block> -->
						<!-- <block v-if="item.type == 'jian'">
							<view class="line40 f36 scoreRed">-{{item.score}}</view>
						</block> -->
					</view>
				</view>
			</view>
			<view class="loading-text" style="text-align: center;" v-if="showLoadMore">{{ loadingText }}</view>
			<!-- <view class="contentPanel" style="border-radius: 12rpx; margin-top: 20rpx;">
				<view class="line40">
					消费抵扣
				</view>
				<view class="line40">
					<view class="line40 left50 f26">
						2022年2月23日
					</view>
					<view class="line40 right50 right">
						-10
					</view>
				</view>
			</view> -->
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
					{id:1,title:'登录',time:'2022年2月24日',score:10,type:'jia'},
					{id:1,title:'登录',time:'2022年2月23日',score:10,type:'jia'},
					{id:1,title:'登录',time:'2022年2月22日',score:10,type:'jia'},
					{id:1,title:'登录',time:'2022年2月21日',score:10,type:'jia'},
					{id:1,title:'消费抵扣',time:'2022年2月20日',score:10,type:'jian'},
					{id:1,title:'消费抵扣',time:'2022年2月19日',score:10,type:'jian'},
					{id:1,title:'消费抵扣',time:'2022年2月18日',score:10,type:'jian'}
				],
				score:0,
				memberScoreList:[],//积分列表
				loadingText: "正在加载...",
				showLoadMore: false,
				pageIndex: 0, //页数
				pageSize: 15, //条数
				pageCount: 0, //最大页数
			};
		},
		onReady() {
			
		},
		onShow() {
			
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
		onPageScroll(e) {
		    that.head_is_fixed = e.scrollTop > 0 ? 1 : 0
		},
		onLoad() {
			that = this;
			that.memberScoreList=[]
			that.bindData(); //
			that.bindMemberInfo();
		},
		methods: {
			bindData(){
				var data = {};
				data.memberId = common.getMemberId()
				data.pageIndex = that.pageIndex
				data.pageSize = that.pageSize 
				common.call("GetViewMemberScoreList", data, function(result) {
					console.log(result)
					that.memberScoreList.push(...result.data.dataList);
					that.pageCount = result.data.pageCount;
					that.pageIndex = result.data.pageIndex;
					that.pageSize = result.data.pageSize;
					if(that.memberScoreList.length== 0 || that.memberScoreList.length<=15){
						that.showLoadMore =true
						that.loadingText="没有更多数据了" 
					}
				});
			},
			bindMemberInfo(){
				var data = {};
				data.id = common.getMemberId()
				common.call("GetViewMember", data, function(result) {
					console.log(result)
					that.score = result.data.score;
				});
			},
			clickToBack(){
				uni.navigateBack();
			},
		},
		filters: {
			// toImgPath: function(value) {
			// 	if (value == null || value == "") {
			// 		return "/static/image/common/uploadImage.png";
			// 	} else {
			// 		return that.adminRootUrl + value;
			// 	}
			// },
			toScore: function(value) {
				if (value == null || value == "") {
					return 0;
				} else {
					return value;
				}
			},
			toAmount: function(value) {
				if (value == null || value == "") {
					return 0;
				} else {
					return value;
				}
			},
			toTypeScore(value){
				if (value == null || value == "") {
					return 0
				}
				if(value > 0){
					return "+" + value
				}else{
					return value
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
			//margin-top: 40px;
		/* #endif */
		/* #ifdef H5 */     
			//margin-top: 0px;
		/* #endif */
		z-index: 8;
	}
	.scoreGreen{
		color: #53D776;font-weight: bold;
	}
	.scoreRed{
		color: #FE613D;font-weight: bold;
	}
</style>
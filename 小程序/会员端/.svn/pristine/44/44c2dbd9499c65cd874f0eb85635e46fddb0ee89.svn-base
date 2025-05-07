<template>
	<view>
		<view class="videoContent">
			<tw-videov ref="videoGroup" :autoplay="autoplay" :nextPlay="nextPlay" @handleInfo="handleInfo" @handleOneFeeInfo="handleOneFeeInfo" @handlePayInfo="handlePayInfo" @handlePay="handlePay" :totalvod="storyVideoList.length"></tw-videov>
			<!-- <view class="automatic" @click="nextPlay = !nextPlay">{{nextPlay?'关闭自动播放':'开启自动播放'}}</view> -->
		</view>
	</view>
</template>

<script>
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	import twVideov from '@/components/tsp-video/member-video-list/video-v.vue'
	// import vodData from '@/static/js/vodData.js' //假数据
	let statusBarHeight = uni.getSystemInfoSync().statusBarHeight; // 状态栏 高度
	let that = null;
	export default { 
		components:{ 
			twVideov
		},
		data() {
			return {
				imageRootUrl:config.imageRootUrl,//图片地址
				adminRootUrl:config.adminRootUrl +"/",//后端图片地址
				statusBarHeight,// 状态栏 高度
				head_is_fixed:0,
				autoplay:false,// ==false 自动播放视频  ==true 不自动播放视频
				// videoData: vodData,
				nextPlay:false ,// ==false  不会跳转到下一个   ==true 播放完自动跳转到下一个
				videoList:[
					{
						id:1,
						vodUrl: 'https://sns-video-bd.xhscdn.com/stream/110/258/01e4833fc93bd2ed0103700388a0b48d5a_258.mp4',
						coverImg: '', ///static/image/draft_cover16.jpg
						object_fit: 'cover',
						coverShow:false, //是否显示视频封面，vue 小程序端不播放会显示视频，可以不用显示封面，App不播放不会显示视频，就需要封面了
						sliderShow:true, //是否显示进度条
						haveSeenCount:1,//观看人数
						isLike:true,//是否已经点赞
						likeCount:11,//点赞人数
						isCollection:true ,//是否已经关注
						collectionCount:111, //关注人数
						recommendedReason:'莫·威廉斯，凯迪克奖作品，获得47项童书大奖。孩子看似不可理喻的行为背后，都有一个被理解的需求',//推荐理由
						title:'古纳什小兔：警察故事一则11',
						isPay:false,//是否付费了的
					},
					{
						id:2,
						vodUrl: 'https://partyanimals.cn/media/videos/1.mp4',
						coverImg: '',///static/image/draft_cover1.jpg
						object_fit: 'contain',
						coverShow:false, //是否显示视频封面，vue 小程序端不播放会显示视频，可以不用显示封面，App不播放不会显示视频，就需要封面了
						sliderShow:true, //是否显示进度条
						haveSeenCount:2,//观看人数
						isLike:false,//是否已经点赞
						likeCount:22,//点赞人数
						isCollection:false ,//是否已经关注
						collectionCount:222, //关注人数
						recommendedReason:'甲龙宝宝鱼霸王龙之间也会有感人的亲情。孩子在收听故事至于，更可以潜移默化地体验到爱与被爱的快乐。',//推荐理由
						title:'三只羊嘎啦嘎啦22',
						isPay:false,//是否付费了的
					},
					{
						id:3,
						vodUrl: 'https://partyanimals.cn/media/videos/2.mp4',
						coverImg: '/static/image/draft_cover2.jpg',
						object_fit: 'contain',
						coverShow:false, //是否显示视频封面，vue 小程序端不播放会显示视频，可以不用显示封面，App不播放不会显示视频，就需要封面了
						sliderShow:true, //是否显示进度条
						haveSeenCount:3,//观看人数
						isLike:true,//是否已经点赞
						likeCount:33,//点赞人数
						isCollection:true ,//是否已经关注
						collectionCount:333, //关注人数
						recommendedReason:'甲龙宝宝鱼霸王龙之间也会有感人的亲情。孩子在收听故事至于，更可以潜移默化地体验到爱与被爱的快乐。',//推荐理由
						title:'海底的秘密33',
						isPay:true,//是否付费了的
					},
					{
						id:4,
						vodUrl: 'https://sns-video-bd.xhscdn.com/stream/110/258/01e481f9b8bb290f01037703889bb7c376_258.mp4',
						coverImg: '/static/image/draft_cover17.jpg',
						object_fit: 'cover',
						coverShow:false, //是否显示视频封面，vue 小程序端不播放会显示视频，可以不用显示封面，App不播放不会显示视频，就需要封面了
						sliderShow:true, //是否显示进度条
						haveSeenCount:4,//观看人数
						isLike:false,//是否已经点赞
						likeCount:44,//点赞人数
						isCollection:false ,//是否已经关注
						collectionCount:444, //关注人数
						recommendedReason:'甲龙宝宝鱼霸王龙之间也会有感人的亲情。孩子在收听故事至于，更可以潜移默化地体验到爱与被爱的快乐。',//推荐理由
						title:'成立最漂亮的巨人44',
						isPay:false,//是否付费了的
					},
					{
						id:5,
						vodUrl: 'https://partyanimals.cn/media/videos/3.mp4',
						coverImg: '/static/image/draft_cover3.jpg',
						object_fit: 'contain',
						coverShow:false, //是否显示视频封面，vue 小程序端不播放会显示视频，可以不用显示封面，App不播放不会显示视频，就需要封面了
						sliderShow:true, //是否显示进度条
						haveSeenCount:5,//观看人数
						isLike:true,//是否已经点赞
						likeCount:55,//点赞人数
						isCollection:true ,//是否已经关注
						collectionCount:555, //关注人数
						recommendedReason:'甲龙宝宝鱼霸王龙之间也会有感人的亲情。孩子在收听故事至于，更可以潜移默化地体验到爱与被爱的快乐。',//推荐理由
						title:'胆小鬼威利555',
						isPay:false,//是否付费了的
					},
					{
						id:6,
						vodUrl: 'http://wxsnsdy.tc.qq.com/105/20210/snsdyvideodownload?filekey=30280201010421301f0201690402534804102ca905ce620b1241b726bc41dcff44e00204012882540400&bizid=1023&hy=SH&fileparam=302c020101042530230204136ffd93020457e3c4ff02024ef202031e8d7f02030f42400204045a320a0201000400',
						coverImg: '/static/image/draft_cover15.jpg',
						object_fit: 'contain',
						coverShow:false, //是否显示视频封面，vue 小程序端不播放会显示视频，可以不用显示封面，App不播放不会显示视频，就需要封面了
						sliderShow:true, //是否显示进度条
						haveSeenCount:6,//观看人数
						isLike:false,//是否已经点赞
						likeCount:66,//点赞人数
						isCollection:false ,//是否已经关注
						collectionCount:666, //关注人数
						recommendedReason:'甲龙宝宝鱼霸王龙之间也会有感人的亲情。孩子在收听故事至于，更可以潜移默化地体验到爱与被爱的快乐。',//推荐理由
						title:'变焦66',
						isPay:false,//是否付费了的
					},
					{
						id:7,
						vodUrl: 'https://partyanimals.cn/media/videos/4.mp4',
						coverImg: '/static/image/draft_cover4.jpg',
						object_fit: 'contain',
						coverShow:false, //是否显示视频封面，vue 小程序端不播放会显示视频，可以不用显示封面，App不播放不会显示视频，就需要封面了
						sliderShow:true, //是否显示进度条
						haveSeenCount:7,//观看人数
						isLike:true,//是否已经点赞
						likeCount:77,//点赞人数
						isCollection:true ,//是否已经关注
						collectionCount:777, //关注人数
						recommendedReason:'甲龙宝宝鱼霸王龙之间也会有感人的亲情。孩子在收听故事至于，更可以潜移默化地体验到爱与被爱的快乐。',//推荐理由
						title:'CLICK，CLACK,MOO Cows That Type77',
						isPay:false,//是否付费了的
					},
					{
						id:8,
						vodUrl: 'https://partyanimals.cn/media/videos/5.mp4',
						coverImg: '/static/image/draft_cover5.jpg',
						object_fit: 'contain',
						coverShow:false, //是否显示视频封面，vue 小程序端不播放会显示视频，可以不用显示封面，App不播放不会显示视频，就需要封面了
						sliderShow:true, //是否显示进度条
						haveSeenCount:8,//观看人数
						isLike:false,//是否已经点赞
						likeCount:88,//点赞人数
						isCollection:false ,//是否已经关注
						collectionCount:888, //关注人数
						recommendedReason:'甲龙宝宝鱼霸王龙之间也会有感人的亲情。孩子在收听故事至于，更可以潜移默化地体验到爱与被爱的快乐。',//推荐理由
						title:'怕浪费婆婆88',
						isPay:false,//是否付费了的
					},
				],
				storyVideoList:[],//视频列表
				selectedIndex:0,//视频选中的第几个
				arrayNightStoryType: [],//订阅类型列表
				tabbarIndex:0,
			};
		},
		onReady() { },
		onPageScroll(e) {
		    that.head_is_fixed = e.scrollTop > 0 ? 1 : 0
		},
		onShow() {
			//console.log(1111)
			/* 播放视频 */
			if(this.$refs.videoGroup){
				this.$refs.videoGroup.showPlay()
			}
		},
		onLoad(option) {
			that = this;
			//console.log(item.id) //视频ID
			var data = {};
			data.tableName = "night_story_type"
			data.orderBy = "display_order"
			common.call('GetTable', data, function(result) {
				//console.log(result)
				// for (let i = 0; i < result.data.length; i++) {
				// 	let item = result.data[i];
				// 	let datas = {
				// 		id:item.id,//ID
				// 		name:item.name,//名称
				// 		price:item.price,//价格
				// 		dayCount:item.day_count,//天数
				// 	}
				// 	that.arrayNightStoryType.push(datas)
				// }
				that.arrayNightStoryType = result.data
				console.log(that.arrayNightStoryType)
				that.bindData(); //
			});
			// #ifdef H5
			this.autoplay = false
			// #endif
			that.selectedIndex = option.index
			that.tabbarIndex = option.tabbarIndex
			this.initVod()
		},
		onHide() {
			/* 暂停视频 */
			if(this.$refs.videoGroup){
				this.$refs.videoGroup.hidePause()
			}
		},
		methods: {
			bindData(){ },
			clickToBack(){
				uni.navigateBack();
			},
			initVod(){ /* 初始加载视频数据 */
				setTimeout(()=>{
					var serviceName =""
					var data = {};
					data.orderBy = "create_time desc"
					data.memberId = common.getMemberId()
					data.pageSize = 10000
					if(that.tabbarIndex == 0){ // 看过
						serviceName = "GetViewMemberBrowsedStoryVideoList"
					}else if(that.tabbarIndex == 1){//喜欢
						serviceName = "GetViewMemberLikeStoryVideoList"
					}else if(that.tabbarIndex == 2){//收藏
						serviceName = "GetViewMemberCollectStoryVideoList"
					}else if(that.tabbarIndex == 3){//付费故事
						serviceName = "GetViewRetailOrderList"
						// data.condition = "(retail_order_type_code='NightStory' or retail_order_type_code='StoryVideo') and retail_order_status_code='Paid'"
						data.condition = "( retail_order_type_code='StoryVideo') and retail_order_status_code='Paid'"  //只查处支付一元的列表    retail_order_type_code='NightStory' or
					}
					common.call(serviceName, data, function(result) {
						console.log(result)
						for (let i = 0; i < result.data.dataList.length; i++) {
							let videoItem =  result.data.dataList[i];
							//console.log(videoItem.title)
							let videoData = {
								id:videoItem.id, //ID 
								vodUrl: that.adminRootUrl + videoItem.storyVideoFilePath,//视频路径
								coverImg: '', ///static/image/draft_cover16.jpg //封面图
								object_fit: 'contain', //
								coverShow:false, //是否显示视频封面，vue 小程序端不播放会显示视频，可以不用显示封面，App不播放不会显示视频，就需要封面了
								sliderShow:true, //是否显示进度条
								recommendedReason:videoItem.storyVideoTitle,//推荐理由
								title:videoItem.storyVideoTitle,
								arrayNightStoryType:that.arrayNightStoryType
							}
							that.storyVideoList.push(videoData)
						}
						console.log(that.storyVideoList)
						that.$refs.videoGroup.initVod(that.storyVideoList,parseInt(that.selectedIndex)); 
					});
				},500)
			},
			handleInfo(e){ //点击点赞和收藏  根据ID去修改数据库
				console.log(e)
				console.log("回调1111")
			},
			handleOneFeeInfo(e){ //一元支付 根据ID去修改数据库
				console.log(e)
				console.log("一元支付回调11111")   
				for (let i = 0; i < that.storyVideoList.length; i++) {
					if(that.storyVideoList[i].id == e.id){
						that.storyVideoList[i].isPay = true
					}
				}
				console.log(that.storyVideoList)
			},
			handlePayInfo(){ //三天免费  19.9支付 365支付  所有视频能看 去修改数据库
				console.log("所有视频能看回调11111")   
				for (let i = 0; i < that.storyVideoList.length; i++) {
					that.storyVideoList[i].isPay = true
				}
				console.log(that.storyVideoList)
			},
			handlePay(e){//点击收费按钮，弹框支付 同时修改数据库数据和当前视频的状态为已支付
				console.log(e)
				uni.showModal({
					title: '提示',
					content: '是否支付12元？',
					showCancel: true,//没有取消按钮的弹框
					success: function (res) {
						if (res.confirm) {
							console.log('用户点击确定');
							for (let i = 0; i < that.storyVideoList.length; i++) {
								if(that.storyVideoList[i].id == e.id){
									that.storyVideoList[i].isPay = true
								}
							}
						}
					},
				});
			},
		},
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
</style>

<style lang="scss">
	.videoContent{
		width: 100%;
		min-height: 100%;
	}
	.automatic{
		position: absolute;
		z-index: 20;
		top: 120rpx;
		left: 50rpx;
		font-size: 32rpx;
		color: blue;
	}
	.automatic:active{
		transform: scale(0.8);
		transition: all 0.3s linear;
	}
</style>


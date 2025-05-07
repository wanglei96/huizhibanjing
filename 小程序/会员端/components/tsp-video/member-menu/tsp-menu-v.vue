<template>
	<view>
		<!-- 头部 -->
		<view class="topTitle"
			:class="[vodIndex == index?(sliderDrag?'vodMenu-bright1':(moveOpacity?'vodMenu-bright2':'vodMenu-bright0')):'']">
			<view><text class="foot-cont">《{{item.title}}》</text></view> <!-- {{item.recommendedReason}} -->
			<view style="width: 300rpx;">
			</view>
		</view>
		<!-- 底部标题 -->
		<view class="footTitle"
			:class="[vodIndex == index?(sliderDrag?'vodMenu-bright1':(moveOpacity?'vodMenu-bright2':'vodMenu-bright0')):'']">
			<view class="h30"></view>
			<view><text class="foot-name">故事介绍</text></view>
			<view><text class="foot-cont"> {{item.recommendedReason}}</text></view> <!-- {{item.recommendedReason}} -->
			<view style="width: 300rpx;">
			</view>
		</view>
		<!-- 右侧操作栏 -->
		<view class="menuBox">
			<view class="vodMenu"
				:class="[vodIndex == index?(sliderDrag?'vodMenu-bright1':(moveOpacity?'vodMenu-bright2':'vodMenu-bright0')):'']">
				<!-- 点赞 -->
				<view class="fabulous">
					<view class="fabulous-image fabulous-taoxin" @click="fabulousBtn('like')">
						<image src="../../../static/image/video/like_active.png" mode="" class="fabulous-image"
							v-if="item.isLike  == true"></image>
						<image src="../../../static/image/video/like.png" mode="" class="fabulous-image"
							v-if="item.isLike == false"></image>
					</view>
					<view class="fabulous-num">{{item.likeCount}}</view>
				</view>
				<!-- 收藏 -->
				<view class="fabulous">
					<view class="fabulous-image fabulous-taoxin" @click="fabulousBtn('collect')">
						<image src="../../../static/image/video/collect_active.png" mode="" class="fabulous-image"
							v-if="item.isCollection == true"></image>
						<image src="../../../static/image/video/collect.png" mode="" class="fabulous-image"
							v-if="item.isCollection == false"></image>
					</view>
					<view class="fabulous-num">{{item.collectionCount}}</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	// import uninoticebar from '../uni-notice-bar/uni-notice-bar.vue'
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	export default {
		components: {
			// uninoticebar
		},
		data() {
			return {
				isLike: false, //点赞
				isCollection: false, //收藏
				followShow: null,
				followOpen: false,
				fabuTimeOut: null,
				payTimeOut: null,
				arrayNightStoryType: [],//订阅类型列表
				videoID:0,//视频ID
			}
		},
		props: {
			index: {
				type: Number,
				default: 0
			},
			vodIndex: {
				type: Number,
				default: 0
			},
			vodCurIndex: {
				type: Number,
				default: 0
			},
			item: {
				type: Object,
				default: () => {
					return {}
				}
			},
			discussNum: {
				type: Number,
				default: 0
			},
			sliderDrag: { //是否在拖动进度
				type: Boolean,
				default: false
			},
			moveOpacity: { //是否透明
				type: Boolean,
				default: false
			},
			palyCartoon: { //是否播放旋转头像
				type: Boolean,
				default: false
			}
		},
		onLoad() {
			
		},
		methods: {
			/* 视频点赞动效 */
			fabulousBtn(type) {
				let that = this
				let obj = Object.assign({}, that.item)
				console.log(obj)
				console.log(type)
				if (type == "like") {
					
					var data = {};
					data.memberId = common.getMemberId(); //会员ID
					data.storyVideoId = obj.id;//视频ID
					common.call('MemberLikeStoryVideo', data, function(res) { //点赞
						setTimeout(() => {
							that.$nextTick(() => {
								that.item.isLike = !that.item.isLike
								obj.isLike = !obj.isLike
								if (that.item.isLike == true) {
									that.item.likeCount += 1
									obj.likeCount += 1
								} else {
									that.item.likeCount -= 1
									obj.likeCount -= 1
								}
								console.log(that.item.likeCount)
								that.$forceUpdate()
							})
							that.fabuTimeOut = setTimeout(() => {
								that.$emit('handleInfo', obj) //点赞成功
							}, 200)
						}, 300)
						
					});
					// console.log("点赞：" + this.item.isLike + "obj：" + obj.isLike)
				} else {
					var data = {};
					data.memberId = common.getMemberId(); //会员ID
					data.storyVideoId = obj.id;//视频ID
					common.call('MemberCollectStoryVideo', data, function(res) { //收藏
						setTimeout(() => {
							that.$nextTick(() => {
								that.item.isCollection = !that.item.isCollection
								obj.isCollection = !obj.isCollection
								if (that.item.isCollection == true) {
									that.item.collectionCount += 1
									obj.collectionCount += 1
								} else {
									that.item.collectionCount -= 1
									obj.collectionCount -= 1
								}
							})
							console.log(that.item.collectionCount)
							that.$forceUpdate()
							that.fabuTimeOut = setTimeout(() => {
								that.$emit('handleInfo', obj) //点赞成功
							}, 200)
						}, 200)
					
					});
					// console.log("收藏：" + this.item.isCollection + "obj：" + obj.collectionCount)
				}
				// obj.isLike = !obj.isLike
				// clearTimeout(this.fabuTimeOut)
			},
		}
	}
</script>

<style>
	/* 右边菜单栏 */
	.menuBox {
		position: absolute;
		right: 10rpx;
		width: 115rpx;
		height: 100%;
		z-index: 8;
		display: flex;
		align-items: center;
	}

	.avatarMenu {
		position: absolute;
		bottom: 20px;
		right: 10rpx;
		width: 115rpx;
		z-index: 8;
		display: flex;
		align-items: center;
		flex-direction: column;
	}

	.vodMenu {
		margin-top: 500rpx;
		width: 115rpx;
		/* bottom: 400rpx; */
		display: flex;
		align-items: center;
		flex-direction: column;
	}

	.vodMenu-bright0 {
		opacity: 1;
		transition: all 0.3s linear;
	}

	.vodMenu-bright1 {
		opacity: 0;
		transition: all 0.3s linear;
	}

	.vodMenu-bright2 {
		opacity: 0.2;
		transition: all 0.3s linear;
	}

	.menu-avatar {
		position: relative;
		width: 115rpx;
		height: 115rpx;
		border-radius: 50%;
		background-color: #FFFFFF;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.avatar-image {
		width: 100rpx;
		height: 100rpx;
		border-radius: 50%;
	}

	.follow {
		position: absolute;
		bottom: -20rpx;
		width: 40rpx;
		height: 40rpx;
		background-color: #FFFFFF;
		border-radius: 50%;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.follow:active {
		transform: scale(0.7);
		transition: all 0.3s linear;
	}

	.follow-guanzhu {
		width: 40rpx;
		height: 40rpx;
	}

	.guanzhu-gou {
		width: 30rpx;
		height: 30rpx;
	}

	.followActive {
		transform: rotate(180deg);
		transition: all 0.5s linear;
	}

	.followHide {
		transform: scale(0);
		transition: all 0.8s linear;
	}

	.fabulous {
		width: 100rpx;
		/* margin-top: 80rpx; */
		display: flex;
		align-items: center;
		flex-direction: column;
	}

	.fabulous-image {
		width: 80rpx;
		height: 80rpx;
	}

	.fabulous-num {
		width: 100rpx;
		font-size: 28rpx;
		color: #FFFFFF;
		text-align: center;
		margin-top: 10rpx;
	}

	.fabulous-taoxin:active {
		transition: all 0.2s linear;
		transform: scale(0.7);
	}

	.rotate-avatar {
		width: 95rpx;
		height: 95rpx;
		border-radius: 95rpx;
		background-color: #333333;
		display: flex;
		align-items: center;
		justify-content: center;
		animation: rotateName 5s linear infinite;
	}

	.rotate-image {
		width: 55rpx;
		height: 55rpx;
		border-radius: 55rpx;
	}

	@keyframes rotateName {
		0% {
			transform: rotate(0deg);
		}

		100% {
			transform: rotate(360deg);
		}
	}

	/* 头部标题部分 */
	.topTitle {
		position: absolute;
		top: 20px;
		left: 0;
		z-index: 8;
		/* width: 500rpx;
		margin-left: 30rpx; */
		width: 100%;
		/* margin-left: 15px; */
		height: 200rpx;
		line-height: 200rpx;
		text-align: center;
	}

	/* 底部标题部分 */
	.footTitle {
		position: absolute;
		bottom: 20px;
		left: 0;
		z-index: 8;
		width: 500rpx;
		margin-left: 30rpx;
	}

	.foot-name {
		font-size: 33rpx;
		color: #FFFFFF;
		font-weight: bold;
	}

	.foot-cont {
		font-size: 30rpx;
		color: #FFFFFF;
	}

	.foot-primary {
		font-size: 27rpx;
		color: #FFFFFF;
	}

	.k-paused {
		animation-play-state: paused;
	}

	.k-running {
		animation-play-state: running;
	}

	.payPanel {
		border: 1px solid #ffffff;
		background: #FACC3F;
		width: 242rpx;
		border-radius: 20rpx;
	}

	.seconds {
		float: left;
		text-align: center;
		border-right: 1px solid #ffffff;
		width: 120rpx;
		font-size: 26rpx;
		font-weight: 600;
	}

	.pay {
		float: left;
		text-align: center;
		width: 120rpx;
		font-size: 26rpx;
		font-weight: 600;
	}
</style>
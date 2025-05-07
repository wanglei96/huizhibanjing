<template>
	<view>
		<!-- 底部标题 -->
		<view class="footTitle" :class="[vodIndex == index?(sliderDrag?'vodMenu-bright1':(moveOpacity?'vodMenu-bright2':'vodMenu-bright0')):'']">
			<view><text class="foot-name">@卧槽无情</text></view>
			<view><text class="foot-cont">开发不易，如果对你有帮助，请给个五星好评！</text></view>
			<view style="width: 300rpx;">
				<!-- <text class="foot-primary">卧槽无情的原声-卧槽</text> -->
				<view style="height: 80rpx;transform: scale(0.85);margin-left: -32rpx;margin-bottom: -20rpx;">
					<uninoticebar :single="true" text="卧槽无情的原声-卧槽-卧槽无情的原声" scrollable="true" background-color="transparent"
					 color="#fff" :speed="30"></uninoticebar>
				</view>
			</view>
		</view>
		<!-- 右侧操作栏 -->
		<view class="menuBox" :style="{height:vodHeight+'px'}">
			<view class="vodMenu" :class="[vodIndex == index?(sliderDrag?'vodMenu-bright1':(moveOpacity?'vodMenu-bright2':'vodMenu-bright0')):'']">
				<!-- 头像 -->
				<view class="vodMenu-top">
					<view class="menu-avatar" @click="JumpBtn(1)">
						<image src="/static/icon/touxiang.jpg" mode="" class="avatar-image"></image>
					</view>
					<view class="follow" @click="followBtn(index)" v-if="!item.followReally" :class="{followHide:followShow==2}">
						<image src="/static/icon/gou.png" mode="" class="follow-guanzhu guanzhu-gou" v-if="followShow == 1 || followShow == 2"></image>
						<image src="/static/icon/guanzhu.png" mode="" class="follow-guanzhu" v-if="followShow==null || followShow==0"></image>
					</view>
				</view>
				<!-- 点赞 -->
				<view class="fabulous" @click="JumpBtn(2)">
					<view class="fabulous-image fabulous-taoxin" @click="fabulousBtn(index)">
						<image src="/static/icon/selectTaoxin.png" mode="" class="fabulous-image" v-if="item.fabulousShow"></image>
						<image src="/static/icon/taoxin.png" mode="" class="fabulous-image" v-else></image>
					</view>
					<text class="fabulous-num">{{vodIndex}}</text>
				</view>
				<!-- 评论 -->
				<view class="fabulous" style="margin-top: 30rpx;" @click="JumpBtn(3)">
					<view class="fabulous-image">
						<image src="/static/icon/pinlun.png" mode="" class="fabulous-image"></image>
					</view>
					<text class="fabulous-num">{{discussNum}}</text>
				</view>
				<!-- 转发 -->
				<view class="fabulous" style="margin-top: 30rpx;" @click="JumpBtn(4)">
					<view class="fabulous-image">
						<image src="/static/icon/ward.png" mode="" class="fabulous-image"></image>
					</view>
					<text class="fabulous-num" style="font-size: 26rpx;">转发</text>
				</view>
			</view>
		</view>
		<!-- 旋转头像 -->
		<view class="avatarMenu" v-if="item.rotateImgShow" :class="[vodIndex == index?(sliderDrag?'vodMenu-bright1':(moveOpacity?'vodMenu-bright2':'vodMenu-bright0')):'']">
			<view style="position: relative;width: 95rpx;height: 95rpx;" @click="JumpBtn(5)">
				<view :ref="'rotateImg'+ index">
					<view class="rotate-avatar">
						<image src="/static/icon/touxiang.jpg" mode="" class="rotate-image"></image>
					</view>
				</view>
				<view :style="`position: absolute;top: 0;left: 0;opacity:${item.vodPaly? 0 : 1}`">
					<view class="rotate-avatar">
						<image src="/static/icon/touxiang.jpg" mode="" class="rotate-image"></image>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	const animation = uni.requireNativePlugin('animation')
	import uninoticebar from '../uni-notice-bar/uni-notice-bar.vue'
	export default{
		components:{
			uninoticebar
		},
		data(){
			return {
				fabulousShow:false,
				followShow:null,
				fabuTimeOut:null,
				rotateNum:0,
				rotateTime:null
			}
		},
		props:{
			vodHeight:{ //视频部分的高度
				type:Number,
				default:0
			},
			index:{
				type:Number,
				default:0
			},
			vodIndex:{
				type:Number,
				default:0
			},
			item:{
				type:Object,
				default:()=>{return {}}
			},
			discussNum:{
				type:Number,
				default:0
			},
			sliderDrag:{ //是否在拖动进度
				type:Boolean,
				default:false
			},
			moveOpacity:{ //是否透明
				type:Boolean,
				default:false
			},
		},
		methods:{
			/* 旋转头像动画 */
			rotateAvatar(){
				let testEl = this.$refs['rotateImg'+this.vodIndex+'']
				animation.transition(testEl, {
				  styles: {
					  transform:'rotate(0deg)',
				  },
				  duration: 0, //ms
				  timingFunction: 'linear',
				  delay: 0 //ms
				},()=>{
					this.rotateAvatar1();
				})
			},
			rotateAvatar1(){
				let testEl = this.$refs['rotateImg'+this.vodIndex+'']
				animation.transition(testEl, {
				  styles: {
					  transform:'rotate(360deg)',
				  },
				  duration: 3000, //ms
				  timingFunction: 'linear',
				  delay: 0 //ms
				},()=>{
					this.rotateAvatar();
				})
			},
			/* 视频点赞动效 */
			fabulousBtn(index){
				clearTimeout(this.fabuTimeOut)
				this.fabuTimeOut = setTimeout(()=>{
					this.$emit('fabulousBtn',index)  //关注成功
				},200)
			},
			/* 关注动效 */
			followBtn(index){
				this.followShow = 0
				setTimeout(()=>{
					this.followShow = 1
					setTimeout(()=>{
						this.followShow = 2
						setTimeout(()=>{
							this.$emit('followBtn',index)  //关注成功
						},500)
					},50)
				},300)
			},
			/* 点击右侧菜单选项 1头像 2点赞 3评论 4转发 5旋转头像 */
			JumpBtn(index){
				switch(index){
					case 1 :
						console.log('点击头像')
						uni.navigateTo({
							url:'/pages/details/details-n'
						})
					break;
					case 2 :
						console.log('点击点赞')
					break;
					case 3 :
						console.log('点击3评论')
					break;
					case 4 :
						console.log('点击4转发')
					break;
					case 5 :
						console.log('5旋转头像')
					break;
				}
			}
		}
	}
</script>

<style>
	.menuBox{
		position: absolute;
		right: 10rpx;
		width: 115rpx;
		z-index: 8;
		justify-content: center;
	}
	.avatarMenu{
		position: absolute;
		bottom: 20px;
		right: 10rpx;
		width: 115rpx;
		z-index: 8;
		align-items: center;
		flex-direction: column;
	}
	.vodMenu{
		margin-top: 100rpx;
		width: 115rpx;
		width: 115rpx;
		align-items: center;
		flex-direction: column;
	}
	.vodMenu-bright0{
		opacity: 1;
		transition-property: opacity;
		transition-timing-function:linear;
		transition-duration: 0.3s;
	}
	.vodMenu-bright1{
		opacity: 0;
		transition-property: opacity;
		transition-timing-function:linear;
		transition-duration: 0.3s;
	}
	.vodMenu-bright2{
		opacity: 0.2;
		transition-property: opacity;
		transition-timing-function:linear;
		transition-duration: 0.3s;
	}
	.vodMenu-top{
		height: 140rpx;
		width: 115rpx;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	.menu-avatar{
		position: relative;
		width: 115rpx;
		height: 115rpx;
		border-radius: 115rpx;
		background-color: #FFFFFF;
		align-items: center;
		justify-content: center;
	}
	.avatar-image{
		width: 100rpx;
		height: 100rpx;
		border-radius: 100rpx;
	}
	.follow{
		position: absolute;
		bottom: 0;
		width: 40rpx;
		height: 40rpx;
		background-color: #FFFFFF;
		border-radius: 40rpx;
		align-items: center;
		justify-content: center;
	}
	.follow:active{
		transition-property: transform;
		transition-timing-function:linear;
		transition-duration: 0.4s;
		transform: scale(0.7);
	}
	.follow-guanzhu{
		width: 40rpx;
		height: 40rpx;
	}
	.guanzhu-gou{
		width: 30rpx;
		height: 30rpx;
	}
	.followActive{
		transition-property: transform;
		transition-timing-function:linear;
		transition-duration: 0.5s;
		transform: rotate(180deg);
	}
	.followHide{
		transition-property: transform;
		transition-timing-function:linear;
		transition-duration: 0.5s;
		transform: scale(0);
	}
	.fabulous{
		width: 100rpx;
		margin-top: 80rpx;
		align-items: center;
		flex-direction: column;
	}
	.fabulous-image{
		width: 80rpx;
		height: 80rpx;
	}
	.fabulous-num{
		width: 100rpx;
		font-size: 28rpx;
		color: #FFFFFF;
		text-align: center;
		margin-top: 10rpx;
	}
	.fabulous-taoxin:active{
		transition-property: transform;
		transition-timing-function:linear;
		transition-duration: 0.2s;
		transform: scale(0.7);
	}
	.fabulous-taoxin:enabled{
		transition-property: transform;
		transition-timing-function:linear;
		transition-duration: 0.2s;
		transform: scale(1);
	}
	/* 底部标题部分 */
	.footTitle{
		position: absolute;
		bottom: 20px;
		left: 0;
		width: 500rpx;
		margin-left: 30rpx;
	}
	.foot-name{
		font-size: 33rpx;
		color: #FFFFFF;
		font-weight: bold;
	}
	.foot-cont{
		font-size: 30rpx;
		color: #FFFFFF;
	}
	.foot-primary{
		font-size: 27rpx;
		color: #FFFFFF;
	}
	.rotate-avatar{
		width: 95rpx;
		height: 95rpx;
		border-radius: 95rpx;
		background-color: #333333;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	.rotate-image{
		width: 55rpx;
		height: 55rpx;
		border-radius: 55rpx;
	}
</style>
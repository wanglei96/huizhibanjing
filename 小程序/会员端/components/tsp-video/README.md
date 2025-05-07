## 说明
- 免费使用
- 支持H5、小程序、APP(nvue)、目前只支持vue2版本，vue3版本后期更新
- H5、小程序端采用的是3个item节点循环加载显示，到第一个视频和最后一个视频自动禁止循环滚动加载，不用担心数据过多卡顿的问题
- 断网无法播放时显示进度条加载动效，有网络时自动加载播放
- 不会串音，可以指定某个视频预览播放
- 自定义进度条，可拖动播放
- 已经封装成组件，使用简单，开箱即用，不用过多更改

## 快速上手

1. 页面引入tsp-video库
```js
/* H5、小程序 */
import twVideov from '@/components/tsp-video/tsp-video-list/video-v.vue';
components:{
	twVideov
};
/* APP nvue */
import twVideon from '@/components/tsp-video/tsp-video-list/video-n.vue';
components:{
	twVideon
};
```

2. 初始加载视频数据
```js
/* 调用视频的初始方法 */
this.$refs.videoGroup.initVod(data,0); //0是播放的下标（默认播放下标是0）
```

3. 下拉刷新
```js
/* 调用视频的重新加载方法 */
this.$refs.videoGroup.refreshSquare(data,0); //0是播放的下标（默认播放下标是0）
```

4. 上拉加载
```js
/* 调用视频的到底加载方法方法 */
this.$refs.videoGroup.lodingData(data);
```

5. onShow()播放视频
```js
onShow() {
	/* 播放视频 */
	if(this.$refs.videoGroup){
		this.$refs.videoGroup.showPlay()
	}
}
```

6. onHide()暂停视频
```js
onHide() {
	/* 暂停视频 */
	if(this.$refs.videoGroup){
		this.$refs.videoGroup.hidePause()
	}
}
```

## 参数配置

| 属性                | 类型        | 默认值        | 说明                                                                   |
| ------------------- | -----------| ----------- | ---------------------------------------------------------------------- |
| loadOpen            | Boolean    | true        | 是否开启下拉刷新 |
| tabBarShow          | Number     | 0     		 | tabBar栏 0系统tabBar栏 1自定义tabBar栏                                                         |
| tabBarHeight        | Number     | 50          | 自定义底部栏的高度                                                           |
| speedBottom         | Number     | 0           | 进度条离底部的距离 px                                                       |
| autoplay            | Boolean    | true        | 初始加载完成是否自动播放                       |
| loopPlay            | Boolean    | true        | 是否循环播放                       |
| nextPlay            | Boolean    | false       | 是否开启自动播放                       |
| totalvod            | Number     | 0           | 视频总数量，有值才能滑动加载到最后一个视频并禁止循环滑动（仅H5、小程序支持）                                        |

## 事件
| 属性                | 类型        | 默认值        | 说明                                                                   |
| ------------------- | -----------| ----------- | ---------------------------------------------------------------------- |
| @refreshData        | EventHandle| EventHandle | 下拉刷新加载回调                                                     |
| @lodData            | EventHandle| EventHandle | 上拉加载回调                                          |                                              


## 使用示例

```html
<template>
	<view class="content">
		<tw-videov ref="videoGroup" @lodData="loadingData" @refreshData="refreshData" :autoplay="autoplay"></tw-videov>
	</view>
</template>

<script>
	import twVideov from '@/components/tsp-video/tsp-video-list/video-v.vue'
	import vodData from '@/static/js/vodData.js' //假数据
	export default{
		components:{
			twVideov
		},
		data(){
			return {
				autoplay:true,
				videoData: vodData
			}
		},
		onLoad() {
			// #ifdef H5
			this.autoplay = false
			// #endif
			this.initVod()
		},
		onShow() {
			/* 播放视频 */
			if(this.$refs.videoGroup){
				this.$refs.videoGroup.showPlay()
			}
		},
		onHide() {
			/* 暂停视频 */
			if(this.$refs.videoGroup){
				this.$refs.videoGroup.hidePause()
			}
		},
		methods:{
			startData(){
				let list = []
				/* 模拟请求 */
				return new Promise((resolve, reject)=>{
					setTimeout(()=>{
						for(let i=0; i < 15; i++){
							let obj = {
								/** 参数数据自行拼接  */
								vodUrl:this.videoData[i].src,
								coverImg:this.videoData[i].coverImg, //视频封面
								coverShow:false, //是否显示视频封面，vue 小程序端不播放会显示视频，可以不用显示封面，App不播放不会显示视频，就需要封面了
								object_fit:this.videoData[i].object_fit, //视频的显示类型
								sliderShow:true, //是否显示进度条
								rotateImgShow:true, //是否显示旋转头像
								
								fabulousShow:false,//是否已经点赞
								followReally:false //是否已经关注
							}
							list.push(obj);
						}
						resolve(list)
					},500)
				})
			},
			/* 初始加载视频数据 */
			initVod(){
				this.startData().then((res)=>{
					if(res.length > 0){
						/* 调用视频的初始方法 */
						this.$refs.videoGroup.initVod(res,0); //0是播放的下标（默认播放下标是0）
					}
				})
			},
			/* 下拉刷新 */
			refreshData(){
				this.startData().then((res)=>{
					if(res.length > 0){
						/* 调用视频的重新加载方法 */
						setTimeout(()=>{
							this.$refs.videoGroup.refreshSquare(res,0); //0是播放的下标（默认播放下标是0）
						},2000)
					}
				})
			},
			/* 上拉加载 */
			loadingData(){
				this.startData().then((res)=>{
					if(res.length > 0){
						/* 调用视频的到底加载方法方法 */
						this.$refs.videoGroup.lodingData(res);
					}
				})
			}
		}
	}
</script>

<style>
	page{
		background-color: #000;
	}
	.content{
		width: 100%;
		min-height: 100%;
	}
</style>

```

## 联系我
- 需要核心源代码的可联系我，开发不易，源代码需要额外赞助一下才单独开放
- qq：1670280472

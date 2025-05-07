<template>
    <view class="">
		<view class="h40"></view>
		<!-- 参数
		* @property {ObjectArray} list = [] 列表数据,数据格式[{"name": "花呗","icon": "/static/img/1.png",}]
		* @property {Boolean} feedbackGenerator = [true|false] 是否开启拖动触感反馈  
		* @property {Boolean} longTouch = [true|false] 是否开启长按拖动  
		* @property {Boolean} autoScroll = [true|false] 是否拖拽至边缘自动滚动列表  
		* @property {Number} longTouchTime = [] 选填,触发长按时长,单位:ms,默认350ms
		* @property {Number} listHeight = 0 选填,可拖动列表整体的高度,单位:px,默认等于窗口高度 
		* @property {Number} rowHeight = 44 必填,行高,单位:px,默认44px
		* @event {Function} change 行位置发生改变时触发事件 返回值:{index:'原始下标',moveTo:'被拖动到的下标',moveRow:'拖动行数据'}   
		* @event {Function} confirm 拖拽结束且行位置发生了改变触发事件 返回值:{index:'原始下标',moveTo:'被拖动到的下标',moveRow:'拖动行数据',list:'整个列表拖动后的数据'}  -->
		<HM-dragSorts ref="dragSorts" :list="deliverPlaceList" :longTouch="true" :feedbackGenerator="true" :rowHeight="44" @onclick="onclick"  @change="change" @confirm="confirm"></HM-dragSorts> 
		<!-- <view class="listView">
		    <view class="liView"  v-for="(item,index) in deliverPlaceList" :key="index" @click="clickToDeliverPlace(item)">
				
				<view class="info" style="float: left; width:calc(100% - 120px)">
					{{item.name}}
				</view>
				<view class="butt" style="float: right; ">  
					<view  @tap.stop="clickMoveUp(item,index,'MoveUp')" :style="{'background-color':index != 0?'#FACC3F':'gray','color':'#ffffff'}">上移</view>
					<view  @tap.stop="clickMoveDown(item,index,'MoveDown')" :style="{'background-color':index + 1 != deliverPlaceList.length?'#FACC3F':'gray','color':'#ffffff'}">下移</view>
				</view> 
		    </view>
		</view> -->
    </view>
</template>

<script>
	let that = null;
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	import HMDragSorts from '@/components/HM-dragSorts/HM-dragSorts.vue'
	
    export default {
        data() {
            return {
				deliverPlaceList:[],
				deliverLineId:''
            }
        },
		components: {
			HMDragSorts
		},
		onLoad(option) {
			that = this;
			that.deliverLineId = option.deliverLineId
			that.bindData();
		},
        mounted() {},
        methods: {
			bindData(){//绑定数据
				var data = {};
				data.deliverLineId = that.deliverLineId 
				data.orderBy = "display_order"
				data.pageSize = 10000
				common.call('GetViewDeliverPlaceList', data, function(result) {
					console.log(result)
					that.deliverPlaceList= result.data.dataList
				});
			},
			onclick(e) {
				
				console.log('=== onclick start ===');
				console.log('被点击行: ', JSON.stringify(e));
				console.log('=== onclick end ===');
			},
			change(e) {
				console.log('=== change start ===');
				console.log('被拖拽行: ', JSON.stringify(e.moveRow));
				console.log('原始下标: ', e.index);
				console.log('被拖拽到: ', e.moveTo);
				console.log('=== change end ===');
			},
			confirm(e) {
				console.log('=== confirm start ===');
				console.log('被拖拽行: ', JSON.stringify(e.moveRow));
				console.log('原始下标: ', e.index);
				console.log('被拖拽到: ', e.moveTo);
				console.log('排序后的list: ', e.list);
				// 如果需要删除列表行，必须要排序后，同步一下排序后的list到页面list
				// 因为排序后页面的list和组件内的list已经不一样，不同步list，直接删除页面的list中项目，达不到实际效果。
				console.log('=== confirm end ===');
				let array =[];//deliverPlaceIds
				for (let i = 0; i < e.list.length; i++) {
					array.push(e.list[i].id)
				}
				let deliverPlaceIds = array.join(',');
				console.log(deliverPlaceIds)
				
				var data = {};
				data.deliverPlaceIds = deliverPlaceIds
				common.call('UpdateDeliverPlaceDisplayOrder', data, function(result) {
					console.log(result)
				});
			}
		}
    }
</script>
<style lang="scss">
	@import url("/css/common.css");
	page {
		background: #5EAC59;
	}
	.listView {
		flex-shrink: 1;
		overflow: auto;
		padding: 20rpx;
		padding-top: 0;
	
		.liView {
			padding: 10px;
			background: #fff;
			border-radius: 20rpx;
			display: flex;
			margin-bottom: 10px;
	
			.butt {
				width: 100px;
	
				view {
					width: 90px;
					height: 30px;
					background: #FACC3F;
					border-radius: 6px;
					font-size: 13px;
					font-weight: 600; 
					margin-bottom: 30rpx;
					display: flex;
					align-items: center;
					justify-content: center;
				}
			}
	
			.item {
				color: #333333;
				font-size: 14px;
				padding: 4px 0;
	
				span {
					color: #999999;
				}
			}
	
			.tips {
				color: red;
				font-size: 14px;
				margin-top: 4px;
			}
	
			.info {
				flex: 1;
	
				>.item:nth-child(1) {
					font-size: 16px;
	
					span {
						// font-size: 14px;
					}
				}
			}
		}
	}
	
</style>
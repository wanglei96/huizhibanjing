<template>
	<view class="content-view">
		<view class="infoView">
			<view class="info" v-if="isDistribution">
				<view class="item f26">本月配送单：{{peisongMonth}}</view>
				<view class="item f26">本年度配送单：{{peisongYear}}</view>
				<view class="item f26">总配送单：{{peisongSum}}</view>
			</view>
			<view class="info" v-if="isBookPicker">
				<view class="item f26">本月拣书单：{{jianshuMonth}}</view>
				<view class="item">本年度拣书单：{{jianshuYear}}</view>
				<view class="item">总拣书单：{{jianshuSum}}</view>
			</view>
		</view>
		<view id="dayBay" class="qiun-charts">
			<view class="line60 f26" style=" margin-left: 20rpx;">每日走势</view>
			<canvas canvas-id="canvasDay" id="canvasDay" class="charts-rotate" :width="cWidth*pixelRatio" :height="cHeight*pixelRatio" :style="{'width':cWidth+'px','height':cHeight+'px'}" disable-scroll=true 
				@touchstart="touchLineDay" @touchmove="moveLineDay" @touchend="touchEndLineDay"></canvas>
				
				
		</view>
		<view id="moonBay">
			<view class="line60 f26" style=" margin-left: 20rpx;">每月走势</view>
			<canvas canvas-id="canvasMonth" id="canvasMonth" class="charts-rotate" :width="cWidth*pixelRatio" :height="cHeight*pixelRatio" :style="{'width':cWidth+'px','height':cHeight+'px'}" disable-scroll=true
				@touchstart="touchLineMonth" @touchmove="moveLineMonth" @touchend="touchEndLineMonth"></canvas>
		</view>
		<!-- <canvas canvas-id="canvasLineA" id="canvasLineA" class="charts"></canvas> -->

	</view>
</template>

<script>
	let that = null;
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	// import uCharts from "@/components/u-charts/component.vue";
	import uCharts from '@/components/u-charts/u-charts.js';

	var canvaDay = null;
	var canvaMonth = null;
	export default {
		data() {
			return {
				peisongMonth: 0,
				peisongYear: 0,
				peisongSum: 0,
				jianshuMonth: 0,
				jianshuYear: 0,
				jianshuSum: 0,
				dayOrderData: {},
				monthOrderData: {},
				cWidth: '',
				cHeight: '',
				pixelRatio: 1,
				arcbarWidth: '', //圆弧进度图，进度条宽度,此设置可使各端宽度一致
				cWidth3: '', //圆弧进度图
				cHeight3: '', //圆弧进度图
				isDistribution: false, //是否配送员
				isBookPicker: false, //是否拣书员
			}
		},
		components: {
			// lineChart,
			//uCharts
		},
		onShow() {
			let roleList = uni.getStorageSync("RoleList");
			that.isDistribution = false;
			that.isBookPicker = false;
			for (let i = 0; i < roleList.length; i++) {
				let stringdata = JSON.stringify(roleList[i])
				if (stringdata.indexOf("配送员") != -1) {
					that.isDistribution = true
				}
				if (stringdata.indexOf("拣书员") != -1) {
					that.isBookPicker = true
				}
			}
			that.bindData();
			console.log(that.isDistribution)
			console.log(that.isBookPicker)
		},
		onLoad() {
			that = this;
			//#ifdef MP-ALIPAY
			uni.getSystemInfo({
				success: function(res) {
					if (res.pixelRatio > 1) {
						that.pixelRatio = 2;
					}
				}
			});
			//#endif
			that.cWidth = uni.upx2px(730);
			that.cHeight = uni.upx2px(300);
			that.cWidth3 = uni.upx2px(350); //这里要与样式的宽高对应
			that.cHeight3 = uni.upx2px(305); //这里要与样式的宽高对应
			that.arcbarWidth = uni.upx2px(24);
			that.bindData();
		},
		mounted() {},
		methods: {
			bindData() {
				var data = {};
				data.delivererId = uni.getStorageSync("DelivererID");
				common.call('GetBookOutOrderStatistics', data, function(result) {
					console.log(result.data)
					
					that.peisongMonth= result.data.peisongData.peisongMonth
					that.peisongYear= result.data.peisongData.peisongYear
					that.peisongSum= result.data.peisongData.peisongSum
					that.jianshuMonth=result.data.jianshuData.jianshuMonth
					that.jianshuYear= result.data.jianshuData.jianshuYear
					that.jianshuSum= result.data.jianshuData.jianshuSum
					
					
					let dayData = {}
					let dayData1 = {}
					let monthData = {}
					let monthData1 = {}
					let yDayMaxData = 0
					let yMonthMaxData = 0
					let daySeries=[]
					let monthSeries=[]
					if(that.isDistribution ==true && that.isBookPicker == false){//配送员角色
						dayData = result.data.peisongData.dayData
						monthData = result.data.peisongData.monthData
						
						let dayDataseries ={
							"name":'配送单',
							"data": dayData.yData
						}
						daySeries.push(dayDataseries)
						
						let monthDataseries ={
							"name":'配送单',
							"data": monthData.yData
						}
						monthSeries.push(monthDataseries)
						
						yDayMaxData = dayData.yData[0];
						for (let i = 0; i < dayData.yData.length; i++) {
							if (dayData.yData[i] > yDayMaxData) {
								yDayMaxData = dayData.yData[i];
							}
						}
						
						yMonthMaxData = monthData.yData[0];
						for (let i = 0; i < monthData.yData.length; i++) {
							if (monthData.yData[i] > yMonthMaxData) {
								yMonthMaxData = monthData.yData[i];
							}
						}
						
					}else if(that.isDistribution ==false && that.isBookPicker == true){//拣书员角色角色
						dayData = result.data.jianshuData.dayData
						monthData = result.data.jianshuData.monthData
						
						let dayDataseries ={
							"name":'拣书单',
							"data": dayData.yData
						}
						daySeries.push(dayDataseries)
						
						let monthDataseries ={
							"name":'拣书单',
							"data": monthData.yData
						}
						monthSeries.push(monthDataseries)
						
						for (let i = 0; i < dayData.yData.length; i++) {
							if (dayData.yData[i] > yDayMaxData) {
								yDayMaxData = dayData.yData[i];
							}
						}
						yMonthMaxData = monthData.yData[0];
						for (let i = 0; i < monthData.yData.length; i++) {
							if (monthData.yData[i] > yMonthMaxData) {
								yMonthMaxData = monthData.yData[i];
							}
						}
					}else if(that.isDistribution ==true && that.isBookPicker == true){//配送员和拣书员角色
						dayData = result.data.peisongData.dayData
						monthData = result.data.peisongData.monthData
						let dayDataseries ={
							"name":'配送单',
							"data": dayData.yData
						}
						daySeries.push(dayDataseries)
						
						let monthDataseries ={
							"name":'配送单',
							"data": monthData.yData
						}
						monthSeries.push(monthDataseries)
						
						
						
						
						dayData1 = result.data.jianshuData.dayData
						monthData1 = result.data.jianshuData.monthData
						
						let dayDataseries1 ={
							"name":'拣书单',
							"data": dayData1.yData
						}
						daySeries.push(dayDataseries1)
						
						let monthDataseries1 ={
							"name":'拣书单',
							"data": monthData1.yData
						}
						monthSeries.push(monthDataseries1)
						
						
						let dayArr =[]
						for (let i = 0; i < dayData.yData.length; i++) {
							dayArr.push(dayData.yData[i]);
						}
						for (let i = 0; i < dayData1.yData.length; i++) {
							dayArr.push(dayData1.yData[i]);
						}
						for (let i = 0; i < dayArr.length; i++) {
							if (dayArr[i] > yDayMaxData) {
								yDayMaxData = dayArr[i];
							}
						}
						let monthArr =[]
						for (let i = 0; i < monthData.yData.length; i++) {
							monthArr.push(monthData.yData[i]);
						}
						for (let i = 0; i < monthData1.yData.length; i++) {
							monthArr.push(monthData1.yData[i]);
						}
						for (let i = 0; i < monthArr.length; i++) {
							if (monthArr[i] > yMonthMaxData) {
								yMonthMaxData = monthArr[i];
							}
						}
					}

					
					
					
					
					console.log(yDayMaxData)
					console.log(yMonthMaxData)
					
					
					canvaDay = new uCharts({
						$this: that,
						canvasId: "canvasDay",
						type: 'line',
						// colors:['#90ed7d'],
						fontSize: 11,
						padding: [15, 20, 0, 15],
						legend: {
							show: true,
							padding: 5,
							lineHeight: 11,
							margin: 0,
						},
						dataLabel: false,
						dataPointShape: true,
						background: '#FFFFFF',
						pixelRatio: that.pixelRatio,
						categories: dayData.xData,
						series: daySeries,
						animation: true,
						xAxis: {
							type: 'grid',
							gridColor: '#CCCCCC',
							gridType: 'dash',
							dashLength: 8,
							disableGrid: true,
							labelCount: 10,
						},
						yAxis: {
							gridType: 'dash',
							gridColor: '#CCCCCC',
							dashLength: 8,
							max: yDayMaxData * 2,  //5
							format: function (val) {
								return val.toFixed(0);
							},
						},
						width: that.cWidth * that.pixelRatio,
						height: that.cHeight * that.pixelRatio,

					});
					
					canvaMonth = new uCharts({
						$this: that,
						canvasId: "canvasMonth",
						type: 'line',
						// colors:['#90ed7d'],
						fontSize: 11,
						padding: [10, 40, 10, 10],
						legend: {
							show: true,
							padding: 5,
							lineHeight: 11,
							margin: 0,
						},
						dataLabel: false,
						dataPointShape: true,
						background: '#FFFFFF',
						pixelRatio: that.pixelRatio,
						categories: monthData.xData,
						series: monthSeries,
						animation: true,
						xAxis: {
							type: 'grid',
							gridColor: '#CCCCCC',
							gridType: 'dash',
							dashLength: 8,
							disableGrid: true,
							// labelCount:10 ,
							scrollShow: true
						},
						yAxis: {
							gridType: 'dash',
							gridColor: '#CCCCCC',
							dashLength: 8,
							max: yMonthMaxData * 1,   //5
							format: function (val) {
								return val.toFixed(0);
							},
						},
						width: that.cWidth * that.pixelRatio,
						height: that.cHeight * that.pixelRatio,

					});
				});
			},
			touchLineDay(e) {
				canvaDay.scrollStart(e);
			},
			moveLineDay(e) {
				canvaDay.scroll(e);
			},
			touchEndLineDay(e) {
				canvaDay.scrollEnd(e);
				//下面是toolTip事件，如果滚动后不需要显示，可不填写
				canvaDay.touchLegend(e);
				canvaDay.showToolTip(e, {
					format: function(item, category) {
						return category + "日" + '：' + item.data
					}
				});
			},
			touchLineMonth(e) {
				canvaDay.scrollStart(e);
			},
			moveLineMonth(e) {
				canvaMonth.scroll(e);
			},
			touchEndLineMonth(e) {
				canvaMonth.scrollEnd(e);
				//下面是toolTip事件，如果滚动后不需要显示，可不填写
				canvaMonth.touchLegend(e);
				canvaMonth.showToolTip(e, {
					format: function(item, category) {
						return category + "月" + '：' + item.data
					}
				});
			},
			init() {
				// var xData = ['1日', '2日', '3日', '4日', '5日', '6日', '7日','8日', '9日', '10日']
				//var infoData = [120, 200, 150, 80, 70, 110, 130, 70, 110, 130,120, 200, 150, 80, 70, 110, 130, 70, 110, 130,120, 200, 150, 80, 70, 110, 130, 70, 110, 130]
				var xData = that.dayOrderData.xData
				var infoData = that.dayOrderData.yData
				//var infoData = [120, 200, 150, 80, 70, 110, 130, 70, 110, 130,120, 200, 150, 80, 70, 110, 130, 70, 110, 130,120, 200, 150, 80, 70, 110, 130, 70, 110, 130]
				this.BarInit(document.getElementById('dayBay'), '日配送单走势', xData, infoData)
				var xData = that.monthOrderData.xData
				var infoData = that.monthOrderData.yData
				//var infoData = [120, 200, 150, 80, 70, 110, 130, 70, 110, 130,120, 200]
				this.BarInit(document.getElementById('moonBay'), '月配送单走势', xData, infoData)
			},
			BarInit(dom, title, xData, infoData) {
				var myChart = echarts.init(dom);
				var option = {
					tooltip: {
						trigger: 'axis',
						axisPointer: {
							type: 'shadow'
						}
					},
					xAxis: {
						type: 'category',
						axisLabel: {
							interval: 0, //代表显示所有x轴标签显示
						},
						data: xData
					},
					grid: {
						top: '20%',
						left: '4%',
						right: '4%',
						bottom: '5%',
						containLabel: true
					},
					yAxis: {
						type: 'value',
						name: title,
						nameTextStyle: {
							color: "#000",
							padding: [0, 0, 10, 25]
						},
					},
					dataZoom: [{
						// 设置滚动条的隐藏与显示
						show: true,
						// 设置滚动条类型
						type: "slider",
						// 设置背景颜色
						backgroundColor: "rgb(19, 63, 100)",
						// 设置选中范围的填充颜色
						fillerColor: "rgb(16, 171, 198)",
						// 设置边框颜色
						borderColor: "rgb(19, 63, 100)",
						// 是否显示detail，即拖拽时候显示详细数值信息
						showDetail: false,
						// 数据窗口范围的起始数值
						startValue: 0,
						// 数据窗口范围的结束数值（一页显示多少条数据）
						endValue: 5,
						// empty：当前数据窗口外的数据，被设置为空。
						// 即不会影响其他轴的数据范围
						filterMode: "empty",
						// 设置滚动条宽度，相对于盒子宽度
						width: "50%",
						// 设置滚动条高度
						height: 8,
						// 设置滚动条显示位置
						left: "center",
						// 是否锁定选择区域（或叫做数据窗口）的大小
						zoomLoxk: true,
						// 控制手柄的尺寸
						handleSize: 0,
						// dataZoom-slider组件离容器下侧的距离
						bottom: 3,

					}],
					series: [{
						data: infoData,
						type: 'bar',
						label: {
							normal: {
								show: true,
								position: "top",
								textStyle: {
									color: "#999999",
									fontSize: 12
								},
							}
						},
					}]
				};
				myChart.setOption(option)
				window.addEventListener('resize', function() {
					myChart.resize();
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

	#dayBay,
	#moonBay {
		// height: 280px;
		margin: 20rpx 20rpx;
		background: #fff;
		border-radius: 8px;
	}

	.content-view {
		overflow: auto;
		padding-bottom: 80px;
		// border-top: 1px solid rgba(0, 0, 0, 0.1);
	}

	.infoView {
		padding: 10px;
		background: #fff;
		border-radius: 8px;
		display: flex;
		margin: 20rpx 20rpx;

		.butt {
			width: 100px;

			view {
				width: 90px;
				height: 30px;
				background: #FACC3F;
				border-radius: 6px;
				font-size: 13px;
				font-weight: 600;
				margin-bottom: 7px;
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
			// >.item:nth-child(1){
			//     font-size: 16px;
			//     span{
			//         font-size: 14px;
			//     }
			// }
		}
	}

	.charts {
		width: 750upx;
		height: 500upx;
		background-color: #FFFFFF;
	}

	.qiun-charts {
		// width: 750upx;
		// height: 500upx;
		// background-color: #FFFFFF;
	}
</style>
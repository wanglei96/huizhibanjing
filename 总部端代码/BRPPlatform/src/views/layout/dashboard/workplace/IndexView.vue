<script setup>
	import * as echarts from 'echarts'
	import common from '@/js/common'
	import util from '@/js/util'
	import {
		nextTick
	} from 'vue'
	const desktopData = ref([]);
	const selectVal = ref('month')

	function selectFun() {
		getInfoData(selectVal.value)
	}
	onMounted(() => {
		getInfoData(selectVal.value)
	})
	/**
	 *  GetDesktopStatisticsList
	 * // 门店数量 companyNumber;
		// 会员数量 memberNumber;
		// 新增会员数量  addMemberNumber;
		// 书本数量   booksNumber;
		// 已安装书箱  installedBookboxNumber;
		// 库存书箱数量   inventoryBookboxNumber;
		// 会员各年龄段分布  memberAgeGroup;
		// 图书各年龄段分布  booksAgeGroup;
		// 门店会员数量排行前10  companyMemberGroup;
		// 图书数量排行前十  booksNumberTop;
	 */
	function getInfoData(type) {
		common.call("GetDesktopStatisticsList", {
			type
		}, function(result) {
			var data = result.data
			desktopData.value = result.data;
			var domData = [{
					name: 'storeView',
					key: 'companyNumber'
				},
				{
					name: 'MemberView',
					key: 'memberNumber'
				},
				{
					name: 'booksNumberView',
					key: 'booksNumber'
				},
				{
					name: 'installedBookboxView',
					key: 'installedBookboxNumber'
				},
				{
					name: 'inventoryBookboxView',
					key: 'inventoryBookboxNumber'
				},

				// {name:'memberAgeGroupView',key:'memberAgeGroup'},
				// {name:'booksAgeGroupView',key:'booksAgeGroup'},
			]

			var domData1 = [{
				name: 'AddMemberView',
				key: 'addMemberNumber'
			}]

			for (var i = 0; i < domData.length; i++) {
				var obj = {
					dom: document.getElementById(domData[i].name),
					xData: data[domData[i].key]
					.xData, //['1月', '2月', '3月', '4月','5月', '6月', '7月', '8月','9月', '10月', '11月', '12月'],
					yData: data[domData[i].key].yData
				}
				newHistogram(obj, 'line')
			}
			for (var i = 0; i < domData1.length; i++) {
				var obj = {
					dom: document.getElementById(domData1[i].name),
					xData: data[domData1[i].key]
					.xData, //['1月', '2月', '3月', '4月','5月', '6月', '7月', '8月','9月', '10月', '11月', '12月'],
					yData: data[domData1[i].key].yData
				}
				newHistogram(obj, 'bar')
			}
			/* var list = []
			data.memberAgeGroup.forEach(item=>{
				list.push({name:item.birthday,value:Number(item.count)})
			})
			newPie({
				dom: document.getElementById('memberAgeGroupView'),
				data:list
			}) */

			/* var list = []
			data.booksAgeGroup.forEach(item=>{
				list.push({name:item.birthday,value:Number(item.count)})
			})
			newPie({
				dom: document.getElementById('booksAgeGroupView'),
				data:list
			}) */

			var keys = []
			var vals = []
			data.companyMemberGroup.forEach(item => {
				keys.push(item.companyName)
				vals.push(Number(item.count))
			})
			newHistogram({
				dom: document.getElementById('maxStore'),
				xData: keys,
				yData: vals
			}, 'bar')
		}, function(result) {
			console.log("获取桌面统计数据失败");
		})
	}
	// 饼状图
	function newPie(obj) {
		var myChart = echarts.init(obj.dom)
		var option = {
			grid: {
				top: '20%', // 顶部间距为容器高度的10%
				bottom: '0', // 底部间距为容器高度的20%
				left: '2%',
				right: '2%',
				containLabel: true
			},
			tooltip: {
				trigger: 'item'
			},
			legend: {
				orient: 'vertical',
				right: 20,
				top: 20
			},
			series: [{
				name: '',
				type: 'pie',
				radius: ['40%', '60%'],
				center: ['40%', '50%'], //饼图位置
				avoidLabelOverlap: false,
				itemStyle: {
					borderRadius: 10,
					borderColor: '#fff',
					borderWidth: 2
				},
				label: {
					normal: {
						// {font|{c}}\n{hr|}\n
						formatter: '{font|{d}%}',
						rich: {
							font: {
								fontSize: 10,
								padding: [5, 0],
								color: '#000'
							},
							hr: {
								height: 0,
								borderWidth: 1,
								width: '100%',
								borderColor: '#000'
							}
						}
					},
				},
				labelLine: {
					lineStyle: {
						color: '#000'
					}
				},
				emphasis: {
					label: {
						show: true,
						fontSize: 30,
						fontWeight: 'bold'
					}
				},
				data: obj.data
			}]
		}
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option)
		// 为窗口加上宽度变化事件，当宽高发生改变时，调用echarts的resize()方法，调整图表尺寸
		window.addEventListener('resize', function() {
			myChart.resize()
		})
	}
	// 柱状图
	function newHistogram(obj, type) {
		var myChart = echarts.init(obj.dom)
		// 指定图表的配置项和数据
		var option = {
			grid: {
				top: '15%', // 顶部间距为容器高度的10%
				bottom: '0', // 底部间距为容器高度的20%
				left: '2%',
				right: '2%',
				containLabel: true
			},
			tooltip: {
				trigger: 'axis',
				axisPointer: {
					type: 'shadow'
				}
			},
			xAxis: [{
				type: 'category',
				data: obj.xData,
				axisTick: {
					alignWithLabel: true
				}
			}],
			yAxis: [{
				type: 'value'
			}],
			series: [{
				name: '',
				type: type,
				barWidth: '60%',
				data: obj.yData,
				"label": {
					"show": true,
					"position": "top",
					"distance": 6,
					fontSize: 12,
					"color": "#000"
				}
			}]
		}
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option)
		// 为窗口加上宽度变化事件，当宽高发生改变时，调用echarts的resize()方法，调整图表尺寸
		window.addEventListener('resize', function() {
			myChart.resize()
		})
	}

	const borrowContainer = ref()
	const dateType = ref("FourteenDay");

	/**
	 * 14天借阅人数统计
	 */
	function bindFourteenBookOrderTotal() {
		/*  let params = {
		    companyId: companyId.value,
		    dateType: dateType.value
		  } */
		let params = {
			dateType: dateType.value
		}

		common.call("GetFourteenBookOrderTotal", params, (res) => {
			let dataList = res.data;
			let orderDate = [];
			let offlineCount = [];
			let onlineCount = [];
			dataList.forEach(element => {
				orderDate.push(element.orderDate);
				offlineCount.push(element.offlineCount);
				onlineCount.push(element.onlineCount);
			});
			bindFourteenBookOrderTotalBarChart(orderDate, offlineCount, onlineCount);
		})
	}
	/**
	 * 14天借阅人数统计图
	 */
	function bindFourteenBookOrderTotalBarChart(orderDate, offlineCount, onlineCount) {
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(borrowContainer.value)
		// 指定图表的配置项和数据
		var option = {
			title: {
				text: '借阅人数统计'
			},
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				orient: 'horizontal',
				left: 'center',
				data: ['配送到家', '到店借还'],
				textStyle: {
					fontSize: 12,
				},
			},
			grid: {
				left: '3%',
				right: '4%',
				bottom: '6%',
				containLabel: true
			},
			toolbox: {
				feature: {
					saveAsImage: {}
				}
			},
			xAxis: {
				type: 'category',
				boundaryGap: false,
				data: orderDate,
				axisLabel: {
					rotate: 45, // 设置倾斜角度
					textStyle: {}
				},
			},
			yAxis: {
				type: 'value',
			},
			series: [{
					name: '配送到家',
					type: 'line',
					stack: 'Total',
					data: onlineCount,
					stack: null,
					label: {
						show: true,
						position: 'top',
					},
				},
				{
					name: '到店借还',
					type: 'line',
					stack: 'Total',
					data: offlineCount,
					label: {
						show: true,
						position: 'top',
					},
				}
			]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option)
		// 为窗口加上宽度变化事件，当宽高发生改变时，调用echarts的resize()方法，调整图表尺寸
		window.addEventListener('resize', function() {
			myChart.resize()
		})
	}

	function init() {
		//获取14天借阅人数统计
		bindFourteenBookOrderTotal();
		// 获取年卡会员各个年龄段分布
		bindMemberAgeGroupTotal();
		// 获取图书年龄段数据
		bindBookAgeGroupTotal();
	}

	function fourteenBookOrderChange() {
		//获取14天借阅人数统计
		bindFourteenBookOrderTotal();
	}


	/**
	 * 获取年卡会员各个年龄段分布
	 */
	function bindMemberAgeGroupTotal() {
		let params = {
			companyId: null
		}
		common.call('GetMemberAgeGroupTotal', params, (res) => {
			let dataList = res.data
			bindMemberAgeGroupTotalBarChart(dataList)
		})
	}
	const memberAgeGroupContainer = ref()

	function bindMemberAgeGroupTotalBarChart(dataList) {
		var myChart = echarts.init(memberAgeGroupContainer.value)
		var option = {
			/* title: {
			   text: '年卡会员各个年龄段分布',
			   left: 'left'
			 }, */
			tooltip: {
				trigger: 'item'
			},
			legend: {
				bottom: 0,
				orient: 'horizontal',
				left: 'center',
				textStyle: {
					fontSize: 10
				}
			},
			series: [{
				name: '年龄分布',
				type: 'pie',
				radius: '50%',
				data: dataList,
				label: {
					formatter: '{b}: ({c}) ({d}%)' // {b}表示数据项名称，{c}表示数值，{d}%表示百分比
				},
				emphasis: {
					itemStyle: {
						shadowBlur: 10,
						shadowOffsetX: 0,
						shadowColor: 'rgba(0, 0, 0, 0.5)'
					}
				}
			}]
		}

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option)
		// 为窗口加上宽度变化事件，当宽高发生改变时，调用echarts的resize()方法，调整图表尺寸
		window.addEventListener('resize', function() {
			myChart.resize()
		})
	}

	/**
	 * 获取图书年龄段数据
	 */
	function bindBookAgeGroupTotal() {
		let params = {
			companyId: null
		}
		common.call('GetBookAgeGroupListTotal', params, (res) => {
			let dataList = res.data
			bindBookAgeGroupTotalBarChart(dataList)
		})
	}
	const bookAgeGroupContainer = ref()

	function bindBookAgeGroupTotalBarChart(dataList) {
		var myChart = echarts.init(bookAgeGroupContainer.value)
		var option = {
			/* title: {
			   text: '图书各年龄段比例',
			   left: 'left'
			 }, */
			tooltip: {
				trigger: 'item'
			},
			legend: {
				bottom: 0,
				orient: 'horizontal',
				left: 'center',
				textStyle: {
					fontSize: 10
				}
			},
			series: [{
				name: '年龄分布',
				type: 'pie',
				radius: '50%',
				data: dataList,
				label: {
					formatter: '{b}: ({c}) ({d}%)' // {b}表示数据项名称，{c}表示数值，{d}%表示百分比
				},
				emphasis: {
					itemStyle: {
						shadowBlur: 10,
						shadowOffsetX: 0,
						shadowColor: 'rgba(0, 0, 0, 0.5)'
					}
				}
			}]
		}

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option)
		// 为窗口加上宽度变化事件，当宽高发生改变时，调用echarts的resize()方法，调整图表尺寸
		window.addEventListener('resize', function() {
			myChart.resize()
		})
	}

	onMounted(() => {
		init()
	})
</script>
<template>
	<div class="tongji-body">
		<el-select v-model="selectVal" @change="selectFun()" placeholder="请选择">
			<el-option label="年统计" value="year">
			</el-option>
			<el-option label="月统计" value="month">
			</el-option>
			<el-option label="日统计" value="day">
			</el-option>

		</el-select>

		<!-- 头部统计图表 -->
		<el-row :gutter="30" class="row-card" style="margin-top: 15px">
			<el-col :xs="24" :sm="24" :md="12" :lg="6">
				<el-card shadow="hover">
					<!-- /////////// card content //////////// -->
					<div class="card-header">
						<span>门店数量</span>
						<!-- <span>
							<el-icon>
								<More />
							</el-icon>
						</span> -->
					</div>
					<div id="storeView" class="echartsSty"></div>
				</el-card>
			</el-col>
			<el-col :xs="24" :sm="24" :md="12" :lg="6">
				<el-card shadow="hover">
					<!-- /////////// card content //////////// -->
					<div class="card-header">
						<span>会员数量</span>
						<!-- <span>
							<el-icon>
								<el-tag class="ml-2" type="danger">月</el-tag>
							</el-icon>
						</span> -->
					</div>
					<div id="MemberView" class="echartsSty"></div>
				</el-card>
			</el-col>
			<el-col :xs="24" :sm="24" :md="12" :lg="6">
				<el-card shadow="hover">
					<!-- /////////// card content //////////// -->
					<div class="card-header">
						<span>新增会员数量</span>
						<!-- <span>
							<el-icon>
								<el-tag class="ml-2" type="warning">月</el-tag>
							</el-icon>
						</span> -->
					</div>
					<div id="AddMemberView" class="echartsSty"></div>
				</el-card>
			</el-col>
			<el-col :xs="24" :sm="24" :md="12" :lg="6">
				<el-card shadow="hover">
					<!-- /////////// card content //////////// -->
					<div class="card-header">
						<span>书本数量</span>
						<!-- <span>
							<el-icon>
								<More />
							</el-icon>
						</span> -->
					</div>
					<div id="booksNumberView" class="echartsSty"></div>
				</el-card>
			</el-col>
		</el-row>
		<el-row :gutter="30" class="row-card" style="margin-top: 15px">
			<el-col :xs="24" :sm="24" :md="12" :lg="6">
				<el-card shadow="hover">
					<!-- /////////// card content //////////// -->
					<div class="card-header">
						<span>已安装书箱</span>
						<!-- <span>
							<el-icon>
								<More />
							</el-icon>
						</span> -->
					</div>
					<div id="installedBookboxView" class="echartsSty"></div>
				</el-card>
			</el-col>

			<el-col :xs="24" :sm="24" :md="12" :lg="6">
				<el-card shadow="hover">
					<!-- /////////// card content //////////// -->
					<div class="card-header">
						<span>库存书箱数量</span>
						<!-- <span>
							<el-icon>
								<More />
							</el-icon>
						</span> -->
					</div>
					<div id="inventoryBookboxView" class="echartsSty"></div>
				</el-card>
			</el-col>
			<el-col :xs="24" :sm="24" :md="12" :lg="6">
				<el-card shadow="hover">
					<!-- /////////// card content //////////// -->
					<div class="card-header">
						<span>会员各年龄段分布</span>
						<!-- <span>
							<el-icon>
								<el-tag class="ml-2" type="warning">月</el-tag>
							</el-icon>
						</span> -->
					</div>
					<!-- <div id="memberAgeGroupView" class="echartsSty"></div> -->
					<div ref="memberAgeGroupContainer" class="echartsSty"></div>
				</el-card>
			</el-col>
			<el-col :xs="24" :sm="24" :md="12" :lg="6">
				<el-card shadow="hover">
					<!-- /////////// card content //////////// -->
					<div class="card-header">
						<span>图书各年龄段分布</span>
						<!-- <span>
							<el-icon>
								<More />
							</el-icon>
						</span> -->
					</div>
					<!-- <div id="booksAgeGroupView" class="echartsSty"></div> -->
					<div ref="bookAgeGroupContainer" class="echartsSty"></div>
				</el-card>
			</el-col>
		</el-row>
		<!-- 柱状图 -->
		<el-card shadow="never" style="margin-top: 15px">
			<template #header>
				<div class="card-header">
					<div class="card-tabs">门店会员数量排行前10</div>
					<!-- <div>
						<el-radio-group v-model="radio2">
							<el-radio-button label="今日" />
							<el-radio-button label="本周" />
							<el-radio-button label="本月" />
							<el-radio-button label="本年" />
						</el-radio-group>
					</div> -->
				</div>
			</template>
			<el-row>
				<el-col :span="18">
					<!-- <div>
            <el-radio-group v-model="radio3" size="small" @change="showSale">
              <el-radio-button label="销售额" />
              <el-radio-button label="访问量" />
            </el-radio-group>
          </div> -->
					<!-- <div ref="saleContainer" style="width: 100%; height: 400px; margin-top: 10px"
						v-if="radio3 === '销售额'"></div>
					<div ref="visitContainer" style="width: 100%; height: 400px; margin-top: 10px"
						v-if="radio3 === '访问量'"></div> -->
					<div id="maxStore"></div>
				</el-col>
				<el-col :span="6">
					<div class="sale-paihang">
						<div class="sale-title">图书数量排行榜</div>
						<div class="list-item" v-for="(item,index) in desktopData.booksNumberTop">
							<span class="sale-item-index">{{index + 1}}</span>
							<span class="sale-item-title">{{item.bookName}}</span>
							<span class="sale-item-amout">{{item.count}}</span>
						</div>
					</div>
				</el-col>
			</el-row>
		</el-card>
	</div>
	<!-- 借阅人数统计 -->
	<el-row class="row-card-jy" style="margin-top: 20px;">
		<el-col :xs="24" :sm="24" :md="24" :lg="24">
			<el-card shadow="hover">
				<div>
					<el-radio-group v-model="dateType" @change="fourteenBookOrderChange">
						<el-radio-button label="FourteenDay">14天</el-radio-button>
						<el-radio-button label="Year">年</el-radio-button>
						<el-radio-button label="Month">月</el-radio-button>
					</el-radio-group>
				</div>
				<div class="card-body">
					<div ref="borrowContainer" style="width: 100%; height: 100%; padding-top: 10px"></div>
				</div>
			</el-card>
		</el-col>
	</el-row>
</template>

<style lang="scss" scoped>
	.echartsSty {
		width: 100%;
		height: 180px;
	}

	#maxStore {
		width: 100%;
		height: 300px;
	}


	.tongji-body {
		font-size: 14px;
	}

	.card-header {
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.card-tabs {
		width: 50%;
	}

	.row-card {
		.el-card {
			height: 230px;
			display: flex;
			flex-direction: column;
			justify-content: space-between;

			.card-header {
				display: flex;
				padding-bottom: 0px;
				justify-content: space-between;
				align-items: center;
				font-size: 13px;
				color: #999999;
			}

			.card-body {
				display: flex;
				flex-direction: column;
				height: 100px;
				align-items: center;

				.card-xse {
					width: 100%;
					height: 70px;
					text-align: left;
					display: flex;
					align-items: center;
					justify-content: flex-start;
					font-size: 32px;
				}

				.card-percent {
					display: flex;
					margin-bottom: 10px;
					width: 100%;
					color: #666;
					display: flex;
					justify-content: space-between;
				}
			}

			.card-bottom {
				font-size: 14px;
				border-top: 1px solid #ccc;
				padding-top: 15px;
			}
		}
	}

	.demo-progress .el-progress--line {
		margin-bottom: 15px;
		width: 250px;
	}

	.sale-list {
		width: 100%;
		display: flex;
		height: 36px;
		align-items: center;
		padding: 0 20px;
	}

	.sale-paihang {
		padding: 15px;
	}

	.sale-title {
		line-height: 36px;
	}

	.list-item {
		width: 100%;
		height: 40px;
		display: flex;
		align-items: center;
		border-bottom: 1px solid #ddd;
	}

	.sale-item-index {
		width: 18px;
		height: 18px;
		background-color: #314659;
		font-size: 12px;
		border-radius: 50%;
		margin-right: 10px;
		color: #ffffff;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.sale-item-amout {
		margin-left: auto;
		color: #999;
	}

	.row-card-jy {
		.el-card {
			height: 550px;
			display: flex;
			flex-direction: column;
			justify-content: space-between;

			.card-header {
				display: flex;
				padding-bottom: 0px;
				justify-content: space-between;
				align-items: center;
				font-size: 13px;
				color: #999999;
			}

			.card-body {
				display: flex;
				flex-direction: column;
				height: 500px;
				align-items: center;

				.card-xse {
					width: 100%;
					height: 70px;
					text-align: left;
					display: flex;
					align-items: center;
					justify-content: flex-start;
					font-size: 32px;
				}

				.card-percent {
					display: flex;
					margin-bottom: 10px;
					width: 100%;
					color: #666;
					display: flex;
					justify-content: space-between;
				}
			}

			.card-bottom {
				font-size: 14px;
				border-top: 1px solid #ccc;
				padding-top: 15px;
			}
		}
	}
</style>

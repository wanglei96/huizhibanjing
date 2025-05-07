父组件传参文件内容引用uni-ui框架[用到分页插件]
<template>
<view>
	<line-chart :args="args" />
	<view class="page-next">
		<uni-pagination @change="pageChange" :pageSize="2" :show-icon="true" :total="total" title="" />
	</view>
</view>
</template>
<script>
import lineChart from "@/components/line/line.vue";
	export default{
		components:{
			lineChart
		},
		data(){
			return {
				args: null,
				page: 0,
				total: 0,
				jsonData: [
					[{
						x: ["02月", "04月", "06月", "08月", "09月", "11月"],//设置字符类型，非Number
						y: [29, 78, 50, 35, 26, 35],
					},
					{
						x: ["02月", "04月", "06月", "08月", "09月", "11月"],//设置字符类型，非Number
						y: [19, 68, 30, 25, 36, 15],
					},],
					[{
						x: ["01月", "02月", "03月", "08月", "09月", "11月"],//设置字符类型，非Number
						y: [39, 38, 50, 85, 56, 35],
					},
					{
						x: ["01月", "02月", "03月", "08月", "09月", "11月"],//设置字符类型，非Number
						y: [29, 28, 30, 25, 36, 85],
					}],
					[{
						x: ["03月", "02月", "03月", "08月", "09月", "11月"],//设置字符类型，非Number
						y: [89, 38, 50, 85, 56, 35],
					},
					{
						x: ["03月", "02月", "03月", "08月", "09月", "11月"],//设置字符类型，非Number
						y: [59, 28, 30, 25, 36, 85],
					}],
					[{
						x: ["04月", "02月", "03月", "08月", "09月", "12月"],//设置字符类型，非Number
						y: [79, 38, 50, 85, 56, 35],
					},
					{
						x: ["04月", "02月", "03月", "08月", "09月", "12月"],//设置字符类型，非Number
						y: [59, 28, 30, 25, 36, 85],
					}]
				]
			}
		},
		methods:{
			pageChange(ev){
				this.page = ev.current;
				this.args = {
					xAxis: {
						textSize: 10, //刻度数字fontSize
						maxNumber: 12,
						splitNumber: 6,
						splitLen: 5,
						marginSplit: 12
					},
					yAxis: {
						textSize: 10, //刻度数字fontSize
						maxNumber: 100, //分段的最大值
						splitNumber: 6, //分成几段
						splitLen: 5, //轴左侧的小横线 -|
						marginSplit: 5 //刻度文字与 “-|”的距离 
					},
					lineStyle: [
						{
							color: "#4caf50",//上部分颜色值
							deepColor: "#fff",//最底下渐变色值
							lineDotType: "wave", //两圆点的连接线 line直线，wave二次贝塞尔曲线
							width: 2, //连线的width
							dash: 0 //是否线条虚线 0实线 1以上虚线
						},
						{
							color: "#03a9f4",//上部分颜色值
							deepColor: "#fff",//最底下渐变色值
							lineDotType: "wave", //两圆点的连接线 line直线，wave二次贝塞尔曲线
							width: 2, //连线的width
							dash: 0 //是否线条虚线 0实线 1以上虚线
						}
					],
					dataJSON: this.jsonData[this.page-1]
				};
			}
		},
		onReady() {
			this.total = this.jsonData.length;
			this.args = {
				xAxis: {
					textSize: 10, //刻度数字fontSize
					maxNumber: 12,
					splitNumber: 6,
					splitLen: 5,
					marginSplit: 12
				},
				yAxis: {
					textSize: 10, //刻度数字fontSize
					maxNumber: 100, //分段的最大值
					splitNumber: 6, //分成几段
					splitLen: 5, //轴左侧的小横线 -|
					marginSplit: 5 //刻度文字与 “-|”的距离 
				},
				lineStyle: [
					{
						color: "#4caf50",//上部分颜色值
						deepColor: "#fff",//最底下渐变色值
						lineDotType: "wave", //两圆点的连接线 line直线，wave二次贝塞尔曲线
						width: 2, //连线的width
						dash: 0 //是否线条虚线 0实线 1以上虚线
					},
					{
						color: "#03a9f4",//上部分颜色值
						deepColor: "#fff",//最底下渐变色值
						lineDotType: "wave", //两圆点的连接线 line直线，wave二次贝塞尔曲线
						width: 2, //连线的width
						dash: 0 //是否线条虚线 0实线 1以上虚线
					}
				],
				dataJSON: this.jsonData[this.page],
			}
		}
	}
</script>
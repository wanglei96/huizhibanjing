<template>
	<view class="pox">
		<view class="legend">
			<label @click="legendClick(0)" class="it-line" :style="{color: legendIndex===0? '#999':'#fe1303'}">1.折线一</label>
			<label @click="legendClick(1)" class="it-line" :style="{color: legendIndex===1? '#999':'#00f000'}">2.折线二</label>
		</view>
		
		<canvas class="echarts" @tap="tapCanvas" canvas-id="line" id="line"></canvas>
		
		<template v-if="tapData.length > 0">
			<view class="position-box" id="position-box" :style="{left: tapData[0].left+'px',top: tapData[0].top+'px'}">
			<view
			class="item-box"
			:style="{color: item.color, borderTop: indx===1?'2rpx solid #999':''}"
			v-for="(item,indx) in tapData"
			:key="indx"> ● {{item.text}}</view>
			</view>
		</template>
		<!-- 以下view可以看完说明删除 -->
		<view class="tip">
			<view>
				<label>是否曲线smooth(true)直线(false):</label>
				<switch style="transform: scale(0.6);" :checked="isSmooth" type="switch" @change="smoothChange" />
			</view>
			<view>
				<label>是否有阴影isLinearColor(true)直线(false):</label>
				<switch style="transform: scale(0.6);" :checked="isLinearColor" type="switch" @change="linearColorChange" />
			</view>
			<view>
				<text>
					说明文档：
					1、点击折线图区域可以去除图例（1.折线一，2.折线二）效果。
					2、参数设置在方法drawLineEchart中optionObj对象（参数类似百度echarts格式）。
				</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props:{
			args: {
				type: Object,
				default:()=>{
					return {}
				}
			}
		},
		data() {
			return {
				isSmooth: true,//是否有曲线
				isLinearColor: true,//是否有阴影
				dom: {
					width: 0,
					height: 0,
					isShow: false
				},
				legendIndex: null,
				tapEvent: null,
				tapPosition: null,
				res: null,
				tapData:[],//点击弹窗中数据
				dataJSON: [],
				xAxisData: []
			}
		},
		watch:{
			args(obj){
				this.tapData = [];
				this.xAxisData = obj.xAxisData;//坐标轴x
				this.dataJSON = obj.dataJSON;//数据
				const res = {
						id: "line",
					 width: uni.upx2px(750),
					height: uni.upx2px(360)
				};
				this.res = res;
				const dpr = uni.getSystemInfoSync().pixelRatio;
				// this.lineDraw("line", res, -1);
				this.drawLineEchart("line",res);
			}
		},
		methods: {
			smoothChange(e){
				console.log("smooth",e);
				this.isSmooth = e.detail.value;
				this.drawLineEchart("line",this.res);
			},
			linearColorChange(e){
				console.log("linearcolor",e);
				this.isLinearColor = e.detail.value;
				this.drawLineEchart("line",this.res);
			},
			legendClick(num){
				this.legendIndex = num;
				this.drawLineEchart("line",this.res);
			},
			tapCanvas(e){
				this.legendIndex = null;
				const pox = {
					x: e.detail.x,
					y: e.detail.y
				};
				this.tapData = [];
				this.drawLineEchart("line",this.res,pox);
				if(this.tapData.length > 0){
					this.$nextTick(()=>{
						const query = uni.createSelectorQuery().in(this);
						query.select('#position-box').boundingClientRect(data => {
							console.log("得到布局位置信息", data);
							if(data && data.width){
								this.dom = {
										w: data.width,
										h: data.height,
								   isShow: true
								};
								if(this.tapData[0].isEnd){
									this.tapData[0].left = this.tapData[0].left - data.width;
								}
							}
						}).exec();
					})
				}
			},
			toFixedNumber(n = 2, val) { //去小数点后2位
				if (typeof val == "number") {
					return val.toFixed(n)/1
				}
			},
			drawLineEchart(ids,res,pox){
				let ctx = uni.createCanvasContext(ids,this),
				w = res.width, h = res.height;
				const optionObj = {
					grid:{
						  left: 30,
						 right: w-20,
						   top: 20,
						bottom: h-25,
					},
					xAxis:{
						// splitNumber: 7,
						splitLineColor: '#838383',//刻度线颜色
						labelTextColor: "#333",//刻度文字颜色
						labelFontSize: 12,//刻度文字fontsize
						splitLineLength: 5,//刻度线长度
						data: this.xAxisData,//x轴上数据
					},
					yAxis:{
						unit: '度',//单位
						labelFontSize: 12,//刻度文字fontsize
						labelTextColor: "#333",//刻度文字颜色
						splitNumber: 4,//分割几段
						maxNumber: 100,//y轴最上方最大值
						splitLineColor: '#838383',//刻度线颜色
						splitLineLength: 5,//刻度线长度
					},
					series:[
						{
							type: "line",
							disabled: false,//是否禁用图例切换
							smooth: this.isSmooth,//是否贝塞尔曲线true,false直线连接
							linearColor:(w,h)=>{//设置渐变色
								const grd = ctx.createLinearGradient(w, 20, w, h);
								grd.addColorStop(0.3, 'orange');
								grd.addColorStop(0.9, 'yellow');
								return grd;
							},
							globalAlpha: 0.9,//设置背景透明值0-1
							isLinearColor: this.isLinearColor,//是否加背景渐变色
							dotColor: "#f00",//圆点中间颜色
							lineColor: "#535353",//边框line颜色
							data:[10, 80, 23, 62, 44, 34]
						},
						{
							type: "line",
							disabled: false,//是否禁用 图例切换
							smooth: this.isSmooth,//是否贝塞尔曲线true,false直线连接
							linearColor:(w,h)=>{//设置渐变色
								const grd = ctx.createLinearGradient(w, 20, w, h);
								grd.addColorStop(0.3, 'green');
								grd.addColorStop(0.9, '#8775e3');
								return grd;
							},
							globalAlpha: 0.6,//设置背景透明值0-1
							isLinearColor: this.isLinearColor,//是否加背景渐变色
							dotColor: "#00f000",//圆点中间颜色
							lineColor: "#555",//边框line颜色
							data:[90, 10, 63, 42, 54, 64]
						},
					]
				};
				if(Array.isArray(optionObj.series)){
					optionObj.series.forEach((item,ix)=>{
						item.data = this.dataJSON[ix];
						ix === this.legendIndex?item.disabled=true:item.disabled = false;
					})
				}
				ctx.beginPath();ctx.globalAlpha = 1;
				//绘制 x轴
				ctx.moveTo(optionObj.grid.left, optionObj.grid.bottom);
				ctx.lineTo(optionObj.grid.right, optionObj.grid.bottom);
				//绘制 y轴
				ctx.moveTo(optionObj.grid.left, optionObj.grid.top);
				ctx.lineTo(optionObj.grid.left, optionObj.grid.bottom);
				//绘制折线
				ctx.stroke();
				
				ctx.beginPath();
				//绘制 x轴刻度
				const len = optionObj.xAxis.data.length-1,
				xAxisLen = w-optionObj.grid.left-(w-optionObj.grid.right),
				xAxisStart = {x: optionObj.grid.left,y: optionObj.grid.bottom};
				const splitLen = xAxisLen/len, fontHeight = 8+optionObj.xAxis.splitLineLength;
				ctx.strokeStyle = optionObj.xAxis.splitLineColor;
				ctx.setFontSize(optionObj.xAxis.labelFontSize);
				ctx.setFillStyle(optionObj.xAxis.labelTextColor);
				for(let i=0;i<=len;i++){
					ctx.moveTo(xAxisStart.x+splitLen*i, xAxisStart.y);
					ctx.lineTo(xAxisStart.x+splitLen*i, xAxisStart.y+optionObj.xAxis.splitLineLength);
					ctx.textAlign = "center";
					ctx.textBaseline = "middle";
					ctx.fillText(optionObj.xAxis.data[i], xAxisStart.x+splitLen*i, xAxisStart.y+fontHeight); 
				}
				ctx.stroke();
				ctx.beginPath();
				//绘制 y轴刻度
				const leny = optionObj.yAxis.splitNumber,
				yAxisLen = h-optionObj.grid.top-(h-optionObj.grid.bottom),
				yAxisStart = {x: optionObj.grid.left,y: optionObj.grid.bottom};
				const splitLeny = yAxisLen/leny, fontHeighty = 3;
				ctx.strokeStyle = optionObj.yAxis.splitLineColor;
				ctx.setFontSize(optionObj.yAxis.labelFontSize);
				ctx.setFillStyle(optionObj.yAxis.labelTextColor);
				for(let i=0;i<=leny;i++){
					ctx.moveTo(yAxisStart.x, yAxisStart.y-splitLeny*i);
					ctx.lineTo(yAxisStart.x-optionObj.yAxis.splitLineLength, yAxisStart.y-splitLeny*i);
					ctx.textAlign = "right";
					ctx.textBaseline = "middle";
					ctx.fillText(optionObj.yAxis.maxNumber/leny*i,
					yAxisStart.x-optionObj.yAxis.splitLineLength-fontHeighty,
					yAxisStart.y-splitLeny*i);
					if(i==leny){//最后一个值 定义的unit单位显示
						ctx.fillText(optionObj.yAxis.unit,
						yAxisStart.x+ctx.measureText(optionObj.yAxis.unit).width/2,
						yAxisStart.y-splitLeny*i-8);//8是y轴离刻度单位 “度”的间距
					}
				}
				ctx.stroke();
				let drawDataFunc=()=>{
					if(Array.isArray(optionObj.series)){//是数组array
					optionObj.series.forEach((obj,inx)=>{
						if(obj.disabled){
							return null;
						}
					//绘制数据区域渐变背景
					if(obj.isLinearColor){//判断是否需要渐变背景色
					ctx.beginPath();
					ctx.globalAlpha = obj.globalAlpha;
					//设置渐变色 color值
					ctx.fillStyle = obj.linearColor(w,h);//渐变色
					ctx.moveTo(optionObj.grid.left, optionObj.grid.bottom-1,1,0,2*Math.PI);
					obj.data.forEach((val,index,arr)=>{
						const point = {
							x: xAxisStart.x+splitLen*index,
							y: yAxisStart.y-val/optionObj.yAxis.maxNumber*yAxisLen
						};
						if(obj.smooth){//采用曲线渲染
							if(index < arr.length-1){
								const pointNext = {
								x: xAxisStart.x+splitLen*(index+1),
								y: yAxisStart.y-(obj.data[index+1])/optionObj.yAxis.maxNumber*yAxisLen
								};
								const ctrlPoint = {
								x: pointNext.y-point.y < 0 ?point.x:pointNext.x,//自己定义的控制点x
								y: pointNext.y-point.y < 0 ?point.y+(pointNext.y-point.y)/2-10:point.y+(pointNext.y-point.y)/2
								};
								ctx.lineTo(point.x, point.y);
								ctx.quadraticCurveTo(ctrlPoint.x, ctrlPoint.y, pointNext.x, pointNext.y);
							}else{
								ctx.arc(point.x, point.y, 3, 0, 2*Math.PI);
							}
						}else{//采用直线渲染
							ctx.lineTo(point.x, point.y,1,0,2*Math.PI);
						}
					})
					ctx.lineTo(optionObj.grid.right, optionObj.grid.bottom,1,0,2*Math.PI);
					ctx.lineTo(optionObj.grid.left, optionObj.grid.bottom,1,0,2*Math.PI);
					ctx.fill();
					}
					//数据曲线 或 直线连接渲染
					const smoothArc = [];
					obj.data.forEach((val,index,arr)=>{
						const point = {
							x: xAxisStart.x+splitLen*index,
							y: yAxisStart.y-val/optionObj.yAxis.maxNumber*yAxisLen
						};
						if(pox){//绘制tap触摸echarts中间显示 内容
							if(point.x-6 < pox.x && pox.x < point.x+6){
								this.tapData.push({
									text: val,
									 top: pox.y,
								   color: obj.dotColor,
								   isEnd: arr.length-1 === index,
									left: arr.length-1 === index ? point.x:point.x
								});
								this.createLineDashTouch(ctx, point, optionObj, val);
							}
						}
						
						ctx.fillStyle = obj.dotColor;//设置圆中间颜色
						ctx.strokeStyle = obj.lineColor;//设置line线颜色
						if(obj.smooth){//采用曲线连接
							if(index < arr.length-1){
								const pointNext = {
									x: xAxisStart.x+splitLen*(index+1),
									y: yAxisStart.y-(obj.data[index+1])/optionObj.yAxis.maxNumber*yAxisLen
								};
								const ctrlPoint = {
									x: pointNext.y-point.y < 0 ?point.x:pointNext.x,//自己定义的控制点x
									y: pointNext.y-point.y < 0 ?point.y+(pointNext.y-point.y)/2-10:
									point.y+(pointNext.y-point.y)/2,//自己定义的控制点y
								};
								//绘制数据 series.data
								
								ctx.beginPath();
								// ctx.moveTo(ctrlPoint.x+1, ctrlPoint.y);
								// ctx.arc(ctrlPoint.x, ctrlPoint.y, 1, 0,2*Math.PI);
								ctx.lineTo(point.x, point.y);
								ctx.quadraticCurveTo(ctrlPoint.x, ctrlPoint.y, pointNext.x, pointNext.y);
								ctx.stroke();
								ctx.beginPath();
								ctx.moveTo(point.x, point.y);
								ctx.arc(point.x, point.y, 3, 0, 2*Math.PI);
								ctx.fill();
							}else{
								ctx.beginPath();
								ctx.moveTo(point.x-3, point.y);
								ctx.arc(point.x, point.y, 3, 0, 2*Math.PI);
								ctx.fill();
								// ctx.stroke();
							}	
						}else{//采用直线连接
							// ctx.arc(point.x, point.y,3, 0, 2*Math.PI);
							smoothArc.push(point);
						}
					})
					if(smoothArc.length>0){
						ctx.beginPath();
						smoothArc.forEach(point=>{
							ctx.lineTo(point.x, point.y);
						})
						ctx.stroke();
						smoothArc.forEach(arc=>{
							ctx.beginPath();
							ctx.arc(arc.x, arc.y, 3, 0, 2*Math.PI);
							ctx.fill();
						})
					}
					
					})
					}else if(typeof optionObj.series == 'object'){//是对象object
					//绘制数据区域渐变背景
					if(optionObj.series.isLinearColor){//判断是否需要渐变背景色
					ctx.beginPath();ctx.globalAlpha = optionObj.series.globalAlpha;
					//设置渐变色 color值
					ctx.fillStyle = optionObj.series.linearColor(w,h);//渐变色
					ctx.arc(optionObj.grid.left, optionObj.grid.bottom-1,1,0,2*Math.PI);
					optionObj.series.data.forEach((val,index,arr)=>{
						const point = {
							x: xAxisStart.x+splitLen*index,
							y: yAxisStart.y-val/optionObj.yAxis.maxNumber*yAxisLen
						};
						if(index < arr.length-1){
							const pointNext = {
							x: xAxisStart.x+splitLen*(index+1),
							y: yAxisStart.y-(optionObj.series.data[index+1])/optionObj.yAxis.maxNumber*yAxisLen
							};
							const ctrlPoint = {
							x: pointNext.y-point.y < 0 ?point.x:pointNext.x,//自己定义的控制点x
							y: pointNext.y-point.y < 0 ?point.y+(pointNext.y-point.y)/2-10:point.y+(pointNext.y-point.y)/2
							};
							// ctx.moveTo(point.x+2, point.y);
							ctx.arc(point.x, point.y, 3, 0, 2*Math.PI);
							// ctx.moveTo(ctrlPoint.x+1, ctrlPoint.y);
							// ctx.arc(ctrlPoint.x, ctrlPoint.y, 1, 0,2*Math.PI);
							ctx.quadraticCurveTo(ctrlPoint.x, ctrlPoint.y, pointNext.x, pointNext.y);
						}else{
							// ctx.moveTo(point.x+2, point.y);
							ctx.arc(point.x, point.y, 3, 0, 2*Math.PI);
						}
					})
					ctx.arc(optionObj.grid.right, optionObj.grid.bottom-1,1,0,2*Math.PI);
					ctx.closePath();
					ctx.fill();
					}
					//绘制数据 series.data
					ctx.fillStyle=optionObj.series.dotColor;//设置圆中间颜色
					ctx.strokeStyle=optionObj.series.lineColor;//设置line线颜色
					optionObj.series.data.forEach((val,index,arr)=>{
						const point = {
							x: xAxisStart.x+splitLen*index,
							y: yAxisStart.y-val/optionObj.yAxis.maxNumber*yAxisLen
						};
						if(index < arr.length-1){
							const pointNext = {
								x: xAxisStart.x+splitLen*(index+1),
								y: yAxisStart.y-(optionObj.series.data[index+1])/optionObj.yAxis.maxNumber*yAxisLen
							};
							const ctrlPoint = {
								x: pointNext.y-point.y < 0 ?point.x:pointNext.x,//自己定义的控制点x
								y: pointNext.y-point.y < 0 ?point.y+(pointNext.y-point.y)/2-10:
								point.y+(pointNext.y-point.y)/2,//自己定义的控制点y
							};
							ctx.beginPath();
							ctx.moveTo(point.x+3, point.y);
							ctx.arc(point.x, point.y, 3, 0, 2*Math.PI);
							ctx.fill();
							// ctx.moveTo(ctrlPoint.x+1, ctrlPoint.y);
							// ctx.arc(ctrlPoint.x, ctrlPoint.y, 1, 0,2*Math.PI);
							ctx.quadraticCurveTo(ctrlPoint.x, ctrlPoint.y, pointNext.x, pointNext.y);
							ctx.stroke();
						}else{
							ctx.beginPath();
							ctx.moveTo(point.x+3, point.y);
							ctx.arc(point.x, point.y, 3, 0, 2*Math.PI);
							ctx.fill();
							ctx.stroke();
						}
					})	
					}
				}
				drawDataFunc();
				//显示tap and touch canvas
				ctx.draw();
				//end draw
			},
			createLineDashTouch(ctx,pox,obj,val){//触摸折线图显示
				ctx.save();
				ctx.beginPath();ctx.strokeStyle="#000";
				ctx.setLineDash([2,5]);
				ctx.moveTo(pox.x, obj.grid.top);
				ctx.lineTo(pox.x, obj.grid.bottom);
				ctx.moveTo(obj.grid.left, pox.y);
				ctx.lineTo(pox.x, pox.y);
				ctx.stroke();
				ctx.restore();
			},
			//绘制线条 line chart老版本修改 ·距离左侧 y轴 |距离为0 lineDraw
			lineDraw(ids, elem, num = -1) {
				let ctx = uni.createCanvasContext(ids,this),isHover = null;
				let grid = {
					alpha: 0.6,//透明度值 1 - 0.1
					  top: (12 * elem.height) / 100, //canvas标签的高度的12%（相对总高的百分比）
				   bottom: ((100 - 18) * elem.height) / 100, //canvas图形距离底部的百分比 18%
					 left: (12 * elem.width) / 100, //距离左侧的百分比（12%总宽度）
					right: ((100 - 8) * elem.width) / 100 //距离右侧百分比（8%总宽度）
				},
				lineColor = "#999", //x,y轴线颜色
				fillColor = "#333", //x,y轴number颜色
				yAxis = this.yAxis,
				lineWidth = 1,
				xAxis = this.xAxis,
				dotStyle = [{
						color: "#fff",
						arcR: 1, //小圆点1 ○ 半径
						dash: 0 //是否线条虚线 0实线 1以上虚线
					},
					{
						color: "#fff",
						arcR: 1, //小圆点2 ○半径
						dash: 0 //是否线条虚线 0实线 1以上虚线
					}
				],
				lineStyle = this.lineStyle,
				dataJSON = this.dataJSON; //数据data
				lineStyle.forEach((ep, vp) => {
					if (num == vp) {
						this.lineColorArr[vp] = {
							color: ep.color
						};
					} else if (num == -1) {
						this.lineColorArr[vp] = {
							color: ep.color
						};
					} else {
						this.lineColorArr[vp] = {
							color: "#888"
						};
					}
				});
				ctx.beginPath();
				ctx.setLineWidth(lineWidth);
				ctx.setTextAlign("right");
				ctx.setTextBaseline("middle");
				ctx.setStrokeStyle(lineColor);
				ctx.setFillStyle(fillColor);
				ctx.moveTo(grid.left, grid.top);
				ctx.lineTo(grid.left, grid.bottom);
				ctx.setFontSize(yAxis.textSize);
				
				for (let n = 1; n <= yAxis.splitNumber; n++) {
					ctx.moveTo(grid.left, grid.bottom - (grid.bottom - grid.top) / yAxis.splitNumber * n + lineWidth);
					ctx.lineTo(grid.left - yAxis.splitLen, grid.bottom - (grid.bottom - grid.top) / yAxis.splitNumber * n +
						lineWidth);
					ctx.fillText(this.toFixedNumber(1, yAxis.maxNumber / yAxis.splitNumber * n), grid.left - yAxis
						.splitLen - lineWidth - yAxis.marginSplit, grid.bottom - (grid.bottom - grid.top) / yAxis
						.splitNumber * n + lineWidth);
				}
				ctx.moveTo(grid.left, grid.bottom);
				ctx.lineTo(grid.right, grid.bottom);
				ctx.setTextAlign("center");
				ctx.setFontSize(xAxis.textSize);
				
				if(typeof dataJSON[0].x[0] != "number"){//非number类型数据
					xAxis.splitNumber = dataJSON[0].x.length-1;
					xAxis.maxNumber = dataJSON[0].x.length-1;
				}
				
				for (let n = 1; n <= xAxis.splitNumber+1; n++) {
					ctx.moveTo(grid.left + (grid.right - grid.left) / xAxis.splitNumber * n -(grid.right -
								grid.left) / xAxis.splitNumber, grid.bottom);
					ctx.lineTo(grid.left + (grid.right - grid.left) / xAxis.splitNumber * n -(grid.right -
								grid.left) / xAxis.splitNumber, grid.bottom+xAxis.splitLen);
						//this.toFixedNumber(1, xAxis.maxNumber / xAxis.splitNumber * n)
					if( typeof dataJSON[0].x[n-1] != "number"){//数据非number类型
						ctx.fillText(dataJSON[0].x[n-1], grid.left + (grid.right -
								grid.left) / xAxis.splitNumber * n-(grid.right -
								grid.left) / xAxis.splitNumber, grid.bottom + xAxis.splitLen + lineWidth +
							xAxis.marginSplit)
					}else{//number类型
						ctx.fillText(this.toFixedNumber(1, xAxis.maxNumber / xAxis.splitNumber * n), grid.left + (grid.right -
								grid.left) / xAxis.splitNumber * n - lineWidth, grid.bottom + xAxis.splitLen + lineWidth +
							xAxis.marginSplit)
					}	
				}
				ctx.stroke();
				let tapDraw = (obj, data) => {
					ctx.save();
					ctx.beginPath();
					ctx.setFillStyle("#2C405A");
					ctx.setStrokeStyle("#E6A23C");
					ctx.setLineDash([5]);
					ctx.setLineWidth(1);
					ctx.moveTo(grid.left, obj.y - obj.offsetTop);
					ctx.lineTo(grid.right, obj.y - obj.offsetTop);
					ctx.moveTo(obj.x - obj.offsetLeft, grid.top);
					ctx.lineTo(obj.x - obj.offsetLeft, grid.bottom);
					ctx.stroke();
					ctx.restore();
					// ctx.draw(true, function(res,obj){
					// 	console.log('draw success', ctx);
					// });
				}
				let drawLineData = (dataX, dataY, ix) => {
					//折线下方区域的渐变色
					ctx.beginPath();
					ctx.setGlobalAlpha(grid.alpha);//设置透明度
					const grd = ctx.createLinearGradient(grid.left, grid.top, grid.left, grid.bottom);
					grd.addColorStop(0, lineStyle[ix].color);
					grd.addColorStop(1, lineStyle[ix].deepColor);
					
					// Fill with gradient
					ctx.setFillStyle(grd);
					ctx.setStrokeStyle(lineStyle[ix].color);
					ctx.setLineDash([lineStyle[ix].dash]);
					dataX.forEach((ele, indx) => {
						let x = grid.left + (grid.right - grid.left) * ((indx+1) / xAxis.maxNumber)-(grid.right-grid.left) / xAxis.splitNumber,
							y = grid.bottom - (grid.bottom - grid.top) * (dataY[indx] / yAxis.maxNumber) +lineWidth;
						if (indx >= dataX.length - 1) {} else {
							let x2 = grid.left + (grid.right - grid.left) * ((indx + 2) / xAxis.maxNumber)-(grid.right -grid.left) / xAxis.splitNumber,
							y2 = grid.bottom - (grid.bottom - grid.top) * (dataY[indx + 1] / yAxis.maxNumber) + lineWidth;
							let dis = {
								x: x + (x2 - x) / 3 + (x2 - x) / 2,
								y: y2 - y > 0 ? y + (y2 - y) / 3 - (y2 - y) / 5 : y + (y2 - y) / 3 + (y2 - y) / 5
							};
							if(indx === 0){
								ctx.moveTo(grid.right, grid.bottom);
								ctx.lineTo(x, grid.bottom);
								ctx.lineTo(x, y);
							}
							if (lineStyle[ix].lineDotType == "line") { //直线连接
								ctx.lineTo(x2, y2);
							}else if (lineStyle[ix].lineDotType == "wave") { //二次贝塞尔曲线连接
								ctx.quadraticCurveTo(dis.x, dis.y, x2, y2);
							}
						}
					});
					ctx.closePath();
					// ctx.stroke();
					ctx.fill();
					//绘制文字 小圆点
					dataX.forEach((ele, indx) => {
						let x,y;
						if(typeof ele !== "number"){
							x = grid.left + (grid.right - grid.left) * ((indx+1) / xAxis.maxNumber)-(grid.right -
								grid.left) / xAxis.splitNumber,
							y = grid.bottom - (grid.bottom - grid.top) * (dataY[indx] / yAxis.maxNumber) +lineWidth;
						}else{
							x = grid.left + (grid.right - grid.left) * (ele / xAxis.maxNumber) - lineWidth,
							y = grid.bottom - (grid.bottom - grid.top) * (dataY[indx] / yAxis.maxNumber) +lineWidth;
						}
						if (indx >= dataX.length - 1) {} else {
							let x2 = grid.left + (grid.right - grid.left) * ((indx + 2) / xAxis.maxNumber)-(grid.right -grid.left) / xAxis.splitNumber,
							y2 = grid.bottom - (grid.bottom - grid.top) * (dataY[indx + 1] / yAxis.maxNumber) + lineWidth;
							let dis = {
								x: x + (x2 - x) / 3 + (x2 - x) / 2,
								y: y2 - y > 0 ? y + (y2 - y) / 3 - (y2 - y) / 5 : y + (y2 - y) / 3 + (y2 - y) / 5
							};
							ctx.beginPath();
							ctx.setGlobalAlpha(1);//设置透明度
							ctx.setStrokeStyle(lineStyle[ix].color);
							ctx.moveTo(x,y);
							if (lineStyle[ix].lineDotType == "line") { //直线连接
								ctx.lineTo(x2, y2);
							}else if (lineStyle[ix].lineDotType == "wave") { //二次贝塞尔曲线连接
								ctx.quadraticCurveTo(dis.x, dis.y, x2, y2);
							}
							ctx.stroke();
						}
						//显示 x，y的值
						ctx.beginPath();
						ctx.setGlobalAlpha(1);//设置透明度
						ctx.setFillStyle(dotStyle[ix].color);
						ctx.setLineDash([dotStyle[ix].dash]);
						ctx.arc(x, y, dotStyle[ix].arcR, 0, 2 * Math.PI);
						ctx.fill();
						ctx.stroke();
						
						ctx.setFillStyle(lineStyle[ix].color);
						ctx.setTextBaseline("bottom");
						ctx.setFontSize(12);
						ctx.fillText(dataY[indx], x, y-2);
						
					})
				}
				dataJSON.forEach((e, v) => {
					if (v == num && num != -1) {
						drawLineData(e.x, e.y, v);
					} else if (num == -1) {
						drawLineData(e.x, e.y, v);
					} else {
			
					}
				})
				
				if (this.tapEvent != null && isHover != null) {
					tapDraw(this.tapEvent, elem);
				}
				ctx.draw();
			}
		}
	}
</script>

<style scoped>
	.pox{
		position: relative;
	}
	.pox >>> .position-box{
		position: absolute;
		z-index: 922;
		color: #fff;
		font-size: 30rpx;
		transition: linear 0.3s;
	}
	.pox >>> .item-box{
		padding: 10rpx;
		background: rgba(0,0,0,0.7);
	}
	.legend{
		text-align: right;
		/* background: rgba(255,255,255,0.9); */
		/* right: 0;
		top: 0; */
	}
	.it-line{
		padding: 15rpx;
		font-size: 28rpx;
		display: inline-block;
		vertical-align: middle;
	}
	.it-line:active{background: #f1f1f1;}
	.echarts{
		width: 100%;
		height: 360rpx;
		display: block;
		background: #fff;
		position: relative;
		z-index: 1;
		/* border-bottom: 1rpx solid #ececec; */
	}
	.tip{
		padding: 20rpx;color: #f00;font-size: 0.8em;
	}
</style>
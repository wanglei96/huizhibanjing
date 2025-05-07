<template>
	<view>
		 
		<view class="container">
			<view class="contentPanel"  >
				<view class="form_item_90">
					<view class="left20 f27"><text class="c_red">*</text>孩子姓名</view>
					<view class="right80 right f26">
						<input placeholder="请输入孩子姓名" v-model="name" style="height: 90rpx;line-height: 90rpx;" placeholder-style="font-size:26rpx" />
					</view>
				</view>
				<picker mode="date"  @change="bindTimeChange">
					<view class="form_item_90">
						<view class="left20 f27"><text class="c_red"></text>生日</view>
						<view class="right80 right ">
							<view class="f26">{{birthday}}</view>
						</view>
					</view>
				</picker>
				<view class="form_item_90">
					<view class="left20 f27"><text class="c_red">*</text>电话</view>
					<view class="right80 right f26">
						<input placeholder="请输入电话" v-model="phone" style="height: 90rpx;line-height: 90rpx;" placeholder-style="font-size:26rpx" :disabled="true"/>
					</view>
				</view>
				<picker @change="bindPickerChange" :range="genderArray">
						<view class="form_item_90">
							<view class="left20 f27"><text class="c_red"></text>性别</view>
							<view class="right80 right f26">
								<view class="f26">{{gender}}</view>
							</view>
						</view>
				</picker>
				<view class="form_item_90">
					<view class="left20 f27"><text class="c_red"></text>地址</view>
					<view class="right80 right f26">
						<input placeholder="请输入地址" v-model="address" style="height: 90rpx;line-height: 90rpx;" placeholder-style="font-size:26rpx" />
					</view>
				</view>
				<view class="form_item_90">
					<view class="left20 f27"><text class="c_red"></text>学校</view>
					<view class="right80 right f26">
						<input placeholder="请输入学校" v-model="school" style="height: 90rpx;line-height: 90rpx;" placeholder-style="font-size:26rpx" />
					</view>
				</view>
				<picker @change="bindPickerGradeChange" :range="gradeList" range-key="name" >
					<view class="form_item_90">
						<view class="left20 f27"><text class="c_red"></text>年级</view>
						<view class="right80 right f26">
							<input placeholder="请选择年级" v-model="gradeName" style="height: 90rpx;line-height: 90rpx;" placeholder-style="font-size:26rpx"  disabled="true"/>
						</view>
					</view>
				</picker>
				<picker @change="bindPickerClassesChange" :range="classesList" range-key="name" >
					<view class="form_item_90">
						<view class="left20 f27"><text class="c_red"></text>班级</view>
						<view class="right80 right f26">
							<input placeholder="请选择班级" v-model="classesName" style="height: 90rpx;line-height: 90rpx;" placeholder-style="font-size:26rpx" disabled="true"/>
						</view>
					</view>
				</picker>
				<view class="form_item_90" style="border: 0;">
					<view class="left20 f27"><text class="c_red"></text>推荐人</view>
					<view class="right80 right f26">
						<input placeholder="请输入推荐人(选填)" v-model="parentName" style="height: 90rpx;line-height: 90rpx;" placeholder-style="font-size:26rpx" />
					</view>
				</view>
				<view class="h50"></view>
				<view class="next" @click="clickToNext()">下一步</view>
			</view>
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
				name:'',//姓名
				birthday:'请选择生日',//生日
				phone:'',//电话
				gender:'请选择性别',//性别
				genderIndex:0,
				genderArray:['女','男'],
				address:'',//地址
				school:'',//学校
				gradeID:'',//年级ID
				gradeName:'',//年级名称
				classesID:'',//班级ID
				classesName:'',//班级名称
				parentName:'',//推荐人
				gradeList:[],
				classesList:[],
			};
		},
		onReady() { },
		onShow() { },
		onPageScroll(e) { that.head_is_fixed = e.scrollTop > 0 ? 1 : 0 },
		onLoad() {
			that = this;
			// that.phone = uni.getStorageSync("mobilePhone");
			that.bindData(); //
			that.bindGetGradeList();//绑定年级
			that.bindGetClassesList();//绑定班级
		},
		methods: {
			bindData(){
				var data = {};
				data.id = common.getMemberId();
				common.call("GetViewMember", data, function(result) {
					console.log(result.data)
					that.name =result.data.name
					if(result.data.birthday ==null){
						that.birthday ="请选择生日"
					}else{
						that.birthday =result.data.birthdayText.substring(0,10)
					}
					that.phone =result.data.mobilePhone
					console.log(result.data.gender)
					if(result.data.gender == false){
						console.log(1)
						that.genderIndex = 0
						that.gender = "女生"
					}else if(result.data.gender == true){
						console.log(2)
						that.genderIndex = 1
						that.gender = "男生"
					}else{
						console.log(3)
						that.gender = "请选择性别"
					}
					// that.genderIndex =result.data.gender
					that.address =result.data.address
					that.school =result.data.school
					that.gradeID =result.data.gradeId //年级ID
					that.gradeName =result.data.gradeName //年级名称
					that.classesID =result.data.classId //班级ID
					that.classesName =result.data.className //班级名称
					that.parentName =result.data.referrer
				});
			},
			bindTimeChange: function(e) {//生日选择器
			    this.birthday = e.detail.value
			},
			bindPickerChange: function(e) {//性别选择器
			    console.log('picker发送选择改变，携带值为', e.detail.value)
				if(e.detail.value == 0 ){
					that.gender = "女生"
					
				}else{
					that.gender = "男生"
				}
				that.genderIndex =  e.detail.value
			},
			clickToNext(){//客服二维码
				if(that.name =="" || that.name ==null){
					uni.showToast({
						title: '请输入孩子姓名',
						icon: "none",
						duration:2500
					});
					return
				}
				let data = {
					id:common.getMemberId(),
					name:that.name,//姓名
					
					mobilePhone:that.phone,//电话
					gender:that.genderIndex,//性别
					address:that.address,//地址
					school:that.school,//学校
					gradeId:that.gradeID,//年级
					classId:that.classesID,//班级
					referrer:that.parentName,//推荐人
					isSign:1,//是否预登记
				}
				if(that.birthday != "请选择生日"){
					data.birthday = that.birthday//生日
				}
				console.log(data)
				common.call("UpdateMember", data, function(result) {
					setTimeout(() => {
						uni.navigateTo({
							url: 'serviceQRCode'
						});
					}, 200);
				});
			},
			clickToBack(){
				uni.navigateBack();
			},
			bindGetGradeList(){//绑定年级
				var data = {};
				data.pageSize = 1000
				common.call("GetGradeList", data, function(result) {
					that.gradeList = result.data.dataList
				});
			},
			bindGetClassesList(){//绑定班级
				var data = {};
				data.pageSize = 1000
				common.call("GetClassesList", data, function(result) {
					that.classesList = result.data.dataList
				});
			},
			bindPickerGradeChange(e){
				console.log(e.detail.value)
				that.gradeID = that.gradeList[e.detail.value].id
				that.gradeName =  that.gradeList[e.detail.value].name
			},
			bindPickerClassesChange(e){
				console.log(e.detail.value)
				that.classesID = that.classesList[e.detail.value].id
				that.classesName =  that.classesList[e.detail.value].name
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
	.next{
		width: 200rpx; height: 70rpx;line-height: 70rpx;  background-color: #FACC3F; color: #000000; border-radius: 50rpx; text-align: center;font-weight: 600;font-size: 30rpx; margin: 0 auto;
	}
</style>
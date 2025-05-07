<template>
	<view>
		<view class="container center">
			<view class="h50"></view>
			<image show-menu-by-longpress="true" :src="customerServiceImageFilePath"  mode="widthFix" style="width: 100%;"></image>
			<!-- <view class="center line50 c_white">会员信息填写完毕</view> -->
			<view class="center line100 c_white" style="font-weight: bold;">长按识别二维码，加客服微信</view>
			<view class="center line100 " style="font-weight: bold; color: #FACC3F;" @click="clickCallPhone()">或点击此处拨打客服电话</view>
			<view class="h50"></view>
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
				adminRootUrl:config.adminRootUrl +"/",//后端图片地址
				statusBarHeight,// 状态栏 高度
				head_is_fixed:0,
				customerServiceImageFilePath:'',
			};
		},
		onReady() {
			
		},
		onShow() {
			
		},
		onPageScroll(e) {
		    that.head_is_fixed = e.scrollTop > 0 ? 1 : 0
		},
		onLoad() {
			that = this;
			that.bindData(); //
		},
		methods: {
			bindData(){
				var data = {};
				if(common.getCommunityId() == "" || common.getCommunityId() == null ){
					data.condition = 'id=(SELECT id FROM company where is_default is true)'
				}else{
					data.id = common.getCommunityId()
				}
				common.call("GetViewCompanyList", data, function(result) {
					//console.log(result.data)
					
					if(result.data.dataList[0].customerServiceImageFilePath == "" || result.data.dataList[0].customerServiceImageFilePath == null){
						uni.showToast({
							title: result.data.dataList[0].company_name + '未上传客服图片', 
							icon: "none",
							duration:2500
						});
						return
					}
					that.customerServiceImageFilePath = that.adminRootUrl + result.data.dataList[0].customerServiceImageFilePath
					console.log("ServiceImageFilePath:" + that.customerServiceImageFilePath)
				});
			},
			clickToBack(){
				uni.navigateBack();
			},
			clickCallPhone(){
				var data = {};
				if(common.getCommunityId() == "" || common.getCommunityId() == null ){
					data.condition = 'id=(SELECT id FROM company where is_default is true)'
				}else{
					data.id = common.getCommunityId()
				}
				data.pageSize = 100000
				common.call("GetViewCompanyList", data, function(result) {
					console.log(result)
					if(result.data.dataList[0].servicePhone == "" || result.data.dataList[0].servicePhone == null){
						uni.showToast({
							title: result.data.dataList[0].company_name + '未填写客服电话', 
							icon: "none",
							duration:2500
						});
						return
					}
					wx.makePhoneCall({
						phoneNumber: result.data.dataList[0].servicePhone //仅为示例，并非真实的电话号码
					})
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
<template>
	<view class="content">
		<view class="contentPanel">
			<mp-html :content="content" />
		</view>
	</view>
</template>

<script>
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	let statusBarHeight = uni.getSystemInfoSync().statusBarHeight; // 状态栏 高度
	import mpHtml from '@/components/mp-html/mp-html'
	let that = null;
	export default {
		data() {
			return {
				imageRootUrl:config.imageRootUrl,//图片地址
				statusBarHeight,// 状态栏 高度
				head_is_fixed:0,
				content:'',
			};
		},
		components: {
			mpHtml
		},
		onReady() {
			
		},
		onShow() {
			
		},
		onPageScroll(e) {
		    that.head_is_fixed = e.scrollTop > 0 ? 1 : 0
		},
		onLoad(option) {
			that = this;
			that.bindData(); //
			uni.setNavigationBarTitle({
				title: option.name
				
			});

		},
		methods: {
			bindData(){
				var data = {};
				//BusinessHoursConfiguration
				if(common.getCommunityId() == "" || common.getCommunityId() == null ){
					data.condition = 'company_id=(SELECT id FROM company where is_default is true)'
				}else{
					data.companyId = common.getCommunityId()
				}
				data.sysSettingCode = 'DeliveryRules'
				common.call("GetViewCompanySettingList", data, function(result) { 
					console.log(result.data.dataList[0].value) 
					that.content = result.data.dataList[0].value
				})
			},
			clickToBack(){
				uni.navigateBack();
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
			margin-top: 0px;
		/* #endif */
		/* #ifdef H5 */     
			margin-top: 0px;
		/* #endif */
		z-index: 8;
	}
</style>
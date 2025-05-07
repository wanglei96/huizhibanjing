<template>
	<view class="content">
		<view class="page">
			<view class="pd_10">
				<mp-html :content="content" />
			</view>
		</view>
		<view class="f14"></view>
		<view class="indent f14"></view>
		<view style="font-size: 30rpx; font-weight: bold;"></view>
	</view>
</template>
<script>
	import config from "@/js/config.js";
	import common from "@/js/common.js";
	import mpHtml from '@/components/mp-html/mp-html'
	let that = null;
	export default {
		data() {			return {				content: "",
			};
		},
		onLoad(option) {
			that = this;
			that.id= option.cmsContentID
			that.getViewContent();
		},
		methods: {
			getViewContent(code) {
				var self = this;
				var data = {};
				data.id = that.id;
				common.call('GetViewCmsContent', data, function(res) {
					that.content = res.data.text
				});
			},
		}
	};
</script>

<style>
	page {
		width: 750rpx;
		box-sizing: border-box;
		line-height: 52rpx;
		background-color: #5EAC59;
	}

	.indent {
		text-indent: 2rem;
	}

	.pd_10 {
		padding: 10px;
	}

	.f14 {
		font-size: 28rpx;
	}

	.bold {
		font-weight: bold;
	}
</style>
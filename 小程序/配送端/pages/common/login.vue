<template>
	<view class="register">
		<view class="content">
			<!-- 头部logo -->
			<view class="header">
				<image :src="logoImage"></image>
			</view>
			<!-- 注册input主体 -->
			<view class="main">
				<!-- <wInput v-model="loginName" type="text"  placeholder="请输入用户名"></wInput>
				<wInput v-model="password" type="password" maxlength="12" placeholder="请输入密码" isShowPass></wInput> -->
				<view class="main-list oBorder" style="box-sizing: content-box;">
					<input style="border: 0; " class="main-input"  v-model="mobilePhone" type="text"  placeholder="请输入手机号"/><!-- type="password" -->
				</view>
				<view class="main-list oBorder" style="box-sizing: content-box;">
					<input style="border: 0; " class="main-input"  v-model="password" type="password"     maxlength="12" placeholder="请输入密码" v-show="!showPassword"/>  
					<input style="border: 0; " class="main-input"  v-model="password" type="text"    maxlength="12" placeholder="请输入密码"  v-show="showPassword"/>
					
					<!-- <input style="border: 0; " class="main-input"  v-model="password" :type="inputType"     maxlength="12" placeholder="请输入密码" v-show="!showPassword"/>  -->
					
					<!-- 是否可见密码 -->
					<image  class="img cuIcon"  :class="showPassword?'cuIcon-attention':'cuIcon-attentionforbid'"  @tap="showPass"></image>
				</view>
			</view>
			<!-- 注册按钮 -->
			<view  @click="startReg()" class="login"  style="">登 录</view>
		</view>
	</view>
</template>

<script>
	var _this;
	import wInput from '@/components/register/watch-input.vue'  
	// import wButton from '../../components/register/watch-button.vue'
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js"; 
	export default {
		data() {
			return {
				logoImage: '/static/headLogo.png',//logo图
				mobilePhone: '', // 用户名
				password: '', //密码
				showPassword: false, //是否显示明文
			}
		},
		components: {
			wInput,
			// wButton,
		},
		onShow() {
			_this = this;
			console.log(uni.getStorageSync("mobilePhone"))
			if(uni.getStorageSync("mobilePhone") == ""){
				console.log("没有登录")
			}else{
				uni.switchTab({
					url: "/pages/index/index"
				})
			}
		},
		methods: {
			showPass(){
				//是否显示密码
				this.showPassword = !this.showPassword
				// if(this.showPassword){
				// 	return 'text'
				// }else{
				// 	return 'password'
				// }
			},
			inputType(){
				//处理值
				if(this.showPassword){
					return "text"
				}else{
					return "password"
				}
			},
			startReg() {
				console.log(this.mobilePhone)
				console.log(this.password)
				if (this.mobilePhone.length == "") {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '请输入手机号'
					});
					return false;
				}
				if (this.password.length == "") {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '请输入用户名密码'
					});
					return false;
				}
				
				setTimeout(function() {
					var data = {};
					data.mobilePhone = _this.mobilePhone;
					data.password = _this.password;
					common.call('DeliverLogin', data, function(result) {
						console.log(result)
						uni.setStorageSync("RoleList", result.data.role);
						uni.setStorageSync("DelivererID", result.data.deliverer.id); 
						uni.setStorageSync("CompanyID", result.data.deliverer.companyId);
						uni.setStorageSync("mobilePhone", _this.mobilePhone);
						uni.setStorageSync("islogin", "true");
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: '登录成功'
						});
						uni.switchTab({
							url: "/pages/index/index"
						})
					});
				}, 1000)
			},
		}
	}
</script>

<style>
	@import url("../../components/register/css/icon.css");
	@import url("../../static/css/main.css");
	.main-list{
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		height: 36upx;   /* Input 高度 */
		color: #333333;
		padding: 32upx;
		margin-top:24upx;
		margin-bottom: 24upx;
	}
	
	.img{
		width: 32upx;
		height: 32upx;
		font-size: 32upx;
	}
	
	.main-input{
		border: 0;
		flex: 1;
		text-align: left;
		font-size: 28upx;
		/* line-height: 100upx; */
		padding-right: 10upx;
		margin-left: 20upx;
	}
	
	.vercode {
		color: rgba(0,0,0,0.7);
		font-size: 24upx;
		line-height: 100upx;
	}
	
	.vercode-run {
		color: rgba(0,0,0,0.4) !important;
	}
	
	.oBorder{
	    border: none;
	    border-radius: 2.5rem ;
	    -webkit-box-shadow: 0 0 60upx 0 rgba(43,86,112,.1) ;
	    box-shadow: 0 0 60upx 0 rgba(43,86,112,.1) ;
	}
	.codeView {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
	}

	.canvas-img-code {
		display: inline-block;
	}
	.login{
		width: 80%; height: 100rpx; line-height: 100rpx; margin-left: 10%; color: #ffffff; text-align: center;background: linear-gradient(to right, rgb(94, 172, 89), rgb(94, 172, 89)); border-radius: 50rpx;
		margin-top: 40rpx;
	}
</style>

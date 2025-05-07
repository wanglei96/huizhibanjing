<template>
	<view class="content-view">
		<!-- <uni-section title="修改密码" class="tableSty" type="line">
            <view class="example">
                <uni-forms ref="valiForm" :rules="rules" label-width='80px' :modelValue="valiFormData">
                    <uni-forms-item label="原密码" required name="oldPassword">
                        <uni-easyinput v-model="valiFormData.oldPassword" placeholder="请输入原密码" />
                    </uni-forms-item>
                    <uni-forms-item label="新密码" required name="newPassword">
                        <uni-easyinput v-model="valiFormData.newPassword" placeholder="请输入新密码" />
                    </uni-forms-item>
                    <uni-forms-item label="确认密码" required name="confirmPassword">
                        <uni-easyinput v-model="valiFormData.confirmPassword" placeholder="请确认密码" />
                    </uni-forms-item>
                </uni-forms>
            </view>
		<button type="primary" @click="submit('valiForm')">提交</button> 
		</uni-section>-->
		<view class="h40"></view>
		<view class="contentPanel">
			<uni-section title="修改密码" class="" type="line">
			</uni-section>
			<view class="h40"></view>
			<uni-forms ref="valiForm" :rules="rules" label-width='80px' :modelValue="valiFormData">
				<uni-forms-item label="原密码" required name="oldPassword">
				    <uni-easyinput v-model="valiFormData.oldPassword" placeholder="请输入原密码" />
				</uni-forms-item>
				<uni-forms-item label="新密码" required name="newPassword">
				    <uni-easyinput v-model="valiFormData.newPassword" placeholder="请输入新密码" />
				</uni-forms-item>
				<uni-forms-item label="确认密码" required name="confirmPassword">
				    <uni-easyinput v-model="valiFormData.confirmPassword" placeholder="请确认密码" />
				</uni-forms-item>
			</uni-forms>
			<view class="h40"></view>
			<button type="primary" @click="submit('valiForm')">提交</button> 
		</view>
	</view>

</template>

<script>
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	
	import unieasyinput from '@/components/uni-easyinput/uni-easyinput.vue'
	import uniforms from '@/components/uni-forms/uni-forms.vue'
	import uniformsitem from '@/components/uni-forms-item/uni-forms-item.vue'
	export default {
		data() {
			return {
				valiFormData: {},
				formData: {
					name: '111',
					age: '2222',
					hobby: "hobby",
				},
				rules: {
					oldPassword: {
						rules: [{
							required: true,
							errorMessage: '原密码不能为空'
						}]
					},
					newPassword: {
						rules: [{
							required: true,
							errorMessage: '新密码不能为空'
						}]
					},
					confirmPassword: {
						rules: [{
							required: true,
							errorMessage: '确认密码不能为空'
						}]
					},
				},
			}
		},
		components: {
			unieasyinput,
			uniforms,
			uniformsitem
		},
		mounted() {},
		methods: {
			submit(ref) {
				this.$refs[ref].validate().then(res => {
					console.log(res);
					var data = {};
					data.id = uni.getStorageSync("DelivererID");
					data.oldPassword = res.oldPassword;
					data.newPassword = res.newPassword;
					data.confirmPassword = res.confirmPassword;
					common.call('DeliverUpdatePassword', data, function(result) {
						console.log(result)
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: '修改成功'
						});
						uni.navigateBack();
					});
				}).catch(err => {
					console.log('err', err);
				})
			},
		}
	}
</script>

<style lang="scss">
	@import url("/css/common.css");

	page {
		background: #5EAC59;
	}

	.content-view {
		overflow: hidden;
		position: absolute;
		    width: 90%;
		    margin-left: 5%;
	}

	.tableSty {
		margin: 20px;
		border-radius: 8px;
		padding: 0 15px 15px;
	}
</style>

<template>
    <view class="content-view">
        <view class="infoView">
            <img src="../../static/home.png" class="myImg" alt="" />
            <view class="info">
                <view class="item"><span></span>姓名：{{delivererInfo.name}}</view>
                <view class="item"><span></span>电话：{{delivererInfo.mobilePhone}}</view>
                <view class="item"><span></span>所属门店：{{delivererInfo.companyName}}</view>
            </view>
        </view>
        <view class="funView">
            <view @click="toPath('Delivery')" v-show="isDistribution">
                <img src="../../static/Delivery.png" alt="" />
                <text>配送路线管理</text>
            </view>
            <view @click="toPath('password')">
                <img src="../../static/password.png" alt="" />
                <text>修改密码</text>
            </view>
			
			<view @click="clickToLogin()">
			    <img src="../../static/password.png" alt="" />
			    <text>退出登陆</text>
			</view>
        </view>
    </view>
</template>

<script>
	let that = null;
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
    export default {
        data() {
            return {
				delivererInfo:{},
				isDistribution:false, //是否配送员
            }
        },
        mounted() {},
		onShow() {
			let roleList =  uni.getStorageSync("RoleList");
			console.log(roleList);
			that.isDistribution =false
			for (let i = 0; i < roleList.length; i++) {
				// if(roleList[i].[1])
				console.log(JSON.stringify(roleList[i]))
				let stringdata = JSON.stringify(roleList[i])
				//console.log(stringdata.indexOf("配送员") != -1);
				if(stringdata.indexOf("配送员") != -1){
					that.isDistribution =true
				}
			}
			that.bindData();
		},
		onLoad() {
			that = this;
			
		},
        methods: {
			bindData(){//绑定数据
				var data = {};
				data.delivererId = uni.getStorageSync("DelivererID");
				common.call('GetDeliverInfo', data, function(result) {
					console.log(result)
					that.delivererInfo = result.data
				});
			},
            toPath(to) {
                uni.navigateTo({
                	url:to
                });
            },
			clickToLogin(){//退出登錄
				uni.removeStorageSync("DelivererID");
				uni.removeStorageSync("mobilePhone");
				uni.removeStorageSync("islogin");
				uni.navigateTo({
					url: '../common/login'
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
    .funView {
        min-height: 300px;
        padding: 10px;
        background: #fff;
        border-radius: 8px;
        margin: 0 20px;

        view {
            display: flex;
            height: 60px;
            align-items: center;
            border-bottom: 1px solid #eee;
            padding: 0 15px;

            img {
                width: 25px;
                height: 25px;
                margin-right: 10px;
            }
        }
    }

    .content-view {
        overflow: auto;
        // border-top: 1px solid rgba(0, 0, 0, 0.1);
        // background-image: linear-gradient(#FACC3F, 20%, #fff);
    }

    .infoView {
        padding: 10px;
        display: flex;
        margin: 20px 20px;

        .myImg {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            background: #fff;
        }

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
            color: #ffffff;
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
            margin-left: 20px;

            >.item:nth-child(1) {
                font-size: 16px;

                span {
                    font-size: 14px;
                }
            }
        }
    }
</style>
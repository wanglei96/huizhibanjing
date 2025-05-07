<template>
    <view class="">
		<view class="h40"></view>
		<view class="funView">
		    <view  v-for="(item,index) in list" :key="index" @click="clickToDeliverPlace(item)">
				{{item.name}}
		    </view>
		</view>
       <!-- <view class="wayTable">
            <view>
				<uni-table ref="table"  border stripe emptyText="暂无更多数据">
					<uni-tr>
						<uni-th width="50" align="center">顺序</uni-th>
						<uni-th align="center">名称</uni-th>
						<uni-th width="120" align="center">操作</uni-th>
					</uni-tr>
					<uni-tr v-for="(item, index) in deliverPlaceSortList" :key="index">
						<uni-td>{{ index + 1 }}</uni-td>
						<uni-td>
							<view  class="name" style="color: #2A82E4; font-size: 26rpx;">
								{{ item.name }}
							</view>
						</uni-td>
						<uni-td align="center">
							<button v-show="index != 0" @click="clickMoveUp(item,index)">上移</button>
							<button v-show="index + 1 != deliverPlaceSortList.length" @click="clickMoveDown(item,index)">下移</button>
						</uni-td>
					</uni-tr>
				</uni-table>
            </view>
        </view> -->
    </view>
</template>

<script>
	let that = null;
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	// import uCharts from '@/components/u-charts/u-charts.js';
	import unitable from '@/components/uni-table/uni-table.vue'
	import unitbody from '@/components/uni-tbody/uni-tbody.vue'
	import unitr from '@/components/uni-tr/uni-tr.vue'
	import unitd from '@/components/uni-td/uni-td.vue'
	import unith from '@/components/uni-th/uni-th.vue'
	import unithead from '@/components/uni-thead/uni-thead.vue'
	
    export default {
        data() {
            return {
				deliverPlaceSortList:[],
				list:[]
            }
        },
		components: {
			// uCharts,
			unitable,
			unitbody,
			unitr,
			unitd,
			unith,
			unithead,
		},
		onLoad() {
			that = this;
			that.bindData();
			that.bindGetViewDeliverLineList();
		},
        mounted() {},
        methods: {
			bindGetViewDeliverLineList(){ //绑定路线列表数据
				var data = {};
				data.delivererId = uni.getStorageSync("DelivererID");
				// data.bookpickerId = uni.getStorageSync("DelivererID");
				data.pageSize = 10000;
				data.condition = "deliverer_id = '" + uni.getStorageSync("DelivererID") + "'" + " or bookpicker_id = " + uni.getStorageSync("DelivererID")
				data.companyId = uni.getStorageSync("CompanyID")
				common.call('GetViewDeliverLineList', data, function(result) {
					console.log(result)
					that.list= result.data.dataList
				});
			},
			clickToDeliverPlace(item){
				uni.navigateTo({
					url:"DeliverPlace?deliverLineId=" + item.id
				});
			},
			bindData(){//绑定数据
				var data = {};
				data.delivererId = uni.getStorageSync("DelivererID");
				// data.delivererId = 2;
				common.call('GetDeliverPlaceSort', data, function(result) {
					console.log(result)
					that.deliverPlaceSortList= result.data
				});
			},
			clickMoveUp(item,index){ //上移
				let idone = item.id
				let idtow =  that.deliverPlaceSortList[index - 1].id
				let tableValue = []
				let tableData = {
					id:idone
				}
				tableValue.push(tableData)
				let tableData1 = {
					id:idtow
				}
				tableValue.push(tableData1)
				console.log(tableValue)
				var data = {};
				data.delivererId = uni.getStorageSync("DelivererID");
				data.tableValue = tableValue;
				common.call('GetDeliverPlaceSort', data, function(result) {
					console.log(result) 
					that.deliverPlaceSortList= result.data 
				});
			},
			clickMoveDown(item,index){ //下移
				let idtow = item.id
				let idone =  that.deliverPlaceSortList[index + 1].id
				console.log(idone);
				console.log(idtow);
				let tableValue = []
				let tableData = {
					id:idone
				}
				tableValue.push(tableData)
				let tableData1 = {
					id:idtow
				}
				tableValue.push(tableData1)
				console.log(tableValue)
				var data = {};
				data.delivererId = uni.getStorageSync("DelivererID");
				data.tableValue = tableValue;
				common.call('GetDeliverPlaceSort', data, function(result) {
					console.log(result) 
					that.deliverPlaceSortList= result.data 
				});
			},
		}
    }
</script>

<style lang="scss">
	@import url("/css/common.css");
	page {
		background: #5EAC59;
	}
    .wayTable {
        flex-shrink: 1;
        overflow: auto;
        margin-top: 20px;

        .butSty {
            display: flex;
            font-size: 13px;

            button {
                font-size: 13px;
                width: 60px;
                height: 40px;
                // line-height: 40px;
                display: flex;;
                align-items: center;
                justify-content: center;
                margin: 0 4px;
            }
        }

        >view {
            margin: 20rpx;
            background-color: rgb(198, 255, 205);
            border-radius: 20rpx;
            padding: 15rpx;
        }

        .bookcase {
            font-size: 15px;
            margin-bottom: 10px;
            margin-left: 5px;
        }

        table {
            width: 100%;
            background-color: #fff;
            border-collapse: collapse;

            td,
            th {
                text-align: center;
                border: 1px solid rgb(219, 219, 219);
                padding: 8px 0;
                font-size: 14px;
            }
        }
    }

    .content-view {
        height: 100%;
        display: flex;
        flex-direction: column;
    }
	.funView {
	    padding: 20rpx;
	    background: #fff;
	    border-radius: 20rpx;
	    margin: 0 40rpx;
	
	    view {
	        display: flex;
	        height: 90rpx;
	        align-items: center;
	        border-bottom: 2rpx solid #eee;
	        padding: 0 30rpx;
	    }
	}
</style>
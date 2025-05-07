<template>
	<view>
		<view class="container">
			<view style="">
				<view class="" v-for="(item,index) in bookSerieslist" :key="index">
					<view class="line80 center" v-if="item.bookSeriesName !='无'">{{item.bookSeriesName}}</view>
					<view v-for="(book, bookIndex) in item.booksList" :key="bookIndex" class="booksListPanel">
						<view style="position: relative;height: 100% ;width: 100%;"> <!-- overflow: hidden; -->
							<view style="cursor: pointer;" @tap.stop="clickToBooksDetail(book)">
								<image :src="book | toBookImageFilePath" mode="aspectFit"
									style=" height: 210rpx;display: block;width: 95% ;  border:  2rpx solid rgb(211 211 211); border-radius: 10rpx;">
								</image>
								<view class="line50 center f24">{{book.bookName | toName}}</view>
							</view>
						</view>
					</view>
				</view>
				<view class="loading-text" style="text-align: center;" v-if="showLoadMore">{{ loadingText }}</view>
				<view class="h40"></view>
			</view>
		</view>
	</view>
</template>

<script>
	import common from "@/js/common.js";
	import config from "@/js/config.js";
	import util from "@/js/util.js";
	let that = null;
	export default {
		data() {
			return {
				imageRootUrl: config.imageRootUrl, //图片地址
				adminRootUrl: config.adminRootUrl + "/", //后端图片地址
				searchVal: '',
				bookSerieslist: [],
				loadingText: "正在加载...",
				showLoadMore: false,
				pageIndex: 0, //页数
				pageSize: 10, //条数
				pageCount: 0, //最大页数
				scrollViewHeight: 600,
				requiredBookCategoryId:'',//分类ID
			};
		},
		onPullDownRefresh() { //刷新   //下拉刷新，需要自己在page.json文件中配置开启页面下拉刷新 "enablePullDownRefresh": true
			uni.stopPullDownRefresh();
		},
		onReachBottom() { //上拉加载，需要自己在page.json文件中配置"onReachBottomDistance"
			if ((that.pageIndex + 1) == that.pageCount) {
				that.loadingText = "没有更多数据了";
			} else if ((that.pageIndex + 1) < that.pageCount) {
				that.pageIndex = that.pageIndex + 1;
				this.showLoadMore = true;
				that.loadingText = "加载更多";
				that.bindData();
			}
		},
		onReady() {},
		onShow() {},
		onLoad(option) {
			that = this;
			uni.setNavigationBarTitle({
				title: option.name
			});
			that.requiredBookCategoryId = option.id
			that.bookSerieslist = []
			that.bindData(); //
		},
		methods: {
			clickSearch() {
				that.bookSerieslist = []
				that.bindData(); //
			},
			bindData() {
				var data = {};
				if(common.getCommunityId() == "" || common.getCommunityId() == null ){
					 data.condition = 'company_id=(SELECT id FROM company where is_default is true)'
				}else{
					data.companyId = common.getCommunityId()
				}
				data.requiredBookCategoryId = that.requiredBookCategoryId;
				data.pageIndex = that.pageIndex
				data.pageSize = that.pageSize
				common.call("GetRequiredBookInfoList", data, function(result) {
					that.bookSerieslist.push(...result.data.dataList);
					that.pageCount = result.data.pageCount;
					that.pageIndex = result.data.pageIndex;
					that.pageSize = result.data.pageSize;
					if (that.bookSerieslist.length == 0 || that.bookSerieslist.length <= 10) {
						that.showLoadMore = true
						that.loadingText = "没有更多数据了"
					}
					console.log(that.bookSerieslist)
				});
			},
			clickToBooksDetail(item) { //跳转图书详情
				console.log(item)
				// console.log(item.bookSkuId)
				setTimeout(() => {
					uni.navigateTo({
						url: 'booksDetail?bookSkuID=' + item.bookSkuId
					});
				}, 200);
			},
			scroll: function(e) {
				//console.log(e)
				//this.old.scrollTop = e.detail.scrollTop
			},
			upper(e) { //向上滑动加载更多
				console.log("滚动到顶部")
			},
			lower(e) {
				console.log("滚动到底部")
			},
		},
		filters: {
			toName(name) {
				//console.log(title)
				if (name == "undefined" || name == ""|| name == null) {
					return ""
				} else {
					if (name.length > 6) {
						return name.substring(0, 6) + "..."
					} else {
						return name
					}
				}
			},
			toBookImageFilePath(item){
				if(item.bookSkuImageFilePath !="" && item.bookSkuImageFilePath !=null){
					return that.adminRootUrl + "/" + item.bookSkuImageFilePath
				}else{
					return "../../static/image/common/default.jpg"
				}
			}
		}
	};
</script>

<style lang="scss">
	@import url("/css/common.css");

	page {
		background: #5EAC59;
	}

	.container {

		top: 0px;
		margin-left: 0%;
		width: 100%;
	}

	.searchPanel {
		width: 100%;
		background-color: #5EAC59;
		z-index: 9999;
		height: 110rpx;
		position: fixed;
	}

	.contentPanel {
		padding: 10rpx;
		border-radius: 24rpx;
		position: fixed;
		justify-content: center;
		align-items: center;
		display: flex;
		width: calc(100% - 20rpx - 10%);
		margin-left: 5%;
	}

	.booksListPanel {
		display: inline-block;
		width: 30%;
		// height: 250rpx;
		// line-height: 250rpx;
		height: 190rpx;
		line-height: 190rpx;
		text-align: center;
		font-size: 36rpx;
		margin-left: 2%;
		margin-top: 20rpx;
	}
</style>
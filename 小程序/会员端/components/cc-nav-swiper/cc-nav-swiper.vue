<template>
	<swiper class="cate-nav-swiper"  :duration="300">
		<swiper-item v-for="(page_data, page_data_index) of pageDataList" :key="page_data_index">
			<uni-grid :column="columnCount" :highlight="false" :square="square" :showBorder="false">
				<uni-grid-item v-for="(cate_nav, cate_nav_index) of page_data" :key="cate_nav_index">
					<view class="cate-nav" hover-class="none" @click="itemClick(cate_nav)" style="position: relative;overflow: hidden;height: 100% ;width: 90%;margin-left: 5%;">
						<image class="cate-nav-image" :src="cate_nav | toBookImageFilePath" mode="aspectFit" style=" height: 210rpx;display: block;width: 95% ;border:  2rpx solid rgb(211 211 211); border-radius: 10rpx;"> </image>
						<text class="cate-nav-title">{{cate_nav.bookSkuBookName | toTitle}}</text>
					</view>
				</uni-grid-item>
			</uni-grid>
		</swiper-item>
	</swiper>
</template>

<script>
	import config from "@/js/config.js";
	import uniGrid from '@/components/uni-grid/uni-grid.vue' //
	import uniGridItem from '@/components/uni-grid-item/uni-grid-item.vue' //
	export default {
		components: {
			uniGrid,
			uniGridItem
		},
		data() {
			return {
				imageRootUrl: config.imageRootUrl, //图片地址
				adminRootUrl: config.adminRootUrl + "/", //后端图片地址
			}
		},
		props: {
			square: {
				type: Boolean,
				default: true,
			},
			rowCount: {
				type: [Number, String],
				default: 2,
			},
			columnCount: { // 每列显示个数
				type: [Number,String],
				default: 2,
			},
			list: {
				type: Array,
				required: true,
				default: function() {
					return [
						// image: '',
						// title: ''
					]
				}
			},
		},
		computed: {
			pageDataList() {
				const cate_count_per_page = this.rowCount * this.columnCount;
				const page_count = this.list.length / cate_count_per_page;
				const page_data_list = [];

				for (let i = 0; i < page_count; ++i) {
					page_data_list[i] = this.list.slice(i * cate_count_per_page, (i + 1) * cate_count_per_page);
				}
				console.log(page_data_list)
				return page_data_list;
			}
		},
		methods: {
			itemClick(item) {
				this.$emit("itemClick", item);
				uni.navigateTo({
					url: "/pages/home/booksDetail?bookSkuID=" + item.bookSkuId
				})

			},
			// onSwiperChange: function(e) {
			// 	const cate_count_per_page = this.rowCount * this.columnCount;
			// 	const page_count = this.list.length / cate_count_per_page;
			// 	const page_data_list = [];
				
			// 	for (let i = 0; i < page_count; ++i) {
			// 		page_data_list[i] = this.list.slice(i * cate_count_per_page, (i + 1) * cate_count_per_page);
			// 	}
			// 	console.log(e.detail.current)
			// 	if (e.detail.current === page_data_list.length - 1) {
			// 	    console.log('到达最后一个swiper-item');
			// 	}
			// }
		},
		filters: {
			toTitle(title){
				//console.log(title)
				if(title == "undefined"){
					return ""
				}else{
					if(title.length >6){
						return title.substring(0,6) + "..."
					}else{
						return title
					}
				}
			},
			toBookImageFilePath(item){
				if(item.imageFilePath !="" && item.imageFilePath !=null){
					return config.adminRootUrl + "/" + item.imageFilePath
				}else{
					return "../../static/image/common/default.jpg"
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
	$cate-image-size: $uni-img-size-lg;

	.cate-nav-swiper {
		height: $cate-image-size * 6.8;
	}

	.cate-nav {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: column;
		align-items: center;
	}

	.cate-nav-image {
		width: $cate-image-size;
		height: $cate-image-size;
	}

	.cate-nav-title {
		font-size: 24rpx;
		// color: $uni-text-color-grey;
		// margin-top: $uni-spacing-row-base;
	}
</style>
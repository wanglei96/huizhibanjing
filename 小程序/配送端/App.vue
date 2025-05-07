<script>
	export default {
		onLaunch: function() {
			//console.log("App Launch");
			// if (uni.canIUse('getUpdateManager')) {
			// 	const updateManager = uni.getUpdateManager()
			// 	updateManager.onCheckForUpdate(function(res) {
			// 		console.log('onCheckForUpdate====', res)
			// 		// 请求完新版本信息的回调
			// 		if (res.hasUpdate) {
			// 			console.log('res.hasUpdate====')
			// 			updateManager.onUpdateReady(function() {
			// 				uni.showModal({
			// 					title: '更新提示',
			// 					content: '新版本已经准备好，是否重启应用？',
			// 					success: function(res) {
			// 						console.log('success====', res)
			// 						// res: {errMsg: "showModal: ok", cancel: false, confirm: true}
			// 						if (res.confirm) {
			// 							// 新的版本已经下载好，调用 applyUpdate 应用新版本并重启
			// 							updateManager.applyUpdate()
			// 						}
			// 					}
			// 				})
			// 			})
			// 			updateManager.onUpdateFailed(function() {
			// 				// 新的版本下载失败
			// 				uni.showModal({
			// 					title: '已经有新版本了哟~',
			// 					content: '新版本已经上线啦~，请您删除当前小程序，重新搜索打开哟~'
			// 				})
			// 			})
			// 		}
			// 	})
			// }
		},
		onShow: function() {
			//console.log("App Show");
		},
		onHide: function() {
			//console.log("App Hide");
		}
	};
</script>

<style>
	/*每个页面公共css */
	@import "./css/uni.css";
	@import "./css/iconfont.css";
	@import "./css/base.css";
	uni-checkbox .uni-checkbox-input {
		width: 30rpx !important;
		height: 30rpx !important;
	}
	uni-checkbox .uni-checkbox-wrapper {
		font-size: 29rpx;
	}
</style>
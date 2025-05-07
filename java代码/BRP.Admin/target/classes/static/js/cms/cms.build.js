/*
 * 绑定控件
 */
function bindControlsBase() {
	bindCmsLanguage();
}
		

//生成选中页面
function CmsBuild() {
	var data = getCreateParameter();
	//获取语言编码
	data.cmsLanguageCode = getCmsLanguageCodeWhenCreate();
	if(null == data.cmsLanguageCode || data.cmsLanguageCode.length>0){
		call("CmsBuild", data, function(
			result) {
		 //alert(JSON.stringify(result));
		});
		getProgress();
	}else{
		alert("没有选择语言");
	}
	
}
function getProgress(){
	var date1 = new Date();
	var data = new Object();
	data.notLoad = true;
	var html = "<div class='divProgress' id='divProgress'>还剩<input class='inputProgress' id='inputProgress'/>项没有生成完毕</div>";
	$("#divDetailDialog").append(html);
	 $("#divProgress").css({
		    position : "fixed",
		    top : "15px",
		    left : "488px"
	}); 
	var interval = setInterval(
			function() {
				call("GetCmsBuildProgress",data,function(result){
					$("#inputProgress").val(result.data.size);
					if(result.data.building == 'false' && result.data.size == "0"){
						clearInterval(interval);
						$("#divProgress").remove();
						var date2 = new Date();
						console.log(date2.getTime()-date1.getTime());
					}
				});
			}, 1000);
}
	
/*
 绑定CMS多语言
 */
function bindCmsLanguage(){
	if(currentCmsLanguageCode == "Default"){
		return;
	}
	var data = new Object();
	data.cmsSiteId = $.cookie("cmsSiteId");
	call("GetCmsLanguageListCustom",data,function(result){
		if(!result.data.isOnCmslaunguage){
			return;
		}
		 $('#cmsLanguageRange').combobox({
	         valueField: "code", //Value字段
	         textField: "name", //Text字段
	         multiple: true,
	         data: result.data.cmsLanguages,
	         onClick: function (node, checked) {
	             //取消全选
	        	 $("#chooseAllCmsAllLanguage").removeAttr("checked")
	         }
	     });
		 
		 $('#cmsLanguageRange').combobox("setValue",currentCmsLanguageCode);
		$(".createByCmslanguage").show();
	})
}
//获取要生成的语言编码
function getCmsLanguageCodeWhenCreate(){
	var cmsLanguageList = [];
	//没有设置多语言
	if(typeof(currentCmsLanguageCode) == "undefined" || null == currentCmsLanguageCode || currentCmsLanguageCode == "Default"){
		return null;
	}
	var checked = $("#chooseAllCmsAllLanguage").prop("checked");
	if(checked){
		var arr = $('#cmsLanguageRange').combobox("getData");
		for(var i=0;i<arr.length;i++){
			cmsLanguageList.push(arr[i].code);
		}
		return cmsLanguageList;
	}
	var cmsLanguageList = $('#cmsLanguageRange').combobox("getValues");
	alert(JSON.stringify(cmsLanguageList));
	return cmsLanguageList
}
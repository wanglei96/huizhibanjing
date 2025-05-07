const formatTime = date => {
	const year = date.getFullYear()
	const month = date.getMonth() + 1
	const day = date.getDate()
	const hour = date.getHours()
	const minute = date.getMinutes()
	const second = date.getSeconds()

	return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatDate = date => {
	const year = date.getFullYear()
	const month = date.getMonth() + 1
	const day = date.getDate()

	return [year, month, day].map(formatNumber).join('-');
}

/**
 * @param date String
 * @returns String in style "2021-08-27 18:42"
 * @description Modern JS DateTime format (built-in)
 */
const simpleDateTimeFormat = (date) => {
	const timeOptions = {
		timeStyle: "short",
		hour12: false
	};
	let finalDate = new Date(date);
	return finalDate.toLocaleDateString("fr-CA") + " " + finalDate.toLocaleTimeString("zh-CN", timeOptions);
}

/**
 * @param date String
 * @returns string in style "2021-08-27"
 * @description Modern JS Date format (built-in).
 */
const simpleDateFormat = (date) => {
	return new Date(date).toLocaleDateString("fr-CA");
}

/**
 * input: Date string in YYYY-MM-dd
 * outputs:  month (月) and day (日) only
 * @param {string} date
 */
function formatMonthDay(date) {
	return new String(date)
		.split(/\d{4}\-/)[1]
		.replace("\-", "月")
		.concat("日");
}

/**
 * 格式化日期格式【2020-04-25 22:12:12】
 */
function getFormatTime(date) {
	var datetime = new Date(date);
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1;
	var day = datetime.getDate();
	var hour = datetime.getHours();
	var minute = datetime.getMinutes();
	var second = datetime.getSeconds();
	return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':');
}

///////获取时间差函数/////////////
function getTimeDiff(time) {

	var diff = '';
	var date = new Date(time.replace('T', " ").replace(/-/g, '/'));
	var time_diff = new Date().getTime() - new Date(date).getTime(); //时间差的毫秒数 

	//计算出相差天数 
	var days = Math.floor(time_diff / (24 * 3600 * 1000));
	if (days > 0) {
		diff += days + '天前';
	} else {
		//计算出小时数 
		var leave1 = time_diff % (24 * 3600 * 1000);
		var hours = Math.floor(leave1 / (3600 * 1000));
		if (hours > 0) {
			diff += hours + '小时前';
		} else {
			var leave2 = leave1 % (3600 * 1000);
			var minutes = Math.floor(leave2 / (60 * 1000));
			if (minutes > 0) {
				diff += minutes + '分钟前';
			} else {
				diff += '刚刚';
				// var leave3 = leave2 % (60 * 1000);
				// var seconds = Math.round(leave3 / 1000);
				// if (seconds > 0) {
				//   diff += seconds + '秒钟前';
				// }
			}
		}
		//计算相差分钟数 

		//计算相差秒数 

	}
	return diff;
}

const formatNumber = n => {
	n = n.toString()
	return n[1] ? n : '0' + n
}
/*
ArrayBuffer转十六进制字符串
*/
function ab2hex(buffer) {
	var hexArr = Array.prototype.map.call(
		new Uint8Array(buffer),
		function (bit) {
			return ('00' + bit.toString(16)).slice(-2)
		}
	)
	return hexArr.join('');
}
/*
十六进制字符串转ArrayBuffer
*/
function hex2ab(hex) {
	var typedArray = new Uint8Array(hex.match(/[\da-f]{2}/gi).map(function (h) {
		return parseInt(h, 16)
	}));
	var buffer = typedArray.buffer
	return buffer;
}
// /截取字符串，多余的部分用...代替  
function subString(str, len) {
	if (str == null) {
		return "";
	}
	var strlen = 0;
	var s = "";
	if (str == null) {
		str = ""
	}
	for (var i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) > 128) {
			strlen += 2;
		} else {
			strlen++;
		}
		s += str.charAt(i);
		if (strlen >= len) {
			return s + "...";
		}
	}
	return s;
}
/**
 * 滚动到底部
 * @param {*} id 
 */
function scrollToBottom(id) {
	wx.createSelectorQuery().select("#" + id).boundingClientRect(function (rect) {
		wx.pageScrollTo({
			scrollTop: rect.bottom
		})
	}).exec();
}
/**
 * json对象转日期
 * @param {Object} jsonDate
 */
function jsonToDate(jsonDate) {
	if (jsonDate == null) {
		return "";
	}
	var d = new Date();
	d.setTime(jsonDate.time);
	return d;
}
/**
 * json对象转日期字符串
 * @param {Object} jsonDate
 */
function jsonToDateString(jsonDate) {
	if (jsonDate == null) {
		return "";
	}
	var d = jsonToDate(jsonDate);
	var strDate = formatDate(d);
	return strDate;
}
/**
 * json对象转日期时间字符串
 * @param {Object} jsonDate
 */
function jsonToDateTimeString(jsonDate) {
	if (jsonDate == null) {
		return "";
	}
	var d = jsonToDate(jsonDate);
	var strDate = formatTime(d);
	return strDate;
}
/**
 * 转成Pascal字符串
 * @param {Object} text
 */
function toPascal(text) {
	if (!text) {
		return "";
	}
	var arrPart = text.split("_");
	var pascalText = "";
	for (var i = 0; i < arrPart.length; i++) {
		var part = arrPart[i];
		pascalText += part.substr(0, 1).toUpperCase() + part.substr(1);
	}
	return pascalText;
}
/**
 * 转成Cameral字符串
 * @param {Object} text
 */
function toCameral(text) {
	if (!text) {
		return "";
	}
	var arrPart = text.split("_");
	var pascalText = "";
	for (var i = 0; i < arrPart.length; i++) {
		if (i == 0) {
			pascalText += arrPart[i].substr(0, 1).toLowerCase() + arrPart[i].substr(1);
		} else {
			pascalText += arrPart[i].substr(0, 1).toUpperCase() + arrPart[i].substr(1);
		}
	}
	return pascalText;
}

// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.format = function (fmt) { //author: meizz   
	var o = {
		"M+": this.getMonth() + 1,                 //月份   
		"d+": this.getDate(),                    //日   
		"h+": this.getHours(),                   //小时 
		"H+": this.getHours(),                   //小时   
		"m+": this.getMinutes(),                 //分   
		"s+": this.getSeconds(),                 //秒   
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度   
		"S": this.getMilliseconds()             //毫秒   
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

/*
添加年
示例：
new Date().addYears(5);
new Date().addYears(-4);
*/
Date.prototype.addYears = function (years) {
	var month = this.getMonth();
	var year = this.getFullYear() + years;
	var day = this.getDate();
	var resultDate = new Date(year, month, day, this.getHours(), this.getMinutes(), this.getSeconds(), this.getMilliseconds());
	return resultDate;
}

/*
添加月
示例：
new Date().addMonths(5);
new Date().addMonths(-4);
*/
Date.prototype.addMonths = function (months) {
	var currentMonth = this.getMonth();
	var year = this.getFullYear() + parseInt((currentMonth + months) / 12);
	var month = (this.getMonth() + months) % 12;
	var dayInMonth = new Date(year, month + 1, 0).getDate();
	var day = this.getDate();
	if (day > dayInMonth) {
		day = dayInMonth;
	}
	var resultDate = new Date(year, month, day, this.getHours(), this.getMinutes(), this.getSeconds(), this.getMilliseconds());
	return resultDate;
}

/*
添加天数
示例：
new Date().addDays(5);
*/
Date.prototype.addDays = function (days) {
	var dateValue = this.valueOf();
	dateValue += days * 24 * 60 * 60 * 1000;
	return new Date(dateValue);
}

/*
添加小时
示例：
new Date().addHours(5);
new Date().addHours(-4);
*/
Date.prototype.addHours = function (hours) {
	var dateValue = this.valueOf();
	dateValue += hours * 60 * 60 * 1000;
	return new Date(dateValue);
}
/*
添加分钟
示例：
new Date().addMinutes(5);
new Date().addMinutes(-4);
*/
Date.prototype.addMinutes = function (minutes) {
	var dateValue = this.valueOf();
	dateValue += minutes * 60 * 1000;
	return new Date(dateValue);
}
/*
添加秒
示例：
new Date().addSeconds(5);
new Date().addSeconds(-4);
*/
Date.prototype.addSeconds = function (seconds) {
	var dateValue = this.valueOf();
	dateValue += seconds * 1000;
	return new Date(dateValue);
}
/*
删除指定位置的元素
*/
Array.prototype.remove = function (index) {
	if (isNaN(index) || index > this.length) {
		return false;
	}
	for (var i = 0, n = 0; i < this.length; i++) {
		if (this[i] != this[index]) {
			this[n++] = this[i];
		}
	}
	this.length -= 1
}
/*
在指定位置插入元素
*/
Array.prototype.insert = function (index, item) {
	this.splice(index, 0, item);
};

/*
清空对象数据（浅层）
*/
function clearObjectValues(obj) {
	Object.keys(obj).forEach(key => {
		obj[key] = ''; // 或者根据你的需要设置为undefined或者''
	});
}

/**
 * @param {Object} days
 * 日期字符串增加指定天数
 * 2月份 以及 30天月份处理 ....
 */
function getAddDays(dateText, days){
	// 输入日期时间字符串
	var dateString = dateText;
	// 将日期时间字符串转换为Date对象
	var date = new Date(dateString);
	// 获取当前时间的毫秒数
	var currentTime = date.getTime();
	// 加上一天的毫秒数
	var newTime = currentTime + (days * 24 * 60 * 60 * 1000);
	// 设置新的毫秒数为Date对象
	date.setTime(newTime);
	// 将日期时间转换为字符串格式
	return getFormatTime(date);
}

export default {
	getFormatTime,
	formatTime,
	formatDate,
	formatMonthDay,
	ab2hex,
	hex2ab,
	subString,
	getTimeDiff,
	scrollToBottom,
	jsonToDate,
	jsonToDateString,
	jsonToDateTimeString,
	simpleDateFormat,
	simpleDateTimeFormat,
	toPascal,
	toCameral,
	clearObjectValues,
	getAddDays
}

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
		function(bit) {
			return ('00' + bit.toString(16)).slice(-2)
		}
	)
	return hexArr.join('');
}
/*
十六进制字符串转ArrayBuffer
*/
function hex2ab(hex) {
	var typedArray = new Uint8Array(hex.match(/[\da-f]{2}/gi).map(function(h) {
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
	wx.createSelectorQuery().select("#" + id).boundingClientRect(function(rect) {
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
function strToDate(str) {
    var date = new Date(Date.parse(str.replace(/-/g, "/").replace(/T/g, " ")));
    return date;
}
module.exports = {
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
	strToDate
}

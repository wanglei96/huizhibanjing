/*
添加到收藏夹
*/
function addFavorite(url, title) {
    window.external.AddFavorite(url, title);
}


/*
 * 获取地址栏的值
 */
function getQuery(key) {
	const query = window.location.search.substring(1);
	const keys = query.split("&");
	for (let i = 0; i < keys.length; i++) {
		const pair = keys[i].split("=");
		if (pair[0] === key) {
			return decodeURI(pair[1])
		}
	}
	return null;
}


/*
复制到剪贴板
*/
function copyToClipboard(text) {
    var oInput = document.createElement("input");
    oInput.value = text;
    document.body.appendChild(oInput);
    oInput.select(); // 选择对象
    document.execCommand("Copy"); // 执行浏览器复制命令
    document.body.removeChild(oInput);
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
添加天数
示例：
new Date().addDays(5);
new Date().addDays(-4);
*/
Date.prototype.addDays = function (days) {
    var dateValue = this.valueOf();
    dateValue += days * 24 * 60 * 60 * 1000;
    return new Date(dateValue);
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
    var resultDate = new Date(year, month, day);
    return resultDate;
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
Array.prototype.joinObject = function(seperator, propertyName) {
	var result = "";
	for (var i = 0; i < this.length; i++) {
		if (i > 0) {
			result += seperator;
		}
		if (propertyName) {
			result += this[i][propertyName];
		} else {
			result += this[i];
		}
	}
	return result;
}
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/g, "");
}
String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "");
}
/*
格式化字符串
*/
String.prototype.format = function (args) {
    if (arguments.length > 0) {
        var result = this;
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                var reg = new RegExp("({" + key + "})", "g");
                result = result.replace(reg, args[key]);
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] == undefined) {
                    return "";
                }
                else {
                    var reg = new RegExp("\\{" + i + "\\}", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
        return result;
    }
    else {
        return this;
    }
}
/*
添加到收藏夹
*/
function addFavorite(url, title) {
    window.external.AddFavorite(url, title);
}

/*
取两位小数
*/
function formatAmount(amount) {
    if (amount == null || amount == "") {
        return "0.00";
    }
    return amount.toFixed(2);
}
/*
首字母大字
*/
function capitalize(value) {
    if (!value) {
        return ''
    }
    value = value.toString()
    return value.charAt(0).toUpperCase() + value.slice(1)
}

//****************************************************************
//* 名　　称：DataLength
//* 功    能：计算数据的长度
//* 输入参数：fData：需要计算的数据
//* 输出参数：返回fData的长度(Unicode长度为2，非Unicode长度为1)
//*****************************************************************
function DataLength(fData) {
    var intLength = 0
    for (var i = 0; i < fData.length; i++) {
        if ((fData.charCodeAt(i) < 0) || (fData.charCodeAt(i) > 255))
            intLength = intLength + 2
        else
            intLength = intLength + 1
    }
    return intLength
}

//****************************************************************
//* 名　　称：IsEmpty
//* 功    能：判断是否为空
//* 输入参数：fData：要检查的数据
//* 输出参数：True：空                              
//*           False：非空
//*****************************************************************
function IsEmpty(fData) {
    return ((fData == null) || (fData.length == 0))
}


//****************************************************************
//* 名　　称：IsDigit
//* 功    能：判断是否为数字
//* 输入参数：fData：要检查的数据
//* 输出参数：True：是0到9的数字                              
//*           False：不是0到9的数字 
//*****************************************************************
function IsDigit(fData) {
    return ((fData >= "0") && (fData <= "9"))
}


//****************************************************************
//* 名　　称：IsInteger
//* 功    能：判断是否为正整数
//* 输入参数：fData：要检查的数据
//* 输出参数：True：是整数，或者数据是空的                            
//*           False：不是整数
//*****************************************************************
function IsInteger(fData) {
    //如果为空，返回true
    if (IsEmpty(fData))
        return true
    if ((isNaN(fData)) || (fData.indexOf(".") != -1) || (fData.indexOf("-") != -1))
        return false

    return true
}

//****************************************************************
//* 名　　称：IsEmail
//* 功    能：判断是否为正确的Email地址
//* 输入参数：fData：要检查的数据
//* 输出参数：True：正确的Email地址，或者空                              
//*           False：错误的Email地址
//*****************************************************************
function IsEmail(fData) {
    if (IsEmpty(fData))
        return true
    if (fData.indexOf("@") == -1)
        return false
    var NameList = fData.split("@");
    if (NameList.length != 2)
        return false
    if (NameList[0].length < 1)
        return false
    if (NameList[1].indexOf(".") <= 0)
        return false
    if (fData.indexOf("@") > fData.indexOf("."))
        return false
    if (fData.indexOf(".") == fData.length - 1)
        return false

    return true
}

//****************************************************************
//* 名　　称：IsPhone
//* 功    能：判断是否为正确的电话号码（可以含"()"、"（）"、"+"、"-"和空格）
//* 输入参数：fData：要检查的数据
//* 输出参数：True：正确的电话号码，或者空                              
//*           False：错误的电话号码
//* 错误信息：
//*****************************************************************
function IsPhone(fData) {
    var str;
    var fDatastr = "";
    if (IsEmpty(fData))
        return true
    for (var i = 0; i < fData.length; i++) {
        str = fData.substring(i, i + 1);
        if (str != "(" && str != ")" && str != "（" && str != "）" && str != "+" && str != "-" && str != " ")
            fDatastr = fDatastr + str;
    }
    //alert(fDatastr);  
    if (isNaN(fDatastr))
        return false
    return true
}

//****************************************************************
//* 名　　称：IsPositiveNumeric
//* 功    能：判断是否为正确的正数（可以含小数部分）
//* 输入参数：fData：要检查的数据
//* 输出参数：True：正确的正数，或者空                              
//*           False：错误的正数
//* 错误信息：
//*****************************************************************
function IsPositiveNumeric(fData) {
    if (IsEmpty(fData))
        return true
    if ((isNaN(fData)) || (fData.indexOf("-") != -1))
        return false
    if (parseFloat(fData) <= 0) {
        return false;
    }
    return true
}
//****************************************************************
//* 名　　称：IsNegativeNumeric
//* 功    能：判断是否为正确的负数（可以含小数部分）
//* 输入参数：fData：要检查的数据
//* 输出参数：True：正确的负数，或者空                              
//*           False：错误的负数
//* 错误信息：
//*****************************************************************
function IsNegativeNumeric(fData) {
    if (IsEmpty(fData))
        return true
    if ((isNaN(fData)) || (fData.indexOf("-") == -1))
        return false
    if (parseFloat(fData) >= 0) {
        return false;
    }
    return true
}
//****************************************************************
//* 名　　称：IsNotNegativeNumeric
//* 功    能：判断是否为正确的非负数（可以含小数部分）
//* 输入参数：fData：要检查的数据
//* 输出参数：True：正确的负数，或者空                              
//*           False：错误的负数
//* 错误信息：
//*****************************************************************
function IsNotNegativeNumeric(fData) {
    if (IsEmpty(fData))
        return true
    if ((isNaN(fData)) || (fData.indexOf("-") != -1))
        return false
    if (parseFloat(fData) < 0) {
        return false;
    }
    return true
}

//****************************************************************
//* 名　　称：IsNumeric
//* 功    能：判断是否为正确的数字（可以为负数，小数）
//* 输入参数：fData：要检查的数据
//* 输出参数：True：正确的数字，或者空                              
//*           False：错误的数字
//* 错误信息：
//*****************************************************************
function IsNumeric(fData) {
    if (IsEmpty(fData))
        return true
    if (isNaN(fData))
        return false
    return true
}


//****************************************************************
//* 名　　称：IsIntegerInRange
//* 功    能：判断一个数字是否在指定的范围内
//* 输入参数：fInput：要检查的数据
//*           fLower：检查的范围下限，如果没有下限，请用null
//*           fHigh：检查的上限，如果没有上限，请用null
//* 输出参数：True：在指定的范围内                              
//*           False：超出指定范围
//*****************************************************************
function IsIntegerInRange(fInput, fLower, fHigh) {
    if (fLower == null)
        return (fInput <= fHigh)
    else if (fHigh == null)
        return (fInput >= fLower)
    else
        return ((fInput >= fLower) && (fInput <= fHigh))
}
/*
* 身份证15位编码规则：dddddd yymmdd xx p
* dddddd：6位地区编码
* yymmdd: 出生年(两位年)月日，如：910215
* xx: 顺序编码，系统产生，无法确定
* p: 性别，奇数为男，偶数为女
* 
* 身份证18位编码规则：dddddd yyyymmdd xxx y
* dddddd：6位地区编码
* yyyymmdd: 出生年(四位年)月日，如：19910215
* xxx：顺序编码，系统产生，无法确定，奇数为男，偶数为女
* y: 校验码，该位数值可通过前17位计算获得
* 
* 前17位号码加权因子为 Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ]
* 验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]
* 如果验证码恰好是10，为了保证身份证是十八位，那么第十八位将用X来代替
* 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )
* i为身份证号码1...17 位; Y_P为校验码Y所在校验码数组位置
*/
function isIdCard(idCard) {
    //15位和18位身份证号码的正则表达式
    var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
    //如果通过该验证，说明身份证格式正确，但准确性还需计算
    if (!regIdCard.test(idCard)) {
        return false;
    }
    if (idCard.length == 18) {
        var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); //将前17位加权因子保存在数组里
        var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
        var idCardWiSum = 0; //用来保存前17位各自乖以加权因子后的总和
        for (var i = 0; i < 17; i++) {
            idCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];
        }

        var idCardMod = idCardWiSum % 11; //计算出校验码所在数组的位置
        var idCardLast = idCard.substring(17); //得到最后一位身份证号码

        //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
        if (idCardMod == 2) {
            if (idCardLast == "X" || idCardLast == "x") {
                return true;
            } else {
                return false;
            }
        } else {
            //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
            if (idCardLast == idCardY[idCardMod]) {
                return true;
            } else {
                return false;
            }
        }
    }
}

// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.Format = function (fmt) { //author: meizz   
    var o = {
        "M+": this.getMonth() + 1,                 //月份   
        "d+": this.getDate(),                    //日   
        "h+": this.getHours(),                   //小时   
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
String.prototype.PadLeft = function (totalWidth, paddingChar) {
    if (paddingChar != null) {
        return this.PadHelper(totalWidth, paddingChar, false);
    } else {
        return this.PadHelper(totalWidth, ' ', false);
    }
}
String.prototype.PadRight = function (totalWidth, paddingChar) {
    if (paddingChar != null) {
        return this.PadHelper(totalWidth, paddingChar, true);
    } else {
        return this.PadHelper(totalWidth, ' ', true);
    }

}
String.prototype.PadHelper = function (totalWidth, paddingChar, isRightPadded) {

    if (this.length < totalWidth) {
        var paddingString = new String();
        for (i = 1; i <= (totalWidth - this.length); i++) {
            paddingString += paddingChar;
        }

        if (isRightPadded) {
            return (this + paddingString);
        } else {
            return (paddingString + this);
        }
    } else {
        return this;
    }
}

/*
取得文本宽度
*/
function getTextWidth(text, fontSize) {
    var textWidth = 0;
    for (var i = 0; i < text.length; i++) {
        var c = text[i];
        var strCode = text.charCodeAt(i);
        if ((strCode > 65248) || (strCode == 12288)) {
            textWidth += fontSize;
        }
        else {
            textWidth += fontSize / 2;
        }
    }
    return textWidth;
}


/*
下载Canvas
调用示例：downloadCanvas({ type: 'png', fileName: "传播路径", elementID: "divSpreadPathBox" });
*/
function downloadCanvas(options) {
    var type = options.type;
    // 图片导出为 png 格式
    var imgData = $("#" + options.elementID + " canvas")[0].toDataURL(type);
    console.log(options);
    /**
    * 获取mimeType
    * @param  {String} type the old mime-type
    * @return the new mime-type
    */
    var _fixType = function (type) {
        type = type.toLowerCase().replace(/jpg/i, 'jpeg');
        var r = type.match(/png|jpeg|bmp|gif/)[0];
        return 'image/' + r;
    };
    // 加工image data，替换mime type
    var mineType = _fixType(type);
    imgData = imgData.replace(mineType, 'image/octet-stream');
    /**
    * 在本地进行文件保存
    * @param  {String} data     要保存到本地的图片数据
    * @param  {String} filename 文件名
    */
    var saveFile = function (data, filename) {
        var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
        save_link.href = data;
        save_link.download = filename;

        var event = document.createEvent('MouseEvents');
        event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
        save_link.dispatchEvent(event);
    };

    // 下载后的问题名
    var filename = options.fileName + (new Date()).getTime() + '.' + type;
    // download
    saveFile(imgData, filename);
}

//下面是64个基本的编码
var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
var base64DecodeChars = new Array(
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
    52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
    -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
    15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
    -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
    41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);
//编码的方法
function encodeBase64(str) {
    if (str == null || str == "") {
        return "";
    }
    var out, i, len;
    var c1, c2, c3;
    len = str.length;
    i = 0;
    out = "";
    while (i < len) {
        c1 = str.charCodeAt(i++) & 0xff;
        if (i == len) {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt((c1 & 0x3) << 4);
            out += "==";
            break;
        }
        c2 = str.charCodeAt(i++);
        if (i == len) {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
            out += base64EncodeChars.charAt((c2 & 0xF) << 2);
            out += "=";
            break;
        }
        c3 = str.charCodeAt(i++);
        out += base64EncodeChars.charAt(c1 >> 2);
        out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
        out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
        out += base64EncodeChars.charAt(c3 & 0x3F);
    }
    return out;
}
//解码的方法
function decodeBase64(str) {
    if (str == null || str == "") {
        return "";
    }
    var c1, c2, c3, c4;
    var i, len, out;
    len = str.length;
    i = 0;
    out = "";
    while (i < len) {

        do {
            c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while (i < len && c1 == -1);
        if (c1 == -1)
            break;

        do {
            c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while (i < len && c2 == -1);
        if (c2 == -1)
            break;
        out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));

        do {
            c3 = str.charCodeAt(i++) & 0xff;
            if (c3 == 61)
                return out;
            c3 = base64DecodeChars[c3];
        } while (i < len && c3 == -1);
        if (c3 == -1)
            break;
        out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));

        do {
            c4 = str.charCodeAt(i++) & 0xff;
            if (c4 == 61)
                return out;
            c4 = base64DecodeChars[c4];
        } while (i < len && c4 == -1);
        if (c4 == -1)
            break;
        out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
    }
    return out;
}
function utf16to8(str) {
    var out, i, len, c;
    out = "";
    len = str.length;
    for (i = 0; i < len; i++) {
        c = str.charCodeAt(i);
        if ((c >= 0x0001) && (c <= 0x007F)) {
            out += str.charAt(i);
        } else if (c > 0x07FF) {
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
            out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
        } else {
            out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
        }
    }
    return out;
}
function utf8to16(str) {
    var out, i, len, c;
    var char2, char3;
    out = "";
    len = str.length;
    i = 0;
    while (i < len) {
        c = str.charCodeAt(i++);
        switch (c >> 4) {
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                // 0xxxxxxx
                out += str.charAt(i - 1);
                break;
            case 12: case 13:
                // 110x xxxx   10xx xxxx
                char2 = str.charCodeAt(i++);
                out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
                break;
            case 14:
                // 1110 xxxx  10xx xxxx  10xx xxxx
                char2 = str.charCodeAt(i++);
                char3 = str.charCodeAt(i++);
                out += String.fromCharCode(((c & 0x0F) << 12) |
                       ((char2 & 0x3F) << 6) |
                       ((char3 & 0x3F) << 0));
                break;
        }
    }
    return out;
}
/*
截取字符串
*/
function substr(text, startIndex, length) {
    if (text == null) {
        return null;
    }
    return text.substr(startIndex, length);
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
/*
返回指定秒数的时间文本
*/
function getTimeText(seconds) {
    var minutes = parseInt(seconds / 60);
    var remainSeconds = parseInt(seconds % 60);
    var hours = parseInt(minutes / 60);
    var remainMinutes = parseInt(minutes % 60);
    var timeText = hours + "小时" + remainMinutes + "分" + remainSeconds + "秒";
    return timeText;
}
/*
判断是否是微信浏览器的函数
*/
function isWeiXin() {
    var ua = window.navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == 'micromessenger') {
        return true;
    } else {
        return false;
    }
}

/*
进入全屏
*/
function fullScreen(el) {
    if (el == undefined || el == null) {
        el = document.getElementById("content");
        if (el == null) {
            var divContent = document.createElement("div");
            divContent.setAttribute("id", "content");
            divContent.setAttribute("style", "background:#333;width:100%;height:100%;display: none;");
            document.body.appendChild(divContent);
            el = document.getElementById("content");
        }
    }
    var rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullScreen;
    if (typeof rfs != "undefined" && rfs) {
        rfs.call(el);
        return;
    }
    if (typeof window.ActiveXObject != "undefined") {
        var wscript = new ActiveXObject("WScript.Shell");
        if (wscript) {
            wscript.SendKeys("{F11}");
        }
    }
}

/*
退出全屏
*/
function exitFullScreen() {
    var el = document;
    var cfs = el.cancelFullScreen || el.webkitCancelFullScreen || el.mozCancelFullScreen || el.exitFullScreen;
    if (typeof cfs != "undefined" && cfs) {
        cfs.call(el);
        return;
    }

    if (typeof window.ActiveXObject != "undefined") {
        var wscript = new ActiveXObject("WScript.Shell");
        if (wscript != null) {
            wscript.SendKeys("{F11}");
        }
    }
}
/*
取得距离当前时间已过去多少时长
*/
function getDiffTimeText(value) {
    var dateEnd = new Date(); //获取当前时间
    var dateDiff = dateEnd.getTime() - value; //时间差的毫秒数
    var minutes = Math.floor(dateDiff / (60 * 1000)) //计算相差分钟数
    var hours = Math.floor(dateDiff / (3600 * 1000)) //计算出小时数
    var dayDiff = Math.floor(dateDiff / (24 * 3600 * 1000)); //计算出相差天数
    var monthDiff = Math.floor(dayDiff / 30); //获取相差的月份
    var yearDiff = Math.floor(monthDiff / 12); //获取相差的年份
    if (minutes < 60) {
        return minutes + "分钟前";
    }
    if (hours < 24) {
        return hours + "小时前";
    }
    if (dayDiff < 30) {
        return dayDiff + "天前";
    }
    if (monthDiff < 12) {
        return monthDiff + "月前";
    }
    return yearDiff + "年前";
}

/*
* 设置本地储存
*/
function setLocalStorage(key, value) {
    localStorage.setItem(key, JSON.stringify(value));
}
/*
* 取得本地存储
*/
function getLocalStorage(key) {
    var value = localStorage.getItem(key);
    var result = null;
    if (value != null) {
        result
        JSON.parse(value);
    }
    return result;
}
/*
* 删除本地存储
*/
function removeLocalStorage(key) {
    localStorage.removeItem(key);
}


/*
* 设置Cookie
*/
function setCookie(name, value) {
    $.cookie(name, value, {
        expires: 9999,
        path: '/'
    });
}
/*
* 取得Cookie
*/
function getCookie(name) {
    return $.cookie(name);
}
/*
* 删除Cookie
*/
function removeCookie(name) {
    $.removeCookie(name, {
        path: '/'
    });
}


/**
 * 变为首字母大写
 */
function toPascal(name) {
	if (name == null || typeof (name) == "undefined") {
		return "";
	}
	var result = name.replace(/\_(\w)/g, function(all, letter) {
		return letter.toUpperCase();
	});
	result = result.substring(0, 1).toUpperCase() + result.substring(1);
	return result;
}



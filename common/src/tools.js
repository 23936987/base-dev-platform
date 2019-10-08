/**
 *  判断非空
 */
function isEmpty(obj) {
    if (obj == undefined || obj == null || (obj + "").trim() == 'null' || (obj + "").trim() == '' ) {
        return true;
    } else {
        return false;
    }
}
/**
*  判断非空
*/
function isNotEmpty(obj) {
    return isEmpty(obj) ? false : true;
}


/**
 * @class type
 */
var class2type = {}, toString = Object.prototype.toString;
(function () {
    var typeArr = "Boolean,Number,String,Function,Array,Date,RegExp,Object".split(",");
    for (var i = 0; i < typeArr.length; i++) {
        var name = typeArr[i];
        class2type["[object " + name + "]"] = name.toLowerCase();
    }
})();

/**
 *  返回对象类型
 *
 *  @for type
 *  @method check_type
 * @param  Object  obj 目标对象
 *
 * @return boolean 处理结果
 */
function check_type (obj) {
    return obj === null ? String(obj) : class2type[toString.call(obj)] || "object";
}
/**
 *  判断对象是否布尔类型
 *
 *  @for type
 *  @method isBoolean
 * @param  Object  obj 目标对象
 *
 * @return boolean 处理结果
 */
function isBoolean(obj) {
    return isEmpty(obj) ? false : check_type(obj) === 'boolean';
}
/**
 *  判断对象是否数值类型
 *
 *  @for type
 *  @method isNumber
 * @param  Object  obj 目标对象
 *
 * @return boolean 处理结果
 */
function isNumber(obj) {
    return isEmpty(obj) ? false : check_type(obj) === 'number';
}
/**
 *  判断对象是否字符类型
 *
 *  @for type
 *  @method isString
 * @param  Object  obj 目标对象
 *
 * @return boolean 处理结果
 */
function isString(obj) {
    return isEmpty(obj) ? false : check_type(obj) === 'string';
}
/**
 *  判断对象是否函数类型
 *
 *  @for type
 *  @method isFunction
 * @param  Object  obj 目标对象
 *
 * @return boolean 处理结果
 */
function isFunction(obj) {
    return isEmpty(obj) ? false : check_type(obj) === 'function';
}
/**
 *  判断对象是否数组类型
 *
 *  @for type
 *  @method isArray
 * @param  Object  obj 目标对象
 *
 * @return boolean 处理结果
 */
function isArray(obj) {
    return obj == null ? false : check_type(obj) === 'array';
}
/**
 *  判断对象是否日期类型
 *
 *  @for type
 *  @method isDate
 * @param  Object  obj 目标对象
 *
 * @return boolean 处理结果
 */
function isDate(obj) {
    return isEmpty(obj) ? false : check_type(obj) === 'date';
}
/**
 *  判断对象是否正则表达式类型
 *
 *  @for type
 *  @method isRegExp
 * @param  Object  obj 目标对象
 *
 * @return boolean 处理结果
 */
function isRegExp(obj) {
    return isEmpty(obj) ? false : check_type(obj) === 'regexp';
}
/**
 *  判断对象是对象类型
 *
 *  @for type
 *  @method isEmpty
 * @param  Object  obj 目标对象
 *
 * @return boolean 处理结果
 */
function isObject(obj) {
    return isEmpty(obj) ? false : check_type(obj) === 'object';
}
/**
 *  将字符串解析为日期对象
 *
 *  @for util
 *  @method parseDate
 * @param  String  timeStr 字符串
 * @param  String  format 解析格式
 *
 * @return date 处理结果
 */
var parseDate=function() {
    var timeStr, format;
    if (arguments.length === 0) {
        return new Date();
    } else if (arguments.length === 1) {
        timeStr = arguments[0];
        format = 'yyyy-MM-dd HH:mm:ss';
    } else if (arguments.length === 2) {
        timeStr = arguments[0];
        format = arguments[1];
    }

    if (timeStr.length != format.length) {
        alert('参数错误');
        return;
    }
    var yearExpr = /(yyyy)+|(YYYY)+|(yy)+|(YY)+/g;
    var monthExpr = /(MM)+|(M)+/g;
    var dateExpr = /(dd)+|(DD)+|(d)+|(D)+/g;
    var hourExpr = /(HH)+|(H)+/g;
    var miniteExpr = /(mm)+|(m)+/g;
    var secondExpr = /(ss)+|(SS)|(s)+|(S)+/g;

    var getStr = function (expr) {

        var arr = expr.exec(format);
        if (!arr || arr.length === 0){
            return;
        }
        var p = arr[0];
        var start = format.indexOf(p);
        return timeStr.substr(start, p.length);
    };

    var ret = new Date();
    var year = getStr(yearExpr);
    if (year) {
        ret.setYear(parseInt(year));
    } else {
        ret.setYear(1971);
    }
    var month = getStr(monthExpr);
    if (month) {
        ret.setMonth(parseInt(month) - 1);
    } else {
        ret.setMonth(0);
    }
    var date = getStr(dateExpr);
    if (date) {
        ret.setDate(parseInt(date));
    } else {
        ret.setDate(1);
    }
    var hour = getStr(hourExpr);
    if (hour) {
        ret.setHours(parseInt(hour));
    } else {
        ret.setHours(0);
    }
    var minite = getStr(miniteExpr);
    if (minite) {
        ret.setMinutes(parseInt(minite));
    } else {
        ret.setMinutes(0);
    }
    var second = getStr(secondExpr);
    if (second) {
        ret.setSeconds(parseInt(second));
    } else {
        ret.setSeconds(0);
    }

    return ret;
};

/**
 *  将日期格式化为字符串
 *
 *  @for util
 *  @method formatDate
 * @param  date  time 字符串
 * @param  String  format 解析格式
 *
 * @return String 处理结果
 */
var formatDate=function() {
    var time, format;
    if (arguments.length === 0) {
        time = new Date();
        format = 'yyyy-MM-dd HH:mm:ss';
    } else if (arguments.length === 1) {
        time = arguments[0];
        format = 'yyyy-MM-dd HH:mm:ss';
    } else if (arguments.length === 2) {
        time = arguments[0];
        format = arguments[1];
    }
    var dateStr = format;
    var yearExpr = /(yyyy)+|(YYYY)+|(yy)+|(YY)+/g;
    var monthExpr = /(MM)+|(M)+/g;
    var dateExpr = /(dd)+|(DD)+|(d)+|(D)+/g;
    var hourExpr = /(HH)+|(H)+/g;
    var miniteExpr = /(mm)+|(m)+/g;
    var secondExpr = /(ss)+|(SS)|(s)+|(S)+/g;

    var repStr = function (expr) {
        var arr = expr.exec(format);
        if (!arr || arr.length === 0){
            return;
        }
        var p = arr[0];
        var twobit = function (s) {
            s = s + '';
            return s.length < 2 ? "0" + s : s;
        };
        if (p && (p == 'yyyy' || p == 'YYYY')) {
            dateStr = dateStr.replace(p, time.getFullYear());
        } else if (p && (p == 'yy' || p == 'YY')) {
            var yearStr = time.getYear() + '';
            dateStr = dateStr.replace(p, yearStr.substr(2));
        } else if (p && p == 'MM') {
            dateStr = dateStr.replace(p, twobit(time.getMonth() + 1));
        } else if (p && p == 'M') {
            dateStr = dateStr.replace(p, time.getMonth() + 1);
        } else if (p && (p == 'DD' || p == 'dd')) {
            dateStr = dateStr.replace(p, twobit(time.getDate()));
        } else if (p && (p == 'D' || p == 'd')) {
            dateStr = dateStr.replace(p, time.getDate());
        } else if (p && p == 'HH') {
            dateStr = dateStr.replace(p, twobit(time.getHours()));
        } else if (p && p == 'H') {
            dateStr = dateStr.replace(p, time.getHours());
        } else if (p && p == 'mm') {
            dateStr = dateStr.replace(p, twobit(time.getMinutes()));
        } else if (p && p == 'm') {
            dateStr = dateStr.replace(p, time.getMinutes());
        } else if (p && (p == 'SS' || p == 'ss')) {
            dateStr = dateStr.replace(p, twobit(time.getSeconds()));
        } else if (p && (p == 'S' || p == 's')) {
            dateStr = dateStr.replace(p, time.getSeconds());
        } else {
            return null;
        }
    };

    repStr(yearExpr);
    repStr(monthExpr);
    repStr(dateExpr);
    repStr(hourExpr);
    repStr(miniteExpr);
    repStr(secondExpr);

    return dateStr;
};

/**
 *  将对象序列化成字符串
 *
 *  @for util
 *  @method serialize
 * @param  object  obj 对象
 *
 * @return String 处理结果
 */
function serialize(obj) {
    var str = '';
    if (isEmpty(obj)) {
        return str;
    }
    for (var key in obj) {
        var val = obj[key];

        if (!isArray(val)) {
            str += '&' + key + '=' + val;
        } else {
            for (var i = 0, len = val.length; i < len; i++) {
                str += '&' + key + '=' + val[i];
            }
        }
    }
    return str.substring(1);
}
/**
 *  将字符串反序列化成对象
 *
 *  @for util
 *  @method unSerialize
 * @param  String  str 字符串
 *
 * @return object 处理结果
 */
function unSerialize(str) {
    var result = {};
    if (isEmpty(str)) {
        return result;
    }
    var params = str.split("&");

    if (isEmpty(params) || params.length === 0) {
        return result;
    }

    for (var i = 0, len = params.length; i < len; i++) {
        var param = params[i];
        var arr = param.split("=");

        var key = arr[0];
        var val = arr[1];

        if (isEmpty(key) || isEmpty(val)) {
            continue;
        }
        var old = result[key];
        if (isEmpty(old)) {
            result[key] = val;
            continue;
        }

        if (!isArray(old)) {
            result[key] = [old, val];
            continue;
        } else {
            old.push(val);
            continue;
        }
    }
    return result;
}
 
/**
 *  获取url参数
 *
 *  @for util
 *  @method getParameter
 * @param  String  key 键
 * @param  number  loc 下标
 *
 * @return object 处理结果
 */
function getParameter(key, loc) {
    var arr = [];
    loc = loc || 0;
    var patt = new RegExp(key + '=([^&]*)', "g");
    var str = location.search.substring(1);
    var result;
    while ((result = patt.exec(str)) != null) {
        arr.push(result[1]);
    }

    if (arr.length == 0) {
        return '';
    }
    if (isNotEmpty(loc)) {
        return arr[loc];
    }
    return arr.join(",");
}

function getQueryString(name)
{    
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
    if(r!=null)return  unescape(r[2]); return null;
}
/**
 *  获取UUID
 *
 *  @for util
 *  @method uuid
 *
 * @return string 处理结果
 */
function uuid() {
    var b = [], c = "0123456789ABCDEF".split("");
    for (var a = 0; a < 36; a++) {
        b[a] = Math.floor(Math.random() * 16);
    }
    b[14] = 4;
    b[19] = (b[19] & 3) | 8;

    for (var i = 0; i < 36; i++) {
        b[i] = c[b[i]];
    }
    b[8] = b[13] = b[18] = b[23] = "-";
    return b.join("");
}

/**
 *  获取GUID
 *
 *  @for util
 *  @method guid
 *
 * @return string 处理结果
 */
function guid(){
    var str = uuid();
    str = str.replaceAll("-","");
    return str;
}

function setLocalStorage(key,value){
    if(!window.localStorage){
        alert("浏览器不支持localstorage");
        return false;
    }else{
        var storage=window.localStorage;
        storage.setItem(key,value);
    }
}

function getLocalStorage(key){
    if(!window.localStorage){
        alert("浏览器不支持localstorage");
        return false;
    }else{
        var storage=window.localStorage;
        return storage.getItem(key);
    }
}
function getTopWindow(){
    var topWin=window;
    while(topWin.parent != topWin && isNotEmpty(topWin.parent.CC)){
        topWin = topWin.parent;
    }
    return topWin;
}
/**
 *  打印日志
 *
 *  @for util
 *  @method logger
 * @param string msg 日志消息
 * @return string 处理结果
 */

function logger(msg,err){
    var d = new Date();
    var time = formatDate(d);
    var info = time + " : " +d.getMilliseconds() + "  " + msg;

	if(err){
		 console.info(info,err);
	}else{
		console.info(info);
	}
   
}
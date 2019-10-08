

/**
 *  去掉字符串两边空格
 */
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 *  去掉字符串左边空格
 */
String.prototype.trimLeft = function () {
    return this.replace(/^\s*/, "");
};
/**
 *  去掉字符串右边空格
 */
String.prototype.trimRight = function () {
    return this.replace(/\s*$/, "");
};
/**
 *  验证字符串是否以str开始
 */
String.prototype.startWith = function (str) {
    return isEmpty(str) ? false : this.substring(0, str.length) == str;
};

/**
 *  验证字符串是否以str结束
 */
String.prototype.endWith = function (str) {
    return isEmpty(str) ? false : this.substring(this.length - str.length) == str;
};
/**
 *  替换字符串
 */
String.prototype.format = function () {
    var args = isArray(arguments[0]) ? arguments[0] : arguments;
    var result = this;
    for (var i = 0, len = args.length; i < len; i++) {
        result = result.replace("{" + i + "}", args[i]);
    }
    return result;
};


/**
 *  替换所有目标字符串
 */
String.prototype.replaceAll = function (regexp, replacement) {
    return this.replace(new RegExp(regexp, "g"), replacement);
};

/**
 *  获取字符串真实长度 一个汉字算2位
 */
String.prototype.realLength = function () {
    return this.replace(/[^\x00-\xff]/g, "**").length;
};


/**
 *  追加字符串
 */
String.prototype.append = function (str) {
    return this.concat(str);
};
/**
 *  删除指定索引位置的字符，索引无效将不删除任何字符
 *
 */
String.prototype.deleteCharAt = function (index) {
    if (index < 0 || index >= this.length) {
        return this.valueOf();
    }
    else if (index == 0) {
        return this.substring(1, this.length);
    }
    else if (index == this.length - 1) {
        return this.substring(0, this.length - 1);
    }
    else {
        return this.substring(0, index) + this.substring(index + 1);
    }
};
/**
 *  删除指定索引区间的字符串
 */
String.prototype.deleteString = function (start, end) {
    if (start == end) {
        return this.deleteCharAt(start);
    }
    else {
        if (start > end) {
            var temp = start;
            start = end;
            end = temp;
        }
        if (start < 0) {
            start = 0;
        }
        if (end > this.length - 1) {
            end = this.length - 1;
        }
        return this.substring(0, start) + this.substring(end +1 , this.length);
    }
};
/**
 *  比较两个字符串是否相等，也可以直接用 == 进行比较
 *
 */
String.prototype.equal = function (str) {
    if (this.length != str.length) {
        return false;
    }
    else {
        for (var i = 0; i < this.length; i++) {
            if (this.charAt(i) != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }
};
/**
 *  比较两个字符串是否相等，不区分大小写
 *
 */
String.prototype.equalIgnoreCase = function (str) {
    var temp1 = this.toLowerCase();
    var temp2 = str.toLowerCase();
    return temp1.equal(temp2);
};
/**
 *  将指定的字符串插入到指定的位置后面，索引无效将直接追加到字符串的末尾
 *
 */
String.prototype.insert = function (ofset, subStr) {
    if (ofset < 0 || ofset >= this.length - 1) {
        return this.append(subStr);
    }
    return this.substring(0, ofset + 1) + subStr + this.substring(ofset + 1);
};
/**
 *  获取N个相同的字符串
 *
 */
String.prototype.repeat = function(num) {
    var tmpArr = [];
    for ( var i = 0; i < num; i++){
        tmpArr.push(this);
    }
    return tmpArr.join("");
};
/**
 *  将字符串反序排列
 *
 */
String.prototype.reverse = function() {
    return this.split("").reverse().join("");
};

/**
 *  将指定的位置的字符设置为另外指定的字符或字符串.索引无效将直接返回不做任何处理
 *
 */
String.prototype.setCharAt = function (index, subStr) {
    if (index < 0 || index > this.length - 1) {
        return this.valueOf();
    }
    return this.substring(0, index) + subStr + this.substring(index+1);
};
/**
 *  计算长度，每个汉字占两个长度，英文字符每个占一个长度
 *
 */
String.prototype.charLength = function () {
    var temp = 0;
    for (var i = 0; i < this.length; i++) {
        if (this.charCodeAt(i) > 255) {
            temp += 2;
        }
        else {
            temp += 1;
        }
    }
    return temp;
};
/**
 *  得到字节长度
 */
String.prototype.getRealLength = function () {
    return this.replace(/[^x00-xff]/g, "--").length;
};
/**
 *  从左截取指定长度的字串
 */
String.prototype.left = function (n) {
    return this.slice(0, n);
};

/**
 *  从右截取指定长度的字串
 */
String.prototype.right = function (n) {
    return this.slice(this.length - n);
};

/**
 *  统计指定字符出现的次数
 */
String.prototype.occurs = function(ch) {
//  var re = eval("/[^"+ch+"]/g");
//  return this.replace(re, "").length;
    return this.split(ch).length-1;
};
/**
 *  合并多个空白为一个空白
 */
String.prototype.resetBlank = function() {
    return this.replace(/s+/g, " ");
};
/**
 *  获取字符数组
 */
String.prototype.toCharArray = function() {
    return this.split("");
};

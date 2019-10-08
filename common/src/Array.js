var slice = Array.prototype.slice;

/**
 *  数组长度
 */
Array.prototype.size = function () {
    return this.length;
};
/**
 *  清空数组
 */
Array.prototype.clear = function () {
    this.length = 0;
    return this;
};
/**
 *  克隆一个数组
 */
Array.prototype.clone = function () {
    return slice.call(this, 0);
};
/**
 *  按条件过渡数组
 */
Array.prototype.filter = function (fun) {
    var values = [];
    for (var i = 0, len = this.length; i < len; i++) {
        if (fun.call(null, this[i], i,this)) {
            values.push(this[i]);
        }
    }
    return values;
};

/**
 *  去空元素
 */
Array.prototype.compact=function(){
    var func = function(item,index,arrays){
        if(item == null || item == ''){
            return false;
        }
        return true;
    };
    return this.filter(func);
};
/**
 *  如果数组中的每个元素都能通过给定的函数的测试，则返回true，反之false。
 */
Array.prototype.every = function(fn) {
    for ( var i=0, j=this.length; i < j; ++i ) {
        if ( !fn.call(null,this[i], i, this) ) {
            return false;
        }
    }
    return true;
};
/**
 *  但只要有一个通过给定函数的测试就返回true
 */
Array.prototype.some = function(fn) {

    for ( var i=0, j=this.length; i < j; ++i ) {
        if ( fn.call(null, this[i], i, this)) {
            return true;
        }
    }
    return false;
};
/**
 *  遍历数组
 */
Array.prototype.each=function( fun ){
    for( var i=0,n=this.length;i<n;i++){
        fun.call(null,this[i], i, this);
    }
};

/**
 *  让数组中的每一个元素调用给定的函数，然后把得到的结果放到新数组中返回
 */
Array.prototype.map = function(fn) {
    var a = [];
    for ( var i=0, j=this.length; i < j; ++i ) {
        a.push(fn.call(null, this[i], i, this));
    }
    return a;
};


/**
 *  数组洗牌
 */
Array.prototype.random = function () {
    var tempArr = [], me = this, t;
    while (me.length > 0) {
        t = Math.floor(Math.random() * me.length);
        tempArr[tempArr.length] = me[t];
        me = me.deleteAt(t);
    }
    return tempArr;
};

/**
 *  数字数组排序
 */
Array.prototype.sortNum = function (i) {
    if (!i) {
        i = 0;
    }
    if (i == 1) {
        return this.sort(function (a, b) {
            return b - a;
        });
    }
    return this.sort(function (a, b) {
        return a - b;
    });
};
/**
 *  获取数字数组中的最大项
 */
Array.prototype.getMax = function () {
    return this.sortNum(1)[0];
};
/**
 *  获取数字数组中的最小项
 */
Array.prototype.getMin = function () {
    return this.sortNum(0)[0];
};
/**
 *  去除数组中的重复项
 */
Array.prototype.arrUnique = function () {
    var reset = [], done = {};
    for (var i = 0; i < this.length; i++) {
        var temp = this[i];
        if (!done[temp]) {
            done[temp] = true;
            reset.push(temp);
        }
    }
    return reset;
};


/**
 *  元素在数组中的位置
 */
Array.prototype.indexOf = function (item) {
    for (var i = 0, len = this.length; i < len; i++) {
        if (this[i] == item) {
            return i;
        }
    }
    return -1;
};

/**
 *  按Id元素在数组中的位置
 */
Array.prototype.indexOfById = function (id) {
    for (var i = 0, len = this.length; i < len; i++) {
        if (this[i].id == id) {
            return i;
        }
    }
    return -1;
};
/**
 *  按条件元素在数组中的位置
 */
Array.prototype.indexOfByFunc = function (func) {
    for (var i = 0, len = this.length; i < len; i++) {
        if (func.call(null,this[i], i, this)) {
            return i;
        }
    }
    return -1;
};

/**
 *  判断是否存在,通过元素的id
 */
Array.prototype.getElementById=function(elementId){
    for(var i=0;i<this.length;i++){
        if(this[i].id== elementId){
            return this[i];
        }
    }
};
/**
 *  判断是否存在,通过元素的id
 */
Array.prototype.getElementByFunc=function(func){
    for(var i=0;i<this.length;i++){
        if(func.call(null,this[i], i, this)){
            return this[i];
        }
    }
};


/**
 *  确定某个元素是否在数组中
 */
Array.prototype.contains=function(element){
    for(var i=0;i<this.length;i++){
        if(this[i]== element){
            return true;
        }
    }
    return false;
};

/**
 *  判断是否存在,通过元素的id
 */
Array.prototype.containsById=function(elementId){
    for(var i=0;i<this.length;i++){
        if(this[i].id== elementId){
            return true;
        }
    }
    return false;
};
/**
 *  确定某个元素是否在数组中
 */
Array.prototype.containsByFunc=function(func){
    for(var i=0;i<this.length;i++){
        if(func.call(null,this[i], i, this)){
            return true;
        }
    }
    return false;
};

/**
 *  给数组添加元素
 */
Array.prototype.add = function (item) {
    this.push(item);
};

/**
 *  按下标插入数组元素
 */
Array.prototype.addAt=function(index,item){
    this.splice(index,0,item);
};
/**
 *  给数组批量添加元素
 */
Array.prototype.addAll = function (arr) {
    for (var i = 0;i < arr.length; i++) {
        this.add(arr[i]);
    }
};

/**
 *  按下标删除数组元素
 */
Array.prototype.removeAt=function(index) {
    this.splice(index,1);
};

/**
 *  删除数组中的元素
 */
Array.prototype.remove = function (item) {
    var itemIndex = this.indexOf(item);
    if (itemIndex >= 0) {
        this.splice(itemIndex, 1);
        return itemIndex;
    }
    return -1;
};

/**
 *  删除Array的元素,通过元素的Id
 */
Array.prototype.removeById=function(elementId){
    for(var i=0;i<this.length;i++){
        if(this[i].id == elementId){
            this.splice(i,1);
        }
    }
};
/**
 *  删除Array的元素,通过元素的Id
 */
Array.prototype.removeByFunc=function(func){
    for(var i=0;i<this.length;i++){
        if(func.call(null,this[i], i, this)){
            this.splice(i,1);
        }
    }
};
/**
 *  按给定数组删除数组中的元素
 *
 */
Array.prototype.removeAll = function (arr) {
    for (var i = 0, len = arr.length; i < len; i++) {
        this.remove(arr[i]);
    }
};

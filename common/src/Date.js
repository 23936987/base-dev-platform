/**
 * @class date
 */


/**
 *  回到当前时刻
 *
 *  @for date
 *  @method toNow
 *
 * @return void
 */
Date.prototype.toNow = function() {
    this.setTime(Date.now());
    return this;
};


/**
 *  回到午夜零点
 *
 *  @for date
 *  @method toMidnight
 *
 * @return void
 */
Date.prototype.toMidnight = function() {
    this.setHours(0);
    this.setMinutes(0);
    this.setSeconds(0);
    return this;
};
/**
 *  回到一天最后暴时刻
 *
 *  @for date
 *  @method toEndNight
 *
 * @return void
 */
Date.prototype.toEndNight = function() {
    this.setHours(23);
    this.setMinutes(59);
    this.setSeconds(59);
    return this;
};

/**
 *  获取若干秒前
 *
 *  @for date
 *  @method secsAgo
 *  @param number secs 秒
 * @return void
 */
Date.prototype.secsAgo = function(secs) {
    secs = secs ? secs - 0 : 0;
    return new Date(this.getTime() - secs * 1000);
};
/**
 *  获取几天前
 *
 *  @for date
 *  @method daysAgo
 *  @param number days  天
 * @return void
 */
Date.prototype.daysAgo = function(days) {
    days = days ? days - 0 : 1;
    return this.secsAgo(days * 86400).toMidnight();
};

/**
 *  获取月初第一天
 *
 *  @for date
 *  @method monthBegin
 *  @param number days  天
 * @return void
 */
Date.prototype.monthBegin = function(offset) {
    offset = offset ? offset - 0 : 0;
    var days = this.getDate() - 1 - offset;
    return this.daysAgo(days);
};


/**
 *  今年第多少天
 *
 *  @for date
 *  @method getDaysOfYear
 * @return void
 */
Date.prototype.getDaysOfYear = function() {
    var first_day = new Date(this.getFullYear(), 0, 1);
    var micro_secs = this.getTime() - first_day.getTime();
    return parseInt(micro_secs / 8.64E7) + 1;
};

/**
 *  今年第多少周
 *
 *  @for date
 *  @method getWeeksOfYear
 * @return void
 */
Date.prototype.getWeeksOfYear = function(start) {
    var offset = this.getDaysOfYear() - 1;
    var remain = offset % 7;
    if (remain > 0) {
        var first_day = new Date(this.getFullYear(), 0, 1);
        if (start) { //周一作为一周开始
            offset += (first_day.getDay() + 6) % 7;
        } else { //周日作为一周开始
            offset += first_day.getDay();
        }
    }
    return parseInt(offset / 7);
};
/**
 *  返回下个月的第一天的Date对象
 *
 *  @for date
 *  @method getStartOfNextMonth
 * @return void
 */
Date.prototype.getStartOfNextMonth = function(){
    var newDate = new Date(this);
    newDate.setDate(15); //确保月数不会进位
    newDate.setMonth(this.getMonth()+1);
    newDate.setDate(1);
    newDate.toMidnight();
    return newDate;
};

/**
 *  返回下个月的最后一天的Date对象
 *
 *  @for date
 *  @method getEndOfNextMonth
 * @return void
 */
Date.prototype.getEndOfNextMonth = function(){
    var newDate = new Date(this);
    newDate.setDate(15); //确保月数不会进位
    newDate.setMonth(this.getMonth() + 2); //加两个月
    newDate.setDate(0); //再退回上个月的最后一天
    newDate.toEndNight();
    return newDate;
};

/**
 *  返回一个加上若干个月的新Date
 *
 *  @for date
 *  @method plusMonths
 * @return void
 */
Date.prototype.plusMonths = function(num) {
    var newDate = new Date(this);
    newDate.setMonth(this.getMonth() + num); //setMonth()会自动除以12
    //注意：此时，月数可能会自动进位，比如：1-31加上num=1的情况，会变成3-3（非闰年）或3-2（闰年），即2-31自动转换为下个月的某一天。

    var currentMonth = this.getMonth() + this.getFullYear() * 12; //获得月的绝对值
    var diff = (newDate.getMonth() + newDate.getFullYear() * 12) - currentMonth; //计算新旧两个月绝对值的差

    if (diff != num) { //如果月绝对值的差和加上的月数不一样，说明月进位了，此时需要退一个月
        //setDate(0)表示变成上个月的最后一天
        newDate.setDate(0);
    }

    return newDate;
};
/**
 *  返回一个减去days天的新Date
 *
 *  @for date
 *  @method plusMonths
 * @return void
 */
Date.prototype.minusDays = function(days) {
    return new Date(this.getTime() - days*60*60*24*1000);
};
/**
 *  返回一个加上days天的新Date
 *
 *  @for date
 *  @method plusDays
 * @return void
 */
Date.prototype.plusDays = function(days) {
    return new Date(this.getTime() + days*60*60*24*1000);
};
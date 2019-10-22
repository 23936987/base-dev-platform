(function (window) {
    'use strict';
    /**
     * UI组件顶层父类
     *
     * @class Component
     * @constructor
     * @param {String,nodeType,jquery} target 选择器
     * @param {String} processKey 解析器key
     * @namespace _$
     */
    _$.Component = function(target,processKey){
        var _this = this;
        _this.processKey = processKey;
        target = _$.getTarget(target);
        _this.events={};
        _this.el = target;
        _this.uid = uuid();
        _this.id = target.attr("id");

        var opts = _this._attrOpts();
        var properties = _this._attrProps();
        _this.options = $.extend(true,opts,_$.parser.parseOptions(target,properties));

        _$.reg(_this);
        var res = _this._require();
        _this._getEvents.call(_this, ["onloadsuccess"]);
        _$._loadCssAndJs(res);
        _this._create();
    };


    /**
     *  组件加载完成
     *
     *  @for _$.Component
     *  @property onloadsuccess
     * @type Function
     */

    /**
     *  组件加载完成
     *
     *  @for _$.Component
     *  @event onloadsuccess
     * @param {Event} e 事件对象
     */


    _$.Component.prototype = {
        /**
         * 组件载体上下文
         @for _$.Component
         @property el
         *@type {Boolean}
         @final
         */
        el:null,
        /**
         *  判断对象是否扩展自Component构造器
         @for _$.Component
         @property isComponent  {Boolean}
         @final
         */
        isComponent : true,
        /**
         *  ID
         *
         *  @for _$.Component
         *  @property id
         * @type Function
         */
        id:"",
        uid:"",

        component:null,
        _cls:_$._clsPre + "component",
        /**
         *  组件Class
         *
         *  @for _$.Component
         *  @property _uiCls
         * @type Function
         */
        _uiCls:_$._uiPreCls + "component",
        _LOADING_:'loading',
        _LOAD_SUCCESS_:'success',
        _LOADING_:'loading',
        _LOAD_:'loading',

        /**
         *  组件依赖的资源文件,
         *
         *  @for _$.Component
         *  @method _require
         */
        _require:function(){
            var res = {};
            return res;
        },
        /**
         *  组件加载状态,
         *
         *  @for _$.Component
         *  @method getStatus
         */
        getStatus:function(){
            var _this = this;
            return _this._LOAD_;
        },
        /**
         *  组件是否加载完成,
         *
         *  @for _$.Component
         *  @method isLoaded
         */
        isLoaded : function () {
            var _this = this;
            return _this._LOAD_ == _this._LOAD_SUCCESS_;
        },
        /**
         *  组件渲染入口,
         *
         *  @for _$.Component
         *  @method _create
         */
        _create:function(){},
        /**
         *  组件属性申明,
         *
         *  @for _$.Component
         *  @method _attrProps
         */
        _attrProps:function(){
            return ['id', 'remark',"onloadsuccess",{"plugins":"object"}];
        },
        /**
         *  组件默认配置,
         *
         *  @for _$.Component
         *  @method _attrOpts
         */
        _attrOpts:function(){
            return {
                "onloadsuccess":function() {
                },
                "plugins":{}
            };
        },
        /**
         *  组件设置css样式,
         *
         *  @for _$.Component
         *  @method _setStyle
         */
        _setStyle:function(){
        },
        /**
         *  组件解析自定义事件
         *
         *  @for _$.Component
         *  @method _getEvents
         * @param {Array} keys 事件类型
         * @static
         */
        _getEvents:function(keys){
            var _this = this;
            $.map(keys, function(p){
                var func = _this.getOption(p);
                if(isNotEmpty(func)){
                    func = _$.getFunctoin(func);
                    _this._on(p,func);
                }
            });
        },
        /**
         *  组件渲染完成后回调
         *
         *  @for _$.Component
         *  @method _loadSuccess
         */
        _loadSuccess:function(){
            var _this=this;
            if(!_this.isLoaded()){
                _this._LOAD_ =  _this._LOAD_SUCCESS_;
                _$.parser.onComponentComplete.call(_$.parser,_this);
            }
            _this._fire("onloadsuccess");
        },
        _loadSuccessNoEvent:function(){
            var _this=this;
            if(!_this.isLoaded()){
                _this._LOAD_ =  _this._LOAD_SUCCESS_;
                _$.parser.onComponentComplete.call(_$.parser,_this);
            }
        },
        /**
         *  组件事件绑定
         *
         *  @for _$.Component
         *  @method _bindEvents
         */
        _bindEvents:function(){},
        /**
         *  组件事件触发
         *
         *  @for _$.Component
         *  @method _fire
         * @param {String} type 事件类型
         * @param {Object} e 事件对象
         */
        _fire : function(type, e) {
            var _this = this;
            type = type.toLowerCase();
            for(var eventKey in _this.events){
                if(eventKey == type){
                    var event = _this.events[eventKey];
                    _this._fireByEvent(event,eventKey,e);
                }else if(eventKey.indexOf(".") != -1){
                    var arr = eventKey.split(".");
                    if(arr[1] == type){
                        var event = _this.events[eventKey];
                        _this._fireByEvent(event,eventKey,e);
                    }
                }
            }
        },
        /**
         *  组件事件按配置触发
         *
         *  @for _$.Component
         *  @method _fireByEvent
         * @param {String} event 组件事件存储配置
         * @param {String} type 事件类型
         * @param {Object} e 事件对象
         */
        _fireByEvent:function(event,type,e){
            var _this = this;

            if (event) {
                if (!e) {
                    e = {};
                }
                if (e && e != _this) {
                    e.source = _this;
                    if (!e.type) {
                        e.type = type;
                    }
                    e.name = _this.name || _this.name;
                    e.id = _this.id;
                    e.name = _this.id;
                    e.source=_this;
                }
                for ( var c = 0, a = event.length; c < a; c++) {
                    var item = event[c];
                    if (item) {
                        item[0].apply(item[1], [ e ]);
                    }
                }
            }
        },
        /**
         *  组件事件注册
         *
         *  @for _$.Component
         *  @method _on
         * @param {String} type 事件类型
         * @param {Function} fn 事件处理器
         * @param {Object} scope 事件作用域
         */
        _on : function(type, fn, scope) {
            var _this = this;
            if (typeof fn == "string") {
                var func = _$.getFunctoin(fn,scope);
                if (func) {
                    fn = func;
                }
            }
            if (typeof fn != "function" || !type) {
                return false;
            }
            type = type.toLowerCase();
            var event = _this.events[type];
            if (!event) {
                event = _this.events[type] = [];
            }
            scope = scope || _this;
            if (!_this._findListener(type, fn, scope)) {
                event.push([ fn, scope ]);
                _this.events[type]=event;
            }
            return _this;
        },
        /**
         *  组件按事件前缀注销
         *
         *  @for _$.Component
         *  @method _unPrefix
         * @param {String} prefix 事件类型前缀
         */
        _unPrefix:function (prefix) {
            var _this = this;
            for(var eventKey in _this.events){
                if(eventKey.indexOf(".") != -1){
                    var arr = eventKey.split(".");
                    if(arr[0] == prefix){
                        delete _this.events[eventKey];
                    }
                }
            }
        },
        /**
         *  组件按事件类型注销
         *
         *  @for _$.Component
         *  @method _un
         * @param {String} prefix 事件类型前缀
         */
        _un : function(type, fn, scope) {
            var _this = this;
            if (typeof fn != "function") {
                return false;
            }
            type = type.toLowerCase();
            var event = _this.events[type];
            if (event) {
                scope = scope || _this;
                var item = _this._findListener(type, fn, scope);
                if (item) {
                    event.remove(item);
                }
            }
            return _this;
        },
        /**
         *  查询事件处理器
         *
         *  @for _$.Component
         *  @method _findListener
         * @param {String} type 事件类型
         * @param {Function} func 事件处理器
         * @param {Object} scope 事件作用域
         */
        _findListener : function(type, func, scope) {

            var _this = this;
            type = type.toLowerCase();
            scope = scope || _this;
            var event = _this.events[type];
            if (event) {
                for ( var i = 0; i < event.length ; i++) {
                    var item = event[i];
                    if (item[0] == func && item[1] == scope) {
                        return item;
                    }
                }
            }
        },
        /**
         *  替换组件原Dom对象
         *
         *  @for _$.Component
         *  @method _replaceNode
         * @param {String} source 新html
         */
        _replaceNode:function(source){
            var _this = this;
            var template = Handlebars.compile(source);
            var result = template(_this.options);
            var element = $(result);
            _this.el.replaceWith(element);
            _this.el = element;
            _this.el.addClass(_this._uiCls);
            _this.el.attr("componentId",_this.uid);
        },
        /**
         *  修改组件配置
         *
         *  @for _$.Component
         *  @method setOption
         * @param {String} key 配置key
         * @param {Object} value 配置value
         */
        setOption:function(key,value){
            var _this = this;
            _this.options[key]=value;
        },
        /**
         *  获取组件配置
         *
         *  @for _$.Component
         *  @method getOption
         * @param {String} key 配置key
         */
        getOption:function(key){
            var _this = this;
            var value = _this.options[key];
            return value;
        },
        /**
         *  组件toString方法
         *
         *  @for _$.Component
         *  @method toString
         */
        toString:function(){
            var _this = this;
            return _this.id;
        },
        /**
         *  组件销毁方法
         *
         *  @for _$.Component
         *  @method destroy
         */
        destroy : function() {
            var _this = this;
            this.el.remove();
            _$.unReg(_this);
        }
    };

})(window);

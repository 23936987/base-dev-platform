var _$ = BDP = {};

$.extend($,{
    bdp:BDP
});

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

$.extend(_$,{
    basePath:"/lib/bdp/",
    parseInt:function(str){
        var res = parseInt(str);
        return isNaN(res)?0:res;
    },
    parseFloat:function(str){
        var res = parseFloat(str);
        return isNaN(res)?0:res;
    },
    /**
     *  解析选择器
     *
     *  @for _$
     *  @method getTarget
     * @param {String,nodeType,jquery} target 选择器
     * @static
     */
    getTarget:function(target){
        if(target.nodeType){
            return $(target);
        }else if(isString(target)){
            return $(target);
        }
        return target;
    },
    getFunctoin:function(fn,scope){
        var func;
        if(scope){
            func = scope[fn];
        }else{
            func = window[fn];
        }
        return func;
    },
    css : function(target, a) {
        var j = a.toLowerCase().split(";");
        for (var f = 0, d = j.length; f < d; f++) {
            var k = j[f];
            var m = k.split(":");
            if (m.length > 1) {
                if (m.length > 2) {
                    var h = m[0].trim();
                    m.removeAt(0);
                    var g = m.join(":").trim();
                    target.css(h, g);
                } else {
                    target.css(m[0].trim(), m[1].trim());
                }
            }
        }
    }
});

$.extend(_$,{
    extendCommon:function(childClass,parentClass,interfaces,props){
        var childProps = childClass.prototype;
        var parentProps=null;
        if(parentClass) {
            parentProps = parentClass.prototype;
        }
        if(parentProps != null) {
            for ( var key in parentProps) {
                childProps[key] = parentProps[key];
            }
        }
        if(interfaces != null && interfaces.length>0){
            for(var i=0;i<interfaces.length;i++){
                var func = interfaces[i];

                var interfaceName = func.interfaceName;
                var interfaceProps = func.prototype;
                childClass[interfaceName] = interfaceProps;
                if(interfaceProps){
                    for ( var key2 in interfaceProps) {
                        childProps[key2] = interfaceProps[key2];
                    }
                }
            }
        }
        if (props) {
            for ( var key1 in props) {
                childProps[key1] = props[key1];
            }
        }
    },
    /**
     *  解析类继承体系
     *
     *  @for _$
     *  @method extend
     * @param {Function} childClass 子类构造器
     * @param {Function} parentClass 父类构造器
     * @param {JSON} props 子类自定义实例方法和重写父类同名方法
     * @static
     */
    extend: function() {
        var childClass, parentClass,interfaces,props;

        childClass = arguments[0];
        parentClass = arguments[1];
        if(arguments.length == 3){
            props = arguments[2];
            interfaces = [];
        }else if(arguments.length == 4){
            interfaces = arguments[2];
            props = arguments[3];
        }else{
            alert("继承方法参数不对");
            return;
        }

         var parentProps = parentClass.prototype;
        if (childClass.superclass == parentProps) {
            return;
        }

        childClass.superclass = parentProps;
        childClass.superclass.constructor = parentClass;

         _$.extendCommon(childClass,parentClass,interfaces,props);
    },

    extendLoad: function() {

        var childClass, parentClassName,parentClass,interfaces,props;

        childClass = arguments[0];
        parentClassName = arguments[1];

        if(arguments.length == 3){
            props = arguments[2];
            interfaces = [];
        }else if(arguments.length == 4){
            interfaces = arguments[2];
            props = arguments[3];
        }else{
            alert("继承方法参数不对");
            return;
        }

        parentClass =  _$.getClass(parentClassName);
        if(parentClass != null) {
            var childProps = childClass.prototype, parentProps = parentClass.prototype;
            if (childClass.superclass == parentProps) {
                return;
            }

            childClass.superclass = parentProps;
            childClass.superclass.constructor = parentClass;

            _$.extendCommon(childClass,parentClass,interfaces,props);

        }else{
            var res = {};
            res[parentClassName]=_$.basePath+"js/"+parentClassName+".js";
            _$._loadCssAndJs(res,function(){

                parentClass =  _$.getClass(parentClassName);
                var parentProps = parentClass.prototype;
                if (childClass.superclass == parentProps) {
                    return;
                }

                childClass.superclass = parentProps;
                childClass.superclass.constructor = parentClass;

                _$.extendCommon(childClass,parentClass,interfaces,props);
            });
        }
    },

    implements:function () {
        var childClass,interfaces,props;
        if(arguments.length == 2){
            props = arguments[1];
            interfaces = [];
        }else if(arguments.length == 3){
            interfaces = arguments[1];
            props = arguments[2];
        }else{
            alert("继承方法参数不对");
            return;
        }
        childClass = arguments[0];
        _$.extendCommon(childClass,null,interfaces,props);
    }
});
$.extend(_$,{
    components : {},
    uids:{},
    classes:{},
    _clsPre:"cc-",
    _uiPreCls:"ui-",
    /**
     *  注册控件对象到全局上下文中
     *
     *  @for _$
     *  @method reg
     * @param {Component} obj 控件对象
     * @static
     */
    reg:function(obj){
        if(obj === null || !obj.isComponent){
            return;
        }
        var id = obj.id;
        var uid = obj.uid;

        var old = _$.components[id];
        if(old != null){
            if(old.uid != obj.uid){
                delete _$.components[old.id];
                delete _$.uids[old.uid];
                _$.components[id] = obj;
                _$.uids[uid] = obj;
            }
        }else{
            _$.components[id] = obj;
            _$.uids[uid] = obj;
        }
    },
    unReg:function(obj){
        if(obj === null || !obj.isComponent ){
            return;
        }
        var id = obj.id;
        var uid = obj.uid;
        delete _$.components[id];
        delete _$.uids[uid];
    },
    getById:function(id) {
        var obj = _$.components[id];
        return isEmpty(obj)?null:obj;
    },
    getByUId:function(uid) {
        var obj = _$.uids[uid];
        return isEmpty(obj)?null:obj;
    },
    regClass:function(pluginName,func){
        if (func) {
            if(!_$.classes[pluginName]){
                func.prototype.type = pluginName;
                _$.classes[pluginName] = func;
            }
        }
    },
    regPlugins:function(arr){
       _$.parser.plugins.addAll(arr);
    },
    getClass:function(pluginName){
        var func = _$.classes[pluginName];
        return isEmpty(func)?null:func;
    },
    findControls:function(context){
        var _this = this;
        if(context === null) {
            context = $("body");
        }
        var list=$("[class^='"+_this._uiPreCls+"']",context);
        var items = [];
        list.each(function(e){
            var obj = $(this);
            var uid = obj.attr("componentId");
            obj = _$.getByUId(uid);
            if(obj){
                items.add(obj);
            }
        });
        return items;
    }
});

$.extend(_$,{
    cssLoaded:{},
    jsLoaded:{},
    _LOADING_:"loading",
    _SUCCESES_:"success",
    _WAIT_:5,
    _suffix:function (url){
        var index = url.lastIndexOf(".");
        return url.substr(index+1);
    },
    getKeys:function(urls){
        var keys = [];
        for(var key in urls){
            keys.add(key);
        }
        return keys;
    },
    isJsDepsLoad:function(files){
        var _this = this;
        return files.every(function(file){
            var loadState =  _this.jsLoaded[file];
            if(loadState == null || loadState == _this._LOADING_){
                return false;
            }
            return true;
        });
    },
    isCssDepsLoad : function(files){
        var _this = this;
        return files.every(function(file){
            var loadState =  _this.cssLoaded[file];
            if(loadState == null || loadState == _this._LOADING_){
                return false;
            }
            return true;
        });
    },
    _loadCssAndJs:function(urls,func){
        var _this = this;
        var keys = _this.getKeys(urls);
        if(keys != null && keys.length>0){
            var length = keys.length;
            var count=0;
            keys.each(function(key){
                var obj = urls[key];
                var url="";
                var deps=[];
                if(isString(obj)){
                    url = obj;
                }else{
                    url = obj.url;
                    deps = obj.deps;
                }
                var suffix = _this._suffix(url);
                if("js" ==  suffix){
                    var loadState =  _this.jsLoaded[key];
                    if(loadState == null){
                        var loadJs = function(){
                            _this._loadJs(key,url,function(){
                                count++;
                                if(count == length){
                                    func.call(window);
                                }
                            });
                        };
                        if(deps == null || deps.length == 0) {
                            loadJs();
                        }else{
                            if(_this.isJsDepsLoad(deps)){
                                loadJs()
                            }else{
                                var timer = setInterval(function(){
                                    if(_this.isJsDepsLoad(deps)){
                                        clearInterval(timer);
                                        var depUrl = {};
                                        depUrl[key] = obj;
                                        _this._loadCssAndJs(depUrl,function(){
                                            count++;
                                            if(count == length){
                                                func.call(window);
                                            }
                                        })
                                    }
                                },_this._WAIT_);
                            }
                        }
                    }else if(loadState != null && loadState == _this._SUCCESES_){
                        count++;
                        if(count == length){
                            func.call(window);
                        }
                    } else if(loadState != null && loadState == _this._LOADING_){
                        var time1 = setInterval(function(){
                            if(_this.isJsDepsLoad([key])){
                                clearInterval(time1);
                                count++;
                                if(count == length){
                                    func.call(window);
                                }
                            }
                        },_this._WAIT_);
                    }
                }else if("css" == suffix){
                    var loadState =  _this.cssLoaded[key];
                    if(loadState == null){
                        _this._loadCss(key,url,function(){
                            count++;
                            if(count == length){
                                func.call(window);
                            }
                        });
                    }else if(loadState != null && loadState == _this._SUCCESES_){
                        count++;
                        if(count == length){
                            func.call(window);
                        }
                    }else if(loadState != null && loadState == _this._LOADING_){
                        var time2 = setInterval(function(){
                            if(_this.isCssDepsLoad([key])){
                                clearInterval(time2);
                                count++;
                                if(count == length){
                                    func.call(window);
                                }
                            }
                        },_this._WAIT_);
                    }
                }
            });
        }else{
            func.call(window);
        }
    },
    _loadJs:function(key,url,func){
        var _this = this;
        _this.jsLoaded[key] = _this._LOADING_;
        var script = document.createElement("script");
        script.setAttribute("type", "text/javascript");
        script.src = url;
        document.getElementsByTagName("head")[0].appendChild(script);
        script.onload = script.onreadystatechange = function(){
            if (!this.readyState || 'loaded' === this.readyState || 'complete' === this.readyState) {
                _this.jsLoaded[key] = _this._SUCCESES_;
                func.call(window);
            }
        }
    },
    _loadCss:function(key,url,func){
        var _this = this;
        _this.cssLoaded[key] = _this._LOADING_;
        var css = document.createElement("link");
        css.setAttribute("type", "text/css");
        css.setAttribute("rel", "stylesheet");
        css.href = url;
        document.getElementsByTagName("head")[0].appendChild(css);
        css.onload = css.onreadystatechange = function(){
            if (!this.readyState || 'loaded' === this.readyState || 'complete' === this.readyState) {
                _this.cssLoaded[key] = _this._SUCCESES_;
                func.call(window);
            }
        }
    }
});

$.extend(_$,{
    parser:{
        parseOptions: function(target,properties){
            var options = {};
            var s = $.trim(target.attr('data-options'));
            if (s){
                if (s.substring(0, 1) != '{'){
                    s = '{' + s + '}';
                }
                options = (new Function('return ' + s))();
            }

            $.map(['width','height','minWidth','maxWidth','minHeight','maxHeight'], function(p){
                var pv = $.trim(target[0].style[p] || '');
                if (pv){
                    if (pv.indexOf('%') == -1){
                        pv = parseInt(pv);
                        if (isNaN(pv)){
                            pv = undefined;
                        }
                    }
                    options[p] = pv;
                }
            });

            if (properties){
                var opts = {};
                for(var i=0; i<properties.length; i++){
                    var pp = properties[i];
                    if (typeof pp == 'string'){
                        opts[pp] = target.attr(pp);
                    } else {
                        for(var name in pp){
                            var type = pp[name];
                            if (type == 'boolean'  && isNotEmpty(target.attr(name))){
                                var val = target[0].getAttribute(name);
                                opts[name] = val ? (val == 'true') : false;
                            } else if (type == 'number' && isNotEmpty(target.attr(name))){
                                opts[name] = target.attr(name)=='0' ? 0 : parseFloat(target.attr(name)) ;
                            }
                            else if (type == 'json'){
                                var s = $.trim(target.attr(name));
                                if (s){
                                    if (s.substring(0, 1) != '{'){
                                        s = '{' + s + '}';
                                    }
                                    options[name] = (new Function('return ' + s))();
                                }else{
                                    options[name]={};
                                }
                            }else if(type == 'function'){
                                var s = $.trim(target.attr(name));
                                if(isNotEmpty(s)){
                                    var func = _$.getFunctoin(s);
                                    options[name]=func;
                                }
                            }
                            else if (type == 'object'){
                                var s = $.trim(target.attr(name));
                                if (s){
                                    options[name]=eval('(' + s + ')');
                                }
                            }
                        }
                    }
                }
                $.extend(options, opts);
            }
            return options;
        },
        processList:{},
        plugins:[],
        getProgress:function (processKey) {
            var process =  this.processList[processKey];
            return process;
        },
        setProgress:function (processKey,obj) {
            this.processList[processKey] = obj;
        },
        onComponentComplete: function(component){
            var _this = this;
            if(component == null) {
                return;
            }
            var id = component.id;
            var processKey = component.processKey;

            var process = _$.parser.getProgress(processKey);
            if(process == null || process._IS_COMPLETE){
                return;
            }
            process.ids.remove(id);
            // logger("***控件["+type+"]件完成,id[" + id+"]");
            if(process.ids.length === 0){
                process._IS_COMPLETE = true;
                logger("***控件解析完成");
                process.onComplete.call(window);
            }
        },
        parse: function(){
            var context = $("body");
            var callback= function (processKey) {
            };
            if(arguments.length == 1){
                if(isFunction(arguments[0])){
                    callback = arguments[0];
                }else{
                    context = arguments[0];
                    if(isString(context)){
                        context = $(context);
                    }else if(context.nodeType){
                        context = $(context);
                    }
                }
            }else if(arguments.length == 2){
                context = arguments[0];
                callback = arguments[1];
                if(isString(context)){
                    context = $(context);
                }else if(context.nodeType){
                    context = $(context);
                }
            }

            var processKey = uuid();
            new _$.parser.parseComponent(processKey,context,callback);

        },
        parseComponent:function (processKey,context,callback){
            _$.parser.setProgress(processKey,this);
            var _this = this;
            _this._IS_COMPLETE=false;
            _this.count = 0;
            _this.ids = [];
            _this.onComplete = callback;

            if(isEmpty(context) && context.size() == 0) {
                return;
            }
            var components = {};
            for(var i=0; i<_$.parser.plugins.length; i++){
                var name = _$.parser.plugins[i];
                var list = $('.' + _$._clsPre + name, context);
                list.each(function(e){
                    var obj = $(this);
                    var id = obj.attr("id");
                    _this.count++;
                    _this.ids.add(id);
                });
                components[name] = list;
            }
            if(_this.count === 0){
                _this._IS_COMPLETE=true;
                _this.onComplete.call(window,processKey);
                return;
            }
            // logger("控件数 " + _this.count);
            for(var componentName in components){

                var list1 = components[componentName];
                if(list1  == null || list1.size() == 0){
                    continue;
                }

                list1.each(function(){
                    var target = $(this);
                    var Func =  _$.getClass(componentName);

                    if(Func !== null){
                        new Func(target,processKey);
                    }else{
                        (function (componentName) {
                            var res = {};
                            res[componentName]=_$.basePath+"js/"+componentName+".js";
                            _$._loadCssAndJs(res,function(){
                                var time1 = setInterval(function () {
                                    Func =  _$.getClass(componentName);
                                    if(Func != null && Func.superclass != null) {
                                        clearInterval(time1);
                                        new Func(target,processKey);
                                    }
                                },20)
                            });
                        })(componentName)
                    }
                });
            }
        }
    }
});

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
    _$._loadCssAndJs(res,function(){
        _this._create();
    });
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

/**
 *   验证组件
 *
 * @class validator
 * @namespace _$
 */
$.extend(_$,{
    "validator":{
        execute:function(id,name,value,nameCn,rule){

            var dtd=$.Deferred();
            var _this = this;
            var type = rule["type"];
            var validMsg = isEmpty(rule["msg"]) ? _this.messages[type] : rule["msg"];
            var ruleValue = rule['value'];

            var handler = _this.methods[type];

            var params={
                "value":value,
                "id":id,
                "name":name,
                "nameCn":nameCn,
                "ruleValue":ruleValue,
                "validMsg":validMsg
            };

            $.when(handler.call(window,params))
            .done(function(res){
                dtd.resolve(res);
            })
            .fail(function(err){
                dtd.reject(err)
            });
            return dtd.promise();

        },
        addMethod:function(name,func,msg){
            _$.validator.methods[name] = func;
            _$.validator.messages[name] = msg;
        },
        messages:{
        },
        format:function(source, params ) {
            var _this = this;
            if ( arguments.length === 1 ) {
                return function() {
                    var args = $.makeArray( arguments );
                    args.unshift( source );
                    return _this.format.apply( this, args );
                };
            }
            if ( arguments.length > 2 && params.constructor !== Array  ) {
                params = $.makeArray( arguments ).slice( 1 );
            }
            if ( params.constructor !== Array ) {
                params = [ params ];
            }
            $.each( params, function( i, n ) {
                source = source.replace( new RegExp( "\\{" + i + "\\}", "g" ), function() {
                    return n;
                });
            });
            return source;
        },
        zeroHandler:function(params,func,names,type){
            var dtd=$.Deferred();
            var value=params["value"];
            var nameCn=params["nameCn"];
            var validMsg=params["validMsg"];


            if (isEmpty(value) && type != 'required') {
                var res = {
                    "state":true,
                    "info":""
                };
                dtd.resolve(res);
            }else{
                $.when(func.call(null,value))
                .done(function(state){
                    if(isEmpty(names)){
                        names = [nameCn]
                    }
                    var info = _$.validator.format(validMsg, names);
                    var res = {
                        "state":state,
                        "info":info
                    };
                    dtd.resolve(res);
                })
                .fail(function(err){
                    dtd.reject(err)
                });
            }


            return dtd.promise();
        },
        oneHandler:function(params,func){
            var value=params["value"];
            var nameCn=params["nameCn"];
            var param=params["ruleValue"];
            var validMsg=params["validMsg"];
            var dtd=$.Deferred();

            if (isEmpty(value)) {
                var res = {
                    "state":true,
                    "info":""
                };
                dtd.resolve(res);
            }else{
                $.when(func.call(null,value,param))
                .done(function(state){
                    var  names = [nameCn,param];
                    var info = _$.validator.format(validMsg, names);

                    var res = {
                        "state":state,
                        "info":info
                    };
                    dtd.resolve(res);
                })
                .fail(function(err){
                    dtd.reject(err)
                });
            }
            return dtd.promise();
        },
        twoHandler:function(params,func){

            var value=params["value"];
            var nameCn=params["nameCn"];
            var ruleValue=params["ruleValue"];
            var validMsg=params["validMsg"];
            var dtd=$.Deferred();

            if (isEmpty(value)) {
                var res = {
                    "state":true,
                    "info":""
                };
                dtd.resolve(res);
            }else{
                var arr = ruleValue.split(",");
                var param1 = arr[0];
                var param2 = arr[1];

                $.when(func.call(null,value,param1,param2))
                    .done(function(state){
                        var names = [nameCn,param1,param2];
                        var info = _$.validator.format(validMsg, names);

                        var res = {
                            "state":state,
                            "info":info
                        };
                        dtd.resolve(res);
                    })
                    .fail(function(err){
                        dtd.reject(err)
                    });

            }
        },
        compareTo:function (val1,val2,format) {
            if(isNotEmpty(format)){
                //日期
                var date1 = parseDate(val1,format).getTime();
                var date2 = parseDate(val2,format).getTime();

                if(date1 == date2)  {
                    return 0;
                }else if(date1 < date2) {
                    return -1
                }else{
                    return 1;
                }
            }else {
                if(val1 == val2)  {
                    return 0;
                }else if(val1 < val2) {
                    return -1
                }else{
                    return 1;
                }
            }
            return 0;
        },
        methods: {
        }
    }
});


_$.validator.addMethod("required",function(params){
   return _$.validator.zeroHandler(params,function(val){
       var dtd=$.Deferred();
       var flag = isNotEmpty(val);
       dtd.resolve(flag);
       return dtd.promise();
    },null,"required");
},"[{0}]必填");
_$.validator.addMethod("email",function(params){
    return  _$.validator.zeroHandler(params,function(val){
        var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        var dtd=$.Deferred();
        var flag = reg.test(val);
        dtd.resolve(flag);
        return dtd.promise();
    });
},"[{0}]必须是有效的电子邮件地址");
_$.validator.addMethod("password",function(params){
    return  _$.validator.zeroHandler(params,function(val){
        var reg = /^[A-Za-z0-9]{6,20}$/;

        var dtd=$.Deferred();
        var flag = reg.test(val);
        dtd.resolve(flag);
        return dtd.promise();
    });
},"[{0}]必须由6-20位字母数字组成");
_$.validator.addMethod("mobile",function(params){
    return  _$.validator.zeroHandler(params,function(val){
        var reg = /^1\d{10}$/;

        var dtd=$.Deferred();
        var flag = reg.test(val);
        dtd.resolve(flag);
        return dtd.promise();
    });
},"[{0}]必须是有效的手机号");
_$.validator.addMethod("tel",function(params){
    return  _$.validator.zeroHandler(params,function(val){
        var reg = /^0\d{2,3}-?\d{7,8}$/;

        var dtd=$.Deferred();
        var flag = reg.test(val);
        dtd.resolve(flag);
        return dtd.promise();
    });
},"[{0}]必须是有效的座机号");
_$.validator.addMethod("url",function(params){
    return  _$.validator.zeroHandler(params,function(val){
        var reg = /^((https|http|ftp|rtsp|mms)?:\/\/)+[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;

        var dtd=$.Deferred();
        var flag = reg.test(val);
        dtd.resolve(flag);
        return dtd.promise();
    });
},"[{0}]必须是有效的网址");
_$.validator.addMethod("account",function(params){
    return  _$.validator.zeroHandler(params,function(val){
        var reg = /^[a-z0-9A-z]\w{5,9}$/;

        var dtd=$.Deferred();
        var flag = reg.test(val);
        dtd.resolve(flag);
        return dtd.promise();
    });
},"[{0}]必须是由6-10位字母和数字组成");
_$.validator.addMethod("money",function(params){
    return  _$.validator.zeroHandler(params,function(val){
        var reg = /(^-?[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;

        var dtd=$.Deferred();
        var flag = reg.test(val);
        dtd.resolve(flag);
        return dtd.promise();
    });
},"[{0}]必须是有效的金钱格式");
_$.validator.addMethod("number",function(params){
    return  _$.validator.zeroHandler(params,function(val){
        var reg = /^(\-|\+)?\d+(\.\d+)?$/;

        var dtd=$.Deferred();
        var flag = reg.test(val);
        dtd.resolve(flag);
        return dtd.promise();
    });
},"[{0}]必须是数字");
_$.validator.addMethod("integer",function(params){
    return  _$.validator.zeroHandler(params,function(val){
        var reg = /^[1-9]\d*$/;

        var dtd=$.Deferred();
        var flag = reg.test(val);
        dtd.resolve(flag);
        return dtd.promise();
    });
},"[{0}]必须是正整数");
_$.validator.addMethod("positive",function(params){
    return  _$.validator.zeroHandler(params,function(val){
        var reg = /^-?\d+$/;

        var dtd=$.Deferred();
        var flag = reg.test(val);
        dtd.resolve(flag);
        return dtd.promise();
    });
},"[{0}]必须是整数");
_$.validator.addMethod("natural",function(params){
    return  _$.validator.zeroHandler(params,function(val){
        var reg = /^\d+$/;

        var dtd=$.Deferred();
        var flag = reg.test(val);
        dtd.resolve(flag);
        return dtd.promise();
    });
},"[{0}]必须是自然数");
_$.validator.addMethod("greaterThan",function(params){
    return  _$.validator.oneHandler(params,function(val,num){
        var dtd=$.Deferred();

        if (isNaN(val)) {
            dtd.resolve(false);
            return dtd.promise();
        }
        val = parseFloat(val);
        if (val > num) {
            dtd.resolve(true);
            return dtd.promise();
        }else{
            dtd.resolve(false);
            return dtd.promise();
        }
    });
},"[{0}]必须大于{1}");
_$.validator.addMethod("greaterEqThan",function(params){
    return  _$.validator.oneHandler(params,function(val,num){
        var dtd=$.Deferred();
        if (isNaN(val)) {
            dtd.resolve(false);
            return dtd.promise();
        }
        val = parseFloat(val);
        if (val >= num) {
            dtd.resolve(true);
            return dtd.promise();
        }else{
            dtd.resolve(false);
            return dtd.promise();
        }
    });
},"[{0}]必须大于等于{1}");
_$.validator.addMethod("lessThan",function(params){
    return  _$.validator.oneHandler(params,function(val,num){
        var dtd=$.Deferred();
        if (isNaN(val)) {
            dtd.resolve(false);
            return dtd.promise();
        }
        val = parseFloat(val);
        if (val < num) {
            dtd.resolve(true);
            return dtd.promise();
        }else{
            dtd.resolve(false);
            return dtd.promise();
        }
    });
},"[{0}]必须小于{1}");
_$.validator.addMethod("lessEqThan",function(params){
    return  _$.validator.oneHandler(params,function(val,num){
        var dtd=$.Deferred();
        if (isNaN(val)) {
            dtd.resolve(false);
            return dtd.promise();
        }
        val = parseFloat(val);
        if (val <= num) {
            dtd.resolve(true);
            return dtd.promise();
        }else{
            dtd.resolve(false);
            return dtd.promise();
        }
    });
},"[{0}]必须小于等于{1}");

_$.validator.addMethod("lengthGreaterThan",function(params){
    return  _$.validator.oneHandler(params,function(val,num){
        var dtd=$.Deferred();
        var str = (val + "").trim();
        if (str.length > num) {
            dtd.resolve(true);
            return dtd.promise();
        }else{
            dtd.resolve(false);
            return dtd.promise();
        }
    });
},"[{0}]必须大于{1}个字符");
_$.validator.addMethod("lengthGreaterEqThan",function(params){
    return  _$.validator.oneHandler(params,function(val,num){
        var dtd=$.Deferred();
        var str = (val + "").trim();
        if (str.length >= num) {
            dtd.resolve(true);
            return dtd.promise();
        }else{
            dtd.resolve(false);
            return dtd.promise();
        }
    });
},"[{0}]必须大于等于{1}个字符");
_$.validator.addMethod("lengthGreaterThan",function(params){
    return  _$.validator.oneHandler(params,function(val,num){
        var dtd=$.Deferred();
        var str = (val + "").trim();
        if (str.length < num) {
            dtd.resolve(true);
            return dtd.promise();
        }else{
            dtd.resolve(false);
            return dtd.promise();
        }
    });
},"[{0}]必须小于{1}个字符");
_$.validator.addMethod("lengthGreaterEqThan",function(params){
    return  _$.validator.oneHandler(params,function(val,num){
        var dtd=$.Deferred();
        var str = (val + "").trim();
        if (str.length <= num) {
            dtd.resolve(true);
            return dtd.promise();
        }else{
            dtd.resolve(false);
            return dtd.promise();
        }
    });
},"[{0}]必须大于等于{1}个字符");
_$.validator.addMethod("rangeLength",function(params){
    return  _$.validator.twoHandler(params,function(val,min,max){
        var dtd=$.Deferred();
        var str = (val + "").trim();
        if (str.length < max && str.length > min) {
            dtd.resolve(true);
            return dtd.promise();
        }else{
            dtd.resolve(false);
            return dtd.promise();
        }
    });
},"[{0}]长度必须是大{1},小于{2}");
_$.validator.addMethod("rangeLengthEq",function(params){
    return  _$.validator.twoHandler(params,function(val,min,max){
        var dtd=$.Deferred();
        var str = (val + "").trim();
        if (str.length <= max && str.length >= min) {
            dtd.resolve(true);
            return dtd.promise();
        }else{
            dtd.resolve(false);
            return dtd.promise();
        }
    });
},"[{0}]长度必须是大于{1},小于等于{2}");
_$.validator.addMethod("range",function(params){
    return  _$.validator.twoHandler(params,function(val,min,max){
        var dtd=$.Deferred();
        if (isNaN(val)) {
            dtd.resolve(false);
            return dtd.promise();
        }
        val = parseFloat(val);
        if (val < max && val > min) {
            dtd.resolve(true);
            return dtd.promise();
        }else{
            dtd.resolve(false);
            return dtd.promise();
        }
    });
},"[{0}]必须大于{1},小于{2}");
_$.validator.addMethod("rangeEq",function(params){
    return  _$.validator.twoHandler(params,function(val,min,max){
        var dtd=$.Deferred();
        if (isNaN(val)) {
            dtd.resolve(false);
            return dtd.promise();
        }
        val = parseFloat(val);
        if (val <= max && val >= min) {
            dtd.resolve(true);
            return dtd.promise();
        }else{
            dtd.resolve(false);
            return dtd.promise();
        }
    });
},"[{0}]必须是大于等于{1},小于等于{2}");
_$.validator.addMethod("greaterEqThanTo",function(params){
    var ruleValue=params["ruleValue"];
    var arr = ruleValue.split(",");

    if(arr.length == 1){
        var compareTo = arr[0];
        var objTo = _$.getById(compareTo)
        var valueTo = objTo.getValue();

        var nameCn=params["nameCn"];
        var nameCnTo = objTo.getOption("nameCn");

        var names = [nameCn,nameCnTo];
        return  _$.validator.zeroHandler(params,function(val){
            var dtd=$.Deferred();
            var result =_$.validator.compareTo(val,valueTo) >= 0;
            dtd.resolve(result);
            return dtd.promise();
        },names);
    }else if(arr.length == 2){
        var compareTo = arr[0];
        var format = arr[1];
        var objTo = _$.getById(compareTo)
        var valueTo = objTo.getValue();

        var nameCn=params["nameCn"];
        var nameCnTo = objTo.getOption("nameCn");

        var names = [nameCn,nameCnTo];
        return  _$.validator.zeroHandler(params,function(val){
            var dtd=$.Deferred();
            var result =_$.validator.compareTo(val,valueTo,format) >= 0;
            dtd.resolve(result);
            return dtd.promise();

        },names);
    }
},"[{0}]必须大于等于[{1}]");
_$.validator.addMethod("greaterThanTo",function(params){
    var ruleValue=params["ruleValue"];
    var arr = ruleValue.split(",");

    if(arr.length == 1){
        var compareTo = arr[0];
        var objTo = _$.getById(compareTo)
        var valueTo = objTo.getValue();

        var nameCn=params["nameCn"];
        var nameCnTo = objTo.getOption("nameCn");

        var names = [nameCn,nameCnTo];
        return  _$.validator.zeroHandler(params,function(val){
            var dtd=$.Deferred();
            var result = _$.validator.compareTo(val,valueTo) > 0;
            dtd.resolve(result);
            return dtd.promise();
        },names);
    }else if(arr.length == 2){
        var compareTo = arr[0];
        var format = arr[1];
        var objTo = _$.getById(compareTo)
        var valueTo = objTo.getValue();

        var nameCn=params["nameCn"];
        var nameCnTo = objTo.getOption("nameCn");

        var names = [nameCn,nameCnTo];
        return  _$.validator.zeroHandler(params,function(val){
            var dtd=$.Deferred();
            var result = _$.validator.compareTo(val,valueTo,format) > 0;
            dtd.resolve(result);
            return dtd.promise();

        },names);
    }
},"[{0}]必须大于[{1}]");
_$.validator.addMethod("lessEqThanTo",function(params){
    var ruleValue=params["ruleValue"];
    var arr = ruleValue.split(",");

    if(arr.length == 1){
        var compareTo = arr[0];
        var objTo = _$.getById(compareTo)
        var valueTo = objTo.getValue();

        var nameCn=params["nameCn"];
        var nameCnTo = objTo.getOption("nameCn");

        var names = [nameCn,nameCnTo];
        return  _$.validator.zeroHandler(params,function(val){
            var dtd=$.Deferred();
            var result =_$.validator.compareTo(val,valueTo) <= 0;
            dtd.resolve(result);
            return dtd.promise();
        },names);
    }else if(arr.length == 2){
        var compareTo = arr[0];
        var format = arr[1];
        var objTo = _$.getById(compareTo)
        var valueTo = objTo.getValue();

        var nameCn=params["nameCn"];
        var nameCnTo = objTo.getOption("nameCn");

        var names = [nameCn,nameCnTo];
        return  _$.validator.zeroHandler(params,function(val){
            var dtd=$.Deferred();
            var result =_$.validator.compareTo(val,valueTo,format) <= 0;
            dtd.resolve(result);
            return dtd.promise();

        },names);
    }
},"[{0}]必须小于等于[{1}]");
_$.validator.addMethod("lessThanTo",function(params){
    var ruleValue=params["ruleValue"];
    var arr = ruleValue.split(",");

    if(arr.length == 1){
        var compareTo = arr[0];
        var objTo = _$.getById(compareTo)
        var valueTo = objTo.getValue();

        var nameCn=params["nameCn"];
        var nameCnTo = objTo.getOption("nameCn");

        var names = [nameCn,nameCnTo];
        return  _$.validator.zeroHandler(params,function(val){
            var dtd=$.Deferred();
            var result = _$.validator.compareTo(val,valueTo) < 0;
            dtd.resolve(result);
            return dtd.promise();
        },names);
    }else if(arr.length == 2){
        var compareTo = arr[0];
        var format = arr[1];
        var objTo = _$.getById(compareTo)
        var valueTo = objTo.getValue();

        var nameCn=params["nameCn"];
        var nameCnTo = objTo.getOption("nameCn");

        var names = [nameCn,nameCnTo];
        return  _$.validator.zeroHandler(params,function(val){
            var dtd=$.Deferred();
            var result = _$.validator.compareTo(val,valueTo,format) < 0;
            dtd.resolve(result);
            return dtd.promise();

        },names);
    }
},"[{0}]必须小于[{1}]");
_$.validator.addMethod("equalTo",function(params){
    var ruleValue=params["ruleValue"];
    var arr = ruleValue.split(",");

    var compareTo = arr[0];
    var objTo = _$.getById(compareTo)
    var valueTo = objTo.getValue();

    var nameCn=params["nameCn"];
    var nameCnTo = objTo.getOption("nameCn");

    var names = [nameCn,nameCnTo];
    return  _$.validator.zeroHandler(params,function(val){
        var dtd=$.Deferred();
        var result = _$.validator.compareTo(val,valueTo) == 0;
        dtd.resolve(result);
        return dtd.promise();
    },names);
},"[{0}]必须等于[{1}]");

/**
 * @class Form
 * @constructor
 * @extends _$.Component
 * @param {String,nodeType,jquery} target 选择器
 * @namespace _$
 */
_$.Form=function(target,formId){
    target = _$.getTarget(target);
    var _this = this;
    _this.el = target;
    _this.formId = formId;

    var opts = {
    };
    _this.options = $.extend(true,opts);
};

_$.Form.prototype = {
    validState:true,
    clear:function(){
        var _this = this;
        var formItems =  _this.getFields(_this.el);
        if(formItems !== null  && formItems.length>0){
            for(var i=0;i<formItems.length;i++){
                var obj = formItems[i];
                if(obj){
                    obj.setValue(null);
                }
            }
        }
    },
    reset:function(){
        var _this = this;
        var formItems =  _this.getFields(_this.el);
        if(formItems !== null  && formItems.length>0){
            for(var i=0;i<formItems.length;i++){
                var obj = formItems[i];
                if(obj){
                    obj.reset();
                }
            }
        }
    },
    setValue:function(values){
        var _this = this;
        var formItems =  _this.getFields(_this.el);
        for(var i=0;i<formItems.length;i++){
            var obj = formItems[i];
            if(obj != null){
                var key = obj.getOption("name") || obj.id;
                if(values.hasOwnProperty(key)){
                    var value = values[key];
                    obj.orgiValue = value;
                    var setValueFormat = obj.getOption("setValueFormat");

                    value = setValueFormat.call(window,values,value,obj.id);
                    if(_this.formId){
                        obj.setFormData(values,_this.formId);
                    }
                    obj.setValue(value);
                }
            }
        }
    },
    setValueNoChange:function(values,propChange){
        var _this = this;
        if(isEmpty(propChange)){
            propChange={};
        }
        var formItems =  _this.getFields(_this.el);
        for(var i=0;i<formItems.length;i++){
            var obj = formItems[i];
            if(obj != null){
                var key = obj.getOption("name") || obj.id;
                if(values.hasOwnProperty(key)){
                    var value = values[key];
                    obj.orgiValue = value;
                    var setValueFormat = obj.getOption("setValueFormat");
                    value = setValueFormat.call(window,values,value);
                    if(_this.formId){
                        obj.setFormData(values,_this.formId);
                    }
                    var change = propChange[key];
                    if(!change){
                        obj.setValue(value,false);
                    }else{
                        obj.setValue(value);
                    }
                }
            }
        }
    },
    getValue:function(){
        var _this = this;
        var formItems =  _this.getFields(_this.el);
        var values = {};
        for(var i=0;i<formItems.length;i++){
            var obj = formItems[i];
            if(obj !== null){
                var returnValue = obj.getOption("returnValue");
                if(returnValue){
                    var value = obj.getValue();
                    if(isNotEmpty(value)){
                        var key = obj.getOption("name") || obj.id;
                        values[key] = value;
                    }
                }
            }
        }
        return values;
    },
    getEditData:function(){
        var _this = this;
        var formItems =  _this.getFields(_this.el);
        var values = {};
        for(var i=0;i<formItems.length;i++){
            var obj = formItems[i];
            if(obj !== null){
                obj.getEditValue(values);
            }
        }
        return values;
    },
    getAddData:function(){
        var _this = this;
        var values = {};
        var current = _this.getValue();
        for(var key in current){
            var c_val = current[key];
            if(isNotEmpty(c_val)){
                values[key] = c_val;
            }
        }
        return values;
    },
    getQueryData:function(){
        var _this = this;
        var formItems =  _this.getFields(_this.el);
        var values = {};
        for(var i=0;i<formItems.length;i++){
            var obj =formItems[i];
            var _id =obj.id;

            if((_id+"").indexOf("_type") != -1){
                continue;
            }
            if(obj != null){
                var value = obj.getValue();
                if(isNotEmpty(value)){
                    var name = obj.getOption("name") || _id;
                    values[name] = value;

                    var qtype = obj.getOption("qtype");
                    qtype = qtype || _id;
                    var typeId = qtype + "_type";

                    var typeObj = _$.getById(typeId);

                    if(typeObj!= null){
                        var typeValue = typeObj.getValue();
                        if(isNotEmpty(typeValue)){
                            var typeName = typeObj.getOption("name");
                            values[typeName] = typeValue;
                        }
                    }
                }
            }
        }
        return values;
    },
    getFields:function(){
        var _this = this;
        var items = _$.findControls(_this.el);

        var list = [];
        for(var i=0;i<items.length;i++){
            var item = items[i];
            if(item.isFormItem){
                list.add(item);
            }
        }
        return list;
    },
    setValid:function(flag){
        var _this = this;
        _this.validState = flag;
        var formItems = _this.getFields();
        for(var i=0;i<formItems.length;i++){
            var obj = formItems[i];
            obj.setValid(flag);
        }
    },
    isValid:function(hiddenFlag){
        var _this = this;
        var dtd=$.Deferred();

        $("#validDiv").empty();
        hiddenFlag = hiddenFlag || false;
        _this.validState = true;

        var formItems = _this.getFields();

        var arr = [];
        for(var i=0;i<formItems.length;i++){
            var obj = formItems[i];
            arr.push(obj.isValid(hiddenFlag));
        }

        $.when.apply(this,arr)
        .done(function(){
            for (var i = 0; i < arguments.length; i++) {
                var flag = arguments[i];
               if(_this.validState && !flag){
                   _this.validState = flag;
                   break;
               }
            }
            dtd.resolve(_this.validState);
        })
        .fail(function(err){
            dtd.reject(err)
        });

        return dtd.promise();
    }
};
/**
 *  表单组件模块
 *
 * @submodule form
 * @module CC
 */

/**
 *  表单组件父类
 * @class FormItem
 * @submodule form
 * @constructor
 * @extends _$.Component
 * @param {String,nodeType,jquery} target 选择器
 * @param {String} processKey 解析器key
 * @namespace _$
 */

var _EDITABLE_ = 1;
var _SHOW_ = 0;
_$.FormItem = function(target,processKey){
    var _this = this;
    _$.FormItem.superclass.constructor.call(_this,target,processKey);
};

_$.extend(_$.FormItem,_$.Component,{
    /**
     * 判断组件是否是表单
     @for _$.FormItem
     @property isFormItem
     *@type {Boolean}
     */
    isFormItem:true,
    formData:{},
    /**
     * 组件取值
     @for _$.FormItem
     @property value
     *@type {Object}
     */
    value:null,
    /**
     * 组件原值
     @for _$.FormItem
     @property value
     *@type {Object}
     */
    orgiValue:null,
    _uiCls:_$._uiPreCls + "FormItem",
    _cls:_$._clsPre + "formItem",
    _spanCls:_$._uiPreCls + "span",
    /**
     * 组件校验状态
     @for _$.FormItem
     @property validState
     *@type {Boolean}
     */
    validState:true,
    initialization:false,
    setFormData:function (values,formId) {
        var _this = this;
        _this.formData[formId] = values;
    },
    getFormData:function (formId) {
        var _this = this;
        return _this.formData[formId];
    },
    /**
     * 组件校验失败信息
     @for _$.FormItem
     @property errMsg
     *@type {Array}
     */
    errMsg:[],
    _create :function(){
        var _this = this;
        if(_this._isEdit()){
            _this._editModel();
        }else{
            _this._showModel();
        }
        _this.initialization=true;
        _this.el.attr("formId",_this.id);
    },
    /**
     *  组件编辑模式初始化,
     *
     *  @for _$.FormItem
     *  @method _editModel
     */
    _editModel:function(){
    },
    /**
     *  获取用户自定义数据,
     *
     *  @for _$.FormItem
     *  @method getUserData
     */
    getUserData:function () {
        var _this = this;
        return _this.getOption("userData");
    },
    /**
     *  组件展示模式初始化,
     *
     *  @for _$.FormItem
     *  @method _editModel
     */
    _showModel:function(){
        var _this = this;
        _this.setOption("model",_SHOW_);
        _this._clearEdit();
        var source='<div class="showDiv"></div>';
        _this._replaceNode(source);
        _this.el.addClass(_this._spanCls);
        _this.el.addClass("FormItem");
        _this.component = $(".showDiv",_this.el);
        _this._bindChangeEvents();
        _this._init();
    },
    /**
     *  清空编辑模式,
     *
     *  @for _$.FormItem
     *  @method _clearEdit
     */
    _clearEdit:function(){
        var _this = this;
        if(_this.component){
            _this.component.unbind();
            _this.component.remove();
        }
    },
    /**
     *  清空展示模式,
     *
     *  @for _$.FormItem
     *  @method _clearShow
     */
    _clearShow:function(){
        var _this = this;
        if(_this.component){
            _this.component.remove();
        }
    },

    selectText:function (jq) {
        var _this = this;
        var val = jq.val() +"";
        var len = val.length;
        var obj =jq[0];
        if(navigator.userAgent.indexOf("MSIE") > -1){
            var range = document.selection.createRange();
            var textRange = obj.createTextRange();
            textRange.moveStart('character',len);
            textRange.collapse();
            textRange.select();
        }else{
            obj.setSelectionRange(0,len);
        }
    },

    _elHeight:function () {
        var _this = this;
        var height =_this.getOption('height');
        if(isNotEmpty(height)){
            var validDivHeight = $(".form_item_valid",_this.el).height();
            var memoDivHeight = $(".form_item_memo",_this.el).height();
            var elHeight = validDivHeight + memoDivHeight + height;
            _this.el.height(elHeight);
        }
    },
    _setStyle:function(){
        var _this = this;
        var width = _this.getOption('width');
        if(isNotEmpty(width)){
            _this.el.width(width);
        }
        _this._elHeight();
    },
    setWidth:function (width) {
        var _this = this;
        if(isNotEmpty(width)){
            _this.el.width(width);
        }
    },
    /**
     *  初始化组件,
     *
     *  @for _$.FormItem
     *  @method _init
     */
    _init:function(){
        var _this = this;
        if(isNotEmpty(_this.value)) {
            _this.setValue(_this.value);
        }else{
            var initValue= _this.options["initValue"];
            if(isNotEmpty(initValue)){
                _this.setValue(initValue);
                _this.orgiValue = initValue;
            }
        }

        _this._loadSuccess();
    },
    /**
     *  表单name属性
     *
     *  @for _$.FormItem
     *  @property name
     * @type String
     */
    /**
     *  表单initValue默认值
     *
     *  @for _$.FormItem
     *  @property initValue
     * @type {Object}
     */
    /**
     *  表单中文说明
     *
     *  @for _$.FormItem
     *  @property nameCn
     * @type {String}
     */
    /**
     *  是否必填
     *
     *  @for _$.FormItem
     *  @property required
     * @type {String}
     */
    /**
     *  必填验证提示消息
     *
     *  @for _$.FormItem
     *  @property required-msg
     * @type {String}
     */
    /**
     *  表单模式 1 编辑模式,2 展示模式
     *
     *  @for _$.FormItem
     *  @property  model
     * @type {Number}
     */
    /**
     *  表单验证条件,参照_$.Validate
     *
     *  @for _$.FormItem
     *  @property  validRules
     * @type {JSON}
     */
    /**
     *  表单setValue格式化方法
     *
     *  @for _$.FormItem
     *  @property  setValueFormat
     * @type {Function}
     */
    /**
     *  监控change事件
     *
     *  @for _$.FormItem
     *  @property  onchange
     * @type {Function}
     */
    /**
     *  监控change事件
     *
     *  @for _$.FormItem
     *  @event onchange
     * @param {Event} e 事件对象
     */

    _attrProps:function(){
        var _this = this;
        var properties = _$.FormItem.superclass._attrProps.call(_this);
        properties.addAll(['name',"onchange",'initValue','nameCn',"required-msg","qtype",
            {'required':'boolean','immediately':'boolean',model: 'number',validRules:'json',userData:'json',"setValueFormat":"function","returnValue":"boolean"}]);
        return properties;
    },
    _attrOpts:function(){
        var _this = this;
        var opts = _$.FormItem.superclass._attrOpts.call(_this);
        return $.extend(true,opts,{
            model:1,
            returnValue:true,
            immediately:false,
            "setValueFormat":function(data,value){
                return value;
            }
        });
    },
    /**
     *  判断组件是否编辑模式,
     *
     *  @for _$.FormItem
     *  @method _isEdit
     */
    _isEdit:function(){
        var _this = this;
        return _this.options.model == _EDITABLE_;
    },
    /**
     *  从组件中获取现取值,
     *
     *  @for _$.FormItem
     *  @method _sync
     */
    _sync:function(){
        var _this = this;
        _this.value =  _this.component.val();
    },
    /**
     *  校验组件,
     *
     *  @for _$.FormItem
     *  @method _validateItem
     */
    _validateItem:function(rule){
        var _this = this;

        var value = _this.getValue();
        var nameCn = _this.getOption("nameCn");
        var id = _this.id;
        var name = _this.getOption("name");


        return  _$.validator.execute(id,name,value,nameCn,rule);
    },
    /**
     *  展示错误信息,
     *
     *  @for _$.FormItem
     *  @method _showErrMsg
     */
    _showErrMsg:function(){
        var _this = this;
        $(".form_item_valid",_this.el).html( _this.errMsg.join(","));
        $("#validDiv").append( _this.errMsg.join(","));
        _this._elHeight();
    },
    /**
     *  渲染组件,
     *
     *  @for _$.FormItem
     *  @method _replaceNode
     * @param {String} temp 组件html
     */
    _replaceNode:function(temp){
        var _this = this;
        var source='<div id="{{id}}" >'+ temp +'<div class="form_item_valid"></div><div class="form_item_memo">{{remark}}</div><div style="clear:both;"></div></div>';

        if(_this._isEdit()){
            source='<div id="{{id}}" >'+ temp +'<div class="form_item_valid"></div><div class="form_item_memo">{{remark}}</div><div style="clear:both;"></div></div>';
        }else{
            source='<div id="{{id}}" >'+ temp +'</div>';
        }
        var template = Handlebars.compile(source);
        var result = template(_this.options);
        var element = $(result);
        _this.el.replaceWith(element);
        _this.el = element;
        _this.el.addClass(_this._uiCls);
        _this.el.addClass("FormItem");
        _this.el.attr("componentId",_this.uid);
    },
    /**
     *  组件返回默认值,
     *
     *  @for _$.FormItem
     *  @method reset
     */
    reset:function(){
        var _this = this;
        _this.setValue(_this.orgiValue);
    },
    /**
     *  组件设置值,
     *
     *  @for _$.FormItem
     *  @method setValue
     * @param {Object} value 组件设置值
     * @param {Boolean} change 是否触发onchange事件
     */
    setValue:function(value,change) {
        var _this = this;
        if(isEmpty(change)){
            change = true;
        }
        var _this = this;
        if(isEmpty(value)){
            _this.value = null;

            if (_this._isEdit()) {
                _this.component.val("");
            } else {
                _this.component.html("");
            }
        }else{
            _this.value = value;
            if (_this._isEdit()) {
                _this.component.val(value);
            } else {
                _this.component.html(value);
            }
        }

        if(change){
            var event = {};
            event.value = _this.value;
            _this._fire("onchange",event);
        }
    },
    /**
     * 获取设置值,
     *
     *  @for _$.FormItem
     *  @method getValue
     */
    getValue:function(){
        var _this = this;
        return _this.value;
    },
    /**
     * 组件设置校验状态,
     *
     *  @for _$.FormItem
     *  @method setValid
     */
    setValid:function(flag){
        var _this = this;
        _this.validState = flag;

        if(_this.validState){
            _this.errMsg=[];
        }
        this._showErrMsg();
    },
    /**
     * 组件校验,
     *
     *  @for _$.FormItem
     *  @method validate
     */
    validate:function (rules){
        var _this = this;
        var dtd=$.Deferred();
        if(isNotEmpty(rules) && rules.length >= 0){
            var arr=[];
            rules.each(function(rule){
                arr.push(_this._validateItem(rule))
            });
            $.when.apply(this,arr)
                .done(function(){
                    var v_flag = true;
                    var errMsg = [];
                    for (var i = 0; i < arguments.length; i++) {
                        var res =  arguments[i];
                        var state = res["state"];
                        var info = res["info"];

                        if(v_flag && !state){
                            v_flag = state;
                            errMsg.add(info);
                        }
                    }
                    var result ={
                        "flag": v_flag,
                        "errMsg": errMsg
                    };
                    dtd.resolve(result);
                })
                .fail(function(err){
                    dtd.reject(err)
                });
        }else{
            setTimeout(function () {
                var result ={
                    "flag": true,
                    "errMsg": []
                };
                dtd.resolve(result);
            },50)
        }
        return dtd.promise();
    },
    /**
     * 组件校验,
     *
     *  @for _$.FormItem
     *  @method isValid
     */
    isValid:function(hiddenFlag){
        var _this = this;
        var dtd=$.Deferred();
        if(!_this._isEdit() || !hiddenFlag  &&  !_this.el.is(":visible")){
            return true;
        }
        var validRules =_this.getOption("validRules");
        var required = _this.getOption("required");

        if(validRules){
            var rules = [];
            if(required == true){
                var required_rule = {};
                required_rule["type"] = "required";
                required_rule["value"] = validRules["required"];
                required_rule["msg"] = validRules["required-msg"];
                rules.add(required_rule);
            }


            for(var key in validRules){
                if(!key.endWith("msg")){
                    var rule = {};
                    rule["type"] = key;
                    rule["value"] = validRules[key];
                    rule["msg"] = validRules[key + "-msg"];

                    rules.add(rule);
                }
            }

            $.when(_this.validate(rules))
            .done(function(res){
                var flag = res.flag;
                _this.errMsg = res.errMsg;
                _this.setValid(flag);
                dtd.resolve(flag);
            })
            .fail(function(err){
                dtd.reject(err)
            });

        }else{
            setTimeout(function () {
                dtd.resolve(true);
            },50)
        }
        return dtd.promise();
    },
    /**
     * 组件获取修改后的值,
     *
     *  @for _$.FormItem
     *  @method getEditValue
     */
    getEditValue:function(values){
        var _this = this;
        var orgiValue =  _this.orgiValue;
        var current = _this.getValue();
        var key =  _this.id;
        if(isEmpty(orgiValue) && isNotEmpty(current)){
            values[key] = current;
        }else if(isNotEmpty(orgiValue) && isEmpty(current)){
            values[key] = null;
        }else if(isNotEmpty(orgiValue) && isNotEmpty(current) && (orgiValue != current)){
            values[key] = current;
        }
    },
    /**
     * 组件设置模式,
     *
     *  @for _$.FormItem
     *  @method setModel
     */
    setModel:function(model){
        var _this = this;
        if(_this.options.model == _SHOW_ && model == _EDITABLE_){
            _this._editModel();
        }else if(_this.options.model == _EDITABLE_ &&  model == _SHOW_){
            _this._showModel();
        }
    },
    /**
     * 为控件绑定事件
     *
     *  @for _$.FormItem
     *  @method _bindChangeEvents
     */
    _bindEvents:function(){
        var _this = this;
        _this._bindChangeEvents();
    },
    /**
     * 组件绑定onchange事件
     *
     *  @for _$.FormItem
     *  @method _bindChangeEvents
     */
    _bindChangeEvents:function () {
        var _this = this;
        _$.FormItem.superclass._getEvents.call(_this, ["onchange"]);
    },
    destroy : function() {
        var _this = this;
        if(_this._isEdit()){
            _this._clearEdit();
        }else{
            _this._clearShow();
        }
        _$.FormItem.superclass.destroy.call(_this);
    }
});

_$.regPlugins(["TextInput","TextArea","Hidden"]);
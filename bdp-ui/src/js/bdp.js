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
    regPlugins:function(plugins,resources){
       _$.parser.plugins=plugins;
       _$.parser.resources=resources;
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
        if (!func){
            func = function () {
            }
        }
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
        var jsObj = $.ajax({
                url:url,
                async: false
            });
        var jsStr = jsObj.responseText;
        eval(jsStr);
        _this.jsLoaded[key] = _this._SUCCESES_;
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
            var dataOptions = $.trim(target.attr('data-options'));
            if (isNotEmpty(dataOptions)){
                if (dataOptions.substring(0, 1) != '{'){
                    dataOptions = '{' + dataOptions + '}';
                }
                dataOptions = (new Function('return ' + dataOptions))();
                $.extend(true,options,dataOptions)
            }

            $.map(['width','height'], function(p){
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

            var validRules = $.trim(target.attr('validRules'));
            if (isNotEmpty(validRules)){
                if (validRules.substring(0, 1) != '{'){
                    validRules = '{' + validRules + '}';
                }
                validRules = (new Function('return ' + validRules))();
                options["validRules"]=validRules;
            }else{
                options["validRules"]={};
            }
            var vtype = $.trim(target.attr('vtype'));
            if(isNotEmpty(vtype)){
                var validRules = isEmpty(options["validRules"])?{}:options["validRules"];

                if(vtype.indexOf(";") == -1 && vtype.indexOf(":") == -1){
                    validRules[vtype] = true;
                }else {
                    var arr = vtype.split(";");
                    if(arr != null && arr.length>0){
                        for (var i = 0; i < arr.length; i++) {
                            var item = arr[i];
                            if(isNotEmpty(item)){
                                if(item.indexOf(":") == -1) {
                                    validRules[item] = true;
                                }else {
                                    var validItem = '{' + item + '}';
                                    validItem = (new Function('return ' + validItem))();
                                    $.extend(true,validRules,validItem);
                                }
                            }
                        }
                    }
                }
                options["validRules"]=validRules;
            }

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
        resources:{},
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

                            var path = _$.parser.resources[componentName];
                            if(isEmpty(path)){
                                path = _$.basePath;
                            }

                            res[componentName+"Js"]=path + "js/"+componentName+".js";
                            _$._loadCssAndJs(res);
                            Func =  _$.getClass(componentName);
                            if(Func != null && Func.superclass != null) {
                                new Func(target,processKey);
                            }
                        })(componentName)
                    }
                });
            }
        }
    }
});


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

_$.regPlugins(["TextInput","TextArea","Hidden","Combo","ComboSearch"],{

});
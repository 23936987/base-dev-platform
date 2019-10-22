(function (window) {
    'use strict';
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
    _$._loadCssAndJs({
        "ComponentJs":_$.basePath + "js/Component.js"
    });
    window._EDITABLE_ = 1;
    window._SHOW_ = 0;
    _$.FormItem = function(target,processKey){
        var _this = this;
        _$.FormItem.superclass.constructor.call(_this,target,processKey);
    };

    _$.regClass("FormItem",_$.FormItem);

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
                {'required':'boolean','immediately':'boolean',model: 'number',userData:'json',"setValueFormat":"function","returnValue":"boolean","changeOnInit":"boolean","index":"number"}]);
            return properties;
        },
        _attrOpts:function(){
            var _this = this;
            var opts = _$.FormItem.superclass._attrOpts.call(_this);
            return $.extend(true,opts,{
                model:1,
                returnValue:true,
                changeOnInit:false,
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
            $("#validDiv").append(_this.errMsg.join(",") + "," );
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
                this.fireOnChange();
            }
        },
        /**
         *
         * 触发onchange事件
         */
        fireOnChange:function(){
            var _this = this;


            var immediately = _this.getOption("immediately");
            if(immediately){
                _this.isValid(false);
            }

            var changeOnInit = _this.getOption("changeOnInit");

            if (this.isLoaded() || changeOnInit) {
                var event = {};
                event.value = this.getValue();
                _this._fire("onchange", event);
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
                _this.component.removeClass("validClass");
            }else{
                _this.component.addClass("validClass");
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

                            if(!state){
                                v_flag = state;
                                if(isNotEmpty(info)){
                                    errMsg.add(info);
                                }
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
})(window);
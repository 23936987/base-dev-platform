/**
 * 文本输入框组件
 *
 * @class Text
 * @constructor
 * @submodule form
 * @extends _$.FormItem
 * @param {String,nodeType,jquery} target 选择器
 * @param {String} processKey 解析器key
 * @namespace _$
 */
_$.Text=function (target,processKey){
    var _this = this;
    _$.Text.superclass.constructor.call(_this,target,processKey);
};
_$.regClass("Text",_$.Text);

_$.extend(_$.Text,_$.FormItem, {
    _cls:_$._clsPre + "Text",
    _uiCls:_$._uiPreCls + "Text",
    _attrProps:function(){
        var _this = this;
        var properties = _$.Text.superclass._attrProps.call(_this);
        properties.addAll(["onfocus","oninput","onblur","inputStyle","type"]);
        return properties;
    },
    _attrOpts:function(){
        var _this = this;
        var opts = _$.Text.superclass._attrOpts.call(_this);
        return $.extend(true,opts,{
            type:"text"
        });
    },

    _bindEvents:function(){
        var _this = this;
        _$.Text.superclass._bindEvents.call(_this);
        _$.Text.superclass._getEvents.call(_this, ["onfocus","oninput","onblur"]);
        if(_this._isEdit()){
            var event = {
            };
            _this.component.bind("focus",function(e){
                event['value'] = _this.getValue();
                _this._focusHandler(event);
            });
            _this.component.bind("input propertychange",function(){
                event['value'] = _this.getValue();
                _this._inputHandler(event);
            });
            _this.component.bind("blur",function(){
                event['value'] = _this.getValue();
                _this._blurHandler(event);
            });
        }
    },
    /**
     *  blur事件处理器
     *
     *  @for _$.Text
     *  @method _blurHandler
     * @param {Object} event  事件对象
     */
    _blurHandler:function(event){
        var _this = this;
        var immediately = _this.getOption("immediately");
        if(immediately){
            _this.isValid(false);
        }

        _this._fire("onblur",event);
        _this._fire("onchange",event);
    },
    /**
     *  input事件处理器
     *
     *  @for _$.Text
     *  @method _inputHandler
     * @param {Object} event  事件对象
     */
    _inputHandler:function(event){
        var _this = this;
        _this._fire("oninput",event)
    },
    /**
     *  focus 事件处理器
     *
     *  @for _$.Text
     *  @method _focusHandler
     * @param {Object} event  事件对象
     */
    _focusHandler:function(event){
        var _this = this;
       _this._fire("onfocus",event)
   },
    _setStyle:function(){
        var _this = this;
        _$.Text.superclass._setStyle.call(_this);

        var height = _this.getOption("height");
        if(height){
            _this.component.height(height);
        }
        var inputStyle = _this.getOption('inputStyle');
        if(isNotEmpty(inputStyle)){
            _$.css(_this.component,inputStyle)
        }
    },
    getValue:function(){
        var _this = this;
        if(_this._isEdit()){
            _this._sync();
        }
        return _this.value;
    },
});

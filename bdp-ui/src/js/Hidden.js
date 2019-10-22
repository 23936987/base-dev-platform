(function (window) {
    'use strict';
    /**
     *  隐藏组件
     *
     *  @class Hidden
     * @constructor
     * @submodule form
     * @extends _$.Text
     * @param {String,nodeType,jquery} target 选择器
     * @param {String} processKey 解析器key
     * @namespace _$
     */
    _$._loadCssAndJs({
        "TextJs":_$.basePath + "js/Text.js",
        "HiddenCss":_$.basePath + "css/Hidden.css"
    });
    _$.Hidden=function (target,processKey){
        var _this = this;
        _$.Hidden.superclass.constructor.call(_this,target,processKey);
    };
    _$.regClass("Hidden",_$.Hidden);

    _$.extend(_$.Hidden,_$.Text, {
        _cls:_$._clsPre + "Hidden",
        _uiCls:_$._uiPreCls + "Hidden",
        _create :function(){
            var _this = this;
            var source='<input type="hidden" class="Hidden" />';
            _this._replaceNode(source);
            _this.el.attr("componentId",_this.uid);
            _this.component = $(".Hidden",_this.el);
            _this._init();
            _this.el.attr("formId",_this.id);
        },
        setValue:function(value) {
            var _this = this;
            if(isEmpty(value)){
                _this.value = null;
                _this.component.val("");
            }else{
                _this.value = value;
                _this.component.val(value);
            }
        },
        getValue:function(){
            var _this = this;
            _this._sync();
            return _this.value;
        }
    });
})(window);

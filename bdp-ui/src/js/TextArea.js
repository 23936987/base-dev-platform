(function (window) {
    'use strict';
    /**
     * 文本输入框组件
     *
     * @class TextArea
     * @constructor
     * @submodule form
     * @extends _$.Text
     * @param {String,nodeType,jquery} target 选择器
     * @param {String} processKey 解析器key
     * @namespace _$
     */
    _$._loadCssAndJs({
        "TextJs":_$.basePath + "js/Text.js",
        "TextAreaCss":_$.basePath + "css/TextArea.css"
    });
    _$.TextArea=function (target,processKey){
        var _this = this;
        _$.TextArea.superclass.constructor.call(_this,target,processKey);
    };
    _$.regClass("TextArea",_$.TextArea);

    _$.extend(_$.TextArea,_$.Text, {
        _cls:_$._clsPre + "TextArea",
        _uiCls:_$._uiPreCls + "TextArea",
        _editModel:function(){
            var _this = this;
            _this.setOption("model",_EDITABLE_);
            _this._clearShow();
            var source='<textarea  class="TextArea"></textarea>';
            _this._replaceNode(source);
            _this.component = $(".TextArea",_this.el);
            _this._setStyle();
            _this._bindEvents();
            _this._init();
        }
    });
})(window);

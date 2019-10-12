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
_$.TextArea=function (target,processKey){
    var _this = this;

    var job1 = setInterval(function () {
        if(_$.TextArea != null && _$.TextArea.superclass != null) {
            clearInterval(job1);
            _$.TextArea.superclass.constructor.call(_this,target,processKey);
        }
    },20)
};
_$.regClass("TextArea",_$.TextArea);

_$.extendLoad(_$.TextArea,"Text", {
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

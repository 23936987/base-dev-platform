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
    "TextInputCss":_$.basePath + "css/TextInput.css"
});
_$.TextInput=function (target,processKey){
    var _this = this;
    _$.TextInput.superclass.constructor.call(_this,target,processKey);
};
_$.regClass("TextInput",_$.TextInput);

_$.extend(_$.TextInput,_$.Text, {
    _cls:_$._clsPre + "TextInput",
    _uiCls:_$._uiPreCls + "TextInput",
      _editModel:function(){
          var _this = this;
          _this.setOption("model",_EDITABLE_);
          _this._clearShow();
          var source='<input type="{{type}}" class="TextInput"/>';
          _this._replaceNode(source);
          _this.component = $(".TextInput",_this.el);
          _this._setStyle();
          _this._bindEvents();
          _this._init();
      },
});

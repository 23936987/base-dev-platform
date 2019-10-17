/**
 * 下拉数据字典组件
 *
 * @class ComboData
 * @constructor
 * @submodule form
 * @extends _$.ComboBox
 * @param {String,nodeType,jquery} target 选择器
 * @param {String} processKey 解析器key
 * @namespace _$
 */

_$._loadCssAndJs({
    "ComboBoxJs":_$.basePath + "js/ComboBox.js",
    "ComboDataCss":_$.basePath + "css/ComboData.css"
});

_$.ComboData=function (target,processKey){
    var _this = this;
    _$.ComboData.superclass.constructor.call(_this,target,processKey);
};
_$.regClass("ComboData",_$.ComboData);

_$.extend(_$.ComboData,_$.ComboBox, {
    _cls:_$._clsPre + "ComboData",
    _uiCls:_$._uiPreCls + "ComboData",
    dataList:[],
    /**
     *   本地数据源,格式为一个对象数组
     *
     *  @for _$.ComboData
     *  @property data
     * @type object
     */
    _attrProps:function(){
        var _this = this;
        var properties = _$.ComboData.superclass._attrProps.call(_this);
        properties.addAll([{"data":"object"}]);
        return properties;
    },
    _attrOpts:function(){
        var _this = this;
        var opts = _$.ComboData.superclass._attrOpts.call(_this);
        return $.extend(true,opts,{ 
            'data':{}
        });
    },
    _initShow:function(value){
        var _this = this;
        var list = _this.getOption("data");
        _this.data = list;
        var initValue= _this.options["initValue"];
        if(isNotEmpty(_this.value)){
            _this.setValue(_this.value);
        }else{
            _this.setValue(initValue);
        }
        _this._loadSuccess();
    },
    _init:function(){
        var _this = this;
        var list = _this.getOption("data");
        var dataFilter = _this.getOption("dataFilter");
        list = dataFilter.call(window, list);
        _this.data = list;
        if(_this._isEdit()){
            _this._setData(list);
        }
        var initValue= _this.options["initValue"];
        if(isNotEmpty(_this.value)){
            _this.setValue(_this.value);
        }else if(isNotEmpty(initValue)){
            _this.setValue(initValue);
            if(isEmpty( _this.orgiValue)){
                _this.orgiValue = initValue;
            }
        }
        _this._loadSuccess();
    }
});

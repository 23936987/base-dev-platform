(function (window) {
    'use strict';
    /**
     * 下拉列表组件
     *
     * @class ComboPagerDynamic
     * @constructor
     * @submodule form
     * @extends _$.ComboPager
     * @param {String,nodeType,jquery} target 选择器
     * @param {String} processKey 解析器key
     * @namespace _$
     */
    _$.ComboPagerDynamic=function (target,processKey){
        var _this = this;
        _$.ComboPagerDynamic.superclass.constructor.call(_this,target,processKey);
    };
    _$.regClass("ComboPagerDynamic",_$.ComboPagerDynamic);

    _$.extend(_$.ComboPagerDynamic,_$.ComboPager,[_$.LoadPager],{
        _cls:_$._clsPre + "ComboPagerDynamic",
        _uiCls:_$._uiPreCls + "ComboPagerDynamic",
        _attrProps:function(){
            var _this = this;
            var properties = [];
            var superProperties = _$.ComboPagerDynamic.superclass._attrProps.call(_this);
            var ajaxProperties = _$.ComboPagerDynamic.loadPager._attrProps.call(_this);
            properties.addAll(superProperties);
            properties.addAll(ajaxProperties);
            return properties;
        },
        _attrOpts:function(){
            var _this = this;
            var opts = _$.ComboPagerDynamic.superclass._attrOpts.call(_this);
            var ajaxOpts = _$.ComboPagerDynamic.loadPager._attrOpts.call(_this);

            return $.extend(true,opts,ajaxOpts,{
            });
        },
    });

})(window);

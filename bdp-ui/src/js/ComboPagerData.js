(function (window) {
    'use strict';
    /**
     * 下拉列表组件
     *
     * @class ComboPagerData
     * @constructor
     * @submodule form
     * @extends _$.ComboPager
     * @param {String,nodeType,jquery} target 选择器
     * @param {String} processKey 解析器key
     * @namespace _$
     */

    _$._loadCssAndJs({
        "ComboPagerJs":_$.basePath + "js/ComboPager.js",
        "ComboPagerDataCss":_$.basePath + "css/ComboPagerData.css"
    });

    _$.ComboPagerData=function (target,processKey){
        var _this = this;
        _$.ComboPagerData.superclass.constructor.call(_this,target,processKey);
    };
    _$.regClass("ComboPagerData",_$.ComboPagerData);

    _$.extend(_$.ComboPagerData,_$.ComboPager, {
        _cls:_$._clsPre + "ComboPagerData",
        _uiCls:_$._uiPreCls + "ComboPagerData",
        _attrProps:function(){
            var _this = this;
            var properties = _$.ComboPagerData.superclass._attrProps.call(_this);
            properties.addAll([{"pageSize":"number","data":"object"}]);
            return properties;
        },
        _attrOpts:function(){
            var _this = this;
            var opts = _$.ComboPagerData.superclass._attrOpts.call(_this);
            return $.extend(true,opts,{
                "pageSize":10,
                "data":{}
            });
        },
        _init:function () {
            var _this = this;
            var list = _this.getOption("data");
            var dataFilter = _this.getOption("dataFilter");
            list = dataFilter.call(window, list);
            _this.total = list.length;
            _this.dataList = list;
            if(_this._isEdit()){
                _this._goto(1);
            }else{
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
            }
            _this._loadSuccess();
        },
        _loadData:function (func) {
            var _this = this;
            var pageSize = _this.getOption("pageSize");
            _this.totalPage = (_this.total % pageSize == 0) ? (_this.total / pageSize) : Math.floor(_this.total / pageSize) + 1;

            var start = (_this.pageNum -1) * pageSize;
            var end = start + pageSize;
            end = end < _this.total ? end : _this.total;

            var list  = _this.dataList.slice(start,end);
            _this.data = list;
            func.call(_this, list);
        },
        _loadDataHandler:function (list) {
            var _this = this;
            _this._setData(list);
            _this._reflashBottom();
            var idField = _this.getOption("idField");
            var textField = _this.getOption("textField");
            var selectFist = _this.options["selectFist"];

            if (isNotEmpty(_this.value)) {
                _this.setValue(_this.value);
            } else if (selectFist) {

                var values = {};
                var item = _this.data[0];
                if(isNotEmpty(item)){
                    values["ids"] = item[idField];
                    values["names"] = item[textField];
                }
                _this.setValue(values);
            } else {
                _this.setValue(null);
            }
        },
        setValue:function (value,change) {

            var _this = this;
            if (isEmpty(change)) {
                change = true;
            }
            var multiple = _this.getOption("multiple");
            var idField = _this.getOption("idField");
            var textField = _this.getOption("textField");

            if (isEmpty(value)) {
                _this.value = null;
                if (_this._isEdit()) {
                    _this.combo_input.html("");
                } else {
                    _this.component.html("");
                }
            }  else {

                _this.value = value;

                if (!multiple) {
                    var name =  null;
                    for (var i = 0; i < _this.dataList.length; i++) {
                        var item = _this.dataList[i];
                        if(item[idField] == value) {
                            name = item[textField];
                            break;
                        }
                    }

                    if (_this._isEdit()) {
                        if(isNotEmpty(name) && isNotEmpty(name)){
                            _this.combo_input.html(name);
                        }else{
                            _this.combo_input.html("");
                        }
                        _this._singleHoverSelect(value);
                    }else{
                        if(isNotEmpty(name) && isNotEmpty(name)){
                            _this.component.html(name);
                        }else{
                            _this.component.html("");
                        }
                    }
                }else{
                    var  ids = value.split(",")
                    var names = [];
                    for (var i = 0; i < _this.dataList.length; i++) {
                        var item = _this.dataList[i];
                        for (var j = 0; j < ids.length; j++) {
                            if(item[idField] == ids[j]) {
                                names.push(item[textField]);
                                break;
                            }
                        }
                    }
                    if (_this._isEdit()) {
                        if (isNotEmpty(names) && isNotEmpty(ids)) {
                            _this.combo_input.html(names.join(","));
                        } else {
                            _this.combo_input.html("");
                        }
                        _this._mutiHoverSelect(ids);
                    }else{
                        if (isNotEmpty(names) && isNotEmpty(ids)) {
                            _this.combo_input.html(names.join(","));
                        } else {
                            _this.combo_input.html("");
                        }
                    }
                }
            }
            if (change) {
                _this.fireOnChange()
            }
        },
        getValue:function(){
            var _this = this;
            return _this.value;
        },
        _mutiItemSelect:function (val) {
            var _this = this;
            var idField = _this.getOption("idField");
            var textField = _this.getOption("textField");
            var name=null;

            var ids = [];
            if(isEmpty(_this.value)){
                ids = [];
            }else{
                ids = _this.value.split(",");
            }

            if(ids.indexOf(val) == -1){
                ids.add(val);
            }else{
                ids.remove(val);
            }

            _this.setValue(ids.join(","));
        },
        _singleItemSelect:function (val) {
            var _this = this;
            var idField = _this.getOption("idField");
            _this.data.each(function (item) {
                if(item[idField] == val){
                    _this.setValue(val);
                }
            });
        },
        _mutiSelectMoreItems:function (id,ids,names) {
            var _this = this;
            var idx = ids.indexOf(id);
            ids.removeAt(idx);
            _this.setValue(ids.join(","));
        },
        _moreItems:function () {
            var _this = this;
            var multiple = _this.getOption("multiple");
            var idField = _this.getOption("idField");
            var textField = _this.getOption("textField");
            if(multiple){
                if(isNotEmpty(_this.value)){
                    var  ids = _this.value.split(",");
                    var names = [];
                    for (var i = 0; i < _this.dataList.length; i++) {
                        var item = _this.dataList[i];
                        for (var j = 0; j < ids.length; j++) {
                            if(item[idField] == ids[j]) {
                                names.push(item[textField]);
                                break;
                            }
                        }
                    }
                    _this._showMoreItems(ids,names);
                }
            }
        },
        _mutiHoverSelect:function (ids) {
            var _this = this;
            var $combo_pager_items = $("." + _this._COMBO_PAGER_ITEM_CLASS_, _this.combo_pager_area);
            $combo_pager_items.removeClass(_this._COMBO_PAGER_ITEM_HOVER_CLASS_);
            $combo_pager_items.removeClass(_this._COMBO_PAGER_ITEM_SELECT_CLASS_);
            var idField = _this.getOption("idField");
            var _isSelect=false;
            if(isNotEmpty(ids)){
                if(ids != null && ids.length>0){
                    ids.each(function (id) {
                        _this.data.each(function (item,index) {
                            if(item[idField] == id){
                                if(!_isSelect){
                                    _isSelect = true;
                                }
                                _this.index = index;
                                var $combo_pager_item = $combo_pager_items.eq(index);
                                $combo_pager_item.addClass(_this._COMBO_PAGER_ITEM_SELECT_CLASS_);
                            }
                        })
                    })
                }
            }

            if(!_isSelect){
                _this._hightLightRow(0);
            }
        },
        _singleHoverSelect:function (ids) {
            var _this = this;
            var idField = _this.getOption("idField");
            var flag = _this.data.some(function (item,index) {
                if(item[idField] == ids){
                    _this.index = index;
                    return true;
                }
                return false;
            });
            if(flag){
                var $combo_pager_items = $("." + _this._COMBO_PAGER_ITEM_CLASS_, _this.combo_pager_area);
                $combo_pager_items.removeClass(_this._COMBO_PAGER_ITEM_HOVER_CLASS_);

                var $combo_pager_item = $combo_pager_items.eq(_this.index);
                $combo_pager_item.addClass(_this._COMBO_PAGER_ITEM_SELECT_CLASS_).siblings().removeClass(_this._COMBO_PAGER_ITEM_SELECT_CLASS_);
            }else{
                _this._hightLightRow(0);
            }
        },
    });
})(window);
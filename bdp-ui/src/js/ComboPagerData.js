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
});

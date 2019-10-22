(function (window) {
    'use strict';
    _$.LoadAjax = function () {
    };
    _$.LoadAjax.interfaceName="loadAjax";


    _$.LoadAjax.prototype={
        _attrProps:function(){
            var properties =["url",'type','parentIds', 'parentKeys', "resultKey","idField","textField",
                {"params": "object", "beforeSend": "function","dataFilter":"function"}];
            return properties;
        },
        _attrOpts:function(){
            var opts = {
                "idField":"id",
                'type':'GET',
                "textField":"name",
                'parentIds': '',
                'parentKeys': '',
                "resultKey": "rows",
                "beforeSend": function (obj) {
                    return {};
                },
                "dataFilter":function (list) {
                    return list;
                }
            };
            return opts;
        },
        _init: function () {
            var _this = this;
            //绑定其它控件事件
            var callback;
            if (!_this._isEdit()) {
                callback = _this._showInit;
            } else {
                callback = _this._editInit;
            }

            var parentIds = _this.getOption("parentIds");
            if (isNotEmpty(parentIds)) {
                var parentIdsArr = parentIds.split(",");
                for (var i = 0; i < parentIdsArr.length; i++) {
                    var parentId = parentIdsArr[i];
                    var parentObj = _$.getById(parentId);
                    if (parentObj == null) {
                        logger("parentIds配置错误");
                        _this._loadSuccess();
                        break;
                    }
                    parentObj._on(_this.id + ".onchange", function (e) {
                        var parentValue = e.value;
                        if (parentValue == null) {
                            var list = [];
                            _this.data = list;
                            _this._setData(list);
                            _this.setValue(null);
                            return;
                        } else {
                            _this._loadData(callback);
                        }
                    });
                }
            }else{
                _this._loadData(callback);
            }
        },
        getAjaxInParams:function () {
            return {};
        },
        _loadData: function (func,searchParams) {
            var _this = this;
            var sendParams;
            var params = _this.getOption("params");
            var beforeSend = _this.getOption("beforeSend");
            var filterData = beforeSend.call(window, _this);

            var parentIds = _this.getOption("parentIds");
            if (params == null) {
                params = {};
            }
            var ajaxIn = _this.getAjaxInParams();
            var sendParams = $.extend(ajaxIn,params, filterData,searchParams);
            if (isEmpty(parentIds)) {
                _this.ajax(sendParams, func);
            } else {
                var parentIdsArr = parentIds.split(",");
                var isParentLoaded = function () {
                    return parentIdsArr.every(function (parentId) {
                        var parentObj = _$.getById(parentId);
                        if (parentObj != null && parentObj.isLoaded()) {
                            return true;
                        }
                        return false;
                    })
                };

                if (isParentLoaded()) {
                    _this._loadDataByParent(sendParams,func)
                }else{
                    var timer1 = setInterval(function () {
                        if (isParentLoaded()) {
                            clearInterval(timer1);
                            _this._loadDataByParent(sendParams,func)
                        }
                    }, 10);
                }
            }
        },
        _loadDataByParent:function(data,func){
            var _this = this;
            var parentIds = _this.getOption("parentIds");
            var parentKeys = _this.getOption("parentKeys");
            var parentIdsArr = parentIds.split(",");
            var parentKeysArr = parentKeys.split(",");

            var parentParams = {};
            for (var i = 0; i < parentIdsArr.length; i++) {
                var parentId = parentIdsArr[i];
                var parentKey = parentKeysArr[i];

                var parentObj = _$.getById(parentId);
                if (parentObj == null) {
                    _this._loadSuccessNoEvent();
                    return;
                }
                var parentValue = parentObj.getValue();
                if (isEmpty(parentValue)) {
                    _this._loadSuccessNoEvent();
                    return;
                }
                parentParams[parentKey] = parentValue;
            }

            var sendParams = $.extend(data, parentParams);
            _this.ajax(sendParams, func);
        },
        _clearArea:function () {
        },
        ajax: function (data, func) {
            var _this = this;
            var url = _this.getOption("url");
            var type = _this.getOption("type");

            if (_this._isEdit()) {
                _this._clearArea();
            }
            _this.data = [];
            $.ajax({
                type:type,
                url: url,
                data: data,
                success: function(result){
                    func.call(_this, result);
                }
            });
        },
        _editInit: function (result) {
            var _this = this;
            var errorCode = result["errorCode"];
            var errorMsg = result["errorMsg"];

            if (errorCode == '0') {
                var resultKey = _this.getOption("resultKey");
                var list = result.body[resultKey];
                var dataFilter = _this.getOption("dataFilter");
                list = dataFilter.call(window, list);
                _this.data = list;
                _this._setData(list);

                var initValue = _this.options["initValue"];
                var selectFist = _this.options["selectFist"];

                if(list != null && list.length>0){
                    if (isNotEmpty(_this.value)) {
                        _this.setValue(_this.value);
                    } else if (isNotEmpty(initValue)) {
                        _this.setValue(initValue);
                        if (isEmpty(_this.orgiValue)) {
                            _this.orgiValue = initValue;
                        }
                    }else if (selectFist) {
                        if (list != null && list.length > 0) {
                            var item = list[0];
                            var idField = _this.getOption("idField");
                            if (isNotEmpty(item)) {
                                var itemValue = item[idField];
                                _this.setValue(itemValue);
                            } else {
                                _this.setValue(null);
                            }
                        }
                    }
                }
                _this._loadSuccess();
            } else {
                logger(errorMsg);
            }
        },
        _showInit: function (result) {
            var _this = this;
            var errorCode = result["errorCode"];
            var errorMsg = result["errorMsg"];
            if (errorCode == '0') {
                var resultKey = _this.getOption("resultKey");
                var list = result.body[resultKey];
                var dataFilter = _this.getOption("dataFilter");
                list = dataFilter.call(window, list);
                _this.data = list;
                var initValue = _this.getOption("initValue");
                if (isNotEmpty(_this.value)) {
                    _this.setValue(_this.value);
                } else if (isNotEmpty(initValue)) {
                    _this.setValue(initValue);
                    if (isEmpty(_this.orgiValue)) {
                        _this.orgiValue = initValue;
                    }
                }
                _this._loadSuccess();
            } else {
                logger(info);
            }
        }
    };
})(window);
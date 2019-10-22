(function (window) {
    'use strict';
    /**
     * 下拉列表组件
     *
     * @class ComboBox
     * @constructor
     * @submodule form
     * @extends _$.ComboSearch
     * @param {String,nodeType,jquery} target 选择器
     * @param {String} processKey 解析器key
     * @namespace _$
     */

    _$._loadCssAndJs({
        "ComboSearchJs":_$.basePath + "js/ComboSearch.js",
        "ComboBoxCss":_$.basePath + "css/ComboBox.css"
    });

    _$.ComboBox = function (target, processKey) {
        var _this = this;
        _$.ComboBox.superclass.constructor.call(_this, target, processKey);
    };
    _$.regClass("ComboBox", _$.ComboBox);

    _$.extend(_$.ComboBox, _$.ComboSearch, {
        _cls: _$._clsPre + "ComboBox",
        _uiCls: _$._uiPreCls + "ComboBox",
        _COMBOBOX_ITEM_CLASS_: "combobox_item",
        _COMBOBOX_ITEM_SELECT_CLASS_: "combobox_item_selected",
        _COMBOBOX_ITEM_HOVER_CLASS_: "combobox_selected",
        /**
         *  返回值,值字段
         *
         *  @for _$.ComboBox
         *  @property idField
         * @type String
         */
        /**
         *  是否默认选中第一条
         *
         *  @for _$.ComboBox
         *  @property selectFist
         * @type boolean
         */
        /**
         *   数据对象过虑条件
         *  eg.
         *      var dataFilter=function (list) {
                return list;
            }
         *  @for _$.ComboBox
         *  @property dataFilter
         * @type function
         */
        _attrProps: function () {
            var _this = this;
            var properties = _$.ComboBox.superclass._attrProps.call(_this);
            properties.addAll(["idField", "textField",{"dataFilter": "function","selectFist": "boolean"}]);
            return properties;
        },
        _attrOpts: function () {
            var _this = this;
            var opts = _$.ComboBox.superclass._attrOpts.call(_this);
            return $.extend(true, opts, {
                "idField": "id",
                "textField": "name",
                "selectFist": false,
                "data":[],
                "dataFilter": function (list) {
                    return list;
                }
            });
        },
        _initPanel: function () {
            var _this = this;
            _$.ComboBox.superclass._initPanel.call(_this);

            var _html = '<div class="combo_box_area"></div>';
            _this.panel_content.append(_html);
            _this.combo_box_area = $(".combo_box_area",_this.panel_content);
        },
        _clearPanel: function () {
            var _this = this;
            $("." + _this._COMBOBOX_ITEM_CLASS_, _this.combo_box_area).unbind();
            $("." + _this._COMBOBOX_ITEM_CLASS_, _this.combo_box_area).remove();
            if(_this.combo_box_area){
                _this.combo_box_area.unbind();
                _this.combo_box_area.remove();
            }
            _$.ComboBox.superclass._clearPanel.call(_this);
        },
        _init: function () {
        },
        _searchByTxt: function (searchTxt) {
            var _this = this;
            var multiple = _this.getOption("multiple");
            var textField = _this.getOption("textField");
            var idField = _this.getOption("idField");

            var list = [];
            if (isEmpty(searchTxt)) {
                if (!multiple) {
                    list = _this.data;
                } else {
                    var values = (_this.value + "").split(",");
                    list = _this.data.filter(function (item, index) {
                        for (var i = 0; i < values.length; i++) {
                            if (item[idField] == values[i]) {
                                return false;
                            }
                        }
                        return true;
                    });
                }
            } else {
                if (!multiple) {
                    for (var i = 0; i < _this.data.length; i++) {
                        var item = _this.data[i];
                        if (item[textField].indexOf(searchTxt) != -1) {
                            list.push(item);
                        }
                    }
                } else {
                    var values = (_this.value + "").split(",");
                    var arr = _this.data.filter(function (item, index) {
                        for (var i = 0; i < values.length; i++) {
                            if (item[idField] == values[i]) {
                                return false;
                            }
                        }
                        return true;
                    });

                    if (arr != null && arr.length > 0) {
                        for (var i = 0; i < arr.length; i++) {
                            var item = arr[i];
                            if (item[textField].indexOf(searchTxt) != -1) {
                                list.push(item);
                            }
                        }
                    }
                }
            }
            _this._setData(list);
            if(isNotEmpty(_this.value)){
                var value = _this.value;
                $("." + _this._COMBOBOX_ITEM_CLASS_, _this.combo_box_area).each(function () {
                    $(this).removeClass(_this._COMBOBOX_ITEM_SELECT_CLASS_);
                });
                list.each(function (item, i) {
                    var id = item[idField];
                    var name = item[textField];

                    if (id == value) {
                        $("." + _this._COMBOBOX_ITEM_CLASS_ + "[data-value='" + value + "']", _this.combo_box_area).each(function () {
                            $(this).addClass(_this._COMBOBOX_ITEM_SELECT_CLASS_);
                        });
                        _this.combo_input.html(name);
                    }
                });
            }
        },
        _setData: function (list) {
            var _this = this;
            if (_this._isEdit()) {

                var multiple = _this.getOption("multiple");
                var idField = _this.getOption("idField");
                var textField = _this.getOption("textField");
                $("." + _this._COMBOBOX_ITEM_CLASS_, _this.combo_box_area).unbind();
                _this.combo_box_area.empty();

                if (list != null && list.size() > 0) {
                    list.each(function (item, i) {
                        var id = item[idField];
                        var name = item[textField];
                        var _html = '<p class="' + _this._COMBOBOX_ITEM_CLASS_ + '" data-value="' + id + '">' + name + '</p>';
                        _this.combo_box_area.append(_html);
                    });

                    var value = _this.value;
                    if (isEmpty(value) || multiple) {
                        _this.index = 0;
                        var $combobox_items = $("." + _this._COMBOBOX_ITEM_CLASS_, _this.combo_box_area);
                        var $combobox_item = $combobox_items.eq(_this.index);
                        $combobox_item.addClass(_this._COMBOBOX_ITEM_HOVER_CLASS_).siblings().removeClass(_this._COMBOBOX_ITEM_HOVER_CLASS_);
                    }

                    if (!multiple) {
                        _this._bindSingleItemEvents();
                    } else {
                        _this._bindMultiIetmEvents();
                    }
                }
            }
        },
        _setOffset: function () {
            var _this = this;
            var panelHeight = _this.getOption("panelHeight");
            var $combobox_items = $("." + _this._COMBOBOX_ITEM_CLASS_, _this.combo_box_area);
            var $combobox_item = $combobox_items.eq(_this.index);
            $combobox_item.addClass(_this._COMBOBOX_ITEM_HOVER_CLASS_).siblings().removeClass(_this._COMBOBOX_ITEM_HOVER_CLASS_);
            _this.combo_box_area.animate({scrollTop: (_this.pageIndex * panelHeight)}, 0);
        },
        _moreItems:function () {
            var _this = this;
            var multiple = _this.getOption("multiple");
            var idField = _this.getOption("idField");
            var textField = _this.getOption("textField");
            if(multiple && isNotEmpty(_this.value)){
                var values = (_this.value + "").split(",");
                var names = [];
                values.each(function (val) {
                    _this.data.each(function (item) {
                        if (val == item[idField]) {
                            names.add(item[textField]);
                        }
                    });
                });
                _this._showMoreItems(values,names);
            }
        },
        _mutiSelectMoreItems:function (id,ids,names) {
            var _this = this;
            ids.remove(id);
            _this.setValue(ids.join(","));
        },
        _bindPanelEvents: function () {
            var _this = this;
            _$.ComboBox.superclass._bindPanelEvents.call(_this);
            _this._keysEvent();
        },
        _keysEvent:function () {
            var _this = this;
            _this.index = -1;
            _this.panel.unbind('keydown').bind("keydown", function (event) {
                var $combobox_items = $("." + _this._COMBOBOX_ITEM_CLASS_, _this.combo_box_area);

                var multiple = _this.getOption("multiple");
                var panelHeight = _this.getOption("panelHeight");
                var itemHeight = 25;

                var total = $combobox_items.size();
                var pageSize = Math.floor(panelHeight / itemHeight);
                var totalPage = (total % pageSize == 0) ? (total / pageSize) : (Math.floor(total / pageSize) + 1);

                var code = event.keyCode;
                if (event.ctrlKey && totalPage > 1) {
                    if (code == 37) { //左
                        _this.pageIndex = 0;
                        _this.index = _this.pageIndex * pageSize;
                    } else if (code == 39) { //右
                        _this.pageIndex = totalPage - 1;
                        _this.index = _this.pageIndex * pageSize;
                    }
                } else {

                    if (code == 37) { //左
                        if (_this.pageIndex <= 0) {
                            _this.pageIndex = totalPage;
                        }
                        _this.pageIndex--;
                        _this.index = _this.pageIndex * pageSize;
                    } else if (code == 39) { //右
                        _this.pageIndex++;
                        if (_this.pageIndex >= totalPage) {
                            _this.pageIndex = 0;
                        }
                        _this.index = _this.pageIndex * pageSize;
                    } else if (code == 38) { //上
                        if (_this.index <= 0) {
                            _this.index = total;
                        }
                        _this.index--;
                        _this.pageIndex = (_this.index % pageSize == 0) ? (_this.index / pageSize) : (Math.floor(_this.index / pageSize));
                    } else if (code == 40) { //下
                        _this.index++;
                        if (_this.index >= total) {
                            _this.index = 0;
                        }
                        _this.pageIndex = (_this.index % pageSize == 0) ? (_this.index / pageSize) : (Math.floor(_this.index / pageSize));
                    }
                }
                _this._setOffset();
                if (code == 13) { //回车
                    var val = $combobox_items.eq(_this.index).attr("data-value");
                    if (!multiple) {
                        _this._singleItemSelect(val);
                        hideComboPanel(_this.id);
                    } else {
                        _this._mutiItemSelect(val);
                    }
                }
                event.stopPropagation();
            });
        },
        _singleItemSelect:function (val) {
            var _this = this;
            _this.setValue(val);
            hideComboPanel(_this.id);
        },
        _mutiItemSelect:function (val) {
            var _this = this;
            var value = _this.value;
            var values = [];
            if (isNotEmpty(value)) {
                values = (value + "").split(",");
            }

            if (values.indexOf(val) == -1) {
                values.add(val);
            } else {
                values.remove(val);
            }
            _this.setValue(values.join(","));
        },
        _bindSingleItemEvents: function () {
            var _this = this;
            $("." + _this._COMBOBOX_ITEM_CLASS_, _this.combo_box_area).unbind("click").bind("click", function () {
                var val = $(this).attr("data-value");
                _this._singleItemSelect(val);
                return false;
            });
        },
        _bindMultiIetmEvents: function () {
            var _this = this;
            $("." + _this._COMBOBOX_ITEM_CLASS_, _this.combo_box_area).unbind("click").bind("click", function () {
                var val = $(this).attr("data-value");
                _this._mutiItemSelect(val);
                return false;
            });
        },

        getData:function () {
            return this.data;
        },
        getSelect:function () {
            var _this =this;

            var idField = _this.getOption("idField");
            var textField = _this.getOption("textField");
            var multiple = _this.getOption("multiple");


            if(!multiple){
                //单选
                for(var i=0;i<_this.data.length;i++){
                    var row = _this.data[i];
                    if(row[idField] == _this.value){
                        return row;
                    }
                }
            }else {
                //多选
                var res = [];
                var arr = (_this.value + "").split(",");
                for(var i=0;i<_this.data.length;i++){
                    var row = _this.data[i];

                    for(var j=0;j<arr.length;j++){
                        var val = arr[j];

                        if(row[idField] == val){
                            res.add(row);
                        }
                    }
                }
                return res;
            }

        },
        setValue: function (value, change) {
            var _this = this;
            if (isEmpty(change)) {
                change = true;
            }
            var idField = _this.getOption("idField");
            var textField = _this.getOption("textField");
            var multiple = _this.getOption("multiple");

            if (isEmpty(value)) {
                _this.value = null;
                if (_this._isEdit()) {
                    _this.combo_input.html("");
                } else {
                    _this.component.html("");
                }
            } else if (isEmpty(_this.data) || _this.data.length == 0) {
                _this.value = value;
            } else {
                _this.value = value;
                if (_this._isEdit()) {
                    if (!multiple) {
                        $("." + _this._COMBOBOX_ITEM_CLASS_, _this.combo_box_area).each(function () {
                            $(this).removeClass(_this._COMBOBOX_ITEM_SELECT_CLASS_);
                        });


                        var flag = _this.data.some(function (item, i) {
                            var id = item[idField];
                            var name = item[textField];

                            if (id == value) {
                                $("." + _this._COMBOBOX_ITEM_CLASS_ + "[data-value='" + value + "']", _this.combo_box_area).each(function () {
                                    $(this).addClass(_this._COMBOBOX_ITEM_SELECT_CLASS_);
                                });
                                _this.combo_input.html(name);
                                return true;
                            }
                            return false;
                        });

                        if (!flag) {
                            _this.value = null;
                            _this.combo_input.html("");
                        }
                    } else {
                        var values = (value + "").split(",");
                        if (values != null && values.length > 0) {
                            var names = [];
                            for (var i = 0; i < values.length; i++) {
                                var itemValue = values[i];

                                _this.data.each(function (item, i) {
                                    var id = item[idField];
                                    var name = item[textField];
                                    if (id == itemValue) {
                                        names.add(name);
                                    }
                                });
                            }
                            if (names.length > 0) {
                                _this.combo_input.html(names.join(","));
                            } else {
                                _this.value = null;
                                _this.combo_input.html('');
                            }

                            var arr = _this.data.filter(function (item, index) {
                                for (var i = 0; i < values.length; i++) {
                                    if (item[idField] == values[i]) {
                                        return false;
                                    }
                                }
                                return true;
                            });
                            var list = [];
                            var searchTxt = _this.panel_search.val();
                            if (arr != null && arr.length > 0) {
                                for (var i = 0; i < arr.length; i++) {
                                    var item = arr[i];
                                    if (item[textField].indexOf(searchTxt) != -1) {
                                        list.push(item);
                                    }
                                }
                            }

                            _this._setData(list);
                        } else {
                            _this.value = null;
                            _this.combo_input.html('');
                        }
                    }
                } else {
                    if (!multiple) {
                        var flag = _this.data.some(function (item, i) {
                            var id = item[idField];
                            var name = item[textField];

                            if (id == value) {
                                _this.component.html(name);
                                return true;
                            }
                            return false;
                        });
                        if (!flag) {
                            _this.value = null;
                            _this.component.html("");
                        }
                    } else {
                        var values = (value + "").split(",");
                        if (values.length > 0) {
                            var names = [];
                            for (var i = 0; i < values.length; i++) {
                                var itemValue = values[i];

                                _this.data.each(function (item, i) {
                                    var id = item[idField];
                                    var name = item[textField];
                                    if (id == itemValue) {
                                        names.add(name);
                                    }
                                });
                            }
                            if (names.length > 0) {
                                _this.component.html(names.join(","));
                            } else {
                                _this.value = null;
                                _this.component.html('');
                            }
                        } else {
                            _this.value = null;
                            _this.component.html('');
                        }
                    }
                }
            }
            if(change){
                this.fireOnChange();
            }
        },
        getValue: function () {
            var _this = this;
            return _this.value;
        },
        _setPanelArea:function(width,height){
            var _this = this;
            _$.ComboBox.superclass._setPanelArea.call(_this,width,height);

            if(_this.combo_box_area){
                _this.combo_box_area.width(width);
                _this.combo_box_area.height(height);
            }
        }
    });

})(window);

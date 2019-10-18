/**
 * 下拉列表组件
 *
 * @class ComboUrl
 * @constructor
 * @submodule form
 * @extends _$.ComboBox
 * @param {String,nodeType,jquery} target 选择器
 * @param {String} processKey 解析器key
 * @namespace _$
 */

_$._loadCssAndJs({
    "ComboBoxJs":_$.basePath + "js/ComboBox.js",
    "LoadAjaxJs":_$.basePath + "js/LoadAjax.js",
    "ComboUrlCss":_$.basePath + "css/ComboUrl.css"
});

_$.ComboUrl = function (target, processKey) {
    var _this = this;
    _$.ComboUrl.superclass.constructor.call(_this, target, processKey);
};
_$.regClass("ComboUrl", _$.ComboUrl);

_$.extend(_$.ComboUrl, _$.ComboBox,[_$.LoadAjax], {
    _cls: _$._clsPre + "ComboUrl",
    _uiCls: _$._uiPreCls + "ComboUrl",

    /**
     *  访问web服务器
     *
     *  @for _$.ComboUrl
     *  @property basePath
     * @type string
     */

    /**
     *  访问路由,service名 + 方法名
     *     eg. user.save
     *  @for _$.ComboUrl
     *  @property call
     * @type string
     */
    /**
     *   联动父控件,在父控件onchange事件时初始化控件如 市联动省。
     *    多个参数以逗号隔开
     *  @for _$.ComboUrl
     *  @property parentIds
     * @type string
     */
    /**
     *   联动父控件,并向后台传递父控件的值,过滤,parentkeys为参数名称
     *    多个参数以逗号隔开
     *  @for _$.ComboUrl
     *  @property parentKeys
     * @type string
     */
    /**
     *   返回值map对象的key值
     *
     *  @for _$.ComboUrl
     *  @property resultKey
     * @type string
     */

    /**
     *   向后台查询列表时,默认参数。
     *   eg
     *    {
     *      "userId":10,    //用户id=1
     *      "age":{         //年龄大于30
     *         type:1
     *         value:30
     *    }
     *
     *  @for _$.ComboUrl
     *  @property params
     * @type object
     */

    /**
     *   向后台查询列表时,默认参数。
     *    返回参数格式如params一样
     *
     *  @for _$.ComboUrl
     *  @property beforeSend
     * @type function
     */
    _attrProps: function () {
        var _this = this;
        var properties = ['searchKey',{'searchMode':'number'}];
        var superProperties = _$.ComboUrl.superclass._attrProps.call(_this);
        var ajaxProperties = _$.ComboUrl.loadAjax._attrProps.call(_this);

        properties.addAll(superProperties);
        properties.addAll(ajaxProperties);
        return properties;
    },
    _attrOpts: function () {
        var _this = this;
        var opts = _$.ComboUrl.superclass._attrOpts.call(_this);
        var ajaxOpts = _$.ComboUrl.loadAjax._attrOpts.call(_this);

        return $.extend(true,opts,ajaxOpts,{
            "resultKey": "list",
            'searchMode':0,
            'searchKey':'name'
        });
    },
    _clearArea:function () {
        var _this = this;
        $("." + _this._COMBOBOX_ITEM_CLASS_, _this.combo_box_area).unbind();
        _this.combo_box_area.empty();
    },

    _bindPanelEvents: function () {
        var _this = this;
        var searchMode = _this.getOption("searchMode");
        if(searchMode == 0){
            _this.panel_search.bind("input",function(e){
                var searchTxt = $(this).val();
                _this._searchByTxt(searchTxt);
                return false;
            });

            _this.panel.unbind('keydown').bind("keydown", function (event) {
                var code = event.keyCode;
                if (code == 13) { //回车
                    var searchTxt = _this.panel_search.val();
                    _this._searchByTxt(searchTxt);
                    return false;
                }
            });
        }else if(searchMode == 1){
            _this.panel_search_icon.bind("click",function(e){
                var searchTxt = _this.panel_search.val();
                _this._searchByTxt(searchTxt);
                return false;
            });
        }
        _this._keysEvent();
    },
    _searchByTxt: function (searchTxt) {
        var _this = this;
        var multiple = _this.getOption("multiple");
        var textField = _this.getOption("textField");
        var idField = _this.getOption("idField");
        var searchMode = _this.getOption("searchMode");

        if(searchMode == 0) {
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
        }else if(searchMode == 1){
            var searchKey = _this.getOption("searchKey");

            if(isNotEmpty(searchKey)){
                var params = {};
                params[searchKey]= searchTxt;
                _this._loadData(_this._editInit,params);
            }
        }
    }
});

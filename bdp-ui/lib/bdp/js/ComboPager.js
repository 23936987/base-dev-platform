(function (window) {
    'use strict';
    /**
     * 下拉列表组件
     *
     * @class ComboPager
     * @constructor
     * @submodule form
     * @extends _$.ComboSearch
     * @param {String,nodeType,jquery} target 选择器
     * @param {String} processKey 解析器key
     * @namespace _$
     */

    _$._loadCssAndJs({
        "ComboSearchJs":_$.basePath + "js/ComboSearch.js",
        "ComboPager":_$.basePath + "css/ComboPager.css"
    });

    _$.ComboPager=function (target,processKey){
        var _this = this;
        _$.ComboPager.superclass.constructor.call(_this,target,processKey);
    };
    _$.regClass("ComboPager",_$.ComboPager);

    _$.extend(_$.ComboPager,_$.ComboSearch, {
        _cls:_$._clsPre + "ComboPager",
        _uiCls:_$._uiPreCls + "ComboPager",
        data:[],
        dataList:[],
        pageNum:1,
        total:0,
        totalPage:1,
        _COMBO_PAGER_ITEM_CLASS_: "combo_pager_item",
        _COMBO_PAGER_ITEM_SELECT_CLASS_: "combo_pager_item_selected",
        _COMBO_PAGER_ITEM_HOVER_CLASS_: "combo_pager_selected",
        _PAGER_IMGS_:{
            _FIRST_A_:_$.basePath + "/images/page_first_a.gif" ,
            _FIRST_B_:_$.basePath + "/images/page_first_b.gif" ,
            _PRE_A_:_$.basePath + "/images/page_pre_a.gif" ,
            _PRE_B_:_$.basePath + "/images/page_pre_b.gif" ,
            _NEXT_A_:_$.basePath + "/images/page_next_a.gif" ,
            _NEXT_B_:_$.basePath + "/images/page_next_b.gif" ,
            _LAST_A_:_$.basePath + "/images/page_last_a.gif" ,
            _LAST_B_:_$.basePath + "/images/page_last_b.gif" ,
        },
        _attrProps:function(){
            var _this = this;
            var properties = _$.ComboPager.superclass._attrProps.call(_this);
            properties.addAll(["idField", "textField",{"pageSize":"number","dataFilter":"function"}]);
            return properties;
        },
        _attrOpts:function(){
            var _this = this;
            var opts = _$.ComboPager.superclass._attrOpts.call(_this);
            return $.extend(true,opts,{
                "idField": "id",
                "textField": "name",
                "pageSize":10,
                "dataFilter": function (list) {
                    return list;
                }
            });
        },
        _initPanel:function () {
            var _this = this;
            _$.ComboPager.superclass._initPanel.call(_this);

            var _html = "<div class='combo_pager_area'></div><div class='combo_pager_area_bottom'></div>";
            _this.panel_content.append(_html);
            _this.combo_pager_area = $(".combo_pager_area",_this.panel_content);
            _this.combo_pager_area_bottom = $(".combo_pager_area_bottom",_this.panel_content);

            var _botton='<label class="combo_pager_pageNum"></label>' +
                '<img src="" class="combo_pager_first" />' +
                '<img src="" class="combo_pager_pre" />' +
                '<img src="" class="combo_pager_next" />' +
                '<img src="" class="combo_pager_last" />';
            _this.combo_pager_area_bottom.append(_botton);

            _this.combo_pager_pageNum =  $(".combo_pager_pageNum",_this.combo_pager_area_bottom);
            _this.combo_pager_first = $(".combo_pager_first",_this.combo_pager_area_bottom);
            _this.combo_pager_pre = $(".combo_pager_pre",_this.combo_pager_area_bottom);
            _this.combo_pager_next = $(".combo_pager_next",_this.combo_pager_area_bottom);
            _this.combo_pager_last = $(".combo_pager_last",_this.combo_pager_area_bottom);
            _this._reflashBottom();
            _this._bindBottonEvents();
        },
        _init:function () {
            var _this = this;
            _this._loadSuccess();
        },
        _first:function () {
            var _this = this;
            if(_this.pageNum>1){
                _this._goto(1)
            }
        },
        _pre:function () {
            var _this = this;
            var pageIndex = _this.pageNum;
            if(pageIndex>1){
                pageIndex--;
                _this._goto(pageIndex)
            }
        },
        _next:function () {
            var _this = this;
            var pageIndex = _this.pageNum;
            if(pageIndex < _this.totalPage){
                pageIndex++;
                _this._goto(pageIndex)
            }
        },
        _last:function () {
            var _this = this;
            if(_this.pageNum < _this.totalPage){
                _this._goto(_this.totalPage);
            }
        },
        _goto:function (pageIndex) {
            var _this = this;
            _this.pageNum = pageIndex;
            _this._loadData(_this._loadDataHandler);
        },
        _reflashBottom:function () {
            var _this = this;
            _this.combo_pager_pageNum.html(_this.pageNum + "/" + _this.totalPage);
            if(_this.pageNum == 1 || _this.totalPage == 1){
                _this.combo_pager_first.attr("src",_this._PAGER_IMGS_._FIRST_B_);
                _this.combo_pager_pre.attr("src",_this._PAGER_IMGS_._PRE_B_);
                _this.combo_pager_first.css({
                    "cursor":"default"
                });
                _this.combo_pager_pre.css({
                    "cursor":"default"
                });
            }else{
                _this.combo_pager_first.attr("src",_this._PAGER_IMGS_._FIRST_A_);
                _this.combo_pager_pre.attr("src",_this._PAGER_IMGS_._PRE_A_);
                _this.combo_pager_first.css({
                    "cursor":"pointer"
                });
                _this.combo_pager_pre.css({
                    "cursor":"pointer"
                });
            }
            if(_this.pageNum == _this.totalPage){
                _this.combo_pager_next.attr("src",_this._PAGER_IMGS_._NEXT_B_);
                _this.combo_pager_last.attr("src",_this._PAGER_IMGS_._LAST_B_);
                _this.combo_pager_next.css({
                    "cursor":"default"
                });
                _this.combo_pager_last.css({
                    "cursor":"default"
                });
            }else{
                _this.combo_pager_next.attr("src",_this._PAGER_IMGS_._NEXT_A_);
                _this.combo_pager_last.attr("src",_this._PAGER_IMGS_._LAST_A_);
                _this.combo_pager_next.css({
                    "cursor":"pointer"
                });
                _this.combo_pager_last.css({
                    "cursor":"pointer"
                });
            }

            var multiple = _this.getOption("multiple");
            if(_this.value){
                var ids = _this.value["ids"];
                var names = _this.value["names"];
                if (!multiple) {
                    _this._singleHoverSelect(ids);
                } else {
                    _this._mutiHoverSelect(ids);
                }
                if(isNotEmpty(names)){
                    _this.combo_input.html(names);
                }else{
                    _this.combo_input.html("");
                }
            }else{
                _this._hightLightRow(0);
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
                var idsArr = ids.split(",");
                if(idsArr != null && idsArr.length>0){
                    idsArr.each(function (id) {
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
        _hightLightRow:function (index) {
            var _this = this;
            _this.index = index;
            var $combo_pager_items = $("." + _this._COMBO_PAGER_ITEM_CLASS_, _this.combo_pager_area);
            var $combo_pager_item = $combo_pager_items.eq(_this.index);
            $combo_pager_item.addClass(_this._COMBO_PAGER_ITEM_HOVER_CLASS_).siblings().removeClass(_this._COMBO_PAGER_ITEM_HOVER_CLASS_);
        },
        _bindBottonEvents: function () {
            var _this = this;
            _this.combo_pager_first.click(function (e) {
                _this._first();
                return;
            });
            _this.combo_pager_pre.click(function (e) {
                _this._pre();
                return false;
            });
            _this.combo_pager_next.click(function (e) {
                _this._next();
                return false;
            });
            _this.combo_pager_last.click(function (e) {
                _this._last();
                return false;
            });
        },
        _loadDataHandler:function () {
        },
        _loadData:function () {
        },
        _setData:function (list) {
            var _this = this;
            if (_this._isEdit()) {
                var multiple = _this.getOption("multiple");
                var idField = _this.getOption("idField");
                var textField = _this.getOption("textField");

                $("." + _this._COMBO_PAGER_ITEM_CLASS_, _this.combo_pager_area).unbind();
                _this.combo_pager_area.empty();

                if (list != null && list.size() > 0) {
                    list.each(function (item, i) {
                        var id = item[idField];
                        var name = item[textField];
                        var _html = '<p class="' + _this._COMBO_PAGER_ITEM_CLASS_ + '" data-value="' + id + '">' + name + '</p>';
                        _this.combo_pager_area.append(_html);
                    });

                    if (!multiple) {
                        _this._bindSingleItemEvents();
                    } else {
                        _this._bindMultiIetmEvents();
                    }
                }
            }
        },
        _bindSingleItemEvents: function () {
            var _this = this;
            $("." + _this._COMBO_PAGER_ITEM_CLASS_, _this.combo_pager_area).unbind("click").bind("click", function () {
                var val = $(this).attr("data-value");
                _this._singleItemSelect(val);
                hideComboPanel(_this.id);
                return false;
            });
        },
        _singleItemSelect:function (val) {
        },
        _bindMultiIetmEvents: function () {
            var _this = this;
            $("." + _this._COMBO_PAGER_ITEM_CLASS_, _this.combo_pager_area).unbind("click").bind("click", function () {
                var val = $(this).attr("data-value");
                _this._mutiItemSelect(val);
                return false;
            });
        },
        _mutiItemSelect:function (val)  {
        },
        _setOffset: function () {
            var _this = this;
            var panelHeight = _this.getOption("panelHeight");
            var $combo_pager_items = $("." + _this._COMBO_PAGER_ITEM_CLASS_, _this.combo_pager_area);
            var $combo_pager_item = $combo_pager_items.eq(_this.index);
            $combo_pager_item.addClass(_this._COMBO_PAGER_ITEM_HOVER_CLASS_).siblings().removeClass(_this._COMBO_PAGER_ITEM_HOVER_CLASS_);
            _this.combo_pager_area.animate({scrollTop: (_this.pageIndex * panelHeight)}, 0);
        },
        _bindPanelEvents: function () {
            var _this = this;
            _this.index = -1;
            _this.panel.unbind('keydown').bind("keydown", function (e) {
                var $combo_pager_items = $("." + _this._COMBO_PAGER_ITEM_CLASS_, _this.combo_pager_area);
                var multiple = _this.getOption("multiple");
                var panelHeight = _this.getOption("panelHeight");
                var itemHeight = 25;

                var total = $combo_pager_items.size();
                var pageSize = Math.floor(panelHeight / itemHeight);
                var totalPage = (total % pageSize == 0) ? (total / pageSize) : (Math.floor(total / pageSize) + 1);

                var code = event.keyCode;
                if (e.ctrlKey && totalPage > 1) {
                    if (code == 37) { //左
                        _this._first();
                    } else if (code == 39) { //右
                        _this._last();
                    }
                } else {
                    if (code == 37) { //左
                        _this._pre();
                    } else if (code == 39) { //右
                        _this._next();
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
                    var val = $combo_pager_items.eq(_this.index).attr("data-value");
                    if (!multiple) {
                        _this._singleItemSelect(val);
                        hideComboPanel(_this.id);
                    } else {
                        _this._mutiItemSelect(val);
                    }
                }
                e.stopPropagation();
            });
        },
        _showPanel:function () {
            var _this = this;
            _this.panel_search.focus();
        },
        _mutiSelectMoreItems:function (id,ids,names) {
            var _this = this;
            var idx = ids.indexOf(id);
            ids.removeAt(idx);
            names.removeAt(idx);

            var values = {
                "ids":ids.join(","),
                "names":names.join(",")
            };
            _this.setValue(values);
        },
        _moreItems:function () {
            var _this = this;
            var multiple = _this.getOption("multiple");
            if(multiple){
                if(isNotEmpty(_this.value)){
                    var ids = _this.value["ids"];
                    var names = _this.value["names"]
                    if(isNotEmpty(ids)){
                        var idsArr = ids.split(",");
                        var namesArr = names.split(",");
                        _this._showMoreItems(idsArr,namesArr);
                    }
                }
            }
        },
        _setPanelArea:function(width,height){
            var _this = this;
            _$.ComboPager.superclass._setPanelArea.call(_this,width,height+30);
            if(_this.combo_pager_area){
                _this.combo_pager_area.width(width);
                _this.combo_pager_area.height(height);
            }
        }
    });

})(window);

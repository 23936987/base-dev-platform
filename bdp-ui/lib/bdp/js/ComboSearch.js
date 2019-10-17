/**
 * 下拉列表组件
 *
 * @class ComboSearch
 * @constructor
 * @submodule form
 * @extends _$.Combo
 * @param {String,nodeType,jquery} target 选择器
 * @param {String} processKey 解析器key
 * @namespace _$
 */

_$._loadCssAndJs({
    "ComboJs":_$.basePath + "js/Combo.js",
    "ComboSearchCss":_$.basePath + "css/ComboSearch.css"
});


_$.ComboSearch = function (target, processKey) {
    var _this = this;
    _$.ComboSearch.superclass.constructor.call(_this, target, processKey);
};
_$.regClass("ComboSearch", _$.ComboSearch);

_$.extend(_$.ComboSearch, _$.Combo, {
    _cls: _$._clsPre + "ComboSearch",
    _uiCls: _$._uiPreCls + "ComboSearch",
    _attrProps: function () {
        var _this = this;
        var properties = _$.ComboSearch.superclass._attrProps.call(_this);
        properties.addAll([]);
        return properties;
    },
    _attrOpts: function () {
        var _this = this;
        var opts = _$.ComboSearch.superclass._attrOpts.call(_this);
        return $.extend(true, opts, {
        });
    },
    _searchByTxt: function (searchTxt) {
    },
    _initPanel: function () {
        var _this = this;
        var _panelHtml =   '<div class="combo_search">'+
            '<input type="text" class="combo_search_input" placeholder="点击图标搜索"/>'+
            '<i class="combo_search_icon"></i>'+
            '</div>'+
            '<div class="combo_content"></div>'+
            '</div>';
        _this.panel.append(_panelHtml);
        _this.panel_search = $(".combo_search_input",_this.panel);
        _this.panel_search_icon = $(".combo_search_icon",_this.panel);
        _this.panel_content = $(".combo_content",_this.panel);
    },
    _clearPanel: function () {
        var _this = this;

        if(_this.panel_search){
            _this.panel_search.unbind();
            _this.panel_search.remove();
        }

        if(_this.panel_search){
            _this.panel_content.unbind();
            _this.panel_content.remove();
        }
        if(_this.panel) {
            _this.panel.unbind();
            _this.panel.remove();
        }
    },
    _bindPanelEvents: function () {
        var _this = this;
        _this.panel_search.bind("input",function(e){
            var searchTxt = $(this).val();
            _this._searchByTxt(searchTxt);
            return false;
        });
    },
    _showPanel:function () {
        var _this = this;
        _this.panel_search.focus();
        var searchText = _this.panel_search.val();
        if(searchText){
            _this._searchByTxt(searchText);
        }else{
            _this._searchByTxt("");
        }
    },
    _setPanelArea:function(width,height){
        var _this = this;
        _$.ComboSearch.superclass._setPanelArea.call(_this,width,height+25);

        if(_this.panel_content){
            _this.panel_content.width(width);
            _this.panel_content.height(height);
        }
    }
});

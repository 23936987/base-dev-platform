(function (window) {
    'use strict';
    /**
     * 下拉框组件
     *
     * @class Combo
     * @constructor
     * @submodule form
     * @extends _$.FormItem
     * @param {String,nodeType,jquery} target 选择器
     * @param {String} processKey 解析器key
     * @namespace _$
     */
    _$._loadCssAndJs({
        "FormItemJs":_$.basePath + "js/FormItem.js",
        "ComboCss":_$.basePath + "css/Combo.css"
    });

    _$.Combo=function (target,processKey){
        var _this = this;
        _$.Combo.superclass.constructor.call(_this,target,processKey);
    };
    _$.regClass("Combo",_$.Combo);

    _$.extend(_$.Combo,_$.FormItem, {
        _cls:_$._clsPre + "Combo",
        _uiCls:_$._uiPreCls + "Combo",
        data: [],
        index: 0,
        pageIndex: 0,
        /**
         *  是否多选 true 是 false 否
         *
         *  @for _$.Combo
         *  @property multiple
         * @type Boolean
         */
        /**
         *  下拉框宽度
         *
         *  @for _$.Combo
         *  @property panelWidth
         * @type number
         */
        /**
         *  下拉框高度
         *
         *  @for _$.Combo
         *  @property panelHeight
         * @type number
         */
        _attrProps:function(){
            var _this = this;
            var properties = _$.Combo.superclass._attrProps.call(_this);
            properties.addAll([{"multiple":"boolean","panelWidth":"number","panelHeight":"number"}]);
            return properties;
        },
        _attrOpts:function(){
            var _this = this;
            var opts = _$.Combo.superclass._attrOpts.call(_this);
            return $.extend(true,opts,{
                "multiple":false,
                "defaultText":"==请选择==",
                "speed":50
            });
        },
        _editModel:function(){
            var _this = this;
            var multiple = _this.getOption("multiple");
            _this._clearShow();
            if(!multiple){
                _this._createSingle();
            }else{
                _this._createMulti();
            }
            hideComboPanel(_this.id);
            _this._bindEvents();
            _this._init();
        },
        _createSingle:function(){
            var _this = this;
            _this.setOption("model",_EDITABLE_);
            var source='<div class="combo_single" >'+
                '<div class="combo_single_show">'+
                '<span type="text" class="combo_single_input"></span>' +
                '<i class="delete_icon"></i>'+
                '<i class="combo_single_arrow"></i>'+
                '</div> ' +
                '<div class="combo_panel" id="combo_panel_'+_this.id+'" comboId="'+_this.id+'">'+
                '</div>';

            _this._replaceNode(source);
            _this.component = $(".combo_single",_this.el);
            _this.combo_show = $(".combo_single_show",_this.component);
            _this.combo_input = $(".combo_single_input",_this.component);
            _this.show_arrow = $(".combo_single_arrow",_this.component);
            _this.combo_delete = $(".delete_icon",_this.component);

            _this.panel = $(".combo_panel",_this.component);
            _this.component.attr("comboId",_this.id);
            _this._setStyle();
            _this._initPanel();
            hideComboPanel(_this.id);
            _this._bindPanelEvents();
            _this._bindSingleEvents();
        },
        _createMulti:function(){
            var _this = this;
            _this.setOption("model",_EDITABLE_);
            var source = '<div class="combo_multi">'+
                '<div class="combo_multi_show">'+
                '<div class="combo_multi_input"></div>'+
                '<i class="combo_multi_more_bar icon_right"></i>'+
                '<div class="combo_multi_more"></div>'+
                '</div>'+
                '<div class="combo_panel" id="combo_panel_'+_this.id+'" comboId="'+_this.id+'">'+
                '</div>';

            _this._replaceNode(source);
            _this.component = $(".combo_multi",_this.el);
            _this.combo_show = $(".combo_multi_show",_this.component);
            _this.combo_input = $(".combo_multi_input",_this.component);
            _this.show_more_bar = $(".combo_multi_more_bar",_this.component);
            _this.show_more = $(".combo_multi_more",_this.component);

            _this.panel = $(".combo_panel",_this.component);
            _this.component.attr("comboId",_this.id);
            _this._setStyle();
            _this._initPanel();
            hideComboPanel(_this.id);
            _this._bindPanelEvents();
            _this._bindMultiEvents();
        },
        _clearEdit:function(){
            var _this = this;
            var multiple = _this.getOption("multiple");
            if(!multiple){
                _this._clearSingle();
            }else{
                _this._clearMulti();
            }
            if(_this.component){
                _this.component.unbind();
                _this.component.remove();
            }
        },
        _clearSingle:function(){
            var _this = this;
            if( _this.show_arrow){
                _this.show_arrow.unbind();
                _this.show_arrow.remove();
            }
            if( _this.combo_input){
                _this.combo_input.unbind();
                _this.combo_input.remove();
            }
            if( _this.combo_show){
                _this.combo_show.unbind();
                _this.combo_show.remove();
            }
            _this._clearPanel();
        },
        _clearPanel:function(){
            var _this = this;
            if( _this.panel){
                _this.panel.unbind();
                _this.panel.remove();
            }
        },
        _clearMulti:function(){
            var _this = this;
            $(".data_item",_this.show_more).unbind();
            $(".data_item .deleteicon",_this.show_more).unbind();

            if( _this.show_more_bar){
                _this.show_more_bar.unbind();
                _this.show_more_bar.remove();
            }
            if( _this.show_more){
                _this.show_more.unbind();
                _this.show_more.remove();
            }
            if( _this.combo_input){
                _this.combo_input.unbind();
                _this.combo_input.remove();
            }
            if( _this.combo_show){
                _this.combo_show.unbind();
                _this.combo_show.remove();
            }

            _this._clearPanel();
        },
        _bindSingleEvents:function(){
            var _this = this;
            var comboId = _this.id;
            _this.combo_show.click(function(e) {
                if(_this.panel.is(":visible")){
                    hideComboPanel(comboId);
                }else{
                    showComboPanel(comboId);
                }
                return false;
            });
            _this.combo_delete.click(function(){
                _this.setValue(null);
                _this._comboDelete("");
                hideComboPanel(_this.id)
                return false;
            });
            $(document).click(function(e) {
                if($(e.target).closest("div.combo_panel").size()>0){
                    return;
                }else{
                    hideComboPanel( _this.id);
                }
            });
        },
        _bindMultiEvents:function(){
            var _this = this;
            var comboId = _this.id;
            _this.combo_input.click(function(e) {
                if(_this.panel.is(":visible")){
                    hideComboPanel(comboId);
                }else{
                    showComboPanel(comboId);
                }
                return false;
            });

            $(document).click(function(e) {
                if($(e.target).closest("div.combo_panel").size()>0 || $(e.target).closest("div.combo_multi_more").size()>0){

                    return;
                }else{
                    hideComboPanel( _this.id);
                }
            });
            _this.show_more_bar.click(function(){
                if(_this.show_more.is(":visible")){
                    _this._hideMore();
                }else{
                    _this._showMore();
                }
                return false;
            });
        },
        _hideMore:function(){
            var _this = this;
            var multiple = _this.getOption("multiple");
            if(multiple){
                _this.show_more_bar.addClass("icon_right").removeClass("icon_left");
                _this.show_more.empty();
                _this.show_more.hide();
            }
        },
        _showMore:function(){
            var _this = this;
            hideComboPanel(_this.id);
            _this.show_more_bar.addClass("icon_left").removeClass("icon_right");
            _this.show_more.empty();
            _this.show_more.show();

            _this._moreItems();
        },
        setText:function (text) {
            this.combo_input.html(text);
        },
        getText:function () {
            return this.combo_input.html();
        },
        _moreItems:function () {
        },
        _showMoreItems:function (ids,names) {
            var _this = this;
            if(ids.length>0){
                for(var i=0;i<ids.length;i++){
                    var id = ids[i];
                    var name = names[i];
                    var _html='<div class="data_item" ><span>'+name+'</span><i class="deleteicon" data-name="'+name+'" data-value="'+id+'"></i></div>';
                    _this.show_more.append(_html);
                }

                var deleteHandler=function(obj){
                    var val = $(obj).attr("data-value");
                    var name = $(obj).attr("data-name");

                    if(ids.indexOf(val) != -1){
                        // _this.setValue(ids.join(","));
                        _this._mutiSelectMoreItems(val,ids,names,name);
                        var item = $(obj).closest(".data_item");
                        $(this).unbind();
                        item.remove();
                    }
                };
                $(".data_item .deleteicon",_this.show_more).unbind().click(function(){
                    deleteHandler(this);
                    return false;
                });

                $(".data_item",_this.show_more).unbind().dblclick(function(){
                    var obj = $(".deleteicon",$(this));
                    deleteHandler(obj);
                    return false;
                });
            }
        },
        _mutiSelectMoreItems:function (id,ids,names) {
        },
        _initPanel:function () {
        },
        _bindPanelEvents:function(){
        },
        _setPanelArea:function(width,height){
            var _this = this;
            if(_this.panel){
                _this.panel.width(width);
            }
            if(_this.panel){
                _this.panel.height(height);
            }
        },
        _showPanel:function () {
        },
        _comboDelete:function () {
        },
        setValid:function(flag){
            var _this = this;
            _this.validState = flag;

            if(_this.validState){
                _this.errMsg=[];
                _this.combo_show.removeClass("validClass");
            }else{
                _this.combo_show.addClass("validClass");
            }
            this._showErrMsg();
        },
    });

    window.hideComboPanel=function(comboId){
        var combo = _$.getById(comboId);
        var panel = $("#combo_panel_" + comboId);
        var combo_show = combo.combo_show;
        var multiple = combo.getOption("multiple");
        var speed = combo.getOption("speed");
        if(multiple || (isNotEmpty(combo.show_more) && combo.show_more.is(":visible"))){
            combo._hideMore();
        }
        var validState = combo.validState;
        if(validState){
            combo_show.css({
                "border-bottom":"1px solid #ccc"
            });
        }else{
            combo_show.css({
                "border-bottom":"1px solid #ff0000"
            });
        }

        panel.slideUp(speed);

        if(!multiple) {
            combo.show_arrow.removeClass("icon_up").addClass("icon_down");
        }
    };
    window.showComboPanel=function(comboId){

        var combo = _$.getById(comboId);
        var panel = $("#combo_panel_" + comboId);
        var multiple = combo.getOption("multiple");
        var speed = combo.getOption("speed");
        var combo_show = combo.combo_show;


        var panelWidth = combo.getOption("panelWidth");
        var panelHeight = combo.getOption("panelHeight");
        var realWidth,width,height;
        if(multiple){
            realWidth=combo_show.innerWidth();
        }else{
            realWidth=combo_show.innerWidth()-1;
        }
        panelWidth = isEmpty(panelWidth) || parseInt(panelWidth)<0 ? 0 : panelWidth;

        if(panelWidth > realWidth){
            width = panelWidth;
        }else if(panelWidth <= realWidth){
            width = realWidth;
        }
        height =  isEmpty(panelHeight) || parseInt(panelHeight)<0 ? 200 : panelHeight;


        combo._setPanelArea(width,height);

        combo.setOption("panelHeight",height);

        $(".combo_panel").each(function(){
            var comboId2 = $(this).attr("comboId");
            hideComboPanel(comboId2);
        });


        combo_show.css({
            "border-bottom":"0px"
        });
        var validState = combo.validState;
        if(validState){
            panel.removeClass("validClass");
        }else{
            panel.addClass("validClass");
        }

        if(!multiple) {
            combo.show_arrow.removeClass("icon_down").addClass("icon_up");
        }
        panel.slideDown(speed);
        combo._showPanel();
    };

})(window);

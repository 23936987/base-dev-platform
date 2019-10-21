
/**
 * 下拉树组件
 *
 * @class ComboTree
 * @constructor
 * @submodule form
 * @extends _$.Combo
 * @param {String,nodeType,jquery} target 选择器
 * @param {String} processKey 解析器key
 * @namespace _$
 */

_$._loadCssAndJs({
    "ComboJs":_$.basePath + "js/Combo.js",
    "LoadAjaxJs":_$.basePath + "js/LoadAjax.js",
    "ComboTreeCss":_$.basePath + "css/ComboTree.css"
});

_$.ComboTree=function (target,processKey){
    var _this = this;
    _$.ComboTree.superclass.constructor.call(_this,target,processKey);
};
_$.regClass("ComboTree",_$.ComboTree);

_$.extend(_$.ComboTree,_$.Combo,[_$.LoadAjax],{
    _cls:_$._clsPre + "ComboTree",
    _uiCls:_$._uiPreCls + "ComboTree",
    _require:function(){
        var res = {
            "metroStyleCss":"/lib/ztree/css/metroStyle/metroStyle.css",
            "ztreeJs":"/lib/ztree/js/jquery.ztree.all.js"
        };
        return res;
    },
    _attrProps:function(){
        var _this = this;
        var properties = ["parentKey","rootKey"];
        var superProperties = _$.ComboTree.superclass._attrProps.call(_this);
        var ajaxProperties = _$.ComboTree.loadAjax._attrProps.call(_this);

        properties.addAll(superProperties);
        properties.addAll(ajaxProperties);
        return properties;
    },
    _attrOpts:function(){
        var _this = this;
        var opts = _$.ComboTree.superclass._attrOpts.call(_this);
        var ajaxOpts = _$.ComboTree.loadAjax._attrOpts.call(_this);

        return $.extend(true,opts,ajaxOpts,{
            "resultKey": "nodes",
            "rootKey":null,
            "leaf":"leaf",
            "level":"level",
            "namePath":"namePath",
            plugins:{
                data: {
                    simpleData: {
                        enable: true,
                        name:"name",
                        idKey: "id",
                        pIdKey:"parentId"
                    }
                },
                view: {
                    showLine:false
                }
            },
            "parentKey":"parentId",
            "cascadeYes":"",
            "cascadeNo":""
        });
    },
    _initPanel: function () {

        var _this = this;
        var _panelHtml =  '<div class="combo_content"></div>'+
            '</div>';
        _this.panel.append(_panelHtml);
        _this.panel_content = $(".combo_content",_this.panel);

        var _html = '<div class="combo_tree_area"></div>';
        _this.panel_content.append(_html);
        _this.combo_tree_area = $(".combo_tree_area",_this.panel_content);

    },

    _setData:function(list){
        var _this = this;
        var parentKey = _this.getOption("parentKey");
        var rootKey = _this.getOption("rootKey");
        var idField = _this.getOption("idField");
        var textField = _this.getOption("textField");
        var multiple = _this.getOption("multiple");
        var plugins = _this.getOption("plugins");

        if(list != null && list.length>0){

            _this.combo_tree_area.empty();

            var _html = '<div id="tree_'+_this.id+'" class="ztree"></div>';
            _this.panel_tree_obj = $(".ztree",_this.el);
            _this.combo_tree_area.append(_html);


            var defaultOptions = $.extend(true,plugins,{
                data: {
                    key:{
                        name:textField,
                    },
                    simpleData: {
                        enable: true,
                        idKey: idField,
                        pIdKey:parentKey,
                        rootPId: rootKey
                    }
                },
            });
            var setting=null;
            if(!multiple){
                setting = $.extend(true,defaultOptions,{
                    callback: {
                        onClick: onComboTreeClick
                    }
                });
            }else{
                var cascadeYes = _this.getOption("cascadeYes");
                var cascadeNo = _this.getOption("cascadeNo");


                setting = $.extend(true,defaultOptions,{

                    callback: {
                        onCheck: onComboTreeCheck
                    },
                    check: {
                        enable: true,
                        chkboxType: { "Y": cascadeYes, "N": cascadeNo }
                    }
                });
            }
            $.fn.zTree.init($("#tree_"+_this.id), setting, list);
        }
        _this._loadSuccess();
    },
    _setPanelArea:function(width,height){
        var _this = this;
        if(_this.panel){
            _this.panel.width(width);
        }
        if(_this.panel){
            _this.panel.height(height+2);
        }
        if(_this.panel_content){
            _this.panel_content.height(height);
        }
        if(_this.panel_tree_obj){
            _this.panel_tree_obj.height(height);
        }
    },
    _clearArea:function () {
        var _this = this;
        _this.combo_tree_area.empty();
    },
    getValue:function(){
        var _this = this;
        return _this.value;
    },
    setValue:function(value,change){
        var _this = this;
        if(isEmpty(change)){
            change = true;
        }


        if(isEmpty(value) || isEmpty(_this.data) || _this.data.length == 0){
            _this.value = null;
            if (_this._isEdit()) {
                _this.combo_input.html("");
            } else {
                _this.component.html("");
            }
        }else{
            var multiple = _this.getOption("multiple");
            var idField = _this.getOption("idField");
            var textField = _this.getOption("textField");

            if(!multiple){

                var flag = _this.data.some(function(item,i){
                    var id = item[idField];
                    var name = item[textField];

                    if(id == value){
                        _this.value = id;

                        if (_this._isEdit()) {
                            _this.combo_input.html(name);
                        } else {
                            _this.component.html(name);
                        }

                        var treeObj = $.fn.zTree.getZTreeObj("tree_" + _this.id);
                        treeObj.checkAllNodes(false);

                        var node = treeObj.getNodeByParam(idField,value, null);
                        if (node != null) {
                            treeObj.selectNode(node);
                        }
                        return true;
                    }
                    return false;
                });

                if(!flag){
                    _this.value = null;
                    if (_this._isEdit()) {
                        _this.combo_input.html("");
                    } else {
                        _this.component.html("");
                    }
                }
            }else{
                var values = (value+"").split(",");
                if(values.length>0){
                    var names=[] ;
                    for(var i=0;i<values.length;i++){
                        var itemValue = values[i];

                        _this.data.each(function(item,i){
                            var id = item[idField];
                            var name = item[textField];
                            if(id == itemValue){
                                names.add(name);
                            }
                        });
                    }
                    if(names.length>0){
                        _this.value = values.join(",");

                        if (_this._isEdit()) {
                            _this.combo_input.html(names.join(","));
                        } else {
                            _this.component.html(names.join(","));
                        }
                    }else{
                        _this.value = null;

                        if (_this._isEdit()) {
                            _this.combo_input.html('');
                        } else {
                            _this.component.html('');
                        }
                    }

                    var treeObj = $.fn.zTree.getZTreeObj("tree_" + _this.id);
                    treeObj.checkAllNodes(false);
                    var selectNode=false;
                    values.each(function(val){
                        var node = treeObj.getNodeByParam(idField,val, null);

                        treeObj.checkNode(node, true, false);
                        if(!selectNode){
                            treeObj.selectNode(node);
                            selectNode=true;
                        }
                    });

                }
            }
        }

        if(change){
            var event = {};
            event.value = _this.getValue();
            _this._fire("onchange",event);
        }
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
});

var onComboTreeClick = function(event, treeId, treeNode) {
    var panel = $(event.target).closest("div.combo_panel");
    if(panel.size()>0){
        var comboId = panel.attr("comboId");
        var combo = _$.getById(comboId);
        if(treeNode){
            var treeObj = $.fn.zTree.getZTreeObj(treeId);

            var parentKey = combo.getOption("parentKey");
            var idField = combo.getOption("idField");
            var textField = combo.getOption("textField");
            var multiple = combo.getOption("multiple");

            if(!multiple) {
                var code = treeNode[idField];
                combo.setValue(code);
                hideComboPanel(comboId);
            }
        }
    }
};
var onComboTreeCheck = function(event, treeId, treeNode) {

    var panel = $(event.target).closest("div.combo_panel");
    if(panel.size()>0){
        var comboId = panel.attr("comboId");
        var combo = _$.getById(comboId);
        if(treeNode){
            var treeObj = $.fn.zTree.getZTreeObj(treeId);

            var parentKey = combo.getOption("parentKey");
            var idField = combo.getOption("idField");
            var textField = combo.getOption("textField");
            var multiple = combo.getOption("multiple");

            if(multiple) {
                var code = treeNode[idField];
                var checked = treeNode.checked;
                var value = combo.value;
                var values = [];
                if(isNotEmpty(value)){
                    values = (value+"").split(",");
                }
                if(checked){
                    if(values.indexOf(code) == -1){
                        values.add(code);
                        combo.setValue(values.join(","));
                    }
                }else{
                    if(values.indexOf(code) != -1){
                        values.remove(code);
                        combo.setValue(values.join(","));
                    }
                }

            }
        }
    }
};


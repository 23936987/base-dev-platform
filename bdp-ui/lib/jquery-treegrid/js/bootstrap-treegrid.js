$(function($){

    function BootstrapTreeGrid(el,option){
        this.options = option;
        this.$el = $(el);
        this.$el_ = this.$el.clone();
        this.timeoutId_ = 0;
        this.timeoutFooter_ = 0;

        this.init();
    }
    BootstrapTreeGrid.DEFAULTS = {
        'initialState': 'collapsed',
        'saveState': true,
        "description":"查询树",
        treeColumn:1,
        dataHandler:function(){
            return {
                "params[parentId].value":1
            };
        },
        expanderExpandedClass: 'glyphicon glyphicon-minus',
        expanderCollapsedClass: 'glyphicon glyphicon-plus',
        columns:[],
        onExpand: function() {
            //alert("Expanded "+$(this).attr("id"));
        }
    };

    BootstrapTreeGrid.prototype.init = function(){
        var _this = this;

        var url = this.options["url"];
        var dataHandler = this.options["dataHandler"];
        var data = dataHandler.call(null);

        $.ajax({
            type: "POST",
            url: url,
            data:data,
            dataType: "json",
            success: function(result){
                var nodes = result.nodes;
                _this.initTable(nodes);
            }
        });
    };

    BootstrapTreeGrid.prototype.initTable = function(nodes){
       var _this = this;
        var columns = _this.options["columns"];

        var _headHtml = "";
        var _bodyHtml = "";
        if(nodes != null && nodes.length>0 && columns != null && columns.length>0){
            _headHtml +="<tr>";
            for(var i=0;i<columns.length;i++){
                var column = columns[i];
                if(column.visible){
                    var title = column["title"];
                    _headHtml += "<td>"+title+"</td>";

                }
            }
            _headHtml +="</tr>";

            for(var j=0;j<nodes.length;j++){
                var node = nodes[j];
                var id = node.id;
                _bodyHtml +='<tr class="treegrid-'+id+'" id="'+id+'" isLeaf="false">';
                for(var k=0;k<columns.length;k++){
                    var column = columns[k];
                    if(column.visible){
                        var field = column["field"];
                        var formatter = column["formatter"];
                        var _tdHtml;
                        var val = node[field];
                        if(formatter){
                            _tdHtml = formatter.call(null,val,node,i)
                        }else{
                            _tdHtml = val
                        }
                        _bodyHtml += "<td>"+_tdHtml+"</td>";
                    }
                }
                _bodyHtml +="</tr>";
            }
        }

        var _html = '<thead>'+_headHtml+'</thead><tbody>'+_bodyHtml+'</tbody>';

        this.$el.html(_html);

        var options = {
            'initialState': this.options['initialState'],
            'saveState': this.options['saveState'],
            treeColumn:this.options['treeColumn'],
            expanderExpandedClass:this.options['expanderExpandedClass'],
            expanderCollapsedClass:this.options['expanderCollapsedClass'],
            onExpand:this.options['onExpand']
        };
        _this.$el.treegrid(options);
    }

    BootstrapTreeGrid.prototype.refresh = function(data){


    };

    var allowedMethods = [
    ];

    $.fn.bootstrapTreeGrid = function(option){
        var options = $.extend({},BootstrapTreeGrid.DEFAULTS,option);
        new BootstrapTreeGrid(this,options);
        return this;
    };
    $.fn.bootstrapTreeGrid.Constructor = BootstrapTreeGrid;
    $.fn.bootstrapTreeGrid.defaults = BootstrapTreeGrid.DEFAULTS;
    $.fn.bootstrapTreeGrid.columnDefaults = BootstrapTreeGrid.COLUMN_DEFAULTS;
    $.fn.bootstrapTreeGrid.methods = allowedMethods;
}(jQuery));
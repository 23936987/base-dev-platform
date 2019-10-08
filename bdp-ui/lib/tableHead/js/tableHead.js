//调整控件位置的js
//在初始化控件前调用
//格式化cc控件的位置
//y 行 x 列
function _formatCCPostion($tableDiv){
    //获取cc控件
    //var $table = $("div.table-head table tbody");
    var tableIndex = parseInt($tableDiv.attr("table-head-index")||0);
    $tableDiv.html("<table><thead></thead><tbody></tbody></table>");
    var $table = $tableDiv.find("table tbody");
    //获取位置的函数
    var getPostion = function(y,x,width,height){
        //获取行
        var trNum = $table.find("tr").length;
        for(;trNum<=(y);trNum++){//没有就添加
            $table.append("<tr></tr>");
        }
        var $tr = $table.find("tr:eq("+y+")");
        $tr.attr("table-head-postion-y",y);
        //获取列
        var tdNum = $tr.find("td[table-head-postion-x]").length;
        for(;tdNum<=(x);tdNum++){//没有就添加
            $tr.append("<td table-head-postion-x='"+tdNum+"'></td>");
        }
        var $td = $tr.find("td[table-head-postion-x]:eq("+x+")");
        //$td.attr("table-head-postion-x",x);
        $td.attr("colspan",width);
        $td.attr("rowspan",height);
        return $td;
    };
    var checkPostion = function(){//检查位置,并补充td
        var maxCol = 0;//最大列数
        $table.find("tr").each(function(i,n){//获取最大列数
            var $tr = $(this);
            var _maxCol = 0;
            $tr.find("td").each(function(){
                _maxCol+=parseInt($(this).attr("colspan")||1);
            });
            maxCol = maxCol<_maxCol?_maxCol:maxCol;
        });
        $table.find("tr").each(function(i,n){//补充td并设置宽度
            var $tr = $(this);
            var tdNum = 0;
            $tr.find("td").each(function(){
                tdNum += parseInt($(this).attr("colspan")||1);
            });
            for(;tdNum<maxCol;tdNum++){
                $tr.append("<td style='display: none'></td>");
            }
            $tr.find("td").each(function(){
                var colspan = parseInt($(this).attr("colspan")||1);
                //$(this).css("min-width",(100*colspan/maxCol)+"%");
                $(this).css("max-width",(100*colspan/maxCol)+"%");
                //$(this).css("width",(100*colspan/maxCol)+"%");

            });
        });

    };


    var $cc = $("div[class*=cc-][table-head-postion][table-head-index="+tableIndex+"]");
    if(tableIndex==0&&$cc.length==0){
        $cc = $("div[class*=cc-][table-head-postion]");
    }
    $cc.each(function(){
        var $this = $(this);
        var thisHtml = this.outerHTML;
        //读取位置属性
        var options = $this.attr("table-head-postion");
        var marginStyle = $this.attr("table-head-margin");
        if(isNotEmpty(options)){
            var y = options.split(",")[0];
            var x = options.split(",")[1];
            var height = options.split(",")[2];
            var width = options.split(",")[3];
            var nameCn = $this.attr("nameCn");
            var $td = getPostion(y,x,width,height);//获取相应位置的td
            if(isNotEmpty(marginStyle)){
                $td.css("text-align",marginStyle);
            }
            if(isNotEmpty(nameCn)){
                var $prev = $("<td></td>");
                $prev.insertBefore($td);
                $prev.attr("colspan",1);
                $prev.attr("rowspan",$td.attr("rowspan"))
                $prev.addClass("table-head-nameCn")
                //$prev.html("<div  class='table-head-nameCn'>"+nameCn+"</div>");
                $prev.html(nameCn);
            }else{
                $td.attr("colspan",parseInt($td.attr("colspan")||1)+1);//内容td加1colspan
            }
            $td.append(thisHtml);
            //$("<td class='table-head-nameCn'>"+nameCn+"</td>").insertBefore($td);
            $this.remove();
        }
    });
    checkPostion();
}
function formatCCPostion(){
    // var $tables = $("div.table-head table tbody");
    $("div.table-head").each(function(){
        _formatCCPostion($(this));
    });

}
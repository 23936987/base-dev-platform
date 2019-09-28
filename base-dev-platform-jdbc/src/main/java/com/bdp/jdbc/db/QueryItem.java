package com.bdp.jdbc.db;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryItem {
    @ApiModelProperty(value = "查询类型")
    private Integer type;
    @ApiModelProperty(value = "值")
    private Object value;
    @ApiModelProperty(value = "开始值")
    private Object start;
    @ApiModelProperty(value = "结束值")
    private Object end;

    public QueryItem(){}

    public QueryItem(Object value){
        this.type = QType.EQUALS.getType();
        this.value = value;
    }
    public QueryItem(Integer type, Object value){
        this.type = type;
        this.value = value;
    }
    public QueryItem(Integer type, Object start, Object end){
        this.type = type;
        this.start = start;
        this.end = end;
    }

}

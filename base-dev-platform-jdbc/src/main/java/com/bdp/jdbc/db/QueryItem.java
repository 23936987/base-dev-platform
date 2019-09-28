package com.bdp.jdbc.db;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryItem {
    @ApiModelProperty(value = "属性名称")
    private String key;
    @ApiModelProperty(value = "查询类型")
    private Integer type;
    @ApiModelProperty(value = "值")
    private Object value;
    @ApiModelProperty(value = "开始值")
    private Object start;
    @ApiModelProperty(value = "结束值")
    private Object end;

    public QueryItem(){}

    public QueryItem(String key,Object value){
        this.type = QType.EQUALS.getType();
        this.key = key;
        this.value = value;
    }
    public QueryItem(String key,Integer type, Object value){
        this.key = key;
        this.type = type;
        this.value = value;
    }
    public QueryItem(String key,Integer type, Object start, Object end){
        this.key = key;
        this.type = type;
        this.start = start;
        this.end = end;
    }

}

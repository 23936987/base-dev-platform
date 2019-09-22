package com.bdp.jdbc.db;

import lombok.Data;

@Data
public class Query {
    private Integer type;
    private Object value;
    private Object start;
    private Object end;

    public Query(){}

    public Query(Object value){
        this.type = QType.EQUALS.getType();
        this.value = value;
    }
    public Query(Integer type,Object value){
        this.type = type;
        this.value = value;
    }
    public Query(Integer type,Object start,Object end){
        this.type = type;
        this.start = start;
        this.end = end;
    }

}

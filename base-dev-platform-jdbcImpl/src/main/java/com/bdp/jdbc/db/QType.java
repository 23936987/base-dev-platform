package com.bdp.jdbc.db;


public enum  QType {
    EQUALS(0),
    GREATER(1),
    GREATER_EQ(2),
    LESS(3),
    LESS_EQ(4),
    FIND_IN_SET(5),
    FULL_LIKE(6),
    LEFT_LIKE(7),
    RIGHT_LIKE(8),
    NOT_IN(9),
    IN(10);

    private Integer type;

    private QType(Integer type){
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

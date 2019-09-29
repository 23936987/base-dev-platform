package com.genetor.util;


public enum MySqlDTM {

    VARCHAR("VARCHAR","java.lang.String","\"\""),
    TEXT("TEXT","java.lang.String","\"\""),
    LONGTEXT("LONGTEXT","String","\"\""),
    JSON("JSON","java.lang.String","\"\""),
    INT("int","java.lang.Integer","0"),
    CHAR("CHAR","java.lang.String",""),
    BLOB("BLOB","byte[]","{}"),
    INTEGER_UNSIGNED("INTEGER UNSIGNED","Long","0l"),
    TINYINT("TINYINT","java.lang.Integer","0"),
    TINYINT_UNSIGNED("TINYINT UNSIGNED","Integer","0"),
    SMALLINT_UNSIGNED("SMALLINT UNSIGNED","Integer","0"),
    MEDIUMINT_UNSIGNED("MEDIUMINT UNSIGNED","String","\"\""),
    BIT("BIT","java.lang.Boolean","true"),
    BIGINT("BIGINT","java.lang.Long","0l"),
    BIGINT_UNSIGNED("BIGINT UNSIGNED","java.math.BigInteger","0"),
    FLOAT("FLOAT","java.lang.Float","0.0"),
    DOUBLE("DOUBLE","java.lang.Double","0.0"),
    DECIMAL("DECIMAL","java.math.BigDecimal","new BigDecimal(0.00)"),
    DATE("DATE","java.util.Date","new Date()"),
    TIME("TIME","java.util.Date","new Date()"),
    DATETIME("DATETIME","java.util.Date","new Date()"),
    TIMESTAMP("TIMESTAMP","java.util.Date","new Date()"),
    YEAR("YEAR","java.util.Date","new Date()");

    private MySqlDTM(String dataType, String propType, String defVal) {
        this.dataType = dataType;
        this.propType = propType;
        this.defVal = defVal;
    }

    private String dataType;
    private String propType;
    private String defVal;

    public String getDefVal() {
        return defVal;
    }

    public void setDefVal(String defVal) {
        this.defVal = defVal;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getPropType() {
        return propType;
    }

    public void setPropType(String propType) {
        this.propType = propType;
    }
}

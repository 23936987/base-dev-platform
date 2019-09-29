package com.genetor.util;

public class MySqlConvert {
    public String parseType(String type) throws Exception {

        if(MySqlDTM.VARCHAR.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.VARCHAR.getPropType();
        }if(MySqlDTM.JSON.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.JSON.getPropType();
        }else if(MySqlDTM.TEXT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.TEXT.getPropType();
        }else if(MySqlDTM.INT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.INT.getPropType();
        }else if(MySqlDTM.CHAR.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.CHAR.getPropType();
        }else if(MySqlDTM.BLOB.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.BLOB.getPropType();
        }else if(MySqlDTM.INTEGER_UNSIGNED.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.INTEGER_UNSIGNED.getPropType();
        }else if(MySqlDTM.TINYINT_UNSIGNED.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.TINYINT_UNSIGNED.getPropType();
        }else if(MySqlDTM.SMALLINT_UNSIGNED.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.SMALLINT_UNSIGNED.getPropType();
        }else if(MySqlDTM.MEDIUMINT_UNSIGNED.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.MEDIUMINT_UNSIGNED.getPropType();
        }else if(MySqlDTM.BIT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.BIT.getPropType();
        }else if(MySqlDTM.BIGINT_UNSIGNED.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.BIGINT_UNSIGNED.getPropType();
        }else if(MySqlDTM.BIGINT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.BIGINT.getPropType();
        }else if(MySqlDTM.FLOAT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.FLOAT.getPropType();
        }else if(MySqlDTM.DOUBLE.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.DOUBLE.getPropType();
        }else if(MySqlDTM.DECIMAL.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.DECIMAL.getPropType();
        }else if(MySqlDTM.DATE.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.DATE.getPropType();
        }else if(MySqlDTM.TIME.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.TIME.getPropType();
        }else if(MySqlDTM.DATETIME.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.DATETIME.getPropType();
        }else if(MySqlDTM.TIMESTAMP.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.TIMESTAMP.getPropType();
        }else if(MySqlDTM.YEAR.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.YEAR.getPropType();
        }else if(MySqlDTM.VARCHAR.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.VARCHAR.getPropType();
        }else if(MySqlDTM.TINYINT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.TINYINT.getPropType();
        }else if(MySqlDTM.LONGTEXT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.LONGTEXT.getPropType();
        } else {
            return null;
        }
    }

    public String parseDefValue(String type) throws Exception {

        if(MySqlDTM.VARCHAR.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.VARCHAR.getDefVal();
        }if(MySqlDTM.JSON.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.JSON.getDefVal();
        }else if(MySqlDTM.TEXT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.TEXT.getDefVal();
        }else if(MySqlDTM.INT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.INT.getDefVal();
        }else if(MySqlDTM.CHAR.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.CHAR.getDefVal();
        }else if(MySqlDTM.BLOB.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.BLOB.getDefVal();
        }else if(MySqlDTM.INTEGER_UNSIGNED.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.INTEGER_UNSIGNED.getDefVal();
        }else if(MySqlDTM.TINYINT_UNSIGNED.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.TINYINT_UNSIGNED.getDefVal();
        }else if(MySqlDTM.SMALLINT_UNSIGNED.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.SMALLINT_UNSIGNED.getDefVal();
        }else if(MySqlDTM.MEDIUMINT_UNSIGNED.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.MEDIUMINT_UNSIGNED.getDefVal();
        }else if(MySqlDTM.BIT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.BIT.getDefVal();
        }else if(MySqlDTM.BIGINT_UNSIGNED.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.BIGINT_UNSIGNED.getDefVal();
        }else if(MySqlDTM.BIGINT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.BIGINT.getDefVal();
        }else if(MySqlDTM.FLOAT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.FLOAT.getDefVal();
        }else if(MySqlDTM.DOUBLE.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.DOUBLE.getDefVal();
        }else if(MySqlDTM.DECIMAL.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.DECIMAL.getDefVal();
        }else if(MySqlDTM.DATE.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.DATE.getDefVal();
        }else if(MySqlDTM.TIME.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.TIME.getDefVal();
        }else if(MySqlDTM.DATETIME.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.DATETIME.getDefVal();
        }else if(MySqlDTM.TIMESTAMP.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.TIMESTAMP.getDefVal();
        }else if(MySqlDTM.YEAR.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.YEAR.getDefVal();
        }else if(MySqlDTM.VARCHAR.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.VARCHAR.getDefVal();
        }else if(MySqlDTM.TINYINT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.TINYINT.getDefVal();
        } else if(MySqlDTM.LONGTEXT.getDataType().equalsIgnoreCase(type)){
            return MySqlDTM.LONGTEXT.getDefVal();
        } else {
            return null;
        }
    }
}

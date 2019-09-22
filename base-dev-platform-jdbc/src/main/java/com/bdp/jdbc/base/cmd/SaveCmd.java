package com.bdp.jdbc.base.cmd;

import com.bdp.exception.Assert;
import com.bdp.helper.JsonHelper;
import com.bdp.helper.ReflectionHelper;
import com.bdp.jdbc.annotation.Transient;
import com.bdp.jdbc.base.entity.Entity;
import com.bdp.jdbc.db.JdbcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveCmd<E extends Entity> extends EntityCmd<E,Integer> {
    private static Logger logger = LoggerFactory.getLogger(SaveCmd.class);

    private E entity;

    public SaveCmd(E entity) {
        this.entity = entity;
    }

    @Override
    public Integer execute(JdbcContext context) throws Exception {

        String insertSql = "insert into [TABLE] ([COLUMNS]) values ([COLUMNS_VALUES])";
        Map<String,Object> wheres = new HashMap<>();

        String columns = "";
        String columnsValues = "";

        String tableName = getTableName();

        List<Field> fields = ReflectionHelper.getDeclaredFields(entityClass);
        for (int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i);
            String fieldName = f.getName();
            String columnName = getColumnName(f);


            Transient tran = f.getAnnotation(Transient.class);
            if (tran != null) {
                continue;
            }

            Object value = ReflectionHelper.getFieldValue(entity,fieldName);
            if(value == null){
                continue;
            }
            columns += "," + columnName;
            columnsValues += ",:" + fieldName;
            wheres.put(fieldName,value);
        }

        Assert.isTrue(columns != null || columns.length() > 1, "model没有属性");

        insertSql = insertSql.replace("[TABLE]", tableName);
        insertSql = insertSql.replace("[COLUMNS]", columns.substring(1));
        insertSql = insertSql.replace("[COLUMNS_VALUES]", columnsValues.substring(1));

        logger.debug("sql : " + insertSql);
        logger.debug("wheres : " + JsonHelper.toJSonString(entity));
        Integer result =context.getNamedParameterJdbcTemplate().update(insertSql, wheres);

        return result;
    }
}

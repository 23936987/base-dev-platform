package com.bdp.jdbc.base.cmd;

import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.JdbcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SaveListCmd<E extends Entity> extends EntityCmd<E,String> {
    private static Logger logger = LoggerFactory.getLogger(SaveListCmd.class);

    private List<Entity> list;

    public SaveListCmd(List<Entity> list) {
        this.list = list;
    }

    @Override
    public String execute(JdbcContext context) throws Exception {

       /* String insertSql = "insert into [TABLE] ([COLUMNS]) values ([COLUMNS_VALUES])";
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

        return result;*/
       return null;
    }
}

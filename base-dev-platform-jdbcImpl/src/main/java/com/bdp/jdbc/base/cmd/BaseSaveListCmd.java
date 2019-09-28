package com.bdp.jdbc.base.cmd;

import com.bdp.exception.Assert;
import com.bdp.helper.JsonHelper;
import com.bdp.helper.ReflectionHelper;
import com.bdp.jdbc.annotation.Transient;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.JdbcContext;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseSaveListCmd<E extends Entity> extends BaseEntityCmd<E, Integer> {
    private static Logger logger = LoggerFactory.getLogger(BaseSaveListCmd.class);

    private List<Entity> list;
    private Integer pageSize=100;

    public BaseSaveListCmd(List<Entity> list,Integer pageSize) {
        this.list = list;
        this.pageSize=pageSize;
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
            columns += "," + columnName;
            columnsValues += ",:" + fieldName;

        }

        Assert.isTrue(columns != null || columns.length() > 1, "model没有属性");

        insertSql = insertSql.replace("[TABLE]", tableName);
        insertSql = insertSql.replace("[COLUMNS]", columns.substring(1));
        insertSql = insertSql.replace("[COLUMNS_VALUES]", columnsValues.substring(1));

        int total=list.size();
        logger.debug("sql : " + insertSql);
        if (total > 0) {
             Integer page = (total % pageSize == 0) ? (total /pageSize) : (total /pageSize + 1);

            for (int i = 1; i <= page; i++) {
                Integer start = (i -1) * pageSize;
                Integer end = 0;
                if(i == page) {
                    end = total;
                }else{
                    end = start + pageSize;
                }
                List<Entity> rows = list.subList(start,end);
                logger.debug("total=" + total+",page=" + page+",start=" + start + ",end=" + end +",rows : " + JsonHelper.toJSonString(rows));
                batchSave(context,insertSql,rows);
            }
        }
       return null;
    }

    private void batchSave(JdbcContext context,String insertSql, List<Entity> rows) {

        Integer total = rows.size();
        Map<String, Object>[] batchValues = new LinkedCaseInsensitiveMap[total];
        batchValues = rows.toArray(batchValues);
        context.getNamedParameterJdbcTemplate().batchUpdate(insertSql, batchValues);
        rows.clear();
    }
}

package com.bdp.jdbc.base.cmd;

import com.bdp.helper.JsonHelper;
import com.bdp.helper.ReflectionHelper;
import com.bdp.helper.StringHelper;
import com.bdp.jdbc.base.entity.Entity;
import com.bdp.jdbc.db.JdbcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class QueryBySqlCmd<E extends Entity> extends EntityCmd<E, List<E>> {
    private static Logger logger = LoggerFactory.getLogger(QueryBySqlCmd.class);

    private Map<String,Object> wheres;
    private String sql;

    public QueryBySqlCmd(String sql, Map<String, Object> wheres){
        this.wheres = wheres;
        this.sql = sql;
    }

    @Override
    public List<E> execute(JdbcContext context) throws Exception {


        String query = getQuery();
        sql = sql.replace("[queryString]",query.substring(1));

        String tableName = getTableName();
        sql = sql.replace("[TABLE]",tableName);

        logger.debug("sql : " + sql);
        logger.debug("params : " + JsonHelper.toJSonString(wheres));

        List<E> list = context.getNamedParameterJdbcTemplate().query(sql, wheres, getEntityRowMapper());
        logger.debug("result : " + JsonHelper.toJSonString(list));
        return list;
    }


    protected RowMapper<E> getEntityRowMapper() {
        return new BeanPropertyRowMapper(){
            @Override
            public E mapRow(ResultSet rs, int rowNumber) throws SQLException {
                try {
                    E model = entityClass.newInstance();
                    List<Field> fields = ReflectionHelper.getDeclaredFields(entityClass);
                    for (int i = 0; i < fields.size(); i++) {
                        Field f = fields.get(i);
                        String fieldName = f.getName();
                        try {
                            Object value = rs.getObject(fieldName);
                            if(value == null) {
                                String columnName = StringHelper.parseCol(fieldName);
                                value = rs.getObject(columnName);
                            }
                            if(value != null) {
                                f.setAccessible(true);
                                f.set(model,value);
                            }
                        } catch (Exception e) {
                            logger.error("异常",e);
                        }
                    }
                    return model;
                } catch (Exception e) {
                    logger.error(e.getMessage(),e);
                    return null;
                }
            }
        };
    }
}

package com.bdp.jdbc.base.cmd;

import com.bdp.helper.BaseHelper;
import com.bdp.helper.JsonHelper;
import com.bdp.helper.ReflectionHelper;
import com.bdp.helper.StringHelper;
import com.bdp.jdbc.base.entity.Entity;
import com.bdp.jdbc.db.JdbcContext;
import com.bdp.jdbc.db.WhereResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
public class QueryVoCmd<V,E extends Entity> extends  EntityCmd<E,List<V>> {

    private Map<String,Object> wheres;
    private String sql;
    protected Class<V> clazz;


    public void setClazz(Class<V> clazz,Class<E> entityClass){
        this.clazz = clazz;
        this.entityClass = entityClass;
    }


    public QueryVoCmd(String sql,Map<String, Object> wheres){
        this.wheres = wheres;
    }


    @Override
    public List<V> execute(JdbcContext context) throws Exception {

        log.debug("sql : " + sql);
        log.debug("params : " + JsonHelper.toJSonString(wheres));

        List<V> list = context.getNamedParameterJdbcTemplate().query(sql, wheres, getVoRowMapper());
        log.debug("result : " + JsonHelper.toJSonString(list));
        return list;
    }

    protected RowMapper<V> getVoRowMapper() {
        return new BeanPropertyRowMapper(){
            @Override
            public V mapRow(ResultSet rs, int rowNumber) throws SQLException {
                try {
                    V model = clazz.newInstance();
                    List<Field> fields = ReflectionHelper.getDeclaredFields(clazz);
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

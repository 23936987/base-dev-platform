package com.bdp.jdbc.db.cmd;

import com.bdp.helper.ReflectionHelper;
import com.bdp.helper.StringHelper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public abstract class BaseCmd<V,R> implements Command<R> {
    protected Class<V> clazz;
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

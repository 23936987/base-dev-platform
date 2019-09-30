package com.bdp.jdbc.helper;

import com.bdp.helper.ReflectionHelper;
import com.bdp.helper.StringHelper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanHelper {

    public static void copyProperties(Object dest, Object orig) throws Exception {
        List<Field> fields = ReflectionHelper.getDeclaredFields(orig.getClass());

        for (int i = 0; i < fields.size(); i++) {
            String fieldsName = fields.get(i).getName();
            Object value = ReflectionHelper.getFieldValue(orig,fieldsName);
            if(value != null){
                Field field = ReflectionHelper.getDeclaredField(dest.getClass(),fieldsName);
                if(field != null) {
                    ReflectionHelper.setFieldValue(dest,fieldsName,value);
                }
            }
        }
    }

    public static Map<String, Object> obj2Map(Object orig) {
        Map<String, Object> res = new HashMap<>();
        List<Field> fields = ReflectionHelper.getDeclaredFields(orig.getClass());

        for (int i = 0; i < fields.size(); i++) {
            String fieldsName = fields.get(i).getName();
            Object value = ReflectionHelper.getFieldValue(orig,fieldsName);
            if(value != null) {
                res.put(fieldsName,value);
            }
        }
        return res;
    }

    public static  <T> RowMapper<T> getVoRowMapper(Class<T> clazz) {
        return new BeanPropertyRowMapper(){
            @Override
            public T mapRow(ResultSet rs, int rowNumber) throws SQLException {
                try {
                    T model = clazz.newInstance();
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

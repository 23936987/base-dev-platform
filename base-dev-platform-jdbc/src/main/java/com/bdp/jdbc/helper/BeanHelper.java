package com.bdp.jdbc.helper;

import com.bdp.helper.ReflectionHelper;

import java.lang.reflect.Field;
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
}

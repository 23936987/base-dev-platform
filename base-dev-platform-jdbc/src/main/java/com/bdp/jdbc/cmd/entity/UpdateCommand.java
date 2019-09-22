package com.bdp.jdbc.cmd.entity;


import com.bdp.helper.JsonHelper;
import com.bdp.helper.ReflectionHelper;
import com.bdp.jdbc.db.Entity;
import com.bdp.jdbc.db.JdbcContext;
import com.bdp.jdbc.db.WhereResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Slf4j
public class UpdateCommand<E extends Entity> extends EntityCommand<E,Integer,E> {
    private static Logger logger = LoggerFactory.getLogger(UpdateCommand.class);
    private Map<String,Object> props;
    private Map<String,Object> wheres;


    public UpdateCommand(Map<String, Object> props, Map<String, Object> wheres){
        this.props = props;
        this.wheres = wheres;
    }
    @Override
    public Integer execute(JdbcContext context) throws Exception {

        String tableName = getTableName();

        String sql = " update [TABLE] t set [FIELDS] where 1=1";
        String fields = "";

        Set<Map.Entry<String,Object>> fieldSet = props.entrySet();
        for (Iterator<Map.Entry<String,Object>> it=fieldSet.iterator();it.hasNext();){
            Map.Entry<String,Object> item = it.next();
            String fieldName = item.getKey();
            Object value = item.getValue();

            Field f = ReflectionHelper.getDeclaredField(entityClass, fieldName);

            if(f != null){
                String columnName = getColumnName(f);
                if(null != value){
                    fields += ",t." +columnName +"=:"+fieldName+"";
                }else{
                    fields += ",t." +columnName +"=null";
                }
            }
        }

        sql = sql.replace("[FIELDS]",fields.substring(1));
        sql = sql.replace("[TABLE]",tableName);

        WhereResult whereResult =  getWhere(wheres);

        Map<String,Object> wheres = whereResult.getWheres();
        sql += whereResult.getSql();

        wheres.putAll(props);

        log.debug("sql : " + sql);
        log.debug("wheres : " + JsonHelper.toJSonString(wheres));

        Integer result;
        result = context.getJdbcTemplate().update(sql,wheres);
        log.debug("result : " + result);

        //返回值处理
        return result;
    }

}

package com.bdp.jdbc.base.cmd;

import com.bdp.helper.JsonHelper;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.JdbcContext;
import com.bdp.jdbc.helper.BeanHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class BaseQueryVoCmd<V,E extends Entity> extends BaseEntityCmd<E,List<V>> {

    private Map<String,Object> wheres;
    private String sql;
    protected Class<V> clazz;

    public void setClazz(Class<V> clazz,Class<E> entityClass){
        this.clazz = clazz;
        this.entityClass = entityClass;
    }

    public BaseQueryVoCmd(String sql, Map<String, Object> wheres){
        this.wheres = wheres;
        this.sql = sql;
    }


    @Override
    public List<V> execute(JdbcContext context) throws Exception {

        log.debug("sql : " + sql);
        log.debug("params : " + JsonHelper.toJSonString(wheres));

        List<V> list = context.getNamedParameterJdbcTemplate().query(sql, wheres, BeanHelper.getVoRowMapper(clazz));
        log.debug("result : " + JsonHelper.toJSonString(list));
        return list;
    }
}

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

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
@Slf4j
public class QueryEntityCmd<E extends Entity> extends EntityCmd<E,List<E>> {

    private Map<String,Object> params;
    private Integer pageNum;
    private Integer pageSize;
    private String orderBy;

    public QueryEntityCmd(Map<String, Object> params){
        this.params = params;
    }
    public QueryEntityCmd(Map<String, Object> params, Integer pageNum, Integer pageSize){
        this.params = params;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
    public QueryEntityCmd(Map<String, Object> params, String orderBy, Integer pageNum, Integer pageSize){
        this.params = params;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }

    @Override
    public List<E> execute(JdbcContext context) throws Exception {

        String sql = "select [QUERY] from [TABLE]  t where 1=1 ";
        String tableName = getTableName();
        sql = sql.replace("[TABLE]",tableName);
        String query = getQuery();
        sql = sql.replace("[QUERY]",query.substring(1));
        sql = sql.replace("[TABLE]",tableName);

        WhereResult whereResult =  getWhere(params);

        Map<String,Object> wheres = whereResult.getWheres();
        sql += whereResult.getSql();

        if(BaseHelper.isNotEmpty(orderBy)){
            sql += " value ";
            wheres.put("orderBy",orderBy);
        }
        if(pageNum != null && pageSize != null){
            int begin = (pageNum -1) * pageSize;
            sql += " limit :begin,:pageSize ";
            wheres.put("begin",begin);
            wheres.put("pageSize",pageSize);
        }

        log.debug("sql : " + sql);
        log.debug("params : " + JsonHelper.toJSonString(wheres));

        List<E> list = context.getNamedParameterJdbcTemplate().query(sql, wheres, getEntityRowMapper());
        log.debug("result : " + JsonHelper.toJSonString(list));
        return list;
    }
    protected BeanPropertyRowMapper getEntityRowMapper() {
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

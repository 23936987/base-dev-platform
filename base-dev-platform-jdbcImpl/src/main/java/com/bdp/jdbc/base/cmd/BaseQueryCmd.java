package com.bdp.jdbc.base.cmd;

import com.bdp.helper.BaseHelper;
import com.bdp.helper.JsonHelper;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.JdbcContext;
import com.bdp.jdbc.db.WhereResult;
import com.bdp.jdbc.helper.BeanHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
@Slf4j
public class BaseQueryCmd<E extends Entity> extends BaseEntityCmd<E,List<E>> {

    private Map<String,Object> params;
    private Integer pageNum;
    private Integer pageSize;
    private String orderBy;

    public BaseQueryCmd(Map<String, Object> params){
        this.params = params;
    }
    public BaseQueryCmd(Map<String, Object> params, Integer pageNum, Integer pageSize){
        this.params = params;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
    public BaseQueryCmd(Map<String, Object> params, String orderBy, Integer pageNum, Integer pageSize){
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

        List<E> list = context.getNamedParameterJdbcTemplate().query(sql, wheres, BeanHelper.getVoRowMapper(entityClass));
        log.debug("result : " + JsonHelper.toJSonString(list));
        return list;
    }
}

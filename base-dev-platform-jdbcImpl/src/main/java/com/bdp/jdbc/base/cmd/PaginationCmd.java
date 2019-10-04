package com.bdp.jdbc.base.cmd;

import com.bdp.helper.BaseHelper;
import com.bdp.helper.Constant;
import com.bdp.helper.JsonHelper;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.JdbcContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class PaginationCmd<V,E extends Entity> extends BaseCmd<V,Map<String,Object>> {

    private String sql;
    private Map<String,Object> wheres;
    private Integer page;
    private Integer pageSize;
    private String orderBy;

    public PaginationCmd(String sql,Map<String,Object> wheres,Integer page,Integer pageSize,String orderBy) {
        this.sql = sql;
        this.wheres = wheres;
        this.page = page;
        this.pageSize=pageSize;
        this.orderBy=orderBy;
    }

    @Override
    public Map<String,Object> execute(JdbcContext context) throws Exception {
	    //查询总数
        Long total= getCount(context);
        //查询列表
        List<V> list = getList(context);

        Map<String,Object> result = new HashMap<>();
        result.put(Constant.TOTAL,total);
        result.put(Constant.ROWS,list);

        return result;
    }



    private List<V> getList(JdbcContext context) throws Exception {

	    // 排序
	    if(BaseHelper.isNotEmpty(orderBy)){
		    sql += orderBy;
	    }
	    sql += " limit " +  ((page -1) * pageSize) +"," + pageSize;

        log.debug("listSql : " + sql);
        log.debug("params : " + JsonHelper.toJSonString(wheres));

        List<V> list = context.getNamedParameterJdbcTemplate().query(sql, wheres,getVoRowMapper());
        return list;
    }

    private Long getCount(JdbcContext context) throws Exception {
        String countSql = "select count(*) cnt from ("+ sql +") v";

        log.debug("sql : " + countSql);
        log.debug("params : " + JsonHelper.toJSonString(wheres));

        return   context.getNamedParameterJdbcTemplate().queryForObject(sql, wheres,new SingleColumnRowMapper<Long>());
    }

}

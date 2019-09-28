package com.bdp.jdbc.db.cmd;

import com.bdp.helper.BaseHelper;
import com.bdp.helper.Constant;
import com.bdp.helper.JsonHelper;
import com.bdp.jdbc.base.cmd.BaseEntityCmd;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.JdbcContext;
import com.bdp.jdbc.db.QueryItem;
import com.bdp.jdbc.db.WhereResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class BasePaginationCommand<E extends Entity> extends BaseEntityCmd<E,Map<String,Object>> {
    private List<QueryItem> params;
    private Integer page;
    private Integer pageSize;
    private String orderBy;
    private String sql;
    private String queryString;
    private List<String> notWhere=new ArrayList<>();

    public BasePaginationCommand(String sql,String queryString,List<QueryItem> params,Integer page,Integer pageSize,String orderBy,List<String> notWhere){
    	super();
    	this.params=params;
    	this.sql = sql;
    	this.queryString=queryString;
    	this.page = page;
    	this.pageSize=pageSize;
    	this.orderBy=orderBy;
    	this.notWhere = notWhere;
    }


    @Override
    public Map<String,Object> execute(JdbcContext context) throws Exception {

	    //替换表名
        String tableName = getTableName();
	    sql = sql.replace("[TABLE]", tableName);

	    Map<String,Object> paramsMap = new HashMap<>();
	    if(params != null && params.size()>0){
            for (int i = 0; i < params.size(); i++) {
                QueryItem queryItem = params.get(i);
                paramsMap.put(queryItem.getKey(),queryItem);
            }
        }
        WhereResult whereResult =  getWhere(paramsMap,notWhere);
	    //替换queryString
        String query = getMapQuery();
        queryString += query;
        sql = sql.replace("[queryString]", queryString);

	    //处理wheres查询条件
	    Map<String,Object> wheres = whereResult.getWheres();
	    sql = sql + whereResult.getSql();

	    //查询总数
        Long total= getCount(context,sql,wheres);
        //查询列表
        List<Map<String,Object>> list = getList(context,sql,wheres);


        Map<String,Object> result = new HashMap<>();
        result.put(Constant.TOTAL,total);
        result.put(Constant.ROWS,list);
        return result;
    }



    private List<Map<String,Object>>  getList(JdbcContext context, String sql,Map<String,Object> wheres) throws Exception {

	    // 排序
	    if(BaseHelper.isNotEmpty(orderBy)){
		    sql += orderBy;
	    }
	    sql += " limit " + ((page -1) * pageSize ) +"," + pageSize;

        log.debug("listSql : " + sql);
        log.debug("params : " + JsonHelper.toJSonString(wheres));

        List<Map<String,Object>>   list = context.getNamedParameterJdbcTemplate().queryForList(sql,wheres);
        return list;

    }

    private Long getCount(JdbcContext context,String sql,Map<String,Object> wheres) throws Exception {
        String countSql = "select count(*) cnt from ("+ sql +") v";

        log.debug("countSql : " + countSql);
        log.debug("params : " + JsonHelper.toJSonString(wheres));

        Long count = context.getNamedParameterJdbcTemplate().queryForObject(countSql,wheres,new SingleColumnRowMapper<Long>());
        return count;
    }
}

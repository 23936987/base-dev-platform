package com.bdp.jdbc.db.cmd;

import com.bdp.helper.*;
import com.bdp.jdbc.base.cmd.BaseEntityCmd;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.JdbcContext;
import com.bdp.jdbc.db.QueryItem;
import com.bdp.jdbc.db.WhereResult;
import com.bdp.jdbc.helper.BeanHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasePaginationCommand<V,E extends Entity> extends BaseEntityCmd<E, Pager<V>> {
    private static Logger logger = LoggerFactory.getLogger(BasePaginationCommand.class);
    private List<QueryItem> params;
    private Integer page;
    private Integer pageSize;
    private String orderBy;
    private String sql;
    private String queryString;
    private Class<V> clazz;
    private List<String> notWhere=new ArrayList<>();

    public void setClazz(Class<V> clazz,Class<E> entityClass) {
        this.clazz = clazz;
        super.setClazz(entityClass);
    }

    public BasePaginationCommand(String sql, String queryString, Pager pager, List<String> notWhere){
    	super();
    	this.params=pager.getParams();
    	this.sql = sql;
    	this.queryString=queryString;
    	this.page = pager.getPage();
    	this.pageSize=pager.getPageSize();
    	this.orderBy=pager.getOrderBy();
    	this.notWhere = notWhere;
    }


    @Override
    public  Pager<V> execute(JdbcContext context) throws Exception {

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
        List<V> list = getList(context,sql,wheres);

        Pager<V> result = new Pager<>();
        result.setTotal(total);
        result.setRows(list);
        return result;
    }



    private  List<V>  getList(JdbcContext context, String sql,Map<String,Object> wheres) throws Exception {

	    // 排序
	    if(BaseHelper.isNotEmpty(orderBy)){
		    sql += orderBy;
	    }
	    sql += " limit " + ((page -1) * pageSize ) +"," + pageSize;

        logger.debug("listSql : " + sql);
        logger.debug("params : " + JsonHelper.toJSonString(wheres));

        List<V>   list = context.getNamedParameterJdbcTemplate().query(sql,wheres, BeanHelper.getVoRowMapper(clazz));
        return list;

    }

    private Long getCount(JdbcContext context,String sql,Map<String,Object> wheres) throws Exception {
        String countSql = "select count(*) cnt from ("+ sql +") v";

        logger.debug("countSql : " + countSql);
        logger.debug("params : " + JsonHelper.toJSonString(wheres));

        Long count = context.getNamedParameterJdbcTemplate().queryForObject(countSql,wheres,new SingleColumnRowMapper<Long>());
        return count;
    }


}

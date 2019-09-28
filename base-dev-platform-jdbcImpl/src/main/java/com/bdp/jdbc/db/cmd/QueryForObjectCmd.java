package com.bdp.jdbc.db.cmd;

import com.bdp.helper.JsonHelper;
import com.bdp.jdbc.db.JdbcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

public class QueryForObjectCmd<V> extends BaseCmd<V,V> {
    private static Logger logger = LoggerFactory.getLogger(QueryForObjectCmd.class);
    private String sql;
    private Map<String,Object> wheres;
    public QueryForObjectCmd(String sql, Map<String, Object> wheres, Class<V> clazz){
        this.sql = sql;
        this.wheres = wheres;
        this.clazz = clazz;
    }

    @Override
    public V execute(JdbcContext context) throws Exception {
        logger.debug("sql : " + sql);
        logger.debug("wheres : " + JsonHelper.toJSonString(wheres));

        V result = context.getNamedParameterJdbcTemplate().queryForObject(sql, wheres, getVoRowMapper());
        logger.debug("result : " + result);
        return result;
    }
}

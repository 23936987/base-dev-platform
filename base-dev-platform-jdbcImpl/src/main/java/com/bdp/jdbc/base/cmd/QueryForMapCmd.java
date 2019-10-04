package com.bdp.jdbc.base.cmd;

import com.bdp.helper.JsonHelper;
import com.bdp.jdbc.db.JdbcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import java.util.Map;


public class QueryForMapCmd implements Command<Map<String,Object>> {
    private static Logger logger = LoggerFactory.getLogger(QueryForMapCmd.class);
    private String sql;
    private Map<String,Object> wheres;

    public QueryForMapCmd(String sql, Map<String, Object> wheres){
        this.sql = sql;
        this.wheres = wheres;
    }

    @Override
    public Map<String,Object> execute(JdbcContext context) throws Exception {
        logger.debug("sql : " + sql);
        logger.debug("wheres : " + JsonHelper.toJSonString(wheres));

        Map<String,Object> result = context.getNamedParameterJdbcTemplate().queryForObject(sql,wheres,new SingleColumnRowMapper<Map>());
        logger.debug("result : " + result);
        return result;
    }
}

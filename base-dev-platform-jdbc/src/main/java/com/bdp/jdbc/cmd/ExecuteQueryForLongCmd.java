package com.bdp.jdbc.cmd;

import com.bdp.helper.JsonHelper;
import com.bdp.jdbc.db.JdbcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class ExecuteQueryForLongCmd implements Command<Long> {
    private static Logger logger = LoggerFactory.getLogger(ExecuteQueryForLongCmd.class);
    private String sql;
    private Map<String,Object> wheres;

    public ExecuteQueryForLongCmd(String sql, Map<String, Object> wheres){
        this.sql = sql;
        this.wheres = wheres;
    }

    @Override
    public Long execute(JdbcContext context) throws Exception {
        logger.debug("sql : " + sql);
        logger.debug("wheres : " + JsonHelper.toJSonString(wheres));

        Long result;
        result = context.getNamedParameterJdbcTemplate().queryForObject(sql,wheres,Long.class);
        logger.debug("result : " + result);
        return result;
    }
}

package com.bdp.jdbc.cmd;

import com.bdp.helper.JsonHelper;
import com.bdp.jdbc.db.JdbcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

public class ExecuteQueryForObjectCmd<T> extends BaseCmd<T,T> {
    private static Logger logger = LoggerFactory.getLogger(ExecuteQueryForObjectCmd.class);
    private String sql;
    private Map<String,Object> wheres;
    public ExecuteQueryForObjectCmd(String sql, Map<String, Object> wheres,Class<T> clazz){
        this.sql = sql;
        this.wheres = wheres;
        this.clazz = clazz;
    }

    @Override
    public T execute(JdbcContext context) throws Exception {
        logger.debug("sql : " + sql);
        logger.debug("wheres : " + JsonHelper.toJSonString(wheres));

        T result = context.getNamedParameterJdbcTemplate().queryForObject(sql, wheres, getVoRowMapper());
        logger.debug("result : " + result);
        return result;
    }
}

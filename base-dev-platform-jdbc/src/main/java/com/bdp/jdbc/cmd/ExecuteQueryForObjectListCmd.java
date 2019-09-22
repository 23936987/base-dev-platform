package com.bdp.jdbc.cmd;

import com.bdp.helper.JsonHelper;
import com.bdp.jdbc.db.JdbcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class ExecuteQueryForObjectListCmd<T> extends BaseCmd<T,List<T>> {
    private static Logger logger = LoggerFactory.getLogger(ExecuteQueryForObjectListCmd.class);
    private String sql;
    private Map<String,Object> wheres;
    public ExecuteQueryForObjectListCmd(String sql, Map<String, Object> wheres, Class<T> clazz){
        this.sql = sql;
        this.wheres = wheres;
        this.clazz = clazz;
    }

    @Override
    public List<T> execute(JdbcContext context) throws Exception {
        logger.debug("sql : " + sql);
        logger.debug("wheres : " + JsonHelper.toJSonString(wheres));

        List<T> result = context.getNamedParameterJdbcTemplate().queryForObject(sql, wheres, getVoRowMapper());
        logger.debug("result : " + result);
        return result;
    }
}

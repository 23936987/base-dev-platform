package com.bdp.jdbc.db.cmd;

import com.bdp.helper.JsonHelper;
import com.bdp.jdbc.db.JdbcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class ExecuteUpdateCmd implements Command<Integer> {
    private static Logger logger = LoggerFactory.getLogger(ExecuteUpdateCmd.class);
    private String sql;
    private Map<String,Object> wheres;

    public ExecuteUpdateCmd(String sql, Map<String, Object> wheres){
        this.sql = sql;
        this.wheres = wheres;
    }

    @Override
    public Integer execute(JdbcContext context) throws Exception {

        logger.debug("sql : " + sql);
        logger.debug("wheres : " + JsonHelper.toJSonString(wheres));

        Integer result  = context.getNamedParameterJdbcTemplate().update(sql,wheres);
        return result;
    }
}

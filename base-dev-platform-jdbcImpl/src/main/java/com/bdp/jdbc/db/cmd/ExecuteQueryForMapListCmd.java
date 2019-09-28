package com.bdp.jdbc.db.cmd;

import com.bdp.helper.JsonHelper;
import com.bdp.jdbc.db.JdbcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


public class ExecuteQueryForMapListCmd implements Command<List<Map<String,Object>>> {
    private static Logger logger = LoggerFactory.getLogger(ExecuteQueryForMapListCmd.class);
    private String sql;
    private Map<String,Object> wheres;

    public ExecuteQueryForMapListCmd(String sql, Map<String, Object> wheres){
        this.sql = sql;
        this.wheres = wheres;
    }

    @Override
    public List<Map<String,Object>> execute(JdbcContext context) throws Exception {
        logger.debug("sql : " + sql);
        logger.debug("wheres : " + JsonHelper.toJSonString(wheres));

        List<Map<String,Object>> result= context.getNamedParameterJdbcTemplate().queryForList(sql,wheres);
        logger.debug("result : " + result);
        return result;
    }
}

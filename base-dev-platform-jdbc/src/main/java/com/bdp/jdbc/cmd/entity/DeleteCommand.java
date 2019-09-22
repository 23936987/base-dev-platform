package com.bdp.jdbc.cmd.entity;

import com.bdp.helper.JsonHelper;
import com.bdp.jdbc.db.Entity;
import com.bdp.jdbc.db.JdbcContext;
import com.bdp.jdbc.db.WhereResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DeleteCommand<E extends Entity> extends EntityCommand<E,Integer,E> {
    private static Logger logger = LoggerFactory.getLogger(DeleteCommand.class);
    private Map<String,Object> wheres;

    public DeleteCommand(Map<String, Object> wheres){
        this.wheres = wheres;
    }
    @Override
    public Integer execute(JdbcContext context) throws Exception {

        String  sql = "delete t from [TABLE] t where 1=1";

        String tableName = getTableName();
        sql = sql.replace("[TABLE]",tableName);

        WhereResult whereResult =  getWhere(wheres);

        Map<String,Object> wheres = whereResult.getWheres();
        sql += whereResult.getSql();

        logger.debug("sql : " + sql);
        logger.debug("wheres : " + JsonHelper.toJSonString(wheres));

        Integer result;
        result = context.getNamedParameterJdbcTemplate().update(sql,wheres);
        return result;
    }

}

package com.bdp.jdbc.base.cmd;

import com.bdp.helper.BaseHelper;
import com.bdp.helper.JsonHelper;
import com.bdp.helper.ReflectionHelper;
import com.bdp.helper.StringHelper;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.JdbcContext;
import com.bdp.jdbc.helper.BeanHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BaseQueryBySqlCmd<E extends Entity> extends BaseEntityCmd<E, List<E>> {
    private static Logger logger = LoggerFactory.getLogger(BaseQueryBySqlCmd.class);

    private Map<String,Object> wheres;
    private String sql;

    public BaseQueryBySqlCmd(String sql, Map<String, Object> wheres){
        this.wheres = wheres;
        this.sql = sql;
    }

    @Override
    public List<E> execute(JdbcContext context) throws Exception {


        String query = getQuery();
        sql = sql.replace("[queryString]",query.substring(1));

        String tableName = getTableName();
        sql = sql.replace("[TABLE]",tableName);

        logger.debug("sql : " + sql);
        logger.debug("params : " + JsonHelper.toJSonString(wheres));

        List<E> list = context.getNamedParameterJdbcTemplate().query(sql, wheres, BeanHelper.getVoRowMapper(entityClass));
        logger.debug("result : " + JsonHelper.toJSonString(list));
        return list;
    }
}

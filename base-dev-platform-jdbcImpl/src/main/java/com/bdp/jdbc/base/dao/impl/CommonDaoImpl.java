package com.bdp.jdbc.base.dao.impl;

import com.bdp.jdbc.base.dao.CommonDao;
import com.bdp.jdbc.db.JdbcContext;
import com.bdp.jdbc.db.cmd.QueryForMapCmd;
import com.bdp.jdbc.db.cmd.QueryForMapListCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 *
 * @ClassName: CommonDaoImpl
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/10/2 12:44
 * @version : 1.0
 */
@Repository
public class CommonDaoImpl implements CommonDao {
    @Autowired
    @Qualifier("primaryJdbcContext")
    private JdbcContext jdbcContext;

    @Override
    public List<Map<String, Object>> queryForList(String sql,Map<String, Object> wheres) throws Exception{
        QueryForMapListCmd cmd = new QueryForMapListCmd(sql,wheres);
        List<Map<String,Object>> list =  cmd.execute(jdbcContext);
        return list;
    }

    @Override
    public Map<String, Object> queryForMap(String sql,Map<String, Object> wheres)throws Exception {
        QueryForMapCmd cmd = new QueryForMapCmd(sql,wheres);
        return cmd.execute(jdbcContext);
    }
}




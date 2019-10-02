package com.bdp.jdbc.cache.domain;

import com.bdp.jdbc.base.dao.CommonDao;
import com.bdp.jdbc.db.Domain;
import com.bdp.jdbc.db.JdbcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *
 * @ClassName: DictListDomain
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/10/2 12:04
 * @version : 1.0
 */
@Component
public class DictsDomain implements Domain {
    @Autowired
    private CommonDao commonDao;
    private JdbcContext jdbcContext;

    public List<Map<String, Object>> queryForList() throws Exception{
        String sql = "select code,name from sys_dict where types=1";
        Map<String,Object> wheres = new HashMap<>();
        return commonDao.queryForList(sql,wheres);
    }
}




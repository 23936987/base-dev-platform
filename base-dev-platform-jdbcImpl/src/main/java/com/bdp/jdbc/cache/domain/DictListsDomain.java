package com.bdp.jdbc.cache.domain;

import com.bdp.jdbc.base.dao.CommonDao;
import com.bdp.jdbc.base.domain.Domain;
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
public class DictListsDomain implements Domain {

    @Autowired
    private CommonDao commonDao;

    public List<Map<String, Object>> queryForList(String dictCode) throws Exception{
        String sql = "select b.code,b.name from sys_dict a join sys_dict_list b where a.id=b.dict_id and a.code=:dictCode";
        Map<String,Object> wheres = new HashMap<>();
        wheres.put("dictCode",dictCode);
        return commonDao.queryForList(sql,wheres);
    }
}




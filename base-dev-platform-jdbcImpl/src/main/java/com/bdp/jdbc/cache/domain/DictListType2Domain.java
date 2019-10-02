package com.bdp.jdbc.cache.domain;

import com.bdp.jdbc.base.dao.CommonDao;
import com.bdp.jdbc.db.Domain;
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
public class DictListType2Domain implements Domain {

    @Autowired
    private CommonDao commonDao;
    public String queryName(String dictCode,String code) throws Exception{
        String sql = "select table_name tableName,value_field valueField,name_field nameField from sys_dict where types=2 and code=''";
        Map<String,Object> wheres = new HashMap<>();
        wheres.put("dictCode",dictCode);

        Map<String,Object> item =  commonDao.queryForMap(sql,wheres);

        String tableName = String.valueOf(item.get("tableName"));
        String valueField = String.valueOf(item.get("valueField"));
        String nameField = String.valueOf(item.get("nameField"));

        String itemSql = "select "+nameField+" name from "+tableName+" where "+valueField+"=:code";
        Map<String,Object> itemWheres = new HashMap<>();
        itemWheres.put("code",code);
        List<Map<String,Object>> list = commonDao.queryForList(itemSql,itemWheres);
        if(list != null){
            return (String) list.get(0).get("name");
        }
        return "";
    }
}




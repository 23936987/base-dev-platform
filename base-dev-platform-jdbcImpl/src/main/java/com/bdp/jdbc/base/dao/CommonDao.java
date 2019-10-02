package com.bdp.jdbc.base.dao;

import java.util.List;
import java.util.Map;

public interface CommonDao {
    List<Map<String,Object>> queryForList(String sql,Map<String,Object> wheres) throws Exception;
    Map<String,Object> queryForMap(String sql,Map<String,Object> wheres) throws Exception;
}

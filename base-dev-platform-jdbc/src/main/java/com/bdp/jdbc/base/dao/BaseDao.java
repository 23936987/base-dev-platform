package com.bdp.jdbc.base.dao;

import com.bdp.jdbc.base.entity.Entity;

import java.util.List;
import java.util.Map;

public interface BaseDao<E extends Entity> {
    /**************save**************start********************/
    String save(E entity) throws Exception;
    String save(List<Entity> list) throws Exception;
    /**************save**************end********************/

    /**************update**************start********************/
    Integer update(String id, Object... array) throws Exception;
    Integer update(List<Long> ids, Object... array) throws Exception;
    Integer update(Map<String, Object> props, String id) throws Exception;
    Integer update(Map<String, Object> props, List<String> ids) throws Exception;
    Integer update(Map<String, Object> props, Object... array) throws Exception;
    Integer update(Map<String, Object> props, Map<String, Object> wheres) throws Exception;
    Integer update(E entity) throws Exception;
    /**************update**************end********************/

    /**************delete**************start********************/
    Integer delete(String id) throws Exception;
    Integer delete(List<String> ids) throws Exception;
    Integer delete(Map<String, Object> wheres) throws Exception;
    Integer delete(Object... array) throws Exception;
    Integer delete(E entity) throws Exception;
    /**************delete**************end********************/

    /**************queryForObject**************start********************/

    E queryForObject(String id) throws Exception;
    E queryForObject(Object... array) throws Exception;
    E queryForObject(Map<String, Object> wheres) throws Exception;
    /**************queryForObject**************end********************/

    /**************queryForList**************start********************/
    List<E> queryForList(Map<String, Object> wheres) throws Exception;
    List<E> queryForList(Map<String, Object> wheres, Integer pageNum, Integer pageSize) throws Exception;

    List<E> queryForList(Object... array) throws Exception;
    List<E> queryForList(Integer pageNum, Integer pageSize, Object... array) throws Exception;
    /**************queryForList**************end********************/

    /**************queryForObjectBySql**************start********************/
    E queryForObjectBySql(String sql, Map<String, Object> wheres) throws Exception;
    List<E> queryForListBySql(String sql, Map<String, Object> wheres) throws Exception;
    List<E> queryForListBySql(String sql, Map<String, Object> wheres, Integer pageNum, Integer pageSize) throws Exception;

    /**************queryForObjectBySql**************end********************/


    /**************pagination**************start********************/
    Long count(String sql, Map<String, Object> wheres)  throws Exception;
    /**************pagination**************end********************/


    /**************executeUpdate**************start********************/
    Integer executeUpdate(String sql, Map<String, Object> wheres) throws Exception;
    //执行sql更新
    //Object ... array
    Integer executeUpdate(String sql, Object... array) throws Exception;
    /**************executeUpdate**************end********************/

}

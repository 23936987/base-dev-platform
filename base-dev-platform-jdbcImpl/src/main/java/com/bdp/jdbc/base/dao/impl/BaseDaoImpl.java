package com.bdp.jdbc.base.dao.impl;

import com.bdp.exception.Assert;
import com.bdp.helper.BaseHelper;
import com.bdp.helper.Constant;
import com.bdp.helper.PrimaryKeyHelper;
import com.bdp.helper.ReflectionHelper;
import com.bdp.jdbc.base.cmd.*;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.JdbcContext;
import com.bdp.jdbc.db.cmd.ExecuteQueryForLongCmd;
import com.bdp.jdbc.db.cmd.ExecuteUpdateCmd;
import com.bdp.jdbc.helper.BeanHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *
 * @ClassName: BaseDaoImpl
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/9/23 22:20
 * @version : 1.0
 */

public abstract class BaseDaoImpl<E extends Entity>  implements BaseDao<E> {

    protected Class<E> entityClass = ReflectionHelper.getClassGenricType(getClass());
    protected JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }


    /**************save**************start********************/
    @Override
    public String save(E entity) throws Exception {
        Assert.isNotNull(entity,"entity不为空");
        String id = PrimaryKeyHelper.getUUID32();
        entity.setId(id);

        SaveCmd cmd = new SaveCmd<>(entity);
        cmd.setClazz(entityClass);
        cmd.execute(jdbcContext);
        return id;
    }

    @Override
    public String save(List<Entity> list) throws Exception {
        Assert.isTrue(list != null && list.size()>0,"entity不为空");
        SaveListCmd cmd = new SaveListCmd<>(list);
        cmd.setClazz(entityClass);
        return  cmd.execute(jdbcContext);
    }
    /**************save**************end********************/
    @Override
    public Integer update(String id, Object... array) throws Exception {
        Assert.isTrue(array != null && array.length>0,"array为空");
        Assert.isNotNull(array.length % 2 == 0,"array格式错误");

        Map<String, Object> props = BaseHelper.array2map(array);
        return update(props, id);
    }

    @Override
    public Integer update(List<Long> ids, Object... array) throws Exception {
        Assert.isTrue(ids != null && ids.size() > 0,  "ids为空");
        Assert.isTrue(array != null && array.length>0,"array为空");
        Assert.isNotNull(array.length % 2 == 0, "array格式错误");
        Map<String, Object> props =  BaseHelper.array2map(array);

        for (int i = 0; i < ids.size(); i++) {
            Long id = ids.get(i);
            update(props,id);
        }
        return ids.size();
    }

    @Override
    public Integer update(Map<String, Object> props, String id) throws Exception {
        Assert.isTrue(props != null && props.size() > 0, "props为空");
        Assert.isNotNull(id, "id为空");

        Map<String, Object> wheres = new HashMap<>();
        wheres.put(Constant.ID, id);
        return update(props, wheres);
    }

    @Override
    public Integer update(Map<String, Object> props, List<String> ids) throws Exception {
        Assert.isTrue(props != null && props.size() > 0, "props为空");
        Assert.isTrue(ids != null && ids.size() > 0,  "ids为空");

        for (int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            update(props,id);
        }
        return ids.size();
    }

    @Override
    public Integer update(Map<String, Object> props, Object... array) throws Exception {
        Assert.isTrue(props != null && props.size() > 0, "props为空");
        Assert.isTrue(array != null && array.length>0,"array为空");
        Assert.isTrue(array.length % 2 == 0, "array格式错误");
        Map<String, Object> wheres = BaseHelper.array2map(array);

        return update(props, wheres);
    }

    @Override
    public Integer update(Map<String, Object> props, Map<String, Object> wheres) throws Exception {
        Assert.isTrue(props != null && props.size() > 0, "props为空");
        Assert.isTrue(wheres != null && wheres.size() > 0, "wheres为空");

        UpdateCmd cmd = new UpdateCmd<E>(props, wheres);
        cmd.setClazz(entityClass);
        return cmd.execute(jdbcContext);
    }

    @Override
    public Integer update(E entity) throws Exception {
        Map<String,Object> props = BeanHelper.obj2Map(entity);
        return update(props,entity.getId());
    }

    @Override
    public Integer delete(String id) throws Exception {
        Assert.isNotNull(id,"id为空");
        return delete(Constant.ID, id);
    }

    @Override
    public Integer delete(List<String> ids) throws Exception {
        Assert.isTrue(ids != null && ids.size() > 0,  "ids为空");
        for (int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            delete(id);
        }
        return ids.size();
    }

    @Override
    public Integer delete(Map<String, Object> wheres) throws Exception {
        Assert.isTrue(wheres != null && wheres.size()>0,"wheres为空");

        DeleteCmd cmd = new DeleteCmd<>(wheres);
        cmd.setClazz(entityClass);
        return cmd.execute(jdbcContext);
    }

    @Override
    public Integer delete(Object... array) throws Exception {
        Assert.isNotNull(array, "array为空");
        Assert.isTrue(array.length % 2 == 0, "array格式错误");

        Map<String, Object> wheres = BaseHelper.array2map(array);
        return delete(wheres);
    }

    @Override
    public Integer delete(E entity) throws Exception {
        Assert.isNotNull(entity, "entity为空");
        Map<String,Object> wheres = BeanHelper.obj2Map(entity);
        return delete(wheres);
    }

    @Override
    public E queryForObject(String id) throws Exception {
        Assert.isNotNull(id,"id为空");

        return queryForObject(Constant.ID, id);
    }

    @Override
    public E queryForObject(Object... array) throws Exception {
        Assert.isNotNull(array,"array为空");
        Assert.isTrue( array.length % 2 == 0, "array格式错误");

        Map<String, Object> wheres = BaseHelper.array2map(array);
        List<E> list = queryForList(wheres);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public E queryForObject(Map<String, Object> wheres) throws Exception {
        Assert.isTrue(wheres != null && wheres.size()>0,"wheres为空");

        List<E> list = queryForList(wheres);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<E> queryForList(Map<String, Object> wheres) throws Exception {
        Assert.isTrue(wheres != null && wheres.size()>0,"wheres为空");

         QueryEntityCmd cmd = new QueryEntityCmd(wheres);
         cmd.setClazz(entityClass);
         return cmd.execute(jdbcContext);
    }

    @Override
    public List<E> queryForList(Map<String, Object> wheres, Integer pageNum, Integer pageSize) throws Exception {
        Assert.isTrue(wheres != null && wheres.size()>0,"wheres为空");


        QueryEntityCmd cmd = new QueryEntityCmd(wheres,pageNum, pageSize);
        cmd.setClazz(entityClass);
        return cmd.execute(jdbcContext);

    }

    @Override
    public List<E> queryForList(Object... array) throws Exception {
        Assert.isNotNull(array,"array为空");
        Assert.isTrue(array.length % 2 == 0,"array格式错误");

        Map<String, Object> wheres = BaseHelper.array2map(array);
        return queryForList(wheres);
    }

    @Override
    public List<E> queryForList(Integer pageNum, Integer pageSize, Object... array) throws Exception {
        Assert.isNotNull(array, "array为空");
        Assert.isTrue(array != null&& array.length % 2 == 0, "array格式错误");
        Map<String, Object> wheres =  BaseHelper.array2map(array);
        return queryForList(wheres, pageNum, pageSize);
    }

    @Override
    public E queryForObjectBySql(String sql, Map<String, Object> wheres) throws Exception {
        Assert.isNotNull(sql,"sql为空");
        Assert.isTrue(wheres != null && wheres.size()>0,"wheres为空");


        List<E> list = this.queryForListBySql(sql, wheres);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<E> queryForListBySql(String sql, Map<String, Object> wheres) throws Exception {
        Assert.isNotNull(sql,"sql为空");
        Assert.isTrue(wheres != null && wheres.size()>0,"wheres为空");


        QueryBySqlCmd cmd = new QueryBySqlCmd<>(sql, wheres);
        cmd.setClazz(entityClass);
        return cmd.execute(jdbcContext);
    }

    @Override
    public List<E> queryForListBySql(String sql, Map<String, Object> wheres, Integer pageNum, Integer pageSize) throws Exception {
        Assert.isNotNull(sql,"sql为空");
        Assert.isTrue(wheres != null && wheres.size()>0,"wheres为空");


        int begin = (pageNum - 1) * pageSize;
        sql += " limit " + begin + "," + pageSize;
        List<E> list = queryForList(sql, wheres);
        return list;
    }


    @Override
    public <V> V queryVoForObjectBySql(String sql, Map<String, Object> wheres, Class<V> vClass) throws Exception {
        Assert.isNotNull(sql,"sql为空");
        Assert.isTrue(wheres != null && wheres.size()>0,"wheres为空");


        QueryVoCmd cmd = new QueryVoCmd(sql,wheres);
        cmd.setClazz(vClass,entityClass);
        List<V> list =  cmd.execute(jdbcContext);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public <V> List<V> queryVoForListBySql(String sql, Map<String, Object> wheres, Class<V> vClass) throws Exception {
        Assert.isNotNull(sql,"sql为空");
        Assert.isTrue(wheres != null && wheres.size()>0,"wheres为空");


        QueryVoCmd cmd = new QueryVoCmd(sql,wheres);
        cmd.setClazz(vClass,entityClass);
        return  cmd.execute(jdbcContext);
    }

    @Override
    public <V> List<V> queryVoForListBySql(String sql, Map<String, Object> wheres, Integer pageNum, Integer pageSize, Class<V> vClass) throws Exception {
        Assert.isNotNull(sql,"sql为空");
        Assert.isTrue(wheres != null && wheres.size()>0,"wheres为空");


        int begin = (pageNum - 1) * pageSize;
        sql += " limit " + begin + "," + pageSize;
        return  queryVoForListBySql(sql, wheres,vClass);
    }

    @Override
    public Long count(String sql, Map<String, Object> wheres) throws Exception {
        Assert.isTrue(wheres != null && wheres.size()>0,"wheres为空");

        sql = sql.replace("[queryString]", "count(*)");
        ExecuteQueryForLongCmd cmd = new ExecuteQueryForLongCmd(sql, wheres);
        return cmd.execute(jdbcContext);
    }

    @Override
    public Integer executeUpdate(String sql, Map<String, Object> wheres) throws Exception {
        Assert.isTrue(wheres != null && wheres.size()>0,"wheres为空");
        ExecuteUpdateCmd cmd = new ExecuteUpdateCmd(sql,wheres);
        return cmd.execute(jdbcContext);
    }

    @Override
    public Integer executeUpdate(String sql, Object... array) throws Exception {
        Assert.isTrue(array != null && array.length>0, "array为空");
        Assert.isTrue(array.length % 2 == 0, "array格式错误");

        Map<String, Object> wheres =  BaseHelper.array2map(array);
        return executeUpdate(sql,wheres);
    }
}




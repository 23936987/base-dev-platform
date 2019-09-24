package com.bdp.jdbc.base.dao.impl;

import com.bdp.exception.Assert;
import com.bdp.helper.BaseHelper;
import com.bdp.helper.Constant;
import com.bdp.helper.PrimaryKeyHelper;
import com.bdp.helper.ReflectionHelper;
import com.bdp.jdbc.base.cmd.SaveListCommand;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.entity.Entity;
import com.bdp.jdbc.db.cmd.Command;
import com.bdp.jdbc.base.cmd.EntityCommand;
import com.bdp.jdbc.base.cmd.SaveCommand;
import com.bdp.jdbc.db.JdbcContext;

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

    public <T> T executeEntity(Command<T> command) throws Exception {
        EntityCommand entityCommand = (EntityCommand) command;
        entityCommand.setClazz(entityClass,entityClass);
        return command.execute(jdbcContext);
    }
    public <T> T executeCommand(Command<T> command) throws Exception {
        return command.execute(jdbcContext);
    }
    /**************save**************start********************/
    @Override
    public String save(E entity) throws Exception {
        String id = PrimaryKeyHelper.getUUID32();
        entity.setId(id);
        executeEntity(new SaveCommand<>(entity));
        return id;
    }

    @Override
    public String save(List<Entity> list) throws Exception {
        executeEntity(new SaveListCommand<>(list));
        return executeEntity(new SaveListCommand<>(list));
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
        Assert.isNotNull(array.length % 2 == 0, "array格式错误");
        Map<String, Object> wheres = BaseHelper.array2map(array);

        return update(props, wheres);
    }

    @Override
    public Integer update(Map<String, Object> props, Map<String, Object> wheres) throws Exception {
        return null;
    }

    @Override
    public Integer update(E entity) throws Exception {
        return null;
    }

    @Override
    public Integer delete(String id) throws Exception {
        return null;
    }

    @Override
    public Integer delete(List<String> ids) throws Exception {
        return null;
    }

    @Override
    public Integer delete(Map<String, Object> wheres) throws Exception {
        return null;
    }

    @Override
    public Integer delete(Object... array) throws Exception {
        return null;
    }

    @Override
    public Integer delete(E entity) throws Exception {
        return null;
    }

    @Override
    public E queryForObject(String id) throws Exception {
        return null;
    }

    @Override
    public E queryForObject(Object... array) throws Exception {
        return null;
    }

    @Override
    public E queryForObject(Map<String, Object> wheres) throws Exception {
        return null;
    }

    @Override
    public List<E> queryForList(Map<String, Object> wheres) throws Exception {
        return null;
    }

    @Override
    public List<E> queryForList(Map<String, Object> wheres, Integer pageNum, Integer pageSize) throws Exception {
        return null;
    }

    @Override
    public List<E> queryForList(Object... array) throws Exception {
        return null;
    }

    @Override
    public List<E> queryForList(Integer pageNum, Integer pageSize, Object... array) throws Exception {
        return null;
    }

    @Override
    public E queryForObjectBySql(String sql, Map<String, Object> wheres) throws Exception {
        return null;
    }

    @Override
    public List<E> queryForListBySql(String sql, Map<String, Object> wheres) throws Exception {
        return null;
    }

    @Override
    public List<E> queryForListBySql(String sql, Map<String, Object> wheres, Integer pageNum, Integer pageSize) throws Exception {
        return null;
    }

    @Override
    public Long count(String sql, Map<String, Object> wheres) throws Exception {
        return null;
    }

    @Override
    public Integer executeUpdate(String sql, Map<String, Object> wheres) throws Exception {
        return null;
    }

    @Override
    public Integer executeUpdate(String sql, Object... array) throws Exception {
        return null;
    }
}




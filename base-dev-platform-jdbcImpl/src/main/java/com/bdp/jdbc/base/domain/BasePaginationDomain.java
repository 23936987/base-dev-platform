package com.bdp.jdbc.base.domain;

import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.JdbcContext;
import com.bdp.jdbc.db.QueryItem;
import com.bdp.jdbc.db.cmd.BasePaginationCommand;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasePaginationDomain<V,E extends Entity>  extends BaseDomain<E> {
    @Autowired
    protected JdbcContext jdbcContext;
    protected Class<V> clazz;
    protected Class<E> entityClass;
    public Map<String,Object> pagination(List<QueryItem> params, Integer page, Integer pageSize, String orderBy) throws Exception {
        String sql = "select [queryString] from [TABLE]  t where 1=1 ";
        String queryString="1 a";
        List<String> notWhere = new ArrayList<>();
        BasePaginationCommand<E> cmd = new BasePaginationCommand<E>(sql,queryString,params,page,pageSize,orderBy,notWhere);
        cmd.setClazz(entityClass);
        return cmd.execute(jdbcContext);
    }
}

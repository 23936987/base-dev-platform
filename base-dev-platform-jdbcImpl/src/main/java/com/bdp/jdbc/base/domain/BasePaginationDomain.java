package com.bdp.jdbc.base.domain;

import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.JdbcContext;
import com.bdp.jdbc.db.cmd.BasePaginationCommand;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BasePaginationDomain<V,E extends Entity>  extends BaseDomain<E> {
    @Autowired
    protected JdbcContext jdbcContext;
    protected Class<V> clazz;
    protected Class<E> entityClass;
    public Pager<V> pagination(Pager pager) throws Exception {
        String sql = "select [queryString] from [TABLE]  t where 1=1 ";
        String queryString="1 a";
        List<String> notWhere = new ArrayList<>();
        BasePaginationCommand<V,E> cmd = new BasePaginationCommand<V,E>(sql,queryString,pager,notWhere);
        cmd.setClazz(clazz,entityClass);
        return cmd.execute(jdbcContext);
    }
}

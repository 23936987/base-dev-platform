package com.bdp.jdbc.base.domain;

import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.entity.po.BaseEntity;

public abstract class BaseDomain<E extends BaseEntity>  implements Domain {
    protected BaseDao<E> dao;
    public void setDao(BaseDao<E> dao) {
        this.dao = dao;
    }
}

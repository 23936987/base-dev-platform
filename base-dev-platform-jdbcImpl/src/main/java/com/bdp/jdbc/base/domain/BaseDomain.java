package com.bdp.jdbc.base.domain;

import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.Domain;

public class BaseDomain<E extends Entity>  implements Domain {
    protected BaseDao<E> dao;
    public void setDao(BaseDao<E> dao) {
        this.dao = dao;
    }
}

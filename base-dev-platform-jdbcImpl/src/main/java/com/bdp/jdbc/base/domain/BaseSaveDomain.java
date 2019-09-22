package com.bdp.jdbc.base.domain;

import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.entity.po.Entity;

public class BaseSaveDomain<E extends Entity>  implements Domain {

    private BaseDao<E> dao;


    public void setDao(BaseDao<E> dao) {
        this.dao = dao;
    }

    public   String save(E entity) throws Exception {
        return dao.save(entity);
    }
}

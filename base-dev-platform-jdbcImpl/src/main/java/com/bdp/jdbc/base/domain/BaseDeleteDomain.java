package com.bdp.jdbc.base.domain;

import com.bdp.jdbc.base.entity.po.BaseEntity;

public abstract class BaseDeleteDomain<E extends BaseEntity>  extends BaseDomain<E> {

    public   Integer delete(String id) throws Exception {
         return dao.delete(id);
    }
}

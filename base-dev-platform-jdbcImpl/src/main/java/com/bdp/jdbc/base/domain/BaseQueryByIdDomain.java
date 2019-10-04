package com.bdp.jdbc.base.domain;

import com.bdp.exception.Assert;
import com.bdp.jdbc.base.entity.po.BaseEntity;

public abstract class BaseQueryByIdDomain<E extends BaseEntity>  extends BaseDomain<E> {

    public E queryById(String id) throws Exception {
        Assert.isNotNull(id,"id为空");
        return dao.queryForObject(id);
    }
}

package com.bdp.jdbc.base.domain;

import com.bdp.exception.Assert;
import com.bdp.jdbc.base.entity.po.BaseEntity;

public abstract class BaseSaveDomain<E extends BaseEntity>  extends BaseDomain<E> {
    public   String save(E entity) throws Exception {
        Assert.isNotNull(entity,"entity为空");
        return dao.save(entity);
    }
}

package com.bdp.jdbc.base.domain;

import com.bdp.exception.Assert;
import com.bdp.jdbc.base.entity.po.Entity;

public class BaseSaveDomain<E extends Entity>  extends BaseDomain<E> {
    public   String save(E entity) throws Exception {
        Assert.isNotNull(entity,"entity为空");
        return dao.save(entity);
    }
}

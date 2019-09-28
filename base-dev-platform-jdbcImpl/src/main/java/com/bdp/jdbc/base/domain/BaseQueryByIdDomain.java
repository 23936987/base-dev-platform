package com.bdp.jdbc.base.domain;

import com.bdp.exception.Assert;
import com.bdp.jdbc.base.entity.po.Entity;

public class BaseQueryByIdDomain<E extends Entity>  extends BaseDomain<E> {

    public E queryById(String id) throws Exception {
        Assert.isNotNull(id,"id为空");
        return dao.queryForObject(id);
    }
}

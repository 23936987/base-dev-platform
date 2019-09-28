package com.bdp.jdbc.base.domain;

import com.bdp.jdbc.base.entity.po.Entity;

public class BaseDeleteDomain<E extends Entity>  extends BaseDomain<E> {

    public   Integer delete(String id) throws Exception {
         return dao.delete(id);
    }
}

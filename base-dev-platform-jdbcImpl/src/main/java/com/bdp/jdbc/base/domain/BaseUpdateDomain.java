package com.bdp.jdbc.base.domain;

import com.bdp.jdbc.base.entity.po.BaseEntity;

import java.util.Map;

public abstract class BaseUpdateDomain<E extends BaseEntity>  extends BaseDomain<E> {

    public   Integer update(Map<String,Object> props,String id) throws Exception {
         return dao.update(props,id);
    }
}

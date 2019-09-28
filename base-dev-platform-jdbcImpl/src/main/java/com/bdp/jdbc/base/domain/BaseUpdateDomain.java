package com.bdp.jdbc.base.domain;

import com.bdp.jdbc.base.entity.po.Entity;

import java.util.Map;

public class BaseUpdateDomain<E extends Entity>  extends BaseDomain<E> {

    public   Integer update(Map<String,Object> props,String id) throws Exception {
         return dao.update(props,id);
    }
}

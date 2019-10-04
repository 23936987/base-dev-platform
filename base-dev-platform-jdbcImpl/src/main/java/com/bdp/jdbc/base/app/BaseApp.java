package com.bdp.jdbc.base.app;

import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.base.entity.po.BaseEntity;

public abstract  class BaseApp<E extends BaseEntity> implements App {

    protected BaseDomain<E> domain;
    public void setDomain(BaseDomain<E> domain) {
        this.domain = domain;
    }

}

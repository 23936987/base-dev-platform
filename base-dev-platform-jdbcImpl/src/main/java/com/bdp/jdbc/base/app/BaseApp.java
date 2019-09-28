package com.bdp.jdbc.base.app;

import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.App;

public abstract  class BaseApp<E extends Entity> implements App {

    protected BaseDomain<E> domain;
    public void setDomain(BaseDomain<E> domain) {
        this.domain = domain;
    }

}

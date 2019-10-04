package com.bdp.jdbc.base.cmd;

import com.bdp.jdbc.helper.BeanHelper;
import org.springframework.jdbc.core.RowMapper;



public abstract class BaseCmd<V,R> implements Command<R> {
    protected Class<V> clazz;

    public void setClazz(Class<V> clazz) {
        this.clazz = clazz;
    }
    protected RowMapper<V> getVoRowMapper() {
        return BeanHelper.getVoRowMapper(clazz);
    }
}

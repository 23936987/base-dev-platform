package com.bdp.jdbc.crud.domain;

import com.bdp.helper.PrimaryKeyHelper;
import com.bdp.jdbc.base.domain.BaseSaveDomain;
import com.bdp.jdbc.crud.entity.po.CrudEntity;

import java.util.Calendar;
import java.util.Date;

public abstract class CrudSaveDomain<E extends CrudEntity> extends BaseSaveDomain<E> {

    @Override
    public String save(E entity) throws Exception {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        entity.setCreateTime(now);
        entity.setModifyTime(now);
        entity.setState(1);
        entity.setState(0);
        entity.setUpdateKey(PrimaryKeyHelper.getUUID16());
        return super.save(entity);
    }
}

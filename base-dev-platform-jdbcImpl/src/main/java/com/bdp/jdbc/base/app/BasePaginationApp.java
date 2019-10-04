package com.bdp.jdbc.base.app;

import com.bdp.helper.Constant;
import com.bdp.jdbc.base.domain.BasePaginationDomain;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.po.BaseEntity;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;


public abstract class BasePaginationApp<V,E extends BaseEntity> extends BaseApp<E> {
    protected Class<V> dtoClass;

    public void setDomain(BasePaginationDomain<V,E> domain) {
        this.domain = domain;
    }

    @Override
    public ResponseContext execute(RequestContext requestDTO) throws Exception {
        ResponseContext responseContext = new ResponseContext();
        Pager pager = requestDTO.getObjectByKey(Constant.PAGER, Pager.class);

        Pager<V> res = ((BasePaginationDomain)domain).pagination(pager);
        responseContext.setBody(Constant.RESULT,res);
        return responseContext;
    }

}

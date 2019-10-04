package com.bdp.jdbc.base.app;

import com.bdp.helper.Constant;
import com.bdp.jdbc.base.domain.BaseListDomain;
import com.bdp.jdbc.base.entity.dto.Pager;
import com.bdp.jdbc.base.entity.po.BaseEntity;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;

import java.util.List;


public abstract class BaseListApp<V,E extends BaseEntity> extends BaseApp<E> {
    protected Class<V> dtoClass;

    public void setDomain(BaseListDomain<V,E> domain) {
        this.domain = domain;
    }

    @Override
    public ResponseContext execute(RequestContext requestDTO) throws Exception {
        ResponseContext responseContext = new ResponseContext();
        Pager pager = requestDTO.getObjectByKey(Constant.PAGER, Pager.class);
        List<V> res = ((BaseListDomain)domain).list(pager);
        responseContext.setBody(Constant.ROWS,res);
        return responseContext;
    }

}

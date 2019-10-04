package com.bdp.jdbc.base.app;

import com.bdp.helper.Constant;
import com.bdp.jdbc.base.convert.Converter;
import com.bdp.jdbc.base.domain.BaseQueryByIdDomain;
import com.bdp.jdbc.base.entity.po.BaseEntity;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;

public abstract class BaseQueryForIdApp<V,E extends BaseEntity> extends BaseApp<E> {
    protected Class<V> dtoClass;
    protected Converter<E,V> converter;

    public void setDomain(BaseQueryByIdDomain<E> domain) {
        this.domain = domain;
    }


    @Override
    public ResponseContext execute(RequestContext requestDTO) throws Exception {
        ResponseContext responseContext = new ResponseContext();
        String id = requestDTO.getStringByKey(Constant.ID);
        E entity  = ((BaseQueryByIdDomain<E>)domain).queryById(id);
        V dto = converter.domain2dto(entity);
        responseContext.setBody(Constant.RESULT,dto);
        return responseContext;
    }
}

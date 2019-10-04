package com.bdp.jdbc.base.app;

import com.bdp.helper.Constant;
import com.bdp.jdbc.base.convert.Converter;
import com.bdp.jdbc.base.domain.BaseSaveDomain;
import com.bdp.jdbc.base.entity.po.BaseEntity;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;

public abstract class BaseSaveApp<V,E extends BaseEntity> extends BaseApp<E> {
    protected Class<V> dtoClass;
    protected Converter<E,V> converter;


    @Override
    public ResponseContext execute(RequestContext requestDTO) throws Exception {
        ResponseContext responseContext = new ResponseContext();
        V  saveDTO = requestDTO.getObjectByKey(Constant.DTO,dtoClass);
        E entity = converter.dto2domain(saveDTO);
        String  id = ((BaseSaveDomain<E>)domain).save(entity);
        responseContext.setBody(Constant.ID,id);
        return responseContext;
    }
}

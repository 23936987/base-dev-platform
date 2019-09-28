package com.bdp.jdbc.base.app;

import com.bdp.helper.Constant;
import com.bdp.jdbc.base.convert.Converter;
import com.bdp.jdbc.base.domain.BaseQueryByIdDomain;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.dto.RequestDTO;
import com.bdp.jdbc.dto.ResponseDTO;

public class BaseQueryForIdApp<V,E extends Entity> extends BaseApp<E> {
    protected Class<V> dtoClass;
    protected Converter<E,V> converter;

    public void setDomain(BaseQueryByIdDomain<E> domain) {
        this.domain = domain;
    }


    @Override
    public ResponseDTO execute(RequestDTO requestDTO) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        String id = requestDTO.getStringByKey(Constant.ID);
        E entity  = ((BaseQueryByIdDomain<E>)domain).queryById(id);
        V dto = converter.domain2dto(entity);
        responseDTO.setBody(Constant.DTO,dto);
        return responseDTO;
    }
}

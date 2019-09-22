package com.bdp.jdbc.base.app;

import com.bdp.jdbc.base.convert.Converter;
import com.bdp.jdbc.base.domain.BaseSaveDomain;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.dto.RequestDTO;
import com.bdp.jdbc.dto.ResponseDTO;

public class BaseSaveApp<V,E extends Entity> implements App {
    protected Class<V> dtoClass;
    protected BaseSaveDomain domain;
    protected Converter<E,V> converter;

    public void setDomain(BaseSaveDomain domain) {
        this.domain = domain;
    }


    @Override
    public ResponseDTO execute(RequestDTO requestDTO) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        V  saveDTO = requestDTO.getObjectByKey("saveDTO",dtoClass);
        E entity = converter.dto2domain(saveDTO);
        String  id = domain.save(entity);
        if(true) throw new Exception("xxx");
        responseDTO.setBody("id",id);
        return responseDTO;
    }
}

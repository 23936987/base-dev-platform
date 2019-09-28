package com.bdp.jdbc.base.app;

import com.bdp.helper.Constant;
import com.bdp.jdbc.base.convert.Converter;
import com.bdp.jdbc.base.domain.BaseSaveDomain;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.dto.RequestDTO;
import com.bdp.jdbc.dto.ResponseDTO;

public class BaseSaveApp<V,E extends Entity> extends BaseApp<E> {
    protected Class<V> dtoClass;
    protected Converter<E,V> converter;


    @Override
    public ResponseDTO execute(RequestDTO requestDTO) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        V  saveDTO = requestDTO.getObjectByKey(Constant.DTO,dtoClass);
        E entity = converter.dto2domain(saveDTO);
        String  id = ((BaseSaveDomain<E>)domain).save(entity);
        responseDTO.setBody(Constant.ID,id);
        return responseDTO;
    }
}

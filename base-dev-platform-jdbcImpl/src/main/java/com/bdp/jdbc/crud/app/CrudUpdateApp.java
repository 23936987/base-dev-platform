package com.bdp.jdbc.crud.app;

import com.bdp.helper.Constant;
import com.bdp.jdbc.base.app.BaseUpdateApp;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import com.bdp.jdbc.crud.domain.CrudUpdateDomain;
import com.bdp.jdbc.crud.entity.po.CrudEntity;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;

import java.util.Map;

public abstract class CrudUpdateApp<E extends CrudEntity> extends BaseUpdateApp<E> {

    @Override
    public ResponseContext execute(RequestContext requestDTO) throws Exception {
        ResponseContext responseContext = new ResponseContext();
        UpdateDTO updateDTO = requestDTO.getObjectByKey(Constant.DTO,UpdateDTO.class);
        String id  = updateDTO.getId();
        String updateKey  = updateDTO.getUpdateKey();
        Map<String,Object> props = updateDTO.getProps();
        Integer  result = ((CrudUpdateDomain<E>)domain).update(props,id,updateKey);
        responseContext.setBody(Constant.RESULT,result);
        return responseContext;
    }
}

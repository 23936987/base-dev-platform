package com.bdp.jdbc.base.app;

import com.bdp.helper.Constant;
import com.bdp.jdbc.base.domain.BaseUpdateDomain;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import com.bdp.jdbc.base.entity.po.BaseEntity;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;

import java.util.Map;

public abstract class BaseUpdateApp<E extends BaseEntity> extends BaseApp<E> {

    @Override
    public ResponseContext execute(RequestContext requestDTO) throws Exception {
        ResponseContext responseContext = new ResponseContext();
        UpdateDTO  updateDTO = requestDTO.getObjectByKey(Constant.DTO,UpdateDTO.class);
        String id  = updateDTO.getId();
        Map<String,Object> props = updateDTO.getProps();

        Integer  result = ((BaseUpdateDomain<E>)domain).update(props,id);
        responseContext.setBody(Constant.RESULT,result);
        return responseContext;
    }
}

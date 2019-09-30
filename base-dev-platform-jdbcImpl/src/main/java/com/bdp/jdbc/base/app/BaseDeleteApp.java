package com.bdp.jdbc.base.app;

import com.bdp.helper.Constant;
import com.bdp.jdbc.base.domain.BaseDeleteDomain;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;

public class BaseDeleteApp<E extends Entity> extends BaseApp<E> {

    @Override
    public ResponseContext execute(RequestContext requestDTO) throws Exception {
        ResponseContext responseContext = new ResponseContext();
        String id  = requestDTO.getStringByKey(Constant.ID);

        Integer  result = ((BaseDeleteDomain<E>)domain).delete(id);
        responseContext.setBody(Constant.RESULT,result);
        return responseContext;
    }
}

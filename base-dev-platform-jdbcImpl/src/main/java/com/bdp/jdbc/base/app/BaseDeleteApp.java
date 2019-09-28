package com.bdp.jdbc.base.app;

import com.bdp.helper.Constant;
import com.bdp.jdbc.base.domain.BaseDeleteDomain;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.dto.RequestDTO;
import com.bdp.jdbc.dto.ResponseDTO;

public class BaseDeleteApp<E extends Entity> extends BaseApp<E> {

    @Override
    public ResponseDTO execute(RequestDTO requestDTO) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        String id  = requestDTO.getStringByKey(Constant.ID);

        Integer  result = ((BaseDeleteDomain<E>)domain).delete(id);
        responseDTO.setBody(Constant.RESULT,result);
        return responseDTO;
    }
}

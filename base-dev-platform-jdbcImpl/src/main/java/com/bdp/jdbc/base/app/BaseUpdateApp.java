package com.bdp.jdbc.base.app;

import com.bdp.helper.Constant;
import com.bdp.jdbc.base.domain.BaseUpdateDomain;
import com.bdp.jdbc.base.entity.dto.UpdateDTO;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.dto.RequestDTO;
import com.bdp.jdbc.dto.ResponseDTO;

import java.util.Map;

public class BaseUpdateApp<E extends Entity> extends BaseApp<E> {

    @Override
    public ResponseDTO execute(RequestDTO requestDTO) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        UpdateDTO  updateDTO = requestDTO.getObjectByKey(Constant.DTO,UpdateDTO.class);
        String id  = updateDTO.getId();
        Map<String,Object> props = updateDTO.getProps();

        Integer  result = ((BaseUpdateDomain<E>)domain).update(props,id);
        responseDTO.setBody(Constant.RESULT,result);
        return responseDTO;
    }
}

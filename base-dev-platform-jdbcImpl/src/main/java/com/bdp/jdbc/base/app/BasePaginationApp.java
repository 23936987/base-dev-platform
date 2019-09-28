package com.bdp.jdbc.base.app;

import com.bdp.helper.Constant;
import com.bdp.jdbc.base.convert.Converter;
import com.bdp.jdbc.base.domain.BasePaginationDomain;
import com.bdp.jdbc.base.domain.BaseQueryByIdDomain;
import com.bdp.jdbc.base.entity.dto.PaginationDTO;
import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.Pager;
import com.bdp.jdbc.db.QueryItem;
import com.bdp.jdbc.dto.RequestDTO;
import com.bdp.jdbc.dto.ResponseDTO;

import java.util.List;
import java.util.Map;

public class BasePaginationApp<V,E extends Entity> extends BaseApp<E> {
    protected Class<V> dtoClass;

    public void setDomain(BasePaginationDomain<V,E> domain) {
        this.domain = domain;
    }

    @Override
    public ResponseDTO execute(RequestDTO requestDTO) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        PaginationDTO paginationDTO = requestDTO.getObjectByKey(Constant.DTO,PaginationDTO.class);

        Map<String,Object> res = ((BasePaginationDomain)domain).pagination(paginationDTO.getParams(),
                paginationDTO.getPage(),paginationDTO.getPageSize(),paginationDTO.getOrderBy());
        responseDTO.setBody(Constant.RESULT,res);
        return responseDTO;
    }

}

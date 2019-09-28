package com.bdp.jdbc.base.domain;

import com.bdp.jdbc.base.entity.po.Entity;
import com.bdp.jdbc.db.QueryItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasePaginationDomain<E extends Entity>  extends BaseDomain<E> {

    public   Map<String,Object> pagination(List<QueryItem> params,Integer pageNum,Integer page) throws Exception {
        Map<String,Object> res = new HashMap<>();

        return res;
    }
}

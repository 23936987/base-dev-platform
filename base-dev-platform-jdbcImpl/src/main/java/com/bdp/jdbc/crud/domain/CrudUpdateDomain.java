package com.bdp.jdbc.crud.domain;

import com.bdp.helper.PrimaryKeyHelper;
import com.bdp.jdbc.base.domain.BaseUpdateDomain;
import com.bdp.jdbc.crud.entity.po.CrudEntity;

import java.util.Map;

public abstract class CrudUpdateDomain<E extends CrudEntity>  extends BaseUpdateDomain<E> {

    public Integer update(Map<String, Object> props, String id,String updateKey) throws Exception {
        E entity = dao.queryForObject(id);
        if(entity != null) {
            if(entity.getUpdateKey() == null) {
                props.put("updateKey", PrimaryKeyHelper.getUUID16());
                return dao.update(props,id);
            }else if(entity.getUpdateKey().equalsIgnoreCase(updateKey)){
                return dao.update(props,id);
            }
        }
        return 0;
    }
}

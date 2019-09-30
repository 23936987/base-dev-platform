<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.domain;

import com.bdp.${parent}.project.${base}.entity.po.${baseCapture}Entity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BaseUpdateDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("${base}.updateDomain")
public class ${baseCapture}UpdateDomain extends BaseUpdateDomain<${baseCapture}Entity> {
   @Autowired
    @Override
    public void setDao(BaseDao<${baseCapture}Entity> dao) {
        super.setDao(dao);
    }
}

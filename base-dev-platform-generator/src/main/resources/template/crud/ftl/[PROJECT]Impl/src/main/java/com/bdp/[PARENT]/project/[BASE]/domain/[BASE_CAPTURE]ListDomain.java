<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.domain;

import com.bdp.${parent}.project.${base}.entity.dto.${baseCapture}ListDTO;
import com.bdp.${parent}.project.${base}.entity.po.${baseCapture}Entity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BaseListDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("${base}.listDomain")
public class ${baseCapture}ListDomain extends BaseListDomain<${baseCapture}ListDTO,${baseCapture}Entity> {
    public ${baseCapture}ListDomain(){
        this.clazz = ${baseCapture}ListDTO.class;
        this.entityClass = ${baseCapture}Entity.class;
    }
   @Autowired
    @Override
    public void setDao(BaseDao<${baseCapture}Entity> dao) {
        super.setDao(dao);
    }
}

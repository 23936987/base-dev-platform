<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.domain;

import com.bdp.${parent}.project.${base}.entity.dto.${baseCapture}PaginationDTO;
import com.bdp.${parent}.project.${base}.entity.po.${baseCapture}Entity;
import com.bdp.jdbc.base.dao.BaseDao;
import com.bdp.jdbc.base.domain.BasePaginationDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("${base}.paginationDomain")
public class ${baseCapture}PaginationDomain extends BasePaginationDomain<${baseCapture}PaginationDTO,${baseCapture}Entity> {
    public ${baseCapture}PaginationDomain(){
        this.clazz = ${baseCapture}PaginationDTO.class;
        this.entityClass = ${baseCapture}Entity.class;
    }
   @Autowired
    @Override
    public void setDao(BaseDao<${baseCapture}Entity> dao) {
        super.setDao(dao);
    }
}

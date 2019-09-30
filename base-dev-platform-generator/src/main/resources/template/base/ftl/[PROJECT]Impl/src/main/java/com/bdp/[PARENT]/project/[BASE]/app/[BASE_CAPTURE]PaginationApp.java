<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.app;

import com.bdp.${parent}.project.${base}.entity.dto.${baseCapture}PaginationDTO;
import com.bdp.${parent}.project.${base}.entity.po.${baseCapture}Entity;
import com.bdp.jdbc.base.app.BasePaginationApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("${base}.pagination")
public class ${baseCapture}PaginationApp extends BasePaginationApp<${baseCapture}PaginationDTO,${baseCapture}Entity> {

    public ${baseCapture}PaginationApp(){
        this.dtoClass = ${baseCapture}PaginationDTO.class;
    }
    @Resource(name="${base}.paginationDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}

<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.app;

import com.bdp.${parent}.project.${base}.entity.dto.${baseCapture}ListDTO;
import com.bdp.${parent}.project.${base}.entity.po.${baseCapture}Entity;
import com.bdp.jdbc.base.app.BaseListApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("${base}.list")
public class ${baseCapture}ListApp extends BaseListApp<${baseCapture}ListDTO,${baseCapture}Entity> {

    public ${baseCapture}ListApp(){
        this.dtoClass = ${baseCapture}ListDTO.class;
    }
    @Resource(name="${base}.listDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}

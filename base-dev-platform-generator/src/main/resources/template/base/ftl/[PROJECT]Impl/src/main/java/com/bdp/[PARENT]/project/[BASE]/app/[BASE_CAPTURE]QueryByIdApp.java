<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.app;

import com.bdp.${parent}.project.${base}.convert.${baseCapture}QueryByIdConvert;
import com.bdp.${parent}.project.${base}.entity.dto.${baseCapture}QueryByIdResponseDTO;
import com.bdp.${parent}.project.${base}.entity.po.${baseCapture}Entity;
import com.bdp.jdbc.base.app.BaseQueryForIdApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("${base}.queryById")
public class ${baseCapture}QueryByIdApp extends BaseQueryForIdApp<${baseCapture}QueryByIdResponseDTO,${baseCapture}Entity> {

    public ${baseCapture}QueryByIdApp(){
        this.dtoClass = ${baseCapture}QueryByIdResponseDTO.class;
        this.converter= ${baseCapture}QueryByIdConvert.INSTANCE;
    }
    @Resource(name="${base}.queryByIdDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}

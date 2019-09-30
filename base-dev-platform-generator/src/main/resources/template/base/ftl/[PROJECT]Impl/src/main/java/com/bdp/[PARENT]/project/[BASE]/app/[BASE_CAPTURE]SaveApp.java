<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.app;

import com.bdp.${parent}.annotation.TransactionalPrimary;
import com.bdp.${parent}.project.${base}.convert.${baseCapture}SaveConvert;
import com.bdp.${parent}.project.${base}.entity.dto.${baseCapture}SaveDTO;
import com.bdp.${parent}.project.${base}.entity.po.${baseCapture}Entity;
import com.bdp.jdbc.base.app.BaseSaveApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("${base}.save")
public class ${baseCapture}SaveApp extends BaseSaveApp<${baseCapture}SaveDTO,${baseCapture}Entity> {

    public ${baseCapture}SaveApp(){
        this.dtoClass = ${baseCapture}SaveDTO.class;
        this.converter=${baseCapture}SaveConvert.INSTANCE;
    }
    @Resource(name="${base}.saveDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }


    @Override
    @TransactionalPrimary
    public ResponseContext execute(RequestContext requestDTO) throws Exception {
        return super.execute(requestDTO);
    }
}

<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.app;

import com.bdp.${parent}.annotation.TransactionalPrimary;
import com.bdp.${parent}.project.${base}.entity.po.${baseCapture}Entity;
import com.bdp.jdbc.base.app.BaseDeleteApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import com.bdp.jdbc.dto.RequestContext;
import com.bdp.jdbc.dto.ResponseContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("${base}.delete")
@TransactionalPrimary
public class ${baseCapture}DeleteApp extends BaseDeleteApp<${baseCapture}Entity> {

    public ${baseCapture}DeleteApp(){
    }
    @Resource(name="${base}.deleteDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }
}

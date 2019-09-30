<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.dao.impl;

import com.bdp.${parent}.project.${base}.dao.${baseCapture}Dao;
import com.bdp.${parent}.project.${base}.entity.po.${baseCapture}Entity;
import com.bdp.jdbc.base.dao.impl.BaseDaoImpl;
import com.bdp.jdbc.db.JdbcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ${baseCapture}DaoImpl extends BaseDaoImpl<${baseCapture}Entity> implements ${baseCapture}Dao{

    @Autowired
    @Qualifier("primaryJdbcContext")
    @Override
    public void setJdbcContext(JdbcContext jdbcContext) {
        super.setJdbcContext(jdbcContext);
    }
}

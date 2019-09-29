<#include "/include/comment.ftl"/>
package com.oading.project.${clazz.name}.dao.impl;

import com.oading.base.dao.impl.BaseDaoImpl;
import com.oading.project.${clazz.name}.dao.${clazz.name?cap_first}Dao;
import com.oading.project.${clazz.name}.entity.${clazz.name?cap_first}Entity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ${clazz.name?cap_first}DaoImpl extends BaseDaoImpl<${clazz.name?cap_first}Entity> implements ${clazz.name?cap_first}Dao {
	public ${clazz.name?cap_first}DaoImpl(){
		super();
		this._SQL_PATH_="${clazz.name?cap_first}";
	}
}

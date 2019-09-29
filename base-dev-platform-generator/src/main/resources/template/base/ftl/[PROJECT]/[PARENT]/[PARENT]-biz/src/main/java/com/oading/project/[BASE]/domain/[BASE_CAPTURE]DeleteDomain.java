<#include "/include/comment.ftl"/>
package com.oading.project.${clazz.name}.domain;

import com.oading.base.dao.BaseDao;
import com.oading.base.domain.DeleteDomain;
import com.oading.project.${clazz.name}.entity.${clazz.name?cap_first}Entity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("${clazz.name}.DeleteDomain")
public class ${clazz.name?cap_first}DeleteDomain extends DeleteDomain<${clazz.name?cap_first}Entity> {
	public ${clazz.name?cap_first}DeleteDomain(){
		super();
		this._SQL_PATH_="${clazz.name?cap_first}";
	}
	@Resource
	@Override
	public void setDao(BaseDao<${clazz.name?cap_first}Entity> dao) {
		super.setDao(dao);
		this._SQL_PATH_="${clazz.name?cap_first}";
	}
}

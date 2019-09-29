<#include "/include/comment.ftl"/>
package com.oading.project.${clazz.name}.entity;

import com.oading.annotation.*;
import com.oading.base.entity.BaseEntity;
import lombok.Data;

@Data
@Table(bizCode = "${clazz.bizCode}", name = "${clazz.table}", base = "${clazz.name}",nameCn="${clazz.nameCn}",label="${clazz.label}",comment="${clazz.comment}")
public class ${clazz.name?cap_first}Entity extends BaseEntity {
/*********************************属性*********************************/
<#list clazz.model_fields as field>
	@Column(nameCn = "${field.nameCn}",label = "${field.label}",comment = "${field.comment}")
<#if field.dictCode ??>
	@DictColumn(dictCode="${field.dictCode}")
</#if>
<#if field.relationId ??>
	@RelationColumn(relationId="${field.relationId}",relationTable="${field.relationTable}",show="${field.relationShow}")
</#if>
<#if field.dbtype == 'datetime'>
	@DateColumn(format = "%Y-%m-%d %T")
</#if>
<#if field.dbtype == 'date'>
	@DateColumn(format = "%Y-%m-%d %T")
</#if>
	private ${field.javaType} ${field.name};
</#list>
}
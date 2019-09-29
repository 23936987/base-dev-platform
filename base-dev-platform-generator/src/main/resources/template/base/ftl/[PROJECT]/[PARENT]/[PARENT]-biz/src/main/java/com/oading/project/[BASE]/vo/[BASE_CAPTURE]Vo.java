<#include "/include/comment.ftl"/>

package com.oading.project.${clazz.name}.vo;

import com.oading.vo.Vo;
import lombok.Data;
import com.oading.annotation.Column;
@Data
public class ${clazz.name?cap_first}Vo implements Vo {
	private Long opt;
/*************属性***********/
<#list clazz.fields as field>
	@Column(nameCn = "${field.nameCn}",label = "${field.label}",comment = "${field.comment}")
	private ${field.javaType} ${field.name};
<#if field.dictCode ?? || field.relationId ?? || field.dbtype == 'datetime' || field.dbtype == 'date'>
	@Column(nameCn = "${field.nameCn}名称",label = "${field.label}",comment = "${field.comment}")
	private java.lang.String ${field.name}Name;
</#if>
</#list>
}

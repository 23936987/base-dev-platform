<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ${baseCapture}PaginationDTO {
/*********************************属性*********************************/
    @ApiModelProperty(value = "主键")
    private String id;
<#list clazz.model_fields as field>
    @ApiModelProperty(value = "${field.nameCn}")
    <#if field.dictCode ??>
    private String ${field.name}Name;
    </#if>
    <#if field.relationId ??>
    private String ${field.name}Name;
    </#if>
    <#if field.dbtype == 'datetime'>
    private String ${field.name}Name;
    </#if>
    <#if field.dbtype == 'date'>
    private String ${field.name}Name;
    </#if>
    private ${field.javaType} ${field.name};
</#list>
}

<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ${baseCapture}ListDTO{
/*********************************属性*********************************/
    @ApiModelProperty(value = "主键")
    private String id;
<#list clazz.model_fields as field>
    @ApiModelProperty(value = "${field.nameCn}")
    private ${field.javaType} ${field.name};
    <#if field.dictCode ??>
    @ApiModelProperty(value = "${field.nameCn}名称")
    private String ${field.name}Name;
    </#if>
    <#if field.relationId ??>
    @ApiModelProperty(value = "${field.nameCn}名称")
    private String ${field.name}Name;
    </#if>
    <#if field.dbtype == 'datetime'>
    @ApiModelProperty(value = "${field.nameCn}名称")
    private String ${field.name}Name;
    </#if>
    <#if field.dbtype == 'date'>
    @ApiModelProperty(value = "${field.nameCn}名称")
    private String ${field.name}Name;
    </#if>
</#list>
}

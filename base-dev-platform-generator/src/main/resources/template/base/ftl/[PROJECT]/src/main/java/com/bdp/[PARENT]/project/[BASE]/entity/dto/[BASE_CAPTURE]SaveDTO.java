<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
@Data
public class ${baseCapture}SaveDTO {
/*********************************属性*********************************/
<#list clazz.model_fields as field>
    @ApiModelProperty(value = "${field.nameCn}")
    private ${field.javaType} ${field.name};
</#list>
}

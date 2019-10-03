import lombok.Data;

<#include"/include/comment.ftl"/>
        package com.bdp.base.project.parameter.entity.dto;

@Data
public class ParameterListDTO{
/*********************************属性*********************************/
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "参数编码")
    private java.lang.String paramCode;
    @ApiModelProperty(value = "参数值")
    private java.lang.String paramValue;
}

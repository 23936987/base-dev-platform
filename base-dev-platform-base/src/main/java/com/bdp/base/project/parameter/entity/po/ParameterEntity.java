import lombok.Data;

<#include"/include/comment.ftl"/>
        package com.bdp.base.project.parameter.entity.po;

@Data
@Table(name = "sys_parameter", base = "parameter",nameCn="参数配置表",label="参数配置表",comment="参数配置表")
public class ParameterEntity extends Entity {
/*********************************属性*********************************/
    @Column(nameCn = "参数编码",label = "参数编码",comment = "参数编码")
    private java.lang.String paramCode;
    @Column(nameCn = "参数值",label = "参数值",comment = "参数值")
    private java.lang.String paramValue;
}

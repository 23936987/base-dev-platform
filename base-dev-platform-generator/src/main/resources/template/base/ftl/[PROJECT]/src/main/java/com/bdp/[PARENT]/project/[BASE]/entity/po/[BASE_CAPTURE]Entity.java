<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.entity.po;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;

@Data
@Table(name = "${clazz.table}", base = "${base}",nameCn="${clazz.nameCn}",label="${clazz.label}",comment="${clazz.comment}")
public class ${baseCapture}Entity extends Entity {
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
    @DateColumn(format = "%Y-%m-%d")
    </#if>
    private ${field.javaType} ${field.name};
</#list>
}

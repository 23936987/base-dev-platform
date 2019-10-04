/**
* 基础场景-数据字典表-数据字典表
* <p>完成日期：2019-10-04 13:15:02</p>
* @varsion 1.0
* @author hj
*/
        
package com.bdp.base.project.dict.entity.po;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;
import com.bdp.jdbc.base.entity.po.BaseEntity;

@Data
@Table(name = "sys_dict", base = "dict",nameCn="数据字典表",label="数据字典表",comment="数据字典表")
public class DictEntity extends BaseEntity {
/*********************************属性*********************************/
    @Column(nameCn = "编码",label = "编码",comment = "编码")
    private java.lang.String code;
    @Column(nameCn = "名称",label = "名称",comment = "名称")
    private java.lang.String name;
    @Column(nameCn = "类型",label = "类型",comment = "1 普通 2、其它表 ")
    @DictColumn(dictCode="dict_type")
    private java.lang.Integer types;
    @Column(nameCn = "表名称",label = "表名称",comment = "表名称")
    private java.lang.String tableName;
    @Column(nameCn = "值字段",label = "值字段",comment = "值字段")
    private java.lang.String valueField;
    @Column(nameCn = "名称字段",label = "名称字段",comment = "名称字段")
    private java.lang.String nameField;
}

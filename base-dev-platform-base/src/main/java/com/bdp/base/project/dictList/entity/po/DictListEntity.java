/**
* 基础场景-数据字典明细表-数据字典明细表
* <p>完成日期：2019-10-04 13:15:03</p>
* @varsion 1.0
* @author hj
*/
        
package com.bdp.base.project.dictList.entity.po;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;
import com.bdp.jdbc.base.entity.po.BaseEntity;

@Data
@Table(name = "sys_dict_list", base = "dictList",nameCn="数据字典明细表",label="数据字典明细表",comment="数据字典明细表")
public class DictListEntity extends BaseEntity {
/*********************************属性*********************************/
    @Column(nameCn = "编码",label = "编码",comment = "编码")
    private java.lang.String code;
    @Column(nameCn = "名称",label = "名称",comment = "名称")
    private java.lang.String name;
    @Column(nameCn = "数据字典主键",label = "数据字典主键",comment = "数据字典主键")
    @RelationColumn(relationId="id",relationTable="sys_dict",show="name")
    private java.lang.String dictId;
}

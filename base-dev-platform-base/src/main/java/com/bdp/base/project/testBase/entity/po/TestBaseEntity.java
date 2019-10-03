/**
* 基础场景-测试基础场景表-这是一个测试基础场景的示例
* <p>完成日期：2019-10-02 19:31:08</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testBase.entity.po;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;

@Data
@Table(name = "t_test_base", base = "testBase",nameCn="测试基础场景表",label="测试",comment="这是一个测试基础场景的示例")
public class TestBaseEntity extends Entity {
/*********************************属性*********************************/
    @Column(nameCn = "编码",label = "编码",comment = "编码")
    private java.lang.String code;
    @Column(nameCn = "名称",label = "名称",comment = "名称")
    private java.lang.String name;
    @Column(nameCn = "状态",label = "状态",comment = "0 无效,1 生效,2 删除")
    @DictColumn(dictCode="stateType")
    private java.lang.Integer state;
    @Column(nameCn = "创建时间",label = "创建时间",comment = "创建时间")
    @DateColumn(format = "%Y-%m-%d %T")
    private java.util.Date createTime;
    @Column(nameCn = "修改时间",label = "修改时间",comment = "修改时间")
    @DateColumn(format = "%Y-%m-%d %T")
    private java.util.Date modifyTime;
    @Column(nameCn = "生日",label = "生日",comment = "生日")
    @DateColumn(format = "%Y-%m-%d")
    private java.util.Date birthDate;
    @Column(nameCn = "时刻",label = "时刻",comment = "时刻")
    private java.util.Date times;
}

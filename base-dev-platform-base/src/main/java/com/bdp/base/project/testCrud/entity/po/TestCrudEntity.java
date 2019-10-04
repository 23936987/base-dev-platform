/**
* 基础场景-测试CRUD场景表-测试CRUD场景表
* <p>完成日期：2019-10-04 10:21:07</p>
* @varsion 1.0
* @author hj
*/
        
package com.bdp.base.project.testCrud.entity.po;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;

@Data
@Table(name = "t_test_crud", base = "testCrud",nameCn="测试CRUD场景表",label="测试CRUD",comment="测试CRUD场景表")
public class TestCrudEntity extends Entity {
/*********************************属性*********************************/
    @Column(nameCn = "编码",label = "编码",comment = "编码")
    private java.lang.String code;
    @Column(nameCn = "名称",label = "名称",comment = "名称")
    private java.lang.String name;
    @Column(nameCn = "生日",label = "生日",comment = "生日")
    @DateColumn(format = "%Y-%m-%d")
    private java.util.Date birthDate;
    @Column(nameCn = "时刻",label = "时刻",comment = "时刻")
    private java.util.Date times;
    @Column(nameCn = "更改键",label = "更改键",comment = "更改键")
    private java.lang.String updateKey;
    @Column(nameCn = "创建时间",label = "创建时间",comment = "创建时间")
    @DateColumn(format = "%Y-%m-%d %T")
    private java.util.Date createTime;
    @Column(nameCn = "修改时间",label = "修改时间",comment = "修改时间")
    @DateColumn(format = "%Y-%m-%d %T")
    private java.util.Date modifyTime;
    @Column(nameCn = "创建人",label = "创建人",comment = "创建人")
    private java.lang.Long createUser;
    @Column(nameCn = "修改人",label = "修改人",comment = "修改人")
    private java.lang.Long modifyUser;
    @Column(nameCn = "创建人名称",label = "创建人名称",comment = "创建人名称")
    private java.lang.String createUserName;
    @Column(nameCn = "修改人名称",label = "修改人名称",comment = "修改人名称")
    private java.lang.String modifyUserName;
    @Column(nameCn = "排序",label = "排序",comment = "排序")
    private java.lang.Integer seq;
    @Column(nameCn = "备注",label = "备注",comment = "备注")
    private java.lang.String remark;
    @Column(nameCn = "状态",label = "状态",comment = "0 无效,1 生效,2 删除")
    @DictColumn(dictCode="state_type")
    private java.lang.Integer state;
}

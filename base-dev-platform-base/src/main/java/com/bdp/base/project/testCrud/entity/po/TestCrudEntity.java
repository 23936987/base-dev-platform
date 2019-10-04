/**
* CRUD场景-测试CRUD场景表-测试CRUD场景表
* <p>完成日期：2019-10-04 10:37:18</p>
* @varsion 1.0
* @author hj
*/
        
package com.bdp.base.project.testCrud.entity.po;
import com.bdp.jdbc.annotation.Column;
import com.bdp.jdbc.annotation.DateColumn;
import com.bdp.jdbc.annotation.Table;
import com.bdp.jdbc.crud.entity.po.CrudEntity;
import lombok.Data;

@Data
@Table(name = "t_test_crud", base = "testCrud",nameCn="测试CRUD场景表",label="测试CRUD",comment="测试CRUD场景表")
public class TestCrudEntity extends CrudEntity {
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

}

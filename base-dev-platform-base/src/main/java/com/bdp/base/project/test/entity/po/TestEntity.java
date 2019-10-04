/**
* 基础场景-测试表现-测试基础框架功能
* <p>完成日期：2019-10-04 13:12:42</p>
* @varsion 1.0
* @author hj
*/
        
package com.bdp.base.project.test.entity.po;

import com.bdp.jdbc.annotation.Column;
import com.bdp.jdbc.annotation.Table;
import com.bdp.jdbc.base.entity.po.BaseEntity;
import lombok.Data;

@Data
@Table(name = "t_test", base = "test",nameCn="测试表现",label="测试",comment="测试基础框架功能")
public class TestEntity extends BaseEntity {
/*********************************属性*********************************/
    @Column(nameCn = "编码",label = "编码",comment = "编码")
    private java.lang.String code;
    @Column(nameCn = "名称",label = "名称",comment = "名称")
    private java.lang.String name;
}

package com.bdp.base.project.test.entity.po;

import com.bdp.jdbc.annotation.Column;
import com.bdp.jdbc.annotation.Table;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;

@Data
@Table(name = "t_test",base = "test",nameCn = "测试",label = "测试",comment = "测试")
public class TestEntity extends Entity {
    @Column(name = "id",nameCn = "主键",label = "主键",comment = "主键")
    private String id;
    @Column(name = "code",nameCn = "编码",label = "编码",comment = "编码")
    private String code;
    @Column(name = "name",nameCn = "名称",label = "名称",comment = "名称")
    private String name;
}

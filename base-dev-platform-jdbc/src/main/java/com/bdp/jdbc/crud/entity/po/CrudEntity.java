package com.bdp.jdbc.crud.entity.po;

import com.bdp.jdbc.annotation.Column;
import com.bdp.jdbc.annotation.DateColumn;
import com.bdp.jdbc.annotation.DictColumn;
import com.bdp.jdbc.base.entity.po.BaseEntity;
import lombok.Data;

/***
 *
 * @ClassName: Entity
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/9/22 10:17
 * @version : 1.0
 */
@Data
public class CrudEntity extends BaseEntity {
    @Column(nameCn = "更改键",label = "更改键",comment = "更改键")
    private java.lang.String updateKey;
    @Column(nameCn = "创建时间",label = "创建时间",comment = "创建时间")
    @DateColumn(format = "%Y-%m-%d %T")
    private java.util.Date createTime;
    @Column(nameCn = "修改时间",label = "修改时间",comment = "修改时间")
    @DateColumn(format = "%Y-%m-%d %T")
    private java.util.Date modifyTime;
    @Column(nameCn = "创建人",label = "创建人",comment = "创建人")
    private java.lang.String createUser;
    @Column(nameCn = "修改人",label = "修改人",comment = "修改人")
    private java.lang.String modifyUser;
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




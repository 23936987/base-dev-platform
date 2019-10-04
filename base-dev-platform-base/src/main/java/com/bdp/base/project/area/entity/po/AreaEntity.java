/**
* 基础场景-区域表-区域表
* <p>完成日期：2019-10-04 13:14:59</p>
* @varsion 1.0
* @author hj
*/
        
package com.bdp.base.project.area.entity.po;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;
import com.bdp.jdbc.base.entity.po.BaseEntity;

@Data
@Table(name = "sys_area", base = "area",nameCn="区域表",label="区域表",comment="区域表")
public class AreaEntity extends BaseEntity {
/*********************************属性*********************************/
    @Column(nameCn = "编码",label = "编码",comment = "编码")
    private java.lang.String code;
    @Column(nameCn = "省",label = "省",comment = "省")
    private java.lang.String provinceCode;
    @Column(nameCn = "市",label = "市",comment = "市")
    private java.lang.String cityCode;
    @Column(nameCn = "区县",label = "区县",comment = "区县")
    private java.lang.String countyCode;
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
    @Column(nameCn = "名称",label = "名称",comment = "名称")
    private java.lang.String name;
    @Column(nameCn = "名称路径",label = "名称路径",comment = "名称路径")
    private java.lang.String namePath;
    @Column(nameCn = "级别",label = "级别",comment = "级别")
    private java.lang.Integer level;
    @Column(nameCn = "左值",label = "左值",comment = "左值")
    private java.lang.Integer leftValue;
    @Column(nameCn = "右值",label = "右值",comment = "右值")
    private java.lang.Integer rightValue;
    @Column(nameCn = "叶子",label = "叶子",comment = "0 否,1 是")
    @DictColumn(dictCode="yesOrNo")
    private java.lang.Integer leaf;
    @Column(nameCn = "上级",label = "上级",comment = "上级")
    @RelationColumn(relationId="id",relationTable="sys_area",show="name")
    private java.lang.Long parentId;
}

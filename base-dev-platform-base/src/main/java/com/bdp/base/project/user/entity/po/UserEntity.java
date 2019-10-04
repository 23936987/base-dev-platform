/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 10:08:20</p>
* @varsion 1.0
* @author hj
*/
        
package com.bdp.base.project.user.entity.po;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;

@Data
@Table(name = "sys_user", base = "user",nameCn="系统用户表",label="系统用户表",comment="系统用户表")
public class UserEntity extends Entity {
/*********************************属性*********************************/
    @Column(nameCn = "手机号",label = "手机号",comment = "手机号")
    private java.lang.String phoneNo;
    @Column(nameCn = "名称",label = "名称",comment = "名称")
    private java.lang.String name;
    @Column(nameCn = "密码",label = "密码",comment = "密码")
    private java.lang.String password;
    @Column(nameCn = "头像",label = "头像",comment = "头像")
    private java.lang.String avatar;
    @Column(nameCn = "区域编码",label = "区域编码",comment = "区域编码")
    private java.lang.String areaCode;
    @Column(nameCn = "性别",label = "性别",comment = "1 男 2 女")
    @DictColumn(dictCode="sex")
    private java.lang.Integer gender;
    @Column(nameCn = "锁定",label = "锁定",comment = "0 否 1 是")
    @DictColumn(dictCode="yesOrNo")
    private java.lang.Integer locked;
    @Column(nameCn = "失败次数",label = "失败次数",comment = "失败次数")
    private java.lang.Integer failTimes;
    @Column(nameCn = "邮箱",label = "邮箱",comment = "邮箱")
    private java.lang.String email;
    @Column(nameCn = "管理员",label = "管理员",comment = "0 否 1 是")
    @DictColumn(dictCode="yesOrNo")
    private java.lang.Integer admin;
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

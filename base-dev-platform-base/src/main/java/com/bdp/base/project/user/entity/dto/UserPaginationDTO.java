/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 13:15:04</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.user.entity.dto;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
@Data
public class UserPaginationDTO {
/*********************************属性*********************************/
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "手机号")
    private java.lang.String phoneNo;
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
    @ApiModelProperty(value = "密码")
    private java.lang.String password;
    @ApiModelProperty(value = "头像")
    private java.lang.String avatar;
    @ApiModelProperty(value = "区域编码")
    private java.lang.String areaCode;
    @ApiModelProperty(value = "性别")
    private java.lang.Integer gender;
    @ApiModelProperty(value = "性别名称")
    private String genderName;
    @ApiModelProperty(value = "锁定")
    private java.lang.Integer locked;
    @ApiModelProperty(value = "锁定名称")
    private String lockedName;
    @ApiModelProperty(value = "失败次数")
    private java.lang.Integer failTimes;
    @ApiModelProperty(value = "邮箱")
    private java.lang.String email;
    @ApiModelProperty(value = "管理员")
    private java.lang.Integer admin;
    @ApiModelProperty(value = "管理员名称")
    private String adminName;
    @ApiModelProperty(value = "更改键")
    private java.lang.String updateKey;
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
    @ApiModelProperty(value = "创建时间名称")
    private String createTimeName;
    @ApiModelProperty(value = "修改时间")
    private java.util.Date modifyTime;
    @ApiModelProperty(value = "修改时间名称")
    private String modifyTimeName;
    @ApiModelProperty(value = "创建人")
    private java.lang.Long createUser;
    @ApiModelProperty(value = "修改人")
    private java.lang.Long modifyUser;
    @ApiModelProperty(value = "创建人名称")
    private java.lang.String createUserName;
    @ApiModelProperty(value = "修改人名称")
    private java.lang.String modifyUserName;
    @ApiModelProperty(value = "排序")
    private java.lang.Integer seq;
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
    @ApiModelProperty(value = "状态")
    private java.lang.Integer state;
    @ApiModelProperty(value = "状态名称")
    private String stateName;
}

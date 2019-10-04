/**
* CRUD场景-测试CRUD场景表-测试CRUD场景表
* <p>完成日期：2019-10-04 10:37:18</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testCrud.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class TestCrudSaveDTO {
/*********************************属性*********************************/
    @ApiModelProperty(value = "编码")
    private java.lang.String code;
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
    @ApiModelProperty(value = "生日")
    private java.util.Date birthDate;
    @ApiModelProperty(value = "时刻")
    private java.util.Date times;
    @ApiModelProperty(value = "更改键")
    private java.lang.String updateKey;
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
    @ApiModelProperty(value = "修改时间")
    private java.util.Date modifyTime;
    @ApiModelProperty(value = "创建人")
    private java.lang.String createUser;
    @ApiModelProperty(value = "修改人")
    private java.lang.String modifyUser;
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
}

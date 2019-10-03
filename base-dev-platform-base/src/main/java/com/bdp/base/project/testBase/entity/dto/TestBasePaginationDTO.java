/**
* 基础场景-测试基础场景表-这是一个测试基础场景的示例
* <p>完成日期：2019-10-02 19:31:08</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.testBase.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TestBasePaginationDTO {
/*********************************属性*********************************/
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "编码")
    private java.lang.String code;
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
    @ApiModelProperty(value = "状态")
    private String stateName;
    private java.lang.Integer state;
    @ApiModelProperty(value = "创建时间")
    private String createTimeName;
    private java.util.Date createTime;
    @ApiModelProperty(value = "修改时间")
    private String modifyTimeName;
    private java.util.Date modifyTime;
    @ApiModelProperty(value = "生日")
    private String birthDateName;
    private java.util.Date birthDate;
    @ApiModelProperty(value = "时刻")
    private java.util.Date times;
}

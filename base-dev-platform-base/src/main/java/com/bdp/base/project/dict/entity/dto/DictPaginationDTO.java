/**
* 基础场景-数据字典表-数据字典表
* <p>完成日期：2019-10-04 13:15:02</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dict.entity.dto;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
@Data
public class DictPaginationDTO {
/*********************************属性*********************************/
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "编码")
    private java.lang.String code;
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
    @ApiModelProperty(value = "类型")
    private java.lang.Integer types;
    @ApiModelProperty(value = "类型名称")
    private String typesName;
    @ApiModelProperty(value = "表名称")
    private java.lang.String tableName;
    @ApiModelProperty(value = "值字段")
    private java.lang.String valueField;
    @ApiModelProperty(value = "名称字段")
    private java.lang.String nameField;
}

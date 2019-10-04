/**
* 基础场景-区域表-区域表
* <p>完成日期：2019-10-04 13:14:59</p>
* @varsion 1.0
* @author hj
*/
 package com.bdp.base.project.area.entity.dto;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;
import com.bdp.jdbc.base.entity.dto.QueryByIdResponseDTO;
import io.swagger.annotations.ApiModelProperty;
@Data
public class AreaQueryByIdResponseDTO extends QueryByIdResponseDTO {
/*********************************属性*********************************/
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "编码")
    private java.lang.String code;
    @ApiModelProperty(value = "省")
    private java.lang.String provinceCode;
    @ApiModelProperty(value = "市")
    private java.lang.String cityCode;
    @ApiModelProperty(value = "区县")
    private java.lang.String countyCode;
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
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
    @ApiModelProperty(value = "名称路径")
    private java.lang.String namePath;
    @ApiModelProperty(value = "级别")
    private java.lang.Integer level;
    @ApiModelProperty(value = "左值")
    private java.lang.Integer leftValue;
    @ApiModelProperty(value = "右值")
    private java.lang.Integer rightValue;
    @ApiModelProperty(value = "叶子")
    private java.lang.Integer leaf;
    @ApiModelProperty(value = "叶子名称")
    private String leafName;
    @ApiModelProperty(value = "上级")
    private java.lang.Long parentId;
    @ApiModelProperty(value = "上级名称")
    private String parentIdName;
}

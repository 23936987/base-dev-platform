/**
* 基础场景-数据字典明细表-数据字典明细表
* <p>完成日期：2019-10-04 13:15:03</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dictList.entity.dto;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
@Data
public class DictListPaginationDTO {
/*********************************属性*********************************/
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "编码")
    private java.lang.String code;
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
    @ApiModelProperty(value = "数据字典主键")
    private java.lang.String dictId;
    @ApiModelProperty(value = "数据字典主键名称")
    private String dictIdName;
}

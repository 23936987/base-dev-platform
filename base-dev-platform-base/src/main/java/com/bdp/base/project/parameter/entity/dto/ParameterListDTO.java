/**
* 基础场景-参数配置表-参数配置表
* <p>完成日期：2019-10-04 13:15:03</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.parameter.entity.dto;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
@Data
public class ParameterListDTO{
/*********************************属性*********************************/
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "参数编码")
    private java.lang.String paramCode;
    @ApiModelProperty(value = "参数值")
    private java.lang.String paramValue;
}

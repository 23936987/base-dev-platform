/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-04 13:15:01</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.contrast.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
@Data
public class ContrastSaveDTO {
/*********************************属性*********************************/
    @ApiModelProperty(value = "英文名称")
    private java.lang.String nameEn;
    @ApiModelProperty(value = "中文名称")
    private java.lang.String nameCn;
}

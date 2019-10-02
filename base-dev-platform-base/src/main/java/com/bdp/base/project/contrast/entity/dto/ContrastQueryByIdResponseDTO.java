/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-01 08:14:13</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.contrast.entity.dto;

import com.bdp.jdbc.base.entity.dto.QueryByIdResponseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ContrastQueryByIdResponseDTO extends QueryByIdResponseDTO {
/*********************************属性*********************************/
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "英文名称")
    private java.lang.String nameEn;
    @ApiModelProperty(value = "中文名称")
    private java.lang.String nameCn;
}

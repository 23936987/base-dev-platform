package com.bdp.base.project.test.entity.dto;

import com.bdp.jdbc.base.entity.dto.QueryByIdResponseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TestQueryByIdResponseDTO extends QueryByIdResponseDTO {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "名称")
    private String name;
}

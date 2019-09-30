package com.bdp.jdbc.base.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PaginationResponseDTO<T> {
    @ApiModelProperty(value = "总数")
    private Long total;
    @ApiModelProperty(value = "编码")
    private List<T> rows;
}

package com.bdp.jdbc.base.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ListResponseDTO<T> {
    @ApiModelProperty(value = "编码")
    private List<T> rows;
}

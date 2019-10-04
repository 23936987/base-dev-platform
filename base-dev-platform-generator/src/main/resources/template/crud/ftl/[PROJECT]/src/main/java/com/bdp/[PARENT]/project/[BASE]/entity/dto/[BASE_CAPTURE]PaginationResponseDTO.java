<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.entity.dto;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;
import com.bdp.jdbc.base.entity.dto.PaginationResponseDTO;
import io.swagger.annotations.ApiModelProperty;

@Data
public class ${baseCapture}PaginationResponseDTO extends PaginationResponseDTO<${baseCapture}PaginationDTO> {
}




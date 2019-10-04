<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.entity.dto;
import com.bdp.jdbc.base.entity.dto.ListResponseDTO;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class ${baseCapture}ListResponseDTO extends ListResponseDTO<${baseCapture}ListDTO> {

}

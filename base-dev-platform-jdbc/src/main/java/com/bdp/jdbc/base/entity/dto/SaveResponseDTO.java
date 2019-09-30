package com.bdp.jdbc.base.entity.dto;

import com.bdp.jdbc.dto.RequestDTO;
import com.bdp.jdbc.dto.ResponseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/***
 *
 * @ClassName: UpdateDTO
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/9/27 21:16
 * @version : 1.0
 */

@Data
public class SaveResponseDTO extends ResponseDTO {
    @ApiModelProperty(value = "主键")
    private String id;
}




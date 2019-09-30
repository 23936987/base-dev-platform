package com.bdp.jdbc.base.entity.dto;

import com.bdp.jdbc.dto.ResponseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 *
 * @ClassName: UpdateDTO
 * @Description: TODO
 * @Auther: yecao
 * @Date: 2019/9/27 21:16
 * @version : 1.0
 */

@Data
public class DeleteResponseDTO extends ResponseDTO {
    @ApiModelProperty(value = "影响行数,0:没有操作,大于 1")
    private Integer result;
}




package com.bdp.jdbc.base.entity.dto;

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
public class UpdateDTO {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "属性列表,实体key为键,value为值")
    private Map<String,Object> props;
}




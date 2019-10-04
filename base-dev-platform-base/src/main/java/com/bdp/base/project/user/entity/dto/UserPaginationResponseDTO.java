/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 10:08:20</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.user.entity.dto;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;
import com.bdp.jdbc.base.entity.dto.PaginationResponseDTO;
import io.swagger.annotations.ApiModelProperty;

@Data
public class UserPaginationResponseDTO extends PaginationResponseDTO<UserPaginationDTO> {
}




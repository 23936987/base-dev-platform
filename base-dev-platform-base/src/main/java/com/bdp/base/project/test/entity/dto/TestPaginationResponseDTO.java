/**
* 基础场景-测试表现-测试基础框架功能
* <p>完成日期：2019-10-04 13:12:42</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.test.entity.dto;
import com.bdp.jdbc.annotation.*;
import com.bdp.jdbc.base.entity.po.Entity;
import lombok.Data;
import com.bdp.jdbc.base.entity.dto.PaginationResponseDTO;
import io.swagger.annotations.ApiModelProperty;

@Data
public class TestPaginationResponseDTO extends PaginationResponseDTO<TestPaginationDTO> {
}




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
import io.swagger.annotations.ApiModelProperty;
@Data
public class TestListDTO{
/*********************************属性*********************************/
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "编码")
    private java.lang.String code;
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
}

package com.bdp.jdbc.base.entity.dto;

import com.bdp.jdbc.db.QueryItem;
import com.bdp.jdbc.dto.RequestDTO;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Pager<V> extends RequestDTO {
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize=10;
    @ApiModelProperty(value = "当前页码")
    private Integer page=1;
    @ApiModelProperty(value = "查询条件")
    private List<QueryItem> params = new ArrayList<>();
    @ApiModelProperty(value = "排序")
    private String orderBy="";
    @Ignore
    private Long total=0l;
    @Ignore
    private List<V> rows=new ArrayList<>();
}

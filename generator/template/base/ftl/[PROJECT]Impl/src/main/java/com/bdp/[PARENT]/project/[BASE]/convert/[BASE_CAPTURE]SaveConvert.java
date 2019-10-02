<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.convert;

import com.bdp.${parent}.project.${base}.entity.dto.${baseCapture}SaveDTO;
import com.bdp.${parent}.project.${base}.entity.po.${baseCapture}Entity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface ${baseCapture}SaveConvert extends Converter<${baseCapture}Entity, ${baseCapture}SaveDTO> {
    ${baseCapture}SaveConvert INSTANCE = Mappers.getMapper(${baseCapture}SaveConvert.class);
    @Mappings({
    })
    @Override
    public ${baseCapture}SaveDTO domain2dto(${baseCapture}Entity entity);
    @Mappings({
    })
    @Override
    public List<${baseCapture}SaveDTO> domain2dto(List<${baseCapture}Entity> entitys);
    @Mappings({
    })
    @Override
    public ${baseCapture}Entity dto2domain(${baseCapture}SaveDTO domain);
    @Mappings({
    })
    @Override
    public List<${baseCapture}Entity> dto2domain(List<${baseCapture}SaveDTO> domains);
}

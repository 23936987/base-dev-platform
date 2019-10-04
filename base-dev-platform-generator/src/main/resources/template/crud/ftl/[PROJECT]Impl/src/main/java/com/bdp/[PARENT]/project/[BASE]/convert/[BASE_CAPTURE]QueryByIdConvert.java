<#include "/include/comment.ftl"/>
package com.bdp.${parent}.project.${base}.convert;

import com.bdp.${parent}.project.${base}.entity.dto.${baseCapture}QueryByIdResponseDTO;
import com.bdp.${parent}.project.${base}.entity.po.${baseCapture}Entity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ${baseCapture}QueryByIdConvert extends Converter<${baseCapture}Entity, ${baseCapture}QueryByIdResponseDTO> {
    ${baseCapture}QueryByIdConvert INSTANCE = Mappers.getMapper(${baseCapture}QueryByIdConvert.class);
    @Mappings({
    })
    @Override
    public ${baseCapture}QueryByIdResponseDTO domain2dto(${baseCapture}Entity entity);
    @Mappings({
    })
    @Override
    public List<${baseCapture}QueryByIdResponseDTO> domain2dto(List<${baseCapture}Entity> entitys);
    @Mappings({
    })
    @Override
    public ${baseCapture}Entity dto2domain(${baseCapture}QueryByIdResponseDTO domain);
    @Mappings({
    })
    @Override
    public List<${baseCapture}Entity> dto2domain(List<${baseCapture}QueryByIdResponseDTO> domains);
}

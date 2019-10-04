/**
* 基础场景-参数配置表-参数配置表
* <p>完成日期：2019-10-04 13:15:03</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.parameter.convert;

import com.bdp.base.project.parameter.entity.dto.ParameterQueryByIdResponseDTO;
import com.bdp.base.project.parameter.entity.po.ParameterEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ParameterQueryByIdConvert extends Converter<ParameterEntity, ParameterQueryByIdResponseDTO> {
    ParameterQueryByIdConvert INSTANCE = Mappers.getMapper(ParameterQueryByIdConvert.class);
    @Mappings({
    })
    @Override
    public ParameterQueryByIdResponseDTO domain2dto(ParameterEntity entity);
    @Mappings({
    })
    @Override
    public List<ParameterQueryByIdResponseDTO> domain2dto(List<ParameterEntity> entitys);
    @Mappings({
    })
    @Override
    public ParameterEntity dto2domain(ParameterQueryByIdResponseDTO domain);
    @Mappings({
    })
    @Override
    public List<ParameterEntity> dto2domain(List<ParameterQueryByIdResponseDTO> domains);
}

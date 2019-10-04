/**
* 基础场景-参数配置表-参数配置表
* <p>完成日期：2019-10-04 10:08:18</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.parameter.convert;

import com.bdp.base.project.parameter.entity.dto.ParameterSaveDTO;
import com.bdp.base.project.parameter.entity.po.ParameterEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface ParameterSaveConvert extends Converter<ParameterEntity, ParameterSaveDTO> {
    ParameterSaveConvert INSTANCE = Mappers.getMapper(ParameterSaveConvert.class);
    @Mappings({
    })
    @Override
    public ParameterSaveDTO domain2dto(ParameterEntity entity);
    @Mappings({
    })
    @Override
    public List<ParameterSaveDTO> domain2dto(List<ParameterEntity> entitys);
    @Mappings({
    })
    @Override
    public ParameterEntity dto2domain(ParameterSaveDTO domain);
    @Mappings({
    })
    @Override
    public List<ParameterEntity> dto2domain(List<ParameterSaveDTO> domains);
}

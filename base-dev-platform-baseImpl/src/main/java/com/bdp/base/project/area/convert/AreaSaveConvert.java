/**
* 基础场景-区域表-区域表
* <p>完成日期：2019-10-04 13:14:59</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.area.convert;

import com.bdp.base.project.area.entity.dto.AreaSaveDTO;
import com.bdp.base.project.area.entity.po.AreaEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface AreaSaveConvert extends Converter<AreaEntity, AreaSaveDTO> {
    AreaSaveConvert INSTANCE = Mappers.getMapper(AreaSaveConvert.class);
    @Mappings({
    })
    @Override
    public AreaSaveDTO domain2dto(AreaEntity entity);
    @Mappings({
    })
    @Override
    public List<AreaSaveDTO> domain2dto(List<AreaEntity> entitys);
    @Mappings({
    })
    @Override
    public AreaEntity dto2domain(AreaSaveDTO domain);
    @Mappings({
    })
    @Override
    public List<AreaEntity> dto2domain(List<AreaSaveDTO> domains);
}

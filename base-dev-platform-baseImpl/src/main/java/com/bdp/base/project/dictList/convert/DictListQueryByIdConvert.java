/**
* 基础场景-数据字典明细表-数据字典明细表
* <p>完成日期：2019-10-04 13:15:03</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dictList.convert;

import com.bdp.base.project.dictList.entity.dto.DictListQueryByIdResponseDTO;
import com.bdp.base.project.dictList.entity.po.DictListEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DictListQueryByIdConvert extends Converter<DictListEntity, DictListQueryByIdResponseDTO> {
    DictListQueryByIdConvert INSTANCE = Mappers.getMapper(DictListQueryByIdConvert.class);
    @Mappings({
    })
    @Override
    public DictListQueryByIdResponseDTO domain2dto(DictListEntity entity);
    @Mappings({
    })
    @Override
    public List<DictListQueryByIdResponseDTO> domain2dto(List<DictListEntity> entitys);
    @Mappings({
    })
    @Override
    public DictListEntity dto2domain(DictListQueryByIdResponseDTO domain);
    @Mappings({
    })
    @Override
    public List<DictListEntity> dto2domain(List<DictListQueryByIdResponseDTO> domains);
}

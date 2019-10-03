/**
* 基础场景-数据字典表-数据字典表
* <p>完成日期：2019-10-02 19:39:27</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dict.convert;

import com.bdp.base.project.dict.entity.dto.DictQueryByIdResponseDTO;
import com.bdp.base.project.dict.entity.po.DictEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DictQueryByIdConvert extends Converter<DictEntity, DictQueryByIdResponseDTO> {
    DictQueryByIdConvert INSTANCE = Mappers.getMapper(DictQueryByIdConvert.class);
    @Mappings({
    })
    @Override
    public DictQueryByIdResponseDTO domain2dto(DictEntity entity);
    @Mappings({
    })
    @Override
    public List<DictQueryByIdResponseDTO> domain2dto(List<DictEntity> entitys);
    @Mappings({
    })
    @Override
    public DictEntity dto2domain(DictQueryByIdResponseDTO domain);
    @Mappings({
    })
    @Override
    public List<DictEntity> dto2domain(List<DictQueryByIdResponseDTO> domains);
}

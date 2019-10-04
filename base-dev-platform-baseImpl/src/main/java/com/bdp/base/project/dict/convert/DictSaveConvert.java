/**
* 基础场景-数据字典表-数据字典表
* <p>完成日期：2019-10-04 13:15:02</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dict.convert;

import com.bdp.base.project.dict.entity.dto.DictSaveDTO;
import com.bdp.base.project.dict.entity.po.DictEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface DictSaveConvert extends Converter<DictEntity, DictSaveDTO> {
    DictSaveConvert INSTANCE = Mappers.getMapper(DictSaveConvert.class);
    @Mappings({
    })
    @Override
    public DictSaveDTO domain2dto(DictEntity entity);
    @Mappings({
    })
    @Override
    public List<DictSaveDTO> domain2dto(List<DictEntity> entitys);
    @Mappings({
    })
    @Override
    public DictEntity dto2domain(DictSaveDTO domain);
    @Mappings({
    })
    @Override
    public List<DictEntity> dto2domain(List<DictSaveDTO> domains);
}

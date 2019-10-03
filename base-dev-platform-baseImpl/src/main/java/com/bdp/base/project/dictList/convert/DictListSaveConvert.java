/**
* 基础场景-数据字典明细表-数据字典明细表
* <p>完成日期：2019-10-02 19:39:29</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.dictList.convert;

import com.bdp.base.project.dictList.entity.dto.DictListSaveDTO;
import com.bdp.base.project.dictList.entity.po.DictListEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface DictListSaveConvert extends Converter<DictListEntity, DictListSaveDTO> {
    DictListSaveConvert INSTANCE = Mappers.getMapper(DictListSaveConvert.class);
    @Mappings({
    })
    @Override
    public DictListSaveDTO domain2dto(DictListEntity entity);
    @Mappings({
    })
    @Override
    public List<DictListSaveDTO> domain2dto(List<DictListEntity> entitys);
    @Mappings({
    })
    @Override
    public DictListEntity dto2domain(DictListSaveDTO domain);
    @Mappings({
    })
    @Override
    public List<DictListEntity> dto2domain(List<DictListSaveDTO> domains);
}

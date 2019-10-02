/**
* 基础场景-中英对照表-中英对照表
* <p>完成日期：2019-10-01 08:14:13</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.contrast.convert;

import com.bdp.base.project.contrast.entity.dto.ContrastQueryByIdResponseDTO;
import com.bdp.base.project.contrast.entity.po.ContrastEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContrastQueryByIdConvert extends Converter<ContrastEntity, ContrastQueryByIdResponseDTO> {
    ContrastQueryByIdConvert INSTANCE = Mappers.getMapper(ContrastQueryByIdConvert.class);
    @Mappings({
    })
    @Override
    public ContrastQueryByIdResponseDTO domain2dto(ContrastEntity entity);
    @Mappings({
    })
    @Override
    public List<ContrastQueryByIdResponseDTO> domain2dto(List<ContrastEntity> entitys);
    @Mappings({
    })
    @Override
    public ContrastEntity dto2domain(ContrastQueryByIdResponseDTO domain);
    @Mappings({
    })
    @Override
    public List<ContrastEntity> dto2domain(List<ContrastQueryByIdResponseDTO> domains);
}

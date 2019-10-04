/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 10:08:20</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.user.convert;

import com.bdp.base.project.user.entity.dto.UserQueryByIdResponseDTO;
import com.bdp.base.project.user.entity.po.UserEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserQueryByIdConvert extends Converter<UserEntity, UserQueryByIdResponseDTO> {
    UserQueryByIdConvert INSTANCE = Mappers.getMapper(UserQueryByIdConvert.class);
    @Mappings({
    })
    @Override
    public UserQueryByIdResponseDTO domain2dto(UserEntity entity);
    @Mappings({
    })
    @Override
    public List<UserQueryByIdResponseDTO> domain2dto(List<UserEntity> entitys);
    @Mappings({
    })
    @Override
    public UserEntity dto2domain(UserQueryByIdResponseDTO domain);
    @Mappings({
    })
    @Override
    public List<UserEntity> dto2domain(List<UserQueryByIdResponseDTO> domains);
}

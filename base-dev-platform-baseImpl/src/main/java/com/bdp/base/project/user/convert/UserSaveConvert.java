/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 13:15:04</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.user.convert;

import com.bdp.base.project.user.entity.dto.UserSaveDTO;
import com.bdp.base.project.user.entity.po.UserEntity;
import com.bdp.jdbc.base.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface UserSaveConvert extends Converter<UserEntity, UserSaveDTO> {
    UserSaveConvert INSTANCE = Mappers.getMapper(UserSaveConvert.class);
    @Mappings({
    })
    @Override
    public UserSaveDTO domain2dto(UserEntity entity);
    @Mappings({
    })
    @Override
    public List<UserSaveDTO> domain2dto(List<UserEntity> entitys);
    @Mappings({
    })
    @Override
    public UserEntity dto2domain(UserSaveDTO domain);
    @Mappings({
    })
    @Override
    public List<UserEntity> dto2domain(List<UserSaveDTO> domains);
}

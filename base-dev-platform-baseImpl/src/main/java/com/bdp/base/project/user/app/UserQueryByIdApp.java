/**
* 基础场景-系统用户表-系统用户表
* <p>完成日期：2019-10-04 13:15:04</p>
* @varsion 1.0
* @author hj
*/
package com.bdp.base.project.user.app;

import com.bdp.base.project.user.convert.UserQueryByIdConvert;
import com.bdp.base.project.user.entity.dto.UserQueryByIdResponseDTO;
import com.bdp.base.project.user.entity.po.UserEntity;
import com.bdp.jdbc.base.app.BaseQueryForIdApp;
import com.bdp.jdbc.base.domain.BaseDomain;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("user.queryById")
public class UserQueryByIdApp extends BaseQueryForIdApp<UserQueryByIdResponseDTO,UserEntity> {

    public UserQueryByIdApp(){
        this.dtoClass = UserQueryByIdResponseDTO.class;
        this.converter= UserQueryByIdConvert.INSTANCE;
    }
    @Resource(name="user.queryByIdDomain")
    @Override
    public void setDomain(BaseDomain domain) {
        super.setDomain(domain);
    }

}
